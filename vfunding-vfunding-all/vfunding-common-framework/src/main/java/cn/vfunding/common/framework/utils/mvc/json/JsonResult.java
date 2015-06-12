package cn.vfunding.common.framework.utils.mvc.json;

import cn.vfunding.common.framework.easyui.page.utils.Json;

/**
 * 请求结果封装类
 * @author liuyijun
 *
 */
public class JsonResult extends Json {
	
	boolean showMsg=false;

	/**
	 * 是否需要重定向请求
	 */
	boolean neesRedirect=false;
	/**
	 * 服务器返回信息后需要重定向请求的url路径地址
	 */
	private String redirectUrl;

	

	public boolean isNeesRedirect() {
		return neesRedirect;
	}

	public void setNeesRedirect(boolean neesRedirect) {
		this.neesRedirect = neesRedirect;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public boolean isShowMsg() {
		return showMsg;
	}

	public void setShowMsg(boolean showMsg) {
		this.showMsg = showMsg;
	}
	
	
	
}
