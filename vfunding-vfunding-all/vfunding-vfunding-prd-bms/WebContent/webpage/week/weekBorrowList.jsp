<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>标的列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/week/weekBorrowList.js"></script>
<c:if test="${canEdit==true }">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${canDelete==true }">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>

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
</head>
<body class="easyui-layout" data-options="fit : true,border : false">
	<input type="hidden" id="weekId" name="weekId" value="${weekId}" /> 
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<div id="list"></div>
	</div>
	<div id="searchTool" style="display: none;">
		<div>
			<c:if test="${canEdit==true }">
				<a href="javascript:addWeekBorrow();" class="easyui-linkbutton"
					style="margin: 5px;" iconCls="icon-add" plain="true">添加标的</a>
			</c:if>
			<c:if test="${canEdit==false }">
				<a href="javascript:void(0);" class="easyui-linkbutton"
					style="margin: 5px;" iconCls="icon-add" plain="true">没权限添加</a>
			</c:if>
			标的编号:<input type="text" id="id" name="id" style="width: 120px; height: 12px; margin: 0;"/> 
			计划状态:		
			<select id="status" name="status" style="width: 120px; height: 28px;">
				<option value="">全部</option>
				<option value="0">未发布</option>
				<option value="1">提交审核</option>
				<option value="2">审核失败</option>
				<option value="3">审核成功</option>
			</select> 
			<a href="javascript:queryDetail();" class="easyui-linkbutton" iconCls="icon-search" id="searchButton">查询</a>
			<a href="/system/week/weekListPage" style="margin-left:100px;">》返回计划管理页面《</a>
		</div>
		<div style="margin:5px;font-size:14px;font-weight:bold;color:blue;">短期理财计划名称:${weekName}</div>
	</div>	
</body>
</html>