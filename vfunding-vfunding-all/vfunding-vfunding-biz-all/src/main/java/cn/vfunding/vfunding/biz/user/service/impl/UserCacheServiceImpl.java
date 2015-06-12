package cn.vfunding.vfunding.biz.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.user.dao.UserCacheMapper;
import cn.vfunding.vfunding.biz.user.model.UserCache;
import cn.vfunding.vfunding.biz.user.service.IUserCacheService;
@Service("userCacheService")
public class UserCacheServiceImpl implements IUserCacheService {

	@Autowired
	private UserCacheMapper mapper;
	@Override
	public int deleteByPrimaryKey(Integer userId) {
		return this.mapper.deleteByPrimaryKey(userId);
	}

	@Override
	public int insert(UserCache record) {
		return this.mapper.insert(record);
	}

	@Override
	public int insertSelective(UserCache record) {
		return this.mapper.insertSelective(record);
	}

	@Override
	public UserCache selectByPrimaryKey(Integer userId) {
		return this.mapper.selectByPrimaryKey(userId);
	}

	@Override
	public int updateByPrimaryKeySelective(UserCache record) {
		return this.mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserCache record) {
		return this.mapper.updateByPrimaryKey(record);
	}

	@Override
	public UserCache selectBaseInfoByUserId(Integer userId) {
		return this.mapper.selectBaseInfoByUserId(userId);
	}

}
