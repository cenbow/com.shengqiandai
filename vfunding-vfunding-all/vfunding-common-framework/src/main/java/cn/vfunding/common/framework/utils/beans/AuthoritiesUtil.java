package cn.vfunding.common.framework.utils.beans;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.vfunding.common.framework.server.auth.UserAuthUtil;

public class AuthoritiesUtil {

	/**
	 * 根据传入的URL判断权限，并把判断好的boolean值放到request中
	 * 
	 * @author LiuJun
	 * @param request
	 * @param urls
	 *            <ul>
	 *            <li>第一个为添加数据的URL，request的key为"canToInsert"</li>
	 *            <li>第二个为修改数据的URL，request的key为 "canToUpdate"</li>
	 *            <li>第三个为查看详细数据的URL，request的key为"canToSelect"</li>
	 *            <li>第四个为删除数据的URL，request的key为"canToDelete"</li>
	 *            <li>其余的URLkey为"canTo"+URL在数据组中的下标</li>
	 *            </ul>
	 */
	public static void isPass(HttpServletRequest request, String... urls) {
		if (EmptyUtil.isNotEmpty(urls)) {
			if (EmptyUtil.isNotEmpty(request)) {
				for (int i = 0; i < urls.length; i++) {

					switch (i) {
					case 0:
						request.setAttribute("canToInsert",
								UserAuthUtil.isPass(urls[i]));
						break;
					case 1:
						request.setAttribute("canToUpdate",
								UserAuthUtil.isPass(urls[i]));
						break;
					case 2:
						request.setAttribute("canToSelect",
								UserAuthUtil.isPass(urls[i]));
						break;
					case 3:
						request.setAttribute("canToDelete",
								UserAuthUtil.isPass(urls[i]));

						break;

					default:
						request.setAttribute("canTo" + i,
								UserAuthUtil.isPass(urls[i]));
						break;
					}
				}
			}
		}
	}

	/**
	 * 权限判断URL
	 * 
	 * @author LiuJun
	 * @param request
	 * @param urls
	 */
	public static void isPass(HttpServletRequest request,
			Map<String, String> maps) {

	}
}
