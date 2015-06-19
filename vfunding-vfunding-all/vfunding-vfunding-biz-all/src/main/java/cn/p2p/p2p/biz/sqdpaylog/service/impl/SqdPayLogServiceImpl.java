package cn.p2p.p2p.biz.sqdpaylog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.sqdpaylog.dao.SqdPayLogMapper;
import cn.p2p.p2p.biz.sqdpaylog.model.SqdPayLog;
import cn.p2p.p2p.biz.sqdpaylog.service.ISqdPayLogService;
@Service
public class SqdPayLogServiceImpl implements ISqdPayLogService {
	/**
	 * sqd支付记录服务
	 */
	
	@Autowired
	private SqdPayLogMapper sqdPayLogMapper;
	
	@Override
	public void deleteByPrimaryKey(Integer payLogId){
		sqdPayLogMapper.deleteByPrimaryKey(payLogId);
	};
	//添加
    @Override
	public  void insert(SqdPayLog record){
    	sqdPayLogMapper.insert(record);
    };

    
    @Override
	public  void insertSelective(SqdPayLog record){
    	sqdPayLogMapper.insert(record);
    };

	   @Override
	public   SqdPayLog selectByPrimaryKey(Integer payLogId){
		  return sqdPayLogMapper.selectByPrimaryKey(payLogId);
	   };

   @Override
	public  void updateByPrimaryKeySelective(SqdPayLog record){
		   sqdPayLogMapper.updateByPrimaryKeySelective(record);
	   };
	
	   @Override
	public  void updateByPrimaryKey(SqdPayLog record){
		   sqdPayLogMapper.updateByPrimaryKey(record);
	   };
}
