package cn.vfunding.vfunding.biz.trial.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.trial.dao.GiftboxBatchInfoMapper;
import cn.vfunding.vfunding.biz.trial.model.GiftboxBatchInfo;
import cn.vfunding.vfunding.biz.trial.service.IGiftboxBatchInfoService;
@Service("giftboxBatchInfoService")
public class GiftboxBatchInfoServiceImpl implements IGiftboxBatchInfoService {

	@Autowired
	private GiftboxBatchInfoMapper giftboxBatchInfoMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return giftboxBatchInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(GiftboxBatchInfo record) {
		// TODO Auto-generated method stub
		return giftboxBatchInfoMapper.insertSelective(record);
	}

	@Override
	public GiftboxBatchInfo selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return giftboxBatchInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(GiftboxBatchInfo record) {
		// TODO Auto-generated method stub
		return giftboxBatchInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<GiftboxBatchInfo> selectByBacthId(Integer batchId) {
		// TODO Auto-generated method stub
		return giftboxBatchInfoMapper.selectByBacthId(batchId);
	}

}
