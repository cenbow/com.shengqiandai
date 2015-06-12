<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
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
	href="/css/rechargeSuccess.css" />


<!--[if IE 6]>
         <script src="js/DD_belatedPNG_0.0.8a.js"></script> 
         <script>
         
          $(function(){
          
          DD_belatedPNG.fix('*');
          
          })
         
         </script> 
         
      <![endif]-->



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
							<h2 class="zhcz">充值成功</h2>
							<a href="${pageContext.request.contextPath }/account/recharge" class="goBack">返回</a>
						</div>

						<div class="sendEamil">


							<div class="send">
								<p id="finishVerify">
									充值提交成功！

								</p>
								<p id="sex">预计10分钟左右到账</p>

<p id="maxsex">您的充值流水号：888888888888</p>
<p><a class="look"href="${pageContext.request.contextPath }/user/account">查看我的账户</a></p>
							</div>
							<div class="goCheck"></div>


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

