package cn.vfunding.vfunding.biz.system.service;

import java.util.List;

import cn.vfunding.vfunding.biz.system.model.Area;

public interface IAreaService {

	int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
    /**
     * 查询所有省份信息
     * @return
     * @author liuyijun
     */
    List<Area> selectProvince();
    /**
     * 根据父ID查询子地区
     * @param pid
     * @return
     * @author liuyijun
     */
    List<Area> selectByParentId(Integer pid);
    
	String getAreaNameById(Integer id);
}
