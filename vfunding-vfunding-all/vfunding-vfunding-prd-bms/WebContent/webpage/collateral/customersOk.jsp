<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>借款客户信息</title>
<jsp:include page="${pageContext.request.contextPath }/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/collateral/customers.js"></script>
</head>
<body>
<body class="easyui-layout" data-options="fit : true,border : false">
		<div id="easrchTool">
  客户姓名：       <input type="text" name="ownerName" value="${car.ownerName }" id="ownerName"/><br/>
合同开始日期：<input type="text" name="contractStart" id="contractStart" value="${start}"/><input type="hidden" name="id" id="id" value="${booro.id}"/><br/>
合同结束日期：<input type="text" name="contractEnd" id="contractEnd" value="${end}" /><br/>
<a href="javascript:;" class="easyui-linkbutton"
			onclick="updaBorrow()" id="searchButton">保存</a>
</div>
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<div id="customersList"></div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/collateral/customers.js"></script>
</body>
</html>
