var wholeTotal = 1;

$(function() {
	rechargeAjax(1, null);
	pagingAjax();

	$(".kindsOfdate li").each(function() {
		$(this).click(function() {
			var text = $(this).text();
			var sDate = getDay(0);
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

	$(".exchangeType li").each(function() {
		$(this).click(function() {
			var text = $(this).text();
			if (text == "充值") {
				$("#pagType").val("recharge");
			} else if (text == "提现") {
				$("#pagType").val("cash");
			}
			rechargeAjax(1);
			pagingAjax();
		});
	})
});

function myendtime(startDate, endDate) {
	rechargeAjax(1, startDate, endDate);
	pagingAjax();
}

function pagingAjax() {
	var pagingcount = $("#pagCount").val();
	if (wholeTotal > 1) {
		pagingcount = wholeTotal;
	}
	$("#paging").paginate({
		count : pagingcount,
		start : 1,
		display : 5,
		images : false,
		mouse : 'press',
		onChange : function(page) {
			rechargeAjax(page);
		}
	});
}
function rechargeAjax(page, startDate, endDate) {
	var starttime = $("#d5221").val();
	var endtime = $("#d5222").val();
	var type = $("#pagType").val();
	if (startDate != null && startDate != "") {
		starttime = startDate;
	}
	if (endDate != null && endDate != "") {
		endtime = endDate;
	}
	$
			.ajax({
				url : '/account/rechargeCashAjax',
				data : {
					page : page,
					rows : 8,
					start : starttime,
					end : endtime,
					type : type
				},
				cache : false,
				async : false,
				type : 'post',
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert('很遗憾，出异常了' + errorThrown);
				},
				beforeSend : function() {
					$(".loading").show();
				},
				success : function(result) {
					$(".loading").hide();
					result = $.parseJSON(result);
					var rows = result.rows;
					if (type == "recharge") {
						var html = "<tr style='background: none repeat scroll 0% 0% rgb(245, 247, 248);'><td height='48' width='156'>时间</td><td width='154'>充值金额</td><td width='154'>到账金额</td><td width='180'>状态</td></tr>";
						if (rows != null && rows.length > 0) {
							$("#paging").show();
							rows = eval(rows);
							for (var i = 0; i < rows.length; i++) {
								var status;
								if (rows[i].status == 0) {
									status = '待审';
								} else if (rows[i].status == 1) {
									status = '成功';
								} else if (rows[i].status == 2) {
									status = '失败';
								}
								html = html
										+ "<tr class='recordTableTr'><td height='48' width='156'>"
										+ rows[i].addtime
										+ "</td><td width='154'>"
										+ parseFloat(rows[i].money).toFixed(2)
										+ "</td><td width='154'>"
										+ parseFloat(rows[i].money).toFixed(2)
										+ "</td><td width='180'>" + status
										+ "</td></tr>"
							}
							$(".recordTable").html(html);
							if (result.total >= 1) {
								$("#pagCount").val(result.total / 8);
								wholeTotal = result.total / 8;
							}

						} else {
							$("#paging").hide();
							html = html
									+ "<tr><td height='48' colspan='5'>没有记录</td></tr>";
							$(".recordTable").html(html);
						}
					} else if (type == 'cash') {
						var html = "<tr style='background: none repeat scroll 0% 0% rgb(245, 247, 248);'><td height='48' width='156'>时间</td><td width='154'>提现金额</td><td width='154'>到账金额</td><td width='154'>手续费</td><td width='180'>状态</td></tr>";
						if (rows != null && rows.length > 0) {
							$("#paging").show();
							rows = eval(rows);
							for (var i = 0; i < rows.length; i++) {
								var status;
								if (rows[i].status == 0) {
									status = '待审';
								} else if (rows[i].status == 1) {
									status = '成功';
								} else if (rows[i].status == 2) {
									status = '失败';
								}
								html = html
										+ "<tr class='recordTableTr'><td height='48' width='156'>"
										+ rows[i].addtime
										+ "</td><td width='154'>"
										+ (parseFloat(rows[i].money) + parseFloat(rows[i].fee))
												.toFixed(2)
										+ "</td><td width='154'>"
										+ parseFloat(rows[i].money).toFixed(2)
										+ "</td><td width='180'>"
										+ parseFloat(rows[i].fee).toFixed(2)
										+ "</td><td width='180'>" + status
										+ "</td></tr>"
							}
							$(".recordTable").html(html);
							if (result.total >= 1) {
								$("#pagCount").val(result.total / 10);
								wholeTotal = result.total / 10;
							}

						} else {
							$("#paging").hide();
							html = html
									+ "<tr><td height='48' colspan='5'>没有记录</td></tr>";
							$(".recordTable").html(html);
						}
					}

				},
				contentType : "application/x-www-form-urlencoded;charset=UTF-8"
			});
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