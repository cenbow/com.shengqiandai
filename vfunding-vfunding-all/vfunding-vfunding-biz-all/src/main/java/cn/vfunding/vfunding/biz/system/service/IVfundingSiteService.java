package cn.vfunding.vfunding.biz.system.service;

import cn.vfunding.vfunding.biz.system.model.VfundingSite;

public interface IVfundingSiteService {
    int deleteByPrimaryKey(Integer siteId);

    int insert(VfundingSite record);

    int insertSelective(VfundingSite record);

    VfundingSite selectByPrimaryKey(Integer siteId);

    int updateByPrimaryKeySelective(VfundingSite record);

    int updateByPrimaryKeyWithBLOBs(VfundingSite record);

    int updateByPrimaryKey(VfundingSite record);
}