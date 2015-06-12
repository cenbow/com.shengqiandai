<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>借款客户信息</title>
<jsp:include page="${pageContext.request.contextPath }/inc.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/collateral/customers.js"></script>
</head>
<body class="easyui-layout" data-options="fit : true,border : false">

	<div id="easrchTool">
		客户姓名： <input type="text" name="textfield" id="ownerName"
			style="width: 120px; height: 12px; margin: 0;" /> 抵押品种： <select
			name="select" id="mortgageType"
			style="height: 24px; font-size: 12px; margin: 0;">
			<option selected="selected" value="">所有</option>
			<option value="1">汽车</option>
			<option value="2">债权抵押</option>
		</select> <!-- 满标时间： <input type="text" name="textfield2" id="successTime"
			style="width: 120px; height: 12px; margin: 0;" /> 到 <input
			type="text" name="textfield3" id="successTime1"
			style="width: 120px; height: 12px; margin: 0;" /> --> <a
			href="javascript:findCustomers();" class="easyui-linkbutton"
			iconCls="icon-search" id="searchButton">查询</a>
	</div>


	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<div id="customersList"></div>
	</div>











</body>
</html>
