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
	href="${pageContext.request.contextPath}/css/chosen.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bankAdd.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
</head>

<body>
	<!--头部 -->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>

	<!--中间-->
	<div class="content">
		<div class="myAccount">
			<!-- 左边通用页面 -->
			<jsp:include
				page="${pageContext.request.contextPath }/user/accountLeft.jsp"></jsp:include>
			<!-- 右边主体部分 开始 -->
			<div class="rechargeTakeCash fr">
				<div class="rT box-shadow">
					<div class="rTWrap">
						<div class="txTop">
							<b class="midLine"></b>
							<h2>银行卡添加</h2>
						</div>

						<div class="cardAdd">

							<div id="AddBankCardCont" class="bankcards">
							<!-- 招商银行联名卡 -->
								<!-- 
									<div class="model-box bank-card newBankCard">
									<div class="head">
										<div class="bank-logo">
											<i class="bank-small banksmall-zsyh"></i>招商银行
										</div>
										<div class="vfunding-logo"></div>
									</div>
									<c:if
										test="${not empty icbcCard && not empty icbcCard.icbcCard && not empty accountBankIcbc}">
										<div class="content1">
											<ul>
												<li><b>卡号</b> <em
													style="font-size: 22px; font-style: normal;color:#FFCC00; ">${accountBankIcbc.accountInfo }</em></li>
												<li id="kjzf"><span class="k-zhifu fl">快捷支付</span>
													<div class="kaitong fr">
														<c:choose>
															<c:when test="${accountBankIcbc.ktkjfs == 'Y'}">
																<a href="#">已开通</a>
															</c:when>
															<c:otherwise>
																<c:if test="${kjcount == 1}">
																	<a href="#">已存在快捷支付卡,不可再次开通</a>
																	<a href="#" class="k-go"></a>
																</c:if>
																<c:if test="${kjcount == 0}">
																	<a href="#">未开通</a>
																	<a
																		href="/accountBank/openQuickPayDetail?accountId=${accountBankIcbc.id}&isIcbcCard=Y"
																		class="k-go">马上开通</a>
																</c:if>
															</c:otherwise>
														</c:choose>
													</div></li>
											</ul>
										</div>
									</c:if>
									<c:if
										test="${empty icbcCard || empty icbcCard.icbcCard || empty accountBankIcbc}">
										<div class="content1">
											<div class="zs-bg">
												<div class="zsyh-cnt">
													<p>招商银行微积金联名卡</p>
													<p>
														尊享提现<span>免手续费</span>
													</p>
													<a href="/utilpage/toPage/lianmingka">了解详情</a>
												</div>
											</div>
										</div>
									</c:if>
									<div class="foot">
										<c:if test="${empty icbcCard || empty icbcCard.addtime}">
											<a id="apply-now" href="/accountBank/toIcbcCardPage">立即申请</a>
										</c:if>
										<c:if
											test="${not empty icbcCard && not empty icbcCard.addtime && not empty icbcCard.icbcCard}">

										</c:if>
										<c:if
											test="${not empty icbcCard && not empty icbcCard.addtime && not empty icbcCard.icbcCard}">
											<c:if test="${empty accountBankIcbc}">
												<div class="ui-state">
													银行卡已经寄出，请收到后 <a id="add-zsCard"
														href="/accountBank/addBankDetail?isIcbcCard=Y">添加银行卡</a>
												</div>
											</c:if>
											<c:if test="${not empty accountBankIcbc }">
												<div class="bank-action">
													<form class="unbindForm">
														<input type="hidden" value="${accountBankIcbc.id }" name="id">
													</form>
													<a class="r3 bank-delete" onclick="deleteUnbindCard(this);"
														href="javascript:;"><i class="icons yclose"></i>删除</a>
												</div>
											</c:if>
										</c:if>
										<c:if test="${not empty icbcCard && not empty icbcCard.addtime && empty icbcCard.icbcCard}">
												<div class="ui-state">
													您已经成功申请，卡片正在办理中
												</div>
										</c:if>
									</div>
								</div>
								-->
								<!-- 普通银行卡 -->
							<c:forEach items="${banks}" var="bank">
								<c:if test="${bank.account != icbcCard.icbcCard }">
									<div class="model-box bank-card newBankCard">
										<div class="head">
											<div class="bank-logo">
												<i class="bank-small banksmall-${bank.bankNameLetter }"></i>${bank.bankName }
											</div>
										</div>
										<div class="content1">
											<ul>
												<li><b>卡号</b> <em
													style="font-size: 22px; font-style: normal;">${bank.accountInfo }</em></li>

												<li id="kjzf"><c:choose>
														<c:when
															test="${bank.bank == 'COMM' || bank.bank == 'PSBC' || bank.bank == 'BCCB'}">
															<font color="red">抱歉，该银行卡仅支持提现，不支持快捷支付</font>
														</c:when>
														<c:when test="${empty bank.scId}">
															<font color="red">抱歉，该银行卡新浪资金托管暂不支持，不能使用。</font>
														</c:when>
														<c:otherwise>

															<span class="k-zhifu fl">快捷支付</span>
															<div class="kaitong fr">
																<c:choose>
																	<c:when test="${bank.ktkjfs == 'Y'}">
																		<a href="#">已开通</a>
																	</c:when>
																	<c:otherwise>
																		<c:if test="${kjcount == 1}">
																			<a href="#">已存在快捷支付卡,不可再次开通</a>
																			<a href="#" class="k-go"></a>
																		</c:if>
																		<c:if test="${kjcount == 0}">
																			<a href="#">未开通</a>
																			<a
																				href="/accountBank/openQuickPayDetail?accountId=${bank.id}"
																				class="k-go">马上开通</a>
																		</c:if>
																	</c:otherwise>
																</c:choose>
															</div>
														</c:otherwise>
													</c:choose></li>
											</ul>
										</div>
										<div class="foot">
											<div class="bank-action">
												<form class="unbindForm">
													<input type="hidden" value="${bank.id }" name="id">
												</form>
												<a class="r3 bank-delete" onclick="deleteUnbindCard(this);"
													href="javascript:;"><i class="icons yclose"></i>删除</a>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
							<c:if test="${canAdd==true }">
								<div class="model-box bank-card bank-card-add">
									<div class="bank-add-button">
										<a href="javascript:;"><i class="icons add-blue"></i>添加一张银行卡</a>
									</div>
								</div>
							</c:if>
							</div>
						</div>
						<c:if test="${empty banks}">
						平台资金托管模式已升级，为了确保您的资金安全。请重新添加您的同名银行卡。给您带来不便，敬请谅解。
						</c:if>

					<!-- 温馨提示 -->

					<div class="warm-tips">
						<h3>温馨提示</h3>
						<ul>
							<li>1、您的银行卡开通快捷支付后，将会成为指定充值提现专卡，以后只能用该卡进行快捷充值和提现（网银充值不受限），如果需要更换该专卡，需要先删除该专卡，再用其他银行卡进行重新开通，成为新的专卡。</li>
							<li>2、删除专卡需要用户账户的资金余额为0才可以解绑。</li>
							<li>3、建议您使用目前支持快捷支付的对应银行银行卡：中行、农行、建行、工行、招商、中信、民生、广发、兴业、光大、上海、邮储、华夏、平安。</li>
							<li>4、 下列银行，目前支持网银充值及提现，但不支持手机端充值：交行、浦发、北京。</li>
							<li>5、 其他银行请通过网银充值，但不支持提现，请确保至少添加一张可提现银行卡。</li>
						</ul>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<input id="realnameFlag" value="${loginedSession.realStatus }"
		type="hidden">
	</div>




	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/js/bankCardAdd.js"></script>
	<script src="${pageContext.request.contextPath}/js/realName.js"></script>
	<script src="${pageContext.request.contextPath}/js/city.js"></script>
	<script src="${pageContext.request.contextPath}/js/chosen.jquery.js"></script>

	<script type="text/javascript">
		$(function() {
			if ('${noDelete}' == 'Y') {
				art.dialog({
					content : '用户余额不为0,不可删除已开通快捷支付的卡',
					ok : function() {
						location.href = '/accountBank/bank';
					},
					lock : true,
					icon : 'succeed'
				});
			}
			//聚焦失焦
			$(".input_tip").on("click", function() {
				$(this).hide().siblings("#mobilePhone").trigger("focus");
				$("#mobilePhone").focus();
			});
			$("#mobilePhone").on("focus", function() {
				$(".input_tip").hide();
			});
			$("#mobilePhone").on("blur", function() {
				var val = $(this).val();
				if (val == null || val == "") {
					$(".input_tip").show();
				}
			});

			//选择银行卡
			var bankDialog = $('#BankSelectDialog');
			var bankMore = bankDialog.find('.bank-more a');
			var blanks = bankDialog.find('.bank-text');
			bankMore.click(function() {
				if (blanks.is(':visible')) {
					bankMore.find('.icons').removeClass('arrow-up').addClass(
							'arrow-down');
					blanks.hide()
				} else {
					bankMore.find('.icons').removeClass('arrow-down').addClass(
							'arrow-up');
					blanks.show()
				}
				webUtil.jDialog.Modal('BankSelectDialog', 'BankSelectEntry');
			});

			//快捷方式
			$("#li-kj,#ktkjfs").on("click", function() {
				if ($("#ktkjfs").attr("checked")) {
					$("#ktkjfs").attr("checked", false);
					$("#kuaijiebox").addClass("hide");
				} else {
					$("#ktkjfs").attr("checked", true);
					$("#kuaijiebox").removeClass("hide");
				}
			});

		});
	</script>
	<script type="text/javascript">
		$(function() {
			if ('${unbindMsg}' != 'success' && '${unbindMsg}' != '') {
				alert('${unbindMsg}');
			}
			var bankDialog = $('#BankSelectDialog');
			var bankMore = bankDialog.find('.bank-more a');
			var blanks = bankDialog.find('.bank-text');
			bankMore.click(function() {
				if (blanks.is(':visible')) {
					bankMore.find('.icons').removeClass('arrow-up').addClass(
							'arrow-down');
					blanks.hide()
				} else {
					bankMore.find('.icons').removeClass('arrow-down').addClass(
							'arrow-up');
					blanks.show()
				}
				webUtil.jDialog.Modal('BankSelectDialog', 'BankSelectEntry');
			});

		});
	</script>
</body>
</html>


