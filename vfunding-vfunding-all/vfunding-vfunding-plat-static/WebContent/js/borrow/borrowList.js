var wholeTotal = 1;

$(function() {
	borrowListAjax(1);
	pagingAjax();

	$("#condition1 li").each(function() {
		$(this).click(function() {
			var text = $(this).text();
			if (text == "全部") {
				$("#biaoType").val("");
			} else if (text == "抵押标") {
				$("#biaoType").val("fast");
			} else if (text == "体验标") {
				$("#biaoType").val("feel");
			}
			borrowListAjax(1);
			pagingAjax();
		});
	})
	$("#condition2 li").each(function() {
		$(this).click(function() {
			var text = $(this).text();
			var month = text.split("个")[0];
			if (month == "全部") {
				$("#timeLimit").val("");
			} else if (month == "大于12月") {
				$("#timeLimit").val("13");
			} else {
				$("#timeLimit").val(month);
			}
			borrowListAjax(1);
			pagingAjax();
		});
	})
	$("#condition3 li").each(function() {
		$(this).click(function() {
			var text = $(this).text();
			if (text == "全部") {
				$("#style").val("");
			} else if (text == "等额本息") {
				$("#style").val("0");
			} else if (text == "到期还款") {
				$("#style").val("2");
			} else if (text == "先息后本") {
				$("#style").val("3");
			}
			borrowListAjax(1);
			pagingAjax();
		});
	})
	$("#condition4 li").each(function() {
		$(this).click(function() {
			var text = $(this).text();
			if (text == "全部") {
				$("#status").val("");
			} else if (text == "正在招标") {
				$("#status").val("1");
			} else if (text == "满标待审") {
				$("#status").val("11");
			} else if (text == "成功借款") {
				$("#status").val("3");
			}
			borrowListAjax(1);
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
			borrowListAjax(page);
			location.href = "#condition1"
		}
	});
}
function borrowListAjax(page) {
	var biaoType = $("#biaoType").val();
	var timeLimit = $("#timeLimit").val();
	var style = $("#style").val();
	var status = $("#status").val();
	$
			.ajax({
				url : '/borrow/borrowListAjax',
				data : {
					page : page,
					status : status,
					style : style,
					timeLimit : timeLimit,
					biaoType : biaoType
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
					var html = '<tr><td width="264" height="56">借款标题&nbsp;</td><td width="108">担保保障&nbsp;</td>	<td width="143">借款金额&nbsp;</td>	<td width="100">期限&nbsp;</td>	<td width="109">预期纯收益&nbsp;</td>	<td width="110">进度&nbsp;</td>	<td width="117">状态&nbsp;</td></tr>';
					if (rows != null && rows.length > 0) {
						$("#paging").show();
						for (var i = 0; i < rows.length; i++) {
							var rowContent = rows[i];
							if (rowContent.isday == 1) {
								month = rowContent.timeLimitDay + "天";
							} else {
								month = rowContent.timeLimit + "个月";
							}
							var tenderAction;
							if (rowContent.status == 1
									&& parseFloat(rowContent.completePercent) < 100) {
								tenderAction = '<a href="/borrow/borrowDetail/'
										+ rowContent.id
										+ '"	class="btn116 touColor" target="blank">马上投标';
							} else if (rowContent.status == 1
									&& parseFloat(rowContent.completePercent) == 100) {
								tenderAction = '<a href="/borrow/borrowDetail/'
										+ rowContent.id
										+ '"	class="btnMan manColor" target="blank">满标待审';
							} else if (rowContent.status == 3
									&& !rowContent.borrowIsRepay) {
								tenderAction = '<a href="/borrow/borrowDetail/'
										+ rowContent.id
										+ '"	class="repaying huanColor" target="blank" style="margin:0 5px;">还款中';
							} else if (rowContent.status == 3
									&& rowContent.borrowIsRepay) {
								tenderAction = '<a href="/borrow/borrowDetail/'
										+ rowContent.id
										+ '"	class="repaying jieColor" target="blank" style="margin:0 5px;">已还款';
							} else if (rowContent.status == 10
									&& parseFloat(rowContent.completePercent) < 100) {
								tenderAction = '<a href="/borrow/borrowDetail/'
										+ rowContent.id
										+ '" class="btn116 touColor" target="blank">马上体验';
							} else if (rowContent.status == 10
									&& parseFloat(rowContent.completePercent) == 100) {
								tenderAction = '<a href="/borrow/borrowDetail/'
										+ rowContent.id
										+ '"	class="btnMan manColor" target="blank">满标待审';
							} else if (rowContent.status == 30
									&& !rowContent.borrowIsRepay) {
								tenderAction = '<a href="/borrow/borrowDetail/'
										+ rowContent.id
										+ '"	class="repaying huanColor" target="blank" style="margin:0 5px;">还款中';
							} else if (rowContent.status == 30
									&& rowContent.borrowIsRepay) {
								tenderAction = '<a href="/borrow/borrowDetail/'
										+ rowContent.id
										+ '"	class="repaying jieColor" target="blank" style="margin:0 5px;">已还款';
							}
							var name = rowContent.name;
							if (name.length > 10) {
								name = name.substr(0, 12) ;
							}
							
							var name2 = rowContent.name;
						
							
							if (rowContent.status < 10) {
								var litpicPath = '<img src="http://static.vfunding.cn/images/index/touxiang.png" height="72" width="72" />';
							} else {
								var litpicPath = '<img src="http://static.vfunding.cn/images/index/touxiang2.png" height="72" width="72" />';
							}

							if (rowContent.litpicPath != null
									&& rowContent.litpicPath != '') {
								litpicPath = '<img src="'
										+ rowContent.litpicPath
										+ '" height="72" width="72" />';
							}
							html = html
									+ '<tr><td width="264" height="112" class="boder_bottom"><a class="avantar" target="blank" href="/borrow/borrowDetail/'
									+ rowContent.id
									+ '">'
									+ litpicPath
									+ ' </a> <a class="biaoti" target="blank"  title="'+name2+' "   href="/borrow/borrowDetail/'
									+ rowContent.id
									+ '">'
									+ name
									+ '</a></td>'
									+ '<td width="108" class="boder_bottom"> <a href="/utilpage/aboutUs#security" target="_blank">本息保障</a></td>	<td width="143" class="boder_bottom">'
									+ parseFloat(rowContent.account).toLocaleString()
									+ '元</td>'
									+ '<td width="100" class="boder_bottom">'
									+ month
									+ '</td>	<td width="109" class="rate boder_bottom">'
									+ rowContent.expectApr
									+ '%</td>'
									+ '<td width="110" class="boder_bottom">	<div class="progress_circle   progressBar progressBar'
									+ rowContent.completeInteger + '">	<p>'
									+ rowContent.completePercent
									+ '</p></div></td>'
									+ '<td width="126" class="boder_bottom">'
									+ tenderAction + '</a></td></tr>';
						}
						$("#borrowItem").html(html);
						if (result.total >= 1) {
							$("#pagCount").val(result.total / 10);
							wholeTotal = result.total / 10;
						}

					} else {
						$("#paging").hide();
						html = html
								+ "<tr><td height='56' colspan='7'>没有记录</td></tr>";
						$("#borrowItem").html(html);
						location.href = "#condition1"
					}
				},
				contentType : "application/x-www-form-urlencoded;charset=UTF-8"
			});
}
