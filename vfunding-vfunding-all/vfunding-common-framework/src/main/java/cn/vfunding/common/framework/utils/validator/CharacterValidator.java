package cn.vfunding.common.framework.utils.validator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.lang.reflect.MethodUtils;

import cn.vfunding.common.framework.utils.beans.ConverterUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.NullUtil;
import cn.vfunding.common.framework.utils.beans.StringUtil;

/**
 * 验证实体字段值和配置信息是否相同
 * 
 * */
public class CharacterValidator {
	/**
	 * 验证方法 全部验证通过返回true；验证方法必须是get方法且配有CharacterAnnotation
	 * 
	 * @author LiuJun
	 * @param t
	 *            要验证的对象
	 * */
	public static <T> boolean validator(T t) {
		if (NullUtil.isNull(t)) {
			return true;
		}
		List<Method> methods = getMethods(t);
		return validator(methods, t);
	}

	/**
	 * 获取要验证的方法
	 * 
	 * @author LiuJun
	 * */
	private static <T> List<Method> getMethods(T t) {
		Method[] methods = t.getClass().getMethods();
		List<Method> methodList = null;
		if (EmptyUtil.isNotEmpty(methods)) {
			methodList = new ArrayList<Method>();

			for (Method method : methods) {
				String name = method.getName();
				if (StringUtil.startsWith(name, "get", true)
						|| StringUtil.startsWith(name, "is", true)) {
					if (method.isAnnotationPresent(CharacterAnnotation.class)) {
						methodList.add(method);
						continue;
					}// 其中含有CharacterAnnotation
				}// 以get开通的方法
			}
		}
		return methodList;
	}

	private static <T> boolean validator(List<Method> methods, T t) {
		if (EmptyUtil.isEmpty(methods)) {
			return true;
		}

		for (Method method : methods) {
			CharacterAnnotation tation = method
					.getAnnotation(CharacterAnnotation.class);
			try {
				Object value = MethodUtils.invokeMethod(t, method.getName(),
						null);

				if (NullUtil.isNull(value)) {
					continue;
				}

				validatorRegexp(value, tation);
				validatorBlank(value, tation);
				validatorType(value, tation);

				validatorLenth(value, tation);
				valueValidator(value, tation);

			} catch (NoSuchMethodException e) {
				throw new ConversionException(e);
			} catch (IllegalAccessException e) {
				throw new ConversionException(e);
			} catch (InvocationTargetException e) {
				throw new ConversionException(e);
			}
		}

		return true;
	}

	/**
	 * 根据正则表达式进行验证
	 * 
	 * */
	private static boolean validatorRegexp(Object value,
			CharacterAnnotation tation) {
		if (EmptyUtil.isNotEmpty(tation.regexp())) {
			if (ValidatorUtil.matchRegexp(value.toString(), tation.regexp())) {
				return true;
			}
			StringBuilder builder = new StringBuilder(tation.name());
			builder.append("格式错误");
			throw new ConversionException(builder.toString());
			// if (EmptyUtil.isNotEmpty(tation.regexpInfos())) {
			// // FIXME 如果添加国际化，则需要修改此处代码
			// } else if (EmptyUtil.isNotEmpty(tation.info())) {
			// throw new ConversionException(tation.regexpInfo());
			// }

			// throw new ConversionException(StringUtil.getMsgs(tation
			// .regexpInfo()));
			// throw new ConversionException(StringUtil.getMsgs(tation
			// .regexpInfo()));
		}
		return true;
	}

	/**
	 * 验证是否为空
	 * 
	 * */
	private static boolean validatorBlank(Object value,
			CharacterAnnotation tation) {
		if (tation.isNotNull()) {
			if (EmptyUtil.isEmpty(value)) {
				throwConversionException(tation, "不能为空");
			}
		}
		return true;
	}

	/**
	 * 验证值和配置类型是否相同
	 * 
	 * @author LiuJun
	 * */
	private static boolean validatorType(Object value,
			CharacterAnnotation tation) {
		System.out.println(tation.type());
		if (CharacterTypeEnums.Boolean.equals(tation.type())) {
			if (ValidatorUtil.isBoolean(value)) {
				return true;
			}
			// throwConversionException(tation,
			// StringUtil.getMsgs(PageHolderUtil.format));
			throwConversionException(tation, "类型错误");
		} else if (CharacterTypeEnums.Date.equals(tation.type())) {
			if (ValidatorUtil.isDate(value)) {
				return true;
			}
			// throwConversionException(tation,
			// StringUtil.getMsgs(PageHolderUtil.format));
			throwConversionException(tation, "类型错误");
		} else if (CharacterTypeEnums.Double.equals(tation.type())) {
			if (ValidatorUtil.isDouble(value)) {
				return true;
			}
			// throwConversionException(tation,
			// StringUtil.getMsgs(PageHolderUtil.format));
			throwConversionException(tation, "类型错误");
		} else if (CharacterTypeEnums.Float.equals(tation.type())) {
			if (ValidatorUtil.isFloat(value)) {
				return true;
			}
			// throwConversionException(tation,
			// StringUtil.getMsgs(PageHolderUtil.format));

			throwConversionException(tation, "类型错误");
		} else if (CharacterTypeEnums.Integer.equals(tation.type())) {
			if (ValidatorUtil.isInt(value)) {
				return true;
			}
			// throwConversionException(tation,
			// StringUtil.getMsgs(PageHolderUtil.format));
			throwConversionException(tation, "类型错误");
		} else if (CharacterTypeEnums.Email.equals(tation.type())) {
			if (EmptyUtil.isEmpty(value)) {
				throwConversionException(tation, "不能为空");
				// throwConversionException(tation,
				// StringUtil.getMsgs(PageHolderUtil.format));
			} else if (ValidatorUtil.isEmail(value.toString())) {
				return true;
			}
			throwConversionException(tation, "格式错误");
		} else if (CharacterTypeEnums.telephoneNumber.equals(tation.type())) {
			if (EmptyUtil.isEmpty(value)) {
				// throwConversionException(tation,
				// StringUtil.getMsgs(PageHolderUtil.format));
				throwConversionException(tation, "不能为空");
			} else if (ValidatorUtil.isTelephoneNumber(value.toString())) {
				return true;
			}
			throwConversionException(tation, "格式错误");
			// throwConversionException(tation,
			// StringUtil.getMsgs(PageHolderUtil.format));
		} else if (CharacterTypeEnums.PhoneNo.equals(tation.type())) {
			if (EmptyUtil.isEmpty(value)) {
				// throwConversionException(tation,
				// StringUtil.getMsgs(PageHolderUtil.format));
				throwConversionException(tation, "不能为空");
			} else if (ValidatorUtil.isPhoneNo(value.toString())) {
				return true;
			}
			// throwConversionException(tation,
			// StringUtil.getMsgs(PageHolderUtil.format));
			throwConversionException(tation, "格式错误");
		} else if (CharacterTypeEnums.phoneNumber.equals(tation.type())) {
			if (EmptyUtil.isEmpty(value)) {
				// throwConversionException(tation,
				// StringUtil.getMsgs(PageHolderUtil.format));
				throwConversionException(tation, "不能为空");
			} else if (ValidatorUtil.isPhoneNumber(value.toString())) {
				return true;
			}
			// throwConversionException(tation,
			// StringUtil.getMsgs(PageHolderUtil.format));
			throwConversionException(tation, "格式错误");
		}
		return true;
	}

	/**
	 * 验证值长度
	 * 
	 * @author LiuJun
	 * */
	private static boolean validatorLenth(Object value,
			CharacterAnnotation tation) {
		if (EmptyUtil.isEmpty(value)) {
			return false;
		}

		int length = StringUtil.getLength(value.toString());

		if (EmptyUtil.isNotEmpty(tation.minLength())) {
			int minLen = ConverterUtil.integerConverter(tation.minLength());
			if (minLen > length) {
				// throwConversionException(
				// tation,
				// StringUtil.getMsg(PageHolderUtil.MINLENGTH)
				// + tation.minLength()
				// + StringUtil.getMsg(PageHolderUtil.LENGTH));

				throwConversionException(tation, "最小长度" + tation.minLength()
						+ "位");
			}
		}
		int maxLength = tation.maxLength();
		if (maxLength < length) {
			// throwConversionException(
			// tation,
			// StringUtil.getMsg(PageHolderUtil.MAXLENGTH)
			// + tation.maxLength()
			// + StringUtil.getMsg(PageHolderUtil.LENGTH));

			throwConversionException(tation, "最大长度" + tation.maxLength() + "位");
		}

		return true;
	}

	private static boolean valueValidator(Object value,
			CharacterAnnotation tation) {
		if (CharacterTypeEnums.Integer.equals(tation.type())) {
			String max = tation.max();
			String min = tation.min();

			if (EmptyUtil.isNotEmpty(max) && EmptyUtil.isNotEmpty(min)
					&& NullUtil.isNotNull(value)) {
				int valueInt = ConverterUtil.integerConverter(value);
				int maxValue = ConverterUtil.integerConverter(max);
				int minValue = ConverterUtil.integerConverter(min);

				if (valueInt > maxValue) {
					// throwConversionException(tation, StringUtil.getMsgs(
					// PageHolderUtil.MAX, PageHolderUtil.VALUE,
					// PageHolderUtil.TO));

					throwConversionException(tation, "最大值为" + maxValue);
				} else if (minValue > valueInt) {
					// throwConversionException(tation, StringUtil.getMsgs(
					// PageHolderUtil.MIN, PageHolderUtil.VALUE,
					// PageHolderUtil.TO));

					throwConversionException(tation, "最小值为" + minValue);
				}

			} else if (CharacterTypeEnums.Double.equals(tation.type())) {
				double valueInt = ConverterUtil.doubleConverter(value);
				double maxValue = ConverterUtil.doubleConverter(tation.max());
				double minValue = ConverterUtil.doubleConverter(tation.min());

				if (valueInt > maxValue) {
					// throwConversionException(tation, StringUtil.getMsgs(
					// PageHolderUtil.MAX, PageHolderUtil.VALUE,
					// PageHolderUtil.TO));

					throwConversionException(tation, "最大值为" + maxValue);
				} else if (minValue > valueInt) {
					// throwConversionException(tation, StringUtil.getMsgs(
					// PageHolderUtil.MIN, PageHolderUtil.VALUE,
					// PageHolderUtil.TO));
					throwConversionException(tation, "最小值为" + minValue);
				}
			} else if (CharacterTypeEnums.Float.equals(tation.type())) {
				float valueInt = ConverterUtil.floatConverter(value);
				float maxValue = ConverterUtil.floatConverter(tation.max());
				float minValue = ConverterUtil.floatConverter(tation.min());

				if (valueInt > maxValue) {
					// throwConversionException(tation, StringUtil.getMsgs(
					// PageHolderUtil.MAX, PageHolderUtil.VALUE,
					// PageHolderUtil.TO));

					throwConversionException(tation, "最大值为" + maxValue);
				} else if (minValue > valueInt) {
					// throwConversionException(tation, StringUtil.getMsgs(
					// PageHolderUtil.MIN, PageHolderUtil.VALUE,
					// PageHolderUtil.TO));
					throwConversionException(tation, "最小值为" + minValue);
				}
			}
		}
		return true;
	}

	/**
	 * 抛出异常方法
	 * */
	private static void throwConversionException(CharacterAnnotation tation,
			String errorInfo) {
		StringBuilder info = new StringBuilder();
		// if (EmptyUtil.isNotEmpty(tation.keys())) {
		// // FIXME 为国际化预留接口
		// // info.append(StringUtil.getMsgs(tation.keys()));
		// } else if (EmptyUtil.isNotEmpty(tation.info())) {
		// info.append(tation.info());
		// } else {
		// throw new ConversionException(tation.name() + "配置错误");
		// }

		info.append((tation.name()));
		info.append(errorInfo);
		throw new ConversionException(info.toString());
	}

}
