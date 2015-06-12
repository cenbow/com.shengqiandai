package cn.vfunding.union.plat.mq.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.ObjectId;
import cn.vfunding.union.plat.mq.dao.ProfitRecordInfoMapper;
import cn.vfunding.union.plat.mq.model.ProfitRecordInfo;
import cn.vfunding.union.plat.mq.model.ProfitStatisticsVO;
import cn.vfunding.union.plat.mq.service.IProfitRecordInfoService;

@Service("profitRecordInfoService")
public class ProfitRecordInfoServiceImpl implements IProfitRecordInfoService {

	@Autowired
	private ProfitRecordInfoMapper mapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		return this.mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ProfitRecordInfo record) {
		return this.mapper.insert(record);
	}

	@Override
	public int insertSelective(ProfitRecordInfo record) {
		return this.mapper.insertSelective(record);
	}

	@Override
	public ProfitRecordInfo selectByPrimaryKey(String id) {
		return this.mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ProfitRecordInfo record) {
		return this.mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ProfitRecordInfo record) {
		return this.mapper.updateByPrimaryKey(record);
	}

	@Override
	public void selectAndSaveMouthData(ProfitStatisticsVO vo) {
		List<ProfitRecordInfo> list=this.mapper.createMouthData(vo);
		for (ProfitRecordInfo profitRecordInfo : list) {
			profitRecordInfo.setId(ObjectId.get());
			profitRecordInfo.setInsertTime(new Date());
			this.mapper.insertSelective(profitRecordInfo);
		}
		
	}

	

}
