<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="${pageContext.request.contextPath }/inc.jsp"></jsp:include>
<c:if test="${canChange==true }">
	<script type="text/javascript">
		$.canChange = true;
	</script>
</c:if>

<c:if test="${canGrant==true }">
	<script type="text/javascript">
		$.canGrant = true;
	</script>
</c:if>

<c:if test="${canAddOrEdit==true }">
	<script type="text/javascript">
		$.canAddOrEdit = true;
	</script>
</c:if>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/system/roleList.js">
</script>
</head>
<body class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<div id="roledatagrid"></div>
	</div>

	<div id="roleToolbar" style="display: none;">
	  <div>
	   <c:if test="${canAddOrEdit==true }">
	     <div style="float: left;">
	        <a href="javascript:addRole();" class="easyui-linkbutton" iconCls="icon-add" plain="true" >添加角色</a>
	     </div>
	   </c:if>
	     <div style="text-align: right; vertical-align: middle; margin-top: 4px; margin-right: 20px;">
		    <input id="roleSearch" class="easyui-searchbox"
			data-options="searcher:searchRole,prompt:'请输入角色名称',menu:'#rolemm'" style="width: 250px;"></input>

		    <div id="rolemm" style="width: 120px">
			  <div data-options="name:'roleName'">角色名称</div>		
		    </div>
		</div>
	  </div>
	   
	</div>
	
	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		 <c:if test="${canAddOrEdit==true }">
			<div onclick="addRole();" data-options="iconCls:'icon-add'">添加角色</div>
		</c:if>
		<c:if test="${canAddOrEdit==true }">
			<div onclick="editRole();" data-options="iconCls:'icon-edit'">修改角色</div>
		</c:if>
		<c:if test="${canGrant==true }">
			<div onclick="grantFun();" data-options="iconCls:'icon-redo'">查看资源</div>
		</c:if>
		
	</div>


</body>
</html>