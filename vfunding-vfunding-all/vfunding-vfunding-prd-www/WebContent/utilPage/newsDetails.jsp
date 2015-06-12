<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="date" uri="http://cn.vfunding.vfunding.prd.www/tags"%>
<!doctype html>
<html>
<head>
<meta charset="${empty charset ?'utf-8':charset}">
<meta name="description" content="微积金(www.vfunding.cn) 整合了民营汽车借贷机构、二手车商、第三方汽车服务机构等传统领域企业，形成了独特的针对汽车提供投融资对接服务的创新型O2O互联网汽车金融服务模式。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、汽车贷款等。" />
<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|微贷|红包|基金|微基金|小额贷款|微财富|宜车贷|二手车抵押|车贷|托管" />
<title>${article.name}_微积金_中国领先的O2O互联网汽车金融平台</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/newsDetails.css"/>
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script>
$(function(){
	$('.au-nav li').eq(${article.siteId}==95?3:4).addClass("au-selected");
})
</script>
</head>
<body>
<!--头部--->
<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>
<!--中间-->
<div class="content clear">
	  <jsp:include page="${pageContext.request.contextPath }/utilPage/aboutUsMenu.jsp"></jsp:include>
<div class="about-us-right fr box-shadow">
<div class="au-box">
<h3 class="newsTilte">${article.name}</h3>
<p class="newsTime" style="${article.siteId!=95?'text-align:center':'text-align:right'}">
	<date:date parttern="yyyy-MM-dd" value="${article.addtime}"/> &nbsp; &nbsp; &nbsp;<c:if test="${article.siteId!=95}">来源：${article.source}</c:if></p>
<div class="news-content" style="padding:0 20px; line-height:24px;">
	${article.content}
</div>
</div>
</div>
</div>
<!------尾部------>
<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
</body>
</html>