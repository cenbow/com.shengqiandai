package cn.vfunding.vfunding.prd.www.account.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.interceptors.NeedSession;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.model.AccountFeel;
import cn.vfunding.vfunding.biz.account.model.AccountFeelLog;
import cn.vfunding.vfunding.biz.account.service.IAccountFeelLogService;
import cn.vfunding.vfunding.biz.account.service.IAccountFeelService;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowCollectionService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.common.vo.BorrowTenderVO;
import cn.vfunding.vfunding.biz.common.vo.CollectionTenderUserVO;
import cn.vfunding.vfunding.biz.common.vo.FeelVO;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.system.model.Feel;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IFeelService;
import cn.vfunding.vfunding.biz.user.service.IUserService;

@Controller
@RequestMapping("")
public class AccountFeelController extends BaseController {

	@Autowired
	private IFeelService feelService;
	@Autowired
	private IAccountFeelService accountFeelService;
	@Autowired
	private IBorrowCollectionService borrowCollectionService;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IBorrowTenderService borrowTenderService;
	@Autowired
	private IAccountFeelLogService accountFeelLogService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IBorrowService borrowService;

	/**
	 * @Description:获取账户接口 for php
	 * @param userId
	 * @return
	 * @author liuhuan
	 */
	@RequestMapping("/myAccountFeel")
	@ResponseBody
	public Json myAccountFeel(@RequestParam("userId") String userId) {
		Json j = new Json();
		if (userId == null || "".equals(userId)) {
			j.setMsg("用户ID不能为空");
		} else {
			AccountFeel accountFeel = accountFeelService.selectByUserId(Integer.parseInt(userId));
			j.setObj(accountFeel);
		}
		return j;
	}

	/**
	 * @Description:体验金充值页面 for java
	 * @return
	 * @author liuhuan
	 */
	@NeedSession(returnUrl = "/user/feelCard")
	@RequestMapping("/user/feelCard")
	public ModelAndView myGift() {
		ModelAndView mv = new ModelAndView("user/feelCard");
		Integer userId = UserSession.getLoginUserId();
		// 账户信息
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		AccountFeel accountFeel = accountFeelService.selectByUserId(userId);
		mv.addObject("accountFeel", accountFeel);
		PageSearch search = new PageSearch();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("status", new int[]{10,20,30,40,50,60});
		search.setEntity(map);
		List<BorrowTenderVO> tenders = borrowTenderService.selectFeelList(search);
		mv.addObject("tenders", tenders);//投资列表
		BigDecimal tenderAccounts = new BigDecimal("0");
		for(BorrowTenderVO v : tenders){
			tenderAccounts = tenderAccounts.add(new BigDecimal(v.getMoney()));
		}
		mv.addObject("tenderAccounts", tenderAccounts);//已投金额
		List<CollectionTenderUserVO> collections = borrowCollectionService.selectFeelList(search);
		mv.addObject("collections", collections);//待收列表
		//充值记录
		List<AccountFeelLog> accountFeelLogs = accountFeelLogService.selectByUserIdType(new AccountFeelLog(UserSession.getLoginUserId(),"recharge")); //历史充值记录
		if(!accountFeelLogs.isEmpty()){
			List<Feel> feels = new ArrayList<Feel>();
			String code = "";
			for(AccountFeelLog log : accountFeelLogs){
				code = log.getRemark().substring(log.getRemark().length()-16,log.getRemark().length());
				feels.add(feelService.selectByCode(code));
			}
			mv.addObject("feels", feels);
		}
		return mv;
	}

	/**
	 * 体验金充值for php
	 * 
	 * @return
	 * @author liuhuan
	 */
	@RequestMapping(value = "/rechargeFeel")
	@ResponseBody
	public Json rechargeFeel(HttpServletRequest request, HttpSession session) {
		Json j = new Json();
		String feelCode = request.getParameter("feelCode");
		String userId = request.getParameter("userId");
		User user = userService.selectByPrimaryKey(Integer.parseInt(userId));
		String msg = "";
		if (feelCode == null || "".equals(feelCode)) {
			msg = "请输入体验码";
		} else {
			msg = accountFeelService.updateRechargeFeel(user, feelCode, request.getRemoteAddr());
			if (msg.equals("充值成功.")) {
				j.setSuccess(true);
			}
			j.setObj(accountFeelService.selectByUserId(user.getUserId()));
		}
		j.setMsg(msg);
		System.out.println(msg);
		return j;
	}

	/**
	 * 体验金充值for java
	 * 
	 * @return
	 * @author liuhuan
	 */
	@NeedSession
	@RequestMapping(value = "/rechargeFeelWeb")
	@ResponseBody
	public Json rechargeFeelWeb(HttpServletRequest request, HttpSession session) {
		Json j = new Json();
		String feelCode = request.getParameter("feelCode");
		User user = userService.selectByPrimaryKey(UserSession.getLoginUserId());
		String msg = "";
		if (feelCode == null || "".equals(feelCode)) {
			msg = "请输入体验码";
		} else {
			msg = accountFeelService.updateRechargeFeel(user, feelCode, request.getRemoteAddr());
			if (msg.equals("充值成功.")) {
				j.setSuccess(true);
				AccountFeel accountFeel = accountFeelService.selectByUserId(user.getUserId());
				j.setObj(accountFeel);
			}
		}
		j.setMsg(msg);
		System.out.println(msg);
		return j;
	}

	/**
	 * @Description:查询可派发的体验券(暂时没用)
	 * @param request
	 * @param session
	 * @return
	 * @author liuhuan
	 */
	@RequestMapping("selectUseFeel")
	public ModelAndView selectUseFeel(HttpServletRequest request, HttpSession session) {
		ModelAndView mv = new ModelAndView("insertFeel");
		Feel feelvo = new Feel();
		feelvo.setStatus(1);
		feelvo.setNo("20");
		List<Feel> feels = feelService.selectByNoStatus(feelvo);
		if (feels.size() != 0) {
			mv.addObject("feels", feels.size() > 20 ? feels.subList(0, 20) : feels.subList(0, feels.size()));
		}
		return mv;
	}

	/**
	 * @Description:批量绑定userId for php(移除)
	 * @param request
	 * @param type :1为网站用户；2为虚拟组用户
	 * @param session
	 * @return
	 * @author liuhuan
	 */
	@RequestMapping(value="/batchInsertFeel")
	@ResponseBody
	public Json batchInsertFeel(@RequestParam("username") String username, @RequestParam("startNo") String startNo,
				@RequestParam("endNo") String endNo, @RequestParam("type") int type) {
		Json j = new Json();
		String result = "";
		if (startNo == null || endNo == null) {
			result = "Id不能为空";
		} else if (username == null | "".equals(username)) {
			result = "姓名不能为空.";
		} else {
			result = feelService.updateBatchUserId(Integer.parseInt(startNo), Integer.parseInt(endNo), username, type);
			if (result.equals("绑定成功.")) {
				j.setSuccess(true);
			}
		}
		j.setMsg(result);
		return j;
	}

	/**
	 * @Description:按数量 生成体验券 for php
	 * @author liuhuan
	 */
	@NeedSession
	@RequestMapping("insertFeel")
	public Json insertFeel(HttpServletRequest request, HttpSession session) {
		Json j = new Json();
		String result = "";
		BigDecimal money = new BigDecimal(request.getParameter("money"));
		String size = request.getParameter("size");
		String type = request.getParameter("type");// 81 or 61
		if(!type.equals("61")||!type.equals("81")){
			j.setMsg("类型错误");
			return j;
		}
		result = accountFeelService.updateSendCodeForOnline(type, money, Integer.parseInt(size));
		j.setSuccess(true);
		j.setMsg(result);
		return j;
	}

	/**
	 * 绑定列表  for php
	 * @author liuhuan
	 */
	@RequestMapping("/selectFeels")
	@ResponseBody
	public PageResult<FeelVO> selectFeels(FeelVO feelvo, PageSearch pageSearch) throws Exception {
		PageResult<FeelVO> feels = this.selectFeels(pageSearch, feelvo);
		return feels;
	}
	public PageResult<FeelVO> selectFeels(PageSearch pageSearch, FeelVO feelvo) {
		pageSearch.setEntity(feelvo);
		PageResult<FeelVO> result = new PageResult<FeelVO>();
		List<FeelVO> feelvos = null;
		if(feelvo.getType() == null){
			feelvos = accountFeelService.selectFeelsListPage(pageSearch);
		} else if(feelvo.getType() == 1){
			feelvos = accountFeelService.selectFeelsListPage(pageSearch);
		} else if(feelvo.getType() ==2){
			feelvos = feelService.selectFeelsListPage(pageSearch);
		} else {
			feelvos = accountFeelService.selectFeelsListPage(pageSearch);
		}
		result.setRows(feelvos);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}

	//首页2条体验标
	@RequestMapping("/indexForFeel")
	@ResponseBody
	public List<Borrow> indexForFeel(){
		List<Borrow> borrows = borrowService.selectFeelBorrowForIndex();
		return borrows;
	}
}
