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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ui-form.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cjd-loginFirst.css"/>
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
</head>
<body>
<div class="total">
<!--头部-->
  <div class="top clear">
    <div class="top-left fl">
      
         <ul>
           <li class="logo-pic logo1"></li>
           <li  class="logo-pic logo2"></li>
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
   <img src="${pageContext.request.contextPath}/images/cjd/login.png"/>
  </div>
  <div class="login-tip"></div>
  <div class="login-div">
     <form class="ui-form" action="#" id="loginForm" method="post">
      <div class="ui-form-item">
        <label class="ui-label">用户名：</label>
        <input type="text" id="username" name="username" value="${reg.phone}"/>
        <input type="hidden" id="returnUrl" value="${returnUrl }" />
        <input type="hidden" id="third" value="${reg.uaccount}" />
        <input type="hidden" id="errorNum" value="1">
        <input type="hidden" id="showController" value="${showVerifyCode }">
        <span>请输入6-16位字符.英文,数字</span>
      </div>
 
      <div class="ui-form-item">
        <label class="ui-label">密码：</label>
        <input type="password" id="password" name="password"/>
        
       <span>请输入6到20位密码,建议英文+数字</span>
      </div> 
 

	  <div class="ui-form-item codeBox mt20" style="display: none;height: 40px;width: 362px;">
        <label class="ui-label">验证码：</label>
        <input type="text" id="authCode" name="authCode" maxlength="4"/>
       <a href="javascript:changeImage();" > <img id="authCodeImg"
		alt="看不清?换一张"
		src="${pageContext.request.contextPath}/verification/getGenImage/68/32">
		</a>
      </div> 

	  <div class="ui-form-item loginName submitBtn">
		 <input type="button" id="submit_btn" value="" class="submit-btn-bg" />
		 &nbsp;&nbsp;<a href="https://www.vfunding.cn/user/findPwdFirst" class="forgetPwd">忘记密码？</a> 

	  </div>
	  <div class="ui-form-item" style="margin-top:20px;color:red;">注：红包活动新用户【初始登录密码】请查看短信，或财经道网站站内信</div>	 
     </form>
  
  </div>
  
  
</div>
</div>

<script type="text/javascript">
		 
$(function() {
	$('#username').blur(function() {
		$.getJSON('/getUserLoginInfo', {
			value : $(this).val()
		}, function(data) {
			if (data.success) {
				if (data.msg > 2) {
					$(".codeBox").show();
				}
				$('#errorNum').val(data.msg);
			}
		});
	});



	var wrongType = 0, // 用户密码的错误类型

	wrongHtml = new Array("", "*请输入用户名", "*用户名长度太短", "*用户名长度超过16位", "*您的用户名或密码错误",
			"*超时，请重新登陆", "*请输入密码", "*密码长度小于6位", "*密码中含有非法字符", "*请输入用户名和密码",
			"*密码不能超过16位", "*请输入验证码", "*验证码不能超过4个", "*请输入用户名，密码和验证码", "*验证码错误");

	$('#authCode').blur(function() {
		var vcode = $(this).val();
		if (vcode.length > 0) {
			$.getJSON('/verification/checkVerifyCode/' + vcode, function(data) {
				if (!data.success) {
					$(".login-tip").html("");
					$(".login-tip").html("*验证码错误");
					wrongType = 14;
				} else {
					$(".login-tip").html("");
					wrongType = 0;
				}
			});
		}

	});

	$('body').focus(); // 让输入框不再自动获取焦点

	$("#submit_btn").click(function() {
		wrongType = 0;
		var uname = $("#username").val(); // 用户名
		var pwd = $("#password").val(); // 用户密码
		$.ajax({
			type : 'get',
			url : '/getUserLoginInfo',
			cache : false,
			async : false,
			dataType : 'json',
			data : {value:uname},
			success : function(data) {
				if (data.success) {
					if (data.msg > 2) {
						$(".codeBox").show();
					}
					$('#errorNum').val(data.msg);
				}
			}
		});
		
		
		var plength = pwd.length;
		var nlength = uname.length;
		var yzm = null;

		var ylength = null;

		if ($("#authCode").is(":visible")) {

			yzm = $("#authCode").val();
			ylength = yzm.length;

			if (ylength == 0) {
				wrongType = 11;
			} else if (ylength > 4) {
				wrongType = 12;
			}
		}

		if (nlength == 0 && plength == 0) {
			wrongType = 9;
		} else if (nlength == 0) {

			wrongType = 1;

		} else if (nlength > 0 && nlength < 2) {

			wrongType = 2;

		} else if (plength == 0) {

			wrongType = 6;
		} else {
			var patrn = /^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,16}$/;
			if (plength < 6)
				wrongType = 7;

		}
		inputTip(wrongHtml, wrongType);
		if (wrongType == 0) {// 在用户输入信息完全合法的情况下，即数组下标全部为0 开始执行ajax验证
			$("#loginForm input").attr('disabled', true);
			loginAjax(uname, pwd, yzm);
		}
	});

	var inputTip = function(tipHtml, tipNum) {
		$(".login-tip").html("");
		$(".login-tip").html(tipHtml[tipNum]);
	}

	var loginAjax = function(uname, pwd, vcode) {
		$(".login-btn").addClass("logining");
		var returnUrl = $('#returnUrl').val();
		var d;
		if ($("#authCode").is(":visible")) {
			if ($.trim(returnUrl).length > 0) {
				d = {
					"loginValue" : uname,
					"password" : pwd,
					"vcode" : vcode,
					"third" : $('#third').val(),
					"returnUrl" : returnUrl
				}
			} else {
				d = {
					"loginValue" : uname,
					"password" : pwd,
					"third" : $('#third').val(),
					"vcode" : vcode
				}
			}

		} else {
			if ($.trim(returnUrl).length > 0) {
				d = {
					"loginValue" : uname,
					"password" : pwd,
					"third" : $('#third').val(),
					"returnUrl" : returnUrl
				}
			} else {
				d = {
					"loginValue" : uname,
					"third" : $('#third').val(),
					"password" : pwd
				}
			}

		}

		$.ajax({
			type : 'post',
			url : '/loginCjdao',
			cache : false,
			async : false,
			dataType : 'json',
			data : d,
			success : function(data) {
				if (data.success) {
					$('#errorNum').val('1');
					if (data.neesRedirect) {
						location.href = data.redirectUrl;
					}
				}
			},
			error : function(d) {

				var errorNum = Number($('#errorNum').val());
				if (errorNum == null || errorNum == '') {
					errorNum = 1;
				}
				if (errorNum > 2) {
					$(".codeBox").show();
				}
				$(".login-tip").html("");

				$("#loginForm input").attr('disabled', false);
				$("#submit_btn").removeClass("logining");
				if (d.responseText == '密码错误') {
					var newNum = errorNum + 1;
					$('#errorNum').val(newNum);
					var residueNum = 5 - errorNum;
					if (residueNum == 0) {
						$(".login-tip").html('账号将被锁定24小时，请联系客服。');
					} else {
						$(".login-tip").html(
								d.responseText + ',您还有' + (5 - errorNum)
										+ '次机会');
					}
				} else {
					$(".login-tip").html(d.responseText);
				}
			}
		})
	}

	$(document).bind("keypress", function(ev) {

		var keycode = ev.which;
		if (keycode == 13) {
			ev.preventDefault();
			$("#submit_btn").trigger("click");
		}

	});

});

function changeImage() {
	$("#authCodeImg").attr({
		"src" : "/verification/getGenImage/68/32?id=" + Math.random() * 1000,
		"alt" : "看不清，换一张"
	});
}

function getUserLoginInfo(){
	
}		 
	 
</script>


</body>
</html>
