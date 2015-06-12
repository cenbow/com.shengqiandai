package cn.vfunding.vfunding.prd.www.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.vfunding.vfunding.biz.session.utils.HttpSessionTool;
import cn.vfunding.vfunding.biz.session.utils.UserSession;

/**
 * 本拦截器只是对HttpSession对象的引用，实际不会对user数据进行拷贝！
 * 
 * @author liuyijun
 * 
 */
public class UserSessionFilter implements Filter {

	private static ThreadLocal<HttpSession> threadLocalHttpSession = new ThreadLocal<HttpSession>();

	public static HttpSession getHttpSession() {
		return threadLocalHttpSession.get();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		String uri = ((HttpServletRequest) request).getRequestURI();
		if (uri.endsWith(".jsp")) {
			String url = ((HttpServletRequest) request).getRequestURL()
					.toString();
			if (!url.contains("localhost")) {
				HttpServletResponse response = (HttpServletResponse) arg1;
				response.sendRedirect("/notFound");
				return;
			}
		}
		HttpSession session = ((HttpServletRequest) request).getSession();
		threadLocalHttpSession.set(session);
		UserSession userSession = (UserSession) session
				.getAttribute(HttpSessionTool.SESSION_KEY);
		UserSession.setUserSession(userSession);
		arg2.doFilter(request, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
