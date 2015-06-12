package cn.p2p.common.plat.file.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class WorkerPropertiesUtil extends
		cn.vfunding.common.framework.utils.beans.WorkerPropertiesUtil {
	private static final Logger logger = Logger
			.getLogger(WorkerPropertiesUtil.class);
	private static Properties prop = null;
	static {
		prop = new Properties();
		InputStream in = null;
		try {
			String path = FileUtils.getUserDirectoryPath();
			Object.class.getResourceAsStream(path + "/conf/worker.properties");
			prop.load(in);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public static String getFilePath() {
		return prop.getProperty("file.dir");
	}
}
