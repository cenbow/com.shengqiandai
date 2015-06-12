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
	href="${pageContext.request.contextPath}/css/unionPayCardDetail.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<!-- 上传需要的CSS和JS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/upload/upload.css"
	media="screen" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/upload/ajaxupload.js"></script>
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
							<h2>申请联名卡</h2>
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
												<li><label class="labelBox">手机号码</label> <input
													type="text" id="mobilePhone" name="userPhone" />
													<p class="input_tip">银行预留的手机号码</p></li>

												<li><label class="labelBox">邮寄地址</label> 
												<select id="province" name="province"></select> 
													<select id="city" name="city">
														<option>选择市</option>
												</select></li>

												<li style="height: auto;"><label class="labelBox"></label>
													<textarea rows="4" cols="50" id="address-detail"></textarea>
													<p class="input_tip" style="width: 280px;">邮寄卡片的详细地址，包括街道名称，门牌，楼层和房间号等信息</p>
													<input type="hidden" name="address" id="address" /></li>
												<li><label class="labelBox">邮政编码</label> <input
													type="text" id="emailCode" name="postCode" />
													<p class="input_tip">邮寄地址的邮政编码</p></li>
												<li><label class="labelBox">身份证正面</label> <input
													type="text" id="idCardFront-input" name="" readonly="readonly"> 
													<p class="input_tip" style="display: block;">身份证正面扫描件或照片</p>
													<div class="fileType" id="idCardFace">浏览</div></li>
												<li><label class="labelBox">身份证反面</label> <input
													type="text" id="idCardBackface-input" readonly="readonly"> 
													<p class="input_tip" style="display: block;">身份证反面扫描件或照片</p>
													<div class="fileType" id="idCardBack">浏览</div></li>

											</ul>
											<div class="Error"></div>
											<a class="submitUp" id="subForm">立即申请</a>
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

	<script src="${pageContext.request.contextPath}/js/city.js"></script>


	<script type="text/javascript">
		$(function() {

			//输入框 聚焦失焦
			function tip(obj, subObj) {
				$(obj).on('click', ".input_tip", function() {
					$(this).hide().parent().children(subObj).trigger('focus');
				}).on('focus', subObj, function() {
					$(this).parent().children('.input_tip').hide();
				}).on('blur', subObj, function() {
					if (!this.value) {
						$(this).parent().children('.input_tip').show();
					}
				});
			}

			tip($('#addNewCardForm li'), "input");//输入框
			tip($('#addNewCardForm li'), "textarea");//文本域

			$("#subForm")
					.on(
							"click",
							function() {
								artDialog.tips = function(content, time) {
									return artDialog({
										id : 'Tips',
										title : false,
										cancel : false,
										fixed : true,
										lock : true
									}).content(
											'<div style="padding: 0 1em;">'
													+ content + '</div>').time(
											time || 1);
								};
								$("#address").val($("#address-detail").val());
								if (checkForm()) {
									$
											.ajax({
												url : "/accountBank/addIcbcCard",
												type : "post",
												dataType : "json",
												data : $("#addNewCardForm")
														.serialize(),
												beforeSend : function() {
													art.dialog.tips(
															'正在提交申请...', 100);
												},
												success : function(data) {
													if (!data.success) {
														art.dialog.tips(
																'正在提交申请...', 0);
														$(".Error").html(
																data.msg);
													} else {
														art.dialog.tips(
																'正在提交申请...', 0);
														artDialog(
														        {	
														            content:'您已成功提交招商 银行（上海分行）微积金联名卡申请，<br />我们将在每月30号统一采集信息并在3个工作日内与您取得联系',
														            lock:true,
														            style:'succeed'
														        },
														        function(){
														        	self.location.href = '/accountBank/bank';
														        }
														);
													}
												},
												error : function() {
													art.dialog.tips(
															'正在提交申请...', 0);
													$(".Error")
															.html(
																	"绑卡请求异常,请稍后再试或联系客服!");
												}
											});
								}
							});

		});

		function checkForm() {
			if ($("#mobilePhone").val() == '') {
				$(".Error").html("请输入手机号码");
				return false;
			} else if ($("#province").val() == '') {
				$(".Error").html("请选择省份");
				return false;
			} else if ($("#city").val() == '') {
				$(".Error").html("请选择城市");
				return false;
			} else if ($("#address-detail").val() == '') {
				$(".Error").html("请输入详细地址");
				return false;
			} else if ($("#emailCode").val() == '') {
				$(".Error").html("请输入邮政编码");
				return false;
			} else if ($("#idCardFront-input").val() == '') {
				$(".Error").html("请上传身份证正面图片");
				return false;
			} else if ($("#idCardBackface-input").val() == '') {
				$(".Error").html("请上传身份证背面图片");
				return false;
			}
			return true;
		}

		/**
		 * 上传身份证图片:正面
		 */
		$(function() {
			var button = $('#idCardFace'), interval;
			new AjaxUpload(
					button,
					{
						action : '/accountBank/idCardUpload',
						name : 'files',
						data : {
							'name' : 'idCardFront-input',
							'idCardType' : 'face'
						},
						onSubmit : function(file, ext) {
							if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG|gif|GIF|bmp|BMP)$/
									.test(ext))) {
								alert('图片格式不正确,请选择 jpg 格式的文件!', '系统提示');
								return false;
							}
							button.text('上传');
							this.disable();
							interval = window.setInterval(function() {
								var text = button.text();
								if (text.length < 10) {
									button.text(text + '...');
								} else {
									button.text('上传');
								}
							}, 200);
						},
						onComplete : function(file, response) {
							var res = $.parseJSON(response);
							if (res.success) {
								button.text('浏览');
								window.clearInterval(interval);
								this.enable();
								$("#idCardFront-input").val(file);
							}
						}
					});
		});

		/**
		 * 上传身份证图片:背面
		 */
		$(function() {
			var button = $('#idCardBack'), interval;
			new AjaxUpload(
					button,
					{
						action : '/accountBank/idCardUpload',
						name : 'files',
						data : {
							'name' : 'idCardBackface-input',
							'idCardType' : 'back'
						},
						onSubmit : function(file, ext) {
							if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG|gif|GIF|bmp|BMP)$/
									.test(ext))) {
								alert('图片格式不正确,请选择 jpg 格式的文件!', '系统提示');
								return false;
							}
							button.text('上传');
							this.disable();
							interval = window.setInterval(function() {
								var text = button.text();
								if (text.length < 10) {
									button.text(text + '...');
								} else {
									button.text('上传');
								}
							}, 200);
						},
						onComplete : function(file, response) {
							var res = $.parseJSON(response);
							if (res.success) {
								button.text('浏览');
								window.clearInterval(interval);
								this.enable();
								$("#idCardBackface-input").val(file);
							}
						}
					});
		});
	</script>

</body>
</html>


