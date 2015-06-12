<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>微积金-着陆页</title>
<link rel="stylesheet" type="text/css" href="/css/ui-form.css" />
<link rel="stylesheet" type="text/css" href="/css/zhuoluye.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/register.css" />
<script type="text/javascript">
	var rootPath = '${pageContext.request.contextPath}';
</script>
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/kinMaxShow.js"></script>
<script src="${pageContext.request.contextPath}/js/formValidator.js"></script>
<script src="${pageContext.request.contextPath}/js/formValidatorRegex.js"></script>
<script src="${pageContext.request.contextPath}/js/themes/126${pageContext.request.contextPath}/js/theme.js"></script>
<script src="${pageContext.request.contextPath}/js/in_tip_Plugin.js"></script>
<script src="${pageContext.request.contextPath}/js/res.js"></script>
<script
	src="${pageContext.request.contextPath}/js/getCodeForRegister.js"></script>

</head>
<body>
	<div class="zl-header">
		<div class="clear">
			<a class="zl-logo fl"  href="/"><img
				src="https://www.vfunding.cn/images/header/logo.png"></a>
			<div class="zl-index fr"><a href="/">微积金首页</a></div>
		</div>
		<div class="zl-banner">
		
			<div class="input_box">
		<div class="res_top">
			<span class="s1">注册微积金</span>&nbsp;&nbsp;&nbsp;<span class="s2"></span>
		</div>
		<form action="/register" method="post" name="zhuce" id="zhuce">
			<div class="zc">
				<div class="username_box" id="username_box">
					<div class="username_div">
						<div class="user_name">用户名：</div>
						<label class="username_label"> <span style="display: none;">请输入用户名</span> <input type="text" class="g-ipt" id="username" name="userName" value="" style="color: rgb(0, 0, 0);">
						</label>
						<div id="usernameTip" class="onCorrect" style="margin: 0px; padding: 0px; background: transparent;"><p class="noticeWrap"><b class="ico-succ"></b><span class="txt-succ">该用户名可以注册</span></p></div>
					</div>

				</div>


				<div class="username_box">
					<div class="username_div">
						<div class="user_name">密码：</div>
						<label class="password_label"> <span style="display: none;">请输入6到16位密码</span> <input type="password" class="g-ipt" id="password" name="password" value="" style="color: rgb(0, 0, 0);">
						</label>
						<div id="passwordTip" class="onCorrect" style="margin: 0px; padding: 0px; background: transparent;"><p class="noticeWrap"><b class="ico-succ"></b><span class="txt-succ">密码强度：弱</span></p></div>
					</div>

				</div>


				<div class="username_box">
					<div class="username_div">
						<div class="user_name">确认密码：</div>
						<label class="password_label"> <span style="display: none;">再次输入密码</span> <input type="password" class="g-ipt" id="password2" name="rePassword" value="" style="color: rgb(0, 0, 0);">
						</label>
						<div id="password2Tip" class="onCorrect" style="margin: 0px; padding: 0px; background: transparent;"><p class="noticeWrap"><b class="ico-succ"></b><span class="txt-succ">输入正确</span></p></div>
					</div>

				</div>

				<div class="username_box">
					<div class="username_div">
						<div class="user_name">手机：</div>
						<label class="email_label"> <span style="display: none;">请输入你的手机</span> <input type="text" class="g-ipt" value="" id="mobile" name="mobile" style="color: rgb(0, 0, 0);">
						</label>

						<div id="mobileTip" class="onCorrect" style="margin: 0px; padding: 0px; background: transparent;"><p class="noticeWrap"><b class="ico-succ"></b><span class="txt-succ">恭喜你,你输对了</span></p></div>
					</div>

				</div>


				<!--  <div class="username_box">
					<div class="username_div">
						<div class="user_name">介绍人：</div>
						<label class="jieshao_label"> <span>请输入介绍人</span> 
								<input type="text" class="input_text"
									value="" id="jieshao" name="introducer" />
							 
						</label>
						<div id="jieshaoTip"></div>
					</div>
				</div>-->
				<!-- <input id="inputCode" class="getCode" type="button" value="短信获取"
							onclick="getCode('mobile');" /> <input id="getByPhone"
							class="getCode" style="display: none;" type="button" value="电话获取"
							onclick="" /> -->

				<div class="username_box">
					<div class="username_div">
						<div class="user_name">验证码：</div>
						<label class="jieshao_label" id="yzm_label"> <span style="display: none;">请输入验证码</span>
							<input type="text" class="g-ipt-err" id="auth_code" name="vcode" style="color: rgb(0, 0, 0);">
						</label>
						<div id="auth_codeTip" class="onError" style="margin: 0px; padding: 0px; background: transparent;"><p class="noticeWrap"><b class="ico-warning"></b><span class="txt-err">验证码错误!</span></p></div>
						<input type="hidden" value="" name="unionUserId"> <input type="hidden" value="" name="productId"> <input type="hidden" value="publicityPage/publicityPage" name="errorReturnUrl"> <input type="hidden" value="" name="experienceMoney">
							
							<input type="hidden" value="" name="fromType">
							
							
					</div>

				</div>

				<div class="username_box" style="text-align: center;">
					<div class="username_div" style="text-align: center; padding: 0px; margin-left: 65px; margin-bottom: 0px;">
						<a href="javascript:getCode();" id="phoneCode" class="yzm">短信获取</a> <a href="javascript:getVoice();" id="voiceCode" class="yzm">语音获取</a>
					</div>

				</div>



				<div class="username_box" style="height: 20px; margin-left: 0px; margin-top: 2px; margin-bottom: 2px;">
					<div id="waitMsg" style="position: absolute; top: 20px; left: 50px; color: #888888;"></div>
				</div>

				<input type="submit" name="button" id="button" class="res_btn" style="margin-top: 20px; margin-bottom: 2px;" value="立即注册"><span class="res_tip">已有账号？<a href="/goLogin" target="_blank">立即登录</a></span>

			</div>
		</form>

	</div>
		</div>
	</div>




<script type="text/javascript">
				function getCode(){
					$.ajax({
						type:"post",
						url:"/nextRegister",
						data:$("#zhuce").serialize()
					})
					if (checkPhone('mobile')) {
						$.getJSON(rootPath+'/isCheckRegisterUserName',function(data){
							if(data){
								$.get(rootPath+'/verification/getRegistVerifyCode/'+mobile, function(data) {
									var wait = 60;
									var buttonObject = $('#phoneCode');
									doWait(buttonObject, wait);
								});
							}else{
								$('#username').focus();
							}
						});
					}
				}
				function getVoice(){
					$.ajax({
						type:"post",
						url:"/nextRegister",
						data:$("#zhuce").serialize()
					})
					if (checkPhone('mobile')) {
						$.getJSON(rootPath+'/isCheckRegisterUserName',function(data){
							if(data){
								$.get(rootPath+'/verification/getRegistVerifyCodeByVoice/'+mobile, function(data) {
									var wait = 60;
									var buttonObject = $('#phoneCode');
									doWait(buttonObject, wait);
								});
							}else{
								$('#username').focus();
							}
						});
					}
				}
				</script>
	<div class="zl-module clear w1000">
		<div class="module-box">
			<div class="module module1"></div>
			<div class="zl-module-cnt">
				<p>39倍银行存款利率</p>
				<p>最高年化13.8%的净收益</p>
			</div>
		</div>
		<div class="module-box">
			<div class="module module2"></div>
			<div class="zl-module-cnt">
				<p>专做以车辆为标的的理财产品 本息保障，招商银行风险准备金托管 新浪支付全程提供系统支持和资金托管</p>
			</div>
		</div>
		<div class="module-box">
			<div class="module module3"></div>
			<div class="zl-module-cnt">
				<p>50元起投，8秒注册</p>
				<p></p>
			</div>
		</div>


		<div class="module-box">
			<div class="module module4"></div>
			<div class="zl-module-cnt">
				<p>多层风控，方便快捷</p>
				<p>透明操作，专业管理</p>
			</div>
		</div>
	</div>

	<div class="w1000 zl-fund">
		<div class="zl-intro-img">
			<img src="/images/login/zl2.png" />
		</div>
		<div class="zl-intro">
			微积金（www.vfunding.cn）是中国领先的O2O互联网汽车金融平台，由从业于金融、互联网行业多年的精英团队倾力打造国内最安全的互联网金融信息平台。微积金专注于互联网汽车金融领域，有信心通过自身的高品质服务能帮助您制订让所有人都能看得懂的理财计划！</div>
	</div>

	<div class="w1000 zl-tender pos-r">
		<a href="#"><img src="/images/login/zl3.png"></a>
	</div>

	<div class="w1000 zl-income">
		<p class="income-p pos-a">微积金现正在全方位多角度地给用户送钱</p>
		<div class="zl-reg"><a href="${pageContext.request.contextPath}/goRegister"><img  src="/images/login/zl-reg.png"/></a></div>
	</div>
	<p class="w1000 income-tip">
		活动期间（3月10日-4月10日）新手用户首笔投资收益提升50%（限额10000元），送多少现金，您自己决定！完成新手任务还送现金红包&提现抵扣券！</p>
	<div class="w1000  jiangLi">
		<img src="/images/login/jiangli.gif"> <img
			src="/images/login/zhuanpan.gif">
	</div>
	<div class="w1000 investment">
		<img src="/images/login/liebiao.gif">
	</div>
	<div class="w1000 details">
		<img src="/images/login/xiangQ.gif">
	</div>
</body>
</html>