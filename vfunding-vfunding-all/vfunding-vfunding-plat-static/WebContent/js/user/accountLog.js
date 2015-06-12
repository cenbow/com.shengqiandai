var wholeTotal = 1;
$(function() {
	pagingAjax();
	$(".exchangeType2 li").each(function() {
		$(this).click(function() {
			var text = $(this).text();
			if (text == "全部") {
				$("#pagType").val("0");
			} else if (text == "充值") {
				$("#pagType").val("1");
			} else if (text == "提现") {
				$("#pagType").val("2");
			} else if (text == "投资") {
				$("#pagType").val("3");
			} else if (text == "回款记录") {
				$("#pagType").val("4");
			} else if (text == "扣除冻结款") {
				$("#pagType").val("5");
			} else if (text == "充值手续费") {
				$("#pagType").val("6");
			} else if (text == "投标失败资金返回") {
				$("#pagType").val("7");
			} else if (text == "借款入帐") {
				$("#pagType").val("8");
			} else if (text == "提现冻结") {
				$("#pagType").val("9");
			} else if (text == "保证金") {
				$("#pagType").val("10");
			} else if (text == "还款") {
				$("#pagType").val("11");
			} else if (text == "利息管理费用") {
				$("#pagType").val("12");
			}
			rechargeAjax(1);
			pagingAjax();
		});
	})

	$(".kindsOfdate li").each(function() {
		$(this).click(function() {
			var text = $(this).text();
			var sDate = getDay(7);
			var eDate = getDay(0, 0);
			if (text == "近一周") {
				sDate = getDay(7);
			} else if (text == "1个月") {
				sDate = getDay(31);
			} else if (text == "3个月") {
				sDate = getDay(93);
			} else if (text == "半年") {
				sDate = getDay(183);
			}
			$("#d5221").val(sDate);
			$("#d5222").val(eDate);
			rechargeAjax(1);
			pagingAjax();
		});
	})
});

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
			rechargeAjax(page);
		}
	});
}
function rechargeAjax(page, startDate, endDate)  {
	var type = $("#pagType").val();
	var starttime = $("#d5221").val();
	var endtime = $("#d5222").val();
	if (startDate != null && startDate != "") {
		starttime = startDate;
	}
	if (endDate != null && endDate != "") {
		endtime = endDate;
	}
	$
			.ajax({
				url : '/accountLog/ajaxListPage',
				data : {
					page : page,
					startTime : starttime,
					endTime : endtime,
					status : type
				},
				cache : false,
				async : false,
				type : 'post',
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert('很遗憾，出异常了' + errorThrown);
				},
				beforeSend: function(){ 
					$('.loading').show();
				},
				success : function(result) {
					$('.loading').hide();
					result = $.parseJSON(result);
					var rows = result.rows;
					var html = '<tr style="background: none repeat scroll 0% 0% rgb(245, 247, 248);">'
							+ '<td height="48" width="60">类型</td>'
							+ '<td width="86">操作金额</td>'
							+ '<td width="96">总金额</td>'
							+ '<td width="86">可用金额</td>'
							+ '<td width="86">冻结金额 </td>'
							+ '<td width="96">待收金额</td>'
							+ '<td width="70">交易对方</td>'
							+ '<td width="96">交易时间</td>'
							+ '<td width="100">备注信息</td></tr>';
					if (rows != null && rows.length > 0) {
						var html2 = '';
						$("#paging").show();
						rows = eval(rows);
						for (var i = 0; i < rows.length; i++) {
							var type_ = rows[i].type;
							type_ = type_ == 'recharge' ? '充值'
									: (type_ == 'tender' ? '投资'
											: (type_ == 'cash' ? '提现'
													: (type_ == 'collection' ? '回款'
															: (type_ == 'recharge_fee'
																	|| type_ == 'fee' ? '充值手续费'
																	: type_ == 'invest_false' ? '投资失败回款'
																			: (type_ == 'borrow_success' ? '借款入帐'
																					: (type_ == 'cash_frost' ? '提现冻结'
																							: (type_ == 'margin' ? '保证金'
																									: (type_ == 'repayment' ? '还款'
																											: (type_ == 'tender_mange' ? '利息管理费用'
																													: (type_ == 'invest' ? '扣除冻结款'
																															: (type_ == 'invest_repayment' ? '还款'
																																	: (type_ == 'ticheng' ? '提成'
																																			: (type_ == 'recharge_success'?'提现成功'
																																					: (type_ == 'recharge_false'?'提现失败'
																																					:(type_ == 'tixian_fee'?'提现手续费'
																																							:(type_ == 'invest_flow'?'流标退款':''))))))))))))))))

							html2 = html2
									+ '<tr><td height="48" width="60">'
									+ type_
									+ '</td><td width="86">'
									+ rows[i].money
									+ "元</td><td width='96'>"
									+ rows[i].total
									+ "元</td><td width='86'>"
									+ rows[i].useMoney
									+ "元</td><td width='86'>"
									+ rows[i].noUseMoney
									+ "元</td><td width='96'>"
									+ rows[i].collection
									+ "元</td><td width='70'>"
									+ "结算中心"
									+ "</td><td width='96'>"
									+ timetodate(rows[i].addtime,
											"yyyy-MM-dd hh:mm:ss")
									+ "</td><td width='100'>" + rows[i].remark
									+ "</td></tr>"
						}
						$(".recordTable").html(html + html2);
						if (result.total >= 1) {
							$("#pagCount").val(result.total / 10);
							wholeTotal = result.total / 10;
						}
					} else {
						$("#paging").hide();
						html2 = html2
								+ "<tr><td height='48' colspan='9'>没有记录</td></tr>";
						$(".recordTable").html(html + html2);
					}
				},
				contentType : "application/x-www-form-urlencoded;charset=UTF-8"
			});
}

// 选择结束时间需要判断的
function myendtime(startDate, endDate) {
	rechargeAjax(1, startDate, endDate);
	pagingAjax();
}
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

function getDay(day, fun) {
	var today = new Date();
	var targetday_milliseconds = today.getTime();
	if (fun == 1) {
		targetday_milliseconds = targetday_milliseconds + 1000 * 60 * 60 * 24
				* day;
	} else {
		targetday_milliseconds = targetday_milliseconds - 1000 * 60 * 60 * 24
				* day;
	}

	today.setTime(targetday_milliseconds); // 注意，这行是关键代码
	var tYear = today.getFullYear();
	var tMonth = today.getMonth();
	var tDate = today.getDate();
	tMonth = doHandleMonth(tMonth + 1);
	tDate = doHandleMonth(tDate);
	return tYear + "-" + tMonth + "-" + tDate;
}

function doHandleMonth(month) {
	var m = month;
	if (month.toString().length == 1) {
		m = "0" + month;
	}
	return m;
}
