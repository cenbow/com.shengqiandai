
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
	href="${pageContext.request.contextPath}/css/earningsContrast.css" />


<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/global-1.js"></script>
<script src="${pageContext.request.contextPath}/js/chosen.jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/highcharts.js"></script>

<script src="${pageContext.request.contextPath}/js/profit-contrast.js"></script>
<script>
	$(function() {
		$("#putLength").chosen();
	})
</script>

</head>

<body>


	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>



	<div id="wrapper" style="position: relative"  class="box-shadow">
		<div class="title-sub">
			<h2>收益对比器</h2>
			<b class="line"></b>

		</div>
		<div class="bid-opt profit-contrast">
			<ul class="items2" style="height:70px;">
				<li class="txt">投入金额：</li>
				<li><input type="text" id="putMoney" class="input"> <label>元</label>
					<div class="tip error" id="MoneyTip"
						style="left: -8px; top: 44px; display: none"></div></li>
			</ul>

			<div style="height: 3px; clear: both;"></div>
			<div class="pro_name fl">投入时长：</div>

			<div class="product fl">
				<select id="putLength">
					<option selected="selected">3</option>
					<option>6</option>
					<option>12</option>
					<option>18</option>
					<option>24</option>
					<option>36</option>
					<option>60</option>
				</select> <label>个月</label>

			</div>


			<div style="height: 1px; clear: both;"></div>

			<ul class="items2" style="height: 50px">
				<li class="txt">投入方式：</li>
				<li>
					<ul class="checkbox-list" id="reCastBox">
						<li><div class="yCheckbox">
								<span class="ico selected" id="futou"></span>
							</div>复投</li>
					</ul>
				</li>
			</ul>
			<ul class="items2" style="height: 130px">
				<li class="txt">对比产品：</li>
				<li>
					<ul class="checkbox-list" id="RepaymentBox"></ul>
				</li>
			</ul>
			<div class="btn-submit">
				<a href="javascript:;" id="submitButton" class="gbtn">计算</a> <a
					href="javascript:;" id="resetButton" class="gbtn gbtn-gray">重置</a>
			</div>
		</div>
		<div class="column-score model-box">
			<h2>对比结果</h2>
			<div id="columnChart" style="width: 490px; height: 290px"></div>
		</div>
		<div class="bid-info">
			<h2 style="font-size: 16px; color: #fc8936">温馨提示</h2>
			<ul class="items2">
				
				<li>1、银行活期收益根据当季央行公布活期利率进行计算；</li>
				<li>2、银行定期收益根据所选投入时长匹配相应定期产品后，根据当季央行公布的定期利率进行计算。若不选择复投，则只会进行一次投资计算；</li>
				<li>3、货币基金收益根据时长平均水平进行计算。</li>
			</ul>
		</div>
		<div class="bid-info">
			<h2>收益对比计算器简介</h2>
			<ul class="items2">
				<li>收益对比计算器用于查看您在微积金进行的投资和其他渠道进行投资所获得的收益对比，方便您选择更适合您的投资方式，获得最大收益。</li>
			</ul>
		</div>
	</div>



	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>


</body>
</html>
