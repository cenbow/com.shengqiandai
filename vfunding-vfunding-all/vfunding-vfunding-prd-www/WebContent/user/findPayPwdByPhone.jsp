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
	href="${pageContext.request.contextPath}/css/ui-form.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/chosen.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/findPwdNow.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/findPwd2.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/chosen.jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/pswLevel.js"></script>
<script src="${pageContext.request.contextPath}/js/getCodeFindPayPwdByPhone.js"></script>

<script type="text/javascript">
	$(function() {

		$('.ui-form-item').on('click', ".input_tip", function() {
			$(this).hide().parent().children('input').trigger('focus');
		}).on('focus', 'input', function() {
			$(this).parent().children('.input_tip').hide();
		}).on('blur', 'input', function() {
			if (!this.value) {
				$(this).parent().children('.input_tip').show();
			}
		}).find("input").trigger("focus");

		// password-strength	
		$('.pwd').on('keyup', function() {
			var $range = $('.psw-streng div');
			var v = $(this).val();
			var res = pswLevel(v);
			switch (res.level) {
			case '强':
				$range.removeClass().addClass('high');
				break;
			case '中':
				$range.removeClass().addClass('mid');
				break;
			case '弱':
				$range.removeClass().addClass('low');
				break;
			}
			$range.show();
		}).on('blur', function() {

		});

		//select frame
		$("#safe-question").chosen();
		
		 // 身份证号码验证
   $.validator.addMethod( "isIdCardNo" ,  function (value, element)  {
	 
	  var length = value.length;
      var idcardNo = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
      return   this .optional(element)  ||(length==15||18) &&idcardNo.test(value);    
      
  } ,  " " );

		// validate frame

		$("#resetPayPwd-form").validate({
			submitHandler : function(form) {
				//form.submit();
				var isIdCardNo = $("#isIdCardNo").val();
				var verification = $('#verification').val();
				var newPassword = $('#pwd').val();
				var rePassword = $('#pwd2').val();
				$.ajax({
					type : "POST",
					url : "/user/findPayPasswordByPhone",
					dataType : "json",
					data : {
						'cardId' : isIdCardNo,
						'code' : verification,
						'password' : newPassword,
						'repassword' : rePassword
					},
					async : false,
					success : function(data) {
						if (data.success) {
							art.dialog({
								content : '您的支付密码已重新设置，请牢记',
								ok : function() {
									location.href = '/user/safeCenter';
								},
								lock : true,
								icon : 'succeed'
							});

						} else {
							art.dialog({
								content : data.msg,
								ok : true,
								lock : true,
								icon : 'error'
							});
						}

					},
					beforeSend : function(XHR) {
						$("#resetPayPwd-form input").attr('disabled', true);
						$(".btn116").val('处理中...');
					},
					complete : function(XHR, TS) {
						$("#resetPayPwd-form input").attr('disabled', false);
						$(".btn116").val('确认找回');
					}
				});
				

			},

			rules : {
				idCardNo : {
					required : true,
					isIdCardNo:true,
					remote:{
					　　     type:"POST",
					　　     url:"/user/checkCardId",
					        dataType:"json", 
					　　     data:{
					　　            "cardId":function(){return $("#isIdCardNo").val();}
					　　          } 
						
					}
				},
				verification: {
					required : true,
					remote:{
					　　     type:"POST",
					　　     url:"/verification/checkVerifyCodeMethod",
					        dataType:"json", 
					　　     data:{
					　　            "verifyCode":function(){return $("#verification").val();}
					　　          } 
						
					}
					
					
				},
				pwd : {
					required : true,
					minlength : 6,
					maxlength : 20
				},

				pwd2 : {

					required : true,
					equalTo : "#pwd"

				}

			},

			messages : {
				idCardNo : {
					required : "身份证不能为空",
					isIdCardNo:"身份证格式错误",
					remote:"身份证号码错误"
				},
				
				verification:{
					required : "验证码不能为空",
					remote:"验证码不正确"
					
				},
				pwd : {
					required : "密码不能为空",
					minlength : "密码不能少于6位",
					maxlength : "密码不能多于20位"

				},
				pwd2 : {
					required : "密码不能为空",
					equalTo : "两次密码不一致"
				}

			},
			success : "valid"

		});

	})
	
</script>
</head>

<body>
	<!-----头部---->
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
							<h2 class="zhcz">重置支付密码</h2>
							<a
								href="${pageContext.request.contextPath }/user/findPayPasswordPage"
								class="goBack">返回</a>
						</div>

						<p class="suggest">6-20个字符，建议使用字母加数字或符号的组合密码</p>


						<div class="reset-form">

							<form class="ui-form" id="resetPayPwd-form">

								<div class="ui-form-item"  id="cardNoDiv">

									<label class="ui-label"> <span class="ui-form-required">*</span>
										身份证号：
									</label> <input type="text" class=" ui-input answer" name="idCardNo"
										id="isIdCardNo" />
									<p class="input_tip">请输入身份证号码</p>
								</div>


								<div class="ui-form-item " style="height: 60px;">
									<label class="ui-label"> <span class="ui-form-required">*</span>
										手机号码：
									</label> <input type="text" id="phone" class="ui-input answer"
										name="phone" value="${userPhone }" disabled />

								</div>


								<div class="ui-form-item " style="height: 40px;">
									<label class="ui-label"> <span class="ui-form-required">*</span>
										验证码：
									</label> <input type="text" class=" ui-input  verification"
										name="verification" id="verification" />
									<p class="input_tip">请输入验证码</p>
								</div>

								<div class="ui-form-item"
									style="margin-top: 10px; padding-left: 160px;">
									<a href="#" id="phoneCode"
										class="get-freeyzm ">短信获取</a> <a href="javascript:;"
										id="voiceCode" class="get-freeyzm"
										style="background-color: #EEEEEE">语音获取</a>
								</div>

								<div class="ui-form-item" style="height: 5px;">
									<div id="waitMsg" style="color: #888888;"></div>
								</div>
								<div class="ui-form-item">

									<label class="ui-label"> <span class="ui-form-required">*</span>
										新密码：
									</label> <input type="password" class=" ui-input pwd" name="pwd"
										id="pwd" />
									<div class="psw-streng">
										<div></div>
									</div>
									<p class="input_tip">请输入密码</p>
								</div>

								<div class="ui-form-item">

									<label class="ui-label"> <span class="ui-form-required">*</span>
										确认新密码：
									</label> <input type="password" class=" ui-input pwd2" name="pwd2"
										id="pwd2" />
									<p class="input_tip">请再次输入密码</p>

								</div>

								<div class="ui-form-item">

									<label class="ui-label"> <span class="ui-form-required"></span>

									</label> <input type="submit" class="btn116" value="确认找回" />

								</div>

							</form>

						</div>




						<p class="confirm">确认 若您无法通过密保问题重置支付密码，您可以通过邮箱将您的信息发送至
							service@vfunding.cn
							申请人工重置支付密码（处理时限为1个工作日），请您在邮件中提供：姓名、用户名、身份证正反面照片或影印件（需注明“仅供微积金重置支付密码使用”）
						</p>


					</div>
				</div>
			</div>


		</div>
	</div>
	<script type="text/javascript">
	$(function(){
		
		$("#phoneCode").on("click",function(){
			var cardid = $("#isIdCardNo").val();
			if(cardid == ''){
				var error = $("#cardNoDiv").find(".error");
				if(error.text() != '身份证不能为空'){
					$("#cardNoDiv").append("<label for=\"isIdCardNo\" class=\"error\">身份证不能为空</label>");
					$("#isIdCardNo").trigger("focus");
					
				}
				return false;
			}
			getPhoneCode();
		})
		
		
	})
	</script>
	
	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
</body>
</html>


