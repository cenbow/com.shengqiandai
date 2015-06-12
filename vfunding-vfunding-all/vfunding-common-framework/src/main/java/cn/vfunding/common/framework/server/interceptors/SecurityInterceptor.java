package cn.vfunding.common.framework.server.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.vfunding.common.framework.server.EmployeeSession;
import cn.vfunding.common.framework.server.auth.UserAuthUtil;

/**
 * 权限拦截器
 * 
 * @author liuyijun
 * 
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		UnSession unSession=handlerMethod.getMethodAnnotation(UnSession.class);
		if(unSession!=null){
			return true;
		}else{
			EmployeeSession eSession = EmployeeSession.getUserSession();
			if (eSession == null || eSession.getEmpId() == null) {
				request.setAttribute("msg", "您还没有登录或登录已超时，请重新登录，然后再刷新本功能！");
				request.getRequestDispatcher("/error/noSession.jsp").forward(
						request, response);
				return false;
			}else{
				
				UnSecurity security = handlerMethod
						.getMethodAnnotation(UnSecurity.class);
				if (security != null) {
					return true;
				} else {		
					if (!UserAuthUtil.isPass(url)) {
						request.setAttribute("msg", "您没有[" + url + "]资源的权限！");
						request.getRequestDispatcher("/error/noSecurity.jsp").forward(
								request, response);
						return false;
					}
				}
			}
		}	
		return true;
	}

	
}
