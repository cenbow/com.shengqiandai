package cn.vfunding.common.framework.lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 需要共享锁的注解
 * @author liuyijun
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedLock {
	/**
	 * 锁的路径
	 * @return
	 * 2014年9月16日
	 * liuyijun
	 */
	String value() default "/locks/borrow";	
}
