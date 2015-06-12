package cn.vfunding.vfunding.biz.prize.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.prize.dao.PrizeRepositoryMapper;
import cn.vfunding.vfunding.biz.prize.model.PrizeRepository;
import cn.vfunding.vfunding.biz.prize.service.IPrizeRepositoryService;
import cn.vfunding.vfunding.biz.prize.vo.PrizeRepositoryVO;

@Service("prizeRespositoryService")
public class PrizeRepositoryServiceImpl implements IPrizeRepositoryService {
	@Autowired
	private PrizeRepositoryMapper prizeRepositoryMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.prizeRepositoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(PrizeRepository record) {
		return this.prizeRepositoryMapper.insert(record);
	}

	@Override
	public int insertSelective(PrizeRepository record) {
		return this.insertSelective(record);
	}

	@Override
	public PrizeRepository selectByPrimaryKey(Integer id) {
		return this.prizeRepositoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(PrizeRepository record) {
		return this.prizeRepositoryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(PrizeRepository record) {
		return this.updateByPrimaryKey(record);
	}

	@Override
	public List<PrizeRepositoryVO> selectMyPrizeChoose(PrizeRepository entity) {
		return this.prizeRepositoryMapper.selectMyPrizeChoose(entity);
	}

	@Override
	public List<PrizeRepositoryVO> selectPrizeChoose(Map<String,Integer> map) {
		return this.prizeRepositoryMapper.selectPrizeChoose(map);
	}

	@Override
	public List<PrizeRepositoryVO> selectMyPrizeChooseForSeasonTwo(
			PrizeRepository entity) {
		return this.prizeRepositoryMapper.selectMyPrizeChooseForSeasonTwo(entity);
	}

	@Override
	public List<PrizeRepositoryVO> selectPrizeChooseForSeasonTwo(
			Map<String, Integer> map) {
		return this.prizeRepositoryMapper.selectPrizeChooseForSeasonTwo(map);
	}
}
