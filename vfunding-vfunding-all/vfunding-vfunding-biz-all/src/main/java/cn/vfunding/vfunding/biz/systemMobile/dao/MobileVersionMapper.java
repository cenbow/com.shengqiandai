package cn.vfunding.vfunding.biz.systemMobile.dao;

import cn.vfunding.vfunding.biz.systemMobile.model.MobileVersion;

public interface MobileVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileVersion record);

    int insertSelective(MobileVersion record);

    MobileVersion selectByPrimaryKey(Integer id);
    
    MobileVersion selectByType(String type);

    int updateByPrimaryKeySelective(MobileVersion record);

    int updateByPrimaryKey(MobileVersion record);
}