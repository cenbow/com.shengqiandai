package cn.vfunding.vfunding.biz.prize.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.prize.dao.PrizeChooseRuleLogMapper;
import cn.vfunding.vfunding.biz.prize.model.PrizeChooseRuleLog;
import cn.vfunding.vfunding.biz.prize.service.IPrizeChooseRuleLogService;

@Service("prizeChooseRuleLogService")
public class PrizeChooseRuleLogServiceImpl implements
		IPrizeChooseRuleLogService {
	@Autowired
	private PrizeChooseRuleLogMapper prizeChooseRuleLogMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.prizeChooseRuleLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(PrizeChooseRuleLog record) {
		return this.prizeChooseRuleLogMapper.insert(record);
	}

	@Override
	public int insertSelective(PrizeChooseRuleLog record) {
		return this.insertSelective(record);
	}

	@Override
	public PrizeChooseRuleLog selectByPrimaryKey(Integer id) {
		return this.prizeChooseRuleLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(PrizeChooseRuleLog record) {
		return this.prizeChooseRuleLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(PrizeChooseRuleLog record) {
		return this.prizeChooseRuleLogMapper.updateByPrimaryKey(record);
	}

}
