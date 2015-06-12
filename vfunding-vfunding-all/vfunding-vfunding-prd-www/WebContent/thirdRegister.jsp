<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="微积金(www.vfunding.cn) 整合了民营汽车借贷机构、二手车商、第三方汽车服务机构等传统领域企业，形成了独特的针对汽车提供投融资对接服务的创新型O2O互联网汽车金融服务模式。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、汽车贷款等。" />
<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|微贷|红包|基金|微基金|小额贷款|微财富|宜车贷|二手车抵押|车贷|托管" />
<title>微积金，中国领先的互联网汽车金融服务平台_理财_理财产品_投融资_理财需求_投资_投资标的_财富_财富成长_手机理财_微信理财_微理财</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/ui-form.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/qqReg.css" />
<script type="text/javascript">
	var rootPath = '${pageContext.request.contextPath}';
</script>

<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/pswLevel.js"></script>
<script src="${pageContext.request.contextPath}/js/getCodeForRegister.js"></script>

<script>
   
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
			
	  
	 
	 //validate
	 $.validator.addMethod("isMobile", function(value, element) {       
    var length = value.length;   
     var mobile = /^0?(13|15|18)[0-9]{9}$/;   
     return this.optional(element) || (length == 11 && mobile.test(value));       
 }, "");   
	  
	
	 
	 $("#qqRegForm").validate({
		  
		  
		  submitHandler: function (form){
			  form.submit();
			  },

		   rules:{
			   userName:{
				  required:true,
				  minlength:6,
				  maxlength:16,
				  remote:{
		　　       type:"POST",
			　　      url:"/checkRegisterUserName",
					dataType:"json", 
			　　     data:{
			　　            "value":function(){return $("#userName").val();}
			　　          } 
				 }
				  
				  
				  },
		  password:{
				  
		          required:true,
				  minlength:6,
				  maxlength:20
				   
				  },
		  rePassword:{
                     required:true,
					 equalTo: "#password"
					},  
				  
		    mobile:{
					  
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
				  remote:{
		　　       type:"POST",
			　　     url:"/verification/checkVerifyCodeMethod",
					dataType:"json", 
			　　     data:{
			　　          "verifyCode":function(){return $("#vcode").val();}
			　　          } 
				 }
				  
				  
				  }
			  },
		   messages:{

			   userName:{
				  required:"用户名不能为空",
				  minlength:"用户名不能少于6位",
				  maxlength:"用户名不能多于16位",
				  remote:"用户名已存在"
				  },

           
             password:{
				  required:"密码不能为空",
				  minlength:"密码不能少于6位",
				  maxlength:"密码不能多于20位",
				  },

		     rePassword:{
                    required:"密码不能为空",
					 equalTo: "两次密码不一致"
					},

			mobile:{
					  
					  required:"手机号码不能为空",
					  isMobile:"手机格式不正确",
					  remote:"手机号码已存在"
					  
					  },
          vcode:{
			    required:"验证码不能为空",
				remote:"验证码不正确"
			   
			   }

			   },
			   
			   
		   success:"valid"

		  
		  });
	  
	  
	  
	  
	//pwdLevel
	
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

	  });

</script>
</head>
<body>

	<!-------------头部-------------------->
	<jsp:include page="top.jsp"></jsp:include>
	<!--中间-->


	<div class="register">

		<div class="reg_content">

			<div class="reg_logo">
				<h1 class="reg_logo_pic">设置账号</h1>
				<c:choose>
					<c:when test="${not empty qqNickname }">
						<a
							href="${pageContext.request.contextPath}/thirdLogin/thirdBind?qqNickname=${qqNickname }"
							class="go_login">已有账号，点击绑定</a>

						<span
							style="position: absolute; top: 22px; right: 10px; display: block; font-size: 14px;">欢迎：${qqNickname }，使用QQ登录</span>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/thirdLogin/thirdBind"
							class="go_login">已有账号，点击绑定</a>
					</c:otherwise>
				</c:choose>

			</div>
			<div class="reg_conditions">

				<div class="form_validator">

					<form action="${pageContext.request.contextPath}/thirdRegister"
						method="post" class="ui-form" id="qqRegForm">
						<fieldset>
							<div class="ui-form-item">

								<label class="ui-label"> <span class="ui-form-required"></span>
									用户名：
								</label> <input type="text" class="ui-input pl60 us_bg" name="userName"
									id="userName" value="${register.userName }" />
								<p class="input_tip">6-16位字符，数字，字母组成</p>

							</div>


							<div class="ui-form-item">

								<label class="ui-label"> <span class="ui-form-required"></span>
									密码：
								</label> <input type="password" class="ui-input pl60 us_bg"
									name="password" id="password" value="${register.password }" />
								<div class="psw-streng">
									<div></div>
								</div>
								<p class="input_tip">请输入密码（6-20位字符）</p>

							</div>

							<div class="ui-form-item">

								<label class="ui-label"> <span class="ui-form-required"></span>
									确认密码：
								</label> <input type="password" class="ui-input pl60 us_bg"
									name="rePassword" id="rePassword"
									value="${register.rePassword }" />
								<p class="input_tip">请再次输入密码</p>

							</div>

							<div class="ui-form-item">
								<label class="ui-label"> <span class="ui-form-required"></span>
									手机号码：
								</label> <input type="text" class="ui-input pl60 us_bg" name="mobile"
									id="mobile" value="${register.mobile }" />
								<p class="input_tip">请输入手机号码</p>

							</div>




							<div class="ui-form-item" style="height: 30px;">

								<label class="ui-label"> <span class="ui-form-required"></span>
									验证码：
								</label> <input type="text" name="vcode" class="ui-input" id="vcode"
									value="${register.vcode }">

								<p class="input_tip" id="input_tip">请输入验证码</p>

							</div>

							<div class="ui-form-item" style="height: 20px;">
								<div id="errorMsg"
									style="position: absolute; top: 0; left: 170px; color: #ff5555;">${verificationErrorMsg }</div>
							</div>

							<div class="ui-form-item" style="height: 20px;">
								<div id="waitMsg"
									style="position: absolute; top: 8px; left: 170px; color: #888888;"></div>
							</div>

							<div class="ui-form-item"
								style="height: 30px; margin-bottom: 20px;">

								<a href="javascript:getPhoneCode('mobile');" id="phoneCode"
									class="getPhoneCode">短信获取</a> <a href="javascript:;"
									id="voiceCode" class="getVoiceCode"
									style="background-color: #EEEEEE">语音获取</a>
							</div>

							<!--  <div class="ui-form-item">

								<a class="yzmPic" href="#"></a> <a class="voiceyzmPic" href="#"></a>


							</div>-->



							<div class="ui-form-item h40">
								<span class="ui-checkbox" id="ui-checkbox"> <input
									type="checkbox" id="tongyi" checked /><label>
										&nbsp;我已阅读并且同意<a href="#" target="_blank"> 《微积金协议》 </a>
								</label>
								</span>

							</div>


							<div class="ui-form-item">

								<input type="submit" class="submit_btn" hidefocus="true"
									value="免费注册">

							</div>

						</fieldset>

					</form>

				</div>

			</div>
		</div>
	</div>
	<!------尾部------>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
