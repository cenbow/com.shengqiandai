package cn.vfunding.vfunding.biz.system.dao;

import cn.vfunding.vfunding.biz.system.model.ReturnedMoney;

public interface ReturnedMoneyMapper {

	int insert(ReturnedMoney returnedMoney);

    int insertSelective(ReturnedMoney returnedMoney);

    int updateByPrimaryKeySelective(ReturnedMoney returnedMoney);

    int updateByPrimaryKey(ReturnedMoney returnedMoney);
    
    ReturnedMoney selectByPrimaryKey(Integer userId);
}
