<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>微积金-理财师</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/lcsPost.css" />
</head>

<body>

	<!---头部--->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>

	<!--中间-->

	<div class="lcs">
		<div class="lcs-firstSector">
			<div class="lcs-pic">
				<img
					src="${pageContext.request.contextPath}/images/huodong/lcs-bg.png">
			</div>
		</div>
		<div class="lcs-secondSector">
			<div class="lcs-titlebg title1"></div>
			<div class="invite-cnt">
				<p class="invite" style="font-size:18px;">你通过邀请朋友（A）注册并投资微积金，A再邀请了他的朋友（B）注册并投资微积金，B再邀请了他的朋友（C）注册并投资微积金
					，你可以同时享受A（0.72%年化）收益、B的（0.09%）收益、C的（0.09%）的收益。</p>
				<h3 class="em" style="font-size: 18px;">示例</h3>
				<p class="invite">“小四”注册并投资成为微积金正式用户后，邀请了“大明”注册了微积金并完成了一笔10000元的投资，“大明”又邀请了他的家人“小明”成为微积金的用户投资了10000元，“小明”又邀请了他的女神“冰冰”投资了微积金10000元。</p>
				<div class="lcs-step clear">
					<div class="lcs-step-left fl">
						<p style="color:#846315;">“小四”作为最上级理财她一共可以获得：</p>
						<p>
							<span>“大明”的10000X0.72%（年化）+“小明”的10000X0.09%（年化）+</span>
						</p>
						<p>
							<span>“冰冰”的10000X0.09%（年化）</span>
						</p>

						<p style="margin-top: 30px;color:#846315;">由于“大明”成为正式用户后，邀请了“小明”进行投资，则“大明”也自动
						</p>
						<p>成为微积金理财师，则“大明”可以获得的收益为：</p>
						<p>
							<span>“小明”的10000X0.72%（年化）+“冰冰”的10000X0.09%（年化）</span>
						</p>
					</div>
					<div class="lcs-step-right fr">
						<img
							src="${pageContext.request.contextPath}/images/huodong/fanlibg.png">
					</div>
				</div>
			</div>
		</div>

		<div class="lcs-thirdSector">
			<div class="lcs-titlebg title2"></div>
			<div class="third-cnt">
				<h3 class="em">完成制定的系统任务：</h3>
				<p>转发链接、邀请注册、邀请投资、每日登陆等，即可获得一定的加息权益或现金奖励。</p>
				<div class="third-img">
					<img
						src="${pageContext.request.contextPath}/images/huodong/lcs-system.png">
				</div>
			</div>
		</div>
		<div class="lcs-fourthSecond">
			<div class="lcs-titlebg title3"></div>
			<div class="fourth-cnt">
				<p>一次邀请终生有效！你邀请的用户每一笔投资您将终生获得返利！</p>
				<div class="fourth-img">
					<img
						src="${pageContext.request.contextPath}/images/huodong/lcs-tree.png">
				</div>
			</div>
		</div>
		<div class="lcs-fiveSecond">
			<div class="lcs-titlebg title4"></div>
			<div class="five-cnt">
				<p class="invite fontSize18">通过您的真实社交、网络社交，网络渠道，向你的朋友发送邀请。比如每次通过微信朋友圈分享邀请，平均都有3-10人的推广效果。朋友享收益，你享返利，投资立返，立竿见影，还不去试试？</p>
				<h3 class="em">推广方法</h3>
				<p class="invite fontSize18">注册并实名认证后，到“我的账户”左边导航栏下方找到“理财师管理”，把专属于你的类似于
					http://www.vfunding.cn/fp/inviteRegister/12345678的推广网址发给他人，或者手机扫二维码分享即可。</p>
			</div>
			<a class="btn-check lcs-titlebg" href="/fp/toFpPage"></a>
		</div>
		<div class="lcs-sixthSecond">
			<div class="sixth-img">
				<img
					src="${pageContext.request.contextPath}/images/huodong/lcs-bottombg.png">
			</div>
		</div>
	</div>

	<!--尾部---->
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>



</body>
</html>
