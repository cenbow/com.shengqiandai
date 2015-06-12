<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>解锁列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<c:if test="${canUnlock==true }">
	<script type="text/javascript">
		$.canUnlock = true;
	</script>
</c:if>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/user/lockUserList.js"></script>
</head>
<body>
	<div id="searchTool">
		用户名:<input type="text" id="userName" name="userName" />
		电话:<input type="text" id="phone" name="phone" />
		<a href="javascript:queryDetail();" class="easyui-linkbutton" 
		iconCls="icon-search" id="searchButton">查询</a>
	</div>
	<table id="list"></table>
</body>
</html>