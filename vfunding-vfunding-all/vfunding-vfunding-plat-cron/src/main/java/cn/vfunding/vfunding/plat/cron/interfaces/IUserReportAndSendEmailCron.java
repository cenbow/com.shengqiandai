package cn.vfunding.vfunding.plat.cron.interfaces;

import cn.vfunding.common.framework.utils.cron.annotation.CronInfoAnnotation;

public interface IUserReportAndSendEmailCron {
	@CronInfoAnnotation(className = "cn.vfunding.vfunding.plat.cron.impl.UserReportAndSendEmailCron", methodName = "userReportAndSendEmail", cronName = "定时发送用户投资理财报告pdf文件", cronRemark = "定时发送用户投资理财报告pdf文件", systemName = "微积金投资理财报告", systemType = "vfunding-vfunding", reStartPath = "/userReport/reStartUserReportAndSendEmail")
	String userReportAndSendEmail();
}
