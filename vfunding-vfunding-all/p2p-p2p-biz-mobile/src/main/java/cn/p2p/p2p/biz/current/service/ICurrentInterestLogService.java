package cn.p2p.p2p.biz.current.service;

import java.math.BigDecimal;
import java.util.List;

import cn.p2p.p2p.biz.current.model.CurrentInterestLog;

public interface ICurrentInterestLogService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(CurrentInterestLog record);

    CurrentInterestLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CurrentInterestLog record);
    
    List<CurrentInterestLog> selectByParam(CurrentInterestLog record);
    
    BigDecimal selectSumInterestByUserId(Integer userId);
}