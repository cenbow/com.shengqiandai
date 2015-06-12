package cn.vfunding.vfunding.biz.sina.util;

import java.net.URLEncoder;
import java.util.Map;

import cn.vfunding.vfunding.biz.sina.tools.SignUtil;
import cn.vfunding.vfunding.biz.sina.tools.Tools;
import cn.vfunding.vfunding.biz.sina.tools.VoToMapUtil;
import cn.vfunding.vfunding.biz.sina.vo.sends.BaseSinaSendVO;

/**
 * 新浪资金托管 加密编码工具类
 * 
 * @author louchen 2015-01-13
 *
 */
public class SinaSignUtil {

	/**
	 * 获取签名发送数据
	 * 
	 * @param obj
	 *            必须继承BaseSinaVO
	 * @return
	 * @author louchen 2015-01-13
	 */
	public static String getSignMsg(Object obj) throws Exception {
		String encode = null;
		if (obj instanceof BaseSinaSendVO) {
			BaseSinaSendVO base = (BaseSinaSendVO) obj;
			Map<String, String> params = VoToMapUtil.voToMap(obj, "sign");
			// 排序后的基本参数和业务参数 格式key1=value1&key2=value2
			String content = Tools.createLinkString(params, false);
			// 加密key
			String signKey = "";
			if ("MD5".equalsIgnoreCase(base.getSign_type())) {
				signKey = SinaParamsUtil.getInstance().getKeyMD5();
			} else {
				signKey = SinaParamsUtil.getInstance().getKeyRSA();
			}

			// 加密
			String sign = SignUtil.sign(content, base.getSign_type(), signKey,
					base.get_input_charset());
			// 编码
			encode = URLEncoder.encode(sign, base.get_input_charset());
			return encode;

		}else{
			throw new Exception("参数异常");
		}
	}	
}
