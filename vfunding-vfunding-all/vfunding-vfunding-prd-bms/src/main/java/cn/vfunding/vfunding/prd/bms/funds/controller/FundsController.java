package cn.vfunding.vfunding.prd.bms.funds.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.EmployeeSession;
import cn.vfunding.common.framework.server.interceptors.NeedLogger;
import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.kaptcha.VerifyCodeUtils;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.model.AccountCash;
import cn.vfunding.vfunding.biz.account.model.AccountRecharge;
import cn.vfunding.vfunding.biz.account.model.CashNumber;
import cn.vfunding.vfunding.biz.account.service.IAccountCashService;
import cn.vfunding.vfunding.biz.account.service.IAccountRechargeService;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.borrow.model.BorrowRepayment;
import cn.vfunding.vfunding.biz.borrow.model.BorrowTender;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowRepaymentService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.common.vo.AccountCashVO;
import cn.vfunding.vfunding.biz.common.vo.AccountRechargeVO;
import cn.vfunding.vfunding.biz.common.vo.AccountSystemVO;
import cn.vfunding.vfunding.biz.common.vo.SearchVO;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.service.IAccountCashSinaService;
import cn.vfunding.vfunding.biz.sina.service.IHostingCollectTradeSinaService;
import cn.vfunding.vfunding.biz.sina.service.IQuerySinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendLogService;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateHostingCollectTradeSendVO;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.common.module.activemq.message.SendVerifyCodeMessage;
import cn.vfunding.vfunding.prd.bms.utils.UserAuthFilter;

/**
 * 财务
 */
@Controller
@RequestMapping(value = { "/system/funds", "/funds" })
public class FundsController {

	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("sinaSendActionLog");
	@Autowired
	private IAccountCashService accountCashService;
	@Autowired
	private IAccountRechargeService accountRechargeService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IAccountCashSinaService accountCashSinaService;
	// 新浪推送日志
	@Autowired
	private ISinaSendLogService sinaSendLogService;
	@Autowired
	private IHostingCollectTradeSinaService hostingCollectTradeSinaService;
	@Autowired
	private IBorrowRepaymentService borrowRepaymentService;
	@Autowired
	private JmsSenderService jmsSenderUtil;
	@Autowired
	private IQuerySinaService querySinaService;
	/**
	 * 提现待审 页面
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/waitApplyCashListPage")
	public ModelAndView waitApplyCashListPage() {
		ModelAndView mv = new ModelAndView("webpage/funds/waitApplyCashList");
		boolean canEdit = UserAuthFilter.isPass("/system/funds/takeCash");
		if (canEdit) {
			mv.addObject("canEdit", canEdit);
		}
		boolean canAdd = UserAuthFilter.isPass("/system/funds/addBankNumber");
		if (canAdd) {
			mv.addObject("canAdd", canAdd);
		}
		boolean canUpdate = UserAuthFilter.isPass("/system/funds/updateBankNumber");
		if (canUpdate) {
			mv.addObject("canUpdate", canUpdate);
		}
		return mv;
	}

	/**
	 * 成功提现页面
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/succCashListPage")
	public ModelAndView succCashListPage() {
		ModelAndView mv = new ModelAndView("webpage/funds/succCashList");
		return mv;
	}

	/**
	 * 失败提现页面
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/failureCashListPage")
	public ModelAndView failureCashList() {
		ModelAndView mv = new ModelAndView("webpage/funds/failureCashList");
		return mv;
	}

	/**
	 * 提现详情页面
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/cashDialog")
	public ModelAndView cashDialog() {
		ModelAndView mv = new ModelAndView("webpage/funds/cashDialog");
		return mv;
	}

	/**
	 * 提现待审列表
	 * 
	 * @return
	 * @author liuhuan
	 * @throws Exception
	 */
	@ParentSecurity("/system/funds/waitApplyCashListPage")
	@RequestMapping("/applyCashList")
	@ResponseBody
	public PageResult<AccountCashVO> applyCashList(PageSearch pageSearch, SearchVO search) throws Exception {
		PageResult<AccountCashVO> results = new PageResult<AccountCashVO>();
		// 时间转换
		ModelAndViewUtil.addSearchTimeVO(search);
		pageSearch.setEntity(search);
		List<AccountCashVO> cashList = accountCashService.selectWaitApplyListPage(pageSearch);
		results.setRows(cashList);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}

	/**
	 * 添加财务汇款记录
	 * 
	 * @return
	 * @author lilei
	 */
	@NeedLogger(desc = "添加财务汇款记录")
	@RequestMapping("/addBankNumber")
	@ResponseBody
	public Json addBankNumber(CashNumber cashNumber) {
		Json j = new Json();
		cashNumber.setAddUserid(EmployeeSession.getEmpSessionEmpId());
		int i = accountCashService.insertCashNumber(cashNumber);
		j.setSuccess(i > 0 ? true : false);
		return j;
	}

	/**
	 * 更新财务汇款记录
	 * 
	 * @return
	 * @author lilei
	 */
	@NeedLogger(desc = "财务汇款记录")
	@RequestMapping("/updateBankNumber")
	@ResponseBody
	public Json updateBankNumber(CashNumber cashNumber) {
		Json j = new Json();
		cashNumber.setAddUserid(EmployeeSession.getEmpSessionEmpId());
		int i = accountCashService.updateCashNumber(cashNumber);
		j.setSuccess(i > 0 ? true : false);
		return j;
	}

	/**
	 * 提现详情
	 * 
	 * @author liuhuan
	 */
	@ParentSecurity("/system/funds/waitApplyCashListPage")
	@RequestMapping("/cashDetail")
	@ResponseBody
	public ModelAndView cashDetail(@RequestParam("id") int id) {
		ModelAndView mv = new ModelAndView("webpage/funds/cashDetail");
		PageSearch pageSearch = new PageSearch();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cashId", id);
		pageSearch.setEntity(map);
		AccountCashVO cashList = null;
		try {
			cashList = accountCashService.selectWaitApplyListPage(pageSearch).get(0);
		} catch (Exception e) {
		}
		mv.addObject("cashList", cashList);
		if (cashList.getTypeId().equals("40")) {
			mv.addObject("repayUser", "Y");
			AccountCash ac = accountCashService.selectByPrimaryKey(cashList.getCashId());
			mv.addObject("repayMoneyToday", this.getRepayMoney(ac.getUserId(), "0")); // 借款用户今日待还款
			mv.addObject("repayMoneyTomorrow", this.getRepayMoney(ac.getUserId(), "1"));// 借款用户明日待还款
			mv.addObject("repayUserUseMoney", this.accountService.selectByUserId(ac.getUserId()).getUseMoney());
		}
		return mv;
	}

	/**
	 * 
	 * @param userId
	 * @param days
	 *            0当天 正数加几天 负数减几天
	 * @return
	 * 
	 *         jianwei
	 */
	public String getRepayMoney(Integer userId, String days) {
		Map<String, String> moneyMap = new HashMap<String, String>();
		moneyMap.put("userId", userId.toString());
		moneyMap.put("days", days);// 0为今天
		List<BorrowRepayment> brs = this.borrowRepaymentService.selectTodayRepayByUserId(moneyMap);
		DecimalFormat df = new DecimalFormat("0.00");
		BigDecimal rePayMoney = new BigDecimal(0);
		if (!brs.isEmpty()) {
			for (BorrowRepayment br : brs) {
				BigDecimal tempRepay = new BigDecimal(br.getRepaymentAccount());
				rePayMoney = rePayMoney.add(tempRepay);
			}
		}
		return df.format(rePayMoney);
	}

	/**
	 * 财务打款单号详情
	 * 
	 * @author lilei
	 */
	@ParentSecurity("/system/funds/waitApplyCashListPage")
	@RequestMapping("/cashNumberDetail")
	@ResponseBody
	public ModelAndView cashNumberDetail(@RequestParam("id") int id) {
		ModelAndView mv = new ModelAndView("webpage/funds/cashNumberDetail");
		CashNumber cashNumber = accountCashService.selectCashNumberByCashId(id);
		PageSearch pageSearch = new PageSearch();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cashId", id);
		pageSearch.setEntity(map);
		AccountCashVO cashList = null;
		cashList = accountCashService.selectWaitApplyListPage(pageSearch).get(0);
		mv.addObject("cashList", cashList);
		mv.addObject("cashNumber", cashNumber);
		return mv;
	}

	/**
	 * @Description:财务提现放款
	 * @return
	 * @author liuhuan
	 */
	@NeedLogger(desc = "财务提现放款")
	@RequestMapping(value = "/takeCash")
	@ResponseBody
	public Json takeCash(HttpServletRequest request, AccountCashVO cashVO) {
		logger.info("*****[sina 提现 财务审核开始]:[accountCashId:" + cashVO.getCashId() + "]");
		Json j = new Json();
		String result = null;
		String msgResult = null;
		AccountCash accountCash = accountCashService.selectByPrimaryKey(cashVO.getCashId());
		// 对审核状态进行校验
		if (accountCash.getStatus() != 0) {
			msgResult = "请勿重复审核";
		} else if (accountCash == null || EmptyUtil.isEmpty(cashVO.getRemark()) || EmptyUtil.isEmpty(cashVO.getStatus())) {
			msgResult = "数据异常，提现失败";
		} else if(!this.canSinaBalance(accountCash.getUserId(),accountCash.getTotal())){
			msgResult = "该用户新浪账户余额不足,无法提现,请联系技术部!";
		}else {
			accountCash.setVerifyUserid(UserSession.getLoginUserId());
			accountCash.setAddip(request.getRemoteAddr());
			if (cashVO.getStatus().equals(2)) {// 审核拒绝情况直接执行本地数据操作
				logger.info("[sina 提现 审核拒绝开始]:[accountCashId:" + cashVO.getCashId() + ",userId:" + accountCash.getUserId() + "]");
				String updateResult = accountCashService.updateTakeCash(accountCash, cashVO);
				logger.info("[sina 提现 审核拒绝结束]:[accountCashId:" + cashVO.getCashId() + ",userId:" + accountCash.getUserId() + "]");
				if (updateResult.equals("1")) {
					j.setSuccess(true);
				}
				UserWithBLOBs takeUser = this.userService.selectByPrimaryKey(accountCash.getUserId());
				if (EmptyUtil.isNotEmpty(takeUser) && EmptyUtil.isNotEmpty(takeUser.getPhone())) {
					/**
					 * 发送提现失败短信
					 */
					SendVerifyCodeMessage message = new SendVerifyCodeMessage();
					message.setContent("小伙伴，您的提现申请失败，如有疑虑请详询400-991-9999");
					message.setMobile(takeUser.getPhone());
					// this.jmsSender.sendAsynchronousMessage(message);
					this.jmsSenderUtil.asynSendSystemJms(message);
				}
			} else {
				logger.info("[sina 提现 修改状态为处理中开始]:[accountCashId:" + cashVO.getCashId() + ",userId:" + accountCash.getUserId() + "]");
				accountCash.setStatus((byte) 5);// 处理中状态
				this.accountCashService.updateByPrimaryKeySelective(accountCash);
				logger.info("[sina 提现 修改状态为处理中结束]:[accountCashId:" + cashVO.getCashId() + ",userId:" + accountCash.getUserId() + "]");
				logger.info("[sina 提现 审核通过开始]:[accountCashId:" + cashVO.getCashId() + ",userId:" + accountCash.getUserId() + "]");
				byte oldStatus = (byte) 0;
				// 推送新浪提现接口
				try {
					logger.info("[sina 提现 推送新浪接口开始]:[accountCashId:" + cashVO.getCashId() + ",userId:" + accountCash.getUserId() + "]");
					result = this.accountCashSinaService.doUserTakeCash(accountCash, cashVO);
					logger.info("[sina 提现 推送新浪接口结束]:[accountCashId:" + cashVO.getCashId() + ",userId:" + accountCash.getUserId() + "]");
				} catch (Exception e) {
					logger.error("[sina 提现 推送新浪接口 异常]:[accountCashId:" + cashVO.getCashId() + ",userId:" + accountCash.getUserId() + "]:[异常内容:" + e.getMessage() + "]");
					e.printStackTrace();
					logger.info("*****[sina 提现 财务审核结束]:[accountCashId:" + cashVO.getCashId() + "]");
					return j;
				}
				if (result.equals(SinaMemberParmUtil.success)) {
					logger.info("[sina 提现 接口推送成功]:[accountCashId:" + cashVO.getCashId() + ",userId:" + accountCash.getUserId() + "]");
					logger.info("[sina 提现 更新本地状态]:[accountCashId:" + cashVO.getCashId() + ",userId:" + accountCash.getUserId() + "]:[status:4]");
					accountCash.setStatus((byte) 4);// 待新浪处理状态
					this.accountCashService.updateByPrimaryKeySelective(accountCash);
					logger.info("[sina 提现 本地状态更新完毕]:[accountCashId:" + cashVO.getCashId() + ",userId:" + accountCash.getUserId() + "]:[status:4]");
					User u = userService.selectByPrimaryKey(accountCash.getUserId());
					BigDecimal credited = accountCash.getCredited();
					BigDecimal fee = accountCash.getTotal().subtract(credited);// 手续费
					if (fee.doubleValue() > 0) { // 如果有手续费则转账给微积金
						logger.info("[sina 提现 手续费不为0,开始处理手续费待收]:[accountCashId:" + cashVO.getCashId() + ",userId:" + accountCash.getUserId() + "]:[fee:" + fee.doubleValue() + "]");
						CreateHostingCollectTradeSendVO ccts = new CreateHostingCollectTradeSendVO();// 托管代收
						ccts.setOut_trade_no(new Date().getTime() + "" + accountCash.getUserId());
						ccts.setOut_trade_code(SinaMemberParmUtil.OutTradeCode._1001);
						ccts.setSummary("提现手续费");
						ccts.setPayer_id(accountCash.getUserId().toString());
						ccts.setPayer_identity_type(SinaMemberParmUtil.IdentityType.UID);
						// ccts.setPayer_ip("127.0.0.1");
						if (!u.getTypeId().equals(40)) {// 如果提现用户是投资人(存钱罐账户),则不能直接使用转账功能
							ccts.setPay_method("balance^" + fee.doubleValue() + "^SAVING_POT");
						} else {
							ccts.setPay_method("balance^" + fee.doubleValue() + "^BASIC");
						}
						try {
							logger.info("[sina 提现 推送新浪待收手续费开始]:[accountCashId:" + cashVO.getCashId() + ",userId:" + accountCash.getUserId() + "]:[fee:" + fee.doubleValue() + "]");
							hostingCollectTradeSinaService.sinaSend(ccts, "提现手续费");
							logger.info("[sina 提现 推送新浪待收手续费结束]:[accountCashId:" + cashVO.getCashId() + ",userId:" + accountCash.getUserId() + "]:[fee:" + fee.doubleValue() + "]");
						} catch (Exception e) {
							logger.error("[sina 提现 推送新浪待收手续费 异常]:[accountCashId:" + cashVO.getCashId() + ",userId:" + accountCash.getUserId() + "]:[fee:" + fee.doubleValue() + "]:[异常内容:"
									+ e.getMessage() + "]");
							e.printStackTrace();
							logger.info("*****[sina 提现 财务审核结束]:[accountCashId:" + cashVO.getCashId() + "]");
							return j;
						}
					}
					msgResult = "新浪接口该笔提现请求成功,等待新浪T+1日处理";
				} else {
					accountCash.setStatus(oldStatus);
					this.accountCashService.updateByPrimaryKeySelective(accountCash);
					j.setSuccess(false);
					msgResult = "新浪接口数据推送失败!" + result;
				}
			}
		}
		j.setMsg(msgResult);
		logger.info("*****[sina 提现 财务审核结束]:[accountCashId:" + cashVO.getCashId() + "]");
		return j;
	}

	/**
	 * 新浪余额是否足够
	 * 
	 * @param repaymentId
	 * @return
	 * @author louchen 2015-04-02
	 */
	private synchronized Boolean canSinaBalance(Integer userId, BigDecimal money) {
		Boolean result = false;
		Map<String, String> map = null;
		try {
			map = querySinaService.doQueryBalance(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (map != null && map.get("success").equals("success") && !map.get("balance").equals("null")) {
			BigDecimal sinaBalance = new BigDecimal(map.get("balance"));
			if (sinaBalance.compareTo(money) >= 0) {
				result = true;
			}
		}
		return result;
	}

	/************************* 充值 *********************************/

	@RequestMapping(value = "/waitApplyRechargeListPage")
	public ModelAndView waitApplyRechargeListPage() {
		ModelAndView mv = new ModelAndView("webpage/funds/waitApplyRechargeList");
		boolean canEdit = UserAuthFilter.isPass("/system/funds/checkRechargeOffline");
		if (canEdit) {
			mv.addObject("canEdit", canEdit);
		}
		boolean canAdd = UserAuthFilter.isPass("/system/funds/addRecharge");
		if (canAdd) {
			mv.addObject("canAdd", canAdd);
		}
		return mv;
	}

	/**
	 * 充值列表 页面
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/rechargeList")
	public ModelAndView rechargeList() {
		ModelAndView mv = new ModelAndView("webpage/funds/rechargeList");
		return mv;
	}

	/**
	 * 添加后台线下充值页面
	 * 
	 * @author liuhuan
	 */
	@ParentSecurity("/system/funds/waitApplyRechargeListPage")
	@RequestMapping(value = "/addRechargePage")
	public ModelAndView addRechargePage() {
		ModelAndView mv = new ModelAndView("webpage/funds/addRecharge");
		return mv;
	}

	/**
	 * 充值列表
	 * 
	 * @author liuhuan
	 * @throws Exception
	 */
	@ParentSecurity("/system/funds/waitApplyRechargeListPage")
	@RequestMapping("/applyRechargeList")
	@ResponseBody
	public PageResult<AccountRechargeVO> applyRechargeList(PageSearch pageSearch, SearchVO search) throws Exception {
		PageResult<AccountRechargeVO> results = new PageResult<AccountRechargeVO>();
		if (EmptyUtil.isNotEmpty(search.getType())) {
			search.setType((search.getStatus() != null && search.getStatus() == 0) ? 2 : search.getType()); // 线下待审参数
		}
		// 时间转换
		ModelAndViewUtil.addSearchTimeVO(search);
		pageSearch.setEntity(search);
		List<AccountRechargeVO> rechargeList = accountRechargeService.selectWaitApplyListPage(pageSearch);
		results.setRows(rechargeList);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}

	/**
	 * 充值详情
	 * 
	 * @author liuhuan
	 */
	@ParentSecurity("/system/funds/waitApplyRechargeListPage")
	@RequestMapping("/rechargeDetail")
	public ModelAndView rechargeDetail(@RequestParam("id") int id) {
		ModelAndView mv = new ModelAndView("webpage/funds/rechargeDetail");
		PageSearch pageSearch = new PageSearch();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rechargeId", id);
		pageSearch.setEntity(map);
		AccountRechargeVO rechargeList = null;
		try {
			rechargeList = accountRechargeService.selectWaitApplyListPage(pageSearch).get(0);
		} catch (Exception e) {
		}
		mv.addObject("rechargeList", rechargeList);
		return mv;
	}

	/**
	 * 添加线下充值
	 * 
	 * @author liuhuan
	 */
	@NeedLogger(desc = "添加线下充值")
	@RequestMapping("/addRecharge")
	@ResponseBody
	public Json addRecharge(AccountRecharge recharge, @RequestParam("username") String username, @RequestParam("code") String code, HttpServletRequest request, HttpSession httpSession) {
		Json j = new Json();
		String result = "";
		if (!VerifyCodeUtils.check(httpSession, code)) {
			result = "验证码错误";
		} else if (EmptyUtil.isEmpty(username)) {
			result = "用户不能为空";
		} else if (EmptyUtil.isEmpty(recharge.getMoney())) {
			result = "充值金额不能为空";
		} else if (EmptyUtil.isEmpty(recharge.getRemark())) {
			result = "备注不能为空";
		} else {
			User user = userService.selectByUserName(username);
			if (EmptyUtil.isEmpty(user)) {
				j.setMsg("用户不存在,充值无效");
				return j;
			}
			recharge.setUserId(user.getUserId());
			recharge.setStatus((byte) 0); // 线下充值待审 0 & type=2
			recharge.setAddip(request.getRemoteAddr());
			recharge.setAddtime(Integer.parseInt(DateUtil.getTime()));
			recharge.setType((byte) 2);// 线下充值
			recharge.setTradeNo(recharge.getRemark());// 备注为充值银行流水号
			int i = this.accountRechargeService.insertSelectiveForOffLine(recharge);
			if (i > 0) {
				j.setSuccess(true);
				result = "提交成功";
			} else {
				result = "提交失败";
			}
		}
		j.setMsg(result);
		return j;
	}

	/**
	 * 审核线下充值
	 * 
	 * @author liuhuan
	 */
	@NeedLogger(desc = "审核线下充值")
	@RequestMapping("/checkRechargeOffline")
	@ResponseBody
	public Json checkRechargeOffline(AccountRecharge recharge, String msg, HttpServletRequest request) {
		Json j = new Json();
		AccountRecharge accountRecharge = accountRechargeService.selectByPrimaryKey(recharge.getId());
		if (EmptyUtil.isEmpty(recharge.getStatus()) || EmptyUtil.isEmpty(recharge.getRemark()) || EmptyUtil.isEmpty(accountRecharge)) {
			j.setMsg("数据异常，审核失败");
			return j;
		} else if (accountRecharge.getStatus() != 0) {
			j.setMsg("请勿重复审核");
			return j;
		} else {
			accountRecharge.setVerifyTime(Integer.parseInt(DateUtil.getTime()));
			accountRecharge.setVerifyUserid(EmployeeSession.getEmpSessionEmpId());
			recharge.setRemark(recharge.getRemark() + (EmptyUtil.isNotEmpty(msg) ? ";支付宝流水号[" + msg + "]" : ""));
			accountRecharge.setVerifyRemark(recharge.getRemark());
			accountRecharge.setStatus(recharge.getStatus());// 后台传入值：1通过；0不通过
			int i = accountRechargeService.updateCheckRechargeOffline(accountRecharge, request.getRemoteAddr());
			if (i > 0) {
				j.setMsg("审核成功");
				j.setSuccess(true);
			} else {
				j.setMsg("审核失败");
			}
		}
		return j;
	}

	/**
	 * 验证码
	 */
	@ParentSecurity("/system/funds/waitApplyRechargeListPage")
	@RequestMapping(value = "/getGenImage/{width}/{height}", method = RequestMethod.GET)
	public void getGenImage(@PathVariable("width") Integer width, @PathVariable("height") Integer height, HttpSession httpSession, HttpServletResponse response) throws Exception {
		response.addHeader("Content-Control", "post-check=0, pre-check=0");
		response.addHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Content-Type", "image/jpg");
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4, httpSession);
		VerifyCodeUtils.outputImage(width, height, response.getOutputStream(), verifyCode);
	}

	/**
	 * 转向后台账户列表页面
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/accountSystemListPage")
	public ModelAndView accountSystemListPage() {
		ModelAndView mv = new ModelAndView("webpage/funds/accountSystemList");
		return mv;
	}

	/**
	 * 后台账户列表
	 * 
	 * @author liuhuan
	 */
	@ParentSecurity("/system/funds/accountSystemListPage")
	@RequestMapping("/accountSystemList")
	@ResponseBody
	public PageResult<AccountSystemVO> accountSystemList(PageSearch pageSearch, SearchVO search) {
		PageResult<AccountSystemVO> results = new PageResult<AccountSystemVO>();
		pageSearch.setEntity(search);
		List<AccountSystemVO> accountList = accountService.selectAccountSystemListPage(pageSearch);
		results.setRows(accountList);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}

	@Autowired
	private IBorrowTenderService borrowTenderService;

	/**
	 * 标的投资人集合
	 * 
	 * @author liuhuan
	 */
	@ParentSecurity(value = "/system/borrow/repayment/allRepays")
	@RequestMapping("/borrowTenders")
	public ModelAndView borrowTenders(PageSearch pageSearch, Integer id) {
		ModelAndView mv = new ModelAndView("webpage/borrow/borrowTenders");
		List<BorrowTender> tenderList = this.borrowTenderService.selectTenderByBorrowId(id);
		mv.addObject("tenderList", tenderList);
		return mv;
	}
}
