<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd");
	//	Date date = new Date();
	//	String sDate  = fmat.format(date);
	//	sDate = sDate + " 00:00:00";
	Calendar yest2 = Calendar.getInstance();
	//yest2.add(Calendar.DAY_OF_YEAR, -30);
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

<title>微积金-借款记录</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/borrowRecord.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/account3.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script src="${pageContext.request.contextPath}/js/myAccount.js"></script>

<!-- 分页需要的CSS和JS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/paging/style.css" media="screen" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/paging/jquery.paginate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/myRecord.js"></script>
</head>
<body>
	<!---头部------>
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
							<h2>借款记录</h2>
							<!-- 	<div class="excel">
								<a href="#">导出EXCEL</a>
							</div> -->
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
								<%-- <input id="d5221" class="Wdate startTime" type="text"
									value="<%=sDate%>"
									onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},maxDate:'#F{$dp.$D(\'d5222\')}'})" />
								-<input id="d5222" class="Wdate endTime" type="text"
									value="<%=eDate%>"
									onFocus="WdatePicker({onpicking:function(dp){myendtime(dp.cal.getNewDateStr());},minDate:'#F{$dp.$D(\'d5221\')}'})" /> --%>
							</div>
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
									<li class="liColor">全部</li>

									<li>初审被拒</li>
									<li>复审被拒</li>
									<li>还款中</li>
									<li>流标</li>
									<li>已还款</li>

								</ul>
							</div>

						</div>

						<div class="show-loading">
							<div class="loading"></div>
						</div>
						<div class="dataTableWrap" style="overflow: hidden;">
							<div class="dataShow">
								<table class="recordTable" width="780" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="50" width="133" class="tdBg"  class="top-border">提交时间</td>
										<td width="267" class="tdBg" >借款标题</td>
										<td width="94" class="tdBg" >借款期限</td>
										<td width="156" class="tdBg" >借款金额</td>
										<td width="131" class="tdBg" >状态</td>
									</tr>
								</table>
							</div>
							<input type="hidden" id="pagCount" value="1">
							<!-- 保存从第几页开始-->
							<input type="hidden" id="pagStart" value="1">
							<!-- 0是所有,1代表待还,2代表已还-->
							<input type="hidden" id="pagType" value="0">
							<!-- 保存borrow-status的值-->
							<input type="hidden" id="pagStatus" value="10">

							<div id="paging" style="display: none;"></div>
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
