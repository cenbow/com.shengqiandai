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
	href="${pageContext.request.contextPath}/css/recharge.css" />

<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/global-1.js"></script>

<script src="${pageContext.request.contextPath}/js/recharge.js"></script>

<script src="${pageContext.request.contextPath}/js/realName.js"></script>
<script type="text/javascript">
    var userIdInfo='${userIdInfo }';
	function changeBank(bank) {
		if (!$('#rechargeSubmit').hasClass('czBtn')) {
			$('#rechargeSubmit').addClass('czBtn');
		}
		if (bank == 'more') {
			$('#rechargeBank').val('');
		} else {
			$('#rechargeBank').val(bank);
		}
	}

	function showError() {
		$('#rechargeSubmit').removeClass('czBtn');
		art.dialog({
			content : '目前招行和汇潮支付的通道存在一定技术的问题，汇潮支付正积极排查中，微积金建议您试一下其他的支付通道！',
			close : true,
			lock : true,
			icon : 'error'
		});
	}
</script>

</head>

<body>
	<!----头部---->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>


	<!--中间-->

	<div class="content">
		<div class="myAccount">
			<!-- 左边通用页面 -->
			<jsp:include
				page="${pageContext.request.contextPath }/user/accountLeft.jsp"></jsp:include>

			<div class="rechargeTakeCash fr">
				<div class="rT box-shadow">
					<div class="rTWrap">
						<div class="txTop">
							<b class="midLine"></b>
							<h2 class="zhcz">账户充值</h2>
							<a href="${pageContext.request.contextPath }/user/account"
								class="goBack">返回</a>
						</div>

						<div class="rechargeAmount">
							<ul>
								<li class="rechargeNum">充值金额</li>
								<li class="moneyAmount" id="moneyAmount"><i
									class="moneySymbol"></i> <input type="text" id="keyMoney"
									maxlength="10"></li>
								<li>当前可用余额 <span id="curCanUse">${account.useMoney }</span>元
								</li>

								<!-- <li>充值后余额 <span id="rechargeAfter"></span>元
								</li> -->
							</ul>
						</div>

						<div class="moneyLegalCheck"></div>

						<div class="rechargeWay" style="margin-top: 2px;">
							<ul>
								<li class="rechargeNum">充值类型</li>
								<li class="rechargeOnline"><i class="selectedSymbol"></i>
									线上充值</li>

								<li class="rechargeOffline"><i></i> 线下充值</li>

								<li class="zhifubao"><i></i> 支付宝</li>

							</ul>
						</div>

						<div class="czWrap">
							<div class="pay-channel clear mt20">
								<div class="zh-on fl">充值通道</div>
								<ul class="fl">
									<li class="sina"><i class="selectedSymbol"></i> 新浪支付</li>
									<li class="gfb"><i></i> 国付宝</li>
									<li class="hczf"><i></i> 汇潮支付</li>
									<li class="wyzx"><i></i> 网银在线</li>

								</ul>
							</div>
							<!-- 新浪支付 -->

							<div class="chongzhi online">

								<div class="zh-on fl">选择银行</div>

								<div class="banks fl">
									<ul>
										<li class="icbc banksImg" onclick="changeBank('ICBC');"><i></i></li>

										<li class="cmb banksImg" onclick="changeBank('CMB');"><i></i></li>

										<li class="ccb banksImg" onclick="changeBank('CCB');"><i></i></li>
										<li class="abc banksImg" onclick="changeBank('ABC');"><i></i></li>
										<li class="bc banksImg" onclick="changeBank('BOC');"><i></i></li>

										<li class="spdb banksImg" onclick="changeBank('SPDB');"><i></i></li>

										<li class="cib banksImg" onclick="changeBank('CIB');"><i></i></li>

										<li class="ceb banksImg" onclick="changeBank('CEB');"><i></i></li>

										<li class="jiaotong banksImg" onclick="changeBank('COMM');"><i></i></li>

										<li class="minsheng banksImg" onclick="changeBank('CMBC');"><i></i></li>

										<li class="zhongxin banksImg" onclick="changeBank('CITIC');"><i></i></li>

										<li class="cgb banksImg" onclick="changeBank('GDB');"><i></i></li>

										<li class="pingan banksImg" onclick="changeBank('SZPAB');"><i></i></li>

										<li class="psbc banksImg" onclick="changeBank('PSBC');"><i></i></li>

										<li class="hb banksImg" onclick="changeBank('HXB');"><i></i></li>

										<!-- 北京银行 -->
										<li class="bjyh  banksImg" onclick="changeBank('BCCB');"><i></i></li>

										<!-- 上海银行 -->
										<li class="shyh banksImg" onclick="changeBank('BOS');"><i></i></li>
										<li class="morebank banksImg" onclick="changeBank('more');"><i></i></li>

									</ul>


								</div>

							</div>




							<!-------- 国付宝 -------->
							<div class="chongzhi online">

								<div class="zh-on fl">选择银行</div>

								<div class="banks fl">
									<ul>
										<li class="icbc banksImg" onclick="changeBank('ICBC');"><i></i></li>

										<li class="cmb banksImg" onclick="changeBank('CMB');"><i></i></li>

										<li class="ccb banksImg" onclick="changeBank('CCB');"><i></i></li>
										<li class="abc banksImg" onclick="changeBank('ABC');"><i></i></li>
										<li class="bc banksImg" onclick="changeBank('BOC');"><i></i></li>

										<li class="spdb banksImg" onclick="changeBank('SPDB');"><i></i></li>

										<li class="cib banksImg" onclick="changeBank('CIB');"><i></i></li>

										<li class="ceb banksImg" onclick="changeBank('CEB');"><i></i></li>

										<li class="jiaotong banksImg" onclick="changeBank('BOCOM');"><i></i></li>

										<li class="minsheng banksImg" onclick="changeBank('CMBC');"><i></i></li>

										<li class="zhongxin banksImg" onclick="changeBank('CITIC');"><i></i></li>

										<li class="cgb banksImg" onclick="changeBank('GDB');"><i></i></li>

										<li class="pingan banksImg" onclick="changeBank('PAB');"><i></i></li>

										<li class="psbc banksImg" onclick="changeBank('PSBC');"><i></i></li>

										<li class="hb banksImg" onclick="changeBank('HXBC');"><i></i></li>


										<!-- 北京银行 -->
										<li class="bjyh  banksImg" onclick="changeBank('BOBJ');"><i></i></li>
										<!-- 天津银行 -->
										<li class="tjyh banksImg" onclick="changeBank('TCCB');"><i></i></li>
										<!-- 上海银行 -->
										<li class="shyh banksImg" onclick="changeBank('BOS');"><i></i></li>
										<!-- 上海农商银行 -->
										<li class="shnsyh  banksImg" onclick="changeBank('SRCB');"><i></i></li>
										<!-- 北京农商银行 -->
										<li class="bjnsyh banksImg" onclick="changeBank('BRCB');"><i></i></li>


										<li class="morebank banksImg" onclick="changeBank('more');"><i></i></li>

									</ul>


								</div>

							</div>


							<!-------- 汇潮支付-------->
							<div class="chongzhi online">

								<div class="zh-on fl">选择银行</div>

								<div class="banks fl">
									<ul>
										<li class="icbc banksImg" onclick="changeBank('ICBC');"><i></i></li>

										<li class="cmb banksImg" onclick="changeBank('CMB');"><i></i></li>
										<!--onclick="showError();"  -->

										<li class="ccb banksImg" onclick="changeBank('CCB');"><i></i></li>
										<li class="abc banksImg" onclick="changeBank('ABC');"><i></i></li>
										<li class="bc banksImg" onclick="changeBank('BOC');"><i></i></li>

										<li class="spdb banksImg" onclick="changeBank('SPDB');"><i></i></li>

										<li class="cib banksImg" onclick="changeBank('CIB');"><i></i></li>

										<li class="ceb banksImg" onclick="changeBank('CEB');"><i></i></li>

										<li class="jiaotong banksImg" onclick="changeBank('BOCOM');"><i></i></li>

										<li class="minsheng banksImg" onclick="changeBank('CMBC');"><i></i></li>

										<li class="zhongxin banksImg" onclick="changeBank('CITIC');"><i></i></li>

										<li class="cgb banksImg" onclick="changeBank('GDB');"><i></i></li>

										<li class="pingan banksImg" onclick="changeBank('PAB');"><i></i></li>

										<li class="psbc banksImg" onclick="changeBank('PSBC');"><i></i></li>

										<li class="hb banksImg" onclick="changeBank('HXBC');"><i></i></li>

										<!-- 北京银行 -->
										<li class="bjyh  banksImg" onclick="changeBank('BOBJ');"><i></i></li>
										<!-- 上海银行 -->
										<li class="shyh  banksImg" onclick="changeBank('BOS');"><i></i></li>
										<!-- 上海农商银行 -->
										<li class="shnsyh  banksImg" onclick="changeBank('SRCB');"><i></i></li>
										<li class="morebank banksImg" onclick="changeBank('more');"><i></i></li>
									</ul>



								</div>

							</div>

							<!-------- 网银在线 -------->

							<div class="chongzhi online">

								<div class="zh-on fl">选择银行</div>

								<div class="banks fl">
									<ul>
										<li class="icbc banksImg" onclick="changeBank('1025');"><i></i></li>

										<li class="cmb banksImg" onclick="changeBank('3080');"><i></i></li>

										<li class="ccb banksImg" onclick="changeBank('105');"><i></i></li>
										<li class="abc banksImg" onclick="changeBank('103');"><i></i></li>
										<li class="bc banksImg" onclick="changeBank('104');"><i></i></li>

										<li class="spdb banksImg" onclick="changeBank('314');"><i></i></li>

										<li class="cib banksImg" onclick="changeBank('309');"><i></i></li>

										<li class="ceb banksImg" onclick="changeBank('312');"><i></i></li>

										<li class="jiaotong banksImg" onclick="changeBank('301');"><i></i></li>

										<li class="minsheng banksImg" onclick="changeBank('305');"><i></i></li>

										<li class="zhongxin banksImg" onclick="changeBank('313');"><i></i></li>

										<li class="cgb banksImg" onclick="changeBank('306');"><i></i></li>

										<li class="pingan banksImg" onclick="changeBank('307');"><i></i></li>

										<li class="psbc banksImg" onclick="changeBank('3230');"><i></i></li>
										<li class="hb banksImg" onclick="changeBank('311');"><i></i></li>

										<!-- 南京银行 -->
										<li class="njyh  banksImg" onclick="changeBank('316');"><i></i></li>
										<!-- 杭州银行 -->
										<li class="hzyh  banksImg" onclick="changeBank('324');"><i></i></li>
										<!-- 北京银行 -->
										<li class="bjyh  banksImg" onclick="changeBank('310');"><i></i></li>
										<!-- 上海银行 -->
										<li class="shyh  banksImg" onclick="changeBank('326');"><i></i></li>
										<!-- 重庆农商银行 -->
										<li class="cqnsyh  banksImg" onclick="changeBank('342');"><i></i></li>
										<!-- 北京农商行 -->
										<li class="bjnsyh  banksImg" onclick="changeBank('335');"><i></i></li>
										<!-- 成都银行 -->
										<li class="cdyh banksImg" onclick="changeBank('336');"><i></i></li>
										<!-- 宁波银行 -->
										<li class="nbyh banksImg" onclick="changeBank('302');"><i></i></li>
										<li class="morebank banksImg" onclick="changeBank('more');"><i></i></li>

									</ul>



								</div>

							</div>

							<form id="bankForm" target="_blank">
								<input type="hidden" id="address" value="http://pay.vfunding.cn/sinaPay/sinaPay/${userIdInfo }"> <input
									type="hidden" id="rechargeMoney" name="rechargeMoney" value="">
								<input type="hidden" id="rechargeBank" name="rechargeBankCode"
									value="">
							</form>
							<input id="realnameFlag" value="${loginedSession.realStatus }"
								type="hidden"> <a href="#" class="czBtn"
								id="rechargeSubmit"> <i class="moneySymbol"></i> 充值
							</a>


						</div>



						<!-------- 线下充值 -------->

						<div class="chongzhi offline clear">
							<div class="zh-on fl">选择银行</div>
							<div class="banks2 fl">
								<p>
									<label for="bank1"><input type="radio" name="bk"
										id="bank1" value="36" checked="checked"> 7628 0188
										0002 3069 3 中国光大银行上海漕河泾开发区支行 微积金金融信息服务(上海)有限公司 </label> <input
										type="hidden" id="bk" value="36" />
								</p>
								<div id="problem">
									<p>线下充值如遇到问题，请马上与客服联系联系；</p>
									<p>（1）有效充值登记时间为:周一至周五的9:30到16:00，充值成功请跟我们的客服联系。</p>
									<p>（2）国庆期间只能同行转账。</p>
									<p>（3）线下充值的单笔最低金额不低于10000元。</p>
								</div>
							</div>
						</div>
						<!---- 支付宝 ----->

						<div class="chongzhi  alipay  clear ">
							<div class="zh-on fl"></div>
							<div class="banks2 fl">
								<p>
									<label for="bank1">
										<!-- <input type="radio"  name="bk2"

										id="bank2" value="1"  checked="checked"> -->

										请一定看完以下支付宝代充步骤后再进行您在微积金的支付宝代充之旅：
									</label> <a
										href="${pageContext.request.contextPath }/user/useAlipay.html"
										target="_blank" id="howRecharge"><span>更多充值说明请点击</span>“如何转账”</a>

								</p>
								<div id="problem">
									<h3>充值顺序：</h3>
									<p class="title">一、登陆支付宝完成实际的转帐交易</p>
									<p>
										(1) 登陆您的支付宝帐号<a href="https://auth.alipay.com/login/index.htm"
											class="link">（https://auth.alipay.com/login/index.htm）</a>
									</p>
									<p>(2) 转帐相应的金额至微积金网站的企业支付宝帐号（cz@vfunding.cn）</p>
									<p class="title">二、在微积金系统提交审核请求</p>
									<p>(1) 在本页面的“充值金额”栏填写已完成的转账金额</p>
									<p>(2) 在本页面的“充值备注”栏填写您的支付宝账号</p>
									<p>(3) 如您未填写，请您在充值后及时联系客服：400-991-9999， 验证身份后为您充值！</p>
									<p class="title">三、系统后台确认</p>
									<p>(1) 充值时间在工作日8:30-17:30之间充值，将在一小时内到达您的微积金账户。其余时间内充值将在次日
										10:00前到达您的微积金账户,并会短消息通知您</p>
									<p>(2) 您可以选择您心仪的标的开始投资了</p>

								</div>
							</div>
						</div>
						<!--  充值备注 -->
						<div class="clear  recharge-mark" style="display: none">
							<div class="zh-on fl" style="margin-top: 20px;">充值备注</div>
							<div class="banks2 fl">
								<div class="beizhu">
									<input type="text" id="beizhu" name="tradeNo" maxlength="30" />
								</div>
								<span class="bank-liushui">请注明您的转账流水号，谢谢配合</span> <input
									type="button" value="提交" id="submitBtn" />
							</div>
						</div>

						<!-------- 温馨提示-------->
						<div class="warmTip">
							<h2>温馨提示</h2>
							<div class="tips">
								<p>1. 为了您的资金安全，请您在充值之前先完成实名认证；</p>
								<p>2. 每日的充值限额依据各银行限额为准；</p>
								<p>3.
									禁止信用卡套现、虚假交易等行为，一经发现将予以处罚，限制收款、冻结账户、永久停止服务,并有可能影响相关信用记录。</p>
								<p>4. 充值后未用于投资的提现申请金额，将视情况通过第三方支付平台退回原卡，到账时间以发卡行通知为准；</p>
								<p>5. 网上银行充值过程中请耐心等待,充值成功后，请不要关闭浏览器,充值成功后返回微积金,
									充值金额才能打入您的帐号。</p>

							</div>

						</div>



					</div>
				</div>
			</div>


		</div>
	</div>


	<div id="rechBankDialog" class="dialog-main" style="width: 400px">
		<div class="dialog-head">
			<h2>跳转到网银充值</h2>
			<a href="javascript:;" class="closeModal r3"><i class="icons">关闭</i></a>
		</div>
		<div class="rech-dialog-entry" id="rechBankEntry">
			<p class="p">请在新开网银页面完成充值后选择：</p>
			<div class="rech-mode">
				<i class="icons green-proper"></i><strong>充值成功</strong> | 您可以：<a
					href="${pageContext.request.contextPath }/account/rechargeCash">查看充值记录</a>
			</div>
			<div class="rech-mode">
				<i class="icons reg-error"></i><strong>充值失败</strong> | 建议您：<a
					href="javascript:location.reload();">选择其他充值方式</a> <a
					href="${pageContext.request.contextPath }/utilpage/helpCenter">查看充值帮助</a>
			</div>
		</div>
	</div>
	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
	<script>
		function other() {
			$('#rechBankDialog').hide();
			$('#overlayModal').css('background', '');
		}
	</script>
</body>
</html>


