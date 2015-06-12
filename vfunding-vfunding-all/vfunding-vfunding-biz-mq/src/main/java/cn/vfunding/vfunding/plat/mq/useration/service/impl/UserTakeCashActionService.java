package cn.vfunding.vfunding.plat.mq.useration.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.model.AccountCash;
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
public class UserTakeCashActionService {

	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("actionlog");

	@Autowired
	private IThirdRelationshipService thirdRelationshipService;
	@Autowired
	private UserActionSynchroDataService synchroDataService;
	@Autowired
	private IJmsLogService mapper;

	@Async
	public void doUserAccountCash(AfterActionMessage msg) {
			try {
				String cashResult = msg.getMethodReturn().toString();
				AccountCash cash = JSON.parseObject(
						msg.getMethodArgs()[0].toString(), AccountCash.class);
				if (cashResult.equals("1")) {// 提现成功
					// 提现后第三方数据同步
					this.doUserAccountCashBiz(cash, msg);
				} else {
					logger.info("*****[system " + cash.getUserId() + " 提现 失败]");
				}
				JmsLogUtil.doLog(mapper, msg.getMessageId(), true);
				logger.info("*****[system " + cash.getUserId() + " 提现MQ消息处理成功]");
			} catch (Exception e) {
				logger.error(e.getMessage());
				logger.error(e.getStackTrace().toString());
				logger.error(e.getMessage());
				JmsLogUtil.doLog(mapper, msg.getMessageId(), false);
			}
	}

	/**
	 * 用户提现成功后第三方平台数据同步
	 * 
	 * @param cash
	 *            提现记录对象 2014年12月31日 liuyijun
	 */

	private void doUserAccountCashBiz(AccountCash cash, AfterActionMessage msg) {
		// 提现成功
		Integer userId = cash.getUserId();
		logger.info("*****[cjd " + "userId:" + cash.getUserId() + " 提现同步 开始]");
		// 财经道同步
		ThirdRelationship ship = this.thirdRelationshipService
				.selectByUserIdType(userId, 1);
		if (EmptyUtil.isNotEmpty(ship)) {
			// 财经道用户余额同步
			synchroDataService.doSynchroData(cash, "/cjdUserAction/userCash",
					msg.getActionName(), "cjd", userId);
		} else {
			logger.info("*****[system " + "userId:" + cash.getUserId()
					+ " 提现同步 失败]:[不属于财经道]");
		}
		logger.info("*****[cjd " + "userId:" + cash.getUserId() + " 提现同步 结束]");

	}

}
