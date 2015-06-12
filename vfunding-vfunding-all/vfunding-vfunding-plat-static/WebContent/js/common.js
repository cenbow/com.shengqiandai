// JavaScript Document
	$(function(){
		$("#kinMaxShow").kinMaxShow({
			height:300,
			button:{
            normal:{
	        background:'url(http://static.vfunding.cn/images/button.png) no-repeat -14px 0',marginRight:'8px',border:'0',right:'44%'},
            focus:{background:'url(http://static.vfunding.cn/images/button.png) no-repeat 0 0',border:'0'}
			       }
			
	   });
	   
	   $(".msb_main").mouseover(function(){
		   
		   
		   $(this).find("img").attr("src","http://static.vfunding.cn/images/click_show_hover.png");
		   
		   
		   }).mouseout(function(){
			   
			   
			     $(this).find("img").attr("src","http://static.vfunding.cn/images/click_show.png");
			   
		  });	
	   
	
	$(".sub_list:last").css("border-bottom","none");

	
		$(window).scroll(function() {
			if ($(window).scrollTop() > 600) {
				$('#float_box li:eq(0)').fadeIn(1000);
			} else {
				$('#float_box li:eq(0)').fadeOut(800);
			}
		});
		
		$("#top").click(function() {
			$('body,html').animate({
				scrollTop: 0
			},
			1000);
			return false;
		});
		
		
	});
