package cn.vfunding.common.framework.utils.validator;

import org.apache.commons.validator.GenericValidator;

import cn.vfunding.common.framework.utils.beans.ConverterUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.NullUtil;
import cn.vfunding.common.framework.utils.beans.StringUtil;

/**
 * 验证类公共方法
 * */
public class ValidatorUtil {
	/**
	 * 验证参数是否是int类型
	 * 
	 * @author LiuJun
	 * */
	public static <T> boolean isInt(T value) {
		if (NullUtil.isNull(value)) {
			return false;
		}
		return GenericValidator.isInt(value.toString());
	}

	/**
	 * 验证参数是否是Float类型
	 * 
	 * @author LiuJun
	 * */
	public static <T> boolean isFloat(T value) {
		if (NullUtil.isNull(value)) {
			return false;
		}
		return GenericValidator.isFloat(value.toString());
	}

	/**
	 * 验证参数是否是float类型
	 * 
	 * @author LiuJun
	 * */
	public static <T> boolean isDouble(T value) {
		if (NullUtil.isNull(value)) {
			return false;
		}
		return GenericValidator.isDouble(value.toString());
	}

	/**
	 * 验证参数是否是date类型
	 * 
	 * @author LiuJun
	 * */
	public static <T> boolean isDate(T value) {
		if (NullUtil.isNull(value)) {
			return false;
		}
		ConverterUtil.DateConverterStr(value.toString(), null);
		return true;
	}

	/**
	 * 验证参数是否是date类型
	 * 
	 * @author LiuJun
	 * */
	public static <T> boolean isInRange(T value, int max, int min) {
		if (isInt(value)) {
			return GenericValidator.isInRange((Integer) value, min, max);
		}
		return false;
	}

	/**
	 * 验证参数是否是date类型
	 * 
	 * @author LiuJun
	 * */
	public static <T> boolean isInRange(T value, float max, float min) {
		if (isFloat(value)) {
			return GenericValidator.isInRange((Float) value, min, max);
		}
		return false;
	}

	/**
	 * 验证参数是否是date类型
	 * 
	 * @author LiuJun
	 * */
	public static <T> boolean isInRange(T value, double max, double min) {
		if (isDouble(value)) {
			return GenericValidator.isInRange((Double) value, min, max);
		}
		return false;
	}

	/**
	 * 验证邮箱地址是否合法
	 * 
	 * @author LiuJun
	 * */
	public static boolean isEmail(String value) {
		if (EmptyUtil.isEmptyChars(value)) {
			return false;
		}
		return GenericValidator.isEmail(value);
	}

	/**
	 * 长度验证
	 * 
	 * @author LiuJun
	 * */
	public static <T> boolean lengthValidator(T value, int max, int min) {
		if (NullUtil.isNull(value)) {
			return isInRange(0, max, min);
		}
		return isInRange(StringUtil.getLength(value.toString()), max, min);
	}

	/**
	 * 正则表达式验证
	 * 
	 * @author LiuJun
	 * */
	public static boolean matchRegexp(String value, String regexp) {
		return GenericValidator.matchRegexp(value, regexp);
	}

	/**
	 * 验证座机号码
	 * 
	 * @author LiuJun
	 * */
	public static boolean isPhoneNumber(String value) {
		if (EmptyUtil.isEmptyChars(value)) {
			return false;
		}
		String regexp = "^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$";
		if (matchRegexp(value, regexp)) {
			return true;
		}
		return false;
	}

	/**
	 * 验证手机号码
	 * 
	 * @author LiuJun
	 * */
	public static boolean isPhoneNo(String value) {
		if (EmptyUtil.isEmptyChars(value)) {
			return false;
		}
		String regexp = "^[1][358]\\d{9}$";
		if (matchRegexp(value, regexp)) {
			return true;
		}
		return false;
	}

	/**
	 * 验证座机号码和手机号码
	 * 
	 * @author LiuJun
	 * */
	public static boolean isTelephoneNumber(String value) {
		if (isPhoneNumber(value) || isPhoneNo(value)) {
			return true;
		}
		return false;
	}

	public static boolean isBoolean(Object value) {
		if (NullUtil.isNull(value)) {
			return false;
		}
		try {
			ConverterUtil.booleanConverter(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
