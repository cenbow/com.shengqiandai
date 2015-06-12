package cn.vfunding.vfunding.plat.cron.interfaces;

import cn.vfunding.common.framework.utils.cron.annotation.CronInfoAnnotation;

public interface IThirdSynRecordCronService {

	/**
	 * 第三发合作同步数据
	 * 
	 * 2014年10月8日
	 * liuyijun
	 */
	@CronInfoAnnotation(className="cn.vfunding.vfunding.plat.cron.impl.ThirdSynRecordCronServiceImpl",methodName="thirdSysRecord",cronName="第三方合作用户信息同步",cronRemark="第三方合作用户信息同步",systemName="微积金第三方合作"
			,systemType="vfunding-vfunding",reStartPath="/cron/thirdSynRecord/reStartThirdSynRecord")
	String thirdSysRecord();
}
