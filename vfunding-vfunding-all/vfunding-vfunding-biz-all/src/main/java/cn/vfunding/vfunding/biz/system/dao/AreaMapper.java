package cn.vfunding.vfunding.biz.system.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.system.model.Area;

public interface AreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
    /**
     * 加载所有数据
     * @return
     * @author liuyijun
     */
    List<Area> selectAll();
    /**
     * 加载所有城市数据
     * @return
     * @author liuyijun
     */
    List<Area> selectAllCity();
    
    /**
     * 根据拼音找城市信息
     * @return
     * @author liuyijun
     */
    Area selectCityByPinYin();
}