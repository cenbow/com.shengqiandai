// JavaScript Document


$(function(){
	
	  $.validator.addMethod( "isChinese",  function (value, element)  {

	  var length = value.length;
	  var isChinese = /^[\u4E00-\u9FA5]{2,20}$/;
         
      return   this .optional(element)  ||(length==2||20) &&isChinese.test(value);       
  } ,  " " );
	
	
	 // 身份证号码验证
  $.validator.addMethod( "isIdCardNo" ,  function (value, element)  {
	 
	  var length = value.length;
	  var idcardNo = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
         
      return   this .optional(element)  ||(length==15||18) &&idcardNo.test(value);       
  } ,  " " );

	
	  $("#realname").validate({
	  
	  	submitHandler: function (form){
			
			//form.submit();
	  		
	  		$.ajax({
				  type: "POST",
				  url: "/user/saveRealName",
				  dataType: "json",
				  data:{'realname':$(form).find("#truename").val(),'cardId':$(form).find("#idcode").val().toUpperCase()},
				  async:false,
				  success:function(data){
					  if(data.success){
						  art.dialog({
								content : '恭喜您已通过实名认证',
								ok : function() {
									location.href = '/user/safeCenter';
								},								
								lock : true,
								icon : 'succeed'
							});
						  
					  }else{
						  art.dialog({
								content : data.msg,
								ok : true,
								lock : true,
								icon : 'error'
							});
					  }
					  
				  },
				  beforeSend:function(XHR){
					  $("#realname input").attr('disabled', 'disabled');
					  $(".tjBtn").val('验证中...');
				  },
				  complete:function(XHR, TS){
					  $("#realname input").removeAttr('disabled');
					  $(".tjBtn").val('提交');
				  }
				});

			},

 rules: {
  realname:{
	 required:true,
	 isChinese:true
	
    },

cardId:{
    required: true,
	isIdCardNo:true,
	remote:{
	　　     type:"POST",
	　　     url:"/verification/checkCardIdCanRegister",
		 dataType:"json", 
	　　     data:{ 
	             "cardId":function(){return $("#idcode").val();}
	　　          } 
	
          }
   
	
   } 

  },
  messages: {
 
  realname: {
    required: "用户名不能为空",
	isChinese:"至少为2个为汉字"
  
  
   },

   cardId:{
    required: "身份证不能为空",
	isIdCardNo:"身份证号码不正确",
	remote:"该身份证号码不可登记" 
   }
   },
      
	success: function(label) {

    label.html("&nbsp;").addClass("valid");
	
   
   } 
	  
    });
	
	
	
	
	$('.ui-form').on('click', ".input_tip",function () {
			$(this).hide().parent().children('input').trigger('focus');
			}).on('focus', 'input',function () {
			$(this).parent().children('.input_tip').hide();
			}).on('blur', 'input',function () {
			if (!this.value) {
			$(this).parent().children('.input_tip').show();
			}
			}).find("input").trigger("focus");
			
	
	
	// 修改登录密码
	
$("#modifyPwd").validate({

  	
	submitHandler: function (form){
		
		form.submit();
		
		},
	
		rules:{
		   oldpassword:{
			    required:true,
			   	remote:{
		　　     type:"POST",
		　　     url:"/user/checkPassword",
				dataType:"json", 
		　　     data:{ 
		             "password":function(){return $("#pwdBefore").val();}
		　　          } 
		
                 }

			   },
			   
		   password:{
			   required:true,
			   minlength:6,
			   maxlength:16
			   },
			   
		   repassword:{
			    required:true,
			    equalTo: "#pwdNew"
			   }

		},
		messages:{
			
		   oldpassword:{
			   required:"请输入原密码",
			   remote:"原密码输入错误" 
			   },
			   
		   password:{
			    required:"请输入新密码", 
			    minlength:"至少输入6位密码",
			    maxlength:"密码不能超过16位"
			   
			   },
			   
		   repassword:{
			   
			    required:"请再次输入新密码", 
                equalTo: "两次输入密码不一致"
				
			   }
	   },
	   
	   success: function(label) {
    
                         label.html("&nbsp;").addClass("valid");
                         $('#errorMsg').html("");
	                            } 
	
	
	});
	
	
	
	
	
	$('#pwdNew').on('keyup',function(){
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
	
	$('#ExpwdNew').on('keyup',function(){
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

	
	// 修改交易密码
	
	$("#modifyExchangePwd").validate({

  	
	submitHandler: function (form){
		
		form.submit();
		
		},
	
		rules:{
		   oldpassword:{
			    required:true,
			   	remote:{
		　　     type:"POST",
		　　     url:"/user/checkPayPassword",
				dataType:"json", 
		　　     data:{ 
		             "payPassword":function(){return $("#ExpwdBefore").val();}
		　　          } 
		
                 }

			   },
			   
			   paypassword:{
			   required:true,
			   minlength:6,
			   maxlength:16
			   },
			   
		    repaypassword:{
			    required:true,
			    equalTo: "#ExpwdNew"
			   }

		},
		messages:{
			
		   oldpassword:{
			   required:"请输入原密码",
			   remote:"原密码输入错误",  
			   },
			   
			   paypassword:{
			    required:"请输入新密码", 
			    minlength:"至少输入6位密码",
			    maxlength:"密码不能超过16位",
			   
			   },
			   
		    repaypassword:{
			    required:"请再次输入新密码", 
                equalTo: "两次输入密码不一致"
				
			   }
	   },
	   
	   success: function(label) {
    
                         label.html("&nbsp;").addClass("valid");
                         $('#errorMsg').html("");
	                            } 

	});
	
	
	
	
		
 jQuery.validator.addMethod("isMobile", function(value, element) {       
    var length = value.length;   
     var mobile = /^0?(13|15|18)[0-9]{9}$/;   
     return this.optional(element) || (length == 11 && mobile.test(value));       
 }, "请输入正确的手机号码");   
	  
	
jQuery.validator.addMethod("checkNum", function(value, element) {
var checkNum = /^[0-9]*$/;
return this.optional(element) || (checkNum.test(value));
}, "只能输入数字");









	// 修改原手机
	
$("#keyInPhone").validate({
	
	
	submitHandler: function (form){
		
		form.submit();
		
		},
	
		rules:{
		    checkCode:{
			   required:true,
			   checkNum:true,
			   minlength:6,
			   maxlength:6,
			   remote:{
			　　     type:"POST",
			　　     url:"/verification/checkVerifyCodeMethod",
					dataType:"json", 
			　　     data:{ 
			             "verifyCode":function(){return $("#checkCode").val();}
			　　          } 
			
	                 }
			   
			   },
			   
		    cardId:{
			    required:true,
				isIdCardNo:true,
				remote:{
				　　     type:"POST",
				　　     url:"/user/checkCardId",
						dataType:"json", 
				　　     data:{ 
				             "cardId":function(){return $("#idcardNo").val();}
				　　          } 
				
		                 }
			  
			   }

		},
		messages:{
		    checkCode:{
			    required:"请输入验证码", 
			    minlength:"至少输入6位数字验证码",
			    maxlength:"验证码不能超过6位数字",
			    remote:"验证码错误"
			   },
			   
		    cardId:{
			    required:"请输入身份证号码",
				isIdCardNo:"请输入正确的身份证号码", 
			    remote:"身份证号码错误或您还没有通过实名认证，请确保您已经通过实名认证"
			   }
	   },
	   
	   success: function(label) {
    
                         label.html("&nbsp;").addClass("valid");
                         $('#errorMsg').html("");
	                            } 
	
	
	
	
	});
	
	
	// KEY IN NEW MOBILE PHONE
	
	
	$("#newPhone").validate({
	
	
	submitHandler: function (form){
		
		form.submit();
		
		},
	
		rules:{
		   phone:{
			    required:true,
				isMobile:true,
			   	remote:{
		　　     type:"POST",
		　　     url:"/checkRegister",
				dataType:"json", 
		　　     data:{ 
		             "value":function(){return $("#mobile").val();}
		　　          } 
		
                 }

			   },
			   
		    vcode:{
			   required:true,
			   checkNum:true,
			   minlength:6,
			   maxlength:6,
			   remote:{
			　　     type:"POST",
			　　      url:"/verification/checkVerifyCodeMethod",
					dataType:"json", 
			　　     data:{ 
			　　    	"verifyCode":function(){return $("#newCheckCode").val();}
			　　          } 
			
	                 }
			   }

		},
		messages:{
			
			phone:{
			   required:"请输入手机号码",
			   isMobile:"请输入正确的手机号码",
			   remote:"该号码已存在",
			   },
			   
		    vcode:{
			    required:"请输入验证码", 
			    minlength:"至少输入6位数字验证码",
			    maxlength:"验证码不能超过6位数字",
			    remote:"验证码错误" 
			   }
	   },
	   
	   success: function(label) {
    
                         label.html("&nbsp;").addClass("valid");
                         $('#errorMsg').html("");
	                            } 
	
	
	
	
	});	
	
	
	
	// EMAIL MODIFY
	
	$("#modifyEmail").validate({
		
		submitHandler: function (form){
		
		form.submit();
		
		},
	
		rules:{
			
			email:{
				required:true,
				email:true,
				remote:{
		　　     type:"POST",
		　　      url:"/system/user/checkUserOldEmail",
				dataType:"json", 
		　　     data:{ 
		             "oldEmail":function(){return $("#oldEmail").val();}
		　　          }
                 }
				
				}
			},
		messages:{
			
			email:{
				required:true,
				email:"请输入正确的邮箱",
				remote:"您的原邮箱填写错误"
				
				}
			
			
			},
	    success: function(label) {
    
             label.html("&nbsp;").addClass("valid");
	         $('#errorMsg').html("");
	                            } 

		          });
	
	
	
	
	
	// NEW EMAIL
	
	
		$("#modifyNewEmail").validate({
		
		submitHandler: function (form){
		
		//form.submit();
			
			$.ajax({
				  type: "POST",
				  url: "/user/newEmailSubmit",
				  dataType: "json",
				  data:{'email':$(form).find("#newEmail").val()},
				  async:false,
				  success:function(data){
					  if(data.success){
						  art.dialog({
								content : '邮箱修改成功，去认证吧！',
								ok : function(){
									 location.href = '/user/checkEmail';
								},
								lock : true,
								icon : 'succeed'
							});
					  }else{
						  art.dialog({
								content : data.msg,
								ok : true,
								lock : true,
								icon : 'error'
							});
					  }
				  },
				  beforeSend:function(XHR){
					  $("#modifyNewEmail input").attr('disabled', true);
				  },
				  complete:function(XHR, TS){
					  $("#modifyNewEmail input").attr('disabled', false);
				  }
				});
		
		},
	
		rules:{			
			email:{
				required:true,
				email:true,
				remote:{
				　　     type:"POST",
				　　     url:"/checkRegister",
						dataType:"json", 
				　　     data:{ 
				             "value":function(){return $("#newEmail").val();}
				　　          }
		                 }
				
				}
			
			
			},
		messages:{
			
			email:{
				required:"请输入邮箱，不能为空",
				email:"请输入正确的邮箱",
			    remote:"该邮箱已注册"
				}
			
			
			},
	    success: function(label) {
    
             label.html("&nbsp;").addClass("valid");
             $('#errorMsg').html("");
	                            } 

		          });
		
		
		
		// EMAIL ADD
		
		$("#emailAdd").validate({
	
	submitHandler: function (form){
	
	   //form.submit();
		$.ajax({
			  type: "POST",
			  url: "/user/checkEmailSubmit",
			  dataType: "json",
			  data:{'email':$(form).find("#email_renzheng").val()},
			  async:false,
			  success:function(data){
				  if(data.success){
					  art.dialog({
							content : '邮箱添加成功，系统已向您的邮箱发送了验证邮件，请注意查收！',
							ok : function(){
								 location.href = '/user/sendedVerifyEmail';
							},
							lock : true,
							icon : 'succeed'
						});
				  }else{
					  art.dialog({
							content : data.msg,
							ok : true,
							lock : true,
							icon : 'error'
						});
				  }
			  },
			  beforeSend:function(XHR){
				  $("#emailAdd input").attr('disabled', true);
			  },
			  complete:function(XHR, TS){
				  $("#emailAdd input").attr('disabled', false);
			  }
			});
		
	
	},

	rules:{
		
		email_renzheng:{
			required:true,
			email:true,
			remote:{
			　　     type:"POST",
			　　     url:"/checkRegister",
					dataType:"json", 
			　　     data:{ 
			             "value":function(){return $("#email_renzheng").val();}
			　　          }
	                 }

			
			}
		
		
		},
	messages:{
		
		email_renzheng:{
			required:"请输入邮箱，不能为空",
			email:"请输入正确的邮箱",
			remote:"该邮箱已注册"
			
			}
		
		
		},
    success: function(label) {

         label.html("&nbsp;").addClass("valid");
         $('#errorMsg').html("");
                            } 

	          });
	
	
	
	
	// EMAIL VERIFY
	
			$("#emailVerify").validate({
		
		submitHandler: function (form){
		
		//form.submit();
			$.ajax({
				  type: "POST",
				  url: "/user/checkEmailSubmit",
				  dataType: "json",
				  data:{'email':$(form).find("#email_renzheng").val()},
				  async:false,
				  success:function(data){
					  if(data.success){
						  location.href = '/user/sendedVerifyEmail';
					  }else{
						  $('#errorMsg').html("");
						  $('#errorMsg').html(data.msg);
					  }
					  
				  },
				  beforeSend:function(XHR){
					  $("#emailAdd input").attr('disabled', true);
				  },
				  complete:function(XHR, TS){
					  $("#emailAdd input").attr('disabled', false);
				  }
				});
		
		}

		          });
	
	
	})