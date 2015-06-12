package cn.vfunding.vfunding.biz.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.account.dao.AccountFeelLogMapper;
import cn.vfunding.vfunding.biz.account.model.AccountFeelLog;
import cn.vfunding.vfunding.biz.account.service.IAccountFeelLogService;
@Service("accountFeelLogService")
public class AccountFeelLogServiceImpl implements IAccountFeelLogService {

	@Autowired
	private AccountFeelLogMapper accountFeelLogMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(AccountFeelLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(AccountFeelLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AccountFeelLog selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(AccountFeelLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(AccountFeelLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AccountFeelLog> selectByUserIdType(AccountFeelLog record) {
		return accountFeelLogMapper.selectByUserIdType(record);
	}
	
	

}
