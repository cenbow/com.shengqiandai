<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>活期宝一期</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/current/currentList.js"></script>
<c:if test="${canEdit==true }">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
</head>
<body class="easyui-layout" data-options="fit : true,border : false">
	<!-- 活期宝一期  参数 -->
	<input type="hidden" name="currentId" id="currentId" value="1"/>
	<div id="searchTool">
		<div>
			<div style="height: 80px;">
				<a href="javascript:editCurrent();" class="easyui-linkbutton"
					iconCls="icon-add" plain="true">修改活期池</a>
					
				<a href="javascript:addCurrentUpdate();" class="easyui-linkbutton"
					iconCls="icon-add" plain="true">增资活期池</a>
					
				<select name="status" id="status" style="width:150px;">
					<option value="1">招标中的标</option>
					<option value="3">即将生效的标</option>
					<option value="2">完成的标</option>
					<option value="0">全部标的</option>
				</select>
				<a href="javascript:queryDetail();" class="easyui-linkbutton"
					iconCls="icon-search" id="searchButton">查询</a>
					<br> <span id="addSearchToolContent" style="background-color: red;"></span>
					<br/>
				<span id="msg" style="height:30px;"></span>
			</div>
		</div>
	</div>
	
	<div id="list_"></div>
	
</body>
</html>