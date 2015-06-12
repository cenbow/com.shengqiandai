package cn.vfunding.vfunding.biz.system.service;

import java.util.List;

import cn.vfunding.vfunding.biz.system.model.Linkage;

public interface ILinkageService {
    int deleteByPrimaryKey(Short id);

    int insert(Linkage record);

    int insertSelective(Linkage record);

    Linkage selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(Linkage record);

    int updateByPrimaryKey(Linkage record);
    
    /**
     * 根据类型ID查询
     * @param typeId
     * @return
     * @author liuyijun
     */
    List<Linkage> selectByTypeId(Integer typeId);
}