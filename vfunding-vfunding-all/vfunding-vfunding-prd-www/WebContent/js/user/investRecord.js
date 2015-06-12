var wholeTotal = 1;
$(function() {
	pagingAjax();
	$(".exchangeType li,.exchangeDetail li").click(function() {
		$(this).addClass("liColor").siblings().removeClass("liColor");
	});
	$(".exchangeType li").each(function() {
		$(this).click(function() {
			var text = $(this).text();
			var eDate = getDay(0, 0);
			if (text == "投标记录") {
				$('#detail1_').show();
				$('#detail2_').hide();
				$("#pagType").val("1");
				$('#searchType').val(0);
				
				if($("#time_").find("li").eq(0).text() == '近一周' && $("#time_").find("li").eq(0).attr('class')=='liColor'){
					$("#d5222").val(eDate);
					$("#d5221").val(getDay(7));
				} else if($("#time_").find("li").eq(1).text() == '1个月' && $("#time_").find("li").eq(1).attr('class')=='liColor'){
					$("#d5222").val(eDate);
					$("#d5221").val(getDay(30));
				} else if($("#time_").find("li").eq(2).text() == '3个月' && $("#time_").find("li").eq(2).attr('class')=='liColor'){
					$("#d5222").val(eDate);
					$("#d5221").val(getDay(90));
				} else if($("#time_").find("li").eq(3).text() == '半年' && $("#time_").find("li").eq(3).attr('class')=='liColor'){
					$("#d5222").val(eDate);
					$("#d5221").val(getDay(183));
				}

				$('#type').val(1);
				$("#detail1_").find("li").eq(0).addClass("liColor")
				$("#detail1_").find("li").eq(1).removeClass("liColor")
			} else if (text == "收款记录") {
				$('#detail2_').show();
				$('#detail1_').hide();
				$("#pagType").val("-1");
				$('#searchType').val(1);

				if($("#time_").find("li").eq(0).text() == '近一周' && $("#time_").find("li").eq(0).attr('class')=='liColor'){
					$("#d5221").val(eDate);
					$("#d5222").val(getDay(-7));
				} else if($("#time_").find("li").eq(1).text() == '1个月' && $("#time_").find("li").eq(1).attr('class')=='liColor'){
					$("#d5221").val(eDate);
					$("#d5222").val(getDay(-30));
				} else if($("#time_").find("li").eq(2).text() == '3个月' && $("#time_").find("li").eq(2).attr('class')=='liColor'){
					$("#d5221").val(eDate);
					$("#d5222").val(getDay(-90));
				} else if($("#time_").find("li").eq(3).text() == '半年' && $("#time_").find("li").eq(3).attr('class')=='liColor'){
					$("#d5221").val(eDate);
					$("#d5222").val(getDay(-180));
				}
				
				
				$('#type').val(2);
				$("#detail2_").find("li").eq(0).addClass("liColor")
				$("#detail2_").find("li").eq(1).removeClass("liColor")
			}
			if ($('#searchType').val() == 1) {
				rechargeAjax2(1);
				pagingAjax2();
			} else {
				rechargeAjax(1);
				pagingAjax();
			}
		});
	})
	$(".exchangeDetail li").each(function() {
		$(this).click(function() {
			var text = $(this).text();
			if (text == "成功") {// tender
				$("#pagType").val("1");
				$('#status').val(1);
				$('#searchType').val(0);
			} else if (text == "关闭") {
				$("#pagType").val("2");
				$('#searchType').val(0);
				$('#status').val(2);
			} else if (text == "待收") { // collection
				var eDate = getDay(0, 0);
				if($("#time_").find("li").eq(0).text() == '近一周' && $("#time_").find("li").eq(0).attr('class')=='liColor'){
					$("#d5221").val(eDate);
					$("#d5222").val(getDay(-7));
				} else if($("#time_").find("li").eq(1).text() == '1个月' && $("#time_").find("li").eq(1).attr('class')=='liColor'){
					$("#d5221").val(eDate);
					$("#d5222").val(getDay(-30));
				} else if($("#time_").find("li").eq(2).text() == '3个月' && $("#time_").find("li").eq(2).attr('class')=='liColor'){
					$("#d5221").val(eDate);
					$("#d5222").val(getDay(-90));
				} else if($("#time_").find("li").eq(3).text() == '半年' && $("#time_").find("li").eq(3).attr('class')=='liColor'){
					$("#d5221").val(eDate);
					$("#d5222").val(getDay(-183));
				}
				$("#pagType").val("-1");
				$('#searchType').val(1);
				$('#status').val(1);
			} else if (text == "已收") {
				var eDate = getDay(0, 0);
				if($("#time_").find("li").eq(0).text() == '近一周' && $("#time_").find("li").eq(0).attr('class')=='liColor'){
					$("#d5222").val(eDate);
					$("#d5221").val(getDay(7));
				} else if($("#time_").find("li").eq(1).text() == '1个月' && $("#time_").find("li").eq(1).attr('class')=='liColor'){
					$("#d5222").val(eDate);
					$("#d5221").val(getDay(30));
				} else if($("#time_").find("li").eq(2).text() == '3个月' && $("#time_").find("li").eq(2).attr('class')=='liColor'){
					$("#d5222").val(eDate);
					$("#d5221").val(getDay(90));
				} else if($("#time_").find("li").eq(3).text() == '半年' && $("#time_").find("li").eq(3).attr('class')=='liColor'){
					$("#d5222").val(eDate);
					$("#d5221").val(getDay(183));
				}
				$("#pagType").val("-2");
				$('#searchType').val(1);
				$('#status').val(2);
			}
			if ($('#searchType').val() == 1) {
				rechargeAjax2(1);
				pagingAjax2();
			} else {
				rechargeAjax(1);
				pagingAjax();
			}
		});
	})
	$(".kindsOfdate li").each(function() {
		$(this).click(function() {
			var text = $(this).text();
			var sDate = null;
			var eDate = null;
			if($('#searchType').val() == 0) {
				sDate = getDay(7);
				eDate = getDay(0, 0);
				//项目
				if (text == "近一周") {
					sDate = getDay(7);
				} else if (text == "1个月") {
					sDate = getDay(30);
				} else if (text == "3个月") {
					sDate = getDay(90);
				} else if (text == "半年") {
					sDate = getDay(180);
				}
				$("#d5221").val(sDate);
				$("#d5222").val(eDate);
			} else {
				eDate = getDay(7);
				sDate = getDay(0, 0);
				//待收
				if (text == "近一周") {
					eDate = getDay(-7);
				} else if (text == "1个月") {
					eDate = getDay(-30);
				} else if (text == "3个月") {
					eDate = getDay(-93);
				} else if (text == "半年") {
					eDate = getDay(-180);
				}
				$("#d5221").val(sDate);
				$("#d5222").val(eDate);
			}
			
			$('#startTime').val(sDate);
			$('#endTime').val(eDate);
			if ($('#searchType').val() == 1) { // collection
				rechargeAjax2(1);
				pagingAjax2();
			} else { // tender
				rechargeAjax(1);
				pagingAjax();
			}
		});
	})
});
// 选择结束时间需要判断的
function myendtime(startDate, endDate) {
	if ($('#searchType').val() == 1) {// collection
		rechargeAjax2(1, startDate, endDate);
		pagingAjax2();
	} else { // tender
		rechargeAjax(1, startDate, endDate);
		pagingAjax();
	}
}
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
function pagingAjax2() {
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
			rechargeAjax2(page);
		}
	});
}
function rechargeAjax(page, startDate, endDate) {
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
				url : '/borrowTender/investRecordAjax',
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
					/*
					 * if(type == 12 || type == 0){ var html = '<tr style="background: none repeat scroll 0% 0% rgb(245, 247, 248);">' +'<td width="238">项目名称</td>' +'<td width="64">期限</td>' +'<td width="120">投资金额</td>' +'<td width="120">预期纯收益</td>' +'<td height="50" width="88">时间</td>' +'<td width="82">状态</td>' +'</tr>';
					 * if (rows != null && rows.length > 0) { var html2="";
					 * $("#paging").show(); rows = eval(rows); for (var i = 0; i <
					 * rows.length; i++) { var status =
					 * rows[i].status==1?'投资成功':(rows[i].status==2?'失败':'待审');
					 * var bfee = isNaN(rows[i].bfee)?0:rows[i].bfee; var gfee =
					 * isNaN(rows[i].gfee)?0:rows[i].gfee; html2=html2+'<tr></td><td width="238" height="50"><a
					 * href="/borrow/borrowDetail/'+ rows[i].id + '"
					 * target="_blank">' + rows[i].borrowName + '</a>' + '</td><td width="64">' +
					 * rows[i].timeLimit + '个月' + '</td><td width="120">￥' +
					 * rows[i].account + '</td><td width="120">' +
					 * (rows[i].apr-gfee-bfee).toFixed(2)+'%' + '</td><td width="120">' +
					 * timetodate(rows[i].addtime,"yyyy-MM-dd hh:mm:ss") + "</td><td width='82'><a
					 * href='#' target='_blank'>"+status+"</a></td></tr>" }
					 * $(".recordTable").html(html+html2); if (result.total >=
					 * 1) { $("#pagCount").val(result.total / 10); wholeTotal =
					 * result.total / 10; } } else { $("#paging").hide(); html =
					 * html + "<tr><td height='48' colspan='9'>暂无记录</td></tr>";
					 * $(".recordTable").html(html); } } else
					 */
					if (type == 1 || type == 0) {// 已成功项目
						var html = '<tr style="background: none repeat scroll 0% 0% rgb(245, 247, 248);">'
								+ '<td width="186" class="top-border">项目名称</td>'
								+ '<td width="74" class="top-border">净收益率</td>'
								+ '<td width="100" class="top-border">投资金额</td>'
								+ '<td width="100" class="top-border">待收金额</td>'
								+ '<td width="100" class="top-border">已收金额</td>'
								//+ '<td width="72" class="top-border">待收期次</td>'
								+ '<td width="72" class="top-border">已收期次</td>'
								+ '<td height="50" width="88" class="top-border">投标时间</td>'
								+ '<td width="88" class="top-border">操作</td>' + '</tr>';
						if (rows != null && rows.length > 0) {
							var html2 = "";
							$("#paging").show();
							rows = eval(rows);
							for (var i = 0; i < rows.length; i++) {
								var bfee = isNaN(rows[i].bfee) ? 0
										: rows[i].bfee;
								var gfee = isNaN(rows[i].gfee) ? 0
										: rows[i].gfee;
								var status = rows[i].status == 1 ? '投资成功'
										: (rows[i].status == 2 ? '失败' : '待审');
								html2 = html2
										+ '<tr><td height="50" width="186" align="left"><a href="/borrow/borrowDetail/'
										+ rows[i].id
										+ '" target="_blank">'
										+ rows[i].borrowName
										+ '</a>'
										+ '</td><td width="74">'
										+ (rows[i].apr - gfee - bfee)
												.toFixed(2)
										+ '%'
										+ '</td><td width="100">'
										+ rows[i].account
										+ '元</td><td width="100">'
										+ rows[i].repaymentAccount
										+ '元</td><td width="100">'
										+ rows[i].repaymentYesAccount
										+ '</td><td width="72">'
										+ (rows[i].allOrder - rows[i].noOrder)+'/'+rows[i].allOrder
										+ '</td><td width="88">'
										+ timetodate(rows[i].addtime,
												"yyyy-MM-dd hh:mm:ss")
										+ "</td><td width='88'>"
										+ status + "</td></tr>"
							}
							$(".recordTable").html(html + html2);
							if (result.total >= 1) {
								$("#pagCount").val(result.total / 10);
								wholeTotal = result.total / 10;
							}
						} else {
							$("#paging").hide();
							html = html
									+ "<tr><td height='48' colspan='8'>暂无记录</td></tr>";
							$(".recordTable").html(html);
						}
					} else if (type == 2) {// 已关闭项目
						var html = '<tr style="background: none repeat scroll 0% 0% rgb(245, 247, 248);">'
								+ '<td width="263" class="top-border">项目名称</td>'
								+ '<td width="80" class="top-border">期限</td>'
								+ '<td width="80" class="top-border">年利率</td>'
								+ '<td width="191" class="top-border">投标金额</td>'
								+ '<td height="50" width="88" class="top-border">时间</td>'
								+ '<td width="78" class="top-border">状态</td>' + '</tr>';
						if (rows != null && rows.length > 0) {
							var html2 = "";
							$("#paging").show();
							rows = eval(rows);
							for (var i = 0; i < rows.length; i++) {
								var status = rows[i].status == 1 ? "投资成功 "
										: (rows[i].status == 2 ? "失败" : "待审");
								html2 = html2
										+ '<tr><td height="50" width="88" align="left"><a href="/borrow/borrowDetail/'
										+ rows[i].id
										+ '" target="_blank">'
										+ rows[i].borrowName
										+ '</a>'
										+ '</td><td width="80">'
										+ rows[i].timeLimit
										+ '</td><td width="80">'
										+ rows[i].apr
										+ '%'
										+ '</td><td width="191">'
										+ rows[i].account
										+ '元</td><td width="88">'
										+ timetodate(rows[i].addtime,
												"yyyy-MM-dd hh:mm:ss")
										+ "</td><td width='78'>"
										+ status + "</td></tr>"
							}
							$(".recordTable").html(html + html2);
							if (result.total >= 1) {
								$("#pagCount").val(result.total / 10);
								wholeTotal = result.total / 10;
							}
						} else {
							$("#paging").hide();
							html = html
									+ "<tr><td height='48' colspan='8'>暂无记录</td></tr>";
							$(".recordTable").html(html);
						}
					}
				},
				contentType : "application/x-www-form-urlencoded;charset=UTF-8"
			});
}
function rechargeAjax2(page, startDate, endDate) {
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
				url : '/borrowTender/collectionRecordAjax',
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
							+ '<td height="50" width="191" class="top-border">项目名称</td>'
							+ '<td width="42" class="top-border">期次</td>'
							+ '<td width="115" class="top-border">应收本金</td>'
							+ '<td width="115" class="top-border">应收利息</td>'
							+ '<td width="81" class="top-border">逾期利息</td>'
							+ '<td width="120" class="top-border">应收时间</td>'
							+ '<td width="81" class="top-border">应收金额</td>';
					if (type == -1 || type == -2) {
						html = html + '<td width="118">状态</td>';
					}
					html = html + '</tr>';
					if (rows != null && rows.length > 0) {
						var html2 = "";
						$("#paging").show();
						rows = eval(rows);
						for (var i = 0; i < rows.length; i++) {
							var status = rows[i].status == 1 ? "已还" : "未还";
							var temp = '';
							if (type == -1 || type == -2) {
								temp = '<td width="118"><a>' + status
										+ '</a></td>'
							}
							html2 = html2
									+ '<tr><td height="50" width="191" align="left"><a href="/borrow/borrowDetail/'
									+ rows[i].id
									+ '" target="_blank">'
									+ rows[i].borrowName
									+ '</a>'
									+ '</td><td width="42">'
									+ (rows[i].order + 1)
									+ '/'
									+ rows[i].timeLimit
									+ '</td><td width="115">'
									+ rows[i].capital.toFixed(2)
									+ '元</td><td width="115">'
									+ rows[i].interest.toFixed(2)
									+ '元</td><td width="81">'
									+ rows[i].lateInterest
									+ '元</td><td width="120">'
									+ timetodate(rows[i].repayTime, "yyyy-MM-dd")
									+ '</td><td width="81">'
									+ rows[i].repaymentAccount.toFixed(2)
									+ '元</td>' + temp + '</tr>'
						}
						$(".recordTable").html(html + html2);
						if (result.total >= 1) {
							$("#pagCount").val(result.total / 10);
							wholeTotal = result.total / 10;
						}
					} else {
						$("#paging").hide();
						html = html
								+ "<tr><td height='48' colspan='8'>暂无记录</td></tr>";
						$(".recordTable").html(html);
					}
				},
				contentType : "application/x-www-form-urlencoded;charset=UTF-8"
			});
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

// js时间戳转时间
Date.prototype.pattern = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours() == 0 ? 12 : this.getHours(), // 小时
		"H+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds()
	// 秒
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	if (/(E+)/.test(fmt)) {
		fmt = fmt
				.replace(
						RegExp.$1,
						((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f"
								: "\u5468")
								: ""));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
}
function timetodate(tim, dat) {
	return new Date(parseInt(tim) * 1000).pattern(dat); // "yyyy/MM/dd,hh,mm,ss"
}
$(function(){
	if(window.location.hash == '#recentSum' || window.location.hash == '#recentAccount'){
		$("#type_").find("li").eq(1).click();
		$("#detail1_").hide();
		$("#detail2_").show();
		$("#time_").find("li").eq(3).click();
		rechargeAjax2(1);
	}
	$('#exportExcel').click(function(){
		$('#startTime').val($('#d5221').val());
		$('#endTime').val($('#d5222').val());
		$('#exportForm').submit();
	});
})