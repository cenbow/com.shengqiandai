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
			<div class="au-box" id="fee_">
				<div class="au-title">网站资费</div>
				<h3 class="font18">投资人费用</h3>
				<p>除提现费用外，其他费用微积金平台已预先扣除，投资标的显示的年化收益为用户纯收益，详情请参考<a href="/borrow/agreementVfunding/1937" style="color: #00a0e9;">《微积金网站服务协议》</a>。</p>

				<table width="780" cellpadding="0" cellspacing="0" class="feeForm">
					<tbody><tr>
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
						
							<p>充值15天内未完全投标额部分，额外加收提现金额的0.3%手续费</p>
						</td>
					</tr>
				<!--  	<tr>
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
					</tr>	-->
				</tbody></table>

				<h3 class="font18">贷款人费用</h3>
				<table width="780" cellpadding="0" cellspacing="0" class="loanForm">
					<tbody><tr>
						<td width="195" height="66" class="gray-color">借款服务费<br>
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
						<td height="70" class="gray-color">借款管理费<br> （针对信用标、净值标）
						</td>
						<td><p>0.4%/月</p></td>
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
							<p>罚息利率 0.05%（1-30天）； 0.1%（31天以上）</p>
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
							<p>微积金目前的利率范围为10%-24%。</p>
							<p>在微积金平台上借贷的最高年利率设定为同期银行借款年利率的4倍。且随着银行借款利率的调整，微积金的利率上限也将随之调整。</p>
							<p>注：</p>
							<p>1.微积金的利率的调整会在商业银行借款年利率调整后1个月内进行调整。</p>
							<p>2.在利率调整之前成功的借款不受调整的影响。</p>

						</td>
					</tr>
				</tbody></table>
			</div>
		</div></div>

	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>


</body>
</html>
