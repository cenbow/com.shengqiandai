var wholeTotal = 1;

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
	var userName = $('#friendSearch').val();
	var data;
	if (userName != null && userName != '' && userName.length > 0) {
		data = {
			"page" : page,
			"rows" : "5",
			"userName" : userName
		}
	} else {
		data = {
			"rows" : "5",
			"page" : page
		}
	}
	$
			.ajax({
				url : '/friend/friendList',
				data : data,
				cache : false,
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
					var html = '<tr><td width="195" height="50" class="friendBg">好友用户名</td><td width="195" class="friendBg">真实姓名</td><td width="195" class="friendBg">注册时间</td><td width="195" class="friendBg">用户级别</td></tr>';
					if (rows != null && rows.length > 0) {
						$("#paging").show();
						rows = eval(rows);
						for (var i = 0; i < rows.length; i++) {

							html = html + '<tr><td width="195" height="50">'
									+ rows[i].username
									+ '</td><td width="195">'
									+ rows[i].realnameStr
									+ '</td><td width="195">'
									+ rows[i].addtimeStr
									+ '</td><td width="195">'
									+ rows[i].typeName + "</td></tr>"
						}
						$("#myFriend").html(html);
						if (result.total >= 1) {
							$("#pagCount").val(result.total / 5);
							wholeTotal = result.total / 5;
						}
						if(page == 1){
							pagingAjax();
						}
					} else {
						$("#paging").hide();
						html = html
								+ '<tr><td width="195" height="50" colspan="4">没有记录</td></tr>';
						$("#myFriend").html(html);
					}
					$('#searched').val("yes");// 标记已获取过数据
				},
				contentType : "application/x-www-form-urlencoded;charset=UTF-8"
			});
}

function searchFriend() {
	rechargeAjax(1);
}
