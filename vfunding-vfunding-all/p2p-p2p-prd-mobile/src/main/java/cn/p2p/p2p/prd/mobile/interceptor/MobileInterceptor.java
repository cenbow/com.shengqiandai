package cn.p2p.p2p.prd.mobile.interceptor;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.p2p.p2p.prd.mobile.cache.IUseraEhCacheService;
import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.user.model.User;

import com.alibaba.fastjson.JSON;

/**
 * 权限拦截器
 * 
 * @author liuyijun
 * 
 */
public class MobileInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private IUseraEhCacheService userService;

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		InterceptorNote interceptorNote = handlerMethod
				.getMethodAnnotation(InterceptorNote.class);
		if (interceptorNote == null) {
			return true;
		} else {
			 InputStream is = request.getInputStream();
			 byte bytes[] = new byte[request.getContentLength()];
			 is.read(bytes);
			 // ②得到请求中的内容区数据（以CharacterEncoding解码）
			 // 此处得到数据后你可以通过如json-lib转换为其他对象
			 String jsonStr = new String(bytes,
			 request.getCharacterEncoding());
			 Map<String, String> req = JSON.parseObject(jsonStr, Map.class);
			if (EmptyUtil.isNotEmpty(req.get("userId"))
					&& EmptyUtil.isNotEmpty(req.get("password"))) {
				User user = this.userService.selectByUserIdFromCache(Integer
						.parseInt(req.get("userId")));
				if (user.getPassword().endsWith(req.get("password"))) {
					return true;
				} else {
					throw new BusinessException("8008014", "用户密码错误");
				}
			} else {
				return true;
				// throw new BusinessException("8008015", "无此用户");
			}
		}
	}

}
