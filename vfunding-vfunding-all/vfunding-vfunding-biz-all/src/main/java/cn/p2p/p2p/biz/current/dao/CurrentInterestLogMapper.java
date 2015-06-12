package cn.p2p.p2p.biz.current.dao;

import java.math.BigDecimal;
import java.util.List;

import cn.p2p.p2p.biz.current.model.CurrentInterestLog;

public interface CurrentInterestLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CurrentInterestLog record);

    int insertSelective(CurrentInterestLog record);

    CurrentInterestLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CurrentInterestLog record);

    int updateByPrimaryKey(CurrentInterestLog record);
    
   List<CurrentInterestLog> selectByParam(CurrentInterestLog record);
   
   BigDecimal selectSumInterestByUserId(Integer userId);
}