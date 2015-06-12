<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
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
	href="${pageContext.request.contextPath}/css/jj.css" />
<script src="/js/jquery1.8.3.js"></script>

<!-- 弹出层JS -->
<script
	src="${pageContext.request.contextPath}/js/artDialog/artDialog.js?skin=default"></script>
</head>

<body>


	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>

	<!--中间-->
	<div class="jj-main">
		<div class="jj-top"></div>
		<div class="jj-content">
			<a class="jj-btn-bg jj-btn" href="#"
				onclick="checkLogin();return false;"></a>
			<div class="jj-rules">
				<div class="jj-rule">
					<div class="jj-arrow">
						<div class="jj-arrow-title">赛事概况</div>
						<i class="arrow-right"></i>
					</div>
					<div class="jj-rule-cnt">
						即日起至2015年3月28日，微积金携手JJ棋牌举办“微积金羸现金红包、小米电源斗地主大赛”健康娱乐比赛。活动期间凡微积金用户通过本活动页面注册、
						下载并安装JJ比赛即可参加，每天现金红包送不停！！更有机会获得100元现金红包+大容量小米移动电源！</div>
				</div>
				<div class="jj-rule">
					<div class="jj-arrow">
						<div class="jj-arrow-title">赛事奖励</div>
						<i class="arrow-right"></i>
					</div>
					<div class="jj-rule-cnt">
						第一名：微积金100元现金红包+小米10400MAH大容量移动电源+秋卡+金币<br />
						第二名：微积金40元现金红包+小米10400MAH大容量移动电源+秋卡+金币<br /> 第三名：微积金30元现金红包+秋卡+金币<br />
						参与奖：现金红包5元红包+秋卡+金币
					</div>
				</div>
				<div class="jj-rule">
					<div class="jj-arrow">
						<div class="jj-arrow-title">赛事说明</div>
						<i class="arrow-right"></i>
					</div>
					<div class="jj-rule-cnt">
						1．点击<span class="want">“我要参加“</span>按钮；<br />
						2．通过JJ页面注册报名成功即可获取5元红包券，现金红包券直接发送到JJ账户物品箱；<br />
						3．实物奖品将在比赛结束一周后由微积金客服联系获奖用户快递至提供地址。<br />
						4．若出现作弊，通过系统漏洞，黑客工具等非正常方式参与活动或者其他违规行为，将取消活动资格，不发奖品及红包。<br />
						5．微积金拥有本次活动最终解释权。
					</div>
				</div>
				<div class="jj-rule">
					<div class="jj-arrow" style="width: 250px;">
						<div class="jj-arrow-title">红包使用规则</div>
						<i class="arrow-right" style="right: -8px;"></i>
					</div>
					<div class="jj-rule-cnt">
						1．活动期间每个参与账户都有5元参与红包，前三名以JJ方提取名单为准发放红包 。<br />
						2．用户请确保提供手机号和微积金绑定手机号一致。比赛前3名的客户需向JJ棋牌提供真实有效的个人信息（姓名、手机号）， <br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;以便奖品发放。由于用户提供手机号码错误导致无法发奖的，微积金概不承担责任 。<br />
						3．微积金老用户现金红包兑换方式：<br />
						<p style="padding-left:26px;">
						      A、请邮件至<a href="mailto:service@vfunding.cn" style="color:#976514;">service@vfunding.cn</a>，邮件内注明“JJ红包兑换+用户名或手机号码+代金券卡密”。<br />

                              B、关注微信vfunding并回复“JJ红包兑换+用户名或手机号码+代金券卡密”。<br />

                              C、联系客服QQ2775156774，发送“JJ红包兑换+用户名或手机号码+代金券卡密”协助兑换 。 <br />
                                                                                                 新用户注册后，现金红包直接发放到微积金-我的账号-消息中心-礼品盒中，点击使用即充值入账户余额。
                        </p>
						4．红包激活后可以直接登录微积金投资或提现使用，现金红包50元起，才可提现 。<br /> 
						5．本次活动所有现金红包有效日期自领取后15日内有效，请及时使用 。<br /> 
						6．本次活动暂不支持移动端，请至电脑端参加 。
						
					</div>
				</div>
				<a class="cosplay" href="#" onclick="checkLogin();return false;"></a>
			</div>
		</div>
	</div>

	<!--尾部-->
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>


	<script type="text/javascript">
	function checkLogin(){
		var logined = ${ not empty loginedSession ?true:false};
		if(!logined){
    		art.dialog({
				content : "请登录后再参加活动",
				title : '微积金提示',
				icon : 'warning',
				cancelVal : '关闭',
				cancel : true,
				button : [ {
					name : '去登录',
					callback : function() {
						window.location.href = "/goLogin?returnUrl=utilpage/toPage/jj";
					},
					focus : true
				} ],
				fixed : true,
				lock : true,
				opacity : .3
			});
    		return false;
    	}else{
    		window.open("http://oreg.jj.cn/gpbd/wjj.html?siteid=4501099");
    	}
	}
</script>
</body>
</html>
