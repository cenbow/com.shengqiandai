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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ui-form.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/findPwd.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script type="text/javascript">
	function changeImage() {
		$("#authCodeImg").attr(
			{
				"src" : "${pageContext.request.contextPath }/verification/getGenImage/68/32?id="
						+ Math.random() * 1000,
				"alt" : "看不清，换一张"
			});
	}

	 $(function(){
		$("#checkUsernameForm").validate({
			submitHandler: function (form){
			   form.submit();
			},
		rules:{
			   value:{
				required:true,
				remote:{
		　　     type:"POST",
		　　     url:"${pageContext.request.contextPath }/verification/checkValueForFindPwd",
				dataType:"json", 
		　　     data:{
		　　            "value":function(){return $("#user-name").val();}
		　　          } 
		
			 }
				
				
			 },
			vcode:{
				
			    required:true,
				remote:{
		　　     type:"POST",
		　　     url:"${pageContext.request.contextPath }/verification/checkVerifyCodeMethod",
				dataType:"json", 
		　　     data:{
		　　            "verifyCode":function(){return $("#input-yzm").val();
		             }
	             } 
		
			 }
	     }
     },
		messages:{
			value:{
				  required:"用户名不能为空",
				 
				  remote:"用户信息错误"
				},
			vcode:{
				required:"验证码不能为空",
				  remote:"验证码不正确"
				}
			},
		success: function(label) {
            label.html("&nbsp;").addClass("valid");
	     } 
	  
	  
			
			
			
			
			
			}); 
		 
		 
		 
		 
		 })
</script>
</head>

<body>
	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>

	<!--中间-->

	<div class="findPwd  box-shadow">
		<div class="b-line">
			<h2>找回密码</h2>
			<div class="h-line"></div>
		</div>

		<div class="find-steps">
			<ol class="reg_step">
				<li class="step1"><span class="step_msg"> <i
						class="icons dot24">1</i> <span class="whiteColor">输入账户名</span>
				</span></li>
				<li class="step2"><span class="step_msg"> <i
						class="icons dot24">2</i> 验证账户名
				</span></li>


				<li class="step3"><span class="step_msg"> <i
						class="icons dot24">3</i> 重置密码
				</span></li>



				<li class="step4"><span class="step_msg"> <i
						class="icons dot24">4</i> 完成
				</span></li>
			</ol>

		</div>


		<div class="check-username">

			<form action="${pageContext.request.contextPath }/user/findPwdSecond"
				method="post" id="checkUsernameForm" class="ui-form">

				<div class=" ui-form-item">

					<label class="ui-label"> <span class="ui-form-required">*</span>
						用户名：
					</label> <input type="text" class="ui-input" name="value" id="user-name" />
					<p class="forget-username">忘记用户名？就用手机和邮箱</p>

				</div>


				<div class="ui-form-item" style="margin-top: 40px;">

					<label class="ui-label"> <span class="ui-form-required">*</span>
						验证码：
					</label> <input type="text" id="input-yzm" class="ui-input" name="vcode" />

				</div>

				<div class="ui-form-item" style="margin-top: 0px;">

					<a href="javascript:changeImage();"
						style="width: 68px; height: 32px;margin-left: 0px;" class="yzmPic"> <img
						id="authCodeImg" alt="看不清?换一张"
						src="${pageContext.request.contextPath }/verification/getGenImage/68/36">
					</a>



				</div>

				<div class="ui-form-item" style="margin-top: 0px;">${errorMsg }
				</div>

				<div class="ui-form-item" style="margin-top: 20px;">


					<input type="submit" class="btn116" value="下一步" />


				</div>


			</form>


		</div>

	</div>
	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
</body>
</html>


