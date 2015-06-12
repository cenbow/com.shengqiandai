package cn.vfunding.vfunding.prd.bms.login.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.server.EmployeeSession;
import cn.vfunding.common.framework.server.interceptors.UnSecurity;
import cn.vfunding.common.framework.server.interceptors.UnSession;
import cn.vfunding.common.framework.utils.http.HttpSessionUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.vfunding.biz.bms_employee.model.Employee;
import cn.vfunding.vfunding.biz.bms_employee.service.IEmployeeService;
import cn.vfunding.vfunding.biz.bms_system.model.Role;
import cn.vfunding.vfunding.biz.bms_system.model.SysResource;
import cn.vfunding.vfunding.biz.bms_system.model.SysResourceGroup;

/**
 * 登陆相关控制器
 * 
 * @author liuyijun
 * 
 */
@Controller
@RequestMapping("")
public class LoginController extends BaseController {

	/**
	 * 工作流和系统用户转换服务
	 */
	@Autowired
	private IEmployeeService employeeService;

	/**
	 * 跳转到系统首页
	 * 
	 * @param session
	 * @return
	 */
	@UnSession
	@UnSecurity
	@RequestMapping(value = {"","/index"}, method = RequestMethod.GET)
	public ModelAndView index(HttpSession session) {
		ModelAndView mv = null;
		EmployeeSession eSession = HttpSessionUtil.getAuthInfo(session);
		if (eSession == null) {
			mv = new ModelAndView("loginIn");
		} else {
			mv = new ModelAndView("index");
		}
		return mv;
	}

	/**
	 * 用户登陆
	 * 
	 * @param loginName
	 * @param password
	 * @param postId
	 * @param session
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@UnSession
	@UnSecurity
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Json login(@RequestParam("loginName") String loginName,
			@RequestParam("password") String password, HttpSession session)
			throws IllegalAccessException, InvocationTargetException {
		Assert.hasText(loginName, "登陆名不可为空");
		Assert.hasText(password, "密码不可为空");
		Json j = new Json();
		EmployeeSession eSession = HttpSessionUtil.getAuthInfo(session);
		if (eSession == null) {
			boolean exist = this.employeeService.checkEmpLogin(loginName,
					password);
			Employee emp = null;
			if (exist) {
				boolean usable = this.employeeService.checkEmpState(loginName,
						password);
				if (usable) {
					emp = this.employeeService.login(loginName, password);
					if (emp != null) {
						doLogin(emp, session);
						j.setSuccess(true);
					} else {
						j.setMsg("当前用户没有任何资源");// 当前用户没有任何资源
					}
				} else {
					j.setMsg("当前用户的账号被禁用");// 当前用户的账号被禁用
				}
			} else {
				j.setMsg("用户名或密码错误");// 用户名或密码错误
			}
		}
		return j;
	}

	/**
	 * 重新登陆
	 * 
	 * @param loginName
	 * @param password
	 * @param postId
	 * @param session
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@UnSession
	@UnSecurity
	@RequestMapping(value = "/againLogin", method = RequestMethod.POST)
	public Json againLogin(@RequestParam("loginName") String loginName,
			@RequestParam("password") String password,
			@RequestParam("postId") Integer postId, HttpSession session)
			throws IllegalAccessException, InvocationTargetException {
		Assert.hasText(loginName, "登陆名不可为空");
		Assert.hasText(password, "密码不可为空");
		Json j = new Json();
		EmployeeSession eSession = HttpSessionUtil.getAuthInfo(session);
		if (eSession == null) {
			boolean exist = this.employeeService.checkEmpLogin(loginName,
					password);
			Employee emp = null;
			if (exist) {
				emp = this.employeeService.login(loginName, password);
				if (emp != null)
					doLogin(emp, session);
				else {
					// throw new BusinessException("10001");
				}
			}
		}
		return j;
	}

	/**
	 * 登陆成功后用户信息封装保存在session中
	 * 
	 * @param emp
	 * @param session
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	private void doLogin(Employee emp, HttpSession session)
			throws IllegalAccessException, InvocationTargetException {

		EmployeeSession eSession = new EmployeeSession();
		eSession.setEmpId(emp.getEmpId());
		eSession.setEmpName(emp.getEmpName());
		eSession.setPassword(emp.getEmpPassword());
		eSession.setLoginName(emp.getEmpLoginName());
		eSession.getAttributes().put("roles", emp.getRoles());
		eSession.getAttributes().put("resourceGroups", emp.getResourceGroups());
		eSession.getAttributes().put("resources", emp.getResources());

		List<String> resourceUrls = new ArrayList<String>();// 资源url集合
		for (SysResource sysResource : emp.getResources()) {
			if (StringUtils.hasText(sysResource.getUrl()))
				resourceUrls.add(sysResource.getUrl());
		}
		eSession.getAttributes().put("resourceUrls", resourceUrls);

		List<String> roleNames = new ArrayList<String>();// 角色名称集合
		for (Role role : emp.getRoles()) {
			if (!roleNames.contains(role.getRoleName()))
				roleNames.add(role.getRoleName());
		}
		eSession.getAttributes().put("roleNames", roleNames);

		List<String> groupIds = new ArrayList<String>();// 菜单组Id集合
		for (SysResourceGroup group : emp.getResourceGroups()) {
			if (!groupIds.contains(group.getId().toString()))
				groupIds.add(group.getId().toString());
		}
		eSession.setGroupIds(groupIds);

		List<String> activitiGroupIds = new ArrayList<String>();

		eSession.setAcitivitiGroupIds(activitiGroupIds);

		/*
		 * Employee e = new Employee(); e.setEmpId(emp.getEmpId());
		 */
		HttpSessionUtil.login(session, eSession);
	}

	/**
	 * 退出登陆
	 * 
	 * @param session
	 * @return
	 */
	@UnSession
	@UnSecurity
	@RequestMapping(value = "/loginOut", method = RequestMethod.GET)
	@ResponseBody
	public Json loginOut(HttpSession session) {
		Json j = new Json();
		HttpSessionUtil.logOut(session);
		j.setSuccess(true);
		return j;
	}

}
