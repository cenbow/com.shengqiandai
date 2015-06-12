<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>导出列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
</head>
<body style="margin:0 auto;">
<div style="height:30px;"></div>
<form id="exportForm" name="exportForm">
<div>
提现状态:<select id="status" name="status" style="width:150px;">
			<option value="" selected="selected">全部</option>
			<option value="0">待审核</option>
			<option value="1">审核成功</option>
			<option value="2">审核失败</option>
			<option value="3">用户取消申请</option>
		</select>
<br/>
时间：<input type="text" name="startTime" id="startTime" readonly="readonly" onClick="WdatePicker()" size="10" style="width:100px;"/>
-<input type="text" name="endTime" id="endTime" readonly="readonly" onClick="WdatePicker()" size="10" style="width:100px;"/>
<br/>
<input type="button" value="导出" name="sub" id="subfrom">
</div>
</form>
<script type="text/javascript">
$(function(){
	$('#subfrom').click(function(){
		window.location.href="/system/export/cashExcel?status="+
				$('#status').val()+'&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val();
	});
})
</script>
</body>
</html>