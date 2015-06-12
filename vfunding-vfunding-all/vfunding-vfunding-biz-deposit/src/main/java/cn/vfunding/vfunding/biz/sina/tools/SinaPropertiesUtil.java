package cn.vfunding.vfunding.biz.sina.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
/**
 * 读取sina属性文件的工具类
 * @author louchen 2015-01-23
 *
 */
public class SinaPropertiesUtil {
	private static Properties prop = null;
	static {
		InputStream in = null;
		try {
			prop = new Properties();
			String path = getPath() + "/conf/sina.properties";
			in = new FileInputStream(new File(path));
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
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
//public class SinaPropertiesUtil {
//	private Properties prop = null;
//	private static SinaPropertiesUtil instance = null;
//	private SinaPropertiesUtil() {
//		InputStream in = null;
//		try {
//			prop = new Properties();
//			in = this.getClass().getClassLoader().getResourceAsStream("cn/vfunding/vfunding/sinaconfig/sina.properties");
//			prop.load(in);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			IOUtils.closeQuietly(in);
//		}
//	}
//	private static synchronized void syncInit() {
//		if (instance == null) {
//			instance = new SinaPropertiesUtil();
//		}
//	}
//	public static SinaPropertiesUtil getInstance() {
//		if (instance == null) {
//			syncInit();
//		}
//		return instance;
//	}
//	
//	public String getValue(String key) {
//		return prop.getProperty(key);
//	}
//}