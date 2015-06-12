package cn.vfunding.vfunding.biz.sina.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.sina.model.SinaArea;

public interface SinaAreaMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(SinaArea record);

	int insertSelective(SinaArea record);

	SinaArea selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SinaArea record);

	int updateByPrimaryKey(SinaArea record);

	List<SinaArea> selectProvince();

	List<SinaArea> selectCity(String province);
}