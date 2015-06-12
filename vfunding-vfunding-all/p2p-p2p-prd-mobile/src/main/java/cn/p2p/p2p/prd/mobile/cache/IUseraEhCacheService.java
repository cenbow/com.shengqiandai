package cn.p2p.p2p.prd.mobile.cache;

import cn.vfunding.vfunding.biz.user.model.User;

public interface IUseraEhCacheService {

	User selectByUserIdFromCache(Integer userId);

	void loginCacheEvict(Integer userId);

}
