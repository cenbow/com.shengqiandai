package cn.vfunding.common.framework.utils.beans;

/**
 * 枚举公共方法
 * 
 * @author LiuJun
 * */
public class EnumsUtils {
	/**
	 * 判断两个枚举的name是否相同
	 * 
	 * @author LiuJun
	 * */
	public static boolean equalsName(Enum<?> en1, Enum<?> en2) {
		if (NullUtil.isNull(en1) || NullUtil.isNull(en2)) {
			return false;
		}
		return en1.name().equals(en2.name());
	}

	/**
	 * 判断两个枚举的ordinal是否相同
	 * 
	 * @author LiuJun
	 * */
	public static boolean equalsValue(SupperEnums en1, SupperEnums en2) {
		if (NullUtil.isNull(en1) || NullUtil.isNull(en2)
				|| NullUtil.isNull(en1.getValues())
				|| NullUtil.isNull(en2.getValues())) {
			return false;
		}
		return en1.getValues() == en2.getValues();
	}

	/**
	 * 判断两个枚举的ordinal是否相同
	 * 
	 * @author LiuJun
	 * */
	public static boolean equalsValue(String en1, SupperEnums en2) {
		if (NullUtil.isNull(en1) || NullUtil.isNull(en2)) {
			return false;
		}
		return EqualsUtil.equest(en1, en2.getValues());
	}

	/**
	 * 判断两个枚举的ordinal是否相同
	 * 
	 * @author LiuJun
	 * */
	public static boolean equalsValue(Integer en1, SupperEnums en2) {
		if (NullUtil.isNull(en1) || NullUtil.isNull(en2)
				|| NullUtil.isNull(en2.getValues())) {
			return false;
		}
		return en1.intValue() == en2.getValues().intValue();
	}

	/**
	 * 判断两个枚举的ordinal是否相同
	 * 
	 * @author LiuJun
	 * */
	public static boolean notEqualsOrdinal(Integer en1, Enum<?> en2) {
		if (NullUtil.isNull(en1) || NullUtil.isNull(en2)) {
			return true;
		} else if (en1.intValue() == en2.ordinal()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断值和枚举中的值是否相同
	 * 
	 * @author LiuJun
	 * */
	public static boolean equalsName(String name, Enum<?> e) {
		if (NullUtil.isNotNull(name) || NullUtil.isNull(e)) {
			return false;
		} else if (name.equals(e.name())) {
			return true;
		}
		return false;
	}
}
