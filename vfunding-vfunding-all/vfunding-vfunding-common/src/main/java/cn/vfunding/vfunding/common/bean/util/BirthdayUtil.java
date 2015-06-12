package cn.vfunding.vfunding.common.bean.util;

import java.util.Date;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;

public class BirthdayUtil {

	public static String getBirthdyByCardId(String cardId) {
		String month = "";
		String day = "";
		System.out.println(cardId.length());
		if (cardId.length() == 18) {
			month = cardId.substring(10, 12);
			day = cardId.substring(12, 14);
		} else if (cardId.length() == 15) {
			month = cardId.substring(8, 10);
			day = cardId.substring(10, 12);
		}
		if (EmptyUtil.isNotEmpty(month) && EmptyUtil.isNotEmpty(day)) {
			return month + "-" + day;
		}
		return "";
	}

}
