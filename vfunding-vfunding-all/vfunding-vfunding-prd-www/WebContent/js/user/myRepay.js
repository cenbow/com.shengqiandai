var wholeTotal = 1;

$(function() {
	rechargeAjax(1, null);
	pagingAjax();
	$('#keyword').keydown(function(e) {
		if (e.keyCode == 13) {
			queryRepay();
		}
	});
	$(".exchangeType li").each(function() {
		$(this).click(function() {
			var text = $(this).text();
			if (text == "已还借款") {
				$("#pagType").val("1");
			} else if (text == "待还借款") {
				$("#pagType").val("0");
			} else if (text == "全部") {
				$("#pagType").val("");
			}
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
function rechargeAjax(page, newDate) {
	var type = $("#pagType").val();
	var name = $("#keyword").val();
	$
			.ajax({
				url : '/borrowRepayment/myRepayAjax',
				data : {
					page : page,
					status : type,
					name : name
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
					var html = '<tr style="background: none repeat scroll 0% 0% rgb(245, 247, 248);"><td height="50" width="178" class="top-border">借款标题</td><td width="70" class="top-border">借款期次</td><td width="88" class="top-border">应还款时间</td><td width="115" class="top-border">本期应还本金</td><td width="79" class="top-border">利息</td><td width="90" class="top-border">逾期利息</td><td width="64" class="top-border">逾期天数</td><td width="96" class="top-border">状态/操作</td></tr>';
					if (rows != null && rows.length > 0) {
						$("#paging").show();
						rows = eval(rows);
						for (var i = 0; i < rows.length; i++) {
							var status = rows[i].status == 0 ? "<td width='96'><a class='btn78' href='javascript:myRepayAjax("
									+ rows[i].id
									+ ","
									+ "\""+rows[i].borrowName+"\""
									+ ","
									+ rows[i].repaymentAccount + ")'>还款</a>"
									: "<td width='96'>已还款";
							html = html + '<td height="50" width="178"><a href="/borrow/borrowDetail/'+rows[i].borrowId+'" target="_blank">'
									+ rows[i].borrowName
									+ '</a></td><td width="88">'
									+ parseInt(rows[i].order + 1)
									+ "</td><td width='70'>"
									+ rows[i].repaymentTimeStr
									+ "</td><td width='115'>"
									+ parseFloat(rows[i].capital).toFixed(2)
									+ "</td><td width='79'>"
									+ parseFloat(rows[i].interest).toFixed(2)
									+ "</td><td width='90'>"
									+ rows[i].lateInterest
									+ "</td><td width='70'>" + rows[i].lateDays
									+ "</td>" + status + "</td></tr>"
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

// 点击还款需调用的请求
function myRepayAjax(id,title,money) {
	//先弹出确认框，点击确认再还款-start
	var success = artDialog({
        id: 'artConfirm',
        icon: 'question',
        fixed: true,
        lock: true,
        opacity: .1,
        content: "还款标题:"+title+",还款金额:"+money+",是否确认要还款",
        ok: function () {
        	//还款-start
        	$.ajax({
        		url : '/borrow/repaymentBorrow',
        		data : {
        			repaymentId : id
        		},
        		beforeSend : function() {
        			$(".loading").show();
        		},
        		cache : false,
        		async : false,
        		type : 'post',
        		error : function(errorLog) {
        			$(".loading").hide();
        			art.dialog({
        				content : errorLog.responseText,
        				title : '微积金提示',
        				icon : 'warning',
        				cancelVal : '关闭',
        				cancel : true,
        				button : [ {
        					name : '去充值',
        					callback : function() {
        						window.location.href = "/account/recharge";
        					},
        					focus : true
        				} ],
        				fixed : true,
        				lock : true,
        				opacity : .3
        			// 为true等价于function(){}
        			});
        		},
        		success : function(result) {
        			$(".loading").hide();
        			result = $.parseJSON(result);
        			if (result.msg == '还款成功') {
        				art.dialog({
        					title : '微积金提示',
        					content : result.msg,
        					icon : 'succeed',
        					ok : function() {
        						var useMoneyNum = $("#useMoneyNum").text();
        						var strs = useMoneyNum.split("元"); // 字符分割
        						$("#useMoneyNum").text(
        								changeTwoDecimal_f(parseFloat(strs[0] - money))
        										+ "元");

        						$("#daihuan").removeClass("liColor");
        						$("#yihuan").addClass("liColor");
        						$("#pagType").val("1");
        						rechargeAjax(1);
        						pagingAjax();

        					},
        					fixed : true,
        					lock : true
        				// 为true等价于function(){}
        				});
        			} else if (result.msg.substring(0, 4) == '余额不足') {
        				art.dialog({
        					content : result.msg,
        					title : '微积金提示',
        					icon : 'warning',
        					cancelVal : '关闭',
        					cancel : true,
        					button : [ {
        						name : '去充值',
        						callback : function() {
        							window.location.href = "/account/recharge";
        						},
        						focus : true
        					} ],
        					fixed : true,
        					lock : true,
        					opacity : .3
        				// 为true等价于function(){}
        				});
        			} else {
        				art.dialog({
        					content : result.msg,
        					title : '微积金提示',
        					icon : 'warning',
        					ok : true,
        					fixed : true,
        					lock : true,
        					opacity : .3
        				});
        			}
        		},
        		contentType : "application/x-www-form-urlencoded;charset=UTF-8"
        	});
        	//还款-end
        	return true;
        },
        cancel: function () {
           return true;
        }
	});
	return false;
	//先弹出确认框，点击确认再还款-end
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

// 根据关键字查询
function queryRepay() {
	var type = $("#pagType").val();
	var name = $("#keyword").val();
	$
			.ajax({
				url : '/borrowRepayment/myRepayAjax',
				data : {
					page : 1,
					status : type,
					name : name
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
					var html = '<tr style="background: none repeat scroll 0% 0% rgb(245, 247, 248);"><td height="50" width="178" class="top-border">借款标题</td><td width="70">借款期次</td><td width="88">应还款时间</td><td width="115">本期应还本金</td><td width="79">利息</td><td width="90">逾期利息</td><td width="64">逾期天数</td><td width="96">状态/操作</td></tr>';
					if (rows != null && rows.length > 0) {
						$("#paging").show();
						rows = eval(rows);
						for (var i = 0; i < rows.length; i++) {
							var status = rows[i].status == 0 ? "<td width='96'><a class='btn78' href='javascript:myRepayAjax("
									+ rows[i].id
									+ ","
									+ "\""+rows[i].borrowName+"\""
									+ ","
									+ rows[i].repaymentAccount + ")'>还款</a>"
									: "<td width='96'>已还款";
							html = html + '<td height="50" width="178"><a href="/borrow/borrowDetail/'+rows[i].borrowId+'" target="_blank">'
									+ rows[i].borrowName
									+ '</a></td><td width="88">'
									+ parseInt(rows[i].order + 1)
									+ "</td><td width='70'>"
									+ rows[i].repaymentTimeStr
									+ "</td><td width='115'>"
									+ parseFloat(rows[i].capital).toFixed(2)
									+ "</td><td width='79'>"
									+ parseFloat(rows[i].interest).toFixed(2)
									+ "</td><td width='90'>"
									+ rows[i].lateInterest
									+ "</td><td width='70'>" + rows[i].lateDays
									+ "</td>" + status + "</td></tr>"
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
	pagingAjax();
}