<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="${pageContext.request.contextPath }/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/system/bmsLogList.js"></script>
<c:if test="${canDelete==true }">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
</head>
<body class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<div id="loglists"></div>
	</div>
	<div id="searchTool">
		用户名：<input type="text" id="username" name="username" width="150"/>
		状态：
			<select id="success" name="success" style="width:100px;">
				<option value="">全部</option>
				<option value="Y">成功</option>
				<option value="N">失败</option>
			</select>
		时间：<input type="text" name="startTime" readonly="readonly" id="startTime" onClick="WdatePicker()" size="10" style="width:100px;cursor:pointer;background-color:#fff;"/>
-<input type="text" name="endTime" readonly="readonly" id="endTime" onClick="WdatePicker()" size="10" style="width:100px;cursor:pointer;background-color:#fff;"/>
		
	<a href="javascript:queryDetail();" class="easyui-linkbutton" iconCls="icon-search" id="searchButton">查询</a>
		<c:if test="${canDelete==true }">
			<a href="javascript:deleteByIds();" class="easyui-linkbutton" iconCls="icon-no" id="searchButton">删除</a>
		</c:if>
		
	</div>
</body>
</html>