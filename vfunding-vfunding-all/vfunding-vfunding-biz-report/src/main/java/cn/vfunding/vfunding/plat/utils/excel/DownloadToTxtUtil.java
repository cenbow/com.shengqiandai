package cn.vfunding.vfunding.plat.utils.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 下载Txt公用类
 * 
 */
public class DownloadToTxtUtil {
	// LOG定义
	private static final Log LOG = LogFactory.getLog(DownloadToTxtUtil.class);
	// 下载文件编码


	/**
	 * 功能：下載TXT文檔 可指定下載編碼方式
	 * 
	 * @param data
	 *            下載字串內容
	 * @param response
	 *            響應對象
	 * @param fileName
	 *            文件名稱
	 * @param downloadEncoding
	 *            下載編碼
	 */
	public static void writeTxtToResponse(String data,
			HttpServletResponse response, String fileName,
			String downloadEncoding) {
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			response.reset();
			response.setCharacterEncoding(downloadEncoding);
			response.setContentType("application/vnd.ms-excel; charset="+downloadEncoding);
			String filename2 = "chinadim-" + fileName + ".xls";

			// response.setHeader(......)这个设置是下载文件的名称为中文,不乱码
			response.setHeader("Content-disposition", "attachment; filename="
					+ filename2);
			out.write(data.getBytes());
			
		} catch (Exception e) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(baos);
			e.printStackTrace(ps);
			LOG.error("response writer error!");
			LOG.error(new String(baos.toByteArray()));
			// close stream
			try {
				baos.close();
				ps.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} finally {
			try {
				if (out != null)
					out.flush();
			} catch (Exception e) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				PrintStream ps = new PrintStream(baos);
				e.printStackTrace(ps);
				LOG.error("response  flush error!");
				LOG.error(new String(baos.toByteArray()));
				// close stream
				try {
					baos.close();
					ps.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

}