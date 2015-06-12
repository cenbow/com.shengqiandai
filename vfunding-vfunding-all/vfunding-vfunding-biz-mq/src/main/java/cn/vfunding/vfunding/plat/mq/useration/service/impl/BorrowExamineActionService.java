package cn.vfunding.vfunding.plat.mq.useration.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.vfunding.biz.account.dao.AccountMapper;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowAutoMapper;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowMapper;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.BorrowAuto;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionVO;
import cn.vfunding.vfunding.biz.system.service.IJmsLogService;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.week.model.Week;
import cn.vfunding.vfunding.common.module.activemq.message.afteraction.AfterActionMessage;

import com.alibaba.fastjson.JSON;

/**
 * 用户成功投资后相关业务处理服务类
 * 
 * @author liuyijun
 * 
 */
@Component
public class BorrowExamineActionService {

	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("actionlog");

	@Autowired
	private IBorrowTenderService tenderService;

	@Autowired
	private BorrowMapper borrowMapper;

	@Autowired
	private BorrowAutoMapper borrowAutoMapper;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private IBorrowTenderService borrowTenderService;

	@Autowired
	private UserActionSynchroDataService synchroDataService;

	@Autowired
	private UserTenderActionService userTenderActionService;

	@Autowired
	private IJmsLogService mapper;

	@Async
	public void doBorrowExamine(AfterActionMessage msg) {
		try {
			Integer r = Integer.parseInt(msg.getMethodReturn().toString());
			Borrow borrow = JSON.parseObject(msg.getMethodArgs()[0].toString(),
					Borrow.class);
			if (r == 1) {// 初审成功
				this.doBorrowExamineMethod(borrow, msg.getActionName());
			} else {
				logger.info("*****[system " + borrow.getId()
						+ " 初审 失败 useractionmq无需处理]");
			}
			JmsLogUtil.doLog(mapper, msg.getMessageId(), true);
			logger.info("*****[system  " + borrow.getId() + " 标的初审MQ消息业务处理成功]");
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
			e.printStackTrace();
			JmsLogUtil.doLog(mapper, msg.getMessageId(), false);
		}
	}

	private void doBorrowExamineMethod(Borrow borrow, String actionName) {
		// 自动投标处理
		this.insertBorrowAutoUserId(borrow.getId());
		// 发标初审通过后财经道数据同步业务处理
		this.doBorrowExamineBiz(borrow, actionName);
	}

	@Async
	public void doWeekExamine(AfterActionMessage msg) {
		try {
			Json j = JSON.parseObject(msg.getMethodReturn().toString(),
					Json.class);
			Week week = JSON.parseObject(msg.getMethodArgs()[0].toString(),
					Week.class);
			String subType = msg.getMethodArgs()[2].toString();
			if (j.getSuccess()) {
				// 同步财经道标的信息
				this.doWeekExamineBiz(week, msg.getActionName(), subType);
			} else {
				logger.info("*****[system  " + week.getId() + " 7天理财发标 失败]");
			}
			JmsLogUtil.doLog(mapper, msg.getMessageId(), true);
			logger.info("*****[system  " + week.getId()
					+ " 7天理财标的初审MQ消息业务处理成功]");
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
			e.printStackTrace();
			JmsLogUtil.doLog(mapper, msg.getMessageId(), false);
		}

	}

	/**
	 * 周盈宝发标
	 * 
	 * @param week
	 * @param actionName
	 *            2015年1月16日 liuyijun
	 */
	private void doWeekExamineBiz(Week week, String actionName, String subType) {
		if (subType.equals("ok")) {
			// 周盈宝发标财经道同步数据
			synchroDataService.doSynchroData(week,
					"/cjdUserAction/weekExamine", actionName, "cjd", 0);
		} else {
			logger.info("*****[cjd  " + week.getId() + " 7天理财发标 失败]");
		}
	}

	/**
	 * 初审
	 * 
	 * @param borrow
	 * @param actionName
	 *            2015年1月16日 liuyijun
	 */
	private void doBorrowExamineBiz(Borrow borrow, String actionName) {
		// 初审财经道同步数据
		synchroDataService.doSynchroData(borrow,
				"/cjdUserAction/borrowExamine", actionName, "cjd", 0);
	}

	private void insertBorrowAutoUserId(Integer borrowId) {
		Borrow borrow = this.borrowMapper.selectBorrowById(borrowId);
		List<BorrowAuto> List = this.borrowAutoMapper
				.selectBorrowAutoUserId(borrow);
		Account useraccount = null;
		Double useMoney = 0d;
		User user = null;
		UserTenderActionVO vo;
		UserTenderActionResultVO result;
		for (BorrowAuto borrowAuto : List) {
			Integer userId = borrowAuto.getUserId();
			if (userId != borrow.getUserId()
					&& !borrow.getBiaoType().equals("huodong")) {
				Integer tenderMoney = borrowAuto.getTenderAccount();
				useraccount = this.accountMapper.selectByUserId(userId);
				useMoney = useraccount.getUseMoney().doubleValue();
				if (useMoney >= borrow.getLowestAccount().doubleValue()
						&& tenderMoney >= 50) {
					try {
						user = this.userMapper.selectByPrimaryKey(userId);
						Integer userType = user.getTypeId();
						vo = new UserTenderActionVO();
						vo.setUserId(userId);
						vo.setBorrowId(borrowId);
						vo.setUserType(userType);
						vo.setPayMoney(new BigDecimal(tenderMoney));
						vo.setUserip("borrowAuto");
						result = this.borrowTenderService.userTenderAction(vo);
						if (result.getTenderType().equals("normal")) {
							userTenderActionService.doUserTenderBiz(result,
									"borrowTender");
						} else if (vo.getTenderType().equals("week")) {
							userTenderActionService.doUserTenderWeekBiz(result,
									"borrowTender");
						}
					} catch (Exception e) {
						e.printStackTrace();
						logger.error(e.getMessage());
						logger.error(e.getStackTrace().toString());
					}
				}
			}
		}
	}

}
