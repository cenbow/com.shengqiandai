package cn.vfunding.vfunding.biz.cron.service;

import cn.vfunding.common.framework.utils.cron.annotation.CronInfoAnnotation;

public interface IGiftboxHongbaoCronService {
	
	/**
	 * 扫描过期红包并更新状态=2
	 * @return
	 * @author louchen 2015-02-06
	 */
	@CronInfoAnnotation(className="cn.vfunding.vfunding.biz.cron.service.impl.GiftboxHongbaoCronService",methodName="updateGiftboxHongbaoExpireStatus",
			cronName = "扫描过期红包", cronRemark = "扫描过期红包", systemName = "扫描过期红包",
			systemType = "vfunding-vfunding",reStartPath="/cron/giftbox/retryGiftHongbao")
	String updateGiftboxHongbaoExpireStatus();
	
	/**
	 * 用户红包过期提醒
	 * @return
	 */
	@CronInfoAnnotation(className="cn.vfunding.vfunding.biz.cron.service.impl.GiftboxHongbaoCronService",methodName="sendMessageByGiftboxHongbaoExpire",
			cronName = "用户红包过期提醒", cronRemark = "用户红包过期提醒", systemName = "用户红包过期提醒",
			systemType = "vfunding-vfunding",reStartPath="/cron/giftbox/remindGiftHongbao")
	String sendMessageByGiftboxHongbaoExpire();
	
	
}
