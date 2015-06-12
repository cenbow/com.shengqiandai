$(function() {
	$('#username').blur(function() {
		$.getJSON('/getUserLoginInfo', {
			value : $(this).val()
		}, function(data) {
			if (data.success) {
				if (data.msg > 2) {
					$(".codeBox").show();
				}
				$('#errorNum').val(data.msg);
			}
		});
	});

	var uname = $("#username").val();
	var pwd = $("#password").val();
	if (uname.length > 0) {
		$(".keyuname").hide();

	} else {
		$(".keyuname").show();
	}

	if (pwd.length > 0) {
		$(".keyuPwd").hide();
	} else {
		$(".keyuPwd").show();
	}

	$('.ui-form').on('click', ".placeholder", function() {
		$(this).hide().parent().children('input').trigger('focus');
	}).on('focus', 'input', function() {
		$(this).parent().children('.placeholder').hide();
	}).on('blur', 'input', function() {
		if (!this.value) {
			$(this).parent().children('.placeholder').show();
		}
	})

	var wrongType = 0, // 用户密码的错误类型

	wrongHtml = new Array("", "请输入用户名", "用户名长度太短", "用户名长度超过16位", "您的用户名或密码错误",
			"超时，请重新登陆", "请输入密码", "密码长度小于6位", "密码中含有非法字符", "请输入用户名和密码",
			"密码不能超过16位", "请输入验证码", "验证码不能超过4个", "请输入用户名，密码和验证码", "验证码错误");

	$('#authCode').blur(function() {
		var vcode = $(this).val();
		if (vcode.length > 0) {
			$.getJSON('/verification/checkVerifyCode/' + vcode, function(data) {
				if (!data.success) {
					$(".login_tip").html("");
					$(".login_tip").html("验证码错误");
					wrongType = 14;
				} else {
					$(".login_tip").html("");
					wrongType = 0;
				}
			});
		}

	});

	$('body').focus(); // 让输入框不再自动获取焦点

	$("#submit_btn").click(function() {
		wrongType = 0;
		var uname = $("#username").val(); // 用户名
		var pwd = $("#password").val(); // 用户密码
		$.ajax({
			type : 'get',
			url : '/getUserLoginInfo',
			cache : false,
			async : false,
			dataType : 'json',
			data : {value:uname},
			success : function(data) {
				if (data.success) {
					if (data.msg > 2) {
						$(".codeBox").show();
					}
					$('#errorNum').val(data.msg);
				}
			}
		});
		
		
		var plength = pwd.length;
		var nlength = uname.length;
		var yzm = null;

		var ylength = null;

		if ($("#authCode").is(":visible")) {

			yzm = $("#authCode").val();
			ylength = yzm.length;

			if (ylength == 0) {
				wrongType = 11;
			} else if (ylength > 4) {
				wrongType = 12;
			}
		}

		if (nlength == 0 && plength == 0) {
			wrongType = 9;
		} else if (nlength == 0) {

			wrongType = 1;

		} else if (nlength > 0 && nlength < 2) {

			wrongType = 2;

		} else if (plength == 0) {

			wrongType = 6;
		} else {
			var patrn = /^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,16}$/;
			if (plength < 6)
				wrongType = 7;
			//if (plength > 16)
				//wrongType = 10;
			// if (plength > 6 && plength < 20) {
			// if (!patrn.exec(pwd))
			// wrongType = 8;
			// }
		}
		inputTip(wrongHtml, wrongType);
		if (wrongType == 0) {// 在用户输入信息完全合法的情况下，即数组下标全部为0 开始执行ajax验证
			$("#loginForm input").attr('disabled', true);
			loginAjax(uname, pwd, yzm);
		}
	});

	var inputTip = function(tipHtml, tipNum) {
		$(".login_tip").html("");
		$(".login_tip").html(tipHtml[tipNum]);
	}

	var loginAjax = function(uname, pwd, vcode) {
		$("#submit_btn").addClass("logining");
		var returnUrl = $('#returnUrl').val();
		var d;
		if ($("#authCode").is(":visible")) {
			if ($.trim(returnUrl).length > 0) {
				d = {
					"loginValue" : uname,
					"password" : pwd,
					"vcode" : vcode,
					"returnUrl" : returnUrl
				}
			} else {
				d = {
					"loginValue" : uname,
					"password" : pwd,
					"vcode" : vcode
				}
			}

		} else {
			if ($.trim(returnUrl).length > 0) {
				d = {
					"loginValue" : uname,
					"password" : pwd,
					"returnUrl" : returnUrl
				}
			} else {
				d = {
					"loginValue" : uname,
					"password" : pwd
				}
			}

		}

		$.ajax({
			type : 'post',
			url : '/login',
			cache : false,
			async : false,
			dataType : 'json',
			data : d,
			success : function(data) {
				if (data.success) {
					$('#errorNum').val('1');
					if (data.neesRedirect) {
						location.href = data.redirectUrl;
					}
				}
			},
			error : function(d) {

				var errorNum = Number($('#errorNum').val());
				if (errorNum == null || errorNum == '') {
					errorNum = 1;
				}
				if (errorNum > 2) {
					$(".codeBox").show();
				}
				$(".login_tip").html("");

				$("#loginForm input").attr('disabled', false);
				$("#submit_btn").removeClass("logining");
				if (d.responseText == '密码错误') {
					var newNum = errorNum + 1;
					$('#errorNum').val(newNum);
					var residueNum = 5 - errorNum;
					if (residueNum == 0) {
						$(".login_tip").html('账号将被锁定24小时，请联系客服。');
					} else {
						$(".login_tip").html(
								d.responseText + ',您还有' + (5 - errorNum)
										+ '次机会');
					}
				} else {
					$(".login_tip").html(d.responseText);
				}
			}
		})
	}

	$(document).bind("keypress", function(ev) {

		var keycode = ev.which;
		if (keycode == 13) {
			ev.preventDefault();
			$("#submit_btn").trigger("click");
		}

	});

});

function changeImage() {
	$("#authCodeImg").attr({
		"src" : "/verification/getGenImage/68/32?id=" + Math.random() * 1000,
		"alt" : "看不清，换一张"
	});
}


function getUserLoginInfo(){
	
}