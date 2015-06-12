package cn.vfunding.vfunding.biz.mobile.dao;

import cn.vfunding.vfunding.biz.mobile.model.MobileRequestLog;

public interface MobileRequestLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileRequestLog record);

    int insertSelective(MobileRequestLog record);

    MobileRequestLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileRequestLog record);

    int updateByPrimaryKeyWithBLOBs(MobileRequestLog record);

    int updateByPrimaryKey(MobileRequestLog record);
    
    int selectCountByOrderNo(String orderNo);
}