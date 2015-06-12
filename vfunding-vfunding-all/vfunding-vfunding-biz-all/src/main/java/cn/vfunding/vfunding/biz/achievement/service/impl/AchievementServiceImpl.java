package cn.vfunding.vfunding.biz.achievement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.achievement.dao.AchievementMapper;
import cn.vfunding.vfunding.biz.achievement.model.Achievement;
import cn.vfunding.vfunding.biz.achievement.service.IAchievementService;

@Service("achievementService")
public class AchievementServiceImpl implements IAchievementService {

	@Autowired
	private AchievementMapper achievementMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return achievementMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Achievement record) {
		return achievementMapper.insert(record);
	}

	@Override
	public int insertSelective(Achievement record) {
		return achievementMapper.insertSelective(record);
	}

	@Override
	public Achievement selectByPrimaryKey(Integer id) {
		return achievementMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Achievement record) {
		return achievementMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Achievement record) {
		return achievementMapper.updateByPrimaryKey(record);
	}

}