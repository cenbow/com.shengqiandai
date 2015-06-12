package cn.vfunding.vfunding.biz.account.service;

import cn.vfunding.vfunding.biz.account.model.AccountSinabonus;

public interface IAccountSinabonusService {

    int insertSelective(AccountSinabonus record);

    AccountSinabonus selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(AccountSinabonus record);
    
    int createUserEmptyData(Integer userId);

}