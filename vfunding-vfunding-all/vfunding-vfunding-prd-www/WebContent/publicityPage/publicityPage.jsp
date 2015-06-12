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
	href="${pageContext.request.contextPath}/css/base.css" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/register.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bottom.css" />


<script type="text/javascript">
	var rootPath = '${pageContext.request.contextPath}';
</script>
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/kinMaxShow.js"></script>
<script src="${pageContext.request.contextPath}/js/formValidator.js"></script>
<script src="${pageContext.request.contextPath}/js/formValidatorRegex.js"></script>
<script src="${pageContext.request.contextPath}/js/themes/126${pageContext.request.contextPath}/js/theme.js"></script>
<script src="${pageContext.request.contextPath}/js/in_tip_Plugin.js"></script>
<script src="${pageContext.request.contextPath}/js/getCodeForRegister.js"></script>
<script src="${pageContext.request.contextPath}/js/res.js"></script>
</head>
<c:if test="${not empty verificationErrorMsg}">
	<script type="text/javascript">
		alert('${verificationErrorMsg }');
	</script>
</c:if>
<body>
	<!-------------头部-------------------->
	<div id="res_header">
		<div class="res_logo">
			<a href="${pageContext.request.contextPath}"><img
				src="/images/header/logo.png" /></a>
		</div>

	</div>

	<div class="pic_change" id="pic_chang">
		<div id="kinMaxShow">
			<c:forEach items="${scrollPics}" var="scroll">
				<div>
					<a href="${scroll.url}" target="_blank"><img
						src="${scroll.pic }" width="2099" height="300" /></a>
				</div>
			</c:forEach>
		</div>
	</div>


	<div class="input_box">
		<div class="res_top">
			<span class="s1">注册微积金</span>&nbsp;&nbsp;&nbsp;<span class="s2">即可得20元红包</span>
		</div>
		<form action="/publicityRegister" method="post" name="zhuce"
			id="zhuce">
			<div class="zc">
				<div class="username_box" id="username_box">
					<div class="username_div">
						<div class="user_name">用户名：</div>
						<label class="username_label"> <span>请输入用户名</span> <input
							type="text" class="input_text" id="username" name="userName"
							name="rePassword" value="${register.userName }" />
						</label>
						<div id="usernameTip"></div>
					</div>

				</div>


				<div class="username_box">
					<div class="username_div">
						<div class="user_name">密码：</div>
						<label class="password_label"> <span>请输入6到16位密码</span> <input
							type="password" class="input_text" id="password" name="password"
							value="${register.password }" />
						</label>
						<div id="passwordTip"></div>
					</div>

				</div>


				<div class="username_box">
					<div class="username_div">
						<div class="user_name">确认密码：</div>
						<label class="password_label"> <span>再次输入密码</span> <input
							type="password" class="input_text" id="password2"
							name="rePassword" value="${register.rePassword }" />
						</label>
						<div id="password2Tip"></div>
					</div>

				</div>

				<div class="username_box">
					<div class="username_div">
						<div class="user_name">手机：</div>
						<label class="email_label"> <span>请输入你的手机</span> <input
							type="text" class="input_text" value="${register.mobile }"
							id="mobile" name="mobile" />
						</label>

						<div id="mobileTip"></div>
					</div>

				</div>


				<!--  <div class="username_box">
					<div class="username_div">
						<div class="user_name">介绍人：</div>
						<label class="jieshao_label"> <span>请输入介绍人</span> <c:if
								test="${empty from}">
								<input type="text" class="input_text"
									value="${register.introducer }" id="jieshao" name="introducer" />
							</c:if> <c:if test="${not empty from}">
								<input type="text" class="input_text" disabled="disabled"
									id="jieshao" name="introducer" />
							</c:if>
						</label>
						<div id="jieshaoTip"></div>
					</div>
				</div>-->
				<!-- <input id="inputCode" class="getCode" type="button" value="短信获取"
							onclick="getCode('mobile');" /> <input id="getByPhone"
							class="getCode" style="display: none;" type="button" value="电话获取"
							onclick="" /> -->

				<div class="username_box">
					<div class="username_div">
						<div class="user_name">验证码：</div>
						<label class="jieshao_label" id="yzm_label"> <span>请输入验证码</span>
							<input type="text" class="input_text" id="auth_code" name="vcode" />
						</label>
						<div id="auth_codeTip"></div>
						<input type="hidden" value="${register.unionUserId }"
							name="unionUserId" /> <input type="hidden"
							value="${register.productId }" name="productId" /> <input
							type="hidden" value="publicityPage/publicityPage"
							name="errorReturnUrl" /> <input type="hidden"
							value="${register.experienceMoney }" name="experienceMoney" />
							
							<input type="hidden"
							value="${register.fromType }" name="fromType" />
							
							
					</div>

				</div>

				<div class="username_box" style="text-align: center;">
					<div class="username_div" style="text-align: center; padding: 0px; margin-left: 65px; margin-bottom: 0px;">
						<a href="javascript:getPhoneCode('mobile');" id="phoneCode"
							class="yzm">短信获取</a> <a href="javascript:;" id="voiceCode"
							class="yzm" style="background-color: #EEEEEE">语音获取</a>
					</div>

				</div>



				<div class="username_box"
					style="height: 20px; margin-left: 0px; margin-top: 2px; margin-bottom: 2px;">
					<div id="waitMsg"
						style="position: absolute; top: 20px; left: 50px; color: #888888;"></div>
				</div>

				<input type="submit" name="button" id="button" class="res_btn" style="margin-top: 2px; margin-bottom: 2px;"
					value="立即注册" /><span class="res_tip">已有账号？<a
					href="${pageContext.request.contextPath }/goLogin" target="_blank">立即登录</a></span>

			</div>
		</form>

	</div>

	<div class="xuanchuan">
		<div class="whatVF">
			<p>
			微积金（www.vfunding.cn），是中国领先的O2O互联网汽车金融平台，由从业于金融、互联网行业多年的精英团队倾力打造国内最安全的互联网金融信息平台。微积金专注于互联网汽车金融领域，有信心通过自身的高品质服务能帮助您制订让所有人都能看得懂的理财计划！</p>
		</div>
		<div class="cunkuan">
			<ul>
				<li class="hqck">活期存款</li>
				<li class="dqck">定期存款</li>
				<li class="jjdt">基金定投</li>
				<li class="yeb">余额宝</li>
				<li id="wjj">微积金</li>
			</ul>
		</div>



		<div class="tedian">
			<ul>
				<li>基于个人间短期、透明、小额网络借贷型债权；</li>
				<li>高达13.9%的预期年化净收益；</li>
				<li>由国内知名“二手车经营公司欧美亚名车行”提供车辆处置服务；</li>
				<li>由第三方担保公司提供担保支持的安全性服务；</li>
				<li>提供风险保障金、风险准备金等多元支持的100%本息保障型创新理财产品。</li>

			</ul>
		</div>

		<div class="vipService">

			<ul>
				<li>享受“微积金本金保障计划”外更进一步的“本息保障计划”服务；</li>
				<li>享有微积金观察员身份，可免费参加微积金实地考察；</li>
				<li>尊享一对一个人财富管理规划顾问式服务；</li>
				<li>拥有微积金理财师申请资格；</li>
				<li>提供微积金免费微信、短信通知服务，及时传递最新的金融信息与市场资讯；</li>
				<li>免费参加微积金全年邀约活动（美食之旅、红酒品鉴、文化之旅、理财讲座）；</li>
				<li>优先享受微积金平台各项促销、优惠服务。</li>
			</ul>
		</div>
	</div>

	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
</body>
</html>
