package cn.p2p.p2p.biz.sqdpaylog.service;

import cn.p2p.p2p.biz.sqdpaylog.model.SqdPayLog;

public interface ISqdPayLogService {

	public abstract void deleteByPrimaryKey(Integer payLogId);

	//添加
	public abstract void insert(SqdPayLog record);

	public abstract void insertSelective(SqdPayLog record);

	public abstract SqdPayLog selectByPrimaryKey(Integer payLogId);

	public abstract void updateByPrimaryKeySelective(SqdPayLog record);

	public abstract void updateByPrimaryKey(SqdPayLog record);

}