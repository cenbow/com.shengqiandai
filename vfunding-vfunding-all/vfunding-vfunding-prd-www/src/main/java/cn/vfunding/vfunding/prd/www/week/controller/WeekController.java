package cn.vfunding.vfunding.prd.www.week.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.week.model.Week;
import cn.vfunding.vfunding.biz.week.model.WeekBorrow;
import cn.vfunding.vfunding.biz.week.service.IWeekBorrowService;
import cn.vfunding.vfunding.biz.week.service.IWeekRepaymentService;
import cn.vfunding.vfunding.biz.week.service.IWeekService;
import cn.vfunding.vfunding.biz.week.service.IWeekTenderService;

@Controller
@RequestMapping(value = "/week")
public class WeekController extends BaseController {

	@Autowired
	private IWeekService weekService;

	@Autowired
	private IWeekBorrowService weekBorrowService;

	@Autowired
	private IWeekTenderService weekTenderService;

	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private IWeekRepaymentService weekRepaymentService;
	
	/**
	 * @Description:标的详情页（体验标&普通标）
	 * @param id
	 *            标id
	 * @return
	 * @author liuhuan
	 */
	@RequestMapping("/weekDetail/{id}")
	public ModelAndView weekDetail(HttpServletRequest request,
			HttpSession session, @PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView("week/weekDetail");
		// 周盈宝 信息查询
		Week week = this.weekService.selectByPrimaryKey(id);
		if(EmptyUtil.isEmpty(week) || week.getStatus()<3){
			throw new BusinessException("8005012", "此计划还未发布");
		}		
		mv.addObject("week", week);
		// 用户账户信息查询
		Account account = accountService.selectByUserId(UserSession
				.getLoginUserId());
		mv.addObject("account", account);
		// 周盈宝 对应标的查询
		List<WeekBorrow> weekBorrowList = this.weekBorrowService
				.selectWeekBorrowByWeekId(id);
		mv.addObject("weekBorrowList", weekBorrowList);
		return mv;
	}
	
	@RequestMapping(value="/repayWeekRepayment", method = RequestMethod.POST)
	@ResponseBody
	public Json repayWeekRepayment(HttpServletRequest request,@RequestParam(value="repaymentId",required = true) Integer repaymentId){
		if(EmptyUtil.isNotEmpty(repaymentId) && repaymentId>0 ){
			return this.weekRepaymentService.updateRepayWeek(repaymentId,UserSession.getUserSession(),request.getRemoteAddr());
		}else{
			Json json = new Json();
			json.setMsg("参数异常");
			return json;
		}
	
	}

}
