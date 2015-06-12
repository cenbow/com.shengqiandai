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
<link href="${pageContext.request.contextPath}/css/financialist.css"
	rel="stylesheet" type="text/css" />


<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>

<script src="${pageContext.request.contextPath}/js/friend/financiaDetail.js"></script>
<!-- 弹出层JS artDialog-->
<script
	src="${pageContext.request.contextPath}/js/artDialog/artDialog.js?skin=default"></script>
<script
	src="${pageContext.request.contextPath}/js/artDialog/plugins/iframeTools.js"></script>
<script>
	$(function() {

		$(".rank-explain li").hover(function() {

			var index = $(".rank-explain").find("li").index($(this));

			$(".all").find(".lcs-cont").eq(index).show().siblings().hide();

		});

	})
</script>





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
							<h2>申请理财师</h2>
						</div>
						<c:choose>
							<c:when test="${loginedSession.typeId ==2}">
								<div class="lcs">
									<span class="fl">当前用户等级：<i class="rank rank_bg rank1"></i>推荐一位好友实名注册+投资，即可申请推荐达人
									</span> <a href="javascript:upfinancia();" class="btn136 fr">申请特约理财师</a>
								</div>
								<p class="lcs-rank">理财师等级</p>
								<div class="rank-pic">
									<a class="rank-pic1 rankbg rpic1"></a> <a
										class="rank-pic2 rankbg"></a> <a class="rank-pic3 rankbg"></a>
									<a class="rank-pic4 rankbg"></a> <a class="rank-pic5 rankbg"></a>
								</div>
								<div class="rank-progress rpbg rank-progress2"></div>
							</c:when>
							<c:when test="${loginedSession.typeId  ==32}">
								<div class="lcs">
									<span class="fl">当前用户等级：<i class="rank rank_bg rank2"></i>投资5000元，即可申请特约理财师
									</span> <a href="javascript:upfinancia();" class="btn136 fr">申请特约理财师</a>
								</div>
								<p class="lcs-rank">理财师等级</p>
								<div class="rank-pic">
									<a class="rank-pic1 rankbg rpic1"></a> <a
										class="rank-pic2 rankbg rpic2"></a> <a
										class="rank-pic3 rankbg"></a> <a class="rank-pic4 rankbg"></a>
									<a class="rank-pic5 rankbg"></a>
								</div>
								<div class="rank-progress rpbg rank-progress3"></div>
							</c:when>

							<c:when test="${loginedSession.typeId  ==28}">
								<div class="lcs">
									<span class="fl">当前用户等级：<i class="rank rank_bg rank3"></i>投资5000元，并且30天内好友总投资金额超过1,000,000元，即可申请高级理财师
									</span> <a href="javascript:upfinancia();" class="btn136 fr">申请高级理财师</a>
								</div>
								<p class="lcs-rank">理财师等级</p>
								<div class="rank-pic">
									<a class="rank-pic1 rankbg rpic1"></a> <a
										class="rank-pic2 rankbg rpic2"></a> <a
										class="rank-pic3 rankbg rpic3"></a> <a
										class="rank-pic4 rankbg"></a> <a class="rank-pic5 rankbg"></a>
								</div>
								<div class="rank-progress rpbg rank-progress4"></div>
							</c:when>
							<c:when test="${loginedSession.typeId  ==29}">
								<div class="lcs">
									<span class="fl">当前用户等级：<i class="rank rank_bg rank4"></i>投资5000元，并且30天内好友总投资金额超过5,000,000元，即可申请首席理财师
									</span> <a href="javascript:upfinancia();" class="btn136 fr">申请首席理财师</a>
								</div>
								<p class="lcs-rank">理财师等级</p>
								<div class="rank-pic">
									<a class="rank-pic1 rankbg rpic1"></a> <a
										class="rank-pic2 rankbg rpic2"></a> <a
										class="rank-pic3 rankbg rpic3"></a> <a
										class="rank-pic4 rankbg rpic4"></a> <a
										class="rank-pic5 rankbg"></a>
								</div>
								<div class="rank-progress rpbg rank-progress5"></div>
							</c:when>
							<c:when test="${loginedSession.typeId  ==30}">
								<div class="lcs">
									<span class="fl">当前用户等级：<i class="rank rank_bg rank5"></i>恭喜您，您已是最高级别了
									</span>
								</div>
								<p class="lcs-rank">理财师等级</p>
								<div class="rank-pic">
									<a class="rank-pic1 rankbg rpic1"></a> <a
										class="rank-pic2 rankbg rpic2"></a> <a
										class="rank-pic3 rankbg rpic3"></a> <a
										class="rank-pic4 rankbg rpic4"></a> <a
										class="rank-pic5 rankbg rpic5"></a>
								</div>
								<div class="rank-progress rpbg rank-progress6"></div>
							</c:when>
						</c:choose>


						<div class="rank-explain">
							<ul>
								<li class="li1">普通用户</li>
								<li class="li2">推荐达人</li>
								<li class="li3">特约理财师</li>
								<li class="li4">高级理财师</li>
								<li class="li5">首席理财师</li>

							</ul>
						</div>

						<div class="all">
							<div class="lcs-cont" style="border: none;"></div>

							<div class="lcs-cont" style="display: block;">
								<em class="arrow-up" style="left: 170px;"><i class="i1"></i></em>

								<p class="teyue">推荐达人(活动已结束)</p>
								<p>享有特权：享受所推荐好友的每一笔投资额的0.72%（年化）净收益。</p>
								<p class="pd90">随时推荐好友，随时享有高额收益率！</p>
								<p>达标条件：推荐好友注册并产生投资行为（超过100元），次日即可变身微积金推荐达人！</p>
							</div>

							<div class="lcs-cont">
								<em class="arrow-up" style="left: 346px;"><i class="i1"></i></em>

								<p class="teyue">特约理财师</p>
								<p>享有特权：享受所推荐好友的每一笔投资额的0.72%（年化）净收益。</p>
								<p>达标条件：30天内自身投资金额超过100,000元，并有一定客户资源可申请成为特约理财师。</p>
								<p>降级条件：1.连续30天，自身净资产低于100,000元。</p>
								<p class="pd90">2.30天内好友返利低于500元或60天没有新介绍好友。</p>
								<p class="pd90">同时满足以上条件，即失去特约理财师身份。</p>
							</div>
							<div class="lcs-cont">
								<em class="arrow-up" style="left: 508px;"><i class="i1"></i></em>

								<p class="teyue">高级理财师</p>
								<p>享有特权：享受所推荐好友的每一笔投资额的0.81%（年化）净收益。</p>
								
								<p>达标条件：1.30天内自身投资金额超过5000元。</p>
								<p class="pd90">2.30天内好友总投资金额超过1,000,000元。</p>
								<p class="pd90">同时满足以上条件，并有一定客户资源可申请成为高级理财师。</p>

								<p>降级条件：1.连续30天，自身净资产低于5000元。</p>
								<p class="pd90">2.30天内好友返利低于500元或连续60天内没有新投资人产生。</p>
								<p class="pd90">同时满足以上条件，即失去高级理财师身份。</p>
							</div>
							<div class="lcs-cont">
								<em class="arrow-up" style="left: 650px;"><i class="i1"></i></em>

								<p class="teyue">首席理财师</p>
								<p>享有特权：享受所推荐好友的每一笔投资额的0.9%（年化）净收益。</p>
								
								<p>达标条件：1.30天内自身投资金额超过5000元。</p>
								<p class="pd90">2.30天内好友总投资金额超过5,000,000元。</p>
								<p class="pd90">同时满足以上条件，并有一定客户资源可申请成为首席理财师。</p>
								
								<p>降级条件：1.连续30天，自身净资产低于5000元。</p>
								<p class="pd90">2.30天内好友返利低于500元或连续60天内没有新投资人产生。</p>
								<p class="pd90">同时满足以上条件，即失去首席理财师身份。</p>
							</div>
						</div>
						<p class="about-lcs">关于微积金理财师</p>
						<p class="about-cont">
							微积金理财师是面向全社会推出的惠人恵己的惠民理财计划，让您可以在理财的同时，通过发展投资人，获得高额的佣金。成为微积金理财师，对微积金进行推广，每发展一位用户（产生投资行为），微积金将根据理财师等级对投资人产生的投资金额给予您高额佣金。</p>
						<p class="about-lcs">作为微积金理财师，还将获得以下特权</p>

						<p class="vv">
							1.享有微积金观察员身份，可免费参加微积金实地考察；<br /> 2.尊享一对一个人财富管理规划顾问式服务；<br />
							3.提供微积金免费微信、短信通知服务，及时传递最新的金融信息与市场咨询；<br />
							4.免费参加微积金全年邀约活动（美食之旅、红酒品鉴、文化之旅、理财讲座）；<br />
							5.优化享受微积金平台各项促销、优惠服务。
						</p>


						<p class="about-lcs">微积金为您提供</p>

						<div class="module">
							<ul>
								<li><a class="img1 m_bg"></a> <span class="span1">宣传物料</span>


								</li>
								<li><a class="img2 m_bg"></a> <span class="span1">技术支持</span>
								</li>
								<li><a class="img3 m_bg"></a> <span class="span1">理财讲座</span>
								</li>
								<li><a class="img4 m_bg"></a> <span class="span1">营销课程</span>
								</li>
								<li><a class="img5 m_bg"></a> <span class="span1">交流聚会</span>
								</li>
							</ul>
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
