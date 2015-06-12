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
	href="${pageContext.request.contextPath}/css/fresh.css" />
</head>
<body>

	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>

	<!--中间-->
	<div class="fresh-all">
		<div class="fresh">
			<div class="center">
				<div class="hd-notice">
					<div class="notice clear">
						<div class="child-notice">活动公告</div>
						<div class="aw-right"></div>
					</div>
				</div>
				<h3 class="hd-time">
					活动有效期至:-------<font>2015</font>年<font>6</font>月<font>30</font>日
				</h3>
				<p class="no-circle">
					<i class="circle">1</i> <span><a
						href="http://www.vfunding.cn">微积金</a>平台为了帮助您更全面地了解网站的各项功能，而设置了新手任务环节。</span>
				</p>
				
				<br /> 
				<p class="no-circle" id="no-circle">
					<i class="circle">2</i> <span>每项任务对应的礼品不同，完成项目越多，您所获得礼品也越多。</span>
				</p>
				<br/>
				
		        <p class="no-circle" id="no-circle">
					<i class="circle">3</i> <span>礼品使用规则：现金红包，发放于您的礼品盒红包中，可以直接使用增加账户余额。<br>提现抵扣券，发放于您的礼品盒提现抵扣券中，在用户提现时抵扣相应的手续费。</span>
				</p>
				<br/>
				
				<div class="hd-notice">
					<div class="notice clear">
						<div class="child-notice">新手任务</div>
						<div class="aw-right"></div>
					</div>
				</div>
				<div class="desk"></div>
				<div class="thinking"></div>
				<div class="task">
					<ul>
						<li class="task-pic t3">
							<div class="wrap">
								<a class="fresh-step"
									href="${pageContext.request.contextPath}/goRegister">注册送提现抵扣券</a>
								<c:choose>
									<c:when test="${not empty user}">
										<a class="reg"></a>
									</c:when>
									<c:when test="${ not empty loginedSession && empty user}">
										<a class="unfinished"></a>
									</c:when>
								</c:choose>
							</div>
							<p class="pp">加入vfunding，注册成为我们的会员，</p>
							<p>
								即可获得<span>5元 提现抵扣券</span>
							</p>
						</li>
						<li class="task-pic t3">
							<div class="wrap">
								<a class="fresh-step"
									href="${pageContext.request.contextPath}/user/realName">认证送提现抵扣券</a>
								<c:choose>
									<c:when test="${user.realStatus ==1}">
										<a class="reg"></a>
									</c:when>
									<c:when
										test="${not empty loginedSession &&  user.realStatus !=1}">
										<a class="unfinished"></a>
									</c:when>
								</c:choose>
							</div>
							<p class="pp">未认证用户实名认证完成，可获得</p>
							<p>
								<span>5元 提现抵扣券</span>
							</p>
						</li>
						<li class="task-pic t3">
							<div class="wrap">
								<a class="fresh-step"
									href="${pageContext.request.contextPath}/user/safeCenter">完善送提现抵扣券</a>

								<c:choose>
									<c:when test="${user.phoneStatus==1 and user.emailStatus ==1}">
										<a class="reg"></a>
									</c:when>
									<c:when
										test="${not empty loginedSession &&  user.emailStatus !=1}">
										<a class="unfinished"></a>
									</c:when>
								</c:choose>
							</div>
							<p class="pp">完成 邮箱认证，可获得</p>
							<p>
								<span>5元 提现抵扣券</span>
							</p>
						</li>

						<li class="task-pic t1">
							<div class="wrap">
								<a class="fresh-step"
									href="${pageContext.request.contextPath}/borrow/borrowList">投资送现金红包</a>

								<c:choose>
									<c:when test="${not empty tenders}">
										<a class="reg"></a>
									</c:when>
									<c:when test="${ not empty loginedSession && empty tenders}">
										<a class="unfinished"></a>
									</c:when>
								</c:choose>
							</div>
							<p class="pp">新手完成第一笔投资，开启互联网金融</p>
							<p>
								理财之旅，可获得<span>5元 现金红包</span>
							</p>
						</li>


						<li class="task-pic t1">
							<div class="wrap">
								<a class="fresh-step" href="/utilpage/app">APP投资送现金红包</a>
								<c:choose>
									<c:when test="${phoneTask ==1}">
										<a class="reg"></a>
									</c:when>
									<c:when test="${not empty loginedSession && phoneTask ==0 }">
										<a class="unfinished"></a>
									</c:when>
								</c:choose>

							</div>
							<p class="pp">下载APP并首笔投资超过2000元，</p>
							<p>
								可获得<span>5元 现金红包</span>
							</p>
						</li>


						<li class="task-pic t3">
							<div class="wrap">
								<a class="fresh-step"
									href="${pageContext.request.contextPath }/friend/myFriend">邀请送提现抵扣券</a>
								<c:if test="${not empty loginedSession}">
									<a class="reging"></a>
								</c:if>
							</div>
							<p class="pp">
								每邀请一位好友投资超过100元，
							</p>
							<p>可获得<span>5元 提现抵扣券</span>，累计推荐有效</p>
						</li>

						<li class="task-pic t3">
							<div class="wrap">
								<a class="fresh-step" href="/borrowAuto/borrowAuto">自动投标送提现抵扣券</a>
								<c:choose>
									<c:when test="${autoTask ==1}">
										<a class="reg"></a>
									</c:when>
									<c:when test="${not empty loginedSession && autoTask ==0 }">
										<a class="unfinished"></a>
									</c:when>
								</c:choose>
							</div>
							<p class="pp">设置自动投标并完成第一笔自动投标</p>
							<p>
								投资，可获得 <span>5元 提现抵扣券</span>
							</p>
						</li>

						<li style="width: 264px; height: 125px;"><img
							src="/images/huodong/hd2.png" /></li>

					</ul>
				</div>

			</div>
		</div>

	</div>

	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>

</body>
</html>