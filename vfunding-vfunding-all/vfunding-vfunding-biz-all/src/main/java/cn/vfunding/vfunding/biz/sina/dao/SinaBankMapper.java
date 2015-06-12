package cn.vfunding.vfunding.biz.sina.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.sina.model.SinaBank;

public interface SinaBankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SinaBank record);

    int insertSelective(SinaBank record);

    SinaBank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SinaBank record);

    int updateByPrimaryKey(SinaBank record);
    
    List<SinaBank> selectSinaBankByParam(SinaBank record);
}