$(function() {
	webUtil.CustomCheckbox($('.yCheckbox'));
	$('#selectBank').click(function() {
		webUtil.jDialog.Modal('BankSelectDialog', 'BankSelectEntry');
	});
	var bankName = $('#BankCheck');
	var bankCity = $('#BankCity');
	var houseName = $('#HouseBankName');
	var cardNum = $('#BankCardNumber');
	var addBankOpts = $('#addBankOpts');
	var bankDialog = $('#BankSelectDialog');
	var affirm = bankDialog.find('.dialog-foot .bank-bind');
	var addsList = $('.adds-bankcard');
	var mdir = addsList.find('.banks-dir');
	mdir.append('<i class="icons" style="display:none"></i>');
	var addBtn = addsList.find('.adds-card');
	var targetClass = function(a, b) {
		a.addClass('selected').siblings().removeClass('selected');
		b.find('.icons').hide();
		a.find('.icons').show();
	};
	mdir.click(function() {
		var tar = $(this);
		if (!tar.hasClass("selected")) {
			bindCard(tar, mdir);
		}
	});
	cardNum.val('请输入银行卡号')
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

	houseName.blur(function() {
		var houseTip = $('#HouseBankNameTip');
		if (checkBankName($(this).val()) == false) {
			return;
		} else {
			houseTip.html('').hide();
		}

	});

	bankCity.blur(function() {
		var cityTip = $('#BankCityTip');
		if (checkCityName($(this).val()) == false) {
			return;
		} else {
			cityTip.html('').hide();
		}

	});

	// 选择银行确认
	affirm.click(function() {
		var radio = $('input:radio[name="bankRadio"]:checked');
		var error = bankDialog.find('.mError');
		if (radio.length > 0) {
			var bankcode = null;
			var strs = $.trim(radio.val().slice(0, -4));
			var code = $.trim(radio.val().slice(-3));
			mdir.each(function() {
				var bcode = $(this).attr('data-bankcode');
				if (bcode == code) {
					bankcode = code
				}
			});
			$("#bankId").val(code);
			bankName.val(strs).css('color', '#333');
			bankName.attr('data-bankcode', code);
			bankDialog.hide();
			error.hide();
			$('#overlayModal').remove();
		} else {
			error.html('请选择银行').show();
		}
	});
	$("#HouseBankName").focus(function() {
		if ($("#bankId").val() == "") {
			$('#BankNameTip').html("请先选择银行").show();
		}
	});
	$('#cancelAddBankCard').click(function() {
		addBankOpts.animate({
			height : 0,
			opacity : 0
		}).hide()
		addBtn.removeClass('adds-current');
	});
	addBtn.click(function() {

		if ($("#realnameFlag").val() == "0") {

			webUtil.jDialog.Modal('dialogRealName', 'dialogRealNameCont');

			return;
		}
		addBankOpts.animate({
			height : 310,
			opacity : 1
		}, 100).show();
		addBtn.addClass('adds-current');
	});
	$('#addBankCard').click(
			function() {
				var numTip = $('#BankCardNumberTip');
				var nameTip = $('#BankNameTip');
				var cityTip = $('#BankCityTip');
				var houseTip = $('#HouseBankNameTip');
				var cred = '<i class="icons reg-error"></i>';
				numTip.hide();
				nameTip.hide();
				cityTip.hide();
				houseTip.hide();
				if (webUtil.checkVal(cardNum.val())
						|| cardNum.val() == '请输入银行卡号') {
					numTip.html(cred + '请输入银行卡号').show();
				} else if (webUtil.checkVal(bankName.val())
						|| bankName.val() == '未选择银行') {
					nameTip.html(cred + '请选择银行').show();
				} else if (checkBankName(houseName.val()) == false) {
					return;
				} else if (checkCityName(bankCity.val()) == false) {
					return;
				} else {
					addnewcard();
				}
			});
	$(".tx111")
			.click(
					function() {
						// webUtil.stopBubble(e);

						if ($("#realnameFlag").val() == "0") {
							webUtil.jDialog.Modal('dialogRealName',
									'dialogRealNameCont');
							return;
						}
						if ($("#msg").val() == 'noBindCard') {
							webUtil.arrowPrompt($("#addCard"), '请先绑定一张银行卡',
									'err');
							return;
						}
						if ($("#withdrawMoney").val() > 0
								&& Number($("#realTackOut").html()) <= 0) {
							$("#errMsg").html("到账金额不足").show();
							return;
						}
						if (!checkPassword()) {
							return;
						}

						if (checkWithdrawCash()) {
							var money = $('#withdrawMoney').val();
							var payPassword = $('#payPassword').val();
							var hongbao;
							if ($("#hongbao").attr("checked")) {
								hongbao = 'yes'
							} else {
								hongbao = 'no'
							}
							$.ajax({
										type : "POST",
										url : "/accountCash/applyCash",
										data : {
											money : money,
											payPassword : payPassword,
											useHongbao:hongbao
										},
										async : false,
										dataType : 'json',
										success : function(data) {
											if (data.success) {
												$('#withdrawMoney').val('');
												$('#payPassword').val('');
												art.dialog({
															content : '提现申请成功，请等待审核！',
															ok : function() {
																location.href = '/account/rechargeCash';
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
											$('#tijiao').attr('disabled', true);
											$("#txForm input").attr('disabled',
													true);
										},
										complete : function(XHR, TS) {
											$("#txForm input").attr('disabled',
													false);
											$('#tijiao')
													.attr('disabled', false);
										}
									});

						}

					});

	$('#withdrawMoney').blur(function() {
		if (checkWithdrawCash()) {
			getTakeCashInfo();
		}
	});

	$('#hongbao').click(function() {
		if (checkWithdrawCash()) {
			getTakeCashInfo();
		}
	});

	$('#payPassword').blur(function() {
		if (checkPassword()) {
			$.ajax({
				type : "POST",
				url : "/verification/checkPayPassword",
				dataType : "json",
				data : {
					payPassword : $(this).val()
				},
				success : function(data) {
					if (!data) {
						$("#passwordErrorMsg").html("交易密码错误").show();
						return false
					} else {
						$("#passwordErrorMsg").html('').hide();
						return true;
					}
				}
			});
		}
	});

});

function addnewcard() {
	if (cardNoCheck($("#BankCardNumber").val())) {
		$.ajax({
			dataType : 'json',
			url : "/accountBank/checkBankCard",
			data : {
				"bankCard" : $("#BankCardNumber").val()
			},
			type : 'POST',
			success : function(data) {
				if (!data) {
					$('#BankCardNumberTip').html(
							'<i class="icons reg-error"></i>该银行卡已经添加了').show();
				} else {
					$("#BankCardNumberTip").html("").hide();
					var form = $("#addNewCardForm");
					form.submit();
				}
			}
		});
	}
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

function checkWithdrawCash() {
	var userTypeId = $("#userTypeId").val();
	var balance = Number($("#useMoney").val());
	var withdrawCash = Number($("#withdrawMoney").val());
	if (withdrawCash == " " || withdrawCash == null) {
		$("#withdrawCashErr").html('<i class="icons reg-error"></i>输入取款金额')
				.show();
		$('#withdrawFee').html('0.00');
		return false;
	/*} else if (withdrawCash > 500000) {
		$("#withdrawCashErr").html(
				'<i class="icons reg-error"></i>单次提现金额不能超过500000').show();
		$('#withdrawFee').html('0.00');
		return false;*/
	} else if (withdrawCash != "" && withdrawCash != null) {
		var reg = /^[0-9]+([.]\d{1,2})?$/;
		if (!reg.test(withdrawCash)) {
			$("#withdrawCashErr").html('<i class="icons reg-error"></i>输入金额有误')
					.show();
			$('#withdrawFee').html('0.00');
			$('#realTackOut').html('0.00');
			return false;
		} else {
			if ((withdrawCash - balance) > 0) {
				$("#withdrawCashErr").html(
						'<i class="icons reg-error"></i>余额不足').show();
				$('#withdrawFee').html('0.00');
				$('#realTackOut').html('0.00');
				return false;
			} else if (withdrawCash < 1) {
				$("#withdrawCashErr").html(
						'<i class="icons reg-error"></i>输入金额有误').show();
				$('#withdrawFee').html('0.00');
				$('#realTackOut').html('0.00');
				return false;
			} else {
				$("#withdrawCashErr").html("").hide();
				return true;
			}
		}
	}
}
/**
 * 获取取现手续费
 */
function getTakeCashInfo() {
	var takeMoney = Number($("#withdrawMoney").val());
	var hongbao;
	if ($("#hongbao").attr("checked")) {
		hongbao = 'yes'
	} else {
		hongbao = 'no'
	}

	$.post('/accountCash/getCashFee', {
		takeMoney : takeMoney,
		hongbao : hongbao
	}, function(data) {
		res = $.parseJSON(data);
		if (res.success) {
			$('#withdrawFee').html(res.obj.fee);
			$('#realTackOut').html(res.obj.real);
			$('#hongbaoResult').html(res.obj.hongbaoResult);
		}
	});
}

function checkPassword() {
	if (checkWithdrawCash()) {
		var password = $('#payPassword').val();
		if (password == null || password == '' || password.length < 4) {
			$("#passwordErrorMsg").html("交易密码错误").show();
			return false
		} else {
			$("#passwordErrorMsg").html("").hide();
			;
			return true;
		}

	}
}

function checkBankName(bankName) {
	var houseTip = $('#HouseBankNameTip');
	var reg = /^[\u4e00-\u9fa5]+$/i;
	var cred = '<i class="icons reg-error"></i>';
	if (bankName == "" || bankName == null) {
		houseTip.html(cred + '请输入开户行').show();
		return false;
	} else if (!reg.test(bankName)) {
		houseTip.html(cred + '开户行只能是中文').show();
		return false;
	}
	return true;
}

function checkCityName(bankName) {
	var cityTip = $('#BankCityTip');
	var cred = '<i class="icons reg-error"></i>';
	var reg = /^[\u4e00-\u9fa5]+$/i;
	if (bankName == "" || bankName == null) {
		cityTip.html(cred + '请输入城市名').show();
		return false;
	} else if (!reg.test(bankName)) {
		cityTip.html(cred + '城市名只能是中文').show();
		return false;
	}
	return true;
}