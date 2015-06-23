package cn.p2p.p2p.biz.sqdpaylog.service;

import java.util.List;

import cn.p2p.p2p.biz.sqdpaylog.model.SqdPayLog;
import cn.vfunding.common.framework.utils.page.PageSearch;

public interface ISqdPayLogService {

	public abstract void deleteByPrimaryKey(Integer payLogId);

	/**
	 * 添加支付记录
	 * @author huangyuancheng
	 * @param SqdPayLog
	 * @return
	 */
	public abstract void insert(SqdPayLog record);

	public abstract void insertSelective(SqdPayLog record);

	public abstract SqdPayLog selectByPrimaryKey(Integer payLogId);

	public abstract void updateByPrimaryKeySelective(SqdPayLog record);

	public abstract void updateByPrimaryKey(SqdPayLog record);

	/**
	 * 查询所有支付记录
	 * @author huangyuancheng
	 * @param pageSearch
	 * @return
	 */
	public abstract List<SqdPayLog> selectAllPayLog(PageSearch pageSearch);

}