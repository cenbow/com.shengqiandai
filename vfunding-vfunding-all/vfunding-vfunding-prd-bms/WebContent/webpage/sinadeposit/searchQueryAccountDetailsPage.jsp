<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询收支明细</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sinadeposit/searchQueryAccountDetails.js"></script>
<c:if test="${canSearch==true }">
	<script type="text/javascript">
		$.canSearch = true;
	</script>
</c:if>
<style> 
table{border:1px solid #333333} 
table td{border:1px solid #333333} 
</style> 
</head>
<body class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<div style="margin:10px;">
			用户名:<input type="text" id="userId" name="userId" style="width: 120px; height: 12px; margin: 0;" value=""/> 
			用户类型<select id="idType" name="idType" style="width: 120px; height: 28px;">
				<option value="UID">UID</option>
				<option value="EMAIL">EMAIL</option>
			</select> 
			开始时间:<input type="text" id="start" name="start" readonly="readonly" onClick="WdatePicker()" size="10" style="width:100px;cursor:pointer;background-color:#fff;"
			 value="${start}" />
			截止时间:<input type="text" id="end" name="end" readonly="readonly" onClick="WdatePicker()" size="10" style="width:100px;cursor:pointer;background-color:#fff;"
			 value="${end}" />
			第几页:
			<input type="text" id="pageNo" name="pageNo" style="width: 120px; height: 12px; margin: 0;" value="1"/> 
			一页几条数据:
			<input type="text" id="pageSize" name="pageSize" style="width: 120px; height: 12px; margin: 0;" value="20"/> 
			 
			<c:if test="${canSearch==true }">
				<a href="javascript:queryDetail();" class="easyui-linkbutton" iconCls="icon-search" id="searchButton">查询</a>
			</c:if>			
		</div>
		<div id="wait" style="margin:10px;">
			<img src="/style/images/wait.gif" />
		</div>
		<div id="result" style="margin:10px;">
		</div>		
	</div>
	<div id="searchTool" style="display:none;">
	</div>
</body>
</html>