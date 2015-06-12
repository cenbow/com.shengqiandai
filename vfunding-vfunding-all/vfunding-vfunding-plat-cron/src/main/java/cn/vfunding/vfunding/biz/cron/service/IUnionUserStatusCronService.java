package cn.vfunding.vfunding.biz.cron.service;

import cn.vfunding.common.framework.utils.cron.annotation.CronInfoAnnotation;

public interface IUnionUserStatusCronService {

	/**
	 * 终止网站联盟用户结算状态
	 */
	@CronInfoAnnotation(className="cn.vfunding.vfunding.biz.cron.service.impl.UnionUserStatusServiceImpl",methodName="stopUnionUser",cronName="定时更新网站联盟用户的可分利状态",cronRemark="定时更新网站联盟用户的可分利状态",systemName="网站联盟",systemType="vfunding-vfunding")
	String stopUnionUser();

	/**
	 * 复活网站联盟用户结算状态
	 */
	@CronInfoAnnotation(className="cn.vfunding.vfunding.biz.cron.service.impl.UnionUserStatusServiceImpl",methodName="reviveUnionUser",cronName="定时更新复活网站联盟用户结算状态",cronRemark="定时更新复活网站联盟用户结算状态",systemName="网站联盟",systemType="vfunding-vfunding")
	String reviveUnionUser();

	/**
	 * 复活网站联盟用户结算状态且将以前所介绍的用户设置成不进行分利状态，以新介绍的用户进行分利计算，即：
	 * 当网站联盟用户复活了2次以后再次复活，
	 * 那么他复活前所介绍的用户就不能给他分利了，只有复活后的用户才可以给他分利
	 */
	@CronInfoAnnotation(className="cn.vfunding.vfunding.biz.cron.service.impl.UnionUserStatusServiceImpl",methodName="clearOldUserAndRevive",cronName="定时更新网站联盟用户清零复活",cronRemark="定时更新网站联盟用户清零复活",systemName="网站联盟",systemType="vfunding-vfunding")
	String clearOldUserAndRevive();
}
