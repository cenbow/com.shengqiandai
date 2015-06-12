package cn.vfunding.vfunding.biz.bms_system.dao;

import cn.vfunding.vfunding.biz.bms_system.model.RoleResourceKey;

public interface RoleResourceMapper {
    int deleteByPrimaryKey(RoleResourceKey key);

    int insert(RoleResourceKey record);

    int insertSelective(RoleResourceKey record);
    
    void deleteByRoleId(Integer roleId);
}