package cn.vfunding.vfunding.biz.sinaRealname.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.sinaRealname.model.SinaRealnameLog;

public interface SinaRealnameLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SinaRealnameLog record);

    int insertSelective(SinaRealnameLog record);

    SinaRealnameLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SinaRealnameLog record);

    int updateByPrimaryKey(SinaRealnameLog record);
    
    List<SinaRealnameLog> selectUserIdByRealstatus();
}