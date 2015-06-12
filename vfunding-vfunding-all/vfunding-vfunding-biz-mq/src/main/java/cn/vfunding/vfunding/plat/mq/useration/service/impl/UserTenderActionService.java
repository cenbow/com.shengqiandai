package cn.vfunding.vfunding.plat.mq.useration.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.utils.beans.EncryptionUtil;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
import cn.vfunding.vfunding.biz.system.service.IJmsLogService;
import cn.vfunding.vfunding.biz.week.service.IWeekTenderService;
import cn.vfunding.vfunding.common.module.activemq.message.afteraction.AfterActionMessage;

import com.alibaba.fastjson.JSON;

/**
 * 用户成功投资后相关业务处理服务类
 * 
 * @author liuyijun
 * 
 */
@Component
public class UserTenderActionService {

	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("actionlog");

	@Autowired
	private IBorrowTenderService tenderService;

	@Autowired
	private IJmsLogService mapper;

	@Autowired
	private IWeekTenderService weekTenderService;
	@Autowired
	private UserActionSynchroDataService synchroDataService;

	/**
	 * 用户成功投资后相关辅助业务及第三方平台数据同步
	 * 
	 * @param vo
	 *            成功投资的返回对象 2014年12月31日 liuyijun
	 */
	@Async
	public void doUserTender(AfterActionMessage msg) {
			try {
				UserTenderActionResultVO vo = JSON.parseObject(msg
						.getMethodReturn().toString(),
						UserTenderActionResultVO.class);

				if (vo.getTenderType().equals("normal")) {
					this.doUserTenderBiz(vo, msg.getActionName());
				} else if (vo.getTenderType().equals("week")) {
					this.doUserTenderWeekBiz(vo, msg.getActionName());
				}
				JmsLogUtil.doLog(mapper, msg.getMessageId(), true);
				logger.info("*****[system " + "userId:" + vo.getUserId() + "|tenderId:"
						+ vo.getTenderId() + " 用户投资MQ消息处理成功]");
			} catch (Exception e) {
				logger.error(e.getMessage());
				logger.error(e.getStackTrace().toString());
				logger.error(e.getMessage());
				JmsLogUtil.doLog(mapper, msg.getMessageId(), false);
			}
	}

	public void doUserTenderBiz(UserTenderActionResultVO vo, String actionName) {
		// 投资辅助业务处理,该方法一定要放在第一个执行，因为该方法会给vo对象设置tenderId信息
		logger.info("*****[system " + "userId:" + vo.getUserId() + "|tenderId:"
				+ vo.getTenderId() + " 投资辅助业务 开始]");
		this.tenderService.userTenderAfter(vo);
		logger.info("*****[system " + "userId:" + vo.getUserId() + "|tenderId:"
				+ vo.getTenderId() + " 投资辅助业务 结束]");
		// 用户投资相关活动处理
		logger.info("*****[activity " + "userId:" + vo.getUserId()
				+ "|tenderId:" + vo.getTenderId() + " 投资相关活动 开始]");
		synchroDataService.doSynchroData(vo, "/activityUserAction/userTender",
				actionName, "activity", vo.getUserId());
		logger.info("*****[activity " + "userId:" + vo.getUserId()
				+ "|tenderId:" + vo.getTenderId() + " 投资相关活动 结束]");
		// 新浪用户账户信息数据同步
		logger.info("*****[sina " + "userId:" + vo.getUserId() + "|tenderId:"
				+ vo.getTenderId() + " 投资同步 开始]");
		synchroDataService.doSynchroData(vo, "/sinaSendAction/doBorrowTender",
				"borrowTender", "sina", vo.getUserId());
		logger.info("*****[sina " + "userId:" + vo.getUserId() + "|tenderId:"
				+ vo.getTenderId() + " 投资同步 结束]");
		// 财经道数据同步业务
		logger.info("*****[cjd " + "userId:" + vo.getUserId() + "|tenderId:"
				+ vo.getTenderId() + " 投资同步 开始]");
		synchroDataService.doSynchroData(vo, "/cjdUserAction/userTender",
				actionName, "cjd", vo.getUserId());
		logger.info("*****[cjd " + "userId:" + vo.getUserId() + "|tenderId:"
				+ vo.getTenderId() + " 投资同步 结束]");
	}

	/**
	 * 7天理财产品投资
	 * 
	 * @param vo
	 * @param rvo
	 *            2014年12月31日 liuyijun
	 */

	public void doUserTenderWeekBiz(UserTenderActionResultVO vo,
			String actionName) {
		logger.info("*****[system " + "userId:" + vo.getUserId() + "|tenderId:"
				+ vo.getTenderId() + " 7天理财投资辅助表添加 开始]");
		this.weekTenderService.weekTenderAssist(vo);
		logger.info("*****[system " + "userId:" + vo.getUserId() + "|tenderId:"
				+ vo.getTenderId() + " 7天理财投资辅助表添加 结束]");
		// 新浪用户账户信息数据同步
		logger.info("*****[sina " + "userId:" + vo.getUserId() + "|tenderId:"
				+ vo.getTenderId() + " 7天理财投资同步 开始]");
		synchroDataService.doSynchroData(vo, "/sinaSendAction/doBorrowTender",
				"borrowTender", "sina", vo.getUserId());
		logger.info("*****[sina " + "userId:" + vo.getUserId() + "|tenderId:"
				+ vo.getTenderId() + " 7天理财投资同步 结束]");
		// 财经道数据同步业务
		logger.info("*****[cjd " + "userId:" + vo.getUserId() + "|tenderId:"
				+ vo.getTenderId() + " 7天理财投资同步 开始]");
		synchroDataService.doSynchroData(vo, "/cjdUserAction/userTenderWeek",
				actionName, "cjd", vo.getUserId());
		logger.info("*****[cjd " + "userId:" + vo.getUserId() + "|tenderId:"
				+ vo.getTenderId() + " 7天理财投资同步 结束]");
	}
	
	
	public static void main(String[] args) {
		String a="39897234D93A0746AAAE4F22B770DB4D05B048B020A2713D7382850FAEC915C204B76E5B68D9B7DB282E3DA29444E8ADC1620AD87E96A003BB2A4CC99B0DA05E4DA076DACEA4632D942801E2A2019198917D04CA6444B0E4226401BE26CEBC60736F9738236D53F96E08DC35122BA786E9AC8C454FA28585F73FFD9BB0D54F1C298B022009BD63078A2C457B2E0B96C54795931022C04BACD97821EE9896A4FFAA4690177D0E28AB3F19D2F924338255AFAFD835F5700DDBBC091C9DA2E4C6CC3ADCD6DA900213D0";
		String b=EncryptionUtil.decrypt(a);
		System.out.println(b);
	}

}
