<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/activity-zan/list.js">
	
</script>
<div class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<div id="activitydatagrid"></div>
	</div>

	<div id="activityToolbar" style="display: none;">
		<div>

			<div style="float: left;">
				<a href="javascript:add();" class="easyui-linkbutton"
					iconCls="icon-add" plain="true">添加统计</a>
			</div>

			<div
				style="text-align: right; vertical-align: middle; margin-top: 4px; margin-right: 20px;">
				<input id="roleSearch" class="easyui-searchbox"
					data-options="searcher:search,prompt:'请输入用户名称',menu:'#rolemm'"
					style="width: 250px;"></input>
					<input type="hidden" id="fileBasePath" value="${fileDownloadBasePath }"/>

				<div id="rolemm" style="width: 120px">
					<div data-options="name:'roleName'">用户名称</div>
				</div>
			</div>
		</div>

	</div>

</div>