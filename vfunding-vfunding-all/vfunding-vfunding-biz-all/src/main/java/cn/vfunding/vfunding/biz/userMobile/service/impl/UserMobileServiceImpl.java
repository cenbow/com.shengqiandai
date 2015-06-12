package cn.vfunding.vfunding.biz.userMobile.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.BeanUtils;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.dao.AccountFeelMapper;
import cn.vfunding.vfunding.biz.account.model.AccountFeel;
import cn.vfunding.vfunding.biz.userMobile.dao.UserMobileMapper;
import cn.vfunding.vfunding.biz.userMobile.dao.UserStatisticsDataMobileMapper;
import cn.vfunding.vfunding.biz.userMobile.model.UserMobile;
import cn.vfunding.vfunding.biz.userMobile.model.UserRechargeCashMobile;
import cn.vfunding.vfunding.biz.userMobile.model.UserStatisticsDataMobile;
import cn.vfunding.vfunding.biz.userMobile.model.UserTenderMobile;
import cn.vfunding.vfunding.biz.userMobile.service.IUserMobileService;

@Service("userMobileService")
public class UserMobileServiceImpl implements IUserMobileService {

	@Autowired
	private UserMobileMapper umMapper;

	@Autowired
	private UserStatisticsDataMobileMapper usdMapper;

	@Autowired
	private AccountFeelMapper afMapper;

	/**
	 * 用户帐号信息
	 * 
	 * @param id
	 * @return author LiLei 2014年5月8日
	 */
	@Override
	public UserMobile selectUserInfoById(Integer id) {
		// TODO Auto-generated method stub
		UserMobile u = this.umMapper.selectUserInfoById(id);
		BigDecimal userHavaInterestMoney = this.umMapper
				.selectUserHavaInterestMoney(id);
		UserMobile um = this.umMapper.selectUserLastRepayTime(id);
		AccountFeel af = this.afMapper.selectByUserId(id);
		if (EmptyUtil.isNotEmpty(um)) {
			u.setEndYieldTime(um.getEndYieldTime());
			u.setEndYieldMoney(um.getEndYieldMoney());
		} else {
			u.setEndYieldTime("");
			u.setEndYieldMoney(new BigDecimal("0"));
		}
		u.setSumYield(userHavaInterestMoney);
		if (EmptyUtil.isNotEmpty(af)) {
			u.setFeelMoney(af.getUseMoney());
		} else {
			u.setFeelMoney(new BigDecimal("0"));
		}
		return u;
	}

	/**
	 * 根据userID查询投资记录
	 * 
	 * @param pageSearch
	 * @return author LiLei 2014年5月8日
	 */
	@Override
	public List<UserTenderMobile> selectBorrowTenderByUserIdListPage(
			PageSearch pageSearch) {
		// TODO Auto-generated method stub
		return this.umMapper.selectBorrowTenderByUserIdListPage(pageSearch);
	}

	/**
	 * 根据USERID查询充值提现记录
	 * 
	 * @param pageSearch
	 * @return author LiLei 2014年5月8日
	 */
	@Override
	public List<UserRechargeCashMobile> selectRechargeCashByUserIdListPage(
			PageSearch pageSearch) {
		// TODO Auto-generated method stub
		return this.umMapper.selectRechargeCashByUserIdListPage(pageSearch);
	}

	/**
	 * 用户资产详情
	 * 
	 * @param id
	 * @return author LiLei 2014年5月7日
	 */
	@Override
	public UserStatisticsDataMobile selectUserAssetsInfoByUserId(Integer id) {
		// TODO Auto-generated method stub
		UserMobile u = this.umMapper.selectUserInfoById(id);
		AccountFeel af = this.afMapper.selectByUserId(id);
		if (EmptyUtil.isNotEmpty(af)) {
			u.setFeelMoney(af.getUseMoney());
		} else {
			u.setFeelMoney(new BigDecimal("0"));
		}
		UserStatisticsDataMobile usd = BeanUtils.copyBean(u,
				UserStatisticsDataMobile.class);
		List<UserStatisticsDataMobile> EarnedAndWait = this.usdMapper
				.selectUserEarnedAndWaitByUserId(id);
		for (UserStatisticsDataMobile earnedAndWait : EarnedAndWait) {
			if (earnedAndWait.getStatus() == 0) {
				usd.setSumWaitAccount(earnedAndWait.getSumWaitAccount());
				usd.setSumWaitInterest(earnedAndWait.getSumWaitInterest());
			} else if (earnedAndWait.getStatus() == 1) {
				usd.setSumEarnedInterest(earnedAndWait.getSumWaitInterest());
			}
		}

		BigDecimal earnedInterestOfSon = this.usdMapper
				.selectUserSumEarnedInterestOfSonByUserId(id);
		BigDecimal waitInterestOfSon = this.usdMapper
				.selectUserSumWaitInterestOfSonByUserId(id);
		BigDecimal tenderMoney = this.usdMapper.selectSumTenderMoney(id);
		BigDecimal rechargeMoney = this.usdMapper.selectSumRechargeMoney(id);
		BigDecimal cashMoney = this.usdMapper.selectSumCashMoney(id);
		if (EmptyUtil.isEmpty(tenderMoney)) {
			tenderMoney = new BigDecimal(0);
		}
		if (EmptyUtil.isEmpty(rechargeMoney)) {
			rechargeMoney = new BigDecimal(0);
		}
		if (EmptyUtil.isEmpty(cashMoney)) {
			cashMoney = new BigDecimal(0);
		}
		usd.setSumWaitSonInterest(waitInterestOfSon);
		usd.setSumEarnedSonInterest(earnedInterestOfSon);
		usd.setSumTenderMoney(tenderMoney.setScale(2, BigDecimal.ROUND_DOWN));
		usd.setSumCashMoney(cashMoney.setScale(2, BigDecimal.ROUND_DOWN));
		usd.setSumRechargeMoney(rechargeMoney
				.setScale(2, BigDecimal.ROUND_DOWN));
		return usd;
	}

	/**
	 * 累计好友返利
	 * 
	 * @param id
	 * @return author LiLei 2014年5月8日
	 */
	@Override
	public BigDecimal selectUserSumInterestOfSonByUserId(Integer id) {
		// TODO Auto-generated method stub
		BigDecimal earnedInterestOfSon = this.usdMapper
				.selectUserSumEarnedInterestOfSonByUserId(id);
		BigDecimal waitInterestOfSon = this.usdMapper
				.selectUserSumWaitInterestOfSonByUserId(id);
		if (earnedInterestOfSon == null) {
			earnedInterestOfSon = new BigDecimal(0);
		}
		if (waitInterestOfSon == null) {
			waitInterestOfSon = new BigDecimal(0);
		}
		BigDecimal count = earnedInterestOfSon.add(waitInterestOfSon).setScale(
				2, BigDecimal.ROUND_DOWN);
		return count;
	}

}
