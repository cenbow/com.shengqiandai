package cn.vfunding.vfunding.biz.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.test.dao.VfTestMapper;
import cn.vfunding.vfunding.biz.test.model.VfTest;
import cn.vfunding.vfunding.biz.test.service.IVfTestService;
@Service("vfTestService")
public class VfTestServiceImpl implements IVfTestService {

	@Autowired
	private VfTestMapper mapper;
	@Override
	public int updateByPrimaryKeySelective(VfTest record) {
		int result=this.mapper.updateByPrimaryKeySelective(record);
		return result;
	}

	@Override
	public int updateByPrimaryKey(VfTest record) {
		return this.mapper.updateByPrimaryKey(record);
	}

}
