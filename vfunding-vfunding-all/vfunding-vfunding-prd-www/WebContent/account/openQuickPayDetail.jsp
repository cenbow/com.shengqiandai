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
							<h2>开通快捷支付</h2>
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
													type="text" readonly
													value="${fn:substring(loginedSession.cardId, 0, 6)}************${fn:substring(loginedSession.cardId, 14,20)}"
													id="idCardNo" disabled="disabled" /></li>
												<li><label class="labelBox">银行</label><input
													type="text" disabled="disabled" id="BankCheck"
													name="bankCheck" value="${sd.dicMsg}"></li>
												<li><label class="labelBox">储蓄卡号</label> <input
													type="text" maxlength="19"
													value="${fn:substring(bank.account, 0, 4)}************${fn:substring(bank.account, fn:length(bank.account)-4,fn:length(bank.account))}"
													id="BankCardNumber" name="account" class="input"
													disabled="disabled"></li>
												<li><label class="labelBox">开户城市</label> <select
													id="province" name="provinceName" disabled="disabled">
														<option>${sc.province}</option>
												</select> <select id="city" name="cityName" disabled="disabled">
														<option>${sc.city}</option>
												</select></li>

												<li id="kuaijiebox" style="height: auto;">
													<div id="spanPhone" class="clear">
														<input type="text" id="mobilePhone" name="mobilePhone" />
													</div>
													<div id="spanCode" class="clear">
														<input type="text" id="checkCode" name="checkCode" /> <a
															class="code" id="code" onclick="clickGetCode()">获取验证码</a>
													</div>
													<p class="input_tip" style="display: block;">请输入此卡在银行预留的手机号码</p>
													<div id="noNeed" class="clear">
														<em id="kj-tip">开通快捷支付，下次可快速付款无需再次绑定。</em> <span
															class="agree-deal"> <input id="userDeal"
															name="userDeal" type="checkbox"/> <a
															href="/utilpage/toPage/sinaPayService" id="agreePro">我已阅读并同意《新浪支付快捷支付服务协议》</a>
														</span>
													</div> <input type="hidden" value="${sc.scId }" id="scId" /> <input
													type="hidden" value="${bank.bank}" id="bankCode" />
												</li>
											</ul>
											<div class="Error"></div>
											<a class="submitUp">开通</a>
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

	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/js/realName.js"></script>


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
			$("#li-kj,#ktkjfs").on("click", function() {
				if ($("#ktkjfs").attr("checked")) {
					$("#ktkjfs").attr("checked", false);
					$("#kuaijiebox").addClass("hide");
				} else {
					$("#ktkjfs").attr("checked", true);
					$("#kuaijiebox").removeClass("hide");
				}
			});

			//点击获取验证码
			

			$(".submitUp").click(function() {
				var mobile = /^0?(13|15|14|18)[0-9]{9}$/;
				artDialog.tips = function(content, time) {
					return artDialog({
						id : 'Tips',
						title : false,
						cancel : false,
						fixed : true,
						lock : true
					})
							.content(
									'<div style="padding: 0 1em;">' + content
											+ '</div>').time(time || 1);
				};
				if(!$("#userDeal").is(":checked")){
					$(".Error").html("*请阅读并勾选《新浪支付快捷支付服务协议》");
					return false;
				}else if($("#checkCode").val() == ''){
					$(".Error").html("*请输入验证码");
					return false;
				}else if ($("#mobilePhone").val() == null || $("#mobilePhone").val() == "") {
					$(".Error").html("*手机号不能为空");
					return false;
				} else if (!mobile.test($("#mobilePhone").val())) {
					$(".Error").html("*手机号码不对,请检查。");
					return false;
				}else{
					$.ajax({
						url : "/accountBank/sinaBindingBankAdvance?checkCode="+$("#checkCode").val(),
						type : "post",
						dataType : "json",
						beforeSend : function(){
							art.dialog.tips(
									'正在开通快捷支付...', 10);
							$(".submitUp").attr("disabled","disabled");
							$(".submitUp").text("添加中...")
						},
						success : function(data) {
							if (!data.success) {
								$(".Error").html(data.msg);
								$(".submitUp").removeAttr("disabled");
								$(".submitUp").text("添加")
							} else {
								self.location.href = '/accountBank/bank';
							}
						},
						error : function(){
							$(".submitUp").removeAttr("disabled");
							$(".submitUp").text("添加")
							$(".Error").html("绑卡请求异常,请稍后再试或联系客服!");
						}
					});
				}
			});
		});
		
		

		//点击获取验证码
		function clickGetCode(){
			if(!$("#userDeal").is(":checked")){
				$(".Error").html("*请阅读并勾选《新浪支付快捷支付服务协议》");
				return false;
			}else if ($("#mobilePhone").val() == null || $("#mobilePhone").val() == "") {
				$(".Error").html("*手机号不能为空");
				return false;
			}
				var wait = 60;
				var buttonObject = $("#code");
				$("#mobilePhone").attr("readonly", "readonly");
				$.ajax({
					url : "/accountBank/openQuickPay?scId="
							+ $("#scId").val() + "&phone="
							+ $("#mobilePhone").val() + "&bankCode="
							+ $("#bankCode").val(),
					type : "post",
					dataType : "json",
					async : false,
					success : function(data) {
						if (!data.success) {
							$(".Error").html(data.msg);
						}else{
							doWait(buttonObject, wait);
						}
					}
				});
		}
		function doWait(buttonObject, wait) {
			if (wait == 0) {
				buttonObject.attr("onclick","clickGetCode();");
				buttonObject.css("background-color","#00a0e9");
				wait = 60;
				buttonObject.html("获取验证码.");
			} else {
				buttonObject.removeAttr("onclick");
				buttonObject.css("background-color","#64696B");
				buttonObject.html(wait+" 秒后，重新获取验证码");
				wait--;
				setTimeout(function() {
					doWait(buttonObject, wait);
				}, 1000)
			}
		}
	</script>

</body>
</html>


