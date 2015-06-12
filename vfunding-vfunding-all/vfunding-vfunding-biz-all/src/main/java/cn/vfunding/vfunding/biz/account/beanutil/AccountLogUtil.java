package cn.vfunding.vfunding.biz.account.beanutil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.account.model.AccountLog;
import cn.vfunding.vfunding.biz.account.model.AccountRecharge;
import cn.vfunding.vfunding.biz.message.model.GiftboxMessage;
import cn.vfunding.vfunding.biz.sina.model.SinaSettlement;

/**
 * 资金记录对象的辅助工具类，主要用来根据其他对象来创建日志对象
 * 
 * @author liuyijun
 * 
 */
public class AccountLogUtil {

	/**
	 * 根据充值日志和充值后账户信息创建资金日志对象
	 * 
	 * @param accountRecharge
	 *            充值日志
	 * @param account
	 *            充值后账户信息
	 * @return 资金日志对象 2014年12月26日 liuyijun
	 */
	public static AccountLog createLogByAccountRechargeAndAccount(
			AccountRecharge accountRecharge, Account account) {
		BigDecimal money = null;
		if (EmptyUtil.isNotEmpty(accountRecharge.getFee())) {
			money = accountRecharge.getMoney().subtract(
					accountRecharge.getFee());// 到账金额
		} else {
			money = accountRecharge.getMoney();
		}
		AccountLog accountLog = new AccountLog();
		accountLog.setUserId(accountRecharge.getUserId());
		accountLog.setType("recharge");
		accountLog.setMoney(money);
		accountLog.setUseMoney(account.getUseMoney());
		accountLog.setNoUseMoney(account.getNoUseMoney());
		accountLog.setCollection(account.getCollection());
		accountLog.setTotal(account.getTotal());
		accountLog.setRemark(accountRecharge.getRemark());
		accountLog.setAddtime(Integer.parseInt(DateUtil.getTime()));
		accountLog.setAddip(accountRecharge.getAddip());
		return accountLog;
	}
	
	/**
	 * 根据礼品盒对象和充值后账户信息创建资金日志对象
	 * @param giftboxMessage
	 * 	礼品盒对象
	 * @param userAccount
	 * 	充值后账户信息
	 * @return AccountLog
	 * 	资金日志对象
	 * @author louchen 2015-01-09
	 */
	public static AccountLog createLogForGift(GiftboxMessage giftboxMessage,Account userAccount){
		AccountLog userAccountLog = new AccountLog();
		userAccountLog.setUserId(giftboxMessage.getReceiveUser());
		userAccountLog.setType("gift_addmoney");
		userAccountLog.setMoney(giftboxMessage.getMoney());
		userAccountLog.setUseMoney(userAccount.getUseMoney());
		userAccountLog.setNoUseMoney(userAccount.getNoUseMoney());
		userAccountLog.setCollection(userAccount.getCollection());
		userAccountLog.setTotal(userAccount.getTotal());
		userAccountLog.setToUser(0);
		userAccountLog.setRemark("【礼品】"+giftboxMessage.getTitle()+",入账"+giftboxMessage.getMoney()+"元");
		userAccountLog.setBorrowId(0);
		userAccountLog.setAddtime(Integer.parseInt(DateUtil.getTime()));
		userAccountLog.setAddip("127.0.0.1");
		return userAccountLog;
	}

	/**
	 * 根据新浪存钱罐收益对象创建资金日志对象
	 * @param giftboxMessage
	 * 	礼品盒对象
	 * @param userAccount
	 * 	充值后账户信息
	 * @return AccountLog
	 * 	资金日志对象
	 * @author louchen 2015-01-09
	 */
	public static AccountLog createLogForSinaBonus(SinaSettlement ss,Account userAccount){
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		AccountLog userAccountLog = new AccountLog();
		userAccountLog.setUserId(ss.getUserId());
		userAccountLog.setType("sinaBonus_addmoney");
		userAccountLog.setMoney(ss.getSyncBonusRealmoney());
		userAccountLog.setUseMoney(userAccount.getUseMoney());
		userAccountLog.setNoUseMoney(userAccount.getNoUseMoney());
		userAccountLog.setCollection(userAccount.getCollection());
		userAccountLog.setTotal(userAccount.getTotal());
		userAccountLog.setToUser(0);
		//userAccountLog.setRemark("【新浪存钱罐收益】"+sdf.format(ss.getAddtime())+",入账"+ss.getSyncBonusRealmoney()+"元");
		userAccountLog.setRemark("【新浪存钱罐余额生息】,入账"+ss.getSyncBonusRealmoney()+"元");
		userAccountLog.setBorrowId(0);
		userAccountLog.setAddtime(Integer.parseInt(DateUtil.getTime()));
		userAccountLog.setAddip("127.0.0.1");
		return userAccountLog;
	}
}
