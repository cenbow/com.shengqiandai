package cn.vfunding.vfunding.prd.bms.system.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.EmployeeSession;
import cn.vfunding.common.framework.server.interceptors.NeedLogger;
import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.model.AccountCash;
import cn.vfunding.vfunding.biz.account.model.AccountRecharge;
import cn.vfunding.vfunding.biz.account.service.IAccountCashService;
import cn.vfunding.vfunding.biz.account.service.IAccountRechargeService;
import cn.vfunding.vfunding.biz.bms_employee.model.EmpRoleKey;
import cn.vfunding.vfunding.biz.bms_employee.model.Employee;
import cn.vfunding.vfunding.biz.bms_employee.model.UserEmp;
import cn.vfunding.vfunding.biz.bms_employee.service.IEmpRoleService;
import cn.vfunding.vfunding.biz.bms_employee.service.IEmployeeService;
import cn.vfunding.vfunding.biz.bms_employee.service.IRoleService;
import cn.vfunding.vfunding.biz.bms_employee.service.IUserEmpService;
import cn.vfunding.vfunding.biz.bms_system.model.Role;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.common.vo.InvesVO;
import cn.vfunding.vfunding.biz.common.vo.SearchVO;
import cn.vfunding.vfunding.prd.bms.utils.UserAuthFilter;

@Controller
@RequestMapping(value = "/system/userEmp")
public class UserEmpController {
	@Autowired
	private IUserEmpService userEmpService;
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IEmpRoleService empRoleService;
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IAccountRechargeService accountRechargeService;
	@Autowired
	private IAccountCashService accountCashService;
	@Autowired
	private IBorrowTenderService borrowTenderService;

	/**
	 * 页面跳转
	 * 
	 * @return lx
	 */
	@RequestMapping(value = "/userEmpList")
	public ModelAndView userEmpList() {
		ModelAndView mv = new ModelAndView("webpage/distribution/userEmp");
		boolean isPass = isPower(EmployeeSession.getEmpSessionEmpId());
		if(!isPass){
			//所有客服
			List<EmpRoleKey> roles = empRoleService.selectEmpAndRoleByRoleId(3); // 3 为客服
			List<Employee> emps = new ArrayList<Employee>();
			Employee e = null;
			for(EmpRoleKey role : roles){
				e = employeeService.selectByPrimaryKey(role.getEmpId());
				emps.add(e);
			}
			mv.addObject("emps", emps);
		}
		//权限判断
		mv.addObject("isPower", isPass);
		boolean canRecharge = UserAuthFilter.isPass("/system/userEmp/recharge");
		if (canRecharge) {
			mv.addObject("canRecharge", canRecharge);
		}
		boolean canCash = UserAuthFilter.isPass("/system/userEmp/withdrawals");
		if (canCash) {
			mv.addObject("canCash", canCash);
		}
		boolean canTender = UserAuthFilter.isPass("/system/userEmp/investmentHtml");
		if (canTender) {
			mv.addObject("canTender", canTender);
		}
		boolean canEdit = UserAuthFilter.isPass("/system/userEmp/editRemark");
		if (canEdit) {
			mv.addObject("canEdit", canEdit);
		}
		return mv;
	}

	/**
	 * 查询分配信息
	 * 
	 * @param pageSearch
	 * @return lx
	 * @throws Exception 
	 */
	@ParentSecurity("/system/userEmp/userEmpList")
	@RequestMapping(value = "/finduserEmpList")
	@ResponseBody
	public PageResult<UserEmp> finduserEmpList(PageSearch pageSearch, SearchVO search,String emp_id) throws Exception {
		PageResult<UserEmp> result = new PageResult<UserEmp>();
		// 得到当前用户
		Integer empId = EmployeeSession.getEmpSessionEmpId();
		if(isPower(empId)){
			search.setId(empId);
		} else {
			if(EmptyUtil.isNotEmpty(emp_id)){
				search.setId(Integer.parseInt(emp_id));
			}
		}
		//时间转换
		if(search.getStartTime()!=null&&!"".equals(search.getStartTime())){
			search.setStartTime(DateUtil.getLongTimeByStringValue(search.getStartTime() + " 00:00:00").toString());
		}
		if(search.getEndTime()!=null&!"".equals(search.getEndTime())){
			search.setEndTime(DateUtil.getLongTimeByStringValue(search.getEndTime() + " 23:59:59").toString());
		}
		pageSearch.setEntity(search);
		List<UserEmp> userEmps = userEmpService.selectFriendByUserEmpListPage(pageSearch);
		result.setRows(userEmps);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}

	/**
	 * 当前登录的用户权限判断
	 * @return :true代表客服；false代表其他登录人
	 * @author liuhuan
	 */
	public boolean isPower(Integer empId){
		boolean flag = true;
		List<EmpRoleKey> roles = empRoleService.selectEmpAndRoleByKey(empId);
		Role r = null;
		for(EmpRoleKey role : roles){
			 r = roleService.selectByPrimaryKey(role.getRoleId());
			 flag = r.getRoleName().equals("客服")?true:false;
		}
		return flag;
	}
	/**
	 * 跳转页面到充值详情页面
	 * 
	 * @return
	 */
	@ParentSecurity("/system/userEmp/userEmpList")
	@RequestMapping(value = "/rechargeHt")
	public ModelAndView rechargeHt(String userId) {
		ModelAndView mv = new ModelAndView("webpage/distribution/recharge");
		mv.addObject("userId", userId);
		return mv;
	}

	/**
	 * 查询用户充值记录
	 * 
	 * @param userId
	 * @return
	 */
	@ParentSecurity("/system/userEmp/userEmpList")
	@RequestMapping(value = "/recharge")
	@ResponseBody
	public PageResult<AccountRecharge> recharge(String userId,
			PageSearch pageSearch) {
		PageResult<AccountRecharge> result = new PageResult<AccountRecharge>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		pageSearch.setEntity(map);
		List<AccountRecharge> recharges = accountRechargeService
				.selectByIdListPage(pageSearch);
		result.setRows(recharges);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}

	/**
	 * 跳转至提现记录页面
	 * 
	 * @param userId
	 * @return
	 */
	@ParentSecurity("/system/userEmp/userEmpList")
	@RequestMapping(value = "/withdrawalsHtml")
	@ResponseBody
	public ModelAndView withdrawalsHtml(String userId) {
		ModelAndView mv = new ModelAndView("webpage/distribution/withdrawals");
		mv.addObject("userId", userId);
		return mv;

	}

	/**
	 * 查询用户提现记录
	 * 
	 * @param userId
	 * @param pageSearch
	 * @return
	 */
	@ParentSecurity("/system/userEmp/userEmpList")
	@RequestMapping(value = "/withdrawals")
	@ResponseBody
	public PageResult<AccountCash> withdrawals(String userId,
			PageSearch pageSearch) {
		PageResult<AccountCash> result = new PageResult<AccountCash>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		pageSearch.setEntity(map);
		List<AccountCash> cashs = accountCashService
				.selectByidListPage(pageSearch);
		result.setRows(cashs);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}

	/**
	 * 用户投资记录查询页面跳转
	 * 
	 * @param userId
	 * @return
	 */
	@ParentSecurity("/system/userEmp/userEmpList")
	@RequestMapping(value = "/investment")
	@ResponseBody
	public ModelAndView investment(String userId) {
		ModelAndView mv = new ModelAndView("webpage/distribution/investment");
		mv.addObject("userId", userId);
		return mv;
	}

	/**
	 * 分页查询用户投资记录
	 * 
	 * @param userId
	 * @param pageSearch
	 * @return
	 */
	@ParentSecurity("/system/userEmp/userEmpList")
	@RequestMapping(value="/investmentHtml")
	@ResponseBody
	public PageResult<InvesVO> investmentHtml(String userId,
			PageSearch pageSearch) {
		PageResult<InvesVO> result = new PageResult<InvesVO>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		pageSearch.setEntity(map);
		List<InvesVO> vos = borrowTenderService.selectByIdListPage(pageSearch);
		result.setRows(vos);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}
	
	/**
	 * 页面跳转
	 * 
	 * @param userId
	 * @return
	 */
	@ParentSecurity("/system/userEmp/userEmpList")
	@RequestMapping(value = "/editPage")
	public ModelAndView editPage(String userId) {
		ModelAndView mv = new ModelAndView("webpage/distribution/editPage");
		UserEmp userEmp = userEmpService.selectByUserId(Integer.parseInt(userId));
		mv.addObject("userEmp", userEmp);
		return mv;
	}
	/**
	 * 编辑
	 * @author liuhuan
	 */
	@NeedLogger(desc = " 客服跟踪编辑")
	@RequestMapping(value="/editRemark")
	@ResponseBody
	public Json editRemark(UserEmp userEmp) {
		Json j = new Json();
		UserEmp emp = userEmpService.selectByPrimaryKey(userEmp.getId());
		if(EmptyUtil.isNotEmpty(userEmp.getRemark()) && !userEmp.getRemark().equals(emp.getRemark())){
			userEmp.setRemark(userEmp.getRemark()+"["+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"];");
		}
		if(EmptyUtil.isNotEmpty(userEmp.getTrack()) && !userEmp.getTrack().equals(emp.getTrack())){
			userEmp.setTrack(userEmp.getTrack()+"["+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"];");
		}
		int i = userEmpService.updateByPrimaryKeySelective(userEmp);
		if(i > 0){
			j.setSuccess(true);
		}
		return j;
	}
	
	
	/**
	 * 手动分配客服  页面
	 * @author liuhuan
	 */
	@RequestMapping(value = "/editUserEmpPage")
	public ModelAndView editUserEmpPage() {
		ModelAndView mv = new ModelAndView("webpage/distribution/editUserEmp");
		//所有客服
		List<EmpRoleKey> roles = empRoleService.selectEmpAndRoleByRoleId(3); // 3 为客服
		List<Employee> emps = new ArrayList<Employee>();
		Employee e = null;
		for(EmpRoleKey role : roles){
			e = employeeService.selectByPrimaryKey(role.getEmpId());
			emps.add(e);
		}
		mv.addObject("emps", emps);
		return mv;
	}
	/**
	 * 手动分配客服
	 * @author liuhuan
	 */
	@NeedLogger(desc = "手动分配客服")
	@RequestMapping(value="/editUserEmp")
	@ResponseBody
	public Json editUserEmp(@RequestParam(value = "userIds[]")String[] userIds,String empId,String updateRemark) {
		Json j = new Json();
		int i = userEmpService.updateChangeUserEmp(userIds,empId,updateRemark);
		if(i!=1){
			j.setMsg("更新失败");
		} else {
			j.setSuccess(true);
		}
		return j;
	}
}
