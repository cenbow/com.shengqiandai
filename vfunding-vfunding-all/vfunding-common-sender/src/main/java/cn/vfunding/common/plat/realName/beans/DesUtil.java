package cn.vfunding.common.plat.realName.beans;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class DesUtil {
	public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";
    private static Log log = LogFactory.getLog(DesUtil.class);

    /**
     * DES算法，加密
     *
     * @param data 待加密字符串
     * @param key  加密私钥，长度不能够小于8位
     * @return 加密后的字节数组，一般结合Base64编码使用
     * @throws CryptException 异常
     */
    public static String encode(String key,String data) throws Exception
    {
        return encode(key, data.getBytes("GBK"));
    }
    /**
     * DES算法，加密
     *
     * @param data 待加密字符串
     * @param key  加密私钥，长度不能够小于8位
     * @return 加密后的字节数组，一般结合Base64编码使用
     * @throws CryptException 异常
     */
    public static String encode(String key,byte[] data) throws Exception
    {
        try
        {
	    	DESKeySpec dks = new DESKeySpec(key.getBytes());
	    	
	    	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            //key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.ENCRYPT_MODE, secretKey,paramSpec);
            
            byte[] bytes = cipher.doFinal(data);
            return Base64.encode(bytes);
        } catch (Exception e)
        {
            throw new Exception(e);
        }
    }

    /**
     * DES算法，解密
     *
     * @param data 待解密字符串
     * @param key  解密私钥，长度不能够小于8位
     * @return 解密后的字节数组
     * @throws Exception 异常
     */
    public static byte[] decode(String key,byte[] data) throws Exception
    {
        try
        {
        	SecureRandom sr = new SecureRandom();
	    	DESKeySpec dks = new DESKeySpec(key.getBytes());
	    	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            //key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.DECRYPT_MODE, secretKey,paramSpec);
            return cipher.doFinal(data);
        } catch (Exception e)
        {
            throw new Exception(e);
        }
    }
    
    /**
     * 获取编码后的值
     * @param key
     * @param data
     * @return
     * @throws Exception
     */
    public static String decodeValue(String key,String data) 
    {
    	byte[] datas;
    	String value = null;
		try {
			if(System.getProperty("os.name") != null && (System.getProperty("os.name").equalsIgnoreCase("sunos") || System.getProperty("os.name").equalsIgnoreCase("linux")))
	        {
	    		log.debug("os.name(true)=" + System.getProperty("os.name"));
	    		datas = decode(key, Base64.decode(data));
	    		log.debug("ddd=" + new String(datas));
	        }
	    	else
	    	{
	    		log.debug("os.name(false)=" + System.getProperty("os.name"));
	    		datas = decode(key, Base64.decode(data));
	    		log.debug("ddd=" + new String(datas,"GBK"));
	    	}
			
			value = new String(datas,"GBK");
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("解密失败");
			value = "";
		}
    	return value;
    }

    public static void main(String[] args) throws Exception
    {
      
      System.out.println("明：abc ；密：" + DesUtil.encode("12345678","abc"));
  	  System.out.println("明：ABC ；密：" + DesUtil.encode("12345678","ABC"));
  	  System.out.println("明：中国人 ；密：" + DesUtil.encode("12345678","中国人"));
  	  System.out.println("明：abc123木头人 ；密：" + DesUtil.encode("12345678","caishenbaojiekou"));
  	  System.out.println("中国人=" + DesUtil.decodeValue("12345678", "qMeKyoDWvsE="));
  	  System.out.println("X=" + DesUtil.decodeValue("12345678", "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"));
    }
}
