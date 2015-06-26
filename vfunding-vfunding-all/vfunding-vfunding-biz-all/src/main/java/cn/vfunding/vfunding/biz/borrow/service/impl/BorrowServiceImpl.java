package cn.vfunding.vfunding.biz.borrow.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.utils.beans.ConverterUtil;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.FileUtil;
import cn.vfunding.common.framework.utils.beans.ImageUtil;
import cn.vfunding.common.framework.utils.beans.ObjectId;
import cn.vfunding.common.framework.utils.beans.WorkerPropertiesUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.dao.AccountFeelLogMapper;
import cn.vfunding.vfunding.biz.account.dao.AccountFeelMapper;
import cn.vfunding.vfunding.biz.account.dao.AccountLogMapper;
import cn.vfunding.vfunding.biz.account.dao.AccountMapper;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.account.model.AccountFeel;
import cn.vfunding.vfunding.biz.account.model.AccountFeelLog;
import cn.vfunding.vfunding.biz.account.model.AccountLog;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowCollectionMapper;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowMapper;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowRepaymentMapper;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowTenderMapper;
import cn.vfunding.vfunding.biz.borrow.dao.MortgageCarMapper;
import cn.vfunding.vfunding.biz.borrow.dao.MortgageCarPicMapper;
import cn.vfunding.vfunding.biz.borrow.dao.MortgageTypeMapper;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.BorrowCollection;
import cn.vfunding.vfunding.biz.borrow.model.BorrowRepayment;
import cn.vfunding.vfunding.biz.borrow.model.BorrowTender;
import cn.vfunding.vfunding.biz.borrow.model.MortgageCar;
import cn.vfunding.vfunding.biz.borrow.model.MortgageCarPic;
import cn.vfunding.vfunding.biz.borrow.model.MortgageType;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;
import cn.vfunding.vfunding.biz.common.vo.BorrowVO;
import cn.vfunding.vfunding.biz.common.vo.FinalVerifyVO;
import cn.vfunding.vfunding.biz.common.vo.MessageVO;
import cn.vfunding.vfunding.biz.common.vo.ReleaseBorrowVO;
import cn.vfunding.vfunding.biz.common.vo.SuccessTenderVO;
import cn.vfunding.vfunding.biz.common.vo.TenderBorrowVO;
import cn.vfunding.vfunding.biz.common.vo.TheTrialBorrowVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderVO;
import cn.vfunding.vfunding.biz.common.vo.VerifyBorrowVO;
import cn.vfunding.vfunding.biz.common.vo.VerifyVO;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.mq.service.IBorrowAutoService;
import cn.vfunding.vfunding.biz.returns.dao.InviteFeesMapper;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.system.dao.BiaoTypeMapper;
import cn.vfunding.vfunding.biz.system.dao.InvestorsFeeMapper;
import cn.vfunding.vfunding.biz.system.dao.MessageMapper;
import cn.vfunding.vfunding.biz.system.dao.SmsLogMapper;
import cn.vfunding.vfunding.biz.system.model.BiaoType;
import cn.vfunding.vfunding.biz.system.model.InvestorsFee;
import cn.vfunding.vfunding.biz.system.model.Message;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.common.module.activemq.message.SendEmailMessage;
import cn.vfunding.vfunding.common.module.activemq.message.SendVerifyCodeMessage;
import cn.vfunding.vfunding.common.sendconfig.ISendConfigUtil;

@Service("borrowService")
public class BorrowServiceImpl implements IBorrowService {

	@Autowired
	private BorrowMapper borrowMapper;
	@Autowired
	private BorrowTenderMapper borrowTenderMapper;
	@Autowired
	private BorrowCollectionMapper borrowCollectionMapper;
	@Autowired
	private BorrowRepaymentMapper borrowRepaymentMapper;
	@Autowired
	private InvestorsFeeMapper investorsFeeMapper;
	@Autowired
	private InviteFeesMapper inviteFeeMapper;
	@Autowired
	private AccountFeelMapper accountFeelMapper;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private AccountFeelLogMapper accountFeelLogMapper;
	@Autowired
	private AccountLogMapper accountLogMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private BiaoTypeMapper biaoTypeMapper;
	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private SmsLogMapper smsLogMapper;
	@Autowired
	private MortgageCarMapper mortgageCarMapper;
	@Autowired
	private MortgageTypeMapper mortgageTypeMapper;
	@Autowired
	private MortgageCarPicMapper mortgageCarPicMapper;
	@Autowired
	private JmsSenderService jmsSenderUtil;
	@Autowired
	private IBorrowAutoService borrowAutoService;

	/**
	 * 后台借款管理
	 * 
	 * @author liuhuan
	 */
	@Override
	public List<VerifyBorrowVO> selectBorrowSystemListPage(PageSearch pageSearch) {
		return borrowMapper.selectBorrowSystemListPage(pageSearch);
	}

	@Override
	public List<Borrow> selectBorrowForIndex(PageSearch pageSearch, Integer feelRows) {

		List<Borrow> listAll = new ArrayList<Borrow>();
		if (pageSearch.getPage() == 1) {
			listAll = this.borrowMapper.selectFeelBorrow(feelRows);
			for (Borrow b : listAll) {
				Integer status = b.getStatus().intValue();
				BigDecimal aprAccount = b.getAccountYes().divide(b.getAccount(), 2, BigDecimal.ROUND_DOWN);
				BigDecimal rc = b.getRepaymentAccount();
				BigDecimal rys = b.getRepaymentYesaccount();
				if (EmptyUtil.isEmpty(rc)) {
					rc = new BigDecimal(0);
				}
				if (EmptyUtil.isEmpty(rys)) {
					rys = new BigDecimal(0);
				}
				if (status == 10 && aprAccount.doubleValue() < 1) {
					b.setStatus((byte) 11);
				} else if (status == 10 && aprAccount.doubleValue() == 1d) {
					b.setStatus((byte) 12);
				} else if (status == 30 && rc.doubleValue() > rys.doubleValue() + 1d) {
					b.setStatus((byte) 13);
				} else if (status == 30 && rc.doubleValue() <= rys.doubleValue() + 1d) {
					b.setStatus((byte) 14);
				}
			}
		}
		List<Borrow> list = this.borrowMapper.selectBorrowListPage(pageSearch);
		for (Borrow b : list) {
			Integer status = b.getStatus().intValue();
			BigDecimal aprAccount = b.getAccountYes().divide(b.getAccount(), 2, BigDecimal.ROUND_DOWN);
			BigDecimal rc = b.getRepaymentAccount();
			BigDecimal rys = b.getRepaymentYesaccount();
			if (EmptyUtil.isEmpty(rc)) {
				rc = new BigDecimal(0);
			}
			if (EmptyUtil.isEmpty(rys)) {
				rys = new BigDecimal(0);
			}
			if (status == 1 && aprAccount.doubleValue() < 1) {
				b.setStatus((byte) 1);
			} else if (status == 1 && aprAccount.doubleValue() == 1d) {
				b.setStatus((byte) 2);
			} else if (status == 3 && rc.doubleValue() > rys.doubleValue() + 1d) {
				b.setStatus((byte) 3);
			} else if (status == 3 && rc.doubleValue() <= rys.doubleValue() + 1d) {
				b.setStatus((byte) 4);
			}
		}
		return list;
	}

	@Override
	public int insert(Borrow borrow) {
		return this.borrowMapper.insert(borrow);
	}

	@Override
	public Borrow selectById(Integer id) {
		return borrowMapper.selectById(id);
	}

	@Override
	public int insertSelective(Borrow record) {
		return borrowMapper.insertSelective(record);
	}

	/**
	 * 预初审
	 * 
	 * @author liuhuan
	 */
	@Override
	public int updateSaveBorrowForm(Borrow borrow, MortgageCar car) {
		Borrow b = borrowMapper.selectBorrowById(borrow.getId());
		MortgageCar mortgageCar = mortgageCarMapper.selectByPrimaryKey(b.getMortgageId());
		if (EmptyUtil.isNotEmpty(car.getBrand())) {
			mortgageCar.setBrand(car.getBrand());
		}
		if (EmptyUtil.isNotEmpty(car.getAssessMoney())) {
			mortgageCar.setAssessMoney(car.getAssessMoney());
		}
		if (EmptyUtil.isNotEmpty(car.getBuyMoney())) {
			mortgageCar.setBuyMoney(car.getBuyMoney());
		}
		mortgageCarMapper.updateByPrimaryKeySelective(mortgageCar);// 更新汽车信息

		if (EmptyUtil.isNotEmpty(borrow.getVerifyRemark())) {
			b.setVerifyRemark(borrow.getVerifyRemark());
		}
		borrowMapper.updateByPrimaryKeySelective(b);// 更新标的初审备注

		return 1;
	}

	/**
	 * 后台初审审核 admin 审核人
	 * 
	 * @author liuhuan
	 */
	@Override
	public int updateVerifyBorrow(Borrow borrow, Integer userId, InvestorsFee fee, MortgageCar car, String ip) {
		int result = 0;
		borrow.setVerifyUser(userId);
		borrow.setSuccessTime(Integer.parseInt(DateUtil.getTime()));
		borrow.setVerifyTime(Integer.parseInt(DateUtil.getTime()));
		borrow.setVerifyRemark(borrow.getVerifyRemark());
		borrow.setStatus(borrow.getStatus());

		// 更新car
		MortgageCar mortgageCar = mortgageCarMapper.selectByPrimaryKey(borrow.getMortgageId());
		mortgageCar.setBrand(car.getBrand());
		mortgageCar.setAssessMoney(car.getAssessMoney());
		mortgageCar.setBuyMoney(car.getBuyMoney());
		mortgageCarMapper.updateByPrimaryKeySelective(mortgageCar);

		if (borrow.getStatus() != 2) { // 初审不通过则不生成费率表
			InvestorsFee ifee = investorsFeeMapper.selectByBorrowId(borrow.getId());
			if (ifee == null) {
				// 插入投资费率
				InvestorsFee investorsFee = new InvestorsFee();
				investorsFee.setBid(borrow.getId());
				investorsFee.setBfee(fee.getBfee());
				investorsFee.setGfee(fee.getGfee());
				investorsFee.setBfeeType(new BigDecimal("0"));
				investorsFee.setAddtime(Integer.parseInt(DateUtil.getTime()));
				investorsFee.setAddip(ip);
				investorsFeeMapper.insert(investorsFee);
			} else {
				ifee.setBfee(fee.getBfee());
				ifee.setGfee(fee.getGfee());
				investorsFeeMapper.updateByPrimaryKeySelective(ifee);
			}
			result = 1; // 初审成功，允许自动投标
		} else {
			result = 2; // 初审不通过，不触发自动投标
		}
		this.borrowMapper.updateByPrimaryKeySelective(borrow);
		return result;
	}

	/**
	 * @Description:体验标--投标
	 * @param borrow
	 * @param tenderUser
	 * @param accountFeel
	 *            投资账户
	 * @param account
	 *            有效投标金额
	 * @param ip
	 * @return
	 * @author liuhuan
	 */
	@Override
	public synchronized SuccessTenderVO updateTenderFeelBorrow(TenderBorrowVO tenderBorrowvo, String ip) {
		SuccessTenderVO successvo = new SuccessTenderVO();
		String result = "";
		Borrow borrow = borrowMapper.selectById(tenderBorrowvo.getBorrowId());
		AccountFeel accountFeel = accountFeelMapper.selectByUserId(tenderBorrowvo.getUserId()); // 投资人账户
		List<AccountFeelLog> feelLogs = accountFeelLogMapper.selectByUserIdType(new AccountFeelLog(tenderBorrowvo.getUserId(), "recharge"));
		if (borrow == null) {
			result = "该标不存在.";
		} else if (tenderBorrowvo.getUserId() == borrow.getUserId()) {
			result = "不能投自己的标.";
		} else if (tenderBorrowvo.getMoney().compareTo(borrow.getLowestAccount()) == -1) {
			result = "投资金额不能小于最低投标额.";
		} else if (borrow.getMostAccount().intValue() != 0 && tenderBorrowvo.getMoney().compareTo(borrow.getMostAccount()) == 1) {
			result = "投资金额不能高于最大投标额.";
		} else if (borrow.getStatus() != 10 || borrow.getAccount().compareTo(borrow.getAccountYes()) == 0 || !borrow.getBiaoType().equals("feel")) {
			result = "该标不是可投的标.";
		} else if (accountFeel == null) {
			result = "你没有充值体验金，不能投资。";
		} else if (accountFeel.getUseMoney().compareTo(tenderBorrowvo.getMoney()) == -1) {
			result = "体验金不足.";
		} else if (Integer.parseInt(DateUtil.getTime()) - feelLogs.get(0).getAddtime() > 2592000) { // 体验金有效期30天
			result = "体验金已过期.";
		} else {
			// 投标额与标剩余额取小者为有效金额
			BigDecimal waitAccount = borrow.getAccount().subtract(borrow.getAccountYes());
			BigDecimal account = tenderBorrowvo.getMoney().compareTo(waitAccount) == -1 ? tenderBorrowvo.getMoney() : waitAccount; // 有效投标金额

			InvestorsFee investorsFee = investorsFeeMapper.selectByBorrowId(borrow.getId());
			BiaoType biaoType = biaoTypeMapper.selectByTypeName(borrow.getBiaoType());
			borrow.setTenderTimes((short) (borrow.getTenderTimes() + 1));
			borrow.setAccountYes(borrow.getAccountYes().add(account));

			// 实得利率占比
			BigDecimal waitApr = (borrow.getApr().subtract(investorsFee.getBfee()).subtract(investorsFee.getGfee())).divide(borrow.getApr(), 10, BigDecimal.ROUND_DOWN);
			// 服务费利率占比
			BigDecimal bfeeRate = investorsFee.getBfee().divide(borrow.getApr(), 10, BigDecimal.ROUND_DOWN);
			// 担保利率占比
			BigDecimal gfeeRate = investorsFee.getGfee().divide(borrow.getApr(), 10, BigDecimal.ROUND_DOWN);

			BorrowTender borrowTender = new BorrowTender();
			borrowTender.setSiteId(0);
			borrowTender.setUserId(tenderBorrowvo.getUserId());
			borrowTender.setStatus(50); // 体验标50投资待审
			borrowTender.setBorrowId(borrow.getId());
			borrowTender.setMoney(tenderBorrowvo.getMoney().toString()); // 投标金额
			borrowTender.setAccount(account.toString()); // 有效金额
			// XX天利息
			System.out.println(borrow.getApr());
			BigDecimal dayInterest = ConverterUtil.daysInterest(borrow.getTimeLimitDay(), account, borrow.getApr());

			borrowTender.setInterest(dayInterest.multiply(waitApr).setScale(2, BigDecimal.ROUND_DOWN).toString());
			borrowTender.setRepaymentAccount(account.add(new BigDecimal(borrowTender.getInterest())).toString());
			borrowTender.setGuaranteeFees(dayInterest.multiply(gfeeRate).setScale(2, BigDecimal.ROUND_DOWN).toString());
			borrowTender.setServiceFees(dayInterest.multiply(bfeeRate).setScale(2, BigDecimal.ROUND_DOWN).toString());

			// 投资返利、佣金(体验标没有)
			borrowTender.setRepaymentYesaccount("0");
			borrowTender.setRepaymentYesinterest("0");
			borrowTender.setWaitAccount(borrowTender.getRepaymentAccount());
			borrowTender.setWaitInterest(borrowTender.getInterest());
			borrowTender.setWaitGuaranteeFees(borrowTender.getGuaranteeFees());
			borrowTender.setWaitServiceFees(borrowTender.getServiceFees());
			// 投资返利、佣金(体验标没有)
			borrowTender.setWaitReturnFees("0");
			borrowTender.setAddtime(Integer.parseInt(DateUtil.getTime()));
			borrowTender.setAddip(ip);
			borrowTenderMapper.insertSelective(borrowTender); // 插入borrowTender

			// 天标只生成一条待收记录
			BorrowCollection borrowCollection = new BorrowCollection();
			borrowCollection.setStatus(50);
			borrowCollection.setOrder(0);
			borrowCollection.setTenderId(borrowTender.getId());
			borrowCollection.setInterest(borrowTender.getInterest());
			borrowCollection.setServiceFees(borrowTender.getServiceFees());
			borrowCollection.setGuaranteeFees(borrowTender.getGuaranteeFees());
			borrowCollection.setRepayAccount(borrowTender.getRepaymentAccount());
			borrowCollection.setCapital(borrowTender.getAccount());
			borrowCollection.setAddtime(DateUtil.getTime());
			borrowCollection.setAddip(ip);
			borrowCollection.setInterestFee(new BigDecimal(borrowTender.getInterest()).multiply(biaoType.getInterestFeeRate())); // 利息费
			borrowCollectionMapper.insertSelective(borrowCollection);

			accountFeel.setUseMoney(accountFeel.getUseMoney().subtract(account));
			accountFeel.setNoUseMoney(accountFeel.getNoUseMoney().add(account));
			AccountFeelLog accountFeelLog = new AccountFeelLog();
			accountFeelLog.setUserId(tenderBorrowvo.getUserId());
			accountFeelLog.setType("tender");
			accountFeelLog.setMoney(account);
			accountFeelLog.setUseMoney(accountFeel.getUseMoney());
			accountFeelLog.setNoUseMoney(accountFeel.getNoUseMoney());
			accountFeelLog.setCollection(accountFeel.getCollection());
			accountFeelLog.setTotal(accountFeel.getTotal());
			accountFeelLog.setToUser(0);
			accountFeelLog.setRemark("冻结投资者的投标体验金,对标[<a href='/borrow/borrowDetail/" + borrow.getId() + "'>" + borrow.getName() + "</a>]");
			accountFeelLog.setBorrowId(borrow.getId());
			accountFeelLog.setAddtime(Integer.parseInt(DateUtil.getTime()));
			accountFeelLog.setAddip(ip);
			borrowMapper.updateByPrimaryKeySelective(borrow); // 更新borrow
			accountFeelMapper.updateByPrimaryKey(accountFeel); // 更新accountFeel
			accountFeelLogMapper.insert(accountFeelLog); // 插入accountFeel_log
			result = "投资成功。";
			successvo.setAccount(new BigDecimal(borrowTender.getAccount()));

			// 满标自动复审
			if (borrow.getAccount().compareTo(borrow.getAccountYes()) == 0) {
				VerifyVO verifyvo = new VerifyVO();
				verifyvo.setUserId(0);// 系统自动复审
				verifyvo.setVerifyRemark("系统自动复审,复审时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				verifyvo.setStatus(30); // 默认复审成功状态
				verifyvo.setIp(ip);
				verifyvo.setBorrowId(borrow.getId());// 复审标id
				this.updateFinalVerifyFeelBorrow(verifyvo);
			}

		}
		successvo.setResult(result);
		return successvo;
	}

	/**
	 * @Description: 体验标---复审
	 * @param borrow
	 * @param tenders投资者集合
	 * @param remark
	 * @param status
	 *            30：满标复审通过,40:复审不通过 ;
	 * @author liuhuan
	 */
	@Override
	public synchronized String updateFinalVerifyFeelBorrow(VerifyVO verifyvo) {
		String result = "";
		Borrow borrow = borrowMapper.selectById(verifyvo.getBorrowId());
		List<BorrowTender> tenders = borrowTenderMapper.selectListByBorrowId(borrow.getId());
		if (borrow == null || borrow.getAccount().compareTo(borrow.getAccountYes()) != 0) {
			result = "借款标数据异常,审核失败.";
		} else if (borrow.getStatus() != 10) {
			result = "借款标不能复审.";
		} else {
			AccountFeel borrowerAccountFeel = accountFeelMapper.selectByUserId(borrow.getUserId());
			if (verifyvo.getStatus() == 40) {
				// 退标退款
				borrow.setStatus((byte) 40);
				for (BorrowTender tender : tenders) {
					tender.setStatus(20);
					AccountFeel tenderAccountFeel = accountFeelMapper.selectByUserId(tender.getUserId());
					tenderAccountFeel.setNoUseMoney(tenderAccountFeel.getNoUseMoney().subtract(new BigDecimal(tender.getAccount())));
					tenderAccountFeel.setUseMoney(tenderAccountFeel.getUseMoney().add(new BigDecimal(tender.getAccount())));
					AccountFeelLog accountFeelLog = new AccountFeelLog();
					accountFeelLog.setUserId(borrow.getUserId());
					accountFeelLog.setType("invest_false");
					accountFeelLog.setMoney(new BigDecimal(tender.getAccount()));
					accountFeelLog.setUseMoney(tenderAccountFeel.getUseMoney());
					accountFeelLog.setNoUseMoney(tenderAccountFeel.getNoUseMoney());
					accountFeelLog.setCollection(tenderAccountFeel.getCollection());
					accountFeelLog.setToUser(0);
					accountFeelLog.setRemark("投标[<a href='/borrow/borrowDetail/" + borrow.getId() + "' target=_blank><font color=red>" + borrow.getName() + "</font></a>]退标解除冻结款");
					accountFeelLog.setBorrowId(borrow.getId());
					accountFeelLog.setAddtime(Integer.parseInt(DateUtil.getTime()));
					accountFeelLog.setAddip(verifyvo.getIp());
					accountFeelMapper.updateByPrimaryKey(tenderAccountFeel);
					accountFeelLogMapper.insert(accountFeelLog); // 入账日志
					borrowTenderMapper.updateByPrimaryKey(tender);
				}
				borrowMapper.updateByPrimaryKeySelective(borrow);
				result = "退标成功.";
			} else if (verifyvo.getStatus() == 30) {
				borrow.setRepaymentUser(verifyvo.getUserId());
				borrow.setRepaymentTime(Integer.parseInt(DateUtil.getTime()));
				borrow.setRepaymentRemark(verifyvo.getRepaymentRemark());
				borrow.setStatus((byte) 30);
				borrow.setRepaymentAccount(ConverterUtil.daysInterest(borrow.getTimeLimitDay(), borrow.getAccount(), borrow.getApr()).add(borrow.getAccount()));
				borrow.setEachTime(DateUtil.getNextDay(borrow.getTimeLimitDay(), "dd") + "日");
				borrow.setEndTime(Integer.parseInt(DateUtil.getDayTime(borrow.getTimeLimitDay())));
				borrow.setSuccessTime(Integer.parseInt(DateUtil.getTime()));

				BigDecimal dayInterest = ConverterUtil.daysInterest(borrow.getTimeLimitDay(), borrow.getAccount(), borrow.getApr());
				BorrowRepayment borrowRepayment = new BorrowRepayment();
				borrowRepayment.setStatus(30); // 30--体验标未还；40体验标已还
				borrowRepayment.setWebstatus(0);
				borrowRepayment.setOrder(0);
				borrowRepayment.setBorrowId(borrow.getId());
				borrowRepayment.setRepaymentTime(Integer.parseInt(DateUtil.getDayTime(borrow.getTimeLimitDay())));
				borrowRepayment.setRepaymentYesaccount("0");
				borrowRepayment.setRepaymentAccount(borrow.getAccount().add(dayInterest).toString());
				borrowRepayment.setInterest(dayInterest.toString());
				borrowRepayment.setCapital(borrow.getAccount().toString());
				borrowRepayment.setReminderFee("0");
				borrowRepayment.setAddtime(DateUtil.getTime());
				borrowRepayment.setAddip(verifyvo.getIp());
				borrowRepaymentMapper.insertSelective(borrowRepayment);// 插入repayment

				for (BorrowTender tender : tenders) {
					tender.setStatus(10);
					List<BorrowCollection> borrowCollections = borrowCollectionMapper.selectByTenderId(tender.getId());// 体验标投资人只有一条待收记录
					System.out.println(borrowCollections.size() + "记录条数");
					BigDecimal collectionForAccount = new BigDecimal("0");
					BigDecimal collectionForInterest = new BigDecimal("0");
					for (BorrowCollection collection : borrowCollections) {
						collection.setTenderId(tender.getId());
						collection.setStatus(30);
						collection.setRepayTime(DateUtil.getDayTime(borrow.getTimeLimitDay()));
						collectionForAccount = collectionForAccount.add(ConverterUtil.StringToBigDecimal(collection.getRepayAccount()));
						collectionForInterest = collectionForInterest.add(ConverterUtil.StringToBigDecimal(collection.getInterest()));
						borrowCollectionMapper.updateByPrimaryKey(collection); // 更新collection
					}

					// 投资人 账户解冻
					AccountFeel tenderAccountFeel = accountFeelMapper.selectByUserId(tender.getUserId());
					tenderAccountFeel.setNoUseMoney(tenderAccountFeel.getNoUseMoney().subtract(new BigDecimal(tender.getAccount())));
					AccountFeelLog accountFeelLog = new AccountFeelLog();
					accountFeelLog.setUserId(tender.getUserId());
					accountFeelLog.setType("invest"); // 解冻
					accountFeelLog.setMoney(new BigDecimal(tender.getAccount()));
					accountFeelLog.setUseMoney(tenderAccountFeel.getUseMoney());
					accountFeelLog.setNoUseMoney(tenderAccountFeel.getNoUseMoney());
					accountFeelLog.setCollection(tenderAccountFeel.getCollection());
					accountFeelLog.setTotal(tenderAccountFeel.getTotal());
					accountFeelLog.setToUser(0);
					accountFeelLog.setRemark("投标[<a href='/borrow/borrowDetail/" + borrow.getId() + "' target=_blank><font color=red>" + borrow.getName() + "</font></a>]成功费用扣除");
					accountFeelLog.setBorrowId(borrow.getId());
					accountFeelLog.setAddtime(Integer.parseInt(DateUtil.getTime()));
					accountFeelLog.setAddip(verifyvo.getIp());
					accountFeelMapper.updateByPrimaryKey(tenderAccountFeel);
					accountFeelLogMapper.insert(accountFeelLog); // 入账日志
					// 更新账户待收
					tenderAccountFeel.setCollection(tenderAccountFeel.getCollection().add(collectionForAccount));
					tenderAccountFeel.setTotal(tenderAccountFeel.getTotal().add(collectionForInterest));
					// 所得利息累计(还款时候需要相应扣除)
					tenderAccountFeel.setOther(new BigDecimal(tenderAccountFeel.getOther()).add(collectionForInterest).toString());
					AccountFeelLog accountFeelLog2 = new AccountFeelLog();
					accountFeelLog2.setUserId(tender.getUserId());
					accountFeelLog2.setType("tender"); // 待收
					accountFeelLog2.setMoney(collectionForAccount);
					accountFeelLog2.setUseMoney(tenderAccountFeel.getUseMoney());
					accountFeelLog2.setNoUseMoney(tenderAccountFeel.getNoUseMoney());
					accountFeelLog2.setCollection(tenderAccountFeel.getCollection());
					accountFeelLog2.setTotal(tenderAccountFeel.getTotal());
					accountFeelLog2.setToUser(0);
					accountFeelLog2.setRemark("待收项目[<a href='/borrow/borrowDetail/" + borrow.getId() + "' target=_blank><font color=red>" + borrow.getName() + "</font></a>]");
					accountFeelLog2.setBorrowId(borrow.getId());
					accountFeelLog2.setAddtime(Integer.parseInt(DateUtil.getTime()));
					accountFeelLog2.setAddip(verifyvo.getIp());
					accountFeelMapper.updateByPrimaryKey(tenderAccountFeel);
					accountFeelLogMapper.insert(accountFeelLog2); // 入账日志

					Message message = new Message();
					message.setSentUser(0);
					message.setReceiveUser(tender.getUserId());
					message.setName("投标成功");
					message.setContent("投标[<a href='/borrow/borrowDetail/" + borrow.getId() + "' target=_blank><font color=red>" + borrow.getName() + "</font></a>]成功费用扣除");
					message.setStatus(0);
					message.setType("system");
					message.setSented(null);
					message.setDeltype(0);
					message.setAddtime(DateUtil.getTime());
					message.setAddip(verifyvo.getIp());
					messageMapper.insert(message);

					borrowTenderMapper.updateByPrimaryKey(tender); // 更新tender
				}
				borrowerAccountFeel.setUseMoney(borrowerAccountFeel.getUseMoney().add(borrow.getAccount()));
				borrowerAccountFeel.setTotal(borrowerAccountFeel.getTotal().add(borrow.getAccount()));
				AccountFeelLog accountFeelLog = new AccountFeelLog();
				accountFeelLog.setUserId(borrow.getUserId());
				accountFeelLog.setType("borrow_success");
				accountFeelLog.setMoney(borrow.getAccount());
				accountFeelLog.setUseMoney(borrowerAccountFeel.getUseMoney());
				accountFeelLog.setNoUseMoney(borrowerAccountFeel.getNoUseMoney());
				accountFeelLog.setCollection(borrowerAccountFeel.getCollection());
				accountFeelLog.setTotal(borrowerAccountFeel.getTotal());
				accountFeelLog.setToUser(0);
				accountFeelLog.setRemark("通过[<a href='/borrow/borrowDetail/" + borrow.getId() + "' target=_blank>" + borrow.getName() + "</a>]借到的款");
				accountFeelLog.setBorrowId(borrow.getId());
				accountFeelLog.setAddtime(Integer.parseInt(DateUtil.getTime()));
				accountFeelLog.setAddip(verifyvo.getIp());
				accountFeelLogMapper.insert(accountFeelLog); // 入账日志
				accountFeelMapper.updateByPrimaryKey(borrowerAccountFeel); // 借款人--入账

				// 冻结保证金---体验标不冻结
				/*
				 * borrowerAccountFeel.setUseMoney(borrowerAccountFeel.getUseMoney
				 * ().subtract(borrow.getForstAccount()));
				 * borrowerAccountFeel.setNoUseMoney
				 * (borrowerAccountFeel.getNoUseMoney
				 * ().add(borrow.getForstAccount())); AccountFeelLog
				 * accountFeelLog2 = new AccountFeelLog();
				 * accountFeelLog2.setUserId(borrow.getUserId());
				 * accountFeelLog2.setType("margin"); // 冻结保证金
				 * accountFeelLog2.setMoney(borrow.getForstAccount());
				 * accountFeelLog2
				 * .setUseMoney(borrowerAccountFeel.getUseMoney());
				 * accountFeelLog2
				 * .setNoUseMoney(borrowerAccountFeel.getNoUseMoney());
				 * accountFeelLog2
				 * .setCollection(borrowerAccountFeel.getCollection());
				 * accountFeelLog2.setTotal(borrowerAccountFeel.getTotal());
				 * accountFeelLog2.setToUser(0); accountFeelLog2.setRemark(
				 * "冻结借款标的[<a href='/borrow/borrowDetail/" +borrow.getId
				 * ()+"' target=_blank>"+borrow.getName()+"</a>]的保证金");
				 * accountFeelLog2.setBorrowId(borrow.getId());
				 * accountFeelLog2.setAddtime
				 * (Integer.parseInt(DateUtil.getTime()));
				 * accountFeelLog2.setAddip(ip);
				 * accountFeelLogMapper.insert(accountFeelLog2); // 入账日志
				 * accountFeelMapper.updateByPrimaryKey(borrowerAccountFeel); //
				 * 借款人--入账
				 */
				Message message = new Message();
				message.setSentUser(0);
				message.setReceiveUser(borrow.getUserId());
				message.setName("借款入账");
				message.setContent("通过[<a href='/borrow/borrowDetail/" + borrow.getId() + "' target=_blank>" + borrow.getName() + "</a>]借到的款");
				message.setStatus(0);
				message.setType("system");
				message.setSented(null);
				message.setDeltype(0);
				message.setAddtime(DateUtil.getTime());
				message.setAddip(verifyvo.getIp());
				messageMapper.insert(message);

				borrowMapper.updateByPrimaryKeySelective(borrow); // 更新borrow
				result = "审核通过.";
			} else {
				result = "数据异常，审核失败";
			}
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(DateUtil.getNextDayTime(new Date(), 1).getTime());
	}

	/**
	 * 天标---复审
	 */
	@Override
	public synchronized String updateFinalVerifyDayBorrow(FinalVerifyVO finalVerify) {
		String result = "成功";
		Borrow borrow = borrowMapper.selectById(finalVerify.getBorrowId());
		List<BorrowTender> tenders = borrowTenderMapper.selectListByBorrowId(borrow.getId());
		if (borrow == null || borrow.getAccount().compareTo(borrow.getAccountYes()) != 0) {
			result = "借款标数据异常,审核失败.";
		} else if (borrow.getStatus() != 1) {
			result = "借款标不能复审.";
		} else {
			// 允许误差0.01
			BigDecimal accountAll = new BigDecimal("0");
			for (BorrowTender tender : tenders) {
				accountAll = accountAll.add(ConverterUtil.StringToBigDecimal(tender.getAccount()));
			}
			if (borrow.getAccount().subtract(accountAll).abs().compareTo(new BigDecimal("0.01")) == 1) {
				throw new BusinessException("8005011", "复审失败，投标数据异常");
			}
			if (finalVerify.getStatus() == 4) { // 复审不通过
				result = this.updateFailBorrow(borrow, 4, finalVerify.getIp()); // 返还投资人钱
			} else if (finalVerify.getStatus() == 3) {
				borrow.setRepaymentUser(finalVerify.getAdminUserId());
				borrow.setRepaymentTime(Integer.parseInt(DateUtil.getTime()));
				borrow.setRepaymentRemark(finalVerify.getRemark());
				borrow.setStatus((byte) 3);
				borrow.setSuccessTime(Integer.parseInt(DateUtil.getTime()));// 复审时间
				Integer days = borrow.getTimeLimitDay().intValue();
				borrow.setEndTime(Integer.parseInt(String.valueOf(DateUtil.getNextDayTime(new Date(), days).getTime() / 1000))); // 预计还款时间

				BigDecimal Interest = borrow.getAccount().multiply(new BigDecimal(days)).multiply(borrow.getApr()).divide(new BigDecimal("36500"), 2, BigDecimal.ROUND_HALF_UP);
				// 总还额度
				borrow.setRepaymentAccount(Interest.add(borrow.getAccount()));

				// 天标到期一次性还款
				BorrowRepayment repayment = new BorrowRepayment();
				repayment.setSiteId(0);
				repayment.setStatus(0);
				repayment.setOrder(0);
				repayment.setBorrowId(borrow.getId());
				repayment.setRepaymentTime(Integer.parseInt(DateUtil.getDayTime(borrow.getTimeLimitDay())));
				repayment.setRepaymentAccount(borrow.getRepaymentAccount().toString());
				repayment.setInterest(borrow.getRepaymentAccount().subtract(borrow.getAccount()).toString());
				repayment.setCapital(borrow.getAccount().toString());
				repayment.setAddtime(DateUtil.getTime());
				repayment.setAddip(finalVerify.getIp());
				borrowRepaymentMapper.insertSelective(repayment);

				// 投资人资金处理
				for (BorrowTender tender : tenders) {
					tender.setStatus(1); // 投资成功
					BigDecimal collectionForAccount = new BigDecimal("0.00");// 总待收
					BigDecimal collectionForInterest = new BigDecimal("0.00");// 新增利息
					// 天标只有一条待收
					List<BorrowCollection> borrowCollections = borrowCollectionMapper.selectByTenderId(tender.getId()); // 该标投资待还记录
					for (BorrowCollection collection : borrowCollections) {
						collection.setStatus(0); // 未还
						collection.setRepayTime(repayment.getRepaymentTime().toString());
						borrowCollectionMapper.updateByPrimaryKeySelective(collection);
						collectionForAccount = collectionForAccount.add(ConverterUtil.StringToBigDecimal(collection.getRepayAccount()));
						collectionForInterest = collectionForInterest.add(ConverterUtil.StringToBigDecimal(collection.getInterest()));
					}
					borrowTenderMapper.updateByPrimaryKeySelective(tender);
					BigDecimal tender_money = ConverterUtil.StringToBigDecimal(tender.getAccount()); // 解冻金额
					// 投资人 解--冻
					Account tenderAccount = accountMapper.selectByUserId(tender.getUserId());
					tenderAccount.setNoUseMoney(tenderAccount.getNoUseMoney().subtract(tender_money));
					AccountLog accountLog = new AccountLog();
					accountLog.setUserId(tender.getUserId());
					accountLog.setType("invest"); // 解冻
					accountLog.setMoney(tender_money);
					accountLog.setUseMoney(tenderAccount.getUseMoney());
					accountLog.setNoUseMoney(tenderAccount.getNoUseMoney());
					accountLog.setCollection(tenderAccount.getCollection());
					accountLog.setTotal(tenderAccount.getTotal());
					accountLog.setToUser(0);
					accountLog.setRemark("投标" + borrow.getName() + "成功费用扣除");
					accountLog.setBorrowId(borrow.getId());
					accountLog.setAddtime(Integer.parseInt(DateUtil.getTime()));
					accountLog.setAddip(finalVerify.getIp());
					accountMapper.updateByPrimaryKey(tenderAccount);
					accountLogMapper.insert(accountLog); // 入账日志

					// 更新待收
					tenderAccount.setCollection(tenderAccount.getCollection().add(collectionForAccount));
					tenderAccount.setTotal(tenderAccount.getTotal().add(collectionForAccount));
					AccountLog accountLog1 = new AccountLog();
					accountLog1.setUserId(tender.getUserId());
					accountLog1.setType("tender_collection");
					accountLog1.setMoney(collectionForAccount);
					accountLog1.setUseMoney(tenderAccount.getUseMoney());
					accountLog1.setNoUseMoney(tenderAccount.getNoUseMoney());
					accountLog1.setCollection(tenderAccount.getCollection());
					accountLog1.setTotal(tenderAccount.getTotal());
					accountLog1.setToUser(0);
					accountLog1.setRemark("待收项目" + borrow.getName());
					accountLog1.setBorrowId(borrow.getId());
					accountLog1.setAddtime(Integer.parseInt(DateUtil.getTime()));
					accountLog1.setAddip(finalVerify.getIp());
					Message message = new Message();
					message.setSentUser(0);
					message.setReceiveUser(tender.getUserId());
					message.setName("借出成功，扣除冻结款");
					message.setContent("投标" + borrow.getName() + "成功费用扣除");
					message.setStatus(0);
					message.setType("system");
					message.setSented(null);
					message.setDeltype(0);
					message.setAddtime(DateUtil.getTime());
					message.setAddip(finalVerify.getIp());
					accountMapper.updateByPrimaryKey(tenderAccount);
					accountLogMapper.insert(accountLog1); // 入账日志
					messageMapper.insert(message); // 投资人站内信
				}

				// 借款入账
				Account borrowAccount = accountMapper.selectByUserId(borrow.getUserId());
				borrowAccount.setUseMoney(borrowAccount.getUseMoney().add(borrow.getAccount()));
				borrowAccount.setTotal(borrowAccount.getTotal().add(borrow.getAccount()));
				AccountLog log = new AccountLog();
				log.setUserId(borrow.getUserId());
				log.setType("borrow_success");
				log.setMoney(borrow.getAccount());
				log.setUseMoney(borrowAccount.getUseMoney());
				log.setNoUseMoney(borrowAccount.getNoUseMoney());
				log.setCollection(borrowAccount.getCollection());
				log.setTotal(borrowAccount.getTotal());
				log.setToUser(0);
				log.setRemark("通过" + borrow.getName() + "借到的款");
				log.setBorrowId(borrow.getId());
				log.setAddtime(Integer.parseInt(DateUtil.getTime()));
				log.setAddip(finalVerify.getIp());
				Message message = new Message();
				message.setSentUser(0);
				message.setReceiveUser(borrow.getUserId());
				message.setName("借款入账");
				message.setContent("通过" + borrow.getName() + "借到的款");
				message.setStatus(0);
				message.setType("system");
				message.setDeltype(0);
				message.setAddtime(DateUtil.getTime());
				message.setAddip(finalVerify.getIp());
				accountMapper.updateByPrimaryKeySelective(borrowAccount);
				accountLogMapper.insert(log);
				messageMapper.insert(message);
				borrowMapper.updateByPrimaryKeySelective(borrow);

				// 投资人复审发邮件
				String content = "你的冻结金额于" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "成功扣除，[" + borrow.getName() + "]标成功满额。";
				List<UserTenderVO> tendersGroupUserId = borrowTenderMapper.selectTendersGroupByUserId(borrow.getId());
				for (UserTenderVO vo : tendersGroupUserId) {
					if (EmptyUtil.isNotEmpty(vo.getEmail())) {
						SendEmailMessage sendEmail = new SendEmailMessage();
						sendEmail.setEmail(vo.getEmail());
						sendEmail.setTitle("8戒满标审核结果");
						sendEmail.setContent(content);
						this.jmsSenderUtil.asynSendSystemJms(sendEmail);
					}
					if (vo.getTenderIp().equals("borrowAuto")) {// 自动投标
						this.jmsSenderUtil.sendSms(vo.getPhone(), ISendConfigUtil.SMS_VERIFYBORROW_AUTOINVEST, vo.getUsername(), borrow.getName(), vo.getTenderAccount().toString());
					} else {
						this.jmsSenderUtil.sendSms(vo.getPhone(), ISendConfigUtil.SMS_VERIFYBORROW, vo.getUsername(), borrow.getName());
					}
				}
				// 借款人发邮件
				String toBorrower = "您的[" + borrow.getName() + "]标已成功满额复审通过，您已成功借款" + borrow.getAccount() + "元。";
				User user = userMapper.selectByPrimaryKey(borrow.getUserId());
				SendEmailMessage sendEmail = new SendEmailMessage();
				sendEmail.setEmail(user.getEmail());
				sendEmail.setTitle("微积金满标审核结果");
				sendEmail.setContent(toBorrower);
				this.jmsSenderUtil.asynSendSystemJms(sendEmail);
				result = "成功";
			}
		}
		return result;
	}

	/**
	 * 普通标--复审
	 * 
	 * @param borrow
	 * @param tenders
	 * @param remark
	 * @param status
	 *            3：满标复审通过,4:复审不通过
	 * @param ip
	 * @return
	 * @author liuhuan
	 */
	@Override
	public synchronized String updateFinalVerifyBorrow(FinalVerifyVO finalVerify) {
		String result = "";
		Borrow borrow = borrowMapper.selectById(finalVerify.getBorrowId());
		if (borrow.getIsday() == 1) {
			this.updateFinalVerifyDayBorrow(finalVerify);
		}
		List<BorrowTender> tenders = borrowTenderMapper.selectListByBorrowId(borrow.getId());
		if (borrow == null || borrow.getAccount().compareTo(borrow.getAccountYes()) != 0) {
			result = "借款标数据异常,审核失败.";
		} else if (borrow.getStatus() != 1) {
			result = "借款标不能复审.";
		} else {
			BiaoType biaoType = biaoTypeMapper.selectByTypeName(borrow.getBiaoType());
			// 允许误差0.01
			BigDecimal accountAll = new BigDecimal("0");
			for (BorrowTender tender : tenders) {
				accountAll = accountAll.add(ConverterUtil.StringToBigDecimal(tender.getAccount()));
			}
			if (borrow.getAccount().subtract(accountAll).abs().compareTo(new BigDecimal("0.01")) == 1) {
				throw new BusinessException("8005011", "复审失败，投标数据异常");
			}
			if (finalVerify.getStatus() == 4) { // 复审不通过
				result = this.updateFailBorrow(borrow, 4, finalVerify.getIp()); // 返还投资人钱
			} else if (finalVerify.getStatus() == 3) {
				borrow.setRepaymentUser(finalVerify.getAdminUserId());
				borrow.setRepaymentTime(Integer.parseInt(DateUtil.getTime()));
				borrow.setRepaymentRemark(finalVerify.getRemark());
				borrow.setStatus((byte) 3);
				borrow.setForstAccount(borrow.getAccount().multiply(biaoType.getFrostRate()));
				borrow.setEachTime("每月" + Calendar.getInstance(TimeZone.getDefault()).get(Calendar.DATE) + "日");
				borrow.setSuccessTime(Integer.parseInt(DateUtil.getTime()));// 复审时间
				borrow.setEndTime(Integer.parseInt(DateUtil.getMonthTime(borrow.getTimeLimit()))); // 预计还款时间

				// 总还额度
				borrow.setRepaymentAccount(borrow.getStyle() == 0 ? ConverterUtil.monthlyAccount(borrow.getAccount(), borrow.getApr(), new BigDecimal("" + borrow.getTimeLimit())).multiply(new BigDecimal("" + borrow.getTimeLimit())) : borrow.getAccount().add(ConverterUtil.disposableInterest(borrow.getAccount(), borrow.getApr(), new BigDecimal(borrow.getTimeLimit()))));

				if (borrow.getStyle() == 2) {// 2--到期一次性还款
					BorrowRepayment repayment = new BorrowRepayment();
					repayment.setSiteId(0);
					repayment.setStatus(0);
					repayment.setOrder(0);
					repayment.setBorrowId(borrow.getId());
					repayment.setRepaymentTime(Integer.parseInt(DateUtil.getMonthTime(borrow.getTimeLimit())));
					repayment.setRepaymentAccount(borrow.getAccount().add(ConverterUtil.disposableInterest(borrow.getAccount(), borrow.getApr(), new BigDecimal(borrow.getTimeLimit()))).toString());
					repayment.setInterest(ConverterUtil.disposableInterest(borrow.getAccount(), borrow.getApr(), new BigDecimal(borrow.getTimeLimit())).toString());
					repayment.setCapital(ConverterUtil.StringToBigDecimal(repayment.getRepaymentAccount()).subtract(ConverterUtil.StringToBigDecimal(repayment.getInterest())).toString());
					repayment.setAddtime(DateUtil.getTime());
					repayment.setAddip(finalVerify.getIp());
					borrowRepaymentMapper.insertSelective(repayment);
				} else {
					BigDecimal monthlyAccount3 = new BigDecimal("0");
					BigDecimal monthlyInterest3 = new BigDecimal("0");
					if (borrow.getStyle() == 1) {
						// 等本等息-每月还款总额
						monthlyAccount3 = ConverterUtil.monthlyAccount1(borrow.getAccount(), borrow.getApr(), new BigDecimal("" + borrow.getTimeLimit()));
						// 等本等息-每月还款利息
						monthlyInterest3 = ConverterUtil.monthlyInterest(borrow.getAccount(), borrow.getApr());
					}

					for (int i = 0; i < borrow.getTimeLimit(); i++) {
						BorrowRepayment repayment = new BorrowRepayment();
						repayment.setSiteId(0);
						repayment.setStatus(0);
						repayment.setWebstatus(0);
						repayment.setOrder(i);
						repayment.setBorrowId(borrow.getId());
						if (borrow.getStyle() == 0) { // 0--等额本息还款
							// 每月还款金额
							borrow.setMonthlyRepayment(ConverterUtil.monthlyAccount(borrow.getAccount(), borrow.getApr(), new BigDecimal("" + borrow.getTimeLimit())));
							borrow.setPaymentAccount(borrow.getMonthlyRepayment());
							// 等额本息-按月还款每月利息
							BigDecimal monthlyInterest = ConverterUtil.monthlyInterest(borrow.getAccount(), borrow.getApr(), new BigDecimal("" + borrow.getTimeLimit()), new BigDecimal(i + 1));

							repayment.setRepaymentTime(Integer.parseInt(DateUtil.getMonthTime(i + 1)));
							repayment.setRepaymentAccount(ConverterUtil.monthlyAccount(borrow.getAccount(), borrow.getApr(), new BigDecimal(borrow.getTimeLimit())).toString());
							repayment.setInterest(monthlyInterest.toString());
							repayment.setCapital(ConverterUtil.StringToBigDecimal(repayment.getRepaymentAccount()).subtract(ConverterUtil.StringToBigDecimal(repayment.getInterest())).toString());
						} else if (borrow.getStyle() == 3) { // 3--按月付息到期还本
							// 按月付息-每月利息
							BigDecimal monthlyInterest2 = ConverterUtil.disposableInterest(borrow.getAccount(), borrow.getApr(), new BigDecimal(borrow.getTimeLimit())).divide(new BigDecimal(borrow.getTimeLimit()), BigDecimal.ROUND_DOWN);
							borrow.setMonthlyRepayment(monthlyInterest2);
							borrow.setPaymentAccount(borrow.getMonthlyRepayment());

							repayment.setRepaymentTime(Integer.parseInt(DateUtil.getMonthTime(i + 1)));
							repayment.setRepaymentAccount(i + 1 == borrow.getTimeLimit() ? monthlyInterest2.add(borrow.getAccount()).toString() : monthlyInterest2.toString());
							repayment.setInterest(monthlyInterest2.toString());
							// 按月付息，最后一期月还本金为借款本金
							repayment.setCapital((i + 1) == borrow.getTimeLimit() ? borrow.getAccount().toString() : "0");
						} else if (borrow.getStyle() == 1) { // 1--等本等息
							borrow.setMonthlyRepayment(monthlyAccount3);
							borrow.setPaymentAccount(borrow.getMonthlyRepayment());
							// 等本等息 复审
							repayment.setRepaymentTime(Integer.parseInt(DateUtil.getMonthTime(i + 1)));
							repayment.setRepaymentAccount(monthlyAccount3.toString());
							repayment.setInterest(monthlyInterest3.toString());
							repayment.setCapital(new BigDecimal(repayment.getRepaymentAccount()).subtract(new BigDecimal(repayment.getInterest())).toString());
						} else {
							throw new BusinessException("8005011", "复审失败，还款方式错误");
						}
						repayment.setAddtime(DateUtil.getTime());
						repayment.setAddip(finalVerify.getIp());
						borrowRepaymentMapper.insertSelective(repayment);
					}
				}

				// tenderList
				for (BorrowTender tender : tenders) {
					tender.setStatus(1); // 投资成功
					BigDecimal collectionForAccount = new BigDecimal("0.00");// 总待收
					BigDecimal collectionForInterest = new BigDecimal("0.00");// 新增利息
					int j = 1;
					List<BorrowCollection> borrowCollections = borrowCollectionMapper.selectByTenderId(tender.getId()); // 该标投资待还记录
					for (BorrowCollection collection : borrowCollections) {
						collection.setStatus(0); // 未还
						collection.setRepayTime(DateUtil.getMonthTime(j));
						borrowCollectionMapper.updateByPrimaryKeySelective(collection);
						collectionForAccount = collectionForAccount.add(ConverterUtil.StringToBigDecimal(collection.getRepayAccount()));
						collectionForInterest = collectionForInterest.add(ConverterUtil.StringToBigDecimal(collection.getInterest()));
						j++;
					}
					borrowTenderMapper.updateByPrimaryKeySelective(tender);
					BigDecimal tender_money = ConverterUtil.StringToBigDecimal(tender.getAccount()); // 解
																										// 冻金额
					// 投资人 解--冻
					Account tenderAccount = accountMapper.selectByUserId(tender.getUserId());
					tenderAccount.setNoUseMoney(tenderAccount.getNoUseMoney().subtract(tender_money));
					tenderAccount.setTotal(tenderAccount.getTotal().subtract(tender_money));
					AccountLog accountLog = new AccountLog();
					accountLog.setUserId(tender.getUserId());
					accountLog.setType("invest"); // 解冻
					accountLog.setMoney(tender_money);
					accountLog.setUseMoney(tenderAccount.getUseMoney());
					accountLog.setNoUseMoney(tenderAccount.getNoUseMoney());
					accountLog.setCollection(tenderAccount.getCollection());
					accountLog.setTotal(tenderAccount.getTotal());
					accountLog.setToUser(0);
					accountLog.setRemark("投标[<a href='/borrow/borrowDetail/" + borrow.getId() + "' target=_blank><font color=red>" + borrow.getName() + "</font></a>]成功费用扣除");
					accountLog.setBorrowId(borrow.getId());
					accountLog.setAddtime(Integer.parseInt(DateUtil.getTime()));
					accountLog.setAddip(finalVerify.getIp());
					accountMapper.updateByPrimaryKey(tenderAccount);
					accountLogMapper.insert(accountLog); // 入账日志

					// 更新待收
					tenderAccount.setCollection(tenderAccount.getCollection().add(collectionForAccount));
					tenderAccount.setTotal(tenderAccount.getTotal().add(collectionForAccount));
					AccountLog accountLog1 = new AccountLog();
					accountLog1.setUserId(tender.getUserId());
					accountLog1.setType("tender");
					accountLog1.setMoney(collectionForAccount);
					accountLog1.setUseMoney(tenderAccount.getUseMoney());
					accountLog1.setNoUseMoney(tenderAccount.getNoUseMoney());
					accountLog1.setCollection(tenderAccount.getCollection());
					accountLog1.setTotal(tenderAccount.getTotal());
					accountLog1.setToUser(0);
					accountLog1.setRemark("待收项目[<a href='/borrow/borrowDetail/" + borrow.getId() + "' target=_blank><font color=red>" + borrow.getName() + "</font></a>]");
					accountLog1.setBorrowId(borrow.getId());
					accountLog1.setAddtime(Integer.parseInt(DateUtil.getTime()));
					accountLog1.setAddip(finalVerify.getIp());
					Message message = new Message();
					message.setSentUser(0);
					message.setReceiveUser(tender.getUserId());
					message.setName("借出成功，扣除冻结款");
					message.setContent("投标[<a href='/borrow/borrowDetail/" + borrow.getId() + "' target=_blank><font color=red>" + borrow.getName() + "</font></a>]成功费用扣除");
					message.setStatus(0);
					message.setType("system");
					message.setSented(null);
					message.setDeltype(0);
					message.setAddtime(DateUtil.getTime());
					message.setAddip(finalVerify.getIp());
					accountMapper.updateByPrimaryKey(tenderAccount);
					accountLogMapper.insert(accountLog1); // 入账日志
					messageMapper.insert(message); // 投资人站内信

				}
				// 借款入账
				Account borrowAccount = accountMapper.selectByUserId(borrow.getUserId());
				borrowAccount.setUseMoney(borrowAccount.getUseMoney().add(borrow.getAccount()));
				borrowAccount.setTotal(borrowAccount.getTotal().add(borrow.getAccount()));
				AccountLog log = new AccountLog();
				log.setUserId(borrow.getUserId());
				log.setType("borrow_success");
				log.setMoney(borrow.getAccount());
				log.setUseMoney(borrowAccount.getUseMoney());
				log.setNoUseMoney(borrowAccount.getNoUseMoney());
				log.setCollection(borrowAccount.getCollection());
				log.setTotal(borrowAccount.getTotal());
				log.setToUser(0);
				log.setRemark("通过[<a href='/borrow/borrowDetail/" + borrow.getId() + "' target=_blank>" + borrow.getName() + "</a>]借到的款");
				log.setBorrowId(borrow.getId());
				log.setAddtime(Integer.parseInt(DateUtil.getTime()));
				log.setAddip(finalVerify.getIp());
				Message message = new Message();
				message.setSentUser(0);
				message.setReceiveUser(borrow.getUserId());
				message.setName("借款入账");
				message.setContent("通过[<a href='/borrow/borrowDetail/" + borrow.getId() + "' target=_blank>" + borrow.getName() + "</a>]借到的款");
				message.setStatus(0);
				message.setType("system");
				message.setSented(null);
				message.setDeltype(0);
				message.setAddtime(DateUtil.getTime());
				message.setAddip(finalVerify.getIp());
				accountMapper.updateByPrimaryKeySelective(borrowAccount);
				accountLogMapper.insert(log);
				messageMapper.insert(message);
				// 保证金
				borrowAccount.setUseMoney(borrowAccount.getUseMoney().subtract(borrow.getForstAccount()));
				borrowAccount.setNoUseMoney(borrowAccount.getNoUseMoney().add(borrow.getForstAccount()));
				AccountLog log1 = new AccountLog();
				log1.setUserId(borrow.getUserId());
				log1.setType("margin");
				log1.setMoney(borrow.getForstAccount());
				log1.setUseMoney(borrowAccount.getUseMoney());
				log1.setNoUseMoney(borrowAccount.getNoUseMoney());
				log1.setCollection(borrowAccount.getCollection());
				log1.setTotal(borrowAccount.getTotal());
				log1.setToUser(0);
				log1.setRemark("冻结借款标的[<a href='/borrow/borrowDetail/" + borrow.getId() + "' target=_blank>" + borrow.getName() + "</a>]的保证金");
				log1.setBorrowId(borrow.getId());
				log1.setAddtime(Integer.parseInt(DateUtil.getTime()));
				log1.setAddip(finalVerify.getIp());
				accountMapper.updateByPrimaryKeySelective(borrowAccount);
				accountLogMapper.insert(log1);
				borrowMapper.updateByPrimaryKeySelective(borrow);

				// 投资人复审发邮件
				String content = "你的冻结金额于" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "成功扣除，[" + borrow.getName() + "]标成功满额。";
				List<UserTenderVO> tendersGroupUserId = borrowTenderMapper.selectTendersGroupByUserId(borrow.getId());
				for (UserTenderVO vo : tendersGroupUserId) {
					if (EmptyUtil.isNotEmpty(vo.getEmail())) {
						SendEmailMessage sendEmail = new SendEmailMessage();
						sendEmail.setEmail(vo.getEmail());
						sendEmail.setTitle("微积金满标审核结果");
						sendEmail.setContent(content);
						// this.jmsSender.sendAsynchronousMessage(sendEmail);
						this.jmsSenderUtil.asynSendSystemJms(sendEmail);
					}
					if (vo.getTenderIp().equals("borrowAuto")) {// 自动投标
						/**
						 * 发送短信
						 */
						this.jmsSenderUtil.sendSms(vo.getPhone(), ISendConfigUtil.SMS_VERIFYBORROW_AUTOINVEST, vo.getUsername(), borrow.getName(), vo.getTenderAccount().toString());
					} else {
						/**
						 * 发送短信
						 */
						this.jmsSenderUtil.sendSms(vo.getPhone(), ISendConfigUtil.SMS_VERIFYBORROW, vo.getUsername(), borrow.getName());
					}
				}
				// 借款人发邮件
				String toBorrower = "您的[" + borrow.getName() + "]标已成功满额复审通过，您已成功借款" + borrow.getAccount() + "元。";
				User user = userMapper.selectByPrimaryKey(borrow.getUserId());
				SendEmailMessage sendEmail = new SendEmailMessage();
				sendEmail.setEmail(user.getEmail());
				// sendEmail.setEmail("1129209963@qq.com");
				sendEmail.setTitle("微积金满标审核结果");
				sendEmail.setContent(toBorrower);
				// this.jmsSender.sendAsynchronousMessage(sendEmail);
				this.jmsSenderUtil.asynSendSystemJms(sendEmail);
				result = "成功";
			} else {
				result = "数据异常，审核失败";
			}
		}
		return result;
	}

	/**
	 * 复审成功 后置业务
	 * 
	 * @author lilei
	 * @param vo
	 *            2015年1月21日
	 */
	public synchronized void updateFinalVerifyBorrowAfter(FinalVerifyVO vo) {
		Borrow borrow = this.borrowMapper.selectById(vo.getBorrowId());

	}

	/**
	 * @Description:体验标--还款
	 * @param borrow
	 * @param ip
	 * @return
	 * @author liuhuan
	 */
	@Override
	public synchronized String updateRepayFeelBorrow(String repaymentId, Integer userId, String ip) {
		String result = "";
		BorrowRepayment borrowRepayment = borrowRepaymentMapper.selectByPrimaryKey(Integer.parseInt(repaymentId));

		AccountFeel borrowerAccountFeel = accountFeelMapper.selectByUserId(userId); // 借款人账户
		if (borrowRepayment.getStatus() == 40) {
			result = "该标已还款.";
			// } else if (repayments.get(0).getId() !=
			// Integer.parseInt(repaymentId)) {
			// result = "你上期的借款还没还，请先还上期的";
		} else if (borrowerAccountFeel.getUseMoney().compareTo(new BigDecimal(borrowRepayment.getRepaymentAccount())) == -1) {
			result = "余额不足，请充值后还款.";
		} else {
			Borrow borrow = borrowMapper.selectById(borrowRepayment.getBorrowId());
			InvestorsFee investorsFee = investorsFeeMapper.selectByBorrowId(borrowRepayment.getBorrowId());
			List<BorrowTender> borrowTenders = borrowTenderMapper.selectListByBorrowId(borrow.getId());
			if (borrow == null || investorsFee == null || borrowTenders.isEmpty() || !borrow.getBiaoType().equals("feel")) {
				result = "数据异常，还款失败.";
				return result;
			}
			int order = borrowRepayment.getOrder(); // 还款期数
			BigDecimal repayAccount = new BigDecimal(borrowRepayment.getRepaymentAccount()); // 本次还款实际金额(含利息)
			borrow.setRepaymentYesaccount(borrow.getRepaymentYesaccount().add(new BigDecimal(borrowRepayment.getRepaymentAccount())));
			borrow.setRepaymentYesinterest(borrow.getRepaymentYesinterest().add(new BigDecimal(borrowRepayment.getInterest())));
			borrowMapper.updateByPrimaryKeySelective(borrow); // 更新borrow

			// List<BorrowRepayment> borrowRepayments =
			// borrowRepaymentMapper.selectByBorrowIdStatus(borrow.getId(),30);
			// //根据order 升序排列
			// 借出借款人冻结款---体验标不冻结
			/*
			 * if(borrowRepayments.size() == 1){ // 最后一期退还冻结款 BigDecimal
			 * forstAccount = borrow.getForstAccount(); // 冻结款
			 * borrowerAccountFeel
			 * .setUseMoney(borrowerAccountFeel.getUseMoney().
			 * add(forstAccount));
			 * borrowerAccountFeel.setNoUseMoney(borrowerAccountFeel
			 * .getNoUseMoney().subtract(forstAccount));
			 * 
			 * AccountFeelLog accountFeelLog = new AccountFeelLog();
			 * accountFeelLog.setUserId(borrow.getUserId());
			 * accountFeelLog.setType("margin");
			 * accountFeelLog.setMoney(forstAccount);
			 * accountFeelLog.setUseMoney(borrowerAccountFeel.getUseMoney());
			 * accountFeelLog
			 * .setNoUseMoney(borrowerAccountFeel.getNoUseMoney());
			 * accountFeelLog
			 * .setCollection(borrowerAccountFeel.getCollection());
			 * accountFeelLog.setTotal(borrowerAccountFeel.getTotal());
			 * accountFeelLog.setToUser(0);
			 * accountFeelLog.setRemark("解冻借款标的[<a href='/borrow/borrowDetail/"
			 * +borrow.getId(
			 * )+"' target=_blank>"+borrow.getName()+"</a>]的保证金");
			 * accountFeelLog.setBorrowId(borrow.getId());
			 * accountFeelLog.setAddtime(Integer.parseInt(DateUtil.getTime()));
			 * accountFeelLog.setAddip(ip);
			 * accountFeelLogMapper.insert(accountFeelLog); // 入账日志
			 * accountFeelMapper
			 * .updateByPrimaryKey(borrowerAccountFeel);//更新借款人account }
			 */

			// 借款人还款
			borrowerAccountFeel.setUseMoney(borrowerAccountFeel.getUseMoney().subtract(repayAccount));
			borrowerAccountFeel.setTotal(borrowerAccountFeel.getTotal().subtract(repayAccount));
			AccountFeelLog accountFeelLog = new AccountFeelLog();
			accountFeelLog.setUserId(borrow.getUserId());
			accountFeelLog.setType("repayment");
			accountFeelLog.setMoney(repayAccount);
			accountFeelLog.setUseMoney(borrowerAccountFeel.getUseMoney());
			accountFeelLog.setNoUseMoney(borrowerAccountFeel.getNoUseMoney());
			accountFeelLog.setCollection(borrowerAccountFeel.getCollection());
			accountFeelLog.setTotal(borrowerAccountFeel.getTotal());
			accountFeelLog.setToUser(0);
			accountFeelLog.setRemark("对[<a href='/borrow/borrowDetail/" + borrow.getId() + "' target=_blank><font color=red>" + borrow.getName() + "</font></a>]借款标的还款");
			accountFeelLog.setBorrowId(borrow.getId());
			accountFeelLog.setAddtime(Integer.parseInt(DateUtil.getTime()));
			accountFeelLog.setAddip(ip);
			accountFeelLogMapper.insert(accountFeelLog); // 入账日志
			accountFeelMapper.updateByPrimaryKey(borrowerAccountFeel);// 更新借款人account

			borrowRepayment.setStatus(40);
			borrowRepayment.setRepaymentYestime(Integer.parseInt(DateUtil.getTime()));
			borrowRepayment.setRepaymentYesaccount(repayAccount.toString());
			borrowRepaymentMapper.updateByPrimaryKey(borrowRepayment); // 更新repayment

			for (BorrowTender tender : borrowTenders) {
				AccountFeel tenderAccountFeel = accountFeelMapper.selectByUserId(tender.getUserId());// 体验账户
				Account tenderAccount = accountMapper.selectByUserId(tender.getUserId()); // 真是资金账户

				BorrowCollection collection = borrowCollectionMapper.selectByTenderIdOrder(tender.getId(), order); // 所有待收的当期记录
				if (collection.getStatus() != 30) {
					throw new BusinessException("8005011", "还款失败，标类型错误");
				}
				collection.setStatus(40);
				collection.setRepayYestime(DateUtil.getTime());
				collection.setRepayYesaccount(collection.getRepayAccount());
				borrowCollectionMapper.updateByPrimaryKey(collection); // 更新collection

				tender.setStatus(10);
				tender.setRepaymentYesaccount(new BigDecimal(tender.getRepaymentYesaccount()).add(new BigDecimal(collection.getRepayAccount())).toString());
				tender.setRepaymentYesinterest(new BigDecimal(tender.getRepaymentYesinterest()).add(new BigDecimal(collection.getInterest())).toString());
				tender.setWaitAccount(new BigDecimal(tender.getWaitAccount()).subtract(new BigDecimal(collection.getRepayAccount())).toString());
				tender.setWaitInterest(new BigDecimal(tender.getWaitInterest()).subtract(new BigDecimal(collection.getInterest())).toString());
				tender.setWaitGuaranteeFees(new BigDecimal(tender.getWaitGuaranteeFees()).subtract(new BigDecimal(collection.getGuaranteeFees())).toString());
				tender.setWaitServiceFees(new BigDecimal(tender.getWaitServiceFees()).subtract(new BigDecimal(collection.getServiceFees())).toString());

				borrowTenderMapper.updateByPrimaryKey(tender); // 更新tender

				BigDecimal collectionInterest = new BigDecimal(collection.getInterest());// 还款到账利息
				BigDecimal collectionAccount = new BigDecimal(collection.getRepayAccount());// 还款总额含利息

				// 扣除体验金账户待还利息
				tenderAccountFeel.setCollection(tenderAccountFeel.getCollection().subtract(collectionAccount));
				tenderAccountFeel.setTotal(tenderAccountFeel.getTotal().subtract(collectionAccount));
				tenderAccountFeel.setOther(new BigDecimal(tenderAccountFeel.getOther()).subtract(collectionInterest).toString());
				accountFeelMapper.updateByPrimaryKey(tenderAccountFeel);

				// 给投资人入账利息
				tenderAccount.setUseMoney(tenderAccount.getUseMoney().add(collectionInterest));
				tenderAccount.setTotal(tenderAccount.getTotal().add(collectionInterest));
				AccountLog accountlog = new AccountLog();
				accountlog.setUserId(tender.getUserId());
				accountlog.setType("collection");
				accountlog.setMoney(collectionInterest);
				accountlog.setUseMoney(tenderAccount.getUseMoney());
				accountlog.setNoUseMoney(tenderAccount.getNoUseMoney());
				accountlog.setCollection(tenderAccount.getCollection());
				accountlog.setTotal(tenderAccount.getTotal());
				accountlog.setToUser(0);
				accountlog.setRemark("体验金[<a href='/borrow/borrowDetail/" + borrow.getId() + "' target=_blank><font color=red>" + borrow.getName() + "</font></a>]入账");
				accountlog.setBorrowId(borrow.getId());
				accountlog.setAddtime(Integer.parseInt(DateUtil.getTime()));
				accountlog.setAddip(ip);
				accountMapper.updateByPrimaryKey(tenderAccount);
				accountLogMapper.insert(accountlog);

				Message message = new Message();
				message.setSentUser(0);
				message.setReceiveUser(tender.getUserId());
				message.setName("体验标利息入账");
				message.setContent("体验金[<a href='/borrow/borrowDetail/" + borrow.getId() + "' target=_blank>" + borrow.getName() + "</a>]入账");
				message.setStatus(0);
				message.setType("system");
				message.setSented(null);
				message.setDeltype(0);
				message.setAddtime(DateUtil.getTime());
				message.setAddip(ip);
				messageMapper.insert(message);
			}
			result = "还款成功";
		}
		return result;
	}

	/**
	 * @Description: 天标--还款
	 * @author liuhuan
	 */
	@Override
	public synchronized String updateRepayDayBorrow(String repaymentId, String ip) {
		String msg = "";
		BorrowRepayment borrowRepayment = borrowRepaymentMapper.selectByPrimaryKey(Integer.parseInt(repaymentId));
		List<BorrowRepayment> repayments = borrowRepaymentMapper.selectByBorrowIdStatus(borrowRepayment.getBorrowId(), 0);// 该标所有--未还记录
		List<BorrowRepayment> yesRepayments = borrowRepaymentMapper.selectByBorrowIdStatus(borrowRepayment.getBorrowId(), 1);// 该标所有--已还记录
		Borrow borrow = borrowMapper.selectById(borrowRepayment.getBorrowId());
		Account borrowAccount = accountMapper.selectByUserId(borrow.getUserId());
		if (borrow.getIsday() != 1) {
			msg = "不是天标，不能还款";
		} else if (borrowRepayment.getStatus() == 1) {
			msg = "该标已还款.";
		} else if (repayments.get(0).getId() != Integer.parseInt(repaymentId)) {
			msg = "你上期的借款还没还，请先还上期的";
		} else if (borrowAccount.getUseMoney().compareTo(new BigDecimal(borrowRepayment.getRepaymentAccount())) == -1) {
			msg = "余额不足，请充值后还款.";
		} else {
			InvestorsFee investorsFee = investorsFeeMapper.selectByBorrowId(borrowRepayment.getBorrowId());
			List<BorrowTender> borrowTenders = borrowTenderMapper.selectListByBorrowId(borrowRepayment.getBorrowId());

			BigDecimal collectionSum = new BigDecimal("0.00");
			BigDecimal tenderSum = new BigDecimal("0.00");
			for (BorrowTender tender : borrowTenders) {
				if (EmptyUtil.isNotEmpty(tender.getEqualInterest())) {
					tenderSum = tenderSum.add(ConverterUtil.StringToBigDecimal(tender.getEqualInterest()));
				} else {
					tenderSum = tenderSum.add(ConverterUtil.StringToBigDecimal(tender.getInterest()));
				}
				List<BorrowCollection> collections = borrowCollectionMapper.selectByTenderId(tender.getId());
				// 待收和=待收本金+服务费+担保费
				for (BorrowCollection bc : collections) {
					collectionSum = collectionSum.add(ConverterUtil.StringToBigDecimal(bc.getCapital())).add(ConverterUtil.StringToBigDecimal(bc.getServiceFees())).add(ConverterUtil.StringToBigDecimal(bc.getGuaranteeFees()));
				}
			}
			BigDecimal repaySum = new BigDecimal("0.00");// 该标总待还额
			for (BorrowRepayment br : repayments) {// 未还待还总额
				repaySum = repaySum.add(ConverterUtil.StringToBigDecimal(br.getRepaymentAccount()));
			}
			for (BorrowRepayment br : yesRepayments) {// 已还待还总额
				repaySum = repaySum.add(ConverterUtil.StringToBigDecimal(br.getRepaymentAccount()));
			}

			// 待还待收误差比较 -- 允许误差1
			if (repaySum.subtract(collectionSum.add(tenderSum)).abs().compareTo(new BigDecimal("1")) == 1) {
				throw new BusinessException("8005011", "还款失败，数据异常");
			}
			if (borrow == null || investorsFee == null || borrowTenders.isEmpty() || borrow.getIsday() != 1) {
				msg = "数据异常，还款失败.";
			} else {
				BigDecimal repaymentAccount = new BigDecimal(borrowRepayment.getRepaymentAccount()); // 借款人待还金额
				BigDecimal repaymentInterest = new BigDecimal(borrowRepayment.getInterest()); // 借款人待还利息
				// 借款人扣款
				borrowAccount.setUseMoney(borrowAccount.getUseMoney().subtract(repaymentAccount));
				borrowAccount.setTotal(borrowAccount.getTotal().subtract(repaymentAccount));
				AccountLog accountlog = new AccountLog();
				accountlog.setUserId(borrow.getUserId());
				accountlog.setType("repayment");
				accountlog.setMoney(repaymentAccount);
				accountlog.setUseMoney(borrowAccount.getUseMoney());
				accountlog.setNoUseMoney(borrowAccount.getNoUseMoney());
				accountlog.setCollection(borrowAccount.getCollection());
				accountlog.setTotal(borrowAccount.getTotal());
				accountlog.setToUser(0);
				accountlog.setRemark("对" + borrow.getName() + "借款标的还款");
				accountlog.setBorrowId(borrow.getId());
				accountlog.setAddtime(Integer.parseInt(DateUtil.getTime()));
				accountlog.setAddip(ip);
				accountMapper.updateByPrimaryKey(borrowAccount);
				accountLogMapper.insert(accountlog);

				// 更新borrow
				borrow.setRepaymentYesaccount(borrow.getRepaymentYesaccount().add(repaymentAccount));
				borrow.setRepaymentYesinterest(borrow.getRepaymentYesinterest().add(repaymentInterest));
				borrowMapper.updateByPrimaryKeySelective(borrow);

				// 更新repayment
				borrowRepayment.setStatus(1);// 已还
				borrowRepayment.setRepaymentYesaccount(borrowRepayment.getRepaymentAccount());
				borrowRepayment.setRepaymentYestime(Integer.parseInt(DateUtil.getTime()));
				borrowRepaymentMapper.updateByPrimaryKeySelective(borrowRepayment);

				List<BorrowTender> tender_lists = borrowTenderMapper.selectListByBorrowId(borrowRepayment.getBorrowId());
				// 更新tender
				for (BorrowTender tender : tender_lists) {
					Account tenderAccount = accountMapper.selectByUserId(tender.getUserId()); // 真实资金账户
					BorrowCollection collection = borrowCollectionMapper.selectByTenderIdOrder(tender.getId(), borrowRepayment.getOrder());
					BigDecimal collectionAccount = new BigDecimal(collection.getRepayAccount());// 还款总额含利息
					collection.setStatus(1);// 已还
					collection.setRepayYesaccount(collection.getRepayAccount());
					collection.setRepayYestime(DateUtil.getTime());
					borrowCollectionMapper.updateByPrimaryKeySelective(collection); // 更新collection

					tender.setStatus(1); // 已还
					tender.setRepaymentYesaccount(new BigDecimal(tender.getRepaymentYesaccount()).add(new BigDecimal(collection.getRepayAccount())).toString());
					tender.setRepaymentYesinterest(new BigDecimal(tender.getRepaymentYesinterest()).add(new BigDecimal(collection.getInterest())).toString());
					tender.setWaitAccount(new BigDecimal(tender.getWaitAccount()).subtract(new BigDecimal(collection.getRepayAccount())).toString());
					tender.setWaitInterest(new BigDecimal(tender.getWaitInterest()).subtract(new BigDecimal(collection.getInterest())).toString());
					tender.setWaitGuaranteeFees(new BigDecimal(tender.getWaitGuaranteeFees()).subtract(new BigDecimal(collection.getGuaranteeFees())).toString());
					tender.setWaitServiceFees(new BigDecimal(tender.getWaitServiceFees()).subtract(new BigDecimal(collection.getServiceFees())).toString());
					// 修改待还返利
					tender.setWaitReturnFees(new BigDecimal(tender.getWaitReturnFees()).subtract(new BigDecimal(collection.getReturnFees())).toString());

					borrowTenderMapper.updateByPrimaryKeySelective(tender);

					// 投资人回款到账
					tenderAccount.setUseMoney(tenderAccount.getUseMoney().add(collectionAccount));
					tenderAccount.setCollection(tenderAccount.getCollection().subtract(collectionAccount));
					AccountLog accountlog1 = new AccountLog();
					accountlog1.setUserId(tender.getUserId());
					accountlog1.setType("collection");
					accountlog1.setMoney(collectionAccount);
					accountlog1.setUseMoney(tenderAccount.getUseMoney());
					accountlog1.setNoUseMoney(tenderAccount.getNoUseMoney());
					accountlog1.setCollection(tenderAccount.getCollection());
					accountlog1.setTotal(tenderAccount.getTotal());
					accountlog1.setToUser(0);
					accountlog1.setRemark(borrow.getName() + "入账");
					accountlog1.setBorrowId(borrow.getId());
					accountlog1.setAddtime(Integer.parseInt(DateUtil.getTime()));
					accountlog1.setAddip(ip);
					accountMapper.updateByPrimaryKey(tenderAccount);
					accountLogMapper.insert(accountlog1);

					// 投资人站内信
					Message message = new Message();
					message.setSentUser(0);
					message.setReceiveUser(tender.getUserId());
					message.setName("投标利息入账");
					message.setContent(borrow.getName() + "入账");
					message.setStatus(0);
					message.setType("system");
					message.setSented(null);
					message.setDeltype(0);
					message.setAddtime(DateUtil.getTime());
					message.setAddip(ip);
					messageMapper.insert(message);
				}
				msg = "还款成功";
			}
		}
		return msg;
	}

	@Override
	public synchronized String updateRepayBorrow(String repaymentId, String ip) {
		String msg = "";
		BorrowRepayment borrowRepayment = borrowRepaymentMapper.selectByPrimaryKey(Integer.parseInt(repaymentId));
		List<BorrowRepayment> repayments = borrowRepaymentMapper.selectByBorrowIdStatus(borrowRepayment.getBorrowId(), 0);// 该标所有--未还记录
		List<BorrowRepayment> yesRepayments = borrowRepaymentMapper.selectByBorrowIdStatus(borrowRepayment.getBorrowId(), 1);// 该标所有--已还记录
		Account borrowAccount = accountMapper.selectByUserId(UserSession.getLoginUserId());
		Borrow borrow = borrowMapper.selectById(borrowRepayment.getBorrowId());
		if (borrow.getIsday() == 1) {
			this.updateRepayDayBorrow(repaymentId, ip);
		}
		if (borrowRepayment.getStatus() == 1) {
			msg = "该标已还款.";
		} else if (repayments.get(0).getId() != Integer.parseInt(repaymentId)) {
			msg = "你上期的借款还没还，请先还上期的";
		} else if (borrowAccount.getUseMoney().compareTo(new BigDecimal(borrowRepayment.getRepaymentAccount())) == -1) {
			msg = "余额不足，请充值后还款.";
		} else {
			User user = null;
			InvestorsFee investorsFee = investorsFeeMapper.selectByBorrowId(borrowRepayment.getBorrowId());
			List<BorrowTender> borrowTenders = borrowTenderMapper.selectListByBorrowId(borrowRepayment.getBorrowId());

			BigDecimal collectionSum = new BigDecimal("0.00");
			BigDecimal tenderSum = new BigDecimal("0.00");
			for (BorrowTender tender : borrowTenders) {
				if (EmptyUtil.isNotEmpty(tender.getEqualInterest())) {
					tenderSum = tenderSum.add(ConverterUtil.StringToBigDecimal(tender.getEqualInterest()));
				} else {
					tenderSum = tenderSum.add(ConverterUtil.StringToBigDecimal(tender.getInterest()));
				}
				List<BorrowCollection> collections = borrowCollectionMapper.selectByTenderId(tender.getId());
				// 待收和=待收本金+服务费+担保费
				for (BorrowCollection bc : collections) {
					collectionSum = collectionSum.add(ConverterUtil.StringToBigDecimal(bc.getCapital())).add(ConverterUtil.StringToBigDecimal(bc.getServiceFees())).add(ConverterUtil.StringToBigDecimal(bc.getGuaranteeFees()));
				}
			}
			BigDecimal repaySum = new BigDecimal("0.00");// 该标总待还额
			for (BorrowRepayment br : repayments) {// 未还待还总额
				repaySum = repaySum.add(ConverterUtil.StringToBigDecimal(br.getRepaymentAccount()));
			}
			for (BorrowRepayment br : yesRepayments) {// 已还待还总额
				repaySum = repaySum.add(ConverterUtil.StringToBigDecimal(br.getRepaymentAccount()));
			}

			// 待还待收误差比较 -- 允许误差1
			if (repaySum.subtract(collectionSum.add(tenderSum)).abs().compareTo(new BigDecimal("1")) == 1) {
				throw new BusinessException("8005011", "还款失败，数据异常");
			}

			if (borrow == null || investorsFee == null || borrowTenders.isEmpty() || borrow.getBiaoType().equals("feel")) {
				msg = "数据异常，还款失败.";
			} else {
				BigDecimal repaymentAccount = new BigDecimal(borrowRepayment.getRepaymentAccount()); // 借款人待还金额
				BigDecimal repaymentInterest = new BigDecimal(borrowRepayment.getInterest()); // 借款人待还利息

				// 最后一期保证金退还
				if (borrowRepayment.getOrder() + 1 == (int) borrow.getTimeLimit()) {
					BigDecimal forstAccount = borrow.getForstAccount(); // 冻结金额
					if (forstAccount.doubleValue() != 0.00) {
						borrowAccount.setUseMoney(borrowAccount.getUseMoney().add(forstAccount));
						borrowAccount.setNoUseMoney(borrowAccount.getNoUseMoney().subtract(forstAccount));
						AccountLog log = new AccountLog();
						log.setUserId(borrow.getUserId());
						log.setType("margin");
						log.setMoney(forstAccount);
						log.setUseMoney(borrowAccount.getUseMoney());
						log.setNoUseMoney(borrowAccount.getNoUseMoney());
						log.setCollection(borrowAccount.getCollection());
						log.setTotal(borrowAccount.getTotal());
						log.setToUser(0);
						log.setRemark("解冻借款标的[<a href='/borrow/borrowDetail/" + borrow.getId() + "' target=_blank>" + borrow.getName() + "</a>]的保证金");
						log.setBorrowId(borrow.getId());
						log.setAddtime(Integer.parseInt(DateUtil.getTime()));
						log.setAddip(ip);
						accountLogMapper.insert(log);
					}
				}

				// 借款人积分变动

				// 借款人扣款
				borrowAccount.setUseMoney(borrowAccount.getUseMoney().subtract(repaymentAccount));
				borrowAccount.setTotal(borrowAccount.getTotal().subtract(repaymentAccount));
				AccountLog accountlog = new AccountLog();
				accountlog.setUserId(borrow.getUserId());
				accountlog.setType("repayment");
				accountlog.setMoney(repaymentAccount);
				accountlog.setUseMoney(borrowAccount.getUseMoney());
				accountlog.setNoUseMoney(borrowAccount.getNoUseMoney());
				accountlog.setCollection(borrowAccount.getCollection());
				accountlog.setTotal(borrowAccount.getTotal());
				accountlog.setToUser(0);
				accountlog.setRemark("对[<a href='/borrow/borrowDetail/" + borrow.getId() + "' target=_blank><font color=red>" + borrow.getName() + "</font></a>]借款标的还款");
				accountlog.setBorrowId(borrow.getId());
				accountlog.setAddtime(Integer.parseInt(DateUtil.getTime()));
				accountlog.setAddip(ip);
				accountMapper.updateByPrimaryKey(borrowAccount);
				accountLogMapper.insert(accountlog);

				// 更新borrow
				borrow.setRepaymentYesaccount(borrow.getRepaymentYesaccount().add(repaymentAccount));
				borrow.setRepaymentYesinterest(borrow.getRepaymentYesinterest().add(repaymentInterest));
				borrowMapper.updateByPrimaryKeySelective(borrow);

				// 更新repayment
				borrowRepayment.setStatus(1);// 已还
				borrowRepayment.setRepaymentYesaccount(borrowRepayment.getRepaymentAccount());
				borrowRepayment.setRepaymentYestime(Integer.parseInt(DateUtil.getTime()));
				borrowRepaymentMapper.updateByPrimaryKeySelective(borrowRepayment);

				List<BorrowTender> tender_lists = borrowTenderMapper.selectListByBorrowId(borrowRepayment.getBorrowId());
				// 更新tender
				for (BorrowTender tender : tender_lists) {
					user = userMapper.selectByPrimaryKey(tender.getUserId());
					Account tenderAccount = accountMapper.selectByUserId(tender.getUserId()); // 真实资金账户
					BorrowCollection collection = borrowCollectionMapper.selectByTenderIdOrder(tender.getId(), borrowRepayment.getOrder());
					BigDecimal collectionAccount = new BigDecimal(collection.getRepayAccount());// 还款总额含利息
					collection.setStatus(1);// 已还
					collection.setRepayYesaccount(collection.getRepayAccount());
					collection.setRepayYestime(DateUtil.getTime());
					// 逾期利息 如何分配投资人
					// collection.setLateDays(borrowRepayment.getLateDays());
					// collection.setLateInterest(ConverterUtil.multiply(ConverterUtil.divide(collection.getCapital(),
					// borrowRepayment.getCapital()).toString(),
					// borrowRepayment.getLateInterest()).toString());
					borrowCollectionMapper.updateByPrimaryKeySelective(collection); // 更新collection

					tender.setStatus(1); // 已还
					tender.setRepaymentYesaccount(new BigDecimal(tender.getRepaymentYesaccount()).add(new BigDecimal(collection.getRepayAccount())).toString());
					tender.setRepaymentYesinterest(new BigDecimal(tender.getRepaymentYesinterest()).add(new BigDecimal(collection.getInterest())).toString());
					tender.setWaitAccount(new BigDecimal(tender.getWaitAccount()).subtract(new BigDecimal(collection.getRepayAccount())).toString());
					tender.setWaitInterest(new BigDecimal(tender.getWaitInterest()).subtract(new BigDecimal(collection.getInterest())).toString());
					tender.setWaitGuaranteeFees(new BigDecimal(tender.getWaitGuaranteeFees()).subtract(new BigDecimal(collection.getGuaranteeFees())).toString());
					tender.setWaitServiceFees(new BigDecimal(tender.getWaitServiceFees()).subtract(new BigDecimal(collection.getServiceFees())).toString());
					// 修改待还返利
					tender.setWaitReturnFees(new BigDecimal(tender.getWaitReturnFees()).subtract(new BigDecimal(collection.getReturnFees())).toString());

					borrowTenderMapper.updateByPrimaryKeySelective(tender);

					// 投资人回款到账
					tenderAccount.setUseMoney(tenderAccount.getUseMoney().add(collectionAccount));
					tenderAccount.setCollection(tenderAccount.getCollection().subtract(collectionAccount));
					// 利息再次增加???
					// tenderAccount.setTotal(tenderAccount.getTotal().add(collectionInterest));
					AccountLog accountlog1 = new AccountLog();
					accountlog1.setUserId(tender.getUserId());
					accountlog1.setType("collection");
					accountlog1.setMoney(collectionAccount);
					accountlog1.setUseMoney(tenderAccount.getUseMoney());
					accountlog1.setNoUseMoney(tenderAccount.getNoUseMoney());
					accountlog1.setCollection(tenderAccount.getCollection());
					accountlog1.setTotal(tenderAccount.getTotal());
					accountlog1.setToUser(0);
					accountlog1.setRemark("[<a href='/borrow/borrowDetail/" + borrow.getId() + "' target=_blank><font color=red>" + borrow.getName() + "</font></a>]入账");
					accountlog1.setBorrowId(borrow.getId());
					accountlog1.setAddtime(Integer.parseInt(DateUtil.getTime()));
					accountlog1.setAddip(ip);
					accountMapper.updateByPrimaryKey(tenderAccount);
					accountLogMapper.insert(accountlog1);

					// 投资人站内信
					Message message = new Message();
					message.setSentUser(0);
					message.setReceiveUser(tender.getUserId());
					message.setName("投标利息入账");
					message.setContent("[<a href='/borrow/borrowDetail/" + borrow.getId() + "' target=_blank>" + borrow.getName() + "</a>]入账");
					message.setStatus(0);
					message.setType("system");
					message.setSented(null);
					message.setDeltype(0);
					message.setAddtime(DateUtil.getTime());
					message.setAddip(ip);
					messageMapper.insert(message);
					// 借款人发短信
					String toBorrower = "您已经成功对：" + borrow.getName() + "标的还款" + repaymentAccount + "元。";
				}
				msg = "还款成功";
			}
		}
		return msg;
	}

	@Override
	public void updateRepayBorrowAfter(Integer repaymentId) {
		BorrowRepayment borrowRepayment = borrowRepaymentMapper.selectByPrimaryKey(repaymentId);
		Borrow borrow = borrowMapper.selectById(borrowRepayment.getBorrowId());
		List<UserTenderVO> tendersGroupUserId = borrowTenderMapper.selectTendersGroupByUserId(borrow.getId());
		for (UserTenderVO vo : tendersGroupUserId) {
			if (EmptyUtil.isNotEmpty(vo.getPhone())) {
				if (EmptyUtil.isNotEmpty(borrow.getTimeLimit()) && borrow.getTimeLimit() > 1) {
					// 发送还款到账提醒短信
					this.jmsSenderUtil.sendSms(vo.getPhone(), ISendConfigUtil.SMS_REPAYMENT_MONTH, vo.getUsername(), borrow.getName());
				} else {
					// 发送还款到账提醒短信
					this.jmsSenderUtil.sendSms(vo.getPhone(), ISendConfigUtil.SMS_REPAYMENT, vo.getUsername(), borrow.getName());
					if (EmptyUtil.isNotEmpty(vo.getEmail())) {
						this.jmsSenderUtil.sendEmail(vo.getEmail(), ISendConfigUtil.EMAIL_TEMPLET_REPAYMENT, vo.getUsername(), vo.getUsername(), borrow.getName());
					}
				}
			}
		}
		BigDecimal repaymentAccount = new BigDecimal(borrowRepayment.getRepaymentAccount()); // 借款人待还金额
		// 借款人发短信
		String toBorrower = "您已经成功对：" + borrow.getName() + "标的还款" + repaymentAccount + "元。";
		User user = userMapper.selectByPrimaryKey(borrow.getUserId());
		SendVerifyCodeMessage sendCode = new SendVerifyCodeMessage();
		sendCode.setMobile(user.getPhone());
		sendCode.setContent(toBorrower);
		this.jmsSenderUtil.asynSendSystemJms(sendCode);
	}

	@Override
	public void updateRepayDayBorrowAfter(Integer repaymentId) {
		BorrowRepayment borrowRepayment = borrowRepaymentMapper.selectByPrimaryKey(repaymentId);
		Borrow borrow = borrowMapper.selectById(borrowRepayment.getBorrowId());
		List<UserTenderVO> tendersGroupUserId = borrowTenderMapper.selectTendersGroupByUserId(borrow.getId());
		for (UserTenderVO vo : tendersGroupUserId) {
			if (EmptyUtil.isNotEmpty(vo.getPhone())) {
				if (EmptyUtil.isNotEmpty(borrow.getTimeLimit()) && borrow.getTimeLimit() > 1) {
					// 发送还款到账提醒短信
					this.jmsSenderUtil.sendSms(vo.getPhone(), ISendConfigUtil.SMS_REPAYMENT_MONTH, vo.getUsername(), borrow.getName());
				} else {
					// 发送还款到账提醒短信
					this.jmsSenderUtil.sendSms(vo.getPhone(), ISendConfigUtil.SMS_REPAYMENT, vo.getUsername(), borrow.getName());
					if (EmptyUtil.isNotEmpty(vo.getEmail())) {
						this.jmsSenderUtil.sendEmail(vo.getEmail(), ISendConfigUtil.EMAIL_TEMPLET_REPAYMENT, vo.getUsername(), vo.getUsername(), borrow.getName());
					}
				}
			}
		}
		BigDecimal repaymentAccount = new BigDecimal(borrowRepayment.getRepaymentAccount()); // 借款人待还金额
		// 借款人发短信
		String toBorrower = "您已经成功对：" + borrow.getName() + "标的还款" + repaymentAccount + "元。";
		User user = userMapper.selectByPrimaryKey(borrow.getUserId());
		SendVerifyCodeMessage sendCode = new SendVerifyCodeMessage();
		sendCode.setMobile(user.getPhone());
		// sendCode.setMobile("13774326783");
		sendCode.setContent(toBorrower);
		// this.jmsSender.sendAsynchronousMessage(sendCode);
		this.jmsSenderUtil.asynSendSystemJms(sendCode);
	}

	/**
	 * 后台撤标
	 * 
	 * @param borrow
	 * @param admin
	 * @param ip
	 * @return
	 * @author liuhuan
	 */
	@Override
	public synchronized String updateFailBorrow(Borrow borrow, Integer status, String ip) {
		String result = "";
		borrow.setStatus(status.byteValue());
		borrowMapper.updateByPrimaryKeySelective(borrow);
		List<BorrowTender> tenders = borrowTenderMapper.selectListByBorrowId(borrow.getId());
		for (BorrowTender tender : tenders) {
			tender.setStatus(2); // 撤标
			BigDecimal tender_money = new BigDecimal(tender.getAccount()); // 返还投资金额
			Account tenderAccount = accountMapper.selectByUserId(tender.getUserId());
			tenderAccount.setNoUseMoney(tenderAccount.getNoUseMoney().subtract(tender_money));
			tenderAccount.setUseMoney(tenderAccount.getUseMoney().add(tender_money));
			AccountLog accountLog = new AccountLog();
			accountLog.setUserId(tender.getUserId());
			accountLog.setType("invest_false");
			accountLog.setMoney(tender_money);
			accountLog.setUseMoney(tenderAccount.getUseMoney());
			accountLog.setNoUseMoney(tenderAccount.getNoUseMoney());
			accountLog.setCollection(tenderAccount.getCollection());
			accountLog.setTotal(tenderAccount.getTotal());
			accountLog.setToUser(0);
			accountLog.setRemark("借款标[<a href='/borrow/borrowDetail/" + borrow.getId() + "' target=_blank><font color=red>" + borrow.getName() + "</font></a>]失败投资金额入账");
			accountLog.setBorrowId(borrow.getId());
			accountLog.setAddtime(Integer.parseInt(DateUtil.getTime()));
			accountLog.setAddip(ip);

			accountMapper.updateByPrimaryKey(tenderAccount);
			accountLogMapper.insert(accountLog);
			borrowTenderMapper.updateByPrimaryKeySelective(tender);
		}
		result = "成功";
		return result;
	}

	/**
	 * 流标(每晚23:59开始运行，更新所有待流标的标&退款)
	 * 
	 * @return
	 * @author liuhuan
	 */
	@Override
	public synchronized void updateFlowBorrow() {
		Integer status = 1;// 正在招标的标（非体验标）
		String ip = "system";
		// 查询所有未满的标，并且有效期已满
		List<Borrow> borrows = borrowMapper.selectByStatus(status);
		if (EmptyUtil.isNotEmpty(borrows)) {
			System.out.println("待流标数量:" + borrows.size());
			for (Borrow b : borrows) {
				b.setStatus((byte) 7);
				List<BorrowTender> tenders = borrowTenderMapper.selectListByBorrowId(b.getId());
				for (BorrowTender tender : tenders) {
					tender.setStatus(3); // 流标
					BigDecimal tender_money = new BigDecimal(tender.getAccount()); // 返还投资金额
					Account tenderAccount = accountMapper.selectByUserId(tender.getUserId());
					tenderAccount.setNoUseMoney(tenderAccount.getNoUseMoney().subtract(tender_money));
					tenderAccount.setUseMoney(tenderAccount.getUseMoney().add(tender_money));
					AccountLog accountLog = new AccountLog();
					accountLog.setUserId(tender.getUserId());
					accountLog.setType("invest_flow");
					accountLog.setMoney(tender_money);
					accountLog.setUseMoney(tenderAccount.getUseMoney());
					accountLog.setNoUseMoney(tenderAccount.getNoUseMoney());
					accountLog.setCollection(tenderAccount.getCollection());
					accountLog.setTotal(tenderAccount.getTotal());
					accountLog.setToUser(0);
					accountLog.setRemark("借款标[<a href='/borrow/borrowDetail/" + b.getId() + "' target=_blank><font color=red>" + b.getName() + "</font></a>]流标,投资金额入账");
					accountLog.setBorrowId(b.getId());
					accountLog.setAddtime(Integer.parseInt(DateUtil.getTime()));
					accountLog.setAddip(ip);
					accountMapper.updateByPrimaryKeySelective(tenderAccount);
					accountLogMapper.insert(accountLog);
					borrowTenderMapper.updateByPrimaryKeySelective(tender);
				}
				borrowMapper.updateByPrimaryKeySelective(b);
			}
		}
	}

	/**
	 * 定时 还款短信提醒 (针对卓文汽车)
	 * 
	 * @author liuhuan
	 */
	@Override
	public void updateSendWillRepayBorrow() {
		String phone = null;
		boolean isPhone = false;
		StringBuffer content = new StringBuffer("亲爱的用户：您近期的借款还款日为");
		List<BorrowRepayment> willRepays = borrowRepaymentMapper.selectWillRepayBorrow(3); // 未来三天内即将还款信息
		if (EmptyUtil.isNotEmpty(willRepays)) {
			int i = 1;
			for (BorrowRepayment repayment : willRepays) {
				if (repayment.getUserName().equals("卓文汽车A")) {
					phone = repayment.getPhone();
					isPhone = true;
					String j = (i == 1 ? "①" : (i == 2 ? "②" : (i == 3 ? "③" : (i == 4 ? "④" : (i == 5 ? "⑤" : (i == 6 ? "⑥" : (i == 7 ? "⑦" : (i == 8 ? "⑧" : (i == 9 ? "⑨" : (i == 10 ? "⑩" : ""))))))))));
					content.append(j + DateUtil.getStringDateByLongDate(repayment.getRepaymentTime().longValue(), "yyyy年MM月dd日") + "，应还款金额" + repayment.getRepaymentAccount() + "元。");
					i++;
				}
			}
			content.append("请您于还款当日14:00前还款至微积金网站。");
			if (EmptyUtil.isNotEmpty(phone) && isPhone) {
				SendVerifyCodeMessage sendCode = new SendVerifyCodeMessage();
				sendCode.setContent(content.toString());
				// 卓文汽车1
				sendCode.setMobile(phone);
				// this.jmsSender.sendAsynchronousMessage(sendCode);
				this.jmsSenderUtil.asynSendSystemJms(sendCode);
				// 卓文汽车2
				sendCode.setMobile("18605327365");
				// this.jmsSender.sendAsynchronousMessage(sendCode);
				this.jmsSenderUtil.asynSendSystemJms(sendCode);
				// 张总
				sendCode.setMobile("18901750778");
				// this.jmsSender.sendAsynchronousMessage(sendCode);
				this.jmsSenderUtil.asynSendSystemJms(sendCode);
			}
		}
	}

	@Override
	public List<Borrow> selectByUserIdListPage(PageSearch pageSearch) {
		return this.borrowMapper.selectByUserIdListPage(pageSearch);
	}

	/**
	 * 根据borrowID查询borrow的详情
	 * 
	 * @param borrowId
	 * @return author LiLei 2014年5月12日
	 */
	@Override
	public Borrow selectBorrowById(Integer borrowId) {
		return this.borrowMapper.selectBorrowById(borrowId);
	}

	/**
	 * @Description:普通标发标
	 * @param user
	 * @param borrow
	 * @return
	 * @author liuhuan
	 * @throws IOException
	 */
	@Override
	public synchronized MessageVO insertNewBorrow(UserSession user, Borrow borrow, BorrowVO bvo, MortgageType mortgagetype, MortgageCar mortgageCar, HttpServletRequest request) throws IOException {
		MessageVO message = new MessageVO();
		MortgageType mtype = mortgageTypeMapper.selectByTypeName(mortgagetype.getTypeName());
		String result = "";
		mortgageCar.setAddTime(new Date());

		// handType=2-更新；4-重发
		if (bvo.getHandleType() != null && bvo.getHandleType() == 2) {
			mortgageCar.setId(bvo.getMcId());
			mortgageCarMapper.updateByPrimaryKeySelective(mortgageCar);
		} else {
			mortgageCarMapper.insertSelective(mortgageCar);
		}
		MortgageCarPic carPic = null;
		if (bvo.getHandleType() == null || bvo.getHandleType() != 2) { // 图片不做更新
			carPic = mortgageCarPicMapper.selectByPrimaryKey((Integer) request.getSession().getAttribute("card_pic1"));
			if (carPic != null) {
				carPic.setCarId(mortgageCar.getId());
				carPic.setType("0");
				mortgageCarPicMapper.updateByPrimaryKeySelective(carPic);// 身份证
																			// 正面
			}

			carPic = mortgageCarPicMapper.selectByPrimaryKey((Integer) request.getSession().getAttribute("card_pic2"));
			if (carPic != null) {
				carPic.setCarId(mortgageCar.getId());
				carPic.setType("1");
				mortgageCarPicMapper.updateByPrimaryKeySelective(carPic);// 身份证反面
			}

			carPic = mortgageCarPicMapper.selectByPrimaryKey((Integer) request.getSession().getAttribute("car_pic1"));
			if (carPic != null) {
				carPic.setCarId(mortgageCar.getId());
				carPic.setType("2");
				mortgageCarPicMapper.updateByPrimaryKeySelective(carPic);// 车1
			}

			carPic = mortgageCarPicMapper.selectByPrimaryKey((Integer) request.getSession().getAttribute("car_pic2"));
			if (carPic != null) {
				carPic.setCarId(mortgageCar.getId());
				carPic.setType("2");
				mortgageCarPicMapper.updateByPrimaryKeySelective(carPic);// 车2
			}

			carPic = mortgageCarPicMapper.selectByPrimaryKey((Integer) request.getSession().getAttribute("car_pic3"));
			if (carPic != null) {
				carPic.setCarId(mortgageCar.getId());
				carPic.setType("2");
				mortgageCarPicMapper.updateByPrimaryKeySelective(carPic);// 车3
			}

			carPic = mortgageCarPicMapper.selectByPrimaryKey((Integer) request.getSession().getAttribute("car_pic4"));
			if (carPic != null) {
				carPic.setCarId(mortgageCar.getId());
				carPic.setType("2");
				mortgageCarPicMapper.updateByPrimaryKeySelective(carPic);// 车4
			}

			carPic = mortgageCarPicMapper.selectByPrimaryKey((Integer) request.getSession().getAttribute("carcard_pic1"));
			if (carPic != null) {
				carPic.setCarId(mortgageCar.getId());
				carPic.setType("3");
				mortgageCarPicMapper.updateByPrimaryKeySelective(carPic);// 车行驶证1
			}
			// borrow.setLitpic((String)
			// request.getSession().getAttribute("uploadAttestationId")); 标图片不上传
			// 非必须上传项
			carPic = mortgageCarPicMapper.selectByPrimaryKey((Integer) request.getSession().getAttribute("carcard_pic2"));
			if (carPic != null) {
				carPic.setType("3");
				carPic.setCarId(mortgageCar.getId());
				mortgageCarPicMapper.updateByPrimaryKeySelective(carPic);// 车行驶证2
			}
			carPic = mortgageCarPicMapper.selectByPrimaryKey((Integer) request.getSession().getAttribute("carcard_pic3"));
			if (carPic != null) {
				carPic.setCarId(mortgageCar.getId());
				carPic.setType("3");
				mortgageCarPicMapper.updateByPrimaryKeySelective(carPic);// 车行驶证3
			}
			carPic = mortgageCarPicMapper.selectByPrimaryKey((Integer) request.getSession().getAttribute("other1"));
			if (carPic != null) {
				carPic.setCarId(mortgageCar.getId());
				carPic.setType("4");
				mortgageCarPicMapper.updateByPrimaryKeySelective(carPic);// 其他1
			}
			carPic = mortgageCarPicMapper.selectByPrimaryKey((Integer) request.getSession().getAttribute("other2"));
			if (carPic != null) {
				carPic.setCarId(mortgageCar.getId());
				carPic.setType("4");
				mortgageCarPicMapper.updateByPrimaryKeySelective(carPic);// 其他2
			}
			carPic = mortgageCarPicMapper.selectByPrimaryKey((Integer) request.getSession().getAttribute("other3"));
			if (carPic != null) {
				carPic.setCarId(mortgageCar.getId());
				carPic.setType("4");
				mortgageCarPicMapper.updateByPrimaryKeySelective(carPic);// 其他3
			}
		}

		// 如果是债券类型，保存合同时间
		if (EmptyUtil.isNotEmpty(bvo.getContract_StartStr()) && EmptyUtil.isNotEmpty(bvo.getContract_End())) {
			borrow.setContractStart(bvo.getContract_StartStr());
			borrow.setContractEnd(bvo.getContract_EndStr());
		}

		borrow.setUserId(user.getUserId());
		borrow.setStatus((byte) 0);
		borrow.setAddtime(Integer.parseInt(DateUtil.getTime()));
		borrow.setMortgageTypeid(mtype.getId());
		borrow.setMortgageId(mortgageCar.getId());

		borrow.setIsFast((byte) (borrow.getBiaoType().equals("fast") ? 1 : 0));
		// handType=2-更新；4-重发
		if (bvo.getHandleType() != null && bvo.getHandleType() == 2) {
			borrow.setId(bvo.getBorrowId());
			borrow.setOpenAccount(borrow.getOpenAccount() == null ? 0 : borrow.getOpenAccount());
			borrow.setOpenBorrow(borrow.getOpenBorrow() == null ? 0 : borrow.getOpenBorrow());
			borrow.setOpenCredit(borrow.getOpenCredit() == null ? 0 : borrow.getOpenCredit());
			borrow.setOpenTender(borrow.getOpenTender() == null ? 0 : borrow.getOpenTender());
			borrowMapper.updateByPrimaryKeySelective(borrow);
			result = "编辑成功，请等待审核。";
		} else {
			borrowMapper.insertSelective(borrow);
			result = "发布成功，请等待审核。";
		}
		message.setMsg(result);
		message.setId(borrow.getId());
		return message;
	}

	@Override
	public synchronized MessageVO insert8jieBorrow(UserSession user, Borrow borrow, BorrowVO bvo, MortgageType mortgagetype, MortgageCar mortgageCar, HttpServletRequest request, String card_pic1, String card_pic2, String car_pic1, String other3, String other2, String other1, String carcard_pic3, String car_pic2, String car_pic3, String car_pic4, String carcard_pic1, String carcard_pic2) throws IOException {
		MessageVO message = new MessageVO();
		MortgageType mtype = mortgageTypeMapper.selectByTypeName(mortgagetype.getTypeName());
		String result = "";
		mortgageCar.setAddTime(new Date());
		mortgageCarMapper.insertSelective(mortgageCar);

		MortgageCarPic carPic = new MortgageCarPic();
		carPic.setAddtime(new Date());
		carPic.setCarId(mortgageCar.getId());
		if (card_pic1 != null) {
			carPic.setPic(card_pic1);
			carPic.setType("0");
			mortgageCarPicMapper.insertSelective(carPic);// 身份证
		}
		if (card_pic2 != null) {
			carPic.setId(null);
			carPic.setPic(card_pic2);
			carPic.setType("1");
			mortgageCarPicMapper.insertSelective(carPic);// 身份证反面
		}
		if (car_pic1 != null) {
			carPic.setId(null);
			carPic.setPic(car_pic1);
			carPic.setType("2");
			mortgageCarPicMapper.insertSelective(carPic);// 车1
		}
		if (car_pic2 != null) {
			carPic.setId(null);
			carPic.setPic(car_pic2);
			carPic.setType("2");
			mortgageCarPicMapper.insertSelective(carPic);// 车2
		}
		if (car_pic3 != null) {
			carPic.setId(null);
			carPic.setPic(car_pic3);
			carPic.setType("2");
			mortgageCarPicMapper.insertSelective(carPic);// 车3
		}
		if (car_pic4 != null) {
			carPic.setId(null);
			carPic.setPic(car_pic4);
			carPic.setType("2");
			mortgageCarPicMapper.insertSelective(carPic);// 车4
		}
		if (carcard_pic1 != null) {
			carPic.setId(null);
			carPic.setPic(carcard_pic1);
			carPic.setType("3");
			mortgageCarPicMapper.insertSelective(carPic);// 车行驶证1
		}
		if (carcard_pic2 != null) {
			carPic.setId(null);
			carPic.setType("3");
			carPic.setPic(carcard_pic2);
			mortgageCarPicMapper.insertSelective(carPic);// 车行驶证2
		}
		if (carcard_pic3 != null) {
			carPic.setId(null);
			carPic.setPic(carcard_pic3);
			carPic.setType("3");
			mortgageCarPicMapper.insertSelective(carPic);// 车行驶证3
		}
		if (other1 != null) {
			carPic.setId(null);
			carPic.setPic(other1);
			carPic.setType("4");
			mortgageCarPicMapper.insertSelective(carPic);// 其他1
		}
		if (other2 != null) {
			carPic.setId(null);
			carPic.setPic(other2);
			carPic.setType("4");
			mortgageCarPicMapper.insertSelective(carPic);// 其他2
		}
		if (other3 != null) {
			carPic.setId(null);
			carPic.setPic(other3);
			carPic.setType("4");
			mortgageCarPicMapper.insertSelective(carPic);// 其他3
		}
		// 如果是债券类型，保存合同时间
		if (EmptyUtil.isNotEmpty(bvo.getContract_StartStr()) && EmptyUtil.isNotEmpty(bvo.getContract_End())) {
			borrow.setContractStart(bvo.getContract_StartStr());
			borrow.setContractEnd(bvo.getContract_EndStr());
		}

		borrow.setUserId(user.getUserId());
		borrow.setStatus((byte) 0);
		borrow.setAddtime(Integer.parseInt(DateUtil.getTime()));
		borrow.setMortgageTypeid(mtype.getId());
		borrow.setMortgageId(mortgageCar.getId());

		// borrow.setIsFast((byte) (borrow.getBiaoType().equals("fast") ? 1 :
		// 0));
		borrowMapper.insertSelective(borrow);
		result = "发布成功，请等待审核。";
		message.setMsg(result);
		message.setId(borrow.getId());
		return message;
	}

	public MortgageCarPic upFile(MultipartHttpServletRequest multipartRequest, String files, MortgageCarPic carPic) throws IOException {
		// 获得文件：
		MultipartFile file = multipartRequest.getFile(files);
		// 获得文件名：
		String filename = file.getOriginalFilename();
		if (file != null && filename != null && !"".equals(filename)) {
			// 获得输入流：
			InputStream input = file.getInputStream();
			String extName = filename.substring(filename.lastIndexOf(".") + 1);
			String picId = ObjectId.get();
			String filePath = "";
			if (ImageUtil.isImage(extName)) {
				filePath = WorkerPropertiesUtil.getFileDirValue() + "/" + picId + "." + extName;
				// filePath = "D:/images" + "/" + picId + "." + extName;
			}
			try {
				FileUtil.InputStreamTOFile(input, filePath);
				carPic.setPic(filePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return carPic;
	}

	/**
	 * @Description:发布体验标并初审
	 * @param borrow
	 * @param investorsFee
	 * @return
	 * @author liuhuan
	 */
	@Override
	public String insertFeelBorrow(Borrow borrow, Integer userId, String ip) {
		borrow.setSiteId((short) 0);
		borrow.setUserId(userId);
		borrow.setStatus((byte) 10);
		borrow.setAddtime(Integer.parseInt(DateUtil.getTime()));
		borrow.setAddip(ip);
		// borrow.setType(borrow.getType());
		borrow.setValidTime(7);
		borrow.setLowestAccount(borrow.getLowestAccount());
		borrow.setMostAccount(borrow.getMostAccount());
		borrow.setApr(borrow.getApr());
		borrow.setStyle((byte) 2);// 到期一次性还款
		borrow.setBiaoType("feel");
		borrow.setIsday((byte) 1); // 1：天标
		// borrow.setLitpic("themes/soonmes/images/feel.png");
		// borrow.setLitpic("537f1099e4b069a15a3d1e4d.png");
		borrow.setVerifyUser(0);
		borrow.setSuccessTime(Integer.parseInt(DateUtil.getTime()));
		borrow.setVerifyTime(Integer.parseInt(DateUtil.getTime()));
		borrow.setVerifyRemark("体验标系统自动初审");
		borrowMapper.insertSelective(borrow);

		// 插入投资费率
		InvestorsFee investorsFee = new InvestorsFee();
		investorsFee.setBid(borrow.getId());
		investorsFee.setBfee(new BigDecimal("0"));
		investorsFee.setGfee(new BigDecimal("0"));
		investorsFee.setBfeeType(new BigDecimal("0")); // ??
		investorsFee.setAddtime(Integer.parseInt(DateUtil.getTime()));
		investorsFee.setAddip(ip);
		investorsFeeMapper.insert(investorsFee);
		return "发布成功";
	}

	@Override
	public List<MortgageCarPic> selectBorrowPic(MortgageCarPic pic) {
		return this.mortgageCarPicMapper.selectBorrowPic(pic);
	}

	/**
	 * @Description:首页显示2条体验标
	 * @return
	 * @author liuhuan
	 */
	@Override
	public List<Borrow> selectFeelBorrowForIndex() {
		return borrowMapper.selectFeelBorrowForIndex();
	}

	/**
	 * @Description:借款总额
	 * @param userId
	 * @return
	 * @author liuhuan
	 */
	@Override
	public BigDecimal selectSumBorrowAccount(Integer userId) {
		return borrowMapper.selectSumBorrowAccount(userId);
	}

	/**
	 * 根据userID查询借款信息
	 * 
	 * @param pageSearch
	 * @return author LiLei 2014年5月19日
	 */
	@Override
	public List<Borrow> selectBorrowByUserIdListPage(PageSearch pageSearch) {
		// Auto-generated method stub
		return borrowMapper.selectBorrowByUserIdListPage(pageSearch);
	}

	@Override
	public ReleaseBorrowVO selectBorrowForRelease(int borrowId) {
		return borrowMapper.selectBorrowForRelease(borrowId);
	}

	@Override
	public List<Borrow> selectAllBorrowListPage(PageSearch pageSearch) {
		return borrowMapper.selectAllBorrowListPage(pageSearch);
	}

	/**
	 * 后台复审
	 * 
	 * @author liuhuan
	 */
	@Override
	public List<VerifyBorrowVO> selectFinalBorrowListPage(PageSearch pageSearch) {
		return borrowMapper.selectFinalBorrowListPage(pageSearch);
	}

	/**
	 * 后台初审
	 * 
	 * @param pageSearch
	 * @return author LiLei 2014年6月5日
	 */
	@Override
	public List<TheTrialBorrowVO> selectTheTrialListPage(PageSearch pageSearch) {
		// Auto-generated method stub
		return borrowMapper.selectTheTrialListPage(pageSearch);
	}

	/**
	 * 后台撤标
	 * 
	 * @author liuhuan
	 */
	@Override
	public List<VerifyBorrowVO> failBorrowListPage(PageSearch pageSearch) {
		return borrowMapper.failBorrowListPage(pageSearch);
	}

	@Override
	public int updateByPrimaryKeySelective(Borrow record) {
		// Auto-generated method stub
		return borrowMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int addSqdProduct(Borrow borrow) {
		//添加sqd产品
		return borrowMapper.insertSelective(borrow);
	}

}
