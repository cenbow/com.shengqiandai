package cn.vfunding.vfunding.plat.mq.useration.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.vfunding.biz.message.model.GiftboxMessage;
import cn.vfunding.vfunding.biz.message.service.IGiftboxMessageService;
import cn.vfunding.vfunding.biz.system.service.IJmsLogService;
import cn.vfunding.vfunding.common.module.activemq.message.afteraction.AfterActionMessage;

import com.alibaba.fastjson.JSON;

/**
 * 用户成功投资后相关业务处理服务类
 * 
 * @author liuyijun
 * 
 */
@Component
public class GiftBoxActionService {

	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("actionlog");

	@Autowired
	private UserActionSynchroDataService synchroDataService;
	@Autowired
	private IJmsLogService mapper;
	@Autowired
	private IGiftboxMessageService boxMessageService;

	@Async
	//@NeedLock("/locks/useraction/doGiftbox")
	public  void doGiftbox(AfterActionMessage msg) {
		//synchronized(JmsLogUtil.lock){
			try {
				Json j = JSON.parseObject(msg.getMethodReturn().toString(),
						Json.class);
				Integer boxId=Integer.parseInt(msg.getMethodArgs()[0].toString());
				Integer loginUserId = Integer.parseInt(msg.getMethodArgs()[1]
						.toString());
				GiftboxMessage gm = this.boxMessageService.selectByPrimaryKey(boxId);
				this.doGiftboxBiz(j, gm, loginUserId, msg.getActionName());
				JmsLogUtil.doLog(mapper, msg.getMessageId(), true);
				logger.info("*****[system " + "giftboxId:" + gm.getId()
						+ " 礼品盒使用MQ消息处理成功]");
			} catch (Exception e) {
				logger.error(e.getMessage());
				logger.error(e.getStackTrace().toString());
				JmsLogUtil.doLog(mapper, msg.getMessageId(), false);
			}
		//}
	}

	/**
	 * 礼品盒业务
	 * 
	 * @param j
	 * @param gm
	 * @param loginUserId
	 * @param actionName
	 */
	private void doGiftboxBiz(Json j, GiftboxMessage gm, Integer loginUserId,
			String actionName) {
		if (j.getSuccess()) {
			logger.info("*****[sina " + "giftboxId:" + gm.getId()
					+ " 礼品盒使用红包同步 开始]");
			synchroDataService.doSynchroData(gm, "/sinaSendAction/doUseGift",
					actionName, "sina", loginUserId);
			logger.info("*****[sina " + "giftboxId:" + gm.getId()
					+ " 礼品盒使用红包同步 结束]");
		} else {
			logger.info("*****[system " + "giftboxId:" + gm.getId()
					+ " 礼品盒 失败]:[操作失败无需同步数据]");
		}
	}
	

}
