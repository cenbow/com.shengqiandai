package cn.vfunding.vfunding.plat.activity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.vfunding.biz.common.vo.FinalVerifyVO;
import cn.vfunding.vfunding.biz.common.vo.RegisterVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.useraction_activity.service.IUserActionService;
@Controller
@RequestMapping("/activityUserAction")
public class UserActionActivityController extends BaseController {

	@Autowired
	private IUserActionService userActionService;
	
	/**
	 * 用户投资后相关活动
	 * @param vo
	 * @return
	 * 2015年1月13日
	 * liuyijun
	 * 
	 */
	@RequestMapping(value="/userTender",method=RequestMethod.POST, consumes = { "application/json" })
	@ResponseBody
	public String doUserTenderBizAction(@RequestBody UserTenderActionResultVO vo){
		return this.userActionService.doUserTenderBiz(vo);
	}
	
	/**
	 * 用户注册后相关活动处理
	 * @param vo
	 * @return
	 * 2015年1月19日
	 * liuyijun
	 */
	@RequestMapping(value="/userRegist",method=RequestMethod.POST, consumes = { "application/json" })
	@ResponseBody
	public String doUserRegistAction(@RequestBody RegisterVO vo){
		return this.userActionService.doRegisterBiz(vo);
	}
	
	/**
	 * 实名认证活动
	 * @param user
	 * @return
	 * 2015年1月19日
	 * liuyijun
	 */
	@RequestMapping(value="/realName",method=RequestMethod.POST, consumes = { "application/json" })
	@ResponseBody
	public String doRealNameAction(@RequestBody User record){
		return this.userActionService.doRealNameVerifyBiz(record);
	}
	
	/**
	 * 邮箱验证活动
	 * @param user
	 * @return
	 * 2015年1月19日
	 * liuyijun
	 */
	@RequestMapping(value="/emailVerify",method=RequestMethod.POST, consumes = { "application/json" })
	@ResponseBody
	public String doEmailVerifyAction(@RequestBody User record){
		return this.userActionService.doEmailVerifyBiz(record);
	}
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	@ResponseBody
	public String doTest(){
		return "success";
	}
	
	/**
	 * 满标复审
	 * @param verifyVO
	 * @return
	 * @author louchen 2015-03-13
	 */
	@RequestMapping(value="/doborrowVerify",method=RequestMethod.POST, consumes = { "application/json" })
	@ResponseBody
	public String doUserRegistAction(@RequestBody FinalVerifyVO verifyVO){
		return this.userActionService.doBorrowVerify(verifyVO);
	}
	
}
