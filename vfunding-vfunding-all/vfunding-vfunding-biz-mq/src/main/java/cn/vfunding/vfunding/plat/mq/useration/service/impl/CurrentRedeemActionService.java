package cn.vfunding.vfunding.plat.mq.useration.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import cn.p2p.p2p.biz.current.vo.UserAccountActionResultVO;
import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.vfunding.common.framework.lock.NeedLock;
import cn.vfunding.vfunding.biz.system.service.IJmsLogService;
import cn.vfunding.vfunding.common.module.activemq.message.afteraction.AfterActionMessage;

import com.alibaba.fastjson.JSON;

/**
 * 活期投资
 * 
 * @author carry
 * 
 */
@Component
public class CurrentRedeemActionService {

	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("actionlog");

	@Autowired
	private IJmsLogService mapper;
	@Autowired
	private UserActionSynchroDataService synchroDataService;

	/**
	 * 用户成功投资后相关辅助业务及第三方平台数据同步
	 * 
	 * @param vo
	 *            成功投资的返回对象 2014年12月31日 liuyijun
	 */
	@Async
	@NeedLock
	public void doUserTender(AfterActionMessage msg) {
		try {
			MobileBaseResponse mbr = JSON.parseObject(msg.getMethodReturn().toString(), MobileBaseResponse.class);
			UserAccountActionResultVO vo = (UserAccountActionResultVO) mbr.getResponseObject();
			this.doUserRedeemBiz(vo, msg.getActionName());
			JmsLogUtil.doLog(mapper, msg.getMessageId(), true);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
			logger.error(e.getMessage());
			JmsLogUtil.doLog(mapper, msg.getMessageId(), false);
		}
	}

	public void doUserRedeemBiz(UserAccountActionResultVO vo, String actionName) {
		// 新浪用户账户信息数据同步
		logger.info("*****[sina " + "userId:" + vo.getUserId() + "|tenderId:" + vo.getTenderId() + " 投资同步 开始]");
		synchroDataService.doSynchroData(vo, "/sinaSendAction/doCurrentRedeem", actionName, "sina", vo.getUserId());
		logger.info("*****[sina " + "userId:" + vo.getUserId() + "|tenderId:" + vo.getTenderId() + " 投资同步 结束]");
	}
}
