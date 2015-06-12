<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>还款记录列表列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/week/repaymentRecordList.js"></script>


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
			<c:if test="${canEdit==true }">
				<a href="javascript:addWeek();" class="easyui-linkbutton"
					style="margin: 5px;" iconCls="icon-add" plain="true">新建计划</a>
			</c:if>
			<c:if test="${canEdit==false }">
				<a href="javascript:void(0);" class="easyui-linkbutton"
					style="margin: 5px;" iconCls="icon-add" plain="true">没权限新建</a>
			</c:if>
			计划名称:<input type="text" id="name" name="name" style="width: 120px; height: 12px; margin: 0;"/> 
			
			还款时间:<input type="text" id="time" name="time" readonly="readonly" onClick="WdatePicker()" size="10" style="width:100px;cursor:pointer;background-color:#fff;"
					value="${saleTime}"
					/>

			<a href="javascript:queryDetail();" class="easyui-linkbutton" iconCls="icon-search" id="searchButton">查询</a>
		</div>
	</div>	
</body>
</html>