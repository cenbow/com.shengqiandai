package cn.vfunding.vfunding.common.interceptors.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 动作后通知注解，主要作用是配合拦截器(ActionAfterInterceptor)使用
 * 
 * @author liuyijun
 * 
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AfterAction {

	String value() default "";

	/**
	 * 动作的名称，比如:投资、充值、取现等等
	 * @return
	 * 2014年12月29日
	 * liuyijun
	 */
	String actionName() default "";

}
