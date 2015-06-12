package cn.vfunding.vfunding.biz.borrow.dao;

import org.apache.ibatis.annotations.Param;

import cn.vfunding.vfunding.biz.borrow.model.MortgageType;

public interface MortgageTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MortgageType record);

    int insertSelective(MortgageType record);

    MortgageType selectByPrimaryKey(Integer id);
    
    MortgageType selectByTypeName(@Param("name")String name);

    int updateByPrimaryKeySelective(MortgageType record);

    int updateByPrimaryKey(MortgageType record);
}