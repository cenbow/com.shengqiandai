package cn.vfunding.vfunding.biz.system.service;

import cn.vfunding.vfunding.biz.system.model.Task;

public interface ITaskService {

	int deleteByPrimaryKey(Integer id);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);
}
