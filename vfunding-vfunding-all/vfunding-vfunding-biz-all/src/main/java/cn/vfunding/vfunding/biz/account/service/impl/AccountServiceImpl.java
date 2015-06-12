package cn.vfunding.vfunding.biz.account.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.dao.AccountMapper;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.common.vo.AccountSystemVO;
import cn.vfunding.vfunding.biz.userMobile.dao.UserStatisticsDataMobileMapper;
import cn.vfunding.vfunding.biz.userMobile.model.UserStatisticsDataMobile;

@Service("accountServiceImpl")
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private UserStatisticsDataMobileMapper userStatisticsDataMobileMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.accountMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Account record) {
		this.accountMapper.insert(record);
		return record.getId();
	}

	@Override
	public int insertSelective(Account record) {
		this.accountMapper.insertSelective(record);
		return record.getId();
	}

	@Override
	public Account selectByPrimaryKey(Integer id) {
		return this.accountMapper.selectByPrimaryKey(id);
	}

	@Override
	public Account selectByUserId(Integer userId) {
		return this.accountMapper.selectByUserId(userId);
	}

	@Override
	public int updateByPrimaryKeySelective(Account record) {
		return this.accountMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Account record) {
		return this.accountMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByUserId(Account record) {
		return this.accountMapper.updateByUserId(record);
	}

	/**
	 * @Description:返利总收益
	 * @param userId
	 * @return
	 * @author liuhuan
	 */
	@Override
	public BigDecimal backAllIncome(Integer userId) {
		BigDecimal wait = userStatisticsDataMobileMapper.selectUserSumWaitInterestOfSonByUserId(userId);
		BigDecimal earned = userStatisticsDataMobileMapper.selectUserSumEarnedInterestOfSonByUserId(userId);
		return (wait == null ? new BigDecimal("0") : wait).add(earned == null ? new BigDecimal("0") : earned);
	}

	/**
	 * @Description:累计 待收&利息
	 * @param userId
	 * @return
	 * @author liuhuan
	 */
	@Override
	public List<UserStatisticsDataMobile> sumAccountInterest(Integer userId) {
		return userStatisticsDataMobileMapper.selectUserEarnedAndWaitByUserId(userId);
	}

	/**
	 * 后台列表
	 * 
	 * @author liuhuan
	 */
	@Override
	public List<AccountSystemVO> selectAccountSystemListPage(PageSearch pageSearch) {
		return this.accountMapper.selectAccountSystemListPage(pageSearch);
	}

	@Override
	public BigDecimal selectSumRechargeByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return this.accountMapper.selectSumRechargeByUserId(userId);
	}

	@Override
	public BigDecimal selectSumCashByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return this.accountMapper.selectSumCashByUserId(userId);
	}

	@Override
	public BigDecimal selectSumUseMoney() {
		return this.accountMapper.selectSumUseMoney();
	}

	@Override
	public List<Account> selectAccountUseMoneyThanZero() {
		return this.accountMapper.selectAccountUseMoneyThanZero();
	}

	@Override
	public List<Account> selectByLeaveUnused() {
		return this.accountMapper.selectByLeaveUnused();
	}

}
