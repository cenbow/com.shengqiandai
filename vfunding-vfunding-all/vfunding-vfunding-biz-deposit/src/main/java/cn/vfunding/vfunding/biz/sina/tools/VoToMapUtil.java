package cn.vfunding.vfunding.biz.sina.tools;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * vo转Map 工具类
 * 
 * @author jianwei
 * @date 2015年1月13日
 */
public class VoToMapUtil {
	/**
	 * 
	 * @param obj
	 *            接口VO对象
	 * @param mapType
	 *            send发送,sign签名
	 * @return
	 * @author jianwei
	 */
	public static Map<String, String> voToMap(Object obj, String mapType) {
		Map<String, String> map = new HashMap<String, String>();
		String json = JSON.toJSONString(obj);
		map = GsonUtil.fronJson2Map(json);
		if (mapType.equals("sign")) {
			map.remove("sign");
			map.remove("sign_type");
			map.remove("sign_version");
		}
		map.put("_input_charset", map.get("input_charset"));
		map.remove("input_charset");
		return map;
	}

//	/**
//	 * 获取发送post请求的参数
//	 * 
//	 * @param obj
//	 * @param sign
//	 * @return
//	 * @throws UnsupportedEncodingException
//	 */
//	public static List<NameValuePair> voToList(Object obj, String sign)
//			throws UnsupportedEncodingException {
//		List<NameValuePair> nvpList = new ArrayList<NameValuePair>();
//		Map<String, String> map = voToMap(obj, "send");
//		map.put("sign", sign);
//		List<String> keys = new ArrayList<String>(map.keySet());
//		Collections.sort(keys);
//		for (int i = 0; i < map.size(); i++) {
//			NameValuePair nvp = new NameValuePair(keys.get(i),
//					URLEncoder.encode(map.get(keys.get(i)),
//							map.get("_input_charset")));
//			nvpList.add(nvp);
//		}
//		return nvpList;
//	}


//	public static void main(String[] args) {
//		SetRealNameVO v = new SetRealNameVO();
//		v.setExtend_param("111");
//		v.setRequest_time("20140101000000");
//		v.setService("aaaaaa");
//		v.setSign_type("sdfdfdf");
//		Map<String, String> map = voToMap(v, "sign");
//		System.out.println(map);
//	}

}
