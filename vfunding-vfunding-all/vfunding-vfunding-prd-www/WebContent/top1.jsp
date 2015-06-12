<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="/css/account3.css" />
<link rel="Shortcut Icon" href="http://www.vfunding.cn/favicon.ico" />
<script src="/js/global-1.js" type="text/javascript"></script>
<div id="header">
	<c:if test="${empty loginedSession }">
		<c:choose>
			<c:when test="${not empty qqNickname}">
				<div id="header_top">
					<div id="tip_bar">
						<div id="tip_left " class=" login_after_left fl">

							<span class="hotline"><i class="ww tel-icon"></i>服务热线：400-991-9999</span>
							<span class="attention"> <a class="we">关注我们</a> <a
								href="http://weibo.com/vfunding" class="ww sina-weibo"
								target="black" rel="nofollow"></a> <a href="#" class="ww tt-weixin" rel="nofollow"></a>
							</span>
						</div>
						<div id="login_after_right" class="fr">
							<ul>
								<li style="width: 192px; text-align: right">您好，${qqNickname }</li>

								<li class="pd20 border_right"><a
									href="http://www.vfunding.cn/fresh" target="_blank">新手任务</a></li>
								<!-- <li class="pd20 border_right"><a href="#">工具箱</a></li> -->
								<li class="pd20" id="help"><a
									href="http://www.vfunding.cn/utilpage/helpCenter"
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
								href="http://weibo.com/vfunding" class="ww sina-weibo"
								target="black"></a> <a href="#" class="ww tt-weixin"></a>
							</span>

						</div>
						<div id="tip_right" class="fr">

							<ul>
								<li class="pd20 border_right"><a
									href="${pageContext.request.contextPath}/goLogin">登录</a></li>
								<li class="pd20 border_right"><a
									href="${pageContext.request.contextPath}/goRegister">注册</a></li>
								<li class="pd20 border_right"><a
									href="http://www.vfunding.cn/fresh" target="_blank">新手任务</a></li>
								<!-- <li class="pd20 border_right"><a href="#">工具箱</a></li> -->
								<li class="pd20" id="help"><a
									href="http://www.vfunding.cn/utilpage/helpCenter"
									target="_blank">帮助中心</a></li>
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
						href="http://weibo.com/vfunding" class="ww sina-weibo"
						target="black"></a> <a href="#" class="ww tt-weixin"></a>
					</span>
				</div>
				<div id="login_after_right" class="fr">
					<ul>
						<li style="width: 192px; text-align: right">您好，<a
							href="${pageContext.request.contextPath}/user/account"
							class="base-color">${loginedSession.userName }</a></li>
						<li class="pd20 border_right"><a
							href="http://www.vfunding.cn/loginOut">退出</a></li>
						<li class="pd20 border_right"><a
							href="http://www.vfunding.cn/fresh" target="_blank">新手任务</a></li>
						<!-- <li class="pd20 border_right"><a href="#">工具箱</a></li> -->
						<li class="pd20" id="help"><a
							href="http://www.vfunding.cn/utilpage/helpCenter" target="_blank">
								帮助中心</a></li>
					</ul>

				</div>

			</div>
		</div>

	</c:if>

	<div id="logo_navs">
		<div class="logo_img fl">
			<a href="http://www.vfunding.cn/index"> <img
				src="/images/header/logo.png" /></a>
		</div>
		<div class="nav fr">
			<ul class="nav_ul clear">
				<li><a href="http://www.vfunding.cn/index" id="index"
					class="font_color">首页</a></li>
				<li><a href="http://www.vfunding.cn/borrow/borrowList"
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
						<a href="http://www.vfunding.cn/borrow/releaseBorrow"
							class="font_color">我要借款</a>
					</c:if> <c:if test="${ empty loginedSession }">
						<a href="http://www.vfunding.cn/utilpage/loanRecommend"
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



				<li><a href="http://www.vfunding.cn/utilpage/aboutUs"
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
			</ul>

		</div>

	</div>

</div>









<script>
	$(function() {

		$(".tt-weixin").click(function() {

			webUtil.jDialog.Modal("weixinDialog", "weixinEntry");

		});

	})
</script>



<div style="width: 360px;" class="dialog-main" id="weixinDialog">

	<div class="dialog-head">
		<h2>关注微积金官方微信</h2>
		<a class="closeModal r3" href="javascript:;"
			style="line-height: 24px; text-align: center;">X</a>
	</div>
	<div id="weixinEntry">
		<p style="text-align: center; padding: 15px 0">
			<img src="/images/weixin_code.gif"
				style="display: block; margin: 0 auto; width: 220px; height: 220px">
		</p>
		<div class="dialog-foot">
			<p style="padding: 7px 10px 0 10px; font-size: 12px">打开微信，点击底部的″发现″，使用″扫一扫″即可关注微积金官方微信。</p>
		</div>
	</div>
</div>




