package cn.vfunding.vfunding.biz.sinaResend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.sinaResend.dao.SinaResendLogMapper;
import cn.vfunding.vfunding.biz.sinaResend.model.SinaResendLog;
import cn.vfunding.vfunding.biz.sinaResend.service.ISinaResendLogService;
@Service("sinaResendLogService")
public class SinaResendLogServiceImpl implements ISinaResendLogService {

	@Autowired
	private SinaResendLogMapper sinaResendLogMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sinaResendLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(SinaResendLog record) {
		return sinaResendLogMapper.insertSelective(record);
	}

	@Override
	public SinaResendLog selectByPrimaryKey(Integer id) {
		return sinaResendLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SinaResendLog record) {
		return sinaResendLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<SinaResendLog> selectBySelective(SinaResendLog record) {
		return sinaResendLogMapper.selectBySelective(record);
	}

}
