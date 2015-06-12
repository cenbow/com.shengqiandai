package cn.vfunding.vfunding.biz.system.service;

import cn.vfunding.vfunding.biz.system.model.VfundingSystem;

public interface IVfundingSystemService {
    int deleteByPrimaryKey(Integer id);

    int insert(VfundingSystem record);

    int insertSelective(VfundingSystem record);

    VfundingSystem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VfundingSystem record);

    int updateByPrimaryKey(VfundingSystem record);
}