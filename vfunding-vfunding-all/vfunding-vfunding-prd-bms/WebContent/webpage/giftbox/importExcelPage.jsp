<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/upload/upload.css"
	media="screen" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/upload/ajaxupload.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/giftbox/importExcel.js"></script>
<style type="text/css">
.disSpan {
	display: block;
	width: 100%;
}
.disSpan2{
	width: auto;
}
</style>
</head>
<body>
	<div style="margin-left: 2%; margin-top: 2%">
		<form action="/giftboxTiral/importExcel" id="subForm"
			enctype="multipart/form-data">
			<span style="width: 100%; display: block;">红包标题：<input
				type="text" id="title" name="title" style="width: 40%" /></span> <span
				style="float: left;" id="disSpan" class="disSpan">选择红包模板： <select
				name="templateId" id="templateSelect"
				onchange="showTemplateContent()">
					<option value="">请选择</option>
					<c:forEach var="gtemp" items="${gtList}">
						<option value="${gtemp.id}">${gtemp.name}</option>
					</c:forEach>
			</select>

			</span>
			<div style="padding-left: 1%; width: 60%; overflow: hidden;min-height: 40px;display: none"
				id="templateContent">
				<c:forEach var="gtemp" items="${gtList}">
					<font color="blue" style="display: none;" name="gtContent"
						id="${gtemp.id}">${gtemp.content}</font>
				</c:forEach>
			</div>
			<span id="fileSpan" style="width: 100%; display: block;">选择导入红包Excel：
				<span> <input type="button" style="width: 100px;"
					id="searchExcel" value="浏览"> <input type="text"
					name="excelName" id="excelName" readonly="readonly" />
			</span> <font color="blue">导入信息表格前三列固定为接收红包用户ID、生效日期、失效日期,金额列请以"money"为列头</font>
			</span> <span id="fileSpan" style="width: 100%; display: block;"> <input
				type="button" id="subBtn" style="width: 100px;" id="subBtn"
				value="提交审核">
			</span>
			<div>
				<table id="showPerview">
				</table>
			</div>
		</form>
	</div>
</body>
</html>