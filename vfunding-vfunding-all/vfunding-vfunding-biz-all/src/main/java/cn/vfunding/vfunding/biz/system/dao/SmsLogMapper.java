package cn.vfunding.vfunding.biz.system.dao;

import cn.vfunding.vfunding.biz.system.model.SmsLog;

public interface SmsLogMapper {
	
	int insert(SmsLog smsLog);

    int insertSelective(SmsLog smsLog);
	
}
