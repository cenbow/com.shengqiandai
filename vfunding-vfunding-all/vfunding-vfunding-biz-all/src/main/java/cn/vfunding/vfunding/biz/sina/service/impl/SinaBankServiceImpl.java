package cn.vfunding.vfunding.biz.sina.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.sina.dao.SinaBankMapper;
import cn.vfunding.vfunding.biz.sina.model.SinaBank;
import cn.vfunding.vfunding.biz.sina.service.ISinaBankService;

@Service("sinaBankService")
public class SinaBankServiceImpl implements ISinaBankService {
	
	@Autowired
	private SinaBankMapper sinaBankMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(SinaBank record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(SinaBank record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SinaBank selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(SinaBank record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(SinaBank record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SinaBank> selectSinaBankByParam(SinaBank record) {
		// TODO Auto-generated method stub
		return this.sinaBankMapper.selectSinaBankByParam(record);
	}

}
