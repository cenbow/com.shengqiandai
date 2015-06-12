<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>微积金2014年度报告</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" href="/css/report.css">
<style>
</style>
<script src="/js/jquery-1.9.0.min.js"></script>
<script src="/js/jquery.onepage-scroll.min.js"></script>
<script>
	$(function() {

		$('.main').onepage_scroll({
			sectionContainer : '.page',
			responsiveFallback : true
		});

		/*$('.main').onepage_scroll({
			sectionContainer: '.page'
		});
		
		if ((screen.width == 1024) && (screen.height == 768)) {
			$(".main").attr("style","width:80%; top:2%");//此分辨率下你需要的操作
		} else if ((screen.width == 1360) && (screen.height == 900)) {
			$(".main").attr("style","width:80%; top:2%");//这个分辨率下你的操作
		} else {
			$(".main").attr("style","width:80%; top:2%");//默认操作
		}*/

	});
</script>
</head>

<body>
<body class="viewing-page-1 disabled-onepage-scroll">
	<ul class="onepage-pagination">
		<li><a href="#1" data-index="0" class="active"></a></li>
		<li><a href="#2" data-index="1"></a></li>
		<li><a href="#3" data-index="2"></a></li>
		<li><a href="#4" data-index="3"></a></li>
		<li><a href="#5" data-index="4"></a></li>
		<li><a href="#6" data-index="5"></a></li>
		<li><a href="#7" data-index="6"></a></li>
		<li><a href="#8" data-index="7"></a></li>
		<li><a href="#9" data-index="8"></a></li>
		<li><a href="#10" data-index="9"></a></li>
		<li><a href="#11" data-index="10"></a></li>
		<li><a href="#12" data-index="11"></a></li>
		<li><a href="#13" data-index="12"></a></li>
		<li><a href="#14" data-index="13"></a></li>
		<li><a href="#15" data-index="14"></a></li>
		<li><a href="#16" data-index="15"></a></li>
		<li><a href="#17" data-index="16"></a></li>
		<li><a href="#18" data-index="17"></a></li>
		<li><a href="#19" data-index="18"></a></li>
	</ul>


	<a class="return" href="http://www.vfunding.cn">返回首页</a>
	<div class="main onepage-wrapper" style="position: relative;">
		<div class="page page0 section active"
			style="position: absolute; top: 0%; left: 0px;" data-index="0">
			<img src="/images/zuro.jpg" />
		</div>
		<div class="page page1 section active"
			style="position: absolute; top: 100%; left: 0px;" data-index="1">
			<img src="/images/one.jpg" />
		</div>
		<div class="page page2 section"
			style="position: absolute; top: 200%; left: 0px;" data-index="2">
			<img src="/images/aa.jpg" />
		</div>
		<div class="page page3 section"
			style="position: absolute; top: 300%; left: 0px;" data-index="3">
			<img src="/images/two.jpg" />
		</div>
		<div class="page page4 section"
			style="position: absolute; top: 400%; left: 0px;" data-index="4">
			<img src="/images/three.jpg" />
		</div>
		<div class="page page5 section"
			style="position: absolute; top: 500%; left: 0px;" data-index="5">
			<img src="/images/four.jpg" />
		</div>
		<div class="page page6 section"
			style="position: absolute; top: 600%; left: 0px;" data-index="6">
			<img src="/images/five.jpg" />
		</div>
		<div class="page page7 section"
			style="position: absolute; top: 700%; left: 0px;" data-index="7">
			<img src="/images/six.jpg" />
		</div>
		<div class="page page8 section"
			style="position: absolute; top: 800%; left: 0px;" data-index="8">
			<img src="/images/seven.jpg" />
		</div>
		<div class="page page9 section"
			style="position: absolute; top: 900%; left: 0px;" data-index="9">
			<img src="/images/eight.jpg">
		</div>
		<div class="page page10 section"
			style="position: absolute; top: 1000%; left: 0px;" data-index="10">
			<img src="/images/nine.jpg" />
		</div>
		<div class="page page11 section"
			style="position: absolute; top: 1100%; left: 0px;" data-index="11">
			<img src="/images/ten.jpg" />
		</div>
		<div class="page page12 section"
			style="position: absolute; top: 1200%; left: 0px;" data-index="12">
			<img src="/images/eleven.jpg">
		</div>
		<div class="page page13 section"
			style="position: absolute; top: 1300%; left: 0px;" data-index="13">
			<img src="/images/twelve.jpg" />
		</div>
		<div class="page page14 section"
			style="position: absolute; top: 1400%; left: 0px;" data-index="14">
			<img src="/images/thirteen.jpg" />
		</div>
		<div class="page page15 section"
			style="position: absolute; top: 1500%; left: 0px;" data-index="15">
			<img src="/images/fourteen.jpg" />
		</div>
		<div class="page page16 section"
			style="position: absolute; top: 1600%; left: 0px;" data-index="16">
			<img src="/images/fifteen.jpg" />
		</div>
		<div class="page page17 section"
			style="position: absolute; top: 1700%; left: 0px;" data-index="17">
			<img src="/images/sixteen.jpg" />
		</div>
		<div class="page page18 section"
			style="position: absolute; top: 1800%; left: 0px;" data-index="18">
			<img src="/images/seventeen.jpg" />
		</div>
	</div>


</body>
</html>