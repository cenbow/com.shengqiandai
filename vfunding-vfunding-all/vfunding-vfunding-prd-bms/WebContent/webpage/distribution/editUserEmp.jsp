<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="${pageContext.request.contextPath }/inc.jsp"></jsp:include>
<title>手动分配管理列表</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/distribution/editUserEmp.js"></script>
</head>
<body class="easyui-layout" data-options="fit : true,border : false">
	<div id="userEmpquery">
		用户名： <input type="text" name="userName" style="width: 120px; height: 12px; margin: 0;" id="userName" />
		客服姓名：
			<select style="width: 120px; height: 26px; margin: 0;" name="emp_id" id="emp_id">
					<option value="">全部</option>
					<c:forEach items="${emps}" var="v">
						<option value="${v.empId}">${v.empName}</option>
					</c:forEach>
			</select>
		<a href="javascript:findUserEmp();" class="easyui-linkbutton" iconCls="icon-search" id="searchButton">搜索</a>
	<div>
		更换客服：
			<select style="width: 120px; height: 26px; margin: 0;" name="update_emp_id" id="update_emp_id">
					<option value="">请选择</option>
					<c:forEach items="${emps}" var="v">
						<option value="${v.empId}">${v.empName}</option>
					</c:forEach>
			</select>
		更换备注：<textarea rows="5" cols="1" name="updateRemark" id="updateRemark" style="width: 368px; height: 17px;margin: 0;"></textarea>
		<a href="javascript:edit();" class="easyui-linkbutton" iconCls="icon-save" id="searchButton">更换</a>
	</div>
	</div>
	
	<div data-options="region:'center',border:false">
		<div id="userEmpList"></div>
	</div>
</body>
</html>