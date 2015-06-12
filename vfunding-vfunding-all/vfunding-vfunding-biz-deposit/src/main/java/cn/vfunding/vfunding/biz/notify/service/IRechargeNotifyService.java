package cn.vfunding.vfunding.biz.notify.service;

import cn.vfunding.vfunding.biz.sina.vo.notify.RechargeNotifyVO;
import cn.vfunding.vfunding.common.interceptors.annotations.AfterAction;

public interface IRechargeNotifyService {
	
	/**
	 * 新浪回调充值,后置通知
	 * @param request
	 * @return
	 * @throws Exception
	 * @author louchen
	 * @date 2015-03-06
	 */
	@AfterAction(actionName = "notifyRecharge")
	public String recharge(RechargeNotifyVO vo) throws Exception;
	
}
