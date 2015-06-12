<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>额度审批</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/borrow/userAmountList.js"></script>
<c:if test="${canEdit==true }">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
</head>
<body>
	<div id="searchTool">
		用户名<input type="text" id="userName" name="userName" /> 状态:<select
			id="status" name="status">
			<option value="">全部</option>
			<option value="2" selected="selected">待审</option>
			<option value="1">审核成功</option>
			<option value="0">审核失败</option>
		</select> <a class="easyui-linkbutton" id="export" style="cursor: pointer"
			data-options="iconCls:'icon-save'">导出</a> <a
			href="javascript:queryDetail();" class="easyui-linkbutton"
			iconCls="icon-search" id="searchButton">查询</a>

	</div>
	<table id="list"></table>
</body>
<script type="text/javascript">
	$(function() {
		$('#export').click(function() {
			window.location.href = "/system/amount/excelUserAmount";
		});
	});
</script>
</html>