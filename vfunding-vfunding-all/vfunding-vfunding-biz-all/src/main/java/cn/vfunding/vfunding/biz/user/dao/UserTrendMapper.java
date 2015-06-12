package cn.vfunding.vfunding.biz.user.dao;

import cn.vfunding.vfunding.biz.user.model.UserTrend;

public interface UserTrendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTrend record);

    int insertSelective(UserTrend record);

    UserTrend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTrend record);

    int updateByPrimaryKeyWithBLOBs(UserTrend record);

    int updateByPrimaryKey(UserTrend record);
}