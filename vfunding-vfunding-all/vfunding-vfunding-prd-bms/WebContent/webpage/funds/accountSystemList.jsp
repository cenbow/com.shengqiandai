<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>账户列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/funds/accountSystemList.js"></script>
</head>
<body class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<div id="list"></div>
	</div>
	<div id="accountToolbar">
		<div style="text-align: left; vertical-align: middle; margin-top: 4px; margin-right: 20px;">
			<input id="empSearch" class="easyui-searchbox"
				data-options="searcher:searchAccount,prompt:'请输入用户登录名',menu:'#rolemm'"></input>
			<div id="rolemm" style="width: 120px;  ">
				<div data-options="name:'userName'">用户名</div>
			</div>
		</div>
	</div>
</body>
</html>