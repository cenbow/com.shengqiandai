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
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/chosen.jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/pswLevel.js"></script>

<script>
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

		// validate frame

		$("#resetPayPwd-form").validate({
			submitHandler : function(form) {
				//form.submit();
				var question = $('#safe-question').val();
				var answer = $('#answer').val();
				var newPassword = $('#pwd').val();
				var rePassword = $('#pwd2').val();
				$.ajax({
					type : "POST",
					url : "/user/findPayPassword",
					dataType : "json",
					data : {
						'question' : question,
						'answer' : answer,
						'newPassword' : newPassword,
						'rePassword' : rePassword
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
						$(".btn116").val('验证中...');
					},
					complete : function(XHR, TS) {
						$("#resetPayPwd-form input").attr('disabled', false);
						$(".btn116").val('确认');
					}
				});

			},

			rules : {
				answer : {
					required : true,
					remote:{ // 验证用户密保问题
					　　     type:"POST",
					　　     url:"/user/checkUserAnswer",
					     dataType:"json", 
					　　     data:{
					　　            "answer":function(){return $("#answer").val();},
					        "question":function(){return $("#safe-question").val();}
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
				answer : {
					required : "答案不能为空",
					remote:"您的密保问题答案错误"
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
	
	function change(){
		$('#answer').focus().val('');
	}
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
							<h2 class="zhcz">重置支付密码</h2>
							<a
								href="${pageContext.request.contextPath }/user/findPayPasswordPage"
								class="goBack">返回</a>
						</div>

						<p class="suggest">6-20个字符，建议使用字母加数字或符号的组合密码</p>


						<div class="reset-form">

							<form class="ui-form" id="resetPayPwd-form">

								<div class="ui-form-item">

									<label class="ui-label"> <span class="ui-form-required">*</span>
										安全问题：
									</label> <select id="safe-question" name="safeQuestion"
										class="ui-state-selected" onchange="change();">
										<c:forEach items="${linkages}" var="linkage">
											<option value="${linkage.id }">${linkage.name }</option>
										</c:forEach>
									</select>
								</div>
								<div class="ui-form-item">

									<label class="ui-label"> <span class="ui-form-required">*</span>
										回答：
									</label> <input type="text" class=" ui-input answer" name="answer"
										id="answer" />
									<p class="input_tip">请输入答案</p>

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

									</label> <input type="submit" class="btn116" value="确认" />

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
	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
</body>
</html>


