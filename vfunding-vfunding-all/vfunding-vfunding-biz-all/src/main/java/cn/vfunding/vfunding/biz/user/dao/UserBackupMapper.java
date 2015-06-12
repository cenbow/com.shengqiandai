package cn.vfunding.vfunding.biz.user.dao;

import cn.vfunding.vfunding.biz.user.model.UserBackup;

public interface UserBackupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserBackup record);

    int insertSelective(UserBackup record);

    UserBackup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserBackup record);

    int updateByPrimaryKeyWithBLOBs(UserBackup record);

    int updateByPrimaryKey(UserBackup record);
}