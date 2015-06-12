<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
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
	href="${pageContext.request.contextPath}/css/myRepay.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/myAccount.js"></script>
<!-- 分页需要的CSS和JS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/paging/style.css" media="screen" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/paging/jquery.paginate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/myRepay.js"></script>
<!-- 弹出层JS -->
<script
	src="${pageContext.request.contextPath}/js/artDialog/artDialog.js?skin=default"></script>
<script
	src="${pageContext.request.contextPath}/js/artDialog/plugins/iframeTools.js"></script>
<!-- 时间控件JS -->
<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
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

			<!-- 还款主页面 -->
			<div class="rechargeTakeCash fr">
				<div class="rT box-shadow">
					<div class="rTWrap">
						<div class="txTop">
							<b class="midLine"></b>
							<h2>我的还款</h2>
							<!-- 	<div class="excel">
								<a href="#">导出EXCEL</a>
							</div> -->
						</div>
						<div class="ss">

							<div class="w700">
								<table width="280" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60" height="30">关键字</td>
										<td width="120"><input type="text" id="keyword"
											value="${vo.name }" tabindex="1" /></td>
										<td width="86"><input type="button" value="查询"
											class="btn78" onclick="queryRepay()" tabindex="2" /></td>
									</tr>
								</table>
							</div>
						</div>

						<div class="exchangeRecords">
							<div class="startEndDate fl">&nbsp;类型</div>
							<div class="exchangeType fl">
								<ul>
									<li class="liColor" id="daihuan">待还借款</li>
									<li  id="yihuan">已还借款</li>
								</ul>
							</div>
						</div>
						
						<div class="show-loading"><div class="loading"></div></div>
						<div class="dataTableWrap">
							<div class="dataShow">
								<table class="recordTable" width="780" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="50" width="178"  class="top-border">借款标题</td>
										<td width="70">借款期次</td>
										<td width="88">应还款时间</td>
										<td width="115">本期应还本金</td>
										<td width="79">利息</td>
										<td width="90">逾期利息</td>
										<td width="64">逾期天数</td>
										<td width="96">状态/操作</td>
									</tr>

								</table>
							</div>
							<!-- 保存一共多少页 -->
							<input type="hidden" id="pagCount" value="1">

							<!-- 保存从第几页开始-->
							<input type="hidden" id="pagStart" value="1">
							<!-- 保存需要查的类型-->
							<input type="hidden" id="pagType" value="0">
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
