<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>撤标列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/borrow/failBorrowList.js"></script>
<c:if test="${canEdit==true }">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
</head>
<body>
	<div id="searchTool">
		用户名<input type="text" id="userName" name="userName" />
		状态:<select id="status" name="status">
			<option value="1" selected="selected">待撤标</option>
			<option value="5">撤标成功</option>
		</select> <a href="javascript:queryDetail();" class="easyui-linkbutton"
			iconCls="icon-search" id="searchButton">查询</a>
	</div>
	<table id="list_"></table>
</body>
</html>