package cn.vfunding.vfunding.biz.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.dao.LockRecordMapper;
import cn.vfunding.vfunding.biz.system.model.LockRecord;
import cn.vfunding.vfunding.biz.system.service.ILockRecordService;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
@Service("lockRecordService")
public class LockRecordServiceImpl implements ILockRecordService {

	@Autowired
	private LockRecordMapper mapper;
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer userId) {
		return this.mapper.deleteByPrimaryKey(userId);
	}

	@Override
	public int insert(LockRecord record) {
		return this.mapper.insert(record);
	}

	@Override
	public int insertSelective(LockRecord record) {
		return this.mapper.insertSelective(record);
	}

	@Override
	public LockRecord selectByPrimaryKey(Integer userId) {
		return this.mapper.selectByPrimaryKey(userId);
	}

	@Override
	public int updateByPrimaryKeySelective(LockRecord record) {
		return this.mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(LockRecord record) {
		return this.updateByPrimaryKey(record);
	}

	@Override
	public List<LockRecord> selectByListPage(PageSearch search) {
		return this.mapper.selectByListPage(search);
	}

	@Override
	public LockRecord selectByLoginValue(String value) {
		return this.mapper.selectByLoginValue(value);
	}

	/**
	 * 后台解锁用户
	 * 0正常；1锁定
	 * @author liuhuan
	 */
	@Override
	public int lockUserByUserId(Integer userId,Integer status) {
		User user = userMapper.selectByPrimaryKey(userId);
		UserWithBLOBs uw = new UserWithBLOBs();
		uw.setUserId(user.getUserId());
		uw.setIslock(status);
		userMapper.updateByPrimaryKeySelective(uw);
		this.mapper.deleteByPrimaryKey(userId);
		return 1;
	}
}
