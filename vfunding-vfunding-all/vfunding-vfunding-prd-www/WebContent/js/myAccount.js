// JavaScript Document

$(function() {

	$(".item dd").click(
			function() {

				$(this).addClass("current").parent().parent().find("dd")
						.removeClass("current");

				$(this).addClass("current").siblings().removeClass("current");

				$(".item .safeCenter").children().removeClass("current");

				$(".item .friendControl").children().removeClass("current");

			});

	$(".item .safeCenter").click(
			function() {

				$(this).children().addClass("current");
				$(".items dd").removeClass("current");
				$(this).parent().next().find(".friendControl").children()
						.removeClass("current");

			});

	$(".item .friendControl").click(
			function() {

				$(this).children().addClass("current");
				$(".items dd").removeClass("current");
				$(this).parent().prev().find(".safeCenter").children()
						.removeClass("current");

			});

	$(".kindsOfdate li,.exchangeType li").click(function() {

		$(this).addClass("liColor").siblings().removeClass("liColor");

	});
	$(".kindsOfdate li,.exchangeType2 li").click(function() {

		$(this).addClass("liColor").siblings().removeClass("liColor");

	});
	$("input.wdate").on("click", function() {
		WdatePicker();
	});

	$(
			".recordTable tr:first,#recordTable2 tr:first,#recordTable3 tr:first,#recordTable4 tr:first,#recordTable5 tr:first,#recordTable6 tr:first")
			.css("background", "#f5f7f8");

	$(".dataTableWrap").find(".dataShow").eq(0).show();
	$(".exchangeType li").click(
			function() {

				var index1 = $(".exchangeType").find("li").index($(this));

				$(".dataTableWrap").find(".dataShow").eq(index1)
						.slideDown(1000).siblings().hide();

			});

	$(".exchangeType2 li").click(
			function() {

				var index1 = $(".exchangeType2").find("li").index($(this));

				$(".dataTableWrap").find(".dataShow").eq(index1).show()
						.siblings().hide();

			});

	$("#myPrize tr:first,#myCashCoupon tr:first").css("background-color",
			"#f5f7f8").children("td").css("border-top", "none");

	// $(".gift").find(".giftControl").eq(0).show();

	$(".friendManage li").eq(0).css({
		background : "#0065b4",
		color : "#fff"
	});

	$(".giftManage li,.friendManage li").click(function() {

		var index = $(".giftManage").find("li").index($(this));
		$(this).css({
			background : "#0065b4",
			color : "#fff"
		}).siblings().css({
			background : "#f5f7f8",
			color : "#444"
		})

		$(".gift").find(".giftControl").eq(index).show().siblings().hide();

	});

	$(".firends").find(".friend").eq(0).show();

	$(".friendManage li").click(function() {

		var index = $(".friendManage").find("li").index($(this));
		$(this).css({
			background : "#0065b4",
			color : "#fff"
		}).siblings().css({
			background : "#f5f7f8",
			color : "#444"
		})

		$(".firends").find(".friend").eq(index).show().siblings().hide();

		if (index == 1) {
			var searched = $('#searched').val();
			if (searched != 'yes') {
				rechargeAjax(1);
				pagingAjax();
			}
		}

	});

	$(".fenye a").on("click", function() {

		$(this).css({
			background : "#0065b4",
			color : "#fff"
		}).siblings().css({
			background : "#eee",
			color : "#888"
		});

	});

	$(".lastboder").css("border-bottom", "1px solid #ffe4b3");

	$("#myFriend tr:first").find("td").css("border-bottom", "none")

	$(".exchangeType2 li").filter("li:gt(7)").hide();
	$(".showMore").click(function() {

		$(".exchangeType2 li").filter("li:gt(7)").toggle();

	});
	
	$(".msg-box").click(function(){
		window.location.href="/sysMessage/sysMessagePage"; 
	})
	
	$(".gift-box").click(function(){
		window.location.href="/giftbox/boxPage"; 
	})

});

function accountUpfinancia() {
	$.ajax({
		url : '/friend/upfinancia',
		cache : false,
		async : false,
		type : 'get',
		error : function(errorLog) {
			art.dialog({
				content : errorLog.responseText,
				icon : 'warning',
				left : '48%',
				ok : true,
				fixed : true,
				lock : true
			// 为true等价于function(){}
			});
		},
		success : function(result) {
			result = $.parseJSON(result);
			art.dialog({
				icon : 'face-smile',
				left : '48%',
				content : result,
				lock : true,
				ok : function() {
					window.location.href = "/user/account";
				},
				cancelVal : '关闭'
			});

		},
		contentType : "application/x-www-form-urlencoded;charset=UTF-8"
	});
}

//选择头像
function upHeadPic(pic){
	if(pic!= null && pic != '' && pic.length>0){
		$.post('/user/headPic/'+pic,function(data){
			if(data.success){
				location.href.reload();
			}else{
				 art.dialog({
						content : data.msg,
						ok : true,
						lock : true,
						icon : 'error'
					});
			}
		});
	}
}
