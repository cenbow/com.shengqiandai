package cn.vfunding.vfunding.prd.bms.utils;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import cn.vfunding.common.framework.server.EmployeeSession;

/**
 * 权限拦截工具类
 * 
 * @author liuyijun
 * 
 */
public class UserAuthFilter {

	public static boolean isPass(String url, String... parentUrls) {
		boolean result = false;

		result = checkAuth(url, parentUrls);

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
