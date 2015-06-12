package cn.p2p.p2p.prd.mobile.utils;

import java.util.HashMap;
import java.util.Map;

import cn.p2p.p2p.prd.mobile.vo.VerificationCodeMapVo;

public class VerificationCodeCache {

	public static Map<String, VerificationCodeMapVo> codeMap = new HashMap<String, VerificationCodeMapVo>();

	public static void setCodeMap(String phone, String code, Long time) {
		VerificationCodeMapVo vo = new VerificationCodeMapVo();
		vo.setCode(code);
		vo.setTime(time);
		codeMap.put(phone, vo);
	}

	public static VerificationCodeMapVo getCodeMap(String phone) {
		return codeMap.get(phone);
	}

}
