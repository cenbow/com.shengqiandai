var wholeTotal = 1;

$(function() {
	rechargeAjax(1, '/sysMessage/sysMessagePageAjax');
	pagingAjax();

	// 点击邮件标题
	$("#recordTableBody").on("click", "a.msg-title", function(ev) {
		$(this).find("i").removeClass("xf1").addClass("xf2");
		var index = $("a.msg-title").index($(this));
		$(".msg-content").hide();
		$(".msg-content").eq(index).show();
		var url = "";
		if ($(".active").text() == "站内信") {
			url = "/sysMessage/lookMessage";

		} else if ($(".active").text() == "系统消息") {
			url = "/sysMessage/lookSysMessage";
		}
		$.post(url + "?id=" + $(this).find("i").attr("id"), function(data) {
			var json = $.parseJSON(data);
			$("#newNoReadNum").html(json.unIsLookCount);
			$("#newReadNum").html(json.isLookCount);
		});
	});
	// 选项卡
	$("#msgNavUl li").on("click", function() {
		$("#msgNavUl li").removeClass("active").addClass("unactive");
		$(this).addClass("active");
		var text = $(this).text();
		getContent(text);
	});
	// 点击未读标签
	$("#noRead").click(function() {
		$("#isLook").val(0);
		selectReadOrNoRead($(".active").text());
	})
	// 点击已读标签
	$("#read").click(function() {
		$("#isLook").val(1);
		selectReadOrNoRead($(".active").text());
	})
	// 点击消息行的X
	$("#recordTableBody").on("click", ".cancel", function() {
		var deleteId = $(this).attr('deleteId');
		var url = "";
		if ($(".active").text() == "站内信") {
			url = "/sysMessage/deleteMessage"
		} else if ($(".active").text() == "系统消息") {
			url = "/sysMessage/deleteSystemMessage";
		}
		$.getJSON(url, {
			ids : deleteId
		}, function(data) {
			if (data) {
				location.reload(true);
			}
		});
	});

	// 全选
	$("#selectAll").click(function() {

		if ($(this).attr("checked")) {
			$(this).attr("checked", true);
			$("input[name='check-box']").attr("checked", true);

		} else {

			$(this).removeAttr("checked");
			$("input[name='check-box']").removeAttr("checked");

		}
	});

	// 都选中则全选，一个未选则不全选

	$("input[name='check-box']")
			.each(
					function() {
						$(this)
								.click(
										function() {
											if ($("input[name='check-box']:checked").length == $("input[name='check-box']").length) {
												$("#selectAll").attr("checked",
														true);
											} else {
												$("#selectAll").removeAttr(
														"checked");
											}
										});
					});

});

// 获取选项卡内容
function getContent(text) {
	$("#isLook").val(0);
	selectReadOrNoRead(text);
}
// 根据站内信还是系统消息判断执行的请求链接
function selectReadOrNoRead(text) {
	if (text == '站内信') {
		rechargeAjax(1, '/sysMessage/messagePageAjax');
		pagingAjax();
	} else {
		rechargeAjax(1, '/sysMessage/sysMessagePageAjax');
		pagingAjax();
	}
}

function myendtime(startDate, endDate) {
	rechargeAjax(1, '/sysMessage/sysMessagePageAjax');
	pagingAjax();
}

// 分页显示
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
			if ($(".active").text() == '站内信') {
				rechargeAjax(page, '/sysMessage/messagePageAjax');
			} else {
				rechargeAjax(page, '/sysMessage/sysMessagePageAjax');
			}
		}
	});
}
// 请求数据
function rechargeAjax(page, url) {
	var type = $("#pagType").val();
	$
			.ajax({
				url : url,
				data : {
					page : page,
					rows : 10,
					isLook : $("#isLook").val()
				},
				cache : false,
				async : false,
				type : 'post',
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert('很遗憾，出异常了' + errorThrown);
				},
				success : function(result) {
					result = $.parseJSON(result);
					var rows = result.result.rows;
					var html = "";
					if (rows != null && rows.length > 0) {
						$("#paging").show();
						if (result.messageType == "system") {// 加载系统消息
							$
									.each(
											rows,
											function(index, sysMessage) {
												html += '<div class="msg">'
														+ '<div class="zn-msg">'
														+ '<input type="checkbox" id="checkbox1" deleteId="'
														+ sysMessage.id
														+ '" name="check-box" />';
												if (sysMessage.isLook == 1) {
													html += '<a class="msg-title  fl" title="yes"> <i id="'
															+ sysMessage.id
															+ '" class="msgbg xf2"></i>'
															+ sysMessage.title
															+ '</a>';
												} else {
													html += '<a class="msg-title  fl"> <i id="'
															+ sysMessage.id
															+ '" class="msgbg xf1"></i> '
															+ sysMessage.title
															+ '</a>';
												}
												html += '<div class="cancel fr" deleteId="'
														+ sysMessage.id
														+ '"></div>'
														+ '<div class="msg-time fr">'
														+ sysMessage.addtime.substr(0,11)
														+ '</div>'
														+ '</div>'
														+ '<div class="msg-content">'
														+ sysMessage.content
														+ '</div>' + '</div>';
											})
						} else if (result.messageType == "ordinary") {// 加载普通站内信
							$
									.each(
											rows,
											function(index, message) {
												html += '<div class="msg">'
														+ '<div class="zn-msg">'
														+ '<input type="checkbox" id="checkbox1" deleteId="'
														+ message.id
														+ '" name="check-box" />';
												if (message.status == 1) {
													html += '<a class="msg-title  fl" title="yes"> <i id="'
															+ message.id
															+ '" class="msgbg xf2"></i>'
															+ message.name
															+ '</a>';
												} else {
													html += '<a class="msg-title  fl"> <i id="'
															+ message.id
															+ '" class="msgbg xf1"></i> '
															+ message.name
															+ '</a>';
												}
												html += '<div class="cancel fr" deleteId="'
														+ message.id
														+ '"></div>'
														+ '<div class="msg-time fr">'
														+ timetodate1(message.addtime,"yyyy-MM-dd")
														+ '</div>'
														+ '</div>'
														+ '<div class="msg-content">'
														+ message.content
														+ '</div>' + '</div>';
											})
						}
						$("#recordTableBody").html(html);
						if (result.result.total >= 1) {
							$("#pagCount").val(result.result.total / 10);
							wholeTotal = result.result.total / 10;
						}
					} else {
						$("#paging").hide();
						html = html
								+ "<span style='width:100%;text-align:center;display:block;'>没有记录</span>";
						$("#recordTableBody").html(html);
					}
					// 填充 已读，未读，共有 数量
					$("#newNoReadNum").html(result.unIsLookCount);
					$("#newReadNum").html(result.isLookCount);
					$("#sumNum")
							.html(result.isLookCount + result.unIsLookCount);
				},
				contentType : "application/x-www-form-urlencoded;charset=UTF-8"
			});
}

// 批量删除消息
function deteleMessages() {
	var ids = '';
	$("input[name='check-box']").each(function() {
		if ($(this).attr("checked")) {
			var id = $(this).attr('deleteId');
			if (id != '' && id != 'undefined') {
				ids += ',' + id;
			}
		}
	});
	if (ids != '') {
		ids = ids.substr(1, ids.length);
		var url = "";
		if ($(".active").text() == "站内信") {
			url = "/sysMessage/deleteMessage"
		} else if ($(".active").text() == "系统消息") {
			utl = "/sysMessage/deleteSystemMessage";
		}
		$.getJSON(url, {
			ids : ids
		}, function(data) {
			if (data) {
				location.reload(true);
			}
		});
	}
}


//js时间戳转时间
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
	return new Date(parseInt(tim)).pattern(dat); // "yyyy/MM/dd,hh,mm,ss"
}

function timetodate1(tim, dat) {
	return new Date((parseInt(tim) * 1000)).pattern(dat); // "yyyy/MM/dd,hh,mm,ss"
}