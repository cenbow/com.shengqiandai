package cn.vfunding.vfunding.prd.www.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.vfunding.common.framework.server.interceptors.NeedSession;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.session.utils.UserSession;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		NeedSession session = handlerMethod
				.getMethodAnnotation(NeedSession.class);
		if (session != null) {
			UserSession eSession = UserSession.getUserSession();
			if (eSession == null || UserSession.getLoginUserId() == null) {

				// request.setAttribute("noSessionMsg",
				// "您还没有登录或登录已超时，请重新登录，然后再刷新本功能！");
				String returnUrl = "";
				if (EmptyUtil.isNotEmpty(session.returnUrl())) {
					returnUrl = "?returnUrl=" + session.returnUrl();
					// request.getRequestDispatcher("/goLogin" + returnUrl)
					// .forward(request, response);
					response.sendRedirect("/goLogin" + returnUrl);
				} else {
					// request.getRequestDispatcher("/goLogin").forward(request,
					// response);
					response.sendRedirect("/goLogin"); 
				}

				//
				return false;
			} else {
//				if (session.logRequestTime()) {
//					LastRequestTimeMessage msg = new LastRequestTimeMessage();
//					msg.setSessionId(request.getSession().getId());
//					msg.setUserId(UserSession.getLoginUserId());
//					this.jmsSender.sendAsynchronousMessage(msg);
//				}
			}
		}
		return true;
	}
}
