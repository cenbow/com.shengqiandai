<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title> #现金奖励遇到理财师佣金！蓄势待发，带你赚钱带你飞！#新手收益提升50%！全场活动奖励超百万！转发分享好友迎取更多抽奖机会，高额现金红包、精美笔记本、收益提升任你拿！</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="/css/account3.css" />
<link rel="stylesheet" type="text/css" href="/css/mar.css" />
<link rel="stylesheet" type="text/css" href="/css/prize/jqueryui/jqueryui.css" />
<link rel="stylesheet" type="text/css" href="/css/prize/prize2.css" />
<script src="/js/jquery1.8.3.js"></script>
<script type="text/javascript" src="/js/prize/jqueryui.js"></script>
<script src="/js/scroll.js"></script>
<!-- 弹出层JS -->
<script	src="${pageContext.request.contextPath}/js/artDialog/artDialog.js?skin=default"></script>
</head>

<body>
	<!-------------头部-固定写死------------------->
<%-- 	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include> --%>

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
								class="ww sina-weibo" target="black"></a> <a href="#"
								class="ww tt-weixin"></a>
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
									target="_blank"> 帮助中心</a></li>
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
								class="ww sina-weibo" target="black"></a> <a href="#"
								class="ww tt-weixin"></a>
							</span>
							
							<a class="iso-enterprise" href="http://www.vfunding.cn/utilpage/toPage/iso">ISO9001认证</a>

						</div>
						<div id="tip_right" class="fr">

							<ul>
								<li class="pd20 border_right"><a
									href="${pageContext.request.contextPath}/goLogin">登录</a></li>
								<li class="pd20 border_right"><a
									href="javascript:register();">注册</a></li>
								<li class="pd20 border_right"><a
									href="${pageContext.request.contextPath}/fresh" target="_blank">新手任务</a></li>
								<!-- <li class="pd20 border_right"><a href="#">工具箱</a></li> -->
								<li class="pd20" id="help"><a
									href="${pageContext.request.contextPath}/utilpage/helpCenter"
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
						href="javascript:window.open('http://weibo.com/vfunding')"
						class="ww sina-weibo" target="black"></a> <a href="#"
						class="ww tt-weixin"></a>
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
							target="_blank"> 帮助中心</a></li>
					</ul>

				</div>

			</div>
		</div>

	</c:if>

	<div id="logo_navs">
		<div class="logo_img fl">
			<a href="${pageContext.request.contextPath}"> <img
				src="/images/header/logo.png" /></a>
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


	<!-- 注册写死结束 -->
	<!--中间-->
	<input type="hidden" id="shareId" name="shareId" value="${shareId}">
	<input type="hidden" id="inviteCode" name="inviteCode" value="${inviteCode}">
	<div class="mar">

		<div class="mar-top">
			<div class="w1000 h606 pos-r">
				<div class="actvity-time">
					<p>第一季：3月10日 - 3月16日</p>
					<p>第二季：3月16日 - 4月10日</p>
				</div>
				<div class="mar-top-cnt pos-a">
					<h1>蓄势待发，带你赚钱带你飞！</h1>
					<p>又是一年春暖花开，你准备好赚钱了吗？</p>
					<p>2015年，微积金理财师全新起航</p>
					<p>蓄势待发，带你赚钱带你飞！</p>
				</div>
			</div>

		</div>

		<div class="mar-sector-first w1000">
			<div class="h47 m2bg m2bg1"></div>
			<div class="mar-sector-first-cnt clear">
				<div class="fl">
					<p>活动期间注册用户首笔投资可</p>
					<p>
						享有<span>收益提升50%</span>，限额10000元！
					</p>
				</div>
				<div class="fr m2bg m2bg-small"></div>
			</div>
		</div>

		<div class="w1000 mar-sector-second">
			<div class="h47 m2bg m2bg2"></div>
			<div class="mar-sector-second-cnt">
				奖品里不仅有可以在您理财的时候助您一臂之力的加息和抵扣券，所有投资加息皆可累积使用，多抽多得；我们还精心制作了微积金笔记本，让您新一年的理财道路更加有规划；春暖花开，我们贴心地为您准备了踏青旅行基金，抽中的话，就带上您的家人去踏青赏花吧！
			</div>
			<div class="mar-sector-second-rotate m2bg pos-r">
				<div class="rotate-title">
					您今天还有<span id="chooseCount">${canChooseCount==null?0:canChooseCount}</span>次抽奖机会
				</div>
				
			<!-- 转盘 -->	
				
			<div class="rotate-box">
			    <div class="rotatebg">
	            	<div id="turnplatewrapper" onselectstart="return false;" class="bgfix">
						<div id="turnplate" class="bgfix">
							<div id="awards" class="bgfix"></div>
							<div id="platebtn" class="bgfix"></div>
							<p id="msg"></p>
							<p id="init" class="dn">初始化中，请稍后<span></span></p>
						</div>
					</div>
	            	<div id="gift" class="bgfix"></div>
	            	<div id="lotteryMsg" class="dn">
						<div class="getPrize clear">
		    				<div class="prizeIcon fl"></div>
		    				<div class="prizeWord fl">
			        			<div class="onGet"></div>
			        			<div class="prizeName"> <span class="msg-center">点击<a href="/sysMessage/sysMessagePage" target="_blank">消息中心</a>了解详情</span></div>
			        			<div class="getPrizeSteps"></div>
		    				</div>
						</div>
					</div>
	            </div>		
			</div>
				
				
		<!-- 转盘  结束-->			
				

				<div class="qualification pos-a">
					<p>每个账户每日登录一次即可获得一次抽奖机会；</p>
					<p>
						投资超过10000元可再获得一次抽奖机会；<a class="btn-invest" target="_blank" href="/borrow/borrowList">马上投资</a>
					</p>
					<p>当日分享5次活动信息给朋友可再次获得一次抽奖机会；</p>

					<!--分享部分-->

					<div class="share-box clear">
						<div class="share-to fl" >分享到：</div>
						<div class="share-icon fl" >

									<!-- 百度分享插件 html-->
									<div class="bdsharebuttonbox" style="margin-top:-8px">

										<a title="分享到新浪微博" href="#" class="bds_tsina" data-cmd="tsina"
											style="font-size: 1em;margin-right:1em;" >&nbsp;</a> 
										<a title="分享到腾讯微博" href="#"
											class="bds_tqq" data-cmd="tqq" style="font-size: 1em;margin-right:1em;" >&nbsp;</a>
										<a title="分享到QQ好友" href="#" class="bds_sqq" data-cmd="sqq"
											style="font-size: 1em;margin-right:1em;" >&nbsp;</a> <a href="#" class="bds_more"
											data-cmd="more"><span></span></a>
									</div>
									
							
									
									<!-- 百度分享插件  start-->
									<c:if test="${empty loginedSession }">
										<script type="text/javascript">
										var userId = "";
										</script>
									</c:if>
	
									<c:if test="${not empty loginedSession }">
										<script type="text/javascript">
										var userId = ${loginedSession.userId};
										</script>
									</c:if>
									<script type="text/javascript">
										var sl = "";
										if(userId!=""){
											sl = "http://www.vfunding.cn/prize/season2/toChoosePage?f="+userId;
										}else{
											sl = "http://www.vfunding.cn/prize/season2/toChoosePage";
										}										
										window._bd_share_config = {
											"common" : {
												"bdSnsKey" : {},
												"bdUrl" : sl,
												"bdText" : "#现金奖励遇到理财师佣金！蓄势待发，带你赚钱带你飞！#新手收益提升50%！全场活动奖励超百万！转发分享好友迎取更多抽奖机会，高额现金红包、精美笔记本、收益提升任你拿！",
												"bdMini" : "2",
												"bdMiniList" : false,
												"bdPic" : "",
												"bdStyle" : "0",
												"bdSize" : "12"
											},
											"share" : {}
										};
										with (document)
											0[(getElementsByTagName('head')[0] || body)
													.appendChild(createElement('script')).src = '/js/fp/share.js?v=89860593.js?cdnversion='
													+ ~(-new Date() / 36e5)];
									</script>
									<style>
										.bdshare-button-style0-24 a,.bdshare-button-style0-24 .bds_more {
											line-height: 18px;
										}
									</style>
									
									
									<!-- 百度分享插件  end-->


						</div>
					</div>
					<!--分享部分 结束-->

				</div>

				<div class="reward-record pos-a">

					<div class="more" style="height:30px;">
<!-- 						<a>更多</a> -->
					</div>
					<div class="myPrize clear">
						<table cellspacing="0" cellpadding="0" id="myPrizeList">
						</table>
					</div> 
				</div>

				<div class="rewardList pos-a clear">
				</div>

				<!-- <div class="wait m2bg"></div> -->




			</div>


		</div>

		<div class="mar-sector-third w1000">
			<div class="h47 m2bg m2bg3"></div>
			<div class="mar-sector-third-cnt">活动期间（3月16日-4月10日），理财师可根据不同成就解锁不同奖励</div>
			<div class="mar-sector-third-bg m2bg">
				<div class="card card-first">
					<div class="top-cnt">
						活动期间邀请最多实名注册用户理财师，可获得半年内<span>提现免费奖励</span>
					</div>
					<a class="btn-link" type="1">查看自己的成绩</a>
					<div class="card-foot">
						<div class="card-foot-title">
							邀请最多实名注册用户理财师<br />已邀请
						</div>
						<!-- <div class="no-open m2bg"></div> -->
						      	<div class="no-open">
							<span id="c1">0</span>位用户
						</div>
					</div>
				</div>

				<div class="card card-second pos-a">
					<div class="top-cnt">
						活动期间邀请最多投资用户，可获得<span>888元现金奖励</span>
					</div>
					<a class="btn-link" id="mt68" type="2">查看自己的成绩</a>
					<div class="card-foot">
						<div class="card-foot-title">
							邀请最多投资用户<br />已邀请
						</div>
						<!-- <div class="no-open m2bg"></div> -->
                    	<div class="no-open">
							<span id="c2">0</span>位用户
						</div>
					</div>
				</div>

				<div class="card card-third pos-a">
					<div class="top-cnt">
						活动期间邀请最多投资金额理财师（之前邀请的用户在活动期间复投也计入成绩），本次活动期间所有获得的 <span>返利全部翻倍</span>
					</div>
					<a class="btn-link" id="mt10" type="3">查看自己的成绩</a>
					<div class="card-foot">
						<div class="card-foot-title">
							邀请最多投资金额理财师<br />已邀请投资
						</div>
						<!-- <div class="no-open m2bg"></div> -->
					<div class="no-open">
							<span id="c3">0</span>元
						</div> 
					</div>
				</div>

            <!--  <div class="wait2 m2bg"></div> -->
             
			</div>
			<a class="click-more m2bg" target="_blank" href="/fp/toFpPage"></a>

		</div>

		<div class="mar-sector-fourth w1000">
			<div class="h47 m2bg m2bg4"></div>
			<div class="rules">
				<ul>
					<li><i class="dot">1</i>
						活动期间新手注册收益提升50%、5元现金红包和理财师推荐返利都将通过礼品盒发放，登陆我的账户打开礼品盒点击现金红包使用即可自动打入账户余额。请在收到日15天内登陆账户点击使用激活，否则将在15天后失效。

					</li>

					<li><i class="dot">2</i>
						活动期间所有投资加息（1%、2%）将通过礼品盒发放，用户可以在投资的时候选择是否使用加息（可累积使用）。请在2015年6月30日前使用完成。
					</li>


					<li><i class="dot">3</i>
						我们将在活动结束后7个工作日内与抽中发放实物奖品和旅行基金的用户联系并送出，旅行基金以现金红包形式发放。</li>

					<li><i class="dot">4</i> 对于参与用户虚假作弊行为，微积金有权该用户取消参加活动资格。</li>
				</ul>

			</div>
			<p class="owner">微积金对本次活动拥有最终解释权</p>
		</div>
	</div>
	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
	<!--turnlate-->
	<script type="text/javascript" src="/js/prize/prizeSeason2.js"></script>
	<script type="text/javascript">
		$(function() {
			$('#lotteryMsg').dialog({
				modal: true,
				width: 900,
				height: 400,
				resizable: false,
				title: '获奖信息',
				autoOpen: false
			}); 
			turnplate.logined = ${ not empty loginedSession ?true:false};
			turnplate.lotteryTime = $("#chooseCount").val();
			turnplate.init();
			//活动信息
			ajaxActivityInfo();
			//获奖次数
			prizeChooseCount();
			//获奖列表
			myPrizeChooseList();
			//奖品列表
			prizeChooseList(); 
			//分享链接
			shareLink();
			//查看成绩
			dialogTop();
		})
	</script>
</body>
</html>
