package cn.p2p.p2p.prd.mobile.utils;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DESCipher {
	private static String DESKey = "vf1010PhoneDES12"; // 字节数必须是8的倍数
	private static byte[] iv1 = { (byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF };

	public byte[] desEncrypt(byte[] plainText) throws Exception {
		// SecureRandom sr = new SecureRandom();
		// sr.setSeed(iv);
		// IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
		IvParameterSpec iv = new IvParameterSpec(iv1);
		DESKeySpec dks = new DESKeySpec(DESKey.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		byte data[] = plainText;
		byte encryptedData[] = cipher.doFinal(data);
		return encryptedData;
	}

	public String encrypt(String input) {
		String result = "";
		try {
			result = base64Encode(desEncrypt(input.getBytes()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String base64Encode(byte[] s) {
		if (s == null)
			return null;
		return Base64.encodeToString(s, Base64.DEFAULT);

	}

	public static void main(String[] args) {
		DESCipher des = new DESCipher();
		System.out.print(des.encrypt("18015914821"));
		String sendString = "xhxmi5uIv9yNLfy7SOvQ8A==";
		byte[] sendBytes;
		try {
			sendBytes = sendString.getBytes("US-ASCII");
			System.out.println(des.desEncrypt(sendBytes));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
