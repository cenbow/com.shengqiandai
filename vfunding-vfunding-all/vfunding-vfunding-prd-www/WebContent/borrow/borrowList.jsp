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
<title>我要投资_微积金_中国领先的O2O互联网汽车金融平台</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/borrowList.css" />

<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/borrow/borrowList.js"></script>


<!-- 分页需要的CSS和JS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/paging/style.css" media="screen" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/paging/jquery.paginate.js"></script>
<script>
	$(function() {

		$(".condition li").click(function() {

			$(this).addClass("slected").siblings().removeClass("slected");

		});

		$("#condition2 li").filter("li:gt(6)").hide();

		$("#show-more").click(function() {

			$("#condition2 li").filter("li:gt(6)").toggle();

		});

	})
</script>


</head>
<body>
	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>
	<!--中间-->

	<div class="borrowList" style="position:relative;">

		<div class="bread">
			<a href="${pageContext.request.contextPath}">首页></a><a href="${pageContext.request.contextPath}/borrow/borrowList">我要投资></a> <a>投资列表</a>
		</div>
		<div class="listConditions clear box-shadow">
			<div class="condition-left fl">

				<div class="condition" id="condition1">
					<a>投资类型</a>
					<ul>
						<li class="slected">全部</li>
						<li>抵押标</li>
					<!-- <li>体验标</li>-->
						<li>活动标</li>
					</ul>
				</div>
				<input type="hidden" id="biaoType" value="">
				<div class="cd2 clear">
					<div class="condition" id="condition2">
						<a>投资期限</a>
						<ul>
							<li class="slected alls">全部</li>
							<li>1个月</li>
							<li>2个月</li>
							<li>3个月</li>
							<li>4个月</li>
							<li>5个月</li>
							<li>6个月</li>
							<li id="month9">9个月</li>
							<li id="mb20">12个月</li>
							<li id="mb20">大于12个月</li>
						</ul>
					</div>
					<div id="show-more"></div>
				</div>
				<input type="hidden" id="timeLimit" value="">
				<div class="condition" id="condition3">
					<a>还款方式</a>
					<ul>
						<li class="slected alls">全部</li>
						<li>等额本息</li>
						<li>到期还款</li>
						<li>先息后本</li>
						<li>等本等息</li>
					</ul>

				</div>
				<input type="hidden" id="style" value="">
				<div class="condition" id="condition4">
					<a>招标状态</a>
					<ul>
						<li class="slected alls">全部</li>
						<li>正在招标</li>
						<li>满标待审</li>
						<li>成功借款</li>
					</ul>
				</div>
				<input type="hidden" id="status" value="">
			</div>
			 <div class="condition-right fr">

				<div class="guide">新手引导</div>

				<div class="guideItem">
					<ul>
						<li><a href="${pageContext.request.contextPath}/utilpage/aboutUs" target="_blank">什么是微积金？</a></li>
						<li><a href="${pageContext.request.contextPath }/utilpage/aboutUs#security" target="_blank">微积金安全保障体系是什么?</a></li>
						<li><a href="${pageContext.request.contextPath}/utilpage/helpCenter#fee" target="_blank">微积金网站资费？</a></li>
						<li><a href="${pageContext.request.contextPath}/utilpage/helpCenter" target="_blank">常见问题？</a></li>
					</ul>
				</div>

			</div> 
		</div>


		<div class="borrowItems box-shadow">
			<div class="itemsWrap">

				<div class="itemsTitle clear">
					<h2 class="fl" id="topAnchor">投资列表</h2>
					<p class="fr deal">昨日成交金额 ￥${yesterdaySumBorrowAccount }</p>
				</div>


				<div class="borrowItem">
					<table width="960" cellpadding="0" cellspacing="0" id="borrowItem">
						<tr>
							<td width="372" height="56">借款标题&nbsp;</td>
						<!-- 	<td width="112">担保保障&nbsp;</td> -->
							<td width="143">借款金额&nbsp;</td>
							<td width="100">期限&nbsp;</td>
							<td width="109">预期纯收益&nbsp;</td>
							<td width="110">进度&nbsp;</td>
							<td width="117">状态&nbsp;</td>
						</tr>
						<tr >
						<td colspan="7">无记录</td>
						</tr>
					</table>
				</div>
				<input type="hidden" id="pagCount" value="1">
				<div id="paging"></div>


			</div>

		</div>

	</div>
	
	
	
	
	
	
	
	
	
	
	
	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>

</body>
</html>
