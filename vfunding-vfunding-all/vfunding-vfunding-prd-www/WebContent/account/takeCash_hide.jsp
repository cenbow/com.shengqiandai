<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="description"
	content="微积金(www.vfunding.cn) 整合了民营汽车借贷机构、二手车商、第三方汽车服务机构等传统领域企业，形成了独特的针对汽车提供投融资对接服务的创新型O2O互联网汽车金融服务模式。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、汽车贷款等。" />
<meta name="keywords"
	content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|微贷|红包|基金|微基金|小额贷款|微财富|宜车贷|二手车抵押|车贷|托管" />
<title>微积金，中国领先的互联网汽车金融服务平台_理财_理财产品_投融资_理财需求_投资_投资标的_财富_财富成长_手机理财_微信理财_微理财</title>
<link rel="Shortcut Icon"
	href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/takeCash.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>

<script type="text/javascript">

$(function(){
	$.ajax({
		url : "/accountCash/getRepayTodayMoney",
		type : "post",
		success : function(data) {
			var da = $.parseJSON(data);
			if(da.success){
				$("#repay").append('<font color="red" id="repayMsg" style="position: absolute; left: 1%; top: 9%;">'+da.msg+'</font>');
			}
		}
	});
});
</script>

</head>

<body>
	<!----头部---->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>

	<!--中间---->

	<div class="content">
		<div class="myAccount">
			<!-- 左边通用页面 -->
			<jsp:include
				page="${pageContext.request.contextPath }/user/accountLeft.jsp"></jsp:include>
			<input type="hidden" value="${loginedSession.typeId}" id="userTypeId">
			<div class="rechargeTakeCash fr">
				<div class="rT box-shadow">
					<div class="rTWrap">

						<div class="txTop">
							<b class="midLine"></b>
							<h2 class="zhcz">提现</h2>
							<a href="javascript:history.go(-1);" class="goBack">返回</a>
						</div>
						<div class="fund-trustee clear">
							<span>微积金已全面接入新浪支付全程资金托管，为了您的资金安全，银行卡必须与实名认证同名。</span>
						</div>
					
						<c:if test="${empty bind}">
							<p class="union-p">平台资金托管模式已升级，为了确保您的资金安全。请重新添加您的同名银行卡。给您带来不便，敬请谅解。</p>
						</c:if>
						
						<div id="wrapper" style="width: 700px; border: none;">

							<!--main-->
							<div id="main">

								<div class="model-box withdraw-post">
									<ul class="items">
										<li class="txt">持卡人：</li>
										<li>${loginedSession.realName }</li>
									</ul>
									<ul class="items">
										<li class="txt"><b>提现至银行卡：</b></li>
										<li>
											<ul class="adds-bankcard">

												<c:if test="${not empty bind }">
												
													<li class="banks-dir selected" data-bankcode="102"><i
														class="logo-bank bank-${bind.bankNameLetter }"></i> <i
														class="icons"></i><em>${bind.accountLastFour }</em> <i
														class="icons" style="display: none"></i></li>
												</c:if>
												<c:if test="${empty bind }">
												
													<li class="adds-card" id="addCard"><span><i
															class="icons add-gray"></i>添加提现银行卡</span></li>
												</c:if>
												
												<!-- <li id="zsyh-tip">
												<c:if test="${isIcbcCard == 'Y' }">
                                                 		 微积金联名卡<br />每月3笔提现免手续费 
                                                 	</c:if> <c:if
 														test="${isIcbcCard != 'Y' }"> 
                                                  		招商银行微积金联名卡免提现费<br />
														<a href="/accountBank/bank">立即申请</a>
 													</c:if>
												</li>
-->

											</ul>
										</li>
									</ul>


									<ul class="items">
										<li class="txt">可提现金额：</li>
										<li class="light-org">￥${account.useMoney } <input
											type="hidden" id="useMoney" value="${account.useMoney }">
										</li>
									</ul>
									<form name="txForm" id="txForm">

										<ul class="items">
											<li class="txt"><b>提现金额：</b></li>
											<li id="repay">
												<div class="sum-input" style="height:57px;">

													<label>￥</label> <input class="input" name="money"
														id="withdrawMoney" type="text">
												</div> <span class="tip err" style="display: none;"
												id="withdrawCashErr"><i class="icons reg-error"></i>余额不足</span>
											</li>
										</ul>


										<ul class="items">
											<li class="txt">实付手续费：</li>
											<li id="procedureFee">￥<span id="withdrawFee">0.00</span>
												<div class="hb">
													<c:choose>
														<c:when test="${canHongbao == true }">
															<c:if test="${noFeeCount >= 3 || empty noFeeCount}"> 
																<input type="checkbox" name="useHongbao" id="hongbao">
															</c:if> 
														</c:when>
														<c:otherwise>
															<input type="checkbox" name="useHongbao"
																disabled="disabled" id="hongbao">
														</c:otherwise>
													</c:choose>
													<c:if test="${noFeeCount < 3 && not empty noFeeCount}">
														<label for="hongbao">免手续费</label>
														<input type="hidden" id="hongbaoValue" value="0">
													</c:if>
													<c:if test="${noFeeCount >= 3 || empty noFeeCount}">
														<label for="hongbao">提现券抵扣</label>
														<input type="hidden" id="hongbaoValue" value="${hongbao }">
													</c:if>
												</div>

												<div class="hb">
													剩余提现券：<span id="hongbaoResult">${hongbao }</span>
												</div>
											</li>
										</ul>

										<ul class="items">
											<li class="txt">实际到账金：</li>
											<li>￥<span id="realTackOut">0.00</span><span id="errMsg"
												class="err none">可用余额不足</span></li>
										</ul>

										<ul class="items">
											<li class="txt">支付密码：</li>
											<li><input type="password" name="payPassword"
												id="payPassword" class="exchangPwd" /> <span
												id="passwordErrorMsg" class="err none"></span></li>
										</ul>

										<div class="nextsubmit">

											<div class="subbtn">
												<a href="javascript:;" class="tx111" id="tijiao"
													style="padding: 0; width: 150px;">提现</a>
											</div>

										</div>
									</form>
								</div>
							</div>
							<!--/main-->
						</div>
						<input id="realnameFlag" value="${loginedSession.realStatus }"
							type="hidden"> 
							<c:if test="${noFeeCount >= 3 || empty noFeeCount}">
							<input type="hidden" id="isIcbcCard"
							value="N" />
							</c:if>
							<c:if test="${noFeeCount < 3 && not empty noFeeCount}">
							<input type="hidden" id="isIcbcCard"
							value="${isIcbcCard }" />
							</c:if>
						<div class="warmtip">

							<h3>温馨提示</h3>

							<p>1、您必须先进行身份实名认证和取现银行卡设置；（必须为本人银行卡）</p>
							<p>2、当日14:00前取现T+1入账，14:00后取现T+2入账；</p>
							<p>3、单笔提现每笔收取3元手续费，用户自充值之日起于15日之内且未完全投标的额外加收0.3%手续费；</p>
							<p>4、新浪托管第三方每日提现规定，每笔限额5万，最高提现额度50万，超过此额度请提前联系在线客服，申请提高提现限额。
							</p>
							<p>5、每笔取现会从您的账户余额里面扣除手续费，取现时请保证您的账户里面有足够余额。</p>
							<p></p>

						</div>

					</div>
				</div>
			</div>

		</div>
	</div>

	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>

	<script src="${pageContext.request.contextPath}/js/withdraw.js"></script>
	<script>
		$(function() {
			$("#addCard")
					.on(
							"click",
							function() {
								window.location.href = "${pageContext.request.contextPath }/accountBank/bank";
							});
		})
	</script>
</body>
</html>