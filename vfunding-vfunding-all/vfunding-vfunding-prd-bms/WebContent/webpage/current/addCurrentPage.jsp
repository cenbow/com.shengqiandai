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
</head>
<body>
<body class="easyui-layout" data-options="fit : true,border : false">
	<form id="addForm" action="/current/addCurrent">
		<table>
			<tr>
				<td>标的名称</td>
				<td><input type="text" name="currentName"/></td>
				<td>标的金额</td>
				<td><input type="text" name="sumMoney"/></td>
			</tr>
			<tr>
				<td>标的利率</td>
				<td><input type="text" name="apr"/></td>
				<td>开售时间</td>
				<td><input type="text" name="saleTime" onfocus="WdatePicker({startDate:'%y-%M-%d %H',dateFmt:'yyyy-MM-dd HH:00:00'})"/></td>
			</tr>
			<tr>
				<td>最小投资额</td>
				<td><input type="text" name="lowestAccount"/></td>
				<td>最大投资额</td>
				<td><input type="text" name="mostAccount"/></td>
			</tr>
		</table>
	</form>
</body>
</html>