package cn.vfunding.vfunding.biz.account.service;

import java.util.List;

import cn.vfunding.common.framework.lock.NeedLock;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.model.AccountRecharge;
import cn.vfunding.vfunding.biz.common.vo.AccountRechargeVO;
import cn.vfunding.vfunding.biz.userMobile.model.UserRechargeCashMobile;
import cn.vfunding.vfunding.common.interceptors.annotations.AfterAction;

public interface IAccountRechargeService {
	int deleteByPrimaryKey(Integer id);

	@AfterAction(actionName = "recharge")
	int insert(AccountRecharge record);

	@AfterAction(actionName = "recharge")
	int insertSelectiveForOffLine(AccountRecharge record);

	@AfterAction(actionName = "recharge")
	int insertSelective(AccountRecharge record);

	AccountRecharge selectByPrimaryKey(Integer id);
	
	
	int updateByPrimaryKeySelective(AccountRecharge record);

	int updateByPrimaryKey(AccountRecharge record);

	/**
	 * 用户是否充值过
	 * 
	 * @author liuhuan
	 */
	int selectUserIsRecharge(Integer userId);

	/**
	 * 根据用户ID分页查询
	 * 
	 * @param search
	 * @return 2014年4月24日 liuyijun
	 */
	List<UserRechargeCashMobile> selectByUserIdListPage(PageSearch search,
			Integer type);

	/**
	 * 更新状态
	 * 
	 * @param record
	 * @return 2014年4月30日 liuyijun
	 */
	@AfterAction(actionName="recharge")
	int updateStatus(AccountRecharge record);

	/**
	 * 根据订单号
	 * 
	 * @param tradeNo
	 * @return 2014年4月30日 liuyijun
	 */
	AccountRecharge selectByTradeNo(String tradeNo);

	List<AccountRechargeVO> selectWaitApplyListPage(PageSearch search);

	/**
	 * 审核线下充值
	 * 
	 * @author liuhuan
	 */
	@AfterAction(actionName="recharge")
	int updateCheckRechargeOffline(AccountRecharge recharge, String ip);

	/**
	 * 根据用户id查询出用户充值记录
	 * 
	 * @return lx
	 */
	List<AccountRecharge> selectByIdListPage(PageSearch search);
	
	/**
	 * 新浪回调更新状态
	 * @param tradeNo 流水号
	 * @param depositAmount 通知充值金额
	 * @param success 新浪消息标志位
	 * @return
	 * @author louchen 2015-01-21
	 * @throws Exception 
	 */
	@NeedLock("/locks/notifyRecharge")
	int sinaReturnUpdateStatus(AccountRecharge rec,String depositAmount,Boolean success) throws Exception;
	
	int sinaReturnUpdateStatus(String tradeNo,String depositAmount,Boolean success) throws Exception;
	
	/**
	 * 获取充值状态中文提示
	 * @param status
	 * @return
	 * @author louchen 2015-01-22
	 */
	String getRechargeStatusMsg(byte status);
	
}
