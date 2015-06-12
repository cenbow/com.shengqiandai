$(function() {
	//实名验证
	if ($("#realnameFlag").val() == "0") {
		dialog_('face-sad', '您还没有通过实名验证', '实名验证', '/user/realName');
		return false;
	}
	//选项卡
	$(".netPay li").on("click", function() {
		var index = $(".netPay li").index($(this));
		$(this).removeClass("graybg").addClass("bluebg");
		$(this).siblings().removeClass("bluebg").addClass("graybg");
		$(".payBox").show();
		$(".payBox").eq(index).hide();

	});
	//网银输入变量
	var inputMoney = $('#keyMoney');
	var rechargeAfter =  $("#rechargeAfter");
	var curCanUse = $("#curCanUse");
	//快捷方式输入变量
	var kjInputMoney = $("#inputMoney");
	var moneyAfter = $("#moneyAfter");
	var currentMoney = $("#currentMoney");
	//网银输入金额
	InputVal(inputMoney,curCanUse,rechargeAfter);
	//快捷方式输入金额
	InputVal(kjInputMoney,currentMoney,moneyAfter);	
	//show image
	$(".czWrap").find(".chongzhi").eq(0).show();
	//网银在线_银行Icon
	$(".banks li").click(
			function() {
				$(this).css("border-color", "#0065b4").find("i").addClass(
						"selectedSymbol").end().siblings().css("border-color",
						"#cccccc").find("i").removeClass("selectedSymbol");

	});
	//快捷支付_银行Icon
	$(".adds-bankcard li").click(
			function(){
				$(this).css("border-color", "#349CD8").addClass("selected").siblings().css("border-color","#cccccc").removeClass("selected");
				bankLimit();
	});
	// 网银输入
	var keyMoney = $('#keyMoney');
	var moneyLegalCheck = $('.moneyLegalCheck');
	keyMoney.keyup(function() {
				var charge = $.trim(keyMoney.val());
				if (isNaN(charge * 1) || charge * 1 <0) {
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
   // 快捷方式输入	
	var fastKeyMoney = $('#inputMoney');
	var errorTip = $('.error-tip');
	fastKeyMoney.keyup(function() {
				var charge = $.trim(fastKeyMoney.val());
				if (isNaN(charge * 1) || charge * 1 <0) {
					errorTip.html('请输入正确的充值金额').show();
					fastKeyMoney.val('');
					fastKeyMoney.focus();
					return false;
				} else {
					var douhao = new RegExp(",", "g");
					var n = ((parseFloat($('#currentMoney').html().replace(douhao,
							"")) || 0) * 1 + fastKeyMoney.val() * 1).toFixed(2);
					var re = /(\d{1,3})(?=(\d{3})+(?:$|\.))/g;

					$('#moneyAfter').html(n.replace(re, '$1,'));
					errorTip.html('');
					$('#moneyAfter').val(fastKeyMoney.val());
					return true;
				}
	});
	//网银在线充值
	$(".czBtn").click(function() {
		if ($("#realnameFlag").val() == "0") {
			dialog_('face-sad', '您还没有通过实名验证', '实名验证', '/user/realName');
			return false;
		}
		var bankcode = $('.selectedSymbol').attr("bankcode");
		var money = $('#keyMoney').val();		
		if (money == null || money == '') {
			moneyLegalCheck.html('请输入正确的充值金额').show();
			keyMoney.val('');
			keyMoney.focus();
			return false;
		} else if (bankcode == null || bankcode == 'undefined') {
			moneyLegalCheck.html('请选支付银行').show();
			return false;
		} else {
			moneyLegalCheck.html('').show();
			//新窗口打开
			var targetWndName = "vfunding_sinaRecharge";  
			var newWindow = window.open("",targetWndName);  
			var link = document.getElementById("link"); 
			link.target = targetWndName;
			//请求地址
			var url = "/account/sinaRechargeByOnlineBank";
			var data = {};
			data["money"]=money;
			data["bankCode"]=bankcode;
			$.ajax({
				url : url,
				data :data,
				cache : false,
				type : 'post',
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert('很遗憾，出异常了' + errorThrown);
				},
				success : function(result) {
					result = $.parseJSON(result);
					if(result.success){
						//绕过浏览器新窗口拦截
						link.href = result.action;  
						link.click();  
						//newWindow.location.href = result.action;
					}else{
						//输出错误消息
						newWindow.close();
					}
				},
				contentType : "application/x-www-form-urlencoded;charset=UTF-8"
			});
			$('#keyMoney').val('');
		}
		webUtil.jDialog.Modal("rechBankDialog", "rechBankEntry");
	});
	//快捷支付1
	$("#quickCz1").click(function(){
		quickPay1();
	});
	//快捷支付2
	$("#quickCz2").click(function(){
		/**
		 * 短暂提示
		 * 
		 * @param {String}
		 *            提示内容
		 * @param {Number}
		 *            显示时间 (默认1.5秒)
		 */
		artDialog.tips = function(content, time) {
			return artDialog({
				id : 'Tips',
				title : false,
				cancel : false,
				fixed : true,
				lock : true
			}).content(
					'<div style="padding: 0 1em;">' + content
							+ '</div>').time(time || 1);
		};		
		var msgCode = $('#msgCode').val();
		var qucikTradeNo = $('#qucikTradeNo').val();
		var url = "/account/sinaRechargeByBindingPayAdvance";
		var data = {};
		data["captcha"]=msgCode;
		data["tradeNo"]=qucikTradeNo;
		if (msgCode == null || msgCode == 'undefined' || msgCode=="") {
			$(".msgError-tip").html("请输入验证码");
			return false;
		}else if(qucikTradeNo == null || qucikTradeNo == 'undefined' || qucikTradeNo=="") {
			dialog_('face-sad', "流水号异常，请重新充值", '重新充值', '/account/recharge');
			return false;
		}
		$.ajax({
			url : url,
			data :data,
			async : true,
			cache : false,
			type : 'post',
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert('很遗憾，出异常了' + errorThrown);
			},
			success : function(result) {
				result = $.parseJSON(result);
				if(result.success){
					location.href =result.action;
				}else{
					dialog_('face-sad', result.msg, '重新充值', '/account/recharge');
				}	
				//btnDisabled("quickCz2",false);
			},
			beforeSend : function(XHR) {
				art.dialog.tips('充值申请提交中...', 3600);
				$('#quickCz2').attr('disabled', true);
			},
			complete : function(XHR, TS) {
				$('#quickCz2').attr('disabled', false);
			}
		});
		$('#inputMoney').val('');
	});
	//添加银行卡
	$("#addBankBtn1").click(function(){
		window.location.href = "/accountBank/bank";
	});
	//快捷支付2_取消
	$("#chosenBankCard2_cancle").click(function(){
		dialog_('face-sad', '充值取消', '重新充值', '/account/recharge');
		return false;
	});
	//获取银行卡限额
	bankLimit();
})


//获取银行卡限额
function bankLimit(){
	var bankCode = $('.selected').attr("bank");
	var data = {};
	data["bankCode"]=bankCode;
	var url = "/accountBank/bankLimit";
	var div = $(".limit-tip");
	$.ajax({
		url:url,
		cache:false,
		async:true,
		type:'post',
		data:data,
		success:function(data){
			var json = $.parseJSON(data);
			if(json.success){
				var obj = json.obj;
				//div.html(obj.bankName+",首次绑卡支付限额<font color='00a0e9'>"+obj.bindingpayFirstLimit+"</font>元,已绑卡支付单笔限额<font color='00a0e9'>"+obj.bindingpaySingleLimit+"</font>元,每日限额<font color='00a0e9'>"+obj.bindingpayDayLimit+"</font>元。");
				div.html("该卡单笔限额"+obj.bindingpaySingleLimit+"元,首次限额"+obj.bindingpayFirstLimit+"元");
			}else{
				div.html("");
			}
		}
	});
}


//快捷支付短信验证码倒数60秒
function doWait(buttonObject, wait) {
	if (wait == 0) {
		buttonObject.attr({
			href : "javascript:quickPay1();"
		});
		buttonObject.removeAttr("style");
		buttonObject.html("点击重获手机验证码");
	} else {
		buttonObject.attr({
			href : "javascript:return false;",
			style: "background-color:#CCCCCC;color:#000000"
		});
		buttonObject.html(wait + " 秒后重新获取...");
		wait--;
		setTimeout(function() {
			doWait(buttonObject, wait);
		}, 1000)
	}
}
//快捷支付1
function quickPay1(){
	if ($("#realnameFlag").val() == "0") {
		dialog_('face-sad', '您还没有通过实名验证', '实名验证', '/user/realName');
		return false;
	}
	var card = $('.selected').attr("card");
	var phone = $('.selected').attr("phone");
	var cardNo = $('.selected').attr("cardNo");
	var bank = $('.selected').attr("bank");
	var money = $('#inputMoney').val();	
	var url1 = "/account/sinaRechargeByBindingPayCheck";
	var url2 = "/account/sinaRechargeByBindingPay";
	var data = {};
	data["money"]=money;
	data["card"]=card;
	if (money == null || money == 'undefined' || money == '') {
		$(".error-tip").html('请输入正确的充值金额').show();
		$('#inputMoney').val('');
		$('#inputMoney').focus();
		return false;
	} else if (card == null || card == 'undefined' || card=="") {
		$(".error-tip").html('请选支付银行').show();
		return false;
	} 
	$("#czje_s").html(money);		
	$("#cardNo_em").html(cardNo);
	$("#phone_s").html(phone);
	if(phone=='****'){
		$("#phone_title").hide();
	}else{
		$("#phone_title").show();
	}
	if(bank!=null&&bank!=""){
		$("#i_chosebank2").addClass("bank-"+bank);
	}
	$.ajax({
		url : url1,
		data :data,
		async : false,
		cache : false,
		type : 'post',
		success : function(result) {
			result = $.parseJSON(result);
			if(result.success){
				$("#chosenBankCard1").addClass("hide");
				$("#chosenBankCard2").removeClass("hide");
				$.ajax({
					url : url2,
					data :data,
					async : true,
					cache : false,
					type : 'post',
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert('很遗憾，出错了' + errorThrown);
					},
					success : function(result) {
						result = $.parseJSON(result);
						if(result.success){
							$("#qucikTradeNo").val(result.msg);
						}else{
							$("#chosenBankCard1").removeClass("hide");
							$("#chosenBankCard2").addClass("hide");
							if(result.msg=="快捷支付银行卡与新浪支付绑定失败无法支付,请重新开通快捷支付。"||result.msg=="快捷支付银行卡有误系统中无法搜索到,请重新开通快捷支付。"){
								dialog_('face-sad', result.msg, '重新绑定快捷支付', '/accountBank/bank');
								return false;
							}else{
								dialog_('face-sad', result.msg, '重新充值', '/account/recharge');
								return false;
							}
						}				
					},
					contentType : "application/x-www-form-urlencoded;charset=UTF-8"
				});
				doWait($("#sendBtn"),60);
			}else{
				if(result.msg=="快捷支付银行卡与新浪支付绑定失败无法支付,请重新开通快捷支付。"||result.msg=="快捷支付银行卡有误系统中无法搜索到,请重新开通快捷支付。"){
					dialog_('face-sad', result.msg, '重新绑定快捷支付', '/accountBank/bank');
					return false;
				}else{
					dialog_('face-sad', result.msg, '重新充值', '/account/recharge');
					return false;
				}
			}				
		},
		contentType : "application/x-www-form-urlencoded;charset=UTF-8"
	});
}
//弹出框方法
function dialog_(face, content, btnName, url) {
	art.dialog({
		icon : face,
		title : '微积金提示',
		left : '48%',
		width : 200,
		content : content,
		lock : true,
		opacity : 0.15,
		resize : false,
		drag : true,
		cancel:false,
		button : [ {
			name : btnName,
			callback : function() {
				window.location.href = url;
			},
			focus : true
		} ]
	});
}
// 输入金额验证
function InputVal(obj,beforeTip,afterTip){
	obj.keyup(function() {
		var objVal = $.trim(obj.val());
        var index = objVal.indexOf(".");
        if(objVal.indexOf(".")>-1){
        	var strBefore = objVal.substring(0,index);
        	var strAfter = objVal.substring(index,index+3);
        	obj.val(strBefore.concat(strAfter));
        }
		
		if(objVal==null||objVal==""){
			afterTip.html(beforeTip.html())
		}
});


}

//提交是否失效
function btnDisabled(btnId,disabled){
	if(disabled){
		$("#"+btnId).attr("disabled",true);	
		$("#"+btnId).attr({
			style: "background-color:#CCCCCC;color:#000000"
		});
	}else{
		$("#"+btnId).attr("disabled",false);	
		$("#"+btnId).removeAttr("style");
	}
}
