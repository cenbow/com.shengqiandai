package cn.vfunding.vfunding.prd.bms.sinadeposit.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.server.interceptors.NeedSession;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.account.model.AccountBank;
import cn.vfunding.vfunding.biz.account.model.AccountRecharge;
import cn.vfunding.vfunding.biz.account.service.IAccountRechargeService;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.service.IBindingBankCardSinaService;
import cn.vfunding.vfunding.biz.sina.service.IHostingCollectTradeSinaService;
import cn.vfunding.vfunding.biz.sina.service.IQuerySinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendService;
import cn.vfunding.vfunding.biz.sina.service.ISingleHostingPayTradeSinaService;
import cn.vfunding.vfunding.biz.sina.util.SinaParamsUtil;
import cn.vfunding.vfunding.biz.sina.vo.returns.BaseSinaReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.BindingBankCardSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.BindingVerifySendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateHostingCollectTradeSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateSingleHostingPayTradeSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.QueryAccountDetailsSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.QueryHostingTradeSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.SetRealNameSendVO;
import cn.vfunding.vfunding.biz.sinaPhone.model.SinaPhoneLog;
import cn.vfunding.vfunding.biz.sinaPhone.service.ISinaPhoneLogService;
import cn.vfunding.vfunding.biz.sinaRealname.model.SinaRealnameLog;
import cn.vfunding.vfunding.biz.sinaRealname.service.ISinaRealnameLogService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.prd.bms.utils.UserAuthFilter;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping(value = "/sinadeposit")
public class SinaController {

	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("sinaSendActionLog");

	private final static String baseUrl = "/sinadeposit";

	private final static String baseJspPath = "webpage/sinadeposit";

	private final static String datePatten = "yyyyMMddHHmmss";

	@Autowired
	private IQuerySinaService querySinaService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IAccountService accountServiceImpl;

	@Autowired
	private IAccountRechargeService accountRechargeService;

	@Autowired
	private ISingleHostingPayTradeSinaService borrowVerifySinaService;

	@Autowired
	private IHostingCollectTradeSinaService borrowTenderSinaService;

	@Autowired
	private IBindingBankCardSinaService bindingBankCardSinaService;
	
	@Autowired
	private ISinaRealnameLogService sinaRealnameLogService;
	
	@Autowired
	private ISinaSendService sinaSendService;
	
	@Autowired
	private ISinaPhoneLogService sinaPhoneLogService;
	/**
	 * 跳转到查询可用余额页面
	 * 
	 * @return ModelAndView
	 * @author louchen 2015-01-29
	 */
	@RequestMapping(value = "/searchBalancePage")
	@ResponseBody
	public ModelAndView searchBalancePage() {
		ModelAndView mv = new ModelAndView(baseJspPath + "/searchBalancePage");
		// 新建权限
		boolean canSearch = UserAuthFilter.isPass(baseUrl + "/canSearch");
		if (canSearch) {
			mv.addObject("canSearch", canSearch);
		}
		return mv;
	}

	/**
	 * 查询用户可用余额
	 * 
	 * @return
	 * @throws Exception
	 * @author louchen 2015-01-29
	 */
	@RequestMapping(value = "/searchBalance")
	@ResponseBody
	public Json searchBalance(String condition) throws Exception {
		// Json json = new Json();
		// Map<String, Object> entity = new HashMap<String, Object>();
		// List<UserWithBLOBs> users =
		// this.userService.selectByLogin(condition);
		// if (users.size() == 1) {
		// UserWithBLOBs user = users.get(0);
		// if (EmptyUtil.isNotEmpty(user) && user.getUserId() > 0) {
		// Account account = this.accountServiceImpl.selectByUserId(user
		// .getUserId());
		// if (EmptyUtil.isNotEmpty(account) && account.getId() > 0) {
		// Map<String, String> result = this.querySinaService
		// .doQueryBalance(user.getUserId());
		// if (result.get("success").equals("success")) {
		// // 微积金用户资金
		// entity.put("v_account", account);
		// // 新浪用户资金
		// entity.put("s_account", result);
		// json.setSuccess(true);
		// json.setObj(entity);
		// } else {
		// // 新浪接口错误消息
		// json.setMsg(result.get("msg"));
		// }
		// } else {
		// json.setMsg("匹配不到微积金账户余额");
		// }
		// } else {
		// json.setMsg("没有该用户");
		// }
		// } else {
		// json.setMsg("找不到该用户/手机号");
		// }
		// return json;
		Json json = new Json();
		Map<String, Object> entity = new HashMap<String, Object>();
		UserWithBLOBs user = this.userService.selectByPrimaryKey(Integer
				.parseInt(condition));
		if (EmptyUtil.isNotEmpty(user) && user.getUserId() > 0) {
			Account account = this.accountServiceImpl.selectByUserId(user
					.getUserId());
			if (EmptyUtil.isNotEmpty(account) && account.getId() > 0) {
				Map<String, String> result = this.querySinaService
						.doQueryBalance(user.getUserId());
				if (result.get("success").equals("success")) {
					// 微积金用户资金
					entity.put("v_account", account);
					// 新浪用户资金
					entity.put("s_account", result);
					json.setSuccess(true);
					json.setObj(entity);
				} else {
					// 新浪接口错误消息
					json.setMsg(result.get("msg"));
				}
			} else {
				json.setMsg("匹配不到微积金账户余额");
			}
		} else {
			json.setMsg("没有该用户");
		}
		return json;
	}

	/**
	 * 跳转到查询充值页面
	 * 
	 * @return ModelAndView
	 * @author louchen 2015-01-29
	 */
	@RequestMapping(value = "/searchDepositPage")
	@ResponseBody
	public ModelAndView searchDepositPage() {
		ModelAndView mv = new ModelAndView(baseJspPath + "/searchDepositPage");
		// 新建权限
		boolean canSearch = UserAuthFilter.isPass(baseUrl + "/canSearch");
		if (canSearch) {
			mv.addObject("canSearch", canSearch);
		}
		return mv;
	}

	/**
	 * 查询充值
	 * 
	 * @return
	 * @throws Exception
	 * @author louchen 2015-01-29
	 */
	@RequestMapping(value = "/searchDeposit")
	@ResponseBody
	public Json searchDeposit(String condition) throws Exception {
		Json json = new Json();
		Map<String, Object> entity = new HashMap<String, Object>();
		AccountRecharge rec = this.accountRechargeService
				.selectByTradeNo(condition);
		if (EmptyUtil.isNotEmpty(rec) && rec.getUserId() > 0) {
			Map<String, String> result = this.querySinaService
					.doQueryDeposit(rec);
			if (result.get("success").equals("success")) {
				entity.put("vfundingResult", rec);
				entity.put("sinaResult", result);
				json.setSuccess(true);
				json.setObj(entity);
			} else {
				// 新浪接口错误消息
				json.setMsg(result.get("msg"));
			}
		} else {
			json.setMsg("流水单号:" + condition + "查询不到充值记录");
		}
		return json;
	}

	/**
	 * 跳转到查询托管交易页面
	 * 
	 * @return ModelAndView
	 * @author louchen 2015-02-09
	 */
	@RequestMapping(value = "/searchHostingTradePage")
	@ResponseBody
	public ModelAndView searchHostingTradePage() {
		ModelAndView mv = new ModelAndView(baseJspPath
				+ "/searchHostingTradePage");
		// 新建权限
		boolean canSearch = UserAuthFilter.isPass(baseUrl + "/canSearch");
		if (canSearch) {
			mv.addObject("canSearch", canSearch);
		}
		mv.addObject("start",
				DateUtil.getAfterDate(new Date(), -60, "yyyy-MM-dd"));
		mv.addObject("end", DateUtil.parseDate(new Date()));
		return mv;
	}

	/**
	 * 查询托管交易
	 * 
	 * @return
	 * @throws Exception
	 * @author louchen 2015-02-09
	 */
	@RequestMapping(value = "/searchHostingTrade")
	@ResponseBody
	public Json searchHostingTrade(String userId, String idType, String start,
			String end, String pageNo, String pageSize) throws Exception {
		Json json = new Json();
		UserWithBLOBs user = this.userService.selectByPrimaryKey(Integer
				.parseInt(userId));
		if (EmptyUtil.isNotEmpty(user) && user.getUserId() > 0) {
			SimpleDateFormat source = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat format = new SimpleDateFormat(datePatten);
			start += " 00:00:00";
			end += " 23:59:59";
			Date sDate = source.parse(start);
			Date eDate = source.parse(end);
			QueryHostingTradeSendVO sendVO = new QueryHostingTradeSendVO();
			sendVO.setIdentity_id(user.getUserId().toString());
			sendVO.setIdentity_type(idType);
			sendVO.setStart_time(format.format(sDate));
			sendVO.setEnd_time(format.format(eDate));
			sendVO.setPage_no(pageNo);
			sendVO.setPage_size(pageSize);
			Map<String, String> result = this.querySinaService
					.doQueryHostingTrade(sendVO);
			Map<String, Object> entity = new HashMap<String, Object>();
			if (result.get("success").equals("success")) {
				entity.put("sinaResult", result);
				json.setSuccess(true);
				json.setObj(entity);
			} else {
				// 新浪接口错误消息
				json.setMsg(result.get("msg"));
			}
		} else {
			json.setMsg("没有该用户");
		}
		return json;
	}

	/**
	 * 跳转到查询收支明细页面
	 * 
	 * @return ModelAndView
	 * @author louchen 2015-02-09
	 */
	@RequestMapping(value = "/searchQueryAccountDetailsPage")
	@ResponseBody
	public ModelAndView searchQueryAccountDetailsPage() {
		ModelAndView mv = new ModelAndView(baseJspPath
				+ "/searchQueryAccountDetailsPage");
		// 新建权限
		boolean canSearch = UserAuthFilter.isPass(baseUrl + "/canSearch");
		if (canSearch) {
			mv.addObject("canSearch", canSearch);
		}
		mv.addObject("start",
				DateUtil.getAfterDate(new Date(), -7, "yyyy-MM-dd"));
		mv.addObject("end", DateUtil.parseDate(new Date()));
		return mv;
	}

	/**
	 * 查询收支明细
	 * 
	 * @return
	 * @throws Exception
	 * @author louchen 2015-02-09
	 */
	@RequestMapping(value = "/searchQueryAccountDetails")
	@ResponseBody
	public Json searchQueryAccountDetails(String userId, String idType,
			String start, String end, String pageNo, String pageSize)
			throws Exception {
		Json json = new Json();
		UserWithBLOBs user = this.userService.selectByPrimaryKey(Integer
				.parseInt(userId));
		if (EmptyUtil.isNotEmpty(user) && user.getUserId() > 0) {
			SimpleDateFormat source = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat format = new SimpleDateFormat(datePatten);
			start += " 00:00:00";
			end += " 23:59:59";
			Date sDate = source.parse(start);
			Date eDate = source.parse(end);
			QueryAccountDetailsSendVO sendVO = new QueryAccountDetailsSendVO();
			sendVO.setIdentity_id(user.getUserId().toString());
			sendVO.setIdentity_type(idType);
			if (!user.getTypeId().equals(40)) { // 收款人账户类型
				sendVO.setAccount_type(SinaMemberParmUtil.AccountType.SAVING_POT);
			} else {
				sendVO.setAccount_type(SinaMemberParmUtil.AccountType.BASIC);
			}
			sendVO.setStart_time(format.format(sDate));
			sendVO.setEnd_time(format.format(eDate));
			sendVO.setPage_no(Integer.parseInt(pageNo));
			sendVO.setPage_size(Integer.parseInt(pageSize));
			Map<String, String> result = this.querySinaService
					.doQueryAccountDetails(sendVO);
			Map<String, Object> entity = new HashMap<String, Object>();
			if (result.get("success").equals("success")) {
				entity.put("sinaResult", result);
				json.setSuccess(true);
				json.setObj(entity);
			} else {
				// 新浪接口错误消息
				json.setMsg(result.get("msg"));
			}
		} else {
			json.setMsg("没有该用户");
		}
		return json;
	}

	/**
	 * 跳转到代收金额页面
	 * 
	 * @return ModelAndView
	 * @author louchen 2015-02-10
	 */
	@RequestMapping(value = "/hostingCollectTradePage")
	@ResponseBody
	public ModelAndView hostingCollectTradePage() {
		ModelAndView mv = new ModelAndView(baseJspPath
				+ "/hostingCollectTradePage");
		// 新建权限
		boolean canSearch = UserAuthFilter.isPass(baseUrl + "/canSearch");
		if (canSearch) {
			mv.addObject("canSearch", canSearch);
		}
		return mv;
	}

	/**
	 * 代收金额 从username到中间账户
	 * 
	 * @return
	 * @throws Exception
	 * @author louchen 2015-02-10
	 */
	@RequestMapping(value = "/hostingCollectTrade", method = RequestMethod.POST)
	@ResponseBody
	public Json hostingCollectTrade(String username, String money,
			String summary,String tradeCode) throws Exception {
		Json json = new Json();
		String result = "failed";
		UserWithBLOBs user = null;
		if(!username.equals(SinaParamsUtil.getInstance().getVfundingCompanyAccount())){
			if(this.isNumeric(username)){
				user = this.userService.selectByPrimaryKey(Integer.parseInt(username));
			}	
		}
		if ((EmptyUtil.isNotEmpty(user) && user.getUserId() > 0)
				|| username.equals(SinaParamsUtil.getInstance()
						.getVfundingCompanyAccount())) {
			String type = "";
			//sendVO
			CreateHostingCollectTradeSendVO sendVO = new CreateHostingCollectTradeSendVO();
			//摘要
			sendVO.setSummary(summary);
			//流水号内部唯一
			sendVO.setOut_trade_no(System.currentTimeMillis() + ""); 
			//交易码
			sendVO.setOut_trade_code(tradeCode);
			if (username.equals(SinaParamsUtil.getInstance()
					.getVfundingCompanyAccount())) {//微积金企业钱包
				type = SinaMemberParmUtil.AccountType.BASIC;
				//付款人
				sendVO.setPayer_id(SinaParamsUtil.getInstance().getVfundingCompanyAccount());
				//付款人_类型
				sendVO.setPayer_identity_type(SinaMemberParmUtil.IdentityType.EMAIL); 
				//支付方式^金额^账户类型
				sendVO.setPay_method("balance^" + money + "^" + type); 
			} else {//微积金系统账户
				if (!user.getTypeId().equals(40)) { //收款人账户类型
					type = SinaMemberParmUtil.AccountType.SAVING_POT;
				} else {
					type = SinaMemberParmUtil.AccountType.BASIC;
				}	
				//付款人
				sendVO.setPayer_id(user.getUserId().toString());
				//付款人_类型
				sendVO.setPayer_identity_type(SinaMemberParmUtil.IdentityType.UID); 
				//支付方式^金额^账户类型
				sendVO.setPay_method("balance^" + money + "^" + type); 
			}
			
			try {
				// 发送接口
				result = borrowTenderSinaService.sinaSend(sendVO, summary);
			} catch (Exception e) {
				logger.error("#####[sina " + user.getUserId() + " 代付  异常]"
						+ "[url=" + SinaParamsUtil.getInstance().getTradesUrl()
						+ "]" + "[arg=" + JSON.toJSONString(sendVO) + "]"
						+ "[errormessage=" + e.getMessage() + "]" + "#####");
			}
			if (result.equals("success")) {
				json.setSuccess(true);
				json.setMsg(result);
			} else {
				json.setMsg(result);
			}
		} else {
			json.setMsg("没有该用户");
		}
		return json;
	}

	/**
	 * 跳转到代付金额页面
	 * 
	 * @return ModelAndView
	 * @author louchen 2015-02-10
	 */
	@RequestMapping(value = "/singleHostingPayTradePage")
	@ResponseBody
	public ModelAndView singleHostingPayTradePage() {
		ModelAndView mv = new ModelAndView(baseJspPath
				+ "/singleHostingPayTradePage");
		// 新建权限
		boolean canSearch = UserAuthFilter.isPass(baseUrl + "/canSearch");
		if (canSearch) {
			mv.addObject("canSearch", canSearch);
		}
		return mv;
	}

	/**
	 * 代付金额  从中间账户到username
	 * 
	 * @return
	 * @throws Exception
	 * @author louchen 2015-02-10
	 */
	@RequestMapping(value = "/singleHostingPayTrade", method = RequestMethod.POST)
	@ResponseBody
	public Json singleHostingPayTrade(String username, String money,
			String summary,String tradeCode) throws Exception {
		Json json = new Json();
		String result = "failed";
		UserWithBLOBs user = null;
		if(!username.equals(SinaParamsUtil.getInstance().getVfundingCompanyAccount())){
			if(this.isNumeric(username)){
				user = this.userService.selectByPrimaryKey(Integer.parseInt(username));
			}	
		}
		if ((EmptyUtil.isNotEmpty(user) && user.getUserId() > 0)
				|| username.equals(SinaParamsUtil.getInstance()
						.getVfundingCompanyAccount())) {
			// sendVO
			CreateSingleHostingPayTradeSendVO sendVO = new CreateSingleHostingPayTradeSendVO();
			// 流水号内部唯一
			sendVO.setOut_trade_no(System.currentTimeMillis() + "");
			// 交易码
			sendVO.setOut_trade_code(tradeCode);
			// 代付金额
			sendVO.setAmount(money);
			// 代付摘要
			sendVO.setSummary(summary);
			if (username.equals(SinaParamsUtil.getInstance()
					.getVfundingCompanyAccount())) {// 微积金企业钱包
				//收款人
				sendVO.setPayee_identity_id(SinaParamsUtil.getInstance().getVfundingCompanyAccount()); 
				//收款人类型
				sendVO.setPayee_identity_type(SinaMemberParmUtil.IdentityType.EMAIL);
				sendVO.setAccount_type(SinaMemberParmUtil.AccountType.BASIC);
			} else {// 微积金系统账户
				//收款人
				sendVO.setPayee_identity_id(user.getUserId().toString()); 
				//收款人类型
				sendVO.setPayee_identity_type(SinaMemberParmUtil.IdentityType.UID);
				// 收款人账户类型
				if (!user.getTypeId().equals(40)) { 
					sendVO.setAccount_type(SinaMemberParmUtil.AccountType.SAVING_POT);
				} else {
					sendVO.setAccount_type(SinaMemberParmUtil.AccountType.BASIC);
				}	
			}
			try {
				//发送接口
				result = borrowVerifySinaService.sinaSend(sendVO, summary);
			} catch (Exception e) {
				logger.error("#####[sina " + user.getUserId() + " 代付  异常]"
						+ "[url="
						+ SinaParamsUtil.getInstance().getTradesUrl() + "]"
						+ "[arg=" + JSON.toJSONString(sendVO) + "]"
						+ "[errormessage=" + e.getMessage() + "]" + "#####");
			}
			if (result.equals("success")) {
				json.setSuccess(true);
				json.setMsg(result);
			} else {
				json.setMsg(result);
			}
		} else {
			json.setMsg("没有该用户");
		}
		return json;
	}

	/**
	 * 新浪绑定银行卡
	 * 
	 * @return
	 * @author jianwei
	 */
	@NeedSession(returnUrl = "/sinadeposit/sinaBindingBank")
	@RequestMapping(value = "/sinaBindingBank")
	@ResponseBody
	public Json sinaBindingBank(AccountBank accountBank,
			HttpServletRequest request) {

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
			bbcs.setIdentity_id(accountBank.getUserId().toString());// userID
			bbcs.setIdentity_type(SinaMemberParmUtil.IdentityType.UID);
			bbcs.setProvince(accountBank.getProvinceName());// 省会
			bbcs.setRequest_no(new Date().getTime() + "");// 订单号
			if (!StringUtils.isEmpty(accountBank.getKtkjfs())
					&& accountBank.getKtkjfs().equals("Y")) { // 是否开通快捷支付

				bbcs.setPhone_no(accountBank.getMobilePhone());// 银行卡预留手机号
				bbcs.setVerify_mode(SinaMemberParmUtil.CardVerify.SIGN);// 认证方式
																		// SIGN
				j.setMsg("Y");
			}
			map = bindingBankCardSinaService.doBindingBank(bbcs,
					accountBank.getAccount(), accountBank.getMobilePhone());
			j.setObj(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}

	//是否数字
	private boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
	}
	
	
	/**
	 * 校验身份证绑定
	 * @return
	 */
	@NeedSession(returnUrl = "/sinadeposit/checkSinaRealname")
	@RequestMapping(value = "/checkSinaRealname")
	@ResponseBody
	public Json checkSinaRealname(){
		Json j = new Json();
		List<SinaRealnameLog> srs = sinaRealnameLogService.selectUserIdByRealstatus();
		if(!srs.isEmpty()){
			for(SinaRealnameLog sr : srs){
				SetRealNameSendVO srns = new SetRealNameSendVO();
				User u = userService.selectByPrimaryKey(sr.getUserId());
				srns.setIdentity_id(u.getUserId()+"");
				srns.setIdentity_type(SinaMemberParmUtil.IdenType.UID);
				try {
					srns.setReal_name(u.getRealname());
					srns.setCert_no(u.getCardId());
					srns.setCert_type(SinaMemberParmUtil.CertType.IC);
					BaseSinaReturnVO bsr = sinaSendService.sinaSendMgs(srns, BaseSinaReturnVO.class);
					sr.setResponseMsg(bsr.getResponse_message());
					sr.setResponseCode(bsr.getResponse_code());
					sr.setAddtime(new Date());
					sinaRealnameLogService.insertSelective(sr);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	/**
	 * 校验手机号认证
	 * @return
	 */
	@NeedSession(returnUrl = "/sinadeposit/checkSinaPhone")
	@RequestMapping(value = "/checkSinaPhone")
	@ResponseBody
	public Json checkSinaPhone(){
		Json j = new Json();
		List<SinaPhoneLog> sps = sinaPhoneLogService.selectUserIdByPhone();
		if(!sps.isEmpty()){
			for(SinaPhoneLog sp : sps){
				BindingVerifySendVO bvs = new BindingVerifySendVO();
				User u = userService.selectByPrimaryKey(sp.getUserId());
				bvs.setIdentity_id(u.getUserId()+"");
				bvs.setIdentity_type(SinaMemberParmUtil.IdenType.UID);
				try {
					bvs.setVerify_type(SinaMemberParmUtil.VerifyType.MOBILE);
					bvs.setVerify_entity(u.getPhone());
					BaseSinaReturnVO bsr = sinaSendService.sinaSendMgs(bvs, BaseSinaReturnVO.class);
					sp.setResponseMsg(bsr.getResponse_message());
					sp.setResponseCode(bsr.getResponse_code());
					sp.setAddtime(new Date());
					sinaPhoneLogService.insertSelective(sp);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	
}
