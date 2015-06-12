package cn.vfunding.vfunding.biz.bms_system.service;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.bms_system.model.BmsLog;

public interface IBmsLogService {

	int deleteByPrimaryKey(Integer id);

    int insert(BmsLog record);

    int insertSelective(BmsLog record);

    BmsLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BmsLog record);

    int updateByPrimaryKey(BmsLog record);
    
    List<BmsLog> selectSystemBmsLogListPage(PageSearch search);

	int deleteByIds(int[] ids);
}
