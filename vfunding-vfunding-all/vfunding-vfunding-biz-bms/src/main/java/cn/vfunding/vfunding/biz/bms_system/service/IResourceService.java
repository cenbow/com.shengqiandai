package cn.vfunding.vfunding.biz.bms_system.service;

import java.util.List;







import cn.vfunding.common.framework.easyui.page.utils.Resource;
import cn.vfunding.common.framework.easyui.page.utils.Tree;
import cn.vfunding.common.framework.server.EmployeeSession;
import cn.vfunding.vfunding.biz.bms_system.model.SysResource;

public interface IResourceService {

	/**
	 * 获得资源树(资源类型为菜单类型)
	 * 
	 * 通过用户ID判断，他能看到的资源
	 * 
	 * @param EmployeeSession 
	 * @return
	 * @throws Exception 
	 */
	List<Tree> tree(EmployeeSession eSession, Integer resourceGroupId) throws Exception;

	int deleteByPrimaryKey(String id);

	int insert(SysResource record);

	int insertSelective(SysResource record);

	SysResource selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SysResource record);

	int updateByPrimaryKey(SysResource record);

	/**
	 * 获得所有资源树
	 * 
	 * @return
	 * @throws Exception 
	 */
	List<Tree> allTree() throws Exception;

	/**
	 * 获得资源树
	 * 
	 * 通过用户ID判断，他能看到的资源
	 * 
	 * @return
	 * @throws Exception 
	 */
	List<Tree> employeeAllTree() throws Exception;

	/**
	 * 所有资源数表格
	 * 
	 * @return
	 * @throws Exception 
	 */
	List<Resource> treeGrid() throws Exception;

	
	
	/**
	 * 根据parentId查询
	 * @param parentId
	 * @return
	 */
	List<SysResource> selectByParentId(String parentId);
}
