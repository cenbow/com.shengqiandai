package cn.vfunding.vfunding.biz.bms_system.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.bms_system.model.SysResourceGroup;

public interface SysResourceGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysResourceGroup record);

    int insertSelective(SysResourceGroup record);

    SysResourceGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysResourceGroup record);

    int updateByPrimaryKey(SysResourceGroup record);
    
    List<SysResourceGroup> selectAll();
}