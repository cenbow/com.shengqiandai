package cn.vfunding.common.framework.server.interceptors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 需要记录业务日志的注解
 * @author liuyijun
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedLogger {

	String value() default "";	
	/**
	 * 业务描述
	 * @return
	 * 2014年7月28日
	 * liuyijun
	 */
	String desc() default "";
}
