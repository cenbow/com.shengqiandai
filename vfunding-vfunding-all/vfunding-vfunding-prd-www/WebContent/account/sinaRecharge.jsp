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
<link rel="stylesheet" type="text/css" href="/css/sinaPay.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<script src="/js/jquery1.8.3.js"></script>
</head>
<body>
	<a id="link" href="javascript:void(0)" style="visibility:hidden;position:absolute;"></a>  
	<input id="qucikTradeNo" value="" type="hidden">
	<input id="realnameFlag" value="${loginedSession.realStatus }" type="hidden">
	<!----头部---->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>
	<!--中间-->
	<div class="content">
		<div class="myAccount">
			<!-- 左边通用页面 -->
			<jsp:include page="${pageContext.request.contextPath }/user/accountLeft.jsp"></jsp:include>
			<div class="rechargeTakeCash  box-shadow fr ">
				<div class="rT">
					<div class="rTWrap">
						<div class="txTop">
							<b class="midLine"></b>
							<h2 class="zhcz">账户充值</h2>
							<a href="#" class="goBack">返回</a>
						</div>

						<div class="moneyNow">
							当前可用余额：<span>${empty account.useMoney?"0.00":account.useMoney}</span>元
						</div>
						<div class="money-tuoguan">微积金已全面接入新浪支付全程资金托管，账户余额将直接存入新浪存钱罐，享有对应货币基金的收益。按日结算。</div>

						<div class="netPay">
							<ul>
								<li class="bluebg">快捷支付</li>
								<li class="graybg" style="margin-right: 0;">网银支付</li>
							</ul>
						</div>

						<div class="e-bank payBox hide">

							<form method="post" action="#" name="#" id="#">
								<div class="czWrap">

									<div class="chongzhi online">

										<div class="zh-on fl">选择银行</div>

										<div class="banks fl">
											<ul>
												<!-- 中国工商银行 -->
												<li class="icbc banksImg"><i bankcode="ICBC"></i></li>
												<!-- 招商银行 -->
												<li class="cmb banksImg"><i bankcode="CMB"></i></li>
												<!-- 中国建设银行 -->
												<li class="ccb banksImg"><i bankcode="CCB"></i></li>
												<!-- 中国农业银行 -->
												<li class="abc banksImg"><i bankcode="ABC"></i></li>
												<!-- 中国银行 -->
												<li class="bc banksImg"><i bankcode="BOC"></i></li>
												<!-- 上海浦东发展银行 -->
												<li class="spdb banksImg"><i bankcode="SPDB"></i></li>
												<!-- 兴业银行 -->
												<li class="cib banksImg"><i bankcode="CIB"></i></li>
												<!-- 中国光大银行 -->
												<li class="ceb banksImg"><i bankcode="CEB"></i></li>
												<!-- 中国交通银行 -->
												<li class="jiaotong banksImg"><i bankcode="COMM"></i></li>
												<!-- 中国民生银行 -->
												<li class="minsheng banksImg"><i bankcode="CMBC"></i></li>
												<!-- 中国中信银行 -->
												<li class="zhongxin banksImg"><i bankcode="CITIC"></i></li>
												<!-- 中国广发银行 -->
												<li class="cgb banksImg"><i bankcode="GDB"></i></li>
												<!-- 中国平安银行 -->
												<li class="pingan banksImg"><i bankcode="SZPAB"></i></li>
												<!-- 中国邮政储蓄银行 -->
												<li class="psbc banksImg"><i bankcode="PSBC"></i></li>
												<!-- 北京银行 -->
												<li class="bjyh banksImg"><i bankcode="BCCB"></i></li>
												<!-- 渤海银行 -->
												<li class="bhyh banksImg"><i bankcode="CBHB"></i></li>
												<!-- 南京银行 -->
												<li class="njyh banksImg"><i bankcode="NJCB"></i></li>
												<!-- 浙商银行 -->
												<li class="zsyh banksImg"><i bankcode="CZB"></i></li>							
												<!-- Test银行 -->
<!-- 												<li class=""><i bankcode="TESTBANK"></i></li> -->
												<!-- 不支持更多银行 -->			
 												<!-- <li onclick="changeBank('more');" class="morebank banksImg"><i></i></li> -->
											</ul>
										</div>
									</div>
								</div>

								<div class="rechargeAmount">
									<ul>
										<li class="rechargeNum">充值金额</li>
										<li class="moneyAmount" id="moneyAmount"><i
											class="moneySymbol"></i> <input type="text" id="keyMoney">
										</li>
										<li>当前可用余额 <span id="curCanUse">${empty account.useMoney?"0.00":account.useMoney}</span>元
										</li>

										<li>充值后余额 <span id="rechargeAfter">${empty account.useMoney?"0.00":account.useMoney}</span>元
										</li>
									</ul>
								</div>
								<div class="moneyLegalCheck"></div>
								<a href="#" class="czBtn"> <i class="moneySymbol"></i> 充值
								</a>
							</form>
						</div>

						<div class="payBox">
							
						<c:choose>
							<c:when test="${sinaCardsLen==0}">
								<div class="unOpen">
								<span class="go-app">开通后，当您在微积金app及手机上访问时，更为便利。</span>
								
								<span class="kj-tip">你尚未开通快捷支付</span> <a class="btn226" href="/accountBank/bank">立即开通</a>
								</div>
							</c:when>
							<c:otherwise>
								<div id="chosenBankCard1" class="chosenBankCard1">
									<form action="#" method="post">
										<div class="cards clear">
											<span class="fl choseBank">选择银行</span>
											<ul class="adds-bankcard fl">
												<c:forEach items="${sinaCards}" var="card" varStatus="status">
													<li card="${card.scId}" 
														bank="${empty card.bank?"":card.bank}""
														phone="${fn:substring(card.bankPhone, 0, 3)}****${fn:substring(card.bankPhone, fn:length(card.bankPhone)-4,fn:length(card.bankPhone))}" 
														cardNo="*****${fn:substring(card.cardId, fn:length(card.cardId)-4,fn:length(card.cardId))}"
														class="banks-dir ${status.index==0?"selected":""}">			
														<i class="logo-bank bank-${empty card.bank?"":card.bank}"></i>
														<i class="icons"></i><em>*****${fn:substring(card.cardId, fn:length(card.cardId)-4,fn:length(card.cardId))}</em>
													</li>
												</c:forEach>											
										
<!-- 												<li id="addCard" class="adds-card"> -->
<!-- 													<span id="addBankBtn1"><i class="icons add-gray"></i>添加充值银行卡</span> -->
<!-- 												</li> -->
											</ul>
										</div>
	
										<div class="rechargeAmount">
											<ul>
												<li class="rechargeNum"
													style="padding: 0 20px; margin-right: 0; width: auto;">充值金额</li>
												<li class="moneyAmount" id="rechargeNum"><i
													class="moneySymbol"></i> <input type="text" id="inputMoney">
												</li>
												<li>当前可用余额 <span id="currentMoney">${empty account.useMoney?"0.00":account.useMoney}</span>元
												</li>
	
												<li>充值后余额 <span id="moneyAfter">${empty account.useMoney?"0.00":account.useMoney}</span>元
												</li>
											</ul>
										</div>
										<div class="error-tip"></div>		
										
										<div class="limit-tip"></div>	
										
										<div class="btn-recharge">
											<a class="btn226 rechargeBtn1" id="quickCz1">充值</a>
											<span class="checkLimit">
	<!-- 											<a href="#">取消</a> -->
												<a href="/utilpage/toPage/banklimit" target="_blank">查看支持的银行以及限额</a>
											</span>
										</div>
									</form>
	
								</div>
	
	
	
								<div  id="chosenBankCard2" class="chosenBankCard2 hide">
									<form action="#" method="post">
	
										<div class="cards clear">
											<span class="fl choseBank">选择银行</span>
											
											<ul class="adds-bankcard fl">
												<li class="banks-dir selected">
													<i class="logo-bank" id="i_chosebank2"></i>
													<i class="icons"></i><em id="cardNo_em"></em>
												</li>
	
											</ul>
										</div>
	
										<div class="czje">
											充值金额：<span id="czje_s">0</span>元
										</div>
	
										<div class="message">
											<p>
												<span id="phone_title">验证码已发送到手机号：<span id="phone_s"></span></span> <br /> 校验码15分钟内有效
											</p>
											<div class="msg-receive">
												<div class="inputBox">
													<input type="text" id="msgCode">
												</div>
												<a id="sendBtn" class="sendBtn" href="#">点击重获手机验证码 </a>
	
											</div>
											<div class="msgError-tip" style="padding-left: 0;"></div>
										</div>

										<div class="btn-recharge">
											<a class="btn226 rechargeBtn2" id="quickCz2">充值</a>
											<span class="checkLimit">
											<a href="#" id="chosenBankCard2_cancle">取消</a>
											</span>
										</div>
	
	
									</form>
								</div>
							</c:otherwise>
						</c:choose>
						
						</div>


						<div class="warmTip">
							<h2>温馨提示</h2>
							<div class="tips">
								<p>1. 所有投标资金将由第三方平台托管。</p>
								<p>2. 充值后，账户余额直接进入新浪存钱罐，享有对应的货币基金收益。</p>
								<p>3. 即时充值所产生的费用，由微积金平台承担。</p>
								<p>4. 请注意您的银行卡充值限制，以免造成不便。</p>
								<p>5. 如果充值金额没有及时到账，请和客服联系。</p>

							</div>

						</div>



					</div>
				</div>
			</div>


		</div>
	</div>


	<div id="rechBankDialog" class="dialog-main" style="width: 400px;">

		<div class="dialog-head">
			<h2>跳转到网银充值</h2>
			<a href="javascript:;" class="closeModal r3"><i class="icons">关闭</i></a>
		</div>
		<div class="rech-dialog-entry" id="rechBankEntry">
			<p class="p">请在新开网银页面完成充值后选择：</p>
			<div class="rech-mode">
				<i class="icons green-proper"></i><strong>充值成功</strong> | 您可以：
<!-- 				<a href="/rechargeWithdraw.session.action">查看充值记录</a> -->
				<a href="/account/rechargeCash" target="_blank">查看充值记录</a>
			</div>
			<div class="rech-mode">
				<i class="icons reg-error"></i><strong>充值失败</strong> | 建议您：
<!-- 					<a href="/rechargePage.session.action">重新充值</a>  -->
					<a href="/account/recharge" target="_blank">重新充值</a> 
					<a href="/static/help/" target="_blank">查看充值帮助</a>
			</div>
		</div>
	</div>
	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
	<script src="/js/recharge.js"></script>
</body>
</html>


