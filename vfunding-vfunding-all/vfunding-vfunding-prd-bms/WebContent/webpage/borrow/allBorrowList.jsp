<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>贷款管理列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/borrow/allBorrowList.js"></script>
</head>
<body>
<div id="searchTool">
	借款类型:<select id="status" name="status" style="width:150px;">
			<option value="0" selected="selected">全部</option>
			<option value="1">正在招标的借款</option>
			<option value="2">初审未通过的借款</option>
			<option value="3">满标复审的标</option>
			<option value="4">流标</option>
			<option value="5">撤标</option>
		</select>
	初审时间:<input type="text" name="startTime" id="startTime" readonly="readonly" onClick="WdatePicker()" size="10" style="width:100px;cursor:pointer;"/>
-<input type="text" name="endTime" id="endTime" readonly="readonly" onClick="WdatePicker()" size="10" style="width:100px;cursor:pointer;"/>
	<a href="javascript:queryDetail();"
		class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="searchButton">查询</a>
	<a class="easyui-linkbutton" id="export"
		style="cursor: pointer" data-options="iconCls:'icon-save'">导出</a>
</div>
	<table id="list"></table>
	
	<script>
	$('#export').click(function() {
		window.location.href="/system/export/systemBorrowExcel?status="+
				$('#status').val()+'&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val();
	});
	
	</script>
</body>
</html>