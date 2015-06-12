package cn.vfunding.vfunding.biz.sinaPhone.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.sinaPhone.model.SinaPhoneLog;

public interface SinaPhoneLogMapper {
	int deleteByPrimaryKey(Integer id);

    int insert(SinaPhoneLog record);

    int insertSelective(SinaPhoneLog record);

    SinaPhoneLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SinaPhoneLog record);

    int updateByPrimaryKey(SinaPhoneLog record);
    
    List<SinaPhoneLog> selectUserIdByPhone();
}