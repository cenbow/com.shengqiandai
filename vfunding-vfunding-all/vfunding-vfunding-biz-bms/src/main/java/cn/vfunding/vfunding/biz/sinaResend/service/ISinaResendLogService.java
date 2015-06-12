package cn.vfunding.vfunding.biz.sinaResend.service;

import java.util.List;

import cn.vfunding.vfunding.biz.sinaResend.model.SinaResendLog;

public interface ISinaResendLogService {
	int deleteByPrimaryKey(Integer id);


    int insertSelective(SinaResendLog record);

    SinaResendLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SinaResendLog record);

    
    List<SinaResendLog> selectBySelective(SinaResendLog record);
}
