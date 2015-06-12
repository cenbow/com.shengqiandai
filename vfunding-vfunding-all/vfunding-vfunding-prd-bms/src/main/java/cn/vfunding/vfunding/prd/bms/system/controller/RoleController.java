package cn.vfunding.vfunding.prd.bms.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.easyui.page.utils.Tree;
import cn.vfunding.common.framework.server.interceptors.NeedLogger;
import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.server.interceptors.UnSecurity;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.bms_employee.service.IRoleService;
import cn.vfunding.vfunding.biz.bms_system.model.Role;
import cn.vfunding.vfunding.prd.bms.utils.UserAuthFilter;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private IRoleService roleService;
	
	/**
	 * 转向角色列表页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/toListPage")
	public ModelAndView toListPage() throws Exception{
		ModelAndView mv=new ModelAndView("webpage/system/roleList");
		boolean canChange=UserAuthFilter.isPass("/role/changeStatus");
		boolean canGrant=UserAuthFilter.isPass("/role/grant");
		boolean canAddOrEdit=UserAuthFilter.isPass("/role/addOrEdit");
		
		Map<String,Integer> rule=new HashMap<String,Integer>();
		rule.put("org", 0);
		rule.put("post", 1);
		
		
		mv.addObject("canChange", canChange);
		mv.addObject("canGrant", canGrant);
		mv.addObject("canAddOrEdit", canAddOrEdit);
		return mv; 
	}
	
	/**
	 * 转向角色授权页面 
	 * @return
	 */
	@UnSecurity
	@RequestMapping("/toGrantPage")
	public ModelAndView toGrantPage(@RequestParam("roleId") Integer roleId){
		ModelAndView mv=new ModelAndView("webpage/system/roleGrant");
		Role r = roleService.selectRoleAndResourcesByKey(roleId);
		if(r!=null)
			mv.addObject("role", r);
		boolean canGrant=UserAuthFilter.isPass("/role/grant");
		mv.addObject("canGrant", canGrant);
		return mv;
	}
	
	/**
	 * 转向角色修改页面
	 * @return
	 */
	@UnSecurity
	@RequestMapping("/toAddOrEditPage")
	public ModelAndView toAddOrEditPage(@RequestParam(value="roleId",required=false) Integer roleId){
		ModelAndView mv=new ModelAndView("webpage/system/roleAddOrEdit");
		if(roleId!=null){
			Role r = roleService.selectByPrimaryKey(roleId);
			if(r!=null)
				mv.addObject("role", r);
		}		
		boolean canAddRole=UserAuthFilter.isPass("/role/addOrEdit");
		boolean canEditRole=UserAuthFilter.isPass("/role/addOrEdit");
		mv.addObject("canAddRole", canAddRole);
		mv.addObject("canEditRole", canEditRole);
		return mv;
	}
	
	/**
	 * 角色列表数据
	 * @param page 分页请求封装
	 * @param role 角色查询封装
	 * @return
	 */
	@ParentSecurity("/role/toListPage")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult<Role> list(PageSearch page,Role role){
		PageResult<Role> result=new PageResult<Role>();
		page.setEntity(role);
		List<Role> list=this.roleService.selectRoleListPage(page);
		result.setRows(list);
		result.setTotal(page.getTotalResult());		
		return result;
	}
	
	/**
	 * 角色下拉表格数据
	 * @param page
	 * @return
	 */
	@UnSecurity
	@RequestMapping("/comboxgrid")
	@ResponseBody
	public PageResult<Role> comboxgrid(PageSearch page,@RequestParam(value="q",required=false) String q){
		PageResult<Role> result=new PageResult<Role>();
		Role role=new Role();
		role.setRoleName(q);
		page.setEntity(role);
		List<Role> list=this.roleService.selectRoleComboxgridListPage(page);
		result.setRows(list);
		result.setTotal(page.getTotalResult());		
		return result;
	}
	
	@UnSecurity
	@RequestMapping("/comboxgridNoPage")
	@ResponseBody
	public List<Role> comboxgridNoPage(){		
		List<Role> list=this.roleService.selectRoleComboxgridList();		
		return list;
	}
	
	
	/**
	 * 角色分配资源
	 * @param role
	 * @return
	 */
	@NeedLogger(desc="角色分配资源")
	@RequestMapping(value="/grant",method=RequestMethod.POST)
	@ResponseBody
	public Json grant(Role role){
		Json j=new Json();
		try {
			this.roleService.grant(role);
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 添加或修改角色
	 * @param role
	 * @return
	 */
	@NeedLogger(desc="添加或修改角色")
	@RequestMapping(value="/addOrEdit",method=RequestMethod.POST)
	public @ResponseBody Json update(Role role){
		Json j=new Json();
		try {
			if(role.getRoleId()!=null)
			   this.roleService.updateByPrimaryKeySelective(role);
			else{
				role.setRoleState(0);
				this.roleService.insert(role);
			}
			j.setSuccess(true);
		} catch (Exception e) {			
		}	
		return j;
	}
	
	
	
	/**
	 * 禁用和启用角色
	 * @param roleId
	 * @param status
	 * @return
	 */
	@NeedLogger(desc="禁用和启用角色")
	@RequestMapping("/changeStatus")
	@ResponseBody
	public Json changeStatus(@RequestParam("roleId") Integer roleId,@RequestParam("status") Integer status){
		Json j=new Json();
		try {
			if(this.roleService.exist(roleId)){
				Role role=new Role();
				role.setRoleId(roleId);
				if(status==1){
					role.setRoleState(1);
				}else{
					role.setRoleState(0);
				}
				this.roleService.updateByPrimaryKeySelective(role);
				j.setSuccess(true);
			}else{
				j.setMsg("不存在当前角色信息，无法作此操作");
			}
			
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	
	/**
	 * 获得所有role树
	 * @return
	 */
	@UnSecurity
	@RequestMapping("/allRoleTree")
	@ResponseBody
	public List<Tree> allTree() {
		return roleService.allRoleTree();
	}
}
