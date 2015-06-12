package cn.vfunding.vfunding.biz.account.dao;

import cn.vfunding.vfunding.biz.account.model.AccountSinabonus;

public interface AccountSinabonusMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(AccountSinabonus record);

    int insertSelective(AccountSinabonus record);

    AccountSinabonus selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(AccountSinabonus record);

    int updateByPrimaryKey(AccountSinabonus record);
}