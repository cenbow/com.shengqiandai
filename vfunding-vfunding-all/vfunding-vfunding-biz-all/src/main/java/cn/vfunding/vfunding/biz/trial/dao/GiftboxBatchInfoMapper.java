package cn.vfunding.vfunding.biz.trial.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.trial.model.GiftboxBatchInfo;

public interface GiftboxBatchInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GiftboxBatchInfo record);

    int insertSelective(GiftboxBatchInfo record);

    GiftboxBatchInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GiftboxBatchInfo record);

    int updateByPrimaryKey(GiftboxBatchInfo record);
    
    List<GiftboxBatchInfo> selectByBacthId(Integer batchId);
}