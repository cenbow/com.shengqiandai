package cn.vfunding.vfunding.biz.sina.service;

import java.util.List;

import cn.vfunding.vfunding.biz.sina.model.SinaArea;

public interface ISinaAreaService {
	
    int deleteByPrimaryKey(Integer id);

    int insert(SinaArea record);

    int insertSelective(SinaArea record);

    SinaArea selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SinaArea record);

    int updateByPrimaryKey(SinaArea record);
    
    List<SinaArea> selectAreaByParam(SinaArea record);
}