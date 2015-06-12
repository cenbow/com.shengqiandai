package cn.vfunding.vfunding.prd.www.pay.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import beartool.MD5;
import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.lock.LockUtil;
import cn.vfunding.common.framework.server.interceptors.NeedSession;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.vfunding.biz.account.model.AccountRecharge;
import cn.vfunding.vfunding.biz.account.service.IAccountRechargeService;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.prd.www.util.pay.GopayUtils;
import cn.vfunding.vfunding.prd.www.vo.pay.ChinaBankReceiveVO;
import cn.vfunding.vfunding.prd.www.vo.pay.ChinaBankVO;
import cn.vfunding.vfunding.prd.www.vo.pay.EcpssVO;
import cn.vfunding.vfunding.prd.www.vo.pay.GoPayVO;

@Controller()
@RequestMapping("/pay")
public class PayController extends BaseController {

	@Autowired
	private LockUtil lockUtil;
	Logger logger = LoggerFactory.getLogger("paylog");

	@Autowired
	private IAccountRechargeService accountRecharageService;

	@Autowired
	private IAccountService accountService;

	/**
	 * 转向网银在线页面
	 * 
	 * @param chinaBank
	 * @return 2014年4月29日 liuyijun
	 * 
	 */
	@NeedSession
	@RequestMapping(value = "/chinaBank")
	public ModelAndView payChinaBank(ChinaBankVO chinaBank,
			HttpServletRequest request) {
		logger.info("网银在线支付发送请求日志：记录开始");
		logger.info("网银在线支付发送请求日志：充值人：" + UserSession.getLoginUserId());
		ModelAndView mv = new ModelAndView("pay/chinabank/Send");
		if (this.checkRealName(mv)) {
			chinaBank.setV_amount(chinaBank.getRechargeMoney());
			chinaBank.setPmode_id(chinaBank.getRechargeBankCode());
			/*
			 * chinaBank.setRemark2("[url:=http://" + request.getServerName() +
			 * ":" + request.getServerPort() + chinaBank.getRemark2() + "/" +
			 * UserSession.getLoginUserId() + "]");
			 */

			chinaBank.setRemark2("[url:="
					+ ModelAndViewUtil.createBaseUrl(request)
					+ chinaBank.getRemark2() + "/"
					+ UserSession.getLoginUserId() + "]");

			/*
			 * chinaBank.setV_url("http://" + request.getServerName() + ":" +
			 * request.getServerPort() + chinaBank.getV_url());
			 */

			chinaBank.setV_url(ModelAndViewUtil.createBaseUrl(request)
					+ chinaBank.getV_url());

			if (EmptyUtil.isEmpty(chinaBank.getV_oid())) // 判断是否有传递订单号
			{
				/*
				 * chinaBank.setV_oid(DateUtil.getDateString(new
				 * Date(),"yyyyMMddHHmmss") + "-" +
				 * VerifyCodeUtils.createVerifyCode(4));
				 */

				chinaBank.setV_oid(String.valueOf(System.currentTimeMillis()));
			}
			chinaBank.setV_md5info(new MD5().getMD5ofStr(chinaBank
					.getV_amount()
					+ chinaBank.getV_moneytype()
					+ chinaBank.getV_oid()
					+ chinaBank.getV_mid()
					+ chinaBank.getV_url() + chinaBank.getKey()));
			mv.addObject("chinaBank", chinaBank);

		}
		return mv;
	}

	/**
	 * 检测是否已实名认证
	 * 
	 * @param mv
	 * @return 2014年6月3日 liuyijun
	 */
	private boolean checkRealName(ModelAndView mv) {
		if (EmptyUtil.isEmpty(UserSession.getUserSession().getRealStatus())
				&& !UserSession.getUserSession().getRealStatus().equals("1")) {
			mv.setViewName("redirect:/user/realName");
			return false;
		} else {
			return true;
		}

	}

	/**
	 * 网银在线接收页面
	 * 
	 * @param chinaBankReceive
	 * @return 2014年4月29日 liuyijun
	 * @throws Exception
	 * @throws UnsupportedEncodingException
	 */
	@NeedSession
	@RequestMapping(value = "/chianBankReceive")
	public ModelAndView chinaBankReceive(ChinaBankReceiveVO chinaBankReceive)
			throws Exception {
		logger.info("网银在线前台接收日志：记录开始");
		logger.info("网银在线前台接收日志：充值人：" + UserSession.getLoginUserId());
		logger.info("充值金额：" + chinaBankReceive.getV_amount());
		logger.info("订单号：" + chinaBankReceive.getV_oid());
		ModelAndView mv = new ModelAndView("redirect:/pay/paySuccess");
		String text = new MD5().getMD5ofStr(
				chinaBankReceive.getV_oid() + chinaBankReceive.getV_pstatus()
						+ chinaBankReceive.getV_amount()
						+ chinaBankReceive.getV_moneytype()
						+ chinaBankReceive.getKey()).toUpperCase();
		logger.info("返回状态码：" + chinaBankReceive.getV_pstatus());
		logger.info("网银在线前台接收日志：返回的加密信息:" + chinaBankReceive.getV_md5str());
		logger.info("网银在线前台接收日志：比对的加密：" + text);
		if (chinaBankReceive.getV_md5str().equals(text)) {
			if ("30".equals(chinaBankReceive.getV_pstatus())) {
				mv.setViewName("redirect:/pay/payFaild");
				this.createAndSaveRecharge(chinaBankReceive.getV_oid(),
						chinaBankReceive.getV_pstring(), new BigDecimal(
								chinaBankReceive.getV_amount()), "网银在线在线充值",
						(byte) 0, (short) 55);
				logger.info("网银在线前台接收日志：充值失败");
			} else if ("20".equals(chinaBankReceive.getV_pstatus())) {
				this.doRechargeSuccess(chinaBankReceive.getV_oid(),
						new BigDecimal(chinaBankReceive.getV_amount()),
						(short) 55, UserSession.getLoginUserId(), "网银在线在线充值");
				// mv.addObject("chinaBankReceive", chinaBankReceive);
				logger.info("网银在线前台接收日志：充值成功");
			}
		} else {
			logger.info("网银在线前台接收日志：加密信息错误");
			mv.setViewName("redirect:/pay/payFaild");
		}
		return mv;
	}

	/**
	 * 网银在线自动对账
	 * 
	 * @param chinaBankReceive
	 * @return 2014年4月30日 liuyijun
	 * @throws Exception
	 */
	@RequestMapping(value = "/chinaBankAutoReceive/{userId}")
	public ResponseEntity<String> chinaBankAutoReceive(
			ChinaBankReceiveVO chinaBankReceive,
			@PathVariable("userId") Integer userId) throws Exception {//
		logger.info("网银在线后台对账日志：记录开始");
		String data = "ok";
		logger.info("充值人：" + userId);
		logger.info("充值金额：" + chinaBankReceive.getV_amount());
		logger.info("订单号：" + chinaBankReceive.getV_oid());
		logger.info("返回状态码：" + chinaBankReceive.getV_pstatus());

		logger.info("网银在线后台对账日志：返回的加密信息:" + chinaBankReceive.getV_md5str());

		String text = new MD5().getMD5ofStr(
				chinaBankReceive.getV_oid() + chinaBankReceive.getV_pstatus()
						+ chinaBankReceive.getV_amount()
						+ chinaBankReceive.getV_moneytype()
						+ chinaBankReceive.getKey()).toUpperCase();
		logger.info("网银在线后台对账日志：比对的加密：" + text);
		if (chinaBankReceive.getV_md5str().equals(text)) {
			data = "ok";
			if ("20".equals(chinaBankReceive.getV_pstatus())) {
				this.doRechargeSuccess(chinaBankReceive.getV_oid(),
						new BigDecimal(chinaBankReceive.getV_amount()),
						(short) 55, userId, "网银在线在线充值");
			} else {
				logger.info("网银在线后台对账日志：充值失败");
			}
			logger.info("网银在线后台对账日志：充值成功");
		} else {
			data = "error";
			logger.info("网银在线后台对账日志：加密信息错误");
		}

		logger.info("网银在线后台对账日志：记录结束");
		return new ResponseEntity<String>(data, HttpStatus.OK);
	}

	/**
	 * 转向国付宝支付页面
	 * 
	 * @param goPay
	 * @return 2014年5月4日 liuyijun
	 */
	@NeedSession
	@RequestMapping(value = "/goPay")
	public ModelAndView goPay(GoPayVO goPay, HttpServletRequest request) {
		logger.info("国付宝在线支付发送请求日志：记录开始");
		logger.info("国付宝在线支付发送请求日志：充值人：" + UserSession.getLoginUserId());
		ModelAndView mv = new ModelAndView("pay/gopay/sign");
		if (this.checkRealName(mv)) {
			goPay.setTranAmt(goPay.getRechargeMoney());
			goPay.setBankCode(goPay.getRechargeBankCode());
			goPay.setMerOrderNum(String.valueOf(System.currentTimeMillis()));
			goPay.setTranDateTime(DateUtil.getDateString(new Date(),
					"yyyyMMddHHmmss"));
			goPay.setTranIP(ModelAndViewUtil.getIpAddr(request));
			/*
			 * goPay.setFrontMerUrl("http://" + request.getServerName() + ":" +
			 * request.getServerPort() + goPay.getFrontMerUrl() + "/" +
			 * UserSession.getLoginUserId());
			 */

			goPay.setFrontMerUrl(ModelAndViewUtil.createBaseUrl(request)
					+ goPay.getFrontMerUrl() + "/"
					+ UserSession.getLoginUserId());
			goPay.setBackgroundMerUrl(ModelAndViewUtil.createBaseUrl(request)
					+ goPay.getBackgroundMerUrl() + "/"
					+ UserSession.getLoginUserId());
			// 组织加密明文
			String plain = "version=[" + goPay.getVersion() + "]tranCode=["
					+ goPay.getTranCode() + "]merchantID=["
					+ goPay.getMerchantID() + "]merOrderNum=["
					+ goPay.getMerOrderNum() + "]tranAmt=["
					+ goPay.getTranAmt() + "]feeAmt=[]tranDateTime=["
					+ goPay.getTranDateTime() + "]frontMerUrl=["
					+ goPay.getFrontMerUrl() + "]backgroundMerUrl=["
					+ goPay.getBackgroundMerUrl()
					+ "]orderId=[]gopayOutOrderId=[]tranIP=["
					+ goPay.getTranIP()
					+ "]respCode=[]gopayServerTime=[]VerficationCode=["
					+ goPay.getVerficationCode() + "]";

			String signValue = GopayUtils.md5(plain);
			goPay.setSignValue(signValue);
			mv.addObject("goPay", goPay);
		}
		logger.info("国付宝在线支付发送请求日志：请求记录结束");
		return mv;
	}

	/**
	 * 国付宝支付前台接收
	 * 
	 * @param goPay
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/goPayForntReceive/{userId}")
	public ModelAndView goPayForntReceive(
			@PathVariable("userId") Integer userId, GoPayVO goPay,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		logger.info("国付宝在线前台接收日志：记录开始");
		logger.info("国付宝在线前台接收日志：充值人：" + userId);
		logger.info("充值金额：" + goPay.getTranAmt());
		logger.info("订单号：" + goPay.getMerOrderNum());
		ModelAndView mv = new ModelAndView("redirect:/pay/paySuccess");
		// 组织加密明文
		String plain = "version=[" + goPay.getVersion() + "]tranCode=["
				+ goPay.getTranCode() + "]merchantID=[" + goPay.getMerchantID()
				+ "]merOrderNum=[" + goPay.getMerOrderNum() + "]tranAmt=["
				+ goPay.getTranAmt() + "]feeAmt=[" + goPay.getFeeAmt()
				+ "]tranDateTime=[" + goPay.getTranDateTime()
				+ "]frontMerUrl=[" + goPay.getFrontMerUrl()
				+ "]backgroundMerUrl=[" + goPay.getBackgroundMerUrl()
				+ "]orderId=[" + goPay.getOrderId() + "]gopayOutOrderId=["
				+ goPay.getGopayOutOrderId() + "]tranIP=[" + goPay.getTranIP()
				+ "]respCode=[" + goPay.getRespCode()
				+ "]gopayServerTime=[]VerficationCode=["
				+ goPay.getVerficationCode() + "]";

		String signValue = GopayUtils.md5(plain);
		logger.info("返回状态码：" + goPay.getRespCode());
		logger.info("国付宝在线前台接收日志：返回的加密信息:" + goPay.getSignValue());
		logger.info("国付宝在线前台接收日志：比对的加密：" + signValue);
		if (signValue.equals(goPay.getSignValue())) {
			if (goPay.getRespCode().equals("0000")) {
				this.doRechargeSuccess(goPay.getMerOrderNum(), new BigDecimal(
						goPay.getTranAmt()), (short) 32, userId, "国付宝在线充值");
				logger.info("国付宝在线前台接收日志：充值成功");
			} else {
				this.createAndSaveRecharge(goPay.getMerOrderNum(), "充值失败",
						new BigDecimal(goPay.getTranAmt()), "国付宝在线充值",
						(byte) 0, (short) 32, userId);
				mv.setViewName("redirect:/pay/payFaild");
				logger.info("国付宝在线前台接收日志：充值失败");
			}

		} else {
			logger.info("国付宝在线前台接收日志：加密信息错误");
			mv.setViewName("redirect:/pay/payFaild");
		}
		logger.info("国付宝在线前台接收日志：记录结束");
		return mv;
	}

	/**
	 * 充值成功
	 * 
	 * @return 2014年6月4日 liuyijun
	 */
	@RequestMapping(value = "/paySuccess")
	public ModelAndView paySuccess() {
		ModelAndView mv = ModelAndViewUtil
				.createModelAndViewAndAddAccountToView(accountService,
						"pay/success");
		return mv;
	}

	/**
	 * 充值失败
	 * 
	 * @return 2014年6月4日 liuyijun
	 */
	@RequestMapping(value = "/payFaild")
	public ModelAndView payFaild() {
		ModelAndView mv = ModelAndViewUtil
				.createModelAndViewAndAddAccountToView(accountService,
						"pay/faild");
		return mv;
	}

	/**
	 * 国付宝支付后台接收
	 * 
	 * @param goPay
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/goPayReceive/{userId}")
	public ResponseEntity<String> goPayReceive(
			@PathVariable("userId") Integer userId, GoPayVO goPay,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		logger.info("国付宝在线后台对账日志：记录开始");

		String data = "RespCode=9999|JumpURL="
				+ ModelAndViewUtil.createBaseUrl(request) + "/pay/payFaild";
		// 组织加密明文
		String plain = "version=[" + goPay.getVersion() + "]tranCode=["
				+ goPay.getTranCode() + "]merchantID=[" + goPay.getMerchantID()
				+ "]merOrderNum=[" + goPay.getMerOrderNum() + "]tranAmt=["
				+ goPay.getTranAmt() + "]feeAmt=[" + goPay.getFeeAmt()
				+ "]tranDateTime=[" + goPay.getTranDateTime()
				+ "]frontMerUrl=[" + goPay.getFrontMerUrl()
				+ "]backgroundMerUrl=[" + goPay.getBackgroundMerUrl()
				+ "]orderId=[" + goPay.getOrderId() + "]gopayOutOrderId=["
				+ goPay.getGopayOutOrderId() + "]tranIP=[" + goPay.getTranIP()
				+ "]respCode=[" + goPay.getRespCode()
				+ "]gopayServerTime=[]VerficationCode=["
				+ goPay.getVerficationCode() + "]";

		String signValue = GopayUtils.md5(plain);
		logger.info("充值人：" + userId);
		logger.info("返回状态码：" + goPay.getRespCode());
		logger.info("充值金额：" + goPay.getTranAmt());
		logger.info("订单号：" + goPay.getMerOrderNum());
		logger.info("国付宝在线后台对账日志：返回的加密信息:" + goPay.getSignValue());
		logger.info("国付宝在线后台对账日志：比对的加密：" + signValue);
		if (signValue.equals(goPay.getSignValue())) {
			if (goPay.getRespCode().equals("0000")) {
				this.doRechargeSuccess(goPay.getMerOrderNum(), new BigDecimal(
						goPay.getTranAmt()), (short) 32, userId, "国付宝在线充值");
				data = "RespCode=0000|JumpURL="
						+ ModelAndViewUtil.createBaseUrl(request)
						+ "/pay/paySuccess";
				logger.info("国付宝在线后台对账日志：充值成功");
			} else {
				this.createAndSaveRecharge(goPay.getMerOrderNum(), "充值失败",
						new BigDecimal(goPay.getTranAmt()), "国付宝在线充值",
						(byte) 0, (short) 32, userId);
				logger.info("国付宝在线后台对账日志：充值失败");
			}

		} else {
			logger.info("国付宝在线后台对账日志：充值失败，校验码错误");
		}
		logger.info("国付宝在线后台对账日志：记录结束");
		return new ResponseEntity<String>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/testString")
	public ResponseEntity<String> testString() {
		String data = "spring";
		return new ResponseEntity<String>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/testJson")
	@ResponseBody
	public Json testJson() {
		Json j=new Json();
		j.setSuccess(true);
		j.setMsg("test");
		return j;
	}

	/**
	 * 汇潮支付网关
	 * 
	 * @param ecpss
	 * @return 2014年5月4日 liuyijun
	 */
	@NeedSession
	@RequestMapping("/ecpss")
	public ModelAndView ecpss(EcpssVO ecpss, HttpServletRequest request) {
		logger.info("汇潮在线支付发送请求日志：记录开始");
		logger.info("汇潮在线支付发送请求日志：充值人：" + UserSession.getLoginUserId());
		ModelAndView mv = new ModelAndView("pay/ecpss/paysubmit");
		if (this.checkRealName(mv)) {
			ecpss.setAmount(ecpss.getRechargeMoney());
			String billNo = String.valueOf(System.currentTimeMillis());
			mv.addObject("BillNo", billNo);

			mv.addObject("ReturnURL", ModelAndViewUtil.createBaseUrl(request)
					+ ecpss.getReturnURL());

			mv.addObject("AdviceURL", ModelAndViewUtil.createBaseUrl(request)
					+ ecpss.getAdviceURL() + "/" + UserSession.getLoginUserId());
			String md5src; // 加密字符串
			md5src = ecpss.getMerNo() + "&" + billNo + "&" + ecpss.getAmount()
					+ "&" + ModelAndViewUtil.createBaseUrl(request)
					+ ecpss.getReturnURL() + "&" + ecpss.getMD5key();
			MD5 md5 = new MD5();
			mv.addObject("Amount", ecpss.getAmount());

			mv.addObject("defaultBankNumber", ecpss.getRechargeBankCode());
			mv.addObject("MerNo", ecpss.getMerNo());
			mv.addObject("SignInfo", md5.getMD5ofStr(md5src));
			mv.addObject("orderTime",
					DateUtil.getDateString(new Date(), "yyyyMMddHHmmss"));
		}
		logger.info("汇潮在线支付发送请求日志：记录结束");
		return mv;
	}

	/**
	 * 汇潮支付页面接收
	 * 
	 * @param ecpss
	 * @return 2014年5月4日 liuyijun
	 * @throws Exception
	 */
	@RequestMapping("/ecpssReceive")
	public ModelAndView ecpssReceive(EcpssVO ecpss) throws Exception {
		logger.info("汇潮支付在线前台接收日志：记录开始");
		logger.info("汇潮支付在线前台接收日志：充值人：" + UserSession.getLoginUserId());
		logger.info("汇潮支付在线前台接收日志：充值金额：" + ecpss.getAmount());
		logger.info("汇潮支付在线前台接收日志：订单号：" + ecpss.getBillNo());
		ModelAndView mv = new ModelAndView("redirect:/pay/payFaild");
		logger.info("返回状态码：" + ecpss.getSucceed());
		AccountRecharge re = this.accountRecharageService.selectByTradeNo(ecpss
				.getBillNo());
		if (EmptyUtil.isNotEmpty(re) && EmptyUtil.isNotEmpty(re.getStatus())
				&& re.getStatus() == 1) {
			mv.setViewName("redirect:/pay/paySuccess");
			logger.info("汇潮支付在线前台接收日志：充值成功");
		} else {
			MD5 md5 = new MD5();
			String md5src = ecpss.getBillNo() + "&" + ecpss.getAmount() + "&"
					+ ecpss.getSucceed() + "&" + ecpss.getMD5key();
			logger.info("汇潮支付在线前台接收日志：发送体加密信息：" + ecpss.getSignMD5info());
			logger.info("汇潮支付在线前台接收日志：接收加密信息：" + md5.getMD5ofStr(md5src));
			if (ecpss.getSignMD5info().equals(md5.getMD5ofStr(md5src))) {
				if (ecpss.getSucceed().equals("88")) {
					this.doRechargeSuccess(ecpss.getBillNo(), new BigDecimal(
							ecpss.getAmount()), (short) 54, UserSession
							.getLoginUserId(), "汇潮支付在线充值");
					mv.setViewName("redirect:/pay/paySuccess");
					logger.info("汇潮支付在线前台接收日志：充值成功");
				} else {
					this.createAndSaveRecharge(ecpss.getBillNo(), "充值失败",
							new BigDecimal(ecpss.getAmount()), "汇潮支付在线充值",
							(byte) 0, (short) 54);
					logger.info("汇潮支付在线前台接收日志：充值失败");
				}
			} else {
				logger.info("汇潮支付在线前台接收日志：充值失败，校验码错误呀");
			}
		}

		return mv;
	}

	/**
	 * 汇潮支付后台同步通知
	 * 
	 * @param ecpss
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/ecpssAdvice/{userId}")
	public void ecpssAdvice(@PathVariable("userId") Integer userId,
			EcpssVO ecpss, HttpServletResponse response) throws Exception {
		logger.info("汇潮支付在线后台对账日志：记录开始");
		MD5 md5 = new MD5();
		String md5src = ecpss.getBillNo() + "&" + ecpss.getAmount() + "&"
				+ ecpss.getSucceed() + "&" + ecpss.getMD5key();
		logger.info("充值人：" + userId);
		logger.info("充值金额：" + ecpss.getAmount());
		logger.info("订单号：" + ecpss.getBillNo());
		logger.info("返回状态码：" + ecpss.getSucceed());

		logger.info("汇潮支付在线后台对账日志：发送体加密信息：" + ecpss.getSignMD5info());
		logger.info("汇潮支付在线后台对账日志：接收加密信息：" + md5.getMD5ofStr(md5src));
		if (ecpss.getSignMD5info().equals(md5.getMD5ofStr(md5src))) {
			if (ecpss.getSucceed().equals("88")) {
				this.doRechargeSuccess(ecpss.getBillNo(),
						new BigDecimal(ecpss.getAmount()), (short) 54, userId,
						"汇潮支付在线充值");
				response.getWriter().write("ok");
				logger.info("汇潮支付在线后台对账日志：充值成功");
			} else {
				logger.info("汇潮支付在线后台对账日志：充值失败");
			}
		} else {
			logger.info("汇潮支付在线后台对账日志：充值失败，校验码错误呀");
		}

		logger.info("汇潮支付在线后台对账日志：记录结束");

	}

	/**
	 * 创建并保存充值对象，如果状态为1则更新用户的可用金额
	 * 
	 * @param tradeNo
	 * @param bankReturn
	 * @param money
	 * @param remark
	 * @param status
	 * @param payment
	 *            2014年4月30日 liuyijun
	 */
	private void createAndSaveRecharge(String tradeNo, String bankReturn,
			BigDecimal money, String remark, byte status, short payment) {
		AccountRecharge recharge = new AccountRecharge();
		recharge.setTradeNo(tradeNo);
		recharge.setAddtime(new Integer(DateUtil.getTime()));
		recharge.setBankReturn(bankReturn);
		recharge.setUserId(UserSession.getLoginUserId());
		recharge.setMoney(money);
		recharge.setRemark(remark);
		recharge.setStatus(status);
		recharge.setPayment(payment);
		recharge.setType((byte) 1);
		this.accountRecharageService.insertSelective(recharge);
	}

	/**
	 * 创建并保存充值对象，如果状态为1则更新用户的可用金额
	 * 
	 * @param tradeNo
	 * @param bankReturn
	 * @param money
	 * @param remark
	 * @param status
	 * @param payment
	 * @param userId
	 *            2014年5月4日 liuyijun
	 */
	private void createAndSaveRecharge(String tradeNo, String bankReturn,
			BigDecimal money, String remark, byte status, short payment,
			Integer userId) {
		AccountRecharge recharge = new AccountRecharge();
		recharge.setTradeNo(tradeNo);
		recharge.setAddtime(new Integer(DateUtil.getTime()));
		recharge.setBankReturn(bankReturn);
		recharge.setUserId(userId);
		recharge.setMoney(money);
		recharge.setRemark(remark);
		recharge.setStatus(status);
		recharge.setPayment(payment);
		recharge.setType((byte) 1);
		this.accountRecharageService.insertSelective(recharge);
	}

	/**
	 * 充值成功公用方法
	 * 
	 * @param tradeNo
	 * @param money
	 * @param payment
	 * @param userId
	 * @param from
	 *            2014年5月4日 liuyijun
	 * @throws Exception
	 */
	private void doRechargeSuccess(String tradeNo, BigDecimal money,
			short payment, Integer userId, String from) throws Exception {
		InterProcessLock lock = this.lockUtil.getLock("/lock/recharge");
		try {
			if (lock.acquire(120, TimeUnit.SECONDS)) {
				AccountRecharge re = this.accountRecharageService
						.selectByTradeNo(tradeNo);
				if (EmptyUtil.isNotEmpty(re)) {
					if (re.getStatus() != 1) {
						re.setStatus((byte) 1);
						re.setBankReturn("充值成功");
						re.setType((byte) 1);
						this.accountRecharageService.updateStatus(re);
					}
				} else {
					this.createAndSaveRecharge(tradeNo, "充值成功", money, from,
							(byte) 1, (short) payment, userId);
				}
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			lock.release();
		}

	}

}
