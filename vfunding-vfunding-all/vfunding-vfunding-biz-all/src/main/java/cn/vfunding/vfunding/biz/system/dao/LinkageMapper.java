package cn.vfunding.vfunding.biz.system.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.system.model.Linkage;

public interface LinkageMapper {
	int deleteByPrimaryKey(Short id);

	int insert(Linkage record);

	int insertSelective(Linkage record);

	Linkage selectByPrimaryKey(Short id);

	int updateByPrimaryKeySelective(Linkage record);

	int updateByPrimaryKey(Linkage record);

	List<Linkage> selectAll();

	Linkage selectByName(String name);
}