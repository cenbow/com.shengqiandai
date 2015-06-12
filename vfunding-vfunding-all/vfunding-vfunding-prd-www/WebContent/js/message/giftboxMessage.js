var wholeTotal = 1;
$(function() {
	// 第二个选项卡
	$("#typelist li").on("click", function() {
		$("#typelist li").removeClass("active");
		$(this).addClass("active");
		if($(this).html().indexOf("已使用")>=0){
			$("#lastTime").text("使用时间");
		}else{
			$("#lastTime").text("失效时间");
		}
		getContent(1,'红包');
		//重新计算分页
		pagingAjax();
		//	
	});
	$.ajax({
		url : "/giftbox/giftBoxSum",
		type : "post",
		dataType : "json",
		async:false,
		success : function(json) {
			$("#useMoney").html(json);
		}
	});
	$.ajax({
		url : "/giftbox/giftBoxNoUseMoney",
		type : "post",
		dataType : "json",
		async:false,
		success : function(json) {
			$("#noUseMoney").html("即将生效红包：￥"+json);
		}
	});
	getContent(1, '红包');
	pagingAjax();
	
	//选项卡
	$(".giftUl li").on("click", function() {
		var index = $(".giftUl li").index($(this));
		$(".giftUl li").removeClass("active").addClass("unactive");
		$(this).addClass("active");
		$(".gift-cnt").hide();
		$(".gift-cnt").eq(index).show();
		if($(this).text() == '提现券'){
			$.ajax({
				url : "/giftbox/cashHongbao",
				type : "post",
				dataType : "json",
				async:false,
				success : function(json) {
					$("#cashHongbao").html(json);
				}
			});
			getContent(1, '提现券');
			pagingAjax();
		}else if ($(this).text() == '加息卡'){
			$.ajax({
				url : "/giftbox/hikesUseRate",
				type : "post",
				dataType : "json",
				async:false,
				success : function(json) {
					$("#hikesUseRate").html(json+"%");
				}
			});
			getContent(1, '加息卡');
			pagingAjax();
		}else if ($(this).text() == '红包'){
			$.ajax({
				url : "/giftbox/giftBoxSum",
				type : "post",
				dataType : "json",
				async:false,
				success : function(json) {
					$("#useMoney").html(json);
				}
			});
			getContent(1, '红包');
			pagingAjax();
		}else if ($(this).text() == '其他奖品'){
			getContent(1, '其他奖品');
			pagingAjax();
		}
	});
	
	//点击标题 显示内容
	$(document).on("click",".item-title",function() {
		var data={};
		data["type"]=$(this).attr('type');
		data["id"]=$(this).attr('id');
		updateIsLook_other(data);		
		var index = $("#cnt-items a").index($(this));
		$(this).parent().find(".card-cnt-tip").show();
		$(this).css("font-weight", "normal");
	});
	
	if(getQueryString("dakai")>0){
		$(".giftUl").find("li").eq(getQueryString("dakai")-1).click();
	}
	
});


function getQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}

function useGiftAny(){
	if ($("#realStatus").val() == '1') {
		$.ajax({
			url : "/giftbox/useGiftAny",
			type : "post",
			dataType : "json",
			async:false,
			success : function(json) {
				if(json.success == true){
					location.href = "/giftbox/boxPage";
				}else{
					art.dialog({
						title : '微积金提示',
						content : json.msg,
						icon : 'warning',
						ok : function() {

						},
						opacity : .3,
						fixed : true,
						lock : true
					});
				}
			}
		});
	} else {
		art.dialog({
					content : "您还未进行实名认证",
					title : '微积金提示',
					icon : 'warning',
					cancelVal : '关闭',
					cancel : true,
					button : [ {
						name : '去认证',
						callback : function() {
							window.location.href = "/user/realName?returnUrl=giftbox/boxPage";
						},
						focus : true
					} ],
					fixed : true,
					lock : true,
					opacity : .3
				});
		return false;
	}
	
	
}

function pagingAjax(start) {
	var pagingcount = $("#pagCount").val();
	if (wholeTotal > 1) {
		pagingcount = wholeTotal;
	}
	start = start==undefined||start==""?1:start;
	$("#paging").paginate({
		count : pagingcount,
		start : start,
		images : false,
		mouse : 'press',
		onChange : function(page) {
			var active = $(".active").eq(0);
			if (active.text() == '提现券') {
				getContent(page, '提现券');
				//pagingAjax();
			} else if (active.text() == '加息卡') {
				getContent(page, '加息卡');
				//pagingAjax();
			} else if (active.text() == '红包') {
				getContent(page, '红包');
				//pagingAjax();
			} else if (active.text() == '其他奖品') {
				getContent(page, '其他奖品');
				//pagingAjax();
			}
			location.href = "#condition1"
		}
	});
}
function getContent(page, cardType) {
	var postUrl = "/giftbox/boxList";
	if (cardType == '提现券') {
		postUrl = "/giftbox/cashList";
	} else if (cardType == '加息卡') {
		postUrl = "/giftbox/hikesList";
	} else if (cardType == '红包') {
		postUrl = "/giftbox/boxList";
	} else if (cardType == '其他奖品'){
		postUrl = "/giftbox/giftotherList";
	}
	var data = {};
	data["page"] = page;
	$("#typelist li").each(function() {
		if ($(this).hasClass("active")) {
			data["status"] = $(this).attr("status");
		}
	});
	if (postUrl && data.status) {
		$.ajax({
					url : postUrl,
					type : "post",
					dataType : "json",
					data : data,
					async:false,
					success : function(json) {
						if (json.total > 0) {
							var html = "";
							if (cardType == '提现券') {
								$(json.rows).each(function(i) {
									var look = this.isLook;
									html += '<li class="clear">';
									if(look == 1){
										html += '<a id="'+this.id+'" type="tixian" class="item-title" style="font-weight:normal">';
									}else{
										html += '<a id="'+this.id+'" type="tixian" class="item-title">';
									}
									html +='<i class="dot"></i>'+
										this.title+
									'</a>'+
									'<div class="item-num">'+
									this.money+
									'</div>'+
									'<div class="item-time">'+
									getLocalTime(this.addtime)+
									'</div>'+
									'<div class="card-cnt-tip" style="display:none">'+
									this.content+
									'</div>'+
									'</li>';
								});
								var index = $(".giftUl li").index($(".active"));
								$(".gift-cnt").eq(index).find("#cnt-items").html(html);
							} else if (cardType == '加息卡') {
								$(json.rows).each(function(i) {
									var look = this.isLook;
									html += '<li class="clear">';
									if(look == 1){
										html += '<a id="'+this.id+'" type="jiaxi" class="item-title" style="font-weight:normal">';
									}else{
										html += '<a id="'+this.id+'" type="jiaxi" class="item-title">';
									}
									html += '<i class="dot"></i>'+
										this.title +
									'</a>'+
									'<div class="item-num">'+
									this.rate+"%"+
									'</div>'+
									'<div class="item-time">'+
									getLocalTime(this.addtime)+
									'</div>'+
									'<div class="card-cnt-tip" style="display:none">'+
									this.content+
									'</div>'+
									'</li>';
								});
								var index = $(".giftUl li").index($(".active"));
								$(".gift-cnt").eq(index).find("#cnt-items").html(html);
							} else if (cardType == '红包') {
								$(json.rows).each(function(i) {
													if(this.isLook==0){
														html += "<tr id=\"tr_hb_"+this.id+"\" style=\"font-weight:bold\">";
													}else{
														html += "<tr id=\"tr_hb_"+this.id+"\" style=\"font-weight:normal\">";
													}
													html += "<td  class=\""+(this.isLook == '0'?'soll':'holl')+"\"  width=\"212\" height=\"40\"><div  class='titleCss' title='"+this.title+"'>"
															+ this.title
															+ "</div></td>";
													html += "<td width=\"120\">"
															+ this.money
															+ "</td>";
													html += "<td width=\"120\">"
															+ getLocalTime(this.addtime)
															+ "</td>";
													html += "<td width=\"120\">"
															+ getLocalTime(this.takeTime)
															+ "</td>";
													if($("#typelist").find(".active").eq(0).text().indexOf("已使用")>=0){
														html += "<td width=\"120\">"
															+ getLocalTime(this.updateTime)
															+ "</td>";
													}else{
														html += "<td width=\"120\">"
															+ strToDate(this.loseTime)
															+ "</td>";
													}
													html += "<td width=\"120\"><a class=\"clickDetail\" onclick=\"updateIsLook("
															+ this.id
															+ ")\">详情</a></td>";
													html += "</tr>";
													if (this.status == "0") {
														html += "<tr class=\"redbagUse\">";
														html += "<td colspan=\"6\" height=\"auto\" ><div><span>";
														html += this.content;
														html += "</span><a class=\"use\" giftid=\""
																+ this.id
																+ "\">使用</a></div></td>";
														html += "</tr>";
													} else {
														html += "<tr class=\"redbagUse\">";
														html += "<td colspan=\"6\" height=\"50\"><div style=\"height:50px;position:relative;\"><span>";
														html += this.content;
														html += "</span></div></td>";
														html += "</tr>";
													}
												});
								$(".cntDetail").html(html);
								clickDetail();
							} else if (cardType == '其他奖品') {
								$(json.rows).each(function(i) {
									var look = this.isLook;
									html += '<li class="clear">';
									if(look == 1){
										html += '<a id="'+this.id+'" type="other" class="item-title" style="font-weight:normal">';
									}else{
										html += '<a id="'+this.id+'" type="other" class="item-title">';
									}
									html += '<i class="dot"></i>'+
										this.title +
									'</a>'+
									'<div class="item-time" style="margin-left:50px">'+
									getLocalTime(this.addtime)+
									'</div>'+
									'<div class="card-cnt-tip" style="display:none">'+
									this.content+
									'</div>'+
									'</li>';
								});	
								var index = $(".giftUl li").index($(".active"));
								$(".gift-cnt").eq(index).find("#cnt-items").html(html);
							}
							
							$("#pagCount").val(json.total / 5);
							wholeTotal = json.total / 5;
							//pagingAjax();
						} else {
							$("#pagCount").val(1);
							wholeTotal=1;
							pagingAjax();
							$(".cntDetail")
									.html(
											"<tr><td colspan=\"6\" height=\"50\">暂无记录</td></tr>");
						}

					}
				})
	}
}
// 点击详情
function clickDetail() {
	$("a.clickDetail").on("click", function() {
		var index = $("a.clickDetail").index($(this));
		$(".redbagUse").hide();
		$(".redbagUse").eq(index).show();
	});
	// 使用
	$("a.use").on("click",function() {
						if ($("#realStatus").val() == '1') {
							$.ajax({
										url : "/giftbox/useGift",
										type : "post",
										dataType : "json",
										data : {
											"giftId" : $(this).attr("giftid")
										},
										success : function(json) {
											if (json.success) {
												getContent(1);
												//pagingAjax();
												location.href="/giftbox/boxPage";
											} else {
												art.dialog({
													title : '微积金提示',
													content : json.msg,
													icon : 'warning',
													ok : function() {
														getContent(1);
														//pagingAjax();
													},
													opacity : .3,
													fixed : true,
													lock : true
												});
												
											}
										}
									})
						} else {
							art
									.dialog({
										content : "您还未进行实名认证",
										title : '微积金提示',
										icon : 'warning',
										cancelVal : '关闭',
										cancel : true,
										button : [ {
											name : '去认证',
											callback : function() {
												window.location.href = "/user/realName?returnUrl=giftbox/boxPage";
											},
											focus : true
										} ],
										fixed : true,
										lock : true,
										opacity : .3
									});
							return false;
						}
					});
}
// 更新查看状态
function updateIsLook(id) {
	$.ajax({
		url : "/giftbox/updateIsLook",
		type : "post",
		dataType : "json",
		data : {
			"id" : id
		}
	})
	$("#tr_hb_"+id).attr("style","font-weight:normal");
}
//更新查看状态
function updateIsLook_other(data) {
	$.ajax({
		url : "/giftbox/updateIsLook",
		type : "post",
		dataType : "json",
		data : data
	})
}
// 格式化时间
function getLocalTime(nS) {
	return nS.substr(0, 11);
}


function strToDate(date){
	date =  date.replace(/-/g,"/");
	var oDate1 = new Date(date);
	oDate1.setDate(oDate1.getDate() - 1)
	var nowStr = oDate1.format("yyyy-MM-dd");
	return nowStr;
}