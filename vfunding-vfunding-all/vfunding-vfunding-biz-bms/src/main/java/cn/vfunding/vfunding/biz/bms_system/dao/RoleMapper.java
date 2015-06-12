package cn.vfunding.vfunding.biz.bms_system.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.bms_system.model.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    /**
	 * 分页条件查询
	 * 
	 * @param page
	 * @return
	 */
	List<Role> selectRoleListPage(PageSearch page);

	/***
	 * 验证角色是否存在
	 * 
	 * @param roleId
	 * @return >1为true
	 */
	int exist(Integer roleId);

	/**
	 * 根据角色id查询出带有资源信息
	 * 
	 * @param roleId
	 * @return
	 */
	Role selectRoleAndResourcesByKey(Integer roleId);

	List<Role> allRoleTree();
	/**
	 * 角色下拉表格数据查询
	 * @param page
	 * @return
	 */
	List<Role> selectRoleComboxgridListPage(PageSearch page);
	/**
	 * 角色下拉框数据查询
	 * @return
	 */
	List<Role> selectRoleComboxgridList();
}