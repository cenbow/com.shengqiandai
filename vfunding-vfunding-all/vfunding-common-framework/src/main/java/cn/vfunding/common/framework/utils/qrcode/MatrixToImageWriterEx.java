package cn.vfunding.common.framework.utils.qrcode;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.google.zxing.common.BitMatrix;

public class MatrixToImageWriterEx {
	private MatrixToImageWriterEx() {}

	public static BufferedImage write2File(BitMatrix matrix, String format, String imageSavePath, MatrixToImageConfigEx config) throws IOException {
		BufferedImage image = toBufferedImage(matrix, config);
		if (!ImageIO.write(image, format, new File(imageSavePath))) {
			throw new IOException((new StringBuilder("Could not write an image of format ")).append(format).append(" to ").append(imageSavePath).toString());
		} else {
			return image;
		}
	}

	/**
	 * 二维码添加自定义logo
	 */
	public static void overlapImage(BufferedImage image, String imgSavePath, String logoPath, String formate) {
		try {
			BufferedImage logo = ImageIO.read(new File(logoPath));
			Graphics2D g = image.createGraphics();
			// logo宽高
			//int width = image.getWidth() / 5;
			//int height = image.getHeight() / 5;
			int width = 35;
			int height = 35;
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
	
	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		return toBufferedImage(matrix, DEFAULT_CONFIG);
	}

	public static BufferedImage toBufferedImage(BitMatrix matrix, MatrixToImageConfigEx config) {
	
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		
		BufferedImage image = new BufferedImage(width, height, config.getBufferedImageColorModel());
		int onColor = config.getPixelOnColor();
		int offColor = config.getPixelOffColor();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? onColor : offColor);
			}
		}
	
		return image;
	}

	public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
		writeToFile(matrix, format, file, DEFAULT_CONFIG);
	}

	public static void writeToFile(BitMatrix matrix, String format, File file, MatrixToImageConfigEx config) throws IOException {
		BufferedImage image = toBufferedImage(matrix, config);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException((new StringBuilder("Could not write an image of format ")).append(format).append(" to ").append(file).toString());
		}
	}

	public static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
		writeToStream(matrix, format, stream, DEFAULT_CONFIG);
	}

	public static void writeToStream(BitMatrix matrix, String format, OutputStream stream, MatrixToImageConfigEx config) throws IOException {
		BufferedImage image = toBufferedImage(matrix, config);
		if (!ImageIO.write(image, format, stream)) {
			throw new IOException((new StringBuilder("Could not write an image of format ")).append(format).toString());
		}
	}

	private static final MatrixToImageConfigEx DEFAULT_CONFIG = new MatrixToImageConfigEx();
}
