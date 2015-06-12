package cn.vfunding.vfunding.prd.www.login.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.StringUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.BorrowTender;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.inviteCode.service.IInviteCodeService;
import cn.vfunding.vfunding.biz.inviteCode.status.DialogStatusIndex;
import cn.vfunding.vfunding.biz.mq.service.IUserTaskService;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.system.model.AllStatistics;
import cn.vfunding.vfunding.biz.system.model.Article;
import cn.vfunding.vfunding.biz.system.model.ScrollpicMobile;
import cn.vfunding.vfunding.biz.system.model.UserTask;
import cn.vfunding.vfunding.biz.system.service.IAllStatisticsService;
import cn.vfunding.vfunding.biz.system.service.IArticleService;
import cn.vfunding.vfunding.biz.system.service.IScrollpicService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.biz.week.model.Week;
import cn.vfunding.vfunding.biz.week.service.IWeekService;

@Controller
@RequestMapping("")
public class IndexController {

	@Autowired
	private IArticleService articleService;
	@Autowired
	private IScrollpicService scrollPicService;
	@Autowired
	private IBorrowService borrowService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IBorrowTenderService borrowTenderService;
	@Autowired
	private IAllStatisticsService allStatisticsService;
	@Autowired
	private IUserTaskService userTaskService;
	@Autowired
	private IWeekService weekService;
	@Autowired
	private IInviteCodeService inviteCodeService;

	@RequestMapping("/swfPage")
	public ModelAndView swfPage() {
		ModelAndView mv = new ModelAndView("vedio");
		return mv;
	}

	@RequestMapping("/notFound")
	public ModelAndView notFound() {
		ModelAndView mv = new ModelAndView("error/404");
		return mv;
	}

	/**
	 * 首页
	 * 
	 * @return 2014年6月25日 liuyijun
	 */
	@RequestMapping("")
	public ModelAndView welcome(HttpServletRequest request) {
		return this.index(request);
	}

	/**
	 * @Description:加载首页
	 * 
	 * @author liuhuan
	 */

	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		if (CheckAgent(request.getHeader("user-agent"))&& request.getParameter("mac")==null) {
			return new ModelAndView("redirect:http://m.vfunding.cn");
		}
		ModelAndView mv = new ModelAndView("index"); // TODO 页面
		// 首页banner
		// List<Scrollpic> scrollPics =
		// scrollPicService.selectScrollPicByStatus(1, 3); // TODO status???
		List<ScrollpicMobile> scrollPics = scrollPicService
				.selectScrollPicByTypeMobile(1);
		// 首页公告文章3条
		List<Article> reports = articleService.selectArticleByStatusCount(95,
				1, 0, 2);
		// 首页媒体文章3条
		List<Article> meidas = articleService.selectArticleByStatusCount(85, 1,
				0, 3);
		// 首页标6条
		// List<Borrow> borrows = borrowService.selectBorrowForIndex();
		// 首页标6条
		PageSearch pageSearch = new PageSearch();
		pageSearch.setPage(1);
		pageSearch.setRows(6);
		List<Borrow> borrows = borrowService
				.selectBorrowForIndex(pageSearch, 0);
		AllStatistics as = allStatisticsService.selectAllStatistics();
		// 投资总额
		BigDecimal allTender = as.getAllTenderMoney();
		// 利息总额
		BigDecimal allInterest = as.getAllInterestMoney();
		// 首页周盈宝
		Week week = weekService.selectIndexWeek();
		mv.addObject("reports", reports);
		mv.addObject("meidas", meidas);
		mv.addObject("scrollPics", scrollPics);
		mv.addObject("borrows", borrows);
		mv.addObject("allTender",
				StringUtil.getFinanceString(allTender.doubleValue()));
		mv.addObject("allInterest",
				StringUtil.getFinanceString(allInterest.doubleValue()));
		mv.addObject("week", week);
		return mv;
	}

	/**
	 * 新手活动页
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/fresh")
	public ModelAndView fresh(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user/fresh");
		UserSession user = UserSession.getUserSession();
		if (EmptyUtil.isNotEmpty(user)) {
			PageSearch search = new PageSearch();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", user.getUserId());
			map.put("status", new int[] { 1, 5 });
			search.setEntity(map);
			List<BorrowTender> tenders = borrowTenderService
					.selectListByParam(search);
			mv.addObject("tenders", tenders);// 我要投资(不含体验标)
			User record = new User();
			PageSearch pageSearch = new PageSearch();
			record.setInviteUserid(user.getUserId().toString());
			pageSearch.setEntity(record);
			List<User> friendList = this.userService
					.selectFriendByUserIdListPage(pageSearch);
			Boolean invite = false;
			if (EmptyUtil.isNotEmpty(friendList)) {
				List<Integer> userids = new ArrayList<Integer>();
				for (User u : friendList) {
					userids.add(u.getUserId());
				}
				map.clear();
				map.put("userId", userids.toArray());
				map.put("status", new int[] { 1, 5 });
				List<BorrowTender> bts = this.borrowTenderService
						.selectListByMyFriend(pageSearch);
				if (EmptyUtil.isNotEmpty(bts)) {
					invite = Double.parseDouble(bts.get(0).getAccount()) > 100.0 ? true
							: false;
				}
			}
			mv.addObject("invite", invite);// 邀请好友
			mv.addObject("user", user);// 注册会员、资料认证
			UserTask phoneTask = this.userTaskService.selectTaskByIdAndTaskId(
					user.getUserId(), 6);
			UserTask autoTask = this.userTaskService.selectTaskByIdAndTaskId(
					user.getUserId(), 7);
			Integer phoneTaskStatus = 0;
			Integer autoTaskStatus = 0;
			if (EmptyUtil.isNotEmpty(phoneTask)) {
				phoneTaskStatus = 1;
			}
			if (EmptyUtil.isNotEmpty(autoTask)) {
				autoTaskStatus = 1;
			}
			mv.addObject("phoneTask", phoneTaskStatus);// 邀请好友
			mv.addObject("autoTask", autoTaskStatus);// 注册会员、资料认证
		}
		//新手任务 点击后 修改新手任务 提醒状态
		modifyNoviceTaskbgColor(request);
		
		
		return mv;
	}
	/**
	 * wang.zeyan 2015年5月6日
	 */
	private void modifyNoviceTaskbgColor(HttpServletRequest request){
		Integer userId = UserSession.getLoginUserId();
		if(userId != null){
//			boolean status = inviteCodeService.isLeapDialog(userId, DialogStatusIndex.noviceTaskbgColorStatus);
//			if(! status ){
//				inviteCodeService.updateDialogStatusByPrimaryKey(userId, DialogStatusIndex.noviceTaskbgColorStatus);
//				request.getSession().setAttribute("noviceTaskbgColorStatus", !status);
//			}
		}
	}

	/**
	 * 资金托管详细页
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/cmb")
	public ModelAndView cmb() {
		ModelAndView mv = new ModelAndView("cmb");
		return mv;
	}

	static String[] keywords = { "Android", "iPhone", "iPod", "iPad",
			"Windows Phone", "MQQBrowser" };

	public static boolean CheckAgent(String agent) {
		boolean flag = false;

		// 排除 Windows 桌面系统
		if (agent != null
				&& !agent.contains("Windows NT")
				|| (agent != null && agent.contains("Windows NT") && agent
						.contains("compatible; MSIE 9.0;"))) {
			// 排除 苹果桌面系统
			if (!agent.contains("Windows NT") && !agent.contains("Macintosh")) {
				for (String item : keywords) {
					if (agent.contains(item)) {
						flag = true;
						break;
					}
				}
			}
		}  
		return flag;
	}
}
