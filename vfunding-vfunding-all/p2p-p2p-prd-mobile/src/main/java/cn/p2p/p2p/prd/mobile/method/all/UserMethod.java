package cn.p2p.p2p.prd.mobile.method.all;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.account.service.IInterestDaysService;
import cn.p2p.p2p.biz.current.dao.CurrentRedeemMapper;
import cn.p2p.p2p.biz.current.dao.CurrentRedeemRuleMapper;
import cn.p2p.p2p.biz.current.dao.CurrentTenderMapper;
import cn.p2p.p2p.biz.current.model.CurrentInterestLog;
import cn.p2p.p2p.biz.current.model.CurrentRedeem;
import cn.p2p.p2p.biz.current.model.CurrentRedeemRule;
import cn.p2p.p2p.biz.current.model.CurrentTender;
import cn.p2p.p2p.biz.current.model.CurrentUserAccount;
import cn.p2p.p2p.biz.current.service.ICurrentInterestLogService;
import cn.p2p.p2p.biz.current.service.ICurrentUserAccountService;
import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.p2p.p2p.mobile.interceptors.CheckToken;
import cn.p2p.p2p.prd.mobile.method.request.vo.GeneralRequestVO;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.VerifyIdCard;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.account.model.AccountBank;
import cn.vfunding.vfunding.biz.account.service.IAccountBankService;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaDicParmUtil;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.model.SinaDic;
import cn.vfunding.vfunding.biz.sina.service.ISinaDicService;
import cn.vfunding.vfunding.biz.sina.service.ISinaMemberManagerService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.biz.usertoken.service.IUserTokenService;
import cn.vfunding.vfunding.biz.week.service.IWeekTenderService;

@Service
public class UserMethod {

	@Autowired
	private IUserService userService;

	/**
	 * Sina会员管理接口
	 */
	@Autowired
	private ISinaMemberManagerService sinaMemberManagerService;

	/**
	 * Sina code字典
	 */
	@Autowired
	private ISinaDicService sinaDicService;
	@Autowired
	private IUserTokenService userTokenService;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IWeekTenderService weekTenderService;

	@Autowired
	private IAccountBankService accountBankService;

	/**
	 * 实名认证
	 * 
	 * @param user
	 * @return
	 */
	@CheckToken
	public MobileBaseResponse saveRealName(GeneralRequestVO generalRequest) {
		if (generalRequest.getRealCard() == null || generalRequest.getRealName() == null) {
			return new MobileBaseResponse("cardId_fail", "参数有误");
		}
		if (!VerifyIdCard.verify(generalRequest.getRealCard())) {
			return new MobileBaseResponse("cardId_fail", "身份证号码错误");
		}
		List<UserWithBLOBs> cardUsers = this.userService.selectByCardId(generalRequest.getRealCard());
		if (EmptyUtil.isNotEmpty(cardUsers) && cardUsers.size() > 0) {
			return new MobileBaseResponse("cardId_fail", "该身份证已被注册");
		} else {
			User user = this.userService.selectByPrimaryKey(this.userTokenService.selectUserIdByToken(generalRequest.getToken()));
			if (user.getRealStatus() == null || !user.getRealStatus().equals("1")) {

				// 实名认证接口
				try {
					user.setRealname(generalRequest.getRealName());
					user.setCardId(generalRequest.getRealCard());
					String responseCode = sinaMemberManagerService.setRealName(user);

					if (StringUtils.isBlank(responseCode)) {
						return new MobileBaseResponse("cardId_fail", "出现异常，请重新认证");
					} else if (SinaMemberParmUtil.success.equals(responseCode) || SinaMemberParmUtil.ResponseCode.DUPLICATE_VERIFY.equals(responseCode)) {
						user.setRealStatus("1");
						BigDecimal hongbao = this.userService.getUserHongbao(user.getUserId());
						user.setHongbao(EmptyUtil.isEmpty(hongbao) ? new BigDecimal("0") : hongbao);
						this.userService.updateRealName(user);
					} else {
						SinaDic sinaDic = sinaDicService.dicLoad(SinaDicParmUtil.DicType.ResponseCode, responseCode);
						return new MobileBaseResponse("cardId_fail", "身份信息验证失败," + (sinaDic == null ? responseCode : sinaDic.getDicMsg()));
					}
				} catch (Exception e) {
					e.printStackTrace();
					return new MobileBaseResponse("cardId_fail", StringUtils.isBlank(e.getMessage()) ? "出现异常，请重新认证" : e.getMessage());
				}
			}
		}
		return new MobileBaseResponse();
	}

	@Autowired
	private IInterestDaysService interestDaysServive;

	/**
	 * 用户账户信息
	 * 
	 * @param uaRequest
	 * @return
	 */
	@CheckToken
	public MobileBaseResponse userAccount(GeneralRequestVO generalRequest) {
		Integer userId = this.userTokenService.selectUserIdByToken(generalRequest.getToken());
		Account account = this.accountService.selectByUserId(userId);
		// HoldingAssetsVO hav =
		// this.weekTenderService.selectHoldingAssetsByUserId(userId);
		// if (hav == null) {
		// hav = new HoldingAssetsVO();
		// }
		User user = this.userService.selectByPrimaryKey(userId);
		String phone = user.getPhone().substring(0, 3) + "****" + user.getPhone().substring(7, 11);
		BigDecimal sumInterest = this.interestDaysServive.selectByUserIdSum(userId);
		BigDecimal yesterdayInterest = this.interestDaysServive.selectByUserIdYesterday(userId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("phone", phone);
		map.put("sumInterest", sumInterest.toString());
		map.put("yesterdayInterest", yesterdayInterest.toString());
		map.put("useMoney", account.getUseMoney().toString());
		return new MobileBaseResponse(map);
	}
	@CheckToken
	public MobileBaseResponse userActionSteps(GeneralRequestVO generalRequest) {
		Integer userId = this.userTokenService.selectUserIdByToken(generalRequest.getToken());
		User u = this.userService.selectByPrimaryKey(userId);
		if (u.getPaypassword() == null || u.getPaypassword().equals("")) {
			return new MobileBaseResponse("1", "需设置支付密码");
		}
		if (u.getRealStatus() == null || u.getRealStatus().equals("0")) {
			return new MobileBaseResponse("2", "需实名认证");
		}
		List<AccountBank> banks = this.accountBankService.selectByUserId(userId);
		if (banks.isEmpty()) {
			return new MobileBaseResponse("3", "需绑卡");
		}
		return new MobileBaseResponse();
	}

	@Autowired
	private ICurrentUserAccountService currentAccountService;

	@Autowired
	private ICurrentInterestLogService currentInterestLogService;

	@Autowired
	private CurrentRedeemMapper currentRedeemMapper;

	@Autowired
	private CurrentRedeemRuleMapper currentRedeemRuleMapper;
	@CheckToken
	public MobileBaseResponse userCurrentAccount(GeneralRequestVO generalRequest) {
		Integer userId = this.userTokenService.selectUserIdByToken(generalRequest.getToken());
		CurrentUserAccount userCurrentAccount = this.currentAccountService.selectByPrimaryKey(userId);
		CurrentInterestLog cil = new CurrentInterestLog();
		cil.setUserId(userId);
		cil.setAddtime(DateUtil.dateToDate(new Date(), "yyyy-MM-dd"));
		List<CurrentInterestLog> listLog = this.currentInterestLogService.selectByParam(cil);

		BigDecimal sumInterest = this.currentInterestLogService.selectSumInterestByUserId(userId);
		// 得到取现规则
		CurrentRedeemRule redeemRule = this.currentRedeemRuleMapper.selectByPrimaryKey(userId);
		// 得到用户当日已取金额
		BigDecimal daySumMoney = this.currentRedeemMapper.selectByUserIdAndDate(userId);
		// 计算出还可取的金额
		BigDecimal enchashment = redeemRule.getDayMostAccount().subtract(daySumMoney);
		Map<String, String> map = new HashMap<String, String>();
		if (!listLog.isEmpty()) {
			CurrentInterestLog yesterdayInterest = listLog.get(0);
			map.put("yesterdayInterest", yesterdayInterest.getInterest().toString());
		} else {
			map.put("yesterdayInterest", "0.000");
		}
		map.put("total", userCurrentAccount.getTotal().toString());
		map.put("useMoney", userCurrentAccount.getUseMoney().add(userCurrentAccount.getNoUseMoney()).toString());
		map.put("enchashment", enchashment.toString());
		map.put("sumInterest", sumInterest.toString());
		return new MobileBaseResponse(map);
	}
	@CheckToken
	public MobileBaseResponse userCurrentRedeemLog(GeneralRequestVO generalRequest) {
		Integer userId = this.userTokenService.selectUserIdByToken(generalRequest.getToken());
		// 查询取出记录
		PageSearch pageSearch = new PageSearch();
		Map<String, Integer> pageSearchMap = new HashMap<String, Integer>();
		pageSearchMap.put("userId", userId);
		pageSearch.setEntity(pageSearchMap);
		List<CurrentRedeem> list = this.currentRedeemMapper.selectCurrentRedeemListPage(pageSearch);
		return new MobileBaseResponse(list);
	}
	
	@Autowired
	private CurrentTenderMapper currentTenderMapper;
	@CheckToken
	public MobileBaseResponse userCurrentTenderLog(GeneralRequestVO generalRequest) {
		Integer userId = this.userTokenService.selectUserIdByToken(generalRequest.getToken());
		// 查询取出记录
		PageSearch pageSearch = new PageSearch();
		Map<String, Integer> pageSearchMap = new HashMap<String, Integer>();
		pageSearchMap.put("userId", userId);
		pageSearch.setEntity(pageSearchMap);
		List<CurrentTender> list = this.currentTenderMapper.selectCurrentTenderListPage(pageSearch);
		return new MobileBaseResponse(list);
	}

	public static void main(String[] args) {
		System.out.println("18015914821".substring(0, 3) + "***" + "18015914821".substring(7, 11));
		System.out.println(DateUtil.getDateString(DateUtil.getAfterDateAsDate(new Date(), -1), "yyyy-MM-dd"));
		System.out.println(DateUtil.dateToDate(DateUtil.getAfterDateAsDate(new Date(), -1), "yyyy-MM-dd"));
	}
}
