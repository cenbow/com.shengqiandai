var wholeTotal = 1;

$(function() {
	rechargeAjax(1, null);
	pagingAjax();

	$(".exchangeType li").each(function() {
		$(this).click(function() {
			var text = $(this).text();
			if (text == "初审被拒") {
				$("#pagType").val("0");
				$("#pagStatus").val("2");
			} else if (text == "复审被拒") {
				$("#pagType").val("0");
				$("#pagStatus").val("4");
			} else if (text == "还款中") {
				$("#pagType").val("1");
				$("#pagStatus").val("3");
			} else if (text == "流标") {
				$("#pagType").val("0");
				$("#pagStatus").val("7");
			} else if (text == "已还款") {
				$("#pagType").val("2");
				$("#pagStatus").val("3");
			} else if (text == "全部") {
				$("#pagType").val("0");
				$("#pagStatus").val("10");
			}
			rechargeAjax(1);
			pagingAjax();
		});
	})

	$(".kindsOfdate li").each(function() {
		$(this).click(function() {
			var text = $(this).text();
			var sDate = getDay(0);
			var eDate = getDay(0, 0);
			if (text == "今天") {
				sDate = getDay(0);
			} else if (text == "近一周") {
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
function rechargeAjax(page, startDate, endDate) {
	var starttime = $("#d5221").val();
	var endtime = $("#d5222").val();
	var type = $("#pagType").val();
	var status = $("#pagStatus").val();
	if (startDate != null && startDate != "") {
		starttime = startDate;
	}
	if (endDate != null && endDate != "") {
		endtime = endDate;
	}
	$
			.ajax({
				url : '/borrow/myRecordAjax',
				data : {
					page : page,
					startTime : starttime,
					endTime : endtime,
					status : status,
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
					var html = '<tr style="background: none repeat scroll 0% 0% rgb(245, 247, 248);"><td height="50" width="133" class="tdBg top-border">提交时间</td><td width="267" class="tdBg  top-border">借款标题</td>	<td width="94" class="tdBg top-border">借款期限</td><td width="156" class="tdBg top-border" >借款金额</td>	<td width="131" class="tdBg top-border">状态</td></tr>'
					if (rows != null && rows.length > 0) {
						$("#paging").show();
						rows = eval(rows);
						for (var i = 0; i < rows.length; i++) {
							var status = rows[i].status;
							var statusHtml = "";
							if (status == 1) {
								statusHtml = '<td width="131">招标中';
							} else if (status == 2) {
								statusHtml = '<td width="131"><a class="btn78" href="/borrow/handleBorrow/'
										+ rows[i].id
										+ '?status='
										+ rows[i].status + '">编辑</a>';
							} else if (status == 4) {
								statusHtml = '<td width="131"><a class="btn78" href="/borrow/handleBorrow/'
										+ rows[i].id
										+ '?status='
										+ rows[i].status + '">再次申请</a>';
							} else if (status == 3
									&& parseFloat(rows[i].repaymentYesaccount + 1) >= rows[i].repaymentAccount) {
								statusHtml = '<td width="131">已还款';
							} else if (status == 3
									&& parseFloat(rows[i].repaymentYesaccount + 1) < rows[i].repaymentAccount) {
								statusHtml = '<td width="131"><a class="btn78" href="/borrowRepayment/myRepay?name='
										+ rows[i].name
										+ '"  target="_black">还款中</a>';
							} else if (status == 0) {
								statusHtml = '<td width="131">等待初审';
							} else if (status == 7) {
								statusHtml = '<td width="131">流标';
							} else if (status == 6) {
								statusHtml = '<td width="131">撤标申请中';
							} else if (status == 4) {
								statusHtml = '<td width="131">复审被拒';
							} else if (status == 5) {
								statusHtml = '<td width="131">已撤标';
							}

							html = html + '<td height="50" width="133">'
									+ rows[i].addTimeStr
									+ '</td><td width="267"><a href="/borrow/borrowDetail/'+rows[i].id+'" target="_blank">' + rows[i].name
									+ "</a></td><td width='94'>"
									+ rows[i].timeLimit
									+ "</td><td width='156'>"
									+ parseFloat(rows[i].account).toFixed(2)
									+ "</td>" + statusHtml + "</td></tr>"
						}
						$(".recordTable").html(html);
						if (result.total >= 1) {
							$("#pagCount").val(result.total / 10);
							wholeTotal = result.total / 10;
						}
					} else {
						$("#paging").hide();
						html = html
								+ "<tr><td height='48' colspan='8'>没有记录</td></tr>";
						$(".recordTable").html(html);
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