package cn.vfunding.vfunding.prd.www.system.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.server.interceptors.NeedSession;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.EncryptionUtil;
import cn.vfunding.common.framework.utils.beans.StringUtil;
import cn.vfunding.common.framework.utils.beans.VerifyIdCard;
import cn.vfunding.common.framework.utils.http.MultipartEntityBuilderUtil;
import cn.vfunding.common.framework.utils.http.RestInvokerFactory;
import cn.vfunding.common.framework.utils.kaptcha.VerifyCodeUtils;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.account.model.AccountFeelLog;
import cn.vfunding.vfunding.biz.account.model.AccountSinabonus;
import cn.vfunding.vfunding.biz.account.service.IAccountBankService;
import cn.vfunding.vfunding.biz.account.service.IAccountFeelLogService;
import cn.vfunding.vfunding.biz.account.service.IAccountFeelService;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.account.service.IAccountSinabonusService;
import cn.vfunding.vfunding.biz.borrow.model.BorrowTender;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowCollectionService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowRepaymentService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.common.vo.AccountVO;
import cn.vfunding.vfunding.biz.common.vo.MessageVO;
import cn.vfunding.vfunding.biz.common.vo.UnionUserBandVO;
import cn.vfunding.vfunding.biz.common.vo.UserInfoVO;
import cn.vfunding.vfunding.biz.credit.model.CreditRank;
import cn.vfunding.vfunding.biz.credit.service.ICreditRankService;
import cn.vfunding.vfunding.biz.credit.service.ICreditService;
import cn.vfunding.vfunding.biz.financialPlanner.service.IFinancialPlannerService;
import cn.vfunding.vfunding.biz.inviteCode.service.IInviteCodeService;
import cn.vfunding.vfunding.biz.inviteCode.status.DialogStatusIndex;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.message.model.SystemMessage;
import cn.vfunding.vfunding.biz.message.service.ICashMessageService;
import cn.vfunding.vfunding.biz.message.service.IGiftboxMessageService;
import cn.vfunding.vfunding.biz.message.service.IGiftotherMessageService;
import cn.vfunding.vfunding.biz.message.service.IHikesMessageService;
import cn.vfunding.vfunding.biz.message.service.ISystemMessageService;
import cn.vfunding.vfunding.biz.returns.model.InviteFees;
import cn.vfunding.vfunding.biz.session.utils.HttpSessionTool;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaDicParmUtil;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.model.SinaDic;
import cn.vfunding.vfunding.biz.sina.service.IQuerySinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaDicService;
import cn.vfunding.vfunding.biz.sina.service.ISinaMemberManagerService;
import cn.vfunding.vfunding.biz.system.model.AllStatistics;
import cn.vfunding.vfunding.biz.system.model.Attestation;
import cn.vfunding.vfunding.biz.system.model.AttestationType;
import cn.vfunding.vfunding.biz.system.model.Linkage;
import cn.vfunding.vfunding.biz.system.model.Message;
import cn.vfunding.vfunding.biz.system.model.Pic;
import cn.vfunding.vfunding.biz.system.model.UserTrack;
import cn.vfunding.vfunding.biz.system.service.IAllStatisticsService;
import cn.vfunding.vfunding.biz.system.service.IAttestationService;
import cn.vfunding.vfunding.biz.system.service.IAttestationTypeServicve;
import cn.vfunding.vfunding.biz.system.service.ILinkageService;
import cn.vfunding.vfunding.biz.system.service.IMessageService;
import cn.vfunding.vfunding.biz.system.service.IUserTrackService;
import cn.vfunding.vfunding.biz.system.service.PicService;
import cn.vfunding.vfunding.biz.thirdplat.model.CashVolume;
import cn.vfunding.vfunding.biz.thirdplat.service.ICashVolumeService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.model.UserCache;
import cn.vfunding.vfunding.biz.user.model.UserInfo;
import cn.vfunding.vfunding.biz.user.model.UserType;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.user.service.IUserCacheService;
import cn.vfunding.vfunding.biz.user.service.IUserInfoService;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.biz.user.service.IUserTypeService;
import cn.vfunding.vfunding.biz.userMobile.service.IUserMobileService;
import cn.vfunding.vfunding.common.module.activemq.message.SendVerifyEmailMessage;
import cn.vfunding.vfunding.common.module.activemq.message.UnionUserRealNameMessage;
import cn.vfunding.vfunding.prd.www.util.financialPlanner.FinancialPlannerUtil;
import cn.vfunding.vfunding.prd.www.vo.FindPayPwdVO;

@Controller
@RequestMapping(value = { "/user", "/system/user" })
public class UserController extends BaseController {
	@Autowired
	private IUserService userService;
	@Value("${php.returnBaseUrl}")
	private String phpReturnBaseUrl;
	@Autowired
	private IAccountFeelService accountFeelService;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private ICreditService creditService;
	@Autowired
	private ICreditRankService creditRankService;
	@Autowired
	private IMessageService messageService;
	@Autowired
	private ILinkageService linkageService;
	@Autowired
	private IAttestationService attestationService;
	@Autowired
	private IAttestationTypeServicve attestationTypeService;
	@Autowired
	private IUserInfoService userInfoService;
	@Autowired
	private IAccountBankService accountBankService;
	@Autowired
	private IUserCacheService userCacheService;
	@Autowired
	private IBorrowService borrowService;
	@Autowired
	private IBorrowCollectionService borrowCollectionService;
	@Autowired
	private IBorrowRepaymentService borrowRepaymentService;
	@Autowired
	private IBorrowTenderService borrowTenderService;
	@Autowired
	private IAllStatisticsService allStatisticsService;
	@Autowired
	private IUserTypeService typeService;
	@Autowired
	private IAccountFeelLogService accountFeelLogService;

	@Autowired
	private ICashVolumeService cashVolumeService;
	@Autowired
	private IUserTrackService trackService;

	@Autowired
	private ISystemMessageService systemMessageService;
	@Autowired
	private IGiftboxMessageService giftboxMessageService;
	@Autowired
	private IGiftotherMessageService giftotherMessageService;
	@Autowired
	private IHikesMessageService hikesMessageService;
	@Autowired
	private ICashMessageService cashMessageService;
	
	@Autowired
	private IQuerySinaService querySinaService;

	@Autowired
	IInviteCodeService inviteCodeService;

	@Autowired
	private IFinancialPlannerService financialPlannerService;
	/**
	 * Sina会员管理接口
	 */
	@Autowired
	private ISinaMemberManagerService sinaMemberManagerService;

	@Autowired
	private PicService picService;
	
	@Autowired
	private IAccountSinabonusService accountSinabonusService;
	/**
	 * Sina code字典
	 */
	@Autowired
	private ISinaDicService sinaDicService;

	@Autowired
	private JmsSenderService jmsSenderUtil;

	@Value("${vipPrice}")
	private Integer vipPrice;

	/**
	 * 实名认证服务
	 */
	@Resource(name = "realNameRestInvokerFactory")
	private RestInvokerFactory realNameRestInvokerFactory;

	/**
	 * 网站联盟用户实名认证同步微积金消息
	 * 
	 * @param message
	 *            2014年5月29日 liuyijun
	 */
	@RequestMapping(value = "/unionUserRealName", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public void unionUserRealName(@RequestBody UnionUserRealNameMessage message) {
		//jmsSender.sendAsynchronousMessage(message);
		this.jmsSenderUtil.asynSendSystemJms(message);
	}

	/**
	 * 网站联盟用户绑定微积金账户信息
	 * 
	 * @param unionUserBand
	 * @return
	 * @author liuyijun
	 */
	@RequestMapping(value = "/unionUserBandVfundingUser", method = RequestMethod.POST, consumes = { "application/json" })
	@ResponseBody
	public void unionUserBandVfundingUser(
			@RequestBody UnionUserBandVO unionUserBand) {
		//jmsSender.sendAsynchronousMessage(unionUserBand);
		this.jmsSenderUtil.asynSendSystemJms(unionUserBand);
	}

	/**
	 * php实名认证接口(新版)
	 * 
	 * @deprecated
	 * @param record
	 * @return 2014年5月28日 liuyijun
	 */
	@RequestMapping(value = "/phpRealName")
	@ResponseBody
	public Json realNameForPhp(User record) {
		Json j = new Json();
		List<UserWithBLOBs> cardUsers = this.userService.selectByCardId(record
				.getCardId());
		if (EmptyUtil.isNotEmpty(cardUsers) && cardUsers.size() > 0) {
			j.setMsg("该身份证已被注册，不可验证");
		} else {
			User user = this.userService.selectByPrimaryKey(record.getUserId());
			if (EmptyUtil.isNotEmpty(user)) {
				if (EmptyUtil.isEmpty(user.getRealStatus())
						|| !user.getRealStatus().equals("1")) {
					if (EmptyUtil.isNotEmpty(record.getCardId())) {
						if (EmptyUtil.isNotEmpty(record.getRealname())) {
							if (VerifyIdCard.verify(record.getCardId())) {
								// 实名认证接口
								boolean checkCardId = false;
								checkCardId = this.realNameRestInvokerFactory
										.getRestInvoker().get(
												"/" + record.getRealname()
														+ "/"
														+ record.getCardId(),
												Boolean.class);
								if (checkCardId) {
									record.setRealStatus("1");
									record.setHongbao(user.getHongbao());// 设置原有红包
									this.userService.updateRealName(record);
									j.setSuccess(true);
									if (EmptyUtil.isNotEmpty(UserSession
											.getUserSession())) {
										UserSession.getUserSession()
												.setRealStatus("1");
										UserSession
												.getUserSession()
												.setRealName(user.getRealname());
										UserSession.getUserSession().setCardId(
												user.getCardId());
									}
								} else {
									j.setMsg("身份信息验证失败");
								}
							} else {
								j.setMsg("身份证号码错误");
							}
						} else {
							j.setMsg("真实姓名不可为空");
						}
					} else {
						j.setMsg("身份证不可为空");
					}
				} else {
					j.setMsg("您已通过实名认证");
				}
			} else {
				j.setMsg("用户信息错误");
			}
		}
		return j;
	}

	/**
	 * php版实名认证接口
	 * 
	 * @deprecated
	 * 
	 * @param record
	 * @param multipartRequest
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadCardImage")
	public ModelAndView uploadCardImage(User record, String returnUrl,
			MultipartHttpServletRequest multipartRequest) throws Exception {
		ModelAndView mv = new ModelAndView();
		String baseUrl = "redirect:" + phpReturnBaseUrl;
		if (EmptyUtil.isNotEmpty(returnUrl)) {
			baseUrl += returnUrl;
		}
		mv.setViewName(baseUrl);
		String newRealname = record.getRealname();
		record.setRealname(URLDecoder.decode(newRealname, "UTF-8"));
		List<MultipartFile> multipartFiles = multipartRequest.getFiles("files");
		if (EmptyUtil.isNotEmpty(multipartFiles)) {
			List<MultipartEntityBuilder> multipartEntitys = new ArrayList<MultipartEntityBuilder>();
			for (MultipartFile multipartFile : multipartFiles) {
				if (multipartFile.getSize() > 0) {
					MultipartEntityBuilder multipartEntity = MultipartEntityBuilderUtil
							.createMultipartEntityBuilderByMultipartFile(multipartFile);
					multipartEntitys.add(multipartEntity);
				} else {
					break;
				}
			}
			this.userService.updateUserCardImage(record, multipartEntitys);
		}
		return mv;
	}

	@Autowired
	private IUserMobileService umService;

	/**
	 * 转向账户中心页面
	 * 
	 * @return 2014年4月25日 liuyijun
	 * 
	 *         -理财师等级修改[初窥门径，理财师] -wang.zeyan 2014.3.3
	 */
	@NeedSession(returnUrl = "/user/account")
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public ModelAndView account() {
		ModelAndView mv = new ModelAndView("user/account");

		// 账户信息
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		// 积分信息
		Integer credit = this.creditService.selectValueByUserId(UserSession
				.getLoginUserId());
		if (EmptyUtil.isNotEmpty(credit)) {
			CreditRank rank = this.creditRankService
					.selectByCreditValue(credit);
			if (EmptyUtil.isNotEmpty(rank)) {
				int rankNum = rank.getRank() / 4;
				mv.addObject("rankNum", rankNum);
			}
		}

		boolean isVIP = (EmptyUtil.isEmpty(UserSession.getUserSession()
				.getVipStatus()) || UserSession.getUserSession().getVipStatus() == 0) ? false
				: true;
		mv.addObject("isVIP", isVIP);// 是否是会员

		// 站内信
		Integer messageCount = this.messageService
				.selectNoReadCount((Integer) (UserSession.getLoginUserId()));
		if (EmptyUtil.isNotEmpty(messageCount)) {
			mv.addObject("messageCount", messageCount);
		}

		AccountVO accountvo = new AccountVO();
		accountvo.setBackIncome(accountService.backAllIncome(UserSession
				.getLoginUserId()));// 返利总收益

		// BigDecimal sumIncome = new BigDecimal("0");
		AllStatistics collectionStatistics = allStatisticsService
				.selectColletionForUser(UserSession.getLoginUserId());

		/*
		 * List<UserStatisticsDataMobile> all = accountService
		 * .sumAccountInterest(UserSession.getLoginUserId()); if
		 * (!all.isEmpty()) { for (UserStatisticsDataMobile data : all) {
		 * sumIncome = sumIncome.add(data.getSumWaitInterest()); }
		 * accountvo.setSumIncome(sumIncome.add(accountvo.getBackIncome())); }
		 */
		BigDecimal accountAll = borrowTenderService
				.sumTenderAccountByUserId(UserSession.getLoginUserId());// 总有效投资
		AllStatistics averageCollection = allStatisticsService
				.selectSumColletionUser(UserSession.getLoginUserId());

		// sumIncome =
		// averageCollection.getSumCollection().subtract(accountAll); //
		// 总待收-总有效投资=待收总利息
		// accountvo.setSumIncome(sumIncome.add(accountvo.getBackIncome()));//
		// 累计收益(返利总收益+待收总利息)

		accountvo.setSumIncome(umService.selectUserInfoById(
				UserSession.getLoginUserId()).getSumYield()); // 累计收益（已收,和手机同步）

		accountvo
				.setAverageApr((UserSession.getUserSession().getTypeId() == 31 || UserSession
						.getUserSession().getTypeId() == 27) ? averageCollection
						.getAverageApr().add(new BigDecimal("2.2"))
						: averageCollection.getAverageApr());// 加权收益

		accountvo.setAccountFeel(accountFeelService.selectByUserId(UserSession
				.getLoginUserId())); // 体验金账户
		// 投资账户
		accountvo.setSumCollection(collectionStatistics.getSumCollection());
		accountvo.setCountCollection(collectionStatistics.getCountCollection());

		accountvo.setRecentCollection(this.umService.selectUserInfoById(
				UserSession.getLoginUserId()).getEndYieldMoney()); // 最近待收金额

		accountvo.setRecentCollectionTime(collectionStatistics
				.getRecentCollectionTime());

		// 借款账户
		accountvo.setSumBorrowAccount(borrowService
				.selectSumBorrowAccount(UserSession.getLoginUserId())); // 借款总额
		AllStatistics repayStatistics = allStatisticsService
				.selectRepayForUser(UserSession.getLoginUserId());
		accountvo.setSumRepay(repayStatistics.getSumRepay());
		accountvo.setCountRepay(repayStatistics.getCountRepay());
		accountvo.setRecentRepay(repayStatistics.getRecentRepay());
		accountvo.setRecentRepayTime(repayStatistics.getRecentRepayTime());

		// 现金劵
		CashVolume cash = cashVolumeService.selectByUserId(UserSession
				.getLoginUserId());
		mv.addObject("cash", cash);

		// 是否充值过体验金
		boolean isNew = false;
		List<AccountFeelLog> accountFeelLogs = accountFeelLogService
				.selectByUserIdType(new AccountFeelLog(UserSession
						.getLoginUserId(), "recharge")); // 历史充值记录
		if (EmptyUtil.isEmpty(accountFeelLogs)
				&& !accountAll.toString().equals("0")) {
			isNew = true;// 老用户
		}
		if (EmptyUtil.isNotEmpty(accountFeelLogs)) {
			isNew = true;// 老用户
		}
		mv.addObject("isNew", isNew);
		mv.addObject("accountvo", accountvo);
		SystemMessage sm = new SystemMessage();
		sm.setReceiveUser(UserSession.getLoginUserId());
		sm.setIsLook(0);
		// 查询未读系统消息数量
		mv.addObject("unIsLookCount",
				systemMessageService.selectIsLookCount(sm));
		// 查询未读礼品数量
		mv.addObject("unIsLookCount_gift", 
				giftboxMessageService.selectUnLookCountByUserId(UserSession.getLoginUserId())
				+giftotherMessageService.selectUnLookCountByUserId(UserSession.getLoginUserId())
				+hikesMessageService.selectUnLookCountByUserId(UserSession.getLoginUserId())
				+cashMessageService.selectUnLookCountByUserId(UserSession.getLoginUserId())
				);
		BigDecimal hongbao = this.userService.getUserHongbao(UserSession
				.getLoginUserId());
		if (hongbao == null)
			hongbao = new BigDecimal("0.00");

		mv.addObject("hongbao", hongbao.doubleValue() == 0.00 ? "0"
				: StringUtil.getTwoDecimals(hongbao));

		// 添加理财师等级
		mv.addAllObjects(getFinancialPlannerLevelTitle());

		// 添加对话框状态，是否弹出警告
		Integer userId = UserSession.getLoginUserId();

		// 判断是否有邀请好友注册成功对话框已经弹出
		if (!inviteCodeService.isLeapDialog(userId,
				DialogStatusIndex.inviteFriendsFirstDialogIndex)) {
			int count = userService.getInviteCount(userId);

			// 判断是否有好友注册成功
			if (count > 0) {
				// 邀请的第一位好友注册成功对话框
				mv.addAllObjects(inviteCodeService.getDialogStatusMap(userId,
						DialogStatusIndex.inviteFriendsFirstDialogIndex));
				mv.addObject("inviteUserName",
						userService.getInviteFirstUser(userId).getUsername());
			}
			// 第一位投资成功的好友对话框
		}
		// 判断是否好友第一次投资对话框已经弹出
		if (!inviteCodeService.isLeapDialog(userId,
				DialogStatusIndex.friendsFirstInvestmentDialogIndex)) {

			List<User> inviteUsers = userService.getInviteAllUser(userId);
			BorrowTender bt = isInviteInvestment(inviteUsers);

			// 判断是否有投资过的好友
			if (bt != null) {
				mv.addAllObjects(inviteCodeService.getDialogStatusMap(userId,
						DialogStatusIndex.friendsFirstInvestmentDialogIndex));
				mv.addObject("inviteUserName",
						userService.selectByPrimaryKey(bt.getUserId())
								.getUsername());

				InviteFees infees = financialPlannerService
						.selectByTUserIdAndTenderId(userId, bt.getId());

				mv.addObject("inviteUserName",
						userService.selectByPrimaryKey(bt.getUserId())
								.getUsername());

				mv.addObject("rebate", "未返利");
				if (infees != null) {
					mv.addObject("rebate", infees.getTfees());
				}
			}
		}
		//余额生息昨日收益
		AccountSinabonus as = accountSinabonusService.selectByPrimaryKey(userId);
		mv.addObject("accountSinabonus",as);
		
		//我的账户图片
		Pic pic = picService.selectByPrimaryKey("account_page_banner");
		mv.addObject("pic",pic);
		
		//余额生息七日年化收益率 信息
		try {
			mv.addAllObjects(querySinaService.doQueryFundYieldDayCache());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}

	/**
	 * 转向密码保护页面
	 * 
	 * @return 2014年4月25日 liuyijun
	 */
	@NeedSession
	@RequestMapping(value = "/question", method = RequestMethod.GET)
	public ModelAndView question() {
		ModelAndView mv = new ModelAndView("user/question");
		// 账户信息
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		// 问题选项列表
		List<Linkage> linkages = this.linkageService.selectByTypeId(28);
		if (EmptyUtil.isNotEmpty(linkages)) {
			mv.addObject("linkages", linkages);
		}
		return mv;
	}

	/**
	 * 保存密码保护设置
	 * 
	 * @param question
	 * @param httSession
	 * @return 2014年4月25日 liuyijun
	 */
	@NeedSession
	@RequestMapping(value = "/saveQuestion", method = RequestMethod.POST)
	public ModelAndView saveQuestion(User user) {
		ModelAndView j = new ModelAndView("redirect:/user/safeCenter");
		user.setUserId(UserSession.getLoginUserId());
		this.userService.updateQuestion(user);
		UserSession.getUserSession().setAnswer(user.getAnswer());
		UserSession.getUserSession().setQuestion(user.getQuestion());
		return j;
	}

	/**
	 * 保存密码保护设置
	 * 
	 * @param question
	 * @param httSession
	 * @return 2014年4月25日 liuyijun
	 */
	@NeedSession(logRequestTime = false)
	@RequestMapping(value = "/verificationQuestion", method = RequestMethod.POST)
	@ResponseBody
	public boolean verificationQuestion(User user) {
		String oldQuestion = UserSession.getUserSession().getQuestion();
		String oldAnswer = UserSession.getUserSession().getAnswer();
		if (oldQuestion.equals(user.getQuestion())
				&& oldAnswer.equals(user.getAnswer())) {
			return true;
		}
		return false;
	}

	/**
	 * 转向实名认证页面，如果当前用户已完成实名认证则转向认证成功页面
	 * 
	 * @return 2014年4月25日 liuyijun
	 */
	@NeedSession(returnUrl = "/user/realName")
	@RequestMapping(value = "/realName", method = RequestMethod.GET)
	public ModelAndView realName() {
		ModelAndView mv = new ModelAndView("user/realName");
		// 账户信息
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession().getRealStatus())
				&& UserSession.getUserSession().getRealStatus().equals("1")) {
			mv.setViewName("user/realNameSuccess");
		}
		return mv;
	}

	/**
	 * 实名认证
	 * 
	 * @param user
	 * @param httSession
	 * @return 2014年4月25日 liuyijun
	 * 
	 * 
	 * @author wang.zeyan 2015-01-16 --实名认证接口更换，Sina认证
	 */
	@RequestMapping(value = "/saveRealName", method = RequestMethod.POST)
	@ResponseBody
	public Json saveRealName(User user) {
		Json j = new Json();
		List<UserWithBLOBs> cardUsers = this.userService.selectByCardId(user
				.getCardId());
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
			if (EmptyUtil.isNotEmpty(cardUsers) && cardUsers.size() > 0) {
				j.setMsg("该身份证已被注册");
			} else {
				if (!UserSession.getUserSession().getRealStatus().equals("1")) {
					user.setUserId(UserSession.getLoginUserId());
					if (EmptyUtil.isNotEmpty(user.getCardId())) {
						if (EmptyUtil.isNotEmpty(user.getRealname())) {
							if (VerifyIdCard.verify(user.getCardId())) {
								// 实名认证接口
								try {
									String responseCode = sinaMemberManagerService
											.setRealName(user);

									if (StringUtils.isBlank(responseCode)) {
										j.setMsg("出现异常，请重新认证");
									} else if (SinaMemberParmUtil.success
											.equals(responseCode)
											|| SinaMemberParmUtil.ResponseCode.DUPLICATE_VERIFY
													.equals(responseCode)) {
										user.setRealStatus("1");
										BigDecimal hongbao = this.userService
												.getUserHongbao(UserSession
														.getLoginUserId());
										user.setHongbao(EmptyUtil
												.isEmpty(hongbao) ? new BigDecimal(
												"0") : hongbao);
										this.userService.updateRealName(user);
										j.setSuccess(true);
										if (EmptyUtil.isNotEmpty(UserSession
												.getUserSession())) {
											UserSession.getUserSession()
													.setRealStatus("1");
											UserSession.getUserSession()
													.setRealName(
															user.getRealname());
											UserSession
													.getUserSession()
													.setCardId(user.getCardId());
										}
									} else {
										SinaDic sinaDic = sinaDicService
												.dicLoad(
														SinaDicParmUtil.DicType.ResponseCode,
														responseCode);
										j.setMsg("身份信息验证失败,"
												+ (sinaDic == null ? responseCode
														: sinaDic.getDicMsg()));
									}
								} catch (Exception e) {
									j.setMsg(StringUtils.isBlank(e.getMessage()) ? "出现异常，请重新认证"
											: e.getMessage());
									e.printStackTrace();
								}
							} else {
								j.setMsg("身份证号码错误");
							}
						} else {
							j.setMsg("真实姓名不可为空");
						}
					} else {
						j.setMsg("身份证不可为空");
					}
				} else {
					j.setMsg("您已通过实名认证");
				}
			}
		} else {
			j.setMsg("您已超时或未登录，请登录");
		}
		return j;
	}

	/**
	 * 实名认证(Json版)
	 * 
	 * @param user
	 * @param httSession
	 * @return 2014年4月25日 liuyijun
	 */
	@NeedSession
	@RequestMapping(value = "/saveRealNameJson", method = RequestMethod.POST)
	@ResponseBody
	public Json saveRealNameJson(User user) {
		Json j = new Json();
		List<UserWithBLOBs> cardUsers = this.userService.selectByCardId(user
				.getCardId());
		if (EmptyUtil.isNotEmpty(cardUsers) && cardUsers.size() > 0) {
			throw new BusinessException("8005026");
		} else {
			if (!UserSession.getUserSession().getRealStatus().equals("1")) {
				user.setUserId(UserSession.getLoginUserId());
				if (EmptyUtil.isNotEmpty(user.getCardId())) {
					if (EmptyUtil.isNotEmpty(user.getRealname())) {
						if (VerifyIdCard.verify(user.getCardId())) {
							// 实名认证接口
							boolean checkCardId = false;
							checkCardId = this.realNameRestInvokerFactory
									.getRestInvoker().get(
											"/" + user.getRealname() + "/"
													+ user.getCardId(),
											Boolean.class);
							if (checkCardId) {
								user.setRealStatus("1");
								this.userService.updateRealName(user);
								j.setSuccess(true);
								if (EmptyUtil.isNotEmpty(UserSession
										.getUserSession())) {
									UserSession.getUserSession().setRealStatus(
											"1");
									UserSession.getUserSession().setRealName(
											user.getRealname());
									UserSession.getUserSession().setCardId(
											user.getCardId());
								}
							} else {
								throw new BusinessException("8005027");
							}
						} else {
							throw new BusinessException("8005024", "身份证号码不合法");
						}
					} else {
						throw new BusinessException("8005023", "真实姓名不可为空");
					}
				} else {
					throw new BusinessException("8005022", "身份证不可为空");
				}
			} else {
				throw new BusinessException("8005021", "您已通过实名认证");
			}
		}
		return j;
	}

	/**
	 * 转向认证成功页面
	 * 
	 * @return 2014年4月26日 liuyijun
	 */
	@NeedSession(logRequestTime = false)
	@RequestMapping(value = "/realNameSuccess", method = RequestMethod.GET)
	public ModelAndView userRealNameing() {
		ModelAndView mv = new ModelAndView("user/realNameSuccess");
		// 账户信息
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		return mv;
	}

	/**
	 * 转向更新用户登录密码页面
	 * 
	 * @return 2014年4月26日 liuyijun
	 */
	@NeedSession(returnUrl = "/user/updatePassword")
	@RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
	public ModelAndView updatePassword() {
		ModelAndView mv = new ModelAndView("user/updatePassword");
		// 账户信息
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		return mv;
	}

	/**
	 * 更新用户登录密码
	 * 
	 * @param user
	 * @return 2014年4月26日 liuyijun
	 */
	@NeedSession
	@RequestMapping(value = "/saveNewPassword", method = RequestMethod.POST)
	public ModelAndView saveNewPassword(User user, String oldpassword,
			String repassword) {
		ModelAndView j = new ModelAndView(
				"redirect:/user/updatePasswordSuccess");
		String md5Password = DigestUtils.md5Hex(oldpassword);
		if (UserSession.getUserSession().getPassword().equals(md5Password)) {
			if (user.getPassword().equals(repassword)) {
				user.setUserId(UserSession.getLoginUserId());
				user.setPassword(DigestUtils.md5Hex(user.getPassword()));
				this.userService.updateLoginPassword(user);
				UserSession.getUserSession().setPassword(user.getPassword());
			} else {
				j.setViewName("user/updatePassword");
				j.addObject("errorMsg", "两次密码不一致");
				j.addObject("oldpassword", oldpassword);
				j.addObject("password", user.getPassword());
				j.addObject("repassword", repassword);

				// 账户信息
				ModelAndViewUtil.addAccountToView(j, this.accountService);
			}

		} else {
			j.setViewName("user/updatePassword");
			j.addObject("errorMsg", "原密码错误");
			j.addObject("oldpassword", oldpassword);
			j.addObject("password", user.getPassword());
			j.addObject("repassword", repassword);

			// 账户信息
			ModelAndViewUtil.addAccountToView(j, this.accountService);
		}
		return j;
	}

	/**
	 * 转向更新登录密码成功页面
	 * 
	 * @return 2014年4月26日 liuyijun
	 */
	@NeedSession(logRequestTime = false)
	@RequestMapping(value = "/updatePasswordSuccess", method = RequestMethod.GET)
	public ModelAndView updatePasswordSuccess() {
		ModelAndView mv = new ModelAndView("user/updatePasswordSuccess");
		// 账户信息
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		return mv;
	}

	/**
	 * 更新用户支付密码
	 * 
	 * @param user
	 * @return 2014年4月26日 liuyijun
	 */
	@NeedSession
	@RequestMapping(value = "/saveNewPayPassword", method = RequestMethod.POST)
	public ModelAndView saveNewPayPassword(User user,
			@RequestParam("oldpassword") String oldpassword,
			@RequestParam("repaypassword") String repaypassword) {
		ModelAndView j = new ModelAndView(
				"redirect:/user/updatePayPasswordSuccess");
		String md5Password = DigestUtils.md5Hex(oldpassword);
		if (user.getPaypassword().equals(repaypassword)) {
			if (UserSession.getUserSession().getPayPassword()
					.equals(md5Password)) {
				user.setUserId(UserSession.getLoginUserId());
				user.setPaypassword(DigestUtils.md5Hex(user.getPaypassword()));
				this.userService.updatePayPassword(user);
				UserSession.getUserSession().setPayPassword(
						user.getPaypassword());
			} else {
				j.setViewName("user/updatePayPassword");
				j.addObject("errorMsg", "原密码错误");
				j.addObject("oldpassword", oldpassword);
				j.addObject("paypassword", user.getPaypassword());
				j.addObject("repaypassword", repaypassword);
				// 账户信息
				ModelAndViewUtil.addAccountToView(j, this.accountService);
			}
		} else {
			j.setViewName("user/updatePayPassword");
			j.addObject("errorMsg", "两次密码不一致");
			j.addObject("oldpassword", oldpassword);
			j.addObject("paypassword", user.getPaypassword());
			j.addObject("repaypassword", repaypassword);
			// 账户信息
			ModelAndViewUtil.addAccountToView(j, this.accountService);
		}

		return j;
	}

	/**
	 * 转向更新用户支付密码页面
	 * 
	 * @return 2014年4月26日 liuyijun
	 */
	@NeedSession
	@RequestMapping(value = "/updatePayPassword", method = RequestMethod.GET)
	public ModelAndView updatePayPassword() {
		ModelAndView mv = new ModelAndView("user/updatePayPassword");
		// 账户信息
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		return mv;
	}

	/**
	 * 转向更新用户支付密码成功页面
	 * 
	 * @return 2014年4月26日 liuyijun
	 */
	@NeedSession
	@RequestMapping(value = "/updatePayPasswordSuccess", method = RequestMethod.GET)
	public ModelAndView updatePayPasswordSuccess() {
		ModelAndView mv = new ModelAndView("user/updatePayPasswordSuccess");
		// 账户信息
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		return mv;
	}

	/**
	 * 检测当前登录用户的登录原密码
	 * 
	 * @param password
	 * @return 2014年4月26日 liuyijun
	 */
	@RequestMapping(value = "/checkPassword", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkPassword(@RequestParam("password") String password) {
		boolean j = false;
		String md5Password = DigestUtils.md5Hex(password);

		if (UserSession.getUserSession().getPassword().equals(md5Password)) {
			j = true;
		}

		return j;
	}

	/**
	 * 检测当前登录用户的原支付密码
	 * 
	 * @param payPassword
	 * @return 2014年4月26日 liuyijun
	 */
	@RequestMapping(value = "/checkPayPassword", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkPayPassword(
			@RequestParam("payPassword") String payPassword) {
		boolean result = false;
		String md5Password = DigestUtils.md5Hex(payPassword);

		if (UserSession.getUserSession().getPayPassword().equals(md5Password)) {
			result = true;
		}

		return result;
	}

	/**
	 * 转向更换手机选择方式页面
	 * 
	 * @return 2014年4月26日 liuyijun
	 */
	@NeedSession
	@RequestMapping(value = "/updatePhone", method = RequestMethod.GET)
	public ModelAndView updatePhone() {
		ModelAndView mv = new ModelAndView("user/updatePhone");
		// 账户信息
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		return mv;
	}

	/**
	 * 转向更换手机通过原手机验证页面
	 * 
	 * @return 2014年4月26日 liuyijun
	 */
	@NeedSession(returnUrl = "/user/updatePhoneByOldPhoneFirst")
	@RequestMapping(value = "/updatePhoneByOldPhoneFirst", method = RequestMethod.GET)
	public ModelAndView updatePhoneByOldPhoneFirst() {
		ModelAndView mv = new ModelAndView("user/updatePhoneByOldPhoneFirst");
		// 账户信息
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		mv.addObject("oldPhoneShow", StringUtil.doPhoneNumber(UserSession
				.getUserSession().getPhone()));
		mv.addObject("oldPhone", UserSession.getUserSession().getPhone());
		return mv;
	}

	/**
	 * 修改手机通过原手机验证下一步
	 * 
	 * @param vcode
	 * @param cardId
	 * @param request
	 * @return 2014年4月26日 liuyijun
	 */
	@NeedSession
	@RequestMapping(value = "/updatePhoneByOldPhoneNext", method = RequestMethod.POST)
	public ModelAndView updatePhoneByOldPhoneNext(String checkCode,
			String cardId, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user/updatePhoneByOldPhoneSecond");
		// 账户信息
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		if (VerifyCodeUtils.checkAndRemoveVerifyCode(request.getSession(),
				checkCode)) {
			if (EmptyUtil.isNotEmpty(UserSession.getUserSession().getCardId())) {
				if (UserSession.getUserSession().getCardId().equals(cardId)) {
					request.getSession().setAttribute("checkCardId", true);
					VerifyCodeUtils.removeVerifyCode(request.getSession());// 删除session中的验证码信息
					HttpSessionTool.removeVerifyCodeInfo(request.getSession());// 清除Session中验证码请求时间间隔信息，防止下个页面无法请求
				} else {
					mv.setViewName("user/updatePhoneByOldPhoneFirst");
					mv.addObject("errorMsg", "身份证错误");
					mv.addObject("oldPhoneShow", StringUtil
							.doPhoneNumber(UserSession.getUserSession()
									.getPhone()));
					mv.addObject("oldPhone", UserSession.getUserSession()
							.getPhone());

					mv.addObject("checkCode", checkCode);
					mv.addObject("cardId", cardId);
				}
			} else {// 没有实名认证
				mv.setViewName("redirect:/user/userRealName");
			}
		} else {
			mv.setViewName("user/updatePhoneByOldPhoneFirst");
			mv.addObject("errorMsg", "验证码错误");
			mv.addObject("oldPhoneShow", StringUtil.doPhoneNumber(UserSession
					.getUserSession().getPhone()));
			mv.addObject("oldPhone", UserSession.getUserSession().getPhone());
			mv.addObject("checkCode", checkCode);
			mv.addObject("cardId", cardId);
		}
		return mv;
	}

	/**
	 * 更新用户手机
	 * 
	 * @param user
	 * @param vcode
	 * @param request
	 * @return 2014年4月26日 liuyijun
	 */
	@NeedSession
	@RequestMapping(value = "/saveNewPhone", method = RequestMethod.POST)
	public ModelAndView saveNewPhone(User user, String vcode,
			HttpServletRequest request) {
		ModelAndView j = new ModelAndView("redirect:/user/updatePhoneSuccess");
		if (EmptyUtil.isNotEmpty(request.getSession().getAttribute(
				"checkCardId"))) {
			if (this.userService.checkRegister(user.getPhone())) {
				if (VerifyCodeUtils.checkAndRemoveVerifyCode(
						request.getSession(), vcode)) {
					user.setUserId(UserSession.getLoginUserId());
					this.userService.updatePhone(user);
					UserSession.getUserSession().setPhone(user.getPhone());
				} else {
					j.setViewName("user/updatePhoneByOldPhoneSecond");
					j.addObject("errorMsg", "验证码错误");
					ModelAndViewUtil.addAccountToView(j, this.accountService);
				}
			} else {
				j.setViewName("user/updatePhoneByOldPhoneSecond");
				j.addObject("errorMsg", "该手机号已注册");
				ModelAndViewUtil.addAccountToView(j, this.accountService);
			}
		} else {
			j.setViewName("redirect:/user/updatePhoneByOldPhoneFirst");
			j.addObject("errorMsg", "身份证未验证");
		}

		return j;
	}

	@NeedSession(logRequestTime = false)
	@RequestMapping(value = "/updatePhoneSuccess", method = RequestMethod.GET)
	public ModelAndView updatePhoneByOldPhoneSuccess() {
		ModelAndView mv = new ModelAndView("user/updatePhoneByOldPhoneSuccess");
		// 账户信息
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		return mv;
	}

	/**
	 * 验证身份证是否和用户已认证的身份证一致
	 * 
	 * @param cardId
	 * @return 2014年5月6日 liuyijun
	 */
	@RequestMapping("/checkCardId")
	@ResponseBody
	public boolean checkCardId(@RequestParam("cardId") String cardId,
			HttpServletRequest request) {
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
			if (EmptyUtil.isNotEmpty(UserSession.getUserSession().getCardId())) {
				if (cardId.equals(UserSession.getUserSession().getCardId())) {
					request.getSession().setAttribute(
							"canGetCodeForFindPayPwd", true);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 转向用户修改邮箱的原邮箱页面
	 * 
	 * @return 2014年4月28日 liuyijun
	 */
	@NeedSession
	@RequestMapping(value = "/oldEmail", method = RequestMethod.GET)
	public ModelAndView oldEmail() {
		ModelAndView mv = new ModelAndView("user/oldEmail");
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		if (EmptyUtil.isEmpty(UserSession.getUserSession().getEmail())) {
			mv.setViewName("user/addEmail");
		} else if (EmptyUtil
				.isNotEmpty(UserSession.getUserSession().getEmail())
				&& UserSession.getUserSession().getEmailStatus().equals("0")) {
			mv.setViewName("redirect:/user/checkEmail");
		} else {
			String email = UserSession.getUserSession().getEmail();
			String fEmail = email.substring(0, 3);
			String lEmail = email.substring(email.indexOf("@"));
			mv.addObject("email", fEmail + "****" + lEmail);
		}
		return mv;
	}

	@RequestMapping("/checkUserOldEmail")
	@ResponseBody
	public boolean checkUserOldEmai(@RequestParam("oldEmail") String oldEmail) {
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
			if (oldEmail.equals(UserSession.getUserSession().getEmail())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 原邮箱提交
	 * 
	 * @param user
	 * @return 2014年4月28日 liuyijun
	 */
	@NeedSession
	@RequestMapping(value = "/oldEmailSubmit", method = RequestMethod.POST)
	public ModelAndView oldEmailSubmit(User user) {
		ModelAndView mv = new ModelAndView("user/newEmail");
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		if (!user.getEmail().equals(UserSession.getUserSession().getEmail())) {
			mv.setViewName("user/oldEmail");
			mv.addObject("errorMsg", "原邮箱输入错误");
			mv.addObject("userEmail", user.getEmail());
		}
		return mv;
	}

	/**
	 * 新邮箱提交
	 * 
	 * @param user
	 * @param vcode
	 * @param request
	 * @return 2014年4月28日 liuyijun
	 */
	@RequestMapping(value = "/newEmailSubmit", method = RequestMethod.POST)
	@ResponseBody
	public Json newEmailSubmit(User user, String vcode,
			HttpServletRequest request) {
		Json mv = new Json();
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
			if (userService.checkRegister(user.getEmail())) {
				user.setUserId(UserSession.getLoginUserId());
				this.userService.updateEmail(user);
				UserSession.getUserSession().setEmail(user.getEmail());
				UserSession.getUserSession().setEmailStatus("0");
				mv.setSuccess(true);
			} else {
				mv.setMsg("该邮箱已注册");
			}
		} else {
			mv.setMsg("您已超时或未登录，请登录");
		}
		return mv;
	}

	/**
	 * 转向邮箱验证页面
	 * 
	 * @return 2014年4月28日 liuyijun
	 */
	@NeedSession(returnUrl = "/user/checkEmail")
	@RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
	public ModelAndView checkEmail() {
		ModelAndView mv = new ModelAndView("user/checkEmail");
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		UserWithBLOBs user = this.userService.selectByPrimaryKey(UserSession
				.getLoginUserId());
		if (EmptyUtil.isEmpty(user.getEmail())) {
			mv.setViewName("user/addEmail");
		} else if (EmptyUtil.isNotEmpty(user.getEmail())
				&& user.getEmailStatus().equals("1")) {
			mv.setViewName("user/checkEmailSuccess");
		} else {
			mv.addObject("email", user.getEmail());
		}

		return mv;
	}

	/**
	 * 验证前修改邮箱
	 * 
	 * @return 2014年6月27日 liuyijun
	 */
	@NeedSession(returnUrl = "/user/modifyEmailPage")
	@RequestMapping(value = "/modifyEmailPage", method = RequestMethod.GET)
	public ModelAndView modifyEmailPage() {
		ModelAndView mv = new ModelAndView("user/modifyEmail");
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		UserWithBLOBs user = this.userService.selectByPrimaryKey(UserSession
				.getLoginUserId());
		if (EmptyUtil.isEmpty(user.getEmail())) {
			mv.setViewName("user/addEmail");
		} else if (EmptyUtil.isNotEmpty(user.getEmail())
				&& user.getEmailStatus().equals("1")) {
			mv.setViewName("redirect:/user/oldEmail");
		}
		return mv;
	}

	/**
	 * 验证前修改邮箱
	 * 
	 * @param email
	 * @param request
	 * @return 2014年6月27日 liuyijun
	 */
	@RequestMapping(value = "/modifyEmail", method = RequestMethod.POST)
	@ResponseBody
	public Json modifyEmail(@RequestParam("email") String email,
			HttpServletRequest request) {
		Json mv = new Json();
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
			if (this.userService.checkRegister(email)) {
				this.addOrModifyEmail(email, request, mv);
			} else {
				mv.setMsg("该邮箱已注册");
			}
		} else {
			mv.setMsg("您已超时或未登录，请登录");
		}

		return mv;
	}

	/**
	 * 验证或添加邮箱提交
	 * 
	 * @param email
	 * @return 2014年4月28日 liuyijun
	 */
	@RequestMapping(value = "/checkEmailSubmit", method = RequestMethod.POST)
	@ResponseBody
	public Json checkEmailSubmit(@RequestParam("email") String email,
			HttpServletRequest request) {
		Json j = new Json();
		if (EmptyUtil.isNotEmpty(email)) {
			if (EmptyUtil.isEmpty(UserSession.getUserSession().getEmail())) {
				if (userService.checkRegister(email)) {
					this.addOrModifyEmail(email, request, j);
				} else {
					j.setMsg("该邮箱已注册");
				}
			} else if (email.equals(UserSession.getUserSession().getEmail())) {
				this.sendVerifyEmailMethod(request, email);
				j.setSuccess(true);
			} else {
				j.setMsg("您填写的邮箱与您注册时的邮箱不一致");
			}
		} else {
			j.setMsg("邮箱不可为空");
		}
		return j;
	}

	/**
	 * 再次发送验证邮件
	 * 
	 * @return 2014年4月28日 liuyijun
	 */
	@RequestMapping(value = "/agenSendVerifyEmail", method = RequestMethod.GET)
	@ResponseBody
	public Json agenSendVerifyEmail(HttpServletRequest request) {
		Json j = new Json();
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
			if (EmptyUtil.isNotEmpty(UserSession.getUserSession().getEmail())
					&& UserSession.getUserSession().getEmailStatus()
							.equals("0")) {
				this.sendVerifyEmailMethod(request, UserSession
						.getUserSession().getEmail());
				j.setSuccess(true);
			} else {
				j.setMsg("您已通过邮箱验证");
			}
		} else {
			j.setMsg("您已超时，请重新登录");
		}

		return j;
	}

	/**
	 * 验证邮件发送成功后跳转页面
	 * 
	 * @return 2014年6月13日 liuyijun
	 */
	@RequestMapping(value = "/sendedVerifyEmail")
	@NeedSession
	public ModelAndView sendedVerifyEmail() {
		ModelAndView j = new ModelAndView("user/sendVerifyEmail");
		ModelAndViewUtil.addAccountToView(j, this.accountService);
		String email = UserSession.getUserSession().getEmail();
		String openEmailAddress = "";
		if (email.endsWith("@qq.com")) {
			openEmailAddress = "http://mail.qq.com";
		} else if (email.endsWith("@163.com")) {
			openEmailAddress = "http://mail.163.com";
		} else if (email.endsWith("@aliyun.com")) {
			openEmailAddress = "http://mail.aliyun.com";
		}
		j.addObject("openEmailAddress", openEmailAddress);

		j.addObject("email", email);
		return j;
	}

	/**
	 * 邮箱验证成功页面
	 * 
	 * @return 2014年4月28日 liuyijun
	 */
	@NeedSession(returnUrl = "/user/checkEmailSuccess")
	@RequestMapping(value = "/checkEmailSuccess", method = RequestMethod.GET)
	public ModelAndView checkEmailSuccess() {
		ModelAndView mv = new ModelAndView("user/checkEmailSuccess");
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		if (EmptyUtil.isEmpty(UserSession.getUserSession().getEmail())) {
			mv.setViewName("user/account");
			mv.addObject("errorMsg", "请填写您的邮箱");
		} else if (!UserSession.getUserSession().getEmailStatus().equals("1")) {
			mv.setViewName("user/checkEmail");
		}
		mv.addObject("email", UserSession.getUserSession().getEmail());
		return mv;
	}

	/**
	 * 转向安全中心
	 * 
	 * @return 2014年5月6日 liuyijun
	 */
	@NeedSession(returnUrl = "/user/safeCenter")
	@RequestMapping(value = "/safeCenter", method = RequestMethod.GET)
	public ModelAndView safeCenter() {
		ModelAndView mv = new ModelAndView("user/safeCenter");
		ModelAndViewUtil.addAccountToView(mv, accountService);
		UserWithBLOBs user = this.userService.selectByPrimaryKey(UserSession
				.getLoginUserId());
		mv.addObject(
				"emailStatus",
				EmptyUtil.isNotEmpty(user.getEmailStatus()) ? user
						.getEmailStatus() : '0');
		return mv;
	}

	/**
	 * 转向站内信
	 * 
	 * @return 2014年5月6日 liuyijun
	 */
	@NeedSession(returnUrl = "/user/message")
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public ModelAndView message(
			@RequestParam(value = "type", required = false, defaultValue = "noRead") String type,
			@RequestParam(value = "currentPage", required = false) Integer currentPage) {
		ModelAndView mv = new ModelAndView("user/message");
		ModelAndViewUtil.addAccountToView(mv, accountService);
		int pageSize = 10;
		// 未读站内信
		Integer noReadCount = this.messageService
				.selectNoReadCount((Integer) (UserSession.getLoginUserId()));
		if (EmptyUtil.isNotEmpty(noReadCount)) {
			mv.addObject("noReadCount", noReadCount);
		}

		// 所有站内信
		Integer allCount = this.messageService
				.selectAllCount((Integer) (UserSession.getLoginUserId()));
		if (EmptyUtil.isNotEmpty(allCount)) {
			mv.addObject("allCount", allCount);
		}

		Integer readCount = allCount.intValue() - noReadCount.intValue();
		if (EmptyUtil.isNotEmpty(allCount)) {
			mv.addObject("readCount", readCount);
		}

		PageSearch pageSearch = new PageSearch();
		PageResult<Message> result = new PageResult<Message>();
		Message m = new Message();
		if (EmptyUtil.isNotEmpty(type) && type.equals("read")) {
			m.setStatus(1);
			mv.addObject("type", "read");
		} else {
			m.setStatus(0);
			mv.addObject("type", "noRead");
		}

		if (EmptyUtil.isNotEmpty(currentPage)) {
			if (type.equals("noRead")) {
				if (currentPage > 1 && noReadCount <= pageSize) {
					currentPage = 1;
				}
			} else {
				if (currentPage > 1 && readCount <= pageSize) {
					currentPage = 1;
				}
			}
			mv.addObject("currentPage", currentPage);
			pageSearch.setPage(currentPage);
		} else {
			mv.addObject("currentPage", 1);
		}

		m.setReceiveUser(UserSession.getLoginUserId());
		pageSearch.setEntity(m);
		pageSearch.setRows(pageSize);
		List<Message> messages = this.messageService
				.selectMessageByUserListPage(pageSearch);
		result.setRows(messages);
		result.setTotal(pageSearch.getTotalResult());
		mv.addObject("page", result);

		int totalPages = result.getTotal() % pageSize == 0 ? result.getTotal()
				/ pageSize : result.getTotal() / pageSize + 1;

		mv.addObject("totalPages", totalPages);

		mv.addObject("pageSize", pageSize);

		return mv;
	}

	/**
	 * 转向个人基本信息
	 * 
	 * @return 2014年5月6日 liuyijun
	 */
	@NeedSession(returnUrl = "/user/info")
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView info() {
		ModelAndView mv = new ModelAndView("user/info");
		ModelAndViewUtil.addAccountToView(mv, accountService);
		// 婚姻状况
		List<Linkage> marriages = this.linkageService.selectByTypeId(1);
		if (EmptyUtil.isNotEmpty(marriages)) {
			mv.addObject("marriages", marriages);
		}

		// 学历状况
		List<Linkage> educations = this.linkageService.selectByTypeId(3);
		if (EmptyUtil.isNotEmpty(educations)) {
			mv.addObject("educations", educations);
		}

		// 公司行业
		List<Linkage> industries = this.linkageService.selectByTypeId(11);
		if (EmptyUtil.isNotEmpty(industries)) {
			mv.addObject("industries", industries);
		}

		// 公司性质
		List<Linkage> comTypes = this.linkageService.selectByTypeId(10);
		if (EmptyUtil.isNotEmpty(comTypes)) {
			mv.addObject("comTypes", comTypes);
		}
		// 月收入
		List<Linkage> incomes = this.linkageService.selectByTypeId(4);
		if (EmptyUtil.isNotEmpty(incomes)) {
			mv.addObject("incomes", incomes);
		}

		// 职位
		List<Linkage> companyOffices = this.linkageService.selectByTypeId(13);
		if (EmptyUtil.isNotEmpty(companyOffices)) {
			mv.addObject("companyOffices", companyOffices);
		}

		mv.addObject("mobile", StringUtil.doPhoneNumber(UserSession
				.getUserSession().getPhone()));
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession().getCardId())) {
			String card = UserSession.getUserSession().getCardId();
			mv.addObject("year", card.substring(6, 10));
			mv.addObject("month", card.substring(10, 12));
			mv.addObject("day", card.substring(12, 14));
		}

		List<AttestationType> types = this.attestationTypeService.selectAll();
		if (EmptyUtil.isNotEmpty(types)) {
			mv.addObject("types", types);
		}

		UserInfo info = this.userInfoService.selectByUserId(UserSession
				.getLoginUserId());
		if (EmptyUtil.isNotEmpty(info)) {
			mv.addObject("info", info);
		}

		UserWithBLOBs user = this.userService.selectByPrimaryKey(UserSession
				.getLoginUserId());
		mv.addObject(
				"emailState",
				EmptyUtil.isNotEmpty(user.getEmailStatus()) ? user
						.getEmailStatus() : '0');

		return mv;
	}

	/**
	 * 获取用户上传资料列表数据
	 * 
	 * @return 2014年4月28日 liuyijun
	 */
	@RequestMapping(value = "/attestation", method = RequestMethod.POST)
	@ResponseBody
	public PageResult<Attestation> attestation(PageSearch pageSearch) {
		PageResult<Attestation> pageResult = new PageResult<Attestation>();
		if (EmptyUtil.isNotEmpty(UserSession.getLoginUserId())) {
			Map<String, Integer> search = new HashMap<String, Integer>();
			search.put("userId", UserSession.getLoginUserId());
			pageSearch.setEntity(search);
			pageSearch.setRows(5);
			List<Attestation> attestations = this.attestationService
					.selectByUserId(pageSearch);
			pageResult.setRows(attestations);
			pageResult.setTotal(pageSearch.getTotalResult());
		} else {
			pageResult.setRows(null);
			pageResult.setTotal(0);
		}

		return pageResult;
	}

	/**
	 * 用户上传证明资料
	 * 
	 * @param multipartRequest
	 * @param attestation
	 * @return
	 * @throws IOException
	 *             2014年5月7日 liuyijun
	 */
	@NeedSession
	@RequestMapping(value = "/uploadAttestation", method = RequestMethod.POST)
	@ResponseBody
	public Json uploadAttestation(MultipartHttpServletRequest multipartRequest)
			throws IOException {
		Json j = new Json();
		MultipartFile multipartFile = multipartRequest.getFile("files");
		if (EmptyUtil.isNotEmpty(multipartFile) && multipartFile.getSize() > 0) {
			MultipartEntityBuilder multipartEntity = MultipartEntityBuilderUtil
					.createMultipartEntityBuilderByMultipartFile(multipartFile);
			String fileId = this.attestationService
					.uploadAttestation(multipartEntity);
			multipartRequest.getSession().setAttribute("uploadAttestationId",
					fileId);
			j.setSuccess(true);
		}
		return j;
	}

	/**
	 * 发标上传图片
	 * 
	 * @param multipartRequest
	 * @param attestation
	 * @return
	 * @throws Exception
	 *             版本2
	 */
	@NeedSession
	@RequestMapping(value = "/borrowUploadAttestationV2", method = RequestMethod.POST)
	@ResponseBody
	public Json borrowUploadAttestationV2(
			MultipartHttpServletRequest multipartRequest,
			HttpServletRequest request, String width, String height,
			String name, Integer id, Integer carId, String type)
			throws Exception {
		Json j = new Json();
		MultipartFile multipartFile = multipartRequest.getFile("files");
		if (EmptyUtil.isNotEmpty(multipartFile) && multipartFile.getSize() > 0) {
			MultipartEntityBuilder multipartEntity = MultipartEntityBuilderUtil
					.createMultipartEntityBuilderByMultipartFile(multipartFile);

			/*
			 * MessageVO vo = this.attestationService.borrowUploadAttestation(
			 * multipartEntity, input, Integer.parseInt(width),
			 * Integer.parseInt(height), extName, serverpath);
			 */

			MessageVO vo = this.attestationService.borrowUploadAttestation(
					multipartEntity, id, carId, type);

			multipartRequest.getSession().setAttribute(name, vo.getId());
			j.setSuccess(true);
		}
		return j;
	}

	/**
	 * 发标上传图片
	 * 
	 * @param multipartRequest
	 * @param attestation
	 * @return
	 * @throws Exception
	 */
	@NeedSession
	@RequestMapping(value = "/borrowUploadAttestation", method = RequestMethod.POST)
	@ResponseBody
	public Json borrowUploadAttestation(
			MultipartHttpServletRequest multipartRequest,
			HttpServletRequest request, String width, String height, String name)
			throws Exception {
		Json j = new Json();
		MultipartFile multipartFile = multipartRequest.getFile("files");
		if (EmptyUtil.isNotEmpty(multipartFile) && multipartFile.getSize() > 0) {
			MultipartEntityBuilder multipartEntity = MultipartEntityBuilderUtil
					.createMultipartEntityBuilderByMultipartFile(multipartFile);

			/*
			 * MessageVO vo = this.attestationService.borrowUploadAttestation(
			 * multipartEntity, input, Integer.parseInt(width),
			 * Integer.parseInt(height), extName, serverpath);
			 */

			MessageVO vo = this.attestationService
					.borrowUploadAttestation(multipartEntity);

			multipartRequest.getSession().setAttribute(name, vo.getId());
			j.setSuccess(true);
		}
		return j;
	}

	/**
	 * 添加资料证明
	 * 
	 * @param attestation
	 * @param request
	 * @return
	 * @throws IOException
	 *             2014年5月8日 liuyijun
	 */
	@NeedSession()
	@RequestMapping(value = "/addAttestation", method = RequestMethod.POST)
	@ResponseBody
	public Json addAttestation(Attestation attestation,
			HttpServletRequest request) throws IOException {
		Json j = new Json();
		String fileId = (String) request.getSession().getAttribute(
				"uploadAttestationId");

		if (EmptyUtil.isNotEmpty(fileId)) {
			attestation.setUserId(UserSession.getLoginUserId());
			attestation.setLitpic(fileId);
			this.attestationService.insertSelective(attestation);
			request.getSession().removeAttribute("uploadAttestationId");
			j.setSuccess(true);
		}
		return j;
	}

	/**
	 * 保存用户基本信息
	 * 
	 * @param userInfo
	 * @return 2014年5月8日 liuyijun
	 */
	@NeedSession(returnUrl = "/user/info")
	@RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
	public ModelAndView saveInfo(UserInfoVO userInfo) {
		ModelAndView mv = new ModelAndView("user/saveInfoSuccess");
		ModelAndViewUtil.addAccountToView(mv, accountService);
		userInfo.setUserId(UserSession.getLoginUserId());
		this.userService.saveUserInfo(userInfo);
		return mv;
	}

	/**
	 * 找回密码第一页
	 * 
	 * @return 2014年5月26日 liuyijun
	 */
	@RequestMapping(value = "/findPwdFirst", method = RequestMethod.GET)
	public ModelAndView findPwdFirst() {
		ModelAndView mv = new ModelAndView("user/findPwdFirst");
		return mv;
	}

	/**
	 * 找回密码第二步骤
	 * 
	 * @return 2014年5月26日 liuyijun
	 */
	@RequestMapping(value = "/findPwdSecond", method = RequestMethod.POST)
	public ModelAndView findPwdSecond(String value, String vcode,
			HttpSession httpSession) {
		ModelAndView mv = new ModelAndView("user/findPwdSecond");
		if (VerifyCodeUtils.check(httpSession, vcode)) {
			List<UserWithBLOBs> u = this.userService.selectByLogin(value);
			if (EmptyUtil.isNotEmpty(u)) {
				if (u.size() == 1) {
					HttpSessionTool.recordRegisteringUserInfo(httpSession, u
							.get(0).getUsername());
					httpSession.setAttribute("findPwdUser", u.get(0));
				} else {
					mv.setViewName("user/findPwdFirst");
					mv.addObject("errorMsg", "您输入的用户信息有误");
				}
			} else {
				mv.setViewName("user/findPwdFirst");
				mv.addObject("errorMsg", "您输入的用户信息有误");
			}
		} else {
			mv.setViewName("user/findPwdFirst");
			mv.addObject("errorMsg", "验证码错误");
		}
		return mv;
	}

	/**
	 * 找回密码第三步骤
	 * 
	 * @return 2014年5月26日 liuyijun
	 */
	@RequestMapping(value = "/findPwdThird", method = RequestMethod.POST)
	public ModelAndView findPwdThird(String mobile, String vcode,
			HttpSession httpSession) {
		ModelAndView mv = new ModelAndView("user/findPwdThird");
		if (VerifyCodeUtils.check(httpSession, vcode)) {
			UserWithBLOBs u = (UserWithBLOBs) httpSession
					.getAttribute("findPwdUser");
			if (EmptyUtil.isNotEmpty(u)) {
				if (mobile.equals(u.getPhone())) {
					httpSession.setAttribute("canFindPwd", true);
				} else {
					mv.setViewName("user/findPwdSecond");
					mv.addObject("errorMsg", "手机号码错误");
				}
			} else {
				mv.setViewName("user/findPwdSecond");
				mv.addObject("errorMsg", "请先完成第一步");
			}

		} else {
			mv.setViewName("user/findPwdSecond");
			mv.addObject("errorMsg", "验证码错误");
		}
		return mv;
	}

	/**
	 * 找回密码成功
	 * 
	 * @return 2014年5月26日 liuyijun
	 */
	@RequestMapping(value = "/findPwdLast", method = RequestMethod.POST)
	public ModelAndView findPwdLast(String pwd, HttpSession httpSession) {
		ModelAndView mv = new ModelAndView("user/findPwdLast");
		if (EmptyUtil.isNotEmpty(pwd.trim())) {
			boolean canFindPwd = (Boolean) httpSession
					.getAttribute("canFindPwd");
			UserWithBLOBs user = (UserWithBLOBs) httpSession
					.getAttribute("findPwdUser");
			if (canFindPwd && EmptyUtil.isNotEmpty(user)) {
				User u = new User();
				u.setUserId(user.getUserId());
				u.setPassword(DigestUtils.md5Hex(pwd));
				this.userService.updateLoginPassword(u);
				HttpSessionTool.removeRegisteringUserInfo(httpSession);
				user.setPaypassword(DigestUtils.md5Hex(pwd));
				httpSession.setAttribute("findPwdUser", user);// 更新Session中保存的找回密码的用户信息
				// httpSession.removeAttribute("findPwdUser");
			} else {
				mv.setViewName("user/findPwdThird");
				mv.addObject("errorMsg", "请先完成前两个步骤");
			}
		} else {
			mv.setViewName("user/findPwdThird");
			mv.addObject("errorMsg", "新密码不能为空");
		}

		return mv;
	}

	/**
	 * 找回密码后转到安全中心
	 * 
	 * @param httpSession
	 * @return 2014年5月26日 liuyijun
	 */
	@RequestMapping(value = "/findPwdToSafeCenter", method = RequestMethod.GET)
	public ModelAndView findPwdToSafeCenter(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/user/safeCenter");
		UserWithBLOBs user = (UserWithBLOBs) request.getSession().getAttribute(
				"findPwdUser");
		String lastIp = ModelAndViewUtil.getIpAddr(request);

		if (EmptyUtil.isNotEmpty(user)) {
			if (EmptyUtil.isNotEmpty(user.getIslock())
					&& user.getIslock().intValue() == 1) {
				mv.setViewName("redirect:/goLogin");
			} else {
				UserTrack track = this.trackService
						.selectByLastLogin(new UserTrack(user.getUserId()
								.toString(), "site"));
				if (EmptyUtil.isNotEmpty(track)
						&& EmptyUtil.isEmpty(track.getOutTime())) {
					if (!lastIp.equals(user.getLastip())) {
						throw new BusinessException("8005032");
					}
				} else {
					UserSession userSession = this
							.createSessionByLoginedUser(user);
					this.userService.userLogin(user, request);
					HttpSessionTool.doLogin(request.getSession(), userSession);
				}

				request.getSession().removeAttribute("findPwdUser");
			}
		} else {
			mv.setViewName("redirect:/goLogin");
		}
		return mv;
	}

	/**
	 * 找回密码后登录
	 * 
	 * @param httpSession
	 * @return 2014年5月26日 liuyijun
	 */
	@RequestMapping(value = "/findPwdGoLogin", method = RequestMethod.GET)
	public ModelAndView findPwdGoLogin(HttpSession httpSession) {
		ModelAndView mv = new ModelAndView("redirect:/goLogin");
		httpSession.removeAttribute("findPwdUser");
		return mv;
	}

	/**
	 * 申请VIP页面
	 * 
	 * @return 2014年6月13日 liuyijun
	 */
	@RequestMapping(value = "/applyVIP", method = RequestMethod.GET)
	@NeedSession(returnUrl = "/user/applyVIP")
	public ModelAndView applyVIP() {
		ModelAndView mv = ModelAndViewUtil
				.createModelAndViewAndAddAccountToView(accountService,
						"user/applyVIP");
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession().getVipStatus())
				&& UserSession.getUserSession().getVipStatus() == 1) {
			mv.setViewName("redirect:/user/safeCenter");
		} else {
			Account account = accountService.selectByUserId(UserSession
					.getLoginUserId());
			if (EmptyUtil.isNotEmpty(account)) {
				mv.addObject("account", account);
			}
			// 是否显示余额不足信息
			BigDecimal hongbao = this.userService.getUserHongbao(UserSession
					.getLoginUserId());
			mv.addObject("hongbao", hongbao);
			boolean showInfo = hongbao.add(account.getUseMoney()).intValue() > 150 ? false
					: true;
			mv.addObject("showInfo", showInfo);
		}

		return mv;
	}

	/**
	 * 申请VIP
	 * 
	 * @return 2014年6月13日 liuyijun
	 */
	@RequestMapping(value = "/becomeVIP", method = RequestMethod.POST)
	@ResponseBody
	public Json becomeVIP(@RequestParam("useHongbao") String useHongbao) {
		Json mv = new Json();
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
			Account account = accountService.selectByUserId(UserSession
					.getLoginUserId());
			if (EmptyUtil.isNotEmpty(account)) {
				this.userService.becomeVIP(account, mv, useHongbao, vipPrice);
				mv.setSuccess(true);
			} else {
				mv.setMsg("您的账户信息错误");
			}
		} else {
			mv.setMsg("您已超时，请重新登录");
		}
		return mv;
	}

	/**
	 * 转向用户头像选择页面
	 * 
	 * @return 2014年6月23日 liuyijun
	 */
	@NeedSession
	@RequestMapping("/headPicPage")
	public ModelAndView headPicPage() {
		ModelAndView mv = ModelAndViewUtil
				.createModelAndViewAndAddAccountToView(accountService,
						"user/choseAvantar");
		return mv;
	}

	/**
	 * 用户头像上传
	 * 
	 * @param userInfo
	 * @return 2014年5月8日 liuyijun
	 */
	@RequestMapping(value = "/headPic/{picId}", method = RequestMethod.POST)
	@ResponseBody
	public Json headPic(@PathVariable("picId") String picId) {
		Json mv = new Json();
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
			UserWithBLOBs user = new UserWithBLOBs();
			user.setUserId(UserSession.getLoginUserId());
			user.setUserIcon(picId + "_middle.jpg");
			this.userService.updateHeadPic(user);
			mv.setSuccess(true);
		} else {
			mv.setMsg("您已超时或未登录，请登录");
		}
		return mv;
	}
	
	@RequestMapping(value = "/uploadLocalPic", method = RequestMethod.POST)
	@ResponseBody
	public Json uploadLocalPic(MultipartHttpServletRequest  multipartRequest) throws IOException {
		Json json = new Json();
		MultipartFile multipartFile = multipartRequest.getFile("upfile");
		MultipartEntityBuilder multipartEntity = MultipartEntityBuilderUtil.createMultipartEntityBuilderByMultipartFile(multipartFile);

		String picPath = null;
		
		//文件不为空，大于0M 小于2M
		if(EmptyUtil.isNotEmpty(multipartFile) && multipartFile.getSize() > 0  && multipartFile.getSize() < (2 * 1024 * 1000) ){
			picPath = this.attestationService.uploadAttestation(multipartEntity);
			if(StringUtils.isNotBlank(picPath)){
				UserWithBLOBs user = userService.selectByPrimaryKey(UserSession.getLoginUserId());
				user.setUserIcon("http://file.8jielicai.com/ori/"+picPath);
				userService.updateHeadPic(user);
				json.setSuccess(true);
			}else{
				json.setMsg("文件上传发生未知错误");
				json.setSuccess(false);
			}
		}else{
			json.setMsg("文件大小不符合");
			json.setSuccess(false);
		}
		return json;
	}
	

	/**
	 * 转向认证手机页面
	 * 
	 * @return 2014年6月24日 liuyijun
	 */
	@RequestMapping(value = "/checkPhoneState", method = RequestMethod.GET)
	@NeedSession(returnUrl = "/user/checkPhoneState")
	public ModelAndView checkPhoneState() {
		ModelAndView mv = ModelAndViewUtil
				.createModelAndViewAndAddAccountToView(accountService,
						"user/checkPhoneState");
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession().getPhoneStatus())
				&& UserSession.getUserSession().getPhoneStatus().equals("1")) {
			mv.setViewName("user/phoneStateSuccess");
		}
		return mv;
	}

	/**
	 * 用户验证或添加手机
	 * 
	 * @param mobile
	 * @param vcode
	 * @param request
	 * @return 2014年6月24日 liuyijun
	 */
	@RequestMapping(value = "/savePhoneState", method = RequestMethod.POST)
	@ResponseBody
	public Json savePhoneState(@RequestParam("mobile") String mobile,
			@RequestParam("vcode") String vcode, HttpServletRequest request) {
		Json j = new Json();
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
			if (VerifyCodeUtils.check(request.getSession(), vcode)) {
				UserWithBLOBs user = new UserWithBLOBs();
				user.setUserId(UserSession.getLoginUserId());
				user.setPhoneStatus("1");
				user.setPhone(mobile);
				this.userService.updateByPrimaryKeySelective(user);
				UserSession.getUserSession().setPhone(user.getPhone());
				UserSession.getUserSession().setPhoneStatus("1");
				j.setSuccess(true);
			} else {
				j.setMsg("验证码错误");
			}
		} else {
			j.setMsg("您已超时或未登录，请登录");
		}

		return j;
	}

	/**
	 * 转向找回支付密码页面
	 * 
	 * @return 2014年6月25日 liuyijun
	 */
	@RequestMapping("/findPayPasswordPage")
	@NeedSession("/user/findPayPasswordPage")
	public ModelAndView findPayPasswordPage() {
		ModelAndView mv = ModelAndViewUtil
				.createModelAndViewAndAddAccountToView(accountService,
						"user/findPayPassword");
		return mv;
	}

	/**
	 * 转向通过密保问题找回支付密码页面
	 * 
	 * @return 2014年6月25日 liuyijun
	 */
	@RequestMapping("/findPayPwdByQuestion")
	@NeedSession("/user/findPayPwdByQuestion")
	public ModelAndView findPayPwdByQuestion() {
		ModelAndView mv = ModelAndViewUtil
				.createModelAndViewAndAddAccountToView(accountService,
						"user/findPayPwdByQuestion");
		// 问题选项列表
		List<Linkage> linkages = this.linkageService.selectByTypeId(28);
		if (EmptyUtil.isNotEmpty(linkages)) {
			mv.addObject("linkages", linkages);
		}
		return mv;
	}

	@NeedSession
	@RequestMapping("/checkUserAnswer")
	@ResponseBody
	public boolean checkUserAnswer(@RequestParam("answer") String answer,
			@RequestParam("question") String question) {
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
			if (EmptyUtil
					.isNotEmpty(UserSession.getUserSession().getQuestion())) {
				if (UserSession.getUserSession().getQuestion().equals(question)) {
					if (EmptyUtil.isNotEmpty(UserSession.getUserSession()
							.getAnswer())) {
						if (UserSession.getUserSession().getAnswer()
								.equals(answer)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * 找回支付密码
	 * 
	 * @param findPwd
	 * @return 2014年6月25日 liuyijun
	 */
	@RequestMapping(value = "/findPayPassword", method = RequestMethod.POST)
	@ResponseBody
	public Json findPayPassword(FindPayPwdVO findPwd) {
		Json j = new Json();
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
			if (EmptyUtil.isNotEmpty(findPwd.getAnswer())
					&& EmptyUtil.isNotEmpty(findPwd.getQuestion())
					&& EmptyUtil.isNotEmpty(findPwd.getNewPassword())
					&& EmptyUtil.isNotEmpty(findPwd.getRePassword())) {
				if (findPwd.getNewPassword().equals(findPwd.getRePassword())) {
					if (EmptyUtil.isNotEmpty(UserSession.getUserSession()
							.getQuestion())
							&& EmptyUtil.isNotEmpty(UserSession
									.getUserSession().getAnswer())) {
						if (findPwd.getQuestion().equals(
								UserSession.getUserSession().getQuestion())
								&& findPwd.getAnswer().equals(
										UserSession.getUserSession()
												.getAnswer())) {

							UserWithBLOBs user = new UserWithBLOBs();
							user.setUserId(UserSession.getLoginUserId());
							user.setPaypassword(DigestUtils.md5Hex(findPwd
									.getNewPassword()));
							this.userService.updateByPrimaryKeySelective(user);
							UserSession.getUserSession()
									.setPayPassword(
											DigestUtils.md5Hex(findPwd
													.getNewPassword()));
							j.setSuccess(true);

						} else {
							j.setMsg("您的密保问题回答错误");
						}
					} else {
						j.setMsg("请先设置密码问题和答案信息");
					}
				} else {
					j.setMsg("新密码和确认密码不相同");
				}

			} else {
				j.setMsg("必填信息不可为空");
			}
		} else {
			j.setMsg("您已超时或未登录，请登录");
		}

		return j;
	}

	/**
	 * 转向通过手机找回支付密码页面
	 * 
	 * @return 2014年6月25日 liuyijun
	 */
	@RequestMapping("/findPayPwdByPhone")
	@NeedSession("/user/findPayPwdByPhone")
	public ModelAndView findPayPwdByPhone() {
		ModelAndView mv = ModelAndViewUtil
				.createModelAndViewAndAddAccountToView(accountService,
						"user/findPayPwdByPhone");
		String userPhone = StringUtil.doPhoneNumber(UserSession
				.getUserSession().getPhone());
		mv.addObject("userPhone", userPhone);
		return mv;
	}

	@RequestMapping("/findPayPasswordByPhone")
	@ResponseBody
	@NeedSession(logRequestTime = false)
	public Json findPayPasswordByPhone(@RequestParam("cardId") String cardId,
			@RequestParam("code") String code,
			@RequestParam("password") String password,
			@RequestParam("repassword") String repassword,
			HttpSession httpSession) {
		Json j = new Json();
		if (VerifyCodeUtils.check(httpSession, code)) {
			if (UserSession.getUserSession().getCardId().equals(cardId)) {
				if (repassword.equals(password)) {
					User user = new User();
					user.setUserId(UserSession.getLoginUserId());
					user.setPaypassword(DigestUtils.md5Hex(password));
					this.userService.updatePayPassword(user);
					UserSession.getUserSession().setPayPassword(
							DigestUtils.md5Hex(password));
					httpSession.removeAttribute("canGetCodeForFindPayPwd");
					j.setSuccess(true);
				} else {
					j.setMsg("密码和确认密码不相同");
				}
			} else {
				j.setMsg("身份证号码错误");
			}
		} else {
			j.setMsg("验证码错误");
		}
		return j;
	}

	/**
	 * 发送验证邮件通用方法
	 * 
	 * @param request
	 * @param email
	 *            2014年6月13日 liuyijun
	 */
	private void sendVerifyEmailMethod(HttpServletRequest request, String email) {
		String rootPath = ModelAndViewUtil.createBaseUrl(request)
				+ "/verificationEmail/";
		StringBuilder sb = new StringBuilder();
		sb.append("userId=" + UserSession.getLoginUserId());
		sb.append("&time="
				+ DateUtil.parseDateTime(DateUtil.getAfterDateAsDate(
						new Date(), 2)));
		String url = rootPath + EncryptionUtil.encrypt(sb.toString());

		// 发送jms
		SendVerifyEmailMessage message = new SendVerifyEmailMessage();
		message.setEmail(email);
		message.setUrl(url);
		message.setUserName(UserSession.getUserSession().getUserName());
		//this.jmsSender.sendAsynchronousMessage(message);
		this.jmsSenderUtil.asynSendSystemJms(message);
	}

	/**
	 * 添加邮箱或验证前修改邮箱通用方法
	 * 
	 * @param email
	 * @param request
	 * @param mv
	 *            2014年6月27日 liuyijun
	 */
	private void addOrModifyEmail(String email, HttpServletRequest request,
			Json mv) {
		UserWithBLOBs user = new UserWithBLOBs();
		user.setUserId(UserSession.getLoginUserId());
		user.setEmail(email);
		user.setEmailStatus("0");
		this.userService.updateByPrimaryKeySelective(user);
		UserSession.getUserSession().setEmail(email);
		UserSession.getUserSession().setEmailStatus("0");
		this.sendVerifyEmailMethod(request, email);
		mv.setSuccess(true);
	}

	/**
	 * 根据已登录的用户创建登录session对象
	 * 
	 * @param user
	 * @return 2014年4月25日 liuyijun
	 */
	private UserSession createSessionByLoginedUser(UserWithBLOBs user) {
		if (EmptyUtil.isNotEmpty(user)) {
			UserSession userSession = new UserSession();
			if (EmptyUtil.isNotEmpty(user.getEmail())) {
				userSession.setEmail(user.getEmail());
			}
			userSession.setPhone(user.getPhone());
			userSession
					.setPhoneStatus(EmptyUtil.isEmpty(user.getPhoneStatus()) ? "0"
							: user.getPhoneStatus());
			userSession.setPassword(user.getPassword());
			userSession
					.setPayPassword(EmptyUtil.isEmpty(user.getPaypassword()) ? user
							.getPassword() : user.getPaypassword());
			userSession.setUserId(user.getUserId());
			userSession.setUserName(user.getUsername());
			userSession
					.setEmailStatus(EmptyUtil.isEmpty(user.getEmailStatus()) ? "0"
							: user.getEmailStatus());
			userSession
					.setRealStatus(EmptyUtil.isEmpty(user.getRealStatus()) ? "0"
							: user.getRealStatus());
			userSession.setVideoStatus(user.getVideoStatus());
			userSession.setSceneStatus(user.getSceneStatus());
			if (EmptyUtil.isNotEmpty(user.getCardId())) {
				userSession.setCardId(user.getCardId());
			}

			if (EmptyUtil.isNotEmpty(user.getRealname())) {
				userSession.setRealName(user.getRealname());
			}

			userSession.setTypeId(user.getTypeId());
			if (EmptyUtil.isNotEmpty(user.getTypeId())) {
				UserType type = this.typeService.selectByPrimaryKey(user
						.getTypeId());
				if (EmptyUtil.isNotEmpty(type)
						&& EmptyUtil.isNotEmpty(type.getName())) {
					userSession.setTypeName(type.getName());
				} else {
					userSession.setTypeName("普通会员");
				}
			}

			if (EmptyUtil.isNotEmpty(user.getQuestion())) {
				userSession.setQuestion(user.getQuestion());
			}

			if (EmptyUtil.isNotEmpty(user.getAnswer())) {
				userSession.setAnswer(user.getAnswer());
			}

			UserCache cache = this.userCacheService.selectBaseInfoByUserId(user
					.getUserId());
			if (EmptyUtil.isNotEmpty(cache)
					&& EmptyUtil.isNotEmpty(cache.getVipStatus())) {
				userSession.setVipStatus(cache.getVipStatus());
			} else {
				userSession.setVipStatus(0);
			}

			if (EmptyUtil.isNotEmpty(user.getSex())
					&& user.getSex().equals("1")) {
				userSession.setSex("1");
			} else {
				userSession.setSex("0");
			}
			if (EmptyUtil.isNotEmpty(user.getAddress())) {
				userSession.setAddress(user.getAddress());
			}

			userSession
					.setUserIcon(EmptyUtil.isNotEmpty(user.getUserIcon()) ? user
							.getUserIcon() : null);

			return userSession;
		} else {
			return null;
		}
	}

	/**
	 * 获得理财师等级称号 wang.zeyan 2015年3月3日
	 * 
	 * @return
	 */
	private Map<String, Object> getFinancialPlannerLevelTitle() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("FinancialPlannerLevel",
				FinancialPlannerUtil.fpLevelMap.get(getFinancialPlannerLevel()));
		return map;
	}

	/**
	 * 获得理财师等级 wang.zeyan 2015年3月3日
	 * 
	 * @return
	 */
	private int getFinancialPlannerLevel() {
		Integer userId = UserSession.getLoginUserId();
		int count = userService.getInviteCount(userId);
		return count > 0 ? FinancialPlannerUtil.LCS : FinancialPlannerUtil.CKMJ;
	}

	/**
	 * 判断邀请人是否投资过,返回首个投资人Id wang.zeyan 2015年3月4日
	 * 
	 * @param inviteUsers
	 * @return
	 */
	private BorrowTender isInviteInvestment(List<User> inviteUsers) {
		for (User user : inviteUsers) {
			// List<BorrowTender> list
			// =borrowTenderService.selectBrrowTenderByUserId(user.getUserId());
			// 满标复审过的标的 投资记录
			List<BorrowTender> list = borrowTenderService
					.selectTenderStatusByUserId(user.getUserId(), 3);
			if (list.size() > 0)
				return list.get(0);
		}
		return null;
	}
}
