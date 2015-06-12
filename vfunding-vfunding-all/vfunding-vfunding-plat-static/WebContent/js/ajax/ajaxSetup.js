/**
 * ajax请求全局设置
 * 主要功能是处理session失效问题
 */
$.ajaxSetup({
	// contentType:"application/x-www-form-urlencoded;charset=utf-8",
	complete : function(XMLHttpRequest, textStatus) {
		// 通过XMLHttpRequest取得响应头，sessionstatus，
		var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
		var returnUrl = XMLHttpRequest.getResponseHeader("returnUrl");
		var address = "/goLogin";
		if (sessionstatus == "sessionOut") {
			if (returnUrl) {
				address += "?returnUrl=" + returnUrl;
			}
			window.location.replace(address);
		}
	}
});