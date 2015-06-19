package cn.p2p.p2p.prd.mobile.method.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.p2p.p2p.biz.mobile.base.model.MobileBaseRequest;
import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.p2p.p2p.biz.rechargeandtender.model.RechargeAndTender;
import cn.p2p.p2p.biz.sqdpaylog.model.SqdPayLog;
import cn.p2p.p2p.biz.sqdpaylog.service.ISqdPayLogService;
import cn.p2p.p2p.mobile.interceptors.CheckToken;
import cn.p2p.p2p.prd.mobile.method.all.AccountBankMethod;
import cn.p2p.p2p.prd.mobile.method.all.AccountCashMethod;
import cn.p2p.p2p.prd.mobile.method.all.AccountLogMethod;
import cn.p2p.p2p.prd.mobile.method.all.AccountMethod;
import cn.p2p.p2p.prd.mobile.method.all.BaseMethod;
import cn.p2p.p2p.prd.mobile.method.all.BorrowDeatilMethod;
import cn.p2p.p2p.prd.mobile.method.all.BorrowMethod;
import cn.p2p.p2p.prd.mobile.method.all.BorrowTenderMethod;
import cn.p2p.p2p.prd.mobile.method.all.ChargeMethod;
import cn.p2p.p2p.prd.mobile.method.all.CurrentMethod;
import cn.p2p.p2p.prd.mobile.method.all.GiftboxMethod;
import cn.p2p.p2p.prd.mobile.method.all.InterestDaysMethod;
import cn.p2p.p2p.prd.mobile.method.all.LoginMethod;
import cn.p2p.p2p.prd.mobile.method.all.PasswordMedhod;
import cn.p2p.p2p.prd.mobile.method.all.PhoneVerifyCodeMethod;
import cn.p2p.p2p.prd.mobile.method.all.SystemMessageMethod;
import cn.p2p.p2p.prd.mobile.method.all.UserMethod;
import cn.p2p.p2p.prd.mobile.method.request.vo.AccountBankVO;
import cn.p2p.p2p.prd.mobile.method.request.vo.AccountCashVO;
import cn.p2p.p2p.prd.mobile.method.request.vo.BindingBankAdvanceVO;
import cn.p2p.p2p.prd.mobile.method.request.vo.GeneralRequestVO;
import cn.p2p.p2p.prd.mobile.method.request.vo.PageUtilVO;
import cn.p2p.p2p.prd.mobile.method.request.vo.PasswordVO;
import cn.p2p.p2p.prd.mobile.method.request.vo.QueryBorrowListVO;
import cn.p2p.p2p.prd.mobile.method.vo.DeleteBankVO;
import cn.p2p.p2p.prd.mobile.utils.JsonValidation;
import cn.p2p.p2p.prd.mobile.vo.RechargeMobileaAdvanceVO;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.EncryptionUtil;
import cn.vfunding.common.framework.utils.http.RestInvoker;
import cn.vfunding.common.framework.utils.rest.annotation.RestDescription;
import cn.vfunding.vfunding.biz.account.model.AccountRecharge;
import cn.vfunding.vfunding.biz.sina.model.SinaCard;
import cn.vfunding.vfunding.biz.sina.vo.returns.CreateHostingDepositReturnVO;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.usertoken.service.IUserTokenService;
import cn.vfunding.vfunding.common.jmssender.JmsSenderObj;
import cn.vfunding.vfunding.common.jmssender.SenderObjBuilder;
import cn.vfunding.vfunding.common.module.activemq.message.afteraction.AfterActionMessage;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;

@Controller
public class MobileMethodController {

	@Autowired
	private IUserTokenService userTokenService;
	@Autowired
	private LoginMethod loginMethod;
	// @Autowired
	// private WeekMethod weekMethod;

	@Autowired
	private UserMethod userMethod;
	@Autowired
	private ChargeMethod chargeMethod;

	@Autowired
	private GiftboxMethod giftboxMethod;
	@Autowired
	private BaseMethod baseMethod;
	@Autowired
	private SystemMessageMethod systemMessageMethod;
	@Autowired
	private BorrowDeatilMethod borrowDeatilMethod;
	@Autowired
	private BorrowTenderMethod borrowTenderMethod;
	@Autowired
	private BorrowMethod borrowMethod;

	@Autowired
	private AccountCashMethod accountCashMethod;

	@Autowired
	private CurrentMethod currentMethod;
	@Autowired
	private AccountBankMethod accountBankMethod;
	@Autowired
	private AccountMethod accountMethod;
	@Autowired
	private PasswordMedhod passwordMedhod;

	@Autowired
	private PhoneVerifyCodeMethod phoneVerifyCodeMethod;
	@Autowired
	private AccountLogMethod accountLogMethod;

	@Autowired
	private InterestDaysMethod interestDaysMethod;
	
	//sqd支付记录服务
	@Autowired
	private ISqdPayLogService sqdPayLogService;

	@RequestMapping(value = "/mobile")
	@ResponseBody
	public MobileBaseResponse mobile(@RequestBody String requestJson) throws Exception {
		if (!StringUtils.isEmpty(requestJson) && JsonValidation.validate(requestJson)) {
//			this.saveMobileRequestLog(requestJson);// 保存接口请求日志
			MobileBaseResponse checkRquestJson = this.checkBaseRequest(requestJson);
			if (checkRquestJson.getResponseCode().equals("success")) {// 校验请求信息内容
				MobileBaseRequest mbRequest = JSON.parseObject(requestJson, MobileBaseRequest.class);
				if (mbRequest.getMethodName().equals("register")) {// 注册
					return this.loginMethod.register(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("phoneCode")) {// 发送验证码
					return this.phoneVerifyCodeMethod.sendVerifyCode(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("login")) {// 登陆
					return this.loginMethod.login(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("realname")) {// 实名认证
					return this.userMethod.saveRealName(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("userAccount")) {// 用户账户信息
					return this.userMethod.userAccount(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("charge")) {// 充值
					return this.chargeMethod.recharge(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("chargeAdvance")) {// 充值推进
					return this.chargeMethod.rechargeAdvance(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("chargeStatus")) {// 充值状态查询
					return this.chargeMethod.chargeStatus(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("registerCheckUsername")) {// 校验用户名
					return this.loginMethod.registerCheckUsername(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("registerCheckPhone")) {// 校验手机号
					return this.loginMethod.registerCheckPhone(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("registerCheckMail")) {// 校验邮箱
					return this.loginMethod.registerCheckMail(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("giftIndex")) {// 礼品盒主页
					return this.giftboxMethod.index(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("hongbaoPage")) {// 红包页面基本信息
					return this.giftboxMethod.hongbaoPage(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("hongbaoList")) {// 红包页面红包列表
					return this.giftboxMethod.hongbaoList(JSON.parseObject(requestJson, PageUtilVO.class));
				} else if (mbRequest.getMethodName().equals("hongbaoDetail")) {// 红包详细信息
					return this.giftboxMethod.hongbaoDetail(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("useHongbao")) {// 单个红包使用
					return this.giftboxMethod.useHongbao(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("useHongbaoAny")) {// 一键使用红包
					return this.giftboxMethod.useHongbaoAny(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("jiaxiPage")) {// 加息卡页面基本信息
					return this.giftboxMethod.jiaxiPage(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("jiaxiList")) {// 加息卡列表
					return this.giftboxMethod.jiaxiList(JSON.parseObject(requestJson, PageUtilVO.class));
				} else if (mbRequest.getMethodName().equals("jiaxiDetail")) {// 加息卡详情
					return this.giftboxMethod.jiaxiDetail(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("dikouPage")) {// 抵扣券页面基本信息
					return this.giftboxMethod.dikouPage(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("dikouList")) {// 抵扣券列表
					return this.giftboxMethod.dikouList(JSON.parseObject(requestJson, PageUtilVO.class));
				} else if (mbRequest.getMethodName().equals("dikouDetail")) {// 抵扣券详情
					return this.giftboxMethod.dikouDetail(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("otherList")) {// 其他礼品列表
					return this.giftboxMethod.otherList(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("otherDetail")) {// 其他礼品信息
					return this.giftboxMethod.otherDetail(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("systemMessageList")) {// 系统消息列表
					return this.systemMessageMethod.systemMessageList(JSON.parseObject(requestJson, PageUtilVO.class));
				} else if (mbRequest.getMethodName().equals("systemMessageDetail")) {// 系统消息详情
					return this.systemMessageMethod.systemMessageDetail(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("messageList")) {// 站内信列表
					return this.systemMessageMethod.messageList(JSON.parseObject(requestJson, PageUtilVO.class));
				} else if (mbRequest.getMethodName().equals("messageDetail")) {// 站内信详情
					return this.systemMessageMethod.messageDetail(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("deleteMessage")) {// 删除站内信
					return this.systemMessageMethod.deleteMessage(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("deleteSystemMessage")) {// 删除系统消息
					return this.systemMessageMethod.deleteSystemMessage(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("selectScrollpic")) {// 首页广告滚动
					return this.baseMethod.selectScrollpic();
				} else if (mbRequest.getMethodName().equals("selectArticleBulletin")) {// 首页公告查询
					return this.baseMethod.selectArticleBulletin();
				} else if (mbRequest.getMethodName().equals("selectParam")) {// 首页参数查询
					return this.baseMethod.selectParam();
				} else if (mbRequest.getMethodName().equals("currentList")) {// 活期标的列表
					return this.currentMethod.currentList(JSON.parseObject(requestJson, PageUtilVO.class));
				} else if (mbRequest.getMethodName().equals("currentDetail")) {// 活期标详情
					return this.currentMethod.currentDetail(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("currentTender")) {// 活期标投资
					return this.currentMethod.tenderCurrentAction(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("currentRedeem")) {// 活期转出
					return this.currentMethod.currentRedeem(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("borrowList")) {// 标的列表
					return this.borrowMethod.borrowList(JSON.parseObject(requestJson, QueryBorrowListVO.class));
				} else if (mbRequest.getMethodName().equals("borrowBaseDetail")) {// 标的详情基本信息
					return this.borrowDeatilMethod.borrowBaseDetail(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("borrowLoanDetail")) {// 标的借款信息
					return this.borrowDeatilMethod.borrowLoanDetail(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("borrowCreditDetail")) {// 借款人信用详情
					return this.borrowDeatilMethod.borrowCreditDetail(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("borrowInvestDetail")) {// 标的投资记录
					return this.borrowDeatilMethod.borrowInvestDetail(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("tenderPageDetail")) {// 投资页面详情
					return this.borrowTenderMethod.tenderPageDetail(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("tenderAction")) {// 投资动作
					return this.borrowTenderMethod.tenderAction(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("cashCard")) {// 加载提现银行卡
					return this.accountCashMethod.takeCash(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("cashFee")) {// 计算提现手续费
					return this.accountCashMethod.getCashFee(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("cashAction")) {// 提现动作
					return this.accountCashMethod.applyCash(JSON.parseObject(requestJson, AccountCashVO.class));
				} else if (mbRequest.getMethodName().equals("bindingBank")) {// 绑定银行卡获取短信
					return this.accountBankMethod.sinaBindingBank(JSON.parseObject(requestJson, AccountBankVO.class));
				} else if (mbRequest.getMethodName().equals("BindingBankAdvance")) {// 绑定银行卡短信推进
					return this.accountBankMethod.sinaBindingBankAdvance(JSON.parseObject(requestJson, BindingBankAdvanceVO.class));
				} else if (mbRequest.getMethodName().equals("deleteBank")) {// 删除银行卡
					return this.accountBankMethod.deleteBank(JSON.parseObject(requestJson, DeleteBankVO.class));
				} else if (mbRequest.getMethodName().equals("myBank")) {// 银行卡列表
					return this.accountBankMethod.myBank(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("rechargeCashList")) {// 查询充值提现记录
					return this.accountMethod.getListUserRechargeCash(JSON.parseObject(requestJson, PageUtilVO.class));
				} else if (mbRequest.getMethodName().equals("investRecordList")) {// 查询投资记录
					return this.accountMethod.investRecordListPage(JSON.parseObject(requestJson, PageUtilVO.class));
				} else if (mbRequest.getMethodName().equals("collectionRecordList")) {// 查询待收记录
					return this.accountMethod.collectionRecordListPage(JSON.parseObject(requestJson, PageUtilVO.class));
				} else if (mbRequest.getMethodName().equals("changePassword")) {// 修改密码/支付密码,忘记密码/支付密码
					return this.passwordMedhod.checkCodeAndChangePassword(JSON.parseObject(requestJson, PasswordVO.class));
				} else if (mbRequest.getMethodName().equals("setPayPassword")) {// 设置支付密码
					return this.passwordMedhod.savePayPassword(JSON.parseObject(requestJson, PasswordVO.class));
				} else if (mbRequest.getMethodName().equals("accountLogList")) {// 查询资金记录
					return this.accountLogMethod.getAccountLogList(JSON.parseObject(requestJson, PageUtilVO.class));
				} else if (mbRequest.getMethodName().equals("userActionSteps")) {// 用户当前需完善的进度状态
					return this.userMethod.userActionSteps(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("userCurrentAccount")) {// 用户活期的账户情况
					return this.userMethod.userCurrentAccount(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("userCurrentRedeemLog")) {// 用户活期的账户情况
					return this.userMethod.userCurrentRedeemLog(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("userCurrentTenderLog")) {// 用户活期的账户情况
					return this.userMethod.userCurrentTenderLog(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("interestByUserDateStr")) {// 根据日期查询详情
					return this.interestDaysMethod.selectByUserDateStr(JSON.parseObject(requestJson, GeneralRequestVO.class));
				} else if (mbRequest.getMethodName().equals("interestByUser")) {// 查询每日收益
					return this.interestDaysMethod.selectByUserIdListPage(JSON.parseObject(requestJson, PageUtilVO.class));
				} else if (mbRequest.getMethodName().equals("sinaBankList")) {// 查询银行列表
					return this.accountBankMethod.sinaBankList();
				}
				else if (mbRequest.getMethodName().equals("addPayLog")) {// 添加支付记录
					return this.addPayLog(JSON.parseObject(requestJson, GeneralRequestVO.class));
				}
				else {
					return new MobileBaseResponse("mehodName_fail", "调用接口不存在");
				}
			} else {
				return checkRquestJson;
			}
		} else {
			return new MobileBaseResponse("request_null_fail", "请求内容格式不正确或内容为空");
		}
	}

	private MobileBaseResponse checkBaseRequest(String requestJson) {
		MobileBaseRequest mbRequest = JSON.parseObject(requestJson, MobileBaseRequest.class);
		if (StringUtils.isEmpty(mbRequest.getOrderNo()))
			return new MobileBaseResponse("order_null_fail", "订单编号不可为空");
		else if (StringUtils.isEmpty(mbRequest.getOsType()))
			return new MobileBaseResponse("osType_null_fail", "app系统类型不可为空");
		else if (StringUtils.isEmpty(mbRequest.getVersion()))
			return new MobileBaseResponse("version_null_fail", "调用接口版本不可为空");
		else if (StringUtils.isEmpty(mbRequest.getMethodName()))
			return new MobileBaseResponse("methodName_null_fail", "调用接口名称不可为空");
		else
			return new MobileBaseResponse();
	}


//	private MobileBaseResponse checkReuqestOrderNo(String requestJson) {
//		MobileBaseRequest mbRequest = JSON.parseObject(requestJson, MobileBaseRequest.class);
//		int count = this.mobileRequestLogService.selectCountByOrderNo(mbRequest.getOrderNo());
//		if (count > 0)
//			return new MobileBaseResponse("double_orderNo_fail", "请勿提交重复订单号");
//		else
//			return new MobileBaseResponse();
//	}

	/**
	 * @param args
	 *
	 * jianwei
	 */
	public static void main(String[] args) {
		
		String[] datas = new String[]{"4594386F632392D3AD6DFFC9FEBE3AEE09F3912FB5F57E47987707B0826D71115CD9EDF12A8B73C0293F2DDAB20C18396B51D84A0DD394976F52761227339B8C80E538EBA404378D7568C6E2DD0C2CCED41477C0B3B79FD37BCC2F90CEE1062E5186FE62DB2F2BFB2745FA81B50932ED7A18ED2FFF576228F7B897B8388A8469A194E73AF9E13AB6FB04317078E58ADC63AF8441622767BA8A91EF82D7A414DE23BA7BA8F166EEFDAB29CB4B90607AAE670A8D2FC0C6268CDD07176E0C92F0C8D3DCC9A20CC05E143B8F54A9D211454210FADCD2505341CEBD78914362A9A40F181F7C5740E8ED2DA0F6349F52A434602DE0A328661746F6C8B45799A6AAD46E46750402BAD7F4776399A3D0BB36F425353427F1E6D23E921DC59F16AAA1A204"};
		for (String string : datas) {
			String content =EncryptionUtil.decrypt(string);
			System.out.println(content);
			AfterActionMessage actionMessage = JSON.parseObject(content,AfterActionMessage.class);
			MobileMethodController method = new MobileMethodController();
//			method.asynSendUserActionJms(actionMessage);
		}
		
	}
	
	

	public void asynSendUserActionJms(AfterActionMessage actionMessage) {
		JmsSenderObj obj = this.createObjByMessage(actionMessage,
				SenderObjBuilder.JMS_USERACTION);
		RestInvoker ri = new RestInvoker();
		ri.setBaseURL("http://jms.8jielicai.com");
		ri.post("/send/jms", obj);
	}

	public JmsSenderObj createObjByMessage(Serializable message,
			String destinationName) {
		String content = JSON.toJSONString(message);
		JmsSenderObj obj = SenderObjBuilder.buildJmsSenderObj(content,
				message.getClass(), destinationName);
		return obj;
	}
	
	//添加sqd支付记录,hyc
	@RestDescription("添加支付记录")
	@CheckToken
	public MobileBaseResponse addPayLog(GeneralRequestVO generalRequest) throws Exception {
			System.out.println(generalRequest.getProductId());
			System.out.println(generalRequest.getUserId());
			System.out.println(generalRequest.getTradeNo());
			System.out.println(generalRequest.getPayMoney());
			System.out.println(generalRequest.getResultPay());
			System.out.println(generalRequest.getRemark());
			System.out.println(generalRequest.getAddIp());
			
		
			SqdPayLog sqdPayLog=new SqdPayLog();
			sqdPayLog.setProductId(generalRequest.getProductId());
			sqdPayLog.setUserId(generalRequest.getUserId());
			sqdPayLog.setTradeNo(generalRequest.getTradeNo());
			sqdPayLog.setPayMoney(generalRequest.getPayMoney());
			sqdPayLog.setResultPay(generalRequest.getResultPay());
			sqdPayLog.setRemark(generalRequest.getRemark());
			sqdPayLog.setAddDate(new Date());
			sqdPayLog.setAddIp(generalRequest.getAddIp());
			sqdPayLogService.insert(sqdPayLog);
			System.out.println("添加支付记录成功");
			return new MobileBaseResponse("success", "添加成功");
		
	}
	
}
