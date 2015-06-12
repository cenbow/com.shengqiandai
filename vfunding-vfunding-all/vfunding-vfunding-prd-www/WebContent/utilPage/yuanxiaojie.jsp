<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>微积金-元宵节福利</title>
    <link rel="stylesheet" href="/css/jquery.fullPage.css">
    <link rel="stylesheet" href="/css/yuanxiaojie.css">
    <script src="/js/jquery1.8.3.js"></script>
    <script src="http://cdn.staticfile.org/jqueryui/1.10.3/jquery-ui.min.js"></script>
    <script src="/js/jquery.fullPage.min.js"></script>
    <script>
        $(document).ready(function() {
            $.fn.fullpage({
                anchors: ['page1', 'page2', 'page3', 'page4', 'page5', 'page6', 'page7', 'page8'],
                menu: '#menu'
            });
        });
    </script>
</head>

<body>

<ul id="menu">
    <li data-menuanchor="page1" class="active"><a href="#page1">元宵福利</a></li>
    <li data-menuanchor="page2"><a href="#page2">送电影票</a></li>
    <li data-menuanchor="page3"><a href="#page3">注册有礼</a></li>
    <li data-menuanchor="page4"><a href="#page4">推荐有礼</a></li>
    <li data-menuanchor="page5"><a href="#page5">投资有礼</a></li>
    <li data-menuanchor="page6"><a href="#page6">定时礼包</a></li>
    <li data-menuanchor="page7"><a href="#page7">喜上加喜</a></li>
    <li data-menuanchor="page8"><a href="#page8">活动规则</a></li>
    <li><a href="">返回首页</a></li>
</ul>

<div class="section sector1">
    <div class="img1"></div>
</div>
<div class="section sector2">
    <div class="img2"></div>
</div>
<div class="section sector3">
    <div class="img3"></div>
</div>
<div class="section sector4">
    <div class="img4"></div>
</div>
<div class="section sector5">
    <div class="img5"></div>
</div>
<div class="section sector6">
    <div class="img6"></div>
</div>
<div class="section sector7">
    <div class="img7"></div>
</div>
<div class="section sector8">
    <div class="img8"></div>
</div>








</body>


</html>