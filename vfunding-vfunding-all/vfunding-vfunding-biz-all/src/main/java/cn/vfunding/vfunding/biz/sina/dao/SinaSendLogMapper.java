package cn.vfunding.vfunding.biz.sina.dao;

import cn.vfunding.vfunding.biz.sina.model.SinaSendLog;
import cn.vfunding.vfunding.biz.sina.model.SinaSendLogWithBLOBs;

public interface SinaSendLogMapper {
    int deleteByPrimaryKey(String orderNo);

    int insert(SinaSendLogWithBLOBs record);

    int insertSelective(SinaSendLogWithBLOBs record);

    SinaSendLogWithBLOBs selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(SinaSendLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SinaSendLogWithBLOBs record);

    int updateByPrimaryKey(SinaSendLog record);
    
    SinaSendLogWithBLOBs selectSuccessLogByOrderNo(String orderNo);
    
    SinaSendLogWithBLOBs selectFailedLogByOrderNo(String orderNo);
}