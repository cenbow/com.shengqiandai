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
	href="${pageContext.request.contextPath}/css/cjd-regFirst.css" />
<script type="text/javascript">
	var rootPath = '${pageContext.request.contextPath}';
</script>
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script
	src="${pageContext.request.contextPath}/js/getCodeForRegister.js"></script>
		<!-- 弹出层JS -->
<script
	src="${pageContext.request.contextPath}/js/artDialog/artDialog.js?skin=default"></script>
</head>
<body>
	<div class="total">
		<div class="left-side fl">
			<div class="left-side-top">
				<div class="logo-combo">
					<ul>
						<li class="logo-pic logo1"></li>
						<li class="logo-pic logo2"></li>
					</ul>
				</div>
				<div class="data-transfer">
					<h3>投资预定</h3>
					<p>您在财经道填写的信息已提交至微积金，请在继续完善信息，以便顺利投资</p>
					<p>微积金优质项目。</p>

				</div>
			</div>
			<div class="left-side-bottom">
				<h2>预定信息</h2>
				<table width="542" cellpadding="0" cellspacing="0">
					<tr>
						<td width="90" height="40" class="text-right">产品名称：</td>
						<td width="432">${b.name}</td>
					</tr>
					<tr>
						<td width="90" height="40" class="text-right">发行机构：</td>
						<td width="432">微积金</td>
					</tr>
					<tr>
						<td width="90" height="40" class="text-right">借款金额：</td>
						<td width="432">${b.account}元</td>
					</tr>
					<tr>
						<td width="90" height="40" class="text-right">预期纯收益：</td>
						<td width="432">${b.expectApr}%</td>
					</tr>
					<tr>
						<td width="90" height="40" class="text-right">借款期限：</td>
						<td width="432">${b.timeLimit}个月</td>
					</tr>
					<tr>
						<td width="90" height="40" class="text-right">投资金额：</td>
						<td width="432">${b.lowestAccount}元起</td>
					</tr>
					<tr>
						<td width="90" height="40" class="text-right">还款方式：</td>
						<td width="432">${b.style==0?'等额本息':(b.style==1?'等本等息':(b.style==2?'到期还款':'先息后本'))}
						</td>
					</tr>
					<tr>
						<td width="90" height="40" class="text-right">还需金额：</td>
						<td width="432">${b.account-b.accountYes}元</td>
					</tr>
				</table>

			</div>
		</div>
		<div class="right-side fr">

			<div class="login">
				<div class="login-left fl"></div>
				<div class="login-right fr">
					<div class="reg-bg"></div>
					<div class="reg-tip"></div>
					<form action="#" method="post" id="regForm" class="ui-form">
						<div class="ui-form-item">
							<label class="ui-label">用户名：</label> <input type="text"
								id="username" class="ui-input" name="username"
								value="${reg.uaccount}" /> <input type="hidden"
								name="thirdproductid" id="thirdproductid"
								value="${reg.thirdproductid}" /> <input type="hidden"
								id="uaccount" name="uaccount" value="${reg.uaccount}" />
							<p>请输入6-16位字符.英文,数字</p>
						</div>

						<div class="ui-form-item">
							<label class="ui-label">密码：</label> <input type="password"
								id="password" class="ui-input" name="password" />
							<p>请输入6到20位密码,建议英文、数字的组合</p>
						</div>

						<div class="ui-form-item">
							<label class="ui-label">确认密码：</label> <input type="password"
								id="password2" class="ui-input" name="password2" />
							<p>请再次输入密码</p>
						</div>

						<div class="ui-form-item">
							<label class="ui-label">手机号：</label> <input type="text"
								id="mobile" class="ui-input" name="mobile" value="${reg.phone}"
								readonly="readonly" />
						</div>

						<div class="ui-form-item">
							<span style="display: block;" class="clear"> <a
								href="javascript:phoneCode();" id="phoneCode" class="msg-btn">短信获取</a>
								<a href="javascript:;" id="voiceCode" class="voice-btn"
								style="background-color: #EEEEEE">语音获取</a></span> <span id="waitMsg"
								style="line-height: 22px; display: block; font-size: 12px;"></span>
						</div>


						<div class="ui-form-item">
							<label class="ui-label">手机验证码：</label> <input type="text"
								id="code" class="ui-input" name="code" maxlength="6" />
						</div>

						<div class="ui-form-item">
							<input type="checkbox" checked id="agree-check" />
							<a id="agreement" style="cursor:pointer;" target="_blank">同意个人会员注册协议</a>
						</div>

						<div class="ui-form-item" style="padding-left: 0;">
							<a href="#" class="btn reg-btn" onClick="regCheck()">注册</a>
						</div>

					</form>
				</div>
			</div>

		</div>
	</div>

	<script type="text/javascript">
	$(function(){
		$('#agreement').click(function(){
			$.ajax({
				url:"/utilpage/toPageAjax/regAgreement",
				type : "post",
				dataType:"text",
				success:function(result) {
					art.dialog({
						title : '微积金网站服务协议',
						content : result,
						width : 1000,
						height :500,
						fixed : false,
						drag: false,
						lock : true,
					});
				}
		　　 });
			
		});
	})
	function phoneCode(){
		$.ajax({
			url:"/checkRegisterUserName",
			type : "post",
			dataType:"json",
			data:{
	　　           	"value":$("#username").val()
	　　           },
			success:function(result) {
				if(result){
					getPhoneCode('mobile');
				}
			}
	　　 });
	}
	function regCheck(){
		 var username = $("#username").val();
		 var usernameRgExp= "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";
			if(username==null||username==""){
				$(".reg-tip").html("*用户名不能为空");
				$("#username").focus();
				return false;
			  }else if(username.match(usernameRgExp)){
    		    $(".reg-tip").html("*用户名不能为汉字");
				$("#username").focus();
				return false;  
			  }else if(username.length<6||username.length>16){
				$(".reg-tip").html("*用户名应为6-16位字符");
				$("#username").focus();
				return false; 
			  }else{
				 $(".reg-tip").html(""); 
			  }
			  
			var password = $("#password").val();
			
			if(password==null||password==""){
				$(".reg-tip").html("*密码不能为空");
				$("#password").focus();
				return false;
			  }else if(password.length<6||password.length>20){
				$(".reg-tip").html("*密码应为6-20位");
				$("#password").focus();
				return false; 
			  }else{
				$(".reg-tip").html(""); 
			  }
			
			
			var password2 = $("#password2").val();
			   if(password2==null||password2==""){
				   $(".reg-tip").html("*请再次输入密码"); 
				   $("#password2").focus();
				   return false;	   
				   
				   }else if(password!=password2){
				   $(".reg-tip").html("*两次密码不一致"); 
				   $("#password2").focus();
				   return false;
				 }else{
				   $(".reg-tip").html(""); 
			     }

			var phone = $("#mobile").val();
			var mobileRgExp = /^0?(13|15|14|18)[0-9]{9}$/; 
			
			if(phone==null||phone==""){
			   $(".reg-tip").html("*手机号码不能为空"); 
			       $("#phone").focus();
				   return false;
				 }else if(!phone.match(mobileRgExp)){
				   $(".reg-tip").html("*手机号码不正确"); 
			       $("#phone").focus();
				   return false;				 
					 
				 }else{
				   $(".reg-tip").html(""); 
			     }	
			
			var code = $("#code").val();
			if(code==null||code==""){
			      $(".reg-tip").html("*验证码不能为空"); 
			      $("#code").focus();
				 	return false;
			 }else{
			 	$(".reg-tip").html(""); 
		     }
			
			if(!$("#agree-check").is(":checked")){
			      $(".reg-tip").html("*请勾选注册协议"); 
				  return false;
			 } else {
			 	$(".reg-tip").html(""); 
		     }
		 	$.ajax({
				url:"/cjdao/handleRegFromCjd",
				type:"post",
				data:{
					"username":username,
					"password":password,
					"password2":password2,
					"phone":phone,
					"vcode":code,
					"uaccount":$('#uaccount').val(),
					"type":2,
					"thirdproductid":$("#thirdproductid").val()
				},
				success: function(result){
					result = $.parseJSON(result);
					if(result.success){
						location.href = result.msg;
					} else {
						$(".reg-tip").html(result.msg);
					}
				},
				error:function(results){}
			});
		 }
</script>
</body>
</html>
