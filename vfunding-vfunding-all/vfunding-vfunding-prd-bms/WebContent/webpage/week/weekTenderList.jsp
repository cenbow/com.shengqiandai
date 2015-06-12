<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>投资列表</title>
</head>
<body>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/week/weekTenderList.js"></script>
<style>
.datagrid-row-over, .datagrid-header td.datagrid-header-over {
	background: #eaf2ff;
	color: #000000;
	cursor: default;
}
.datagrid-row-selected {
	background: #eaf2ff;
	color: #000000;
}
</style>
<div id="list"></div>
<div id="searchTool" style="display: none;">
<input type="hidden" id="weekId" name="weekId" value="${weekId}" />
<input type="hidden" id="repaymentTime" name="repaymentTime" value="${repaymentTime}" />
</div>
</body>
</html>