package cn.vfunding.vfunding.biz.session.utils;

import java.util.Map;

import javax.servlet.http.HttpSession;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;

public class HttpSessionTool {
	public static String SESSION_KEY = "loginedSession";
	public static String LOGINERROR_KEY = "login_error_number";
	public static String REGISTERING_USER="registering_user";//注册中的用户名

	/**
	 * 网站注册时验证码请求记录
	 */
	public static String REGISTERVERIFYCODE = "registerVerifyCode";
	
	
	
	/**
	 * 注册时添加正在注册的用户信息，如果没有这个用户信息就不发验证短信
	 * @param session
	 * @param userName
	 * 2014年5月17日
	 * liuyijun
	 */
	public static void recordRegisteringUserInfo(HttpSession session,String userName){
		session.setAttribute(REGISTERING_USER, userName);
	}
	
	/**
	 * 短信验证码时检测是否已注册了用户名
	 * @param session
	 * @return
	 * 2014年5月17日
	 * liuyijun
	 */
	public static boolean checkRegisteringUserInfo(HttpSession session){
		return EmptyUtil.isEmpty(session.getAttribute(REGISTERING_USER))?false:true;
	}
	
	public static void removeRegisteringUserInfo(HttpSession session){
		session.removeAttribute(REGISTERING_USER);
		
	}
	
	
	

	/**
	 * 添加注册验证码信息
	 * 
	 * @param httpSession
	 * @param info
	 *            2014年5月15日 liuyijun
	 */
	public static void addRegisterVerifyCodeInfo(HttpSession httpSession,
			Map<String, Object> info) {
		httpSession.setAttribute(REGISTERVERIFYCODE, info);
	}

	/**
	 * 获取注册时验证码请求信息，用于防止恶意攻击
	 * @param httpSession
	 * @return
	 * 2014年5月15日
	 * liuyijun
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getRegisterVerifyCodeInfo(HttpSession httpSession) {
		return (Map<String, Object>) httpSession
				.getAttribute(REGISTERVERIFYCODE);

	}

	/**
	 * 清除注册验证码信息
	 * 
	 * @param httpSession
	 *            2014年5月15日 liuyijun
	 */
	public static void removeVerifyCodeInfo(HttpSession httpSession) {
		httpSession.removeAttribute(REGISTERVERIFYCODE);
	}

	/**
	 * 将成功登陆的用户保存到session中
	 * 
	 * @param httpSession
	 * @param user
	 * @author liuyijun 2014年4月10日
	 */
	public static void doLogin(HttpSession httpSession,
			UserSession baseUserSession) {
		httpSession.setAttribute(SESSION_KEY, baseUserSession);
		httpSession.removeAttribute(LOGINERROR_KEY);
	}

	/***
	 * 错误的登陆次数
	 * 
	 * @param httpSession
	 * @return
	 * @author liuyijun 2014年4月10日
	 */
	public static Integer getLoginErrorNumber(HttpSession httpSession) {
		return (Integer) httpSession.getAttribute(LOGINERROR_KEY);
	}

	/**
	 * 增加登陆错误次数
	 * 
	 * @param httpSession
	 * @author liuyijun 2014年4月10日
	 */
	public static void addLoginErrorNumber(HttpSession httpSession) {
		Integer number = getLoginErrorNumber(httpSession);
		number = EmptyUtil.isEmpty(number) ? 1 : number + 1;
		httpSession.setAttribute(LOGINERROR_KEY, number);
	}
	
	
	/**
	 * 清除错误登录次数
	 * @param httpSession
	 * 2014年6月30日
	 * liuyijun
	 */
	public static void clearLoginErrorNumber(HttpSession httpSession){
		httpSession.removeAttribute(LOGINERROR_KEY);
	}

	/**
	 * 退出
	 * 
	 * @param httpSession
	 *            2014年4月21日 liuyijun
	 */
	public static void doOut(HttpSession httpSession) {
		httpSession.removeAttribute(SESSION_KEY);
	}

}
