package cn.vfunding.vfunding.biz.credit.dao;

import cn.vfunding.vfunding.biz.credit.model.CreditType;

public interface CreditTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CreditType record);

    int insertSelective(CreditType record);

    CreditType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CreditType record);

    int updateByPrimaryKey(CreditType record);
}