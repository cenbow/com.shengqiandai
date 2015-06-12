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
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>

<script src="${pageContext.request.contextPath}/js/pswLevel.js"></script>

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
  

 
  $("#bindForm").validate({
	  
	  
	  submitHandler: function (form){
		  form.submit();
		  },

	   rules:{
		  username:{
			  required:true,
			  minlength:6,
			  maxlength:16,
			  remote:{ // 验证用户名是否存在
			　　     type:"GET",
			　　     url:"/checkIntroducer",
			        dataType:"json", 
			　　     data:{
			　　            "introducer":function(){return $("#username").val();}
			　　          } 
				 }
			  
			  
			  },
		  password:{
			  
	          required:true,
			  minlength:6,
			  maxlength:20
			   
			  }
		  },
	   messages:{

          username:{
			  required:"用户名不能为空",
			  minlength:"用户名不能少于6位",
			  maxlength:"用户名不能多于16位",
			  remote:"用户不存在"
			  },

        
          password:{
			  required:"密码不能为空",
			  minlength:"密码不能少于6位",
			  maxlength:"密码不能多于20位",
			  }

		   },
		   
		   
	      success:"valid"
		  
	  
	  
	  
	  
	  
	  });

  
  });

</script>
</head>
<body>

	<!-------------头部-------------------->
	<jsp:include page="top.jsp"></jsp:include>
	<!--中间-->

	<div class="register" style="height: auto;">

		<div class="reg_content" style="height: auto;">

			<div class="reg_logo">
				<h1 class="reg_logo_pic">绑定账号</h1>
				<a href="#" class="go_login"></a>

				<c:if test="${not empty qqNickname }">
					<span
						style="position: absolute; top: 22px; right: 10px; display: block; font-size: 14px;">欢迎：${qqNickname }，使用QQ登录</span>
				</c:if>
			</div>
			<div class="reg_conditions" style="height: auto;">





				<div class="form_validator" style="height: auto;">

					<form
						action="${pageContext.request.contextPath}/thirdLogin/thirdBindUser"
						method="post" class="ui-form" id="bindForm">
						<fieldset>
							<div class="ui-form-item">

								<label class="ui-label"> <span class="ui-form-required"></span>
									用户名：
								</label> <input type="text" class="ui-input pl60 us_bg" name="username"
									id="username" value="${value }" />
								<p class="input_tip">6-16位字符，数字，字母组成</p>

							</div>


							<div class="ui-form-item">

								<label class="ui-label"> <span class="ui-form-required"></span>
									密码：
								</label> <input type="password" class="ui-input pl60 us_bg"
									name="password" id="password" value="${password }" />

								<p class="input_tip">请输入密码（6-20位字符）</p>

							</div>

							<div class="ui-form-item" style="height: 20px;">
								<div id="errorMsg"
									style="position: absolute; top: 0; left: 170px; color: #ff5555;">${errorMsg }</div>
							</div>





							<div class="ui-form-item">

								<input type="submit" class="submit_btn" hidefocus="true"
									value="提交">

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
