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
			
	
	$('#ui-checkbox').hcheckbox();
	$('#radiolist').hradio();
	$('#btnOK').click(function(){
		var checkedValues = new Array();
		$('#chklist :checkbox').each(function(){
			if($(this).is(':checked'))
			{
				checkedValues.push($(this).val());
			}
		});

		alert(checkedValues.join(','));
		alert($('#radiolist :checked').val());
	})
	
	
	
 jQuery.validator.addMethod("isMobile", function(value, element) {       
    var length = value.length;   
     var mobile = /^0?(13|15|18)[0-9]{9}$/;   
     return this.optional(element) || (length == 11 && mobile.test(value));       
 }, "");   
	  
	

	

	
  $("#signupForm").validate({
	  
	  	submitHandler: function (form){
			
			$.ajax({
				url:"#",
				type:"POST",
				data:{"username":$(form).find("#username").val(),"password":$(form).find("#password").val(),"confirm_password":$(form).find("#confirm_password").val(),"introducer":$(form).find("#introducer").val()},
				dataType:"json",
				success:function(data){
					
					if(data.result){
						
						}else if(data.message){
							
							
						}else{
							
							
					    }
										
					}
			 });
			
	
			
			},

 rules: {
  username:{
	 required:true,
	 minlength: 6,
	 maxlength:16,
	 remote:{
　　     type:"POST",
　　     url:"#",
        dataType:"json", 
　　     data:{
　　            "username":function(){return $("#username").val();}
　　          } 
         }
	
    },

   password: {
    required: true,
    minlength: 6,
	maxlength:16
	
   },
   confirm_password: {
    required: true,
    minlength: 6,
	maxlength:16,
    equalTo: "#password"
   },
   
   introducer:{
	   
	    remote:{ //验证用户名是否存在
　　     type:"POST",
　　     url:"#",
        dataType:"json", 
　　     data:{
　　            "introducer":function(){return $("#introducer").val();}
　　          } 

	 }
	   
	   
	   }
  },
  messages: {
  // firstname: "请输入姓名",
  username: {
    required: "用户名不能为空",
   minlength: "用户名不能少于6个字符",
   maxlength: "用户名不能多于16个字符",
   remote:jQuery.format("用户名已经被注册")
   },

   password: {
    required: "密码不能为空",
    minlength: jQuery.format("密码不能小于{0}个字符")
   },
   confirm_password: {
    required: "请输入确认密码",
    minlength: "确认密码不能小于6个字符",
    equalTo: "两次输入密码不一致"
      },
	  
   introducer:{
	   
	    remote:jQuery.format("介绍人")

	   }

   },
      
	success: function(label) {
    // set &nbsp; as text for IE
    label.html("&nbsp;").addClass("valid");
	
    //label.addClass("checked").html("");

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