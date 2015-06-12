package cn.vfunding.vfunding.plat.mq.useration.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.system.model.JmsLog;
import cn.vfunding.vfunding.biz.system.service.IJmsLogService;
import cn.vfunding.vfunding.common.module.activemq.message.afteraction.AfterActionMessage;
import cn.vfunding.vfunding.plat.mq.useraction.service.IUserActionService;

@Service("userActionService")
public class UserActionServiceImpl implements IUserActionService {

	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("actionlog");

	@Autowired
	private IJmsLogService jmsService;

	@Autowired
	private UserTenderActionService userTenderActionService;

	@Autowired
	private SinaNotifyRechargeService sinaNotifyRechargeService;

	@Autowired
	private UserTakeCashActionService userTakeCashActionService;

	@Autowired
	private SinaTakeCashActionService sinaTakeCashActionService;

	@Autowired
	private BorrowExamineActionService borrowExamineActionService;

	@Autowired
	private BorrowVerifyActionService borrowVerifyActionService;

	@Autowired
	private RepayBorrowActionService repayBorrowActionService;

	@Autowired
	private UserRegisterActionService userRegisterActionService;

	@Autowired
	private EmailVerifyActionService emailVerifyActionService;

	@Autowired
	private RealNameVerifyActionService realNameVerifyActionService;

	@Autowired
	private GiftBoxActionService giftBoxActionService;

	@Autowired
	private RechargeActionService rechargeActionService;
	@Autowired
	private CurrentRedeemActionService currentRedeemActionService;
	@Autowired
	private CurrentTenderActionService currentTenderActionService;

	@Override
	public void doUserAction(AfterActionMessage msg) {
		if (canAction(msg)) {
			// 消息接收
			this.doJmsLog(msg, true);
			if (msg.getActionName().equals("borrowTender")) {// 投资
				userTenderActionService.doUserTender(msg);
			}  else if (msg.getActionName().equals("notifyRecharge")) {// 新浪回调通知
				sinaNotifyRechargeService.dosinaNotifyRecharge(msg);
			} else if (msg.getActionName().equals("recharge")) {// 充值
				rechargeActionService.doUserRecharge(msg);
			} else if (msg.getActionName().equals("takeCash")) {// 用户提现
				userTakeCashActionService.doUserAccountCash(msg);
			} else if (msg.getActionName().equals("sinaTakeCash")) {// 新浪提现处理
				sinaTakeCashActionService.doSinaTakeCash(msg);
			} else if (msg.getActionName().equals("borrowExamine")) {// 初审
				borrowExamineActionService.doBorrowExamine(msg);
			} else if (msg.getActionName().equals("borrowVerify")) {// 复审
				borrowVerifyActionService.doBorrowVerify(msg);
			} else if (msg.getActionName().equals("pepayFeelBorrow")) {// 体验金还款
				repayBorrowActionService.doRepayment(msg, false);
			} else if (msg.getActionName().equals("pepayBorrow")) {// 真实还款
				repayBorrowActionService.doRepayment(msg, true);
			} else if (msg.getActionName().equals("weekRepayment")) {// 7天理财还款
				repayBorrowActionService.weekRepayment(msg);
			} else if (msg.getActionName().equals("weekExamine")) {// 7天理财发标
				borrowExamineActionService.doWeekExamine(msg);
			} else if (msg.getActionName().equals("register")) {// 用户注册
				userRegisterActionService.doUserRegister(msg);
			} else if (msg.getActionName().equals("emailVerfiy")) {// 邮箱认证
				emailVerifyActionService.doEmailVerify(msg);
			} else if (msg.getActionName().equals("realNameVerfiy")) {// 实名认证
				realNameVerifyActionService.doRealNameVerify(msg);
			} else if (msg.getActionName().equals("giftbox")) { // 礼品盒红包
				giftBoxActionService.doGiftbox(msg);
			} else if(msg.getActionName().equals("currentTender")){
				currentTenderActionService.doUserTender(msg);
			} else if(msg.getActionName().equals("currentRedeem")){
				currentTenderActionService.doUserTender(msg);
			}
			// 消息处理完成
			this.doJmsLog(msg, false);
		}
	}

	private boolean canAction(AfterActionMessage msg) {
		return this.jmsService.canActionByJmsId(msg.getMessageId());
	}

	/**
	 * 消息监控日志处理
	 * 
	 * @param msg
	 * @param isReceive
	 */
	private void doJmsLog(AfterActionMessage msg, boolean isReceive) {
		if (EmptyUtil.isNotEmpty(msg.getMessageId())) {
			JmsLog newLog = new JmsLog();
			newLog.setJmsId(msg.getMessageId());
			if (isReceive) {
				newLog.setReceiveTime(new Date());
				newLog.setJmsStatus(1);
				newLog.setJmsResult("doing");
			} else {
				newLog.setCompleteTime(new Date());
				newLog.setJmsStatus(2);
			}
			this.jmsService.updateByPrimaryKeySelective(newLog);
		}

	}

}
