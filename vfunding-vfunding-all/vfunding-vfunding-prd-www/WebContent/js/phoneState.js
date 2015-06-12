// JavaScript Document

$(function() {

	$('.ui-form').on('click', ".input_tip", function() {
		$(this).hide().parent().children('input').trigger('focus');
	}).on('focus', 'input', function() {
		$(this).parent().children('.input_tip').hide();
	}).on('blur', 'input', function() {
		if (!this.value) {
			$(this).parent().children('.input_tip').show();
		}
	}).find("input").trigger("focus");

	jQuery.validator.addMethod("isMobile", function(value, element) {
		var length = value.length;
		var mobile = /^0?(13|15|18)[0-9]{9}$/;
		return this.optional(element) || (length == 11 && mobile.test(value));
	}, "请输入正确的手机号码");

	// KEY IN NEW MOBILE PHONE

	$("#newPhone").validate({

		submitHandler : function(form) {

			// form.submit();

			$.ajax({
				type : "POST",
				url : "/user/savePhoneState",
				dataType : "json",
				data : {
					'mobile' : $(form).find("#mobile").val(),
					'vcode' : $(form).find("#newCheckCode").val()
				},
				async : false,
				success : function(data) {
					if (data.success) {
						art.dialog({
							content : '您的手机已验证成功',
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
					$("#newPhone input").attr('disabled', true);
					$('.nextStep').val('验证中...');
				},
				complete : function(XHR, TS) {
					$("#newPhone input").attr('disabled', false);
					$('.nextStep').val('提交');
				}
			});

		},

		rules : {
			phone : {
				required : true,
				isMobile : true,
				remote : {
					type : "POST",
					url : "/checkAddphone",
					dataType : "json",
					data : {
						"mobile" : function() {
							return $("#mobile").val();
						}
					}

				}

			},

			vcode : {
				required : true,
				minlength : 4,
				maxlength : 6,
				remote : {
					type : "POST",
					url : "/verification/checkVerifyCodeMethod",
					dataType : "json",
					data : {
						"verifyCode" : function() {
							return $("#newCheckCode").val();
						}
					}

				}
			}

		},
		messages : {

			phone : {
				required : "请输入手机号码",
				isMobile : "请输入正确的手机号码",
				remote : "该号码已存在",
			},

			vcode : {
				required : "请输入验证码",
				minlength : "至少输入4位数字验证码",
				maxlength : "验证码不能超过6位数字",
				remote : "验证码错误"
			}
		},

		success : function(label) {
			label.html("&nbsp;").addClass("valid");
			$('#errorMsg').html("");
		}

	});

})