package cn.vfunding.common.framework.utils.beans;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.BigIntegerConverter;
import org.apache.commons.beanutils.converters.BooleanConverter;
import org.apache.commons.beanutils.converters.ClassConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 公共转换方法
 * 
 * @author LiuJun
 * */
public class ConverterUtil {
	/**
	 * 将对象转换为BigDecimal对象,如果value为null,则返回null;
	 * 
	 * @author LiuJun
	 * */
	public static BigDecimal bigDecimalConverter(Object value) {
		BigDecimalConverter big = new BigDecimalConverter(true);
		return (BigDecimal) big.convert(null, value);
	}

	/**
	 * 将对象转换为BigInteger对象,如果value为null,则返回null;
	 * 
	 * @author LiuJun
	 * */
	public static BigInteger bigIntegerConverter(Object value) {
		BigIntegerConverter big = new BigIntegerConverter(true);
		return (BigInteger) big.convert(null, value);
	}

	/**
	 * 将对象转换为Boolean对象,如果value为null,则返回null;
	 * <p>
	 * "yes","y","true","on","1"都转换为true
	 * </p>
	 * <p>
	 * "no","n","false","off","0"都转换为false
	 * </p>
	 * 
	 * @author LiuJun
	 * */
	public static Boolean booleanConverter(Object value) {
		BooleanConverter converter = new BooleanConverter(true);
		return (Boolean) converter.convert(null, value);
	}

	/**
	 * 将对象转换为Class对象,如果value为null,则返回null；如果此类没有加载，则自动进行加载后返回Class对象;
	 * 
	 * @author LiuJun
	 * */
	public static Class<?> classConverter(Object value) {
		ClassConverter converter = new ClassConverter(true);
		return (Class<?>) converter.convert(null, value);
	}

	/**
	 * 将对象转换为Double对象（对Number对象也能转换),如果value为null,则返回null；
	 * 
	 * 
	 * @author LiuJun
	 * */
	public static Double doubleConverter(Object value) {
		DoubleConverter converter = new DoubleConverter(true);
		return (Double) converter.convert(null, value);
	}

	/**
	 * 将对象转换为Float对象（对Number对象也能转换),如果value为null,则返回null；
	 * 
	 * 
	 * @author LiuJun
	 * */
	public static Float floatConverter(Object value) {
		FloatConverter converter = new FloatConverter(true);
		return (Float) converter.convert(null, value);
	}

	/**
	 * 将对象转换为Float对象（对Number对象也能转换),如果value为null,则返回null；
	 * 
	 * 
	 * @author LiuJun
	 * */
	public static Integer integerConverter(Object value) {
		IntegerConverter converter = new IntegerConverter(true);
		if (EmptyUtil.isEmpty(value)) {
			return 0;
		}
		return (Integer) converter.convert(null, value);
	}

	/**
	 * 将时间(date)对象转换为String
	 * <ul>
	 * <li>如果date为null,则返回null</li>
	 * <li>如果format为空或是"null"、"undefined"则取默认值"yyyy-MM-dd HH:mm:ss"</li>
	 * </ul>
	 * 
	 * @author LiuJun
	 * */
	public static String StringConverterDate(Date date, String format) {
		if (NullUtil.isNull(date)) {
			return null;
		}

		if (EmptyUtil.isEmptyChars(format)) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		return DateFormatUtils.format(date, format);
	}

	/**
	 * 将时间(String类型)对象转换为Date
	 * <ul>
	 * <li>如果date为null,则返回null</li>
	 * <li>如果format为空或是"null"、"undefined"则取默认值"yyyy-MM-dd HH:mm:ss"</li>
	 * </ul>
	 * 
	 * @author LiuJun
	 * */
	public static Date DateConverterStr(String date, String format) {
		if (EmptyUtil.isEmptyChars(date)) {
			return null;
		}

		if (EmptyUtil.isEmptyChars(format)) {
			format = "yyyy-MM-dd HH:mm:ss";
		}

		// FastDateFormat fast = FastDateFormat.getInstance(format);
		SimpleDateFormat simple = new SimpleDateFormat(format);
		try {
			return simple.parse(date);
			// Object obj = fast.parseObject(date);
			// if (NullUtil.isNotNull(obj)) {
			// return (Date) obj;
			// }
		} catch (ParseException e) {
			throw new ConversionException(e);
		}
	}

	/**
	 * 将输入流copy成输出流
	 * 
	 * @author LiuJun
	 * */
	public static long copy(Reader input, Writer output) throws IOException {
		return IOUtils.copyLarge(input, output);
	}

	/**
	 * 月还额度
	 * 
	 * @Description
	 * @param account
	 * @param apr
	 *            如：18
	 * @param monthSum
	 * @return
	 * @author liuhuan
	 */
	public static BigDecimal monthlyAccount(BigDecimal account, BigDecimal apr, BigDecimal monthSum) {
		// :每月月供额=〔贷款本金×月利率×(1＋月利率)＾还款月数〕÷〔(1＋月利率)＾还款月数-1〕
		BigDecimal monthApr = apr.divide(new BigDecimal("1200"), 20, BigDecimal.ROUND_HALF_UP);
		BigDecimal flag = new BigDecimal("1");
		// (1＋月利率)＾还款月数
		BigDecimal temp = (flag.add(monthApr)).pow(monthSum.intValue());
		BigDecimal first = account.multiply(monthApr).multiply(temp);
		BigDecimal second = temp.subtract(flag);
		return first.divide(second, 2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 
	 * 
	 * @Description:每月应还利息=贷款本金×月利率×〔(1+月利率)^还款月数-(1+月利率)^(还款月序号-1)〕÷〔(1+月利率)^还款月数-1〕
	 * @param account
	 * @param apr
	 *            如：18
	 * @param monthSum
	 *            总月份
	 * @param thisMonth
	 *            当前月
	 * @return
	 * @author liuhuan
	 */
	/*
	 * public static BigDecimal monthlyInterest(BigDecimal account, BigDecimal
	 * apr, BigDecimal monthSum, int thisMonth) { BigDecimal monthApr =
	 * apr.divide(new BigDecimal("1200"), 10, BigDecimal.ROUND_HALF_UP); BigDecimal
	 * flag = new BigDecimal("1"); // (1＋月利率)＾还款月数 BigDecimal temp =
	 * (flag.add(monthApr)).pow(monthSum.intValue()); // 贷款本金×月利率 BigDecimal
	 * accountApr = account.multiply(monthApr); return accountApr.multiply(
	 * temp.subtract((flag.add(monthApr).pow(thisMonth - 1)))).divide(
	 * temp.subtract(flag), 2, BigDecimal.ROUND_HALF_UP); }
	 */

	/**
	 * 月还利息
	 * 贷款本金*月利率*(1+月利率)^还贷总月数/[(1+月利率)^还贷总月数-1]-贷款本金*月利率(1+月利率)^(当前还款月-1)/[(1+
	 * 月利率)^还贷总月数-1] a*i(1+i)^N/[(1+i)^N-1]-a*i(1+i)^(n-1)/[(1+i)^N-1] 真实标
	 * 
	 * @return author LiLei 2014年4月22日
	 */
	public static BigDecimal monthlyInterest(BigDecimal account, BigDecimal apr, BigDecimal monthSum, BigDecimal thisMonth) {
		BigDecimal monthApr = apr.divide(new BigDecimal("1200"), 20, BigDecimal.ROUND_HALF_UP);
		BigDecimal flag = new BigDecimal("1");
		// (1+i)^N
		BigDecimal a = (monthApr.add(flag)).pow(monthSum.intValue()).setScale(20, BigDecimal.ROUND_HALF_UP);
		// (1+i)^(n-1)
		BigDecimal b = (monthApr.add(flag)).pow((thisMonth.intValue() - 1)).setScale(20, BigDecimal.ROUND_HALF_UP);
		// a*i(1+i)^N/[(1+i)^N-1]
		BigDecimal c = (account.multiply(monthApr).multiply(a)).divide(a.subtract(flag), 20, BigDecimal.ROUND_HALF_UP);
		// a*i(1+i)^(n-1)/[(1+i)^N-1]
		BigDecimal d = (account.multiply(monthApr).multiply(b)).divide(a.subtract(flag), 20, BigDecimal.ROUND_HALF_UP);
		return c.subtract(d).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 等本等息每月还款总额 （总额/总期数+总额*月利息 ）
	 * @param account
	 * @param apr
	 * @param monthSum
	 * @return
	 * @author liuhuan
	 */
	public static BigDecimal monthlyAccount1(BigDecimal account,BigDecimal apr,BigDecimal monthSum){
		BigDecimal monthApr = apr.divide(new BigDecimal("1200"), 20, BigDecimal.ROUND_HALF_UP);
		//  总额/总期数
		BigDecimal temp = account.divide(monthSum, 20, BigDecimal.ROUND_HALF_UP);
		//  总额*月利息
		BigDecimal temp2 = account.multiply(monthApr);
		BigDecimal result = temp.add(temp2).setScale(2, BigDecimal.ROUND_HALF_UP);
		return result;
	}
	/**
	 * 等本等息  每月利息  (总额*月利息)
	 * @param account 总额
	 * @param apr 年利率  如：12
	 * @return
	 * @author liuhuan
	 */
	public static BigDecimal monthlyInterest(BigDecimal account, BigDecimal apr){
		BigDecimal monthApr = apr.divide(new BigDecimal("1200"), 20, BigDecimal.ROUND_HALF_UP);
		BigDecimal result = account.multiply(monthApr).setScale(2, BigDecimal.ROUND_HALF_UP);
		return result;
	}
	/**
	 * 一次性到期付款的利息
	 * 
	 * @param account
	 * @param apr
	 * @param monthSum
	 * @return author LiLei 2014年4月23日
	 */
	public static BigDecimal disposableInterest(BigDecimal account, BigDecimal apr, BigDecimal monthSum) {
		BigDecimal monthApr = apr.divide(new BigDecimal("1200"), 20, BigDecimal.ROUND_HALF_UP);
		BigDecimal disposableInterest = account.multiply(monthApr).multiply(monthSum).setScale(2, BigDecimal.ROUND_HALF_UP);
		return disposableInterest;
	}

	public static void main(String[] args) {
		System.out.println(ConverterUtil.disposableInterest(new BigDecimal(2000), new BigDecimal(12.8), new BigDecimal(1)));

		System.out.println(multiply("1.591", "9.50"));
		System.out.println(divide("9", "5.16111"));
		
		
		System.out.println(monthlyInterest(new BigDecimal("150000"),new BigDecimal("18")));
		System.out.println(monthlyInterest(new BigDecimal("100000"),new BigDecimal("13.8")));
		System.out.println(monthlyInterest(new BigDecimal("100000"),new BigDecimal("12.3")));
		
		System.out.println(monthlyAccount1(new BigDecimal("100000"),new BigDecimal("12"),new BigDecimal("5")));
		System.out.println(monthlyAccount1(new BigDecimal("100000"),new BigDecimal("12.3"),new BigDecimal("3")));
	}

	/**
	 * 天标利息计算
	 * 
	 * @param days
	 * @param account
	 * @param apr
	 * @return author LiLei 2014年5月13日
	 */
	public static BigDecimal daysInterest(int days, BigDecimal account, BigDecimal apr) {
		return monthlyAccount(account, apr, new BigDecimal("1")).subtract(account).divide(new BigDecimal("30"), 2, BigDecimal.ROUND_HALF_UP)
				.multiply(new BigDecimal(days));
	}

	/**
	 * @Description:平台服务费、担保费 =借款人利息*bfee%/apr%
	 * @param interest
	 * @param scale
	 *            如：4.4
	 * @param apr
	 *            如：18
	 * @return
	 * @author liuhuan
	 */
	public static BigDecimal serviceFee(BigDecimal interest, BigDecimal scale, BigDecimal apr) {
		return interest.multiply(scale).divide(apr, 2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * @Description:String转换BigDecimal
	 * @param arg
	 * @return
	 * @author liuhuan
	 */
	public static BigDecimal StringToBigDecimal(String arg) {
		return new BigDecimal(arg);
	}

	/**
	 * @Description:两个String相乘，保留两位
	 * @param arg
	 * @param arg2
	 * @return
	 * @author liuhuan
	 */
	public static BigDecimal multiply(String arg, String arg2) {
		return new BigDecimal(arg).multiply(new BigDecimal(arg2)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * @Description:两个String相除，保留两位
	 * @param arg
	 * @param arg2
	 * @return
	 * @author liuhuan
	 */
	public static BigDecimal divide(String arg, String arg2) {
		return new BigDecimal(arg).divide(new BigDecimal(arg2), 2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 数字金额大写转换，思想先写个完整的然后将如零拾替换成零 要用到正则表达式
	 */
	public static String digitUppercase(double n) {
		String fraction[] = { "角", "分" };
		String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String unit[][] = { { "元", "万", "亿" }, { "", "拾", "佰", "仟" } };
		String head = n < 0 ? "负" : "";
		n = Math.abs(n);
		String s = "";
		for (int i = 0; i < fraction.length; i++) {
			s += (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
		}
		if (s.length() < 1) {
			s = "整";
		}
		int integerPart = (int) Math.floor(n);
		for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
			String p = "";
			for (int j = 0; j < unit[1].length && n > 0; j++) {
				p = digit[integerPart % 10] + unit[1][j] + p;
				integerPart = integerPart / 10;
			}
			s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
		}
		return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
	}
}
