package cn.vfunding.vfunding.prd.bms.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.interceptors.NeedLogger;
import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.common.vo.SearchVO;
import cn.vfunding.vfunding.biz.system.model.LockRecord;
import cn.vfunding.vfunding.biz.system.service.ILockRecordService;
import cn.vfunding.vfunding.biz.user.model.Upfinancial;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUpfinancialService;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.prd.bms.utils.UserAuthFilter;

@Controller
@RequestMapping("/system/user")
public class UserController {

	@Autowired
	private ILockRecordService lockRecordService;
	
	@Autowired
	private IUserService  userService;
	
	@Autowired
	private IUpfinancialService upfinancialService;
	
	
	@RequestMapping("/getUser/{id}")
	@ResponseBody
	public User getUser(@PathVariable("id") Integer id){
		User user=this.userService.selectByPrimaryKey(id);
		return user;
	}
	
	/**
	 * 转向解锁列表页面
	 * @author liuhuan
	 */
	@RequestMapping(value = "/lockUserListPage")
	public ModelAndView lockUserListPage(){
		ModelAndView mv = new ModelAndView("webpage/user/lockUserList");
		boolean canUnlock = UserAuthFilter.isPass("/system/user/lockUser");
		if (canUnlock) {
			mv.addObject("canUnlock", canUnlock);
		}
		return mv;
	}
	/**
	 * 解锁列表
	 * @author liuhuan
	 */
	@ParentSecurity("/system/user/lockUserListPage")
	@RequestMapping("/lockUserList")
	@ResponseBody
	public PageResult<LockRecord> lockUserList(PageSearch pageSearch,String username,String phone) {
		PageResult<LockRecord> results = new PageResult<LockRecord>();
		Map<String, Object> map = new HashMap<String, Object>();
		if(EmptyUtil.isNotEmpty(username)){
			map.put("username", username);
		}
		if(EmptyUtil.isNotEmpty(phone)){
			map.put("phone", phone);
		}
		pageSearch.setEntity(map);
		List<LockRecord> cashList = lockRecordService.selectByListPage(pageSearch);
		results.setRows(cashList);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}
	/**
	 * 解锁
	 * @author liuhuan
	 */
	@NeedLogger(desc="用户解锁操作")
	@RequestMapping(value = "/lockUser")
	@ResponseBody
	public Json lockUser(Integer userId){
		Json j = new Json();
		int status = 0;//解锁状态：0正常；1上锁
		if(EmptyUtil.isNotEmpty(userId)){
			int i = lockRecordService.lockUserByUserId(userId,status);
			if(i>0){
				j.setSuccess(true);
			}
		}
		return j;
	}
	
	/**
	 * 转向理财师列表页面
	 * @author liuhuan
	 */
	@RequestMapping(value = "/upfinancialListPage")
	public ModelAndView upfinancialListPage(){
		ModelAndView mv = new ModelAndView("webpage/user/upfinancialList");
		boolean canApply = UserAuthFilter.isPass("/system/user/applyFinancial");
		if (canApply) {
			mv.addObject("canApply", canApply);
		}
		return mv;
	}
	/**
	 * 理财师列表 
	 * @return
	 * @author liuhuan
	 */
	@ParentSecurity("/system/user/upfinancialListPage")
	@RequestMapping("/upfinancialList")
	@ResponseBody
	public PageResult<Upfinancial> upfinancialList(PageSearch pageSearch, SearchVO search) {
		PageResult<Upfinancial> results = new PageResult<Upfinancial>();
		pageSearch.setEntity(search);
		List<Upfinancial> upfinancial = upfinancialService.selectByListPage(pageSearch);
		results.setRows(upfinancial);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}
	
	/**
	 * 审核 页面
	 * @author liuhuan
	 */
	@RequestMapping(value = "/applyFinancialPage")
	public ModelAndView applyFinancialPage(Integer id){
		ModelAndView mv = new ModelAndView("webpage/user/applyFinancialPage");
		mv.addObject("id", id);
		return mv;
	}
	
	/**
	 * 审核
	 * @author liuhuan
	 */
	@NeedLogger(desc="理财师审核")
	@RequestMapping(value = "/applyFinancial")
	@ResponseBody
	public Json applyFinancial(Upfinancial financial){
		Json j = new Json();
		Upfinancial f = upfinancialService.selectByPrimaryKey(financial.getId());
		if(f.getStatus() != 0 ){
			j.setMsg("请勿重复审核.");
		} else{
			int i = upfinancialService.updateApplyFinancial(financial.getRemark(),financial.getStatus(),f);
			if(i>0){
				j.setSuccess(true);
			} else {
				j.setMsg("操作失败，系统异常");
			}
		}
		return j;
	}
}
