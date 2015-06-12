package cn.vfunding.vfunding.biz.user.dao;

import cn.vfunding.vfunding.biz.user.model.UserTypechange;

public interface UserTypechangeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTypechange record);

    int insertSelective(UserTypechange record);

    UserTypechange selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTypechange record);

    int updateByPrimaryKey(UserTypechange record);
}