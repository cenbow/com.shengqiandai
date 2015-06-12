package cn.vfunding.vfunding.biz.useraction_activity.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.http.HttpRequester;
import cn.vfunding.vfunding.biz.achievement.model.AchievementLog;
import cn.vfunding.vfunding.biz.achievement.service.IAchievementLogService;
import cn.vfunding.vfunding.biz.activity.service.IActivityLotteryService;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowCollectionMapper;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowTenderMapper;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.BorrowCollection;
import cn.vfunding.vfunding.biz.borrow.model.BorrowTender;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.common.vo.FinalVerifyVO;
import cn.vfunding.vfunding.biz.common.vo.RegisterVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
import cn.vfunding.vfunding.biz.hikes.dao.HikesCardMapper;
import cn.vfunding.vfunding.biz.hikes.model.HikesCard;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.message.dao.GiftboxMessageMapper;
import cn.vfunding.vfunding.biz.message.dao.GiftotherMessageMapper;
import cn.vfunding.vfunding.biz.message.dao.HikesMessageMapper;
import cn.vfunding.vfunding.biz.message.dao.SystemMessageMapper;
import cn.vfunding.vfunding.biz.message.model.GiftboxMessage;
import cn.vfunding.vfunding.biz.message.model.GiftotherMessage;
import cn.vfunding.vfunding.biz.message.model.HikesMessage;
import cn.vfunding.vfunding.biz.message.model.SystemMessage;
import cn.vfunding.vfunding.biz.mq.service.IUserTaskService;
import cn.vfunding.vfunding.biz.newyears.dao.ActivityNewyearMapper;
import cn.vfunding.vfunding.biz.newyears.model.ActivityNewyear;
import cn.vfunding.vfunding.biz.prize.service.IPrizeService;
import cn.vfunding.vfunding.biz.system.dao.InvestorsFeeMapper;
import cn.vfunding.vfunding.biz.system.model.InvestorsFee;
import cn.vfunding.vfunding.biz.system.service.ITaskService;
import cn.vfunding.vfunding.biz.thirdplat.model.JjCard;
import cn.vfunding.vfunding.biz.thirdplat.service.IJjCardService;
import cn.vfunding.vfunding.biz.user.dao.UserFromDictionaryMapper;
import cn.vfunding.vfunding.biz.user.dao.UserFromMapper;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.model.UserFrom;
import cn.vfunding.vfunding.biz.user.model.UserFromDictionary;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.biz.userFrom.model.UserFromDic;
import cn.vfunding.vfunding.biz.userFrom.service.IUserFromDicService;
import cn.vfunding.vfunding.biz.useraction_activity.service.IUserActionService;

@Service("userActionService")
public class UserActionServiceImpl implements IUserActionService {

	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("activitylog");
	@Autowired
	private IUserService userService;

	@Autowired
	private ITaskService taskService;

	@Autowired
	private IBorrowTenderService tenderService;

	@Autowired
	private IPrizeService prizeService;

	@Autowired
	private ActivityNewyearMapper activityNewyearMapper;
	@Autowired
	private UserFromMapper userFromMapper;
	@Autowired
	private UserFromDictionaryMapper userFromDictionaryMapper;
	@Autowired
	private SystemMessageMapper systemMessageMapper;
	@Autowired
	private GiftboxMessageMapper giftboxMessageMapper;
	@Autowired
	private GiftotherMessageMapper giftotherMessageMapper;
	@Autowired
	private IJjCardService jjCardService;

	@Autowired
	private HikesMessageMapper hikesMessageMapper;

	@Autowired
	private HikesCardMapper hikesCardMapper;

	@Value("${Season2TurnplateStart}")
	private String season2TurnplateStart;
	
	@Value("${Season2TurnplateEnd}")
	private String season2TurnplateEnd;
	

	@Value("${JiuXianActivityStart}")
	private String jiuXianActivityStart;
	
	@Value("${JiuXianActivityEnd}")
	private String jiuXianActivityEnd;
	

	
	@Autowired
	private BorrowTenderMapper tenderMapper;

	@Autowired
	private IUserTaskService userTaskService;
	
	@Autowired
	private IBorrowService borrowService;
	
	@Autowired
	private InvestorsFeeMapper investorsFeeMapper;

	@Autowired
	private BorrowCollectionMapper borrowCollectionMapper;
	
	@Autowired
	private IAchievementLogService achievementLogService;
	
	@Autowired
	private IActivityLotteryService activityLotteryService;
	
	@Autowired
	private IUserFromDicService userFromDicService;
	
	
	@Autowired
	private JmsSenderService jmsSenderUtil;
	/**
	 * 用户注册后相关活动
	 */
	@Override
	public String doRegisterBiz(RegisterVO vo) {
		String result = "faild";
		// 注册收红包处理
		this.userTaskService.insertUserTaskInfo(vo.getRegisterUserId(), 1);
		// 用户来源处理
		this.insertUserFrom(vo);
		// 2015破两亿活动注册上线得红包
		this.registerVoteHongbao(vo);
		result = "success";
		return result;
	}

	@Override
	public String doUserTenderBiz(UserTenderActionResultVO vo) {
		String result = "faild";
		// 新手任务中投资的相关活动处理
		this.firstTenderActivity(vo);
		// 用户首次APP投资相关活动
		this.firstAppTenderActivity(vo);
		// 自动投标相关活动
		this.autoTenderActivity(vo);
		// 第二季抽奖活动期间(带你赚钱你带飞)投资有效金额大于10000元，抽奖次数+1(并且在活动有效期内)
		this.prizeForAddTime(vo);
		// 2015年4月活动投资相关
		this.tenderForAprilActivity(vo);
		// 2015年酒仙网活动
		this.tenderForJiuXian(vo);
		
		result = "success";
		return result;
	}
	
	/**
	 * 邮箱验证活动
	 */
	@Override
	public String doEmailVerifyBiz(User record) {
		String result = "faild";
		logger.info("*****[system " + record.getUserId() + " 验证邮箱相关活动 开始]");
		this.userTaskService.insertUserTaskInfo(record.getUserId(), 3);
		result = "success";
		logger.info("*****[system " + record.getUserId() + " 验证邮箱相关活动 结束]");
		return result;
	}

	/**
	 * 实名认证活动
	 */
	@Override
	public String doRealNameVerifyBiz(User record) {
		String result = "faild";
		logger.info("*****[system " + record.getUserId() + " 实名认证相关活动 开始]");
		this.userTaskService.insertUserTaskInfo(record.getUserId(), 5);
		// 理财师活动，被邀请人实名后获得2%加息
		this.realnameBase(record);
		// 2015年破两亿活动, 实名送上线红包,送自己30m流量
		this.realnameVoteHongbao(record.getUserId());
		// 2015年酒仙网活动，实名后送30m流量包
		this.realnameJiuXian(record);
		
		result = "success";
		logger.info("*****[system " + record.getUserId() + " 实名认证相关活动 结束]");
		return result;
	}

	/**
	 * 自动投标相关活动
	 * 
	 * @param vo
	 *            2015年1月19日 liuyijun
	 */
	private void autoTenderActivity(UserTenderActionResultVO vo) {
		Integer userId = vo.getUserId();
		logger.info("*****[system " + userId + " 自动投标相关活动 开始]");
		if (vo.getUserip().equals("borrowAuto")
				&& (vo.getStatus() == 1 || vo.getStatus() == 2)) {
			this.userTaskService.insertUserTaskInfo(vo.getUserId(), 7);
		}
		logger.info("*****[system " + userId + " 自动投标相关活动 结束]");
	}

	/**
	 * 用户首次APP投资相关活动
	 * 
	 * @param vo
	 *            2015年1月19日 liuyijun
	 */
	private void firstAppTenderActivity(UserTenderActionResultVO vo) {
		Integer userId = vo.getUserId();
		logger.info("*****[system " + userId + " app投资送提现券 开始]");
		if (vo.getUserip().equals("app") || vo.getUserip().equals("ios") || vo.getUserip().equals("android")
				&& vo.getPayMoney().doubleValue() >= 2000) {
			this.userTaskService.insertUserTaskInfo(vo.getUserId(), 6);
		}
		logger.info("*****[system " + userId + " app投资送提现券 结束]");
	}

	/**
	 * 新手第一次投资活动
	 * 
	 * 2015年1月19日 liuyijun
	 */
	private void firstTenderActivity(UserTenderActionResultVO vo) {
		Integer userId = vo.getUserId();
		logger.info("*****[system " + userId + " 第一笔投资相关活动处理 开始]");
		// 发送MQ消息,处理新手活动业务
		Integer tenderCount = tenderMapper.selectByUserId(userId);
		User userInfo = this.userService.selectByPrimaryKey(userId);
		Integer inviteUserId = 0;
		if (EmptyUtil.isNotEmpty(userInfo.getInviteUserid())) {
			inviteUserId = Integer.parseInt(userInfo.getInviteUserid());
		}
		if (vo.getRealPayMoney().doubleValue() >= 100 && tenderCount == 1
				&& inviteUserId > 0) {
			logger.info("*****[system " + userId + " 介绍人送提现券 开始]");
			this.userTaskService.insertUserTaskInfo(userId, 2);
			logger.info("*****[system " + userId + " 介绍人送提现券 结束]");

			logger.info("****[system " + userId + " 介绍人送加息 开始]");
			this.insertHikesCard(
					inviteUserId,
					new BigDecimal("0.3"),
					"理财师邀请新用户首投获得加息",
					"您邀请的用户已经完成首次投资，获得0.3%投资加息。获得之后可即刻投资使用，投资加息奖励将于满标审核后以现金红包（可再次用于投资或者提现）的形式统一发放到礼品盒。");
			logger.info("****[system " + userId + " 介绍人送加息 结束]");

			logger.info("****[system " + userId + " 2015新年红包活动加息增加 开始]");
			User inviteUserInfo = this.userService
					.selectByPrimaryKey(inviteUserId);
			this.updateActivityNewyear(inviteUserInfo.getPhone(),
					new BigDecimal(0), new BigDecimal(0.3));
			logger.info("****[system " + userId + " 2015新年红包活动加息增加 结束]");
		}
		if (tenderCount == 1) {
			logger.info("*****[system " + userId + " 自身送提现券 开始]");
			this.userTaskService.insertUserTaskInfo(userId, 4);
			logger.info("*****[system " + userId + " 自身送提现券 结束]");
		}
		logger.info("*****[system " + userId + " 第一笔投资相关活动处理 结束]");
	}

	/**
	 * 抽奖活动抽奖次数加1活动
	 * 
	 * @param vo
	 *            2015年1月19日 liuyijun
	 */
	private void prizeForAddTime(UserTenderActionResultVO vo) {
		logger.info("投资后抽奖活动抽奖次数加1业务处理开始");
		Date start = DateUtil.getDateToString(season2TurnplateStart,
				"yyyy-MM-dd HH:mm:ss");
		Date end = DateUtil.getDateToString(season2TurnplateEnd,
				"yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		if (now.compareTo(start) >= 0 && now.compareTo(end) <= 0) {
			BorrowTender bt = this.tenderService.selectByPrimaryKey(vo
					.getTenderId());
			BigDecimal money = new BigDecimal(bt.getAccount());
			if (money.compareTo(new BigDecimal(10000)) >= 0) {
				this.prizeService.addUserPrizeChooseCount(vo.getUserId(), 2);
			} else {
				logger.info("投资小于10000元，不增加抽奖次数");
			}
		} else {
			logger.info("投资后抽奖次数加1活动已过期");
		}
		logger.info("投资后抽奖活动抽奖次数加1业务处理完成");
	}

	/**
	 * 第二季抽奖活动期间(带你赚钱你带飞)新手首投奖励50%
	 * @param vo
	 */
	private void firstTenderByPrizeSeasonTwo(FinalVerifyVO verifyVO) {
		logger.info("第二季抽奖活动期间(带你赚钱你带飞)用户第一次投资奖励开始");
		Date start = DateUtil.getDateToString(season2TurnplateStart,
				"yyyy-MM-dd HH:mm:ss");
		Date end = DateUtil.getDateToString(season2TurnplateEnd,
				"yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		if (now.compareTo(start) >= 0 && now.compareTo(end) <= 0) {
			Borrow borrow = borrowService.selectBorrowById(verifyVO.getBorrowId());
			if(EmptyUtil.isNotEmpty(borrow) && EmptyUtil.isNotEmpty(verifyVO.getStatus()) && verifyVO.getStatus().equals(3)){
				List<BorrowTender> tender_lists = this.tenderMapper.selectListByBorrowId(borrow.getId());
				//首投用户集合
				List<BorrowTender> firstTenderLists = new ArrayList<BorrowTender>();
				for (BorrowTender tender : tender_lists) {
					List<BorrowTender> userTenderLists = tenderMapper.selectBrrowTenderByUserId(tender.getUserId());
					if(userTenderLists!=null && userTenderLists.size()>0){
						if(userTenderLists.get(0).getId().equals(tender.getId())){
							firstTenderLists.add(tender);
						}
					}
				}	
				this.doFirstTender(firstTenderLists,borrow);
			}else{
				logger.info("第二季抽奖活动期间(带你赚钱你带飞)标的异常不处理");
			}			
		} else {
			logger.info("第二季抽奖活动期间(带你赚钱你带飞)用户第一次投资奖励活动已过期");
		}		
		logger.info("第二季抽奖活动期间(带你赚钱你带飞)用户第一次投资奖励完成");
	}
	
	/**
	 * 2015年4月活动期间新手首投奖励50%
	 * @param vo
	 */
	private void firstTenderByApril(FinalVerifyVO verifyVO) {
		logger.info("2015年4月活动期间用户第一次投资奖励开始");
	
		logger.info("2015年4月活动期间用户第一次投资奖励完成");
	}
	
	/**
	 * 2015年5月活动app注册用户App首次投资
	 * @param vo
	 */
	private void firstTenderByAprilApp(FinalVerifyVO verifyVO) {
//		//TODO
//		logger.info("2015年5月app注册用户首次app投资短信提示开始");
//		Date start = DateUtil.getDateToString(fifteenAprilActivityAppStart,
//				"yyyy-MM-dd HH:mm:ss");
//		Date end = DateUtil.getDateToString(fifteenAprilActivityAppEnd,
//				"yyyy-MM-dd HH:mm:ss");
//		Date now = new Date();
//		if (now.compareTo(start) >= 0 && now.compareTo(end) <= 0) {
//			Borrow borrow = borrowService.selectBorrowById(verifyVO.getBorrowId());
//			if(EmptyUtil.isNotEmpty(borrow) && EmptyUtil.isNotEmpty(verifyVO.getStatus()) && verifyVO.getStatus().equals(3)){
//				List<BorrowTender> tender_lists = this.tenderMapper.selectListByBorrowIdAndApp(borrow.getId());
//				//首投用户集合
//				List<BorrowTender> firstTenderLists = new ArrayList<BorrowTender>();
//				for (BorrowTender tender : tender_lists) {
//					User user = this.userService.selectByPrimaryKey(tender.getUserId());
//					if(user.getAddip().equals("ios") || user.getAddip().equals("Android")){
//						List<BorrowTender> userTenderLists = tenderMapper.selectBrrowTenderByUserId(tender.getUserId());
//						if(userTenderLists!=null && userTenderLists.size()>0){
//							if(userTenderLists.get(0).getId().equals(tender.getId())){
//								firstTenderLists.add(tender);
//							}
//						}
//					}
//				}	
//				
//				if(!firstTenderLists.isEmpty()){
//					for (BorrowTender borrowTender : firstTenderLists) {
//						User user = this.userService.selectByPrimaryKey(borrowTender.getUserId());
//						// 发送短信
//						this.jmsSenderUtil.sendSms(user.getPhone(), ISendConfigUtil.APP_FIRST_BORROW_TENDER);
//					}
//				}
////				this.doFirstTender(firstTenderLists,borrow);
//			}else{
//				logger.info("2015年4月活动期间标的异常不处理");
//			}			
//		} else {
//			logger.info("2015年5月app注册用户首次app投资短信提示已过期");
//		}		
//		logger.info("2015年5月app注册用户首次app投资短信提示完成");
	}
	
	
	/**
	 * 酒仙网首次投资满1000元
	 * @param verifyVO
	 */
	private void firstTenderByJiuXian(FinalVerifyVO verifyVO) {
		logger.info("*****[activity 2015年酒仙网首次投资满1000元  开始]");
		Date start = DateUtil.getDateToString(jiuXianActivityStart,
				"yyyy-MM-dd HH:mm:ss");
		Date end = DateUtil.getDateToString(jiuXianActivityEnd,
				"yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		if (now.compareTo(start) >= 0 && now.compareTo(end) <= 0) {
			Borrow borrow = borrowService.selectBorrowById(verifyVO.getBorrowId());
			if(EmptyUtil.isNotEmpty(borrow) && EmptyUtil.isNotEmpty(verifyVO.getStatus()) && verifyVO.getStatus().equals(3)){
				List<BorrowTender> tender_lists = this.tenderMapper.selectListByBorrowId(borrow.getId());
				for (BorrowTender borrowTender : tender_lists) {
					UserFrom uf = userFromMapper.selectByUserId(borrowTender.getUserId());
					if(uf!=null&&uf.getUserId()>0&&uf.getType().equals(8)){
						//酒仙网用户
						if(new BigDecimal(borrowTender.getAccount()).compareTo(new BigDecimal(1000)) >=0){
							//投资满1000
							List<BorrowTender> userTenderLists = tenderMapper.selectBrrowTenderAccountThanOneThousandByUserId(borrowTender.getUserId());
							if(userTenderLists!=null && userTenderLists.size()>0){
								//用户投资大于1000的tender集合
								if(userTenderLists.get(0).getId().equals(borrowTender.getId())){
									HikesMessage hm = hikesMessageMapper.selectJiuXianHikesFristTenderByUserId(borrowTender.getUserId());
									if(hm==null){
										this.insertHikesCard(borrowTender.getUserId()
												,new BigDecimal(1)
												,"酒仙网新手首投加息奖励！"
												,"恭喜您在酒仙网&微积金活动中，获得1%加息。投资加息将于满标审核后以现金红包的形式统一发放到礼品盒。获得之后可可再次用于投资或者提现，请在“我的账户”－“礼品盒”中查收。详询：4009919999");		
										logger.info("*****[activity " + borrowTender.getUserId() + " 2015年酒仙网首次投资满1000元送1%加息 处理成功]");							
									}
								}
							}	
						}
					}
				}				
			}else{
				logger.info("*****[activity 2015年酒仙网首次投资满1000元  标的异常不处理]");
			}			
		}else{
			logger.info("*****[activity 2015年酒仙网首次投资满1000元  活动已过期]");
		}
		logger.info("*****[activity 2015年酒仙网首次投资满1000元  结束]");
	}
	
	/**
	 * 新手首投奖励
	 * @param firstTenderLists
	 */
	private void doFirstTender(List<BorrowTender> firstTenderLists,Borrow borrow){
		logger.info("新手首投奖励开始");
		if(firstTenderLists!=null && firstTenderLists.size()>0){
			InvestorsFee ifee = investorsFeeMapper.selectByBorrowId(borrow.getId());
			if(EmptyUtil.isNotEmpty(ifee) && EmptyUtil.isNotEmpty(ifee.getBfee()) && EmptyUtil.isNotEmpty(ifee.getGfee())){
				//标最终利率
				BigDecimal finalApr = borrow.getApr().subtract(ifee.getBfee().add(ifee.getGfee()));
				for(BorrowTender tender:firstTenderLists){
					List<BorrowCollection> collectionList =borrowCollectionMapper.selectByTenderId(tender.getId());
					BigDecimal tenderAccount=null;
					Date repayTime=null;
					//最大投资金额10000以内
					if(new BigDecimal(tender.getAccount()).compareTo(new BigDecimal(10000))>0){
						tenderAccount = new BigDecimal(10000);
					}else{
						tenderAccount = new BigDecimal(tender.getAccount());
					}
//					BigDecimal avgApr = finalApr.divide(new BigDecimal(1200),2,BigDecimal.ROUND_HALF_UP);
//					BigDecimal a = avgApr.multiply(tenderAccount);
//					BigDecimal b = a.multiply(new BigDecimal(0.5));
					//最终每期红包奖励
					BigDecimal bonus=(((finalApr.divide(new BigDecimal(1200),8,BigDecimal.ROUND_HALF_UP)).multiply(tenderAccount)).multiply(new BigDecimal(0.5))).setScale(2, BigDecimal.ROUND_HALF_UP);			
					for(BorrowCollection bc:collectionList){
						 SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
						 Long time = new Long(bc.getRepayTime())*1000;
						 String d = format.format(time);
						 try {
							 //红包生效时间
							repayTime=format.parse(d);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						//发红包 
						this.gifxboxMessage(tender.getUserId(), bonus
								, "小主，首笔投资收益提升到账啦"
								, "微积金平台首次投资标的["+borrow.getName()+"]，新手收益提升50%奖励["+bonus+"]元。生效后点击“使用”即可充值到您的现金账户。"
								, repayTime);
						//发红包日志
						logger.info("发送红包 userId:"+tender.getUserId().toString()+""
								+ ",money:"+bonus.toString()+",borrowId:"+borrow.getId().toString()+",borrowName:"+borrow.getName()
								+",takeTime:"+repayTime.toString()+"。");
					}				
				}
			}else{
				logger.info("标的关联InvestorsFee表无数据,borrowId;"+borrow.getId()+",处理新手首投结束");
			}
		}	
		logger.info("新手首投奖励完成");
	}

	/**
	 * 用户来源方法
	 * 
	 * @param vo
	 */
	public void insertUserFrom(RegisterVO vo) {
		Integer userId = vo.getRegisterUserId();
		logger.info("****[system " + userId + " 用户来源 开始]");
		Integer type = Integer.parseInt(vo.getThirdSource() == null ? "0" : vo
				.getThirdSource());
		// 用户来源
		String referUrl = vo.getRefererUrl();
		UserFrom userFrom = new UserFrom();
		userFrom.setUserId(vo.getRegisterUserId());
		userFrom.setAddtime(new Date());
		userFrom.setFromUrl(referUrl);
		userFrom.setType(type);
		UserFromDic ufd = this.userFromDicService.selectByFromNo(type.toString());
		if(ufd == null){
			this.otherUserFrom(userFrom, referUrl);
		}else if (vo.getThirdSource() != null && vo.getThirdSource().equals("1")) {// 来帮我
			this.firstUserFrom(vo, userFrom);
		}else if (vo.getThirdSource() != null
				&& vo.getThirdSource().equals("2")) {// JJ斗地主
			this.secondUserFrom(userFrom, userId);
		}else{
			userFrom.setFromName(ufd.getFromName());
		}
		
//		if (vo.getThirdSource() != null && vo.getThirdSource().equals("1")) {// 来帮我
//			this.firstUserFrom(vo, userFrom);
//		} else if (vo.getThirdSource() != null
//				&& vo.getThirdSource().equals("2")) {// JJ斗地主
//			this.secondUserFrom(userFrom, userId);
//		} else if (vo.getThirdSource() != null
//				&& vo.getThirdSource().equals("3")) {
//			userFrom.setFromName("微财富推广");
//		} else if (vo.getThirdSource() != null
//				&& vo.getThirdSource().equals("4")) {
//			userFrom.setFromName("微财富EDM");
//		} else if (vo.getThirdSource() != null
//				&& vo.getThirdSource().equals("7")) {
//			userFrom.setFromName("微财富基金广告");
//		} else if (vo.getThirdSource() != null
//				&& vo.getThirdSource().equals("8")){
//			userFrom.setFromName("酒仙网");
//		} else {
//			this.otherUserFrom(userFrom, referUrl);
//		}
		this.userFromMapper.insertSelective(userFrom);
		logger.info("****[system " + userId + " 用户来源 结束]");
	}

	/**
	 * 来帮我用户来源
	 * 
	 * @param vo
	 * @param userFrom
	 */
	private void firstUserFrom(RegisterVO vo, UserFrom userFrom) {
		String url = "http://www.bangwoya.com/callback/callback.php?oid=30000031&sn="
				+ vo.getThirdSn() + "&uid=" + vo.getRegisterUserId();
		HttpRequester httpRequest = new HttpRequester();
		try {
			httpRequest.sendPost(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		userFrom.setFromUrl(vo.getThirdSn());
		userFrom.setFromName("来帮我");
	}

	/**
	 * JJ斗地主
	 * 
	 * @param userFrom
	 * @param userId
	 */
	private void secondUserFrom(UserFrom userFrom, Integer userId) {
		SystemMessage systemMessage = new SystemMessage();
		systemMessage.setIsLook(0);
		systemMessage.setType(0);
		systemMessage.setReceiveUser(userId);
		systemMessage.setAddtime(new Date());
		JjCard jc = jjCardService.selectByNoSend(JjCard.DAY_RACE_UNSEND);
		if (jc == null) {
			systemMessage.setTitle("微积金&amp;JJ斗地主大赛参赛码");
			systemMessage.setContent("恭喜您，注册成功!斗地主大赛参赛吗已发放完毕.");
		} else {
			systemMessage.setTitle("微积金&amp;JJ斗地主大赛参赛码");
			systemMessage
					.setContent("恭喜您，注册成功!您的微积金斗地主大赛参赛码：[<font color='red'>"
							+ jc.getCardNo()
							+ "</font>]。您的5元现金红包已发放至您的微积金账户，请登录微积金“我的账户”－“礼品盒”中查收。");
		}
		int i = this.systemMessageMapper.insertSelective(systemMessage);
		GiftboxMessage gm = new GiftboxMessage();
		gm.setSendUser(1);
		gm.setAddtime(new Date());
		gm.setTitle("恭喜您获得微积金&JJ斗地主大赛5元现金红包");
		gm.setContent("感谢您参加微积金斗地主比赛，恭喜您获得由微积金提供的5元现金红包,您可在微积金平台投资或者提现使用.生效后点击“使用”即可充值到您的现金账户。微积金理财年化10-20%收益,本息保障,详询4009919999.关注官方微信：vfunding获取最新活动信息。");
		gm.setIsLook(0);
		gm.setTakeTime(new Date());
		gm.setLoseTime(DateUtil.getNextDayTime(new Date(), 30));
		gm.setMoney(new BigDecimal(5));
		gm.setReceiveUser(userId);
		gm.setStatus(0);
		gm.setType(0);
		giftboxMessageMapper.insertSelective(gm);
		if (i > 0) {
			if (jc != null) {
				jc.setSendTime(new Date());
				jc.setStatus(1);
				jjCardService.updateByPrimaryKeySelective(jc);
			}
		}
		userFrom.setFromName("JJ斗地主");
	}

	/**
	 * 其他用户来源处理
	 */
	private void otherUserFrom(UserFrom userFrom, String referUrl) {
		List<UserFromDictionary> listUserFrom = this.userFromDictionaryMapper
				.selectAllFromDictionary();
		userFrom.setFromName("自然");
		for (UserFromDictionary ufd : listUserFrom) {
			if (EmptyUtil.isNotEmpty(referUrl)
					&& EmptyUtil.isNotEmpty(ufd.getFromName())) {
				if (referUrl.contains(ufd.getFromKey())) {
					userFrom.setFromName(ufd.getFromName());
					break;
				}
			}
		}
	}

	private void registerVoteHongbao(RegisterVO vo) {
		if (vo.getThirdSource() != null && vo.getThirdSource().equals("12")) {
			Integer userId = vo.getRegisterUserId();
			UserWithBLOBs user = this.userService.selectByPrimaryKey(userId);
			// 注册送2元红包给上线
			Integer inviteUserId = 0;
			if (EmptyUtil.isNotEmpty(user.getInviteUserid())) {
				inviteUserId = Integer.parseInt(user.getInviteUserid());
			}
			if (inviteUserId > 0) {
				this.gifxboxMessage(inviteUserId, new BigDecimal("2"),
						"破两亿活动现金红包奖励",
						"破两亿活动邀请新用户完成注册，奖励2元现金红包。生效后点击“使用”即可充值到您的账户余额。");
				User inviteUserInfo = this.userService
						.selectByPrimaryKey(inviteUserId);
				this.updateActivityNewyear(inviteUserInfo.getPhone(),
						new BigDecimal(2), new BigDecimal(0));
			}
		}
	}

	private void updateActivityNewyear(String phone, BigDecimal hongbao,
			BigDecimal hikes) {
		ActivityNewyear activityNewyear = this.activityNewyearMapper
				.selectByPrimaryKey(phone);
		if (activityNewyear != null
				&& activityNewyear.getPhone().length() == 11) {
			activityNewyear.setHongbao(activityNewyear.getHongbao()
					.add(hongbao));
			activityNewyear.setHikes(activityNewyear.getHikes().add(hikes));
			this.activityNewyearMapper
					.updateByPrimaryKeySelective(activityNewyear);
		}
	}

	private void realnameVoteHongbao(Integer userId) {
		UserWithBLOBs user = this.userService.selectByPrimaryKey(userId);
		Integer inviteUserId = 0;
		if (EmptyUtil.isNotEmpty(user.getInviteUserid())) {
			inviteUserId = Integer.parseInt(user.getInviteUserid());
		}
		UserFrom uf = this.userFromMapper.selectByUserId(userId);
		if (uf != null && uf.getUserId()>0 && uf.getType().equals(12)) {
			// 实名送3元红包给上线
			if(inviteUserId > 0){
				UserWithBLOBs inviteUser = this.userService
						.selectByPrimaryKey(inviteUserId);
				this.gifxboxMessage(inviteUserId, new BigDecimal("3"),
						"破两亿活动现金红包奖励",
						"破两亿活动邀请的新用户完成实名认证，奖励3元现金红包。生效后点击“使用”即可充值到您的账户余额。");
				this.updateActivityNewyear(inviteUser.getPhone(),
						new BigDecimal(3), new BigDecimal(0));
			}
			// 本人获得30m流量
			GiftotherMessage gm = new GiftotherMessage();
			gm.setSendUser(0);
			gm.setReceiveUser(user.getUserId());
			gm.setIsLook(0);
			gm.setType(80);
			gm.setTitle("破两亿活动流量奖励！");
			gm.setContent("感谢您参与破两亿红包活动，成功获得30M流量包，活动结束三个工作日内由运营商发送详情至您注册的手机号。具体发放时间以运营商为主。活动期间您在微积金的首次投资将享受50%的收益提升。");
			gm.setAddtime(new Date());	
			giftotherMessageMapper.insertSelective(gm);
			
		}
	}
	
	/**
	 * 理财师活动_实名认证
	 * @param user
	 */
	private void realnameBase(User record) {
		User user = userService.selectByPrimaryKey(record.getUserId());
		if(EmptyUtil.isNotEmpty(user)&&EmptyUtil.isNotEmpty(user.getUserId())&&user.getUserId()>0){
			logger.info("*****[activity " + user.getUserId() + " 理财师活动[实名认证],被邀请人获得2%加息 开始]");
			Integer inviteUserId = 0;
			if (EmptyUtil.isNotEmpty(user.getInviteUserid()) && NumberUtils.isNumber(user.getInviteUserid())) {
				inviteUserId = Integer.parseInt(user.getInviteUserid());
			}
			if(inviteUserId>0){
				User inviteUser = userService.selectByPrimaryKey(inviteUserId);
				//被邀请人实名后获得2%加息
				this.insertHikesCard(user.getUserId()
						,new BigDecimal(2)
						,"邀请加息"
						,"您已接受"+inviteUser.getUsername()+"的邀请成为微积金的用户，获得其赠送的2%邀请加息。获得之后可即刻投资使用，投资加息将于满标审核后以现金红包（可再次用于投资或者提现）的形式统一发放到礼品盒");		
				logger.info("*****[activity " + user.getUserId() + " 理财师活动[实名认证],被邀请人获得2%加息 处理成功]");
			}else{
				logger.info("*****[activity " + user.getUserId() + " 理财师活动[实名认证],被邀请人获得2%加息 没有邀请人无需处理]");
			}
			logger.info("*****[activity " + user.getUserId() + " 理财师活动[实名认证],邀请人获得2%加息 结束]");
		}		
	}
	
	/**
	 * 酒仙网实名认证活动
	 * @param record
	 */
	private void realnameJiuXian(User record) {
		User user = userService.selectByPrimaryKey(record.getUserId());
		if(EmptyUtil.isNotEmpty(user)&&EmptyUtil.isNotEmpty(user.getUserId())&&user.getUserId()>0){
			Date start = DateUtil.getDateToString(jiuXianActivityStart,
					"yyyy-MM-dd HH:mm:ss");
			Date end = DateUtil.getDateToString(jiuXianActivityEnd,
					"yyyy-MM-dd HH:mm:ss");
			Date now = new Date();
			logger.info("*****[activity " + user.getUserId() + " 2015年酒仙网活动实名认证后送30m流量 开始]");
			if (now.compareTo(start) >= 0 && now.compareTo(end) <= 0) {
				//活动期间内
				UserFrom uf = userFromMapper.selectByUserId(user.getUserId());
				if(uf!=null&&uf.getUserId()>0&&uf.getType().equals(8)){
					//酒仙网用户
					GiftotherMessage gm = new GiftotherMessage();
					gm.setSendUser(0);
					gm.setReceiveUser(user.getUserId());
					gm.setIsLook(0);
					gm.setType(80);
					gm.setTitle("恭喜您获得酒仙网活动30M流量包！");
					gm.setContent("恭喜您完成实名认证获得30M流量包一个！您微积金认证手机号码所属的运营商会在一周内将流量包使用详情发送至您手机短信！活动期间您在微积金的首次投资将享受50%的收益提升，详询：4009919999。");
					gm.setAddtime(new Date());	
					giftotherMessageMapper.insertSelective(gm);
					logger.info("*****[activity " + user.getUserId() + " 2015年酒仙网活动实名认证后送30m流量 处理成功]");
				}else{
					logger.info("*****[activity " + user.getUserId() + " 2015年酒仙网活动实名认证后送30m流量 非酒仙网用户无需处理]");
				}
			}else{
				logger.info("*****[activity " + user.getUserId() + " 2015年酒仙网活动实名认证后送30m流量 活动过期]");
			}
			logger.info("*****[activity " + user.getUserId() + " 2015年酒仙网活动实名认证后送30m流量 结束]");
		}		
	}

	private void insertHikesCard(Integer userId, BigDecimal hikes,
			String title, String content) {
		HikesCard hc = this.hikesCardMapper.selectByPrimaryKey(userId);
		hc.setUseRate(hc.getUseRate().add(hikes));
		this.hikesCardMapper.updateByPrimaryKeySelective(hc);

		HikesMessage hm = new HikesMessage();
		hm.setReceiveUser(userId);
		hm.setTitle(title);
		hm.setContent(content);
		hm.setRate(hikes);
		hm.setAddtime(new Date());
		this.hikesMessageMapper.insertSelective(hm);
	}

	/**
	 * 红包数据插入
	 * @param userId
	 * @param hongbao
	 * @param title
	 * @param content
	 * @author louchen 2015-03-13
	 */
	private void gifxboxMessage(Integer userId, BigDecimal hongbao,
			String title, String content) {
		this.gifxboxMessage(userId, hongbao, title, content, new Date());
	}
	
	/**
	 * 红包数据插入
	 * @param userId
	 * @param hongbao
	 * @param title
	 * @param content
	 * @param date
	 * @author louchen 2015-03-13
	 */
	private void gifxboxMessage(Integer userId, BigDecimal hongbao,
			String title, String content,Date date){
		GiftboxMessage gm = new GiftboxMessage();
		gm.setSendUser(1);
		gm.setAddtime(new Date());
		gm.setTitle(title);
		gm.setContent(content);
		gm.setIsLook(0);
		gm.setTakeTime(date);
		gm.setLoseTime(DateUtil.getNextDayTime(date,30));
		gm.setMoney(hongbao);
		gm.setReceiveUser(userId);
		gm.setStatus(0);
		gm.setType(0);
		gm.setAddip("127.0.0.1");
		giftboxMessageMapper.insertSelective(gm);
		
	}
	
	/**
	 * 满标复审后相关活动
	 * @param verifyVO
	 * @return String
	 * @author louchen 2015-03-13
	 */
	@Override
	public String doBorrowVerify(FinalVerifyVO verifyVO) {
		String result = "faild";
		logger.info("*****[activity 满标复审后相关活动 开始]");
		if(verifyVO!=null && verifyVO.getBorrowId()!=null && verifyVO.getStatus()!=null && verifyVO.getStatus().equals(3)){
			// 第二季抽奖活动期间(带你赚钱你带飞)新手首投奖励50%
			this.firstTenderByPrizeSeasonTwo(verifyVO);
			// 2015年4月活动新手首投
			this.firstTenderByApril(verifyVO);
			//2015年5月app首次投资活动 TODO
			this.firstTenderByAprilApp(verifyVO);
			// 2015年酒仙网新手首投>1000
			this.firstTenderByJiuXian(verifyVO);
			// 其他活动
		
			// codes
			logger.info("*****[activity 满标复审后{borrowId:"+verifyVO.getBorrowId()+"}相关活动 成功]");
			result = "success";
		}else{
			logger.info("*****[activity 满标复审后相关活动 标的参数异常，无需处理]");
		}	
		logger.info("*****[activity 满标复审后相关活动 完成]");
		return result;
	}
	
	/**
	 * 2015年四月活动投资相关
	 * @param UserTenderActionResultVO
	 * @author louchen 2015-03-31
	 */
	private synchronized void tenderForAprilActivity(UserTenderActionResultVO vo){
	
	}
	
	/**
	 * 2015年四月活动投资相关_给用户加息成就
	 * @param achievementId 
	 * @param userId
	 * @return
	 */
	private String doTenderForAprilActivityMethod(Integer achievementId,Integer userId){
		String result = "failed";
		AchievementLog al = achievementLogService.selectByAchievementIdAndUserId(achievementId, userId);
		if(EmptyUtil.isNotEmpty(al) && al.getId()>0){
			result = "duplication";
		}else{
			if(achievementLogService.createAchievementLog(achievementId,userId)>0){
				result = "success";
			}else{
				logger.error("*****[activity (2015年4月活动)投资后相关活动,添加成就失败,achievementId:"+achievementId.toString()+",userId:"+userId.toString());
			}
		}		
		return result;
	}
	
	/**
	 * 2015年酒仙网活动_每天新手首投满1000元前十名送红酒
	 * @param vo
	 */
	private synchronized void tenderForJiuXian(UserTenderActionResultVO vo){
		logger.info("*****[activity 2015年酒仙网活动_每天新手首投满1000元前十名送红酒  开始]");
		Date start = DateUtil.getDateToString(jiuXianActivityStart,
				"yyyy-MM-dd HH:mm:ss");
		Date end = DateUtil.getDateToString(jiuXianActivityEnd,
				"yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		if (now.compareTo(start) >= 0 && now.compareTo(end) <= 0) {
			UserFrom uf = userFromMapper.selectByUserId(vo.getUserId());
			if(uf!=null&&uf.getUserId()>0&&uf.getType().equals(8)){
				//酒仙网用户
				if(vo.getRealPayMoney().compareTo(new BigDecimal(1000)) >=0 ){
					//投资大于1000元
					Integer allCount = giftotherMessageMapper.selectJiuXianTopTenCountDay(getTodayStart(), getTodayEnd());
					Integer userCount = giftotherMessageMapper.selectJiuXianTopTenCountDayByUserId(vo.getUserId(),getTodayStart(), getTodayEnd());
					if(allCount<10 && userCount==0){
						GiftotherMessage gm = new GiftotherMessage();
						gm.setSendUser(0);
						gm.setReceiveUser(vo.getUserId());
						gm.setIsLook(0);
						gm.setType(81);
						gm.setTitle("酒仙网活动红酒奖励！");
						gm.setContent("恭喜您在酒仙网&微积金活动中，获得价值180元法国巴黎丽人干红葡萄酒750ml一瓶，将由客服联系您获取地址后快递给您。详询：4009919999。 ");
						gm.setAddtime(new Date());	
						giftotherMessageMapper.insertSelective(gm);
						logger.info("*****[activity 2015年酒仙网活动_每天新手首投满1000元前十名送红酒  处理成功]");
					}
				}				
			}		
		}else{
			logger.info("*****[activity 2015年酒仙网活动_每天新手首投满1000元前十名送红酒  活动已过期]");
		}
		logger.info("*****[activity 2015年酒仙网活动_每天新手首投满1000元前十名送红酒  结束]");
	}
	
	private String getTodayStart(){
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return  sdf.format(todayStart.getTime());
	}
	
	private String getTodayEnd(){
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(todayEnd.getTime());
	}
}
