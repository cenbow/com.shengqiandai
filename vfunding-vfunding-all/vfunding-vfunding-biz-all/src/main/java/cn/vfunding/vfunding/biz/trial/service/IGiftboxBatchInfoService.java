package cn.vfunding.vfunding.biz.trial.service;

import java.util.List;

import cn.vfunding.vfunding.biz.trial.model.GiftboxBatchInfo;

public interface IGiftboxBatchInfoService {
	int deleteByPrimaryKey(Integer id);

    int insertSelective(GiftboxBatchInfo record);

    GiftboxBatchInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GiftboxBatchInfo record);
    
    List<GiftboxBatchInfo> selectByBacthId(Integer batchId);

}
