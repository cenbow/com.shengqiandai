package cn.vfunding.vfunding.biz.aop;

import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionVO;
import cn.vfunding.vfunding.common.interceptors.annotations.AfterAction;

public interface IBorrowTenderAop {
	//@NeedAfterInterceptor("borrowTender")
	@AfterAction(actionName="borrowTender")
	UserTenderActionResultVO insertUserTenderAction(UserTenderActionVO vo);

	
}
