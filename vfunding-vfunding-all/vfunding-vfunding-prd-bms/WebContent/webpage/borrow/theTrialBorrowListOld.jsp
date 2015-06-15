<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>初审列表</title>
<jsp:include page="${pageContext.request.contextPath }/inc.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/borrow/theTrialBorrowList.js"></script>
<c:if test="${canEdit==true }">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>

<script type="text/javascript">
$(function(){
	parent.$.messager.progress('close');
})
function addBorrow(){
	parent.$.modalDialog({
		title : '添加借款标',
		width : 1200,
		height : 600,
		href : '/system/borrow/addBorrowPage',
		buttons : [ {
			text : '添加',
			iconCls:'icon-save',
			handler : function() {
				var f = parent.$.modalDialog.handler.find('#addBorrowform');
				f.submit();
			}
		} ]
	});
}
</script>
</head>
<body class="easyui-layout" data-options="fit : true,border : false">
<div id="searchTool">
		<div style="float: left;">
			<a href="javascript:addBorrow();" class="easyui-linkbutton" iconCls="icon-add" plain="true">发布借款标</a>
		</div>
		<div id="searchTool">
		用户名<input type="text" id="userName" name="userName" /> 类型<select
			id="biaoType" name="biaoType">
			<option value="">全部</option>
			<option value="fast">抵押标</option>
			<option value="xin">信用标</option>
			<option value="jin">净值标</option>
			<option value="lz">流转标</option>
			<option value="miao">秒还标</option>
			<option value="huodong">活动标</option>
		</select> 状态:<select id="status" name="status">
			<option value="">全部</option>
			<option value="0" selected="selected">待初审</option>
			<option value="1">初审成功</option>
			<option value="2">初审失败</option>
		</select> <a href="javascript:queryDetail();" class="easyui-linkbutton"
			iconCls="icon-search" id="searchButton">查询</a>
	</div>
</div>
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<div id="dg"></div>
	</div>
</body>
</html>