package cn.vfunding.vfunding.biz.system.dao;

import cn.vfunding.vfunding.biz.system.model.CashRule;

public interface CashRuleMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(CashRule record);

    int insertSelective(CashRule record);

    CashRule selectByPrimaryKey(Byte id);
    
    CashRule selectOne();

    int updateByPrimaryKeySelective(CashRule record);

    int updateByPrimaryKey(CashRule record);
}