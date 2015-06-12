<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>提现待审列表</title>
<jsp:include page="${pageContext.request.contextPath }/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/funds/waitAppltCashList.js"></script>
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
<c:if test="${canUpdate==true }">
	<script type="text/javascript">
		$.canUpdate = true;
	</script>
</c:if>
</head>
<body>
<div id="searchTool">
	提现账号:<input type="text" id="bankNum" name="bankNum" style="width: 130px; height: 13px; margin: 5px;"/>
	用户名:<input type="text" id="userName" name="userName" style="width: 110px; height: 13px; margin: 5px;"/>
	状态:<select id="status" name="status" style="width: 120px; height: 26px; margin: 0;">
			<option value="">全部</option>
			<option value="0" selected="selected">待审核</option>
			<option value="1">审核成功</option>
			<option value="2">审核失败</option>
			<option value="3">用户取消申请</option>
			<option value="4">新浪待打款</option>
			<option value="5">处理中</option>
		</select>
	用户类型:<select id="type" name="type" style="width: 120px; height: 26px; margin: 0;">
			<option value="" selected="selected">全部</option>
			<option value="1">投资用户</option>
			<option value="2">借款用户</option>
		</select>
	时间:<input type="text" name="startTime" id="startTime" onClick="WdatePicker()" size="10" style="margin: 0;width:100px;height: 13px;cursor:pointer;"/>
-<input type="text" name="endTime" id="endTime" onClick="WdatePicker()" size="10" style="margin: 0;width:100px;height: 13px;cursor:pointer;"/>

	<a href="javascript:queryDetail();"
		class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="searchButton">查询</a>
	<a class="easyui-linkbutton" id="export"
		style="cursor: pointer" data-options="iconCls:'icon-save'">导出</a>
</div>
	<table id="list"></table>
	<script>
	$('#export').click(function() {
		window.location.href="/system/export/cashExcel?status="+
				$('#status').val()+'&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+
				'&keyWord='+$('#bankNum').val()+'&username='+$('#userName').val()+'&type='+$('#type').val();
	});
	
	</script>
</body>
</html>