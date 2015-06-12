<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>招标中体验标</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/experience/feelBorrowing.js"></script>
<c:if test="${canEdit==true }">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
</head>
<body class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<div id="list"></div>
	</div>
	<div id="searchTool" style="display: none;">
		<div>
			<div style="float: left;">
				<c:if test="${canEdit==true }">
					<a href="javascript:addFeel();" class="easyui-linkbutton"
						iconCls="icon-add" plain="true">发布体验标</a>
				</c:if>
				<c:if test="${canEdit==false }">
					<a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-add" plain="true">没权限</a>
				</c:if>
			</div>
			<div
				style="text-align: right; vertical-align: middle; margin-top: 4px; margin-right: 20px;">
				<input id="empSearch" class="easyui-searchbox"
					data-options="searcher:searchFeel,prompt:'体验标标题',menu:'#rolemm'"></input>

				<div id="rolemm" style="width: 120px;  ">
					<div data-options="name:'userName'">体验标标题</div>
				</div>
			</div>
		</div>
	</div>
	<span style="padding: 1px 3px 1px 3px"></span>
</body>
</html>