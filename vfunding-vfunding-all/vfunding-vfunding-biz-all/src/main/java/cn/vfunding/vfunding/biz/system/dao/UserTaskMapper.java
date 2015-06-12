package cn.vfunding.vfunding.biz.system.dao;

import cn.vfunding.vfunding.biz.system.model.UserTask;

public interface UserTaskMapper {
    int deleteByPrimaryKey(Integer id);

	int insert(UserTask record);

    int insertSelective(UserTask record);

    UserTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTask record);

    int updateByPrimaryKey(UserTask record);
    
    UserTask selectByUserIdAndTaskId(UserTask search);
}