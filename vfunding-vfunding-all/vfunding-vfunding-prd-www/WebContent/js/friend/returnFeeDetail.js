var wholeTotal = 1;

$(function() {
	rechargeAjax(1);
	pagingAjax();

	$(".fd-select dd").each(function() {
		$(this).click(function() {
			var text = $(this).text();
			if (text == "待返") {
				$("#status").val("0");
			} else if (text == "已返") {
				$("#status").val("1");
			}
			rechargeAjax(1);
			pagingAjax();
		});
	})
})

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
function rechargeAjax(page) {
	var status = $('#status').val();
	$
			.ajax({
				url : '/friend/returnDetaiAjax',
				data : {
					"page" : page,
					"status" : status,
					"rows" : "5"
				},
				cache : false,
				async : false,
				type : 'get',
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
					var html = '	<tr>	<td width="146" height="50" class="bt">预计到帐日</td><td width="146" height="50" class="bt">投资日期</td><td width="146" class="bt">投资人</td>'
							+ '<td width="146" class="bt">投资标名称</td><td width="146" class="bt">投资金额</td><td width="146" class="bt">返佣金额</td>'
							+ '<td width="146" class="bt">状态</td></tr>';
					if (rows != null && rows.length > 0) {
						$("#paging").show();
						rows = eval(rows);
						var statusText = '';
						if (status == 0) {
							var statusText = '待返';
						} else {
							var statusText = '已返';
						}
						for (var i = 0; i < rows.length; i++) {
							html = html + '<tr><td width="146" height="50">'
									+ rows[i].repaymentTime
									+ '</td><td width="146">' + rows[i].addtime
									+ '</td><td width="146">'
									+ rows[i].userName
									+ '</td><td width="146">'
									+ rows[i].borrowName
									+ '</td><td width="146">' + rows[i].account
									+ '元</td><td width="146">'
									+ rows[i].returnfee
									+ '元</td><td width="146">' + statusText
									+ "</td></tr>"
						}
						$("#returnDetail").html(html);
						if (result.total >= 1) {
							$("#pagCount").val(result.total / 5);
							wholeTotal = result.total / 5;
						}
						$("#paging").show();
					} else {
						$("#paging").hide();
						html = html
								+ '<tr><td colspan="7" height="50">没有记录</td></tr>';
						$("#returnDetail").html(html);
					}

				},
				contentType : "application/x-www-form-urlencoded;charset=UTF-8"
			});
}

function searchFriend() {
	rechargeAjax(1);
	pagingAjax();
}
