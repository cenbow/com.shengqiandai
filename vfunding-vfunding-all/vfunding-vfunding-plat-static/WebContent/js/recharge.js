// JavaScript Document

$(function() {

	$(".czWrap").find(".chongzhi").eq(0).show();

	// 线下
	$(".rechargeOffline").click(function() {
		$("#bk").val('36');
		$(".bank-liushui").html("");
		$(".bank-liushui").html("请注明您的转账流水号，谢谢配合");
		$(".czWrap").hide();
		$(".alipay").hide();
		$(".offline").show();
		$(".recharge-mark").show();
		
	});

	// 线上
	$(".rechargeOnline").click(function() {
		$(".offline").hide();
		$(".alipay").hide();
		$(".czWrap").show();
	});
   //支付宝	
	$(".zhifubao").click(function() {
		$("#bk").val('1');
		$(".bank-liushui").html("");
		$(".bank-liushui").html("请输入你的支付宝账号")
		$(".czWrap").hide();
		$(".offline").hide();
		$(".alipay").show();
		$(".recharge-mark").show()
	});
	
	
	// 选择通道

	$(".pay-channel li").click(
			function() {
				// 设置请求地址

				if ($(this).hasClass("gfb")) {
					$('#address').val("");
					$('#address').val('/pay/goPay');
				} else if ($(this).hasClass("wyzx")) {
					$('#address').val("");
					$('#address').val('/pay/chinaBank');
				} else if ($(this).hasClass("hczf")) {
					$('#address').val("");
					$('#address').val('/pay/ecpss');
				} else {
					$('#address').val("");
					$('#address').val('/pay/goPay');
				}

				$(this).css("border-color", "#0065b4").find("i").addClass(
						"selectedSymbol").end().siblings().css("border-color",
						"#cccccc").find("i").removeClass("selectedSymbol");

				var index = $(".pay-channel").find("li").index($(this));

				$(".chongzhi").eq(index).show().siblings(".chongzhi").hide()
				// 清除选中的样式
				$(".banks").eq(index).find("li").css("border-color", "#ccc")
						.find("i").removeClass("selectedSymbol");
				// 清除选中银行的Code值
				$("#rechargeBank").val("");
				
				if(!$('#rechargeSubmit').hasClass('czBtn')){
					$('#rechargeSubmit').addClass('czBtn');
				}

			});

	// 充值类型
	$(".rechargeWay li:not(:first)").click(
			function() {

				$(this).css("border-color", "#0065b4").find("i").addClass(
						"selectedSymbol").end().siblings().css("border-color",
						"#cccccc").find("i").removeClass("selectedSymbol");

				var index = $(".rechargeWay li").index($(this));
			});

	$(".rechargeOnline,.gfb").css("border-color", "#0065b4");

	// 银行Icon

	$(".banks li").click(
			function() {
				$(this).css("border-color", "#0065b4").find("i").addClass(
						"selectedSymbol").end().siblings().css("border-color",
						"#cccccc").find("i").removeClass("selectedSymbol");

			});

	// 输入

	var keyMoney = $('#keyMoney');
	var moneyLegalCheck = $('.moneyLegalCheck');
	keyMoney
			.keyup(function() {
				var charge = $.trim(keyMoney.val());
				if (isNaN(charge * 1) || charge * 1 <= 0) {
					moneyLegalCheck.html('请输入正确的充值金额').show();

					keyMoney.val('');
					keyMoney.focus();
					return false;
				} else {
					var douhao = new RegExp(",", "g");
					var n = ((parseFloat($('#curCanUse').html().replace(douhao,
							"")) || 0) * 1 + keyMoney.val() * 1).toFixed(2);
					var re = /(\d{1,3})(?=(\d{3})+(?:$|\.))/g;

					$('#rechargeAfter').html(n.replace(re, '$1,'));
					moneyLegalCheck.html('');
					$('#rechargeMoney').val(keyMoney.val());
					return true;
				}
			});

	$(".czBtn").click(function() {// 在线充值
		if ($("#realnameFlag").val() == "0") {
			webUtil.jDialog.Modal('dialogRealName', 'dialogRealNameCont');
			return;
		}
		var address = $('#address').val();
		var money = $('#rechargeMoney').val();
		if (money == null || money == '') {
			moneyLegalCheck.html('请输入正确的充值金额').show();
			keyMoney.val('');
			keyMoney.focus();
			return false;
		} else if (address == null || address == '') {
			dialog_('face-sad', '请选支付银行', '重新充值', '/account/recharge');
			return false;
		} else {
			var form = $('#bankForm');
			form.attr({
				method : "post",
				action : address
			});
			form.submit();
			$('#rechargeMoney').val('');
		}

		webUtil.jDialog.Modal("rechBankDialog", "rechBankEntry");

	});

	$("#submitBtn")
			.click(
					function() {// 线下充值
						if ($("#realnameFlag").val() == "0") {
							webUtil.jDialog.Modal('dialogRealName',
									'dialogRealNameCont');
							return;
						}
						var money = $('#rechargeMoney').val();
						var bank = $("#bk").val();//$(".banks2 p").find('input[type="radio"]:checked').val();
						var remark = $('#beizhu').val();
						if (money == null || money == '') {
							moneyLegalCheck.html('请输入正确的充值金额').show();
							keyMoney.val('');
							keyMoney.focus();
							return false;
						} else if (remark == null || remark == '') {
							art.dialog({
								icon : 'face-sad',
								title : '微积金提示',
								left : '48%',
								content : '请输入充值备注',
								lock : true,
								cancelVal: '关闭',
								cancel:true
							});
							return false;
						} else if (bank == null || bank == '') {
							art.dialog({
								icon : 'face-sad',
								title : '微积金提示',
								left : '48%',
								content : '请选择银行卡号',
								lock : true,
								cancelVal: '关闭',
								cancel:true
							});
							return false;
						} else {
							$.ajax({
								url : "/account/rechargeOffline",
								data : {
									"money" : money,
									"payment" : bank,
									"remark" : remark
								},
								type : 'post',
								dataType : "json",
								async : false,
								success : function(data, textStatus, jqXHR) {
									if (data.success == true) {
										dialog_('face-smile', '提交成功，请等待审核.',
												'确定', '/user/account');
										// location.href = '/user/account';
									} else {
										dialog_('face-sad', '提交失败，请联系在线客服.',
												'确定', '/account/recharge');
									}
								},
								error : function(d) {
									alert(d.responseText);
								}
							});
						}
					});

})
function dialog_(face, content, btnName, url) {
	art.dialog({
		icon : face,
		title : '微积金提示',
		left : '48%',
		content : content,
		lock : true,
		button : [ {
			name : btnName,
			callback : function() {
				window.location.href = url;
			},
			focus : true
		} ]
	});
}

