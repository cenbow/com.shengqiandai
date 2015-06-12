package cn.vfunding.vfunding.plat.cron.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.vfunding.vfunding.biz.report.service.IOperateReportdaysService;
import cn.vfunding.vfunding.plat.cron.interfaces.IOperateReportCron;

@Component("operateReportCron")
public class OperateReportCron implements IOperateReportCron {

	@Autowired
	private IOperateReportdaysService orService;
	@Value("${operate.report.execute.time}")
	private String callProcedureTime;

	@Override
	public String callProcedure() {
		try {
			this.orService.callProcedure();
			this.orService.callProcedureTwo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return callProcedureTime;
	}


}
