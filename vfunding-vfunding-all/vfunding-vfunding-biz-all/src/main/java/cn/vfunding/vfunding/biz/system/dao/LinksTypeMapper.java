package cn.vfunding.vfunding.biz.system.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.system.model.LinksType;

public interface LinksTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LinksType record);

    int insertSelective(LinksType record);

    LinksType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LinksType record);

    int updateByPrimaryKey(LinksType record);
    
    /**
     * 加载所有数据
     * @return
     * @author liuyijun
     */
    List<LinksType> selectAll();
}