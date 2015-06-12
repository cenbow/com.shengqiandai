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
	href="${pageContext.request.contextPath}/css/modifyNewPhone.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/safeCenter.js"></script>

<script src="${pageContext.request.contextPath}/js/getCodeByNewPhone.js"></script>
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
							<h2 class="zhcz">修改手机</h2>
							<a href="${pageContext.request.contextPath }/user/safe"
								class="goBack">返回</a>
						</div>

						<ol class="reg_step">
							<li class="step1"><span class="step_msg"> <i
									class="icons dot24">1</i> <span class="whiteColor">核实原手机号码</span>
							</span></li>
							<li class="step2"><span class="step_msg"> <i
									class="icons dot24">2</i><span class="whiteColor">
										输入新手机号码</span>
							</span></li>
							<li class="step3"><span class="step_msg"> <i
									class="icons dot24">3</i> 完 成
							</span></li>
						</ol>




						<div class="newPhone">

							<form action="${pageContext.request.contextPath }/user/saveNewPhone" name="newPhone" id="newPhone" method="post">

								<div class="ui-form-item">
									<label class="ui-label">手机号码：</label> <input type="text"
										class="ui-input" name="phone" id="mobile" />
								</div>

								<div class="ui-form-item">
									<a href="javascript:getCodeByPhone('mobile');"
										id="phoneCode" class="mobileCheck">短信获取</a> <a
										href="javascript:;" id="voiceCode" class="voiceCheck"
										style="background-color: #EEEEEE">语音获取</a>


								</div>

								<div class="ui-form-item" style="height: 5px;">
									<div id="waitMsg" style="color: #888888;"></div>
								</div>

								<div class="ui-form-item">
									<label class="ui-label">验证码：</label> <input type="text"
										id="newCheckCode" name="vcode" class="ui-input">
								</div>


								<c:if test="${not empty errorMsg }">
									<div class="ui-form-item" id="errorMsg"
										style="height: 0px; color: red; margin-top: 0px;">${errorMsg }</div>
								</c:if>


								<div class="ui-form-item">
									<p class="gaozhi">1分钟内未收到校验码短信，请点击语音验证码 </p>
									<p>微积金将通过 4009919999 官方电话拨打您手机 , 播报语音验证码 。</p>
									<div style="margin-top:28px;">
										<input type="submit" class="nextStep" value="下一步"> <span><a
											href="${pageContext.request.contextPath }/user/updatePhone" class="otherType">选择其他方式找回>></a></span>
									</div>

								</div>

							</form>
  
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


