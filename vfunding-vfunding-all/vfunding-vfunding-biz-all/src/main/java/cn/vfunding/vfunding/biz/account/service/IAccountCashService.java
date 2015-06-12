package cn.vfunding.vfunding.biz.account.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import cn.vfunding.common.framework.lock.NeedLock;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.model.AccountCash;
import cn.vfunding.vfunding.biz.account.model.CashNumber;
import cn.vfunding.vfunding.biz.common.vo.AccountCashVO;
import cn.vfunding.vfunding.biz.common.vo.MessageVO;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.userMobile.model.UserRechargeCashMobile;
import cn.vfunding.vfunding.common.interceptors.annotations.AfterAction;

public interface IAccountCashService {
	int deleteByPrimaryKey(Integer id);

	int insert(AccountCash record);

	int insertSelective(AccountCash record);

	AccountCash selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(AccountCash record);

	int updateByPrimaryKey(AccountCash record);

	/**
	 * 根据用户ID分页查询
	 * 
	 * @param search
	 * @return 2014年4月24日 liuyijun
	 */
	List<UserRechargeCashMobile> selectByUserIdListPage(PageSearch search);

	/**
	 * @Description:获取用户提现时千3手续费 （累计冲值-累计提现+累计利息+佣兵返利）-15天内冲值总额
	 * @param userId
	 * @param account
	 *            提现金额
	 * @return
	 * @author liuhuan
	 */
	BigDecimal userAdditionalCashFee(Integer userId, BigDecimal account);

	/**
	 * @Description:用户申请提现
	 * @param ip
	 * @return
	 * @author liuhuan
	 */
	@NeedLock
	Map<String, Object> updateApplyCash(UserSession user, AccountCashVO cashVO);

	/**
	 * 财务审核列标
	 * 
	 * @author liuhuan
	 */
	List<AccountCashVO> selectWaitApplyListPage(PageSearch search);

	/**
	 * @Description:财务放款
	 * @return
	 * @author liuhuan
	 */
	@NeedLock("/locks/account")
	//@NeedAfterInterceptor("takeCash")
	@AfterAction(actionName="takeCash")
	String updateTakeCash(AccountCash accountCash, AccountCashVO cashVO);

	/**
	 * 提现手续费 for php
	 * 
	 * @author liuhuan
	 */
	MessageVO updateCashFeePhp(AccountCashVO vo);

	/**
	 * 用户当天提现总计
	 * 
	 * @author liuhuan
	 */
	BigDecimal selectByDayUserId(Integer userId);

	int insertCashNumber(CashNumber cashNumber);
	
	int updateCashNumber(CashNumber cashNumber);
	
	CashNumber selectCashNumberByCashId(Integer cashId);
    /**
     * 用户提现记录查询
     * @param pageSearch
     * @return
     * lx
     */
    List<AccountCash> selectByidListPage(PageSearch pageSearch);
    
}
