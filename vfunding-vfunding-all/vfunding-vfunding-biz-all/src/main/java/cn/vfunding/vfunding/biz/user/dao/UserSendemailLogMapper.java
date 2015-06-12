package cn.vfunding.vfunding.biz.user.dao;

import cn.vfunding.vfunding.biz.user.model.UserSendemailLog;

public interface UserSendemailLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserSendemailLog record);

    int insertSelective(UserSendemailLog record);

    UserSendemailLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserSendemailLog record);

    int updateByPrimaryKeyWithBLOBs(UserSendemailLog record);

    int updateByPrimaryKey(UserSendemailLog record);
}