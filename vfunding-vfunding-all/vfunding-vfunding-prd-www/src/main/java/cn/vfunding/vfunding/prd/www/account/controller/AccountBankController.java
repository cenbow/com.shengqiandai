package cn.vfunding.vfunding.prd.www.account.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.server.interceptors.NeedSession;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.http.MultipartEntityBuilderUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.vfunding.biz.account.model.AccountBank;
import cn.vfunding.vfunding.biz.account.service.IAccountBankService;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.common.vo.MessageVO;
import cn.vfunding.vfunding.biz.icbcCard.model.IcbcCard;
import cn.vfunding.vfunding.biz.icbcCard.service.IIcbcCardService;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.model.SinaBank;
import cn.vfunding.vfunding.biz.sina.model.SinaCard;
import cn.vfunding.vfunding.biz.sina.model.SinaDic;
import cn.vfunding.vfunding.biz.sina.service.IBindingBankCardAdvanceSinaService;
import cn.vfunding.vfunding.biz.sina.service.IBindingBankCardSinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaBankService;
import cn.vfunding.vfunding.biz.sina.service.ISinaCardService;
import cn.vfunding.vfunding.biz.sina.service.ISinaDicService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendService;
import cn.vfunding.vfunding.biz.sina.service.IUnbindingBankCardSinaService;
import cn.vfunding.vfunding.biz.sina.vo.SinaBankLimitVO;
import cn.vfunding.vfunding.biz.sina.vo.returns.BindingBankCardReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.returns.QueryBalanceReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.BindingBankCardAdvanceSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.BindingBankCardSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.QueryBalanceSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.UnbindingBankCardSendVO;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.common.sendconfig.ISendConfigUtil;

import com.alibaba.druid.util.StringUtils;

/**
 * 用户银行卡相关接口
 * 
 * @author liuyijun
 * 
 */
@Controller()
@RequestMapping("/accountBank")
public class AccountBankController extends BaseController {
	@Autowired
	private IAccountBankService accountBankService;
	@Autowired
	private IUserService userService;

	@Autowired
	private IAccountService accountService;
	@Autowired
	private IBindingBankCardSinaService bindingBankCardSinaService;
	@Autowired
	private IUnbindingBankCardSinaService unbindingBankCardSinaService;
	@Autowired
	private IBindingBankCardAdvanceSinaService bindingBankCardAdvanceSinaService;

	@Autowired
	private ISinaCardService sinaCardService;
	@Autowired
	private ISinaDicService sinaDicService;
	@Autowired
	private ISinaSendService sinaSendService;

	@Autowired
	private ISinaBankService sinaBankService;

	@Autowired
	private IIcbcCardService icbcCardService;

	@Autowired
	private JmsSenderService jmsSenderUtil;

	/**
	 * 检验银行卡是否存在
	 * 
	 * @param bankCard
	 * @return true 表示不存在 2014年5月8日 liuyijun
	 */
	@RequestMapping("/checkBankCard")
	@ResponseBody
	public boolean checkBankCard(@RequestParam("bankCard") String bankCard) {
		List<AccountBank> banks = this.accountBankService.selectByAccount(bankCard);
		if (EmptyUtil.isEmpty(banks)) {
			return true;
		}
		return false;
	}

	/**
	 * 转向添加银行卡
	 * 
	 * @return 2014年5月8日 liuyijun
	 */
	@NeedSession(returnUrl = "/accountBank/bank")
	@RequestMapping(value = "/bank", method = RequestMethod.GET)
	public ModelAndView bank() {
		ModelAndView mv = ModelAndViewUtil.createModelAndViewAndAddAccountToView(accountService, "account/addBank");
		List<AccountBank> banks = this.accountBankService.selectByUserId(UserSession.getLoginUserId());
		IcbcCard ic = icbcCardService.selectByUserId(UserSession.getLoginUserId());
		int kjcount = 0;
		for (AccountBank ab : banks) {
			if (ab.getKtkjfs().equals("Y")) {
				kjcount = 1;
				break;
			}
		}
		mv.addObject("icbcCard", ic);
		if (ic != null) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("account", ic.getIcbcCard());
			map.put("userId", UserSession.getLoginUserId().toString());
			mv.addObject("accountBankIcbc", this.accountBankService.selectByAccountNew(map));
		}
		mv.addObject("kjcount", kjcount);
		mv.addObject("banks", banks);
		mv.addObject("canAdd", banks.size() < 3 ? true : false);

		return mv;
	}

	/**
	 * 添加银行卡
	 * 
	 * @param accountBank
	 * @return 2014年5月8日 liuyijun
	 */
	@NeedSession(returnUrl = "/accountBank/addBank")
	@RequestMapping(value = "/addBank", method = RequestMethod.POST)
	public ModelAndView addBank(AccountBank accountBank, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/accountBank/bank");
		accountBank.setUserId(UserSession.getLoginUserId());
		accountBank.setAddip(ModelAndViewUtil.getIpAddr(request));
		this.accountBankService.insertSelective(accountBank);
		return mv;
	}

	/**
	 * 提现页面添加银行卡
	 * 
	 * @param accountBank
	 * @return 2014年5月8日 liuyijun
	 */
	@NeedSession(returnUrl = "/accountBank/appendBank")
	@RequestMapping(value = "/appendBank", method = RequestMethod.POST)
	public ModelAndView appendBank(AccountBank accountBank) {
		ModelAndView mv = new ModelAndView("redirect:/accountCash/takeCash");
		accountBank.setUserId(UserSession.getLoginUserId());
		this.accountBankService.addAndBind(accountBank);
		return mv;
	}

	/**
	 * 绑定银行卡
	 * 
	 * @param id
	 * @return 2014年5月8日 liuyijun
	 */
	@NeedSession(returnUrl = "/accountBank/bindBank")
	@RequestMapping(value = "/bindBank", method = RequestMethod.POST)
	public ModelAndView bindBank(@RequestParam("id") Integer id) {
		ModelAndView mv = new ModelAndView("redirect:/accountBank/bank");
		AccountBank accountBank = this.accountBankService.selectByPrimaryKey(id);
		if (EmptyUtil.isNotEmpty(accountBank)) {
			if (accountBank.getUserId().intValue() == UserSession.getLoginUserId().intValue()) {
				if (EmptyUtil.isEmpty(accountBank.getBind()) || accountBank.getBind() != 1) {
					List<AccountBank> abs = accountBankService.selectByUserId(accountBank.getUserId());
					for (AccountBank ab : abs) {// 解除之前绑定银行卡
						if (ab.getBind() == 1) {
							ab.setBind(0);
							accountBankService.updateByPrimaryKeySelective(ab);
						}
					}
					// 重新绑定银行卡
					accountBank.setBind(1);
					this.accountBankService.bindBankCard(accountBank);
				}
			}
		}
		return mv;
	}

	/**
	 * 删除银行卡
	 * 
	 * @param id
	 * @return 2014年5月8日 liuyijun
	 */
	@NeedSession(returnUrl = "/accountBank/deleteBank")
	@RequestMapping(value = "/deleteBank", method = RequestMethod.POST)
	public ModelAndView deleteBank(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("redirect:/accountBank/bank");
		AccountBank accountBank = this.accountBankService.selectByPrimaryKey(id);
		if (EmptyUtil.isNotEmpty(accountBank)) {
			if (accountBank.getUserId().equals(UserSession.getLoginUserId())) {
				// 删除银行卡后解绑sina方的银行卡,并将本地储存的sina银行卡信息状态修改为解绑
				SinaCard sc = new SinaCard();
				sc.setCardId(accountBank.getAccount());
				sc.setStatus("0");
				sc.setUserId(UserSession.getLoginUserId());
				List<SinaCard> scs = sinaCardService.selectSelective(sc);
				// 验证余额
				if (!scs.isEmpty()) {
					SinaCard s = scs.get(0);
					if (s.getIsVerified().equals("Y")) {
						QueryBalanceSendVO qbs = new QueryBalanceSendVO();
						qbs.setIdentity_id(UserSession.getLoginUserId().toString());
						qbs.setIdentity_type(SinaMemberParmUtil.IdenType.UID);
						if (UserSession.getUserSession().getTypeId().equals(40)) {
							qbs.setAccount_type(SinaMemberParmUtil.AccountType.BASIC);
						} else {
							qbs.setAccount_type(SinaMemberParmUtil.AccountType.SAVING_POT);
						}
						try {
							QueryBalanceReturnVO qbr = sinaSendService.sinaSendMgs(qbs, QueryBalanceReturnVO.class);
							if (Double.parseDouble(qbr.getBalance()) > 0) {
								redirectAttributes.addFlashAttribute("noDelete", "Y");
								return mv;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				if (!scs.isEmpty()) {
					sc = scs.get(0);
					if (StringUtils.isEmpty(sc.getSinaCard())) {
						this.accountBankService.deleteByPrimaryKey(id);
						sc.setStatus("1");
						sinaCardService.updateByPrimaryKeySelective(sc);
					} else {
						UnbindingBankCardSendVO ubcs = new UnbindingBankCardSendVO();
						ubcs.setCard_id(sc.getSinaCard());// 新浪银行卡编号
						ubcs.setIdentity_id(UserSession.getLoginUserId().toString());// 用户ID
						ubcs.setIdentity_type(SinaMemberParmUtil.IdenType.UID);
						try {
							String result = unbindingBankCardSinaService.doUnbindingBank(ubcs);
							if (result.equals(SinaMemberParmUtil.success)) {
								this.accountBankService.deleteByPrimaryKey(id);
								sc.setStatus("1");
								sinaCardService.updateByPrimaryKeySelective(sc);
							} else {
								redirectAttributes.addFlashAttribute("unbindMsg", result);
							}
						} catch (Exception e) {
							e.printStackTrace();
							return mv;
						}
					}
				} else {
					this.accountBankService.deleteByPrimaryKey(id);
				}
			}
		}
		return mv;
	}

	/**
	 * 新浪绑定银行卡
	 * 
	 * @return
	 * @author jianwei
	 */
	@NeedSession(returnUrl = "/accountBank/sinaBindingBank")
	@RequestMapping(value = "/sinaBindingBank")
	@ResponseBody
	public Json sinaBindingBank(AccountBank accountBank, HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		Json j = new Json();
		j.setSuccess(false);
		try {
			BindingBankCardSendVO bbcs = new BindingBankCardSendVO();
			bbcs.setBank_account_no(accountBank.getAccount());// 银行卡号
			bbcs.setBank_code(accountBank.getBank());// 银行编号 ICBC等
			bbcs.setCard_attribute(SinaMemberParmUtil.CardParam.C);// 银行卡属性
																	// C 对私
			bbcs.setCard_type(SinaMemberParmUtil.CardType.DEBIT);// 卡类型 借记卡
			bbcs.setCity(accountBank.getCityName());// 城市
			bbcs.setIdentity_id(UserSession.getLoginUserId().toString());// userID
			bbcs.setIdentity_type(SinaMemberParmUtil.IdentityType.UID);
			bbcs.setProvince(accountBank.getProvinceName());// 省会
			bbcs.setRequest_no(new Date().getTime() + "" + UserSession.getLoginUserId());// 订单号
			if (!StringUtils.isEmpty(accountBank.getKtkjfs()) && accountBank.getKtkjfs().equals("Y")) { // 是否开通快捷支付

				bbcs.setPhone_no(accountBank.getMobilePhone());// 银行卡预留手机号
				bbcs.setVerify_mode(SinaMemberParmUtil.CardVerify.SIGN);// 认证方式
																		// SIGN
				j.setMsg("Y");
			}
			if (this.checkBindingBank(accountBank.getAccount())) {
				map = bindingBankCardSinaService.doBindingBank(bbcs, accountBank.getAccount(), accountBank.getMobilePhone());
				if (map.get("result").toString().equals(SinaMemberParmUtil.success)) {
					request.getSession().setAttribute("bbcr", map.get("bbcr"));
					request.getSession().setAttribute("scId", map.get("scId"));
					accountBank.setUserId(UserSession.getLoginUserId());
					accountBank.setAddip(ModelAndViewUtil.getIpAddr(request));
					this.accountBankService.insertSelective(accountBank);
					j.setSuccess(true);
					if (EmptyUtil.isEmpty(accountBank.getKtkjfs()) || !accountBank.getKtkjfs().equals("Y")) {
						// 发送添加卡短信
						this.jmsSenderUtil.sendSms(UserSession.getUserSession().getPhone(), ISendConfigUtil.SMS_ADDBANKCARD, UserSession.getUserSession().getUserName());
						if (EmptyUtil.isNotEmpty(UserSession.getUserSession().getEmail())) {
							this.jmsSenderUtil.sendEmail(UserSession.getUserSession().getEmail(), ISendConfigUtil.EMAIL_TEMPLET_ADDNEWCARD, UserSession.getUserSession().getUserName(), UserSession
									.getUserSession().getUserName());
						}
					}

				} else {
					j.setMsg(map.get("result").toString());
				}
			} else {
				if (!StringUtils.isEmpty(accountBank.getKtkjfs()) && accountBank.getKtkjfs().equals("Y")) {
					map = bindingBankCardSinaService.doBindingBank(bbcs, accountBank.getAccount(), accountBank.getMobilePhone());
					if (map.get("result").toString().equals(SinaMemberParmUtil.success)) {
						request.getSession().setAttribute("bbcr", map.get("bbcr"));
						request.getSession().setAttribute("scId", map.get("scId"));
						j.setSuccess(true);
					} else {
						j.setMsg(map.get("result").toString());
					}
				} else {
					j.setMsg("该银行卡已经绑定过!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 新浪绑定银行卡推进
	 * 
	 * @return
	 * @author jianwei
	 */
	@NeedSession(returnUrl = "/accountBank/sinaBindingBankAdvance")
	@RequestMapping(value = "/sinaBindingBankAdvance")
	@ResponseBody
	public Json sinaBindingBankAdvance(String checkCode, HttpServletRequest request) {
		Json j = new Json();
		String result = "";
		BindingBankCardAdvanceSendVO bbcas = new BindingBankCardAdvanceSendVO();
		if (request.getSession().getAttribute("bbcr") != null) {
			BindingBankCardReturnVO bbcr = (BindingBankCardReturnVO) request.getSession().getAttribute("bbcr");
			bbcas.setTicket(bbcr.getTicket());
			bbcas.setValid_code(checkCode);
			try {
				if (request.getSession().getAttribute("scId") != null) {
					String scId = request.getSession().getAttribute("scId").toString();
					result = bindingBankCardAdvanceSinaService.doBindingBankAdvance(bbcas, scId);
				}
				if (result.equals(SinaMemberParmUtil.success)) {
					j.setSuccess(true);
					// 发送添加卡短信
					this.jmsSenderUtil.sendSms(UserSession.getUserSession().getPhone(), ISendConfigUtil.SMS_ADDBANKCARD, UserSession.getUserSession().getUserName());
					if (EmptyUtil.isNotEmpty(UserSession.getUserSession().getEmail())) {
						this.jmsSenderUtil.sendEmail(UserSession.getUserSession().getEmail(), ISendConfigUtil.EMAIL_TEMPLET_ADDNEWCARD, UserSession.getUserSession().getUserName(), UserSession.getUserSession()
								.getUserName());
					}
					request.getSession().removeAttribute("accountBank" + UserSession.getLoginUserId());
				} else {
					j.setMsg("验证码校验失败,请重新获取验证码.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			j.setMsg("请获取验证码后再进行绑定");
		}
		return j;
	}

	/**
	 * 新浪解绑定银行卡
	 * 
	 * @return
	 * @author jianwei
	 */
	@NeedSession(returnUrl = "/accountBank/sinaUnbindingBank")
	@RequestMapping(value = "/sinaUnbindingBank")
	@ResponseBody
	public Json sinaUnbindingCard(SinaCard sc) {
		Json j = new Json();
		j.setSuccess(false);
		UnbindingBankCardSendVO ubcs = new UnbindingBankCardSendVO();
		ubcs.setCard_id(sc.getSinaCard());// 新浪银行卡编号
		ubcs.setIdentity_id(UserSession.getLoginUserId().toString());// 用户ID
		ubcs.setIdentity_type(SinaMemberParmUtil.IdenType.UID);
		try {
			String result = unbindingBankCardSinaService.doUnbindingBank(ubcs);
			if (result.equals(SinaMemberParmUtil.success)) {
				j.setSuccess(true);
			} else {
				j.setMsg(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 以绑定银行卡的进行快捷支付开通
	 * 
	 * @return
	 * @author jianwei
	 */
	@NeedSession(returnUrl = "/accountBank/openQuickPay")
	@RequestMapping(value = "/openQuickPay")
	@ResponseBody
	public Json openQuickPay(Integer scId, String phone, String bankCode, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Json j = new Json();
		SinaCard sc = sinaCardService.selectByPrimaryKey(scId);
		// 首先解绑银行卡
		if(!StringUtils.isEmpty(phone)){
			sc.setBankPhone(phone);
		}
		UnbindingBankCardSendVO ubcs = new UnbindingBankCardSendVO();
		ubcs.setCard_id(sc.getSinaCard());// 新浪银行卡编号
		ubcs.setIdentity_id(UserSession.getLoginUserId().toString());// 用户ID
		ubcs.setIdentity_type(SinaMemberParmUtil.IdenType.UID);
		try {
			unbindingBankCardSinaService.doUnbindingBank(ubcs);// 解绑银行卡
			// 从新绑定银行卡,并进行认证
			BindingBankCardSendVO bbcs = new BindingBankCardSendVO();
			bbcs.setBank_account_no(sc.getCardId());// 银行卡号
			bbcs.setBank_code(bankCode);// 银行编号 ICBC等
			bbcs.setCard_attribute(SinaMemberParmUtil.CardParam.C);// 银行卡属性
																	// C
																	// 对私
			bbcs.setCard_type(SinaMemberParmUtil.CardType.DEBIT);// 卡类型
																	// 借记卡
			bbcs.setCity(sc.getCity());// 城市
			bbcs.setIdentity_id(UserSession.getLoginUserId().toString());// userID
			bbcs.setIdentity_type(SinaMemberParmUtil.IdentityType.UID);
			bbcs.setProvince(sc.getProvince());// 省会
			bbcs.setRequest_no(new Date().getTime() + "" + UserSession.getLoginUserId());// 订单号
			bbcs.setPhone_no(phone);// 银行卡预留手机号
			bbcs.setVerify_mode(SinaMemberParmUtil.CardVerify.SIGN);// 认证方式
																	// SIGN
			j.setMsg("Y");
			map = bindingBankCardSinaService.doBindingBank(bbcs, sc.getCardId(), sc.getBankPhone());
			if (map.get("result").toString().equals(SinaMemberParmUtil.success)) {
				request.getSession().setAttribute("bbcr", map.get("bbcr"));
				request.getSession().setAttribute("scId", map.get("scId"));
				j.setSuccess(true);
			} else {
				j.setMsg(map.get("result").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 跳转到添加银行卡页面
	 * 
	 * @return
	 * @author jianwei
	 */
	@NeedSession(returnUrl = "/accountBank/bank")
	@RequestMapping(value = "/addBankDetail")
	public ModelAndView addBankDetail(String isIcbcCard) {
		ModelAndView mv = ModelAndViewUtil.createModelAndViewAndAddAccountToView(accountService, "account/addBankDetail");
		List<AccountBank> banks = this.accountBankService.selectByUserId(UserSession.getLoginUserId());
		int kjcount = 0;
		for (AccountBank ab : banks) {
			if (ab.getKtkjfs().equals("Y")) {
				kjcount = 1;
				break;
			}
		}
		mv.addObject("kjcount", kjcount);
		mv.addObject("isIcbcCard", isIcbcCard);
		if (!StringUtils.isEmpty(isIcbcCard) && isIcbcCard.equals("Y")) {
			IcbcCard ic = this.icbcCardService.selectByUserId(UserSession.getLoginUserId());
			mv.addObject("isIcbcCard_bank", "CMB");
			mv.addObject("isIcbcCard_bankName", "招商银行");
			mv.addObject("isIcbcCard_account", ic.getIcbcCard());
		}
		return mv;
	}

	/**
	 * 跳转到开通快捷支付页面
	 * 
	 * @return
	 * @author jianwei
	 */
	@NeedSession(returnUrl = "/accountBank/openQuickPayDetail")
	@RequestMapping(value = "/openQuickPayDetail")
	public ModelAndView openQuickPay(Integer accountId) {
		AccountBank ab = accountBankService.selectByPrimaryKey(accountId);
		SinaCard sc = new SinaCard();
		sc.setStatus("0");
		sc.setCardId(ab.getAccount());
		sc.setUserId(UserSession.getLoginUserId());
		List<SinaCard> scs = sinaCardService.selectSelective(sc);
		SinaDic sd = sinaDicService.dicLoad("BankCode", ab.getBank());
		ModelAndView mv = ModelAndViewUtil.createModelAndViewAndAddAccountToView(accountService, "account/openQuickPayDetail");
		mv.addObject("bank", ab);
		if (!scs.isEmpty()) {
			mv.addObject("sc", scs.get(0));
		}
		mv.addObject("sd", sd);
		return mv;
	}

	/**
	 * 校验银行卡是否已经绑定
	 * 
	 * @param accountNo
	 * @return
	 */
	public boolean checkBindingBank(String accountNo) {
		SinaCard sc = new SinaCard();
		sc.setCardId(accountNo);
		sc.setStatus("0");
		sc.setUserId(UserSession.getLoginUserId());
		List<SinaCard> scs = sinaCardService.selectSelective(sc);
		List<AccountBank> abs = this.accountBankService.selectByAccount(accountNo);
		if (!scs.isEmpty() && scs.get(0) != null && !abs.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 查询新浪绑卡支付每日限额
	 * 
	 * @param bankCode
	 * @return SinaBankLimitVO(对外只提供限额相关的字段)
	 * @author louchen
	 * @date 2015-03-04
	 */
	@NeedSession(returnUrl = "/account/recharge")
	@RequestMapping(value = "/bankLimit")
	@ResponseBody
	public Json bankLimit(String bankCode) {
		Json json = new Json();
		SinaBank sb = new SinaBank();
		sb.setBankCode(bankCode);
		List<SinaBank> list = sinaBankService.selectSinaBankByParam(sb);
		SinaBankLimitVO sbVO = new SinaBankLimitVO();
		if (list.size() == 1) {
			sb = list.get(0);
			sbVO.setBankName(sb.getBankName());
			sbVO.setIsQuick(sb.getIsQuick());
			sbVO.setBindingpayFirstLimit(sb.getBindingpayFirstLimit());
			sbVO.setBindingpaySingleLimit(sb.getBindingpaySingleLimit());
			sbVO.setBindingpayDayLimit(sb.getBindingpayDayLimit());
			json.setSuccess(true);
			json.setObj(sbVO);
		}
		return json;
	}

	/**
	 * 跳转申请联名卡页面
	 * 
	 * @return
	 * 
	 *         jianwei
	 */
	@NeedSession(returnUrl = "/accountBank/bank")
	@RequestMapping(value = "/toIcbcCardPage", method = RequestMethod.GET)
	public ModelAndView toIcbcCardPage() {
		UserSession us = UserSession.getUserSession();
		ModelAndView mv = null;
		if(StringUtils.isEmpty(us.getRealStatus()) || !us.getRealStatus().equals("1")){
			mv = ModelAndViewUtil.createModelAndViewAndAddAccountToView(accountService, "account/addBank");
			
		}else
			mv = ModelAndViewUtil.createModelAndViewAndAddAccountToView(accountService, "account/unionPayCardDetail");
		return mv;
	}

	/**
	 * 身份证图片
	 * 
	 * @param multipartRequest
	 * @param attestation
	 * @return
	 * @throws Exception
	 */
	@NeedSession
	@RequestMapping(value = "/idCardUpload", method = RequestMethod.POST)
	@ResponseBody
	public Json idCardUpload(MultipartHttpServletRequest multipartRequest, HttpServletRequest request, String idCardType, String name) throws Exception {
		Json j = new Json();
		MultipartFile multipartFile = multipartRequest.getFile("files");
		if (EmptyUtil.isNotEmpty(multipartFile) && multipartFile.getSize() > 0) {
			MultipartEntityBuilder multipartEntity = MultipartEntityBuilderUtil.createMultipartEntityBuilderByMultipartFile(multipartFile);
			MessageVO vo = this.icbcCardService.idCardUpload(multipartEntity, UserSession.getLoginUserId(), idCardType);
			multipartRequest.getSession().setAttribute(name, vo.getId());
			j.setSuccess(true);
		}
		return j;
	}

	/**
	 * 添加申请信息
	 * 
	 * @param ic
	 * @return
	 * 
	 *         jianwei
	 */
	@NeedSession
	@RequestMapping(value = "/addIcbcCard", method = RequestMethod.POST)
	@ResponseBody
	public Json addIcbcCard(IcbcCard ic) {
		Json j = new Json();
		IcbcCard i = this.icbcCardService.selectByUserId(UserSession.getLoginUserId());
		if (i != null) {
			i.setAddress(ic.getAddress());
			i.setAddtime(new Date());
			i.setCity(ic.getCity());
			i.setPostCode(ic.getPostCode());
			i.setProvince(ic.getProvince());
			i.setUserIdcard(UserSession.getUserSession().getCardId());
			i.setUserName(UserSession.getUserSession().getRealName());
			i.setUserPhone(ic.getUserPhone());
			this.icbcCardService.updateByPrimaryKeySelective(i);
			j.setSuccess(true);
			j.setMsg("联名卡申请成功!");
		}
		return j;
	}
}
