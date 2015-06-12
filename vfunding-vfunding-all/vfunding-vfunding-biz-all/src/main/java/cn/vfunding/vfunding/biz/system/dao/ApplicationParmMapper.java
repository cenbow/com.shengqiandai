package cn.vfunding.vfunding.biz.system.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.system.model.ApplicationParm;

public interface ApplicationParmMapper {
    int deleteByPrimaryKey(String key);

    int insert(ApplicationParm record);

    int insertSelective(ApplicationParm record);

    ApplicationParm selectByPrimaryKey(String key);

    int updateByPrimaryKeySelective(ApplicationParm record);

    int updateByPrimaryKey(ApplicationParm record);
    
    List<ApplicationParm> selectAll();
}