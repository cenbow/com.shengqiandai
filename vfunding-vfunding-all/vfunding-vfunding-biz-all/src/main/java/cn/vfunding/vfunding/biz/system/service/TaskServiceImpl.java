package cn.vfunding.vfunding.biz.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.system.dao.TaskMapper;
import cn.vfunding.vfunding.biz.system.model.Task;

@Service("taskService")
public class TaskServiceImpl implements ITaskService {

	@Autowired
	private TaskMapper mapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Task record) {
		return this.mapper.insert(record);
	}

	@Override
	public int insertSelective(Task record) {
		return this.mapper.insertSelective(record);
	}

	@Override
	public Task selectByPrimaryKey(Integer id) {
		return this.mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Task record) {
		return this.mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Task record) {
		return this.mapper.updateByPrimaryKey(record);
	}

}
