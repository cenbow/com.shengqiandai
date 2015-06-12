
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/helpCenter.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script>
	$(function() {
		if(window.location.hash == '#fee'){
			$(".au-box").hide();
			$('#fee_').show();
			$(".au-nav").find("li").eq(3).addClass("au-selected")
		}
		
		$(".au-nav li").click(
				function() {

					$(this).addClass("au-selected").siblings().removeClass(
							"au-selected");

					var index = $(".au-nav").find("li").index($(this));

					$(".au-box").eq(index).show().siblings().hide();

				});

		$(".invest-question li:last,.account-question li:last").css(
				"border-bottom", "none");

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


		<div class="about-us-left fl box-shadow">
			<h1>常见问题</h1>
			<div class="au-nav">
				<ul>
					<li>注册登录</li>
					<li>充值提现</li>
					<li>投资还款</li>
					<li>账户安全</li>
					<li>网站资费</li>
					<li>常用名词解释</li>
				</ul>
			</div>
		</div>

		<div class="about-us-right fr box-shadow">

			<div class="au-box">

				<div class="au-title">投资问题</div>

				<div class="invest-question">
					<ul>
						<li><a class="invest-title">能提前收回借出去的钱吗？</a>
							<div class="invest-answer">不可以。一旦您的借款成功后，不可提前收回已借出资金，除非借入者自愿提前还款。</div>

						</li>
						<li><a class="invest-title">如何收取还款？</a>
							<div class="invest-answer">借款人在规定的还款时间内将钱充值到与微积金合作的第三方支付平台上，微积金系统会按出借比例将款项转入投资人的微积金账户余额中。出借人可按个人需求选择提现或继续投资。</div>
						</li>

						<li><a class="invest-title">电子合同有效吗？</a>
							<div class="invest-answer">我国新合同法第十一条规定:
								书面形式合同是指合同书、信件和数据电文(包括电报、电传、传真、电子数据交换和电子邮件)等可以有形地表现所载内容的形式。因此电子合同属于书面形式的合同一种，是受到法律保护的。另外微积金平台中的合同文本都由经济法律师起草的，保证了经过微积金这个平台的交易是具备法律效力的。
							</div></li>
						<li><a class="invest-title">投资人的本金和收益是否可以得到保障？</a>
							<div class="invest-answer">微积金网推荐的本金保障计划及抵押标项目均由合作机构提供100%本金担保，由微积金网提供风险备用金保障，充分保障投资人本金及投资收益。
							</div></li>

						<li><a class="invest-title">借款人逾期了怎么办？</a>
							<div class="invest-answer">若借款人逾期，则推荐借款项目会按照合作机构100%本金担保、微积金本金保障计划在逾期当日内代偿该期本金和收益。
							</div></li>
						<li><a class="invest-title">投资后什么时候开始计算收益？</a>
							<div class="invest-answer">抵押标项目开始计算收益的时间指微积金网将筹款资金划款至借款人账户的时间。
								例如：微积金网在2013年11月18日将借款人筹款资金打到借款人账户，则项目计算收益的时间为：2013年11月18日。</div></li>
						<li><a class="invest-title">投资人申请提现后何时到账？</a>
							<div class="invest-answer">微积金平台资金由新浪支付第三方托管，根据托管规则，工作日在14点之前提现到账时间是T+1，在14点之后提现到账时间是T+2。（周末和节假日顺延），当日具体到账时间以银行出账时间为准。


							</div></li>
					</ul>
				</div>

			</div>

			<div class="au-box">
				<div class="au-title">贷款问题</div>
				<div class="step4-pic"></div>
			</div>
			<div class="au-box">

				<div class="au-title">账户问题</div>

				<div class="account-question">
					<ul>
						<li><a class="invest-title">如何在微积金上注册？</a>
							<div class="invest-answer">
								步骤1：打开微积金网站，点击网站右侧“免费注册”按钮；<br />
								步骤2：进入注册页面后，填写基本注册信息及手机号，获取并输入手机验证码， 其中带星号的为必填；<br /> 步骤3：注册成功。
							</div></li>
						<li><a class="invest-title">如何找回登录密码？</a>
							<div class="invest-answer">
								如果您遗忘了密码，可以在网站用户登录页面，点击“忘记密码”按钮，选择绑定邮箱或注册手机号找回密码。<br />
								1.点击首页“登录”- 点击“忘记密码”。<br /> 2.输入您的注册电子邮箱。<br /> 3.输入您的用户名。<br />
								4.发送验证码成功。<br /> 5.输入发送给您的验证码。<br />
								6.根据提示，输入新密码，点击确认后，重置密码成功，可使用新密码登录微积 金帐号。
							</div></li>

						<li><a class="invest-title">如何修改登录密码？</a>
							<div class="invest-answer">
								步骤1：登录微积金账户，点击左侧导航栏中的“安全中心”；<br /> 步骤2：点击右侧“修改登录密码”；<br />
								步骤3：输入“原始密码”、“新密码”、“确认密码”，完成修改。

							</div></li>
						<li><a class="invest-title">什么是交易密码？如何修改？</a>
							<div class="invest-answer">
								为了让您的提现操作更安全，提现时会要求您输入交易密码，交易密码默认为您的登录密码。<br />请您及时进入“账户管理“-”安全中心“中修改您的交易密码。
								如何让我的账户密码更安全？<br /> 请仔细阅读以下常见的安全措施：<br />
								1.请使用较长、复杂的字母数字组合以提高密码强度，不要用生日等容易被别人 猜中的密码；<br />
								2.请记住不要与任何人共享您的密码。如果您已经与他人共享了密码，微积金平 台强烈建议您尽快更改；<br />
								3.您可能在不经意间使计算机受到病毒、木马、间谍软件、广告软件的感染，这
								些软件也可能记录并盗走您的密码。所以，请安装和使用杀毒软件。<br /> 4.请尽快完成实名认证，以保证资金提现账户的唯一性。


							</div></li>

						<li><a class="invest-title">什么是第三方登录？</a>
							<div class="invest-answer">当您完成注册，成为微积金用户后，可以进入“账户管理”-“账户绑定”中绑定您的微博账号及QQ号。之后，您可以在登录时选择通过微博账号或QQ号登录。


							</div></li>
						<li><a class="invest-title">如何充值？</a>
							<div class="invest-answer">
								1.选择快捷支付，添加一张银行卡作为快捷支付充值银行卡完成充值。<br />
								2.选择网银支付， 只需保证充值银行卡已开通网上银行，选择充值银行，输入充值金额，跳转至网银充值界面，完成充值。<br />
								 <br /> <span>温馨提醒：</span><br />
								1.用户添加银行卡后就可开通快捷支付后进行快速充值。快捷支付银行卡开通以后，用户充值选择快捷支付通道时只可使用该银行卡进行快捷支付充值，改银行卡默认为用户的提现银行卡，用户只可使用该银行卡提现。<br />
								2.用户需要更换快捷支付银行卡时，必须在用户账户余额为0元（无可用金额和冻结金额）的情况下，进行添加更换。<br/>
								3.用户在没有开通快捷支付的情况下，网银支付充值和提现只需保证同名进出，无其他限制。

							</div></li>


						<li><a class="invest-title">为何我的充值失败了？</a>
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
								步骤2：输入绑定的银行信息，选择是否开通快捷方式，点击添加;<br /> 步骤3：完成绑定。
							</div></li>
						<li><a class="invest-title">如何提现？</a>
							<div class="invest-answer">
								步骤1：登录微积金账户，点击左侧账户导航"账户提现"。<br />
								步骤2：选择提现账户，输入提现金额、交易密码、手机验证码，点击“确认提 交”，完成提现。
							</div></li>
						<li><a class="invest-title">提现金额是否有限制？</a>
							<div class="invest-answer">提示：新浪托管第三方每日提现规定，每笔限额5万，最高提现额度50万</div></li>
						<li><a class="invest-title"> 提现后多久会到账？ </a>
							<div class="invest-answer">工作日14:00前取现T+1入账，14:00后取现T+2入账（周末和节假日顺延）当日具体到账时间以银行出账时间为准。
							</div></li>
					</ul>
				</div>
			</div>
			<div class="au-box" id="fee_">

				<div class="au-title">网站资费</div>

				<h3 class="font18">投资人费用</h3>
				<!-- <p>除第三方平台外，微积金平台不向投资人收取投资中介费用，具体涉及投资人的费用如下：</p> -->
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	免费

				<table width="780" cellpadding="0"  cellspacing="0"  class="feeForm">
					<tr>
						<td width="195" height="66" class="gray-color">充值费用</td>
						<td width="585">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	免费
						</td>
					</tr>
					<tr>
						<td width="195" height="114" class="gray-color">提现费用</td>
						<td width="585">
							 <p>单笔最高提现金额为500,000元。每日最高提现金额不得超过5,000,000元。</p>
							<p>提现金额小于10000元收取5元/笔手续费</p>
							<p>提现金额大于10000元收取10元/笔手续费</p>
							<p>充值15天内未完全投标额部分，额外加收本金0.5%的手续费</p>
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
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 150元/年，可以优先获得平台本息垫付资格。
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
				</table>
			</div>
			<div class="au-box" style="display: block;">

				<div class="au-title">常见问题</div>

				<div class="invest-question">
					<ul>
						<li><a class="invest-title">如何成为微积金网的理财投资人？</a>
							<div class="invest-answer">年满18周岁，具有完全民事权利能力和民事行为能力，通过提交注册、实名认证即可成为微积金理财投资人。
							</div></li>
						<li><a class="invest-title">投资人是否需要支付费用？</a>
							<div class="invest-answer">投资人在微积金充值免受充值费；提现时，提现金额小于10000元收取提现通道费5元，大于10000元收取提现通道费10元。<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;基于微积金抵押服务模式与本息保障计划，微积金平台针对不同投资标的代收很小比例的担保监管费与服务费，具体见网站资费。<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
微积金平台借款标的公示的预期纯收益为用户投资后的实得净收益。
							
							</div></li>

						<li><a class="invest-title">投资人的本金和收益是否可以得到保障？</a>
							<div class="invest-answer">微积金目前只做抵押标，实施本金保障计划的所有借款标的均由合作担保机构及微积金风险保障金账户提供100%本息担保，充分保障投资人本金及收益安全。

							</div></li>
						<li><a class="invest-title">借款人逾期了怎么办？</a>
							<div class="invest-answer">若借款人逾期，则推荐借款项目会按照合作机构100%本金担保、微积金本金保障计划在逾期当日内代偿该期本金和收益。

							</div></li>

						<li><a class="invest-title">投资后什么时候开始计算收益？</a>
							<div class="invest-answer">抵押标项目开始计算收益的时间指微积金网将筹款资金划款至借款人账户的时间。例如：微积金网在2013年11月18日满标后将借款人筹款资金打到借款人账户，则项目计算收益的时间为：2013年11月18日。


							</div></li>
						<li><a class="invest-title">投资人申请提现后何时到账？</a>
							<div class="invest-answer">工作日，在14点之前提现到账时间是T+1，在14点之后提现到账时间是T+2。（周末和节假日顺延）当日具体到账时间以银行出账时间为准。


							</div></li>


						<li><a class="invest-title">为什么充值的时候提示充值金额超过每日支付限额？</a>
							<div class="invest-answer">每日支付限额由银行每日支付限额、第三方支付平台每日支付限额和用户自己设定的每日支付限额三者共同决定，在实际使用中三者取最小值。例如：浦发银行的网 银每日支付限额是100万，但是浦发银行和网银在线签协议的时候，规定用户在网银在线使用浦发银行网银的时候每日支付限额为40万，用户自己设定的每日支付限额为50万，此时用户可以使用的每日支付限额为40万。

							</div></li>
							
					   <li><a class="invest-title">微积金VIP客户尊享服务是什么？</a>
							<div class="invest-answer">
							
							微积金VIP客户尊享服务”专为微积金贵宾客户打造的个性化、高品质的投资理财服务品牌。秉承“以客为尊，不断创新”的宗旨，由微积金在客户独享的理财空间内，为客户提供“一对一”的优质创新互联网金融投资理财服务，让客户尽享微积金带来的尊崇新型理财生活。同时，依托微积金服务资源与网络、专业的服务品质和多元化的投资理财产品，将全面照顾客户的理财需求，助客户实现财富的保值增值。 

<br/>1、享受“微积金本金保障计划”外更进一步的“本息保障计划”服务； 

<br/>2、享有微积金观察员身份，可免费参加微积金实地考察； 

<br/>3、尊享一对一个人财富管理规划顾问式服务； 

<br/>4、 拥有微积金理财师申请资格 

<br/>5、 提供微积金免费微信、短信通知服务，及时传递最新的金融信息与市场资讯； 

<br/>6、免费参加微积金全年邀约活动（美食之旅、红酒品鉴、文化之旅、理财讲座）； 

<br/>7、优先享受微积金平台各项促销、优惠服务； 
							

							</div>
							
							
							</li>	
							
							
							
							
							
							
							
							
							
					</ul>
				</div>
			</div>
			<div class="au-box">
				<div class="au-title"  style="margin-bottom:20px;">常用名词解释</div>

				<div class="contact">

					<p style="text-indent: 10px;">
						<b>借款人:</b>
					</p>
					<p style="text-indent: 10px;">也称贷款人（资金需求方），是指已经或准备在微积金网站上进行借款活动的用户。凡18周岁以上的中国大陆地区公民，都</p>
					<p style="text-indent: 10px;">可以成为借款人。</p>
				</div>
				<div class="contact">
					<p style="text-indent: 10px;">
						<b>出借人：</b>
					</p>
					<p style="text-indent: 10px;">也称理财人（或投资人、资金出借方），是指已经或准备在微积金网站上进行出借活动的用户。凡18周岁以上的中国大陆地</p>
					<p style="text-indent: 10px;">区公民，都可以成为理财人。</p>
				</div>
				<div class="contact">
					<p style="text-indent: 10px;">
						<b>借款列表：</b>
					</p>
					<p style="text-indent: 10px;">当借款人成功发布一个借款请求时，我们将包含各种贷款信息的该请求称为一个借款列表。</p>

				</div>

				<div class="contact">
					<p style="text-indent: 10px;">
						<b>招标：</b>
					</p>
					<p style="text-indent: 10px;">是指一个借款人发出一个借款请求，即创建一个借款列表的行为。</p>

				</div>
				<div class="contact">
					<p style="text-indent: 10px;">
						<b>投标：</b>
					</p>
					<p style="text-indent: 10px;">是指出借人将其账户内指定的金额出借给其指定的借款列表的行为。</p>

				</div>
				<div class="contact">
					<p style="text-indent: 10px;">
						<b>满标：</b>
					</p>
					<p style="text-indent: 10px;">是指一个借款列表在投标期限内足额筹集到所需资金。</p>

				</div>

				<div class="contact">
					<p style="text-indent: 10px;">
						<b>放款：</b>
					</p>
					<p style="text-indent: 10px;">指一个借款列表满标后且已符合放款标准，微积金将把所筹资金打入借款人在微积金的账户中，即成功贷款。</p>
				</div>


				<div class="contact">
					<p style="text-indent: 10px;">
						<b>流标：</b>
					</p>
					<p style="text-indent: 10px;">是指一个借款列表的投标期限已过，但是贷款没有足额筹集齐或者在规定期限内未签约成功，即贷款失败。</p>
				</div>


				<div class="contact">
					<p style="text-indent: 10px;">
						<b>提现：</b>
					</p>
					<p style="text-indent: 10px;">当微积金客户需要将微积金账户里的资金转移的时候，可以申请提现，提现仅能将金额提至经过实名认证的客户银行卡中。在用户发起提现申请后，微积金会在1-2个工作日内完成资金划转。</p>
				</div>


				<div class="contact">
					<p style="text-indent: 10px;">
						<b>投标金额：</b>
					</p>
					<p style="text-indent: 10px;">是指出借人对借款列表进行投标的金额。</p>
				</div>


				<div class="contact">
					<p style="text-indent: 10px;">
						<b>帐户总额：</b>
					</p>
					<p style="text-indent: 10px;">指用户账户的所有金额（含冻结金额和可用金额）。</p>
				</div>



				<div class="contact">
					<p style="text-indent: 10px;">
						<b>可用金额：</b>
					</p>
					<p style="text-indent: 10px;">是指用户可自由支配的金额。</p>
				</div>


				<div class="contact">
					<p style="text-indent: 10px;">
						<b>冻结金额：</b>
					</p>
					<p style="text-indent: 10px;">用户对某借款列表进行投标后，在此列表的招标期结束之前，这部分投标金额将被锁定，“冻结金额”是指因这类型锁定金额</p>
					<p style="text-indent: 10px;">的总额。如果列表放款，这部分金额将转给该列表的借款人；如果该列表流标，这部分金额将立即变为用户的可用金额。</p>

				</div>


				<div class="contact">
					<p style="text-indent: 10px;">
						<b>等额本息：</b>
					</p>
					<p style="text-indent: 10px;">等额本息还款法是一种被广泛采用的还款方式，在还款期内，每月偿还同等数额的贷款(包括本金和利息)。但每月偿还的本金</p>
					<p style="text-indent: 10px;">和利息的比例不同，借款人每月还款额中的本金比重逐月递增、利息比重逐月递减。
						其计算公式如下：</p>

					<p style="text-indent: 10px;">每月还款额=[贷款本金×月利率×（1+月利率）^还款总期数]÷[（1+月利率）^还款总期数-1],因计算中存在四舍五入，最后</p>

					<p style="text-indent: 10px;">一期还款金额与之前略有不同。</p>

				</div>
			</div>

		</div>

	</div>


	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>


</body>
</html>
