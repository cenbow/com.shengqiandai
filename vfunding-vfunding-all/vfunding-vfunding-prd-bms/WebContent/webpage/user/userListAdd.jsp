<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="${pageContext.request.contextPath }/inc.jsp"></jsp:include>
<title></title>
<c:if test="${canEdit==true }">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/userListAdd.js"></script>

</head>

<body class="easyui-layout" data-options="fit : true,border : false">

	<div id="userquery" style="width: 100%;">
		用户名： <input type="text" name="userName"
			style="width: 120px; height: 12px; margin: 0;" id="userName" /> 邮箱：
		<input type="text" name="email"
			style="width: 120px; height: 12px; margin: 0;" id="email" /> 真实姓名： <input
			type="text" name="realName"
			style="width: 120px; height: 12px; margin: 0;" id="realName" />
		用户类型:<select id="typeId" name="typeId"
			style="height: 24px; font-size: 12px; margin: 0;">
			<option value="">全部</option>
			<option value="1">超级管理员</option>
			<option value="3">客服</option>
			<option value="23">财务总监</option>
			<option value="7">兼职客服</option>
			<option value="9">信贷审核</option>
			<option value="15">财务</option>
			<option value="18">离职员工组</option>
			<option value="21">管理员</option>
			<option value="24">机构发标</option>
			<option value="25">市场推广</option>
			<option value="26">公司高层</option>
			<option value="10">运营总监</option>
			<option value="2">普通用户</option>
			<option value="27">集团用户</option>
			<option value="28">特约理财师</option>
			<option value="29">高级理财师</option>
			<option value="30">首席理财师</option>
			<option value="31">集团内部理财师</option>
			<option value="16">出纳</option>
			<option value="11">运营</option>
			<option value="12">财富助手</option>
			<option value="40">borrow user</option>
			<option value="32">推荐达人</option>
		</select> <a href="javascript:findUser();" class="easyui-linkbutton"
			iconCls="icon-search" id="searchButton">搜索</a>
	</div>

	<div data-options="region:'center',border:false">
		<div id="userAddList" rownumbers="true"></div>
	</div>

</body>
</html>