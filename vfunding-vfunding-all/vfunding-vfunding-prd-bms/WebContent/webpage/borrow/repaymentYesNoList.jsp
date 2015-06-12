<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>还款列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/borrow/repaymentYesNoList.js"></script>
</head>
<body>
	<div id="searchTool">
		借款标题：<input type="text" id="name" name="name" style="width:150px;"/>
		借款人：<input type="text" id="username" name="username" style="width:150px;"/>
		状态：<input type="hidden" id="status" name="status" value="${status}"/>
		还款时间:<input type="text" name="startTime" readonly="readonly" id="startTime" onClick="WdatePicker()" size="10" style="width:100px;cursor:pointer;"/>
-<input type="text" name="endTime" readonly="readonly" id="endTime" onClick="WdatePicker()" size="10" style="width:100px;cursor:pointer;"/>
		<a href="javascript:queryDetail();" class="easyui-linkbutton"
			id="searchButton" data-options="iconCls:'icon-search'">查询</a>
		<a class="easyui-linkbutton" id="export"
		style="cursor: pointer" data-options="iconCls:'icon-save'">导出</a>
	</div>
	<table id="list"></table>
	
	<script>
	$('#export').click(function() {
		window.location.href="/system/export/exportBorrow?status="+
				$('#status').val()+'&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&keyWord='+$('#name').val()+'&username='+$('#username').val();
	});
	</script>
</body>
</html>