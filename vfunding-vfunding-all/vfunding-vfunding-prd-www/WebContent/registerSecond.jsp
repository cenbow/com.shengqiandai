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
	href="${pageContext.request.contextPath}/css/ui-form.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/phone_vefify.css" />
<script type="text/javascript">
	var rootPath = '${pageContext.request.contextPath}';
</script>
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/phone_verify.js"></script>
<script src="${pageContext.request.contextPath}/js/getCodeForRegister.js"></script>
</head>
<body>

	<!-------------头部-------------------->
	<jsp:include page="top.jsp"></jsp:include>

	<!--中间-->


	<div class="register2 box-shadow">

		<div class="reg_content">

			<div class="reg_logo">
				<h1 class="reg_logo_pic"></h1>
				<a href="${pageContext.request.contextPath}/goLogin"
					class="go_login">已经注册，直接点击这里登录</a>
			</div>
			<div class="reg_conditions">

				<ol class="reg_step">
					<li class="step1"><span class="step_msg"> <i
							class="icons dot24">1</i> <span class="whiteColor">填写注册信息</span>
					</span></li>
					<li class="step2"><span class="step_msg"> <i
							class="icons dot24">2</i> <span class="whiteColor">手机验证</span>
					</span></li>
					<li class="step3"><span class="step_msg"> <i
							class="icons dot24">3</i> 注册成功
					</span></li>
				</ol>

				<div class="form_validator">

					<form action="/register" method="post" class="ui-form"
						id="phoneForm">
						<fieldset>
							<div class="ui-form-item">

								<label class="ui-label"> <span class="ui-form-required">*</span>
									手机号码：
								</label> <input type="text" class="ui-input pl60 us_bg" name="mobile"
									id="mobile" value="${register.mobile}" />
								<p class="input_tip">请输入手机号码</p>

							</div>
							<div class="ui-form-item check_code">

								<label class="ui-label"> <span class="ui-form-required">*</span>
									验证码：
								</label> <input type="text" class="ui-input pl60 us_bg" name="vcode"
									value="${register.vcode}" id="vcode" />


								<p class="input_tip">请输入验证码</p>

							</div>
							<div class="ui-form-item" style="height: 30px;">

								<a href="javascript:getPhoneCode('mobile');" id="phoneCode"
									class="getPhoneCode">短信获取</a> <a href="javascript:;"
									id="voiceCode" class="getVoiceCode"
									style="background-color: #EEEEEE">语音获取</a> <input type="hidden"
									name="userName" value="${register.userName }" /> <input
									type="hidden" name="password" value="${register.password }" />
								<input type="hidden" name="rePassword"
									value="${register.rePassword }" /> <input type="hidden"
									name="introducer" value="${register.introducer }" />

							</div>
							<div class="ui-form-item" style="height: 30px;">
								<div id="waitMsg"
									style="position: absolute; top: 8px; left: 170px; color: #888888;"></div>
							</div>
							<c:if test="${not empty verificationErrorMsg }">
								<div class="ui-form-item" style="height: 25px;">
									<div
										style="height: 25px; position: absolute; top: 0px; left: 170px; color: red;">${verificationErrorMsg }</div>
								</div>
							</c:if>

							<div class="ui-form-item">

								<input type="submit" class="submit_btn" hidefocus="true"
									value="下一步">

							</div>



							<div class="verifyTip">
								如您在1分钟内没有收到短信，请<a href="#">返回修改手机号</a>是否填写正确或使用语音验证或联系我们的客服
							</div>

						</fieldset>


					</form>


				</div>

			</div>
		</div>
	</div>
	<!------尾部------>
	<jsp:include page="footer.jsp"></jsp:include></body>

</body>
</html>

