package cn.vfunding.vfunding.biz.usertoken.service;

import cn.vfunding.vfunding.biz.usertoken.model.UserToken;

public interface IUserTokenService {
	int deleteByPrimaryKey(Integer id);

    int insertSelective(UserToken record);

    UserToken selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserToken record);
    UserToken selectBySelective(UserToken record);
    UserToken selectByToken(String token);
    String selectTokenByUserId(Integer userId);
    Integer selectUserIdByToken(String token);
}
