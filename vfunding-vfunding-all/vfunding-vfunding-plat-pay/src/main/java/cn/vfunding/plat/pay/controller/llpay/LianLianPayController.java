package cn.vfunding.plat.pay.controller.llpay;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.lock.LockUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.EncryptionUtil;
import cn.vfunding.common.framework.utils.beans.StringUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.plat.pay.common.utils.PayUtil;
import cn.vfunding.plat.pay.utils.llpay.Md5Algorithm;
import cn.vfunding.plat.pay.utils.llpay.PayDateUtil;
import cn.vfunding.plat.pay.utils.llpay.YinTongUtil;
import cn.vfunding.plat.pay.vo.llpay.PaymentInfo;
import cn.vfunding.plat.pay.vo.llpay.RetBean;
import cn.vfunding.plat.pay.vo.llpay.ReturnInfo;
import cn.vfunding.vfunding.biz.account.service.IAccountRechargeService;

import com.alibaba.fastjson.JSON;

@Controller()
@RequestMapping("/llPay")
public class LianLianPayController extends BaseController {

	Logger logger = LoggerFactory.getLogger("paylog");

	@Autowired
	private IAccountRechargeService accountRecharageService;
	
	@Autowired
	private LockUtil lockUtil;

	private static String MD5_KEY = "pay.vfunding.cn";

	/**
	 * 转向连连支付页面
	 * 
	 * @param chinaBank
	 * @return 2014年4月29日 liuyijun
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/llPay/{userIdInfo}/{rechargeMoney}")
	public ModelAndView llpay(PaymentInfo payInfo,
			@PathVariable("rechargeMoney") String rechargeMoney,
			@PathVariable("userIdInfo") String userIdInfo,
			HttpServletRequest request) throws Exception {
		logger.info("连连支付在线支付发送请求日志：记录开始");
		ModelAndView mv = new ModelAndView("pay/llpay/send");
		if (EmptyUtil.isNotEmpty(userIdInfo)) {
			Integer userId = new Integer(EncryptionUtil.decrypt(userIdInfo));
			logger.info("连连支付在线支付发送请求日志：充值人：" + userId + "，充值金额："
					+ rechargeMoney);
			if (EmptyUtil.isNotEmpty(rechargeMoney)) {
				payInfo.setDt_order(PayDateUtil.getCurrentDateTimeStr1());

				payInfo.setNo_order(String.valueOf(System.currentTimeMillis()));

				payInfo.setUser_id(userId.toString());

				BigDecimal b = new BigDecimal(rechargeMoney);

				String m = StringUtil.getTwoDecimals(b);
				payInfo.setMoney_order(m);
				 payInfo.setUrl_return("http://pay.vfunding.cn"
				 + payInfo.getUrl_return() + userIdInfo);

//				payInfo.setUrl_return("http://payvfunding.nat123.net"
//						+ payInfo.getUrl_return() + userIdInfo);
//
//				payInfo.setNotify_url("http://payvfunding.nat123.net"
//						+ payInfo.getNotify_url() + userIdInfo);

				payInfo.setNotify_url("http://pay.vfunding.cn"
						+ payInfo.getNotify_url() + userIdInfo);

				StringBuffer strBuf = new StringBuffer();

				strBuf.append("acct_name=");
				strBuf.append(payInfo.getAcct_name());

				strBuf.append("&app_request=");
				strBuf.append(payInfo.getApp_request());

				strBuf.append("&busi_partner=" + payInfo.getBusi_partner());

				strBuf.append("&dt_order=" + payInfo.getDt_order());

				strBuf.append("&id_no=" + payInfo.getId_no());

				strBuf.append("&id_type=" + payInfo.getId_type());

				strBuf.append("&money_order=");
				strBuf.append(payInfo.getMoney_order());

				strBuf.append("&name_goods=" + payInfo.getName_goods());

				strBuf.append("&no_order=");
				strBuf.append(payInfo.getNo_order());
				strBuf.append("&notify_url=");
				strBuf.append(payInfo.getNotify_url());
				strBuf.append("&oid_partner=");
				strBuf.append(payInfo.getOid_partner());

				strBuf.append("&sign_type=");
				strBuf.append(payInfo.getSign_type());
				if (EmptyUtil.isNotEmpty(payInfo.getUrl_return())) {
					strBuf.append("&url_return=");
					strBuf.append(payInfo.getUrl_return());
				}
				if (EmptyUtil.isNotEmpty(payInfo.getUser_id())) {
					strBuf.append("&user_id=");
					strBuf.append(payInfo.getUser_id());
				}

				String sign_src = strBuf.toString();
				if (sign_src.startsWith("&")) {
					sign_src = sign_src.substring(1);
				}
				String sign = "";

				sign_src += "&key=" + MD5_KEY;
				sign = Md5Algorithm.getInstance().md5Digest(
						sign_src.getBytes("utf-8"));
				payInfo.setSign(sign);
				String req_data = JSON.toJSONString(payInfo);
				mv.addObject("req_data", req_data);
			}
		} else {
			logger.error("连连支付在线支付错误：userId 为空");
			mv.setViewName("pay/faild");
		}

		return mv;
	}

	/**
	 * 
	 * 
	 * @param chinaBankReceive
	 * @return 2014年4月29日 liuyijun
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/llPayReceive/{userIdInfo}")
	public ModelAndView llpayReceive(@RequestParam("res_data") String res_data,
			@PathVariable("userIdInfo") String userIdInfo) throws Exception {
		logger.info("连连支付前台接收日志：记录开始");
		Integer userId = new Integer(EncryptionUtil.decrypt(userIdInfo));

		ModelAndView mv = new ModelAndView(
				"redirect:http://m.vfunding.cn/payResult/success");

//		ModelAndView mv = new ModelAndView(
//				"redirect:http://m.vfunding.cn.nat123.net/payResult/success");
		if (EmptyUtil.isNotEmpty(res_data)) {
			ReturnInfo info = JSON.parseObject(res_data, ReturnInfo.class);
			logger.info("连连支付前台接收日志：充值人：" + userId + "，实际充值金额："
					+ info.getMoney_order());
			if (EmptyUtil.isNotEmpty(info)
					&& EmptyUtil.isNotEmpty(info.getResult_pay())) {
				String sign = PayUtil.getSignByReturnInfo(info, MD5_KEY);
				if (sign.equals(info.getSign())) {
					if (info.getResult_pay().equals("SUCCESS")) {
						PayUtil.doRechargeSuccess(info.getNo_order(),
								new BigDecimal(info.getMoney_order()),
								(short) 56, userId, "连连支付在线充值",
								accountRecharageService,this.lockUtil);
						logger.info("连连支付前台接收日志：充值成功");
					} else if (info.getResult_pay().equals("FAILURE")) {
						mv.setViewName("redirect:http://m.vfunding.cn/payResult/faild");
						PayUtil.createAndSaveRecharge(info.getNo_order(),
								info.getResult_pay(),
								new BigDecimal(info.getMoney_order()),
								"连连支付在线充值", (byte) 0, (short) 56, userId,
								accountRecharageService);
						logger.error("连连支付前台接收日志：充值失败");
					}
				} else {
					logger.error("连连支付前台接收日志：充值失败,签名错误,该失败不记录充值记录");
				}

			} else {
				logger.error("连连支付前台接收日志：充值失败,该失败不记录充值记录");
			}
		}

		return mv;
	}

	/**
	 * 连连支付自动对账
	 * 
	 * @param chinaBankReceive
	 * @return 2014年4月30日 liuyijun
	 * @throws Exception
	 */
	@RequestMapping(value = "/llPayAutoReceive/{userIdInfo}")
	public void llpayAutoReceive(@PathVariable("userIdInfo") String userIdInfo,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {//

		logger.info("连连支付后台对账日志：记录开始");
		Integer userId = new Integer(EncryptionUtil.decrypt(userIdInfo));

		resp.setCharacterEncoding("UTF-8");
		RetBean retBean = new RetBean();
		String reqStr = YinTongUtil.readReqStr(req);

		if (YinTongUtil.isnull(reqStr)) {
			retBean.setRet_code("9999");
			retBean.setRet_msg("交易失败");
			resp.getWriter().write(JSON.toJSONString(retBean));
			resp.getWriter().flush();
			return;
		}
		logger.info("接收支付异步通知数据：【" + reqStr + "】");

		try {
			if (!YinTongUtil.checkSign(reqStr, "", MD5_KEY)) {
				retBean.setRet_code("9999");
				retBean.setRet_msg("交易失败");
				resp.getWriter().write(JSON.toJSONString(retBean));
				resp.getWriter().flush();
				logger.error("支付异步通知验签失败");
				return;
			}
		} catch (Exception e) {
			logger.error("异步通知报文解析异常：" + e);
			retBean.setRet_code("9999");
			retBean.setRet_msg("交易失败");
			resp.getWriter().write(JSON.toJSONString(retBean));
			resp.getWriter().flush();
			return;
		}
		ReturnInfo info = JSON.parseObject(reqStr, ReturnInfo.class);
		logger.info("充值人：" + userId + "，实际充值金额：" + info.getMoney_order());
		if (info.getResult_pay().equals("SUCCESS")) {
			PayUtil.doRechargeSuccess(info.getNo_order(),
					new BigDecimal(info.getMoney_order()), (short) 56, userId,
					"连连支付在线充值", accountRecharageService,this.lockUtil);

			retBean.setRet_code("0000");
			retBean.setRet_msg("交易成功");
			resp.getWriter().write(JSON.toJSONString(retBean));
			resp.getWriter().flush();
			logger.info("连连支付后台对账日志：充值成功");
		} else {
			logger.error("连连支付后台对账日志：充值失败");
		}

		logger.info("连连支付后台对账日志：记录结束");

	}
	
	
	/**
	 * 连连支付手机APP充值后台处理
	 * 
	 * @param chinaBankReceive
	 * @return 2014年4月30日 liuyijun
	 * @throws Exception
	 */
	@RequestMapping(value = "/llPayAutoReceiveMobile/{userId}")
	public void llPayAutoReceiveMobile(@PathVariable("userId") Integer userId,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {//

		logger.info("连连支付手机APP充值后台对账日志：记录开始");
		resp.setCharacterEncoding("UTF-8");
		RetBean retBean = new RetBean();
		String reqStr = YinTongUtil.readReqStr(req);

		if (YinTongUtil.isnull(reqStr)) {
			retBean.setRet_code("9999");
			retBean.setRet_msg("交易失败");
			resp.getWriter().write(JSON.toJSONString(retBean));
			resp.getWriter().flush();
			return;
		}
		logger.info("接收支付异步通知数据：【" + reqStr + "】");

		try {
			if (!YinTongUtil.checkSign(reqStr, "", MD5_KEY)) {
				retBean.setRet_code("9999");
				retBean.setRet_msg("交易失败");
				resp.getWriter().write(JSON.toJSONString(retBean));
				resp.getWriter().flush();
				logger.error("支付异步通知验签失败");
				return;
			}
		} catch (Exception e) {
			logger.error("异步通知报文解析异常：" + e);
			retBean.setRet_code("9999");
			retBean.setRet_msg("交易失败");
			resp.getWriter().write(JSON.toJSONString(retBean));
			resp.getWriter().flush();
			return;
		}
		ReturnInfo info = JSON.parseObject(reqStr, ReturnInfo.class);
		logger.info("充值人：" + userId + "，实际充值金额：" + info.getMoney_order());
		if (info.getResult_pay().equals("SUCCESS")) {
			PayUtil.doRechargeSuccess(info.getNo_order(),
					new BigDecimal(info.getMoney_order()), (short) 56, userId,
					"连连支付手机APP充值", accountRecharageService,this.lockUtil);

			retBean.setRet_code("0000");
			retBean.setRet_msg("交易成功");
			resp.getWriter().write(JSON.toJSONString(retBean));
			resp.getWriter().flush();
			logger.info("连连支付手机APP充值后台对账日志：充值成功");
		} else {
			logger.error("连连支付手机APP充值后台对账日志：充值失败");
		}

		logger.info("连连支付手机APP充值后台对账日志：记录结束");

	}

}
