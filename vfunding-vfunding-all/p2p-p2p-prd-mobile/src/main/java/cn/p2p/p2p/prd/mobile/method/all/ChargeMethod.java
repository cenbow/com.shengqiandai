package cn.p2p.p2p.prd.mobile.method.all;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.p2p.p2p.biz.rechargeandtender.model.RechargeAndTender;
import cn.p2p.p2p.biz.rechargeandtender.service.IRechargeAndTenderService;
import cn.p2p.p2p.mobile.interceptors.CheckToken;
import cn.p2p.p2p.prd.mobile.cache.IFilterUnlawfulRequest;
import cn.p2p.p2p.prd.mobile.method.request.vo.GeneralRequestVO;
import cn.p2p.p2p.prd.mobile.vo.RechargeMobileaAdvanceVO;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.rest.annotation.RestDescription;
import cn.vfunding.vfunding.biz.account.model.AccountRecharge;
import cn.vfunding.vfunding.biz.account.service.IAccountRechargeService;
import cn.vfunding.vfunding.biz.sina.model.SinaCard;
import cn.vfunding.vfunding.biz.sina.service.IAccountRechargeSinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaCardService;
import cn.vfunding.vfunding.biz.sina.vo.returns.CreateHostingDepositReturnVO;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.biz.usertoken.service.IUserTokenService;

@Service
public class ChargeMethod {

	@Autowired
	private IUserService userService;

	@Autowired
	private IFilterUnlawfulRequest filterService;

	@Autowired
	private ISinaCardService sinaCardService;

	@Autowired
	private IAccountRechargeSinaService accountRechargeSinaService;
	@Autowired
	private IUserTokenService userTokenService;

	@Autowired
	private IRechargeAndTenderService rechargeAndTenderService;

	@Autowired
	private IAccountRechargeService accountRechargeService;

	@RestDescription("充值")
	@CheckToken
	public MobileBaseResponse recharge(GeneralRequestVO generalRequest) throws Exception {
		Integer userId = this.userTokenService.selectUserIdByToken(generalRequest.getToken());
		UserWithBLOBs user = this.userService.selectByPrimaryKey(userId);
		if (EmptyUtil.isNotEmpty(user) && user.getUserId() > 0) {
			if (EmptyUtil.isNotEmpty(generalRequest.getScId())) {
				SinaCard sc = this.sinaCardService.selectByPrimaryKey(generalRequest.getScId());
				if (EmptyUtil.isNotEmpty(sc)) {
					if (EmptyUtil.isNotEmpty(sc.getSinaCard())) {
						if (EmptyUtil.isNotEmpty(sc.getUserId())) {
							if (sc.getUserId().equals(userId)) {
								if (EmptyUtil.isNotEmpty(generalRequest.getMoney())) {
									BigDecimal zero = new BigDecimal(0);
									BigDecimal depositMoney = new BigDecimal(generalRequest.getMoney().doubleValue());
									if (zero.compareTo(depositMoney) < 0) {
										// 校验通过后更新银行卡权重
										this.sinaCardService.updateQuickPayCardWeight(sc);
										// 创建AccountRecharge对象
										AccountRecharge rec = this.accountRechargeSinaService.getAccountRecharge(userId, generalRequest.getMoney().doubleValue() + "", generalRequest.getOsType(), "新浪支付绑卡支付");
										// 后端请求新浪绑卡支付接口
										Map<String, Object> map = this.accountRechargeSinaService.doUserRechargeByBindingPay(rec, sc);
										if (map.get("result").toString().equals("success")) {
											// 返回流水号等信息
											CreateHostingDepositReturnVO returnVO = (CreateHostingDepositReturnVO) map.get("rechargeByBindingPayReturnVO");
											RechargeMobileaAdvanceVO rma = new RechargeMobileaAdvanceVO();
											rma.setOutTradeNo(returnVO.getOut_trade_no());
											rma.setTicket(returnVO.getTicket());
											// 充值并投资
											if (generalRequest.getTenderMoney() != null && generalRequest.getTenderMoney().doubleValue() >= 0) {
												RechargeAndTender rat = new RechargeAndTender();
												rat.setTradeNo(returnVO.getOut_trade_no());
												rat.setStatus(0);
												rat.setMoney(generalRequest.getTenderMoney());
												rat.setBorrowId(generalRequest.getTenderBorrowId());
												rat.setType(generalRequest.getTenderType());
												rat.setUserId(userId);
												rat.setIsUseHikes(generalRequest.getIsUseHikes());
												rat.setAddtime(new Date());
												this.rechargeAndTenderService.insertSelective(rat);
											}
											return new MobileBaseResponse(rma);
										} else {
											return new MobileBaseResponse("code_fail", "获取验证码失败");
										}
									} else {
										return new MobileBaseResponse("money_fail", "充值金额必须大于0元");
									}
								} else {
									return new MobileBaseResponse("money_fail", "充值金额不能为空");
								}
							} else {
								return new MobileBaseResponse("card_fail", "快捷支付银行卡持有人与充值账户不是同一人,请联系客服");
							}
						} else {
							return new MobileBaseResponse("card_fail", "快捷支付银行卡持有人为空,请重新开通快捷支付。");
						}
					} else {
						return new MobileBaseResponse("card_fail", "快捷支付银行卡与新浪支付绑定失败无法支付,请重新开通快捷支付。");
					}
				} else {
					return new MobileBaseResponse("card_fail", "快捷支付银行卡有误请重新绑定快捷支付银行卡");
				}
			} else {
				return new MobileBaseResponse("card_fail", "请选择快捷支付银行卡");
			}
		} else {
			return new MobileBaseResponse("user_fail", "当前登录人信息获取失败");
		}
	}

	@RestDescription("充值推进")
	@CheckToken
	public MobileBaseResponse rechargeAdvance(GeneralRequestVO generalRequest) throws Exception {
		Integer userId = this.userTokenService.selectUserIdByToken(generalRequest.getToken());
		UserWithBLOBs user = this.userService.selectByPrimaryKey(userId);
		if (EmptyUtil.isNotEmpty(user) && user.getUserId() > 0) {
			String result = this.accountRechargeSinaService.checkRechargeByBindingPayAdvance(userId, generalRequest.getVerifCode(), generalRequest.getOutTradeNo());
			if (result.equals("success")) {
				result = this.accountRechargeSinaService.doUserRechargeByBindingPayAdvance(generalRequest.getVerifCode(), generalRequest.getTicket(), generalRequest.getOutTradeNo());
				// result =
				// this.accountRechargeSinaService.doUserRechargeByBindingPayAdvance(caRequest.getCaptcha(),
				// caRequest.getTicket(),
				// caRequest.getOutTradeNo(),caRequest.getChargeType());
				if (result.equals("success")) {
					RechargeAndTender rat = this.rechargeAndTenderService.selectByPrimaryKey(generalRequest.getOutTradeNo());
					if (rat != null) {
						rat.setStatus(4);
						this.rechargeAndTenderService.updateByPrimaryKeySelective(rat);
					}
					return new MobileBaseResponse("success", "提交成功,预计10分钟左右内到账");
				} else {
					return new MobileBaseResponse("advance_fail", result);
				}
			} else {
				return new MobileBaseResponse("advance_fail", result);
			}
		} else {
			return new MobileBaseResponse("user_fail", "当前登录人信息获取失败");
		}
	}

	public MobileBaseResponse chargeStatus(GeneralRequestVO generalRequest) throws Exception {
		if (generalRequest.getOutTradeNo() == null) {
			return new MobileBaseResponse("chargeStatus_fail", "参数有误");
		}
		RechargeAndTender rat = this.rechargeAndTenderService.selectByPrimaryKey(generalRequest.getOutTradeNo());
		if (rat != null) {
			if (rat.getStatus() == 4) {
				return new MobileBaseResponse("0", "等待新浪回调");
			} else if (rat.getStatus() > 10) {
				return new MobileBaseResponse("11", rat.getRemark());
			} else if (rat.getStatus() == 2) {
				return new MobileBaseResponse("22", "充值失败");
			} else if (rat.getStatus() == 1) {
				return new MobileBaseResponse("12", "充值成功,投资异常(原因:" + rat.getRemark() + ")");
			} else {
				return new MobileBaseResponse("22", "充值失败,错误代码" + rat.getStatus());
			}

		} else {
			AccountRecharge ar = this.accountRechargeService.selectByTradeNo(generalRequest.getOutTradeNo());
			if (ar == null) {
				return new MobileBaseResponse("chargeStatus_fail", "无此订单号");
			}
			if (ar.getStatus() == 4) {
				return new MobileBaseResponse("0", "等待新浪回调");
			} else if (ar.getStatus() == 1) {
				return new MobileBaseResponse("1", "充值成功");
			} else if (ar.getStatus() == 2) {
				return new MobileBaseResponse("2", "充值失败");
			} else {
				return new MobileBaseResponse("2", "充值失败,错误代码" + ar.getStatus());
			}
		}
	}
}
