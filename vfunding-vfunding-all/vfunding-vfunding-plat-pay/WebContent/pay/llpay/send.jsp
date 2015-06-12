<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<html>

<body onLoad="javascript:document.E_FORM.submit()">
	<form action="https://yintong.com.cn/llpayh5/authpay.htm" method="post"
		name="E_FORM">
		<ul>
			<li><input type="hidden" name="req_data" value='${req_data }' /></li>
		</ul>
	</form>
</body>
</html>
