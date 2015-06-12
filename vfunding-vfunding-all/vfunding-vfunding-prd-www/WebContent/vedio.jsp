<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="微积金(www.vfunding.cn) 互联网金融服务平台旨在为有资金需求和理财需求的个人或企业搭建一个真实诚信、安全透明、快捷高效的互联网服务平台和投资理财渠道。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、担保贷款等。" />

<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|工薪贷|生意贷|基金|微基金|小额贷款|投融资|宜车贷|二手车抵押|押车|担保" />

<title>微积金-Flash</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/vedio.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/swfobject.js"></script>
</head>
<body>

	<!---头部--->
	<jsp:include page="top.jsp"></jsp:include>

	<!--中间-->
	<div class="content">
		<script>
			swfobject.embedSWF("/2.swf", "swf", "1000", "600", "9.0.0",
					"expressInstal   l.swf", {
						rich_Ver : 2
					});
		</script>

		<div id="swf"></div>

	</div>


	<!------尾部------>
	<jsp:include page="footer.jsp"></jsp:include>


</body>
</html>
