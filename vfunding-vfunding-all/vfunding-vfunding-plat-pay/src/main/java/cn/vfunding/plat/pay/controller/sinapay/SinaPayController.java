package cn.vfunding.plat.pay.controller.sinapay;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.lock.LockUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.EncryptionUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.plat.pay.common.utils.PayUtil;
import cn.vfunding.plat.pay.utils.llpay.PayDateUtil;
import cn.vfunding.plat.pay.utils.sinapay.MD5;
import cn.vfunding.plat.pay.vo.sinapay.SinaPayInfo;
import cn.vfunding.plat.pay.vo.sinapay.SinaPayReturnInfo;
import cn.vfunding.vfunding.biz.account.service.IAccountRechargeService;

@Controller
@RequestMapping("/sinaPay")
public class SinaPayController extends BaseController {

	Logger logger = LoggerFactory.getLogger("paylog");

	private static String WEIBO_KEY = "VfdWcf_928.cn.vfunding";
	
	@Autowired
	private LockUtil lockUtil;

	@Autowired
	private IAccountRechargeService accountRecharageService;

	/**
	 * 转向新浪支付
	 * 
	 * @param chinaBank
	 * @return 2014年4月29日 liuyijun
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/sinaPay/{userIdInfo}")
	public ModelAndView sinapay(SinaPayInfo payInfo,
			@RequestParam("rechargeMoney") String rechargeMoney,
			@PathVariable("userIdInfo") String userIdInfo,
			@RequestParam("rechargeBankCode") String rechargeBankCode)
			throws Exception {
		logger.info("新浪支付在线支付发送请求日志：记录开始");
		ModelAndView mv = new ModelAndView("pay/weibopay/send");
		if (EmptyUtil.isNotEmpty(userIdInfo)) {
			Integer userId = new Integer(EncryptionUtil.decrypt(userIdInfo));
			logger.info("新浪支付在线支付发送请求日志：充值人：" + userId + "，充值金额："
					+ rechargeMoney);

			if (EmptyUtil.isNotEmpty(rechargeMoney)) {
				payInfo.setBgUrl("http://pay.vfunding.cn"
						+ payInfo.getBgUrl() + userIdInfo);
				payInfo.setCancelUrl("http://pay.vfunding.cn"
						+ payInfo.getCancelUrl());
				payInfo.setOrderAmount(new Integer(rechargeMoney) * 100);
				payInfo.setOrderId(String.valueOf(System.currentTimeMillis()));
				payInfo.setOrderTime(PayDateUtil.getCurrentDateTimeStr1());
				if (EmptyUtil.isNotEmpty(rechargeBankCode)) {
					payInfo.setBankId(rechargeBankCode);
				}
				String signStr = PayUtil.getSignStrByWeiboPayInfo(payInfo)
						+ "&key=" + WEIBO_KEY;
				payInfo.setSignMsg(MD5.getMD5(signStr.getBytes()).toLowerCase());
				mv.addObject("payInfo", payInfo);
			}
		} else {
			logger.error("新浪支付在线支付错误：userId 为空");
			mv.setViewName("pay/faild");
		}

		return mv;

	}

	@RequestMapping("/sinaPayReceive/{userIdInfo}")
	@ResponseBody
	public String sinaPayReceive(@PathVariable("userIdInfo") String userIdInfo,
			SinaPayReturnInfo returnInfo) throws Exception {
		logger.info("新浪支付接收日志：记录开始");
		String result = "<result>2</result>";
		if (EmptyUtil.isNotEmpty(userIdInfo)) {
			String signStr = PayUtil.getSignStrBySinaPayReturnInfo(returnInfo)
					+ "&key=" + WEIBO_KEY;
			String md5Str = MD5.getMD5(signStr.getBytes());
			if (md5Str.equals(returnInfo.getSignMsg())) {
				 
				Integer userId = new Integer(EncryptionUtil.decrypt(userIdInfo));
				logger.info("新浪支付接收日志：充值人：" + userId + "，实际充值金额："
						+ returnInfo.getPayAmount() / 100);
				if (returnInfo.getPayResult().equals(new Integer("10"))) {
					PayUtil.doRechargeSuccess(returnInfo.getOrderId(),
							new BigDecimal(returnInfo.getPayAmount() / 100),
							(short) 57, userId, "新浪支付在线充值",
							accountRecharageService,this.lockUtil);
					result = "<result>1</result><redirecturl>http://www.vfunding.cn/pay/paySuccess</redirecturl>";
					logger.info("新浪支付接收日志：充值成功");
				} else {
					logger.error("新浪支付接收日志：充值失败");
				}
			} else {
				logger.error("新浪支付接收日志：签名错误");
			}
		}
		return result;
	}

	@RequestMapping("/sinaPayCancel")
	public ModelAndView sinaPayCancel() {
		ModelAndView mv = new ModelAndView(
				"redirect:http://www.vfunding.cn/account/recharge");
		return mv;
	}
}
