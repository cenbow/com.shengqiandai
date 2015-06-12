package cn.vfunding.common.framework.utils.validator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CharacterAnnotation {
	/**
	 * 名称(不能为空)
	 * */
	public String name();
//
//	/**
//	 * 异常提示信息，默认为空（如果keys为空，则info一定不为空）
//	 * */
//	public String[] info() default "";

	/**
	 * 配置PageHolderUtil中的常量值,默认为空 （为国际化预留字段）
	 * */
	public String[] keys() default "";

	/**
	 * 字段最大长度(不能为空)
	 * */
	public int maxLength();

	/**
	 * 字段最小长度,默认为""，不进行最小长度判断
	 * */
	public String minLength() default "";

	/**
	 * 是否可以为空,boolean值，默认为false(可以为空)
	 * */
	public boolean isNotNull() default false;

	/**
	 * 字段类型，默认为String类型
	 * */
	public CharacterTypeEnums type() default CharacterTypeEnums.String;

	/**
	 * 最大值，type()为数字类型的时候才会进行判断，默认为""(无最大值)
	 * */
	public String max() default "";

	/**
	 * 最小值，type()为数字类型的时候才会进行判断，默认为""(无最小值)
	 * */
	public String min() default "";

	/**
	 * 自定义正则表达式验证，默认为""，不进行验证
	 * */
	public String regexp() default "";

	/**
	 * 自定义正则表达式验证提示信息，默认为""(为国际化预留字段)
	 * */
	public String[] regexpInfos() default "";

//	/**
//	 * 自定义正则表达式验证提示信息，默认为""
//	 * */
//	public String regexpInfo() default "";

}
