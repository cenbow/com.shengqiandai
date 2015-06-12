package cn.vfunding.common.framework.utils.mvc;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.exception.ResourceNotFoundException;
import cn.vfunding.common.framework.exception.UnauthorizedException;

/**
 * springmvc 异常控制，主要是对自定义异常做特殊处理
 * 
 * @author liuyijun
 * 
 */
public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler
	public void exp(HttpServletRequest request, Exception ex,
			HttpServletResponse response) throws Exception {
		if (ex instanceof BusinessException) {
			BusinessException exception = (BusinessException) ex;
			logger.error(exception.getMessage(), exception);
			response.setStatus(600);
			response.setHeader("err-code", exception.getErrCode());
			response.setCharacterEncoding("UTF-8");
			response.setHeader("err-msg",
					URLEncoder.encode(exception.getErrMsg(), "UTF-8"));
			response.setContentType("text/plain");

			response.getWriter().write(exception.getMessage());

		} else if (ex instanceof ResourceNotFoundException) {
			ResourceNotFoundException exception = (ResourceNotFoundException) ex;
			// TODO 请完成

		} else if (ex instanceof UnauthorizedException) {
			UnauthorizedException exception = (UnauthorizedException) ex;
			// TODO 请完成

		} else {
			// TODO 请完成
			ex.printStackTrace();
		}
	}

	/*@InitBinder
	public void initBinder(WebDataBinder binder) {

		/**
		 * 自动转换日期类型的字段格式
		 */
		/*binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm"), true));*/

		/**
		 * 防止XSS攻击
		 */
		/*binder.registerCustomEditor(String.class, new StringEscapeEditor(true,
				false));*/
	/*}*/

}
