/**
 * 
 */
package cn.vfunding.vfunding.biz.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author changtian
 * @date 2013年12月12日
 */
public enum YesAndNoEnum implements cn.vfunding.common.framework.utils.beans.SupperEnums{
	是(1), 否(2);
	private Integer values = null;

	public Integer getValues() {
		return values;
	}

	YesAndNoEnum(Integer values) {
		this.values = values;
	}

	/**
	 * 根据传入Value获取状态信息实体
	 * 
	 * @param value
	 * @return
	 */
	public static YesAndNoEnum getEnumByValue(int value) {
		for (YesAndNoEnum enums : values()) {
			if (enums.getValues().intValue() == value) {
				return enums;
			}
		}
		return null;
	}

	/**
	 * 返回名称
	 * 
	 * @return
	 */
	public String getName() {
		return this.name();
	}

	/**
	 * 获取枚举中的数据，便于select中进行选择
	 * 
	 * @author LiuJun
	 * */
	public static List<YesAndNoEnum> getClientTypeEnums() {
		List<YesAndNoEnum> lists = new ArrayList<YesAndNoEnum>();
		for (YesAndNoEnum e : values()) {
			String value = String.valueOf(e.getValues());
			if ("-1".equals(value)) {
				continue;
			}

			lists.add(e);
		}

		return lists;
	}

	/**
	 * 获取枚举中的数据，便于select中进行选择
	 * 
	 * @author LiuJun
	 * */
	public static List<Map<String, String>> getDatas() {
		List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
		for (YesAndNoEnum e : values()) {
			String value = String.valueOf(e.getValues());
			if ("-1".equals(value)) {
				continue;
			}
			Map<String, String> maps = new HashMap<String, String>();

			maps.put("value", value);
			maps.put("text", e.name());
			lists.add(maps);
		}

		return lists;
	}
}
