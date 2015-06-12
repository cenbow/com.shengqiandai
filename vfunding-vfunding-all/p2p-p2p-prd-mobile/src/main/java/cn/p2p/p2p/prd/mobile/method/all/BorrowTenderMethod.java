package cn.p2p.p2p.prd.mobile.method.all;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.p2p.p2p.mobile.interceptors.CheckToken;
import cn.p2p.p2p.prd.mobile.method.request.vo.GeneralRequestVO;
import cn.p2p.p2p.prd.mobile.method.vo.TenderPageDetailVO;
import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.account.model.AccountBank;
import cn.vfunding.vfunding.biz.account.service.IAccountBankService;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.BorrowRule;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowCollectionService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowRuleService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.borrowMobile.service.IBorrowTenderMobileService;
import cn.vfunding.vfunding.biz.common.vo.SuccessTenderVO;
import cn.vfunding.vfunding.biz.common.vo.TenderBorrowVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionVO;
import cn.vfunding.vfunding.biz.hikes.dao.HikesCardMapper;
import cn.vfunding.vfunding.biz.hikes.model.HikesCard;
import cn.vfunding.vfunding.biz.system.model.InvestorsFee;
import cn.vfunding.vfunding.biz.system.service.IInvestorsFeeService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.biz.usertoken.service.IUserTokenService;

import com.alibaba.druid.util.StringUtils;

@Service
public class BorrowTenderMethod {

	@Autowired
	private IBorrowTenderService tenderService;

	@Autowired
	private IBorrowCollectionService borrowCollectionService;

	@Autowired
	private IBorrowTenderMobileService borrowTenderMobileService;

	@Autowired
	private IBorrowService borrowService;

	@Autowired
	private IAccountService accountService;

	@Autowired
	private HikesCardMapper hikesCardMapper;

	@Autowired
	private IInvestorsFeeService investorsFeeService;

	@Autowired
	private IBorrowRuleService borrowRuleService;
	@Autowired
	private IUserTokenService userTokenService;
	@Autowired
	private IUserService userService;

	@Autowired
	private IAccountBankService accountBankService;

	@CheckToken
	public MobileBaseResponse tenderPageDetail(GeneralRequestVO generalRequest) {
		Borrow borrow = this.borrowService.selectBorrowById(generalRequest.getId());
		if (EmptyUtil.isEmpty(borrow))
			return new MobileBaseResponse("borrow_null_fail", "标的信息不存在");
		Integer userId = userTokenService.selectUserIdByToken(generalRequest.getToken());
		Account account = this.accountService.selectByUserId(userId);
		// 查询用户加息卡
		HikesCard hikesCard = this.hikesCardMapper.selectByPrimaryKey(userId);
		BigDecimal useHikesRate = new BigDecimal(0);
		BigDecimal expApr = new BigDecimal(0);
		if (hikesCard != null) {
			// 用户总共的加息卡利率
			BigDecimal useRateDouble = hikesCard.getUseRate();
			// 查询平台服务费信息
			InvestorsFee investorsFee = this.investorsFeeService.selectByBorrowId(generalRequest.getId());
			// 用户得到的标的利息
			expApr = borrow.getApr().subtract(investorsFee.getBfee()).subtract(investorsFee.getGfee());
			// 最多可加息
			BorrowRule borrowRule = this.borrowRuleService.selectByPrimaryKey(generalRequest.getId());
			BigDecimal mostHikes = borrowRule.getMostHikes().subtract(expApr);
			BigDecimal useRate = useRateDouble.doubleValue() > mostHikes.doubleValue() ? mostHikes : useRateDouble;
			useHikesRate = useRate;
		}
		List<AccountBank> banks = this.accountBankService.selectByUserId(userId);

		TenderPageDetailVO responseObject = new TenderPageDetailVO();
		if (banks != null) {
			responseObject.setBankName(banks.get(0).getBankName());
		}
		DecimalFormat df = new DecimalFormat("0.00");
		responseObject.setName(borrow.getName());
		responseObject.setAccount(borrow.getAccountStr());
		responseObject.setAccountYes(df.format(borrow.getAccountYes()));
		responseObject.setLowestAccount(df.format(borrow.getLowestAccount()));
		responseObject.setUseMoney(df.format(account.getUseMoney()));
		responseObject.setUseHikesRate(df.format(useHikesRate));
		responseObject.setTimeLimit(borrow.getTimeLimitDay().toString());
		responseObject.setExpectApr(df.format(expApr));
		responseObject.setInterestData("满标次日计息");
		if (hikesCard == null) {
			responseObject.setUseRate("0");
		} else {
			responseObject.setUseRate(df.format(hikesCard.getUseRate()));
		}
		return new MobileBaseResponse(responseObject);
	}

	public MobileBaseResponse tenderPageDetailNoToken(GeneralRequestVO generalRequest) {
		Borrow borrow = this.borrowService.selectBorrowById(generalRequest.getId());
		if (EmptyUtil.isEmpty(borrow))
			return new MobileBaseResponse("borrow_null_fail", "标的信息不存在");
		return new MobileBaseResponse(borrow);
	}

	@CheckToken
	public MobileBaseResponse tenderAction(GeneralRequestVO generalRequest) {
		if (generalRequest.getId() == null || generalRequest.getId() < 1)
			return new MobileBaseResponse("borrowId_fail", "标的编号不正确");
		if (generalRequest.getIsUseHikes() == null || generalRequest.getIsUseHikes() < 0)
			return new MobileBaseResponse("isUserHikes_fail", "加息卡使用状态错误");
		if (generalRequest.getMoney() == null || generalRequest.getMoney().doubleValue() < 0)
			return new MobileBaseResponse("paymoney_fail", "投资金额不正确");
		if (StringUtils.isEmpty(generalRequest.getPaypassword()))
			return new MobileBaseResponse("paypassword_null_fail", "支付密码不可为空");
		UserTenderActionResultVO resultVO = new UserTenderActionResultVO();
		Integer userId = this.userTokenService.selectUserIdByToken(generalRequest.getToken());
		User user = this.userService.selectByPrimaryKey(userId);
		if (generalRequest.getPaypassword().equals(user.getPaypassword())) {
			Borrow borrow = this.borrowService.selectById(generalRequest.getId());
			if (borrow == null) {
				return new MobileBaseResponse("tender_fail", "无此标的");
			}
			if (borrow.getStatus() == 1) {
				UserTenderActionVO userVO = new UserTenderActionVO();
				userVO.setBorrowId(generalRequest.getId());
				userVO.setIsUseHikes(generalRequest.getIsUseHikes());
				userVO.setPayMoney(generalRequest.getMoney());
				userVO.setPaypassword(generalRequest.getPaypassword());
				userVO.setUserType(user.getTypeId());
				userVO.setUserId(user.getUserId());
				userVO.setUserip(generalRequest.getOsType());
				try {
					resultVO = this.tenderService.userTenderAction(userVO);
				} catch (BusinessException e) {
					return new MobileBaseResponse("tender_fail", e.getMessage());
				}

				return new MobileBaseResponse(resultVO);
			} else if (borrow.getStatus() == 10) {
				TenderBorrowVO tenderInfo = new TenderBorrowVO();
				tenderInfo.setUserId(user.getUserId());
				tenderInfo.setBorrowId(generalRequest.getId());
				tenderInfo.setMoney(generalRequest.getMoney());
				tenderInfo.setPaypassword(generalRequest.getPaypassword());
				SuccessTenderVO result = this.borrowService.updateTenderFeelBorrow(tenderInfo, generalRequest.getOsType());
				if (result.getResult().equals("投资成功。")) {
					resultVO.setPayMoney(result.getAccount());
					if (result.getAccount().doubleValue() == generalRequest.getMoney().doubleValue()) {
						resultVO.setStatus(1);
					} else if (generalRequest.getMoney().doubleValue() > 0) {
						resultVO.setStatus(2);
					} else {
						resultVO.setStatus(3);
					}
					return new MobileBaseResponse(resultVO);
				} else {
					return new MobileBaseResponse("tender_other_fail", result.getResult());
				}
			} else {
				return new MobileBaseResponse("tender_fail", "该标不是可投的标.");
			}
		} else {
			return new MobileBaseResponse("paypassword_fail", "支付密码错误");
		}
	}
}
