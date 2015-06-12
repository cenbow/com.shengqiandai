//package cn.p2p.p2p.prd.mobile.method.all;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.codec.digest.DigestUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import cn.p2p.p2p.prd.mobile.method.request.vo.UserWeekTenderListRequestVO;
//import cn.p2p.p2p.prd.mobile.method.request.vo.WeekDetailRequestVO;
//import cn.p2p.p2p.prd.mobile.method.request.vo.WeekListRequestVO;
//import cn.p2p.p2p.prd.mobile.method.request.vo.WeekRepayRequestVO;
//import cn.p2p.p2p.prd.mobile.method.request.vo.WeekTenderRequestVO;
//import cn.vfunding.common.framework.easyui.page.utils.Json;
//import cn.vfunding.common.framework.utils.beans.EmptyUtil;
//import cn.vfunding.common.framework.utils.page.PageSearch;
//import cn.vfunding.vfunding.biz.account.service.IAccountService;
//import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
//import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
//import cn.vfunding.vfunding.biz.common.vo.UserTenderActionVO;
//import cn.vfunding.vfunding.biz.user.model.User;
//import cn.vfunding.vfunding.biz.user.service.IUserService;
//import cn.vfunding.vfunding.biz.usertoken.service.IUserTokenService;
//import cn.vfunding.vfunding.biz.week.model.Week;
//import cn.vfunding.vfunding.biz.week.model.WeekBorrow;
//import cn.vfunding.vfunding.biz.week.model.WeekTender;
//import cn.vfunding.vfunding.biz.week.service.IWeekBorrowService;
//import cn.vfunding.vfunding.biz.week.service.IWeekRepaymentService;
//import cn.vfunding.vfunding.biz.week.service.IWeekService;
//import cn.vfunding.vfunding.biz.week.service.IWeekTenderService;
//import cn.vfunding.vfunding.biz.week.vo.SearchWeekRepaymentVO;
//import cn.vfunding.vfunding.biz.week.vo.WeekRepaymentVO;
//
//@Service
//public class WeekMethod {
//	@Autowired
//	private IWeekService weekService;
//
//	@Autowired
//	private IWeekBorrowService weekBorrowService;
//
//	@Autowired
//	private IWeekTenderService weekTenderService;
//
//	@Autowired
//	private IAccountService accountService;
//
//	@Autowired
//	private IWeekRepaymentService weekRepaymentService;
//	@Autowired
//	private IBorrowTenderService borrowTenderService;
//	@Autowired
//	private IUserTokenService userTokenService;
//	@Autowired
//	private IUserService userService;
//
//	/**
//	 * @Description:标的详情页（体验标&普通标）
//	 * @param id
//	 *            标id
//	 * @return
//	 * @author liuhuan
//	 */
//	public WeekDetailResponseVO weekDetail(WeekDetailRequestVO wdRequet) {
//		if (wdRequet.getWeekId() == null || wdRequet.getWeekId() < 1)
//			return new WeekDetailResponseVO("weekId_fail", "计划编号不正确");
//		// 周盈宝 信息查询
//		Week week = this.weekService.selectByPrimaryKey(wdRequet.getWeekId());
//		if (EmptyUtil.isEmpty(week) || week.getStatus() < 3) {
//			return new WeekDetailResponseVO("week_not_open", "此计划还未发布");
//		}
//		// 周盈宝 对应标的查询
//		List<WeekBorrow> weekBorrowList = this.weekBorrowService.selectWeekBorrowByWeekId(wdRequet.getWeekId());
//		return new WeekDetailResponseVO(week, weekBorrowList);
//	}
//
//	/**
//	 * 周盈宝列表
//	 * 
//	 * @param vo
//	 * @param pageSearch
//	 * @return
//	 * @throws Exception
//	 */
//	public WeekListResponseVO weekList(WeekListRequestVO wlRequest) {
//		if (wlRequest.getPage() < 1)
//			return new WeekListResponseVO("page_fail", "页数不可小于1");
//		PageSearch pageSearch = new PageSearch();
//		pageSearch.setPage(wlRequest.getPage());
//		pageSearch.setRows(wlRequest.getRows());
//		List<Week> list = this.weekService.selectWeekForBuyListPage(pageSearch);
//		if (list.isEmpty())
//			return new WeekListResponseVO("week_count_zero", "暂无产品");
//		for (Week w : list) {
//			SearchWeekRepaymentVO searchWeekRepaymentVO = new SearchWeekRepaymentVO();
//			searchWeekRepaymentVO.setStatus(0);
//			searchWeekRepaymentVO.setWeekId(w.getId());
//			WeekRepaymentVO weekRepaymentVO = this.weekRepaymentService.selectSumWeekRepayment(searchWeekRepaymentVO);
//			if (EmptyUtil.isNotEmpty(weekRepaymentVO)) {
//				w.setWeekIsRepay(false);
//			} else {
//				w.setWeekIsRepay(true);
//			}
//
//		}
//		return new WeekListResponseVO(list);
//	}
//
//	/**
//	 * 投资
//	 * 
//	 * @param request
//	 * @param vo
//	 * @return
//	 */
//	public WeekTenderResponseVO weekTenderAction(WeekTenderRequestVO wtRequest) {
//		Integer userId = this.userTokenService.selectUserIdByToken(wtRequest.getToken());
//		User user = this.userService.selectByPrimaryKey(userId);
//		UserTenderActionVO vo = new UserTenderActionVO();
//		UserTenderActionResultVO resultVO = new UserTenderActionResultVO();
//		Week week = this.weekService.selectByPrimaryKey(wtRequest.getWeekId());
//		if (new Date().before(week.getSaleTime())) {
//			return new WeekTenderResponseVO("week_no_sale_time", "还没到开售时间哦");
//		}
//		if (new Date().after(week.getExpireTime())) {
//			return new WeekTenderResponseVO("week_time_out", "认购时间结束了哦");
//		}
//		if (week.getCreateUser() == userId) {
//			return new WeekTenderResponseVO("week_tender_fail", "不可以投资自己的标的");
//		}
//		if (week.getStatus() != 3) {
//			return new WeekTenderResponseVO("week_tender_fail", "此标的不可投");
//		}
//		if (wtRequest.getBuyShare() % week.getBuyBase() != 0) {
//			return new WeekTenderResponseVO("week_tender_money_fail", "投资金额不符合投资基数");
//		}
//		if (wtRequest.getBuyShare() < week.getSingleMin()) {
//			return new WeekTenderResponseVO("week_buyShare_fail", "投资金额低于最小投资份数");
//		}
//		if (week.getSingleMax() > 0 && wtRequest.getBuyShare() > week.getSingleMax()) {
//			return new WeekTenderResponseVO("week_buyShare_fail", "投资金额大于最大投资份数");
//		}
//		resultVO.setTenderType("week");
//		if (DigestUtils.md5Hex(wtRequest.getPaypassword()).equals(user.getPaypassword())) {
//			vo.setUserType(user.getTypeId());
//			vo.setUserId(userId);
//			vo.setUserip(wtRequest.getOsType());
//			vo.setBuyShare(wtRequest.getBuyShare());
//			vo.setPaypassword(wtRequest.getPaypassword());
//			vo.setWeekId(wtRequest.getWeekId());
//			vo.setTenderType("week");
//			resultVO = this.borrowTenderService.userTenderAction(vo);
//		} else {
//			return new WeekTenderResponseVO("paypassword_fail", "支付密码错误");
//		}
//		return new WeekTenderResponseVO(resultVO);
//	}
//
//	/**
//	 * 查询个人周盈宝投资记录
//	 * 
//	 * @param pageSearch
//	 * @param start
//	 * @param end
//	 * @return
//	 * @throws Exception
//	 */
//	public UserWeekTenderListResponseVO userWeekTenderList(UserWeekTenderListRequestVO uwtlRequest) {
//		if (uwtlRequest.getPage() < 1)
//			return new UserWeekTenderListResponseVO("page_fail", "页数不可小于1");
//		PageSearch pageSearch = new PageSearch();
//		Integer userId = this.userTokenService.selectUserIdByToken(uwtlRequest.getToken());
//		Map<String, Object> map = new HashMap<String, Object>();
//		pageSearch.setPage(uwtlRequest.getPage());
//		pageSearch.setRows(uwtlRequest.getRows());
//		map.put("userId", userId);
//		map.put("start", uwtlRequest.getStart());
//		map.put("end", uwtlRequest.getEnd());
//		pageSearch.setEntity(map);
//		List<WeekTender> wtList = weekTenderService.selectByUserId(pageSearch);
//		if (wtList.isEmpty())
//			return new UserWeekTenderListResponseVO("tender_list_null_fail", "用户暂无投资记录");
//		for (WeekTender wt : wtList) {
//			wt.setWeek(weekService.selectByPrimaryKey(wt.getWeekId()));
//		}
//		return new UserWeekTenderListResponseVO(wtList);
//	}
//
//	/**
//	 * 还款
//	 * 
//	 * @param request
//	 * @param repaymentId
//	 * @return
//	 */
//	public WeekRepayResponseVO WeekRepayment(WeekRepayRequestVO wrRequest) {
//		if (wrRequest.getRepayId() == null || wrRequest.getRepayId() < 1)
//			return new WeekRepayResponseVO("repayId_fail", "请输入正确的还款ID");
//		Integer userId = this.userTokenService.selectUserIdByToken(wrRequest.getToken());
//		Json j = this.weekRepaymentService.updateRepayWeek(wrRequest.getRepayId(), userId, wrRequest.getOsType());
//		if(j.getSuccess())
//			return new WeekRepayResponseVO();
//		else
//			return new WeekRepayResponseVO("repayment_fail",j.getMsg());
//		
//	}
//}
