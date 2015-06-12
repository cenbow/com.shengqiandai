
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="${empty charset ?'utf-8':charset}">
<meta name="description" content="微积金(www.vfunding.cn) 整合了民营汽车借贷机构、二手车商、第三方汽车服务机构等传统领域企业，形成了独特的针对汽车提供投融资对接服务的创新型O2O互联网汽车金融服务模式。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、汽车贷款等。" />
<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|微贷|红包|基金|微基金|小额贷款|微财富|宜车贷|二手车抵押|车贷|托管" />
<title>微积金，中国领先的互联网汽车金融服务平台_理财_理财产品_投融资_理财需求_投资_投资标的_财富_财富成长_手机理财_微信理财_微理财</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<link type="text/css" rel="stylesheet" href="/css/trust.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/aboutUs.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
</head>
<body>
	<input type="hidden" id="yield" value="">
<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>
	<div id="warp">
		<div class="top">
	<div class="topimg"><img src="/images/trustTop.gif" style="width: 100%" height="100%"></div>
		</div>
		<div class="introduction">
			<span>什么是新浪支付存钱罐全托管？<a href="https://pay.sina.com.cn/zjtg">了解更多</a></span>
			<div class="expound">新浪支付（存钱罐）隶属于新浪旗下，是新浪联手汇添富基金主推的名为“存钱罐”第三方支付机构，同时也具有账户余额增值功能。</div>
		</div>
		<div class="center_safe">
			<div class="safe1">
				<div id="text1" class="text">
					<div class="img"></div>
					<p>资金全程由新浪支付托管，投资和还款资金开设独立账户，实现专款专用，与平台自由资金完全分离；监管机构及时监控，律师及业内专家多次认证，专为互联网金融商户量身定做，合规透明，避开资金池的风险。</p>
				</div>
				<div class="bigsafe"></div>
			</div>
			<div class="safe2">
				<div id="text2" class="text">
					<div class="img2"></div>
					<p>微积金与招商银行形成战略合作关系，签署风险备用金托管协议。招行上海分行会对微积金的风险备用金专户资金进行认真、独立的托管，并针对风险备用金专户资金的实际进出情况每月出具托管报告。</p>
				</div>
				<div class="bigsafe2"></div>
			</div>
		</div>
		<div class="center_Profit">
			<div class="left">
				<div class="img3"></div>
				<p class="left_p1">
					新浪支付存钱罐是一款具备货基收益的产品。</br> 账户闲置资金可获得每日收益，真正实现资金不站岗。</br> 天天计息。
				</p>
				</br> </br> <span>七天年化收益率。（<span id="s_date"></span>）</span></br>
				<h1 id="s_yield"></h1>
				<p class="left_p2">*数据来源：新浪存钱罐。货币基金不等同于银行存款，过往业绩不预示其未来表现，市场有风险，投资需谨慎。</p>
			</div>
			<div class="right">
				<p>每日收益</p>
				<h1 id="dayMoney">¥0.00</h1>
				<div class="right_count">
					<span>投资金额</span> <input  type="text"  id="moneyNum"/>
				</div>
				<input class="profit" type="button" value="计算收益" id="moneyBtn">
			</div>
		</div>
		<div class="iMg">
			<div class="iMg_center">
				<div class="iMg_top">
				
				</div>
				<p>手机APP及手机微网站完美实现充值、投资、提现功能。（手机微网站已跟主站完全匹配，手机APP安卓版本春节前夕可实现充值、添加银行卡功能，IOS版本3月可实现充值、提现、添加银行卡功能）。</p>
				<p>体验像余额宝一样简单。随时随地感受财富增值，赶快扫一扫下载官网APP或关注官方微信来试试吧！
</p>
				<div class="iMg_img">
				
				</div>
				<div class="bigimg">
					<div class="bigIMG">
						<div class="bigimg_left">
					<img src="/images/trustNine.gif" width="100%" height="100%" />
						</div>
						<div class="bigimg_right">
							<img src="/images/trustTen.gif" width="100%" height="100%" />
						</div>
					</div>
					
				</div>
			</div>
		</div>
		<div class="footer_text">
			<div class="footer_center_text">
				<div class="footer_center_top">
					
				</div>
				<div class="footer_click">
					
				</div>
			</div>
		</div>
	</div>
		<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
	<script>
		$(function(){
			$.ajax({
				type:'get',
				url:'/sina/query/queryFundYield',
				cache:false,
				async:false,
				success:function(json){
					json = $.parseJSON(json);
					if(json.success){
						var date = json.obj.date;
						var yield = json.obj.yield/100;
						$("#yield").val(yield.toFixed(6));
						$("#s_date").html(date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,date.length)+"");
						$("#s_yield").html(json.obj.yield+"%");
						
					}
					
				}
			});
			
			
			$("#moneyBtn").click(function(){
				var yield = $("#yield").val();
				var moneyNum = parseInt($("#moneyNum").val());
				//alert("moneyNum:"+moneyNum);
				//alert("yield:"+yield);
				var dayMoney = moneyNum*yield/365;
				$("#dayMoney").text("¥"+dayMoney.toFixed(2));
			})
		})
	</script>
</body>
</html>

