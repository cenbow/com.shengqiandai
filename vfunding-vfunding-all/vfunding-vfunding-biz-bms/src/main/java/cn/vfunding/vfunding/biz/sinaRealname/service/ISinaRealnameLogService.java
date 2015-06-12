package cn.vfunding.vfunding.biz.sinaRealname.service;

import java.util.List;

import cn.vfunding.vfunding.biz.sinaRealname.model.SinaRealnameLog;

public interface ISinaRealnameLogService {
	int deleteByPrimaryKey(Integer id);


    int insertSelective(SinaRealnameLog record);

    SinaRealnameLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SinaRealnameLog record);

    List<SinaRealnameLog> selectUserIdByRealstatus();
}
