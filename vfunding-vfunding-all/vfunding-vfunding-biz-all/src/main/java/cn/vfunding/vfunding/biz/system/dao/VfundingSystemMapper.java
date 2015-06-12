package cn.vfunding.vfunding.biz.system.dao;

import cn.vfunding.vfunding.biz.system.model.VfundingSystem;

public interface VfundingSystemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VfundingSystem record);

    int insertSelective(VfundingSystem record);

    VfundingSystem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VfundingSystem record);

    int updateByPrimaryKey(VfundingSystem record);
}