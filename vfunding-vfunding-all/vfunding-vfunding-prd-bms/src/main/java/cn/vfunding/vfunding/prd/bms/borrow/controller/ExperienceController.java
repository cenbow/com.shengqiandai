package cn.vfunding.vfunding.prd.bms.borrow.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.interceptors.NeedLogger;
import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.model.AccountFeel;
import cn.vfunding.vfunding.biz.account.service.IAccountFeelService;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.BorrowRepayment;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowRepaymentService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;
import cn.vfunding.vfunding.biz.common.vo.FeelVO;
import cn.vfunding.vfunding.biz.common.vo.SearchVO;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IFeelService;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.prd.bms.utils.UserAuthFilter;

@Controller
@RequestMapping("/system/experience")
public class ExperienceController {
	@Autowired
	private IBorrowService borrowService;
	@Autowired
	private IBorrowRepaymentService borrowRepaymentService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IFeelService feelService;
	/**
	 * 跳转添加体验标页面
	 * @return
	 */
	@ParentSecurity("/system/experience/feelBorrowingPage")
	@RequestMapping(value="/addFeelBorrowPage")
	public ModelAndView addFeelBorrowPage(){
		ModelAndView mv = new ModelAndView("webpage/experience/addFeelBorrow");
		return mv;
    }
	/**
	 * 招标中的体验标
	 * @author liuhuan
	 */
	@ParentSecurity("/system/experience/addFeelBorrowPage")
	@RequestMapping(value="/feelBorrowingPage")
	public ModelAndView feelBorrowingPage(){
		ModelAndView mv = new ModelAndView("webpage/experience/feelBorrowing");
		boolean canEdit = UserAuthFilter.isPass("/system/experience/addFeelBorrow");
		if (canEdit) {
			mv.addObject("canEdit", canEdit);
		}
		return mv;
	}
	/**
	 * 招标中体验标
	 * @author liuhuan
	 */
	@ParentSecurity("/system/experience/feelBorrowingPage")
	@RequestMapping("/feelBorrowing")
	@ResponseBody
	public PageResult<BorrowRepayment> feelBorrowing(PageSearch pageSearch, SearchVO search) {
		PageResult<BorrowRepayment> results = new PageResult<BorrowRepayment>();
		pageSearch.setEntity(search);
		List<BorrowRepayment> borrowRepayments = borrowRepaymentService.selectFeelBorrowingListPage(pageSearch);
		results.setRows(borrowRepayments);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}
	/**
	 * 添加体验标
	 * @author liuhuan
	 */
	@RequestMapping(value="/addFeelBorrow")
	@ResponseBody
	public Json addFeelBorrow(HttpServletRequest request, Borrow borrow,String username){
		Json j = new Json();
		String msg = "";
		User user = userService.selectByUserName(username.trim());
		if(user == null){
			msg = "该借款人不存在";
		} else if (borrow.getAccount() == null || "".equals(borrow.getAccount())) {
			msg = "借款金额不能为空.";
		} else if (borrow.getApr() == null || "".equals(borrow.getApr())) {
			msg = "借款利率不能为空.";
		} else if (borrow.getLowestAccount() == null) {
			msg = "最低投标额错误.";
		} else if (borrow.getName() == null || "".equals(borrow.getName())) {
			msg = "借款标题不能为空.";
		} else if (borrow.getContent() == null || "".equals(borrow.getContent())) {
			msg = "借款内容不能为空.";
		} else if (borrow.getTimeLimitDay() == null) {
			msg = "天标天数不能为空.";
		} else {
			msg = borrowService.insertFeelBorrow(borrow, user.getUserId(), request.getRemoteAddr());
			j.setSuccess(true);
		}
		j.setMsg(msg);
		return j;
	}
	/**
	 * 还款页面
	 * @author liuhuan
	 */
	@RequestMapping(value="/toFeelPage")
	public ModelAndView toFeelPage(){
		ModelAndView mv = new ModelAndView("webpage/experience/waitRepayFeelBorrowList");
		AccountFeel accountFeel = accountFeelService.selectByUserId(558);// 此处写死
		mv.addObject("accountFeel", accountFeel);
		boolean canEdit = UserAuthFilter.isPass("/system/experience/paymentFeelBorrow");
		if (canEdit) {
			mv.addObject("canEdit", canEdit);
		}
	    return mv;
    }
	/**
	 * 体验标待还列表
	 * @return
	 * @author liuhuan
	 */
	@ParentSecurity("/system/experience/toFeelPage")
	@RequestMapping("/waitRepayFeelBorrowListPage")
	@ResponseBody
	public PageResult<BorrowRepayment> waitRepayFeelBorrowListPage(PageSearch pageSearch, SearchVO search) {
		PageResult<BorrowRepayment> results = new PageResult<BorrowRepayment>();
		pageSearch.setEntity(search);
		List<BorrowRepayment> borrowRepayments = borrowRepaymentService.selectFeelByUserIdStatusListPage(pageSearch);
		results.setRows(borrowRepayments);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}
	/**
	 * 体验标还款
	 * @author liuhuan
	 */
	@NeedLogger(desc = "体验标还款")
	@RequestMapping("/paymentFeelBorrow")
	@ResponseBody
	public Json paymentFeelBorrow(HttpServletRequest request) {
		Json j = new Json();
		String result = "";
		String repaymentId = request.getParameter("repaymentId");
		BorrowRepayment repayment = borrowRepaymentService.selectByPrimaryKey(Integer.parseInt(repaymentId));
		Borrow borrow = borrowService.selectById(repayment.getBorrowId());
		if (repaymentId == null || "".equals(repaymentId)) {
			result = "标数据错误，还款失败.";
		} else {
			result = borrowService
					.updateRepayFeelBorrow(repaymentId, borrow.getUserId(), request.getRemoteAddr());
		}
		j.setMsg(result);
		return j;
	}
	/**
	 * 体验卡号绑定 页面
	 * @return
	 */
	@ParentSecurity("/system/experience/toFeelBindingListPage")
	@RequestMapping(value="/toFeelBindingPage")
	public ModelAndView toFeelBindingPage(){
		ModelAndView mv = new ModelAndView("webpage/experience/bindingFeel");
		return mv;
    }
	/**
	 * @Description:批量绑定
	 * @param type :1为网站用户；2为虚拟组用户
	 * @author liuhuan
	 */
	@NeedLogger(desc = "批量绑定体验卡")
	@RequestMapping(value="/batchInsertFeel")
	@ResponseBody
	public Json batchInsertFeel(@RequestParam("username") String username,
			@RequestParam("startNo") String startNo,
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
	 * 转向体验卡绑定列表
	 * @return
	 */
	@RequestMapping(value="/toFeelBindingListPage")
	public ModelAndView toFeelBindingListPage(){
	   ModelAndView mv = new ModelAndView("webpage/experience/bindingFeelBorrowList");
	   boolean canEdit = UserAuthFilter.isPass("/system/experience/batchInsertFeel");
		if (canEdit) {
			mv.addObject("canEdit", canEdit);
		}
	   return mv;
    }
	
	@Autowired
	private IAccountFeelService accountFeelService;
	
	/**
	 * 体验卡绑定列表
	 * @author liuhuan
	 * @throws Exception 
	 */
	@ParentSecurity("/system/experience/toFeelBindingListPage")
	@RequestMapping("/bindingFeelList")
	@ResponseBody
	public PageResult<FeelVO> bindingFeelList(PageSearch pageSearch, FeelVO feelvo) throws Exception {
		//时间转换成时间戳
		if (EmptyUtil.isNotEmpty(feelvo.getStartTime())) {
			feelvo.setStartTime(DateUtil.getLongTimeByStringValue(feelvo.getStartTime() + " 00:00:00").toString());
		}
		if (EmptyUtil.isNotEmpty(feelvo.getEndTime())) {
			feelvo.setEndTime(DateUtil.getLongTimeByStringValue(feelvo.getEndTime()+ " 23:59:59").toString());
		}
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
}
