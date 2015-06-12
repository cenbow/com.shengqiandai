package cn.vfunding.common.framework.utils.beans;

import java.math.BigDecimal;

public class MoneyUtil {
	private static final int _$1 = 2;

	public MoneyUtil() {
	}

	
	/**
	 * 两个double类型数字相加
	 * @param d
	 * @param d1
	 * @return
	 * 2014年4月25日
	 * liuyijun
	 */
	public static double add(double d, double d1) {
		BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
		BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
		return bigdecimal.add(bigdecimal1).doubleValue();
	}

	public static double add(String s, String s1) {
		BigDecimal bigdecimal = new BigDecimal(s);
		BigDecimal bigdecimal1 = new BigDecimal(s1);
		return bigdecimal.add(bigdecimal1).doubleValue();
	}

	public static double sub(double d, double d1) {
		BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
		BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
		return bigdecimal.subtract(bigdecimal1).doubleValue();
	}

	public static double sub(String s, String s1) {
		BigDecimal bigdecimal = new BigDecimal(s);
		BigDecimal bigdecimal1 = new BigDecimal(s1);
		return bigdecimal.subtract(bigdecimal1).doubleValue();
	}

	public static double mul(double d, double d1) {
		BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
		BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
		return bigdecimal.multiply(bigdecimal1).doubleValue();
	}

	public static double mul(String s, String s1) {
		BigDecimal bigdecimal = new BigDecimal(s);
		BigDecimal bigdecimal1 = new BigDecimal(s1);
		return bigdecimal.multiply(bigdecimal1).doubleValue();
	}

	public static double div(double d, double d1) {
		return div(d, d1, 2);
	}

	public static double div(String s, String s1) {
		return div(Double.parseDouble(s), Double.parseDouble(s1), 2);
	}

	public static double div(double d, double d1, int i) {
		if (i < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
		BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
		return bigdecimal.divide(bigdecimal1, i, 4).doubleValue();
	}

	public static double div(String s, String s1, int i) {
		if (i < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal bigdecimal = new BigDecimal(s);
		BigDecimal bigdecimal1 = new BigDecimal(s1);
		return bigdecimal.divide(bigdecimal1, i, 4).doubleValue();
	}

	public static double round(double d, int i) {
		if (i < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
		BigDecimal bigdecimal1 = new BigDecimal("1");
		return bigdecimal.divide(bigdecimal1, i, 4).doubleValue();
	}

	public static double round(String s, int i) {
		if (i < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal bigdecimal = new BigDecimal(s);
		BigDecimal bigdecimal1 = new BigDecimal("1");
		return bigdecimal.divide(bigdecimal1, i, 4).doubleValue();
	}
	
	public static String roundformat(double d,int i){
		String num = null;
		if(i==1){
			num="0.0";
		}
		if(i==2){
			num="0.00";
		}
		if(i==3){
			num="0.000";
		}
		if(i==4){
			num="0.0000";
		}
		if(i==0){
			num="0";
		}
		java.text.DecimalFormat   df   =new   java.text.DecimalFormat(num);
		return df.format(d);
	}
}