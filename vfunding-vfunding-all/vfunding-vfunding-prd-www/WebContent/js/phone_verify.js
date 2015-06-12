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
		var mobile = /^0?(13|15|14|18|17)[0-9]{9}$/;
		return this.optional(element) || (length == 11 && mobile.test(value));
	}, "请输入正确的手机号码");

	$("#phoneForm").validate({

		submitHandler : function(form) {

			/*
			 * $.ajax({ url:"#", type:"POST",
			 * data:{"authCode":$(form).find("#authCode").val(),"mobile":$(form).find("#mobile").val()},
			 * dataType:"json", success:function(data){
			 * 
			 * if(data.result){
			 * 
			 * }else if(data.message){
			 * 
			 * 
			 * }else{
			 * 
			 *  }
			 *  } });
			 */
			form.submit();
		},

		rules : {

			mobile : {
				required : true,
				isMobile : true,
				remote : {
					type : "POST",
					url : "/checkRegister",
					dataType : "json",
					data : {
						"value" : function() {
							return $("#mobile").val();
						}
					}

				}
			},
			vcode : {
				required : true,
				minlength : 4,
				remote : {
					type : "POST",
					url : "/verification/checkVerifyCodeMethod",
					dataType : "json",
					data : {
						"verifyCode" : function() {
							return $("#vcode").val();
						}
					}

				}

			}

		},
		messages : {

			mobile : {
				required : "手机号码不能为空",
				isMobile : "请输入正确的手机号码",
				remote : "该号码已经被注册"

			},

			vcode : {
				required : "验证码不能为空",
				minlength : "验证码至少是4位",
				remote : "验证码错误"

			}

		},

		success : function(label) {
			// set &nbsp; as text for IE
			label.html("&nbsp;").addClass("valid");

			// label.addClass("checked").html("");

		}

	});

})