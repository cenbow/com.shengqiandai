package cn.vfunding.vfunding.biz.prize.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.prize.dao.PrizeChooseRuleMapper;
import cn.vfunding.vfunding.biz.prize.model.PrizeChooseRule;
import cn.vfunding.vfunding.biz.prize.service.IPrizeChooseRuleService;

@Service("prizeChooseRuleService")
public class PrizeChooseRuleServiceImpl implements IPrizeChooseRuleService {
	@Autowired
	private PrizeChooseRuleMapper prizeChooseRuleMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer userId) {
		return this.prizeChooseRuleMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public int insert(PrizeChooseRule record) {
		return this.prizeChooseRuleMapper.insert(record);
	}

	@Override
	public int insertSelective(PrizeChooseRule record) {
		return this.insertSelective(record);
	}

	@Override
	public PrizeChooseRule selectByPrimaryKey(Integer userId) {
		return this.selectByPrimaryKey(userId);
	}

	@Override
	public int updateByPrimaryKeySelective(PrizeChooseRule record) {
		return this.prizeChooseRuleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(PrizeChooseRule record) {
		return this.prizeChooseRuleMapper.updateByPrimaryKey(record);
	}

}
