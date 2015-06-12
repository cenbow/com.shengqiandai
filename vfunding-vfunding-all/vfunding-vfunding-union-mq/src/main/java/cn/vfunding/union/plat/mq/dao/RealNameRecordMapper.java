package cn.vfunding.union.plat.mq.dao;

import cn.vfunding.union.plat.mq.model.RealNameRecord;

public interface RealNameRecordMapper {
   

    int insert(RealNameRecord record);

    int insertSelective(RealNameRecord record);

    RealNameRecord selectByPrimaryKey(Integer id);
    
    RealNameRecord selectByUserId(String userId);

    int updateByPrimaryKeySelective(RealNameRecord record);

    int updateByPrimaryKey(RealNameRecord record);
}