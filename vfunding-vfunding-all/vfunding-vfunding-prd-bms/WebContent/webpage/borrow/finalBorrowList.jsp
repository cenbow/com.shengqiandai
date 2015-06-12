<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>复审列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/borrow/finalBorrowList.js"></script>
<c:if test="${canEdit==true }">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
</head>
<body>
	<div id="searchTool">
		用户名<input type="text" id="userName" name="userName" /> 类型<select
			id="biaoType" name="biaoType">
			<option value="">全部</option>
			<option value="fast">抵押标</option>
			<option value="xin">信用标</option>
			<option value="jin">净值标</option>
			<option value="lz">流转标</option>
			<option value="miao">秒还标</option>
			<option value="huodong">活动标</option>
		</select> 状态:<select id="status" name="status">
			<option value="">全部</option>
			<option value="1" selected="selected">待复审</option>
			<option value="3">复审成功</option>
			<option value="4">复审失败</option>
		</select> <a href="javascript:queryDetail();" class="easyui-linkbutton"
			iconCls="icon-search" id="searchButton">查询</a>
	</div>
	<table id="list"></table>
</body>
</html>