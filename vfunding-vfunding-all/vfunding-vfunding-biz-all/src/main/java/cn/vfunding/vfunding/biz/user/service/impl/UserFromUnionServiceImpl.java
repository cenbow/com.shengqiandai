package cn.vfunding.vfunding.biz.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.user.dao.UserFromUnionMapper;
import cn.vfunding.vfunding.biz.user.model.CleanOldUserVO;
import cn.vfunding.vfunding.biz.user.model.UserFromUnion;
import cn.vfunding.vfunding.biz.user.service.IUserFromUnionService;

@Service("userFromUnionService")
public class UserFromUnionServiceImpl implements IUserFromUnionService {

	@Autowired
	private UserFromUnionMapper userFromUnionMapper;

	@Override
	public int deleteByPrimaryKey(Integer userId) {
		return this.userFromUnionMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public int insert(UserFromUnion record) {
		return this.userFromUnionMapper.insert(record);
	}

	@Override
	public int insertSelective(UserFromUnion record) {
		return this.userFromUnionMapper.insertSelective(record);
	}

	@Override
	public UserFromUnion selectByPrimaryKey(Integer userId) {
		return this.userFromUnionMapper.selectByPrimaryKey(userId);
	}

	@Override
	public int updateByPrimaryKeySelective(UserFromUnion record) {
		return this.userFromUnionMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserFromUnion record) {
		return this.userFromUnionMapper.updateByPrimaryKey(record);
	}

	@Override
	public UserFromUnion selectByUnionUserIdAndLastRegist(String unionUserId) {
		return this.userFromUnionMapper
				.selectByUnionUserIdAndLastRegist(unionUserId);
	}

	@Override
	public int cleanOldUserByUnionUser(CleanOldUserVO vo) {
		return this.userFromUnionMapper.cleanOldUserByUnionUser(vo);
	}

	@Override
	public List<UserFromUnion> selectByNeedChangePeriods() {
		return this.userFromUnionMapper.selectByNeedChangePeriods();
	}

}
