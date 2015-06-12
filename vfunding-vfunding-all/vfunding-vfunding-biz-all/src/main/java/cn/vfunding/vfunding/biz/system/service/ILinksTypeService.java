package cn.vfunding.vfunding.biz.system.service;

import java.util.List;

import cn.vfunding.vfunding.biz.system.model.LinksType;

public interface ILinksTypeService {
    int deleteByPrimaryKey(Integer id);

    int insert(LinksType record);

    int insertSelective(LinksType record);

    LinksType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LinksType record);

    int updateByPrimaryKey(LinksType record);
    
    /**
     * 查询所有数据(缓存)
     * @return
     * @author liuyijun
     */
    List<LinksType> selectAll();
}