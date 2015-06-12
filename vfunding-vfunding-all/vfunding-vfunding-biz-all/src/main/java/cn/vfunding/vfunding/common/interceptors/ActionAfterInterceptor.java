package cn.vfunding.vfunding.common.interceptors;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.annotation.Autowired;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.common.interceptors.annotations.AfterAction;
import cn.vfunding.vfunding.common.module.activemq.message.afteraction.AfterActionMessage;

/**
 * 平台核心动作后通知，该通知主要是针对平台的核心业务，比如用户充值、投资、提现、发标、初审、复审等核心业务进行
 * 方法后通知拦截。其目的是分离核心业务的主流程和辅助流程，以提高系统的可扩展、可维护及响应速度，支持高并发等功能。
 * 
 * @author liuyijun
 * 
 */
public class ActionAfterInterceptor implements AfterReturningAdvice {

	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("afterlog");

	//private ISenderService senderService;
	@Autowired
	private JmsSenderService jmsSenderUtil;

	@Override
	public void afterReturning(Object arg0, Method method, Object[] arg2,
			Object arg3) throws Throwable {
		// arg0:拦截方法的返回值
		// arg2:拦截方法的参数
		// arg3:被拦截的类对象
		AfterAction action = method.getAnnotation(AfterAction.class);
		if (EmptyUtil.isNotEmpty(action)) {
			logger.info("************************");
			logger.info("动作后通知拦截启动，拦截方法名：" + method.getName());
			logger.info("动作后通知拦截启动，拦截动作名：" + action.actionName());
			AfterActionMessage after = new AfterActionMessage(
					action.actionName(), arg2, arg0);
			// userActionSender.sendAsynchronousMessage(after);
			//this.senderService.asynSendUserActionJms(after);
			this.jmsSenderUtil.asynSendUserActionJms(after);
			logger.info("动作后通知拦截发送'AfterActionMessage'异步消息成功！");
			logger.info("************************");
		}
	}

//	public ISenderService getSenderService() {
//		return senderService;
//	}
//
//	public void setSenderService(ISenderService senderService) {
//		this.senderService = senderService;
//	}

}
