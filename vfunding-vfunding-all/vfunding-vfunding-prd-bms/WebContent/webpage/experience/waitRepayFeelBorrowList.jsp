<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>体验标还款列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/experience/waitRepayFeelBorrowList.js"></script>
<c:if test="${canEdit==true }">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
</head>
<body>
	<div id="searchTool">
		借款标题:<input type="text" id="userName" name="username"/>
		状态:<select id="status" name="status">
			<option value="">全部</option>
			<option value="30" selected="selected">待还</option>
			<option value="40">已还</option>
		</select> <a href="javascript:queryDetail();" class="easyui-linkbutton"
			iconCls="icon-search" id="searchButton">查询</a>
			&nbsp;&nbsp;
		体验金账户余额：<span>${accountFeel.useMoney}元</span>
	</div>
	<table id="list"></table>
</body>
</html>