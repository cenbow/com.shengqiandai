<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>文章列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/article/articleList.js"></script>
<c:if test="${canEdit==true }">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${canDelete==true }">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>

<style>
.datagrid-row-over,
.datagrid-header td.datagrid-header-over {
  background: #eaf2ff;
  color: #000000;
  cursor: default;
}
.datagrid-row-selected {
  background: #eaf2ff;
  color: #000000;
}
</style>
</head>
<body class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<div id="list"></div>
	</div>
	<div id="searchTool" style="display: none;">
			<div style="float: left;">
				<c:if test="${canEdit==true }">
					<a href="javascript:addArticle();" class="easyui-linkbutton" style="margin: 5px;"
						iconCls="icon-add" plain="true">发布新文章</a>
				</c:if>
				<c:if test="${canEdit==false }">
					<a href="javascript:void(0);" class="easyui-linkbutton" style="margin: 5px;"
						iconCls="icon-add" plain="true">没权限</a>
				</c:if>
			</div>
			<div style="text-align: right; vertical-align: middle; margin-top: 4px; margin-right: 20px;">
				类型：
				<select id="siteId" name="siteId" style="width:120px;height:28px;">
					<option value="">全部</option>
					<option value="95">网站公告</option>
					<option value="85">媒体报道</option>
					<option value="108">小微新闻</option>
					<option value="109">小微攻略</option>
				</select>
				<a href="javascript:queryDetail();" class="easyui-linkbutton"
			iconCls="icon-search" id="searchButton">查询</a>
			</div>
	</div>
	<span style="padding: 1px 3px 1px 3px"></span>
</body>
</html>