package cn.p2p.p2p.prd.bms.brrow.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.p2p.p2p.biz.borrow.service.IDayBorrowService;
import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.EmployeeSession;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.BorrowRepayment;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowRepaymentService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;
import cn.vfunding.vfunding.biz.common.vo.SearchVO;
import cn.vfunding.vfunding.biz.common.vo.VerifyBorrowVO;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.sina.service.IQuerySinaService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserService;

/**
 * 8戒 标的管理
 * @author liuhuan
 *
 */
@Controller
@RequestMapping(value = { "/system/dayborrow"})
public class DayBorrowController  extends BaseController {
	
	@Autowired
	private IBorrowService borrowService;
	@Autowired
	private IDayBorrowService dayBorrowService;
	@Autowired
	private IUserService userService;

	/**
	 * 天标 添加、更新
	 * @return
	 */
	@RequestMapping(value = "/addDayBorrow")
	@ResponseBody
	public Json addBorrow(Borrow borrow,String username,HttpServletRequest request){
		Json j = new Json();
		User user = userService.selectByUserName(username);
		if(EmptyUtil.isEmpty(user)){
			j.setMsg("发标人不存在");
		} else {
			Json result = dayBorrowService.insertSelective(borrow, user.getUserId(), request.getRemoteAddr());
			if(result.getSuccess()){
				j.setSuccess(true);
			} else {
				j.setMsg("发标失败，系统异常");
			}
		}
		return j;
	}
	
	/**
	 * 天标表单页面
	 * @return
	 */
	@RequestMapping(value = "/addDayBorrowPage")
	public ModelAndView addDayBorrowPage() {
		ModelAndView mv = new ModelAndView("webpage/dayBorrow/addDayBorrowPage");
		return mv;
	}
	
	/**
	 * 天标列表页面
	 * @return
	 */
	@RequestMapping(value = "/dayBorrowPage")
	public ModelAndView dayBorrowPage() {
		ModelAndView mv = new ModelAndView("webpage/dayBorrow/dayBorrowPage");
		return mv;
	}
	
	/**
	 * 天标 分页数据
	 * @param pageSearch
	 * @param search
	 * @return
	 */
	@RequestMapping("/dayBorrowList")
	@ResponseBody
	public PageResult<VerifyBorrowVO> dayBorrowList(PageSearch pageSearch, SearchVO search) {
		PageResult<VerifyBorrowVO> results = new PageResult<VerifyBorrowVO>();
		pageSearch.setEntity(search);
		List<VerifyBorrowVO> failBorrows = dayBorrowService.dayBorrowListPage(pageSearch);
		results.setRows(failBorrows);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}
	
	/**
	 * 天标详情 TODO
	 * @return
	 */
	@RequestMapping("/dayBorrowDetail")
	public ModelAndView dayBorrowDetail(Integer id){
		ModelAndView mv = new ModelAndView("webpage/dayBorrow/dayBorrowDetail");
		
		mv.addObject("", "");
		return mv;
	}
	
	@RequestMapping(value = "/repayDayBorrow")
	@ResponseBody
	public Json repayDayBorrow(Integer repaymentId,HttpServletRequest request){
		Json j = new Json();
		String msg = "";
		if (repaymentId == null || "".equals(repaymentId)) {
			msg = "数据异常.";
		} else if(!canSinaBalanceRepaymentBorrow(repaymentId.toString())){
			msg = "还款失败，新浪余额不足,请联系微积金客服或技术。";
		} else {
			msg = borrowService.updateRepayDayBorrow(repaymentId.toString(), request.getRemoteAddr());
		}
		j.setMsg(msg);
		return j;
	}
	
	@Autowired
	private IBorrowRepaymentService borrowRepaymentService;
	@Autowired
	private IQuerySinaService querySinaService;
	private synchronized Boolean canSinaBalanceRepaymentBorrow(String repaymentId){
		Boolean result = false;
		Map<String, String> map = null;
		BorrowRepayment borrowRepayment = borrowRepaymentService.selectByPrimaryKey(Integer.parseInt(repaymentId));
		try {
			map = querySinaService.doQueryBalance(UserSession.getLoginUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (map != null && map.get("success").equals("success") && !map.get("balance").equals("null")) {
			BigDecimal sinaBalance = new BigDecimal(map.get("balance"));
			 if (sinaBalance.compareTo(
						new BigDecimal(borrowRepayment.getRepaymentAccount())) >= 0){
				 result = true;
			 } 
			
		}
		return result;
	}
}
