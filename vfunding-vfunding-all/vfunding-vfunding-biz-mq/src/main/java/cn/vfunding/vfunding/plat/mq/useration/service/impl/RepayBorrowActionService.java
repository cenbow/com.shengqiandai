package cn.vfunding.vfunding.plat.mq.useration.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;
import cn.vfunding.vfunding.biz.common.vo.RepaymentVO;
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
public class RepayBorrowActionService {

	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("actionlog");

	@Autowired
	private IBorrowService borrowService;

	@Autowired
	private UserActionSynchroDataService synchroDataService;

	@Autowired
	private IJmsLogService mapper;

	@Async
	public void doRepayment(AfterActionMessage msg, boolean isRealBorrow) {
			try {
				String repaymentResult = msg.getMethodReturn().toString();
				if (repaymentResult.equals("还款成功")) {
					String repaymentId = (String) msg.getMethodArgs()[0];
					// 还款后数据同步
					this.doRepaymentBiz(Integer.parseInt(repaymentId),
							isRealBorrow, msg);
				} else {
					logger.info("*****[system  体验金活动已结束 体验金还款 失败]");
				}
				JmsLogUtil.doLog(mapper, msg.getMessageId(), true);
				logger.info("*****[system  还款MQ消息处理成功]");
			} catch (Exception e) {
				logger.error(e.getMessage());
				logger.error(e.getStackTrace().toString());
				logger.error(e.getMessage());
				JmsLogUtil.doLog(mapper, msg.getMessageId(), false);
			}

	}

	@Async
	public void weekRepayment(AfterActionMessage msg) {
		try {
			Json j = JSON.parseObject(msg.getMethodReturn().toString(),
					Json.class);
			Integer repaymentId = Integer.parseInt(msg.getMethodArgs()[0]
					.toString());
			if (j.getSuccess()) {
				// 还款后数据同步
				this.doRepaymentBiz(repaymentId, true, msg);
			} else {
				logger.info("*****[system  " + repaymentId + " 7天理财还款 失败]");
			}
			JmsLogUtil.doLog(mapper, msg.getMessageId(), true);
			logger.info("*****[system  7天理财还款MQ消息处理成功]");
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
			logger.error(e.getMessage());
			JmsLogUtil.doLog(mapper, msg.getMessageId(), false);
		}

	}

	/**
	 * 平台还款第三方数据同步
	 * 
	 * @param repaymentId
	 *            还款ID
	 * @param realBorrow
	 *            是否是真实标的还款 2014年12月31日 liuyijun
	 */
	private void doRepaymentBiz(Integer repaymentId, boolean realBorrow,
			AfterActionMessage msg) {
		// 还款成功后
		if (msg.getMethodReturn().equals("还款成功")) {
			logger.info("*****[system " + "repaymentId:" + repaymentId
					+ " 还款短信 开始]");
			// 还款成功,发送还款短信
			this.borrowService.updateRepayBorrowAfter(repaymentId);
			// 同步新浪借款人余额和新浪投资人余额(批量)
			logger.info("*****[system " + "repaymentId:" + repaymentId
					+ " 还款短信 结束]");

			logger.info("*****[sina " + "repaymentId:" + repaymentId
					+ " 还款同步 开始]");
			synchroDataService.doSynchroData(repaymentId,
					"/sinaSendAction/doUserRepayBorrowerAndTender/"
							+ repaymentId, msg.getActionName(), "sina",
					repaymentId);
			logger.info("*****[sina " + "repaymentId:" + repaymentId
					+ " 还款同步 结束]");
			RepaymentVO vo = new RepaymentVO();
			vo.setRealBorrow(realBorrow);
			vo.setRepaymentId(repaymentId);

			logger.info("*****[cjd " + "repaymentId:" + repaymentId
					+ " 还款同步 开始]");
			// 同步财经道用户余额
			synchroDataService.doSynchroData(vo, "/cjdUserAction/repayment",
					msg.getActionName(), "cjd", repaymentId);
			logger.info("*****[cjd " + "repaymentId:" + repaymentId
					+ " 还款同步 结束]");
		} else {
			logger.info("*****[system " + "repaymentId:" + repaymentId
					+ " 还款 失败]:[还款失败无需同步数据]");
		}
	}

}
