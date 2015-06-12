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
		starttime = startDate ;
	}
	if (endDate != null && endDate != "") {
		endtime = endDate ;
	}
	$
			.ajax({
				url : '/weekTender/assetsListAjax',
				data : {
					page : page,
					rows : 8,
					start : starttime + " 00:00:00",
					end : endtime + " 23:59:59"
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
						var html = "";
						if (rows != null && rows.length > 0) {
							$("#paging").show();
							$.each(rows,function(index,tender){
								var status;
								if(tender.status == 1){
									status = '已认购';
								}else if(tender.status == 2){
									status = '还款中';
								}else if(tender.status == 3){
									status = '已还款';
								}
								html += "<tr>" +
										"<td>"+tender.week.name+"</td>" +
										"<td>"+tender.week.apr+"%</td>" +
										"<td>"+tender.realbuyShare+"</td>" +
										"<td>"+tender.money+"</td>" +
										"<td>"+tender.interest+"</td>" +
										"<td>"+tender.repaymentAccount+"</td>" +
										"<td>"+tender.addTime+"</td>" +
										"<td>"+tender.repaymentTime+"</td>" +
										"<td>"+status+"</td>" +
										"</tr>";
							})
							$("#recordTableBody").html(html);
							if (result.total >= 1) {
								$("#pagCount").val(result.total / 8);
								wholeTotal = result.total / 8;
							}

						} else {
							$("#paging").hide();
							html = html
									+ "<tr><td height='48' colspan='9'>没有记录</td></tr>";
							$("#recordTableBody").html(html);
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