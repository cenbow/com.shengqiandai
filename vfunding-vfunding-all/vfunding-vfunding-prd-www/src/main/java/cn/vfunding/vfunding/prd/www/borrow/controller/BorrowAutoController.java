package cn.vfunding.vfunding.prd.www.borrow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.server.interceptors.NeedSession;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.service.IAccountRechargeService;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.borrow.model.BorrowAuto;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.mq.service.IBorrowAutoService;
import cn.vfunding.vfunding.biz.session.utils.UserSession;

@Controller
@RequestMapping("/borrowAuto")
public class BorrowAutoController {

	@Autowired
	private IAccountService accountService;
	@Autowired
	private IAccountRechargeService accountRechargeService;
	@Autowired
	private IBorrowAutoService borrowAutoService;
	
	/***
	 * 转向自动投标页面
	 * @author liuhuan
	 */
	@NeedSession(returnUrl = "/borrowAuto/borrowAuto")
	@RequestMapping(value = "/borrowAuto")
	public ModelAndView borrowAuto(){
		ModelAndView mv = new ModelAndView("user/investAuto");
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		
		BorrowAuto auto = borrowAutoService.selectBorrowAutoByUserId(UserSession.getLoginUserId());
		
		mv.addObject("auto", auto);
		return mv;
	}
	/**
	 * 设置自动投标
	 * @author liuhuan
	 */
	@NeedSession(returnUrl = "/borrowAuto/borrowAuto")
	@RequestMapping(value = "changeBorrowAuto")
	@ResponseBody
	public Json changeBorrowAuto(BorrowAuto auto){
		Json j = new Json();
		if(EmptyUtil.isEmpty(auto.getStatus())){
			j.setMsg("投标状态不能为空");
		} else if(EmptyUtil.isEmpty(auto.getTenderAccount())){
			j.setMsg("投标金额不能为空");
		} else if(auto.getTenderAccount() < 50){
			j.setMsg("投标金额不能小于50元");
		} else if(EmptyUtil.isEmpty(auto.getTimelimitMonthFirst())&&EmptyUtil.isEmpty(auto.getTimelimitMonthLast())){
			j.setMsg("借款期限不能为空");
		} else if(auto.getTimelimitMonthLast() < auto.getTimelimitMonthFirst()){
			j.setMsg("借款期限范围不正确");
		} else if(EmptyUtil.isEmpty(auto.getAprFirst())&&EmptyUtil.isEmpty(auto.getAprLast())){
			j.setMsg("年利率不能为空");
		} else if(auto.getAprLast() < auto.getAprFirst()){
			j.setMsg("年利率范围不正确");
		} else if(EmptyUtil.isEmpty(auto.getBorrowStyle())){
			j.setMsg("还款方式不能为空");
		} else {
			auto.setUserId(UserSession.getLoginUserId());
			int i = borrowAutoService.updateBorrowAuto(auto);
			if(i==1||i==0){
				j.setMsg((i==1?"开启自动投标":"关闭自动投标")+"设置成功");
				j.setSuccess(true);
			} else {
				j.setMsg("设置失败，请联系在线客服");
			}
		}
		return j;
	}
	
	/**
	 * 用户是否充值过
	 * @return
	 * @author liuhuan
	 */
	@NeedSession(returnUrl = "/borrowAuto/borrowAuto")
	@RequestMapping(value = "userIsRecharge")
	@ResponseBody
	public Json userIsRecharge(){
		Json j = new Json();
		int i = accountRechargeService.selectUserIsRecharge(UserSession.getLoginUserId());
		if(i > 0){
			j.setSuccess(true);//有充值记录
		} else {
			j.setSuccess(false);//没充过值
		}
		return j;
	}
}
