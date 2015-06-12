package cn.vfunding.vfunding.plat.cron;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.servlet.ServletUtilities;

public class ServletUtilitiesOverwrite extends ServletUtilities {
	public static String saveChartAsJPEG(JFreeChart chart, int width,
			int height, HttpSession session) throws IOException {
		if (chart == null) {
			throw new IllegalArgumentException("Null 'chart' argument.");
		}
		ServletUtilities.createTempDir();
		String prefix = ServletUtilities.getTempFilePrefix();
		if (session == null) {
			prefix = ServletUtilities.getTempOneTimeFilePrefix();
		}
		File dir = new File("C:\\图片要保存的路径");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File tempFile = File.createTempFile(prefix, ".jpg", dir);
		ChartUtilities.saveChartAsPNG(tempFile, chart, width, height);
		if (session != null) {
			ServletUtilities.registerChartForDeletion(tempFile, session);
		}
		return tempFile.getName();
	}
}
