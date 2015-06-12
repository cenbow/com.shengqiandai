package cn.vfunding.union.plat.mq.receiver;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.http.RestInvokerFactory;
import cn.vfunding.common.service.cron.model.CronInfo;
import cn.vfunding.common.service.cron.util.CronUtil;
import cn.vfunding.union.plat.mq.model.ProfitStatisticsVO;
import cn.vfunding.union.plat.mq.service.IProfitRecordInfoService;

@Component("profitRecordInfoCron")
public class ProfitRecordInfoCron {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${vfunding.union.profitStatisticsTime}")
	private String profitStatisticsTime;

	@Resource(name = "cronRestInvokerFactory")
	private RestInvokerFactory cronRestInvokerFactory;
	@Autowired
	private IProfitRecordInfoService profitRecordInfoService;

	/***
	 * 从详细表查询统计信息并保存的调度任务
	 */
	public void createAndSaveStatistics() {
		try {
			CronInfo info = CronUtil.createCronInfo(
					"cn.vfunding.union.plat.mq.receiver.ProfitRecordInfoCron",
					"createAndSaveStatistics", "广告联盟月统计调度任务", "广告联盟月统计调度任务",
					profitStatisticsTime, "广告联盟月统计");
			ProfitStatisticsVO vo = new ProfitStatisticsVO(
					DateUtil.getPreviousMonthFirst(),
					DateUtil.getPreviousMonthEnd());
			try {
				this.profitRecordInfoService.selectAndSaveMouthData(vo);
			
				info.setEndTime(new Date());
				info.setExecuteStatus("success");
			} catch (Exception e) {
				info.setExecuteStatus("faild");
				logger.error("广告联盟月统计调度任务失败：" + e.getMessage());
			}
			// this.cronRestInvokerFactory.getRestInvoker().post("/addCronInfo",
			// info);
		} catch (Exception e) {
			logger.error("广告联盟月统计调度任务失败：" + e.getMessage());
		}
	}
}
