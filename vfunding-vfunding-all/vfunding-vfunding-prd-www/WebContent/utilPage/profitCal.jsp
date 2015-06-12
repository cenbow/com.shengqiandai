
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

<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/chosen.jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/global-1.js"></script>
<script src="${pageContext.request.contextPath}/js/profitCal.js"></script>

<script>
	$(function() {

		$("#product,#repayType,#planType,#profitDis").chosen();

	})
</script>



</head>

<body>
	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>


	<!--中间-->


	<div id="wrapper" class="box-shadow">
		<div class="title-sub">
			<h2>收益计算器</h2>
			<b class="line"></b>
			<div class="sharing-tools"></div>
		</div>


		<div class="bid-opt profit-calc">
			<div class="pro_name fl">投资产品：</div>

			<div class="product fl">

				<select id="product" name="product">
					<option value="loan">投资标</option>
					<!-- <option value="plan">定期宝</option> -->
				</select>

			</div>

			<div style="height: 1px; clear: both;"></div>

			<ul class="items2">
				<li class="txt">投入金额：</li>
				<li><input type="text" class="input" id="investAmount">
					<label>元</label> <span style="display: none;" id="investAmountTip"
					class="tip error"><i class="icons reg-error"></i>该数值须为不小于200元的整数</span>




				</li>
			</ul>
			<div style="display: block;" class="clearfix" id="loanArea">
				<ul class="items2">
					<li class="txt">投入时长：</li>
					<li><input type="text" class="input" id="investTime">
						<label>个月</label> <span style="display: none;" id="investTimeTip"
						class="tip error"></span></li>
				</ul>
				<ul class="items2">
					<li class="txt">年化利率：</li>
					<li><input type="text" value="12" class="input" id="anun">
						<label>%</label> <span id="anunTip" class="tip error"
						style="display: none;"></span></li>
				</ul>

				<div class="pro_name fl">还款方式：</div>
				<div class="product fl">
					<select id="repayType">
						<option value="1">等额本息</option>
						<option value="3">先息后本</option>
						<option value="4">到期还款</option>
						<option value="5">等本等息</option>
					</select>
				</div>


				<ul class="items2">
					<li class="txt"></li>
					<li><span style="display: none;" id="repayTypeTip" class="tip">这部分金额不会加入自动投标</span>
					</li>
				</ul>

			</div>




			<div style="display: none;" class="clearfix" id="planArea">

				<div class="pro_name fl">定存宝类型：</div>
				<div class="product fl">

					<select id="planType">
						<option value="a">A（3月期）</option>
						<option value="b">B（6月期）</option>
						<option value="c">C（12月期）</option>
					</select>

				</div>

				<ul class="items2">
					<li class="txt"></li>
					<li><span id="planTypeTip" class="tip"></span></li>
				</ul>




				<div class="pro_name fl">利息处理方式：</div>
				<div class="product fl">

					<select id="profitDis">
						<option value="1">利益复投</option>
						<option value="2">收益返还</option>
					</select>

				</div>


				<div style="height: 1px; clear: both"></div>


			</div>
			<div class="btn-submit">
				<a id="calcBt" class="gbtn btn78" href="javascript:;">计算</a> <a
					id="resetBt" class="gbtn gbtn-gray btn78" href="javascript:;">重置</a>
			</div>
		</div>



		<div class="calResult">
			<div class="r1 fl">
				<h3>计算结果</h3>
			</div>
			<div class="r2 fr">

				<div class="benxi fl">

					<p>本息合计（到期收益）</p>
					<div class="benxiNum">
						<span id="earnBxNum">0</span>元
					</div>

				</div>

				<div class="lixi fl">

					<p>利息收入（到期收益）</p>

					<div class="benxiNum">
						<span id="lxNum">0</span>元
					</div>

				</div>


				<div class="monthIncome fl">

					<p>每月收款</p>
					<div class="benxiNum">
						<span id="earnBxNumMonth">0</span>元
					</div>

				</div>

			</div>

		</div>
		<div class="model-box colle-time" id="loanTable">
			<div class="title">
				<h2>本息收款时间表</h2>
			</div>
			<div id="loanTableBody"></div>
		</div>


		<div style="display: none;" id="planTable"
			class="model-box colle-time">
			<div class="title">
				<h2>本息收款时间表</h2>
			</div>
			<div id="planTableBody"></div>
		</div>

		<div style="border: 0" class="bid-info">
			<h2>收益计算器简介</h2>
			<ul class="items">
				<li>收益计算器用于计算您在微积金投资将会获得的收益，理财更加透明高效，固定日还款项目由于计息方式略有不同，具体收益请参见投资后的统计。</li>
			</ul>
		</div>
	</div>
	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
		<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/profitCal.css" />
</body>
</html>
