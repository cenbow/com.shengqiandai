// JavaScript Document
$(function() {
	BankSelectAction();
//	var cardNum = $('#BankCardNumber');
//	cardNum.val('请输入银行卡号').css("color", "#888");
//
//	cardNum.focus(function() {
//		if ($(this).val() == "请输入银行卡号") {
//			$(this).val('');
//		}
//	}).blur(function() {
//		if (webUtil.checkVal($(this).val()) || $(this).val() == '请输入银行卡号') {
//			$(this).val('请输入银行卡号');
//		}
//
//	}).keydown(function() {
//		$("#BankCardNumber").css("color", "#444");
//	});

	// 点击提交按钮

	$(".submitUp").click(function() {
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
		if (addNewCardCheck()) {
			if ($("#ktkjfs").is(':checked')) {
				$.ajax({
					url : "/accountBank/sinaBindingBankAdvance",
					type : "post",
					dataType : "json",
					data : $("#addNewCardForm").serialize(),
					beforeSend : function(){
						art.dialog.tips(
								'正在添加银行卡...', 10);
						$(".submitUp").attr("disabled","disabled");
						$(".submitUp").text("添加中...")
					},
					success : function(data) {
						if (!data.success) {
							art.dialog.tips(
									'正在添加银行卡...', 0);
							$(".Error").html(data.msg);
							$(".submitUp").removeAttr("disabled");
							$(".submitUp").text("添加")
						} else {
							self.location.href = '/accountBank/bank';
						}
					},
					error : function(){
						art.dialog.tips(
								'正在添加银行卡...', 0);
						$(".submitUp").removeAttr("disabled");
						$(".submitUp").text("添加")
						$(".Error").html("绑卡请求异常,请稍后再试或联系客服!");
					}
				});
			} else {
				$.ajax({
					url : "/accountBank/sinaBindingBank",
					type : "post",
					dataType : "json",
					data : $("#addNewCardForm").serialize(),
					beforeSend : function(){
						art.dialog.tips(
								'正在绑定银行卡...', 10);
						$(".submitUp").attr("disabled","disabled");
						$(".submitUp").text("添加中...");
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
		}
	});
});

(function($) {
	//快捷方式
	var li_kt = function(){
		if ($("#ktkjfs").is(":checked")) {
			$("#ktkjfs").attr("checked", false);
			$("#kuaijiebox").addClass("hide");
		} else {
			$("#ktkjfs").attr("checked", true);
			$("#kuaijiebox").removeClass("hide");
		}
	}
	
	$("#li-kj").bind("click",li_kt);
	function BankSelectAction() {
		var bankCard = $('#AddBankCardCont');
		var addBlank = $('.bank-add-button');
		var showBlank = $('.bank-card-opt');
		addBlank.find('a').click(function() {
			if ($("#realnameFlag").val() == "0") {
				webUtil.jDialog.Modal('dialogRealName', 'dialogRealNameCont');
				return;
			}
			addBlank.hide();
			showBlank.show();
		});
		$('#cancelBank').click(function() {
			addBlank.show();
			showBlank.hide();
		});
		// 银行信息列表
		var bsd = $('#BankSelectDialog');
		var affirm = bsd.find('.dialog-foot .bank-bind');
		var BankSelect = $('#BankSelect');
		BankSelect.click(function() {
			webUtil.jDialog.Modal('BankSelectDialog', 'BankSelectEntry');
		});
		// 选择银行确认
		affirm.click(function() {
			var radio = $('input:radio[name="bankRadio"]:checked');
			var error = bsd.find('.mError');
			if (radio.length > 0) {
				var strs = $.trim(radio.val().split("_")[0]);
				var code = $.trim(radio.val().split("_")[1]);
				var bankval = $('#BankCheck');
				bankval.val(strs);
				$("#bankId").val(code);
				bankval.attr('data-bankcode', code);
				bsd.hide();
				error.hide();
				if(code == 'COMM' || code == 'PSBC' || code == 'BCCB'){
					$("#kuaijiebox").addClass("hide");
					$("#ktkjfs").attr("disabled","disabled");
					$("#li-kj").unbind('click');
					$("#ktkjfs").removeAttr("checked");
					$(".Error").html("该银行不支持快捷支付,绑定后只可作为提现卡.")
				}else{
					$("#ktkjfs").removeAttr("disabled");
					$("#li-kj").bind("click", li_kt);
					$(".Error").html("");
				}
				$('#overlayModal').remove();
			} else {
				error.html('请选择银行').show();
			}
		});
		$("#HouseBankName").focus(function() {
			if ($("#bankId").val() == "") {
				$(".Error").html("请先选择银行");
			}
		});
	}
	;
	window.BankSelectAction = BankSelectAction;
})(jQuery);


//点击获取验证码
function clickGetCode(){
	if (addNewCardCheck("checkCode")) {
		var wait = 60;
		var buttonObject = $("#code");
		$("#BankCardNumber").attr("readonly","readonly");
		$("#mobilePhone").attr("readonly","readonly");
		$.ajax({
			url : "/accountBank/sinaBindingBank",
			type : "post",
			dataType : "json",
			async : false,
			data : $("#addNewCardForm").serialize(),
			success : function(data) {
				if (!data.success) {
					$(".Error").html(data.msg);
					$("#BankCardNumber").removeAttr("readonly");
					$("#mobilePhone").removeAttr("readonly");
				}else{
					doWait(buttonObject, wait);
				}
			}
		});
	}
}


function addNewCardCheck(isCheckCode) {
	var province = $("#province").find("option:selected").text();
	var city = $("#city").find("option:selected").text();
	var mobilePhone = $("#mobilePhone").val();
	var checkCode = $("#checkCode").val();
	if ($("#ktkjfs").attr("checked")) {// 勾选了快捷方式
		
		if(!$("#userDeal").is(":checked")){
			$(".Error").html("*请阅读并勾选《新浪支付快捷支付服务协议》");
			return false;
		}
		if ($("#BankCheck").val() == "未选择银行") {
			$(".Error").html("*请选择银行");
			return false;
		} else {
			$(".Error").html("");
		}

		if (cardNoCheck($("#BankCardNumber").val()) == false) {
			return false;
		}

		if (province == "请选择" || city == "请选择") {
			$(".Error").html("*请选择省份和城市");
			return false;
		} else {
			$(".Error").html("");
		}

		return checkPhone(mobilePhone, checkCode,isCheckCode);

	} else {// 没有勾选快捷方式

		if ($("#BankCheck").val() == "未选择银行") {
			$(".Error").html("*请选择银行");
			return false;
		} else {
			$(".Error").html("");
		}

		if (cardNoCheck($("#BankCardNumber").val()) == false) {
			return false;
		}

		if (province == "请选择" || city == "请选择") {
			$(".Error").html("*请选择省份和城市");
			return false;
		} else {
			$(".Error").html("");
		}
	}
	return true;
}

// 没有勾选快捷方式复选框
function noCheckBox() {
	if ($("#BankCheck").val() == "未选择银行") {
		$(".Error").html("*请选择银行");
		return false;
	} else {
		$(".Error").html("");
	}

	if (cardNoCheck($("#BankCardNumber").val()) == false) {
		return false;
	}

	var province = $("#province").find("option:selected").text();
	var city = $("#city").find("option:selected").text();

	if (province == "请选择" || city == "请选择") {
		$(".Error").html("*请选择省份和城市");
		return false;
	} else {
		$(".Error").html("");
	}
}

// 银行卡号检查

function cardNoCheck(bankno) {

	if (bankno == "" || bankno == null) {
		$(".Error").html("*卡号不能为空");
		return false;
	} else if (bankno == "请输入银行卡号") {
		$(".Error").html("*卡号不能为空");
		return false;
	}

	var reg = /^\d{16,19}$|^\d{6}[- ]\d{10,13}$|^\d{4}[- ]\d{4}[- ]\d{4}[- ]\d{4,7}$/;
	if (reg.test(bankno)) {
		$(".Error").html("");

	} else {
		$(".Error").html("*卡号输入不正确，请检查");
		return false;
	}

}

// 城市下拉框
function checkCity() {
	var province = $("#province").find("option:selected").text();
	var city = $("#city").find("option:selected").text();

	if (province == "请选择" || city == "请选择") {
		$(".Error").html("*请选择省份和城市");
		return false;
	} else {
		$(".Error").html("");
	}
}

// 检查 输入手机号码和验证码
function checkPhone(phoneNum, phoneCode,isChcekCode) {

	var mobile = /^0?(13|15|14|18)[0-9]{9}$/;
	if (phoneNum == null || phoneNum == "") {
		$(".Error").html("*手机号不能为空");
		return false;
	} else if (!mobile.test(phoneNum)) {
		$(".Error").html("*手机号码不对,请检查。");
		return false;
	} else {
		$(".Error").html("");
	}
	if(isChcekCode != "checkCode"){
		// 如果验证码为空
		if (phoneCode == null || phoneCode == "") {
			$(".Error").html("*验证码不能为空");
			return false;
		} else {
			$(".Error").html("");
		}
	}
	return true;
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
