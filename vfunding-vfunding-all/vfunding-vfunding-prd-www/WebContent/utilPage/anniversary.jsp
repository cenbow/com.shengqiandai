<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
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
<link rel="stylesheet" type="text/css" href="/css/prize/jqueryui/jqueryui.css" />
<link rel="stylesheet" type="text/css" href="/css/anniversary.css"/>
<link rel="stylesheet" type="text/css" href="/css/prize/prize.css" />
<script src="/js/jquery1.8.3.js"></script>
<script type="text/javascript" src="/js/prize/jqueryui.js"></script>
<script src="/js/scroll.js"></script>
<!-- 弹出层JS -->
<script	src="${pageContext.request.contextPath}/js/artDialog/artDialog.js?skin=default"></script>
</head>
<body>
<!-------------头部-------------------->
<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>
<!--中间-->
<input type="hidden" id="shareId" name="shareId" value="${shareId}">
<div class="an-first-sector"></div>
<div class="an-second-sector">
    <div class="an-second-cnt main">
        <div class="asc-title clear">
            <div class="No fl No1 an-icon"></div>
            <div class="title fl">新手专享礼&nbsp;&nbsp; 收益<span>直升50%</span></div>
            <div class="time fr">12月25日- 2月10日</div>
        </div>
        <div class="asc-cnt">
            <div class="content clear">
                <div class="content-side fl cs-bg">规则</div>
                <div class="content-main fl">
                    <p> 1. 新手用户（包括已注册微积金未投资过实际标的用户）首次单笔投资收益提升50%，每位新用户可参与升利率活动的投资本金最多为
                        <br/>&nbsp;&nbsp;&nbsp;10000元单笔投资本金超过10000元的部分仍按照标的的常规利息计算收益。</p>
                    <p> 2. 首笔投资收益提升50%活动适用于平台所有投资标的。</p>
                    <p> 3. 奖励的投资收益将于投资完成的次日以现金红包（可再次用于投资或者提现）的形式统一发放到礼品盒<a href="/giftbox/boxPage" target="_blank">（点击查看您的礼品盒）</a>。</p>
                </div>
            </div>
            <div class="content clear">
                <div class="content-side fl cs-bg">示例</div>
                <div class="content-main fl">
                    <p>小微于2014年6月注册了微积金，她只投资过新手体验标，没有投资过真实标的。周年庆活动开始之后，小微充值并投资了年化收益为
                        <br/>10.6%的单月标，投资金额为5万元。</p>
                    <p>小微在第二日，查看了自己的礼品盒，看到礼品盒中收到了10000元的5.3%年化收益奖励红包（冻结中），一个月以后投资标的到期，
                        <br/>小微不仅收到了5万元的本金和10.6%的年化收益利息，礼品盒中10000元的5.3%年化收益奖励红包也在投资到期日解冻了。</p>

                </div>
            </div>
              <div class="bgAndBtn clear">
                  <div class="rate-up fl an-icon"></div>
                  <a class="invest-btn fr an-icon" href="/borrow/borrowList" target="_blank"></a>
              </div>
            <div class="lookForward an-icon hide"></div>
        </div>
    </div>
</div>



<div class="an-fourth-sector">
    <div class="an-fourth-cnt main">
        <div class="asc-title clear" style="border-bottom: 1px solid #89bbdb;">
            <div class="No fl No3 an-icon" style="width: 76px;"></div>
            <div class="title fl" style="margin-top: 20px;">幸运大转盘</div>
            <div class="time fr">12月30日- 2月10日</div>
        </div>
    <div class="rotate clear">
        <div class="rotate-left fl">
            <h3 class="">您今天还有<span id="chooseCount">${canChooseCount==null?0:canChooseCount}</span>次机会</h3>
            <div class="rotatebg">
            	<div id="turnplatewrapper" onselectstart="return false;" class="bgfix">
					<div id="turnplate" class="bgfix">
						<div id="awards" class="bgfix"></div>
						<div id="platebtn" class="bgfix"></div>
						<p id="msg"></p>
						<p id="init" class="dn">初始化中，请稍后<span></span></p>
					</div>
				</div>
            	<div id="gift" class="bgfix"></div>
            	<div id="lotteryMsg" class="dn">
					<div class="getPrize clear">
	    				<div class="prizeIcon fl"></div>
	    				<div class="prizeWord fl">
		        			<div class="onGet"></div>
		        			<div class="prizeName"> <span class="msg-center">点击<a href="/sysMessage/sysMessagePage" target="_blank">消息中心</a>了解详情</span></div>
		        			<div class="getPrizeSteps"></div>
	    				</div>
					</div>
				</div>
            </div>
        </div>
        <div class="rotate-right fr">
            <div class="pd w108">参与资格</div>
            <p class="lh36">每个账户每天登录一次即可获得一次抽奖机会；</p>
            <div class="investNow clear">
                <diV class="invest-condition fl">投资满一万元可再获得一次抽奖机会；</diV>
                <a class="invetst-btn-small fl an-icon" href="/borrow/borrowList" target="_blank"></a>
            </div>
            <p class="lh36">每天分享活动链接给5位朋友可再次获得一次抽奖机会；</p>
            <div class="share-box clear">
                <div class="share-to fl">分享到：</div>
                <div class="share-icon fl">
					<!-- JiaThis Button BEGIN -->
					<div class="jiathis_style">
						<a class="jiathis_button_qzone"></a>
						<a class="jiathis_button_tsina"></a>
						<a class="jiathis_button_tqq"></a>
						<a class="jiathis_button_weixin"></a>
						<a class="jiathis_button_renren"></a>
						<a class="jiathis_button_xiaoyou"></a>
						<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
						
					</div>	
					<!-- JiaThis Button END -->
                </div>
            </div>
          <div class="">
            <div class="myPrizeWrap">
            <div class="my-record">
                <div class="pd w158 mr">我的中奖纪录</div>
                <div class="dot-more" ><a href="/sysMessage/sysMessagePage" target="_blank" style="color:#fff">更多</a></div>
                <div class="line"></div>
            </div>
            <div class="myPrize clear">
                <table cellpadding="0" cellspacing="0" id="myPrizeList">
                </table>
            </div>
            </div>
            <div class="PrizeListWrap">
            <div class="pd w108">获奖名单</div>
               <div class="prizeList clear">
            <ul id="prizeList">
            </ul>
        </div>
            </div>
            </div>
            <div class="lookForward2 an-icon hide"></div>
        </div>
    </div>
    <div class="rotate-rules">
       <div class="content clear">
           <div class="content-side fl cs-bg" style="background: #0063a2;">规则</div>
           <div class="content-main fl">
               <p>1. 每位用户每日抽奖就会上限为三次，抽奖次数每日零点清零，用户需要在当日使用，用户可以在次日再次完成获得新的三次抽奖机会；</p>
               <p>2. 提现抵扣券在用户抽中阳光奖（提现抵扣券）时，系统会直接自动发送，5元现金奖励会在抽奖次日由客服确认后，发送到礼品盒；</p>
               <p>3. 三等奖每天都有机会获得一次，仅限当日有效，并且只用于当日首次单笔投资奖励；</p>
               <p>4. 当日内，一等奖不可与三等奖叠加使用，同时中奖，以最高收益奖励为准。一等奖奖励有效时间周期为自中奖日起7日内有效。
                   <br/>&nbsp;&nbsp;&nbsp;&nbsp;超出使用周期，奖励失效。 一等奖奖励限制单笔投资10万元以下，超过10万元的部分按照正常收益计算；</p>
               <p>5. 实物礼品微积金会在活动结束后，由客服电话与用户确认收货信息5个工作日内发送，请用户在活动期间保持手机畅通；</p>
               <p>6. 微积金平台拥有活动的最终解释权。</p>
           </div>
       </div>
    </div>
        <div class="wram-tip">温馨提示：用户通过非正常手段、采用虚假信息、舞弊等行为获得的奖励权益，网站有权取消其获奖资格，并保留包括对其账号进行冻结、封号的权利。</div>
    </div>
</div>
<div class="an-third-sector">
   <div class="an-third-cnt main">
    <div class="asc-title clear" style="border-bottom: 1px solid #db899d;">
        <div class="No fl No2 an-icon" style="width: 76px;"></div>
        <div class="title fl">定时礼包&nbsp;&nbsp; <span>8点见！</span></div>
        <div class="time fr">12月30日- 1月8日</div>
    </div>
       <div class="content clear">
           <div class="content-side fl cs-bg" style="background: #9d0025;">规则</div>
           <div class="content-main fl">
               <p>每晚8点，微积金平台会推出一支超高年化收益16.6%的活动标，每位用户每日限投1次，
               <br/>每次投标上限为10000元抢标达人会是你吗！</p>

           </div>
       </div>
      <div class="end an-icon"></div>
   </div>
</div>
<!------尾部------>
<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
<!--turnlate-->
<script type="text/javascript" src="/js/prize/prize.js"></script>
<script type="text/javascript">
	$(function(){
		$('#lotteryMsg').dialog({
			modal: true,
			width: 900,
			height: 400,
			resizable: false,
			title: '获奖信息',
			autoOpen: false
		}); 
		turnplate.logined = ${ not empty loginedSession ?true:false};
		turnplate.lotteryTime = $("#chooseCount").val();
		turnplate.init();	
		prizeChooseCount();
		myPrizeChooseList();
		prizeChooseList(); 
		shareLink();
	})
</script>
<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
</body>
</html>

