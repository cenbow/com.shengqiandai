package cn.vfunding.vfunding.biz.system.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.system.model.LinkageType;

public interface LinkageTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LinkageType record);

    int insertSelective(LinkageType record);

    LinkageType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LinkageType record);

    int updateByPrimaryKey(LinkageType record);
    /**
     * 加载所有数据
     * @return
     * @author liuyijun
     */
    List<LinkageType> selectAll();
}