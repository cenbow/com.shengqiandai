package cn.vfunding.common.framework.utils.http;

import javax.servlet.http.Cookie;

public class CookiesInfo {

	/**
	 * 从cookie中获取注册的额外信息
	 * 
	 * @param cookies
	 * @param register
	 * @throws Exception
	 *             2014年8月28日 liuyijun
	 */
	public static String getRefererUrl(Cookie[] cookies, String cookName) throws Exception {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookName)) {
				return cookie.getValue();
			}
		}
		return "http://www.kongbai.cn";
	}
}
