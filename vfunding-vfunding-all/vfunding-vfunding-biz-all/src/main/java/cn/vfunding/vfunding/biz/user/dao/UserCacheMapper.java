package cn.vfunding.vfunding.biz.user.dao;

import cn.vfunding.vfunding.biz.user.model.UserCache;

public interface UserCacheMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserCache record);

    int insertSelective(UserCache record);

    UserCache selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserCache record);

    int updateByPrimaryKey(UserCache record);
    
    /**
     *  根据用户Id查询基本信息
     * @param userId
     * @return
     * 2014年5月6日
     * liuyijun
     */
    UserCache selectBaseInfoByUserId(Integer userId);
    
}