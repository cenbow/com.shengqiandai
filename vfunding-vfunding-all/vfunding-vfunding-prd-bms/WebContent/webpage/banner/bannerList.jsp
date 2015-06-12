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
	src="${pageContext.request.contextPath }/js/banner/bannerList.js"></script>
</head>
<body class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<div id="list"></div>
	</div>
	<div id="searchTool" style="display: none;">
		<c:if test="${addOrEdit==true }">
			<div style="float: left;">
				<a href="javascript:addBanner();" class="easyui-linkbutton"
					iconCls="icon-add" plain="true">添加banner</a>
			</div>
		</c:if>

		<div
			style="text-align: right; vertical-align: middle; margin-top: 4px; margin-right: 20px;">
			类型： <select id="typeId" name="typeId"
				style="width: 120px; height: 28px;">
				<option value="">全部</option>
				<option value="1">网站</option>
				<option value="2">手机</option>
				<option value="0">宣传</option>
			</select> 状态： <select id="status" name="status"
				style="width: 120px; height: 28px;">
				<option value="">全部</option>
				<option value="1">显示</option>
				<option value="0">隐藏</option>
			</select> <a href="javascript:queryDetail();" class="easyui-linkbutton"
				iconCls="icon-search" id="searchButton">查询</a>
		</div>
	</div>
	<span style="padding: 1px 3px 1px 3px"></span>
</body>
</html>