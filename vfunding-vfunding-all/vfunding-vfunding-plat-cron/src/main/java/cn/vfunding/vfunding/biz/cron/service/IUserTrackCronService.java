package cn.vfunding.vfunding.biz.cron.service;

import cn.vfunding.common.framework.utils.cron.annotation.CronInfoAnnotation;

public interface IUserTrackCronService {

	/**
	 * 用户自动退出定时服务
	 */
	@CronInfoAnnotation(className="cn.vfunding.vfunding.biz.cron.service.impl.UserTrackCronServiceImpl",methodName="outUserService",cronName="定时更新微积金网站用户自动退出服务",cronRemark="定时更新微积金网站用户自动退出服务",systemName="微积金网站"
			,systemType="vfunding-vfunding",reStartPath="/cron/userTrack/reStartOutUser")
	String outUserService();
}
