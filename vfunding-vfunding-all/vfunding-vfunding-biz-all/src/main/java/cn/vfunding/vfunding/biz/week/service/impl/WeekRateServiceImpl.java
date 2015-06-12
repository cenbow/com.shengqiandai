package cn.vfunding.vfunding.biz.week.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.week.dao.WeekRateMapper;
import cn.vfunding.vfunding.biz.week.model.WeekRate;
import cn.vfunding.vfunding.biz.week.service.IWeekRateService;

@Service("weekRateService")
public class WeekRateServiceImpl implements IWeekRateService {
	
	@Autowired
	private WeekRateMapper weekRateMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.weekRateMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WeekRate record) {
		return this.weekRateMapper.insert(record);
	}

	@Override
	public int insertSelective(WeekRate record) {
		return this.weekRateMapper.insertSelective(record);
	}

	@Override
	public WeekRate selectByPrimaryKey(Integer id) {
		return this.weekRateMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WeekRate record) {
		return this.weekRateMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(WeekRate record) {
		return this.weekRateMapper.updateByPrimaryKey(record);
	}

}
