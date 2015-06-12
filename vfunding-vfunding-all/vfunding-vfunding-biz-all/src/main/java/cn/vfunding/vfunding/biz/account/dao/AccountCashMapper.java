package cn.vfunding.vfunding.biz.account.dao;

import java.math.BigDecimal;
import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.model.AccountCash;
import cn.vfunding.vfunding.biz.common.vo.AccountCashVO;
import cn.vfunding.vfunding.biz.userMobile.model.UserRechargeCashMobile;

public interface AccountCashMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountCash record);

    int insertSelective(AccountCash record);

    AccountCash selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountCash record);

    int updateByPrimaryKey(AccountCash record);
    
    /**
     * 根据用户ID、时间分页查询
     * @param search
     * @return
     * 2014年4月24日
     * liuyijun
     */
    List<UserRechargeCashMobile> selectByUserIdListPage(PageSearch search);

    BigDecimal selectByDayUserId(Integer userId);

    /**
     * 财务审核列表
     * @author liuhuan
     */
	List<AccountCashVO> selectWaitApplyListPage(PageSearch search);
	
	/**
	 * 查询用户提现记录 
	 * @param pageSearch
	 * @return
	 * lx
	 */
	List<AccountCash> selectByidListPage(PageSearch pageSearch);
	
	List<AccountCash> selectByParameters(AccountCash record);
}