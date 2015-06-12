package cn.vfunding.vfunding.biz.thirdplat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.thirdplat.dao.JjCardMapper;
import cn.vfunding.vfunding.biz.thirdplat.model.JjCard;
import cn.vfunding.vfunding.biz.thirdplat.service.IJjCardService;
@Service("jjCardService")
public class JjCardServiceImpl implements IJjCardService {
	@Autowired
	private JjCardMapper jjCardMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return jjCardMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(JjCard record) {
		return jjCardMapper.insertSelective(record);
	}

	@Override
	public JjCard selectByPrimaryKey(Integer id) {
		return jjCardMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(JjCard record) {
		return jjCardMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public JjCard selectByNoSend(Integer gamePeriod) {
		return jjCardMapper.selectByNoSend(gamePeriod);
	}

}
