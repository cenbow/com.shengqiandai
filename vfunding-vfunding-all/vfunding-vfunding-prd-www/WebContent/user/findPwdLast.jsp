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
	href="${pageContext.request.contextPath}/css/findPwd4.css" />
</head>

<body>
	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>

	<!--中间-->

	<div class="findPwd">
		<div class="b-line">
			<h2>找回密码</h2>
			<div class="h-line"></div>
		</div>

		<div class="find-steps">
			<ol class="reg_step">
				<li class="step1"><span class="step_msg"> <i
						class="icons dot24">1</i> <span class="whiteColor">输入账户名</span>
				</span></li>
				<li class="step2"><span class="step_msg"> <i
						class="icons dot24">2</i> <span class="whiteColor"> 验证账户名</span>
				</span></li>


				<li class="step3"><span class="step_msg"> <i
						class="icons dot24">3</i> <span class="whiteColor">重置密码</span>
				</span></li>



				<li class="step4"><span class="step_msg"> <i
						class="icons dot24">4</i> <span class="whiteColor">完成</span>
				</span></li>
			</ol>

		</div>

		<div class="pass-through">
			<p>恭喜你，密码修改成功，</p>

			<p>现在你可以使用新密码登录微积金了！</p>
		</div>


		<div class="go-account">
			<a href="http://www.vfunding.cn/user/findPwdGoLogin" class="btn116">立即登录</a> <a href="http://www.vfunding.cn/user/findPwdToSafeCenter" class="safe-center">进入安全中心</a>
		</div>

	</div>

	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
</body>
</html>


