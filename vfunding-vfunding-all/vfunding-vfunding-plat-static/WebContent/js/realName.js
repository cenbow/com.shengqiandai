// JavaScript Document
$(function() {
	var html = '<div id="dialogRealName" class="dialog-main" style="width:400px">';
	html += '<div class="dialog-head"><h2>实名认证</h2><a href="javascript:;" class="closeModal r3"><i class="icons">关闭</i></a></div>';
	html += '<div id="dialogRealNameCont">';
	html += '<p style="text-align:center; padding:15px 0; color:#999">您未进行实名认证，请填写认证信息，系统将立即为您认证。</p>';
	html += '<ul style="padding-left:15px">';
	html += '<li style="height:60px; line-height:40px; position:relative">';
	html += '<label style="float:left; display:inline; margin-right:5px; width:70px; text-align:right">姓名:</label>';
	html += '<input type="text" id="realName" class="input">';
	html += '</li>';
	html += '<li style="height:60px; line-height:40px; position:relative">';
	html += '<label style="float:left; display:inline; margin-right:5px; width:70px; text-align:right">身份证号:</label>';
	html += '<input type="text" id="idCardNo" class="input">';
	html += '</li>';
	html += '</ul>';
	html += '<div style="padding:0 15px 15px 90px; color:#bf0000; display:none;" id="tip">请核对您的姓名和身份证号</div>';
	html += '</div>';
	html += '<div class="dialog-foot"><div class="bank-action">';
	html += '<a href="javascript:;" class="r3 bank-delete closeModal"><i class="icons yclose"></i>取消</a>';
	html += '<a href="javascript:realNameAuthen();" class="r3 gbtn" id="realNameAuthenBt"><i class="icons cm-white"></i>立即认证</a>';
	html += '</div></div>';
	html += '</div></div>';
	$('body').append(html);
	// showAuthenModal();
});
function showAuthenModal() {
	if ($("#realnameFlag").val() == "0") {
		webUtil.jDialog.Modal('dialogRealName', 'dialogRealNameCont');
	}
}
// 检查身份证号码
function checkIdCardNo() {
	var cardno = $("#idCardNo").val();
	var reg = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
	var re = new RegExp(reg);
	var tip = $("#tip");
	if (cardno == "" || cardno.length == 0) {
		initTip();
		tip.html('请输入身份证号码').show();
		return false;
	} else if (!re.test(cardno)) {
		initTip();
		tip.html('身份证号可能有误，请检查后再进行认证').show();
		return false;
	} else {
		return true;
	}
}
// 检查姓名
function checkRealName() {
	var realName = $("#realName").val();
	var tip = $("#tip");
	if (realName == "" || realName.length == 0) {
		initTip();
		tip.html('请输入姓名').show();
		return false;
	} else {
		return true;
	}
}
// 初始化输入提示
function initTip() {
	$("#tip").html('').hide();
}
// 实名认证
function realNameAuthen() {
	var tip = $("#tip");
	initTip();
	if (checkRealName() && checkIdCardNo()) {
		$.ajax({
			dataType : 'json',
			url : "/user/saveRealName",
			data : {
				"cardId" : $("#idCardNo").val(),
				"realname" : $("#realName").val()
			},
			type : 'POST',
			beforeSend : function() {
				$("#realNameAuthenBt").html(
						'<i class="icons cm-white"></i>认证中...').addClass(
						"gbtn-disabled").attr("href", "javascript:;");
			},
			success : function(data) {
				if (data.success) {
					$("#dialogRealName").hide();
					$("#overlayModal").remove();
					var option = {
						text : '认证成功',
						buttonCancel : false,
						icon : 'success-green60',
						confirmCallback : function() {
							$("#realnameFlag").val();
							window.location.reload();
						},
						cancelCallback : function() {
							$("#realnameFlag").val();
							window.location.reload();
						}
					};
					webUtil.jDialog.Prompt(option);
				} else {
					tip.html('认证失败,' + getResultMsg(data)).show();
				}
				$("#realNameAuthenBt").html('<i class="icons cm-white"></i>认证')
						.removeClass("gbtn-disabled").attr("href",
								"javascript:realNameAuthen();");
			},
			error : function(d) {
				tip.html(d.responseText).show();
				$("#realNameAuthenBt").html('<i class="icons cm-white"></i>认证')
						.removeClass("gbtn-disabled").attr("href",
								"javascript:realNameAuthen();");
			}
		});
	}
}
function getResultMsg(data) {
	switch (data) {
	case 0:
		return "不存在的身份证";
		break;
	case 1:
		return "验证成功";
		break;
	case 2:
		return "数据库中存在该身份证号，但是与实名不匹配";
		break;
	case -3:
		return "数据库中存在该身份证号，但是该角色已注册";
		break;
	case 4:
		return "身份证校验失败，请检查身份证号和姓名";
		break;
	case 5:
		return "身份证校验访问出错，请与管理员联系！";
		break;
	case 6:
		return "网站数据库查询访问出错，请与管理员联系！";
		break;
	case 7:
		return "请登录网站！";
		window.location.reload();
		break;
	case 8:
		return "您输入的身份证号码无效！";
		break;
	case 9:
		return "您未满16周岁，无法进行实名认证！";
		break;
	case -98:
		return "一张身份证最多只能绑定三个账号!";
		break;
	default:
		return "请联系客服进行人工绑定!";
		break;
	}
}
