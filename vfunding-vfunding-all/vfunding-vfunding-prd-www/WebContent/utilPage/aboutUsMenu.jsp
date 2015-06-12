<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<script type="text/javascript">
$(function(){
	$('.au-nav li a[href="' + window.location.pathname + '"]').parent().addClass("au-selected");
})
</script>
<!--左菜单 -->
<div class="about-us-left fl box-shadow">
	<h1>关于我们</h1>
	<div class="au-nav">
		<ul>
			<li><a href="${pageContext.request.contextPath}/utilpage/aboutUs">公司介绍</a></li>
			<li><a href="${pageContext.request.contextPath}/utilpage/managerTeam">管理团队</a></li>
			<li><a href="${pageContext.request.contextPath}/utilpage/officeEnvironment">办公环境</a></li>
			<li><a href="${pageContext.request.contextPath}/utilpage/websiteNotice">网站公告</a></li>
			<li><a href="${pageContext.request.contextPath}/utilpage/mediaReports">媒体报道</a></li>
			<li><a href="${pageContext.request.contextPath}/utilpage/contactUs">联系我们</a></li>
			<li><a href="${pageContext.request.contextPath}/utilpage/recruit">招贤纳士</a></li>
			<li><a href="${pageContext.request.contextPath}/utilpage/platTheory">平台原理</a></li>
			<li><a href="${pageContext.request.contextPath}/utilpage/safe">安全保障</a></li>
			<li><a href="${pageContext.request.contextPath}/utilpage/law">法律申明</a></li>
			<li><a href="${pageContext.request.contextPath}/utilpage/surpise">监管报告</a></li>
		</ul>
	</div>
</div>
