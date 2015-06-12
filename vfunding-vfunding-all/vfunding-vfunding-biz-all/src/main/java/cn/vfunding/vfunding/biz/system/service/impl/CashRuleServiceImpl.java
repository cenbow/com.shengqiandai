package cn.vfunding.vfunding.biz.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.system.dao.CashRuleMapper;
import cn.vfunding.vfunding.biz.system.model.CashRule;
import cn.vfunding.vfunding.biz.system.service.ICashRuleService;
@Service("cashRuleService")
public class CashRuleServiceImpl implements ICashRuleService {

	@Autowired
	private CashRuleMapper cashRuleMapper;
	@Override
	public CashRule selectOne() {
		return cashRuleMapper.selectOne();
	}

}
