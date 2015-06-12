package cn.vfunding.vfunding.biz.system.service;

import java.util.List;

import cn.vfunding.vfunding.biz.system.model.ThirdSynRecord;

public interface IThirdSynRecordService {

    int insert(ThirdSynRecord record);

    void updateByPrimaryKey(ThirdSynRecord record);
    
    List<ThirdSynRecord> selectNeedSyn();
}
