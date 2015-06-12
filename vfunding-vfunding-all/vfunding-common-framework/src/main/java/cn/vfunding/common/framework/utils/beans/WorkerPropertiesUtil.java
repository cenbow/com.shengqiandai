package cn.vfunding.common.framework.utils.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class WorkerPropertiesUtil {
	private static final Logger logger = Logger
			.getLogger(WorkerPropertiesUtil.class);
	private static Properties prop = null;
	static {

		InputStream in = null;
		try {
			prop = new Properties();
			String path = getPath() + "/conf/worker.properties";
			logger.error(path);
			in = new FileInputStream(new File(path));
			prop.load(in);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public static String getValue(String key) {
		return prop.getProperty(key);
	}

	public static String getFileDirValue() {
		String value = getValue("file.dir");
		return value;
	}

	public static String getPath() {
		String path = new File("").getAbsolutePath();
		return path;
	}
}
