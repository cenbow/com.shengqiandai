<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="date" uri="http://cn.vfunding.vfunding.prd.www/tags"%>
<!doctype html>
<html>
<head>
<meta charset="${empty charset ?'utf-8':charset}">
<meta name="description" content="微积金互联网金融服务平台旨在为有资金需求和理财需求的个人或企业搭建一个真实诚信、安全透明、简单方便、快捷高效的互联网服务平台。致力于成为中国最专业的互联网汽车金融服务商。" />
<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|微贷|红包|基金|微基金|小额贷款|微财富|宜车贷|二手车抵押|车贷|托管" />
<title>平台原理_微积金_中国领先的O2O互联网汽车金融平台</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/aboutUs.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
</head>
<body>
	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>
	<!--中间-->
	<div class="content clear">
		<jsp:include page="${pageContext.request.contextPath }/utilPage/aboutUsMenu.jsp"></jsp:include>
		<div class="about-us-right fr box-shadow">

			<!-- 平台原理 -->
			<div class="au-box" id="platform_">
				<div class="au-title">平台原理</div>
				<div class="contact">
					<p style="text-indent: 10px;">微积金互联网金融服务平台旨在为有资金需求和理财需求的个人或企业搭建一个真实诚信、安全透明、简单方便、快捷高效的</p>
					<p style="text-indent: 10px;">互联网服务平台。致力于成为中国最专业的互联网汽车金融服务商。</p>
					<br /> <br />
					<p style="text-indent: 10px;">微积金致力于通过大数据、互联网等有效工具为有需要融资或投资的个人或企业提供一个安全、诚信、低风险、回报稳定的投</p>
					<p style="text-indent: 10px;">融资渠道，并以高安全性的交易机制与严格风险控制体系实现高安全的投资交易，从而保证参与交易的各方均在安全可控的交</p>
					<p style="text-indent: 10px;">易环境与状态下实现各自的需求。</p>

				</div>
				<img id="img" src="/images/yuanli.png" />
			</div>

		</div>
	</div>

	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>

</body>
</html>
