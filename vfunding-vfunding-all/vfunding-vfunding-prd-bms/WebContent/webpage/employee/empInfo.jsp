<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>数据显示</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/employee/empInfo.js"></script>


<c:if test="${addOrEdit==true }">
	<script type="text/javascript">
		$.addOrEdit = true;
	</script>
</c:if>
</head>

<body class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<div id="empdatagrid"></div>
	</div>

	<div id="empToolbar" style="display: none;">
		<div>
			<c:if test="${addOrEdit==true }">
				<div style="float: left;">
					<a href="javascript:addEmp();" class="easyui-linkbutton"
						iconCls="icon-add" plain="true">添加员工</a>
				</div>
			</c:if>
			<div
				style="text-align: right; vertical-align: middle; margin-top: 4px; margin-right: 20px;">
				<input id="empSearch" class="easyui-searchbox"
					data-options="searcher:searchEmp,prompt:'请输入员工名称',menu:'#rolemm'"></input>

				<div id="rolemm" style="width: 120px;  ">
					<div data-options="name:'empName'">员工名称</div>
				</div>
			</div>
		</div>
	</div>
	<span style="padding: 1px 3px 1px 3px"></span>



	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<c:if test="${addOrEdit==true }">
			<div onclick="editEmp();" data-options="iconCls:'icon-edit'">修改员工信息</div>
		</c:if>
		
	
	</div>


</body>
</html>