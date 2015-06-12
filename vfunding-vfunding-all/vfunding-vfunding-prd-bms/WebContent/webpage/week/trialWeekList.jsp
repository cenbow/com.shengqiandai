<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>审核计划列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/week/trialWeekList.js"></script>
<c:if test="${canTrial==true }">
	<script type="text/javascript">
		$.canTrial = true;
	</script>
</c:if>
<style>
.datagrid-row-over, .datagrid-header td.datagrid-header-over {
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
		<div id="list"></div>
	</div>
	<div id="searchTool" style="display: none;">
		<div>
			计划编号:<input type="text" id="id" name="id" style="width: 120px; height: 12px; margin: 0;"/> 
			计划状态:		
			<select id="status" name="status" style="width: 120px; height: 28px;">
				<option value="">全部</option>
				<option value="1" selected="selected">待审核</option>
				<option value="2">审核失败</option>
				<option value="3">审核成功</option>
			</select> 
			发售开始时间:<input type="text" id="saleTime" name="saleTime" readonly="readonly" onClick="WdatePicker()" size="10" style="width:100px;cursor:pointer;background-color:#fff;"
					value="${saleTime}"
					/>
			发售截止时间:<input type="text" id="expireTime" name="expireTime" readonly="readonly" onClick="WdatePicker()" size="10" style="width:100px;cursor:pointer;background-color:#fff;"/>

			<a href="javascript:queryDetail();" class="easyui-linkbutton" iconCls="icon-search" id="searchButton">查询</a>
		</div>
	</div>	
</body>
</html>