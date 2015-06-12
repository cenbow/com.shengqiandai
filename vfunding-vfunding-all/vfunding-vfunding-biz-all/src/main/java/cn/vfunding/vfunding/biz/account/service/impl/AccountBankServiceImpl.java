package cn.vfunding.vfunding.biz.account.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.dao.AccountBankMapper;
import cn.vfunding.vfunding.biz.account.model.AccountBank;
import cn.vfunding.vfunding.biz.account.service.IAccountBankService;
import cn.vfunding.vfunding.biz.sina.model.SinaCard;
import cn.vfunding.vfunding.biz.system.dao.LinkageMapper;

@Service("accountBankService")
public class AccountBankServiceImpl implements IAccountBankService {

	@Autowired
	private AccountBankMapper bankMapper;

	@Autowired
	private LinkageMapper linkageMapper;
	

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.bankMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AccountBank record) {
		List<AccountBank> banks = this.bankMapper.selectByUserId(record.getUserId());
		if (EmptyUtil.isEmpty(banks)) {// 表示第一张卡，默认让其自动绑定
			record.setBind(1);
		}

		this.bankMapper.insert(record);
		return record.getId();
	}

	@Override
	public int insertSelective(AccountBank record) {
		List<AccountBank> banks = this.bankMapper.selectByUserId(record.getUserId());
		if (EmptyUtil.isEmpty(banks)) {// 表示第一张卡，默认让其自动绑定
			record.setBind(0);
		}
		record.setAddtime(DateUtil.getLongTime().intValue());
		this.bankMapper.insertSelective(record);
		return record.getId();
	}

	@Override
	public AccountBank selectByPrimaryKey(Integer id) {
		return this.bankMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AccountBank record) {
		return this.bankMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AccountBank record) {
		return this.bankMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<AccountBank> selectByUserId(Integer userId) {
		return this.bankMapper.selectByUserId(userId);
	}

	@Override
	public List<AccountBank> selectByAccount(String account) {
		return this.bankMapper.selectByAccount(account);
	}

	@Override
	public int bindBankCard(AccountBank accountBank) {
		this.cancelOldBind(accountBank.getUserId());
		return this.bankMapper.updateByPrimaryKeySelective(accountBank);
	}

	@Override
	public List<AccountBank> selectBindByUserId(Integer userId) {
		return this.bankMapper.selectBindByUserId(userId);
	}

	/**
	 * 清楚原绑定的银行卡信息
	 * 
	 * @param userId
	 *            2014年5月9日 liuyijun
	 */
	private void cancelOldBind(Integer userId) {
		List<AccountBank> binds = this.bankMapper.selectBindByUserId(userId);
		if (EmptyUtil.isNotEmpty(binds)) {
			for (AccountBank ab : binds) {
				ab.setBind(0);
				this.updateByPrimaryKeySelective(ab);
			}
		}
	}

	/**
	 * @author liuyijun
	 * 
	 */
	@Override
	public int addAndBind(AccountBank accountBank) {
		this.cancelOldBind(accountBank.getUserId());
		accountBank.setBind(1);
		this.bankMapper.insertSelective(accountBank);
		return accountBank.getId();
	}

	@Override
	public AccountBank selectByAccountNew(Map<String, String> pram) {
		return this.bankMapper.selectByAccountNew(pram);
	}

}
