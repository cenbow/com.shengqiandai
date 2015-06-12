<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>


<script type="text/javascript">
	var ctx = '${pageContext.request.contextPath }';
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/artDialog/utilArtDialog.js"></script>


<!-- 分页需要的CSS和JS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/paging/style.css" media="screen" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/paging/jquery.paginate.js"></script>

<!-- 时间控件 -->
<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>

<!-- 弹出层JS artDialog-->
<script
	src="${pageContext.request.contextPath}/js/artDialog/artDialog.js?skin=default"></script>
<script
	src="${pageContext.request.contextPath}/js/artDialog/plugins/iframeTools.js"></script>




