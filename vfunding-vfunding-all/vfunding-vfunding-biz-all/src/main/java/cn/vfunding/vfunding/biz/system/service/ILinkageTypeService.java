package cn.vfunding.vfunding.biz.system.service;

import java.util.List;

import cn.vfunding.vfunding.biz.system.model.LinkageType;

public interface ILinkageTypeService {
    int deleteByPrimaryKey(Integer id);

    int insert(LinkageType record);

    int insertSelective(LinkageType record);

    LinkageType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LinkageType record);

    int updateByPrimaryKey(LinkageType record);
    /**
     * 获取所有数据(缓存)
     * @return
     * @author liuyijun
     */
    List<LinkageType> selectAll();
}