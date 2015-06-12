package cn.vfunding.vfunding.biz.bms_employee.service;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.bms_employee.model.UserEmp;

public interface IUserEmpService {
	
	int insert(UserEmp record);

	int insertSelective(UserEmp record);

	UserEmp selectByPrimaryKey(Integer id);
	UserEmp selectByUserId(Integer id);

	int updateByPrimaryKeySelective(UserEmp record);

	int updateByPrimaryKey(UserEmp record);
	
	/**
	 * 查询用户分配信息
	 * @param pageSearch
	 * @return 
	 * lx
	 */
    List<UserEmp> selectFriendByUserEmpListPage(PageSearch pageSearch);

    /**
     * 手动分配客户
     * @author liuhuan
     */
	int updateChangeUserEmp(String[] userIds, String empId, String updateRemark);
}
