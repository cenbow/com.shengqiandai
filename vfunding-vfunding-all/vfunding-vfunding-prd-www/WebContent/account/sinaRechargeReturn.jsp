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
<link type="text/css" rel="stylesheet" href="/css/txSucceed.css"/>	
<link type="text/css" rel="stylesheet" href="/css/rechargeStatus.css"/>	
<script src="/js/jquery1.8.3.js"></script>

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

			<div class="rechargeTakeCash fr">
				<div class="rT">
					<div class="rTWrap">

						<div class="txTop">
							<b class="midLine"></b>
							<h2 class="zhcz">充值申请</h2>
							<a href="${pageContext.request.contextPath }/user/account" class="goBack">返回</a>
						</div>
						<div style="display:none">
							<span id="tm" >${tradeMsg}</span>
						</div>
						<div id="mainCenter_recharge">
							<div id="Gell">
									<div id="mainCenter_recharge_img1" style="display:none">
										<a class="img"><img width="58" height="58"
											src="/images/recharge/success.gif" /></a>
										<div id="mainCenter_recharge_text1"
											class="mainCenter_recharge_text">
											<h1 class="msg_h">${tradeMsg}</h1>
											<p>您的充值流水号：${tradeNo}</p>
											<a class="ZH" href="/user/account">查看我的账户</a>
										</div>
									</div>
									<div id="mainCenter_recharge_img2" style="display:none">
										<a class="img"><img width="58" height="58"
											src="/images/recharge/error.gif" /></a>
										<div id="mainCenter_recharge_text2"
											class="mainCenter_recharge_text">
											<h1 class="msg_h">${tradeMsg}</h1>
											<p>您的充值流水号：${tradeNo}</p>
											<a class="ZH" href="/user/account">查看我的账户</a>
										</div>
									</div>
									<div id="mainCenter_recharge_wait" style="display:none">
										<a class="img1"><img width="70" height="58"
											src="/images/recharge/wait.gif" /></a> <a class="zh"><h1 class="msg_h">${tradeMsg}</h1></a>
											<br/>
											<p>您的充值流水号：${tradeNo},预计10分钟左右到账</p>
									</div>
								</div>
							</div>


					</div>
				</div>
			</div>

		</div>
	</div>

	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
	<input type="hidden" id="tradeMsg" value="${tradeMsg}"></input>
	<input type="hidden" id="tradeNo" value="${tradeNo}"></input>
	<script type="text/javascript" >
		function query(){
			if($("#tradeMsg").val()=="充值等待"){
				var url = "/account/sinaRechargeReturnAjax/"+$("#tradeNo").val();
				$.ajax({
					url : url,
					async : false,
					cache : false,
					type : 'get',
					success : function(data) {
						data = $.parseJSON(data);	
						$("#tm").html(data.msg);
						divShow();
					},
					contentType : "application/x-www-form-urlencoded;charset=UTF-8"
				});
				if($("#tm").html()=="充值成功"){
					location.href = location.href;
				}
			}else{
				window.clearInterval(interval1);
			}			
		}
		function divShow(){
			var msg = $("#tm").html();
			$(".msg_h").html(msg);
			if(msg=="充值等待"){
				$("#mainCenter_recharge_wait").show();
				$("#mainCenter_recharge_img1").hide();
				$("#mainCenter_recharge_img2").hide();
			}else if(msg=="充值成功"){
				$("#mainCenter_recharge_wait").hide();
				$("#mainCenter_recharge_img1").show();
				$("#mainCenter_recharge_img2").hide();
			}else{
				//充值失败
				$("#mainCenter_recharge_wait").hide();
				$("#mainCenter_recharge_img1").hide();
				$("#mainCenter_recharge_img2").show();
			}
		}
		divShow();
		var interval1=window.setInterval("query()",2000);
	</script>
</body>
</html>


