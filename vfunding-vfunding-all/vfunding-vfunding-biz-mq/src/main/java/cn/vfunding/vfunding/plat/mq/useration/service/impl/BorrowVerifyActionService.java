package cn.vfunding.vfunding.plat.mq.useration.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import cn.vfunding.vfunding.biz.common.vo.FinalVerifyVO;
import cn.vfunding.vfunding.biz.system.service.IJmsLogService;
import cn.vfunding.vfunding.common.module.activemq.message.afteraction.AfterActionMessage;

import com.alibaba.fastjson.JSON;

/**
 * 用户成功投资后相关业务处理服务类
 * 
 * @author liuyijun
 * 
 */
@Component
public class BorrowVerifyActionService {

	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("actionlog");

	@Autowired
	private UserActionSynchroDataService synchroDataService;

	@Autowired
	private IJmsLogService mapper;

	@Async
	public void doBorrowVerify(AfterActionMessage msg) {
		try {
			String bvStatus = msg.getMethodReturn().toString();
			FinalVerifyVO verifyVO = JSON.parseObject(
					msg.getMethodArgs()[0].toString(), FinalVerifyVO.class);// 方法参数
			if (bvStatus.equals("成功")) {
				// 复审通过后相关第三方数据同步业务处理
				this.doBorrowVerifyBiz(verifyVO, msg.getActionName());
			} else {
				logger.info("*****[system " + verifyVO.getBorrowId()
						+ " 复审 失败]");
			}
			JmsLogUtil.doLog(mapper, msg.getMessageId(), true);
			logger.info("*****[system " + "borrowId:" + verifyVO.getBorrowId()
					+ "标的复审MQ消息处理成功]");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
			JmsLogUtil.doLog(mapper, msg.getMessageId(), false);
		}

	}

	/**
	 * 复审第三方平台数据同步
	 * 
	 * @param verifyVO
	 *            复审对象 2014年12月31日 liuyijun
	 */
	private void doBorrowVerifyBiz(FinalVerifyVO verifyVO, String actionName) {
		// 相关活动->plat-activity处理
		logger.info("*****[activity " + "borrowId:" + verifyVO.getBorrowId()
				+ " 集团返利 请求开始]");
		synchroDataService.doSynchroData(verifyVO, "/rebate/internalRebate",
				actionName, "activity", 0);
		logger.info("*****[activity " + "borrowId:" + verifyVO.getBorrowId()
				+ " 集团返利 请求结束]");

		logger.info("*****[activity " + "borrowId:" + verifyVO.getBorrowId()
				+ " 加息券返利 请求开始]");
		synchroDataService.doSynchroData(verifyVO, "/rebate/hikesRebate",
				actionName, "activity", 0);
		logger.info("*****[activity " + "borrowId:" + verifyVO.getBorrowId()
				+ " 加息券返利 请求结束]");

		logger.info("*****[activity " + "borrowId:" + verifyVO.getBorrowId()
				+ " 好友返利 请求开始]");
		synchroDataService.doSynchroData(verifyVO, "/rebate/friendRebate",
				actionName, "activity", 0);
		logger.info("*****[activity " + "borrowId:" + verifyVO.getBorrowId()
				+ " 好友返利 请求结束]");

		logger.info("*****[activity " + "borrowId:" + verifyVO.getBorrowId()
				+ " 满标复审 请求开始]");
		synchroDataService
				.doSynchroData(verifyVO, "/activityUserAction/doborrowVerify",
						actionName, "activity", 0);
		logger.info("*****[activity " + "borrowId:" + verifyVO.getBorrowId()
				+ " 满标复审 请求结束]");

		// 新浪数据同步
		logger.info("*****[sina " + "borrowId:" + verifyVO.getBorrowId()
				+ " 复审同步 请求开始]");
		synchroDataService.doSynchroData(verifyVO,
				"/sinaSendAction/doBorrwoVerify", actionName, "sina", 0);
		logger.info("*****[sina " + "borrowId:" + verifyVO.getBorrowId()
				+ " 复审同步 请求结束]");
		// 财经道数据同步
		logger.info("*****[cjd " + "borrowId:" + verifyVO.getBorrowId()
				+ " 复审同步 请求开始]");
		synchroDataService.doSynchroData(verifyVO,
				"/cjdUserAction/borrwoVerify", actionName, "cjd", 0);
		logger.info("*****[cjd " + "borrowId:" + verifyVO.getBorrowId()
				+ " 复审同步 请求结束]");
	}

}
