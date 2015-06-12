package cn.vfunding.vfunding.biz.sina.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.vfunding.biz.sina.tools.GsonUtil;
import cn.vfunding.vfunding.biz.sina.tools.SignUtil;
import cn.vfunding.vfunding.biz.sina.tools.Tools;
import cn.vfunding.vfunding.biz.sina.tools.VoToMapUtil;
import cn.vfunding.vfunding.biz.sina.vo.returns.BaseSinaReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.BaseSinaSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateActivateMemberSendVO;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;

/**
 * 新浪资金托管 发送消息工具类
 * 
 * @author louchen 2015-01-14
 *
 */
public class SinaSendUtil {
	
	/**
	 * 发送新浪接口消息
	 * @param obj
	 * 	必须继承BaseSinaSendVO
	 * @param mode
	 * 	1=收单网关
	 * 	0=会员网关
	 * @param clazz
	 * 	返回类名
	 * @return
	 * @throws Exception
	 */
	public static <T extends BaseSinaReturnVO> T sendMsg(Object obj,Integer mode,Class<T> clazz) throws Exception {
		if (obj instanceof BaseSinaSendVO && mode >=0 && mode <=1) {
			//获取请求地址
			String url = null;
			if(mode.equals(1)){
				url = SinaParamsUtil.getInstance().getTradesUrl();
			}else if(mode.equals(0)){
				url = SinaParamsUtil.getInstance().getMembersUrl();
			}
			//获取请求参数的签名
			BaseSinaSendVO base = (BaseSinaSendVO) obj;
			base.setSign(SinaSignUtil.getSignMsg(obj));
			//获取所有请求参数
			Map<String, String> params = VoToMapUtil.voToMap(obj, "send");
			String param = Tools.createLinkString(params, false);
			//发送请求获取返回结果
			String msgResult = "";
			try {
				msgResult = URLDecoder.decode(SinaSendUtil.sendPost(url, param),
						SinaParamsUtil.getInstance().getInputCharset());
			} catch (Exception e) {
				throw e;
			}
			//校验返回签名
			if (SinaSendUtil.checkSign(msgResult)) {
				return JSON.parseObject(msgResult,clazz);
			} else {
				throw new Exception("签名失败");
			}

		} else {
			throw new Exception("参数异常");
		}
	}

	public static void main(String[] args) throws Exception {
		CreateActivateMemberSendVO cams = new CreateActivateMemberSendVO();
		cams.setRequest_time("20140101000000");
		cams.setIdentity_id("18016673380");
		cams.setIdentity_type("UID");
		cams.setMember_type("1");
		System.out.println("运行开始时间:" + DateUtil.parseDateTime(new Date()));
		BaseSinaReturnVO result = SinaSendUtil.sendMsg(cams,0,BaseSinaReturnVO.class);
		System.out.println(result.toString());
		System.out.println("运行结束时间:" + DateUtil.parseDateTime(new Date()));
	}

	/**
	 * 签名校验
	 * 
	 * @param result
	 * @return
	 * @throws Exception
	 */
	private static boolean checkSign(String result) throws Exception {
		Map<String, String> content = GsonUtil.fronJson2Map(result);
		if (!StringUtils.isEmpty(content.get("sign_type"))) {
			String signKey = "";
			if ("MD5".equalsIgnoreCase(content.get("sign_type").toString())) {
				signKey = SinaParamsUtil.getInstance().getKeyMD5();
			} else {
				signKey = SinaParamsUtil.getInstance().getKeyRSA();
			}
			String sign_result = content.get("sign").toString();
			String sign_type_result = content.get("sign_type").toString();
			String _input_charset_result = content.get("_input_charset")
					.toString();
			content.remove("sign");
			content.remove("sign_type");
			content.remove("sign_version");
			String like_result = Tools.createLinkString(content, false);
			if (SignUtil.Check_sign(like_result.toString(), sign_result,
					sign_type_result, signKey, _input_charset_result)) {
				return true;
			} else {
				return false;
			}
		}else{
			return true;
		}
	}
	
	@SuppressWarnings("unused")
	private static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public static String sendPost(String url, String params) throws Exception {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(params);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			throw e;
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}
