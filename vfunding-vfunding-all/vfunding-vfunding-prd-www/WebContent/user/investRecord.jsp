<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="date" uri="http://cn.vfunding.vfunding.prd.www/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd");
	//	Date date = new Date();
	Calendar yest2 = Calendar.getInstance();
	yest2.add(Calendar.DATE,-7);
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

<title>微积金-投资记录</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/investRecord.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script src="${pageContext.request.contextPath}/js/myAccount.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/account3.css" />
<!-- 分页需要的CSS和JS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/paging/style.css" media="screen" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/paging/jquery.paginate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/investRecord.js"></script>
</head>
<body>
<div id="export">
	<form action="${pageContext.request.contextPath }/export/tenderExcel" id="exportForm">
		<input type="hidden" id="type" name="type" value="1"/>
		<input type="hidden" id="status" name="status" value="1"/>
		<input type="hidden" id="startTime" name="startTime"/>
		<input type="hidden" id="endTime" name="endTime"/>
	</form>
</div>
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
							<h2>投资记录</h2>
							<div class="excel">
								<a id="exportExcel">导出EXCEL</a>
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
							</div>
							<div class="kindsOfdate fl" id="time_">
								<ul>
									<li class="liColor">近一周</li>
									<li>1个月</li>
									<li>3个月</li>
									<li>半年</li>
								</ul>
							</div>
						</div>
						<div class="exchangeRecords">
							<div class="startEndDate fl">交易类型</div>
							<div class="exchangeType fl" id="type_">
								<ul>
									<li class="liColor">投标记录</li>
									<li>收款记录</li>
								</ul>
							</div>
						</div>
						<div class="exchangeRecords">
							<div class="startEndDate fl">交易状态</div>
							<div class="exchangeDetail fl" id="detail1_">
								<ul>
									<li class="liColor">成功</li>
									<li>关闭</li>
								</ul>
							</div>
							<div class="exchangeDetail fl" id="detail2_"
								style="display: none">
								<ul>
									<li class="liColor">待收</li>
									<li>已收</li>
								</ul>
							</div>
						</div>
						<div class="show-loading"><div class="loading"></div></div>
						<div class="dataTableWrap">
							<div class="dataShow">
								<table class="recordTable" width="780" cellpadding="0"
									cellspacing="0">
									<tr
										style="background: none repeat scroll 0% 0% rgb(245, 247, 248);">
										<td width="186" class="top-border">项目名称</td>
										<td width="74" class="top-border">净收益率</td>
										<td width="100" class="top-border">投资金额</td>
										<td width="100" class="top-border">待收金额</td>
										<td width="100" class="top-border">已收金额</td>
										<td width="72" class="top-border">已收期次</td>
										<td height="50" width="88" class="top-border">投标时间</td>
										<td width="88"  class="top-border">操作</td>
									</tr>
									<!-- 已成功项目默认显示 -->
									<c:forEach items="${page.rows}" var="v">
										<tr>
											<td width="186" align="left"><a
												href="${pageContext.request.contextPath}/borrow/borrowDetail/${v.id}"
												target="_blank">${v.borrowName}</a></td>
											<td width="74">${v.apr-v.bfee-v.gfee }%</td>
											<td width="100">${v.account }元</td>
											<td width="100">${v.repaymentAccount }元</td>
											<td width="100">${v.repaymentYesAccount }元</td>
											<td width="72">${v.allOrder-v.noOrder }/${v.allOrder}</td>
											<td height="50" width="88"><date:date
													parttern="yyyy-MM-dd HH:mm:ss" value="${v.addtime}" /></td>
											<td width="88"><c:if test="${v.status == 1}">投资成功</c:if>
												<c:if test="${v.status == 2}">撤标</c:if> <c:if
													test="${v.status == 5}">待审</c:if></td>
										</tr>
									</c:forEach>
									<c:if test="${empty page.rows}">
										<tr>
											<td width="60" height="50" colspan="8">暂无记录</td>
										</tr>
									</c:if>
								</table>
							</div>

							<!-- 保存一共多少页 -->
							<c:if test="${page.total eq 0 }">
								<input type="hidden" id="pagCount" value="1">
							</c:if>
							<c:if test="${page.total gt 0 }">
								<input type="hidden" id="pagCount" value="${page.total/10}">
							</c:if>
							<!-- 保存从第几页开始-->
							<input type="hidden" id="pagStart" value="1">
							<!-- 保存需要查的类型-->
							<input type="hidden" id="pagType" value="0"> <input
								type="hidden" id="searchType" value="0">
							<c:if test="${page.total gt 0 }">
								<div id="paging"></div>
							</c:if>
							<c:if test="${page.total eq 0 }">
								<div id="paging" style="display: none;"></div>
							</c:if>

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
