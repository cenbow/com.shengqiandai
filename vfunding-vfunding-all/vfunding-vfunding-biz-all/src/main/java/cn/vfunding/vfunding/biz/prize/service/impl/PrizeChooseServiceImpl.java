package cn.vfunding.vfunding.biz.prize.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.prize.dao.PrizeChooseMapper;
import cn.vfunding.vfunding.biz.prize.model.PrizeChoose;
import cn.vfunding.vfunding.biz.prize.service.IPrizeChooseService;

@Service("prizeChooseService")
public class PrizeChooseServiceImpl implements IPrizeChooseService {
	@Autowired
	private PrizeChooseMapper prizeChooseMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.prizeChooseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(PrizeChoose record) {
		return this.prizeChooseMapper.insert(record);
	}

	@Override
	public int insertSelective(PrizeChoose record) {
		return this.insertSelective(record);
	}

	@Override
	public PrizeChoose selectByPrimaryKey(Integer id) {
		return this.prizeChooseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(PrizeChoose record) {
		return this.prizeChooseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(PrizeChoose record) {
		return this.prizeChooseMapper.updateByPrimaryKey(record);
	}

}
