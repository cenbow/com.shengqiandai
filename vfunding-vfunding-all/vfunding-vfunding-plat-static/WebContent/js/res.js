// JavaScript Document
$(function() {
	$("#kinMaxShow").kinMaxShow({
						// 设置焦点图高度(单位:像素) 必须设置 否则使用默认值 500
						height : 486,
						// 设置焦点图 按钮效果
						button : {
							normal : {
								background : 'url(http://static.vfunding.cn/images/button.png) no-repeat -14px 0',
								marginRight : '8px',
								border : '0',
								right : '44%'
							},
							focus : {
								background : 'url(http://static.vfunding.cn/images/button.png) no-repeat 0 0',
								border : '0'
							}
						}

					});

});

$(function() {

	// 显示隐藏文字
	$(".username_label .input_text").reg_tip();
	$(".password_label .input_text").reg_tip();
	$(".email_label input.input_text").reg_tip();
	$(".jieshao_label input.input_text").reg_tip();

	// 注册各种验证
	$.formValidator.initConfig({
		theme : "126",
		submitOnce : true,
		formID : "zhuce",
		onError : function(msg) {
			//alert(msg);
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});

	$("#username").formValidator({
		onCorrect : "该用户名可以注册"
	}).inputValidator({
		min : 6,
		max : 12,
		onError : "你输入的用户长度不正确"
	}).regexValidator({
		regExp : "username",
		dataType : "enum",
		onError : "用户名格式不正确"
	}).ajaxValidator({
		data : {
			"value" : function() {
				return $("#username").val();
			}
		},
		dataType : "json",
		async : true,
		url : "/checkRegisterUserName",
		success : function(data) {
			if (data) {
				return true;
			}
			return false;
		},
		buttons : $("#button"),
		error : function(jqXHR, textStatus, errorThrown) {
			alert("服务器没有返回数据，可能服务器忙，请重试" + errorThrown);
		},
		onError : "该用户名不可用，请更换!",
		onWait : "正在进行合法性校验，请稍候..."
	});

	$("#password").formValidator({
		tipCss : {
			height : 28,
			width : 160
		}
	}).inputValidator({
		min : 6,
		max : 16,
		empty : {
			leftEmpty : false,
			rightEmpty : false,
			emptyError : "密码两边不能有空符号"
		},
		onError : "密码长度错误"
	}).passwordValidator({
		compareID : "username",
		onErrorContinueChar : '',
		onErrorSameChar : '',
		onErrorCompareSame : ''
	});

	$("#password2").formValidator({
		tipCss : {
			height : 28,
			width : 160
		}
	}).inputValidator({
		min : 1,
		empty : {
			leftEmpty : false,
			rightEmpty : false,
			emptyError : "重复密码两边不能有空符号"
		},
		onError : "确认密码不能为空"
	}).compareValidator({
		desID : "password",
		operateor : "=",
		onError : "2次密码不一致"
	});

	$("#mobile")
			.formValidator({
				onEmpty : "请输入手机",
				onCorrect : "恭喜你,你输对了"
			})
			.inputValidator({
				min : 6,
				max : 100,
				onError : "你输入的手机不正确"
			})
			.regexValidator(
					{
						regExp : "^(13[0-9]{9}|15[012356789][0-9]{8}|18[0256789][0-9]{8}|147[0-9]{8}$)",
						onError : "你输入的手机格式不正确"
					})
			/*
			 * .ajaxValidator({ dataType : "json", async : true, url :
			 * "/isCheckRegisterUserName", success : function(data){ if(data){
			 * return true; } return false; }, buttons: $("#button"), error:
			 * function(jqXHR, textStatus,
			 * errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);}, onError :
			 * "请先输入用户名信息", onWait : "正在进行合法性校验，请稍候..." })
			 */.ajaxValidator({
				data : {
					"value" : function() {
						return $("#mobile").val();
					}
				},
				dataType : "json",
				async : true,
				url : "/checkRegister",
				success : function(data) {
					if (data) {
						return true;
					} else {
						return false;
					}

				},
				buttons : $("#button"),
				error : function(jqXHR, textStatus, errorThrown) {
					alert("服务器没有返回数据，可能服务器忙，请重试" + errorThrown);
				},
				onError : "该号码不可用，请更换!",
				onWait : "正在进行合法性校验，请稍候..."
			});

	$("#auth_code").formValidator({
		onEmpty : "请输入验证码",
		onCorrect : "恭喜你,你输对了"
	}).inputValidator({
		min : 4,
		max : 10,
		onError : "请输入正确的验证码"
	}).ajaxValidator({
		data : {
			"verifyCode" : function() {
				return $("#auth_code").val();
			}
		},
		dataType : "json",
		async : true,
		url : "/verification/checkVerifyCodeMethod",
		success : function(data) {
			if (data) {
				return true;
			}
			return false;
		},
		buttons : $("#button"),
		error : function(jqXHR, textStatus, errorThrown) {
			alert("服务器没有返回数据，可能服务器忙，请重试" + errorThrown);
		},
		onError : "验证码错误!",
		onWait : "正在进行合法性校验，请稍候..."
	});

	$("#jieshao").formValidator({
		onCorrect : "该用户存在",
		onEmpty : "",
		empty : true
	}).ajaxValidator({
		// data:{"introducer":function(){return $("#jieshao").val();}},
		// //此处不需要传参
		dataType : "json",
		async : true,
		url : "/checkIntroducer",
		success : function(d) {
			if (d) {
				return true;
			}
			return false;
		},
		buttons : $("#button"),
		error : function(jqXHR, textStatus, errorThrown) {
			alert("服务器没有返回数据，可能服务器忙，请重试" + errorThrown);
		},
		onError : "介绍人信息错误",
		onWait : "正在进行合法性校验，请稍候..."
	});

	$("#jieshao").blur(function() {
		if ($("#jieshao").val() == null || $("#jieshao").val() == "") {
			$("#jieshaoTip").find("b").removeClass("ico-warning");
			return false;
		}
	});

});
