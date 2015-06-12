package cn.vfunding.vfunding.biz.userMobile.dao;

import java.math.BigDecimal;
import java.util.List;

import cn.vfunding.vfunding.biz.userMobile.model.UserStatisticsDataMobile;

public interface UserStatisticsDataMobileMapper {

	List<UserStatisticsDataMobile> selectUserEarnedAndWaitByUserId(Integer id);

	BigDecimal selectUserSumEarnedInterestOfSonByUserId(Integer id);

	BigDecimal selectUserSumWaitInterestOfSonByUserId(Integer id);

	BigDecimal selectSumRechargeMoney(Integer id);

	BigDecimal selectSumCashMoney(Integer id);

	BigDecimal selectSumTenderMoney(Integer id);
}