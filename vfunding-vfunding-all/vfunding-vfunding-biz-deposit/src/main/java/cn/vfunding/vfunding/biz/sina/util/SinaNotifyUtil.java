package cn.vfunding.vfunding.biz.sina.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;






import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.sina.tools.SignUtil;
import cn.vfunding.vfunding.biz.sina.tools.Tools;

public class SinaNotifyUtil {
	
	
	static Logger logger = LoggerFactory.getLogger("sinaNotifyActionLog");
	
	/**
	 * 获取request所有参数
	 * @param request
	 * @return
	 * @author louchen 2015-01-21
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	public static String getAllParametersToString(HttpServletRequest request){
		Enumeration enu=request.getParameterNames(); 
		String str = "";
		while(enu.hasMoreElements()){  
			String paraName=(String)enu.nextElement();  
			if(EmptyUtil.isEmpty(str)){
				str+=paraName+"="+request.getParameter(paraName);
			}else{
				str+=","+paraName+"="+request.getParameter(paraName);
			}
		}  
		return str;
	}	
	
	/**
	 * 获取request所有参数
	 * @param request
	 * @return
	 * @author louchen 2015-01-21
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String,String> getAllParameters(HttpServletRequest request){
		Enumeration enu=request.getParameterNames(); 
		Map<String,String> map = new HashMap<String,String>();
		while(enu.hasMoreElements()){  
			String paraName=(String)enu.nextElement();  
			map.put(paraName, request.getParameter(paraName));
		}  
		return map;
	}
	
	/**
	 * 获取新浪异步回调通知签名,验证签名是否成功
	 * @param params
	 * @return Boolean
	 * @throws Exception 
	 */
	public static Boolean checkNotifySign(Map<String,String> params) throws Exception{
		Boolean result=false;
		String sign_result = params.get("sign").toString();
		String sign_type_result = params.get("sign_type").toString();
		String _input_charset_result = params.get("_input_charset").toString();
		logger.info("sina 回调参数  :  "+JSON.toJSONString(params));
		logger.info("sign_result:"+sign_result +" sign_type_result:"+sign_type_result+" _input_charset_result:"+_input_charset_result);
		params.remove("sign");
		params.remove("sign_type");
		params.remove("sign_version");
		// 排序后的基本参数和业务参数 格式key1=value1&key2=value2
		String content = Tools.createLinkString(params, false);
		// 加密key
		String signKey = "";
		if ("MD5".equalsIgnoreCase(sign_type_result)) {
			signKey = SinaParamsUtil.getInstance().getKeyMD5();
		} else {
			signKey = SinaParamsUtil.getInstance().getKeyRSA();
		}
		logger.info("MD5Key:"+SinaParamsUtil.getInstance().getKeyMD5());
		logger.info("RSAKey:"+SinaParamsUtil.getInstance().getKeyRSA());
		// 加密
		String sign = SignUtil.sign(content, sign_type_result, signKey,
				_input_charset_result);
		logger.info("本地加密后sign:"+sign);
		if(sign_result.equals(sign)){
			result = true;
		}
		return result;
	}
	
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("deposit_amount", "5.00");
		map.put("sign", "5005ca7a417fb6f7f9d1801b787e545f");
		map.put("_input_charset", "utf-8");
		map.put("notify_time", "20150211003618");
		map.put("deposit_status", "SUCCESS");
		map.put("outer_trade_no", "1423586009105");
		map.put("sign_type", "MD5");
		map.put("notify_type", "deposit_status_sync");
		map.put("inner_trade_no", "102142358600943514912");
		map.put("notify_id", "f1e8e98c6f6b4f6ba03c7db7a1c47604");
		map.put("version", "1.0");
		try {
			SinaNotifyUtil.checkNotifySign(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
