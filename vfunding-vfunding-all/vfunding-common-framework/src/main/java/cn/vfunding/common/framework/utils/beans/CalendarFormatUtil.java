package cn.vfunding.common.framework.utils.beans;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 日期计算公共方法
 * 
 * @author LiuJun
 * @date 2013年12月10日
 */
public class CalendarFormatUtil extends DateFormatUtils {
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	public static String format(Date date, String pattern) {
		if (EmptyUtil.isEmpty(date)) {
			return null;
		}
		return format(date, pattern, null, null);
	}

	public static Date formatDate(Object obj, String parrern) {
		if (EmptyUtil.isEmpty(obj)) {
			return null;
		}
		return ConverterUtil.DateConverterStr(obj.toString(), parrern);
	}
}
