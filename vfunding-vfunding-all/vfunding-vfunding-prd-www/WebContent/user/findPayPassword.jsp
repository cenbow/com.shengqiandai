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
	href="${pageContext.request.contextPath}/css/findPayPwd.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
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
				<div class="rT box-shadow">
					<div class="rTWrap">

						<div class="txTop">
							<b class="midLine"></b>
							<h2 class="zhcz">重置支付密码</h2>
							<a href="${pageContext.request.contextPath }/user/safeCenter" class="goBack">返回</a>
						</div>

						<p class="suggest">基于对您账户信息的检测，我们提供以下方式供您重置支付密码：</p>


						<div class="through-type">
							<ul>
								<li class="bottom-border">
									<h3>通过密保问题找回密码</h3>
									<p>如果您还记得您设置的密保问题以及问题的答案，请选择此方式</p> <a href="${pageContext.request.contextPath }/user/findPayPwdByQuestion" class="btn116">立即找回</a>
								</li>
						    <li class="bottom-border">
									<h3>通过身份证+手机验证找回密码</h3>
									<p>如果您忘记了密保问题，可通过身份证号码验证和手机验证找回您的支付验证码。</p> <a href="${pageContext.request.contextPath }/user/findPayPwdByPhone" class="btn116">立即找回</a>
								</li>
								
								<li>
									<h3>通过人工服务找回密码</h3>
									<p>您可以通过邮件将您的信息发送至 service@vfunding.cn
										申请重置支付密码(处理限为2个工作日)，请您在邮件中提供：姓名、用户名、身份证正反面照片或影印件(需注明"仅供微积金重置支付密码使用")</p>

								</li>
							</ul>
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


