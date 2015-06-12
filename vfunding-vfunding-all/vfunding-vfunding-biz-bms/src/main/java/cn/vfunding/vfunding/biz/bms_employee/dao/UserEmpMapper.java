package cn.vfunding.vfunding.biz.bms_employee.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.bms_employee.model.UserEmp;

public interface UserEmpMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(UserEmp record);

	int insertSelective(UserEmp record);

	UserEmp selectByPrimaryKey(Integer id);
	UserEmp selectByUserId(Integer id);

	int updateByPrimaryKeySelective(UserEmp record);

	int updateByPrimaryKey(UserEmp record);

	/**
	 * 查询用户分配信息
	 * 
	 * @param pageSearch
	 * @return lx
	 */
	List<UserEmp> selectFriendByUserEmpListPage(PageSearch pageSearch);
}