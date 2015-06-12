<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>


<script type="text/javascript">
	var ctx = '${pageContext.request.contextPath }';
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 引入jQuery -->
<script type="text/javascript" src="${ctx }/js/jquery-1.8.3.js"></script>

<script type="text/javascript" src="${ctx }/js/extBrowser.js"
	charset="utf-8"></script>

<!-- 引入my97日期时间控件 -->
<script type="text/javascript"
	src="${ctx }/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>

<!-- 引入kindEditor插件 -->
<link rel="stylesheet"
	href="${ctx }/js/kindeditor-4.1.7/themes/default/default.css">
<script type="text/javascript"
	src="${ctx }/js/kindeditor-4.1.7/kindeditor-all-min.js" charset="utf-8"></script>


<!-- 引入Highcharts -->
<script src="${ctx }/js/Highcharts-3.0.1/js/highcharts.js"
	type="text/javascript" charset="utf-8"></script>
<!-- 引入themes-->
<link href="${ctx }/js/easyui/themes/icon.css" rel="stylesheet"
	media="screen">

<!-- 引入EasyUI -->
<link id="easyuiTheme" rel="stylesheet"
	href="${ctx }/js/easyui/themes/<c:out value="${cookie.easyuiThemeName.value}" default="bootstrap"/>/easyui.css"
	type="text/css">

<script type="text/javascript"
	src="${ctx }/js/easyui/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript"
	src="${ctx }/js/easyui/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<!-- 修复EasyUI1.3.3中layout组件的BUG -->
<script type="text/javascript"
	src="${ctx }/js/easyui/plugins/jquery.layout.js" charset="utf-8"></script>

<!-- 引入EasyUI Portal插件 -->
<link rel="stylesheet" href="${ctx }/js/jquery-easyui-portal/portal.css"
	type="text/css">
<script type="text/javascript"
	src="${ctx }/js/jquery-easyui-portal/jquery.portal.js" charset="utf-8"></script>

<!-- 扩展EasyUI -->
<script type="text/javascript"
	src="${ctx }/js/extEasyUI.js?v=201305241044" charset="utf-8"></script>

<!-- 扩展EasyUI Icon -->
<link rel="stylesheet"
	href="${ctx }/style/extEasyUIIcon.css?v=201305301906" type="text/css">

<!-- 扩展jQuery -->
<script type="text/javascript"
	src="${ctx }/js/extJquery.js?v=201305301341" charset="utf-8"></script>

<!--  
<script type="text/javascript" src="${ctx }/js/jquery.outerhtml.js"
	charset="utf-8"></script>


<link href="${ctx }/js/qtip/jquery.qtip.min.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/qtip/jquery.qtip.pack.js"
	charset="utf-8"></script>
-->





