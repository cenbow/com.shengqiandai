<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="微积金(www.vfunding.cn) 整合了民营汽车借贷机构、二手车商、第三方汽车服务机构等传统领域企业，形成了独特的针对汽车提供投融资对接服务的创新型O2O互联网汽车金融服务模式。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、汽车贷款等。" />
<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|基金|微基金|小额贷款|微财富|二手车抵押|" />
<title>shengqiandai</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/homepage.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/kinMaxShow.js"></script>
<script src="${pageContext.request.contextPath}/js/common.js"
	type="text/javascript"></script>
<script charset="utf-8" src="http://wpa.b.qq.com/cgi/wpa.php"></script>
<script type="text/javascript">
	/* $(function() {
		var userAgentInfo = navigator.userAgent;
		var pathName = window.document.location.pathname;
		if (pathName == '/index') {
			return;
		} else {
			var Agents = [ "Android", "iPhone", "SymbianOS", "Windows Phone",
					"iPad", "iPod" ];
			for (var v = 0; v < Agents.length; v++) {
				if (userAgentInfo.indexOf(Agents[v]) > 0) {
					window.location = "http://m.vfunding.cn/index";
					break;
				}
			}
		}
	}); */

	BizQQWPA.addCustom({
		aty : '0',
		a : '0',
		nameAccount : 4009919999,
		selector : 'ceping'
	});
</script>
<meta property="wb:webmaster" content="5691f98ba103536a" />
<!--  <meta property="qc:admins" content="2335321003666564167636" />-->
<meta property="qc:admins" content="23353210036634145636" />
</head>
<body>
	<!---头部--->
	<jsp:include page="top.jsp"></jsp:include>
	
	<div class="pic_change">
		<div id="kinMaxShow">
			<c:forEach items="${scrollPics}" var="scroll">
				<div>
					<a href="${scroll.url}"><img src="${scroll.pic}" width="2099"
						height="300" /></a>
				</div>
			</c:forEach>
		</div>
		<div class="login_box">
			<c:if test="${empty loginedSession }">
				<div class="login_sector">
					<h1>单月产品年化净收益率</h1>
					<p>10.6%</p>
					<span><font>30.3倍</font>活期存款收益&nbsp;&nbsp;<font>3.53倍</font>定期存款收益</span>
					<a href="${pageContext.request.contextPath}/goRegister"
						class="register_btn">免费注册</a>
					<h3 id="warm_tip">
						已有账号? <a href="${pageContext.request.contextPath}/goLogin">立即登录</a>
					</h3>
				</div>

				<div class="transparent"></div>

			</c:if>

			<c:if test="${not empty loginedSession }">
				<div class="login_sector">
					<p id="login_after_box">欢迎来到微积金</p>
					<p id="login_name">
						你当前的登陆账号是：<font>${loginedSession.userName }</font>
					</p>
					<a href="${pageContext.request.contextPath }/user/account"
						class="register_btn">进入我的账户</a>
				</div>
				<div class="transparent"></div>
			</c:if>
		</div>
	</div>
	<!----公告---->
	<div id="notice">

		<p>
			<em class="notice_bg  fl"></em>
			<c:forEach items="${reports}" var="report">
				<span class="fl"><a
					href="${pageContext.request.contextPath}/utilpage/selectArticleDetail/${report.id}">${report.name }</a></span>
			</c:forEach>

			<a href="${pageContext.request.contextPath}/utilpage/websiteNotice"
				class="fr">更多</a>
		</p>

	</div>


	<!--中间主体内容部分-->
	<div id="content">
		<div class="left_sector fl">
			<div class="vfunding_advantages">
				<div class="advantage_points">
					<div class="advantage fl">
						<a href="${pageContext.request.contextPath }/borrow/releaseBorrow"
							class="a1 fl index_bg" title="我要借款"></a> <a class="advantage_explain fl"
							href="${pageContext.request.contextPath }/borrow/releaseBorrow" title="我要借款">
							<h2>我要借款</h2>
							<p>填写借款信息</p>
							<p>审核通过</p>
							<p>发布借款</p>
						</a>
					</div>

					<div class="advantage fl">
						<a href="${pageContext.request.contextPath}/borrow/borrowList"
							class="a2 fl index_bg" title="我要投资"></a> <a class="advantage_explain fl"
							href="${pageContext.request.contextPath}/borrow/borrowList"  title="我要投资">
							<h2>我要投资</h2>
							<p>最低50元起投</p>
							<p>灵活的单月标的</p>
							<p>10%-20%年化收益</p>

						</a>
					</div>

					<div class="advantage fl">
						<a href="${pageContext.request.contextPath }/utilpage/safe" class="a3 fl index_bg" title="安全保障"></a> 
							<a class="advantage_explain fl" href="${pageContext.request.contextPath }/utilpage/toPage/trust" title="安全保障">
							<h2>安全保障</h2>
							<p>只做抵押标</p>
							<p style="color: #ff8636;" >新浪支付全程资金托管</p>
							<p id="cms-money">招行风险备付金托管</p>
						</a>
					</div>

				</div>
				<div class="what_vfunding">
					<h1>
						<a href="${pageContext.request.contextPath}/utilpage/aboutUs">什么是微积金</a>
					</h1>
					<div class="vfunding_detail">

						<a href="${pageContext.request.contextPath}/utilpage/aboutUs"
							class="plugin_container" title="什么是微积金？" > <!-- <div class="mysocialshare" data-orientation="line"
								data-picture="${pageContext.request.contextPath}/images/click_show.png"
								data-jieshao-handle="#" data-baozhang-handle="#"
								data-touming-handle="#" data-zhuanye-handle="sireid"
								data-youshi-handle="#"
								data-networks="jieshao,baozhang,youshi,zhuanye,touming"></div> -->

						</a> <a class="vfunding_info"
							href="${pageContext.request.contextPath}/utilpage/aboutUs">
							<p>微积金（www.vfunding.cn），是中国领先的O2O互联网汽车金融平台，由从业于金融、互</p>
							<p>联网行业多年的精英团队倾力打造国内最安全的互联网金融信息平台 。微积金专注于互联网</p>
							<p>汽车金融领域，有信心通过自身的高品质服务能帮助您制订让所有人都能看得懂的理财计划！</p>
						</a>

					</div>
				</div>
			</div>

			<div class="tender_list">
				<div class="listTitle clear">
					<h1>投资列表</h1>
					<a href="/borrow/borrowList" class="lookMore">查看更多</a>
				</div>
				<div class="list  box-shadow">

					<c:forEach items="${borrows}" var="borrow">
						<div class="sub_list">
							<div class="wrap">
								<c:choose>
									<c:when test="${not empty borrow.litpic }">
										<a href="borrow/borrowDetail/${borrow.id}" class="touxiang fl"
											style="background: none;"> <img
											src="${borrow.litpicPath}" height="100" width="100" />
										</a>
									</c:when>
									<c:otherwise>
										<a href="borrow/borrowDetail/${borrow.id}" class="touxiang fl">
											<c:if test="${borrow.biaoType =='huodong'}">
												<img
													src="${pageContext.request.contextPath}/images/index/huodong-big.png"
													height="100" width="100" />
											</c:if> <c:if test="${borrow.biaoType =='fast'}">
												<img
													src="${pageContext.request.contextPath}/images/index/touxiang.png"
													height="100" width="100" />
											</c:if> <c:if test="${borrow.biaoType =='feel'}">
												<!-- 体验标图片 -->
												<img
													src="${pageContext.request.contextPath}/images/index/touxiang2.png"
													height="100" width="100" />
											</c:if>
										</a>
									</c:otherwise>
								</c:choose>

								<div class="sub_list_content fl">
									<a href="borrow/borrowDetail/${borrow.id}" class="title">${borrow.name }</a>
									<p class="borrow_content">
										<span class="span1 fl">借款金额：${borrow.accountStr }元</span> <span
											class="span2 fr">还款方式：<c:if
												test="${borrow.style ==0 }">等额本息</c:if> <c:if
												test="${borrow.style ==1 }">等本等息</c:if> <c:if
												test="${borrow.style ==2 }">到期还款</c:if> <c:if
												test="${borrow.style ==3 }">先息后本</c:if></span>
									</p>
									<p class="borrow_content h28">
										<span class="span1 fl"><c:if test="${borrow.isday==0 }">借款期限：${borrow.timeLimit }个月</c:if>
											<c:if test="${borrow.isday==1 }">借款期限：${borrow.timeLimitDay }天</c:if>
										</span> <span class="span2 fr number">预期纯收益：<b>${borrow.expectApr }%</b></span>
									</p>

									<p class="borrow_content bt0">
										<span class="span1 fl"><img
											src="${pageContext.request.contextPath}/images/index/bxbz-icon.png" />&nbsp;<a
											href="${pageContext.request.contextPath}/utilpage/aboutUs#security"
											target="_blank">本息保障计划</a></span>
										<%-- 	<c:if test="${not empty borrow.vouchUser }">
											<span class="span2 fr">${borrow.vouchUser }担保</span>
										</c:if> --%>
									</p>
								</div>
								<div
									class="progress_circle fl progressBar progressBar${borrow.completeInteger } ">

									<p>${borrow.completePercent }</p>

								</div>

								<c:if test="${borrow.status==1}">
									<a href="borrow/borrowDetail/${borrow.id}" class="tou fl">马上投标</a>
								</c:if>
								<c:if test="${borrow.status==11}">
									<a href="borrow/borrowDetail/${borrow.id}" class="tou fl">马上体验</a>
								</c:if>
								<c:if test="${borrow.status==2 || borrow.status==12}">
									<a href="borrow/borrowDetail/${borrow.id}" class="man fl"
										style="color: #EEEEEE;">满标待审</a>
								</c:if>
								<c:if test="${borrow.status==3 || borrow.status==13}">
									<a href="borrow/borrowDetail/${borrow.id}" class="huan fl"
										style="width: 110px;">撮合成功</a>
								</c:if>
								<c:if test="${borrow.status==4 || borrow.status==14}">
									<a href="borrow/borrowDetail/${borrow.id}" class="jie fl"
										style="width: 110px;">已还款</a>
								</c:if>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="right_sector fr">
			<a class="fresh_enter"
				href="${pageContext.request.contextPath}/utilpage/videoList"
				target="_blank"></a>
			<div class="right_wrap">
				<h1>已完成投资总额</h1>
				<div class="total_number box-shadow">

					<span> <c:if test="${empty allTender }">
							<em>123,456,789</em>
						</c:if> <c:if test="${not empty allTender }">
							<em>${allTender }</em>
						</c:if>
					</span>
					<!-- <a href="#" class="fr" target="_blank">查看更多统计数据</a> -->

				</div>

				<div class="media_reports clear">

					<h1 class="fl">媒体报道</h1>
					<a href="${pageContext.request.contextPath}/utilpage/mediaReports"
						class="fr">查看更多报道</a>

				</div>

				<div class="report_content box-shadow">
					<c:forEach items="${meidas}" var="meida">
						<div class="report_summary clear">
							<a
								href="${pageContext.request.contextPath}/utilpage/selectArticleDetail/${meida.id}"
								class="report_pic fl"> <c:if
									test="${not empty meida.litpicPath}">
									<img src="${meida.litpicPath}" width="106" height="70" />
								</c:if>
							</a>
							<div class="report_title fr">
								<a
									href="${pageContext.request.contextPath}/utilpage/selectArticleDetail/${meida.id}"
									target="_blank"> ${fn:substring(meida.name, 0, 29)} </a>
							</div>
						</div>
					</c:forEach>
				</div>

				<div class="tools box-shadow">
					<ul>
						<li><a
							href="${pageContext.request.contextPath }/utilpage/profitCal"
							class="fl tool1 index_bg"></a> <span class="tool_name fl">
								<a id="gongju1"
								href="${pageContext.request.contextPath }/utilpage/profitCal">收益计算器</a>
						</span> <span class="tool_tip fr">算算能赚多少</span></li>
						<li><a
							href="${pageContext.request.contextPath}/borrowAuto/borrowAuto"
							class="fl tool2 index_bg"></a> <span class="tool_name fl"><a
								id="gongju2"
								href="${pageContext.request.contextPath}/borrowAuto/borrowAuto">自动投资</a></span>
							<span class="tool_tip fr">省心省力快速投资</span></li>
						<li><a
							href="${pageContext.request.contextPath }/utilpage/cpi"
							class="fl tool3 index_bg"></a> <span class="tool_name fl"><a
								id="gongju3"
								href="${pageContext.request.contextPath }/utilpage/cpi">
									CPI跟踪器</a></span> <span class="tool_tip fr">齐心齐力跑赢CPI</span></li>
						<li><a
							href="${pageContext.request.contextPath }/utilpage/wealthCal"
							class="fl tool4 index_bg"></a> <span class="tool_name fl"><a
								id="gongju4"
								href="${pageContext.request.contextPath }/utilpage/wealthCal">身价计算器</a></span>
							<span class="tool_tip fr">你也能成为高富帅</span></li>
						<li id="border_bottom_none"><a class="fl tool5 index_bg"
							href="${pageContext.request.contextPath }/utilpage/earningsContrast"></a>
							<span class="tool_name fl"><a id="gongju5"
								href="${pageContext.request.contextPath }/utilpage/earningsContrast">收益对比器</a></span>
							<span class="tool_tip fr">好不好比比就知道</span></li>


					</ul>
				</div>

				<!--<a href="${pageContext.request.contextPath}/borrow/borrowList"
					class="small_banner box-shadow"><img
					src="${pageContext.request.contextPath}/images/index/apr.jpg"
					width="300" height="90" /></a>-->
                                <a href="https://www.weicaifu.com/v/regular/detail/hQV54jOgLrE"
                                        class="small_banner box-shadow"><img
                                        src="${pageContext.request.contextPath}/images/index/apr.jpg"
                                        width="300" height="90" /></a>


			</div>

		</div>

	</div>


	<ul id="float_box">

		<li style="display: none; height: 35px;"><a id="top" href="#top"></a></li>

		<li><a id="weixin" href="javascript:void(0)"
			onmouseover="showEWM()" onmouseout="hideEWM()">
				<div id="EWM">
					<img
						src="${pageContext.request.contextPath}/images/weixin_code.jpg" />
				</div>
		</a></li>
		<li><a id="ceping" href="" target="_blank"></a></li>



	</ul>

	<script type="text/javascript">
		function showEWM() {
			document.getElementById("EWM").style.display = 'block';
		}
		function hideEWM() {
			document.getElementById("EWM").style.display = 'none';
		}
	</script>

	<!-- 跳转到监管报告 -->
	<script type="text/javascript">
		$(function() {
			$("#cms-money").click(
							function() {
								window.location.href = "${pageContext.request.contextPath}/utilpage/surpise"
								return false;
							})
		})
	</script>
	<!------尾部------>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

