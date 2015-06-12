<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="微积金(www.vfunding.cn) 整合了民营汽车借贷机构、二手车商、第三方汽车服务机构等传统领域企业，形成了独特的针对汽车提供投融资对接服务的创新型O2O互联网汽车金融服务模式。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、汽车贷款等。" />
<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|微贷|红包|基金|微基金|小额贷款|微财富|宜车贷|二手车抵押|车贷|托管" />
<title>借款协议-${borrow.id==null?"编无号":borrow.id}-${borrow.name==null?"无标题":borrow.name}</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/agreement.css"/>
</head>
<body>
<div class="content  box-shadow">
	<div class="grid900">
		<h1 class="agreement-title">微积金网站服务协议</h1>
		<p class="mt20">本《债权转让协议》（“本协议”）由以下双方于
		<c:if test="${istender == true}">
			<c:if test="${borrow.repaymentTime==null||borrow.repaymentTime==0||borrow.repaymentTime==''}">
				***
			</c:if>
			<c:if test="${borrow.repaymentTime!=null && borrow.repaymentTime!=0 && borrow.repaymentTime==''}">
				<fmt:formatDate value="${repaymentTime}" pattern="yyyy年MM月dd日" />
			</c:if>
		</c:if>
		<c:if test="${istender == false}">
			***
		</c:if>
		 签订：</p>
		<p class="mt20">借款协议号：
			<c:if test="${istender==true}">
				${borrow.repaymentTime==0?'--':borrow.repaymentTime}
			</c:if>
			<c:if test="${istender==false}">
				***
			</c:if>
		</p>
		<p class="mt20"> 借款人:
			<c:if test="${istender==true}">
				${user.username}
			</c:if>
			<c:if test="${istender==false}">
				***
			</c:if>
		</p>
		<p class="mt20"> 出借人：详见本协议第一条</p>
		<p class="mt20">双方根据平等、自愿的原则，就甲方通过由微积金金融信息服务（上海）有限公司（“微积金”）运营管理的微积金网（域名为www.vfunding.cn，“微积金网”）平台向乙方转让债权事宜，达成协议如下：
		<p class="mt20 fb">第一条：标的债权</p>
		<p class="mt20">甲方同意将其通过微积金网平台发放借款形成的以下债权（“标的债权”）转让给乙方，乙方同意受让该债权：</p>
	</div>
<table width="1000" cellpadding="0" cellspacing="0" class="zq-table mt20">
  <tr>
    <td width="70" height="40">出借人</td>
    <td width="100">借款金额</td>
    <td width="70" >借款期限</td>
    <td width="70" >年利率</td>
    <td width="100">平台服务费率</td>
    <td width="100">监管服务费率</td>
    <td width="80">借款开始日</td>
    <td width="80">借款到期日</td>
    <td width="80">截止还款日</td>
    <td width="80">还款本息</td>
    <td width="80">平台服务费</td>
    <td width="80">监管服务费</td>
  </tr>
  <c:if test="${istender == true}">
	  <c:forEach items="${tenderList}" var="v">
			<tr>
				<td>${v.tenderUser}</td>
				<td><fmt:formatNumber pattern="#0.00" value="${v.account }" maxFractionDigits="2"/>元</td>
				<td>${v.timeLimit}个月</td>
				<td>${v.apr}%</td>
				<td>${v.bfee}%</td>
				<td>${v.gfee}%</td>
				<td>
					<c:choose>
						 <c:when test="${v.borrowStartTime!=null && v.borrowStartTime!=0 && v.borrowStartTime=='' && v.borrowEndTime!=null}">
							<date:date parttern="yyyy-MM-dd" value="${v.borrowStartTime }" />
						 </c:when>
						 <c:otherwise>
						 	-
						 </c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						 <c:when test="${v.borrowEndTime!=null && v.borrowEndTime!=0 && v.borrowEndTime==''}">
							<date:date parttern="yyyy-MM-dd" value="${v.borrowEndTime }" />
						 </c:when>
						 <c:otherwise>
						 	-
						 </c:otherwise>
					</c:choose>
				</td>
				<td>${(v.eachTime==null||v.eachTime=='')?'-':v.eachTime}</td>
				<td><fmt:formatNumber pattern="#0.00" value="${v.repaymentAccount }" maxFractionDigits="2"/>元</td>
				<td><fmt:formatNumber pattern="#0.00" value="${v.bfeeMoney}" maxFractionDigits="2"/>元</td>
				<td><fmt:formatNumber pattern="#0.00" value="${v.gfeeMoney}" maxFractionDigits="2"/>元</td>
			</tr>
		</c:forEach>
  </c:if>
</table>
 <div class="grid900">
 <p class="mt20 fb">第二条：借款人</p>
 <p class="mt20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;标的债权的对应借款人（即债务人），以及抵押或质押标的物，已在该标段网页“借款详情”栏内公示，乙方须详细查看并自主决定是否投标。
鉴于“微积金”保护借款人私密信息的承诺，平台公示时隐藏了部分信息，但在乙方投标完成投资后，有权随时向甲方了解或索取有关借款人的全部信息和借款材料。</p>
 <p class="mt20 fb">第三条：服务及收费</p>
 <p class="mt20">&nbsp;&nbsp;&nbsp;&nbsp;“微积金”通过微积金网平台向甲乙各方提供服务，并按照本协议第一条债权列表中注明的费率向甲乙双方收取平台服务费和监管服务费。对此，甲、乙各方均不持任何异议。</p>
 <p class="mt20 fb">第四条：质押或抵押权的托管</p>
 <p class="mt20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;依据甲方与借款人的借款协议约定，双方已将质押或抵押车辆（或其他标的）委托第三方进行监督管理（包括但不限于车辆保管、抵押登记、GPS跟踪、质押或抵押权的实现等）

并支付相应服务费用。乙方作为债权受让人，完全接受甲方与借款人、第三方之间有关质押或抵押的全部约定条款，同时并授权由该第三方继续对借款人的质押或抵押车辆实施监督管理

（包括但不限于车辆保管、抵押登记、GPS跟踪、质押或抵押权的实现等），并同意按本协议第三条约定支付监管服务费。</p>
 <p class="mt20 fb">第五条：还款</p>
 <p class="mt20">1、借款人有义务按照本协议第一条约定的时间和金额按期足额向出借人还款。<br/>

2、借款人的最后一期还款包含全部剩余本金、利息及其他根据本协议产生的全部费用。<br/>

3、借款人的每一期还款的还款金额计算公式为：每月须还款总金额=每月须还利息+每月须还本金。</p>


 <p class="mt20 fb">第六条：债权转让金的支付和还款</p>
 <p class="mt20">1、乙方在完成投标后,即委托“微积金”网站在本协议生效时将相应的债权转让金直接划付至甲方帐户。<br/>
2、乙方委托并授权“微积金”将借款人每期还款直接划付至乙方的“微积金”个人会员帐户内。<br/>
3、“微积金”网站接受甲方、乙方或借款人的委托或指令所采取的划付款项行为，所产生的法律后果均由相应委托方自行承担。<br/>
4、若借款人的任何一期还款不足以偿还应还本金、利息和违约金,则出借人（包括乙方）之间同意按照各自出借金额在出借总额中的比例收取还款。<br/>
5、本协议的履行地为“微积金”网站的住所地(上海市徐汇区)。</p>

 <p class="mt20 fb">第七条：逾期还款</p>
 <p class="mt20">1、若借款人逾期仍未还款,乙方同意并特别授权，“微积金”可通过短信、电话、上门催收等方式对借款人逾期未偿还的本息和罚息进行催收,且“微积金”有权按照未偿还本金千分之八的标准收取催收服务费用。<br/><br/>

2、乙方授权并支持“微积金”采取以下措施之一项或几项：<br/>

（1）将借款人的有关身份资料及其他个人信息通过本网站“逾期黑名单”等栏目对外公开；<br/>

（2）将借款人的有关身份资料及其他个人信息正式备案在“不良信用记录”,列入全国个人信用评级体系的黑名单(“不良信用记录”数据将为银行、电信、担保公司、人才中心等有关机构提供个人不良信用信息)；<br/>

（3）对借款人采取法律措施,由此所产生的所有法律后果将由借款人来承担。<br/><br/>

3、“微积金”接受乙方委托和授权，对逾期仍未还款的借款人收取逾期罚息作为催收费用，采取多种方式催收，将借款人的相关信息对外公开或列入“不良信用记录”或采取法律措施等各项行为，

均只是“微积金”根据本协议为乙方提供的一种服务，“微积金”对借款本息之最终清偿不承担任何担保或其他法律责任。<br/><br/>

4、本协议第一条债权列表中的每一出借人（包括乙方）与借款人之间的借款均是相互独立的,一旦借款人逾期未归还借款本息,任何一出借人都有权单独或共同向借款人自行追索或者提起法律诉讼。</p>
 <p class="mt20 fb">第八条：债权再转让</p>
 <p class="mt20">在正常还款期内，乙方可向“微积金”提出债权再转让申请，并不可撤销地特别授权“微积金”全权办理转让事宜，“微积金”据此提供债权再转让的推荐服务，

一旦有受让人同意受让并支付转让价款（不超过借款人逾期仍未偿还的借款本息，具体金额视出借人在本网站的会员级别不同而有所不同）后，该受让人即取得乙方在本协议项下的债权。</p>

 <p class="mt20 fb">第九条：乙方本金及利息保障</p>
 <p class="mt20">
 1、若借款人逾期归还当期利息而非本金，“微积金”委托的承担监管及本息保障功能的合作机构（以下简称“保障方”）将立即依据“本息保障计划”启动利息垫付程序，借款人此前拖欠的当期利息由“保障方”垫付，
 并及时支付给乙方。若借款人在10日内（质押车辆的借款人在15日内）支付了上述利息，乙方同意该利息转付给已经垫付利息的“保障方”。若借款人逾期10日（质押车辆的借款人逾期15日）仍未支付此利息，
 “微积金”将通过有效方式将借款人违约的情况告知乙方，乙方同意“保障方”立即依“本息保障计划”支付本金给乙方，乙方同意立即将自己持有的对该借款人的全部债权以上述本金价格同步转让给“保障方”，
 此后由受让债权的“保障方”通过法律手段或其他手段向借款人追讨欠款。<br/>
2、若借款人逾期归还的是到期本金（或同时存在利息），“微积金”将通过有效方式将借款人违约的情况告知乙方，乙方同意“保障方”立即依“本息保障计划”支付本金及当期利息给乙方，
乙方立即将自己持有的对该借款人的全部债权以上述本金价格同步转让给“保障方”，此后由受让债权的“保障方”通过法律手段或其他手段向借款人追讨欠款。 <br/>
3、上述债权“再转让”完成（乙方收到“保障方”支付的本金、利息）以后，乙方作为出借人的地位由“保障方”代替，乙方与借款人的直接借款关系终止，乙方有权以收回的资金再进行其他投资。<br/>
4、乙方同意，此条情况下的债权转让不需另行签订书面或在线合同，当乙方收到“微积金”关于借款人违约的告知书，且乙方收到“保障方”支付的本金（或包括利息），则即视为乙方债权已经转让给“保障方”，乙方对此无异议。<br/>
5、若“保障方”无力垫付本金及利息的，由乙方直接通过法律途径向借款人追讨欠款。<br/>
 </p>
 <p class="mt20 fb">第十条：法律适用和管辖</p>
 <p class="mt20">本协议的签订、履行、终止、解释均适用中华人民共和国法律,并由协议履行地的上海市徐汇区人民法院管辖。</p>

 <p class="mt20 fb">第十一条：特别条款</p>
 <p class="mt20">
 	1、乙方承诺并保证，用于投资的资金来源合法，并在自己经济承受能力范围之内。<br/>
2、“微积金”网站仅作为网友之间小额资金互助平台,借款人和出借人均不得利用本网站平台进行信用卡套现、信用卡诈骗、洗钱、不正当或虚假交易等违法活动。否则，出借人、借款人和/或本网站均有权向公安等有关行政机关举报,追究其相关法律责任。
 </p>
 
 <p class="mt20 fb">第十二条：其他</p>
 <p class="mt20">1、本协议采用电子文本形式制成,并通过站内信的形式发送至协议各方本网站信箱。<br/>

2、本协议自甲方在“微积金”网站发布的转让标的债权审核成功之日及本协议题头标明的签订日起生效,甲方、乙方、“微积金”各执一份,并具同等法律效力。<br/>

3、在履行本协议过程中，还应遵守本网站的各项规定。<br/>

4、“微积金”作为本协议当事人之一，根据本协议的规定和网站的其他规定行使各项权利、发出各项通知或采取各项措施。
<br/>
<br/>
【以下无正文】
<c:if test="${borrow.status==3 and istender==true}">
	<div style="text-align:center;"><a href="javascript:window.print();">打印</a></div>
<%-- 	<div style="text-align:center;"><a href="${pageContext.request.contextPath }/borrow/agreementPdf/${borrow.id}">导出</a></div> --%>
</c:if>
</div>
</div>
</body>
</html>
