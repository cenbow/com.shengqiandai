package cn.vfunding.vfunding.biz.system.dao;

import cn.vfunding.vfunding.biz.system.model.BiaoType;

public interface BiaoTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BiaoType record);

    int insertSelective(BiaoType record);

    BiaoType selectByPrimaryKey(Integer id);
    
    BiaoType selectByTypeName(String typeName);

    int updateByPrimaryKeySelective(BiaoType record);

    int updateByPrimaryKey(BiaoType record);
}