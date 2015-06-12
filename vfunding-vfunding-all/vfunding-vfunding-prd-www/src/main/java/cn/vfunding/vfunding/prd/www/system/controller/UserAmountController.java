package cn.vfunding.vfunding.prd.www.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.server.interceptors.NeedSession;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.common.vo.AmountVO;
import cn.vfunding.vfunding.biz.common.vo.ApplyAmountVO;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.user.service.IUserAmountLogService;
import cn.vfunding.vfunding.biz.user.service.IUserAmountapplyService;
/**
 * 申请额度
 */
@Controller
@RequestMapping(value={"/userAmount"})
public class UserAmountController extends BaseController {

	@Autowired
	private IUserAmountapplyService userAmountapplyService;
	@Autowired
	private IUserAmountLogService userAmountLogService;
	@Autowired
	private IAccountService accountService;
	
	/**
	 * @Description:转向额度申请页面
	 * @return
	 * @author liuhuan
	 */
	@NeedSession(returnUrl = "/userAmount/toApplyOnlinePage")
	@RequestMapping(value = "/toApplyOnlinePage")
	public ModelAndView toApplyOnlinePage(){
		ModelAndView mv = new ModelAndView("borrow/applyOnline");
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		
		if(UserSession.getUserSession().getRealStatus().equals("0")){
			return new ModelAndView("redirect:/user/realName");
		}
		
		mv.addObject("user", UserSession.getUserSession());
		return mv;
	}
	
	/**
	 * @Description:额度申请
	 * @return
	 * @author liuhuan
	 */
	@NeedSession(returnUrl = "/userAmount/toApplyOnlinePage")
	@RequestMapping(value = "applyOnline")
	@ResponseBody
	public Json applyOnline(ApplyAmountVO applyAmountvo,HttpServletRequest request){
		Json j = new Json();
		if(EmptyUtil.isEmpty(applyAmountvo.getType())){
			j.setMsg("申请类型不能为空");
		} else if(EmptyUtil.isEmpty(applyAmountvo.getAccount())){
			j.setMsg("申请金额不能为空");
		} else if(EmptyUtil.isEmpty(applyAmountvo.getContent())){
			j.setMsg("借款说明不能为空");
		} else if(EmptyUtil.isEmpty(applyAmountvo.getLimit())){
			j.setMsg("借款期限不能为空");
		} else if(EmptyUtil.isEmpty(applyAmountvo.getMortgagetypeId())){
			j.setMsg("抵押品种不能为空");
		} else {
			Integer userId = UserSession.getLoginUserId();
			applyAmountvo.setUserId(userId);
			applyAmountvo.setIp(request.getRemoteAddr());
			applyAmountvo.setUserId(userId);
			
			j = userAmountLogService.applyOnline(applyAmountvo);
			j.setMsg(j.getMsg());
		}
		return j;
	}
	/**
	 * 后台审核额度
	 * @return
	 * @author liuhuan
	 */
	@NeedSession
	@RequestMapping(value="applyAmount")
	@ResponseBody
	public Json applyAmount(AmountVO amountVO){
		Json j = new Json();
		int i = userAmountLogService.updateApplyAmount(amountVO);
		if(i == 1){
			j.setSuccess(true);
			j.setMsg("审核成功");
		} else if(i == 0){
			j.setMsg("审核失败");
		} else if(i == -1){
			j.setMsg("请勿重复审核");
		}
		return j;
	}
	
}
