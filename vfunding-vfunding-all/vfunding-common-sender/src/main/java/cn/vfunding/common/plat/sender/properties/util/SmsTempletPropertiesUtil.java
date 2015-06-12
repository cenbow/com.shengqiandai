package cn.vfunding.common.plat.sender.properties.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class SmsTempletPropertiesUtil {
	private static final Logger logger = Logger
			.getLogger(SmsTempletPropertiesUtil.class);
	private static Properties prop = null;
	static {

		InputStreamReader in = null;
		try {
			prop = new Properties();
			String path = getPath() + "/conf/smsTemplet.properties";
			logger.info(path);
			in = new FileReader(new File(path));
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


	public static String getPath() {
		String path = new File("").getAbsolutePath();
		return path;
	}
}
