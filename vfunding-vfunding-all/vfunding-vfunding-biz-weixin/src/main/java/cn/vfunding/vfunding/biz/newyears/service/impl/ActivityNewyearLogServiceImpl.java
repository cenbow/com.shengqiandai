package cn.vfunding.vfunding.biz.newyears.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.newyears.dao.ActivityNewyearLogMapper;
import cn.vfunding.vfunding.biz.newyears.model.ActivityNewyearLog;
import cn.vfunding.vfunding.biz.newyears.service.IActivityNewyearLogService;

@Service
public class ActivityNewyearLogServiceImpl implements IActivityNewyearLogService {

	@Autowired
	private ActivityNewyearLogMapper activityNewyearLogMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return this.activityNewyearLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ActivityNewyearLog record) {
		// TODO Auto-generated method stub
		return this.activityNewyearLogMapper.insertSelective(record);
	}

	@Override
	public ActivityNewyearLog selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return this.activityNewyearLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ActivityNewyearLog record) {
		// TODO Auto-generated method stub
		return this.activityNewyearLogMapper.updateByPrimaryKeySelective(record);
	}
}