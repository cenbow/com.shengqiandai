package cn.vfunding.vfunding.biz.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.user.dao.UserUnionMapper;
import cn.vfunding.vfunding.biz.user.model.UserUnion;
import cn.vfunding.vfunding.biz.user.service.IUserUnionService;
@Service("userUnionService")
public class UserUnionServiceImpl implements IUserUnionService {

	@Autowired
	private UserUnionMapper userUnionMapper;
	@Override
	public int deleteByPrimaryKey(String unionUserId) {
		return this.userUnionMapper.deleteByPrimaryKey(unionUserId);
	}

	@Override
	public int insert(UserUnion record) {
		return this.userUnionMapper.insert(record);
	}

	@Override
	public int insertSelective(UserUnion record) {
		return this.userUnionMapper.insertSelective(record);
	}

	@Override
	public UserUnion selectByPrimaryKey(String unionUserId) {
		return this.userUnionMapper.selectByPrimaryKey(unionUserId);
	}

	@Override
	public int updateByPrimaryKeySelective(UserUnion record) {
		return this.userUnionMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserUnion record) {
		return this.userUnionMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<UserUnion> selectCanProfitUser() {
		return this.userUnionMapper.selectCanProfitUser();
	}

	@Override
	public List<UserUnion> selectCanRevive() {
		return this.userUnionMapper.selectCanRevive();
	}

	@Override
	public List<UserUnion> selectNotRevive() {
		return this.userUnionMapper.selectCanNotRevive();
	}

	@Override
	public UserUnion selectByUserId(Integer userId) {
		return this.userUnionMapper.selectByUserId(userId);
	}

}
