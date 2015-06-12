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
			<div class="au-box" id="fee_">
				<div class="au-title">账户安全</div>
				<div class="account-question">
				<ul>
				<li><a class="invest-title">如何修改登录密码？</a>
				<div class="invest-answer">
					步骤1：登录微积金账户，点击左侧导航栏中的“安全中心”；<br>
					步骤2：点击右侧“修改登录密码”；<br>
					步骤3：输入“原始密码”、“新密码”、“确认密码”，完成修改。<br>
				</div>
				</li>
				<li><a class="invest-title">如何找回登录密码？ </a>
					<div class="invest-answer">
					    如果您遗忘了密码，可以在网站用户登录页面，点击“忘记密码”按钮，选择绑定邮箱或注册手机号找回密码。<br>
						1.点击首页“登录”- 点击“忘记密码”。<br>
						2.输入您的注册电子邮箱。<br>
						3.输入您的用户名。<br>
						4.发送验证码成功。<br>
						5.输入发送给您的验证码。<br>
						6.根据提示，输入新密码，点击确认后，重置密码成功，可使用新密码登录微积 金帐号。
					</div>
				</li>
				<li><a class="invest-title">什么是支付密码？如何修改？ </a>
				<div class="invest-answer">为了让您的提现操作更安全，提现时会要求您输入支付密码，支付密码默认为您的登录密码。
															请您及时进入“账户管理“-”安全中心“中修改您的支付密码。
				</div>
				</li>
				<li><a class="invest-title">如何让我的账户密码更安全？ </a>
				<div class="invest-answer">
				请仔细阅读以下常见的安全措施：<br>
								1.请使用较长、复杂的字母数字组合以提高密码强度，不要用生日等容易被别人 猜中的密码；<br>
								2.请记住不要与任何人共享您的密码。如果您已经与他人共享了密码，微积金平 台强烈建议您尽快更改；<br>
								3.您可能在不经意间使计算机受到病毒、木马、间谍软件、广告软件的感染，这 些软件也可能记录并盗走您的密码。所以，请安装和使用杀毒软件。<br>
								4.请尽快完成实名认证，邮箱认证，以保证资金提现账户的唯一性。<br>
				</div>
				</li>
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
				
				</ul>
				</div>
				
			<!--  	<h3 class="font18">投资人费用</h3>
				<p>除提现费用外，其他费用微积金平台已预先扣除，投资标的显示的年化收益为用户纯收益，详情请参考<a href="/borrow/agreementVfunding/1937" style="color: #00a0e9;">《微积金网站服务协议》</a>。</p>

				<table width="780" cellpadding="0"  cellspacing="0"  class="feeForm">
					<tr>
						<td width="195" height="66" class="gray-color">充值费用</td>
						<td width="585">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;由第三方支付通道、网银收取的充值费用成本，目前由微积金承担。用户充值多少，实际到账
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;多少。
						
						</td>
					</tr>
					<tr>
						<td width="195" height="114" class="gray-color">提现费用</td>
						<td width="585">
							<p>单笔最高提现金额为50,000元。每日最高提现金额不得超过500,000元。</p>
							<p>每次提现收取3元/笔手续费</p>
						
							<P>充值15天内未完全投标额部分，额外加收提现金额的0.3%手续费</p>
						</td>
					</tr>
					<tr>
						  <td width="195" height="114" class="gray-color "  >担保监管费</td>
						<td width="585"  class="important">
							投资标的金额的年化1%。
						</td>
					</tr>	
				 <tr>
						<td width="195" height="114" class="gray-color ">平台服务费</td>
						<td width="585"   class="important">
						收取投资者所得利息的10%作为平台服务费。
						</td>
					</tr>	
				 <tr>
						<td width="195" height="114" class="gray-color">VIP服务费</td>
						<td width="585" >
						<p>150元/年，可以优先获得平台本息垫付资格。</p>
						</td>
					</tr>	
				</table>

				<h3 class="font18">贷款人费用</h3>
				<table width="780" cellpadding="0" cellspacing="0" class="loanForm">
					<tr>
						<td width="195" height="66" class="gray-color">借款服务费<br />
							（针对信用标、净值标）
						</td>
						<td width="585">
							<p>信用等级：AA A B C D E HR</p>
							<p>费率： 1% 2% 2.5% 3% 3.5% 4% 6%</p>
						</td>
					</tr>
					<tr>
						<td height="50" class="gray-color">坏账准备金</td>
						<td><p>平台服务费的10%（微积金平台出资）</p></td>
					</tr>
					<tr>
						<td height="70" class="gray-color">借款管理费<br /> （针对信用标、净值标）
						</td>
						<td><P>0.4%/月</P></td>
					</tr>
					<tr>
						<td height="50" class="gray-color">提现费用</td>
						<td><p>参见投资人费用</p></td>
					</tr>
					<tr>
						<td height="50" class="gray-color">充值费用</td>
						<td><p>参见投资人费用</p></td>
					</tr>
					<tr>
						<td height="70" class="gray-color">逾期罚息</td>
						<td>
							<p>罚息总额=逾期本息*对应罚息利率*逾期天数</p>
							<P>罚息利率 0.05%（1-30天）； 0.1%（31天以上）</P>
						</td>
					</tr>
					<tr>
						<td height="70" class="gray-color">逾期管理费</td>
						<td>
							<p>逾期管理费=逾期本息*逾期管理费率*逾期天数</p>
							<p>逾期管理费率 0.1%（1-30天）； 0.5%（31天以上）</p>
						</td>
					</tr>
					<tr>
						<td height="200" class="gray-color">费率范围</td>
						<td>
							<P>微积金目前的利率范围为10%-24%。</P>
							<P>在微积金平台上借贷的最高年利率设定为同期银行借款年利率的4倍。且随着银行借款利率的调整，微积金的利率上限也将随之调整。</P>
							<P>注：</P>
							<p>1.微积金的利率的调整会在商业银行借款年利率调整后1个月内进行调整。</p>
							<p>2.在利率调整之前成功的借款不受调整的影响。</p>

						</td>
					</tr>
				</table>-->
			</div>
		</div>
	</div>


	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>


</body>
</html>
