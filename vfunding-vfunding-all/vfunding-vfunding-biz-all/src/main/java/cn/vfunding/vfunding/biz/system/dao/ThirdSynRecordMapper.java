package cn.vfunding.vfunding.biz.system.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.system.model.ThirdSynRecord;

public interface ThirdSynRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(ThirdSynRecord record);

    int insertSelective(ThirdSynRecord record);

    ThirdSynRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ThirdSynRecord record);

    int updateByPrimaryKey(ThirdSynRecord record);
    
    List<ThirdSynRecord> selectNeedSyn();
}