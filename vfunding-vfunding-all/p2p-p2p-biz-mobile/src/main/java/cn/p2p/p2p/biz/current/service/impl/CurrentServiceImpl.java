package cn.p2p.p2p.biz.current.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.current.dao.CurrentMapper;
import cn.p2p.p2p.biz.current.dao.CurrentRuleMapper;
import cn.p2p.p2p.biz.current.model.Current;
import cn.p2p.p2p.biz.current.model.CurrentRule;
import cn.p2p.p2p.biz.current.service.ICurrentService;
import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.utils.page.PageSearch;

@Service("currentService")
public class CurrentServiceImpl implements ICurrentService{
	
	@Autowired
	private CurrentMapper currentMapper;
	
	@Autowired
	private CurrentRuleMapper currentRuleMapper;

	@Override
	public int deleteByPrimaryKey(Integer currentId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Current record) {
		// TODO Auto-generated method stub
		return this.currentMapper.insertSelective(record);
	}

	@Override
	public Current selectByPrimaryKey(Integer currentId) {
		// TODO Auto-generated method stub
		return this.currentMapper.selectByPrimaryKey(currentId);
	}

	@Override
	public int updateByPrimaryKeySelective(Current record) {
		// TODO Auto-generated method stub
		return this.currentMapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public boolean trialCurrent(Current current,CurrentRule currentRule){
		try {
			current.setVerifyTime(new Date());
			this.currentMapper.updateByPrimaryKeySelective(current);
			this.currentRuleMapper.updateByPrimaryKeySelective(currentRule);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@Override
	public Json insertCurrent(Current current, CurrentRule currentRule) {
		// TODO Auto-generated method stub
		Json json = new Json();
		this.currentMapper.insertSelective(current);
		currentRule.setCurrentId(current.getCurrentId());
		this.currentRuleMapper.insertSelective(currentRule);
		json.setSuccess(true);
		return json;
	}

	@Override
	public List<Current> selectCurrentPageList(PageSearch pageSearch) {
		// TODO Auto-generated method stub
		return this.currentMapper.selectCurrentListPage(pageSearch);
	}

	@Override
	public List<Current> selectCurrentListBmsPage(PageSearch pageSearch) {
		// TODO Auto-generated method stub
		return this.currentMapper.selectCurrentListBmsPage(pageSearch);
	}
   

}