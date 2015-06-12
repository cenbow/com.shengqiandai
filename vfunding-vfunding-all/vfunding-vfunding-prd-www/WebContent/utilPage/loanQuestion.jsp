<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="${empty charset ?'utf-8':charset}">
<meta name="description" content="微积金(www.vfunding.cn) 整合了民营汽车借贷机构、二手车商、第三方汽车服务机构等传统领域企业，形成了独特的针对汽车提供投融资对接服务的创新型O2O互联网汽车金融服务模式。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、汽车贷款等。" />
<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|微贷|红包|基金|微基金|小额贷款|微财富|宜车贷|二手车抵押|车贷|托管" />
<title>微积金，中国领先的互联网汽车金融服务平台_理财_理财产品_投融资_理财需求_投资_投资标的_财富_财富成长_手机理财_微信理财_微理财</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/helpCenter.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script>
	$(function(){
		$(".invest-question li:last,.account-question li:last").css("border-bottom", "none");
		$(".invest-question li a,.account-question li a").click(function() {
			$(".invest-answer").hide();
			$(this).siblings().show();
		});
	});
</script>
</head>
<body>
	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>
	<!--中间-->
	<div class="content clear">
		<jsp:include page="${pageContext.request.contextPath }/utilPage/helpMenu.jsp"></jsp:include>
		<div class="about-us-right fr box-shadow">
			<div class="au-box">
				<div class="au-title">充值提现</div>
				<div class="invest-question">
				<ul>
				<li><a class="invest-title">如何充值？</a>
				<div class="invest-answer">1.选择快捷支付，添加一张银行卡作为快捷支付充值银行卡完成充值。<br>
				2.选择网银支付， 只需保证充值银行卡已开通网上银行，选择充值银行，输入充值金额，跳转至网银充值界面，完成充值。<br>
				温馨提醒：<br>
						1.用户添加银行卡后就可开通快捷支付后进行快速充值。快捷支付银行卡开通以后，用户充值选择快捷支付通道时只可使用该银行卡进行快捷支付充值，该银行卡默认为用户的提现银行卡，用户只可使用该银行卡提现。<br>
						2.用户需要更换快捷支付银行卡时，必须在用户账户余额为0元（无可用金额和冻结金额）的情况下，进行添加更换。<br>
						3.用户在没有开通快捷支付的情况下， 提现只需保证添加的银行卡和账户实名一致，网银充值无限制。<br>
				</div>
						</li>
				<li><a class="invest-title">为何我的充值失败了？</a>
				<div class="invest-answer">
												1.先检查银行卡是否余额不足，或充值金额超过银行的单笔限额。<br>
												2.请确认您的银行卡已经开通了网上银行服务。（可与银行卡发卡行确认）<br>
												3.如使用网银支付需要安装所需的数字证书。<br>
												4.建议使用Microsoft IE浏览器操作,并将IE设置恢复成默认级别。<br>
												5.如确认其他没问题，还可等待银行数据传输后10分钟，再查看账户金额。<br>
												6.如还无法解决问题，请拨打客服电话4009919999或在微积金网站首页联系在线客服。<br>
				</div>
				</li>
						<li><a class="invest-title">为何我的充值有限额？</a>
							<div class="invest-answer">微积金不对充值金额做限制，如遇到金额受限，请先确认您是否对所持有的银行卡做了单笔额度限制,或者查询您所持有的银行卡的单笔(日)限额。</div>
						</li>
						<li><a class="invest-title">为什么充值的时候提示充值金额超过每日支付限额？</a>
						<div class="invest-answer">每日支付限额由银行每日支付限 额、第三方支付平台每日支付限额和用户自己设定的每日支付限额三者共同决定，在实际使用中三者取最小值。例如：浦发银行的网银每日支付限额是100万，但 是浦发银行新浪支付签协议的时候，规定用户在新浪支付使用浦发银行网银的时候每日支付限额为40万，用户自己设定的每日支付限额为50万，此时用户可以 使用的每日支付限额为40万。</div>
						</li>
						<li><a class="invest-title">如何添加银行卡？</a>
						<div class="invest-answer">
						如用户已经为银行卡开通快捷支付，该银行卡默认为提现银行卡。<br>
						如未开通快捷支付，可添加一张银行卡用于提现：<br>
							步骤1：登录微积金账户，点击账户左侧导航“我的账户”，点击“银行卡”选择“添加一张银行卡";<br>
							步骤2：输入绑定的银行信息，选择是否开通快捷方式，点击添加。<br>
							步骤3：完成绑定。 <br>
							温馨提示:<br>
							添加的银行卡必须和账户实名一致,以确保账户资金安全。
						</div>
						</li>
						<li><a class="invest-title">如何提现？</a>
						<div class="invest-answer">
						步骤1：登录微积金账户，点击左侧账户导航"账户提现"。<br>
						步骤2：选择提现账户，输入提现金额、支付密码，点击“确认提 交”，完成提现。<br>
						温馨提示:<br>
						添加的银行卡必须和账户实名一致,以确保账户资金安全。
						</div>
						</li>
						<li><a class="invest-title">提现金额是否有限制？</a>
						<div class="invest-answer">提示：新浪托管第三方每日提现规定，单笔限额为5万，每日最高提现额度为50万</div>
						</li>
						<li><a class="invest-title">提现后多久会到账？</a>
						<div class="invest-answer">工作日14:00前取现T+1入账，14:00后取现T+2入账（周末和节假日顺延）当日具体到账时间以银行出账时间为准。</div>
						</li>
				</ul>
				</div>
			</div>
		</div>
	</div>
	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
</body>
</html>