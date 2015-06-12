package cn.vfunding.vfunding.biz.sina.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.sina.dao.SinaDicMapper;
import cn.vfunding.vfunding.biz.sina.model.SinaDic;
import cn.vfunding.vfunding.biz.sina.service.ISinaDicService;

@Service("sinaDicService")
public class SinaDicServiceImpl implements ISinaDicService {
	@Autowired
	private SinaDicMapper sinaDicMapper;
	
	@Override
	public int insertSelective(SinaDic record) {
		return sinaDicMapper.insertSelective(record);
	}
	@Cacheable(value = "DateCache")
	@Override
	public List<SinaDic> dicLoad(String dicType) {
		// TODO Auto-generated method stub
		return sinaDicMapper.selectByDicType(dicType);
	}
	@Cacheable(value = "DateCache")
	@Override
	public SinaDic dicLoad(String dicType,String dicCode) {
		// TODO Auto-generated method stub
		SinaDic sd = new SinaDic();
		sd.setDicCode(dicCode);
		sd.setDicType(dicType);
		return sinaDicMapper.selectByDicTypeAndDicCode(sd);
	}


}
