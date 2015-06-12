<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询可用余额</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sinadeposit/searchBalance.js"></script>
<c:if test="${canSearch==true }">
	<script type="text/javascript">
		$.canSearch = true;
	</script>
</c:if>
</head>
<body class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<div style="margin:10px;">
			用户名/手机号:<input type="text" id="condition" name="condition" style="width: 120px; height: 12px; margin: 0;" value=""/> 
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