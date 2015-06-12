package cn.vfunding.vfunding.biz.system.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.model.LockRecord;

public interface LockRecordMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(LockRecord record);

    int insertSelective(LockRecord record);

    LockRecord selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(LockRecord record);

    int updateByPrimaryKey(LockRecord record);
    
    List<LockRecord> selectByListPage(PageSearch search);
    
    List<LockRecord> selectByUnlockTime();
    
    LockRecord selectByLoginValue(String value);
}