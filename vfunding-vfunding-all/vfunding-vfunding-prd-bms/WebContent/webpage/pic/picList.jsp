<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>banner列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<c:if test="${addOrEdit==true }">
	<script type="text/javascript">
		$.addOrEdit = true;
	</script>
</c:if>
<c:if test="${canDelete==true }">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/pic/picList.js"></script>
</head>
<body class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<div id="list"></div>
	</div>
	<div id="searchTool" style="display: none;">
	<a href="javascript:addPic();" class="easyui-linkbutton"
		iconCls="icon-add" plain="true">添加图片</a>
	</div>
	<span style="padding: 1px 3px 1px 3px"></span>
</body>
</html>