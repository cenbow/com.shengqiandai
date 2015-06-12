package cn.vfunding.vfunding.biz.sina.util;

import java.io.UnsupportedEncodingException;

import cn.vfunding.vfunding.biz.sina.tools.Base64;
import cn.vfunding.vfunding.biz.sina.tools.RSA;

import com.alibaba.druid.util.StringUtils;



public class RsaEncryptUtil {
	private static final String encrypt = SinaParamsUtil.getInstance().getKeyRSAparm();
	
	public static String encrypt(String data) throws UnsupportedEncodingException, Exception{
		if(StringUtils.isEmpty(data)){
			return null;
		}else{
			byte[] dataByta = null;
			dataByta = RSA.encryptByPublicKey(
						data.getBytes("utf-8"), encrypt);
			String base64Data = Base64.encode(dataByta);
			return base64Data.toString();
		}
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println("中".getBytes());
		System.out.println("中".getBytes());
		System.out.println("123".getBytes());
		System.out.println("123".getBytes("utf-8"));
	}
}
