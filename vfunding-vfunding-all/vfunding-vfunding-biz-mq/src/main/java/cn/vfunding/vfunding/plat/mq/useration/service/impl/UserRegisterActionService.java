package cn.vfunding.vfunding.plat.mq.useration.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import cn.vfunding.vfunding.biz.bms_employee.service.IEmployeeService;
import cn.vfunding.vfunding.biz.common.vo.CjdVO;
import cn.vfunding.vfunding.biz.common.vo.RegisterVO;
import cn.vfunding.vfunding.biz.system.service.IJmsLogService;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.common.module.activemq.message.CjdUserMessage;
import cn.vfunding.vfunding.common.module.activemq.message.UserEmpMessage;
import cn.vfunding.vfunding.common.module.activemq.message.afteraction.AfterActionMessage;

import com.alibaba.fastjson.JSON;

/**
 * 用户成功投资后相关业务处理服务类
 * 
 * @author liuyijun
 * 
 */
@Component
public class UserRegisterActionService {

	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("actionlog");

	@Autowired
	private IUserService userService;

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private UserActionSynchroDataService synchroDataService;

	@Autowired
	private IJmsLogService mapper;

	@Async
	public void doUserRegister(AfterActionMessage msg) {
		try {
			Integer r = Integer.parseInt(msg.getMethodReturn().toString());
			logger.info("注册消息对象AfterActionMessage:[" + JSON.toJSONString(msg) + "]");
			logger.info("注册对象RegisterVO:[" + JSON.toJSONString(msg.getMethodArgs()[0]) + "]:[开始转换对象类型为:RegisterVO]");
			RegisterVO vo = JSON.parseObject(msg.getMethodArgs()[0].toString(), RegisterVO.class);
			logger.info("注册对象RegisterVO:[]:[转换对象类型结束:RegisterVO]");
			if (r > 0) {// 注册成功
				this.doUserRegisterBiz(vo, msg);
			} else {
				logger.info("*****[system  " + vo.getUserName() + " 注册 失败]");
			}
			JmsLogUtil.doLog(mapper, msg.getMessageId(), true);
			logger.info("*****[system  " + vo.getUserName() + " 注册MQ消息处理成功]");
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
			logger.error(e.getMessage());
			JmsLogUtil.doLog(mapper, msg.getMessageId(), false);
		}
	}

	/**
	 * 用户成功投资后相关辅助业务及第三方平台数据同步
	 * 
	 * @param vo
	 *            成功投资的返回对象 2014年12月31日 liuyijun
	 */
	private void doUserRegisterBiz(RegisterVO vo, AfterActionMessage msg) {
		// 注册辅助业务处理
		this.doRegister(vo);
		// 注册相关活动处理
		synchroDataService.doSynchroData(vo, "/activityUserAction/userRegist", msg.getActionName(), "activity", vo.getRegisterUserId());
		// 新浪托管同步用户信息
		synchroDataService.doSynchroData(vo, "/sinaSendAction/register", msg.getActionName(), "sina", vo.getRegisterUserId());

		// 财经道用户信息同步
		this.cjdUserRegisterBiz(vo, msg);

	}

	/**
	 * 注册后相关辅助业务处理
	 * 
	 * @param vo
	 *            2015年1月14日 liuyijun
	 */
	private void doRegister(RegisterVO vo) {
		// 注册后用户账户及积分信息业务处理
		logger.info("*****[system " + vo.getRegisterUserId() + " 注册辅助表添加 开始]");
		this.userService.doRegisterInfo(vo.getRegisterUserId(), vo.getIp());
		logger.info("*****[system " + vo.getRegisterUserId() + " 注册辅助表添加 结束]");
		// 分配客服
		logger.info("*****[system " + vo.getRegisterUserId() + " 注册分配客服业务 开始]");
		UserEmpMessage userEmp = new UserEmpMessage();
		userEmp.setUserId(vo.getRegisterUserId());
		this.employeeService.insertUserEmpByReg(userEmp);
		logger.info("*****[system " + vo.getRegisterUserId() + " 注册分配客服业务 结束]");

	}

	/**
	 * 财经道用户注册后相关业务处理
	 * 
	 * @param vo
	 *            2015年1月14日 liuyijun
	 */
	private void cjdUserRegisterBiz(RegisterVO vo, AfterActionMessage msg) {
		if (vo.isCjd()) {
			CjdVO cjdvo = JSON.parseObject(msg.getMethodArgs()[1].toString(), CjdVO.class);
			Integer type = Integer.parseInt(msg.getMethodArgs()[3].toString());
			logger.info("*****[cjd " + vo.getRegisterUserId() + " 注册辅助业务 开始]");
			this.userService.registerForCjdAfter(vo, cjdvo, type);
			logger.info("*****[cjd " + vo.getRegisterUserId() + " 注册辅助业务 结束]");
			// 财经道注册同步
			logger.info("*****[cjd " + vo.getRegisterUserId() + " 注册同步 请求开始]");
			CjdUserMessage message = new CjdUserMessage();
			message.setType(1);// 1-注册同步
			message.setUserId(vo.getRegisterUserId());
			message.setUaccount(cjdvo.getUaccount());
			synchroDataService.doSynchroData(message, "/cjdUserAction/userRegister", msg.getActionName(), "cjd", message.getUserId());
			logger.info("*****[cjd " + vo.getRegisterUserId() + " 注册同步 请求结束]");
		} else {
			logger.info("*****[cjd " + vo.getRegisterUserId() + " 注册 无需同步]");
		}
	}

}
