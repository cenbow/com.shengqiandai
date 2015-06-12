var wholeTotal = 1;

$(function() {
	$(".zfPwd").change(function() {
		var zfPwd = $(".zfPwd").val();
		if (zfPwd == null || zfPwd == "") {
			$(".zfPwdWrong").html("请输入支付密码");
		} else {
			$.ajax({
				url : "/verification/checkPayPassword",
				cache : false,
				async : false,
				type : 'post',
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert('很遗憾，出异常了' + errorThrown);
				},
				data : {
					"payPassword" : zfPwd
				},
				success : function(data) {
					data = $.parseJSON(data);
					if (data) {
						$(".zfPwdWrong").html("");
					} else {
						$(".zfPwdWrong").html("支付密码错误&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; ");
					}
				},
				contentType : "application/x-www-form-urlencoded;charset=UTF-8"
			});
		}
	});
	// 输入
	var tenderMoney = $('.tenderMoney');
	var tenderMoneyWrong = $('.tenderMoneyWrong');
	tenderMoney
			.keyup(function() {
				var charge = $.trim(tenderMoney.val());
				if (isNaN(charge * 1) || charge * 1 <= 0) {
					tenderMoneyWrong.html('请输入正确的金额').show();
					tenderMoney.val('');
					tenderMoney.focus();
					return false;
				} else {
					var douhao = new RegExp(",", "g");
					var n = ((parseFloat($('.tenderMoney').html().replace(
							douhao, ",")) || 0) * 1 + tenderMoney.val() * 1)
							.toFixed(2);
					var re = /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
					$('.tenderMoney').html(n.replace(re, '$1,'));
					tenderMoneyWrong.html('');
					return true;
				}
			});
	tenderMoney.blur(function() {
		var money = $.trim(tenderMoney.val());
		var lowestAccount = $("#lowestAccount").val();
		var re = /^([1-9][\d]{0,8}|0)(\.[\d]{1,2})?$/;
		if (!re.test(money)) {
			tenderMoneyWrong.html('请输入正确的金额').show();
			tenderMoney.focus();
			return false;
		} else {
			if (parseFloat(money) < parseFloat(lowestAccount)) {
				tenderMoneyWrong.html('投资金额低于最小投标额').show();
				tenderMoney.focus();
				return false;
			} else {
				tenderMoneyWrong.html('');
			}
		}
	});
	// borrowNav
	$(".bornNav a").click(function() {
		$(this).addClass("cur").siblings().removeClass("cur");
	});

})

function verifyInvest() {
	var tenderMoney = $.trim($('.tenderMoney').val());
	var lowestAccount = $("#lowestAccount").val();
	var re = /^([1-9][\d]{0,8}|0)(\.[\d]{1,2})?$/;
	if (!re.test(tenderMoney)) {
		$('.tenderMoneyWrong').html('请输入正确的金额').show();
	} else {
		if (parseFloat(tenderMoney) < parseFloat(lowestAccount)) {
			$('.tenderMoneyWrong').html('投资金额低于最小投标额').show();
		}
	}
	var zfPwd = $(".zfPwd").val();
	var zfPwdWrong = $(".zfPwdWrong").html();
	var tenderMoneyWrong = $('.tenderMoneyWrong').html();
	if (tenderMoney == null || tenderMoney == '') {
		art.dialog({
			title : '微积金提示',
			content : "金额不能为空，请输入金额！",
			icon : 'warning',
			ok : function() {
				$("#tenderMoney").focus();
			},
			opacity : .3,
			fixed : true,
			lock : true,
		// 为true等价于function(){}
		});
	} else if (tenderMoneyWrong != null && tenderMoneyWrong != '') {
		art.dialog({
			title : '微积金提示',
			content : tenderMoneyWrong,
			icon : 'warning',
			ok : function() {
				$("#tenderMoney").focus();
			},
			opacity : .3,
			fixed : true,
			lock : true
		// 为true等价于function(){}
		});
	} else if (zfPwd == null || zfPwd == '') {
		art.dialog({
			title : '微积金提示',
			content : "支付密码不能为空，请输入密码！",
			icon : 'warning',
			ok : function() {
				$(".zfPwd").focus();
			},
			opacity : .3,
			fixed : true,
			lock : true
		// 为true等价于function(){}
		});
	} else if (zfPwdWrong != null && zfPwdWrong != '') {
		art.dialog({
			title : '微积金提示',
			content : zfPwdWrong,
			icon : 'warning',
			ok : function() {
				$(".zfPwd").focus();
			},
			opacity : .3,
			fixed : true,
			lock : true
		// 为true等价于function(){}
		});
	} else {
		$(".btn216").attr("onclick","");
		atonceInvest(zfPwd, tenderMoney);
	}

}

function atonceInvest(zfPwd, tenderMoney) {
	var borrowId = $("#borrowId").val();
	$.ajax({
		url : "/borrowTender/tenderAction",
		cache : false,
		type : 'post',
		error : function(d) {
			$(".btn216").attr("onclick","verifyInvest()");
			art.dialog({
				title : '微积金提示',
				icon : 'warning',
				content : d.responseText,
				ok : true,
				fixed : true,
				lock : true,
				opacity : .3
			// 为true等价于function(){}
			});
		},
		data : {
			"paypassword" : zfPwd,
			"borrowId" : borrowId,
			"payMoney" : tenderMoney
		},
		success : function(data) {
			data = $.parseJSON(data);
			var status = data.status;
			var payMoney = data.payMoney;
			var artMsg;
			// 1:全额投资,2:部分投资,3:已满标
			if (status == 1) {
				artMsg = "您成功投资" + payMoney + "元";
			} else if (status == 2) {
				artMsg = "由于标的已满，成功投资" + payMoney + "元";
			} else if (status == 3) {
				artMsg = "此标已满,可选择其它标的";
			}
			art.dialog({
				content : artMsg,
				title : '微积金提示',
				icon : 'succeed',
				ok : function() {
					location.reload();
					$(".zfPwd").val("");
					$(".tenderMoney").val("");
				},
				opacity : .3,
				fixed : true,
				lock : true
			});
			$(".btn216").attr("onclick","verifyInvest()");

		},
		contentType : "application/x-www-form-urlencoded;charset=UTF-8"
	});
}

function tenderAll() {
	var useMoney = $("#useMoney").val();
	var overplusAccount = $("#overplusAccount").val();
	var money = parseFloat(useMoney) > parseFloat(overplusAccount) ? overplusAccount
			: useMoney;
	$(".tenderMoney").val(money);
	$('.tenderMoneyWrong').html('');
}