<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="description"
	content="微积金(www.vfunding.cn) 整合了民营汽车借贷机构、二手车商、第三方汽车服务机构等传统领域企业，形成了独特的针对汽车提供投融资对接服务的创新型O2O互联网汽车金融服务模式。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、汽车贷款等。" />
<meta name="keywords"
	content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|微贷|红包|基金|微基金|小额贷款|微财富|宜车贷|二手车抵押|车贷|托管" />
<title>微积金，中国领先的互联网汽车金融服务平台_理财_理财产品_投融资_理财需求_投资_投资标的_财富_财富成长_手机理财_微信理财_微理财</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/returnFeeDetail.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/tip.css" />

<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/tip.min.js"></script>
<!-- 分页需要的CSS和JS -->

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/paging/style.css"
	media="screen" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/paging/jquery.paginate.js"></script>
 <script type="text/javascript" 
 	src="${pageContext.request.contextPath}/js/fp/rebateDetail.js"></script>




<script>
	$(function() {

		$(".fd-select dd").click(function() {

			$(this).addClass("sel").siblings().removeClass("sel");

		});
		$('.rank1,.rank2,.rank3 ').poshytip({
			className : 'tip-yellowsimple',
			showTimeout : 1,
			alignTo : 'target',
			alignX : 'center',
			offsetY : 8,
			allowTipHover : true
		});

	})
</script>
</head>





<body>
	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>
	<!--中间-->

	<div class="content">
		<div class="myAccount">
			<!-- 左边通用页面 -->
			<jsp:include
				page="${pageContext.request.contextPath }/user/accountLeft.jsp"></jsp:include>
			<!-- 中间页面 -->
			<div class="rechargeTakeCash fr">
				<div class="rT box-shadow">
					<div class="rTWrap">

						<div class="txTop">
							<b class="midLine"></b>
							<h2 class="zhcz">查看返利明细</h2>
							<p>您的待收返利已全部通过红包的形式发放到您的礼品盒中，理财师推荐返利规则已经升级成立返。赶快参与推荐领钱吧。</p>
							<a href="${pageContext.request.contextPath }/fp/toFpPage"
								class="goBack">返回</a>
						</div>
						<div class="returns">
							<table width="780" cellpadding="0" cellspacing="0">
								<tr>
									<td width="195" height="55" class="level">一层 <a
										class="rank1" title="直接好友投资所获现金奖励"></a>
									</td>
									<td width="195" height="35" class="level">二层 <a
										class="rank2" title="间隔一层关系好友投资所获现金奖励"></a>
									</td>
									<td width="195" height="35" class="level">三层 <a
										class="rank3" title="间隔二层关系好友投资所获现金奖励"></a>
									</td>
									<td width="195" height="35" class="level">合计</td>
								</tr>
								<tr>
									<td width="195" height="55">已返<span>${rebate.oneFees }</span>元
									</td>
									<td width="195" height="35">已返<span>${rebate.twoFees }</span>元
									</td>
									<td width="195" height="35">已返<span>${rebate.threeFees }</span>元
									</td>
									<td width="195" height="35">已返<span>${rebate.allFees }</span>元
									</td>
								</tr>

							</table>
						</div>
						<!-- <dl class="fd-select">
							<dt class="lx">类型</dt>
							<dd class="sib-lx sel">待返</dd>
							<dd class="sib-lx">已返</dd>
						</dl> -->

						 <div class="show-loading">
							<div class="loading"></div>
						</div>
						 <div class="fanliDetail">
							<table width="780" cellpadding="0" cellspacing="0"
								id="returnDetail">
								<tr>
									<td width="146" height="50" class="bt">返利日期</td>
									<td width="146" height="50" class="bt">投资日期</td>
									<td width="146" class="bt">返佣金额</td>
								</tr>
							</table>
							<input type="hidden" id="status" value="0"> <input
								type="hidden" id="pagCount" value="1">
							<div id="paging" style="display: none;"></div>

						</div> 
					</div>
				</div>
			</div>
		</div>
	</div>


	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
</body>
</html>
