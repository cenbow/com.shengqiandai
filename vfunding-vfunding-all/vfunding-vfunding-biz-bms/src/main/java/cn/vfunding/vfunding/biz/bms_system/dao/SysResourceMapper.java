package cn.vfunding.vfunding.biz.bms_system.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.bms_system.model.SysResource;

public interface SysResourceMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysResource record);

    int insertSelective(SysResource record);

    SysResource selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysResource record);

    int updateByPrimaryKey(SysResource record);
    
    /**
	 * 资源列表数据
	 * 
	 * @param page
	 * @return
	 */
	List<SysResource> selectResourceListByTreeGrid();

	/**
	 * 查询所有资源
	 * 
	 * @return
	 */
	List<SysResource> selectResourceList();

	

	/**
	 * 根据parentId查询
	 * 
	 * @param parentId
	 * @return
	 */
	List<SysResource> selectByParentId(String parentId);

	

}