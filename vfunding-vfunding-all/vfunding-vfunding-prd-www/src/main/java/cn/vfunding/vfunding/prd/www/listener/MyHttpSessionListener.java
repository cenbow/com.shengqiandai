package cn.vfunding.vfunding.prd.www.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.user.service.IUserService;

/**
 * 会话监听器
 * 
 * @author liuyijun
 * 
 */
public class MyHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(se.getSession()
						.getServletContext());
		IUserService userService = (IUserService) ctx.getBean("userService");

		UserSession session = (UserSession) se.getSession().getAttribute(
				"loginedSession");
		if(EmptyUtil.isNotEmpty(session) && EmptyUtil.isNotEmpty(session.getUserId())){
			userService.doOut(session.getUserId(),se.getSession());
		}
		
	}

}
