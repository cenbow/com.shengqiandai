package cn.vfunding.vfunding.plat.mq.useration.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.model.AccountRecharge;
import cn.vfunding.vfunding.biz.account.service.IAccountRechargeService;
import cn.vfunding.vfunding.biz.sina.vo.notify.RechargeNotifyVO;
import cn.vfunding.vfunding.biz.system.service.IJmsLogService;
import cn.vfunding.vfunding.biz.thirdplat.model.ThirdRelationship;
import cn.vfunding.vfunding.biz.thirdplat.service.IThirdRelationshipService;
import cn.vfunding.vfunding.common.module.activemq.message.afteraction.AfterActionMessage;

import com.alibaba.fastjson.JSON;

/**
 * 用户成功投资后相关业务处理服务类
 * 
 * @author liuyijun
 * 
 */
@Component
public class SinaNotifyRechargeService {

	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("actionlog");

	@Autowired
	private IAccountRechargeService accountRechargeService;

	@Autowired
	private IThirdRelationshipService thirdRelationshipService;
	@Autowired
	private UserActionSynchroDataService synchroDataService;

	@Autowired
	private IJmsLogService mapper;

	@Async
	public void dosinaNotifyRecharge(AfterActionMessage msg) {
			try {
				String r = msg.getMethodReturn().toString();
				RechargeNotifyVO vo = JSON.parseObject(
						msg.getMethodArgs()[0].toString(),
						RechargeNotifyVO.class);
				if (r.equals("SUCCESS")) {
					// 新浪回调成功
					this.dosinaNotifyRechargeBiz(vo, msg.getActionName());
				} else {
					logger.info("*****[system 新浪回调 充值  无需处理]");
				}
				JmsLogUtil.doLog(mapper, msg.getMessageId(), true);
				logger.info("*****[system 新浪回调 充值MQ消息处理成功]");
			} catch (Exception e) {
				logger.error(e.getMessage());
				logger.error(e.getStackTrace().toString());
				logger.error(e.getMessage());
				JmsLogUtil.doLog(mapper, msg.getMessageId(), false);
			}
	}

	private void dosinaNotifyRechargeBiz(RechargeNotifyVO vo, String actionName) {
		// 新浪回调成功
		String outerTradeNo = vo.getOuter_trade_no(); // 流水号
		AccountRecharge rec = accountRechargeService
				.selectByTradeNo(outerTradeNo);
		if (EmptyUtil.isNotEmpty(rec) && rec.getStatus().intValue() == 1) {
			// 充值成功后第三方数据同步
			this.doUserRechargeBiz(rec, actionName);
		} else {
			logger.info("*****[system 新浪回调 充值(tradeNo=" + outerTradeNo
					+ ") 状态异常无需处理]");
		}
	}

	/**
	 * 用户充值后第三方合作平台相关数据同步
	 * 
	 * @param rec
	 *            充值记录对象 2014年12月31日 liuyijun
	 */
	private void doUserRechargeBiz(AccountRecharge rec, String actionName) {
		logger.info("*****[cjd " + "userId:" + rec.getUserId() + " 余额同步 开始]");
		// 财经道余额同步
		Integer userId = rec.getUserId();
		ThirdRelationship ship = this.thirdRelationshipService
				.selectByUserIdType(userId, 1);
		if (EmptyUtil.isNotEmpty(ship)) {
			synchroDataService.doSynchroData(rec,
					"/cjdUserAction/userRecharge", actionName, "cjd", userId);
		} else {
			logger.info("*****[system " + "userId:" + rec.getUserId()
					+ " 余额同步 失败]:[不属于财经道]");
		}
		logger.info("*****[cjd " + "userId:" + rec.getUserId() + " 余额同步 结束]");
	}

}
