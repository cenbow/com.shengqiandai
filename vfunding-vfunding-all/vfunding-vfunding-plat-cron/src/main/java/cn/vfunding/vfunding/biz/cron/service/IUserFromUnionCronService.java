package cn.vfunding.vfunding.biz.cron.service;

import cn.vfunding.common.framework.utils.cron.annotation.CronInfoAnnotation;

public interface IUserFromUnionCronService {

	/**
	 * 更新来源于广告的用户的返利期数
	 */
	@CronInfoAnnotation(className="cn.vfunding.vfunding.biz.cron.service.impl.UserFromUnionCronServiceImpl",methodName="updatePeriods",cronName="定时更新来源于广告的用户的返利期数",cronRemark="定时更新来源于广告的用户的返利期数",systemName="网站联盟",systemType="vfunding-vfunding")
	String updatePeriods();
	
}
