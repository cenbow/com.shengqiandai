// JavaScript Document

$(function(){	   
	$('.ui-form').on('click', ".input_tip",function () {
			$(this).hide().parent().children('input').trigger('focus');
			}).on('focus', 'input',function () {
			$(this).parent().children('.input_tip').hide();
			}).on('blur', 'input',function () {
			if (!this.value) {
			$(this).parent().children('.input_tip').show();
			}
			}).find("input").trigger("focus");
			
	
	$('#tongyi').click( function () {
       if(!$(this).attr('checked')){
    	   $('.submit_btn').attr('disabled', 'disabled').css({background:"#ccc",color:"#fff"});
       }  else{
    	   $('.submit_btn').css("background","");
    	   $('.submit_btn').removeAttr('disabled').css("background","#ff86361");
       }
	});
	$('#btnOK').click(function(){
		var checkedValues = new Array();
		$('#chklist :checkbox').each(function(){
			if($(this).is(':checked'))
			{
				checkedValues.push($(this).val());
			}
		});

	})
	

	         
 $.validator.addMethod("isMobile", function(value, element) {       
    var length = value.length;   
     var mobile = /^0?(13|15|18)[0-9]{9}$/;   
     return this.optional(element) || (length == 11 && mobile.test(value));       
 }, "");   
	  
	
	 $.validator.addMethod("isChinese", function(value, element) {       
		    var length = value.length;   
		     var isChinese = /^[\w\s]+$/;   
		     return this.optional(element) || (isChinese.test(value));       
		 }, "");   
			
	

	
  $("#signupForm").validate({
	  
	  	submitHandler: function (form){
	  		   form.submit();
			},

 rules: {
	 userName:{
	 required:true,
	 minlength: 6,
	 maxlength:12,
	 isChinese:true,
	 remote:{ // 验证用户名是否存在
　　     type:"POST",
　　     url:"/checkRegisterUserName",
        dataType:"json", 
　　     data:{
　　            "value":function(){return $("#userName").val();}
　　          } 
	 }
	
    },

   password: {
    required: true,
    minlength: 6,
	maxlength:16
	
   },
   rePassword: {
    required: true,
    equalTo: "#password"
   },
   
   introducer:{
	   
	    remote:{ // 验证用户名是否存在
　　     type:"GET",
　　     url:"/checkIntroducer",
        dataType:"json", 
　　     data:{
　　            "introducer":function(){return $("#introducer").val();}
　　          } 
	 }
	   
	   
	   }
  },
  messages: {
  
  userName: {
    required: "用户名不能为空",
    minlength: "用户名不能少于6个字符",
    maxlength: "用户名不能多于12个字符",
    isChinese:"用户名不能为中文/特殊字符",
    remote:jQuery.format("用户名已经被注册")
   },

   password: {
    required: "密码不能为空",
    minlength: jQuery.format("密码不能小于{0}个字符"),
    maxlength: jQuery.format("密码不能多于{0}个字符")
   },
   rePassword: {
    required: "请输入确认密码",
    equalTo: "两次输入密码不一致"
      },
	  
   introducer:{
	    remote:jQuery.format("介绍人信息有误")
	   }

   },
      
	success: function(label) {
    label.html("&nbsp;").addClass("valid");
   } 
	  
    }); 
	
$('#password').on('keyup',function(){
		var $range = $('.psw-streng div');	
		var v = $(this).val();
		var res=pswLevel(v);
		switch (res.level){
		case '强': $range.removeClass().addClass('high');break;
		case '中': $range.removeClass().addClass('mid');break;
		case '弱': $range.removeClass().addClass('low');break;
		}
		$range.show();
		}).on('blur',function(){
		 
		});

	})