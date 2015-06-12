package cn.vfunding.vfunding.biz.system.service;

import java.util.List;

import cn.vfunding.vfunding.biz.system.model.ScrollpicType;

public interface IScrollpicTypeService {
    int deleteByPrimaryKey(Integer id);

    int insert(ScrollpicType record);

    int insertSelective(ScrollpicType record);

    ScrollpicType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScrollpicType record);

    int updateByPrimaryKey(ScrollpicType record);
    
    /**
     * 获取所有数据(缓存)
     * @return
     * @author liuyijun
     */
    List<ScrollpicType> selectAll();
}