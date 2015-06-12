// JavaScript Document

;(function($){
		
	  $.fn.reg_tip = function(){
	var obj = $(this);
	obj.each(function(){
		var thisValue = obj.val();
		 //判断文本框的值是否为空，有值的情况就隐藏提示语，没有值就显示
	 if(thisValue!=""){
       $(this).siblings("span").hide();
      }else{
       $(this).siblings("span").show();
      }
		
	 $(this).keyup(function(){
       var thisValue=$(this).val();
       $(this).siblings("span").hide();
      }).blur(function(){
        var thisValue=$(this).val();
        if(thisValue!=""){
         $(this).siblings("span").hide();
        }else{
         $(this).siblings("span").show();
        }
       })
		
	});  
}			
	
			
			
			
			

	       
	
	 
		
		
	
	
	
	
	
	
	})(jQuery)