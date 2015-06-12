package cn.vfunding.vfunding.biz.userMobile.dao;

import java.math.BigDecimal;
import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.userMobile.model.UserMobile;
import cn.vfunding.vfunding.biz.userMobile.model.UserRechargeCashMobile;
import cn.vfunding.vfunding.biz.userMobile.model.UserTenderMobile;

public interface UserMobileMapper {

	UserMobile selectUserInfoById(Integer id);

	UserMobile selectUserLastRepayTime(Integer id);

	BigDecimal selectUserHavaInterestMoney(Integer id);

	List<UserTenderMobile> selectBorrowTenderByUserIdListPage(
			PageSearch pageSearch);

	List<UserRechargeCashMobile> selectRechargeCashByUserIdListPage(
			PageSearch pageSearch);
	
	 void insertUserAdvise(String content);
}