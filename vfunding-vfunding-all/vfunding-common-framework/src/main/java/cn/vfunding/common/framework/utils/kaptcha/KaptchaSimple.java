package cn.vfunding.common.framework.utils.kaptcha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

/**
 * 验证码辅助类
 * 
 * @author liuyijun
 * 
 */
public class KaptchaSimple {

	private static final String KAPTCHA_SESSION_KEY = "KAPTCHA_SESSION_KEY";

	/**
	 * 生成校验码图片返回，并将图片文本记入HttpSession
	 * 
	 * @param httpSession
	 * @return
	 * @throws IOException
	 */
	public static ResponseEntity<byte[]> gen(HttpSession httpSession)
			throws IOException {
		Properties properties = new java.util.Properties();
		properties.put("kaptcha.border", "yes");
		properties.put("kaptcha.textproducer.char.length", "4");
		properties.put("kaptcha.textproducer.font.color", "white");
		properties.put("kaptcha.textproducer.font.size", "32");
		properties.put("kaptcha.noise.color", "red");
		properties.put("kaptcha.background.clear.from", "blue");
		properties.put("kaptcha.background.clear.to", "black");
		properties.put("kaptcha.image.width", "120");
		properties.put("kaptcha.image.height", "40");
		properties.put("kaptcha.obscurificator.impl",
				"com.google.code.kaptcha.impl.ShadowGimpy");

		return KaptchaSimple.gen(httpSession, properties);
	}

	/**
	 * 生成校验码图片返回，并将图片文本记入HttpSession
	 * 
	 * @param httpSession
	 * @return
	 * @throws IOException
	 */
	public static ResponseEntity<byte[]> gen(HttpSession httpSession,
			Properties properties) throws IOException {
		Config config = new com.google.code.kaptcha.util.Config(properties);
		DefaultKaptcha captchaProducer = new com.google.code.kaptcha.impl.DefaultKaptcha();
		captchaProducer.setConfig(config);
		String capText = captchaProducer.createText();
		BufferedImage bi = captchaProducer.createImage(capText);
		httpSession.setAttribute(KAPTCHA_SESSION_KEY, capText);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(bi, "jpg", byteArrayOutputStream);
		byte[] bytes = byteArrayOutputStream.toByteArray();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Control", "post-check=0, pre-check=0");
		headers.add("Cache-Control", "no-store, no-cache, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Content-Type", "image/jpg");
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
	}

	public static BufferedImage genImage(HttpSession httpSession)
			throws IOException {
		Properties properties = new java.util.Properties();
		properties.put("kaptcha.border", "yes");
		properties.put("kaptcha.textproducer.char.length", "4");
		properties.put("kaptcha.textproducer.font.color", "white");
		properties.put("kaptcha.textproducer.font.size", "32");
		properties.put("kaptcha.noise.color", "red");
		properties.put("kaptcha.background.clear.from", "blue");
		properties.put("kaptcha.background.clear.to", "black");
		properties.put("kaptcha.image.width", "80");
		properties.put("kaptcha.image.height", "30");
		properties.put("kaptcha.obscurificator.impl",
				"com.google.code.kaptcha.impl.ShadowGimpy");
		Config config = new com.google.code.kaptcha.util.Config(properties);
		DefaultKaptcha captchaProducer = new com.google.code.kaptcha.impl.DefaultKaptcha();
		captchaProducer.setConfig(config);
		String capText = captchaProducer.createText();
		BufferedImage bi = captchaProducer.createImage(capText);
		httpSession.setAttribute(KAPTCHA_SESSION_KEY, capText);
		return bi;
	}

	/**
	 * 生成字符串验证码文本放入session并返回
	 * 
	 * @param httpSession
	 * @return
	 * @throws IOException
	 */
	public static String returnGenCode(HttpSession httpSession)
			throws IOException {
		int[] codes = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		String code = "";
		Random random = new Random();

		for (int i = 0; i < 4; i++) {
			int index = random.nextInt(codes.length);
			int j = codes[index];
			code += j;
		}
		httpSession.setAttribute(KAPTCHA_SESSION_KEY, code);
		return code;
	}

	/**
	 * 校验returnCheckCode是否与最新记入的校验码一致
	 * 
	 * @param httpSession
	 * @param returnCheckCode
	 * @return
	 */
	public static boolean check(HttpSession httpSession, String returnCheckCode) {
		String capText = (String) httpSession.getAttribute(KAPTCHA_SESSION_KEY);
		if (capText == null)
			return false;
		if (capText.equals(returnCheckCode)) {
			return true;
		} else {
			return false;
		}
	}
}
