<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<!--以下信息为标准的 HTML 格式 + ASP 语言 拼凑而成的 网银在线 支付接口标准演示页面 无需修改-->

<html>

<body onLoad="javascript:document.E_FORM.submit()">
	<form action="https://pay3.chinabank.com.cn/PayGate?encoding=UTF-8" method="POST"
		name="E_FORM">
		<input type="hidden" name="v_md5info" value="${chinaBank.v_md5info}"
			size="100"> <input type="hidden" name="v_mid"
			value="${chinaBank.v_mid}"> <input type="hidden" name="v_oid"
			value="${chinaBank.v_oid}"> <input type="hidden"
			name="v_amount" value="${chinaBank.rechargeMoney}"> <input
			type="hidden" name="v_moneytype" value="${chinaBank.v_moneytype}">
		<input type="hidden" name="v_url" value="${chinaBank.v_url}">
		<input type="hidden" name="remark2" value="${chinaBank.remark2}">
		<input type="hidden" name="pmode_id" value="${chinaBank.pmode_id}">
	</form>
</body>
</html>
