var wholeTotal = 1;
var shareInput = $("#shares");
var pwdInput = $("#payPwd");
var shareTip = $(".share-tip");
var pwdTip = $(".pwd-tip");
// 倒计时
var now = new Date();

var endDate = new Date(2014, 11, 10, 16);

var leftTime = endDate.getTime() - now.getTime();

var leftsecond = parseInt(leftTime / 1000);

var intDiff = parseInt(leftsecond);// 倒计时总秒数量

$(function() {
	shareInput = $("#shares");
	pwdInput = $("#payPwd");
	shareTip = $(".share-tip");
	pwdTip = $(".pwd-tip");

	// 加载投资记录分页控件
	listAjax(1);
	pagingAjax();

	pwdInput.change(function() {
		var zfPwd = pwdInput.val();
		if (zfPwd == null || zfPwd == "") {
			pwdTip.html("请输入支付密码");
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
						pwdTip.html("");
					} else {
						pwdTip.html("支付密码错误 ");
					}
				},
				contentType : "application/x-www-form-urlencoded;charset=UTF-8"
			});
		}
	});

	shareInput.blur(function() {
		var share = $.trim(shareInput.val());
		var weekSingleMin = $("#weekSingleMin").val();
		var buyBase = $("#weekBuyBase").val();
		var sharePrice = $("#weekSharePrice").val();
		var useMoney = $("#useMoney").val();
		var re = new RegExp("^[0-9]*$");
		if (!re.test(share)) {
			shareTip.html('*请输入正确的份数').show();
			$("#buyMoney").html("0");
			return false;
		} else if (parseFloat(share) < parseFloat(weekSingleMin)) {
			shareTip.html('*购买份数低于最小购买份数').show();
			$("#buyMoney").html("0");
			return false;
		} else if (share % buyBase !== 0) {
			shareTip.html('*份数必须是' + buyBase + '的整数倍').show();
			$("#buyMoney").html("0");
			return false;
		} else if (parseFloat(sharePrice * share) > parseFloat(useMoney)) {
			$("#buyMoney").html(sharePrice * share);
			shareTip.html('*余额不足').show();
			return false;
		} else {
			$("#buyMoney").html(sharePrice * share);
			shareTip.html('');
		}
	});
});

function verifyInvest() {
	var buyShare = shareInput.val();
	var zfPwd = pwdInput.val();
	var shareWrong = shareTip.html();
	var pwdWrong = pwdTip.html();
	if (buyShare == null || buyShare == '') {
		showErrorDialog("金额不能为空，请输入金额！", shareInput)
	} else if (shareWrong != null && shareWrong != '') {
		showErrorDialog(shareWrong, shareInput)
	} else if (zfPwd == null || zfPwd == '') {
		showErrorDialog("支付密码不能为空，请输入密码", pwdInput)
	} else if (pwdWrong != null && pwdWrong != '') {
		showErrorDialog(pwdWrong, pwdInput);
	} else {
		atonceInvest(zfPwd, buyShare);
	}
}
// 投资
function atonceInvest(zfPwd, buyShare) {
	var weekId = $("#weekId").val();
	var isUseVolume = 0;
	if ($('#cashVolumeCheck').attr('checked')
			&& $("#cashVolumeMoney").val() > 0) {
		isUseVolume = 1;
	} else {
		isUseVolume = 0;
	}
	$.ajax({
		url : "/weekTender/tenderAction",
		cache : false,
		type : 'post',
		async : false,
		error : function(d) {
			$(".btn216").attr("onclick", "verifyInvest()");
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
			"weekId" : weekId,
			"buyShare" : buyShare,
			"isUseVolume" : isUseVolume
		},
		success : function(data) {
			data = $.parseJSON(data);
			var status = data.status;
			var buyShare = data.buyShare;
			var artMsg;
			// 1:全额投资,2:部分投资,3:已满标
			if (status == 1) {
				artMsg = "您成功投资" + buyShare + "份";
			} else if (status == 2) {
				artMsg = "由于标的已满，成功投资" + buyShare + "份";
			} else if (status == 3) {
				artMsg = "此标已满,可选择其它标的";
			}
			art.dialog({
				content : artMsg,
				title : '微积金提示',
				icon : 'succeed',
				ok : function() {
					pwdInput.val("");
					shareInput.val("");
					location.reload();
				},
				opacity : .3,
				fixed : true,
				lock : true
			});
			$(".btn216").attr("onclick", "verifyInvest()");
		},
		contentType : "application/x-www-form-urlencoded;charset=UTF-8"
	});
}

// 投资列表分页
function pagingAjax() {
	var pagingcount = $("#pagCount").val();
	if (wholeTotal > 1) {
		pagingcount = wholeTotal;
	}
	$("#paging").paginate({
		count : pagingcount,
		start : 1,
		images : false,
		mouse : 'press',
		onChange : function(page) {
			listAjax(page);
		}
	});
}

function listAjax(page) {
	$
			.ajax({
				url : '/weekTender/weekTenderListAjax',
				data : {
					page : page,
					weekId : $("#weekId").val()
				},
				cache : false,
				async : false,
				type : 'post',
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert('很遗憾，出异常了' + errorThrown);
				},
				success : function(result) {
					result = $.parseJSON(result);
					var rows = result.rows;

					var html = '	<tr>	<th>投资人</th>	<th>投资份额</th>	<th>认购时间</th>	<th>状态</th></tr>';
					if (rows != null && rows.length > 0) {
						$("#paging").show();
						for (var i = 0; i < rows.length; i++) {
							var rowContent = rows[i];
							html = html + '<tr><td>' + rowContent.username
									+ '</td><td>' + rowContent.realbuyShare
									+ '</td><td>' + rowContent.addTime
									+ '</td><td>' + rowContent.status
									+ '</td></tr>'
						}
						$("#listTable").html(html);
						if (result.total >= 1) {
							$("#pagCount").val(result.total / 10);
							wholeTotal = result.total / 10;
						}
					} else {
						$("#paging").hide();
					}
				},
				contentType : "application/x-www-form-urlencoded;charset=UTF-8"
			});
}

function showErrorDialog(content, input) {
	art.dialog({
		title : '微积金提示',
		content : content,
		icon : 'warning',
		ok : function() {
			input.focus();
		},
		opacity : .3,
		fixed : true,
		lock : true
	});
}

function timer(intDiff) {
	setInterval(function() {
		var day = 0, hour = 0, minute = 0, second = 0;// 时间默认值
		if (intDiff > 0) {
			day = Math.floor(intDiff / (60 * 60 * 24));
			// hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
			hour = Math.floor(intDiff / (60 * 60));
			minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
			second = Math.floor(intDiff) - (day * 24 * 60 * 60)
					- (hour * 60 * 60) - (minute * 60);
			if (hour <= 9) {
				hour = "0" + hour
			}
			if (minute <= 9)
				minute = '0' + minute;
			if (second <= 9)
				second = '0' + second;
			//console.log(second);
			var arr = day.toString().split("");
			var arr2 = hour.toString().split("");
			var arr3 = minute.toString().split("");
			var arr4 = second.toString().split("");
			var hour1 = $(".hour1");
			var hour2 = $(".hour2");
			var minute1 = $(".minute1");
			var minute2 = $(".minute2");
			var second1 = $(".second1");
			var second2 = $(".second2");
			hour1.html(arr2[0]);
			hour2.html(arr2[1]);
			minute1.html(arr3[0]);
			minute2.html(arr3[1]);
			second1.html(arr4[0]);
			second2.html(arr4[1]);

		} else {
			$(".remain-time").empty();
			$(".remain-time").html("已截止");
		}
		intDiff--;
	}, 1000);
}