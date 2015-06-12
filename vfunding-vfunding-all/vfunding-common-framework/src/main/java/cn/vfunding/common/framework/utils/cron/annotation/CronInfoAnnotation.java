package cn.vfunding.common.framework.utils.cron.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CronInfoAnnotation {

	/**
	 * 类名
	 * @return
	 * @author liuyijun
	 */
    String className();
    /**
	 * 方法名
	 * @return
	 * @author liuyijun
	 */
	String methodName();
	
	/**
	 * 调度任务名
	 * @return
	 * @author liuyijun
	 */
	String cronName();
	
	/**
	 * 调度任务描述
	 * @return
	 * @author liuyijun
	 */
	String cronRemark();
	
	/**
	 * 模块名称
	 * @return
	 * @author liuyijun
	 */
	String systemName();
	
	/**
	 * 系统类型
	 * @return
	 * @author liuyijun
	 */
	String systemType();
	/**
	 * 手动执行任务的URL地址 
	 * @return
	 * 2014年8月18日
	 * liuyijun
	 */
	String reStartPath() default "";
}
