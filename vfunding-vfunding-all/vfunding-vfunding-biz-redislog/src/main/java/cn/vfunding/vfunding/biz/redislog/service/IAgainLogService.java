package cn.vfunding.vfunding.biz.redislog.service;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.redislog.model.AgainLog;

public interface IAgainLogService {


	void insertNewLog(AgainLog record) throws Exception;

	void updateByPrimaryKey(AgainLog record) throws Exception;

	

	 List<AgainLog> selectByHandleListPage(PageSearch search) throws Exception;

	List<AgainLog> selectByAuto() throws Exception;

	Long deleteByPrimaryKey(String id) throws Exception;



	AgainLog selectByPrimaryKey(String id) throws Exception;
}
