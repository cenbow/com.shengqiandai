package cn.p2p.p2p.prd.mobile.utils;

public class CharUtil {
	public static String change(String str) {
		char[] buffer = str.toCharArray();

		for (int i = 0; i < buffer.length; i++) {
			char ch = buffer[i];
			if (Character.isLowerCase(ch)) {
				buffer[i] = Character.toUpperCase(ch);
			}
		}
		return new String(buffer);
	}
}
