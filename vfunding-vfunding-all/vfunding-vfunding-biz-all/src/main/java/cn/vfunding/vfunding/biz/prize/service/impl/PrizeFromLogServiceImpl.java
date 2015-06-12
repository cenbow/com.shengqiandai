package cn.vfunding.vfunding.biz.prize.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.prize.dao.PrizeFromLogMapper;
import cn.vfunding.vfunding.biz.prize.model.PrizeFromLog;
import cn.vfunding.vfunding.biz.prize.service.IPrizeFromLogService;

@Service("prizeFromLogService")
public class PrizeFromLogServiceImpl implements IPrizeFromLogService {
	@Autowired
	private PrizeFromLogMapper prizeFromLogMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.prizeFromLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(PrizeFromLog record) {
		return this.insert(record);
	}

	@Override
	public int insertSelective(PrizeFromLog record) {
		return this.prizeFromLogMapper.insertSelective(record);
	}

	@Override
	public PrizeFromLog selectByPrimaryKey(Integer id) {
		return this.prizeFromLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(PrizeFromLog record) {
		return this.prizeFromLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(PrizeFromLog record) {
		return this.prizeFromLogMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<PrizeFromLog> selectByCondition(PrizeFromLog record) {
		return this.prizeFromLogMapper.selectByCondition(record);
	}

}
