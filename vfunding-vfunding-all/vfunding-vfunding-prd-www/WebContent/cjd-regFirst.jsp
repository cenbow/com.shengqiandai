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
	href="${pageContext.request.contextPath}/css/cjd-regSecond.css" />
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
		<!--头部-->
		<div class="top clear">
			<div class="top-left fl">

				<ul>
					<li class="logo-pic logo1"></li>
					<li class="logo-pic logo2"></li>
				</ul>
			</div>
			<div class="top-right fr">
				<h3>投资预定</h3>
				<p>您在财经道填写的信息已提交至微积金，请在继续完善信息，以便顺利投资微积金优质项目。</p>
			</div>
		</div>
		<!--中间-->
		<div class="content">
			<div class="content-top">
				<img src="${pageContext.request.contextPath}/images/cjd/reg.png" />
			</div>
			<div class="reg-tip"></div>
			<div class="reg-div">
				<form class="ui-form"
					action="${pageContext.request.contextPath}/cjdao/handleRegFromCjd"
					method="post">
					<div class="ui-form-item">
						<label class="ui-label">用户名：</label> <input type="text"
							id="username" name="username" value="${reg.uaccount}" /> <input
							type="hidden" id="uaccount" name="uaccount"
							value="${reg.uaccount}" /> <span>请输入6-16位字符.英文,数字</span>
					</div>

					<div class="ui-form-item">
						<label class="ui-label">密码：</label> <input type="password"
							id="password" name="password" /> <span>请输入6到20位密码,建议英文+数字</span>
					</div>
					<div class="ui-form-item">
						<label class="ui-label">确认密码：</label> <input type="password"
							id="password2" name="password2" /> <span>请再次输入密码</span>
					</div>


					<div class="ui-form-item">
						<label class="ui-label">手机号：</label> <input type="text"
							id="mobile" name="mobile" value="${reg.phone}"
							readonly="readonly" /> <span>请输入手机号码</span>
					</div>

					<div class="ui-form-item">
						<a href="javascript:phoneCode();" id="phoneCode" class="msg-btn">短信获取</a>
						<a href="javascript:;" id="voiceCode" class="voice-btn"
							style="background-color: #EEEEEE">语音获取</a> <span id="waitMsg"
							style="line-height: 35px;"></span>
					</div>
					<div class="ui-form-item">
						<label class="ui-label">手机验证码：</label> <input type="text"
							id="phone-code" name="phoneCode" size="6" maxlength="6" /> <span>请输入手机验证码</span>
					</div>

					<div class="ui-form-item">
						<input type="checkbox" id="agree" checked />
						<a id="agreement" style="cursor:pointer;" target="_blank">同意个人会员注册协议</a>
					<div class="ui-form-item">
						<a href="#" class="btn reg-btn" id="sub">注&nbsp; 册</a>
					</div>
				</form>
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
		$(function() {
			$("#sub").click(function(ev) {
				ev.stopPropagation();
				var username = $("#username").val();
				var usernameRgExp = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";
				if (username == null || username == "") {
					$(".reg-tip").html("*用户名不能为空");
					$("#username").focus();
					return false;
				} else if (username.length<6||username.length>16) {
					$(".reg-tip").html("*用户名长度应为6-16位");
					$("#username").focus();
					return false;
				} else if (username.match(usernameRgExp)) {
					$(".reg-tip").html("*用户名不能为汉字");
					$("#username").focus();
					return false;
				} else {
					$(".reg-tip").html("");
				}
				var password = $("#password").val();
				if (password == null || password == "") {
					$(".reg-tip").html("*密码不能为空");
					$("#password").focus();
					return false;
				} else {
					$(".reg-tip").html("");
				}
				var password2 = $("#password2").val();
				if (password2 == null || password2 == "") {
					$(".reg-tip").html("*请再次输入密码");
					$("#password2").focus();
					return false;
				} else if (password != password2) {
					$(".reg-tip").html("*两次密码不一致");
					return false;
				} else {
					$(".reg-tip").html("");
				}
				var phone = $("#mobile").val();
				var mobileRgExp = /^0?(13|15|14|18)[0-9]{9}$/;
				if (phone == null || phone == "") {
					$(".reg-tip").html("*请输入手机号码");
					$("#phone").focus();
					return false;
				} else if (!phone.match(mobileRgExp)) {
					$(".reg-tip").html("*手机号码不正确");
					$("#phone").focus();
					return false;
				} else {
					$(".reg-tip").html("");
				}
				var phoneCode = $("#phone-code").val();
				if (phoneCode == null || phoneCode == "") {
					$(".reg-tip").html("*请输入验证码码");
					$("#phone-code").focus();
					return false;
				} else {
					$(".reg-tip").html("");
				}
				if(!$("#agree").is(":checked")){
				      $(".reg-tip").html("*请勾选注册协议"); 
					  return false;
				 } else {
				 	$(".reg-tip").html(""); 
			     }
				$.ajax({
					url : "/cjdao/handleRegFromCjd",
					type : "post",
					data:{
						"username":username,
						"password":password,
						"password2":password2,
						"phone":phone,
						"vcode":phoneCode,
						"uaccount":$('#uaccount').val(),
						"type":1
					},
					success : function(result) {
						result = $.parseJSON(result);
						if(result.success){
							location.href = result.msg;
						} else {
							$(".reg-tip").html(result.msg);
						}
					},
					error : function(result) {
					}
				});
			});
		})
	</script>


</body>
</html>
