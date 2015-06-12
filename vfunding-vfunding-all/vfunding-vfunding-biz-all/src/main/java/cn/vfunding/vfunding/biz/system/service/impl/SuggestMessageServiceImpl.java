package cn.vfunding.vfunding.biz.system.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.system.dao.SuggestMessageMapper;
import cn.vfunding.vfunding.biz.system.model.SuggestMessage;
import cn.vfunding.vfunding.biz.system.service.ISuggestMessageService;

@Service("suggestMessageService")
public class SuggestMessageServiceImpl implements ISuggestMessageService {

	@Autowired
	private SuggestMessageMapper suggestMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return this.suggestMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SuggestMessage record) {
		// TODO Auto-generated method stub
		return this.suggestMapper.insert(record);
	}

	@Override
	public int insertSelective(SuggestMessage record) {
		// TODO Auto-generated method stub
		record.setStatus(0);
		record.setAddTime(new Date());
		return this.suggestMapper.insertSelective(record);
	}

	@Override
	public SuggestMessage selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return this.suggestMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SuggestMessage record) {
		// TODO Auto-generated method stub
		return this.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SuggestMessage record) {
		// TODO Auto-generated method stub
		return this.updateByPrimaryKey(record);
	}

}
