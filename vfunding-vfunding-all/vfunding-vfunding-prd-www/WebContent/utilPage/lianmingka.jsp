<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>微积金-联名卡</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/lianmingka.css"/>
<script src="${pageContext.request.contextPath }/js/jquery1.8.3.js"></script>
</head>

<body>

<!---头部--->
<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>

<!--中间-->

<div class="lmk">
  <div class="lmk-info">
    <p class="bigfont">微积金携手招商银行<span>(上海分行)</span></p>
    <p class="bigfont">推出联名价值认同卡金卡</p>
    <p class="midfont">免微积金网站提现手续费免ATM取款手续费免开户费免年费！</p>
    <p class="smallfont">享招商银行优先叫号享金卡专用窗口服务享微积金专属理财经理服务！</p>
    <p class="smallfont">这是一张微积金携手招商银行（上海分行）为您推出的联名金卡，还在等什么？</p>
    <a href="/accountBank/bank" class="lmk-reg allbg"></a>
  </div>
  <div class="run-card allbg"></div>

  <div class="what-card">
      什么是价值认同卡金卡？
  </div>
    <div class="answer">　　招商银行微积金价值认同卡金卡（联名卡）是招商银行（上海分行）与微积金致力于品牌合作，特为微积金用户度身打造的银行借记卡金卡。该卡属招行银联金卡产品，享有银行所提供的银联金卡客户尊享权益，而且也可凭此卡参加招行及微积金平台举办的各项优惠活动。</div>
    <div class="what-card">
        价值认同卡金卡（联名卡）有什么尊享权益？
    </div>

    <div class="four-sector clear">
        <ul>
            <li class="sector-first">
                <div class="sector-first-img allbg"></div>
                普通客户需要在银行资产超过5万元，才可申请银联金卡，微积金现对所有用户开放；
            </li>
            <li>
                <div class="sector-second-img allbg"></div>
                在招行网点享受优先叫号、金卡专用窗口服务；
            </li>
            <li>
                <div class="sector-third-img allbg"></div>
                本地他行ATM取现每月3笔免手续费，异地招行ATM取现无手续费，异地他行ATM取现无手续费；
            </li>
            <li>
                <div class="sector-fourth-img allbg"></div>
                免开户手续费、免年费；
            </li>
            <li>
                <div class="sector-fivth-img allbg"></div>
                到账短信通知、微积金专属理财经理
            </li>
        </ul>
    </div>



    <div class="zsyh clear">
        <div class="zsyh-img allbg fl"></div>
        <div class="zsyh-cnt fl">
            <i class="star allbg"></i>
            <p class="most-important">最重要的是</p>
            <p class="normal">您将享有每月三笔在微积金网站</p>
            <p class="normal"><span>提现免手续费</span>的权利！</p>
            <p class="normal">理财路上更贴心更便捷！</p>

        </div>
    </div>



    <div class="what-card">
        如何申领价值认同卡金卡（联名卡）？
    </div>
    <div class="answer">
        　　微积金网站现开放线上申请，只需登录您的账户，点击进入【我的账户】左侧菜单栏【银行卡】填写银行卡申请基本材料点击提交申请即可。我们会在收到您的申请后每月30号统一采集信息并与您取得联系，确认信息后进入制卡周期，15个工作日内制卡完成后，免费寄送至您指定的地址。
    </div>


<div class="mt50">
    　　微积金网站长期开放联名卡申请，每月30号统一采集信息。
</div>



</div>











<!------尾部------>
<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>

<script type="text/javascript">
$(function(){
	$("#weixinDialog").hide();
})
</script>
</body>
</html>
