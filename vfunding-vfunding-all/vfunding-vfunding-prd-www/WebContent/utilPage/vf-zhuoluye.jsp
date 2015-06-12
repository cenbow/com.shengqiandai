<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|微贷|红包|基金|微基金|小额贷款|微财富|宜车贷|二手车抵押|车贷|托管" />
<meta name="description" content="微积金(www.vfunding.cn) 整合了民营汽车借贷机构、二手车商、第三方汽车服务机构等传统领域企业，形成了独特的针对汽车提供投融资对接服务的创新型O2O互联网汽车金融服务模式。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、汽车贷款等。" />
<title>微积金，中国领先的互联网汽车金融服务平台_理财_理财产品_财富_理财需求_投资_投资标的_微财富_财富成长_手机理财_微信理财_微理财</title>
<link type="text/css" rel="stylesheet" href="/css/landing.css"/>
<link type="text/css" rel="stylesheet" href="/css/register2.css"/>
<link type="text/css" rel="stylesheet" href="/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bottom.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css" />
<script type="text/javascript">
	var rootPath = '${pageContext.request.contextPath}';
</script>
<script src="/js/jquery1.8.3.js"></script>
<script src="/js/kinMaxShow.js"></script>
<script src="/js/formValidator.js"></script>
<script src="/js/formValidatorRegex.js"></script>
<script src="/js/theme.js"></script>
<script src="/js/in_tip_Plugin.js"></script>
<script src="/js/res.js"></script>
<script
	src="/js/getCodeForRegisterZhuoluye.js"></script>
</head>

<body style="background-color: #fff;">
<c:if test="${thirdSource != 10 }">
<div class="top" id="logoImg" > <img  src="/images/logo.png" style="float:left; "> <a href="/">微积金首页</a> </div>
</c:if>
<c:if test="${thirdSource == 10 }">
<div class="top"  id="addImg"> <img  src="/images/zhuolulogo.png" style="float:left; "> <a href="/">微积金首页</a> </div>
</c:if>
<div class="flexslider" >
  <ul class="slides">
    <li style="background:url(/images/zl-banner.png) 50% 0 no-repeat;"></li>
   <!-- <li style="background:url(/images/img5.jpg) 50% 0 no-repeat;"></li>
		<li style="background:url() 50% 0 no-repeat;"></li>
		<li style="background:url() 50% 0 no-repeat;"></li>
		<li style="background:url() 50% 0 no-repeat;"></li>-->
  </ul>
  <div class="auxiliary">
  <div class="enroll" style="color:#FFF; font-size:13px;">
    <form action="/register" method="post" name="zhuce" id="zhuce" style="z-index:999">
      <div class="zc">
        <div class="username_box" id="username_box">
          <div class="username_div">
            <div class="user_name">用户名：</div>
            <label class="username_label"> <span style="display: none;">请输入用户名</span>
              <input type="text" class="g-ipt-err" id="username" name="userName" value="" style="color: rgb(0, 0, 0);">
            </label>
            <div id="usernameTip" class="onError" style="margin: 0px; padding: 0px; background: transparent;">
              <p class="noticeWrap"><b class="ico-warning"></b><span class="txt-err">你输入的用户长度不正确</span></p>
            </div>
          </div>
        </div>
        <div class="username_box">
          <div class="username_div">
            <div class="user_name">密码：</div>
            <label class="password_label"> <span style="display: none;">请输入6到16位密码</span>
              <input type="password" class="g-ipt-err" id="password" name="password" value="" style="color: rgb(0, 0, 0);">
            </label>
            <div id="passwordTip" class="onError" style="margin: 0px; padding: 0px; background: transparent;color: #ff9595;">
              <p class="noticeWrap"><b class="ico-warning"></b><span class="txt-err">密码长度错误</span></p>
            </div>
          </div>
        </div>
        <div class="username_box">
          <div class="username_div">
            <div class="user_name">确认密码：</div>
            <label class="password_label"> <span style="display: none;">再次输入密码</span>
              <input type="password" class="g-ipt-err" id="password2" name="rePassword" value="" style="color: rgb(0, 0, 0);">
            </label>
            <div id="password2Tip" class="onError" style="margin: 0px; padding: 0px; background: transparent;color: #ff9595;">
              <p class="noticeWrap"><b class="ico-warning"></b><span class="txt-err">确认密码不能为空</span></p>
            </div>
          </div>
        </div>
        <div class="username_box">
          <div class="username_div">
            <div class="user_name">手机：</div>
            <label class="email_label"> <span style="display: none;">请输入你的手机</span>
              <input type="text" class="g-ipt-err" value="" id="mobile" name="mobile" style="color: rgb(0, 0, 0);">
            </label>
            <div id="mobileTip" class="onError" style="margin: 0px; padding: 0px; background: transparent;color: #ff9595;">
              <p class="noticeWrap"><b class="ico-warning"></b><span class="txt-err">你输入的手机不正确</span></p>
            </div>
          </div>
        </div>
        <div class="username_box">
          <div class="username_div">
            <div class="user_name">验证码：</div>
            <label class="jieshao_label" id="yzm_label"> <span style="display: none;">请输入验证码</span>
              <input type="text" class="g-ipt-err" id="auth_code" name="vcode" style="color: rgb(0, 0, 0);">
            </label>
            <div id="auth_codeTip" class="onError" style="margin: 0px; padding: 0px; background: transparent;color: #ff9595;">
              <p class="noticeWrap"><b class="ico-warning"></b><span class="txt-err">请输入正确的验证码</span></p>
            </div>
            <input type="hidden" value="" name="unionUserId">
            <input type="hidden" value="" name="productId">
            <input type="hidden" value="publicityPage/publicityPage" name="errorReturnUrl">
            <input type="hidden" value="" name="experienceMoney">
            <input type="hidden" value="" name="fromType">
          </div>
        </div>
        <div class="username_box" style="text-align: center;">
          <div class="username_div" style="text-align: center; padding: 0px; margin-left: 65px; margin-bottom: 0px;"> <a href="javascript:getCode();" id="phoneCode" class="yzm">短信获取</a> <a href="javascript:getVoice();" id="voiceCode" class="yzm">语音获取</a> </div>
        </div>
        <div class="username_box" style="height: 20px; margin-left: 0px; margin-top: 2px; margin-bottom: 2px;">
          <div id="waitMsg" style="position: absolute; left: 50px; color: #888888;"></div>
        </div>
        <input type="submit" name="button" id="button" class="res_btn" style="margin-top: 15px; margin-bottom: 2px;" value="立即注册">
        <span class="res_tip">已有账号？<a href="/goLogin" target="_blank" style="color: #fff; ">立即登录</a></span> </div>
    </form>
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
  <!-- js调用部分begin --> 
  <script src="/js/jquery.flexslider-min.js"></script> 
  <script>
$(function(){
	$('.flexslider').flexslider({
		directionNav: true,
		pauseOnAction: false
	});
});
</script> 
  <!-- js调用部分end --> 
</div>
<div class="whyText"></div>
<div class="interpretationText">
  <ul>
    <li class="oneli">
      <div class="img one"></div>
      <span id="full" class="hight"><font>高</font>收益</span>
      <span class="onespan">
      <p>39倍银行存款利率</br>
        最高年化13.8%的净收益</p>
      </span></li>
    <li class="twoli">
      <div class="img two"></div>
      <span id="full" class="low"><font>低</font>风险</span>
      <span class="twospan">
      <p>专做以车辆为标的的理财产品本息保障招商银行风险准备金托管新浪支付全程提供系统支持和资金托管</p>
      </span></li>
    <li class="threeli">
      <div class="img three"></div>
      <span id="full"  class="door"><font>低</font>门槛</span>
      <span class="threespan">
      <p>50元起投，8秒注册</p>
      </span></li>
    <li class="fourli">
      <div class="img four"></div>
      <span id="full" class="head"><font>最</font>安心</span>
      <span class="fourspan">
      <p>多层风控，方便快捷</br>
        透明操作，专业管理</p>
      </span></li>
  </ul>
</div>
<div class="safe">
  <div class="whysafe"></div>
  <div class="vlogo"> <span>
    <p>微积金（www.vfunding.cn），融合了二手车、互联网金融（特别是P2P），针对小微企业、二手车经销商提供投融对接服务，已牵手招商银行、新浪支付实现资金全程托管，成为自律性最强的互联网金融平台。</br>
      微积金专注于互联网汽车金融领域，努力打造汽车后金融信息服务生态链。</p>
    </span> </div>
  <div class="bankSafe"></div>
</div>
<div class="investment"></div>
<div class="fly" style="position:relative;"><div class="sprog" style="position:relative;"></div><div class="returnTop"><a href="#top"><img src="/images/return.png"/></a></div>
<div class="report">
  <div class="reportTop"></div>
  <div class="reportImg"></div>
  <div class="media">
    <ul>
      <li><a class="login guoji" href="http://www.vfunding.cn/utilpage/selectArticleDetail/4" target="_blank" style="cursor:pointer"></a></li>
      <li><a class="login tenxun" href="http://www.vfunding.cn/utilpage/selectArticleDetail/635" target="_blank" style="cursor:pointer"></a></li>
      <li><a class="login hexun" href="http://www.vfunding.cn/utilpage/selectArticleDetail/57" target="_blank" style="cursor:pointer"></a></li>
    </ul>
    <ul>
      <li><a class="login xinlang" href="http://www.vfunding.cn/utilpage/selectArticleDetail/103" target="_blank" style="cursor:pointer"></a></li>
      <li><a class="login shsb" href="http://www.vfunding.cn/utilpage/selectArticleDetail/107" target="_blank" style="cursor:pointer"></a></li>
      <li><a class="login cj" href="http://www.vfunding.cn/utilpage/selectArticleDetail/17" target="_blank" style="cursor:pointer"></a></li>
    </ul>
    <ul>
      <li><a class="login dfcf" href="http://finance.eastmoney.com/news/1670,20140917424903519.html" target="_blank" style="cursor:pointer"></a></li>
      <li><a class="login zgrb" href="http://www.vfunding.cn/utilpage/selectArticleDetail/23" target="_blank" style="cursor:pointer"></a></li>
      <li><a class="login fh" href="http://www.vfunding.cn/utilpage/selectArticleDetail/8" target="_blank" style="cursor:pointer"></a></li>
    </ul>
  </div>
</div>
<!-- 百度统计和cnzz统计都会显示图片和文字，故页面需隐藏 -->
<div style="display: none">
	<!--  引入cnzz站点统计js --> 
	<script src='http://w.cnzz.com/q_stat.php?id=1254751504&l=3' language='JavaScript'></script>

	<!-- //Baidu -->	
	<script>
	var _hmt = _hmt || [];
	(function() {
  	var hm = document.createElement("script");
 	 hm.src = "//hm.baidu.com/hm.js?5d7e47bb3bacea3f5574b92f56aec484";
  	var s = document.getElementsByTagName("script")[0]; 
  	s.parentNode.insertBefore(hm, s);
	})();
	</script>

		<!--//Google-->
	<!--	(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})(window, document, 'script',
				'//www.google-analytics.com/analytics.js', 'ga');

		ga('create', 'UA-48347750-1', 'vfunding.cn');
		ga('send', 'pageview');-->
	
	
</div>
<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
</body>
</html>
