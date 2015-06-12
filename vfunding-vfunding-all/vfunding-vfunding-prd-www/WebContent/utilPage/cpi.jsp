
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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/chosen.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/tools.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/cpi.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>

<script src="${pageContext.request.contextPath}/js/global-1.js"></script>
<script src="${pageContext.request.contextPath}/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/js/cpi.js"></script>
</head>
<body>


	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>

	<!--中间-->


	<div id="wrapper" class="box-shadow">
		<div class="title-sub">
			<h2>CPI跟踪器</h2>
			<b class="line"></b>
			<div class="sharing-tools">


				<div class="jiathis_style" id="shareshell" style="display: none">
					<span
						style="float: left; display: inline; font-size: 14px; padding-top: 1px; height: 16px; line-height: 16px">分享到：</span><a
						class="jiathis_button_tsina"></a><a class="jiathis_button_weixin"></a>
					<a class="jiathis_button_qzone"></a> <a class="jiathis_button_tqq"></a>
					<a class="jiathis_button_renren"></a> <a
						href="javascript:if(confirm('http://www.jiathis.com/share  \n\n���ļ��޷��� Teleport Ultra ����, ��Ϊ ����һ�����·���ⲿ������Ϊ������ʼ��ַ�ĵ�ַ��  \n\n�����ڷ������ϴ���?'))window.location='http://www.jiathis.com/share'"
						tppabs="http://www.jiathis.com/share"
						class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
				</div>
				<script type="text/javascript" id="shareshell_js" charset="utf-8"></script>
				<script type="text/javascript">
					setTimeout(
							function() {
								document.getElementById("shareshell_js").src = "http://v3.jiathis.com/code/jia.js?var="
										+ Math.ceil(new Date() / 3600000);
								document.getElementById("shareshell").style.display = 'block';
							}, 1000)
				</script>
			</div>
		</div>
		<div class="bid-opt">
			<ul class="items2">
				<li class="txt">当前财富：</li>
				<li><input type="text" id="myAsset" class="input"> <label>元</label>
					<span class="tip" id="assetTip" style="display: none"></span></li>
			</ul>
			<ul class="items2">
				<li class="txt">计算时长：</li>
				<li><select id="CountLength">
						<option selected="selected">1</option>
						<option>2</option>
						<option>5</option>
						<option>10</option>
						<option>20</option>
						<option>50</option>
				</select> <label>年</label></li>
			</ul>
			<div class="btn-submit">
				<a href="javascript:;" class="gbtn btn78" id="submitButton"
					style="padding: 0 20px">计算</a> <a href="javascript:;"
					id="resetButton" class="gbtn gbtn-gray btn78"
					style="padding: 0 20px">重置</a>
			</div>
		</div>
		<div class="cpi-result">
			<div id="CPIResult"></div>
			<h2>CPI同比涨跌幅</h2>
			<div id="CPIResultChart" style="height: 260px; margin-top: -20px"></div>
			<p style="color: #999; padding: 5px 0 25px 25px; font-size: 12px">数据来源：国家统计局</p>
		</div>
		<div class="bid-info">
			<h2>CPI跟踪器</h2>
			<ul class="items2">
				<li>CPI跟踪器为您展示CPI指数趋势图。如果想财富不随着时间而贬值，请选择合适的投资理财方式。</li>
			</ul>
		</div>
	</div>

	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>


</body>
</html>
