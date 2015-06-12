package cn.vfunding.vfunding.biz.user.dao;

import cn.vfunding.vfunding.biz.user.model.UserIntroduce;

public interface UserIntroduceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserIntroduce record);

    int insertSelective(UserIntroduce record);

    UserIntroduce selectByPrimaryKey(Integer id);
    
    UserIntroduce selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(UserIntroduce record);

    int updateByPrimaryKey(UserIntroduce record);
}