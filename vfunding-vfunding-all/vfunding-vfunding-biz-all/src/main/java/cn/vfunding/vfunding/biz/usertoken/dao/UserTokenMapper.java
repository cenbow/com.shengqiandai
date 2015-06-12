package cn.vfunding.vfunding.biz.usertoken.dao;

import cn.vfunding.vfunding.biz.usertoken.model.UserToken;

public interface UserTokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserToken record);

    int insertSelective(UserToken record);

    UserToken selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserToken record);

    int updateByPrimaryKey(UserToken record);
    
    UserToken selectBySelective(UserToken ut);
}