package cn.vfunding.common.framework.utils.beans;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

/**
 * 时间处理公共函数
 * 
 * @author LiuJun
 * @date 2013年12月10日
 */
public class CalendarUtil extends DateUtils {
	/**
	 * 获取当前时间
	 * 
	 * @return Date
	 */
	public static Date getDate() {
		return new Date();
	}

	/**
	 * 获取当前时间，将其转换为String类型
	 * 
	 * @param pattern
	 *            时间转换格式,默认为yyyy-MM-dd
	 * @return
	 */
	public static String getDateTOStr(String pattern) {
		if (EmptyUtil.isEmpty(pattern)) {
			pattern = "yyyy-MM-dd";
		}
		return DateFormatUtils.format(new Date(), pattern);
	}
}
