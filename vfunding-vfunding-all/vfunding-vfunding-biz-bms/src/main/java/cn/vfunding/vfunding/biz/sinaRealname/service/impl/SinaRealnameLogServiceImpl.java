package cn.vfunding.vfunding.biz.sinaRealname.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.sinaRealname.dao.SinaRealnameLogMapper;
import cn.vfunding.vfunding.biz.sinaRealname.model.SinaRealnameLog;
import cn.vfunding.vfunding.biz.sinaRealname.service.ISinaRealnameLogService;

@Service("sinaRealnameLogService")
public class SinaRealnameLogServiceImpl implements ISinaRealnameLogService{

	@Autowired
	private SinaRealnameLogMapper sinaRealnameLogMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sinaRealnameLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(SinaRealnameLog record) {
		// TODO Auto-generated method stub
		return sinaRealnameLogMapper.insertSelective(record);
	}

	@Override
	public SinaRealnameLog selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sinaRealnameLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SinaRealnameLog record) {
		// TODO Auto-generated method stub
		return sinaRealnameLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<SinaRealnameLog> selectUserIdByRealstatus() {
		// TODO Auto-generated method stub
		return sinaRealnameLogMapper.selectUserIdByRealstatus();
	}

}
