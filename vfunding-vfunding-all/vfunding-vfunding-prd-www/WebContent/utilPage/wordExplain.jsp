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
</head>
<body>
	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>
	<!--中间-->

	<div class="content clear">
		<jsp:include page="${pageContext.request.contextPath }/utilPage/helpMenu.jsp"></jsp:include>

		<div class="about-us-right fr box-shadow">

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
					<p style="text-indent: 10px;">当微积金客户需要将微积金账户里的资金转移的时候，可以申请提现，提现仅能将金额提至经过实名认证的客户银行卡中。</p><p>在用户发起提现申请后，微积金会在1-2个工作日内完成资金划转。</p>
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
				<div class="contact"  id="miao">
					<p style="text-indent: 10px;">
						<b>加息卡：</b>
					</p>
					<p style="text-indent: 10px;">加息卡是微积金送出活动礼券的一种，可以用来提升单次投资的年化收益率，各类加息卡在同时使用时会自动累加，但最高单</p>
					<p style="text-indent: 10px;">个标的的投资收益不超过年化18%。</p>

				</div>
				<div class="contact"  id="miao">
					<p style="text-indent: 10px;">
						<b>资金托管：</b>
					</p>
					<p style="text-indent: 10px;">为微积金的第三方资金托管，资金全程由新浪支付托管，投资和还款资金开设独立账户，实现专款专用，与平台自由资金完全分</p><p style="text-indent: 10px;">离；监管机构及时监控，律师及业内专家多次认证，专为互联网金融商户量身定做，合规透明，避开资金池的风险。
					</p>
<a style="text-indent: 10px; margin-top: 15px; color: #09F;display: block;" href="http://www.vfunding.cn/utilpage/toPage/trust">点击查看更多信息</a>
				</div>
			</div>

		</div>

	</div>


	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>


</body>
</html>
