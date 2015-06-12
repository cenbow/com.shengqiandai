package cn.vfunding.vfunding.biz.sina.service;

import java.util.Map;

import cn.vfunding.common.framework.lock.NeedLock;
import cn.vfunding.vfunding.biz.account.model.AccountRecharge;
import cn.vfunding.vfunding.biz.sina.model.SinaCard;

public interface IAccountRechargeSinaService {
	
	/**
	 * 网银充值
	 * @param rec
	 * @param bankCode
	 * @return
	 * @throws Exception
	 */
	String doUserRechargeByOnlineBank(AccountRecharge rec,String bankCode) throws Exception;
	
	/**
	 * 快捷充值
	 * @param rec
	 * @param scId
	 * @param bankCode
	 * @return
	 * @throws Exception
	 */
	String doUserRechargeByQuickPay(AccountRecharge rec,Integer scId,String bankCode) throws Exception;
	
	/**
	 * 绑定银行卡充值
	 * @param rec
	 * @param scId
	 * @return
	 * @throws Exception
	 */
	Map<String,Object> doUserRechargeByBindingPay(AccountRecharge rec,SinaCard sc) throws Exception;
	
	/**
	 * 绑定银行卡充值_推进
	 * @param captcha
	 * @param ticket
	 * @param tradeNo
	 * @return
	 * @throws Exception
	 */
	@NeedLock("/locks/bindingPayAdvance")
	String doUserRechargeByBindingPayAdvance(String captcha,String ticket,String tradeNo) throws Exception;
	
	/**
	 * 绑定银行卡充值_推进校验
	 * @param userId
	 * @param captcha
	 * @param tradeNo
	 * @return
	 * @throws Exception
	 */
	String checkRechargeByBindingPayAdvance(Integer userId,String captcha,String tradeNo) throws Exception;
	
	/**
	 * 创建rechage对象
	 * @return
	 */
	@NeedLock("/locks/rcg")
	AccountRecharge getAccountRecharge(Integer userId,String money,String ip,String remark);

}
