<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>微积金-幸运大转盘</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css" href="/css/prize/reset.css" />
<link rel="stylesheet" type="text/css" href="/css/prize/common.css" />
<link rel="stylesheet" type="text/css" href="/css/prize/jqueryui/jqueryui.css" />
<link rel="stylesheet" type="text/css" href="/css/prize/index.css" />
<script type="text/javascript" src="/js/jquery1.8.3.js"></script>
<script type="text/javascript" src="/js/prize/jqueryui.js"></script>
</head>
<input type="text" id="shareId" name="shareId" value="${shareId}">
<div id="top-menu-wrapper" class="bgfix">
	<div id="top-menu">
		<div class="l">
			<a class="user bgfix" href="#"> </a>
			<span>（今天还有</span><span class="lottery-times" id="chooseCount">${canChooseCount}</span>
			<span>次抽奖机会）</span>
		</div>
		<a class="logout r" href="#">退出</a>
	</div>
</div>
<div id="header">
	<div id="turnplatewrapper" onselectstart="return false;" class="bgfix">
		<div id="turnplate" class="bgfix">
			<div id="awards" class="bgfix"></div>
			<div id="platebtn" class="bgfix"></div>
			<p id="msg"></p>
			<p id="init" class="dn">初始化中，请稍后<span></span></p>
		</div>
	</div>
	<div id="gift" class="bgfix"></div>
</div>
<div id="lotteryMsg" class="dn">
	<p class="msg"></p>
	<p class="tips">中奖信息已自动添加到您的站内信中，<a href="#" target="_blank">点击查看</a>。</p>
	<hr class="sp" />
	<p class="mv5" style="margin-bottom:5px">副标题！</p>	
	<div class="content mv5">
		“幸运大转盘” <span class="option"></span>每日大转盘摇奖，轻松摇奖，惊喜不断，微积金理财产品，500万用户的选择，值得信赖。
	</div>
</div>
<!--新登录注册弹框-->
<div id="new-login" class="dn"></div>
<!--[if IE 6]>
<script src="/js/prize/DD_belatedPNG_0.0.8a-min.js"></script>
<script>DD_belatedPNG.fix('.bgfix');</script>
<![endif]-->
<script type="text/javascript" src="/js/prize/index.js"></script>
<script type="text/javascript">
	turnplate.lotteryTime = ${canChooseCount==null?0:canChooseCount};
	turnplate.init();
</script>
</body>
</html>