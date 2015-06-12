package cn.vfunding.vfunding.biz.account.service;

import java.math.BigDecimal;
import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.model.AccountLog;

public interface IAccountLogService {
	int deleteByPrimaryKey(Integer id);

    int insert(AccountLog record);

    int insertSelective(AccountLog record);

    AccountLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountLog record);

    int updateByPrimaryKey(AccountLog record);

    /**
     * 资金记录
     * @author liuhuan
     */
	List<AccountLog> selectAccountLogListPage(PageSearch pageSearch);
	
	/**
	 * 用户总存钱罐收益	
	 * @param userId
	 * @return
	 */
	BigDecimal selectSinaBonusHistoryTotalByUserId(Integer userId);
}
