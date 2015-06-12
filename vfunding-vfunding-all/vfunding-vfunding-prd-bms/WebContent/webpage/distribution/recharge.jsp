<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="${pageContext.request.contextPath }/inc.jsp"></jsp:include>
</head>
<body class="easyui-layout" data-options="fit : true,border : false">



	<input type="hidden" value="${userId}" id="userId" name="userId" />
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/distribution/recharge.js"></script>
<!-- 		累计充值记录：<input type="text" value="" id="ljcz" style="width: 120px; height: 12px; margin: 0;" /> -->
		<div id="rechargeList"></div>
</body>
</html>