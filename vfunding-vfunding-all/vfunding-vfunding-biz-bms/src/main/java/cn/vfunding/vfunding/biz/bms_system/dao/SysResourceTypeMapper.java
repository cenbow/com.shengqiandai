package cn.vfunding.vfunding.biz.bms_system.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.bms_system.model.SysResourceType;

public interface SysResourceTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysResourceType record);

    int insertSelective(SysResourceType record);

    SysResourceType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysResourceType record);

    int updateByPrimaryKey(SysResourceType record);
    
    List<SysResourceType> selectAll();
}