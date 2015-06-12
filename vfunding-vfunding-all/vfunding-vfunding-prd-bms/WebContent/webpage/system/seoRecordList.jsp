<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="${pageContext.request.contextPath }/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/system/seoList.js"></script>
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
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<div id="seolists"></div>
	</div>
	<div id="searchTool">
		<c:if test="${addOrEdit==true}">
			<div style="float: left;">
				<a href="javascript:addSeoRecord();" class="easyui-linkbutton"
					iconCls="icon-add" plain="true" style="margin: 5px;">添加记录</a>
			</div>
		</c:if>	
		<c:if test="${addOrEdit==false }">
			<div style="float: left;">
				<a href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-add" plain="true" style="margin: 5px;">没权限</a>
			</div>
		</c:if>
		
		标题：<input type="text" name="title" id="title" style="width: 150px; height: 15px; margin: 5px;"/>
		地址：<input type="text" name="url" id="url" style="width: 180px; height: 15px; margin: 5px;"/>
		模块：
			<select id="type" name="type" style="width:100px;height: 27px;margin: 5px;">
				<option value="">全部</option>
				<option value="1">首页</option>
				<option value="2">我要投资</option>
				<option value="3">我要借款</option>
				<option value="4">我的账户</option>
				<option value="5">关于我们</option>
				<option value="6">帮助中心</option>
				<option value="0">其他</option>
			</select>
		
	<a href="javascript:queryDetail();" class="easyui-linkbutton" iconCls="icon-search" id="searchButton">查询</a>
	</div>
</body>
</html>