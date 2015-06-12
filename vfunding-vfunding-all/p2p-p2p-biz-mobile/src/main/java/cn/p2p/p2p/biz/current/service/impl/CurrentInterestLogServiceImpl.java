package cn.p2p.p2p.biz.current.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.current.dao.CurrentInterestLogMapper;
import cn.p2p.p2p.biz.current.model.CurrentInterestLog;
import cn.p2p.p2p.biz.current.service.ICurrentInterestLogService;

@Service
public class CurrentInterestLogServiceImpl implements ICurrentInterestLogService{
	
	@Autowired
	private CurrentInterestLogMapper currentInterestLogMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(CurrentInterestLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CurrentInterestLog selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(CurrentInterestLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CurrentInterestLog> selectByParam(CurrentInterestLog record) {
		// TODO Auto-generated method stub
		return this.currentInterestLogMapper.selectByParam(record);
	}

	@Override
	public BigDecimal selectSumInterestByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return  this.currentInterestLogMapper.selectSumInterestByUserId(userId);
	}
   
}