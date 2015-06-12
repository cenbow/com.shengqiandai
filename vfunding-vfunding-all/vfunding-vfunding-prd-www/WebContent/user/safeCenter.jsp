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
	href="${pageContext.request.contextPath}/css/safeCenter.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/myAccount.js"></script>
</head>

<body>
	<!-----头部------->
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
							<h2>安全中心</h2>
						</div>

						<div class="safeStrength">

							<span class="anquanDj fl">你的账户安全等级：<font>${safeStr }</font></span>
							<div class="aqBar fr">

								<div class="aqProgreessor"
									style="width: ${percentStr };  
									      <c:if test="${safeStr=='低' }">
					                        background:#ff5555 ;</c:if>
										  <c:if test="${safeStr=='中' }">
											background:#ffc730 ;
										   </c:if>
											<c:if test="${safeStr=='高' }">
											    background:#8bd668 ;
											</c:if>">

								</div>

							</div>


						</div>


						<div class="allSafe">

							<div class="safeItem">
								<div class="safekind">
									<ul>

										<c:choose>
											<c:when test="${loginedSession.realStatus != '1' }">
												<li class="tipicon sf1 sf4">实名认证</li>
											</c:when>
											<c:otherwise>
												<li class="tipicon sf1">实名认证</li>
											</c:otherwise>
										</c:choose>

										<li class="sf2">充值前，必须完成实名认证</li>

										<c:choose>
											<c:when test="${loginedSession.realStatus != '1' }">
												<li class="sf3"><a
													href="${pageContext.request.contextPath }/user/realName">实名认证</a></li>
											</c:when>
											<c:otherwise>
												<li class="sf3">已认证</li>
											</c:otherwise>
										</c:choose>
									</ul>
								</div>
							</div>


							<div class="safeItem">
								<div class="safekind">
									<ul>
										<li class="tipicon sf1">登录密码</li>
										<li class="sf2">保障登录安全，建议您定期更换密码，提高账户安全保障</li>
										<li class="sf3"><a
											href="${pageContext.request.contextPath }/user/updatePassword">更改</a></li>
									</ul>
								</div>
							</div>


							<div class="safeItem">
								<div class="safekind">
									<ul>
										<c:choose>
											<c:when
												test="${loginedSession.payPassword ==  loginedSession.password}">
												<li class="tipicon sf1 sf4">支付密码</li>
											</c:when>
											<c:otherwise>
												<li class="tipicon sf1">支付密码</li>
											</c:otherwise>
										</c:choose>

										<li class="sf2">保障资金安全，也是申请提现的前提条件，建议您支付密码区别于登录密码</li>
										<li class="sf3"><c:choose>
												<c:when
													test="${loginedSession.payPassword ==  loginedSession.password}">
													<a
														href="${pageContext.request.contextPath }/user/updatePayPassword">设置</a>
												</c:when>
												<c:otherwise>
													<a
														href="${pageContext.request.contextPath }/user/updatePayPassword">更改</a>
														|&nbsp;<a
														href="${pageContext.request.contextPath }/user/findPayPasswordPage">找回</a>
												</c:otherwise>
											</c:choose></li>
									</ul>

								</div>
							</div>


							<div class="safeItem">
								<div class="safekind">
									<ul>
										<c:choose>
											<c:when test="${loginedSession.phoneStatus ==  '1'}">
												<li class="tipicon sf1">手机认证</li>
											</c:when>
											<c:otherwise>
												<li class="tipicon sf1 sf4">手机认证</li>
											</c:otherwise>
										</c:choose>


										<li class="sf2">绑定手机号码，账户资金变动实时通知</li>
										<li class="sf3"><c:choose>
												<c:when test="${loginedSession.phoneStatus ==  '1'}">
													<a
														href="${pageContext.request.contextPath }/user/updatePhone">更改</a>
												</c:when>
												<c:otherwise>
													<a
														href="${pageContext.request.contextPath }/user/checkPhoneState">认证</a>
												</c:otherwise>
											</c:choose></li>

									</ul>
								</div>
							</div>


							<div class="safeItem">

								<div class="safekind">
									<ul>

										<c:choose>
											<c:when test="${emailStatus == '1' }">
												<li class="tipicon sf1">邮箱认证</li>
											</c:when>
											<c:otherwise>
												<li class="tipicon sf1 sf4">邮箱认证</li>
											</c:otherwise>
										</c:choose>

										<li class="sf2">每月接收电子账单，查看收支明细；还可通过邮箱登录微积金、找回密码</li>
										<li class="sf3"><c:choose>
												<c:when test="${emailStatus == '1' }">
													<a href="${pageContext.request.contextPath }/user/oldEmail">更换</a>
												</c:when>
												<c:when test="${empty loginedSession.email}">
													<a
														href="${pageContext.request.contextPath }/user/checkEmail">添加</a>
												</c:when>
												<c:otherwise>
													<a
														href="${pageContext.request.contextPath }/user/checkEmail">认证</a>
														|&nbsp;<a
														href="${pageContext.request.contextPath }/user/modifyEmailPage">修改</a>
												</c:otherwise>
											</c:choose></li>
									</ul>

								</div>

							</div>

<%-- 							<div class="safeItem">

								<div class="safekind">
									<ul>
										<c:choose>
											<c:when test="${loginedSession.vipStatus != 1 }">
												<li class="tipicon sf1 sf4">VIP认证</li>
											</c:when>
											<c:otherwise>
												<li class="tipicon sf1">VIP认证</li>
											</c:otherwise>
										</c:choose>

										<li class="sf2">本息保障，更享有微积金提供的个人尊享及个性化服务</li>
										<li class="sf3"><c:choose>
												<c:when test="${loginedSession.vipStatus != 1 }">
													<a href="${pageContext.request.contextPath }/user/applyVIP">申请</a>
												</c:when>
												<c:otherwise>
													已认证
												</c:otherwise>
											</c:choose></li>
									</ul>

								</div>

							</div> --%>

							<div class="safeItem">

								<div class="safekind">
									<ul>
										<c:choose>
											<c:when test="${not empty loginedSession.question }">
												<li class="tipicon sf1">密保问题</li>
											</c:when>
											<c:otherwise>
												<li class="tipicon sf1 sf4">密保问题</li>
											</c:otherwise>
										</c:choose>

										<li class="sf2">保障隐私安全，也是更改个人信息、快速找回密码的重要途径</li>
										<li class="sf3"><c:choose>
												<c:when test="${not empty loginedSession.question }">
													<a href="${pageContext.request.contextPath }/user/question">更改</a>
												</c:when>
												<c:otherwise>
													<a href="${pageContext.request.contextPath }/user/question">设置</a>
												</c:otherwise>
											</c:choose></li>
									</ul>

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
</body>
</html>


