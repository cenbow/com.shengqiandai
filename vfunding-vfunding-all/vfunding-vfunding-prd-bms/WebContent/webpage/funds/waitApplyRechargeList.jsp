<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>充值待审列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/funds/waitApplyRechargeList.js"></script>
<style>
.datagrid-row-over,
.datagrid-header td.datagrid-header-over {
  background: #eaf2ff;
  color: #000000;
  cursor: default;
}
.datagrid-row-selected {
  background: #eaf2ff;
  color: #000000;
}
</style>
<c:if test="${canEdit==true }">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${canAdd==true }">
	<script type="text/javascript">
		$.canAdd = true;
	</script>
</c:if>
</head>
<body>
<div id="searchTool">
	<div style="float: left;">
		<c:if test="${canAdd==true }">
		<a href="javascript:addRechargeOffline();" class="easyui-linkbutton"
			iconCls="icon-add" plain="true">添加线下充值</a>
		</c:if>
	</div>
	&nbsp;&nbsp;&nbsp;&nbsp;
	用户名:<input type="text" id="userName" name="userName"/>
	流水号:<input type="text" id="tradeNo" name="tradeNo"/>
	<!-- 类型:<select id="type" name="type">
			<option value="">全部</option>
			<option value="1">在线充值</option>
			<option value="2">线下充值</option>
		</select>
	状态:<select id="status" name="status">
			<option value="">全部</option>
			<option value="1">充值成功</option>
			<option value="0">充值失败</option>
			<option value="2" selected="selected">充值待审</option>
		</select> -->
	<a href="javascript:queryDetail();"
		class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="searchButton">查询</a>
	<!-- <a href="javascript:alert('wait...')" class="easyui-linkbutton" style="cursor: pointer" data-options="iconCls:'icon-save'">导出</a> -->
</div>


	
	<table id="list"></table>
</body>
</html>