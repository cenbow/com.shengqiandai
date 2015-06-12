package cn.vfunding.vfunding.biz.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.user.dao.UserAmountMapper;
import cn.vfunding.vfunding.biz.user.model.UserAmount;
import cn.vfunding.vfunding.biz.user.service.IUserAmountService;
@Service("userAmountService")
public class UserAmountServiceImpl implements IUserAmountService {
	@Autowired
	private UserAmountMapper userAmountMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return userAmountMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserAmount record) {
		return userAmountMapper.insert(record);
	}

	@Override
	public int insertSelective(UserAmount record) {
		return userAmountMapper.insertSelective(record);
	}

	@Override
	public UserAmount selectByPrimaryKey(Integer id) {
		return userAmountMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserAmount record) {
		return userAmountMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserAmount record) {
		return userAmountMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<UserAmount> selectByParam(UserAmount record) {
		return userAmountMapper.selectByParam(record);
	}

}
