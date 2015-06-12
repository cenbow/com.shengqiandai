package cn.vfunding.vfunding.biz.week.service;

import java.util.List;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.lock.NeedLock;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.week.model.WeekRepayment;
import cn.vfunding.vfunding.biz.week.vo.SearchWeekRepaymentVO;
import cn.vfunding.vfunding.biz.week.vo.WeekRepaymentVO;
import cn.vfunding.vfunding.common.interceptors.annotations.AfterAction;

public interface IWeekRepaymentService {
	int deleteByPrimaryKey(Integer id);

	int insert(WeekRepayment record);

	int insertSelective(WeekRepayment record);

	WeekRepayment selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(WeekRepayment record);

	int updateByPrimaryKey(WeekRepayment record);

	List<WeekRepayment> selectWeekRepaymentListPage(PageSearch pageSearch);

	List<WeekRepaymentVO> selectWeekRepaymentGroupByWeekIdListPage(
			PageSearch pageSearch);

	WeekRepaymentVO selectSumWeekRepayment(
			SearchWeekRepaymentVO searchWeekRepaymentVO);
	
	@NeedLock
	//@NeedAfterInterceptor("weekRepayment")
	@AfterAction(actionName="weekRepayment")
	Json updateRepayWeek(Integer weekRepaymentId, UserSession user,String ip);
}