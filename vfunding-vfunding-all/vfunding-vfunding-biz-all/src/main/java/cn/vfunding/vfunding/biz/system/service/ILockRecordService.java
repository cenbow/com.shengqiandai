package cn.vfunding.vfunding.biz.system.service;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.model.LockRecord;

public interface ILockRecordService {

	int deleteByPrimaryKey(Integer userId);

	int insert(LockRecord record);

	int insertSelective(LockRecord record);

	LockRecord selectByPrimaryKey(Integer userId);

	int updateByPrimaryKeySelective(LockRecord record);

	int updateByPrimaryKey(LockRecord record);

	List<LockRecord> selectByListPage(PageSearch search);
	
	LockRecord selectByLoginValue(String value);

	/**
	 * 后台解锁用户
	 * @author liuhuan
	 */
	int lockUserByUserId(Integer userId,Integer status);
}
