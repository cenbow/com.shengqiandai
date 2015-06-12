$(function(){

	$("#emailModify").validate({

submitHandler: function (form){
	$.ajax({
		  type: "POST",
		  url: "/user/modifyEmail",
		  dataType: "json",
		  data:{'email':$(form).find("#email_renzheng").val()},
		  async:false,
		  success:function(data){
			  if(data.success){
				  art.dialog({
						content : '邮箱修改成功，系统已向您的邮箱发送了验证邮件，请注意查收！',
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
			  $("#emailModify input").attr('disabled', true);
		  },
		  complete:function(XHR, TS){
			  $("#emailModify input").attr('disabled', false);
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
	
});