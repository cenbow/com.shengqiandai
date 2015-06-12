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
	href="${pageContext.request.contextPath}/css/friendControl.css" />


<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>

<script src="${pageContext.request.contextPath}/js/myAccount.js"></script>

<!-- 分页需要的CSS和JS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/paging/style.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/paging/jquery.paginate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/friend/friendList.js"></script>


<script type="text/javascript"
	src="${pageContext.request.contextPath }/ZeroClipboard.js"></script>
<script type="text/javascript">
	var clip = null;
	$(function() {
		$('#searched').val('');

	});
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
							<h2>好友管理</h2>
						</div>

						<div class="friendManage">
							<ul>
								<li>邀请好友</li>

								<li>我的好友</li>
							</ul>
						</div>



						<div class="firends">

							<div class="friend invited0">
								<span class="invitIncome">我的返利收入：￥<font>${fee }</font>
									&nbsp; <a href="/friend/returnDetail">查看明细</a></span>
								<div class="link">
									<span>邀请链接：</span> <input type="text" id="link"
										value="${inviteUrl }" /> <a id="d_clip_button" class="copy"
										data-clipboard-target="link"> <b>复制</b>
									</a>


								</div>

								<div class="sweetTip">

									<table width="780" cellpadding="0" cellspacing="0">
										<tr>
											<td width="74" height="20">温馨提示：</td>
											<td width="706">请不要发送邀请信给不熟悉的人士,避免给别人带来不必要的困扰。</td>
										</tr>
										<tr>
											<td width="64" height="20">&nbsp;</td>
											<td width="716">请把下边的链接地址发给您的好友，这样您就成了他的上线用户。</td>
										</tr>

									</table>
								</div>


								<p class="p5">
									成为微积金理财师，您将充分享受互联网理财带来的高额收益，推荐投资人越多，奖励越多，给您带来无限收益空间！</p>

								<div class="licaishi">

									<table width="780" cellpadding="0" cellspacing="0"
										id="licaishi">
										<tr>
											<td width="260" height="48" class="licaiBg">理财师等级</td>
											<td width="260" class="licaiBg">回报率(年化)</td>
											<td width="260" class="licaiBg">等级说明</td>
										</tr>
										<tr>
											<td width="260" height="48">特约理财师</td>
											<td width="260">0.72%</td>
											<td width="260">自身投资达到100,000元</td>
										</tr>
										<tr>
											<td width="260" height="48">高级理财师</td>
											<td width="260">0.81%</td>
											<td width="260">好友总投资达到1,000,000元</td>
										</tr>
										<tr>
											<td width="260" height="48" class="lastboder">首席理财师</td>
											<td width="260" class="lastboder">0.90%</td>
											<td width="260" class="lastboder">好友总投资达到5,000,000元</td>
										</tr>
									</table>
								</div>
								<br /> <br />

							</div>

							<div class="friend myFriend">
								<div class="friendSearch">

									用户名：<input type="text" id="friendSearch" /> <a
										href="javascript:searchFriend();" class="btn78">搜索</a><input
										type="hidden" id="searched" value=""></input>
								</div>

								<div class="show-loading">
									<div class="loading"></div>
								</div>

								<div class="friendTable">
									<table width="780" cellpadding="0" cellspacing="0"
										id="myFriend">
										<tr>
											<td width="195" height="50" class="friendBg">好友用户名</td>
											<td width="195" class="friendBg">真实姓名</td>
											<td width="195" class="friendBg">注册时间</td>
											<td width="195" class="friendBg">用户级别</td>

										</tr>
										<tr>
											<td colspan="4">无好友信息</td>
										</tr>

									</table>
									<input type="hidden" id="pagCount" value="1">
									<div id="paging" style="display: none;"></div>
								</div>

							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		// 定义一个新的复制对象
		var clip = new ZeroClipboard(
				document.getElementById("d_clip_button"),
				{
					moviePath : "${pageContext.request.contextPath }/ZeroClipboard.swf"
				});

		// 复制内容到剪贴板成功后的操作
		clip.on('complete', function(client, args) {
			alert("链接已成功复制到您的剪切板中");
		});
	</script>

	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
</body>
</html>


