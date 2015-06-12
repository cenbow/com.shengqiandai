// JavaScript Document
$(function() {

	$('.ui-form').on('click', ".placeholder", function() {
		$(this).hide().parent().children('input').trigger('focus');
	}).on('focus', 'input', function() {
		$(this).parent().children('.placeholder').hide();
	}).on('blur', 'input', function() {
		if (!this.value) {
			$(this).parent().children('.placeholder').show();
		}
	}).find("input").trigger("focus");

	$("#loginForm").validate(
			{
				submitHandler : function(form) {
					if (($(form).find("#username").val() != null && $(form)
							.find("#username").val() != "")
							&& ($(form).find("#password").val() != null && $(
									form).find("#password").val() != "")) {
						form.submit();
					}
				},
				rules : {

					loginValue : {
						required : true,
						minlength : 6
					},
					password : {
						required : true,
						minlength : 6
					}

				},
				messages : {
					loginValue : {
						required : "请输入用户名&nbsp;&nbsp;",
						minlength : "请输入正确用名户名&nbsp;&nbsp;"

					},

					password : {
						required : "请输入密码",
						minlength : jQuery.format("请输入正确的密码")
					}

				},

				errorPlacement : function(error, element) {
					if (element.attr("id") == "username") {
						error.appendTo($(".login_tip"));
					} else if (element.hasClass("password")) {
						error.appendTo($(".login_tip"));

					}
				}

			});

	$("#loginBox_withCode").validate(
			{
				submitHandler : function(form) {
					if (($(form).find("#username").val() != null && $(form)
							.find("#username").val() != "")
							&& ($(form).find("#password").val() != null && $(
									form).find("#password").val() != "")) {

						form.submit();

					} else {
						$(".login_tip").html("");
						$(".login_tip").html("登录名或密码不能为空");

					}
				},

				rules : {

					loginValue : {
						required : true,
						minlength : 6
					},

					password : {
						required : true,
						minlength : 6
					},

					vcode : {

						required : true,
						minlength : 4,
						remote : {
							type : 'post',
							url : '/verification/checkVerifyCodeMethod',
							dataType : 'json',
							data : {
								"verifyCode" : function() {
									return $('#authCode').val()
								}
							}
						}
					}

				},
				messages : {
					loginValue : {
						required : "请输入用户名&nbsp;&nbsp;",
						minlength : "请输入正确用户名&nbsp;&nbsp;"

					},

					password : {

						required : "请输入密码",
						minlength : jQuery.format("请输入正确的密码")

					},

					vcode : {
						required : "&nbsp;&nbsp;请输入验证码",
						remote : "验证码错误"
					}

				},

				errorPlacement : function(error, element) {
					if (element.attr("id") == "username") {
						error.appendTo($(".login_tip"));
					} else if (element.hasClass("password")) {
						error.appendTo($(".login_tip"));

					} else if (element.hasClass("authCode")) {
						error.appendTo($(".login_tip"));

					}
				}

			});

})

function changeImage() {
	$("#authCodeImg").attr({
		"src" : "verification/getGenImage/68/32?id=" + Math.random() * 1000,
		"alt" : "看不清，换一张"
	});
}