package cn.vfunding.vfunding.biz.account.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.server.EmployeeSession;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.dao.AccountCashMapper;
import cn.vfunding.vfunding.biz.account.dao.AccountLogMapper;
import cn.vfunding.vfunding.biz.account.dao.AccountMapper;
import cn.vfunding.vfunding.biz.account.dao.AccountRechargeMapper;
import cn.vfunding.vfunding.biz.account.dao.CashNumberMapper;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.account.model.AccountCash;
import cn.vfunding.vfunding.biz.account.model.AccountLog;
import cn.vfunding.vfunding.biz.account.model.CashNumber;
import cn.vfunding.vfunding.biz.account.service.IAccountCashService;
import cn.vfunding.vfunding.biz.activity.dao.ActivityHongbaoMapper;
import cn.vfunding.vfunding.biz.activity.model.ActivityHongbao;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowTenderMapper;
import cn.vfunding.vfunding.biz.common.vo.AccountCashVO;
import cn.vfunding.vfunding.biz.common.vo.MessageVO;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.system.dao.AllStatisticsMapper;
import cn.vfunding.vfunding.biz.system.dao.CashRuleMapper;
import cn.vfunding.vfunding.biz.system.dao.SmsLogMapper;
import cn.vfunding.vfunding.biz.system.model.AllStatistics;
import cn.vfunding.vfunding.biz.system.model.CashRule;
import cn.vfunding.vfunding.biz.system.model.SmsLog;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.userMobile.dao.UserStatisticsDataMobileMapper;
import cn.vfunding.vfunding.biz.userMobile.model.UserRechargeCashMobile;

@Service("accountCashService")
public class AccountCashServiceImpl implements IAccountCashService {

	@Autowired
	private AccountCashMapper mapper;
	@Autowired
	private AccountRechargeMapper accountRechargeMapper;
	@Autowired
	private AllStatisticsMapper allStatisticsMapper;
	@Autowired
	private UserStatisticsDataMobileMapper userStatisticsDataMobileMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AccountCashMapper accountCashMapper;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private AccountLogMapper accountLogMapper;
	@Autowired
	private SmsLogMapper smsLogMapper;
	@Autowired
	private CashRuleMapper cashRuleMapper;
	@Autowired
	private CashNumberMapper cashNumberMapper;

	@Autowired
	private BorrowTenderMapper borrowTenderMapper;

	@Autowired
	private ActivityHongbaoMapper activityHongbaoMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AccountCash record) {
		return this.mapper.insert(record);
	}

	@Override
	public int insertSelective(AccountCash record) {
		return this.mapper.insertSelective(record);
	}

	@Override
	public AccountCash selectByPrimaryKey(Integer id) {
		return this.mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AccountCash record) {
		return this.mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AccountCash record) {
		return this.mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<UserRechargeCashMobile> selectByUserIdListPage(PageSearch search) {
		return this.mapper.selectByUserIdListPage(search);

	}

	@Override
	public BigDecimal selectByDayUserId(Integer userId) {
		return this.mapper.selectByDayUserId(userId);
	}

	/**
	 * @Description:获取用户提现时千3手续费
	 * @param userId
	 * @param account
	 *            提现金额
	 * @return
	 * @author liuhuan
	 */
	@Override
	public BigDecimal userAdditionalCashFee(Integer userId, BigDecimal account) {
		CashRule rule = cashRuleMapper.selectOne(); // 可放缓存

		AllStatistics allStatistics = allStatisticsMapper
				.selectStatisticsByUserId(userId);
		BigDecimal returnFee = userStatisticsDataMobileMapper
				.selectUserSumEarnedInterestOfSonByUserId(userId);
		String count15days = accountRechargeMapper
				.selectCount15daysByUserId(userId);
		// 公式
		BigDecimal sum = allStatistics.getAllRechargeMoney()
				.subtract(allStatistics.getAllCashMoney())
				.subtract(new BigDecimal(count15days))
				.add(allStatistics.getAllInterestMoney())
				.add(returnFee == null ? new BigDecimal("0") : returnFee);
		sum = sum.compareTo(new BigDecimal("0")) == 1 ? sum : new BigDecimal(
				"0");
		// 超出sum的部分收取0.3%的手续费
		BigDecimal scaleExtraFee = new BigDecimal(rule.getScaleExtraFee())
				.divide(new BigDecimal("100"), 3, BigDecimal.ROUND_HALF_UP);
		BigDecimal fee = account.compareTo(sum) != 1 ? new BigDecimal("0")
				: account.subtract(sum).multiply(scaleExtraFee)
						.setScale(2, BigDecimal.ROUND_DOWN);
		return fee;
	}

	/**
	 * @Description:用户申请提现
	 * @param status
	 *            :0待审核
	 * @param isHongbao
	 *            :1使用红包
	 * @param ip
	 * @return
	 * @author liuhuan
	 */
	@Override
	public synchronized Map<String, Object> updateApplyCash(UserSession userSession,
			AccountCashVO cashVO) {
		CashRule rule = cashRuleMapper.selectOne(); // 可放缓存
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(userSession.getTypeId() != 40 && cashVO.getMoney().doubleValue() > 50000d){
			map.put("result", "提现金额不可大于50000元");
			return map;
		}
		
//		String result = "";
		BigDecimal cashMoney = cashVO.getMoney();// 提现金额
		Account account = accountMapper.selectByUserId(userSession.getUserId());
		int count = this.borrowTenderMapper.selectByUserId(userSession
				.getUserId());
//		ActivityHongbao hongbaoList = this.activityHongbaoMapper
//				.selectByPhone(userSession.getPhone());
		if (account.getUseMoney().compareTo(cashMoney) == -1) {
			map.put("result", "账户余额不足");
		} else if (cashMoney.doubleValue() < 50 && count == 0 ) {
			map.put("result", "您不满足提现要求,需提现金额大于50元,或有一次投资记录方可申请50元以下提现金额");
		} else {
			BigDecimal cashFee = this.userAdditionalCashFee(
					account.getUserId(), cashVO.getMoney());// 超出部分手续费
			User user = userMapper.selectByPrimaryKey(userSession.getUserId());

			BigDecimal finalFee = new BigDecimal("0");// 抵扣红包后手续费
			BigDecimal useHongbao = new BigDecimal("0");// 本次使用的红包
			//用户拥有的红包
			BigDecimal realHongBao=this.userMapper.selectHongbaoByUserId(userSession.getUserId());
			if (userSession.getTypeId() == 40) {// 借款用户提现不收超额手续费
				finalFee = cashMoney
						.compareTo(new BigDecimal(rule.getCashLt())) == 1 ? new BigDecimal(
						rule.getEveryGtFee()) : new BigDecimal(
						rule.getEveryLtFee());
				if (cashVO.getIsHongbao() == null) {// 不使用红包
					useHongbao = new BigDecimal("0");
				} else {
					useHongbao = realHongBao.compareTo(finalFee) > 0 ? finalFee
							: realHongBao;
				}
				finalFee = finalFee.subtract(useHongbao);// 抵扣红包后手续费
				user.setHongbao(user.getHongbao().subtract(useHongbao));
			} else {
				if (cashVO.getIsHongbao() == null) { // 不使用红包
					if (cashVO.getMoney().compareTo(
							new BigDecimal(rule.getCashLt())) == 1) { // 大于10000
						finalFee = cashFee.add(new BigDecimal(rule
								.getEveryGtFee()));
					} else {
						finalFee = cashFee.add(new BigDecimal(rule
								.getEveryLtFee()));
					}
				} else { // 使用红包
					if (cashVO.getMoney().compareTo(
							new BigDecimal(rule.getCashLt())) == 1) { // 大于10000
						if (user.getHongbao().compareTo(
								new BigDecimal(rule.getEveryGtFee())) != -1) {
							finalFee = new BigDecimal("0").add(cashFee);
							useHongbao = new BigDecimal(rule.getEveryGtFee());
							user.setHongbao(user.getHongbao().subtract(
									new BigDecimal(rule.getEveryGtFee())));
						} else {
							finalFee = new BigDecimal(rule.getEveryGtFee())
									.subtract(user.getHongbao()).add(cashFee);
							useHongbao = user.getHongbao();
							user.setHongbao(new BigDecimal("0"));
						}
					} else { // 小于等于10000
						if (user.getHongbao().compareTo(
								new BigDecimal(rule.getEveryLtFee())) != -1) {
							finalFee = new BigDecimal("0").add(cashFee);
							useHongbao = new BigDecimal(rule.getEveryLtFee());
							user.setHongbao(user.getHongbao().subtract(
									new BigDecimal(rule.getEveryLtFee())));
						} else {
							finalFee = new BigDecimal(rule.getEveryLtFee())
									.subtract(user.getHongbao()).add(cashFee);
							useHongbao = user.getHongbao();
							user.setHongbao(new BigDecimal("0"));
						}
					}
				}
			}
			UserWithBLOBs uwb = new UserWithBLOBs();
			uwb.setUserId(user.getUserId());
			uwb.setHongbao(user.getHongbao());
			userMapper.updateByPrimaryKeySelective(uwb);// 更新红包

			AccountCash accountCash = new AccountCash();
			accountCash.setUserId(user.getUserId());
			accountCash.setStatus(cashVO.getStatus().byteValue());
			accountCash.setAccount(cashVO.getBankNum());
			accountCash.setBank(cashVO.getBankNo());
			accountCash.setBranch(cashVO.getBranch());
			accountCash.setTotal(cashVO.getMoney());
			accountCash.setCredited(accountCash.getTotal().subtract(finalFee)); // 到账金额
			accountCash.setFee(finalFee.add(useHongbao));// fee包含红包金额
			accountCash.setAddtime(Integer.parseInt(DateUtil.getTime()));
			accountCash.setAddip(cashVO.getIp());
			accountCash.setHongbao(useHongbao);
			accountCashMapper.insertSelective(accountCash);

			// 冻结资金(冻结金额包含手续费)
			account.setUseMoney(account.getUseMoney().subtract(
					accountCash.getTotal()));
			account.setNoUseMoney(EmptyUtil.isNotEmpty(account.getNoUseMoney()) ? account
					.getNoUseMoney().add(accountCash.getTotal()) : accountCash
					.getTotal());
			AccountLog accountLog = new AccountLog();
			accountLog.setUserId(user.getUserId());
			accountLog.setType("cash_frost");
			accountLog.setMoney(accountCash.getTotal());
			accountLog.setUseMoney(account.getUseMoney());
			accountLog.setNoUseMoney(account.getNoUseMoney());
			accountLog.setCollection(account.getCollection());
			accountLog.setTotal(account.getTotal());
			accountLog.setToUser(0);
			accountLog.setRemark("用户申请提现"
					+ accountCash.getTotal().setScale(2,
							BigDecimal.ROUND_HALF_UP)
					+ "元;"
					+ "扣除手续费:"
					+ accountCash.getFee()
					+ "元;"
					+ (useHongbao.doubleValue() == 0.00 ? "" : "使用红包:"
							+ useHongbao + "元"));
			accountLog.setAddtime(Integer.parseInt(DateUtil.getTime()));
			accountLog.setAddip(cashVO.getIp());
			accountLogMapper.insertSelective(accountLog);
			accountMapper.updateByPrimaryKeySelective(account);// 更新账户

			map.put("result", "申请提现成功");
			map.put("accountCash", accountCash);
		}
		return map;
	}

	/**
	 * @Description:财务放款
	 * @param accountCash
	 * @param cashVO
	 * @return
	 * @author liuhuan
	 */
	@Override
	public synchronized String updateTakeCash(AccountCash accountCash,
			AccountCashVO cashVO) {
		AccountCash ac = accountCashMapper.selectByPrimaryKey(accountCash.getId());
		Byte a = 0;
		Byte b = 4;
		if(!ac.getStatus().equals(a) && !ac.getStatus().equals(b)){
			return "0";
		}
		String result = "";
		String back = "";
		Account account = accountMapper.selectByUserId(accountCash.getUserId());
		User user = userMapper.selectByPrimaryKey(accountCash.getUserId());

		if (cashVO.getStatus() == 1) {// 1:成功
			BigDecimal realMoney = accountCash.getTotal()
					.subtract(accountCash.getFee())
					.add(accountCash.getHongbao())
					.setScale(2, BigDecimal.ROUND_HALF_UP); // 提现实际金额=提现总额-提现手续费+红包抵扣
			BigDecimal realFee = accountCash.getTotal().subtract(realMoney)
					.setScale(2, BigDecimal.ROUND_HALF_UP); // 手续费=提现总额-到账金额
			account.setNoUseMoney(account.getNoUseMoney().subtract(realMoney));
			account.setTotal(account.getTotal().subtract(realMoney));
			AccountLog accountLog = new AccountLog();
			accountLog.setUserId(accountCash.getUserId());
			accountLog.setType("recharge_success");
			accountLog.setMoney(realMoney);
			accountLog.setUseMoney(account.getUseMoney());
			accountLog.setNoUseMoney(account.getNoUseMoney());
			accountLog.setCollection(account.getCollection());
			accountLog.setTotal(account.getTotal());
			accountLog.setRemark("用户成功提现"
					+ accountCash.getCredited().setScale(2,
							BigDecimal.ROUND_HALF_UP) + "元");
			accountLog.setAddtime(Integer.parseInt(DateUtil.getTime()));
			accountLog.setAddip(cashVO.getIp());
			accountLogMapper.insertSelective(accountLog);
			accountMapper.updateByPrimaryKeySelective(account);// 更新账户
			// 提现手续费
			account.setNoUseMoney(account.getNoUseMoney().subtract(realFee));
			account.setTotal(account.getTotal().subtract(realFee));
			AccountLog accountLog1 = new AccountLog();
			accountLog1.setUserId(accountCash.getUserId());
			accountLog1.setType("recharge_fee");
			accountLog1.setMoney(realFee);
			accountLog1.setUseMoney(account.getUseMoney());
			accountLog1.setNoUseMoney(account.getNoUseMoney());
			accountLog1.setCollection(account.getCollection());
			accountLog1.setTotal(account.getTotal());
			accountLog1.setRemark("扣除提现手续费"
					+ accountCash.getFee()
							.setScale(2, BigDecimal.ROUND_HALF_UP) + "元,使用红包："
					+ accountCash.getHongbao());
			accountLog1.setAddtime(Integer.parseInt(DateUtil.getTime()));
			accountLog1.setAddip(cashVO.getIp());
			accountLogMapper.insertSelective(accountLog1);
			accountMapper.updateByPrimaryKeySelective(account);// 更新账户

		} else if (cashVO.getStatus() == 2) {// 2:失败
			account.setNoUseMoney(account.getNoUseMoney().subtract(
					accountCash.getTotal()));
			account.setUseMoney(account.getUseMoney().add(
					accountCash.getTotal()));
			// 退还红包
			if (accountCash.getHongbao().toString() != null
					&& !"".equals(accountCash.getHongbao().toString())) {
				user.setHongbao(user.getHongbao().add(accountCash.getHongbao()));
				UserWithBLOBs uwb = new UserWithBLOBs();
				uwb.setUserId(user.getUserId());
				uwb.setHongbao(user.getHongbao());
				back = "退还红包:" + accountCash.getHongbao() + "元";
				userMapper.updateByPrimaryKeySelective(uwb);
			}
			AccountLog accountLog = new AccountLog();
			accountLog.setUserId(accountCash.getUserId());
			accountLog.setType("recharge_false");
			accountLog.setMoney(accountCash.getCredited());
			accountLog.setUseMoney(account.getUseMoney());
			accountLog.setNoUseMoney(account.getNoUseMoney());
			accountLog.setCollection(account.getCollection());
			accountLog.setRemark("用户提现失败,退还资金:"
					+ accountCash.getTotal().setScale(2,
							BigDecimal.ROUND_HALF_UP) + "元" + back);
			accountLog.setAddtime(Integer.parseInt(DateUtil.getTime()));
			accountLog.setAddip(cashVO.getIp());
			accountLogMapper.insertSelective(accountLog);
			accountMapper.updateByPrimaryKeySelective(account);// 更新账户

		} else {
			throw new BusinessException("8005011", "提现状态异常，提现失败");
		}
		accountCash.setStatus(cashVO.getStatus().byteValue());
		accountCash.setVerifyRemark(cashVO.getRemark());
		accountCash.setVerifyTime(Integer.parseInt(DateUtil.getTime()));
		accountCash.setVerifyUserid(EmployeeSession.getEmpSessionEmpId());
		accountCash.setAddip(cashVO.getIp());
		accountCashMapper.updateByPrimaryKeySelective(accountCash);

//		// 发短信
//		String msg = cashVO.getStatus() == 1 ? "您的提现" + accountCash.getTotal()
//				+ "元申请通过了审核,即将安排财务处理,请注意查收！。【微积金】" : "您的提现"
//				+ accountCash.getTotal() + "元申请没有通过审核,请登录网站了解详情！。【微积金】";
//		this.updateSendSms(msg, user.getPhone(), user.getUserId());
//		// result = "提现"+(cashVO.getStatus()==1?"成功":"失败");
		result = "1";// 操作成返回1
		return result;
	}

	/**
	 * for php提现费用
	 * 
	 * @return
	 * @author liuhuan
	 */
	public MessageVO updateCashFeePhp(AccountCashVO vo) {
		CashRule rule = cashRuleMapper.selectOne(); // 可放缓存

		MessageVO m = new MessageVO();
		BigDecimal fee = this.userAdditionalCashFee(vo.getUserId(),
				vo.getMoney());// 千3手续费

		User user = userMapper.selectByPrimaryKey(vo.getUserId());
		BigDecimal baseFee = vo.getMoney().compareTo(
				new BigDecimal(rule.getCashLt())) == 1 ? new BigDecimal(
				rule.getEveryGtFee()) : new BigDecimal(rule.getEveryLtFee());
		BigDecimal allFee = new BigDecimal("0");// 提现总费用
		BigDecimal hongbao = user.getHongbao();
		String useHongbao = "";// 本次使用的红包
		if (user.getTypeId() == 40) {// 借款用户提现不收超额手续费
			allFee = vo.getMoney().compareTo(new BigDecimal(rule.getCashLt())) == 1 ? new BigDecimal(
					rule.getEveryGtFee())
					: new BigDecimal(rule.getEveryLtFee());
			useHongbao = hongbao.compareTo(allFee) > 0 ? allFee.toString()
					: hongbao.toString();
			user.setHongbao(user.getHongbao().subtract(
					new BigDecimal(useHongbao)));
		} else if (hongbao != null) { // 有红包必须使用
			if (hongbao.compareTo(baseFee) == 1) { // 大于baseFee
				allFee = fee.add(baseFee);
				user.setHongbao(hongbao.subtract(baseFee));
				useHongbao = baseFee.toString();
			} else {
				allFee = fee.add(baseFee);
				user.setHongbao(new BigDecimal("0"));
				useHongbao = hongbao.toString();// user.getHongbao().toString();
			}
		} else {
			useHongbao = "0";
			allFee = fee.add(baseFee);
		}

		// 用户红包在php代码中做相应操作

		m.setMsg(allFee.toString()); // 手续费(包含红包)
		m.setMsg2(useHongbao);// 本次使用的红包
		return m;
	}

	/**
	 * 财务审核列表
	 * 
	 * @author liuhuan
	 */
	@Override
	public List<AccountCashVO> selectWaitApplyListPage(PageSearch search) {
		return accountCashMapper.selectWaitApplyListPage(search);
	}

	/**
	 * @Description:发送短信、写入数据库
	 * @param content
	 * @param mobile
	 * @author liuhuan
	 */
	public void updateSendSms(String content, String mobile, Integer userId) {
		if (mobile != null && !"".equals(mobile)) {
			StringBuilder path = new StringBuilder("?");
			path.append("phone=" + mobile);
			path.append("&content=" + content);
			path.append("&type=systme&from=vfunding-www");

			SmsLog sms = new SmsLog();
			sms.setUserId(userId);
			sms.setMobile(mobile);
			sms.setMsgContent(content);
			sms.setIsSend(1);
			sms.setSpNumber("1069800000091717");
			sms.setReportMsg("1");
			sms.setUpdateTime(new Date());
			sms.setAddTime(new Date());

			// smsLogMapper.insertSelective(sms);
			// this.senderSmsRestInvokerFactory.getRestInvoker().get(path.toString(),Integer.class);
		}
	}

	@Override
	public int insertCashNumber(CashNumber cashNumber) {
		// TODO Auto-generated method stub
		cashNumber.setAddTime(new Date());
		return this.cashNumberMapper.insertSelective(cashNumber);
	}

	@Override
	public CashNumber selectCashNumberByCashId(Integer cashId) {
		// TODO Auto-generated method stub
		return this.cashNumberMapper.selectByCashId(cashId);
	}

	@Override
	public int updateCashNumber(CashNumber cashNumber) {
		// TODO Auto-generated method stub
		cashNumber.setUpdateTime(new Date());
		return this.cashNumberMapper.updateByPrimaryKeySelective(cashNumber);

	}

	/**
	 * 查询用户提现记录 lx
	 */
	@Override
	public List<AccountCash> selectByidListPage(PageSearch pageSearch) {
		return accountCashMapper.selectByidListPage(pageSearch);
	}
}
