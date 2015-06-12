package cn.vfunding.vfunding.biz.sinaBalance.service;

import java.util.List;

import cn.vfunding.vfunding.biz.sinaBalance.model.SinaBalanceLog;

public interface ISinaBalanceLogService {
	int deleteByPrimaryKey(Integer id);


    int insertSelective(SinaBalanceLog record);

    SinaBalanceLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SinaBalanceLog record);

    List<SinaBalanceLog> selectTotelNotZeroId();
    
	void querySinaBalanceAsync(List<SinaBalanceLog> sbs);
	
	int deleteAll();

}
