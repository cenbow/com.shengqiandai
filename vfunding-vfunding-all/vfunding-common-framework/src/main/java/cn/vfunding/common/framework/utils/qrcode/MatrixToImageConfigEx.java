package cn.vfunding.common.framework.utils.qrcode;

import java.awt.image.BufferedImage;

public class MatrixToImageConfigEx {
	public static final int BLACK = 0xFF000000;
	public static final int WHITE = 0xFFFFFFFF;

	private final int onColor;
	private final int offColor;

	/**
	 * Creates a default config with on color {@link #BLACK} and off color
	 * {@link #WHITE}, generating normal black-on-white barcodes.
	 */
	public MatrixToImageConfigEx() {
		this(BLACK, WHITE);
	}

	/**
	 * @param onColor
	 *            pixel on color, specified as an ARGB value as an int
	 * @param offColor
	 *            pixel off color, specified as an ARGB value as an int
	 */
	public MatrixToImageConfigEx(int onColor, int offColor) {
		this.onColor = onColor;
		this.offColor = offColor;
	}

	public int getPixelOnColor() {
		return onColor;
	}

	public int getPixelOffColor() {
		return offColor;
	}

	public int getBufferedImageColorModel() {
		// Use faster BINARY if colors match default
	    //return onColor == BLACK && offColor == WHITE ? BufferedImage.TYPE_BYTE_BINARY : BufferedImage.TYPE_INT_RGB;
		return BufferedImage.TYPE_INT_RGB;
	}
}
