
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="微积金(www.vfunding.cn) 整合了民营汽车借贷机构、二手车商、第三方汽车服务机构等传统领域企业，形成了独特的针对汽车提供投融资对接服务的创新型O2O互联网汽车金融服务模式。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、汽车贷款等。" />
<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|微贷|红包|基金|微基金|小额贷款|微财富|宜车贷|二手车抵押|车贷|托管" />
<title>微积金，中国领先的互联网汽车金融服务平台_理财_理财产品_投融资_理财需求_投资_投资标的_财富_财富成长_手机理财_微信理财_微理财</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/webSiteMap.css"/>
</head>

<body>

	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>

<!--中间--->

<div class="content clear">


<div class="site-map">

<div class="site-map-title">网站地图</div>

<dl class="site-map-item">
   <dt>我要投资</dt>
   <dd><a href="${pageContext.request.contextPath}/borrow/borrowList"">债权列表</a></dd>
   <dd><a href="${pageContext.request.contextPath}/borrowAuto/borrowAuto"">自动投标</a></dd>
</dl>


<dl class="site-map-item even-bg">
   <dt>我要贷款</dt>
   <dd><a href="${pageContext.request.contextPath}/borrow/releaseBorrow">债权列表</a></dd>
   <dd><a href="${pageContext.request.contextPath}/borrowAuto/borrowAuto"">自动投标</a></dd>
</dl>



<dl class="site-map-item">
   <dt>关于我们</dt>
   <dd><a href="${pageContext.request.contextPath }/utilpage/aboutUs#company">公司介绍</a></dd>
   <dd><a href="#">公司环境</a></dd>
   <dd><a href="#">团队介绍</a></dd>
   <dd><a href="#">专家顾问</a></dd>
   <dd><a href="${pageContext.request.contextPath }/utilpage/aboutUs#report">网站公告</a></dd>
   <dd><a href="${pageContext.request.contextPath }/utilpage/aboutUs#medias">媒体报道</a></dd>
   <dd><a href="${pageContext.request.contextPath }/utilpage/aboutUs#contact">联系我们</a></dd>
   <dd><a href="${pageContext.request.contextPath }/utilpage/aboutUs#employ">招贤纳士</a></dd>
   <dd><a href="${pageContext.request.contextPath }/utilpage/aboutUs#platform">平台原理</a></dd>
   <dd><a href="${pageContext.request.contextPath }/utilpage/aboutUs#security">安全保障</a></dd>
   <dd><a href="${pageContext.request.contextPath }/utilpage/aboutUs#law">法律申明</a></dd>
</dl>



<dl class="site-map-item even-bg">
   <dt>帮助中心</dt>
   <dd><a href="${pageContext.request.contextPath}/utilpage/helpCenter">投资问题</a></dd>
   <dd><a href="${pageContext.request.contextPath}/utilpage/helpCenter">贷款问题</a></dd>
   <dd><a href="${pageContext.request.contextPath}/utilpage/helpCenter">账户问题</a></dd>
   <dd><a href="${pageContext.request.contextPath}/utilpage/helpCenter">网站资费</a></dd>
   <dd><a href="${pageContext.request.contextPath}/utilpage/helpCenter">常见问题</a></dd>
   <dd><a href="${pageContext.request.contextPath}/utilpage/helpCenter">常用名词解释</a></dd>
</dl>



<dl class="site-map-item">
   <dt>理财工具</dt>
   <dd><a href="${pageContext.request.contextPath }/utilpage/profitCal">收益计算器</a></dd>
   <dd><a href="${pageContext.request.contextPath}/borrowAuto/borrowAuto">自动投资</a></dd>
   <dd><a href="${pageContext.request.contextPath }/utilpage/cpi">CPI跟踪器</a></dd>
   <dd><a href="${pageContext.request.contextPath }/utilpage/wealthCal">身价计算器</a></dd>
   <dd><a href="${pageContext.request.contextPath }/utilpage/earningsContrast">收益对比器</a></dd>
</dl>















</div>

</div>


	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>

</body>
</html>
