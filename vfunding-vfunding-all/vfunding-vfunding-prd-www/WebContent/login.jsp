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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ui-form.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/messages_zh.js"></script>
<script src="${pageContext.request.contextPath}/js/login.js"></script>


</head>

<body>
	<!-------------头部-------------------->
	<jsp:include page="top.jsp"></jsp:include>

	<!-------------中间-------------------->

	<div class="login_bg">
		<div class="login_sector">
			<input type="hidden" id="showController" value="${showVerifyCode }">
			
			
           <a class="loginbg"  href="${pageContext.request.contextPath}/fresh"></a>
           
           
			<div class="login_box login_box_bg">
				<div class="loginpic"></div>
				<form action="#" id="loginForm" class="ui-form" method="post">

					<div class="ui-form-item loginName">
						<label class="ui-label"> <span class="ui-form-required"></span>
							用户名：
						</label> <input type="text" class="ui-input" name="username" id="username" />
						<p class="ui-form-item-placeholder placeholder keyuname">用户名/手机/邮箱</p>
					</div>


					<div class="ui-form-item loginName mt20">
						<label class="ui-label"> <span class="ui-form-required"></span>
							密码：
						</label> <input type="password" class="ui-input password" name="password"
							id="password" /> <input type="hidden" id="returnUrl"
							value="${returnUrl }" />
						<p class="ui-form-item-placeholder placeholder keyuPwd">请输入密码</p>
						<input type="hidden" id="errorNum" value="1">

					</div>

					<div class="ui-form-item loginName codeBox mt20"
						style="display: none;">

						<label class="ui-label"> <span class="ui-form-required"></span>
							验证码：
						</label> <input type="text" class="ui-checkcode-input authCode"
							name="vcode" id="authCode" />
						<p class="ui-form-item-placeholder placeholder">验证码</p>
						<a href="javascript:changeImage();"> <img id="authCodeImg"
							alt="看不清?换一张"
							src="${pageContext.request.contextPath}/verification/getGenImage/68/32">
						</a>


					</div>


					<div class="login_tip"></div>
					<div class="ui-form-item loginName submitBtn">


						<input type="button" id="submit_btn" value=""
							class="submit-btn-bg" />


					</div>


					<div class="ui-form-item q-wb" style="padding: 0; margin: 0;">


						<div class="qq-wb">

							<a href="http://www.vfunding.cn/thirdLogin/qqLogin"
								class="qqwb_bg qq"> QQ登录 </a> <a
								href="http://www.vfunding.cn/thirdLogin/sinaLogin"
								class="qqwb_bg sina_wb">微博登录</a>

						</div>

					</div>

					<div class="ui-form-item login_tip2">

						<span class="forget_reg"><a
							href="${pageContext.request.contextPath}/user/findPwdFirst" target="_blank">忘记密码？</a><a
							href="${pageContext.request.contextPath}/goRegister" target="_blank">免费注册</a></span>

					</div>
				</form>
			</div>

		</div>
	</div>
	<!------尾部------>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
