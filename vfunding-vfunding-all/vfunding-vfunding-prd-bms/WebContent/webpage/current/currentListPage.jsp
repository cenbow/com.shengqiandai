<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/current/current.js"></script>
</head>
<body>
<body class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<div id="list"></div>
	</div>
	<div id="searchTool" style="display: none;">
		<div>
			<a href="javascript:toAddCurrentPage()" class="easyui-linkbutton" style="margin: 5px;" iconCls="icon-add" plain="true">添加活期标的</a>
			用户名<input type="text" id="userName" name="userName" /> 类型<select
			id="biaoType" name="biaoType">
			<option value="">全部</option>
			<option value="fast">抵押标</option>
			<option value="xin">信用标</option>
			<option value="jin">净值标</option>
			<option value="lz">流转标</option>
			<option value="miao">秒还标</option>
			<option value="huodong">活动标</option>
		</select> 状态:<select id="status" name="status">
			<option value="">全部</option>
			<option value="0" selected="selected">待初审</option>
			<option value="1">初审成功</option>
			<option value="2">初审失败</option>
		</select> <a href="javascript:queryDetail();" class="easyui-linkbutton"
			iconCls="icon-search" id="searchButton">查询</a>
		</div>
	</div>
</body>
</html>