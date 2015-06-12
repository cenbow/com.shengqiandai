package cn.vfunding.vfunding.biz.activity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.activity.dao.ActivityHongbaoLogMapper;
import cn.vfunding.vfunding.biz.activity.model.ActivityHongbaoLog;
import cn.vfunding.vfunding.biz.activity.service.IActivityHongbaoLogService;

@Service("activityHongbaoLogService")
public class ActivityHongbaoLogServiceImpl implements IActivityHongbaoLogService {

	@Autowired
	private ActivityHongbaoLogMapper ahlMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return this.ahlMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ActivityHongbaoLog record) {
		// TODO Auto-generated method stub
		return this.ahlMapper.insert(record);
	}

	@Override
	public int insertSelective(ActivityHongbaoLog record) {
		// TODO Auto-generated method stub
		return this.ahlMapper.insertSelective(record);
	}

	@Override
	public ActivityHongbaoLog selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return this.ahlMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ActivityHongbaoLog record) {
		// TODO Auto-generated method stub
		return this.ahlMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ActivityHongbaoLog record) {
		// TODO Auto-generated method stub
		return this.ahlMapper.updateByPrimaryKey(record);
	}

}