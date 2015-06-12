package cn.p2p.p2p.mobile.interceptors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 动作前通知注解，主要作用是配合拦截器(ActionBeforeInterceptor)使用
 * 
 * @author lijianwei
 * 
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ContBefore {

	String value() default "";

}
