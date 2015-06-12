<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="header">
	<c:if test="${empty loginedSession }">
		<c:choose>
			<c:when test="${not empty qqNickname}">
				<div id="header_top">
					<div id="tip_bar">
						<div id="tip_left " class=" login_after_left fl">

							<span class="hotline"><i class="ww tel-icon"></i>服务热线：400-991-9999</span>
							<span class="attention"> <a class="we">关注我们</a> <a
								href="javascript:window.open('http://weibo.com/vfunding')"
								class="ww sina-weibo" target="black" rel="nofollow"></a> <a href="#"
								class="ww tt-weixin" rel="nofollow"></a>
							</span>
						</div>
						<div id="login_after_right" class="fr">
							<ul>
								<li style="width: 192px; text-align: right">您好，${qqNickname }</li>

								<li class="pd20 border_right"><a
									href="${pageContext.request.contextPath}/fresh" target="_blank">新手任务</a></li>
								<!-- <li class="pd20 border_right"><a href="#">工具箱</a></li> -->
								<li class="pd20" id="help"><a
									href="${pageContext.request.contextPath}/utilpage/helpCenter"
									target="_blank"> 常见问题</a></li>
							</ul>

						</div>

					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div id="header_top">
					<div id="tip_bar">
						<div id="tip_left" class="fl">
							<span class="hotline"><i class="ww tel-icon"></i>服务热线：400-991-9999</span>
							<span class="attention"> <a class="we">关注我们</a> <a
								href="javascript:window.open('http://weibo.com/vfunding')"
								class="ww sina-weibo" target="black" rel="nofollow"></a> <a href="#"
								class="ww tt-weixin" rel="nofollow"></a>
							</span>
							
							<a class="iso-enterprise" href="http://www.vfunding.cn/utilpage/toPage/iso">ISO9001认证</a>

						</div>
						<div id="tip_right" class="fr">

							<ul>
								<li class="pd20 border_right"><a
									href="${pageContext.request.contextPath}/goLogin">登录</a></li>
								<li class="pd20 border_right"><a
									href="${pageContext.request.contextPath}/goRegister">注册</a></li>
								<li class="pd20 border_right"><a
									href="${pageContext.request.contextPath}/fresh" target="_blank">新手任务</a></li>
								<!-- <li class="pd20 border_right"><a href="#">工具箱</a></li> -->
								<li class="pd20" id="help"><a
									href="${pageContext.request.contextPath}/utilpage/helpCenter"
									target="_blank">常见问题</a></li>
							</ul>

						</div>

					</div>
				</div>
			</c:otherwise>
		</c:choose>

	</c:if>
	<c:if test="${not empty loginedSession}">
		<div id="header_top">
			<div id="tip_bar">
				<div id="tip_left " class=" login_after_left fl">

					<span class="hotline"><i class="ww tel-icon"></i>服务热线：400-991-9999</span>
					<span class="attention"> <a class="we">关注我们</a> <a
						href="javascript:window.open('http://weibo.com/vfunding')"
						class="ww sina-weibo" target="black" rel="nofollow"></a> <a href="#"
						class="ww tt-weixin" rel="nofollow"></a>
					</span>
					<a class="iso-enterprise" href="http://www.vfunding.cn/utilpage/toPage/iso">ISO9001认证</a>
				</div>
				<div id="login_after_right" class="fr">
					<ul>
						<li style="width: 192px; text-align: right">您好，<a
							href="${pageContext.request.contextPath}/user/account"
							class="base-color">${loginedSession.userName }</a></li>
						<li class="pd20 border_right"><a
							href="${pageContext.request.contextPath}/loginOut">退出</a></li>
						<li class="pd20 border_right"><a
							href="${pageContext.request.contextPath}/fresh" target="_blank">新手任务</a></li>
						<!-- <li class="pd20 border_right"><a href="#">工具箱</a></li> -->
						<li class="pd20" id="help"><a
							href="${pageContext.request.contextPath}/utilpage/helpCenter"
							target="_blank"> 常见问题</a></li>
					</ul>

				</div>

			</div>
		</div>

	</c:if>

	<div id="logo_navs">
		<div class="logo_img fl">
			<a href="${pageContext.request.contextPath}"> <img
				src="/images/header/logo.png"  alt="微积金_中国领先的O2O互联网汽车金融平台"></a>
		</div>
		<div class="nav fr">
			<ul class="nav_ul clear">
				<li><a href="${pageContext.request.contextPath}"
					id="index" class="font_color">首页</a></li>
				<li><a
					href="${pageContext.request.contextPath}/borrow/borrowList"
					class="font_color">我要投资</a> <%-- <div class="nav_items">
						<div class="box">
							<em class="arrow"> <i></i>
							</em>
							<div class="dropdown">
								<a
									href="${pageContext.request.contextPath}/borrow/borrowList/0-0-0-1-p1"
									>招标中的借款标</a> <a
									href="${pageContext.request.contextPath}/borrow/borrowList/0-0-0-2-p1"
									>复审中的借款标</a> <a
									href="${pageContext.request.contextPath}/borrow/borrowList/0-0-0-3-p1"
									>完成的借款标</a> <a
									href="${pageContext.request.contextPath}/borrow/borrowList"
									>逾期的借款标</a>
							</div>
						</div>
					</div> --%></li>

				<li><c:if test="${not empty loginedSession }">
						<a href="${pageContext.request.contextPath}/borrow/releaseBorrow"
							class="font_color">我要借款</a>
					</c:if> <c:if test="${ empty loginedSession }">
						<a
							href="${pageContext.request.contextPath}/utilpage/loanRecommend"
							class="font_color">我要借款</a>

					</c:if>
					<div class="nav_items">
						<%--	<div class="box">
							<em class="arrow"> <i></i>
							</em>
							<div class="dropdown">
							 <a
									href="${pageContext.request.contextPath}/borrow/releaseBorrow">发布信用标</a>
								<a
									href="${pageContext.request.contextPath}/borrow/releaseBorrow">发布净值标</a>
								<a
									href="${pageContext.request.contextPath}/borrow/releaseBorrow">发布抵押标</a>
							</div>
						</div> --%>
					</div></li>
				<li><a href="${pageContext.request.contextPath}/user/account"
					class="font_color">我的账户</a></li>
				<li><a
					href="${pageContext.request.contextPath}/utilpage/aboutUs"
					class="font_color">关于我们</a>
					<div class="nav_items">
						<!-- 	<div class="box">
							<em class="arrow"> <i></i>
							</em>
							<div class="dropdown">
								<a href="#">公司简介</a> <a href="#">常见问题</a> <a href="#">招贤纳士</a> <a
									href="#">联系我们</a>
							</div> -->
					</div></li>

				<li><a
					href="${pageContext.request.contextPath}/utilpage/vNewsList/1/1"
					class="font_color">小微资讯</a>
					<div class="nav_items">
						<div class="box">
							<em class="arrow"> <i></i>
							</em>
							<div class="dropdown">
								<a
									href="${pageContext.request.contextPath}/utilpage/vNewsList/108/1">新闻</a>
								<a
									href="${pageContext.request.contextPath}/utilpage/vNewsList/109/1">攻略</a>
								<a href="${pageContext.request.contextPath}/utilpage/helpCenter">问答</a>
							</div>
						</div>

					</div></li>
			</ul>
		</div>
	</div>
</div>









