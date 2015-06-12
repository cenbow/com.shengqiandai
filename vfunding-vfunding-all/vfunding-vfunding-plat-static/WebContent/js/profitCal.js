$(function() {
	webUtil.ToolTip.init();
	var product = $("#product");
	product.change(function() {
		initTip();
		$("#investAmount").val("");
		if ($(this).val() == "loan") {
			$("#planArea").hide();
			$("#loanArea").show();
			$("#planTable").hide();
			$("#loanTable").show();
		} else if ($(this).val() == "plan") {
			$("#loanArea").hide();
			$("#planArea").show();
			$("#loanTable").hide();
			$("#planTable").show();
		}
		webUtil.ToolTip.init();
	});

	var calcBt = $("#calcBt");
	calcBt.click(function() {
		initTip();
		if ($("#product").val() == "loan") {
			var investAmount = $("#investAmount").val();
			var investAmountTip = $("#investAmountTip");
			if (investAmount == '') {
				investAmountTip.html('<i class="icons reg-error"></i>请输入金额')
						.show();
				return;
			}
			if (isNaN(investAmount)) {
				investAmountTip.html('<i class="icons reg-error"></i>请输入数字')
						.show();
				return;
			}
			if (investAmount > 500000) {
				investAmountTip.html(
						'<i class="icons reg-error"></i>请输入50万以内的数字').show();
				return;
			}

			var investTime = $("#investTime").val();
			var timeCheck =  /^[0-9]*[1-9][0-9]*$/;
			var investTimeTip = $("#investTimeTip");
			if ( isNaN(investTime)||investTime<=0||!(timeCheck.test(investTime))) {
				investTimeTip.html('<i class="icons reg-error"></i>请输入整月份')
						.show();
				return;
			}
			if (isNaN(investTime)) {
				investTimeTip.html('<i class="icons reg-error"></i>请输入数字')
						.show();
				return;
			}
			if (investTime > 60) {
				investTimeTip.html('<i class="icons reg-error"></i>请输入60以内的数字')
						.show();
				return;
			}

			var anun = $("#anun").val();
			var anunTip = $("#anunTip");
			if (anun == '') {
				anunTip.html('<i class="icons reg-error"></i>请输入利率').show();
				return;
			}
			if (isNaN(anun)) {
				anunTip.html('<i class="icons reg-error"></i>请输入数字').show();
				return;
			}
			if (anun > 24) {
				anunTip.html('<i class="icons reg-error"></i>请输入24以内的数字')
						.show();
				return;
			}

			if ($("#repayType").val() == 1) {
				getData();
			} else if ($("#repayType").val() == 2) {
				getData2();
			} else if ($("#repayType").val() == 3) {
				getData3();
			} else if ($("#repayType").val() == 4) {
				getData4();
			}
		} else {
			var investAmount = $("#investAmount").val();
			var investAmountTip = $("#investAmountTip");
			if (investAmount == '') {
				investAmountTip.html('<i class="icons reg-error"></i>请输入金额')
						.show();
				return;
			}
			if (isNaN(investAmount)) {
				investAmountTip.html('<i class="icons reg-error"></i>请输入数字')
						.show();
				return;
			}
			if (investAmount > 100000) {
				investAmountTip.html(
						'<i class="icons reg-error"></i>请输入10万以内的数字').show();
				return;
			}

			calcPlanProfit();
		}
	});

	var resetBt = $("#resetBt");
	resetBt.click(function() {
		$("#investAmount").val("");
		$("#investTime").val("");
		$("#anun").val("");
		$("#repayType").val("1");

		$("#planType").val("a");
		$("#profitDis").val("1");

		$("#loanTableBody").html("");
		$("#planTableBody").html("");

		$("#earnBxNum").html(0);
		$("#earnBxNum").html(0);
		$("#lxNum").html(0);
	});
});

function initTip() {
	$("#investAmountTip").html('').hide();
	$("#investTimeTip").html('').hide();
	$("#anunTip").html('').hide();
	$(".monthIncome").show();
	$("#earnBxNum").html(0);
	$("#earnBxNum").html(0);
	$("#lxNum").html(0);
}

// 创建投资标收益表格
function createLoanProfitTable(data) {
	if ($("#product").val() == "loan") {
		var to = 0, toIn = 0;
		var html = "<table><tr><th>月份</th><th>本金余额(￥)</th><th>本息余额 (￥)</th><th>月收本息 (￥)</th><th>月收本金(￥)</th><th>利息(￥)</th></tr></table>";
		if ($("#repayType").val() == 1) {
			for (var i = 0; i < data.length; i++) {
				to += data[i].plannedTermAmount * 1;
				toIn += data[i].plannedTermInterest * 1;
			}
			$("#earnBxNum").html(
					webUtil.numFormat(data[0].plannedTermAmount, 2));
			$("#earnBxNum").html(webUtil.numFormat(to, 2));
			$("#lxNum").html(
					webUtil.numFormat((to - Number($("#investAmount").val())),
							2));
			$(".monthIncome").show();
		} else {
			to = data[0].termRemainingPrincipal;
			for (var i = 0; i < data.length; i++) {
				to += data[i].plannedTermAmount * 1;
				toIn += data[i].plannedTermInterest * 1;
			}
			$("#earnBxNum").html(webUtil.numFormat(to, 2));
			$("#lxNum").html(
					webUtil.numFormat((to - Number($("#investAmount").val())),
							2));
			$(".monthIncome").hide();
		}

		html += '<div class="colle-table"><table><tr>';
		html += '<td></td>';
		html += '<td>'
				+ webUtil
						.numFormat(
								(Number(data[0].termRemainingPrincipal) + Number(data[0].plannedTermPrinciple)),
								2) + '</td>';
		html += '<td>' + webUtil.numFormat(to, 2) + '</td>';
		html += '<td></td>';
		html += '<td></td>';
		html += '<td></td>';
		html += '</tr></table></div>';

		for (var i = 0, len = data.length; i < len; i++) {
			if ($("#repayType").val() == 1) {

				to -= data[i].plannedTermAmount * 1;
				toIn -= data[i].plannedTermInterest * 1;
				if (i == len - 1) {
					to = 0;
				}
			}

			if (to < 0) {
				to = 0;
			}
			;
			if (toIn < 0) {
				toIn = 0;
			}
			;
			html += '<div class="colle-table"><table><tr>';
			html += '<td>' + data[i].phaseNumber + '</td>';
			html += '<td>'
					+ webUtil.numFormat(
							(Number(data[i].termRemainingPrincipal)), 2)
					+ '</td>';
			html += '<td>' + webUtil.numFormat(to, 2) + '</td>';
			html += '<td>'
					+ webUtil.numFormat((Number(data[i].plannedTermAmount)), 2)
					+ '</td>';
			html += '<td>'
					+ webUtil.numFormat((Number(data[i].plannedTermPrinciple)),
							2) + '</td>';
			html += '<td>'
					+ webUtil.numFormat((Number(data[i].plannedTermInterest)),
							2) + '</td>';
			html += '</tr></table></div>';
		}
		$("#loanTableBody").html(html).slideDown();
	}
}

// 等额本息
function getData() {
	var type = $.trim($("#repayType").val());
	var amount = $.trim($("#investAmount").val());
	var reg = /^[0-9]*[1-9][0-9]*$/;
	var newPar = /^\d+(\.\d+)?$/;
	if (!reg.test(amount)) {
		return;
	}
	var ann = $.trim($("#anun").val());
	var count = $.trim($("#investTime").val());
	if (!newPar.test(ann)) {
		return;
	}
	if (!reg.test(count)) {
		return;
	}
	if (count > 120) {
		return;
	}
	$
			.ajax({
				url : '/utilpage/count',
				data : {
					type : '1',
					money : amount,
					month : count,
					apr : ann,
					style : type
				},
				cache : false,
				async : false,
				type : 'post',
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert('很遗憾，出异常了' + errorThrown);
				},
				success : function(result) {
					result = $.parseJSON(result);
					if (result.length > 0) {
						// 计算出总的本息
						var sumCapitalInterest = changeTwoDecimal_f(result[0].monthCapitalInterest
								* count);
						$("#earnBxNum").html(sumCapitalInterest);
						$("#lxNum")
								.html(
										changeTwoDecimal_f(result[0].monthCapitalInterest
												* count - amount));
						$("#earnBxNumMonth")
								.html(
										changeTwoDecimal_f(result[0].monthCapitalInterest));
						var html = "<table><tr><th>月份</th><th>本金余额(￥)</th><th>本息余额 (￥)</th><th>月收本息 (￥)</th><th>月收本金(￥)</th><th>利息(￥)</th></tr></table>";
						html += '<div class="colle-table"><table><tr>';
						html += '<td></td>';
						html += '<td>' + amount + '</td>';
						html += '<td>' + sumCapitalInterest + '</td>';
						html += '<td></td>';
						html += '<td></td>';
						html += '<td></td>';
						html += '</tr></table></div>';

						for (var i = 0, len = result.length; i < len; i++) {
							// 月收本息
							var monthCapitalInterest = result[i].monthCapitalInterest;
							// 利息
							var monthInterest = result[i].monthInterest;
							// 月收本金
							var monthCapital = changeTwoDecimal_f(monthCapitalInterest
									- monthInterest);
							// 本金余额
							var surplusCapital = changeTwoDecimal_f(amount
									- monthCapital);
							amount = surplusCapital;
							if (surplusCapital <= 0.01) {
								surplusCapital = '0.00';
							}
							// 本息余额
							var surplusCapitalInterest = changeTwoDecimal_f(sumCapitalInterest
									- monthCapitalInterest);
							sumCapitalInterest = surplusCapitalInterest;
							html += '<div class="colle-table"><table><tr>';
							html += '<td>' + (i + 1) + '</td>';
							html += '<td>' + surplusCapital + '</td>';
							html += '<td>' + surplusCapitalInterest + '</td>';
							html += '<td>' + monthCapitalInterest + '</td>';
							html += '<td>' + monthCapital + '</td>';
							html += '<td>' + monthInterest + '</td>';
							html += '</tr></table></div>';
						}
						$("#loanTableBody").html(html).slideDown();
					}
				},
				contentType : "application/x-www-form-urlencoded;charset=UTF-8"
			});
};

// 利息复投
function getData2() {
	var amount = $.trim($("#investAmount").val());
	var reg = /^[0-9]*[1-9][0-9]*$/;
	var newPar = /^\d+(\.\d+)?$/;
	if (!reg.test(amount)) {
		return;
	}
	;
	var ann = $.trim($("#anun").val());
	var count = $.trim($("#investTime").val());
	if (!newPar.test(ann)) {
		return;
	}
	;
	if (!reg.test(count)) {
		return;
	}
	;
	if (count > 120) {
		return;
	}
	;

	function Data() {
		this.phaseNumber = 0;
		this.plannedTermAmount = 0;
		this.plannedTermInterest = 0;
		this.plannedTermPrinciple = 0;
		this.termRemainingPrincipal = 0;
	}
	var monAnn = ann / (12 * 100);
	var dataArr = new Array();
	var temp = new Data();
	temp.phaseNumber = 1;
	temp.plannedTermAmount = (amount * monAnn).toFixed(2);
	temp.plannedTermInterest = temp.plannedTermAmount;
	temp.plannedTermPrinciple = Number(0);
	temp.termRemainingPrincipal = Number(amount);
	dataArr.push(temp);
	for (var i = 1; i < count; i++) {
		var temp = new Data();
		temp.phaseNumber = i + 1;
		temp.plannedTermPrinciple = Number(0);
		temp.termRemainingPrincipal = Number(dataArr[i - 1].termRemainingPrincipal)
				+ Number(dataArr[i - 1].plannedTermInterest);
		temp.plannedTermAmount = (temp.termRemainingPrincipal * monAnn)
				.toFixed(2);
		temp.plannedTermInterest = temp.plannedTermAmount;
		dataArr.push(temp);
	}
	createLoanProfitTable(dataArr);
}

// 先息后本
function getData3() {
	var type = $.trim($("#repayType").val());
	var amount = $.trim($("#investAmount").val());
	var reg = /^[0-9]*[1-9][0-9]*$/;
	var newPar = /^\d+(\.\d+)?$/;
	if (!reg.test(amount)) {
		return;
	}
	var ann = $.trim($("#anun").val());
	var count = $.trim($("#investTime").val());
	if (!newPar.test(ann)) {
		return;
	}
	if (!reg.test(count)) {
		return;
	}
	if (count > 120) {
		return;
	}
	var monAnn = ann / (12 * 100);
	var interest = monAnn * amount;
	var sumCapitalInterest = parseFloat(interest * count) + parseFloat(amount);

	$("#earnBxNum").html(changeTwoDecimal_f(sumCapitalInterest));
	$("#lxNum").html(changeTwoDecimal_f(interest * count));
	$("#earnBxNumMonth").html(changeTwoDecimal_f(interest));
	$(".monthIncome").hide();
	var html = "<table><tr><th>月份</th><th>本金余额(￥)</th><th>本息余额 (￥)</th><th>月收本息 (￥)</th><th>月收本金(￥)</th><th>利息(￥)</th></tr></table>";
	html += '<div class="colle-table"><table><tr>';
	html += '<td></td>';
	html += '<td>' + changeTwoDecimal_f(amount) + '</td>';
	html += '<td>' + changeTwoDecimal_f(sumCapitalInterest) + '</td>';
	html += '<td></td>';
	html += '<td></td>';
	html += '<td></td>';
	html += '</tr></table></div>';

	for (var i = 0, len = count; i < len; i++) {
		// 月收本息
		var monthCapitalInterest = interest;
		// 利息
		var monthInterest = interest;
		// 月收本金
		var monthCapital = '0.00';
		// 本金余额
		var surplusCapital = changeTwoDecimal_f(amount - monthCapital);
		amount = surplusCapital;
		if (surplusCapital <= 0.01) {
			surplusCapital = '0.00';
		}
		// 本息余额
		var surplusCapitalInterest = changeTwoDecimal_f(sumCapitalInterest
				- monthCapitalInterest);
		sumCapitalInterest = surplusCapitalInterest;
		if (i + 1 != len) {
			html += '<div class="colle-table"><table><tr>';
			html += '<td>' + (i + 1) + '</td>';
			html += '<td>' + changeTwoDecimal_f(surplusCapital) + '</td>';
			html += '<td>' + changeTwoDecimal_f(surplusCapitalInterest)
					+ '</td>';
			html += '<td>' + changeTwoDecimal_f(monthCapitalInterest) + '</td>';
			html += '<td>' + changeTwoDecimal_f(monthCapital) + '</td>';
			html += '<td>' + changeTwoDecimal_f(monthInterest) + '</td>';
			html += '</tr></table></div>';
		} else {
			html += '<div class="colle-table"><table><tr>';
			html += '<td>' + (i + 1) + '</td>';
			html += '<td>0.00</td>';
			html += '<td>0.00</td>';
			html += '<td>'
					+ changeTwoDecimal_f(parseFloat(amount)
							+ parseFloat(monthInterest)) + '</td>';
			html += '<td>' + changeTwoDecimal_f(amount) + '</td>';
			html += '<td>' + monthInterest + '</td>';
			html += '</tr></table></div>';
		}
		$("#loanTableBody").html(html).slideDown();

	}
};
function getData4() {
	var type = $.trim($("#repayType").val());
	var amount = $.trim($("#investAmount").val());
	var reg = /^[0-9]*[1-9][0-9]*$/;
	var newPar = /^\d+(\.\d+)?$/;
	if (!reg.test(amount)) {
		return;
	}
	var ann = $.trim($("#anun").val());
	var count = $.trim($("#investTime").val());
	if (!newPar.test(ann)) {
		return;
	}
	if (!reg.test(count)) {
		return;
	}
	if (count > 120) {
		return;
	}
	var monAnn = ann / (12 * 100);
	var interest = monAnn * amount;
	var sumCapitalInterest = parseFloat(interest * count) + parseFloat(amount);

	$("#earnBxNum").html(changeTwoDecimal_f(sumCapitalInterest));
	$("#lxNum").html(changeTwoDecimal_f(interest * count));
	$("#earnBxNumMonth").html(changeTwoDecimal_f(interest));
	$(".monthIncome").hide();
	var html = "<table><tr><th>月份</th><th>本金余额(￥)</th><th>本息余额 (￥)</th><th>月收本息 (￥)</th><th>月收本金(￥)</th><th>利息(￥)</th></tr></table>";
	html += '<div class="colle-table"><table><tr>';
	html += '<td></td>';
	html += '<td>' + changeTwoDecimal_f(amount) + '</td>';
	html += '<td>' + changeTwoDecimal_f(sumCapitalInterest) + '</td>';
	html += '<td></td>';
	html += '<td></td>';
	html += '<td></td>';
	html += '</tr></table></div>';

	// 月收本息
	var monthCapitalInterest = parseFloat(interest * count)
			+ parseFloat(amount);
	// 利息
	var monthInterest = parseFloat(interest * count);
	// 月收本金
	var monthCapital = parseFloat(amount);
	// 本金余额
	var surplusCapital = '0.00';
	// 本息余额
	var surplusCapitalInterest = '0.00';
	html += '<div class="colle-table"><table><tr>';
	html += '<td>' + count + '</td>';
	html += '<td>' + surplusCapital + '</td>';
	html += '<td>' + surplusCapitalInterest + '</td>';
	html += '<td>' + monthCapitalInterest + '</td>';
	html += '<td>' + monthCapital + '</td>';
	html += '<td>' + monthInterest + '</td>';
	html += '</tr></table></div>';
	$("#loanTableBody").html(html).slideDown();
}
// 创建定期宝收益表格
function createPlanProfitTable(data) {
	var html = '<table><tr><th>月份</th><th>本金余额(￥)</th><th>收益(￥)</th></tr></table>';
	html += '<div class="colle-table"><table><tr>';
	html += '<td>' + data[0].phaseNumber + '</td>';
	html += '<td>' + webUtil.numFormat(data[0].principle, 2) + '</td>';
	html += '<td>' + data[0].profit + '</td>';
	html += '</tr></table></div>';

	for (var i = 1, len = data.length; i < len; i++) {
		html += '<div class="colle-table"><table><tr>';
		html += '<td>' + data[i].phaseNumber + '</td>';
		html += '<td>' + webUtil.numFormat(data[i].principle, 2) + '</td>';
		html += '<td>' + webUtil.numFormat(data[i].profit, 2) + '</td>';
		html += '</tr></table></div>';
	}
	$("#planTableBody").html(html).slideDown();
}

// 定期宝
function calcPlanProfit() {
	var planType = $("#planType").val();
	var profitDis = $("#profitDis").val();
	var investAmount = Number($("#investAmount").val());
	var anun = 0, lockedPeriod = 0;
	switch (planType) {
	case "a":
		anun = 7;
		lockedPeriod = 3;
		break;
	case "b":
		anun = 9;
		lockedPeriod = 6;
		break;
	case "c":
		anun = 11;
		lockedPeriod = 12;
		break;
	default:
		break;
	}

	function Data() {
		this.phaseNumber = 0;
		this.principle = 0;
		this.profit = 0;
	}
	var dataArr = new Array();
	if (profitDis == '1') {
		var profit = 0;
		var investAmountTotal = investAmount;
		for (var i = 0; i <= lockedPeriod; i++) {
			var temp = new Data();
			temp.phaseNumber = i == 0 ? "" : i;
			investAmountTotal = round(investAmountTotal + profit, 2);
			temp.principle = investAmountTotal.toFixed(2);
			if (i != lockedPeriod) {
				profit = round(round(investAmountTotal
						* round((1 + anun / (100 * 12)), 8), 2)
						- investAmountTotal, 2);
				temp.profit = i == 0 ? "" : 0;
			} else {
				temp.profit = i == 0 ? "" : (investAmountTotal - investAmount)
						.toFixed(2);
			}
			dataArr.push(temp);
		}
		profit = investAmountTotal - investAmount;

		$("#earnBxNum").html(0);
		$("#earnBxNum").html(webUtil.numFormat(investAmountTotal, 2));
		$("#lxNum").html(webUtil.numFormat(profit, 2));
		$(".monthIncome").hide();
	} else if (profitDis == '2') {
		var profit = round(round(investAmount * round(anun / (12 * 100), 8), 2)
				* lockedPeriod, 2);
		for (var i = 0; i <= lockedPeriod; i++) {
			var temp = new Data();
			temp.phaseNumber = i == 0 ? "" : i;
			temp.principle = investAmount.toFixed(2);
			temp.profit = i == 0 ? "" : round(
					investAmount * round(anun / (12 * 100), 8), 2).toFixed(2);
			dataArr.push(temp);
		}
		$("#earnBxNum").html(
				webUtil.numFormat(round(investAmount
						* round(anun / (12 * 100), 8), 2), 2));
		$("#earnBxNum").html(webUtil.numFormat((profit + investAmount), 2));
		$("#lxNum").html(webUtil.numFormat(profit, 2));
		$(".monthIncome").hide();
	}
	createPlanProfitTable(dataArr);
}

// 四舍六入五成双
function round(num, digit) {
	var ratio = Math.pow(10, digit), _num = num * ratio, mod = _num % 1, integer = Math
			.floor(_num);
	if (mod > 0.5) {
		return (integer + 1) / ratio;
	} else if (mod < 0.5) {
		return integer / ratio;
	} else {
		return (integer % 2 === 0 ? integer : integer + 1) / ratio;
	}
}

// 保留2位小树
function changeTwoDecimal_f(x) {
	var f_x = parseFloat(x);
	if (isNaN(f_x)) {
		alert('function:changeTwoDecimal->parameter error');
		return false;
	}
	var f_x = Math.round(x * 100) / 100;
	var s_x = f_x.toString();
	var pos_decimal = s_x.indexOf('.');
	if (pos_decimal < 0) {
		pos_decimal = s_x.length;
		s_x += '.';
	}
	while (s_x.length <= pos_decimal + 2) {
		s_x += '0';
	}
	return s_x;
}
