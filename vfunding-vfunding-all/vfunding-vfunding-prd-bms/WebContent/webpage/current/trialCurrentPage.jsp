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
<body class="easyui-layout" data-options="fit : true,border : false">
		<table style="width: 100%;">
			<tr>
				<td>标的名称</td>
				<td><input type="text" name="currentName" value="${current.currentName}" readonly="readonly" /></td>
				<td>标的金额</td>
				<td><input type="text" name="sumMoney" value="${current.sumMoney}" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>标的利率</td>
				<td><input type="text" name="apr" value="${current.apr}%" readonly="readonly"/></td>
				<td>开售时间</td>
				<td><input type="text" name="saleTime" id="saleTime" onfocus="WdatePicker({startDate:'%y-%M-%d %H',dateFmt:'yyyy-MM-dd HH:00:00'})"/></td>
			</tr>
			<tr>
				<td>最小投资额</td>
				<td><input type="text" name="lowestAccount" id="lowestAccount"/></td>
				<td>最大投资额</td>
				<td><input type="text" name="mostAccount" id="mostAccount"/></td>
			</tr>
			<tr>
				<td colspan="2" style="width: 50%"><input type="radio" name="trialType" currentId="${current.currentId }" value="1"/>审核通过</td>
				<td colspan="2" style="width: 50%"><input type="radio" name="trialType" currentId="${current.currentId }" value="2"/>审核拒绝</td>
			</tr>
		</table>
<style type="text/css">
tr{
width: auto;
}
tr td{
width: 25%;
}
</style>
</body>
</html>