<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="${pageContext.request.contextPath }/inc.jsp"></jsp:include>
<title>客服管理列表</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/distribution/userEmp.js"></script>
<c:if test="${canEdit==true }">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${canRecharge==true }">
	<script type="text/javascript">
		$.canRecharge = true;
	</script>
</c:if>
<c:if test="${canCash==true }">
	<script type="text/javascript">
		$.canCash = true;
	</script>
</c:if>
<c:if test="${canTender==true }">
	<script type="text/javascript">
		$.canTender = true;
	</script>
</c:if>
</head>
<body class="easyui-layout" data-options="fit : true,border : false">
	<div id="userEmpquery" style="width:100%;">
		用户名： <input type="text" name="userName" style="width: 100px; height: 12px; margin: 5px;" id="userName" />
		介绍人： <input type="text" name="sourceName" style="width: 100px; height: 12px; margin: 0;" id="sourceName" /> 
		<c:if test="${isPower == false}">
		客服姓名：<select style="width: 120px; height: 26px; margin: 0;" name="emp_id" id="emp_id">
					<option value="">全部</option>
					<c:forEach items="${emps}" var="v">
						<option value="${v.empId}">${v.empName}</option>
					</c:forEach>
			</select>
		</c:if>
		注册时间:<input type="text" name="startTime" readonly="readonly" id="startTime" onClick="WdatePicker()" size="10" style="margin: 0;width:100px;height: 13px;cursor:pointer;"/>
-<input type="text" name="endTime" readonly="readonly" id="endTime" onClick="WdatePicker()" size="10" style="margin: 0;width:100px;height: 13px;cursor:pointer;"/>
		<a href="javascript:findUserEmp();" class="easyui-linkbutton" iconCls="icon-search" id="searchButton">搜索</a>
	</div>
	<div data-options="region:'center',border:false">
		<div id="userEmpList" rownumbers="true"></div>
	</div>
</body>
</html>