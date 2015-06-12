package cn.vfunding.vfunding.biz.bms_employee.service;

import java.util.List;

import org.apache.http.entity.mime.MultipartEntityBuilder;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.bms_employee.model.EmpRoleKey;
import cn.vfunding.vfunding.biz.bms_employee.model.Employee;
import cn.vfunding.vfunding.common.module.activemq.message.UserEmpMessage;

public interface IEmployeeService {

	int deleteByPrimaryKey(Integer empId);

	int insert(Employee record, MultipartEntityBuilder multipartEntity);

	int insertEmpInfo(Employee record);

	int insertSelective(Employee record, MultipartEntityBuilder multipartEntity);

	Employee selectByPrimaryKey(Integer empId);

	Employee selectDetailByPrimaryKey(Integer empId);
	
	/**
	 * 根据ID查询，只查询最基本的员工信息(ID，用户名、英文名称)
	 * @param empId
	 * @return
	 */
	Employee selectEmployeeNameByID(Integer empId);
	
	Employee selectEmployeeBaseInfoByLoginName(String loginName);

	int updateByPrimaryKeySelective(Employee record);

	int updateEmpAndRoleByPrimaryKeySelective(Employee record);

	int updateByPrimaryKey(Employee record);

	List<Employee> getEmployeeListPage(PageSearch pageSearch);

	/**
	 * 用户登陆
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	Employee login(String loginname, String password);

	/**
	 * 验证用户是否存在
	 * 
	 * @return
	 */
	boolean checkEmpLogin(String loginname, String password);

	/**
	 * 验证用户状态是否可用(TRUE 可用)
	 * 
	 * @param loginname
	 * @param password
	 * @return
	 */
	boolean checkEmpState(String loginname, String password);

	/**
	 * 分页加条件查询
	 * 
	 * @param page
	 * @return
	 */

	List<Employee> selectEmpListPage(PageSearch page);

	List<Employee> selectEmpAll();


	boolean exist(Integer empId);
	
	void grant(EmpRoleKey record);
	
	/***
	 * 根据用户Id查找角色
	 * 
	 * @param empId
	 * @return
	 */
	String selectEmpAndRoleByKey(Integer empId);
	
	void insertUserEmpByReg(UserEmpMessage userEmp);
}
