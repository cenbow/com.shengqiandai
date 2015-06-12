package cn.vfunding.vfunding.prd.www.interceptors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;

public class CookieInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 存放用户来自哪個網站
		String cookies = null;
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("vfunding_referer")) {
				cookies = cookie.getValue();
				break;
			}
		}
		if (EmptyUtil.isEmpty(cookies)) {
			String referer = request.getHeader("referer");
			if (EmptyUtil.isEmpty(referer)) {
				referer = "http://www.kongbai.com";
			}
			String str = null;
			if (referer.lastIndexOf(".") + 4 <= referer.length()) {
				str = referer.substring(0, referer.lastIndexOf(".") + 4);
			} else {
				str = referer.substring(0, referer.lastIndexOf(".") + 3);
			}

			Cookie cookie = new Cookie("vfunding_referer", str);
			cookie.setMaxAge(60 * 30);// 设置保存半小时
			response.addCookie(cookie);
		}
		return true;
	}
}
