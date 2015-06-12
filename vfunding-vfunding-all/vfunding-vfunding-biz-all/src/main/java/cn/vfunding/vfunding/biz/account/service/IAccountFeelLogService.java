package cn.vfunding.vfunding.biz.account.service;

import java.util.List;

import cn.vfunding.vfunding.biz.account.model.AccountFeelLog;

public interface IAccountFeelLogService {
	int deleteByPrimaryKey(Integer id);

    int insert(AccountFeelLog record);

    int insertSelective(AccountFeelLog record);

    AccountFeelLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountFeelLog record);

    int updateByPrimaryKey(AccountFeelLog record);
    
    List<AccountFeelLog> selectByUserIdType(AccountFeelLog record);
}
