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
	href="${pageContext.request.contextPath}/css/giftPage.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<!-- 分页需要的CSS和JS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/paging/style.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/paging/jquery.paginate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/message/giftboxMessage.js"></script>
</head>
<body>
	<!----头部---->
	<jsp:include page="${pageContext.request.contextPath}/top.jsp"></jsp:include>
	<!--中间-->
	<div class="content">
		<div class="myAccount">
			<!-- 左边通用页面 -->
			<jsp:include
				page="${pageContext.request.contextPath}/user/accountLeft.jsp"></jsp:include>
			<div class="rechargeTakeCash fr">
				<div class="rT">
					<div class="rTWrap">
						<div class="txTop">
							<b class="midLine"></b>
							<h2>礼品盒</h2>
						</div>
						<div class="giftDiv">
							<ul class="giftUl">
								<li type="0" class="active">红包</li>
								<li type="1" class="unactive">加息卡</li>
								<li type="2" class="unactive">提现券</li>
							 	<li type="3" class="unactive">其他奖品</li>   
							</ul>
						</div>

						<!--红包  -->
						<div class="gift-cnt" style="display: block;">
							<div class="card clear">
								<div class="card-left light-red fl">
									<div class="card-icon redbag-icon "></div>
								</div>
								<div class="card-mid dark-red fl ">
									<div class="caret-red"></div>
									<em class="rmb-icon fl">￥</em>
									<div class="num fl" id="useMoney"></div>
								</div>
								<div class="card-right fl dark-red-bg card-icon"></div>
								<a class="oneKey-use" onclick="useGiftAny()">一键使用</a>
							</div>

							<div class="gift-cnt-title clear">
								<a style="margin-right: 20px;">类型:</a>
								<ul id="typelist">
									<li status="0" class="active">未使用红包</li>
									<li status="1">已使用红包</li>
									<li status="2">过期红包</li>
								</ul>
								<span class="fr" id="noUseMoney"></span>
							</div>
							<div class="giftDetail">
								<table cellspacing="0" cellpadding="0">
									<thead>
										<tr>
											<th width=""  height="50" width="200">标题</th>
											<th width="120">金额</th>
											<th width="120">发放日期</th>
											<th width="120">生效日期</th>
											<th width="120" id="lastTime">失效日期</th>
											<th width="120">操作</th>
										</tr>
									</thead>
									<tbody class="cntDetail">
										<tr>
											<td colspan="7" height="50">暂无记录</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>



						<!--加息卡  -->
						<div class="gift-cnt">
							<div class="card clear">
								<div class="card-leftside clear">
									<div class="card-left light-yellow fl">
										<div class="card-icon rate-icon "></div>
									</div>
									<div class="card-mid dark-yellow fl ">
										<div class="caret-yellow"></div>
										<div class="num fl ml20" id="hikesUseRate"></div>
									</div>
									<div class="card-right fl dark-yellow-bg card-icon"></div>
								</div>

								<div class="card-rightside">
									<div class="card-topbar">
										加息活动持续到6月30号&nbsp;&nbsp;&nbsp;<a class="use-now" href="/borrow/borrowList">立即使用</a>
									</div>
									<div class="card-bottombar"><a class="kill" href="/utilpage/toPage/wordExplain#miao" target="_blank" style="color:#00a0e9;">如何获取加息卡？</a>（加息卡所获取的额外收益会以红包的方式发放到您的礼品盒中）</div>
								</div>
							</div>


							<div class="card-cnt">
								<div class="cnt-box clear">
									<div class="cnt-title">主题</div>
									<div class="cnt-num">加息百分比</div>
									<div class="cnt-time">发送时间</div>
								</div>
								<ul id="cnt-items">
									
								</ul>
							</div>
						</div>



						<!-- 提现抵扣券 -->


						<div class="gift-cnt">
							<div class="card clear">
								<div class="card-leftside clear">
									<div class="card-left light-yellow fl">
										<div class="card-icon takecash-icon "></div>
									</div>
									<div class="card-mid dark-yellow fl ">
										<div class="caret-yellow"></div>
										<em class="rmb-icon fl">￥</em>
										<div class="num fl" id="cashHongbao"></div>
									</div>
									<div class="card-right fl dark-yellow-bg card-icon"></div>
								</div>

								<div class="card-rightside">
									<div class="card-topbar">完成新手任务，可以获得更多提现抵扣券</div>
									<div class="mt10">（提现时可以用提现抵扣券来抵扣手续费）</div>
								</div>
							</div>


							<div class="card-cnt">
								<div class="cnt-box clear">
									<div class="cnt-title">主题</div>
									<div class="cnt-num">金额</div>
									<div class="cnt-time">发送时间</div>
								</div>
								<ul id="cnt-items">
									
									
								</ul>
							</div>
						</div>
						
					
					<!-- 其他奖品 -->				
				  	<div class="gift-cnt">
							<div class="card clear">
								<div class="card-leftside clear">
									<div class="pockage" style="float: left;"><img src="/images/pockage.gif"></div>
								<div class="paragraph" style="float: left; margin-top: 20px;">	<p style="font-size: 14px; margin-left: 10px; line-height: 2em;">礼品使用方式请查看详情</p>
										<p style="font-size: 14px; margin-left: 10px;">参与微积金活动，就可以获得更多礼品</p></div>
								</div>

								<div class="card-rightside">
									<a class="activities"  href="/utilpage/toPage/aprilActivities"  style="background: #ff5555; padding: 7px 24px;color: #fff;font-size: 16px;line-height: 5.5em;  cursor: pointer;border-radius: 3px;">参与活动</a>
								</div>
							</div>
							<div class="card-cnt">
								<div class="cnt-box clear">
									<div class="title" style="float:left;width: 400px;text-align: center; ">标题</div>
									<div class="cnt-time" style="width: 300px;text-align: center;float: left;">发放日期</div>
								</div>
								<ul id="cnt-items">
								
		
								</ul>
						
							</div>			
						</div>
						
					
					
						<!-- 分页 -->
						<input type="hidden" id="pagCount" value="1">
						<div id="paging"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" value="${loginedSession.realStatus}" id="realStatus">
	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>

</body>
</html>
