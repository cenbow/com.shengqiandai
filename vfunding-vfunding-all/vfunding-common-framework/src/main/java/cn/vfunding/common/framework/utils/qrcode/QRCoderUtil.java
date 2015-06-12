package cn.vfunding.common.framework.utils.qrcode;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

/**
 * 二维码创建工具类
 * 
 * @author Administrator
 * 
 */
public class QRCoderUtil {

	// 红：0xFFE30022,粉：0xFFF4C2C2 ,黑：0xff000000

	private static int BLACK = 0xff000000;
	private static int WHITE = 0xFFFFFFFF;

	private static final String imgPath = "D:\\newQRCoder.png";

	// public static String logoPath = "/dooctImg/qrcoderLogo/qrcoderLogo.jpg";
	private static MatrixToImageConfigEx DEFAULT_CONFIG = new MatrixToImageConfigEx(
			BLACK, WHITE);

	/**
	 * 创建带logo的二维码，并保存在硬盘中
	 * @param content
	 * @param logoPath
	 * @return
	 * 2014年9月22日
	 * liuyijun
	 */
	public static boolean encode(String content, String logoPath) {
		boolean result = false;
		try {

			BitMatrix byteMatrix;
			byteMatrix = new MultiFormatWriter().encode(
					new String(content.getBytes("UTF-8"), "iso-8859-1"),
					BarcodeFormat.QR_CODE, 250, 250);
			BufferedImage img = toBufferedImage(byteMatrix, DEFAULT_CONFIG);
			// 添加logo图片
			overlapImage(img, imgPath, logoPath, "png");
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static ByteArrayOutputStream encodeOut(String content,
			String logoPath) {
		ByteArrayOutputStream out = null;
		try {
			BitMatrix byteMatrix;
			byteMatrix = new MultiFormatWriter().encode(
					new String(content.getBytes("UTF-8"), "iso-8859-1"),
					BarcodeFormat.QR_CODE, 250, 250);
			BufferedImage img = toBufferedImage(byteMatrix, DEFAULT_CONFIG);
			// 添加logo图片
			out = overlapImageOut(img, logoPath, "png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}
	
	public static ByteArrayOutputStream encodeOut(String content,
			File logoFile) {
		ByteArrayOutputStream out = null;
		try {
			BitMatrix byteMatrix;
			byteMatrix = new MultiFormatWriter().encode(
					new String(content.getBytes("UTF-8"), "iso-8859-1"),
					BarcodeFormat.QR_CODE, 250, 250);
			BufferedImage img = toBufferedImage(byteMatrix, DEFAULT_CONFIG);
			// 添加logo图片
			out = overlapImageOut(img, logoFile, "png");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}


	public static MultipartEntityBuilder createQRCoderMultipartEntity(String content,
			String logoPath) throws Exception {
		MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
		ByteArrayOutputStream out = encodeOut(content, logoPath);
		byte[] b = out.toByteArray();
		multipartEntity.addPart("files", new ByteArrayBody(b,
				"newQRCoder.png"));
		return multipartEntity;
	}
	
	public static MultipartEntityBuilder createQRCoderMultipartEntity(String content,
			File logoFile) throws Exception {
		MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
		ByteArrayOutputStream out = encodeOut(content, logoFile);
		byte[] b = out.toByteArray();
		multipartEntity.addPart("files", new ByteArrayBody(b,
				"newQRCoder.png"));

		return multipartEntity;
	}

	public static BufferedImage toBufferedImage(BitMatrix matrix,
			MatrixToImageConfigEx config) {

		int width = matrix.getWidth();
		int height = matrix.getHeight();

		BufferedImage image = new BufferedImage(width, height,
				config.getBufferedImageColorModel());
		int onColor = config.getPixelOnColor();
		int offColor = config.getPixelOffColor();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? onColor : offColor);
			}

		}

		return image;
	}

	/**
	 * 二维码添加自定义logo
	 */
	public static ByteArrayOutputStream overlapImageOut(BufferedImage image,
			String logoPath, String formate) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {

			BufferedImage logo = ImageIO.read(new File(logoPath));
			Graphics2D g = image.createGraphics(); // logo宽高
			int width = 40;
			int height = 40; // logo起始位置，此目的是为logo居中显示
			int x = (image.getWidth() - width) / 2;
			int y = (image.getHeight() - height) / 2;
			g.drawImage(logo, x, y, width, height, null);
			g.dispose();
			// ImageIO.write(image, formate, new File(imgSavePath));

			ImageIO.write(image, formate, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}
	
	

	/**
	 * 二维码添加自定义logo
	 */
	public static void overlapImage(BufferedImage image, String imgSavePath,
			String logoPath, String formate) {
		try {
			BufferedImage logo = ImageIO.read(new File(logoPath));
			Graphics2D g = image.createGraphics();
			// logo宽高
			int width = 40;
			int height = 40;
			// logo起始位置，此目的是为logo居中显示
			int x = (image.getWidth() - width) / 2;
			int y = (image.getHeight() - height) / 2;
			g.drawImage(logo, x, y, width, height, null);
			g.dispose();
			ImageIO.write(image, formate, new File(imgSavePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 二维码添加自定义logo
	 */
	public static ByteArrayOutputStream overlapImageOut(BufferedImage image,
			File logoFile, String formate) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			BufferedImage logo = ImageIO.read(logoFile);
			Graphics2D g = image.createGraphics(); // logo宽高
			int width = 40;
			int height = 40; // logo起始位置，此目的是为logo居中显示
			int x = (image.getWidth() - width) / 2;
			int y = (image.getHeight() - height) / 2;
			g.drawImage(logo, x, y, width, height, null);
			g.dispose();
			// ImageIO.write(image, formate, new File(imgSavePath));

			ImageIO.write(image, formate, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}
	
	
	public static void main(String[] args) {
		String content="http://www.p2p.cn/download/mobile/n欢迎注册微积金/n精彩生活由此开启/n快乐投资每一天";
		if(encode(content, "D://qrcoderLogo.jpg"));
	}

}
