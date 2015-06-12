package cn.vfunding.vfunding.biz.userMobile.service;


import java.math.BigDecimal;
import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.userMobile.model.UserMobile;
import cn.vfunding.vfunding.biz.userMobile.model.UserRechargeCashMobile;
import cn.vfunding.vfunding.biz.userMobile.model.UserStatisticsDataMobile;
import cn.vfunding.vfunding.biz.userMobile.model.UserTenderMobile;

public interface IUserMobileService {
	UserMobile selectUserInfoById(Integer id);
	
    List<UserTenderMobile> selectBorrowTenderByUserIdListPage(PageSearch pageSearch);
    
    List<UserRechargeCashMobile> selectRechargeCashByUserIdListPage(PageSearch pageSearch);
    
    UserStatisticsDataMobile selectUserAssetsInfoByUserId(Integer id);
    
    BigDecimal selectUserSumInterestOfSonByUserId(Integer id);
    
}
