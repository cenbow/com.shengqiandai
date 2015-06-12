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
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd");
	//	Date date = new Date();
	//	String sDate  = fmat.format(date);
	//	sDate = sDate + " 00:00:00";
	Calendar yest2 = Calendar.getInstance();
	yest2.add(Calendar.DATE, -7);
	String sDate = fmat.format(yest2.getTime());

	Calendar yest = Calendar.getInstance();
	//yest.add(Calendar.DAY_OF_YEAR, 0);
	String eDate = fmat.format(yest.getTime());
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="description"
	content="微积金(www.vfunding.cn) 整合了民营汽车借贷机构、二手车商、第三方汽车服务机构等传统领域企业，形成了独特的针对汽车提供投融资对接服务的创新型O2O互联网汽车金融服务模式。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、汽车贷款等。" />
<meta name="keywords"
	content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|微贷|红包|基金|微基金|小额贷款|微财富|宜车贷|二手车抵押|车贷|托管" />
<title>微积金，中国领先的互联网汽车金融服务平台_理财_理财产品_投融资_理财需求_投资_投资标的_财富_财富成长_手机理财_微信理财_微理财</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/zijinRecord.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/account3.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script src="${pageContext.request.contextPath}/js/myAccount.js"></script>

<!-- 分页需要的CSS和JS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/paging/style.css"
	media="screen" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/paging/jquery.paginate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/accountLog.js"></script>
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
							<h2>资金记录</h2>
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
							<div class="kindsOfdate fl">
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
							<div class="exchangeType2 fl">
								<ul>
									<li class="liColor">全部</li>
									<li>充值</li>
									<li>提现</li>
									<li>投资</li>
									<li>回款记录</li>
									<li>扣除冻结款</li>
									<li>充值手续费</li>
									<li>投标失败资金返回</li>
									<li>借款入帐</li>
									<li>提现冻结</li>
									<li>保证金</li>
									<li>还款</li>
									<li>利息管理费用</li>
									<li>余额生息</li>
								</ul>
							</div>
							<div class="showMore fr" id="showMore"></div>
						</div>


						<div class="show-loading">
							<div class="loading"></div>
						</div>

						<div class="dataTableWrap">
							<div class="dataShow">
								<table class="recordTable" width="780" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="48" width="60">类型</td>
										<td width="86">操作金额</td>
										<td width="96">总金额</td>
										<td width="86">可用金额</td>
										<td width="86">冻结金额</td>
										<td width="96">待收金额</td>
										<td width="70">交易对方</td>
										<td width="96">交易时间</td>
										<td width="100">备注信息</td>
									</tr>
									<c:forEach items="${page.rows}" var="v">
										<tr>
											<td height="48" width="60"><c:if
													test="${v.type == 'recharge'}">充值</c:if> <c:if
													test="${v.type == 'tender'}">投资</c:if> <c:if
													test="${v.type == 'cash'}">提现</c:if> <c:if
													test="${v.type == 'collection'}">回款</c:if> <c:if
													test="${v.type == 'recharge_fee'}">提现手续费</c:if> <c:if
													test="${v.type == 'recharge_success'}">提现成功</c:if> <c:if
													test="${v.type == 'recharge_false'}">提现失败</c:if> <c:if
													test="${v.type == 'invest_false'}">投资失败回款</c:if> <c:if
													test="${v.type == 'borrow_success'}">借款入帐</c:if> <c:if
													test="${v.type == 'cash_frost'}">提现冻结</c:if> <c:if
													test="${v.type == 'invest_flow'}">流标退款</c:if> <c:if
													test="${v.type == 'margin'}">保证金</c:if> <c:if
													test="${v.type == 'repayment' || v.type == 'invest_repayment'}">还款</c:if>
												<c:if test="${v.type == 'tender_mange'}">利息管理费用</c:if> <c:if
													test="${v.type == 'invest'}">扣除冻结款</c:if> <c:if
													test="${v.type == 'ticheng'}">提成</c:if>
													<c:if test="${v.type=='sinaBonus_addmoney'}">余额生息</c:if>
													</td>
											<td width="86"><fmt:formatNumber value="${v.money}"
													pattern="#,##0.00#" />元</td>
											<td width="96"><fmt:formatNumber value="${v.total}"
													pattern="#,##0.00#" />元</td>
											<td width="86"><fmt:formatNumber value="${v.useMoney}"
													pattern="#,##0.00#" />元</td>
											<td width="86"><fmt:formatNumber value="${v.noUseMoney}"
													pattern="#,##0.00#" />元</td>
											<td width="96"><fmt:formatNumber value="${v.collection}"
													pattern="#,##0.00#" />元</td>
											<td width="70">结算中心</td>
											<td width="96"><date:date parttern="yyyy-MM-dd HH:mm:ss"
													value="${v.addtime}"></date:date></td>
											<td width="100">${v.remark}</td>
										</tr>
									</c:forEach>
									<c:if test="${empty page.rows}">
										<tr>
											<td width="60" height="50" colspan="9">暂无记录</td>
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
							<input type="hidden" id="pagType" value="0">
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
