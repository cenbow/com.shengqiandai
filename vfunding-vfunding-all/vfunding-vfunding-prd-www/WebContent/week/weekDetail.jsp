<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="date" uri="http://cn.vfunding.vfunding.prd.www/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="微积金(www.vfunding.cn) 整合了民营汽车借贷机构、二手车商、第三方汽车服务机构等传统领域企业，形成了独特的针对汽车提供投融资对接服务的创新型O2O互联网汽车金融服务模式。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、汽车贷款等。" />
<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|微贷|红包|基金|微基金|小额贷款|微财富|宜车贷|二手车抵押|车贷|托管" />
<title>微积金，中国领先的互联网汽车金融服务平台_理财_理财产品_投融资_理财需求_投资_投资标的_财富_财富成长_手机理财_微信理财_微理财</title>
<link rel="stylesheet" type="text/css" href="/css/zyb_detail.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script
	src="${pageContext.request.contextPath}/js/borrow/borrowDetail.js"></script>
<!-- 弹出层JS -->
<script
	src="${pageContext.request.contextPath}/js/artDialog/artDialog.js?skin=default"></script>
<script
	src="${pageContext.request.contextPath}/js/artDialog/plugins/iframeTools.js"></script>

<!-- 分页需要的CSS和JS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/paging/style.css"
	media="screen" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/paging/jquery.paginate.js"></script>


<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/week/weekTender.js"></script>


</head>
<body>
	<!---头部--->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>
	<!--中间-->

	<div class="content">
		<div class="zyb-detail-top clear">
			<div class="detail-left fl box-shadow">
				<div class="detail-left-leftside fl">
					<div class="detail-title">
						<input type="hidden" value="${week.id }" id="weekId"> <input
							type="hidden" value="${week.singleMin }" id="weekSingleMin">
						<input type="hidden" value="${week.buyBase }" id="weekBuyBase">
						<input type="hidden" value="${week.sharePrice }"
							id="weekSharePrice"> <span>${week.name }</span> <a
							href="#">（用户间）债权转让协议</a>
					</div>
					<div class="safe-promise">100%本息担保</div>
					<div class="detail-infos">
						<div class="detail-info-item clear">
							<div class="detail-info-item-left fl">
								<p class="licai-limit">理财期限</p>
								<div class="licai-days">
									<span class="days-num">${week.timeLimit }</span> <span
										class="days-unit">天</span>
								</div>
							</div>
							<div class="detail-info-item-right fl">
								<div class="remain-share">剩余份额</div>
								<div class="remain-nums">
									<span class="days-num">${week.shareCount-week.shareYescount }</span>
									<span class="days-unit">份</span>
								</div>
							</div>
						</div>
						<div class="detail-info-item clear">
							<div class="detail-info-item-left fl">
								<p class="licai-limit">年华收益率</p>
								<div class="licai-days">
									<span class="days-num">${week.apr }</span> <span
										class="days-unit">%</span>
								</div>
							</div>
							<div class="detail-info-item-right fl">
								<div class="remain-share">剩余时间</div>
								<div class="remain-time">
									<div class="time-bg hour1"></div>
									<div class="time-bg hour2"></div>
									<div class="time-dot"></div>
									<div class="time-bg minute1"></div>
									<div class="time-bg minute2"></div>
									<div class="time-dot"></div>
									<div class="time-bg second1"></div>
									<div class="time-bg second2"></div>
								</div>
							</div>
						</div>
					</div>
					<p class="detai-startTime">
						认购开始时间：
						<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
							value="${week.saleTime}" />
					</p>
					<p class="detai-endTime">
						<span></span> 认购截止时间：
						<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
							value="${week.expireTime}" />

						<input type="hidden"
							value="<fmt:formatDate pattern="yyyy/MM/dd HH:00:00"
							value="${week.expireTime}" />"
							id="endTime">
					</p>

					<div class="start-pot clear">
						<div class="start-pot-left fl">认购起点:&nbsp;</div>
						<div class="start-pot-right fl">
							1元人民币为1份，认购起点份额为100份<br />认购份额为100份的整数倍递增。
						</div>
					</div>
				</div>
				<c:choose>
					<c:when test="${not empty loginedSession}">
						<div class="detail-left-rightside fr">
							<div class="detail-title">认购份数</div>
							<div class="share-input-box">
								<input type="text" id="shares"> 份
							</div>
							<div class="buy-info">
								<p>
									认购金额：<span id="buyMoney">0</span>元
								</p>
								<p>
									<input type="hidden" value="${account.useMoney }" id="useMoney">
									可用金额：${account.useMoney }元 &nbsp;&nbsp;&nbsp;&nbsp;<a
										href="${pageContext.request.contextPath }/account/recharge"
										id="recharge" target="_blank">充值</a>
								</p>
								<p class="share-tip"></p>
							</div>

							<div class="pwd-title">支付密码：</div>
							<div class="pwd-box">
								<input type="password" id="payPwd" />
							</div>
							<div class="pwd-tip"></div>
							<div class="conform-btn">
								<a class="btn216" href="javascript:verifyInvest();">确认购买</a>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="detail-left-rightside fr">
							<div class="detail-title">认购份数</div>
							<div class="share-input-box">
								<input type="text" id="shares" disabled="disabled"> 份
							</div>
							<div class="buy-info">
								<p>认购金额：0元</p>
								<p>
									您的可用余额 &nbsp;&nbsp;<a
										href="/goLogin?returnUrl=/week/weekDetail/${week.id}"
										style="color: #00a0e9;">登录&nbsp;&nbsp;</a>后可见
								</p>
								<p class="share-tip"></p>
							</div>
							<div class="pwd-title">支付密码：</div>
							<div class="pwd-box">
								<input type="password" id="payPwd" disabled="disabled" />
							</div>
							<div class="pwd-tip"></div>
							<div class="conform-btn">
								<a href="/goLogin?returnUrl=/borrow/borrowDetail/${week.id}"
									class="btn216 confirmBuy">去登录</a>
							</div>
						</div>
					</c:otherwise>
				</c:choose>

			</div>
			<div class="detail-right fr box-shadow"></div>
		</div>
		<div class="zyb-detail-mid box-shadow">
			<h2 class="product-info">产品说明</h2>
			“周盈宝”是“微积金”推出的一种创新型稳健理财产品。 其特点是：周期短、资金运用灵活，以汽车抵押／质押作为担保。
			自理财产品计划成立之日起每个认购开放日发行一个或多个子计划，每个子计划的投资周期均为：7天。 <span class="more">[更多...]</span><span
				class="more-cnt"> 认购起点：1元人民币为1份，认购起点份额为100份，认购份额为100份的整数倍递增。<br />
				收益起息日：采用T+1计息方式（即投资者认购后的下一个工作日为起息日）。注：投资者在不同时间段进行认购，起息时间可能会有所不同。
				到期日：起息日后的第7个自然日为各子计划到期日。<br />
				收益计算公式：投资者获得的理财收益金额=投资者认购本理财计划项下的某子计划总份额×投资者认购该子计划预期年化收益率×7÷365
				(金额精确到小数点后 2位)。如微积金提前终止该子计划，则实际理财天数为自该子计划成立日（含）至提前终止日（不含）期间的天数。
				例：投资者认购份额为10000份，预期年化收益率为9%，则投资者收益金额=10000×9%×7/365=17.26元。<br />
				投资者可以在理财产品开放时间内进行认购。投资者成功提交认购申请后，不得撤销申请：产品成立后，在产品到期前不得赎回。

			</span> <span class="shouqi">[收起...]</span>

		</div>

		<div class="zyb-detail-bottom box-shadow">

			<h2>产品详情</h2>
			<div class="product-title clear">
				<ul>
					<li class="liColor current">标的详情</li>
					<li class="liColor">认购记录</li>
				</ul>
			</div>
			<div class="tableList table-detail" style="display: block;">
				<table cellpadding="0" cellspacing="0" width="100%"
					class="tableDetail">
					<tr>
						<th>标的</th>
						<th>借款金额</th>
						<th>审核时间</th>
						<th>查看详情</th>
					</tr>
					<c:forEach var="weekBorrowList" items="${weekBorrowList }">
						<tr>
							<td>${weekBorrowList.name }</td>
							<td>${weekBorrowList.account }</td>
							<td><fmt:formatDate value="${weekBorrowList.verifyTime }" pattern="yyyy-MM-dd"/></td>
							<td><a class="lookInto" name="showBorrowDetail" onclick="showBorrowDetail(${weekBorrowList.id})">查看详细</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="tableList table-detail">
				<table cellpadding="0" cellspacing="0" width="100%"
					class="tableDetail" id="listTable">
					<tr>
						<th>投资人</th>
						<th>投资份额</th>
						<th>认购时间</th>
						<th>状态</th>
					</tr>
				</table>
			</div>
			<input type="hidden" id="pagCount" value="1">
			<div id="paging"></div>
		</div>
	</div>
	</div>




	<!--弹出框-->

	<div style="width: 800px;" class="dialog-main" id="carDialog">

		<div class="dialog-head clear">
			<h2>保时捷凯宴抵押借款</h2>
			<a class="closeModal r3" href="javascript:;"
				style="line-height: 24px; text-align: center;">X</a>
		</div>
		<div id="carEntry" class="clear">
			<div class="car-info">
				<h3>车辆信息</h3>
				<table cellspacing="0" cellpadding="0" width="100%" id="car-info">
					<thead>
						<tr>
							<th>车辆品牌</th>
							<th>贷款金额</th>
							<th>审核时间</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>保时捷</td>
							<td>15,000,000.00</td>
							<td>2014-11-12</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="profile-audit">
				<h3>资料审核</h3>
				<table id="ziliaoshenhe" cellpadding="0" cellspacing="0"
					width="100%">
					<thead>
						<tr>
							<th>借款人身份证明</th>
							<th>借款人资产证明</th>
							<th>机动车登记证</th>
							<th>机动车行驶证</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><div class="pass tipicon"></div></td>
							<td><div class="pass tipicon"></div></td>
							<td><div class="pass tipicon"></div></td>
							<td><div class="pass tipicon"></div></td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th>车主身份证</th>
							<th>车辆保险单</th>
							<th>车辆购置完税证明</th>
							<th>车辆抵押合同及借条</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><div class="pass tipicon"></div></td>
							<td><div class="pass tipicon"></div></td>
							<td><div class="pass tipicon"></div></td>
							<td><div class="pass tipicon"></div></td>
						</tr>
					</tbody>

				</table>

			</div>

			<div class="biao-pic">
				<h3>标的图片</h3>
				<div class="pics">
					<img src="" alt="标的图片" /> <img src="" alt="标的图片" /> <img src=""
						alt="标的图片" /> <img src="" alt="标的图片" />
				</div>
			</div>


		</div>
		<div id="dialog-foot" class="clear">

			<div class="btn216 closeModal close-btn">关闭</div>

		</div>
	</div>
	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
	<script src="/js/jDialog.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {

			//倒计时
			var now = new Date();
			var endDate = new Date($("#endTime").val());
			var leftTime = endDate.getTime() - now.getTime();
			var leftsecond = leftTime / 1000;
			var intDiff = parseInt(leftsecond);//倒计时总秒数量

			var hour1 = $(".hour1");
			var hour2 = $(".hour2");
			var minute1 = $(".minute1");
			var minute2 = $(".minute2");
			var second1 = $(".second1");
			var second2 = $(".second2");

			function timer(intDiff) {
				window.setInterval(function() {
					var day = 0, hour = 0, minute = 0, second = 0;//时间默认值
					if (intDiff > 0) {
						//day = Math.floor(intDiff / (60 * 60 * 24));
						//hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
						hour = Math.floor(intDiff / (60 * 60));
						minute = Math.floor(intDiff / 60) - (hour * 60);
						//console.log(minute);
						second = Math.floor(intDiff) - (hour * 60 * 60)
								- (minute * 60);
						if (hour <= 9) {
							hour = "0" + hour
						}
						if (minute <= 9)
							minute = '0' + minute;
						if (second <= 9)
							second = '0' + second;
						//console.log(minute);
						var arr = day.toString().split("");
						var arr2 = hour.toString().split("");
						var arr3 = minute.toString().split("");
						var arr4 = second.toString().split("");
						hour1.html(arr2[0]);
						hour2.html(arr2[1]);
						minute1.html(arr3[0]);
						minute2.html(arr3[1]);
						second1.html(arr4[0]);
						second2.html(arr4[1]);

					} else {

						$(".remain-time").empty();
						$(".remain-time").html("标的已结束");
					}
					intDiff--;
				}, 1000);
			}
			timer(intDiff);

			$("span.more").on("click", function() {
				$(this).hide();
				$("span.more-cnt,.shouqi").show();
			});

			$(".shouqi").on("click", function() {
				$(this).hide();
				$("span.more-cnt").hide();
				$(".more").show();
			});

			//弹窗
// 			$("a.lookInto").on("click", function() {
// 				webUtil.jDialog.Modal("carDialog", "carEntry");
// 			});

			//选项卡
			$("#paging").hide();
			$(".product-title li").on("click", function() {
				var index = $(".product-title li").index($(this));
				$(this).addClass("current").siblings().removeClass("current");
				$(".tableList").eq(index).show().siblings(".tableList").hide();
				if (index == 0) {
					$("#paging").hide();
				} else {
					$("#paging").show();
				}
			});
		});
		
		function showBorrowDetail(id){
				$.ajax({
 					url:"/weekBorrow/weekBorrowById?id="+id,
//					url:"/utilpage/toPageAjax/regAgreement",
					type : "post",
					dataType:"text",
					success:function(result) {
						art.dialog({
							title : '标的详情',
							content : result,
							width : 1000,
							height :500,
							fixed : false,
							drag: false,
							lock : true,
						});
					}
			　　 });
		}
	</script>

</body>


</html>
