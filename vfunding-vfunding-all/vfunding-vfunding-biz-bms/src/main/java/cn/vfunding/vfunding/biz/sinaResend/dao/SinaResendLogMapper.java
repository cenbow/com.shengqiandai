package cn.vfunding.vfunding.biz.sinaResend.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.sinaResend.model.SinaResendLog;

public interface SinaResendLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SinaResendLog record);

    int insertSelective(SinaResendLog record);

    SinaResendLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SinaResendLog record);

    int updateByPrimaryKey(SinaResendLog record);
    
    List<SinaResendLog> selectBySelective(SinaResendLog record);
}