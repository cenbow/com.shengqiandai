<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>同步数据</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sinadeposit/synPlatData.js"></script>
<c:if test="${canInitProject==true }">
	<script type="text/javascript">
		$.canInitProject = true;
	</script>
</c:if>
</head>
<body class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<div style="margin:10px;">
			<c:if test="${canInitProject==true}">
				<a href="javascript:do1();" class="easyui-linkbutton" iconCls="icon" id="pullBtn">代收企业钱包</a>
				<a href="javascript:do2();" class="easyui-linkbutton" iconCls="icon" id="pushBtn">代付所有用户</a>
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