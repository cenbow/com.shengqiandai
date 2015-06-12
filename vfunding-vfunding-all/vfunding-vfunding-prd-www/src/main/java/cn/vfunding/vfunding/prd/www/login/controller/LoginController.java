package cn.vfunding.vfunding.prd.www.login.controller;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.EncryptionUtil;
import cn.vfunding.common.framework.utils.beans.NullUtil;
import cn.vfunding.common.framework.utils.beans.StringUtil;
import cn.vfunding.common.framework.utils.http.CookiesInfo;
import cn.vfunding.common.framework.utils.kaptcha.VerifyCodeUtils;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.common.framework.utils.mvc.json.JsonResult;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.common.vo.RegisterVO;
import cn.vfunding.vfunding.biz.common.vo.UnionUserRegisterVO;
import cn.vfunding.vfunding.biz.inviteCode.model.InviteCode;
import cn.vfunding.vfunding.biz.inviteCode.service.IInviteCodeService;
import cn.vfunding.vfunding.biz.inviteCode.status.DialogStatusIndex;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.session.utils.HttpSessionTool;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.system.model.LockRecord;
import cn.vfunding.vfunding.biz.system.model.ScrollpicMobile;
import cn.vfunding.vfunding.biz.system.model.UserTrack;
import cn.vfunding.vfunding.biz.system.service.ILockRecordService;
import cn.vfunding.vfunding.biz.system.service.IScrollpicService;
import cn.vfunding.vfunding.biz.system.service.IUserTrackService;
import cn.vfunding.vfunding.biz.thirdplat.model.ThirdRelationship;
import cn.vfunding.vfunding.biz.thirdplat.service.IThirdRelationshipService;
import cn.vfunding.vfunding.biz.user.model.UserCache;
import cn.vfunding.vfunding.biz.user.model.UserType;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.user.service.IUserCacheService;
import cn.vfunding.vfunding.biz.user.service.IUserFromService;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.biz.user.service.IUserTypeService;
import cn.vfunding.vfunding.common.module.activemq.message.UnionUserRealNameMessage;
import cn.vfunding.vfunding.prd.www.vo.LoginVO;

@Controller
@RequestMapping("")
public class LoginController extends BaseController {

	@Autowired
	private IUserCacheService userCacheService;
	@Autowired
	private IUserService userService;

	@Autowired
	private IScrollpicService scrollPicService;

	@Autowired
	private IUserTypeService typeService;

	@Autowired
	private ILockRecordService lockRecordService;
	@Autowired
	private IUserTrackService trackService;

	@Autowired
	private IThirdRelationshipService thirdRelationshipService;
	
	@Autowired
	private IInviteCodeService inviteCodeService;
	
	@Autowired
	private JmsSenderService jmsSenderUtil;
	
	@Autowired
	private IUserFromService userFromService;

	@Value("${send.sms.status}")
	private Integer smsStatus;

	/**
	 * 网站联盟用户绑定微积金用户时验证密码信息
	 * 
	 * @param cardId
	 * @param realName
	 * @param password
	 * @return 2014年6月9日 liuyijun
	 */
	@RequestMapping("/getRealNameByCardIdForUnionUser")
	@ResponseBody
	public boolean getRealNameByCardIdForUnionUser(@RequestParam("cardId") String cardId, @RequestParam("realName") String realName, @RequestParam("password") String password) {
		boolean result = false;
		List<UserWithBLOBs> users = this.userService.selectByCardId(cardId);
		if (EmptyUtil.isNotEmpty(users)) {
			if (users.get(0).getPassword().equals(password) && users.get(0).getRealname().equals(realName)) {
				result = true;
			}

		}
		return result;
	}

	/**
	 * 注册验证
	 * 
	 * @param value
	 * @return true为可注册
	 * @author liuyijun 2014年4月14日
	 */
	@RequestMapping("/checkRegister")
	@ResponseBody
	public boolean checkRegister(@RequestParam("value") String value) {
		return this.userService.checkRegister(value);
	}

	/**
	 * 跳转登录申请页面，用于申请第三方登录
	 * 
	 * @param returnUrl
	 *            登录成功后需要返回的url地址,可不传
	 * @return
	 * @author liuyijun 2014年4月11日
	 */
	@RequestMapping(value = "/loginTest", method = RequestMethod.GET)
	public ModelAndView loginTest() {
		ModelAndView mv = new ModelAndView("login1");
		return mv;
	}

	/**
	 * 注册验证用户名，主要用于用户注册时在session中记录注册人的用户名信息，防止短信验证码被恶意请求
	 * 
	 * @param value
	 * @return true为可注册
	 * @author liuyijun 2014年4月14日
	 */
	@RequestMapping("/checkRegisterUserName")
	@ResponseBody
	public boolean checkRegisterUserName(@RequestParam("value") String value, HttpSession httpSession) {
		boolean result = false;
		result = this.userService.checkRegister(value);
		if (result) {
			HttpSessionTool.recordRegisteringUserInfo(httpSession, value);
		}
		return result;
	}

	/**
	 * 检查用户名是否在session中登记过，如果登记了 返回TRUE
	 * 
	 * @param httpSession
	 * @return 2014年5月17日 liuyijun
	 */
	@RequestMapping("/isCheckRegisterUserName")
	@ResponseBody
	public boolean isCheckRegisterUserName(HttpSession httpSession) {
		return HttpSessionTool.checkRegisteringUserInfo(httpSession);
	}

	/**
	 * 检查用户名是否在session中登记过，如果登记了 返回TRUE
	 * 
	 * @param httpSession
	 * @return 2014年5月17日 liuyijun
	 */
	@RequestMapping("/checkCanGetCodeForFindPayPwd")
	@ResponseBody
	public boolean checkCanGetCodeForFindPayPwd(HttpSession httpSession) {
		return (Boolean) (EmptyUtil.isNotEmpty(httpSession.getAttribute("canGetCodeForFindPayPwd")) ? httpSession.getAttribute("canGetCodeForFindPayPwd") : false);
	}

	/**
	 * 用户注册时检查用户名、邮箱、手机等信息的唯一性
	 * 
	 * @param value
	 * @return
	 * @author liuyijun
	 */
	@RequestMapping("/checkRegisterForUnion/{value:.*}")
	@ResponseBody
	public boolean checkRegisterForUnion(@PathVariable("value") String value) {
		return this.userService.checkRegister(value);
	}

	/**
	 * 检测网站联盟用户实名认证时身份证信息是否在微积金已注册
	 * 
	 * @param cardId
	 * @return 2014年5月30日 liuyijun
	 */
	@RequestMapping(value = "/checkCardIdForUnionUser/{cardId}")
	@ResponseBody
	public String checkCardIdForUnionUser(@PathVariable("cardId") String cardId) {
		List<UserWithBLOBs> users = this.userService.selectByCardId(cardId);
		if (EmptyUtil.isEmpty(users)) {
			return "checkSuccess";
		} else {
			return users.get(0).getUsername();
		}
	}

	/**
	 * 网站联盟用户实名认证时根据网站联盟提供的身份证、密码和真实姓名验证用户信息
	 * 
	 * @param cardId
	 * @param password
	 * @return 2014年5月30日 liuyijun
	 */
	@RequestMapping(value = "/checkUserInfoByUnionUserRealName")
	@ResponseBody
	public Boolean checkUserInfoByUnionUserRealName(@RequestParam("cardId") String cardId, @RequestParam("password") String password, @RequestParam("realName") String realName) {
		boolean result = false;
		List<UserWithBLOBs> users = this.userService.selectByCardId(cardId);
		if (EmptyUtil.isEmpty(users)) {
			if (password.equals(users.get(0).getPassword())) {
				if (users.get(0).getRealname().equals(realName)) {
					result = true;
				}
			}
		}
		return result;
	}

	/**
	 * 宣传页面注册
	 * 
	 * @param register
	 * @param request
	 * @return
	 * @throws Exception
	 * @author liuyijun 2014年4月9日
	 */
	@RequestMapping(value = "/publicityRegister", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView publicityRegister(RegisterVO register, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		register.setErrorReturnUrl("publicityPage/publicityPage");
		if (this.publicityRegisterVerification(request, register, mv)) {// 后台验证
			String ip = ModelAndViewUtil.getIpAddr(request);
			this.doRegister(register, ip, request, mv, "index");
		}
		return mv;
	}

	/**
	 * 第三方登录后注册
	 * 
	 * @param register
	 * @param request
	 * @return
	 * @throws Exception
	 * @author liuyijun 2014年4月9日
	 */
	@RequestMapping(value = "/thirdRegister", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView thirdRegister(RegisterVO register, HttpServletRequest request) throws Exception {
		register.setErrorReturnUrl("thirdRegister");
		ModelAndView mv = new ModelAndView();
		String account = (String) request.getSession().getAttribute("thirdLoginRegisterUserAccount");
		String category = (String) request.getSession().getAttribute("thirdLoginRegisterCategory");
		if (this.thirdRegisterVerification(request, register, mv, account, category)) {// 后台验证
			String ip = ModelAndViewUtil.getIpAddr(request);
			this.doThirdRegister(register, ip, request, mv, "registerSuccess", account, category);
		}
		return mv;
	}

	/**
	 * 注册
	 * 
	 * @param register
	 * @param request
	 * @return
	 * @throws Exception
	 * @author liuyijun 2014年4月9日
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView register(RegisterVO register, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		register.setErrorReturnUrl("registerSecond");
		if (this.registerVerification(request, register, mv)) {// 后台验证
			String ip = ModelAndViewUtil.getIpAddr(request);
			// 处理cookie中网站联盟用户信息
			Cookie[] cookies = request.getCookies();
			this.getRegistInfoFromCookie(cookies, register);
			this.doRegister(register, ip, request, mv, "registerSuccess");
		}
		return mv;
	}

	/**
	 * 跳转到网站注册第一页
	 * 
	 * @return 2014年4月17日 liuyijun
	 */
	@RequestMapping(value = "/goRegister", method = RequestMethod.GET)
	public ModelAndView goRegisterFirstPage(String inviteCode) {
		ModelAndView mv = new ModelAndView("registerFirst");
		mv.addObject("inviteCode", inviteCode);
		return mv;
	}

	/**
	 * 邀请好友注册
	 * 
	 * @param info
	 * @return 2014年5月14日 liuyijun
	 */
	@RequestMapping(value = "/regForInvite/{info}", method = RequestMethod.GET)
	public ModelAndView registerForInvite(@PathVariable(value = "info") String info) {
		ModelAndView mv = new ModelAndView("registerFirst");
		String value = EncryptionUtil.decrypt(info);
		if (EmptyUtil.isNotEmpty(value) && value.contains("introducer")) {
			String introducer = value.substring(value.indexOf("=") + 1);
			if (EmptyUtil.isNotEmpty(introducer)) {
				RegisterVO re = new RegisterVO();
				re.setIntroducer(introducer);
				mv.addObject("register", re);
				mv.addObject("invite", true);// 如果为TRUE，注册页面的介绍人信息不可修改
			}
		}
		return mv;
	}

	/**
	 * 跳转注册成功页面
	 * 
	 * @return 2014年4月17日 liuyijun
	 */
	@RequestMapping(value = "/registerSuccess", method = RequestMethod.GET)
	public ModelAndView registerSuccess(HttpSession session) {
		ModelAndView mv = new ModelAndView("registerSuccess");
		//UserFrom uf =userFromService.selectByUserId(UserSession.getLoginUserId());
		//JJ斗地主注册用户成功后提示活动信息
		Object object = session.getAttribute("thirdSource");
		if(object !=null  && object.toString().equals("13") ){
			mv.addObject("activityMessage", "您已获得微积金&JJ赢500元现金红包斗地主大赛周赛参赛码，请点击“<a href='../sysMessage/sysMessagePage' style='color: #00a0e9;'>消息中心<a>”查看。");
		}
		return mv;
	}

	/**
	 * 注册页面点击下一步，跳转到注册第二页
	 * 
	 * @param register
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/nextRegister", method = RequestMethod.POST)
	public ModelAndView next(RegisterVO register, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("registerSecond");
		if (this.nextVerification(request, register, mv)) {// 后台验证
			// 记录session信息
			HttpSessionTool.recordRegisteringUserInfo(request.getSession(), register.getUserName());
			mv.addObject("register", register);
		}
		return mv;
	}

	/**
	 * 注册时介绍人信息检测
	 * 
	 * @param introducer
	 * @return
	 * @author liuyijun 2014年4月9日
	 */
	@RequestMapping(value = "/checkIntroducer", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkIntroducer(@RequestParam("introducer") String introducer) {
		InviteCode ic = inviteCodeService.selectByCode(introducer);
		if(ic != null){
			return true;
		}else{
			String s = "^(13|15|14|18|17)[0-9]{9}$";
			Pattern pattern = Pattern.compile(s);
			Matcher matcher = pattern.matcher(introducer);
			if(!matcher.matches()){
				return false;
			}else{
				List<UserWithBLOBs> listuser = this.userService.selectByLogin(introducer);
				if (EmptyUtil.isEmpty(listuser) || listuser.size() > 1) {
					return false;
				} else {
					return true;
				}
			}
		}
	}

	/**
	 * 添加手机号码时用的验证手机号码信息
	 * 
	 * @param mobile
	 * @return 2014年6月24日 liuyijun
	 */
	@RequestMapping(value = "/checkAddphone", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkAddphone(@RequestParam("mobile") String mobile) {
		boolean result = false;
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession()) && EmptyUtil.isNotEmpty(UserSession.getUserSession().getPhone())) {
			if (mobile.equals(UserSession.getUserSession().getPhone())) {
				result = true;
			}
		} else {
			if (this.checkRegister(mobile)) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * 跳转到宣传页面
	 * 
	 * @param introducer
	 *            介绍人
	 * @param unionUserId
	 *            网站联盟用户ID
	 * @param productId
	 *            网站联盟产品Id
	 * @param from
	 * @param experienceMoney
	 *            体验金
	 * @return
	 * @throws Exception
	 *             2014年4月24日 liuyijun
	 */
	@RequestMapping(value = "/publicity", method = RequestMethod.GET)
	// /publicity
	public ModelAndView publicity(@RequestParam(value = "introducer", required = false) String introducer, @RequestParam(value = "unionUserId", required = false) String unionUserId,
			@RequestParam(value = "productId", required = false) String productId, @RequestParam(value = "from_type", required = false) String fromType,
			@RequestParam(value = "experienceMoney", required = false) Integer experienceMoney, HttpServletResponse response, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("publicityPage/publicityPage");
		RegisterVO register = new RegisterVO();
		if (EmptyUtil.isNotEmpty(productId)) {
			register.setProductId(productId);
			Cookie cookie = new Cookie("productId", productId);
			cookie.setMaxAge(3600 * 24);// 设置保存24小时
			response.addCookie(cookie);
		}
		if (EmptyUtil.isNotEmpty(unionUserId)) {
			register.setUnionUserId(unionUserId);
			Cookie cookie = new Cookie("unionUserId", unionUserId);
			cookie.setMaxAge(3600 * 24);// 设置保存24小时
			response.addCookie(cookie);
		}
		if (EmptyUtil.isNotEmpty(introducer)) {
			register.setIntroducer(introducer);
		}
		if (EmptyUtil.isNotEmpty(fromType)) {
			Cookie cookie = new Cookie("fromType", fromType);
			cookie.setMaxAge(3600 * 24);// 设置保存24小时
			response.addCookie(cookie);
			register.setFromType(fromType);
		}
		if (EmptyUtil.isNotEmpty(experienceMoney)) {
			register.setExperienceMoney(experienceMoney);
		}
		mv.addObject("register", register);
		this.addScroolPic(mv, 0);
		return mv;
	}

	/**
	 * 网站联盟用户到同步微积金
	 * 
	 * @param vo
	 * @author liuyijun
	 */
	@RequestMapping(value = "/unionUserRegister", method = RequestMethod.POST, consumes = { "application/json" })
	@ResponseBody
	public String unionUserRegister(@RequestBody UnionUserRegisterVO unionUserRegister) {
		// this.userService.unionUserRegister(unionUserRegister);
		//this.jmsSender.sendAsynchronousMessage(unionUserRegister);
		this.jmsSenderUtil.asynSendSystemJms(unionUserRegister);
		return "success";
	}

	/**
	 * 网站联盟用户实名认证同步微积金用户实名认证
	 * 
	 * @param unionUserRealName
	 * @return 2014年5月22日 liuyijun
	 */
	@RequestMapping(value = "/unionUserRealName", method = RequestMethod.POST, consumes = { "application/json" })
	@ResponseBody
	public String unionUserRealName(@RequestBody UnionUserRealNameMessage unionUserRealName) {
		// this.userService.unionUserRegister(unionUserRegister);
		//this.jmsSender.sendAsynchronousMessage(unionUserRealName);
		this.jmsSenderUtil.asynSendSystemJms(unionUserRealName);
		return "success";
	}

	/**
	 * 验证邮箱
	 * 
	 * @param info
	 * @return 2014年4月28日 liuyijun
	 */
	@RequestMapping(value = "/verificationEmail/{info:.*}", method = RequestMethod.GET)
	public ModelAndView verificationEmail(@PathVariable("info") String info) {
		ModelAndView mv = new ModelAndView();
		String value = EncryptionUtil.decrypt(info);
		if (value.contains("userId") && value.contains("time")) {
			String userId = value.substring(value.indexOf("=") + 1, value.indexOf("&"));
			String limitTime = value.substring(value.lastIndexOf("=") + 1);
			boolean isPast = DateUtil.checkLess(new Date(), DateUtil.getDateToString(limitTime, "yyyy-MM-dd HH:mm:ss"));
			if (isPast) {
				UserWithBLOBs user = this.userService.selectByPrimaryKey(new Integer(userId));
				if (EmptyUtil.isNotEmpty(user)) {
					if (EmptyUtil.isNotEmpty(user.getEmailStatus()) && user.getEmailStatus().equals("1")) {
						mv.setViewName("error/requestError");
						mv.addObject("errorMsg", "该链接已失效");
					} else {
						user.setEmailStatus("1");
						// this.userService.updateByPrimaryKeySelective(user);
						this.userService.checkEmail(user);
						if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
							UserSession.getUserSession().setEmailStatus("1");
						}
						mv.setViewName("redirect:/user/checkEmailSuccess");
					}
				} else {
					mv.setViewName("error/requestError");
					mv.addObject("errorMsg", "认证链接有误");
				}
			} else {
				mv.setViewName("error/requestError");
				mv.addObject("errorMsg", "该邮件已过期，请重新认证");
			}
		} else {
			mv.setViewName("error/requestError");
			mv.addObject("errorMsg", "该请求不合法");
		}
		return mv;
	}

	/**
	 * 跳转登录页面
	 * 
	 * @param returnUrl
	 *            登录成功后需要返回的url地址,可不传
	 * @return
	 * @author liuyijun 2014年4月11日
	 */
	@RequestMapping(value = "/goLogin", method = RequestMethod.GET)
	public ModelAndView toLogin(@RequestParam(value = "returnUrl", required = false) String returnUrl, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("login");
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
			mv.setViewName("redirect:/index"); // 已经登录了
		} else {
			if (EmptyUtil.isNotEmpty(returnUrl)) {
				mv.addObject("returnUrl", returnUrl);
			}
		}
		return mv;
	}

	/**
	 * 登录时查询用户的登录错误次数信息
	 * 
	 * @param value
	 * @return 2014年7月17日 liuyijun
	 */
	@RequestMapping(value = "/getUserLoginInfo", method = RequestMethod.GET)
	@ResponseBody
	public Json getUserLoginInfo(@RequestParam("value") String value) {
		Json mv = new Json();
		LockRecord record = this.lockRecordService.selectByLoginValue(value);
		Integer errorNum = 1;
		if (EmptyUtil.isNotEmpty(record) && EmptyUtil.isNotEmpty(record.getErrorNum()) && record.getErrorNum().intValue() > 0) {
			errorNum = record.getErrorNum();
		}
		mv.setSuccess(true);
		mv.setMsg(errorNum.toString());
		return mv;
	}

	/**
	 * 用户登陆
	 * 
	 * @param login
	 * @param httpSession
	 * @return
	 * @author liuyijun 2014年4月10日
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult login(LoginVO login, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		JsonResult mv = new JsonResult();
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
			mv.setNeesRedirect(true);
			mv.setRedirectUrl("/index");

		} else {
			LockRecord record = this.lockRecordService.selectByLoginValue(login.getLoginValue());
			if (NullUtil.isNull(record) || NullUtil.isNull(record.getErrorNum()) || record.getErrorNum().intValue() < 3) {
				this.loginMethod(login, request, mv, record);
			} else {
				// mv.addObject("showVerifyCode", true);
				if (NullUtil.isNull(login.getVcode()) || EmptyUtil.isEmpty(login.getVcode())) {
					// mv.addObject("loginErrorMsg", );
					throw new BusinessException("8005018", "验证码不能为空");
				} else {
					if (VerifyCodeUtils.check(httpSession, login.getVcode())) {
						this.loginMethod(login, request, mv, record);
					} else {
						// mv.addObject("loginErrorMsg", "验证码错误");
						throw new BusinessException("8005019", "验证码错误");
					}
				}
			}
		}
		//已登录用户 新手任务 状态
		noviceTaskbgColor(request);
		
		return mv;
	}
	
	private void noviceTaskbgColor(HttpServletRequest request){
		Object obj = request.getSession().getAttribute(HttpSessionTool.SESSION_KEY);
		boolean status = true;
		if(obj != null){
			Integer userId = ((UserSession)obj).getUserId();
			if(userId != null ){
//				status = inviteCodeService.isLeapDialog(userId, DialogStatusIndex.noviceTaskbgColorStatus);
			}
		}
		request.getSession().setAttribute("noviceTaskbgColorStatus", status);
	}										

	/**
	 * 财经道登录
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/loginCjdao", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult loginCjdao(LoginVO login, String third, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		JsonResult mv = new JsonResult();
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
			mv.setNeesRedirect(true);
			mv.setRedirectUrl("/user/account");

		} else {
			LockRecord record = this.lockRecordService.selectByLoginValue(login.getLoginValue());
			if (NullUtil.isNull(record) || NullUtil.isNull(record.getErrorNum()) || record.getErrorNum().intValue() < 3) {
				this.loginMethodCjdao(login, request, mv, record, third);
			} else {
				// mv.addObject("showVerifyCode", true);
				if (NullUtil.isNull(login.getVcode()) || EmptyUtil.isEmpty(login.getVcode())) {
					// mv.addObject("loginErrorMsg", );
					throw new BusinessException("8005018", "验证码不能为空");
				} else {
					if (VerifyCodeUtils.check(httpSession, login.getVcode())) {
						this.loginMethodCjdao(login, request, mv, record, third);
					} else {
						// mv.addObject("loginErrorMsg", "验证码错误");
						throw new BusinessException("8005019", "验证码错误");
					}
				}
			}
		}

		return mv;
	}

	/**
	 * 退出
	 * 
	 * @param request
	 * @return 2014年4月21日 liuyijun
	 */
	@RequestMapping(value = "/loginOut", method = RequestMethod.GET)
	public ModelAndView loginOut(HttpServletRequest request) {
		ModelAndView j = new ModelAndView("redirect:index");
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute("loginedSession");
		if (EmptyUtil.isNotEmpty(userSession) && EmptyUtil.isNotEmpty(userSession.getUserId())) {
			userService.doOut(userSession.getUserId(), session);
		}
		session.invalidate();
		return j;
	}

	/**
	 * 登录处理方法
	 * 
	 * @param login
	 * @param request
	 * @param mv
	 * @author liuyijun 2014年4月11日
	 */
	private void loginMethod(LoginVO login, HttpServletRequest request, JsonResult j, LockRecord lockRecord) {
		HttpSession httpSession = request.getSession();
		// String lastIp = ModelAndViewUtil.getIpAddr(request);
		List<UserWithBLOBs> users = this.userService.selectByLogin(login.getLoginValue());
		if (NullUtil.isNull(users) || EmptyUtil.isEmpty(users)) {
			throw new BusinessException("8005013", "账户信息错误");
		} else if (users.size() > 1) {
			throw new BusinessException("8005014", "该账户出现重复错误，请联系客服");
		} else {
			UserWithBLOBs user = users.get(0);
			// UserTrack track = this.trackService
			// .selectByLastLogin(new UserTrack(user.getUserId()
			// .toString(), "site"));
			// if (EmptyUtil.isNotEmpty(track)
			// && EmptyUtil.isEmpty(track.getOutTime())) {// 未退出
			// if (!lastIp.equals(user.getLastip())) {
			// throw new BusinessException("8005032");
			// }
			// }
			if (EmptyUtil.isNotEmpty(user.getIslock()) && user.getIslock().intValue() == 1) {// 用户被锁定，不能登录
				throw new BusinessException("8005015", "账户被锁定请联系客服");
			} else if (EmptyUtil.isNotEmpty(user.getStatus()) && user.getStatus().intValue() == 0) {
				throw new BusinessException("8005016", "该账户状态为不可用，请联系客服");
			} else {
				String userPwd = DigestUtils.md5Hex(login.getPassword());
				if (user.getPassword().equals(userPwd)) {

					this.userService.userLogin(user, request);// 登录处理
					// 创建登录session
					UserSession userSession = this.createSessionByLoginedUser(user);
					HttpSessionTool.doLogin(httpSession, userSession);// session处理
					j.setNeesRedirect(true);
					j.setSuccess(true);
					if (EmptyUtil.isNotEmpty(login.getReturnUrl())) {
						j.setRedirectUrl(login.getReturnUrl());
					} else {
						j.setRedirectUrl("/index");// 返回到首页
					}
					VerifyCodeUtils.removeVerifyCode(httpSession);// 删除session中的验证码信息
				} else {
					if (EmptyUtil.isNotEmpty(lockRecord)) {
						Integer errorNum = EmptyUtil.isNotEmpty(lockRecord.getErrorNum()) ? lockRecord.getErrorNum() + 1 : 1;
						if (errorNum.intValue() > 4) {
							this.userService.lockUser(user);
						} else {
							lockRecord.setErrorNum(errorNum);
							this.lockRecordService.updateByPrimaryKeySelective(lockRecord);
						}
					} else {
						lockRecord = new LockRecord();
						if (EmptyUtil.isNotEmpty(user.getEmail())) {
							lockRecord.setEmail(user.getEmail());
						}
						if (EmptyUtil.isNotEmpty(user.getPhone())) {
							lockRecord.setPhone(user.getPhone());
						}
						if (EmptyUtil.isNotEmpty(user.getUsername())) {
							lockRecord.setUsername(user.getUsername());
						}
						lockRecord.setUserId(user.getUserId());
						lockRecord.setErrorNum(1);
						this.lockRecordService.insert(lockRecord);
					}

					// HttpSessionTool.addLoginErrorNumber(httpSession);
					throw new BusinessException("8005017", "密码错误");
				}
			}

		}
	}

	/**
	 * 财经道登录
	 * 
	 * @author liuhuan
	 */
	private void loginMethodCjdao(LoginVO login, HttpServletRequest request, JsonResult j, LockRecord lockRecord, String third) {
		HttpSession httpSession = request.getSession();
		String lastIp = ModelAndViewUtil.getIpAddr(request);
		List<UserWithBLOBs> users = this.userService.selectByLogin(login.getLoginValue());
		if (NullUtil.isNull(users) || EmptyUtil.isEmpty(users)) {
			throw new BusinessException("8005013", "账户信息错误");
		} else if (users.size() > 1) {
			throw new BusinessException("8005014", "该账户出现重复错误，请联系客服");
		} else {
			UserWithBLOBs user = users.get(0);
			UserTrack track = this.trackService.selectByLastLogin(new UserTrack(user.getUserId().toString(), "site"));
			if (EmptyUtil.isNotEmpty(track) && EmptyUtil.isEmpty(track.getOutTime())) {// 未退出
				if (!lastIp.equals(user.getLastip())) {
					throw new BusinessException("8005032");
				}
			}
			if (EmptyUtil.isNotEmpty(user.getIslock()) && user.getIslock().intValue() == 1) {// 用户被锁定，不能登录
				throw new BusinessException("8005015", "账户被锁定请联系客服，24小时后自动解锁");
			} else if (EmptyUtil.isNotEmpty(user.getStatus()) && user.getStatus().intValue() == 0) {
				throw new BusinessException("8005016", "该账户状态为不可用，请联系客服");
			} else {
				String userPwd = DigestUtils.md5Hex(login.getPassword());
				if (user.getPassword().equals(userPwd)) {

					this.userService.userLoginCjdao(user, third, request);// 登录处理
					// 创建登录session
					UserSession userSession = this.createSessionByLoginedUser(user);
					HttpSessionTool.doLogin(httpSession, userSession);// session处理
					j.setNeesRedirect(true);
					j.setSuccess(true);
					if (EmptyUtil.isNotEmpty(login.getReturnUrl())) {
						j.setRedirectUrl(login.getReturnUrl());
					} else {
						j.setRedirectUrl("/user/account");// 返回到首页
					}
					VerifyCodeUtils.removeVerifyCode(httpSession);// 删除session中的验证码信息
				} else {
					if (EmptyUtil.isNotEmpty(lockRecord)) {
						Integer errorNum = EmptyUtil.isNotEmpty(lockRecord.getErrorNum()) ? lockRecord.getErrorNum() + 1 : 1;
						if (errorNum.intValue() > 4) {
							this.userService.lockUser(user);
						} else {
							lockRecord.setErrorNum(errorNum);
							this.lockRecordService.updateByPrimaryKeySelective(lockRecord);
						}
					} else {
						lockRecord = new LockRecord();
						if (EmptyUtil.isNotEmpty(user.getEmail())) {
							lockRecord.setEmail(user.getEmail());
						}
						if (EmptyUtil.isNotEmpty(user.getPhone())) {
							lockRecord.setPhone(user.getPhone());
						}
						if (EmptyUtil.isNotEmpty(user.getUsername())) {
							lockRecord.setUsername(user.getUsername());
						}
						lockRecord.setUserId(user.getUserId());
						lockRecord.setErrorNum(1);
						this.lockRecordService.insert(lockRecord);
					}

					// HttpSessionTool.addLoginErrorNumber(httpSession);
					throw new BusinessException("8005017", "密码错误");
				}
			}

		}
	}

	/**
	 * 注册时后台验证
	 * 
	 * @param request
	 * @param register
	 * @param mv
	 * @return
	 * @author liuyijun 2014年4月16日
	 * @throws Exception
	 */
	public boolean publicityRegisterVerification(HttpServletRequest request, RegisterVO register, ModelAndView mv) throws Exception {
		if (EmptyUtil.isEmpty(register.getMobile()) || EmptyUtil.isEmpty(register.getPassword()) || EmptyUtil.isEmpty(register.getRePassword()) || EmptyUtil.isEmpty(register.getUserName())
				|| EmptyUtil.isEmpty(register.getVcode())) {
			return this.doRegisterErrorMethod(register, mv, "注册时必输项不可为空");
		} else if (!register.getPassword().equals(register.getRePassword())) {
			return this.doRegisterErrorMethod(register, mv, "密码和确认密码不相同");
		} else if (!VerifyCodeUtils.check(request.getSession(), register.getVcode()) && smsStatus == 1) {
			return this.doRegisterErrorMethod(register, mv, "验证码错误");
		} else if (EmptyUtil.isNotEmpty(register.getIntroducer())) {
			return this.checkNewInviteCode(register, mv, "介绍人信息不存在");
		} else if (StringUtil.isChinese(register.getUserName())) {
			return this.doRegisterErrorMethod(register, mv, "用户名不可为中文");
		} else if (!this.userService.checkUserNameIsLegal(register.getUserName())) {
			return this.doRegisterErrorMethod(register, mv, "用户名不可为特殊字符");
		} else {
			// 处理cookie中网站联盟用户信息
			Cookie[] cookies = request.getCookies();
			this.getRegistInfoFromCookie(cookies, register);
			return true;
		}

	}

	/**
	 * 注册时点击下一步后台验证
	 * 
	 * @param request
	 * @param register
	 * @param mv
	 * @return
	 */
	private boolean nextVerification(HttpServletRequest request, RegisterVO register, ModelAndView mv) {
		register.setErrorReturnUrl("registerFirst");
		if (EmptyUtil.isEmpty(register.getPassword()) || EmptyUtil.isEmpty(register.getRePassword()) || EmptyUtil.isEmpty(register.getUserName())) {
			return this.doRegisterErrorMethod(register, mv, "注册时必输项不可为空");
		} else if (!register.getPassword().equals(register.getRePassword())) {
			return this.doRegisterErrorMethod(register, mv, "密码和确认密码不相同");
		} else if (EmptyUtil.isNotEmpty(register.getIntroducer())) {
			return this.checkNewInviteCode(register, mv, "介绍人信息不存在");
		} else {
			return true;
		}
	}

	private boolean registerVerification(HttpServletRequest request, RegisterVO register, ModelAndView mv) {
		if (EmptyUtil.isEmpty(register.getVcode())) {
			return this.doRegisterErrorMethod(register, mv, "验证码必填");
		} else if (EmptyUtil.isEmpty(register.getMobile())) {
			return this.doRegisterErrorMethod(register, mv, "手机号码必填");
		} else if (!this.checkRegister(register.getUserName())) {
			return this.registerFirstPageErrorMethod(register, mv, "该用户已注册");
		} else if (!VerifyCodeUtils.check(request.getSession(), register.getVcode()) && smsStatus == 1) {
			return this.doRegisterErrorMethod(register, mv, "验证码错误");
		} else if (EmptyUtil.isEmpty(register.getPassword()) || EmptyUtil.isEmpty(register.getRePassword()) || EmptyUtil.isEmpty(register.getUserName())) {
			return this.registerFirstPageErrorMethod(register, mv, "注册时必输项不可为空");
		} else if (!register.getPassword().equals(register.getRePassword())) {
			return this.registerFirstPageErrorMethod(register, mv, "密码和确认密码不相同");
		} else if (EmptyUtil.isNotEmpty(register.getIntroducer())) {
			return this.checkNewInviteCode(register, mv, "介绍人信息不存在");
		} else if (StringUtil.isChinese(register.getUserName())) {
			return this.registerFirstPageErrorMethod(register, mv, "用户名不可为中文");
		} else if (!this.userService.checkUserNameIsLegal(register.getUserName())) {
			return this.registerFirstPageErrorMethod(register, mv, "用户名不可为特殊字符");
		} else {
			return true;
		}
	}
	/**
	 * 校验介绍人信息
	 * @param register
	 * @param mv
	 * @param errorMsg
	 * @return
	 *
	 * jianwei
	 */
	public boolean checkNewInviteCode(RegisterVO register, ModelAndView mv, String errorMsg){
		InviteCode ic = inviteCodeService.selectByCode(register.getIntroducer());
		if(ic != null){
			return true;
		}else{
			List<UserWithBLOBs> listUser = this.userService.selectByLogin(register.getIntroducer());
			if (!listUser.isEmpty()) {
				return true;
			}else{
				return this.registerFirstPageErrorMethod(register, mv, errorMsg);
			}
		}
	}
	
	
	private boolean thirdRegisterVerification(HttpServletRequest request, RegisterVO register, ModelAndView mv, String account, String category) {
		if (EmptyUtil.isEmpty(account) && EmptyUtil.isEmpty(category)) {
			return this.doRegisterErrorMethod(register, mv, "第三方登录信息错误");
		} else if (EmptyUtil.isEmpty(register.getVcode())) {
			return this.doRegisterErrorMethod(register, mv, "验证码必填");
		} else if (EmptyUtil.isEmpty(register.getMobile())) {
			return this.doRegisterErrorMethod(register, mv, "手机号码必填");
		} else if (!this.checkRegister(register.getUserName())) {
			return this.doRegisterErrorMethod(register, mv, "该用户已注册");
		} else if (!VerifyCodeUtils.check(request.getSession(), register.getVcode()) && smsStatus == 1) {
			return this.doRegisterErrorMethod(register, mv, "验证码错误");
		} else if (EmptyUtil.isEmpty(register.getPassword()) || EmptyUtil.isEmpty(register.getRePassword()) || EmptyUtil.isEmpty(register.getUserName())) {
			return this.doRegisterErrorMethod(register, mv, "注册时必输项不可为空");
		} else if (!register.getPassword().equals(register.getRePassword())) {
			return this.doRegisterErrorMethod(register, mv, "密码和确认密码不相同");
		} else if (StringUtil.isChinese(register.getUserName())) {
			return this.doRegisterErrorMethod(register, mv, "用户名不可为中文");
		} else if (!this.userService.checkUserNameIsLegal(register.getUserName())) {
			return this.doRegisterErrorMethod(register, mv, "用户名不可为特殊字符");
		} else {
			return true;
		}
	}

	/**
	 * 注册时后台验证错误处理
	 * 
	 * @param register
	 * @param mv
	 * @param errorMsg
	 * @return
	 * @author liuyijun 2014年4月16日
	 */
	private boolean doRegisterErrorMethod(RegisterVO register, ModelAndView mv, String errorMsg) {
		if (EmptyUtil.isNotEmpty(register.getErrorReturnUrl())) {
			mv.setViewName(register.getErrorReturnUrl());
			if (register.getErrorReturnUrl().equals("publicityPage/publicityPage")) {
				this.addScroolPic(mv, 0);
			}

		}
		mv.addObject("register", register);
		mv.addObject("verificationErrorMsg", errorMsg);
		return false;
	}

	/**
	 * 网站注册时检查，如果第一步发生错误时的返回处理
	 * 
	 * @param register
	 * @param mv
	 * @param errorMsg
	 * @return
	 * @author liuyijun 2014年4月17日
	 */
	private boolean registerFirstPageErrorMethod(RegisterVO register, ModelAndView mv, String errorMsg) {
		mv.addObject("register", register);
		mv.addObject("verificationErrorMsg", errorMsg);
		mv.setViewName("registerFirst");
		return false;
	}

	/**
	 * 添加滚动图片对象到页面
	 * 
	 * @param mv
	 * @author liuyijun 2014年4月16日
	 */
	private void addScroolPic(ModelAndView mv, Integer typeId) {
		// List<Scrollpic> scrollPics =
		// scrollPicService.selectScrollPicByTypes(typeId, 10);
		// mv.addObject("scrollPics", scrollPics);//
		List<ScrollpicMobile> scrollPics = scrollPicService.selectScrollPicByTypeMobile(typeId);
		mv.addObject("scrollPics", scrollPics);
	}

	/**
	 * 注冊公用方法
	 * 
	 * @param register
	 * @param ip
	 * @param request
	 * @param mv
	 * @param path
	 *            注册成功跳转页面
	 * @throws Exception
	 */
	private void doRegister(RegisterVO register, String ip, HttpServletRequest request, ModelAndView mv, String path) throws Exception {
		String referUrl = CookiesInfo.getRefererUrl(request.getCookies(), "vfunding_referer");
		// 放入第三方来源信息
		if (request.getSession().getAttribute("thirdSource") != null) {
			register.setThirdSource(request.getSession().getAttribute("thirdSource").toString());
		}
		if (request.getSession().getAttribute("thirdSn") != null) {
			register.setThirdSn(request.getSession().getAttribute("thirdSn").toString());
		}
		Integer userId = this.userService.register(register, ip, referUrl);
		UserWithBLOBs user = this.userService.selectByPrimaryKey(userId);
		// 创建登录session
		UserSession userSession = this.createSessionByLoginedUser(user);
		HttpSessionTool.doLogin(request.getSession(), userSession);// session处理
		// 注册验证码处理
		HttpSessionTool.removeRegisteringUserInfo(request.getSession());// 删除session中注册的用户名信息
		HttpSessionTool.removeVerifyCodeInfo(request.getSession());// 删除session中验证码请求记录信息
		VerifyCodeUtils.removeVerifyCode(request.getSession());// 删除session中的验证码信息
		
		this.noviceTaskbgColor(request);  //新手任务颜色提示状态
		mv.setViewName("redirect:/" + path);
	}

	/**
	 * 第三方注冊公用方法
	 * 
	 * @param register
	 * @param ip
	 * @param request
	 * @param mv
	 * @param path
	 *            注册成功跳转页面
	 * @throws Exception
	 */
	private void doThirdRegister(RegisterVO register, String ip, HttpServletRequest request, ModelAndView mv, String path, String account, String category) throws Exception {

		String refererUrl = CookiesInfo.getRefererUrl(request.getCookies(), "vfunding_referer");
		Integer userId = this.userService.thirdRegister(register, ip, account, category, refererUrl);
		UserWithBLOBs user = this.userService.selectByPrimaryKey(userId);
		// 创建登录session
		UserSession userSession = this.createSessionByLoginedUser(user);
		HttpSessionTool.doLogin(request.getSession(), userSession);// session处理
		// 注册验证码处理
		HttpSessionTool.removeRegisteringUserInfo(request.getSession());// 删除session中注册的用户名信息
		HttpSessionTool.removeVerifyCodeInfo(request.getSession());// 删除session中验证码请求记录信息
		VerifyCodeUtils.removeVerifyCode(request.getSession());// 删除session中的验证码信息
		mv.setViewName("redirect:/" + path);
	}

	/**
	 * 根据已登录的用户创建登录session对象
	 * 
	 * @param user
	 * @return 2014年4月25日 liuyijun
	 */
	public UserSession createSessionByLoginedUser(UserWithBLOBs user) {
		if (EmptyUtil.isNotEmpty(user)) {
			UserSession userSession = new UserSession();
			if (EmptyUtil.isNotEmpty(user.getEmail())) {
				userSession.setEmail(user.getEmail());
			}
			// userSession.setUserId(userId);
			userSession.setPhone(user.getPhone());
			userSession.setPhoneStatus(EmptyUtil.isEmpty(user.getPhoneStatus()) ? "0" : user.getPhoneStatus());
			userSession.setPassword(user.getPassword());
			userSession.setPayPassword(EmptyUtil.isEmpty(user.getPaypassword()) ? user.getPassword() : user.getPaypassword());
			userSession.setUserId(user.getUserId());
			userSession.setUserName(user.getUsername());

			userSession.setEmailStatus(EmptyUtil.isEmpty(user.getEmailStatus()) ? "0" : user.getEmailStatus());
			userSession.setRealStatus(EmptyUtil.isEmpty(user.getRealStatus()) ? "0" : user.getRealStatus());
			userSession.setVideoStatus(user.getVideoStatus());
			userSession.setSceneStatus(user.getSceneStatus());
			if (EmptyUtil.isNotEmpty(user.getCardId())) {
				userSession.setCardId(user.getCardId());
			}

			if (EmptyUtil.isNotEmpty(user.getRealname())) {
				userSession.setRealName(user.getRealname());
			}
			userSession.setTypeId(user.getTypeId());
			if (EmptyUtil.isNotEmpty(user.getTypeId())) {
				UserType type = this.typeService.selectByPrimaryKey(user.getTypeId());
				if (EmptyUtil.isNotEmpty(type) && EmptyUtil.isNotEmpty(type.getName())) {
					userSession.setTypeName(type.getName());
				}
			}
			if (EmptyUtil.isNotEmpty(user.getQuestion())) {
				userSession.setQuestion(user.getQuestion());
			}

			if (EmptyUtil.isNotEmpty(user.getAnswer())) {
				userSession.setAnswer(user.getAnswer());
			}

			UserCache cache = this.userCacheService.selectBaseInfoByUserId(user.getUserId());
			if (EmptyUtil.isNotEmpty(cache) && EmptyUtil.isNotEmpty(cache.getVipStatus())) {
				userSession.setVipStatus(cache.getVipStatus());
			}

			if (EmptyUtil.isNotEmpty(user.getSex()) && user.getSex().equals("1")) {
				userSession.setSex("1");
			} else {
				userSession.setSex("0");
			}
			if (EmptyUtil.isNotEmpty(user.getAddress())) {
				userSession.setAddress(user.getAddress());
			}

			userSession.setUserIcon(EmptyUtil.isNotEmpty(user.getUserIcon()) ? user.getUserIcon() : null);

			// 查询第三方用户信息
			ThirdRelationship tr = this.thirdRelationshipService.selectByUserIdType(user.getUserId(), null);
			if (EmptyUtil.isNotEmpty(tr) && tr.getId() > 0) {
				userSession.setIsThirdUser(1);
			} else {
				userSession.setIsThirdUser(0);
			}
			return userSession;
		} else {
			return null;
		}
	}

	/**
	 * 从cookie中获取注册的额外信息
	 * 
	 * @throws Exception
	 *             2014年8月28日 liuyijun
	 */
	private void getRegistInfoFromCookie(Cookie[] cookies, RegisterVO register) throws Exception {

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("unionUserId")) {
				register.setUnionUserId(URLDecoder.decode(cookie.getValue(), "UTF-8"));
			}
			if (cookie.getName().equals("productId")) {
				register.setProductId(URLDecoder.decode(cookie.getValue(), "UTF-8"));
			}

			if (cookie.getName().equals("fromType")) {
				register.setFromType(URLDecoder.decode(cookie.getValue(), "UTF-8"));
			}

		}
	}

}
