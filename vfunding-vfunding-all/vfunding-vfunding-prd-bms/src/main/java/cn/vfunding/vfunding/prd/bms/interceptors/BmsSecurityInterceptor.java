package cn.vfunding.vfunding.prd.bms.interceptors;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.vfunding.common.framework.server.EmployeeSession;
import cn.vfunding.common.framework.server.interceptors.NeedLogger;
import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.server.interceptors.UnSecurity;
import cn.vfunding.common.framework.server.interceptors.UnSession;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.bms_system.model.BmsLog;
import cn.vfunding.vfunding.biz.bms_system.service.IBmsLogService;
import cn.vfunding.vfunding.prd.bms.utils.UserAuthFilter;

/**
 * 权限拦截器
 * 
 * @author liuyijun
 * 
 */
public class BmsSecurityInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private IBmsLogService logService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// String requestUri = request.getRequestURI();
		// String contextPath = request.getContextPath();
		String url = this.getRequestUrl(request);
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		UnSession unSession = handlerMethod
				.getMethodAnnotation(UnSession.class);
		if (unSession != null) {
			return true;
		} else {
			EmployeeSession eSession = EmployeeSession.getUserSession();
			if (eSession == null || eSession.getEmpId() == null) {
				request.setAttribute("msg", "您还没有登录或登录已超时，请重新登录！");
				request.getRequestDispatcher("/error/noSession.jsp").forward(
						request, response);
				return false;
			} else {
				ParentSecurity parent = handlerMethod
						.getMethodAnnotation(ParentSecurity.class);
				if (parent != null) {
					String[] parentUrls = parent.value();
					for (String parentUrl : parentUrls) {
						if (UserAuthFilter.isPass(url, parentUrl)) {
							return true;
						}
					}

				} else {
					UnSecurity security = handlerMethod
							.getMethodAnnotation(UnSecurity.class);
					if (security != null) {
						return true;
					} else {
						if (UserAuthFilter.isPass(url)) {
							return true;
						}
					}
				}

			}
		}
		request.setAttribute("msg", "您没有[" + url + "]资源的权限！");
		request.getRequestDispatcher("/error/noSecurity.jsp").forward(request,
				response);
		return false;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String url = this.getRequestUrl(request);
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		NeedLogger logger = handlerMethod.getMethodAnnotation(NeedLogger.class);
		if (logger != null) {
			BmsLog log = new BmsLog();
			if (EmptyUtil.isNotEmpty(logger.desc())) {
				log.setOperationDesc(logger.desc());
			}
			log.setOperationTime(new Date());
			log.setRequestAddress(url);
			log.setUserId(EmployeeSession.getEmpSessionEmpId());
			if (ex != null) {
				log.setSuccess("N");
				log.setErrorInfo(ex.getMessage());
			}
			this.logService.insert(log);
		}
		super.afterCompletion(request, response, handler, ex);
	}

	/**
	 * 获取请求的资源路径
	 * @param request
	 * @return 2014年7月28日 liuyijun
	 */
	private String getRequestUrl(HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		return requestUri.substring(contextPath.length());
	}

}
