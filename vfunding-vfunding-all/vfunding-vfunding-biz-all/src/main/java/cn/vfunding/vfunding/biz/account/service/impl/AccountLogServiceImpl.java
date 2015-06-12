package cn.vfunding.vfunding.biz.account.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.dao.AccountLogMapper;
import cn.vfunding.vfunding.biz.account.model.AccountLog;
import cn.vfunding.vfunding.biz.account.service.IAccountLogService;
@Service("accountLogService")
public class AccountLogServiceImpl implements IAccountLogService {

	@Autowired
	private AccountLogMapper accountLogMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return accountLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AccountLog record) {
		return accountLogMapper.insert(record);
	}

	@Override
	public int insertSelective(AccountLog record) {
		return accountLogMapper.insertSelective(record);
	}

	@Override
	public AccountLog selectByPrimaryKey(Integer id) {
		return accountLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AccountLog record) {
		return accountLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AccountLog record) {
		return accountLogMapper.updateByPrimaryKey(record);
	}

	/**
	 * 资金记录
	 * @author liuhuan
	 */
	@Override
	public List<AccountLog> selectAccountLogListPage(PageSearch pageSearch) {
		return accountLogMapper.selectAccountLogListPage(pageSearch);
	}

	@Override
	public BigDecimal selectSinaBonusHistoryTotalByUserId(Integer userId) {
		return accountLogMapper.selectSinaBonusHistoryTotalByUserId(userId);
	}

}
