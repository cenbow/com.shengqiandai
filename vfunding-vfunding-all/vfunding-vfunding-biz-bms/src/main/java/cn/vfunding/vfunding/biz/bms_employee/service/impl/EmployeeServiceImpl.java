package cn.vfunding.vfunding.biz.bms_employee.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.bms_employee.dao.EmpRoleMapper;
import cn.vfunding.vfunding.biz.bms_employee.dao.EmployeeMapper;
import cn.vfunding.vfunding.biz.bms_employee.dao.UserEmpMapper;
import cn.vfunding.vfunding.biz.bms_employee.model.EmpRoleKey;
import cn.vfunding.vfunding.biz.bms_employee.model.Employee;
import cn.vfunding.vfunding.biz.bms_employee.model.UserEmp;
import cn.vfunding.vfunding.biz.bms_employee.service.IEmployeeService;
import cn.vfunding.vfunding.common.module.activemq.message.UserEmpMessage;

@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private EmpRoleMapper empRoleMapper;

	@Override
	public int deleteByPrimaryKey(Integer empId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Employee record, MultipartEntityBuilder multipartEntity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertEmpInfo(Employee record) {

		this.employeeMapper.insertSelective(record);
		if (EmptyUtil.isNotEmpty(record.getAddOrEditRoleIds())) {
			String addOrEditIds = record.getAddOrEditRoleIds();
			if (addOrEditIds.startsWith(",")) {
				addOrEditIds = addOrEditIds.substring(1, addOrEditIds.length());
			}
			String[] ids = addOrEditIds.split(",");
			EmpRoleKey key = new EmpRoleKey();
			key.setEmpId(record.getEmpId());
			for (String id : ids) {
				key.setRoleId(Integer.parseInt(id));
				this.empRoleMapper.insert(key);
			}
		}
		return record.getEmpId();

	}

	@Override
	public int insertSelective(Employee record,
			MultipartEntityBuilder multipartEntity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Employee selectByPrimaryKey(Integer empId) {
		return this.employeeMapper.selectByPrimaryKey(empId);
	}

	@Override
	public Employee selectDetailByPrimaryKey(Integer empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee selectEmployeeNameByID(Integer empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Employee record) {
		return this.employeeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateEmpAndRoleByPrimaryKeySelective(Employee record) {
		if (EmptyUtil.isNotEmpty(record.getAddOrEditRoleIds())) {
			this.empRoleMapper.deleteByEmpId(record.getEmpId());
			String addOrEditIds = record.getAddOrEditRoleIds();
			if (addOrEditIds.startsWith(",")) {
				addOrEditIds = addOrEditIds.substring(1, addOrEditIds.length());
			}
			String[] ids = addOrEditIds.split(",");
			EmpRoleKey key = new EmpRoleKey();
			key.setEmpId(record.getEmpId());
			for (String id : ids) {
				key.setRoleId(Integer.parseInt(id));
				this.empRoleMapper.insert(key);
			}
		}
		return this.employeeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Employee record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Employee> getEmployeeListPage(PageSearch pageSearch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee login(String loginname, String password) {
		Employee employee = new Employee(loginname, password);
		return this.employeeMapper.selectByLogin(employee);
	}

	@Override
	public boolean checkEmpLogin(String loginname, String password) {
		Employee employee = new Employee(loginname, password);
		int result = this.employeeMapper.checkEmpLogin(employee);
		return result > 0 ? true : false;
	}

	@Override
	public boolean checkEmpState(String loginname, String password) {
		Employee employee = new Employee(loginname, password);
		String result = this.employeeMapper.checkEmpState(employee);
		return EmptyUtil.isEmpty(result) || result.equals("0") ? true : false;
	}

	@Override
	public List<Employee> selectEmpListPage(PageSearch page) {
		return this.employeeMapper.selectEmpListPage(page);
	}

	@Override
	public List<Employee> selectEmpAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exist(Integer empId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void grant(EmpRoleKey empRole) {
		if (StringUtils.hasText(empRole.getRoleIds())) {
			String[] ids = empRole.getRoleIds().split(",");
			this.empRoleMapper.deleteByEmpId(empRole.getEmpId());
			for (String id : ids) {
				EmpRoleKey key = new EmpRoleKey();
				key.setRoleId(Integer.parseInt(id));
				key.setEmpId(empRole.getEmpId());
				empRoleMapper.insert(key);
			}
		}
	}

	@Override
	public String selectEmpAndRoleByKey(Integer empId) {
		List<EmpRoleKey> list = this.empRoleMapper.selectEmpAndRoleByKey(empId);
		if (CollectionUtils.isNotEmpty(list)) {
			boolean b = false;
			String ids = "";
			for (EmpRoleKey empRole : list) {
				if (b) {
					ids += ",";
				} else {
					b = true;
				}
				ids += empRole.getRoleId();
			}
			return ids;
		}
		return null;
	}

	@Autowired
	private UserEmpMapper userEmpMapper;
	/**
	 * 注册分配客服
	 * @author liuhuan
	 */
	public void insertUserEmpByReg(UserEmpMessage userEmpMessage){
		//所有客服
		List<EmpRoleKey> roles = empRoleMapper.selectEmpAndRoleByRoleId(3); // 3 为客服
		List<Integer> empId = new ArrayList<Integer>();
		Employee e = null;
		for(EmpRoleKey role : roles){
			e = this.selectByPrimaryKey(role.getEmpId());
			empId.add(e.getEmpId());
		}
		UserEmp userEmp = new UserEmp();
		userEmp.setEmpId(empId.get((int)(Math.random()*empId.size()-1)));//客服id 随机分配
		userEmp.setUserId(userEmpMessage.getUserId());
		userEmp.setType(1);//默认1；为已分配客服
		userEmpMapper.insertSelective(userEmp);
	}

	@Override
	public Employee selectEmployeeBaseInfoByLoginName(String loginName) {
		return this.employeeMapper.selectEmployeeBaseInfoByLoginName(loginName);
	}
}
