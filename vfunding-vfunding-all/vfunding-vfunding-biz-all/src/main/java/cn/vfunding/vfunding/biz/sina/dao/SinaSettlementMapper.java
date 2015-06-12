package cn.vfunding.vfunding.biz.sina.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.sina.model.SinaSettlement;

public interface SinaSettlementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SinaSettlement record);

    int insertSelective(SinaSettlement record);

    SinaSettlement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SinaSettlement record);

    int updateByPrimaryKey(SinaSettlement record);
    
    List<Integer> needSyncBonusUserIds();
    
    List<SinaSettlement> selectNeedSyncDataBySyncStatus();
}