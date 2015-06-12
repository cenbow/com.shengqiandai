package cn.p2p.p2p.prd.mobile.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.User;

@Service("userEhCacheService")
public class UserEhCacheServiceImpl implements IUseraEhCacheService {

	@Autowired
	private UserMapper userMapper;

	@Cacheable(value = "UserInfoCache", key = "#userId")
	@Override
	public User selectByUserIdFromCache(Integer userId) {
		return this.userMapper.selectByPrimaryKey(userId);
	}

	@CacheEvict(value = "UserInfoCache", key = "#userId")
	@Override
	public void loginCacheEvict(Integer userId) {
		// TODO Auto-generated method stub

	}

}
