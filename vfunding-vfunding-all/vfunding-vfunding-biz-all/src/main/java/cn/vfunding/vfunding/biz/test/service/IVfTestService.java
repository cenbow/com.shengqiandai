package cn.vfunding.vfunding.biz.test.service;

import cn.vfunding.vfunding.biz.test.model.VfTest;

public interface IVfTestService {

	
	int updateByPrimaryKeySelective(VfTest record);

    int updateByPrimaryKey(VfTest record);
}
