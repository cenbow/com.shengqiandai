package cn.vfunding.vfunding.prd.www.friend.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.interceptors.NeedSession;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.EncryptionUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.common.vo.EarnedAndWaitOfReturnFeeVO;
import cn.vfunding.vfunding.biz.returns.model.ReturnFeeData;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserInfoService;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.biz.userMobile.service.IUserMobileService;

@Controller
@RequestMapping("/friend")
public class FriendController extends BaseController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IAccountService accountService;

	@Autowired
	private IUserInfoService userInfoService;
	
	@Autowired
	private IUserMobileService umService;

	/**
	 * 转向好友管理页面
	 * 
	 * @return 2014年5月14日 liuyijun
	 */
	@RequestMapping(value = "/myFriend", method = RequestMethod.GET)
	@NeedSession(returnUrl = "/friend/myFriend")
	public ModelAndView myFriend(HttpServletRequest request) {
		ModelAndView mv = ModelAndViewUtil.createModelAndViewAndAddAccountToView(accountService, "friend/friend");
		String base = ModelAndViewUtil.createBaseUrl(request) + "/regForInvite/";
		StringBuilder sb = new StringBuilder();
		sb.append("introducer=" + UserSession.getUserSession().getUserName());
		String url = base + EncryptionUtil.encrypt(sb.toString());
		mv.addObject("inviteUrl", url);
		BigDecimal fee = this.umService.selectUserSumInterestOfSonByUserId( UserSession.getUserSession().getUserId());
		if (fee == null) {
			fee = new BigDecimal("0");
		}
		mv.addObject("fee", fee);
		return mv;
		
	
	}

	/**
	 * 好友列表数据
	 * 
	 * @param pageSearch
	 * @param userName
	 * @return 2014年5月17日 liuyijun
	 */
	@RequestMapping(value = "/friendList", method = RequestMethod.GET)
	@ResponseBody
	public PageResult<User> friendList(PageSearch pageSearch, @RequestParam(value = "userName", required = false) String userName) {
		PageResult<User> result = new PageResult<User>();
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
			User search = new User();
			search.setInviteUserid(UserSession.getLoginUserId().toString());

			if (EmptyUtil.isNotEmpty(userName)) {
				search.setUsername(userName);
			}
			pageSearch.setEntity(search);
			List<User> pageList = this.userService.selectFriendByUserIdListPage(pageSearch);
			result.setRows(pageList);
			result.setTotal(pageSearch.getTotalResult());
		}
		return result;
	}

	@NeedSession(returnUrl = "/friend/returnDetail")
	@RequestMapping(value = "/returnDetaiAjax")
	@ResponseBody
	public PageResult<ReturnFeeData> returnFeeList(PageSearch pageSearch, Integer status) {
		PageResult<ReturnFeeData> result = new PageResult<ReturnFeeData>();
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
			ReturnFeeData vo = new ReturnFeeData();
			vo.setUserId(UserSession.getLoginUserId());
			vo.setStatus(status);
			pageSearch.setEntity(vo);
			List<ReturnFeeData> pageList = this.userInfoService.selectReturnFeeDatailListPage(pageSearch);
			result.setRows(pageList);
			result.setTotal(pageSearch.getTotalResult());
		}
		return result;
	}
	
	/**
	 * 转向明细页面
	 * @param request
	 * @return
	 */
	@NeedSession(returnUrl = "/friend/returnDetail")
	@RequestMapping(value = "/returnDetail")
	@ResponseBody
	public ModelAndView returnDetai(HttpServletRequest request) {
		ModelAndView mv = ModelAndViewUtil.createModelAndViewAndAddAccountToView(accountService, "friend/returnFeeDetail");
		EarnedAndWaitOfReturnFeeVO edw = userInfoService.selectEarnedAndWaitOfReturnFee(UserSession.getUserSession().getUserId());
		mv.addObject("edw", edw);
		return mv;
	}

	
	@NeedSession(returnUrl = "/friend/upfinancia")
	@RequestMapping(value = "/upfinancia")
	@ResponseBody
	public String upfinancia(HttpServletRequest request) {
		UserSession user =UserSession.getUserSession();
		String msg =this.userInfoService.insertUpFinancialPlanner(user);
		return msg;
	}
	/**
	 * 转向申请理财师页面
	 * @param request
	 * @return
	 */
	@NeedSession(returnUrl = "/friend/financiaDetail")
	@RequestMapping(value = "/financiaDetail")
	@ResponseBody
	public ModelAndView financiaDetai(HttpServletRequest request) {
		ModelAndView mv = ModelAndViewUtil.createModelAndViewAndAddAccountToView(accountService, "friend/financiaDetail");
		return mv;
	}
	
	
	
}
