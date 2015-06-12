<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>抵押物信息</title>
<jsp:include page="${pageContext.request.contextPath }/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/collateral/collateral.js"></script>
</head>


<body class="easyui-layout" data-options="fit : true,border : false">
	
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<div id="collateral" rownumbers="true"></div>
	</div>
	
</body>
</html>