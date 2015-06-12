package cn.vfunding.vfunding.prd.bms.system.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.filter.session.UserSessionFilter;
import cn.vfunding.common.framework.server.EmployeeSession;
import cn.vfunding.common.framework.server.interceptors.NeedLogger;
import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.server.interceptors.UnSecurity;
import cn.vfunding.common.framework.server.interceptors.UnSession;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.http.HttpSessionUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.bms_employee.model.EmpRoleKey;
import cn.vfunding.vfunding.biz.bms_employee.model.Employee;
import cn.vfunding.vfunding.biz.bms_employee.service.IEmployeeService;
import cn.vfunding.vfunding.prd.bms.utils.UserAuthFilter;

@Controller
@RequestMapping("/emp")
public class EmployeeController extends BaseController {

	@Autowired
	private IEmployeeService empService;
	
	@UnSecurity
	@UnSession
	@RequestMapping("/checkEmpLoginName")
	@ResponseBody
	public boolean checkEmpLoginName(@RequestParam("loginName") String loginName){
		Employee emp=this.empService.selectEmployeeBaseInfoByLoginName(loginName);
		if(EmptyUtil.isNotEmpty(emp)){
			return false;
		}
		return true;
	}
	
	/**
	 * 转向修改密码页面
	 * 
	 * @return
	 */
	@UnSecurity
	@RequestMapping(value = "/editCurrentUserPwdPage")
	public ModelAndView editCurrentUserPwdPage() {
		ModelAndView mv = new ModelAndView("webpage/user/userEditPwd");
		return mv;
	}

	/**
	 * 转向列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toListPage", method = RequestMethod.GET)
	public ModelAndView toListPage() {
		ModelAndView mv = new ModelAndView("webpage/employee/empInfo");
		boolean addOrEdit = UserAuthFilter.isPass("/emp/addOrEdit");
		mv.addObject("addOrEdit", addOrEdit);
		return mv;
	}

	/**
	 * 员工信息列表数据支持分页条件查询
	 * 
	 * @param pageSearch
	 * @param post
	 * @return
	 */
	@ParentSecurity("/emp/toListPage")
	@RequestMapping(value = "/list")
	@ResponseBody
	public PageResult<Employee> getPostByPage(PageSearch pageSearch,
			Employee employee) {
		PageResult<Employee> result = new PageResult<Employee>();
		List<Employee> list = null;
		pageSearch.setEntity(employee);
		list = this.empService.selectEmpListPage(pageSearch);
		result.setRows(list);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@UnSecurity
	@RequestMapping("/editCurrentUserPwd")
	@ResponseBody
	public Json editCurrentUserPwd(@RequestParam("oldPwd") String oldPwd,
			@RequestParam("newPwd") String newPwd) {
		Json j = new Json();
		EmployeeSession eSession = EmployeeSession.getUserSession();
		if (!DigestUtils.md5Hex(oldPwd).equals(eSession.getPassword()))
			j.setMsg("旧密码错误");
		else {
			Employee e = new Employee();
			e.setEmpId(eSession.getEmpId());
			e.setEmpPassword(DigestUtils.md5Hex(newPwd));
			this.empService.updateByPrimaryKeySelective(e);
			HttpSession session = UserSessionFilter.getHttpSession();
			HttpSessionUtil.logOut(session);
			j.setSuccess(true);
		}
		return j;
	}

	/**
	 * 转向修改页面
	 * 
	 * @return
	 */
	@ParentSecurity("/emp/toListPage")
	@RequestMapping("/toAddOrEditPage")
	public ModelAndView toAddOrEditPage(
			@RequestParam(value = "empId", required = false) Integer empId) {
		ModelAndView mv = new ModelAndView("webpage/employee/empAddOrEdit");
		Employee o = new Employee();
		o.setEmpTime(new Date());
		if (empId != null) {
			o = empService.selectByPrimaryKey(empId);
			String roleIds = o.getRoleIds();

			if (roleIds != null && roleIds.length() > 0)
				mv.addObject("roleIds", roleIds);
		}

		mv.addObject("emp", o);
		return mv;
	}

	/**
	 * 添加或修改员工
	 * 
	 * @param role
	 * @return
	 */
	@NeedLogger(desc="添加或修改员工")
	@RequestMapping(value = "/addOrEdit", method = RequestMethod.POST)
	@ResponseBody
	public Json update(Employee employee) {
		Json j = new Json();
		try {
			if (employee.getEmpId() != null) {
				this.empService.updateEmpAndRoleByPrimaryKeySelective(employee);
			} else {
				employee.setEmpPassword(DigestUtils.md5Hex("123456"));
				this.empService.insertEmpInfo(employee);
			}
			// EmployeeUtil.setValue(employee);
			j.setSuccess(true);
		} catch (Exception e) {
		}
		return j;
	}

	/**
	 * 转向emp分配角色页面
	 * 
	 * @return
	 */
	@ParentSecurity("/emp/toListPage")
	@RequestMapping("/toGrantPage")
	public ModelAndView toGrantPage(@RequestParam("empId") Integer empId) {
		ModelAndView mv = new ModelAndView("webpage/employee/empGrant");
		String roleIds = this.empService.selectEmpAndRoleByKey(empId);
		System.out.println(roleIds);
		if (roleIds != null && roleIds != "") {
			mv.addObject("roleIds", roleIds);
		} else {
			mv.addObject("roleIds", "");
		}
		mv.addObject("empId", empId);
		boolean canGrant = UserAuthFilter.isPass("ffdfe");
		mv.addObject("canGrant", canGrant);
		return mv;
	}

	/**
	 * 角色分配资源
	 * 
	 * @param role
	 * @return
	 */
	@ParentSecurity("/emp/toListPage")
	@RequestMapping(value = "/grant", method = RequestMethod.POST)
	@ResponseBody
	public Json grant(EmpRoleKey empRole) {
		Json j = new Json();
		try {
			this.empService.grant(empRole);
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	/**
	 * 禁用和启用
	 * 
	 * @param roleId
	 * @param status
	 * @return
	 */
	@ParentSecurity("/emp/toListPage")
	@RequestMapping("/changeStatus")
	@ResponseBody
	public Json changeStatus(@RequestParam("empId") Integer empId,
			@RequestParam("status") Integer status) {
		Json j = new Json();
		try {
			if (this.empService.exist(empId)) {
				Employee emp = new Employee();
				emp.setEmpId(empId);
				if (status == 1) {
					emp.setEmpState(1);
					emp.setEmpOuttime(new Date());
				} else {
					emp.setEmpState(0);
				}
				this.empService.updateByPrimaryKeySelective(emp);
				// EmployeeUtil.setValue(emp);
				j.setSuccess(true);
			} else {
				j.setMsg("不存在当前角色信息，无法作此操作");
			}

		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
}
