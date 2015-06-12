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
	$.ajax({
				url : '/user/attestation',
				data : {
					page : page,
					rows : 6
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
					var html = '<tr><td width="153" height="50" class="tdBg">资料名称</td><td width="107" class="tdBg">资料类型</td><td width="100" class="tdBg">上传时间</td><td width="100" class="tdBg">审核时间</td><td width="108" class="tdBg">审核说明</td><td width="72" class="tdBg">积分</td><td width="62" class="tdBg">状态</td><td width="78" class="tdBg">操作</td></tr>';
					if (rows != null && rows.length > 0) {
						$("#paging").show();
						rows = eval(rows);
						for (var i = 0; i < rows.length; i++) {
							var status=rows[i].status == 0?'待审核':'已审核';
							html = html + '<tr><td width="153" height="50">'
									+ rows[i].sortName
									+ '</td><td width="107">' 
									+ rows[i].typeName
									+ '</td><td width="100">'
									+ rows[i].addtimeStr
									+ '</td><td width="100">'
									+ rows[i].verifyTimeStr
									+ '</td><td width="108">' 
									+ rows[i].verifyRemark
									+ '</td><td width="72">'
									+ rows[i].jifen
									+ '</td><td width="62">' 
									+ status
									+ '</td><td width="78">' 
									+ '<a href="javascript:deleteInfo('
									+ rows[i].id
									+');">删除</a>' 
									+ "</td></tr>"
						}
						$(".upItemsForm").html(html);
						if (result.total >= 1) {
							$("#pagCount").val(result.total / 5);
							wholeTotal = result.total / 5;
						}
					} else {
						$("#paging").hide();
						html = html+ '<tr><td width="153" height="50">&nbsp;</td><td width="107">&nbsp;</td><td width="100">&nbsp;</td><td width="100">没有记录</td><td width="108">&nbsp;</td><td width="72">&nbsp;</td><td width="62">&nbsp;</td><td width="78">&nbsp;</td></tr>';
						$(".upItemsForm").html(html);
					}
					$('#searched').val('yes');//标记已获取过数据
				},
				contentType : "application/x-www-form-urlencoded;charset=UTF-8"
			});
}

function deleteInfo(){
	
}
