package cn.vfunding.common.framework.server.interceptors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 需要session的方法注解
 * @author liuyijun
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedSession {

	String value() default "";	
	/**
	 * 登录后需要返回的URL地址
	 * @return
	 * 2014年4月29日
	 * liuyijun
	 */
	String returnUrl() default "";
	/**
	 * 是否需要记录当前请求时间
	 * @return
	 * 2014年8月13日
	 * liuyijun
	 */
	boolean logRequestTime() default true;
}
