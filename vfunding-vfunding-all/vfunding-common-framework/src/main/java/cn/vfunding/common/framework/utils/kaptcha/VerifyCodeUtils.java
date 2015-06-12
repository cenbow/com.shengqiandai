package cn.vfunding.common.framework.utils.kaptcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;

/**
 * 验证码生成工具类
 * 
 * @author liuyijun
 * 
 */
public class VerifyCodeUtils {
	private static final String KAPTCHA_SESSION_KEY = "KAPTCHA_SESSION_KEY";
	private static final String VERIFY_CODES = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * 使用系统默认字符源生成验证码
	 * 
	 * @param verifySize
	 *            验证码长度
	 * @return
	 */
	public static String generateVerifyCode(int verifySize,
			HttpSession httpSession) {
		return generateVerifyCode(verifySize, VERIFY_CODES, httpSession);
	}

	/**
	 * 使用指定源生成验证码
	 * 
	 * @param verifySize
	 *            验证码长度
	 * @param sources
	 *            验证码字符源
	 * @return
	 */
	public static String generateVerifyCode(int verifySize, String sources,
			HttpSession httpSession) {
		if (sources == null || sources.length() == 0) {
			sources = VERIFY_CODES;
		}
		int codesLen = sources.length();
		Random rand = new Random(System.currentTimeMillis());
		StringBuilder verifyCode = new StringBuilder(verifySize);
		for (int i = 0; i < verifySize; i++) {
			verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
		}
		String result = verifyCode.toString();
		if (httpSession != null) {
			httpSession.setAttribute(KAPTCHA_SESSION_KEY, result);
		}
		return result;
	}

	public static String generateVerifyCode(int verifySize, String sources) {
		if (sources == null || sources.length() == 0) {
			sources = VERIFY_CODES;
		}
		int codesLen = sources.length();
		Random rand = new Random(System.currentTimeMillis());
		StringBuilder verifyCode = new StringBuilder(verifySize);
		for (int i = 0; i < verifySize; i++) {
			verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
		}
		return verifyCode.toString();
	}

	/**
	 * 创建手机验证码
	 * 
	 * @param verifySize
	 *            长度
	 * @param httpSession
	 * @return
	 * @author liuyijun 2014年4月10日
	 */
	public static String createMobileVerifyCode(int verifySize,
			HttpSession httpSession) {
		return generateVerifyCode(verifySize, "0123456789", httpSession);
	}

	/**
	 * 创建随机数(0~9)
	 * 
	 * @param verifySize
	 * @return 2014年5月4日 liuyijun
	 */
	public static String createVerifyCode(int verifySize) {
		return generateVerifyCode(verifySize, "0123456789");
	}

	/**
	 * 生成随机验证码文件,并返回验证码值
	 * 
	 * @param w
	 * @param h
	 * @param outputFile
	 * @param verifySize
	 * @return
	 * @throws IOException
	 */
	public static String outputVerifyImage(int w, int h, File outputFile,
			int verifySize, HttpSession httpSession) throws IOException {
		String verifyCode = generateVerifyCode(verifySize, httpSession);
		outputImage(w, h, outputFile, verifyCode);
		return verifyCode;
	}

	/**
	 * 输出随机验证码图片流,并返回验证码值
	 * 
	 * @param w
	 * @param h
	 * @param os
	 * @param verifySize
	 * @return
	 * @throws IOException
	 */
	public static String outputVerifyImage(int w, int h, OutputStream os,
			int verifySize, HttpSession httpSession) throws IOException {
		String verifyCode = generateVerifyCode(verifySize, httpSession);
		outputImage(w, h, os, verifyCode);
		return verifyCode;
	}

	/**
	 * 生成指定验证码图像文件
	 * 
	 * @param w
	 * @param h
	 * @param outputFile
	 * @param code
	 * @throws IOException
	 */
	public static void outputImage(int w, int h, File outputFile, String code)
			throws IOException {
		if (outputFile == null) {
			return;
		}
		File dir = outputFile.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			outputFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(outputFile);
			outputImage(w, h, fos, code);
			fos.close();
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * 输出指定验证码图片流
	 * 
	 * @param w
	 * @param h
	 * @param os
	 * @param code
	 * @throws IOException
	 */
	public static void outputImage(int w, int h, OutputStream os, String code)
			throws IOException {
		int verifySize = code.length();
		BufferedImage image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		Random rand = new Random();
		Graphics2D g2 = image.createGraphics();
		Color[] colors = new Color[5];
		Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN,
				Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
				Color.PINK, Color.YELLOW };
		float[] fractions = new float[colors.length];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
			fractions[i] = rand.nextFloat();
		}
		Arrays.sort(fractions);
		Paint linearPaint = new LinearGradientPaint(0, 0, w, h, fractions,
				colors);
		Paint linearPaint2 = new LinearGradientPaint(0, 0, w, h, new float[] {
				0.3f, .6f, .8f, .9f }, new Color[] { Color.BLUE, Color.BLACK,
				Color.GREEN, Color.BLUE });
		// 设置图片背景为白色
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, w, h);
		// 设置图片渐变背景
		g2.setPaint(linearPaint);
		g2.fillRoundRect(0, 0, w, h, 5, 5);

		g2.setPaint(linearPaint2);
		int fontSize = (int) (Math.min(w / verifySize, h));
		Font font = new Font("微软雅黑", Font.BOLD, fontSize);
		g2.setFont(font);
		char[] chars = code.toCharArray();
		for (int i = 0; i < verifySize; i++) {
			AffineTransform affine = new AffineTransform();
			affine.setToRotation(
					Math.PI / 4 * rand.nextDouble()
							* (rand.nextBoolean() ? 1 : -1), (w / verifySize)
							* i + fontSize / 2, h / 2);
			g2.setTransform(affine);
			g2.drawChars(chars, i, 1, (w / verifySize) * i, h / 2 + fontSize
					/ 2);
		}
		g2.dispose();
		ImageIO.write(image, "jpg", os);

	}

	/**
	 * 验证验证码信息
	 * 
	 * @param httpSession
	 * @param returnCheckCode
	 * @return
	 * @author liuyijun 2014年4月10日
	 */
	public static boolean check(HttpSession httpSession, String returnCheckCode) {
		String capText = (String) httpSession.getAttribute(KAPTCHA_SESSION_KEY);
		if (capText == null)
			return false;
		if (capText.equalsIgnoreCase(returnCheckCode)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 检测验证码，如果验证成功则删除session中的验证码信息
	 * @param httpSession
	 * @param returnCheckCode
	 * @return
	 * 2014年5月22日
	 * liuyijun
	 */
	public static boolean checkAndRemoveVerifyCode(HttpSession httpSession, String returnCheckCode) {
		String capText = (String) httpSession.getAttribute(KAPTCHA_SESSION_KEY);
		if (capText == null)
			return false;
		if (capText.equalsIgnoreCase(returnCheckCode)) {
			httpSession.removeAttribute(KAPTCHA_SESSION_KEY);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除验证码信息
	 * @param httpSession
	 * @return
	 * @author liuyijun 2014年4月10日
	 */
	public static void removeVerifyCode(HttpSession httpSession) {
		if (EmptyUtil.isNotEmpty(httpSession.getAttribute(KAPTCHA_SESSION_KEY))) {
			httpSession.removeAttribute(KAPTCHA_SESSION_KEY);
		}
	}
}
