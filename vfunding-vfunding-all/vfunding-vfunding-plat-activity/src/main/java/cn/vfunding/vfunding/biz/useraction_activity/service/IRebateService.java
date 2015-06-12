package cn.vfunding.vfunding.biz.useraction_activity.service;

public interface IRebateService {

	/**
	 * 内部集团用户返利
	 * 
	 * @author lilei
	 * @param borrowId
	 *            2015年1月22日
	 */
	void internalRebate(Integer borrowId);

	/**
	 * 加息卡返利
	 * 
	 * @author lilei
	 * @param borrowId
	 *            2015年1月22日
	 */
	void hikesRebate(Integer borrowId);

	/**
	 * 好友返利
	 * 
	 * @author lilei 2015年1月22日
	 */
	void friendRebate(Integer borrowId);
}
