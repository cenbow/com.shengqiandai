package cn.p2p.p2p.biz.mobile.dao;

import cn.p2p.p2p.biz.mobile.model.MobileResponseLog;

public interface MobileResponseLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileResponseLog record);

    int insertSelective(MobileResponseLog record);

    MobileResponseLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileResponseLog record);

    int updateByPrimaryKeyWithBLOBs(MobileResponseLog record);

    int updateByPrimaryKey(MobileResponseLog record);
}