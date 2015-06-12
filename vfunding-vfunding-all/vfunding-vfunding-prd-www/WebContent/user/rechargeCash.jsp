<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd");
	//	Date date = new Date();
	//	String sDate  = fmat.format(date);
	//	sDate = sDate + " 00:00:00";
	Calendar yest2 = Calendar.getInstance();
	yest2.add(Calendar.DAY_OF_YEAR, 0);
	String sDate = fmat.format(yest2.getTime());

	Calendar yest = Calendar.getInstance();
	//yest.add(Calendar.DAY_OF_YEAR, 0);
	String eDate = fmat.format(yest.getTime());
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="微积金(www.vfunding.cn) 互联网金融服务平台旨在为有资金需求和理财需求的个人或企业搭建一个真实诚信、安全透明、快捷高效的互联网服务平台和投资理财渠道。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、担保贷款等。" />

<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|工薪贷|生意贷|基金|微基金|小额贷款|投融资|宜车贷|二手车抵押|押车|担保" />

<title>微积金-充值提现记录</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/rechargeTakeCash.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/tip.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/tip.min.js"></script>
<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script src="${pageContext.request.contextPath}/js/myAccount.js"></script>

<!-- 分页需要的CSS和JS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/paging/style.css" media="screen" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/paging/jquery.paginate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/rechargeCash.js"></script>

<script>
	$(function() {
		$("input.time").on('click', function() {
			WdatePicker();
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
			<!-- 中间页面 -->
			<div class="rechargeTakeCash fr">
				<div class="rT box-shadow">
					<div class="rTWrap">

						<div class="txTop">
							<b class="midLine"></b>
							<h2>充值提现</h2>
							<!-- 	<div class="excel">
								<a href="#">导出EXCEL</a>
							</div> -->
						</div>
						<div class="accountRemaining">
							<div class="clear">
								<div class="remainingShow fl">
									<div class="wordExplain fl">
										<p class="account_yuer">可用余额</p>
										<p class="canUseRemaining">
											待收总额 <a href="#" title="待收本金、待收利息之和" class="remainExplain"></a>
										</p>
									</div>
									<div class="moneyShow fr">
										<p class="account_yuer">${account.useMoney}元</p>
										<p class="canUseRemaining">${account.collection}元</p>
									</div>
								</div>
								<div class="rtBtns fr">
									<a href="${pageContext.request.contextPath }/account/recharge"
										class="czBtn fl "> <i class="czicon rtBtn_pic"></i> 充值
									</a> <a
										href="${pageContext.request.contextPath }/accountCash/takeCash"
										class="txBtn fr "> <i class="txicon rtBtn_pic"></i> 提现
									</a>
								</div>

							</div>

							<div class="getAall">
								<table width="450" cellspacing="0" cellpadding="0"
									class="getAllTable">
									<tr>
										<td width="80" height="24">累计充值</td>
										<td><c:choose>
												<c:when test="${not empty sumRecharge&&sumRecharge>0 }">
											${sumRecharge}
											</c:when>
												<c:otherwise>
											0.00
											</c:otherwise>
											</c:choose>元</td>
										<td width="80" height="24">累计提现</td>
										<td><c:choose>
												<c:when test="${not empty sumCash&&sumCash>0 }">
											${sumCash}
											</c:when>
												<c:otherwise>
											0.00
											</c:otherwise>
											</c:choose>元</td>
									</tr>
								</table>
							</div>

						</div>
						<div class="date">
							<div class="startEndDate fl">起止日期</div>
							<div class="dateInput fl">
								<input type="text" id="d5221" class="Wdate startTime"
									value="<%=sDate%>"
									onFocus="WdatePicker({onpicking:function(dp){myendtime(dp.cal.getNewDateStr(),null);},maxDate: '#F{$dp.$D(\'d5222\')}'})" />
								- <input type="text" id="d5222" class="Wdate endTime"
									value="<%=eDate%>"
									onFocus="WdatePicker({onpicking:function(dp){myendtime(null,dp.cal.getNewDateStr());},minDate: '#F{$dp.$D(\'d5221\')}'})" />
								<%-- 	 <input
									id="d5221" class="Wdate startTime" type="text"
									value="<%=sDate%>"
									onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},maxDate:'#F{$dp.$D(\'d5222\')}'})" />
									- <input id="d5222" class="Wdate endTime" type="text"
									value="<%=eDate%>"
									onFocus="WdatePicker({onpicking:function(dp){myendtime(dp.cal.getNewDateStr());},minDate:'#F{$dp.$D(\'d5221\')}'})" /> --%>
							</div>
							<!-- minDate:'#F{$dp.$D(\'d5221\')}' -->
							<div class="kindsOfdate fl">
								<ul>
									<li class="liColor">今天</li>
									<li>近一周</li>
									<li>1个月</li>
									<li>3个月</li>
									<li>半年</li>
								</ul>
							</div>

						</div>

						<div class="exchangeRecords">
							<div class="startEndDate fl">交易类型</div>
							<div class="exchangeType fl">
								<ul>
									<li class="liColor">充值</li>
									<li>提现</li>
								</ul>
							</div>

						</div>
						<div class="show-loading">
							<div class="loading"></div>
						</div>
						<div class="dataTableWrap">
							<div class="dataShow">
								<table class="recordTable" width="780" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height='48' width='156'>时间</td>
										<td width='154'>充值金额</td>
										<td width='154'>到账金额</td>
										<td width='180'>状态</td>
									</tr>
									<%-- 	<c:forEach var="rechange" items="${page.rows }">
										<tr class="recordTableTr">
											<td height="48" width="156">${rechange.addtime}</td>
											<td width="144">${rechange.actionType}</td>
											<td width="154">${rechange.money}</td>
											<td width="154">${rechange.fee}</td>
											<td width="180">${rechange.status}</td>
										</tr>
									</c:forEach>
									<c:if test="${fn:length(page.rows)==0}">
										<tr>
											<td height="48" colspan="5">没有记录</td>
										</tr>
									</c:if> --%>
								</table>
							</div>
						</div>
						<!-- 保存一共多少页 -->

						<input type="hidden" id="pagCount" value="1">


						<!-- 保存从第几页开始-->
						<input type="hidden" id="pagStart" value="1">
						<!-- 保存需要查的类型-->
						<input type="hidden" id="pagType" value="recharge">
						<div id="paging"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		$(function() {

			$(".remainExplain").poshytip({
				className : 'tip-yellowsimple',
				showTimeout : 1,
				alignTo : 'target',
				alignX : 'center',
				offsetY : 8,
				allowTipHover : true
			});

		})
	</script>



	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
</body>
</html>

