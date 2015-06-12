package cn.vfunding.vfunding.biz.sina.util;

/**
 * 
 * @author wang.zeyan
 * @date 2015年1月20日
 * @version 1.0
 */
public class SinaControllerUtil {
	
	public final static String Delimiter = "/";
	
	public final static String sinaSendAction = "sinaSendAction";
	
	/**
	 * Controller 显示地址
	 */
	public final static String C_sinaSendAction = Delimiter + sinaSendAction; 
	
	public final static String register = "register";
	
	public final static String C_register = Delimiter + register;
	
	/**
	 * Controller register 访问地址
	 */
	public final static String C_registerURL = C_sinaSendAction + C_register;
	
	public final static String mobileBindVerify = "mobileBindVerify";
	
	public final static String C_mobileBindVerify = Delimiter + mobileBindVerify;
	
	public final static String C_mobileBindVerifyURL = C_sinaSendAction + C_mobileBindVerify;
}
