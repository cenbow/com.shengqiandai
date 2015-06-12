package cn.vfunding.vfunding.biz.bms_employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.bms_employee.dao.EmpRoleMapper;
import cn.vfunding.vfunding.biz.bms_employee.model.EmpRoleKey;
import cn.vfunding.vfunding.biz.bms_employee.service.IEmpRoleService;

@Service("empRoleService")
public class EmpRoleServiceImpl implements IEmpRoleService {
	@Autowired
	private EmpRoleMapper empRoleMapper;
	
	@Override
	public int deleteByPrimaryKey(EmpRoleKey key) {
		// TODO Auto-generated method stub
		return empRoleMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(EmpRoleKey record) {
		// TODO Auto-generated method stub
		return empRoleMapper.insert(record);
	}

	@Override
	public int insertSelective(EmpRoleKey record) {
		// TODO Auto-generated method stub
		return empRoleMapper.insertSelective(record);
	}

	@Override
	public void deleteByEmpId(Integer empId) {
		// TODO Auto-generated method stub
		empRoleMapper.deleteByEmpId(empId);
	}

	@Override
	public List<EmpRoleKey> selectEmpAndRoleByKey(Integer empId) {
		// TODO Auto-generated method stub
		return empRoleMapper.selectEmpAndRoleByKey(empId);
	}

	@Override
	public List<EmpRoleKey> selectEmpAndRoleByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return empRoleMapper.selectEmpAndRoleByRoleId(roleId);
	}
	
}
