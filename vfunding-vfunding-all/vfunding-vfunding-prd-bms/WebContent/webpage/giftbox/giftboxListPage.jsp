<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/giftbox/giftboxList.js"></script>
</head>
<body>
<body class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<div id="list"></div>
	</div>
	<div id="searchTool" style="display: none;">
		<div>
			<a href="javascript:addTemplate();" class="easyui-linkbutton"
					style="margin: 5px;" iconCls="icon-add" plain="true">新建模板</a>
			红包标题:<input type="text" id="title" name="title" style="width: 120px; height: 12px; margin: 0;"/> 
			红包使用状态：<select name="status" id="status">
							<option value="">全部</option>
							<option value="1">已使用</option>
							<option value="0">未使用</option>
							<option value="2">已过期</option>
						</select>
			红包发放日期:<input type="text" id="addTime" name="addTime" readonly="readonly" onClick="WdatePicker()" size="10" style="width:100px;cursor:pointer;background-color:#fff;"
					value=""/>
			<a href="javascript:queryDetail();" class="easyui-linkbutton" iconCls="icon-search" id="searchButton">查询</a>
		</div>
	</div>
</body>
</html>