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

				<div class="au-title">投资还款</div>

				<div class="account-question">
					<ul>
						<li><a class="invest-title">投资后什么时候开始计算收益？</a>
							<div class="invest-answer">
								借款标开始计算收益的时间指微积金网将筹款资金划款至借款人账户的时间（满标审核成功）。 例如：微积金网在2013年11月18日将借款人筹款资金打到借款人账户，则项目计算收益的时间为：2013年11月18日。
							</div></li>
						<li><a class="invest-title">能提前收回借出去的钱吗？</a>
							<div class="invest-answer">
								不可以。一旦您的借款成功后，不可提前收回已借出资金，除非借入者自愿提前还款。
							</div></li>

						<li><a class="invest-title">如何收取还款？</a>
							<div class="invest-answer">
								借款人在规定的还款时间内将钱充值到与微积金合作的第三方支付平台上，微积金系统会按出借比例将款项转入投资人的微积金账户余额中。出借人可按个人需求选择提现或继续投资。

							</div></li>
						<li><a class="invest-title">投资人的本金和收益是否可以得到保障？</a>
							<div class="invest-answer">
								微积金目前只做抵押标，实施本金保障计划的所有借款标的均由合作担保机构及微积金风险保障金账户提供100%本金担保，充分保障投资人本金及收益安全。


							</div></li>

						<li><a class="invest-title">借款人逾期了怎么办？</a>
							<div class="invest-answer">若借款人逾期，则推荐借款项目会按照合作机构100%本金担保、微积金本息保障计划在逾期当日内代偿该期本金和收益。


							</div></li>
						<li><a class="invest-title">电子合同有效吗？</a>
							<div class="invest-answer">
								我国新合同法第十一条规定: 书面形式合同是指合同书、信件和数据电文(包括电报、电传、传真、电子数据交换和电子邮件)等可以有形地表现所载内容的形式。因此电子合同属于书面形式的 合同一种，是受到法律保护的。另外微积金平台中的合同文本都由经济法律师起草的，保证了经过微积金这个平台的交易是具备法律效力的。

							</div></li>
								<li><a class="invest-title">礼品盒</a>
							<div class="invest-answer">
								<h3>现金红包获取渠道以及使用方法</h3>
								<h4>获取渠道：</h4>
								1.	微积金新手任务奖励：注册后完成新手任务当中的两个首投任务就可获得现金红包。<br>
								2.	活动获取：参与平台活动获取现金红包，微积金平台每次活动的时间和奖励金额都不定，具体奖励金额和发放时间以活动规则为准。<br>
								3.	平台理财师返利：您邀请的好友完成投资，返利将以现金红包形式发放。<br>
								4.	加息卡返利：投资时使用加息卡，加息收益将以现金红包形式发放。
								<h4>使用方法：</h4>
								获得的红包，微积金将在规定时间内发放至“我的账户”礼品盒中红包一栏，生效后点击使用即充值到账户余额中。<br>
								温馨提示：<br>
								所有渠道获取的现金红包都是15天有效期限，从生效日开始计算，失效无用。
								<h3>加息卡获取渠道以及使用方法</h3>
								<h4>获取渠道：</h4>
								1.	理财师邀请新用户首投获得加息：您邀请的好友首次投资超过百元，您将获得0.3%加息卡。<br>
								2.	活动获取：参与平台活动获取加息卡，微积金平台每次活动的时间和奖励加息份额都不定，具体奖励份额和发放时间以活动规则为准。
								<h4>使用方法：</h4>
								获得的加息卡，微积金将在规定时间内发放至“我的账户”礼品盒中加息一栏，各渠道获取的加息卡在发放后会自动累加，
								获得后可选择投资时使用，投资加息奖励将于满标审核后以现金红包发放至“我的账户”礼品盒中红包一栏，生效后点击使用即充值到账户余额中。
								<h3>提现抵扣券获取渠道以及使用方法</h3>	
								<h4>获取渠道：</h4>
								1.	微积金新手任务奖励：注册成功和完成指定的新手任务就可获得提现抵扣券。
								2.	活动获取：参与平台活动获取提现抵扣券，微积金平台每次活动的时间和奖励额度都不定，具体奖励额度和发放时间以活动规则为准。	
								<h4>使用方法：</h4>
								获得的提现抵扣券，微积金将在规定时间内发放至“我的账户”礼品盒中提现抵扣券一栏，各渠道获取的提现抵扣券在发放后会自动累加，
								获得后可选择提现时使用，用于抵用提现时的手续费。<br>
								温馨提示：<br>
								所有渠道获取的提现抵扣券目前无使用期限限制。
								<h3>其他礼品获取渠道以及使用方法</h3>
								<h4>获取渠道：</h4>
								1.	活动获取：参与平台活动获取其他礼品，微积金平台每次活动的时间和礼品都不定，具体奖励礼品和发放时间以活动规则为准。
								<h4>使用方法：</h4>
								获得的其他礼品，微积金将在规定时间内通知至“我的账户”礼品盒中其他礼品一栏，具体使用方法请根据通知时的提示操作。<br>
								温馨提示：<br>
								获得的其他礼品，若未按通知时的操作使用或操作失误而导致礼品失效，微积金不予以补偿。
															
							</div></li>


				<!--  	<li><a class="invest-title">为何我的充值失败了？</a>
							<div class="invest-answer">
								1.首先，请确认您的银行卡已经开通了网上银行服务。（可与银行卡发开行确认）<br /> 2.确保您的卡上有足够的余额<br />
								3.使用IE浏览器打开充值页面<br /> 4.安装网银支付所需的数字证书


							</div></li>

						<li><a class="invest-title">为何我的充值有限额？</a>
							<div class="invest-answer">微积金不对充值金额做限制，如遇到金额受限，请咨询您所使用的银行卡发开行。

							</div></li>
						<li><a class="invest-title">如何绑定提现银行卡？</a>
							<div class="invest-answer">
								如用户已经为银行卡开通快捷支付，该银行卡默认为提现银行卡。<br/>
								如未开通快捷支付，可选择添加银行卡为提现银行卡：<br/>
								步骤1：登录微积金账户，点击账户左侧导航“我的账户”，点击“银行卡”选择“添加一张银行卡";<br />
								步骤2：输入绑定的银行信息，选择是否开通快捷方式，点击添加。<br /> 
								步骤3：完成绑定。
							</div></li>
						<li><a class="invest-title">如何提现？</a>
							<div class="invest-answer">
							步骤1：登录微积金账户，点击左侧账户导航"账户提现"。<br/>
							步骤2：选择提现账户，输入提现金额、交易密码、手机验证码，点击“确认提 交”，完成提现。

							</div></li>
						<li><a class="invest-title">提现金额是否有限制？</a>
							<div class="invest-answer">提示：新浪托管第三方每日提现规定，每笔限额5万，最高提现额度50万</div></li>
						<li><a class="invest-title"> 提现后多久会到账？ </a>
							<div class="invest-answer">工作日14:00前取现T+1入账，14:00后取现T+2入账（周末和节假日顺延）当日具体到账时间以银行出账时间为准。
							</div></li>-->	
					</ul>
				</div>
			</div>
		</div>

	</div>


	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>


</body>
</html>
