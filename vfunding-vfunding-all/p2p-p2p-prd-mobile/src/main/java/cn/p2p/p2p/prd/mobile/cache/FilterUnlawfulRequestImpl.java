package cn.p2p.p2p.prd.mobile.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.user.model.User;

@Service("filterUnlawfulRequest")
public class FilterUnlawfulRequestImpl implements IFilterUnlawfulRequest {

	@Autowired
	private IUseraEhCacheService userehcaCacheService;

	@Override
	public boolean filterRequest(Integer userId, String password) {
		if (EmptyUtil.isNotEmpty(userId) && EmptyUtil.isNotEmpty(password)) {
			User user = this.userehcaCacheService.selectByUserIdFromCache(userId);

			if (EmptyUtil.isNotEmpty(user) && user.getPassword().equals(password)) {
				return true;
			} else {
				throw new BusinessException("8008014", "用户密码错误");
			}
		} else {
			throw new BusinessException("8008015", "密码或者帐号不能为空");
		}
	}

}
