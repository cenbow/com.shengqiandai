package cn.vfunding.vfunding.biz.bms_employee.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.bms_employee.model.EmpRoleKey;

public interface EmpRoleMapper {
    int deleteByPrimaryKey(EmpRoleKey key);

    int insert(EmpRoleKey record);

    int insertSelective(EmpRoleKey record);
    
    void deleteByEmpId(Integer empId);
    
    List<EmpRoleKey> selectEmpAndRoleByKey(Integer empId);
    
    List<EmpRoleKey> selectEmpAndRoleByRoleId(Integer roleId);
}