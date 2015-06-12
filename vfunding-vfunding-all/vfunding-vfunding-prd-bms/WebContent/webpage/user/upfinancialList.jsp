<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>理财师列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/user/upfinancialList.js"></script>
<c:if test="${canApply==true }">
	<script type="text/javascript">
		$.canApply = true;
	</script>
</c:if>
</head>
<body>
	<div id="searchTool">
		登录名:<input type="text" id="username" name="username" style="width: 120px; height: 13px; margin: 5px;"/>
		真实名:<input type="text" id="realname" name="realname" style="width: 120px; height: 13px; margin: 0;"/>
		电话:<input type="text" id="phone" name="phone" style="width: 120px; height: 13px; margin: 0;"/>
		状态:<select id="status" name="status" style="width: 120px; height: 26px; margin: 0;">
			<option value="">全部</option>
			<option value="0" selected="selected">待审核</option>
			<option value="1">通过</option>
			<option value="2">未通过</option>
		</select>
		审核时间:<input type="text" name="startTime" readonly="readonly" id="startTime" onClick="WdatePicker()" size="10" style="margin: 0;width:100px;height: 13px;cursor:pointer;"/>
-<input type="text" name="endTime" readonly="readonly" id="endTime" onClick="WdatePicker()" size="10" style="margin: 0;width:100px;height: 13px;cursor:pointer;"/>
		<a href="javascript:queryDetail();" class="easyui-linkbutton" iconCls="icon-search" id="searchButton">查询</a>
	</div>
	<table id="list"></table>
</body>
</html>