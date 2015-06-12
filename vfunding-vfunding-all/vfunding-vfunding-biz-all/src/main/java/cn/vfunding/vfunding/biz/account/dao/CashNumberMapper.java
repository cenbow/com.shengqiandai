package cn.vfunding.vfunding.biz.account.dao;

import cn.vfunding.vfunding.biz.account.model.CashNumber;

public interface CashNumberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CashNumber record);

    int insertSelective(CashNumber record);

    CashNumber selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CashNumber record);

    int updateByPrimaryKey(CashNumber record);
    
    CashNumber selectByCashId(Integer cashId);
}