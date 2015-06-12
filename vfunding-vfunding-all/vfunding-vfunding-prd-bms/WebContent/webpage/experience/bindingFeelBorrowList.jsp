<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>体验卡绑定列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/experience/bindingFeelBorrowList.js"></script>
<c:if test="${canEdit==true }">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
</head>
<body>
	<div id="searchTool">
		<div style="float: left;">
			<c:if test="${canEdit==true }">
				<a href="javascript:bindingFeel();" class="easyui-linkbutton"
					iconCls="icon-add" plain="true">绑定体验卡</a>
			</c:if>
			<c:if test="${canEdit==false}">
				<a href="javascript:void(0)();" class="easyui-linkbutton"
					iconCls="icon-add" plain="true">没权限</a>
			</c:if>
		</div>
		卡号：<input type="text" id="startNo" name="startNo" style="width:100px;"/>
		-<input type="text" id="endNo" name="endNo" style="width:100px;"/>
		状态:<select id="status" name="status" style="width:100px;">
			<option value="" selected="selected">全部</option>
			<option value="1">理财师用户</option>
			<option value="2">虚拟用户</option>
		</select>
		<input type="text" id="username" name="username" style="width:100px;"/>
		绑定状态：<select id="bindingStatus" name="bindingStatus" style="width:100px;">
			<option value="" selected="selected">全部</option>
			<option value="1">未绑定</option>
			<option value="2">已绑定</option>
		</select>
		 激活状态：<select id="useStatus" name="useStatus" style="width:100px;">
			<option value="" selected="selected">全部</option>
			<option value="1">未激活</option>
			<option value="2">已激活</option>
		</select>
		<br/>
		激活时间：<input type="text" name="startTime" readonly="readonly" id="startTime" onClick="WdatePicker()" size="10" style="width:100px;cursor:pointer;background-color:#fff;"/>
-<input type="text" name="endTime" readonly="readonly" id="endTime" onClick="WdatePicker()" size="10" style="width:100px;cursor:pointer;background-color:#fff;"/>
		<a href="javascript:queryDetail();" class="easyui-linkbutton"
			iconCls="icon-search" id="searchButton">查询</a>
	</div>
	<table id="list"></table>
</body>
</html>