package cn.vfunding.vfunding.biz.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.user.dao.UserFromMapper;
import cn.vfunding.vfunding.biz.user.model.UserFrom;
import cn.vfunding.vfunding.biz.user.service.IUserFromService;

@Service
public class UserFromServiceImpl implements IUserFromService {

	@Autowired
	private UserFromMapper userFromMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(UserFrom record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(UserFrom record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserFrom selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(UserFrom record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(UserFrom record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UserFrom> selectFromUserInfoListPage(PageSearch pageSearch) {
		// TODO Auto-generated method stub
		return userFromMapper.selectFromUserInfoListPage(pageSearch);
	}

	@Override
	public UserFrom selectByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return this.userFromMapper.selectByUserId(userId);
	}

}
