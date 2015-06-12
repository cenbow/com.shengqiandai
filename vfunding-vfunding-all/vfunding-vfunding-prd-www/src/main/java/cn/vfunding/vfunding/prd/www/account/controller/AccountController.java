package cn.vfunding.vfunding.prd.www.account.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import cn.vfunding.common.framework.server.interceptors.NeedSession;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.EncryptionUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.model.AccountRecharge;
import cn.vfunding.vfunding.biz.account.service.IAccountBankService;
import cn.vfunding.vfunding.biz.account.service.IAccountCashService;
import cn.vfunding.vfunding.biz.account.service.IAccountRechargeService;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.sina.model.SinaCard;
import cn.vfunding.vfunding.biz.sina.service.IAccountRechargeSinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaCardService;
import cn.vfunding.vfunding.biz.sina.vo.returns.CreateHostingDepositReturnVO;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.biz.userMobile.model.UserRechargeCashMobile;
import cn.vfunding.vfunding.biz.userMobile.service.IUserMobileService;

@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {

	@Autowired
	private IAccountService accountService;
	@Autowired
	private IAccountBankService accountBankService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IAccountCashService accountCashService;
	@Autowired
	private IAccountRechargeService accountRecharageService;
	@Autowired
	private IUserMobileService userMobileService;
	@Autowired
	private IAccountRechargeSinaService accountRechargeSinaService;
	@Autowired
	private ISinaCardService sinaCardService;
	
	/**
	 * 转向充值页面
	 * 
	 * @return 
	 * @author louchen 2015-01-21
	 */
	@NeedSession(returnUrl = "/account/recharge")
	@RequestMapping(value = "/recharge", method = RequestMethod.GET)
	public ModelAndView recharge() {
		ModelAndView mv = ModelAndViewUtil.createModelAndViewAndAddAccountToView(accountService, "account/sinaRecharge");
		mv.addObject("userIdInfo", EncryptionUtil.encrypt(UserSession.getLoginUserId().toString()));
		this.addQuickPayBankCards(mv);
		return mv;
	}
	
	/**
	 * 加载我的快捷支付银行卡
	 * @param mv
	 * @author louchen 2015-01-23
	 */
	private void addQuickPayBankCards(ModelAndView mv){
		List<SinaCard> cards = this.sinaCardService.selectQuickPayByUserId(UserSession.getLoginUserId().toString());
		mv.addObject("sinaCards", cards);
		mv.addObject("sinaCardsLen", cards.size());
	}
	
	/**
	 * 转向网银充值提示页面
	 * @param request
	 * @return
	 * @author louchen 2015-01-21
	 */
	@NeedSession(returnUrl = "/account/sinaRechargeReturn")
	@RequestMapping(value = "/sinaRechargeReturn/{id}", method = RequestMethod.GET)
	public ModelAndView sinaRechargeReturn(@PathVariable("id") String id) {
		ModelAndView mv = ModelAndViewUtil.createModelAndViewAndAddAccountToView(accountService, "account/sinaRechargeReturn");
		mv.addObject("userIdInfo", EncryptionUtil.encrypt(UserSession.getLoginUserId().toString()));
		mv.addObject("tradeNo",id);
		AccountRecharge rec = this.accountRecharageService.selectByTradeNo(id);
		if(EmptyUtil.isNotEmpty(rec) && rec.getId()>0){
			mv.addObject("tradeMsg",this.accountRecharageService.getRechargeStatusMsg(rec.getStatus()));
		}else{
			mv.addObject("tradeMsg","充值申请不存在");
		}
		return mv;
	}	
	
	/**
	 * 网银充值提示页面结果查询_当页面状态为充值等待时每隔5秒请求一次
	 * @param id
	 * @return
	 * @author louchen 2015-01-22
	 */
	@NeedSession(returnUrl = "/account/sinaRechargeReturn")
	@RequestMapping(value = "/sinaRechargeReturnAjax/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Json sinaRechargeReturnAjax(@PathVariable("id") String id){
		Json json = new Json();
		AccountRecharge rec = this.accountRecharageService.selectByTradeNo(id);
		String msg = "";
		if(EmptyUtil.isNotEmpty(rec) && rec.getId()>0){
			msg = this.accountRecharageService.getRechargeStatusMsg(rec.getStatus());
		}else{
			msg = "充值申请不存在";
		}
		json.setMsg(msg);
		json.setSuccess(true);
		return json;
	}
	
	//-----分割线 
	
	/**
	 * 获取新浪网银在线充值地址
	 * @param request
	 * @param money
	 * @param bankCode
	 * @return
	 * @throws Exception
	 * @author louchen 2015-01-20
	 */
	@NeedSession(returnUrl = "/account/recharge")
	@RequestMapping(value = "/sinaRechargeByOnlineBank", method = RequestMethod.POST)
	@ResponseBody
	public Json sinaRechargeByOnlineBank(HttpServletRequest request,String money,String bankCode) throws Exception{
		Json json = new Json();
		if(!UserSession.getUserSession().getRealStatus().equals("1")){
			json.setMsg("充值前需要先实名认证!");
		}else if(!UserSession.getUserSession().getPhoneStatus().equals("1")){
			json.setMsg("充值前需要先手机认证!");
		}else{
			BigDecimal zero = new BigDecimal(0);
			BigDecimal depositMoney = new BigDecimal(money);
			if(EmptyUtil.isEmpty(money)){
				json.setMsg("充值金额为空");
			}else if(zero.compareTo(depositMoney)>=0){
				json.setMsg("充值金额必须大于0元");
			}else{
				//创建AccountRecharge对象
				AccountRecharge rec = this.accountRechargeSinaService.getAccountRecharge(UserSession.getLoginUserId(),money,ModelAndViewUtil.getIpAddr(request),"新浪支付网银在线");
				//获取前端跳转url
				String url = this.accountRechargeSinaService.doUserRechargeByOnlineBank(rec, bankCode);
				json.setSuccess(true);
				json.setAction(url);
			}
		}
		return json;
	}
	
	/**
	 * 请求新浪接口绑卡支付
	 * @param request
	 * @param money
	 * @param card
	 * @return
	 * @throws Exception
	 * @author louchen 2015-01-22
	 */
	@NeedSession(returnUrl = "/account/recharge")
	@RequestMapping(value = "/sinaRechargeByBindingPay", method = RequestMethod.POST)
	@ResponseBody
	public Json sinaRechargeByBindingPay(HttpServletRequest request,String money,Integer card) throws Exception{
		SinaCard sc = this.sinaCardService.selectByPrimaryKey(card);
		Json json = this.sinaRechargeByBindingPayCheck(request, money, sc);
		if(json.getSuccess()){
			json.setSuccess(false);
			//校验通过后更新银行卡权重
			this.sinaCardService.updateQuickPayCardWeight(sc);		
			//创建充值对象
			AccountRecharge rec = this.accountRechargeSinaService.getAccountRecharge(UserSession.getLoginUserId()
					,money,ModelAndViewUtil.getIpAddr(request),"新浪支付绑卡支付");				
			//请求新浪绑卡支付接口
			Map<String, Object> map = this.accountRechargeSinaService.doUserRechargeByBindingPay(rec, sc);
			//请求成功后1.session记录充值returnvo 2.json记录充值流水号
			if(map.get("result").toString().equals("success")){
				json.setSuccess(true);
				//json记录充值流水号
				json.setMsg(rec.getTradeNo());
				//session记录returnVO
				CreateHostingDepositReturnVO returnVO = (CreateHostingDepositReturnVO)map.get("rechargeByBindingPayReturnVO");
				request.getSession().setAttribute("rechargeByBindingPayReturnVO", returnVO); 
			}else{
				json.setMsg(map.get("result").toString());
				//json.setMsg("获取验证码失败");
			}		
		}
		return json;
	}
	
	/**
	 * 请求新浪接口绑卡支付_校验数据
	 * @param request
	 * @param money
	 * @param card
	 * @return
	 * @throws Exception
	 * @author louchen 2015-01-22
	 */
	@NeedSession(returnUrl = "/account/recharge")
	@RequestMapping(value = "/sinaRechargeByBindingPayCheck", method = RequestMethod.POST)
	@ResponseBody
	public Json sinaRechargeByBindingPayCheck(HttpServletRequest request,String money,Integer card) throws Exception{
		SinaCard sc = this.sinaCardService.selectByPrimaryKey(card);
		return sinaRechargeByBindingPayCheck(request,money,sc);
	}
	
	private Json sinaRechargeByBindingPayCheck(HttpServletRequest request,String money,SinaCard sc){
		Json json = new Json();
		if(!UserSession.getUserSession().getRealStatus().equals("1")){
			json.setMsg("充值前需要先实名认证!");
		}else if(!UserSession.getUserSession().getPhoneStatus().equals("1")){
			json.setMsg("充值前需要先手机认证!");
		}else{
			BigDecimal zero = new BigDecimal(0);
			BigDecimal depositMoney = new BigDecimal(money);		
			if(EmptyUtil.isEmpty(money)){
				json.setMsg("充值金额为空");
			}else if(zero.compareTo(depositMoney)>=0){
				json.setMsg("充值金额必须大于0元");
			}else if(depositMoney.scale()>2){
				json.setMsg("充值金额小数最多保留两位");
			}else if(EmptyUtil.isEmpty(sc)){
				json.setMsg("快捷支付银行卡有误系统中无法搜索到,请重新开通快捷支付。");
			}else if(EmptyUtil.isEmpty(sc.getSinaCard())){
				json.setMsg("快捷支付银行卡与新浪支付绑定失败无法支付,请重新开通快捷支付。");
			}else if(EmptyUtil.isEmpty(sc.getUserId())){
				json.setMsg("快捷支付银行卡持有人为空,请重新开通快捷支付。");
			}else if(!sc.getUserId().equals(UserSession.getLoginUserId())){
				json.setMsg("快捷支付银行卡持有人与充值账户不是同一人,请联系客服");
			}else{
				json.setSuccess(true);		
			}
		}
		return json;
	}	
	
	/**
	 * 请求新浪接口绑卡支付_推进
	 * @param msgCode
	 * 	验证码
	 * @return
	 * @author louchen 2015-01-23
	 * @throws Exception 
	 */
	@NeedSession(returnUrl = "/account/recharge")
	@RequestMapping(value = "/sinaRechargeByBindingPayAdvance", method = RequestMethod.POST)
	@ResponseBody
	public Json sinaRechargeByBindingPayAdvance(HttpServletRequest request,String captcha,String tradeNo) throws Exception{
		Json json = new Json();
		String result = this.accountRechargeSinaService.checkRechargeByBindingPayAdvance(UserSession.getLoginUserId(), captcha, tradeNo);
		if(result.equals("success")){
			CreateHostingDepositReturnVO returnVO = (CreateHostingDepositReturnVO)request.getSession().getAttribute("rechargeByBindingPayReturnVO"); 
			result = this.accountRechargeSinaService.doUserRechargeByBindingPayAdvance(captcha, returnVO.getTicket(),tradeNo);
			if(result.equals("success")){
				json.setSuccess(true);
				json.setAction("/account/sinaRechargeReturn/"+tradeNo);
			}else{
				json.setMsg(result);
			}
		}else{
			json.setMsg(result);
		}
		return json;
	}
	
	
	//-----分割线
	
	/**
	 * 充值提现页面
	 * 
	 * @param type
	 * @param pageSearch
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 * 2014年4月29日 liuyijun
	 */
	@NeedSession(returnUrl = "/account/rechargeCash")
	@RequestMapping("/rechargeCash")
	public ModelAndView rechargeCash(@RequestParam(value = "type", required = false) String type,
			PageSearch pageSearch, @RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "end", required = false) String end) throws Exception {
		ModelAndView mv = new ModelAndView("user/rechargeCash");
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		BigDecimal sumCash = this.accountService.selectSumCashByUserId(UserSession.getLoginUserId());
		BigDecimal sumRecharge = this.accountService.selectSumRechargeByUserId(UserSession.getLoginUserId());
		mv.addObject("sumCash", sumCash);
		mv.addObject("sumRecharge", sumRecharge);
		// PageResult<UserRechargeCashMobile> result =
		// this.getListUserRechargeCashMobile(pageSearch, type, start, end);
		// mv.addObject("page", result);
		return mv;
	}

	/**
	 * 下线充值
	 * 
	 * @param recharge
	 * @return 2014年5月13日 liuyijun
	 */
//	@RequestMapping(value = "/rechargeOffline", method = RequestMethod.POST)
//	@ResponseBody
//	public Json rechargeOffline(AccountRecharge recharge, HttpServletRequest request) {
//		Json j = new Json();
//		recharge.setUserId(UserSession.getLoginUserId());
//		recharge.setStatus((byte) 0);// 线下充值待审0 & type=2
//		recharge.setType((byte) 2);// 线下充值
//		recharge.setAddtime(Integer.parseInt(DateUtil.getTime()));
//		recharge.setAddip(request.getRemoteAddr());
//		recharge.setTradeNo(recharge.getRemark());
//		if(recharge.getPayment()!=1 && recharge.getPayment()!=36){
//			j.setMsg("线下充值参数错误.");
//		} else if (EmptyUtil.isEmpty(recharge.getRemark()) || EmptyUtil.isEmpty(recharge.getMoney())) {
//			j.setMsg("填写不完整");
//		} else {
//			int result = this.accountRecharageService.insertSelective(recharge);
//			if (result > 0) {
//				j.setSuccess(true);
//			}
//		}
//
//		return j;
//	}

	@NeedSession(returnUrl = "/account/rechargeCash")
	@RequestMapping("/rechargeCashAjax")
	@ResponseBody
	public PageResult<UserRechargeCashMobile> rechargeCashAjax(
			@RequestParam(value = "type", required = false) String type, PageSearch pageSearch,
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "end", required = false) String end) throws Exception {
		PageResult<UserRechargeCashMobile> result = this.getListUserRechargeCashMobile(pageSearch, type, start, end);
		return result;
	}

	/**
	 * 查询交易记录
	 * 
	 * @param pageSearch
	 * @param type
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 *             author LiLei 2014年5月19日
	 */
	private PageResult<UserRechargeCashMobile> getListUserRechargeCashMobile(PageSearch pageSearch, String type,
			String start, String end) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", UserSession.getLoginUserId()); // map.put("s", value)
		PageResult<UserRechargeCashMobile> result = new PageResult<UserRechargeCashMobile>();
		List<UserRechargeCashMobile> resultList = null;
		if (EmptyUtil.isNotEmpty(type)) {
			if (type.equals("recharge")) {// 充值
				ModelAndViewUtil.addSearchValue(map, start, end);
				pageSearch.setEntity(map);
				resultList = this.accountRecharageService.selectByUserIdListPage(pageSearch, 0);
			} else if (type.endsWith("cash")) {// 提现
				ModelAndViewUtil.addSearchValue(map, start, end);
				pageSearch.setEntity(map);
				resultList = this.accountRecharageService.selectByUserIdListPage(pageSearch, 1);
			}
		} else {// 全部
			ModelAndViewUtil.addSearchValue(map, start, end);
			pageSearch.setEntity(map);
			resultList = this.userMobileService.selectRechargeCashByUserIdListPage(pageSearch);
		}
		result.setRows(resultList);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}
}
