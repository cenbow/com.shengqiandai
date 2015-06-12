package cn.vfunding.vfunding.biz.sinaBalance.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.sinaBalance.model.SinaBalanceLog;

public interface SinaBalanceLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SinaBalanceLog record);

    int insertSelective(SinaBalanceLog record);

    SinaBalanceLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SinaBalanceLog record);

    int updateByPrimaryKey(SinaBalanceLog record);
    
    List<SinaBalanceLog> selectTotelNotZeroId();
    
    int deleteAll();
}