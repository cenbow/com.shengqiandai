<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>会员登录</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/chosen.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/persnoalInfo.css" />

<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/chosen.jquery.js"></script>

<!-- 上传需要的CSS和JS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/upload/upload.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/upload/ajaxupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/upload/upload.js"></script>


</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
	<input type="text" id="txtFileName" readonly="readonly" />
	<div id="btnUp" style="width: 60px; display: inline-block;" class="btnsubmit">浏览</div>
	
</body>
</html>