package cn.vfunding.vfunding.biz.borrow.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.utils.beans.ConverterUtil;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.dao.AccountLogMapper;
import cn.vfunding.vfunding.biz.account.dao.AccountMapper;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.account.model.AccountLog;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowCollectionMapper;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowMapper;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowRepaymentMapper;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowRuleMapper;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowTenderMapper;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.BorrowCollection;
import cn.vfunding.vfunding.biz.borrow.model.BorrowRule;
import cn.vfunding.vfunding.biz.borrow.model.BorrowTender;
import cn.vfunding.vfunding.biz.borrow.model.UserInvestMoneyVO;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.common.vo.BorrowTenderVO;
import cn.vfunding.vfunding.biz.common.vo.InvesVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderVO;
import cn.vfunding.vfunding.biz.hikes.dao.HikesCardLogMapper;
import cn.vfunding.vfunding.biz.hikes.dao.HikesCardMapper;
import cn.vfunding.vfunding.biz.hikes.model.HikesCard;
import cn.vfunding.vfunding.biz.hikes.model.HikesCardLog;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.message.dao.SystemMessageMapper;
import cn.vfunding.vfunding.biz.message.model.SystemMessage;
import cn.vfunding.vfunding.biz.returns.dao.ReturnfeeMapper;
import cn.vfunding.vfunding.biz.system.dao.BiaoTypeMapper;
import cn.vfunding.vfunding.biz.system.dao.InvestorsFeeMapper;
import cn.vfunding.vfunding.biz.system.dao.ThirdSynRecordMapper;
import cn.vfunding.vfunding.biz.system.model.BiaoType;
import cn.vfunding.vfunding.biz.system.model.InvestorsFee;
import cn.vfunding.vfunding.biz.thirdplat.dao.CashVolumeMapper;
import cn.vfunding.vfunding.biz.thirdplat.dao.ThirdRelationshipMapper;
import cn.vfunding.vfunding.biz.thirdplat.dao.ThirdTendercheckMapper;
import cn.vfunding.vfunding.biz.user.dao.UserFromUnionMapper;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.dao.UserUnionMapper;
import cn.vfunding.vfunding.biz.user.model.UserFromUnion;
import cn.vfunding.vfunding.biz.user.model.UserUnion;
import cn.vfunding.vfunding.biz.week.dao.WeekMapper;
import cn.vfunding.vfunding.biz.week.dao.WeekRateMapper;
import cn.vfunding.vfunding.biz.week.dao.WeekRepaymentMapper;
import cn.vfunding.vfunding.biz.week.dao.WeekTenderMapper;
import cn.vfunding.vfunding.biz.week.model.Week;
import cn.vfunding.vfunding.biz.week.service.IWeekTenderService;
import cn.vfunding.vfunding.common.module.activemq.message.InvestMessage;
import cn.vfunding.vfunding.common.module.activemq.message.useraction.UserTenderMessage;

@Service("borrowTenderService")
public class BorrowTenderServiceImpl implements IBorrowTenderService {

	@Autowired
	private BorrowTenderMapper tenderMapper;
	@Autowired
	private UserFromUnionMapper userFromUnionMapper;
	@Autowired
	private UserUnionMapper userUnionMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private BorrowMapper borrowMapper;

	@Autowired
	private BorrowRepaymentMapper repaymentMapper;

	@Autowired
	private ThirdSynRecordMapper thirdSynRecordMapper;

	@Autowired
	private BiaoTypeMapper biaoTypeMapper;

	@Autowired
	private BorrowCollectionMapper collectionMapper;

	@Autowired
	private InvestorsFeeMapper investorsFeeMapper;

	@Autowired
	private ReturnfeeMapper returnfeeMapper;

	@Autowired
	private BorrowRuleMapper borrowRuleMapper;

	@Autowired
	private AccountLogMapper accountLogMapper;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private ThirdRelationshipMapper thirdRelationshipMapper;

	@Autowired
	private ThirdTendercheckMapper thirdTendercheckMapper;

	@Autowired
	private CashVolumeMapper cashVolumeMapper;
	@Autowired
	private IWeekTenderService weekTenderService;
	@Autowired
	private WeekRepaymentMapper weekRepaymentMapper;
	@Autowired
	private WeekTenderMapper weekTenderMapper;

	@Autowired
	private WeekRateMapper weekRateMapper;

	@Autowired
	private SystemMessageMapper systemMessageMapper;

	@Autowired
	private WeekMapper weekMapper;

	@Autowired
	private HikesCardMapper hikesCardMapper;

	@Autowired
	private HikesCardLogMapper hikesCardLogMapper;
	@Autowired
	private JmsSenderService jmsSenderUtil;

	@Override
	public List<BorrowTenderVO> selectFeelList(PageSearch search) {
		return this.tenderMapper.selectFeelList(search);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.tenderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(BorrowTender record) {
		return this.tenderMapper.insert(record);
	}

	@Override
	public int insertSelective(BorrowTender record) {
		return this.tenderMapper.insertSelective(record);
	}

	@Override
	public BorrowTender selectByPrimaryKey(Integer id) {
		return this.tenderMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<BorrowTender> selectListByUserIdBorrowId(Integer userId,
			Integer borrowId) {
		return this.tenderMapper.selectListByUserIdBorrowId(userId, borrowId);
	}

	@Override
	public int updateByPrimaryKeySelective(BorrowTender record) {
		return this.tenderMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(BorrowTender record) {
		return this.tenderMapper.updateByPrimaryKey(record);
	}

	@Override
	public Integer selectInvestMoneyByUserIdAndDays(UserInvestMoneyVO vo) {
		return this.tenderMapper.selectInvestMoneyByUserIdAndDays(vo);
	}

	@Override
	public List<BorrowTender> selectByDate(String date) {
		return this.tenderMapper.selectByDate(date);
	}

	@Override
	public List<BorrowTender> selectListByBorrowId(Integer borrowId) {
		return this.tenderMapper.selectListByBorrowId(borrowId);
	}

	@Override
	public List<BorrowTender> selectListByParam(PageSearch search) {
		return this.tenderMapper.selectListByParam(search);
	}

	@Override
	public void afterPhpTender(InvestMessage message) {
		if (EmptyUtil.isNotEmpty(message)) {
			if (EmptyUtil.isNotEmpty(message.getUserId())) {
				if (this.checkInvest(message.getUserId(), message)) {
					// 发送mq消息
					//this.jmsSender.sendAsynchronousMessage(message);
					this.jmsSenderUtil.asynSendSystemJms(message);
				}
			}
		}
	}

	/**
	 * 检查用户投资是否可以给网站联盟用户进行分利
	 * 
	 * @param userId
	 * @param message
	 * @return
	 */
	private boolean checkInvest(Integer userId, InvestMessage message) {
		if (EmptyUtil.isNotEmpty(userId)) {
			UserFromUnion userFromUnion = this.userFromUnionMapper
					.selectByPrimaryKey(userId);
			if (EmptyUtil.isNotEmpty(userFromUnion)) {
				if (userFromUnion.getStatus().equals("Y")) {// 该用户当前是可分利状态（即：他投资时可以为其介绍来的网站联盟用户进行分利计算）
					// 获取网站联盟用户在微积金中的对象
					UserUnion unionUser = this.userUnionMapper
							.selectByPrimaryKey(userFromUnion.getUnionUserId());
					if (EmptyUtil.isNotEmpty(unionUser)) {
						if (unionUser.getStatus().equals("Y")) {// 该网站联盟用户处于可分利状态
							// 到此可表示此次投资可正常为网站联盟用户分利
							if (userFromUnion.getPeriods().intValue() == 0) {
								userFromUnion.setPeriods(1);
								userFromUnion.setUpdatePeriodsTime(new Date());
								// 完善用户投资网站联盟用户分利数据的mq消息
								message.setPeriods(userFromUnion.getPeriods());
								message.setUnionUserId(userFromUnion
										.getUnionUserId());
								message.setProductId(userFromUnion
										.getProductId());
								message.setUserName(userFromUnion.getUserName());
								this.userFromUnionMapper
										.updateByPrimaryKeySelective(userFromUnion);
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public synchronized UserTenderActionResultVO userTenderAction(
			UserTenderActionVO vo) {
		/**
		 * 数据传递操作
		 */
		UserTenderActionResultVO resultVO = UserTenderActionResultVO
				.bulidByUserTenderActionVO(vo);
		// 查询用户账户信息
		Account useraccount = this.accountMapper.selectByUserId(vo.getUserId());
		AccountLog accountLog = new AccountLog();
		accountLog.setUserId(vo.getUserId());
		accountLog.setType("tender");
		accountLog.setAddtime(Integer.parseInt(DateUtil.getLongTime()
				.toString()));
		accountLog.setAddip(vo.getUserip());
		if (vo.getTenderType().equals("normal")) {// 普通投标
			resultVO = this.userTender(useraccount, vo, resultVO, accountLog);
		} else if (vo.getTenderType().equals("week")) { // 周盈宝产品
			resultVO = this.userWeekTender(useraccount, vo, resultVO,
					accountLog);
		}
		accountLog.setTotal(useraccount.getTotal());
		accountLog.setNoUseMoney(useraccount.getNoUseMoney());
		accountLog.setUseMoney(useraccount.getUseMoney());
		accountLog.setCollection(useraccount.getCollection());
		accountLog.setToUser(0);
		accountMapper.updateByPrimaryKey(useraccount);
		this.accountLogMapper.insertSelective(accountLog);
		return resultVO;

	}

	/**
	 * 普通投标-主体验证
	 * 
	 * @author lilei
	 * @param useraccount
	 * @param vo
	 * @param resultVO
	 * @param accountLog
	 * @return 2015年1月20日
	 */
	private UserTenderActionResultVO userTender(Account useraccount,
			UserTenderActionVO vo, UserTenderActionResultVO resultVO,
			AccountLog accountLog) {
		BigDecimal payMoney = vo.getPayMoney();
		if (payMoney.doubleValue() <= 0) {
			throw new BusinessException("8005012", "金额不正确");
		}
		if (EmptyUtil.isNotEmpty(vo.getUserip())
				&& vo.getUserip().equals("borrowAuto")) {
			if (useraccount.getUseMoney().compareTo(payMoney) < 0) {
				payMoney = useraccount.getUseMoney();
			}
		} else {
			if (useraccount.getUseMoney().compareTo(payMoney) < 0) {
				throw new BusinessException("8005012", "可用余额不足");
			}
		}
		// 查询标的信息
		Borrow borrow = this.borrowMapper.selectById(vo.getBorrowId());
		if (borrow.getUserId() == vo.getUserId()) {
			throw new BusinessException("8005012", "不能投资自己的标的");
		}
		if (borrow.getStatus() != 1) {
			throw new BusinessException("8005012", "此标的不可投");
		}
		if (payMoney.doubleValue() < borrow.getLowestAccount().doubleValue()) {
			throw new BusinessException("8005012", "投资金额低于最小投资额");
		}
		// 查询borrow的规则限制
		BorrowRule borrowRule = this.borrowRuleMapper.selectByPrimaryKey(vo
				.getBorrowId());
		if (EmptyUtil.isNotEmpty(borrowRule)) {
			if(borrowRule.getTenderTime()!=null && borrowRule.getTenderTime().after(new Date())){
				throw new BusinessException("8005012", "未到开售时间");
			}
			// 单标投资金额限制
			if (borrowRule.getMostAccount().doubleValue() > 0
					&& payMoney.doubleValue() > borrowRule.getMostAccount()
							.doubleValue()) {
				throw new BusinessException("8005012", "投资金额大于最大投资额");
			}
			// 单标投资次数限制
			if (borrowRule.getMostTenderCount() > 0) {
				BorrowTender bt = new BorrowTender();
				bt.setUserId(vo.getUserId());
				bt.setBorrowId(vo.getBorrowId());
				List<BorrowTender> listBt = tenderMapper.selectByParameters(bt);
				if (!listBt.isEmpty()
						&& listBt.size() >= borrowRule.getMostTenderCount()) {
					throw new BusinessException("8005012", "超过单标投资最大次数");
				}
			}
		}
		// 标的还可以借入的金额
		BigDecimal borrowAccountSurplus = borrow.getAccount().subtract(
				borrow.getAccountYes());
		if (borrowAccountSurplus.compareTo(new BigDecimal(0)) == 0) {
			resultVO.setStatus(3);// 已满标
			resultVO.setRealPayMoney(new BigDecimal(0));
			throw new BusinessException("8005012", "标的已满，可选择其他标的");
		}

		// 但投资金额大于标的剩余金额时,投资金额为标的剩余金额
		if (borrowAccountSurplus.compareTo(payMoney) < 0) {
			if (vo.getIsUseHikes() != null && vo.getIsUseHikes() == 1) {
				throw new BusinessException("8005012", "标的剩余可投额度不足您的投资金额");
			}
			payMoney = borrowAccountSurplus;
			resultVO.setStatus(2);// 部分投资
		} else {
			resultVO.setStatus(1);// 全额投资
		}
		resultVO.setRealPayMoney(payMoney);

		if (vo.getIsUseHikes() != null && vo.getIsUseHikes() == 1) {
			// 查询平台服务费信息
			InvestorsFee investorsFee = this.investorsFeeMapper
					.selectByBorrowId(vo.getBorrowId());
			// 查询用户加息卡
			HikesCard hikesCard = this.hikesCardMapper.selectByPrimaryKey(vo
					.getUserId());
			if (hikesCard.getUseRate().doubleValue() > 0) {
				// 用户总共的加息卡利率
				BigDecimal useRateDouble = hikesCard.getUseRate();
				// 用户得到的标的利息
				BigDecimal expApr = borrow.getApr()
						.subtract(investorsFee.getBfee())
						.subtract(investorsFee.getGfee());
				// 最多可加息
				BigDecimal mostHikes = borrowRule.getMostHikes().subtract(
						expApr);

				BigDecimal useRate = useRateDouble.doubleValue() > mostHikes
						.doubleValue() ? mostHikes : useRateDouble;
				// 更新用户的加息卡
				hikesCard.setUseRate(hikesCard.getUseRate().subtract(useRate));
				hikesCard.setNoUseRate(hikesCard.getNoUseRate().add(useRate));
				this.hikesCardMapper.updateByPrimaryKeySelective(hikesCard);
				resultVO.setUseHikesRate(useRate);
			}

		}

		// 扣除账户可用余额
		useraccount.setUseMoney(useraccount.getUseMoney().subtract(payMoney));
		useraccount.setNoUseMoney(useraccount.getNoUseMoney().add(payMoney));
		// 增加资金交易记录-accountLog
		accountLog.setMoney(payMoney);
		accountLog.setRemark("冻结投资者的投标资金,对标[<a href='/borrow/borrowDetail/"
				+ vo.getBorrowId() + "'>" + borrow.getName() + "</a>]");
		// 更新标的已投资金额
		Borrow updateAccountYes = new Borrow();
		updateAccountYes.setId(vo.getBorrowId());
		updateAccountYes.setAccountYes(borrow.getAccountYes().add(payMoney));
		if (EmptyUtil.isNotEmpty(borrow.getTenderTimes())) {
			updateAccountYes
					.setTenderTimes((short) (borrow.getTenderTimes() + 1));
		} else {
			updateAccountYes.setTenderTimes((short) (1));
		}
		borrowMapper.updateByPrimaryKeySelective(updateAccountYes);
		return resultVO;
	}

	/**
	 * 周盈宝投资 - 主体验证
	 * 
	 * @author lilei
	 * @param useraccount
	 * @param vo
	 * @param resultVO
	 * @param accountLog
	 * @return 2015年1月20日
	 */
	private UserTenderActionResultVO userWeekTender(Account useraccount,
			UserTenderActionVO vo, UserTenderActionResultVO resultVO,
			AccountLog accountLog) {
		// 所购买的weekId
		Integer weekId = vo.getWeekId();
		// 记录user所购买份数,用于添加weekTender表中的money字段
		Integer userShare = vo.getBuyShare();
		// 用于真实购买份数,通过此属性计算之后的利息
		Integer buyShare = 0;
		// 查询周盈宝详情
		Week week = this.weekMapper.selectByPrimaryKey(weekId);
		if (new Date().before(week.getSaleTime())) {
			throw new BusinessException("8005012", "还没到开售时间哦");
		}
		if (new Date().after(week.getExpireTime())) {
			throw new BusinessException("8005012", "认购时间结束了哦");
		}
		if (week.getCreateUser() == vo.getUserId()) {
			throw new BusinessException("8005012", "不可以投资自己的标的");
		}
		if (week.getStatus() != 3) {
			throw new BusinessException("8005012", "此标的不可投");
		}
		if (vo.getBuyShare() % week.getBuyBase() != 0) {
			throw new BusinessException("8005012", "投资金额不符合投资基数");
		}
		if (vo.getBuyShare() < week.getSingleMin()) {
			throw new BusinessException("8005012", "投资金额低于最小投资份数");
		}
		if (week.getSingleMax() > 0 && vo.getBuyShare() > week.getSingleMax()) {
			throw new BusinessException("8005012", "投资金额大于最大投资份数");
		}
		// 还可以借入的份数
		Integer WeekShareSurplus = week.getShareCount()
				- week.getShareYescount();
		if (WeekShareSurplus == 0) {
			resultVO.setStatus(3);// 购买份额已满
		}
		// 购买份数大于剩余份数时,投资金额为标的剩余金额
		if (userShare > WeekShareSurplus) {
			buyShare = WeekShareSurplus;
			resultVO.setStatus(2);// 部分投资
		} else {
			resultVO.setStatus(1);// 全额投资
		}
		// 设置购买份数
		resultVO.setRealBuyShare(buyShare);
		// 根据购买份数计算购买金额
		BigDecimal buyMoney = new BigDecimal(buyShare).multiply(week
				.getSharePrice());

		// 扣除账户可用余额
		useraccount.setUseMoney(useraccount.getUseMoney().subtract(buyMoney));
		useraccount.setNoUseMoney(useraccount.getNoUseMoney().add(buyMoney));
		// 增加资金交易记录-accountLog
		accountLog.setMoney(buyMoney);
		accountLog.setRemark("冻结投资者的投标资金,对标[<a href='/week/weekDetail/"
				+ vo.getWeekId() + "'>" + week.getName() + "</a>]");
		// 更新标的已投资金额
		Week updateWeek = new Week();
		updateWeek.setId(weekId);
		updateWeek.setShareYescount(week.getShareYescount() + buyShare);
		this.weekMapper.updateByPrimaryKeySelective(updateWeek);
		return resultVO;
	}

	/**
	 * 普通投资 -辅助业务处理
	 * 
	 * @author lilei
	 * @param vo
	 *            2015年1月20日
	 */
	@Override
	public synchronized void userTenderAfter(UserTenderActionResultVO vo) {
		BigDecimal payMoney = vo.getRealPayMoney();
		// 记录user所投资的金额
		String userMoney = vo.getPayMoney().toString();
		// 查询标的信息
		Borrow borrow = this.borrowMapper.selectById(vo.getBorrowId());
		// 查询平台服务费信息
		InvestorsFee investorsFee = this.investorsFeeMapper.selectByBorrowId(vo
				.getBorrowId());
		// 标的类型
		BiaoType biaoType = biaoTypeMapper.selectByTypeName(borrow
				.getBiaoType());

		// 添加tender详细
		BorrowTender borrowTender = new BorrowTender();
		borrowTender.setSiteId(0);
		borrowTender.setUserId(vo.getUserId());
		borrowTender.setStatus(5); // 5:待审
		borrowTender.setBorrowId(borrow.getId());
		borrowTender.setMoney(userMoney); // 用户投标金额
		borrowTender.setAccount(payMoney.toString()); // 最后的有效金额
		BigDecimal apr = borrow.getApr(); // 年利率
		BigDecimal bfee = new BigDecimal(0);// 平台服务率
		BigDecimal gfee = new BigDecimal(0);// 担保服务率
		if (EmptyUtil.isNotEmpty(investorsFee)) {
			bfee = investorsFee.getBfee();// 平台服务率
			gfee = investorsFee.getGfee();// 担保服务率
		}
		// 获得最后用户的年利率
		BigDecimal expectApr = apr.subtract(bfee).subtract(gfee);
		// 平台服务费的占利息的比例
		BigDecimal bfeeApr = bfee.divide(apr, 20, BigDecimal.ROUND_HALF_UP);
		// 担保服务费的占利息的比例
		BigDecimal gfeeApr = gfee.divide(apr, 20, BigDecimal.ROUND_HALF_UP);
		// 用户的利息占利息的比例
		BigDecimal expectAprApr = expectApr.divide(apr, 20,
				BigDecimal.ROUND_HALF_UP);
		Integer tenderId = 0;
		BigDecimal isday = new BigDecimal(borrow.getIsday());
		// 判断是否为月标
		if (isday.doubleValue() != 1) {
			// 标的类型--还款方式：0--按月还款(等额本息),1--等本等息；2--到期一次性还款,3--按月付息到期还本
			Byte stype = borrow.getStyle();
			// 标的期数
			BigDecimal month = new BigDecimal(borrow.getTimeLimit());
			// 借款人所需付的利息
			BigDecimal aprInterest = new BigDecimal(0);
			BigDecimal expectAprInterest = new BigDecimal(0);
			if (stype == 0) {
				// 等额本息
				aprInterest = ((ConverterUtil.monthlyAccount(payMoney, apr,
						month)).multiply(month)).subtract(payMoney);
				expectAprInterest = ((ConverterUtil.monthlyAccount(payMoney,
						expectApr, month)).multiply(month)).subtract(payMoney);// 投资人所得利息
			} else if (stype == 1) {
				// 等本等息
				aprInterest = ((ConverterUtil.monthlyAccount1(payMoney, apr,
						month)).multiply(month)).subtract(payMoney);
				expectAprInterest = ((ConverterUtil.monthlyAccount1(payMoney,
						expectApr, month)).multiply(month)).subtract(payMoney);// 投资人所得利息
			} else if (stype == 2 || stype == 3) {
				// 2:到期还本,3:先息后本
				aprInterest = ConverterUtil.disposableInterest(payMoney, apr,
						month);
			}
			// 用户获得的总利息
			BigDecimal interest = aprInterest.multiply(expectAprApr).setScale(
					2, BigDecimal.ROUND_HALF_UP);
			// 用户所要支付的总平台服务费
			BigDecimal serviceFees = aprInterest.multiply(bfeeApr).setScale(2,
					BigDecimal.ROUND_HALF_UP);
			// 用户所要支付的总担保服务费
			BigDecimal guaranteeFees = aprInterest.multiply(gfeeApr).setScale(
					2, BigDecimal.ROUND_HALF_UP);
			// 用户将得到的总金额(不包含等额本息)
			if (stype == 0) {
				// 等额本息将得到的总金额
				BigDecimal repaymentAccount = payMoney.add(expectAprInterest);
				borrowTender.setRepaymentAccount(repaymentAccount.toString());
				borrowTender.setWaitAccount(repaymentAccount.toString());// 等待回款的有效金额
				borrowTender.setInterest(expectAprInterest.toString());
				borrowTender.setWaitInterest(expectAprInterest.toString());
				borrowTender.setReturnFees("0");
				borrowTender.setWaitReturnFees("0");
			} else if (stype == 1) {
				// 等本等息将得到的总金额
				BigDecimal repaymentAccount = payMoney.add(expectAprInterest);
				borrowTender.setRepaymentAccount(repaymentAccount.toString());
				borrowTender.setWaitAccount(repaymentAccount.toString());// 等待回款的有效金额
				borrowTender.setInterest(expectAprInterest.toString());
				borrowTender.setWaitInterest(expectAprInterest.toString());
				borrowTender.setReturnFees("0");
				borrowTender.setWaitReturnFees("0");
			} else {
				BigDecimal repaymentAccount = payMoney.add(interest);
				borrowTender.setRepaymentAccount(repaymentAccount.toString());
				borrowTender.setWaitAccount(repaymentAccount.toString());// 等待回款的有效金额
				borrowTender.setInterest(interest.toString());
				borrowTender.setWaitInterest(interest.toString());
				borrowTender.setReturnFees("0");
				borrowTender.setWaitReturnFees("0");
			}
			// 保存等额本息的占比利息，用于之后比较按公式算出的利息的差值
			borrowTender.setEqualInterest(interest.toString());
			borrowTender.setServiceFees(serviceFees.toString());
			borrowTender.setWaitServiceFees(serviceFees.toString());
			borrowTender.setGuaranteeFees(guaranteeFees.toString());
			borrowTender.setWaitGuaranteeFees(guaranteeFees.toString());
			borrowTender.setAddip(vo.getUserip());
			borrowTender.setAddtime(Integer.parseInt(DateUtil.getTime()));
			this.tenderMapper.insertSelective(borrowTender);
			tenderId = borrowTender.getId();

			// 是否使用加息卡
			if (vo.getIsUseHikes() != null && vo.getIsUseHikes() == 1) {
				// 查询用户加息卡
				HikesCard hikesCard = this.hikesCardMapper
						.selectByPrimaryKey(vo.getUserId());
				if (hikesCard.getNoUseRate().doubleValue() > 0) {
					// 添加使用日志
					HikesCardLog hikesCardLog = new HikesCardLog();
					hikesCardLog.setHikesRate(vo.getUseHikesRate());
					hikesCardLog.setTenderId(tenderId);
					hikesCardLog.setUserId(vo.getUserId());
					hikesCardLog.setAddtime(new Date());
					this.hikesCardLogMapper.insertSelective(hikesCardLog);
				}
			}
			// 添加信息到collection
			this.tenderStype(stype, month, payMoney, apr, tenderId,
					expectAprApr, gfeeApr, bfeeApr, vo.getUserip(), biaoType,
					expectApr);
			// resultVO.setPayMoney(payMoney);
		} else {
			// 天标
			BigDecimal days = new BigDecimal(borrow.getTimeLimitDay());// 标的期数
			BigDecimal aprInterest = new BigDecimal(0);
			// 借款人需支付的总利息
			aprInterest = ConverterUtil.daysInterest(days.intValue(), payMoney,
					apr);
			// 用户获得的总利息
			BigDecimal interest = aprInterest.multiply(expectAprApr).setScale(
					2, BigDecimal.ROUND_HALF_UP);
			// 用户所要支付的总平台服务费
			BigDecimal serviceFees = aprInterest.multiply(bfeeApr).setScale(2,
					BigDecimal.ROUND_HALF_UP);
			// 用户所要支付的总担保服务费
			BigDecimal guaranteeFees = aprInterest.multiply(gfeeApr).setScale(
					2, BigDecimal.ROUND_HALF_UP);
			// 用户将得到的总金额
			BigDecimal repaymentAccount = payMoney.add(interest);
			borrowTender.setRepaymentAccount(repaymentAccount.toString());
			borrowTender.setWaitAccount(repaymentAccount.toString());// 等待回款的有效金额
			borrowTender.setInterest(interest.toString());
			borrowTender.setWaitInterest(interest.toString());
			borrowTender.setServiceFees(serviceFees.toString());
			borrowTender.setWaitServiceFees(serviceFees.toString());
			borrowTender.setGuaranteeFees(guaranteeFees.toString());
			borrowTender.setReturnFees("0");
			borrowTender.setWaitReturnFees("0");
			borrowTender.setWaitGuaranteeFees(guaranteeFees.toString());
			borrowTender.setAddip(vo.getUserip());
			borrowTender.setAddtime(Integer.parseInt(DateUtil.getTime()));
			this.tenderMapper.insertSelective(borrowTender);
			tenderId = borrowTender.getId();
			this.tenderStype((byte) 4, days, payMoney, apr, tenderId,
					expectAprApr, gfeeApr, bfeeApr, vo.getUserip(), biaoType,
					expectApr);
		}
		vo.setTenderId(tenderId);
	}

	/**
	 * 生成用户投资的待收明细
	 * 
	 * @author lilei
	 * @param stype
	 * @param month
	 * @param payMoney
	 * @param apr
	 * @param tenderId
	 * @param expectAprApr
	 * @param gfeeApr
	 * @param bfeeApr
	 * @param ip
	 * @param biaoType
	 * @param expectApr
	 *            2015年1月20日
	 */
	private void tenderStype(byte stype, BigDecimal month, BigDecimal payMoney,
			BigDecimal apr, Integer tenderId, BigDecimal expectAprApr,
			BigDecimal gfeeApr, BigDecimal bfeeApr, String ip,
			BiaoType biaoType, BigDecimal expectApr) {
		// stype=0为等额本息
		if (stype == 0) {
			for (int i = 1; i <= month.intValue(); i++) {
				// 每月利息 -- 等额本息方式 借款人需要付的利息
				BigDecimal monthlyInterest = ConverterUtil.monthlyInterest(
						payMoney, apr, month, new BigDecimal(i));
				// 每月本息 --借款人需要付的本息
				// BigDecimal monthlyAccount = ConverterUtil.monthlyAccount(
				// payMoney, apr, month);
				// 每月利息 -- 等额本息方式 投资人得到的利息
				BigDecimal collectionInterest = ConverterUtil.monthlyInterest(
						payMoney, expectApr, month, new BigDecimal(i));
				// 每月本息 --投资人得到的本息
				BigDecimal collectionAccount = ConverterUtil.monthlyAccount(
						payMoney, expectApr, month);
				BorrowCollection borrowCollection = new BorrowCollection();
				borrowCollection.setSiteId(0);
				borrowCollection.setStatus(0); // 0:未还；1:已还
				borrowCollection.setOrder(i - 1);
				borrowCollection.setTenderId(tenderId);
				borrowCollection.setRepayTime(DateUtil.getTime()); // 复审时决定时间
				// 投资人得到的利息
				borrowCollection.setInterest(collectionInterest.toString());
				// 平台服务费
				BigDecimal collectionBfees = monthlyInterest.multiply(bfeeApr)
						.setScale(2, BigDecimal.ROUND_HALF_UP);
				borrowCollection.setServiceFees(collectionBfees.toString());
				// 担保服务费
				BigDecimal collectionGfees = monthlyInterest.multiply(gfeeApr)
						.setScale(2, BigDecimal.ROUND_HALF_UP);
				borrowCollection.setGuaranteeFees(collectionGfees.toString());
				// 集团用户返利(已替代为红包发放)
				borrowCollection.setReturnFees("0");
				// 投资人月本息
				borrowCollection.setRepayAccount(collectionAccount.toString());
				// 投资人月本金 = 投资人月本息-投资人利息
				borrowCollection.setCapital(collectionAccount.subtract(
						collectionInterest).toString());
				borrowCollection.setRepayYesaccount("0");
				borrowCollection.setRepayYestime("");
				borrowCollection.setLateDays(0);
				borrowCollection.setLateInterest("0");
				borrowCollection.setAddtime(DateUtil.getTime());
				borrowCollection.setAddip(ip);
				borrowCollection.setInterestFee(monthlyInterest
						.multiply(biaoType.getInterestFeeRate()));
				borrowCollection.setInviteFees("0");
				collectionMapper.insertSelective(borrowCollection);
			}
		} else if (stype == 1) {// 等本等息
			// 每月利息 -- 等本等息方式 借款人需要付的利息
			BigDecimal monthlyInterest = ConverterUtil.monthlyInterest(
					payMoney, apr);
			// 每月利息 -- 等本等息方式 投资人得到的利息
			BigDecimal collectionInterest = ConverterUtil.monthlyInterest(
					payMoney, expectApr);
			// 每月本息 --投资人得到的本息
			BigDecimal collectionAccount = ConverterUtil.monthlyAccount1(
					payMoney, expectApr, month);
			for (int i = 1; i <= month.intValue(); i++) {
				BorrowCollection borrowCollection = new BorrowCollection();
				borrowCollection.setSiteId(0);
				borrowCollection.setStatus(0); // 0:未还；1:已还
				borrowCollection.setOrder(i - 1);
				borrowCollection.setTenderId(tenderId);
				borrowCollection.setRepayTime(DateUtil.getTime()); // 复审时决定时间
				// 投资人得到的利息
				borrowCollection.setInterest(collectionInterest.toString());
				// 平台服务费
				BigDecimal collectionBfees = monthlyInterest.multiply(bfeeApr)
						.setScale(2, BigDecimal.ROUND_HALF_UP);
				borrowCollection.setServiceFees(collectionBfees.toString());
				// 担保服务费
				BigDecimal collectionGfees = monthlyInterest.multiply(gfeeApr)
						.setScale(2, BigDecimal.ROUND_HALF_UP);
				borrowCollection.setGuaranteeFees(collectionGfees.toString());
				// 集团用户返利(已替代为红包发放)
				borrowCollection.setReturnFees("0");
				// 投资人月本息
				borrowCollection.setRepayAccount(collectionAccount.toString());
				// 投资人月本金 = 投资人月本息-投资人利息
				borrowCollection.setCapital(collectionAccount.subtract(
						collectionInterest).toString());
				borrowCollection.setRepayYesaccount("0");
				borrowCollection.setRepayYestime("");
				borrowCollection.setLateDays(0);
				borrowCollection.setLateInterest("0");
				borrowCollection.setAddtime(DateUtil.getTime());
				borrowCollection.setAddip(ip);
				borrowCollection.setInterestFee(monthlyInterest
						.multiply(biaoType.getInterestFeeRate()));
				borrowCollection.setInviteFees("0");
				collectionMapper.insertSelective(borrowCollection);
			}
		} // 到期一次性还款
		else if (stype == 2) {
			BorrowCollection borrowCollection = new BorrowCollection();
			borrowCollection.setSiteId(0);
			borrowCollection.setStatus(0); // 0:未还；1:已还
			borrowCollection.setOrder(0);
			borrowCollection.setTenderId(tenderId);
			borrowCollection.setRepayTime(DateUtil.getTime()); // 复审时决定时间
			// 借款人所需支付总利息
			BigDecimal disposableInterest = ConverterUtil.disposableInterest(
					payMoney, apr, month);
			// 投资人利息=借款人利息*利率占比
			BigDecimal collectionInterest = disposableInterest.multiply(
					expectAprApr).setScale(2, BigDecimal.ROUND_HALF_UP);
			borrowCollection.setInterest(collectionInterest.toString());
			// 平台服务费
			BigDecimal collectionBfees = disposableInterest.multiply(bfeeApr)
					.setScale(2, BigDecimal.ROUND_HALF_UP);
			borrowCollection.setServiceFees(collectionBfees.toString());
			// 担保服务费
			BigDecimal collectionGfees = disposableInterest.multiply(gfeeApr)
					.setScale(2, BigDecimal.ROUND_HALF_UP);
			borrowCollection.setGuaranteeFees(collectionGfees.toString());
			// 集团用户返利(已替代为红包发放)
			borrowCollection.setReturnFees("0");
			// 投资人月本息 = 利息加本金
			// TODO Auto-generated method stub
			borrowCollection.setRepayAccount(collectionInterest.add(payMoney)
					.toString());
			// 投资人本金
			borrowCollection.setCapital(payMoney.toString());
			borrowCollection.setRepayYesaccount("0");
			borrowCollection.setRepayYestime("");
			borrowCollection.setLateDays(0);
			borrowCollection.setLateInterest("0");
			borrowCollection.setAddtime(DateUtil.getTime());
			borrowCollection.setAddip(ip);
			borrowCollection.setInterestFee(disposableInterest
					.multiply(biaoType.getInterestFeeRate()));
			borrowCollection.setInviteFees("0");
			collectionMapper.insertSelective(borrowCollection);
		} // 按月付息,到期还本
		else if (stype == 3) {
			for (int i = 1; i <= month.intValue(); i++) {
				BorrowCollection borrowCollection = new BorrowCollection();
				borrowCollection.setSiteId(0);
				borrowCollection.setStatus(0); // 0:未还；1:已还
				borrowCollection.setOrder(i - 1);
				borrowCollection.setTenderId(tenderId);
				borrowCollection.setRepayTime(""); // 复审时决定时间
				// 总利息
				BigDecimal disposableInterest = ConverterUtil
						.disposableInterest(payMoney, apr, month);
				// 每月的利息
				BigDecimal monthlyInterest = ConverterUtil.disposableInterest(
						payMoney, apr, month).divide(month, 20,
						BigDecimal.ROUND_HALF_UP);
				// 投资人利息=借款人利息*利率占比
				BigDecimal collectionInterest = monthlyInterest.multiply(
						expectAprApr).setScale(2, BigDecimal.ROUND_HALF_UP);
				borrowCollection.setInterest(collectionInterest.toString());
				// 平台服务费
				BigDecimal collectionBfees = monthlyInterest.multiply(bfeeApr)
						.setScale(2, BigDecimal.ROUND_HALF_UP);
				borrowCollection.setServiceFees(collectionBfees.toString());
				// 担保服务费
				BigDecimal collectionGfees = monthlyInterest.multiply(gfeeApr)
						.setScale(2, BigDecimal.ROUND_HALF_UP);
				borrowCollection.setGuaranteeFees(collectionGfees.toString());
				// 集团用户返利(已替代为红包发放)
				borrowCollection.setReturnFees("0");
				if (i != month.intValue()) {
					// 投资人月本息 = 借款人月本息-平台服务费-担保服务费
					borrowCollection.setRepayAccount(collectionInterest
							.toString());
					// 投资人月本金 = 借款人月本息-平台服务费-担保服务费-投资人利息
					borrowCollection.setCapital(0 + "");
				} else {
					// 最后一个月的本息
					BigDecimal disposableAccount = payMoney
							.add(collectionInterest);
					borrowCollection.setRepayAccount(disposableAccount
							.toString());
					borrowCollection.setCapital(payMoney.toString());
				}
				borrowCollection.setRepayYesaccount("0");
				borrowCollection.setRepayYestime("");
				borrowCollection.setLateDays(0);
				borrowCollection.setLateInterest("0");
				borrowCollection.setAddtime(DateUtil.getTime());
				borrowCollection.setAddip(ip);
				borrowCollection.setInterestFee(disposableInterest
						.multiply(biaoType.getInterestFeeRate()));
				borrowCollection.setInviteFees("0");
				collectionMapper.insertSelective(borrowCollection);
			}
		} else if (stype == 4) {// 天标
			BorrowCollection borrowCollection = new BorrowCollection();
			borrowCollection.setSiteId(0);
			borrowCollection.setStatus(0); // 0:未还；1:已还
			borrowCollection.setOrder(0);
			borrowCollection.setTenderId(tenderId);
			borrowCollection.setRepayTime(DateUtil.getTime()); // 复审时决定时间
			// 借款人所需支付总利息
			Integer days = month.intValue();
			BigDecimal disposableInterest = ConverterUtil.daysInterest(days,
					payMoney, apr);
			// 借款人所需支付总本息
			BigDecimal disposableAccount = payMoney.add(disposableInterest);
			// 投资人利息=借款人利息*利率占比
			BigDecimal collectionInterest = disposableInterest.multiply(
					expectAprApr).setScale(2, BigDecimal.ROUND_HALF_UP);
			borrowCollection.setInterest(collectionInterest.toString());
			// 平台服务费
			BigDecimal collectionBfees = disposableInterest.multiply(bfeeApr)
					.setScale(2, BigDecimal.ROUND_HALF_UP);
			borrowCollection.setServiceFees(collectionBfees.toString());
			// 担保服务费
			BigDecimal collectionGfees = disposableInterest.multiply(gfeeApr)
					.setScale(2, BigDecimal.ROUND_HALF_UP);
			borrowCollection.setGuaranteeFees(collectionGfees.toString());
			// 投资人月本息 = 借款人月本息-平台服务费-担保服务费
			borrowCollection.setRepayAccount((disposableAccount
					.subtract(collectionBfees).subtract(collectionGfees))
					.toString());
			// 集团用户返利(已替代为红包发放)
			borrowCollection.setReturnFees("0");
			// 投资人月本金 = 借款人月本息-平台服务费-担保服务费-投资人利息
			borrowCollection.setCapital(payMoney.toString());
			borrowCollection.setRepayYesaccount("0");
			borrowCollection.setRepayYestime("");
			borrowCollection.setLateDays(0);
			borrowCollection.setLateInterest("0");
			borrowCollection.setAddtime(DateUtil.getTime());
			borrowCollection.setAddip(ip);
			borrowCollection.setInterestFee(disposableInterest
					.multiply(biaoType.getInterestFeeRate()));
			borrowCollection.setInviteFees("0");
			collectionMapper.insertSelective(borrowCollection);
		}
	}

	@Override
	public List<BorrowTender> selectTenderByBorrowId(Integer borrowId) {
		// TODO Auto-generated method stub
		return this.tenderMapper.selectTenderByBorrowId(borrowId);
	}

	/**
	 * 投资列表
	 * 
	 * @return
	 * @author liuhuan
	 */
	@Override
	public List<BorrowTenderVO> selectInvestRecordListPage(PageSearch pageSearch) {
		return this.tenderMapper.selectInvestRecordListPage(pageSearch);
	}

	/**
	 * 昨日投资总额
	 * 
	 * @return author LiLei 2014年5月29日
	 */
	@Override
	public BigDecimal selectYesterdaySumBorrowAccount() {
		// TODO Auto-generated method stub
		return this.tenderMapper.selectYesterdaySumBorrowAccount();
	}

	/**
	 * 我的好友投资列表
	 * 
	 * @return
	 * @author liuhuan
	 */
	@Override
	public List<BorrowTender> selectListByMyFriend(PageSearch pageSearch) {
		return this.tenderMapper.selectListByMyFriend(pageSearch);
	}

	/**
	 * 有效投标总额
	 * 
	 * @author liuhuan
	 */
	@Override
	public BigDecimal sumTenderAccountByUserId(Integer userId) {
		return this.tenderMapper.sumTenderAccountByUserId(userId);
	}

	/**
	 * 协议集合
	 * 
	 * @author liuhuan
	 */
	@Override
	public List<BorrowTenderVO> selectAgreementPage(Integer id) {
		return this.tenderMapper.selectAgreementPage(id);
	}

	/**
	 * 分页查询用户所有的投资记录
	 */
	@Override
	public List<InvesVO> selectByIdListPage(PageSearch pageSearch) {
		// TODO Auto-generated method stub
		return tenderMapper.selectByIdListPage(pageSearch);
	}

	/**
	 * 投资用户根据 group 排序
	 * 
	 * @author liuhuan
	 */
	@Override
	public List<UserTenderVO> selectTendersGroupByUserId(Integer borrowId) {
		return this.tenderMapper.selectTendersGroupByUserId(borrowId);
	}

	@Override
	public void insertSystemMessage(UserTenderMessage utm) {
		// TODO Auto-generated method stub
		Integer tenderCount = tenderMapper.selectByUserId(utm.getUserId());
		if (tenderCount == 1) {
			SystemMessage sm = new SystemMessage();
			sm.setReceiveUser(utm.getUserId());
			sm.setTitle("微积金周年庆，百万现金感恩回馈");
			sm.setIsLook(0);
			sm.setContent("您已完成微积金首笔投资！享受50%收益提升！奖励收益将在满标审核后次日发送至礼品盒！单笔投资本金超过10000元的部分仍按照标的的正常利息计算收益。");
			sm.setAddtime(new Date());
			this.systemMessageMapper.insertSelective(sm);
		}

	}

	@Override
	public List<BorrowTender> selectBrrowTenderByUserId(Integer userId) {

		return tenderMapper.selectBrrowTenderByUserId(userId);
	}

	@Override
	public List<BorrowTender> selectTenderStatusByUserId(Integer userId,
			Integer tenderStatus) {
		
		return tenderMapper.selectByUserIdandTenderStatus(userId, tenderStatus);
	}

	/**
	 * 用户是否有APP投资超过2000元
	 * @param userId
	 * @return
	 */
	@Override
	public Integer hasAppTenderThanTwoThousand(Integer userId) {
		return tenderMapper.hasAppTenderThanTwoThousand(userId);
	}

	@Override
	public BigDecimal myTotalTenderAccountForApril(Integer userId) {
		return tenderMapper.myTotalTenderAccountForApril(userId);
	}

}
