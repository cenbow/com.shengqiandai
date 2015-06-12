<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="微积金(www.vfunding.cn) 整合了民营汽车借贷机构、二手车商、第三方汽车服务机构等传统领域企业，形成了独特的针对汽车提供投融资对接服务的创新型O2O互联网汽车金融服务模式。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、汽车贷款等。" />
<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|微贷|红包|基金|微基金|小额贷款|微财富|宜车贷|二手车抵押|车贷|托管" />
<title>微积金，中国领先的互联网汽车金融服务平台_理财_理财产品_投融资_理财需求_投资_投资标的_财富_财富成长_手机理财_微信理财_微理财</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/zyb_borrowList.css" />
	
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<!-- 分页需要的CSS和JS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/paging/style.css"
	media="screen" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/paging/jquery.paginate.js"></script>
<!-- 本页面js -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/week/weekBorrowList.js"></script>
</head>
<body>
	<!---头部--->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>
	<!--中间-->
	<div class="borrowList">
		<div class="bread">
			<a href="">首页></a> <a href="#">周盈宝投资列表</a>
		</div>
		<div class="zyb-banner box-shadow"></div>
		<diV class="zyb-detail box-shadow">
			<h2>产品详情</h2>
			“周盈宝”是“微积金”推出的一种创新型稳健理财产品。 其特点是：周期短、资金运用灵活，以汽车抵押／质押作为担保。
			自理财产品计划成立之日起每个认购开放日发行一个或多个子计划，每个子计划的投资周期均为：7天。 <span class="more">[更多...]</span>
			<span class="more-cnt"><br />
				认购起点：1元人民币为1份，认购起点份额为100份，认购份额为100份的整数倍递增。<br />
				收益起息日：采用T+1计息方式（即投资者认购后的下一个工作日为起息日）。注：投资者在不同时间段进行认购，起息时间可能会有所不同。
				到期日：起息日后的第7个自然日为各子计划到期日。<br />
				收益计算公式：投资者获得的理财收益金额=投资者认购本理财计划项下的某子计划总份额×投资者认购该子计划预期年化收益率×7÷365
				(金额精确到小数点后 2位)。如微积金提前终止该子计划，则实际理财天数为自该子计划成立日（含）至提前终止日（不含）期间的天数。
				例：投资者认购份额为10000份，预期年化收益率为9%，则投资者收益金额=10000×9%×7/365=17.26元。<br />
				投资者可以在理财产品开放时间内进行认购。投资者成功提交认购申请后，不得撤销申请：产品成立后，在产品到期前不得赎回。 </span> <span
				class="shouqi">[收起...]</span>
		</diV>
		<div class="borrowItems box-shadow">
			<div class="itemsWrap">
				<div class="itemsTitle clear">
					<h2 class="fl">周盈宝认购列表</h2>
				</div>
				<div class="borrowItem">
					<table width="960" cellpadding="0" cellspacing="0" id="borrowItem">
						<tr>
							<td width="260" height="56">借款标题&nbsp;</td>
							<td width="143">借款金额&nbsp;</td>
							<td width="140">期限&nbsp;</td>
							<td width="140">预期纯收益&nbsp;</td>
							<td width="111" style="padding-right: 40px;">进度&nbsp;</td>
							<td width="123">状态&nbsp;</td>
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
	<!-- scripts -->
	<script>
	    $(function(){
				/*
				产品介绍.更多
				*/
	            $("span.more").on("click",function(){
	                $(this).hide();
	
	                $("span.more-cnt,.shouqi").show();
	            });
	
	            $(".shouqi").on("click",function(){
	               $(this).hide();
	               $("span.more-cnt").hide();
	               $(".more").show();
	
	            });	
	    })
	</script>
</body>
</html>