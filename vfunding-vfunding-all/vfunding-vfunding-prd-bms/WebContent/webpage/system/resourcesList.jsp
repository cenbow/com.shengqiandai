<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>资源管理</title>
<jsp:include page="${pageContext.request.contextPath }/inc.jsp"></jsp:include>
<c:if test="${canAddOrEdit==true }">
	<script type="text/javascript">
		$.canAddOrEdit = true;
		
	</script>
</c:if>
<c:if test="${canDelete==true }">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/system/resourcesList.js"></script>

</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
			<table id="resourceTreeGrid"></table>
		</div>
	</div> 
	<div id="toolbar" style="display: none;">
		<c:if test="${canAddOrEdit==true }">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
		</c:if>	
		<a onclick="redo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_next'">展开</a> 
		<a onclick="undo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_previous'">折叠</a> 
		<a onclick="resourceTreeGrid.treegrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>

	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<c:if test="${canAddOrEdit==true }">
			<div onclick="addFun();" data-options="iconCls:'icon-add'">增加</div>
		</c:if>	
		
		<c:if test="${canAddOrEdit==true }">
			<div onclick="editFun();" data-options="iconCls:'icon-edit'">编辑</div>
        </c:if>
	</div>
</body>
</html>