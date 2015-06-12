package cn.p2p.p2p.biz.current.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.current.dao.CurrentRuleMapper;
import cn.p2p.p2p.biz.current.model.CurrentRule;
import cn.p2p.p2p.biz.current.service.ICurrentRuleService;
@Service
public class CurrentRuleServiceImpl implements ICurrentRuleService{

	@Autowired
	private CurrentRuleMapper currentRuleMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer currentId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(CurrentRule record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CurrentRule selectByPrimaryKey(Integer currentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(CurrentRule record) {
		// TODO Auto-generated method stub
		return this.currentRuleMapper.updateByPrimaryKeySelective(record);
	}


}