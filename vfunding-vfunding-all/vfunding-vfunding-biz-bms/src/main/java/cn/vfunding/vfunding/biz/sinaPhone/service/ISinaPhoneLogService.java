package cn.vfunding.vfunding.biz.sinaPhone.service;

import java.util.List;

import cn.vfunding.vfunding.biz.sinaPhone.model.SinaPhoneLog;

public interface ISinaPhoneLogService {
	int deleteByPrimaryKey(Integer id);


    int insertSelective(SinaPhoneLog record);

    SinaPhoneLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SinaPhoneLog record);

    
    List<SinaPhoneLog> selectUserIdByPhone();
}
