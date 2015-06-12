package cn.vfunding.vfunding.prd.www.account.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.server.interceptors.NeedSession;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.StringUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.vfunding.biz.account.model.AccountBank;
import cn.vfunding.vfunding.biz.account.model.AccountCash;
import cn.vfunding.vfunding.biz.account.service.IAccountBankService;
import cn.vfunding.vfunding.biz.account.service.IAccountCashService;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.borrow.model.BorrowRepayment;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowRepaymentService;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.common.vo.AccountCashVO;
import cn.vfunding.vfunding.biz.icbcCard.model.IcbcCard;
import cn.vfunding.vfunding.biz.icbcCard.service.IIcbcCardService;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.service.IAccountCashSinaService;
import cn.vfunding.vfunding.biz.system.model.CashRule;
import cn.vfunding.vfunding.biz.system.service.ICashRuleService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserService;

@Controller
@RequestMapping("accountCash")
public class AccountCashController extends BaseController {

	@Autowired
	private IAccountCashService accountCashService;

	@Autowired
	private IAccountService accountService;

	@Autowired
	private IAccountBankService accountBankService;

	@Autowired
	private ICashRuleService cashRuleService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IAccountCashSinaService accountCashSinaService;
	@Autowired
	private IIcbcCardService icbcCardService;
	@Autowired
	private IBorrowRepaymentService borrowRepaymentService;

	/**
	 * 转向用户提现申请页面
	 * 
	 * @return 2014年5月9日 liuyijun
	 */
	@NeedSession(returnUrl = "/accountCash/takeCash")
	@RequestMapping(value = "/takeCash", method = RequestMethod.GET)
	public ModelAndView takeCash() {
		ModelAndView mv = ModelAndViewUtil.createModelAndViewAndAddAccountToView(accountService, "account/takeCash");
		List<AccountBank> banks = this.accountBankService.selectByUserId(UserSession.getLoginUserId());
		mv.addObject("canAdd", banks.size() < 3 ? true : false);
		List<AccountBank> bindBanks = this.accountBankService.selectByUserId(UserSession.getLoginUserId());
		if (EmptyUtil.isNotEmpty(bindBanks)) {
			AccountBank temp = null;
			for (AccountBank ab : bindBanks) {
				if (ab.getKtkjfs().equals("Y")) {
					temp = ab;
					mv.addObject("bind", ab);
					break;
				} else if (ab.getKtkjfs().equals("N")) {
					temp = ab;
					mv.addObject("bind", ab);
				}
			}
			IcbcCard ic = this.icbcCardService.selectByUserId(UserSession.getLoginUserId());
			if (ic != null && !StringUtils.isEmpty(ic.getIcbcCard()) && ic.getIcbcCard().equals(temp.getAccount())) {
				mv.addObject("isIcbcCard", "Y");
				mv.addObject("noFeeCount", ic.getNoFeeCount());
			}
		}
		BigDecimal hongbao = this.userService.getUserHongbao(UserSession.getLoginUserId());
		if (EmptyUtil.isNotEmpty(hongbao)) {
			mv.addObject("hongbao", hongbao.doubleValue() == 0.00 ? "0" : StringUtil.getTwoDecimals(hongbao));
			if (hongbao.compareTo(new BigDecimal(0)) > 0) {
				mv.addObject("canHongbao", true);
			}
		}

		return mv;
	}

	/**
	 * 用户提现手续费
	 * 
	 * @param takeMoney
	 * @return 2014年5月10日 liuyijun
	 */
	@NeedSession
	@RequestMapping(value = "/getCashFee", method = RequestMethod.POST)
	@ResponseBody
	public Json getCashFee(@RequestParam("takeMoney") BigDecimal takeMoney, @RequestParam(value = "hongbao", required = false) String hongbao,
			@RequestParam(value = "isIcbcCard", required = false) String isIcbcCard, HttpSession session) {
		Json j = new Json();
		CashRule rule = cashRuleService.selectOne(); // 放缓存
		BigDecimal userCashFee = this.accountCashService.userAdditionalCashFee(UserSession.getLoginUserId(), takeMoney);// 千三手续费
		BigDecimal fee = userCashFee.add(new BigDecimal("0"));// 手续费
		BigDecimal hongbaoResult = this.userService.getUserHongbao(UserSession.getLoginUserId());
		if (UserSession.getUserSession().getTypeId() == 40) {// 借款人不收千三
			fee = takeMoney.compareTo(new BigDecimal(rule.getCashLt())) > 0 ? new BigDecimal(rule.getEveryGtFee()) : new BigDecimal(rule.getEveryLtFee());
		} else {
			if(StringUtils.isEmpty(isIcbcCard) || !isIcbcCard.equals("Y")){
				if (takeMoney.compareTo(new BigDecimal(rule.getCashLt())) > 0) {
					fee = fee.add(new BigDecimal(rule.getEveryGtFee()));
				} else {
					fee = fee.add(new BigDecimal(rule.getEveryLtFee()));
				}
			}
		}
		if (StringUtils.isEmpty(isIcbcCard) || !isIcbcCard.equals("Y")) {
			BigDecimal real = takeMoney.subtract(fee);// 到账金额
			if (EmptyUtil.isNotEmpty(hongbao) && hongbao.equals("yes")) {
				if (hongbaoResult.compareTo(new BigDecimal(0)) > 0) {// 红包不为0
					if (takeMoney.compareTo(new BigDecimal(rule.getCashLt())) > 0) {
						if ((new BigDecimal(rule.getEveryGtFee()).compareTo(hongbaoResult) > 0)) {
							real = real.add(new BigDecimal(rule.getEveryGtFee()).subtract(hongbaoResult));
							hongbaoResult = new BigDecimal("0");
						} else {
							real = real.add(new BigDecimal(rule.getEveryGtFee()));
							hongbaoResult = hongbaoResult.subtract(new BigDecimal(rule.getEveryGtFee()));
						}
					} else {
						if ((new BigDecimal(rule.getEveryLtFee()).compareTo(hongbaoResult)) > 0) {
							real = real.add(hongbaoResult);
							hongbaoResult = new BigDecimal("0");
						} else {
							real = real.add(new BigDecimal(rule.getEveryLtFee()));
							hongbaoResult = hongbaoResult.subtract(new BigDecimal(rule.getEveryLtFee()));
						}
					}
				}
			}
			j.setSuccess(true);
			Map<String, String> result = new HashMap<String, String>();
			result.put("fee", new DecimalFormat("0.00").format(fee));
			BigDecimal zer = new BigDecimal(0);
			result.put("real", real.compareTo(zer) > 0 ? StringUtil.getTwoDecimals(real) : "0");
			result.put("hongbaoResult", hongbaoResult.doubleValue() == 0.00 ? "0" : StringUtil.getTwoDecimals(hongbaoResult));
			j.setObj(result);
			if (real.compareTo(zer) > 0) {
				session.setAttribute("takeMoney", takeMoney);
			}
		} else {
			j.setSuccess(true);
			Map<String, String> result = new HashMap<String, String>();
			result.put("fee", new DecimalFormat("0.00").format(fee));
			result.put("real", StringUtil.getTwoDecimals(takeMoney.subtract(fee)));
			result.put("hongbaoResult", hongbaoResult.doubleValue() == 0.00 ? "0" : StringUtil.getTwoDecimals(hongbaoResult));
			j.setObj(result);
		}

		return j;

	}

	/**
	 * 用户申请提现
	 * 
	 * @param request
	 * @param cashVO
	 * @param redirectAttributes
	 * @return 2014年5月12日 liuyijun
	 */
	@RequestMapping(value = "/applyCash")
	@ResponseBody
	public Json applyCash(HttpServletRequest request, AccountCashVO cashVO,String isIcbcCard) {
		Json j = new Json();
		Map<String, Object> map = new HashMap<String, Object>();
		UserSession user = UserSession.getUserSession();
		User u = userService.selectByPrimaryKey(user.getUserId());
		if (u != null && u.getIslock().equals(1)) {
			j.setMsg("您的账号已被锁定,不可提现,请联系客服.");
			return j;
		}
		if (StringUtils.isEmpty(u.getRealStatus()) || !u.getRealStatus().equals("1")) {
			j.setMsg("请实名认证后再进行提现.");
			return j;
		}
		if (EmptyUtil.isNotEmpty(user)) {
			CashRule rule = cashRuleService.selectOne(); // 放缓存
			// 单次50w，单日200w
			BigDecimal dayAll = accountCashService.selectByDayUserId(user.getUserId());
			if (u.getTypeId() != 40 && EmptyUtil.isNotEmpty(dayAll) && dayAll.doubleValue() > rule.getMaxCash().doubleValue()) {
				j.setMsg("提现超出当日累计总额,您还可以提现:" + new BigDecimal(rule.getMaxCash()).subtract(dayAll) + "元");
			} else {
				if (user.getPayPassword().equals(DigestUtils.md5Hex(cashVO.getPayPassword()))) {
					List<AccountBank> accountBanks = this.accountBankService.selectByUserId(UserSession.getLoginUserId());
					AccountBank accountBank = null;
					if (!accountBanks.isEmpty()) {
						for (AccountBank ab : accountBanks) {
							if (ab.getKtkjfs().equals("Y")) {
								accountBank = ab;
								break;
							} else {
								accountBank = ab;
							}
						}
					}
					if (accountBank != null) {
						cashVO.setBankNo(accountBank.getBank());
						cashVO.setBankNum(accountBank.getAccount());
						cashVO.setBranch(accountBank.getBranch());
						if (EmptyUtil.isNotEmpty(cashVO.getUseHongbao()) && cashVO.getUseHongbao().equals("yes")) {
							cashVO.setIsHongbao(new String[] { "1" });
						}
						cashVO.setIp(request.getRemoteAddr());
						cashVO.setStatus(0);// 提现待审
						map = accountCashService.updateApplyCash(user, cashVO);
						if (!map.get("result").toString().equals("申请提现成功")) {
							j.setMsg(map.get("result").toString());
						} else {
							// 根据条件判断是否立刻放款 TODO
							if (false) {
								AccountCash accountCash = (AccountCash) map.get("accountCash");
								cashVO.setStatus(1);
								cashVO.setIp(request.getRemoteAddr());
								cashVO.setRemark("正常用户无审核提现");
								j = this.takeCash(accountCash, cashVO, j, request);
							} else {
								if(!StringUtils.isEmpty(isIcbcCard) && isIcbcCard.equals("Y")){
									this.icbcCardService.addNoFeeCountOne(UserSession.getLoginUserId());
								}
								j.setMsg(map.get("result").toString());
							}
							request.getSession().removeAttribute("takeMoney");
							j.setSuccess(true);
						}
					} else {
						j.setMsg("您的取现银行卡信息错误");
					}
				} else {
					j.setMsg("提现密码错误");
				}
			}
		} else {
			j.setMsg("您已超时或未登录，请先登录");
		}
		return j;
	}

	/**
	 * 提现成功页面
	 * 
	 * @return 2014年5月12日 liuyijun
	 */
	@NeedSession
	@RequestMapping(value = "/takeCashSuccess", method = RequestMethod.GET)
	public ModelAndView tcSuccess() {
		ModelAndView mv = ModelAndViewUtil.createModelAndViewAndAddAccountToView(accountService, "account/takeCashSuccess");
		return mv;
	}

	/**
	 * 验证通过直接放款,不经过财务审核
	 * 
	 * @param accountCash
	 * @param cashVO
	 * @param request
	 * @return
	 */
	private Json takeCash(AccountCash accountCash, AccountCashVO cashVO, Json j, HttpServletRequest request) {
		// 对审核状态进行校验
		String result = null;
		accountCash.setVerifyUserid(UserSession.getLoginUserId());
		accountCash.setAddip(request.getRemoteAddr());
		try {
			// 推送新浪提现接口
			result = this.accountCashSinaService.doUserTakeCash(accountCash, cashVO);
			if (result.equals(SinaMemberParmUtil.success)) {
				accountCash.setStatus((byte) 4);
				this.accountCashService.updateByPrimaryKeySelective(accountCash);
				result = "该笔提现请求成功,等待新浪T+1日处理";
				j.setSuccess(true);
				j.setMsg(result);
			} else {
				j.setSuccess(false);
				result = "新浪接口数据推送失败!" + result;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
	
	@NeedSession(returnUrl = "/accountCash/getRepayTodayMoney")
	@RequestMapping("/getRepayTodayMoney")
	@ResponseBody
	public Json getRepayTodayMoney(){
		Json j = new Json();
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", UserSession.getLoginUserId().toString());
		map.put("days", "0");//0为今天
		List<BorrowRepayment> brs = this.borrowRepaymentService.selectTodayRepayByUserId(map);
		if(!brs.isEmpty()){
			DecimalFormat df = new DecimalFormat("0.00");
			j.setSuccess(true);
			BigDecimal money = new BigDecimal(0);
			for(BorrowRepayment br : brs){
				BigDecimal tempRepay = new BigDecimal(br.getRepaymentAccount());
				money = money.add(tempRepay);
			}
			j.setMsg("您今日还有"+df.format(money)+"元待还款，建议还款后再提现!");
		}
		return j;
	}
}
