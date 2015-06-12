package cn.vfunding.vfunding.biz.account.dao;

import java.math.BigDecimal;
import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.model.AccountLog;

public interface AccountLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountLog record);

    int insertSelective(AccountLog record);

    AccountLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountLog record);

    int updateByPrimaryKey(AccountLog record);
    
    List<AccountLog> selectByParam(AccountLog record);

	List<AccountLog> selectAccountLogListPage(PageSearch pageSearch);
	
	List<AccountLog> selectByConvet();
	
	/**
	 * 用户总存钱罐收益	
	 * @param userId
	 * @return
	 */
	BigDecimal selectSinaBonusHistoryTotalByUserId(Integer userId);
}