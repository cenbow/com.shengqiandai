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
		src="${pageContext.request.contextPath}/js/distribution/withdrawals.js"></script>
		<div id="withdrawalsList"></div>
</body>
</html>