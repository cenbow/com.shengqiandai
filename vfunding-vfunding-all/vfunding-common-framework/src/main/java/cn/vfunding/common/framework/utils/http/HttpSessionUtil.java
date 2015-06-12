package cn.vfunding.common.framework.utils.http;

import javax.servlet.http.HttpSession;

import cn.vfunding.common.framework.server.EmployeeSession;



public class HttpSessionUtil {
	
	public static final String HTTP_SESSION_LOGIN_FLAG = "employee";
	public static final String HTTP_SESSION_LOGIN_COUNT = "HTTP_SESSION_LOGIN_COUNT";//登录次数
	/**
	 * 登录成功
	 * @param httpSession
	 */
	public static void login(HttpSession  httpSession, EmployeeSession EmployeeSession ){
		httpSession.setAttribute(HTTP_SESSION_LOGIN_FLAG, EmployeeSession);
	}
	
	/**
	 * 是否已经登录
	 * @param httpSession
	 */
	public static EmployeeSession getAuthInfo(HttpSession  httpSession){
		EmployeeSession obj = (EmployeeSession)httpSession.getAttribute(HTTP_SESSION_LOGIN_FLAG);
		return obj;
	}
	
	/**
	 * 退出
	 * @param httpSession
	 */
	public static void logOut(HttpSession  httpSession ){
		httpSession.removeAttribute(HTTP_SESSION_LOGIN_FLAG);
	}
	
	/**
	 * 获取登录错误次数
	 * @param httpSession
	 */
	public static int getCount(HttpSession  httpSession){
		Integer count = (Integer)httpSession.getAttribute(HTTP_SESSION_LOGIN_COUNT);
		return count==null?0:count;
	}
	
	
	/**
	 * 累加一次登录次数
	 * @param httpSession
	 */
	public static void addCount(HttpSession  httpSession){
		Integer count = (Integer)httpSession.getAttribute(HTTP_SESSION_LOGIN_COUNT);
		if(count==null) count=0;
		count++;
		httpSession.setAttribute(HTTP_SESSION_LOGIN_COUNT, count);
	}
	
	
	/**
	 * 移除登录次数统计
	 * @param httpSession
	 */
	public static void removeCount(HttpSession  httpSession){
		httpSession.removeAttribute(HTTP_SESSION_LOGIN_COUNT);
	}
	
}
