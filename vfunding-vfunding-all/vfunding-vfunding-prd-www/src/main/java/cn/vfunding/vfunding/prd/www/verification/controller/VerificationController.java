package cn.vfunding.vfunding.prd.www.verification.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.send.util.SendCommon;
import cn.vfunding.common.framework.send.util.SendCommonUtil;
import cn.vfunding.common.framework.server.interceptors.NeedSession;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.VerifyIdCard;
import cn.vfunding.common.framework.utils.kaptcha.VerifyCodeUtils;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.session.utils.HttpSessionTool;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.common.module.activemq.message.SendVerifyCodeMessage;
import cn.vfunding.vfunding.common.module.activemq.message.SendVoiceCodeMessage;
import cn.vfunding.vfunding.common.sendconfig.ISendConfigUtil;

/**
 * @author liuyijun 2014年4月9日
 */
@Controller
@RequestMapping(value = "/verification")
public class VerificationController extends BaseController {
	@Autowired
	private JmsSenderService jmsSenderUtil;
	@Autowired
	private IUserService userService;
	@Value("${send.sms.status}")
	private Integer smsStatus;

	/**
	 * 申请提现验证码
	 * 
	 * @param mobile
	 * @param httpSession
	 * @return
	 * @throws Exception
	 *             2014年6月19日 liuyijun
	 */
	@RequestMapping(value = "/getTakeMoneyVerifyCode/{mobile:[\\d]+}", method = RequestMethod.GET)
	@ResponseBody
	public String getTakeMoneyVerifyCode(@PathVariable("mobile") String mobile,
			HttpSession httpSession) throws Exception {
		BigDecimal takeMoney = (BigDecimal) httpSession
				.getAttribute("takeMoney");
		if (EmptyUtil.isNotEmpty(takeMoney)) {
			String verifyCode = VerifyCodeUtils.createMobileVerifyCode(6,
					httpSession);
			String[] args = new String[] { verifyCode };
			return this.getCodeCommon(mobile, httpSession, args, false,ISendConfigUtil.SMS_TACKCASH_CHECK);
		} else {
			throw new BusinessException("8005012", "错误的请求");
		}
	}

	/**
	 * 注册验证码，注册时获取手机验证码 及发送验证短信
	 * 
	 * @param mobile
	 *            手机号码
	 * @param httpSession
	 * @return
	 * @throws Exception
	 * @author liuyijun 2014年4月9日
	 */
	@RequestMapping(value = "/getRegistVerifyCode/{mobile:[\\d]+}", method = RequestMethod.GET)
	@ResponseBody
	public String getVerifyCode(@PathVariable("mobile") String mobile,
			HttpSession httpSession) throws Exception {
		String verifyCode = VerifyCodeUtils.createMobileVerifyCode(6,
				httpSession);
		String[] args=new String[]{verifyCode};
		return this
				.getCodeCommon(mobile, httpSession, args, true,ISendConfigUtil.SMS_REGISTER_CHECK);

	}

	/**
	 * 通过手机找回支付密码是获取短信验证码
	 * 
	 * @param mobile
	 * @param httpSession
	 * @return
	 * @throws Exception
	 *             2014年8月20日 liuyijun
	 */
	@RequestMapping(value = "/getVerifyCodeForFindPayPwd", method = RequestMethod.GET)
	@ResponseBody
	@NeedSession(logRequestTime = false)
	public String getVerifyCodeForFindPayPwd(HttpSession httpSession)
			throws Exception {
		String verifyCode = VerifyCodeUtils.createMobileVerifyCode(6,
				httpSession);
		String[] args=new String[]{verifyCode};
		String mobile = UserSession.getUserSession().getPhone();
		boolean canGet = (Boolean) (EmptyUtil.isNotEmpty(httpSession
				.getAttribute("canGetCodeForFindPayPwd")) ? httpSession
				.getAttribute("canGetCodeForFindPayPwd") : false);
		if (canGet) {
			return this.getCodeCommon(mobile, httpSession, args,
					false,ISendConfigUtil.SMS_FINDPAYPWD);
		} else {
			throw new BusinessException("8005012", "错误的请求");
		}

	}

	/**
	 * 修改手机时获取验证码
	 * 
	 * @param mobile
	 * @param httpSession
	 * @return
	 * @throws Exception
	 *             2014年5月12日 liuyijun
	 */
	@NeedSession(logRequestTime = false)
	@RequestMapping(value = "/getModifyPhoneVerifyCode", method = RequestMethod.GET)
	@ResponseBody
	public String getModifyPhoneVerifyCode(HttpSession httpSession)
			throws Exception {
		String verifyCode = VerifyCodeUtils.createMobileVerifyCode(6,
				httpSession);
		String[] args=new String[]{verifyCode};
		return this.getCodeCommon(UserSession.getUserSession().getPhone(),
				httpSession, args, false,ISendConfigUtil.SMS_MODIFYPHONE);
	}

	/**
	 * 修改手机时新手机获取验证码
	 * 
	 * @param mobile
	 * @param httpSession
	 * @return
	 * @throws Exception
	 *             2014年6月19日 liuyijun
	 */
	@NeedSession(logRequestTime = false)
	@RequestMapping(value = "/getModifyPhoneVerifyCodeNew/{mobile:[\\d]+}", method = RequestMethod.GET)
	@ResponseBody
	public String getModifyPhoneVerifyCodeNew(
			@PathVariable("mobile") String mobile, HttpSession httpSession)
			throws Exception {
		if (this.userService.checkRegister(mobile)) {
			String verifyCode = VerifyCodeUtils.createMobileVerifyCode(6,
					httpSession);
			String[] args=new String[]{verifyCode};
			return this.getCodeCommon(UserSession.getUserSession().getPhone(),
					httpSession, args, false,ISendConfigUtil.SMS_MODIFYPHONE);
		} else {
			throw new BusinessException("8005012", "错误的请求");
		}

	}

	/**
	 * 添加或验证手机时发送验证短信
	 * 
	 * @param mobile
	 * @param httpSession
	 * @return
	 * @throws Exception
	 *             2014年6月24日 liuyijun
	 */
	@NeedSession(logRequestTime = false)
	@RequestMapping(value = "/getVCodeForPhoneState/{mobile:[\\d]+}", method = RequestMethod.GET)
	@ResponseBody
	public String getVerifyCodeForAddOrCheckPhoneState(
			@PathVariable("mobile") String mobile, HttpSession httpSession)
			throws Exception {
		boolean canSend = false;
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
			canSend = true;
		} else {
			if (this.userService.checkRegister(mobile)) {
				canSend = true;
			}
		}
		if (canSend) {
			String verifyCode = VerifyCodeUtils.createMobileVerifyCode(6,
					httpSession);
			String[] args=new String[]{verifyCode};
			return this.getCodeCommon(mobile, httpSession, args,
					false,ISendConfigUtil.SMS_CHECKPHONE);
		} else {
			throw new BusinessException("8005012", "错误的请求");
		}

	}

	/**
	 * 获取短信验证码(找回密码)
	 * 
	 * @param httpSession
	 * @return
	 * @throws Exception
	 *             2014年5月26日 liuyijun
	 */
	@RequestMapping(value = "/getFindPwdVerifyCode/{mobile:[\\d]+}", method = RequestMethod.GET)
	@ResponseBody
	public String getFindPwdVerifyCode(@PathVariable("mobile") String mobile,
			HttpSession httpSession) throws Exception {
		UserWithBLOBs user = (UserWithBLOBs) httpSession
				.getAttribute("findPwdUser");
		if (EmptyUtil.isNotEmpty(user)) {
			if (user.getPhone().equals(mobile)) {
				String verifyCode = VerifyCodeUtils.createMobileVerifyCode(6,
						httpSession);
				String[] args=new String[]{verifyCode};
				if (EmptyUtil.isNotEmpty(user)) {
					return this.getCodeCommon(user.getPhone(), httpSession,
							args, false,ISendConfigUtil.SMS_FINDPWD);
				} else {
					throw new BusinessException("8005012", "错误的请求");
				}
			} else {
				throw new BusinessException("8005012", "错误的请求");
			}
		} else {
			throw new BusinessException("8005012", "错误的请求");
		}

	}

	/**
	 * 提现语音验证码
	 * 
	 * @param mobile
	 * @param httpSession
	 * @return
	 * @throws Exception
	 *             2014年6月19日 liuyijun
	 */
	@RequestMapping(value = "/getTakeMoneyVerifyCodeByVoice/{mobile:[\\d]+}", method = RequestMethod.GET)
	@ResponseBody
	public String getTakeMoneyVerifyCodeByVoice(
			@PathVariable("mobile") String mobile, HttpSession httpSession)
			throws Exception {
		BigDecimal takeMoney = (BigDecimal) httpSession
				.getAttribute("takeMoney");
		if (EmptyUtil.isNotEmpty(takeMoney)) {
			return this.getVoiceCommon(mobile, httpSession, false);
		} else {
			throw new BusinessException("8005012", "错误的请求");
		}
	}

	/**
	 * 注册时获取语音验证码
	 * 
	 * @param mobile
	 * @param httpSession
	 * @return
	 * @throws Exception
	 *             2014年5月12日 liuyijun
	 */
	@RequestMapping(value = "/getRegistVerifyCodeByVoice/{mobile:[\\d]+}", method = RequestMethod.GET)
	@ResponseBody
	public String getVerifyCodeByVoice(@PathVariable("mobile") String mobile,
			HttpSession httpSession) throws Exception {
		return this.getVoiceCommon(mobile, httpSession, true);
	}

	/**
	 * 通过手机找回支付密码时获取语音验证码
	 * 
	 * @param mobile
	 * @param httpSession
	 * @return
	 * @throws Exception
	 *             2014年5月12日 liuyijun
	 */
	@RequestMapping(value = "/getVerifyCodeByVoiceForFindPayPwd", method = RequestMethod.GET)
	@ResponseBody
	@NeedSession(logRequestTime = false)
	public String getVerifyCodeByVoiceForFindPayPwd(HttpSession httpSession)
			throws Exception {
		String mobile = UserSession.getUserSession().getPhone();
		boolean canGet = (Boolean) (EmptyUtil.isNotEmpty(httpSession
				.getAttribute("canGetCodeForFindPayPwd")) ? httpSession
				.getAttribute("canGetCodeForFindPayPwd") : false);
		if (canGet) {
			return this.getVoiceCommon(mobile, httpSession, false);
		} else {
			throw new BusinessException("8005012", "错误的请求");
		}
	}

	/**
	 * 获取语音验证码修改时用
	 * 
	 * @param mobile
	 * @param httpSession
	 * @return
	 * @throws Exception
	 *             2014年5月12日 liuyijun
	 */
	@RequestMapping(value = "/getVerifyCodeByVoiceForModify", method = RequestMethod.GET)
	@ResponseBody
	@NeedSession(logRequestTime = false)
	public String getVerifyCodeByVoiceForModifyNew(HttpSession httpSession)
			throws Exception {
		return this.getVoiceCommon(UserSession.getUserSession().getPhone(),
				httpSession, false);

	}

	/**
	 * 获取语音验证码修改时新手机用
	 * 
	 * @param mobile
	 * @param httpSession
	 * @return
	 * @throws Exception
	 *             2014年5月12日 liuyijun
	 */
	@RequestMapping(value = "/getVerifyCodeByVoiceForModifyNew/{mobile:[\\d]+}", method = RequestMethod.GET)
	@ResponseBody
	@NeedSession(logRequestTime = false)
	public String getVerifyCodeByVoiceForModifyNew(
			@PathVariable("mobile") String mobile, HttpSession httpSession)
			throws Exception {
		if (this.userService.checkRegister(mobile)) {
			return this.getVoiceCommon(mobile, httpSession, false);
		} else {
			throw new BusinessException("8005012", "错误的请求");
		}

	}

	/**
	 * 添加手机或手机认证时用
	 * 
	 * @param mobile
	 * @param httpSession
	 * @return
	 * @throws Exception
	 *             2014年6月24日 liuyijun
	 */
	@RequestMapping(value = "/getVCodeByVoiceForPhoneState/{mobile:[\\d]+}", method = RequestMethod.GET)
	@ResponseBody
	public String getVerifyCodeByVoiceForAddOrCheckPhoneState(
			@PathVariable("mobile") String mobile, HttpSession httpSession)
			throws Exception {
		boolean canSend = false;
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
			canSend = true;
		} else {
			if (this.userService.checkRegister(mobile)) {
				canSend = true;
			}
		}

		if (canSend) {
			return this.getVoiceCommon(mobile, httpSession, false);
		} else {
			throw new BusinessException("8005012", "错误的请求");
		}

	}

	/**
	 * 获取语音验证码找回密码
	 * 
	 * @param mobile
	 * @param httpSession
	 * @return
	 * @throws Exception
	 *             2014年5月12日 liuyijun
	 */
	@RequestMapping(value = "/getVerifyCodeByVoiceForFindPwd/{mobile:[\\d]+}", method = RequestMethod.GET)
	@ResponseBody
	public String getVerifyCodeByVoiceForFindPwd(
			@PathVariable("mobile") String mobile, HttpSession httpSession)
			throws Exception {
		UserWithBLOBs user = (UserWithBLOBs) httpSession
				.getAttribute("findPwdUser");
		if (EmptyUtil.isNotEmpty(user)) {
			if (user.getPhone().equals(mobile)) {
				String verifyCode = VerifyCodeUtils.createMobileVerifyCode(6,
						httpSession);
				StringBuilder content = new StringBuilder();
				content.append("您申请更改登录密码的验证码是：");
				content.append(verifyCode);
				content.append("，请在1分钟内提交！");
				if (EmptyUtil.isNotEmpty(user)) {
					return this.getVoiceCommon(user.getPhone(), httpSession,
							false);
				} else {
					throw new BusinessException("8005012", "错误的请求");
				}
			} else {
				throw new BusinessException("8005012", "错误的请求");
			}
		} else {
			throw new BusinessException("8005012", "错误的请求");
		}

	}

	/**
	 * 获取验证码图片
	 * 
	 * @param width
	 *            图片宽度
	 * @param height
	 *            图片高度
	 * @param httpSession
	 * @param response
	 * @throws Exception
	 *             2014年4月25日 liuyijun
	 */
	@RequestMapping(value = "/getGenImage/{width}/{height}", method = RequestMethod.GET)
	public void getGenImage(@PathVariable("width") Integer width,
			@PathVariable("height") Integer height, HttpSession httpSession,
			HttpServletResponse response) throws Exception {
		response.addHeader("Content-Control", "post-check=0, pre-check=0");
		response.addHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Content-Type", "image/jpg");
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4, httpSession);
		VerifyCodeUtils.outputImage(width, height, response.getOutputStream(),
				verifyCode);
	}

	/**
	 * 验证验证码
	 * 
	 * @param verifyCode
	 *            验证码
	 * @param httpSession
	 * @return
	 * @author liuyijun 2014年4月10日
	 */
	@RequestMapping("/checkVerifyCode/{verifyCode}")
	@ResponseBody
	public Json checkVerifyCode(@PathVariable("verifyCode") String verifyCode,
			HttpSession httpSession) {
		Json j = new Json();
		if (VerifyCodeUtils.check(httpSession, verifyCode)) {
			j.setSuccess(true);
		} else {
			j.setMsg("验证码错误");
		}
		return j;
	}

	/**
	 * 验证验证码
	 * 
	 * @param verifyCode
	 *            验证码
	 * @param httpSession
	 * @return
	 * @author liuyijun 2014年4月10日1
	 */
	@RequestMapping("/checkVerifyCodeMethod")
	@ResponseBody
	public boolean checkVerifyCodeMethod(
			@RequestParam("verifyCode") String verifyCode,
			HttpSession httpSession) {
		boolean j = false;
		if (VerifyCodeUtils.check(httpSession, verifyCode) || smsStatus == 0) {
			j = true;
		}
		return j;
	}

	/**
	 * 检测身份证是否可注册(是否合法并且未被注册)
	 * 
	 * @param cardId
	 *            身份证
	 * @return
	 * @author liuyijun 2014年4月10日1
	 */
	@RequestMapping("/checkCardIdCanRegister")
	@ResponseBody
	public boolean checkCardIdCanRegister(
			@RequestParam("cardId") String cardId, HttpSession httpSession) {
		boolean j = false;
		if (VerifyIdCard.verify(cardId)) {
			List<UserWithBLOBs> users = this.userService.selectByCardId(cardId);
			if (EmptyUtil.isEmpty(users)) {
				j = true;
			}
		}
		return j;
	}

	/**
	 * 验证交易密码
	 * 
	 * @param payPassword
	 * @return 2014年5月10日 liuyijun
	 */
	@NeedSession(logRequestTime = false)
	@RequestMapping(value = "/checkPayPassword", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkPayPassword(
			@RequestParam("payPassword") String payPassword) {
		if (DigestUtils.md5Hex(payPassword).equals(
				UserSession.getUserSession().getPayPassword())) {
			return true;
		}
		return false;
	}

	/**
	 * 网站联盟发送短信验证码（网站联盟项目发短信对接接口）
	 * 
	 * @param message
	 *            2014年5月23日 liuyijun
	 */
	@RequestMapping(value = "/unionSendSms", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public void unionSendSms(@RequestBody SendVerifyCodeMessage message) {
		this.sendVerifyJMS(message);
	}

	/**
	 * 网站联盟发送语音短信验证码（网站联盟项目发短信对接接口）
	 * 
	 * @param message
	 *            2014年5月23日 liuyijun
	 */
	@RequestMapping(value = "/unionSendVoiceSms", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public void unionSendSms(@RequestBody SendVoiceCodeMessage message) {
		this.sendVerifyJMS(message);
	}

	/**
	 * 找回密码时检测用户名信息
	 * 
	 * @param value
	 * @return 2014年5月26日 liuyijun
	 */
	@RequestMapping(value = "/checkValueForFindPwd", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkValueForFindPwd(@RequestParam("value") String value) {
		boolean result = false;
		List<UserWithBLOBs> u = this.userService.selectByLogin(value);
		if (EmptyUtil.isNotEmpty(u)) {
			if (u.size() == 1) {
				result = true;
			}
		}
		return result;
	}

	@RequestMapping(value = "/checkPhoneForFindPwd", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkPhoneForFindPwd(@RequestParam("mobile") String mobile,
			HttpSession httpSession) {
		boolean result = false;
		UserWithBLOBs user = (UserWithBLOBs) httpSession
				.getAttribute("findPwdUser");
		if (mobile.equals(user.getPhone())) {
			result = true;
		}
		return result;
	}

	/**
	 * 发送短信消息
	 * 
	 * @param message
	 *            2014年5月15日 liuyijun
	 */
	private void sendVerifyJMS(SendVerifyCodeMessage message) {
		this.jmsSenderUtil.asynSendSystemJms(message);
	}

	/**
	 * 发送语音消息
	 * 
	 * @param message
	 *            2014年5月15日 liuyijun
	 */
	private void sendVerifyJMS(SendVoiceCodeMessage message) {
		this.jmsSenderUtil.asynSendSystemJms(message);
	}

	/**
	 * 添加注册码请求信息
	 * 
	 * @param httpSession
	 *            2014年5月15日 liuyijun
	 */
	private void addRegisterVerifyCodeInfo(HttpSession httpSession) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("lastTime", DateUtil.getLongTime());
		HttpSessionTool.addRegisterVerifyCodeInfo(httpSession, info);
	}

	/**
	 * 检验注册时是否可发送验证码(防止恶意攻击)
	 * 
	 * @param httpSession
	 * @return 2014年5月15日 liuyijun
	 */
	private boolean isCanSendForRegister(HttpSession httpSession, String mobile) {
		boolean canSend = false;
		if (this.userService.checkRegister(mobile)) {
			if (EmptyUtil.isNotEmpty(httpSession)) {
				if (HttpSessionTool.checkRegisteringUserInfo(httpSession)) {
					canSend = this.canSendFromSession(httpSession);
				}
			}
		} else {
			throw new BusinessException("8005030", "手机号码不可注册");
		}
		return canSend;
	}

	/**
	 * 检验修改资料或申请验证时是否可发送验证码(防止恶意攻击)
	 * 
	 * @param httpSession
	 * @return 2014年5月15日 liuyijun
	 */
	private boolean isCanSendForModify(HttpSession httpSession) {
		boolean canSend = false;
		if (EmptyUtil.isNotEmpty(httpSession)) {
			canSend = this.canSendFromSession(httpSession);
		}
		return canSend;
	}

	/**
	 * 从session中检测是否可发送短信
	 * 
	 * @param httpSession
	 * @return 2014年5月19日 liuyijun
	 */
	private boolean canSendFromSession(HttpSession httpSession) {
		boolean canSend = false;
		Map<String, Object> codeMap = HttpSessionTool
				.getRegisterVerifyCodeInfo(httpSession);
		if (EmptyUtil.isNotEmpty(codeMap)) {
			Long lastTime = (Long) codeMap.get("lastTime");
			if ((DateUtil.getLongTime() - lastTime) > 60) {
				canSend = true;
			}
		} else {
			canSend = true;
		}
		return canSend;
	}

	/**
	 * 获取手机验证通用方法
	 * 
	 * @param httpSession
	 * @param content
	 * @param checkSessionUserName
	 *            表示是否需要验证session中的用户名信息
	 * @return 2014年5月26日 liuyijun
	 */
	private String getCodeCommon(String mobile, HttpSession httpSession,
			String[] args, boolean checkSessionUserName,String smsKey) {

		boolean canSend = false;
		if (checkSessionUserName) {
			canSend = this.isCanSendForRegister(httpSession, mobile);
		} else {
			canSend = this.isCanSendForModify(httpSession);
		}
		if (canSend) {
			this.sendTempletSms(mobile, args,smsKey);
			this.addRegisterVerifyCodeInfo(httpSession);
			return "ok";
		} else {
			throw new BusinessException("8005012", "错误的请求");
		}

	}

	/**
	 * 通过模板发送短信
	 * 
	 * @param mobile
	 * @param args
	 */
	private void sendTempletSms(String mobile, String[] args, String smsKey) {
		SendCommon sendCommon = SendCommonUtil.createSmsSendCommon(mobile,
				smsKey, args);
		//this.jmsSender.sendAsynchronousMessage(sendCommon);
		this.jmsSenderUtil.asynSendSystemJms(sendCommon);
	}

	/**
	 * 获取语音验证码通用方法
	 * 
	 * @param mobile
	 * @param httpSession
	 * @param content
	 * @param checkSessionUserName
	 *            是否需要从session中验证用户名信息
	 * @return 2014年5月26日 liuyijun
	 */
	private String getVoiceCommon(String mobile, HttpSession httpSession,
			boolean checkSessionUserName) {

		boolean canSend = false;
		if (checkSessionUserName) {
			canSend = this.isCanSendForRegister(httpSession, mobile);
		} else {
			canSend = this.isCanSendForModify(httpSession);
		}
		if (canSend) {
			String verifyCode = VerifyCodeUtils.createMobileVerifyCode(6,
					httpSession);
			SendVoiceCodeMessage message = new SendVoiceCodeMessage();
			message.setMobile(mobile);
			message.setVerifyCode(verifyCode);

			this.sendVerifyJMS(message);

			this.addRegisterVerifyCodeInfo(httpSession);
			return "ok";
		} else {
			throw new BusinessException("8005012", "错误的请求");
		}

	}

}
