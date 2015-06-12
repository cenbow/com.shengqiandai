// JavaScript Document
$(function() {
	BankSelectAction();
	var cardNum = $('#BankCardNumber');
	cardNum.val('请输入银行卡号');
	$("#HouseBankName").blur(function() {
		if (checkBankName($("#HouseBankName").val()) == false) {
			return ;
		}else{
			$(".Error").val('').hide();
		}

	});
	
	$("#BankCity").blur(function() {
		if (checkBankName($("#BankCity").val()) == false) {
			return ;
		}else{
			$(".Error").val('').hide();
		}

	});

	cardNum.focus(function() {
		if ($(this).val() == "请输入银行卡号") {
			$(this).val('');
		}
	}).blur(function() {
		if (webUtil.checkVal($(this).val()) || $(this).val() == '请输入银行卡号') {
			$(this).val('请输入银行卡号');
		}
		cardNoCheck($("#BankCardNumber").val());
	});

	$("#BankCardNumber").blur(function() {
		var BankCardNumber = $(this).val();
		if (cardNoCheck(BankCardNumber)) {
			$.ajax({
				url : "/accountBank/checkBankCard",
				type : "post",
				dataType : "json",
				data : {
					"bankCard" : BankCardNumber
				},
				success : function(data) {
					if (!data) {
						$(".Error").html("该卡号已登记").show();
					}
				}
			});
		}

	});
	
	$("#getYzm").click(function(){
		$.ajax({
			url : "/accountBank/sinaBindingBank",
			type : "post",
			dataType : "json",
			data :$("#addNewCardForm").serialize(),
			success : function(data) {
				if (!data) {
					$(".Error").html("该卡号已登记").show();
				}
			}
		});
	});
	$("#AddBankCardButton").click(function() {
		if (addNewCardCheck()) {
			var form = $("#addNewCardForm");
			if($("#ktkjfs").attr("checked")){
				
			}else{
				
			}
			form.attr("method", "post");
			form.attr("action", "/accountBank/addBank");
			form.submit();
		}

	});

});

(function($) {

	function BankSelectAction() {
		var bankCard = $('#AddBankCardCont');
		var addBlank = $('.bank-add-button');
		var showBlank = $('.bank-card-opt');
		addBlank.find('a').click(function() {
			if ($("#realnameFlag").val() == "0") {
				webUtil.jDialog.Modal('dialogRealName', 'dialogRealNameCont');
				return;
			}
//			addBlank.hide();
//			showBlank.show();
			self.location.href = '/accountBank/addBankDetail';
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
				var strs = $.trim(radio.val().slice(0, -4));
				var code = $.trim(radio.val().slice(-3));
				var bankval = $('#BankCheck');
				bankval.val(strs);
				$("#bankId").val(code);
				bankval.attr('data-bankcode', code);
				bsd.hide();
				error.hide();
				$('#overlayModal').remove();
			} else {
				error.html('请选择银行').show();
			}
		});
		$("#HouseBankName").focus(function() {
			if ($("#bankId").val() == "") {
				$(".Error").html("请先选择银行").show();
			}
		});
	}
	;
	window.BankSelectAction = BankSelectAction;
})(jQuery);
function deleteUnbindCard(obj) {
	var option = {
		text : '确定要删除这张银行卡?',
		confirmCallback : function() {
			var form = $(obj).prev();
			form.attr("method", "post");
			form.attr("action", "/accountBank/deleteBank");
			form.submit();
		},
		cancelCallback : function() {
		}
	};
	webUtil.jDialog.Prompt(option);
}
function bindCard(obj) {
	var form = $(obj).prev().prev();
	form.attr("method", "post");
	form.attr("action", "/accountBank/bindBank");
	form.submit();
}

function addNewCardCheck() {
	if (cardNoCheck($("#BankCardNumber").val()) == false) {
		return false;
	}

	if ($("#BankCheck").val() == "未选择银行") {
		$(".Error").html("*请选择银行").show();
		return false;
	} else {
		$(".Error").hide();
	}

	if ($("#BankCity").val() == "" || $("#BankCity").val() == "中文/拼音") {
		$(".Error").html("*请输入开户城市").show();
		return false;
	} else {
		$(".Error").hide();
	}
	if ($("#HouseBankName").val() == "" || $("#HouseBankName").val() == "中文/拼音") {
		$(".Error").html("*请输入开户行名称").show();
		return false;
	} else {
		$(".Error").hide();
	}
	return true;
}
function cardNoCheck(bankno) {
	
	if (bankno == "" || bankno == null) {
		$(".Error").html("*卡号输入不正确，请检查").show();
		return false;
	}

	var reg = /^\d{16,19}$|^\d{6}[- ]\d{10,13}$|^\d{4}[- ]\d{4}[- ]\d{4}[- ]\d{4,7}$/;
	if (reg.test(bankno)) {
		$(".Error").hide();
		return true;
	} else {
		$(".Error").html("*卡号输入不正确，请检查").show();
		return false;
	}
}
function checkBankName(bankName) {
	var reg = /^[\u4e00-\u9fa5]+$/i;
	if (bankName == "" || bankName == null) {
		$(".Error").html("*请输入开户城市").show();
		return false;
	} else if (!reg.test(bankName)) {
		$(".Error").html("*开户城市和开户行只能是中文").show();
		return false;
	}
	return true;
}
