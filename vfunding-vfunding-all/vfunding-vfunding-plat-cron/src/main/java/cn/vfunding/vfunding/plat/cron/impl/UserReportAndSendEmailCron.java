package cn.vfunding.vfunding.plat.cron.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.vfunding.vfunding.biz.pdfreport.service.ISendEmailUserPdfService;
import cn.vfunding.vfunding.biz.pdfreport.service.IUserPdfFileService;
import cn.vfunding.vfunding.biz.pdfreport.service.IUserUsefundsPieService;
import cn.vfunding.vfunding.plat.cron.interfaces.IUserReportAndSendEmailCron;

@Component("userReportAndSendEmaiCron")
public class UserReportAndSendEmailCron implements IUserReportAndSendEmailCron {
	@Value("${user.report.sendeamail.execute.time}")
	private String executeTime;

	@Value("${user.report.sendeamail.sendemailstatus}")
	private Integer sendEmailStatus;

	@Value("${user.report.createPdfstatus}")
	private Integer createPdfstatus;

	@Autowired
	private IUserUsefundsPieService pieService;

	@Autowired
	private IUserPdfFileService pdfService;

	@Autowired
	private ISendEmailUserPdfService sendMailService;

	@Override
	public String userReportAndSendEmail() {
		if (createPdfstatus == 1) {
			this.pieService.selectUserUsefundsPie();
			this.pdfService.createPdfFile();
		}
		if (sendEmailStatus == 1) {
			this.sendMailService.SendEmailUserPdf();
		}
		return executeTime;
	}

}
