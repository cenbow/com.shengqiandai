package cn.vfunding.vfunding.plat.cron.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.vfunding.biz.cron.service.IAgainLogCronService;
import cn.vfunding.vfunding.biz.cron.service.IBorrowCronService;
import cn.vfunding.vfunding.biz.cron.service.IFinancialPlannerCronService;
import cn.vfunding.vfunding.biz.cron.service.IGiftboxHongbaoCronService;
import cn.vfunding.vfunding.biz.cron.service.ISinaBonusCronService;
import cn.vfunding.vfunding.biz.cron.service.IUserTrackCronService;
import cn.vfunding.vfunding.biz.cron.service.IUserUnLockService;
import cn.vfunding.vfunding.biz.current.service.ICurrentCronService;
import cn.vfunding.vfunding.biz.mq.service.IMercenaryService;
import cn.vfunding.vfunding.biz.week.service.IWeekCronService;
import cn.vfunding.vfunding.common.module.activemq.message.MercenaryMessage;
import cn.vfunding.vfunding.plat.cron.interfaces.IOperateReportCron;
import cn.vfunding.vfunding.plat.cron.interfaces.IThirdSynRecordCronService;
import cn.vfunding.vfunding.plat.cron.interfaces.IUserReportAndSendEmailCron;

@Controller
@RequestMapping("/cron")
public class RestartCronController extends BaseController {

	@Resource(name = "userTrackServiceProxy")
	private IUserTrackCronService cronService;

	@Resource(name = "userUnLockServiceProxy")
	private IUserUnLockService unlockService;

	@Resource(name = "financialPlannerServiceProxy")
	private IFinancialPlannerCronService FinancialPlannerCronService;

	@Resource(name = "currentCronServiceProxy")
	private ICurrentCronService currentCronService;

	@Resource(name = "operateReportCronProxy")
	private IOperateReportCron operateReportService;

	@Resource(name = "userReportAndSendEmaiCronProxy")
	private IUserReportAndSendEmailCron userReportAndSendEmailService;

	@Resource(name = "borrowServiceProxy")
	private IBorrowCronService borrowCronService;

	@Resource(name = "thirdSynRecordProxy")
	private IThirdSynRecordCronService thirdSynRecordCronService;

	@Resource(name = "weekCronProxy")
	private IWeekCronService weekCronService;
	@Autowired
	@Qualifier("userActionAgainLogProxy")
	private IAgainLogCronService againLogCronService;

	@Resource(name = "giftboxHongbaoCronProxy")
	private IGiftboxHongbaoCronService giftboxHongbaoCronService;
	
	@Resource(name = "sinaBonusCronProxy")
	private ISinaBonusCronService sinaBonusCronService;
	
	@RequestMapping("/userTrack/reStartOutUser")
	@ResponseBody
	public Json reStartOutUser() {
		Json j = new Json();
		this.cronService.outUserService();
		j.setSuccess(true);
		return j;
	}

	@RequestMapping("/userUnlock/reStartUnlock")
	@ResponseBody
	public Json reStartUnlock() {
		Json j = new Json();
		this.unlockService.deleteUserLock();
		j.setSuccess(true);
		return j;
	}

	@RequestMapping("/financialPlanner/reStartFinancialPlanner")
	@ResponseBody
	public Json reStartFinancialPlanner() {
		Json j = new Json();
		this.FinancialPlannerCronService.selectUserFinancialplanner();
		j.setSuccess(true);
		return j;
	}

	@RequestMapping("/current/reStartUpdateAllCurrentInfo")
	@ResponseBody
	public Json reStartUpdateAllCurrentInfo() {
		Json j = new Json();
		this.currentCronService.updateAllCurrentInfo();
		j.setSuccess(true);
		return j;
	}

	@RequestMapping("/operate/reStartCallProcedure")
	@ResponseBody
	public Json reStartCallProcedure() {
		Json j = new Json();
		this.operateReportService.callProcedure();
		j.setSuccess(true);
		return j;
	}

	@RequestMapping("/userReport/reStartUserReportAndSendEmail")
	@ResponseBody
	public Json reStartUserReportAndSendEmail() {
		Json j = new Json();
		this.userReportAndSendEmailService.userReportAndSendEmail();
		j.setSuccess(true);
		return j;
	}

	@RequestMapping("/borrow/reStartFlowBorrow")
	@ResponseBody
	public Json reStartFlowBorrow() {
		Json j = new Json();
		this.borrowCronService.flowBorrow();
		j.setSuccess(true);
		return j;
	}

	@RequestMapping("/borrow/reStartWillRepayBorrow")
	@ResponseBody
	public Json reStartWillRepayBorrow() {
		Json j = new Json();
		this.borrowCronService.willRepayBorrow();
		j.setSuccess(true);
		return j;
	}

	@RequestMapping("/thirdSynRecord/reStartThirdSynRecord")
	@ResponseBody
	public Json reStartThirdSynRecord() {
		Json j = new Json();
		this.thirdSynRecordCronService.thirdSysRecord();
		j.setSuccess(true);
		return j;
	}

	@RequestMapping("/week/reStartUpdateWeekInfo")
	@ResponseBody
	public Json reStartUpdateWeekInfo() {
		Json j = new Json();
		this.weekCronService.updateWeekInfo();
		j.setSuccess(true);
		return j;
	}

	@Autowired
	private IMercenaryService mercenaryServiceImpl;

	@RequestMapping("/mercenary/reStartInsertMercenaryInterest")
	@ResponseBody
	public Json reStartInsertMercenaryInterest(MercenaryMessage mercenaryMessage) {
		Json j = new Json();
		this.mercenaryServiceImpl.insertMercenaryInterest(mercenaryMessage);
		j.setSuccess(true);
		return j;
	}
	
	@RequestMapping("/againLog/retryRequest")
	@ResponseBody
	public Json retryRequest() {
		Json j = new Json();
		this.againLogCronService.retryRequest();
		j.setSuccess(true);
		return j;
	}
	
	@RequestMapping("/sina/retrySinaBonusCreate")
	@ResponseBody
	public Json retrySinaBonusCreate(){
		Json j = new Json();
		this.sinaBonusCronService.loadCreate();
		j.setSuccess(true);
		return j;
	}
	
	@RequestMapping("/sina/retrySinaBonusSync")
	@ResponseBody
	public Json retrySinaBonusSync(){
		Json j = new Json();
		this.sinaBonusCronService.loadSync();
		j.setSuccess(true);
		return j;
	}
	

}
