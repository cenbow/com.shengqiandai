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

				<div class="au-title">注册登录</div>

				<div class="invest-question">
					<ul>
						<li><a class="invest-title">如何成为微积金网的理财投资人？</a>
							<div class="invest-answer">年满18周岁，具有完全民事权利能力和民事行为能力，通过提交注册在微积金平台注册、实名认证即可成为微积金理财投资人。</div>

						</li>
						<li><a class="invest-title">如何在微积金上注册？</a>
							<div class="invest-answer">步骤1：打开微积金网站，点击网站右侧“免费注册”按钮；</br>
																		步骤2：进入注册页面后，填写基本注册信息及手机号，获取并输入手机验证码， 其中带星号的为必填；</br>
																		步骤3：注册成功。
							</div>
						</li>

						<li><a class="invest-title">若注册时，收不到手机短信验证码，怎么办？</a>
							<div class="invest-answer">1. 请确认手机是否安装短信拦截或过滤软件；（如360安全卫士等）</br>
								2. 请确认手机是否能够正常接收短信（信号问题、欠费停机等）；</br>
								3. 短信收发过程中可能会存在延迟，请耐心等待，短信在30分钟内均有效；</br>
								4. 您还可以在微积金官网首页点击联系在线客服，或拨打客服热线：400-991-9999。</br>
							</div></li>
						<li><a class="invest-title">如何找回登录密码？</a>
							<div class="invest-answer">如果您遗忘了密码，可以在网站用户登录页面，点击“忘记密码”按钮，选择绑定邮箱或注册手机号找回密码。</br>
							1.点击首页“登录”- 点击“忘记密码”。</br>
							2.输入您的注册电子邮箱。</br>
							3.输入您的用户名。</br>
							4.发送验证码成功。</br>
							5.输入发送给您的验证码。</br>
							6.根据提示，输入新密码，点击确认后，重置密码成功，可使用新密码登录微积金帐号。</br>
							</div></li>

						<li><a class="invest-title">什么是第三方登录？ </a>
							<div class="invest-answer">当您完成注册，成为微积金用户后，可以进入“账户管理”-“账户绑定”中绑定您的微博账号及QQ号。之后，您可以在登录时选择通过微博账号或QQ号登录。
							</div></li>
					<!-- 	<li><a class="invest-title">投资后什么时候开始计算收益？</a>
							<div class="invest-answer">抵押标项目开始计算收益的时间指微积金网将筹款资金划款至借款人账户的时间。
								例如：微积金网在2013年11月18日将借款人筹款资金打到借款人账户，则项目计算收益的时间为：2013年11月18日。</div></li>
						<li><a class="invest-title">投资人申请提现后何时到账？</a>
							<div class="invest-answer">微积金平台资金由新浪支付第三方托管，根据托管规则，工作日在14点之前提现到账时间是T+1，在14点之后提现到账时间是T+2。（周末和节假日顺延），当日具体到账时间以银行出账时间为准。


							</div></li> -->
					</ul>
				</div>

			</div>

			
			
		</div>

	</div>


	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>


</body>
</html>
