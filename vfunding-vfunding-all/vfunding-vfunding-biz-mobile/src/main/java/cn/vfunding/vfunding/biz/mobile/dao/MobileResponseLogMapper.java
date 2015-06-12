package cn.vfunding.vfunding.biz.mobile.dao;

import cn.vfunding.vfunding.biz.mobile.model.MobileResponseLog;

public interface MobileResponseLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileResponseLog record);

    int insertSelective(MobileResponseLog record);

    MobileResponseLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileResponseLog record);

    int updateByPrimaryKeyWithBLOBs(MobileResponseLog record);

    int updateByPrimaryKey(MobileResponseLog record);
}