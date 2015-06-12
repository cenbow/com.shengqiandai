package cn.vfunding.vfunding.biz.bms_employee.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.bms_employee.model.Employee;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer empId);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
    
    /**
	 * 分页加条件查询
	 * 
	 * @param page
	 * @return
	 */
	List<Employee> selectEmpListPage(PageSearch pageSearch);
	
	/**
	 * 根据ID查询，只查询最基本的员工信息(ID，用户名、英文名称)
	 * @param empId
	 * @return
	 */
	Employee selectEmployeeNameByID(Integer empId);
	
	Employee selectEmployeeBaseInfoByLoginName(String loginName);
	
	
	/**
	 * 用户登陆
	 * 
	 * @param employee
	 * @return
	 */
	Employee selectByLogin(Employee employee);

	/**
	 * 登陆前验证用户是否存在
	 * 
	 * @param employee
	 * @return
	 */
	int checkEmpLogin(Employee employee);

	/**
	 * 验证用户状态
	 * 
	 * @param employee
	 * @return
	 */
	String checkEmpState(Employee employee);

	List<Employee> selectEmpAll();
	
	Integer exist(Integer empId);
}