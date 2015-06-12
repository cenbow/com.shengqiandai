package cn.vfunding.vfunding.biz.account.service;

import java.math.BigDecimal;
import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.common.vo.AccountSystemVO;
import cn.vfunding.vfunding.biz.userMobile.model.UserStatisticsDataMobile;

public interface IAccountService {

	int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据
     * @param record
     * @return 自增长ID
     */
    int insert(Account record);

    /**
     * 插入数据
     * @param record
     * @return 自增长ID
     */
    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);
    /**
     * 根据用户ID查询账户信息
     * @param userId
     * @return
     */
    Account selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
    
    /**
     * 根据用户ID跟新
     * @param record
     * @return
     */
    int updateByUserId(Account record);
    
    /**
     * 返利总收益
     * @param userId
     * @return
     * @author liuhuan
     */
    BigDecimal backAllIncome(Integer userId);
    
    /**
     * @Description:累计利息
     * @param userId
     * @return
     * @author liuhuan
     */
    List<UserStatisticsDataMobile> sumAccountInterest(Integer userId);
    
    /**
     * 后台查询账户信息
     * @author liuhuan
     */
    List<AccountSystemVO> selectAccountSystemListPage(PageSearch pageSearch);
    

	BigDecimal selectSumRechargeByUserId(Integer userId);
	
	BigDecimal selectSumCashByUserId(Integer userId);
	
	/**
	 * 可用余额总和
	 * @return
	 */
	BigDecimal selectSumUseMoney();
	
	/**
	 * 可用余额大于0的账户
	 */
	List<Account> selectAccountUseMoneyThanZero();	
	
	/**
	 * 可用余额大于50的闲置账户
	 */
	List<Account> selectByLeaveUnused();
}
