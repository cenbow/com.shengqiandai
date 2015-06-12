package cn.vfunding.common.framework.server.auth;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.ArrayUtils;

import com.atomikos.beans.Property;

import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.server.EmployeeSession;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;

/**
 * 用户权限判断处理共用类
 * 
 * @author liuyijun
 * 
 */
public class UserAuthUtil {

	public static String getAuthConfig() {
		String value = null;
		try {
			Properties props = new Properties();
			InputStream in = new BufferedInputStream(new FileInputStream(
					"conf/worker.properties"));
			props.load(in);
			value = props.getProperty("auth");
		} catch (Exception e) {
			throw new BusinessException("10001");
		}
		return value;
	}

	public static boolean isPass(String url, String... parentUrls) {
		boolean result = false;
		String auth = getAuthConfig();
		if (!EmptyUtil.isEmpty(auth)) {
			if (auth.toUpperCase().equals("B")) {
				try {
					UserAuthUtilOther.isPass(url, parentUrls);
				} catch (Exception e) {
					result = checkAuth(url, parentUrls);
				}
			} else {
				result = checkAuth(url, parentUrls);
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	private static boolean checkAuth(String url, String... parentUrls) {
		boolean result = false;
		EmployeeSession eSession = EmployeeSession.getUserSession();
		if (eSession != null) {
			List<String> roleNames = (List<String>) eSession.getAttributes()
					.get("roleNames");
			for (String roleName : roleNames) {
				if (roleName.equals("管理员")) {
					return true;
				}
			}
			List<String> employeeUrls = (List<String>) eSession.getAttributes()
					.get("resourceUrls");
			if (employeeUrls.contains(url)) {
				return true;
			}

			if (ArrayUtils.isNotEmpty(parentUrls)) {
				for (int i = 0; i < parentUrls.length; i++) {
					if (employeeUrls.contains(parentUrls[i])) {
						return true;
					}
				}
			}

		}

		return result;
	}
}
