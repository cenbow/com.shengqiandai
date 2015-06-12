package cn.vfunding.vfunding.biz.system.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.system.model.ScrollpicType;

public interface ScrollpicTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScrollpicType record);

    int insertSelective(ScrollpicType record);

    ScrollpicType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScrollpicType record);

    int updateByPrimaryKey(ScrollpicType record);
    /**
     * 加载所有数据
     * @return
     * @author liuyijun
     */
    List<ScrollpicType> selectAll();
}