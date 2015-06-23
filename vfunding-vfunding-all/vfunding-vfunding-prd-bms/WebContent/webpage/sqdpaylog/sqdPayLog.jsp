<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>sqd支付记录</title>
<!-- 引入bootstrap样式-->
<link href="${ctx }/js/bootstrap-2.3.1/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
	 
<!-- 引入easyUI 1.4.2-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/jquery-easyui-1.4.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/jquery-easyui-1.4.2/themes/icon.css"  media="screen">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/jquery-easyui-1.4.2/demo/demo.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery-easyui-1.4.2/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
	
	
	<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/sqdpaylog/sqdPayLog.js"></script>
	
	
 <c:if test="${canEdit==true }">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>

<script type="text/javascript">
$(function(){
	parent.$.messager.progress('close');
})

</script> 
	
</head>
<body>
<!-- 表格 -->
<table id="dg"></table>
</body>
</html>