package cn.p2p.p2p.biz.baseinfo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.baseinfo.dao.IndexParamMapper;
import cn.p2p.p2p.biz.baseinfo.model.IndexParam;
import cn.p2p.p2p.biz.baseinfo.service.IIndexParamService;

@Service
public class IndexParamServiceImpl implements IIndexParamService {
	
	@Autowired
	private IndexParamMapper indexParamMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return this.indexParamMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(IndexParam record) {
		// TODO Auto-generated method stub
		return this.indexParamMapper.insertSelective(record);
	}

	@Override
	public IndexParam selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return this.indexParamMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(IndexParam record) {
		// TODO Auto-generated method stub
		return this.indexParamMapper.updateByPrimaryKeySelective(record);
	}
}