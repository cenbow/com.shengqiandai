package cn.vfunding.vfunding.biz.sina.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.sina.dao.SinaSendActionLogMapper;
import cn.vfunding.vfunding.biz.sina.model.SinaSendActionLog;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendActionLogService;

@Service("sinaSendActionLogService")
public class SinaSendActionLogServiceImpl implements ISinaSendActionLogService {

	@Autowired
	private SinaSendActionLogMapper sinaSendActionLogMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {

		return sinaSendActionLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SinaSendActionLog record) {

		return sinaSendActionLogMapper.insert(record);
	}

	@Override
	public int insertSelective(SinaSendActionLog record) {

		return sinaSendActionLogMapper.insertSelective(record);
	}

	@Override
	public SinaSendActionLog selectByPrimaryKey(Integer id) {

		return sinaSendActionLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SinaSendActionLog record) {

		return sinaSendActionLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SinaSendActionLog record) {

		return sinaSendActionLogMapper.updateByPrimaryKey(record);
	}

}
