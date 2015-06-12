package cn.vfunding.vfunding.biz.sina.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import cn.vfunding.vfunding.biz.sina.model.SinaDic;

public interface ISinaDicService {
	int insertSelective(SinaDic record);
	
	List<SinaDic> dicLoad(String dicType);
	SinaDic dicLoad(String dicType ,String dicCode);
}
