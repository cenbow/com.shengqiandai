package cn.vfunding.vfunding.biz.prize.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.prize.model.PrizeFromLog;

public interface PrizeFromLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PrizeFromLog record);

    int insertSelective(PrizeFromLog record);

    PrizeFromLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PrizeFromLog record);

    int updateByPrimaryKey(PrizeFromLog record);
    
    List<PrizeFromLog> selectByCondition(PrizeFromLog record);
}