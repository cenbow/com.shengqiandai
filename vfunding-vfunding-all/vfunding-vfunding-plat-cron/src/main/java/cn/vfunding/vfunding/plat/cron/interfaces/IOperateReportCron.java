package cn.vfunding.vfunding.plat.cron.interfaces;

import cn.vfunding.common.framework.utils.cron.annotation.CronInfoAnnotation;

public interface IOperateReportCron {
	@CronInfoAnnotation(className = "cn.vfunding.vfunding.plat.cron.impl.OperateReportCron", methodName = "callProcedure", cronName = "定时生成运营日报", cronRemark = "定时生成运营日报", systemName = "微积金运营日报", systemType = "vfunding-vfunding",
	reStartPath = "/operate/reStartCallProcedure")
	String callProcedure();
}
