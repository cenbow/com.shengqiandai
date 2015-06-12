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
	href="${pageContext.request.contextPath}/css/bankAddDetail.css" />
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
							<h2>添加银行卡</h2>
						</div>
						<div class="fund-trustee clear">
		                <span>微积金已全面接入新浪支付全程资金托管，为了您的资金安全，银行卡必须与实名认证同名。</span>
		                </div>
						<div class="cardAdd">

							<div id="AddBankCardCont" class="bankcards">

								<div class="bankAddNew">



									<form method="post" action="#" id="addNewCardForm">
										<div class="banks-list">
											<ul id="banks-list">
												<li><label class="labelBox">姓名</label> <input
													type="text" readonly value="${loginedSession.realName}"
													id="BankUserName" disabled="disabled" /></li>
												<li><label class="labelBox">身份证号</label> <input
													type="text" readonly value="${fn:substring(loginedSession.cardId, 0, 6)}************${fn:substring(loginedSession.cardId, 14,20)}"
													id="idCardNo" disabled="disabled" /></li>
												<li><label class="labelBox">银行</label> 
												<input type="text" disabled="disabled" id="BankCheck" name="bankCheck" value="${empty isIcbcCard_bankName?'未选择银行':isIcbcCard_bankName }"> 
												<input type="hidden" id="bankId" name="bank" value="${isIcbcCard_bank}"> 
													<span id="BankSelect" class="bank-select">请选择
												<i class="icons arrow-blue-down"></i>
												</span>
												</li>
												<li><label class="labelBox">储蓄卡号</label> <input
													type="text" maxlength="19" value="${isIcbcCard_account}" id="BankCardNumber"
													name="account" class="input"></li>
												<li><label class="labelBox">开户城市</label> <select
													id="province" name="provinceName"></select> <select id="city"
													name="cityName">
														<option>请选择</option>
												</select>
												</li>
												<c:if test="${kjcount == 0 }">
												<li style="cursor: pointer;width: 180px;padding-left: 116px;">
												<input id="ktkjfs" name="ktkjfs" type="checkbox" value="Y"><span id="li-kj">开通快捷方式</span>
													</li>
												<li id="kuaijiebox" class="hide" style="height: auto;">
													<div id="spanPhone" class="clear">
														<input type="text" id="mobilePhone" name="mobilePhone" maxlength="11"/>
													</div>
<!-- 													<div class="msg-tip">短信已发送至您的手机：151*****0001</div> -->
													<div id="spanCode" class="clear">
														<input type="text" id="checkCode" name="checkCode" maxlength="6"/> <a
															class="code" id="code" onclick="clickGetCode()">获取验证码</a>
													</div>
													<p class="input_tip" style="display: block;">请输入此卡在银行预留的手机号码</p>

													<div id="noNeed" class="clear">
														<em id="kj-tip">开通快捷支付，下次可快速付款无需再次绑定。</em> <span
															class="agree-deal"> <input id="userDeal"
															name="userDeal" type="checkbox" /> <a
															href="/utilpage/toPage/sinaPayService" id="agreePro">我已阅读并同意《新浪支付快捷支付服务协议》</a>
														</span>
													</div>
												</li>
												</c:if>
											</ul>
											<div class="Error"></div>
											<a class="submitUp">添加</a>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<input id="realnameFlag" value="${loginedSession.realStatus }"
			type="hidden">
	</div>



	<div id="BankSelectDialog" class="dialog-main" style="width: 715px;">
		<div class="dialog-head">
			<h2>选择银行</h2>
			<a class="closeModal r3" href="javascript:;"><i class="icons">关闭</i></a>
		</div>
		<div id="BankSelectEntry" class="bank-select-entry">
			<ul class="bank-top">
				<li><input type="radio" value="工商银行_ICBC" id="bankRadio_1"
					name="bankRadio"> <label for="bankRadio_1"><i
						class="logo-bank bank-gsyh">工商银行</i></label></li>
				<li><input type="radio" value="建设银行_CCB" id="bankRadio_2"
					name="bankRadio"> <label for="bankRadio_2"><i
						class="logo-bank bank-jsyh">建设银行</i></label></li>
				<li><input type="radio" value="农业银行_ABC" id="bankRadio_3"
					name="bankRadio"> <label for="bankRadio_3"><i
						class="logo-bank bank-nyyh">农业银行</i></label></li>
				<li><input type="radio" value="中国邮政储蓄银行_PSBC" id="bankRadio_4"
					name="bankRadio"> <label for="bankRadio_4"><i
						class="logo-bank bank-zgyzcxyh">中国邮政储蓄银行</i></label></li>
				<li><input type="radio" value="中国银行_BOC" id="bankRadio_5"
					name="bankRadio"> <label for="bankRadio_5"><i
						class="logo-bank bank-zgyh">中国银行</i></label></li>
				<li><input type="radio" value="招商银行_CMB" id="bankRadio_7"
					name="bankRadio"> <label for="bankRadio_7"><i
						class="logo-bank bank-zsyh">招商银行</i></label></li>
				<li><input type="radio" value="光大银行_CEB" id="bankRadio_8"
					name="bankRadio"> <label for="bankRadio_8"><i
						class="logo-bank bank-gdyh">光大银行</i></label></li>
				<li><input type="radio" value="广发银行_GDB" id="bankRadio_11"
					name="bankRadio"> <label for="bankRadio_11"><i
						class="logo-bank bank-gfyh">广发银行</i></label></li>
				<li><input type="radio" value="中信银行_CITIC" id="bankRadio_12"
					name="bankRadio"> <label for="bankRadio_12"><i
						class="logo-bank bank-zxyh">中信银行</i></label></li>
				<li><input type="radio" value="兴业银行_CIB" id="bankRadio_13"
					name="bankRadio"> <label for="bankRadio_13"><i
						class="logo-bank bank-xyyh">兴业银行</i></label></li>
				<li><input type="radio" value="平安银行_SZPAB" id="bankRadio_14"
					name="bankRadio"> <label for="bankRadio_14"><i
						class="logo-bank bank-payh">平安银行</i></label></li>
				<li><input type="radio" value="民生银行_CMBC" id="bankRadio_15"
					name="bankRadio"> <label for="bankRadio_15"><i
						class="logo-bank bank-msyh">民生银行</i></label></li>
				<li><input type="radio" value="上海银行_BOS" id="bankRadio_16"
					name="bankRadio"> <label for="bankRadio_16"><i
						class="logo-bank bank-shyh">上海银行</i></label></li>
				<li><input type="radio" value="华夏银行_HXB" id="bankRadio_17"
					name="bankRadio"> <label for="bankRadio_17"><i
						class="logo-bank bank-hxyh">华夏银行</i></label></li>
						
				<li><input type="radio" value="交通银行_COMM" id="bankRadio_18"
					name="bankRadio"> <label for="bankRadio_18"><i
						class="logo-bank bank-jtyh">交通银行</i></label></li>
				<li><input type="radio" value="浦发银行_SPDB" id="bankRadio_19"
					name="bankRadio"> <label for="bankRadio_19"><i
						class="logo-bank bank-pfyh">浦发银行</i></label></li>
				<li><input type="radio" value="北京银行_BCCB" id="bankRadio_20"
					name="bankRadio"> <label for="bankRadio_20"><i
						class="logo-bank bank-bjyh">北京银行</i></label></li>
			</ul>


		</div>
		<div class="dialog-foot">
			<div class="bank-action">
				<a class="closeModal r3 bank-delete" href="javascript:;"><i
					class="icons yclose"></i>取消</a><a class="r3 bank-bind"
					href="javascript:;"><i class="icons cm-white"></i>确认</a><span
					class="mError"></span>
			</div>
		</div>
	</div>
	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>


	<script
		src="${pageContext.request.contextPath}/js/bankCardAddDetail.js"></script>
	<script src="${pageContext.request.contextPath}/js/realName.js"></script>
	<script src="${pageContext.request.contextPath}/js/city.js"></script>


	<script type="text/javascript">
		$(function() {

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
			$("#ktkjfs").on("click", function() {
				if (!$("#ktkjfs").attr("checked")) {
					$("#ktkjfs").attr("checked", false);
					$("#kuaijiebox").addClass("hide");
				} else {
					$("#ktkjfs").attr("checked", true);
					$("#kuaijiebox").removeClass("hide");
				}
			});

		});
	</script>

</body>
</html>


