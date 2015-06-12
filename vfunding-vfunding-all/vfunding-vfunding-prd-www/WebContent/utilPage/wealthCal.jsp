
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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/tools.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/wealthCal.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/global-1.js"></script>

</head>
<body>

	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>


	<!--中间-->

	<!--中间-->
	<div id="wrapper"  class="box-shadow">
		<div class="title-sub">
			<h2>身价计算器</h2>
			<b class="line"></b>
		</div>
		<div class="worth-calc">
			<div class="worth-chart">
				<div class="worth-div worth-4">
					<h2>当前身价(元)</h2>
					<strong></strong>
				</div>
			</div>
			<div class="topics">
				<div style="display: none;" class="topics-items" id="worthTopics">
					<h2>4.您希望进行身价预估的时间：</h2>
					<div class="worth-input">
						<select id="curValue_3"><option value="1">1</option>
							<option value="2">2</option>
							<option value="5">5</option>
							<option value="10">10</option>
							<option value="20">20</option>
							<option value="30">30</option></select> <label>年后</label>
						<div class="tip error" id="errorTip_3"></div>
					</div>
					<div class="worth-submint">
						<a href="javascript:;" id="NextTopic" data-rel="3" class="gbtn">完成</a>
					</div>
				</div>
				<div style="display: block;" class="topics-items" id="worthData">
					<ul></ul>
					<span style="display: none" id="worthRecord"></span>
					<div style="display: block;" class="worth-submint none"
						id="worthSubmint">
						<a href="${pageContext.request.contextPath}/borrow/borrowList" class="gbtn" target="_blank">立即开始投资</a> <a
							href="javascript:;" id="resetCount" class="gbtn gbtn-gray">重新计算</a>
						<span class="sharing-tools"> <script
								src="http://v3.jiathis.com/code/jia.js" type="text/javascript"
								id="shareshell_js" charset="utf-8"></script> <script
								type="text/javascript">
									setTimeout(
											function() {
												document
														.getElementById("shareshell_js").src = "http://v3.jiathis.com/code/jia.js?var="
														+ Math
																.ceil(new Date() / 3600000);
												document
														.getElementById("shareshell").style.display = 'block';
											}, 1000)
								</script>
						</span>
					</div>
				</div>
			</div>
		</div>
		<div class="bid-info">
			<h2>身价计算器简介</h2>
			<ul class="items">
				<li>身价计算器用于了解您当前的存款及收入状况，预测您通过微积金进行合理投资后将达到的身价。</li>
			</ul>
		</div>
	</div>
	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/wealthCal.js"></script>





</body>
</html>
