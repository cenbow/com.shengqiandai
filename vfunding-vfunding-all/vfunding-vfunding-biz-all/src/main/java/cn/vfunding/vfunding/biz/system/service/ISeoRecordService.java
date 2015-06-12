package cn.vfunding.vfunding.biz.system.service;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.model.SeoRecord;
import cn.vfunding.vfunding.biz.system.model.SeoRecordWithBLOBs;

public interface ISeoRecordService {
	int deleteByPrimaryKey(Integer id);

    int insert(SeoRecordWithBLOBs record);

    int insertSelective(SeoRecordWithBLOBs record);

    SeoRecordWithBLOBs selectByPrimaryKey(Integer id);
    
    List<SeoRecordWithBLOBs> selectListByUrl(String url);
    
    SeoRecordWithBLOBs selectUniqueByUrl(String url);

    int updateByPrimaryKeySelective(SeoRecordWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SeoRecordWithBLOBs record);

    int updateByPrimaryKey(SeoRecord record);

	List<SeoRecord> selectSystemSeoRecordListPage(PageSearch pageSearch);
}
