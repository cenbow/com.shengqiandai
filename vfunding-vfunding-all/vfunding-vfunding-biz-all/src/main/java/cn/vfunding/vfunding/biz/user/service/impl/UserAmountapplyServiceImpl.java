package cn.vfunding.vfunding.biz.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.user.dao.UserAmountapplyMapper;
import cn.vfunding.vfunding.biz.user.model.UserAmountapply;
import cn.vfunding.vfunding.biz.user.model.UserAmountapplyWithBLOBs;
import cn.vfunding.vfunding.biz.user.service.IUserAmountapplyService;
@Service("userAmountapplyService")
public class UserAmountapplyServiceImpl implements IUserAmountapplyService {
	
	@Autowired
	private UserAmountapplyMapper userAmountapplyMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return userAmountapplyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserAmountapplyWithBLOBs record) {
		return userAmountapplyMapper.insert(record);
	}

	@Override
	public int insertSelective(UserAmountapplyWithBLOBs record) {
		return userAmountapplyMapper.insertSelective(record);
	}

	@Override
	public UserAmountapplyWithBLOBs selectByPrimaryKey(Integer id) {
		return userAmountapplyMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserAmountapplyWithBLOBs record) {
		return userAmountapplyMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(UserAmountapplyWithBLOBs record) {
		return userAmountapplyMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(UserAmountapply record) {
		return userAmountapplyMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<UserAmountapplyWithBLOBs> selectByParam(UserAmountapply record) {
		return userAmountapplyMapper.selectByParam(record);
	}

	@Override
	public List<UserAmountapply> selectAmountApplyListPage(PageSearch pageSearch) {
		return userAmountapplyMapper.selectAmountApplyListPage(pageSearch);
	}
	
}
