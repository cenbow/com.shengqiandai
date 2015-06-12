<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="date" uri="http://cn.vfunding.vfunding.prd.www/tags"%>
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
	href="${pageContext.request.contextPath}/css/myAccount.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/tip.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/tip.min.js"></script>
<script src="${pageContext.request.contextPath}/js/myAccount.js"></script>

<body>
	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>
	

	<!--中间-->
	<div class="content">
		<!-- banner -->
		
	<c:if test="${not empty pic.url && pic.url!='' }">
		<div class="account-banner"><img src="${pic.url }"></img></div>
	</c:if>
	
		<div class="myAccount clear">
			<!-- 左边通用页面 -->
			<jsp:include
				page="${pageContext.request.contextPath }/user/accountLeft.jsp"></jsp:include>
			<div class="myAccount_right fr">
				<div class="simpleInfo box-shadow">
					<div class="blueBg"></div>
					<a href="${pageContext.request.contextPath }/user/headPicPage"
						class="avatarPic"> <c:choose>
							<c:when test="${not empty loginedSession.userIcon }">
								<img
									src="${pageContext.request.contextPath}/data/avatar/default/${loginedSession.userIcon }"
									height="80" width="80" />
							</c:when>
							<c:otherwise>
								<img
									src="/images/account/avatarPic.png"
									height="80" width="80" />
							</c:otherwise>
						</c:choose> 

					</a>
					<div class="whiteBg">
						<div class="userInfo">
							<div class="infoTop">

								<p class="info1">
									你好，${loginedSession.userName } &nbsp;
									<c:if test="${isVIP == true }">
										<img src="/images/account/vip.png" />
									</c:if>
								</p>
								
								<p>等级:</p>
								<c:choose>
									<c:when test="${loginedSession.typeId == 27}">
										<p id="info-junior" style="cursor:pointer;">集团用户</p>
									</c:when>
									<c:otherwise>
										<p id="info-junior" style="cursor:pointer;">${FinancialPlannerLevel }</p>
									</c:otherwise>
								</c:choose>
								<div class="lcs-level" style="display:none"></div>
								<script type="text/javascript">
								$(function(){
									$("#info-junior").on("click",function(){
										self.location.href = "http://"+self.location.host+"/fp/toFpPage";
									})
								})
								</script>
							</div>
							<div class="infoBottom">

								<p class="setPersonalInfo">
									<a href="${pageContext.request.contextPath }/user/info"
										target="_blank">设置个人信息</a>
								</p>


								<p class="info2">
									积分等级：
									<c:forEach begin="1" end="${rankNum }">
										<img src="/images/account/diamond.png" />
									</c:forEach>
									<c:if test="${empty rankNum || rankNum==0}">
										<img src="/images/account/heart.png" />
									</c:if>

								</p>
							</div>
						</div>
						
				<!-- 礼品盒与消息中心 -->	
				<div class="userinfp-right clear">
				  <div class="gift-box fl">
				     <div class="gift-box-icon actvity-icon gift-box-position2">
						 <c:choose>
						 	<c:when test="${unIsLookCount_gift > 0}">
						 		<div class="tip-num1 actvity-icon">${unIsLookCount_gift}</div>
						 	</c:when>				 
						 </c:choose>  
				     </div>
				     <div class="box-title">礼品盒</div>
				  </div>
				  <div class="msg-box fr">
				      <div class="msg-box-icon actvity-icon msg-box-position2">
				      	<c:choose>
						 	<c:when test="${unIsLookCount > 0}">
						 		<div class="tip-num2 actvity-icon">${unIsLookCount}</div>
						 	</c:when>				 
						 </c:choose>      	
				      </div>
				      <div class="box-title">消息中心</div>
				  </div>			
				</div>		
			
					</div>
				</div>


				<div class="accountExpression  box-shadow">

	                <div class="fund-trustee clear">
	                <span> 微积金已全面接入新浪支付全程资金托管，账户余额将直接存入新浪存钱罐，享有对应货币基金的收益。按日结算。</span>
	                <a href="/utilpage/toPage/trust" target="_blank">更多信息</a>
	                </div>
					<div class="assetMakeUp">
						<div class="accountAsset">
							<h3>账户净资产</h3>
							<p>
								<c:set var="all"
									value="${(accountvo.sumCollection==null?0.00:accountvo.sumCollection)
								-(accountvo.sumRepay==null?0.00:accountvo.sumRepay)+
								(account.useMoney==null?0.00:account.useMoney)+(account.noUseMoney==null?0.00:account.noUseMoney)}"></c:set>
								<span> <font> <fmt:formatNumber value="${all}"
											pattern="#,##0.00" />
								</font></span>元
							</p>
						</div>
						<div class="equalIcon iconBg"></div>
						<div class="investAsset">
							<h3>
								<b>投资资产</b><a href="javascript:;" title="投资资产=待收本金+待收利息+已赚返利"
									class="showTip tipcion"></a>
							</h3>
							<p>
								<span><fmt:formatNumber
										value="${(accountvo.sumCollection==null?0:accountvo.sumCollection)}"
										pattern="#,##0.00" /> </span>元
							</p>
						</div>
						<div class="addIcon iconBg"></div>
						<div class="borrowAsset">
							<h3>
								<b>账户余额</b><a href="javascript:;" title="账户余额=可用金额+冻结金额"
									class="showTip tipcion"></a>
							</h3>
							<p>
								<span><fmt:formatNumber
										value="${account.useMoney+account.noUseMoney}"
										pattern="#,##0.00#" /></span>元
							</p>
						</div>
						<div class="addIcon2 iconBg"></div>
						<div class="remainingMoney">
							<h3>
								<b>借款负债</b><a href="javascript:;" title="借款人借款形成的负债总额"
									class="showTip tipcion"></a>
							</h3>
							<p>
								<span><fmt:formatNumber
										value="${accountvo.sumRepay==null?0:accountvo.sumRepay}"
										pattern="#,##0.00#" /></span>元
							</p>
						</div>
					</div>
					<div class="moneyInfo clear">
				
						<!-- 不显示现金券的时候 -->
				        <p style="margin-right:30px">可用金额&nbsp; <b><fmt:formatNumber
							value="${account.useMoney==null?0.00:account.useMoney}"
							pattern="#,##0.00#" /></b> 元</p>
						<p style="margin-right:30px;">冻结金额&nbsp; <b><fmt:formatNumber
							value="${account.noUseMoney==null?0.00:account.noUseMoney}"
							pattern="#,##0.00#" /></b> 元</p>
	
	                    <p style="margin-right:30px;">提现抵扣券<a href="javascript:;" title="用户完成新手任务时微积金平台赠送的提现抵扣券，抵扣券不可用于投资或者直接提现，只可用于在用户提现时抵扣手续费." class="showTip tipcion"></a>&nbsp;&nbsp; <b>${hongbao}</b>元</p>
	                    <a class="redbag" title="点击获取更多" href="${pageContext.request.contextPath}/fresh">券</a>
						<div class="twoBtns fr">
							<a href="${pageContext.request.contextPath }/account/recharge"
								class="rechargeBtn">充值</a> <a
								href="${pageContext.request.contextPath }/accountCash/takeCash"
								class="takeCashBtn">提现</a>
						</div>
						
					</div>
				</div>
				<div class="account  box-shadow">
					<div class="accountWrap">
						<div class="investAccount">
							<h1>
								<span class="fl ac1">投资账户</span><span class="fr ac2">您还有未领取的新手礼包！<a
									href="${pageContext.request.contextPath}/fresh" target="_blank">立即查看</a></span>
							</h1>
							<div class="earnings earnings2">
								<div class="earn">
									<p class="earnAll">
										累计收益&nbsp;<a href="javascript:;"
											title="投资人在微积金所获得的所有投资收益以及其他收益（包括但不限于好友返利、活动收入等）"
											class="backProfit"></a>
									</p>
									<p class="earnMoney">
										<b><fmt:formatNumber
												value="${accountvo.sumIncome==null?0:accountvo.sumIncome}"
												pattern="#,##0.00#" /></b> 元
									</p>
									<p class="wantInvest">
										<a href="${pageContext.request.contextPath}/borrow/borrowList"
											class="wantInvestBtn">我要投资</a>
									</p>
								</div>
								<div class="earn">
									<p class="earnAll">
										累计返利&nbsp;<a href="javascript:;" title="投资人邀请好友投资的返利总额"
											class="backProfit"></a>
									</p>
									<p class="earnMoney">
										<b><fmt:formatNumber
												value="${accountvo.backIncome==null?0:accountvo.backIncome}"
												pattern="#,##0.00#" /></b> 元
									</p>
									<p class="wantInvest">
										<a href="${pageContext.request.contextPath}/friend/myFriend"
											class="wantInvestBtn">如何获得</a>
									</p>

								</div>
								<div class="earn noBoder_right">
									<p class="earnAll">
										余额生息七日年化收益率&nbsp;&nbsp;<!-- <a href="javascript:;"
											title="简单加权收益率综合考虑了投资人在微积金每笔投资的年化收益率及该笔投资的权重（权重 = 该笔投资/总投资金额）"
											class="profitRate"></a> -->
									</p>
									<p class="earnMoney">
										<b id="s_yield">${servenDayOfYearYieldsStr==null?0 : servenDayOfYearYieldsStr }</b>
									</p>
									<p class="wantInvest"></p>
								</div>

								<div class="earn">
									<p class="earnAll">
										待收总额&nbsp;
										<!-- <a href="javascript:;" title="不包含体验金的待收金额"
											class="profitRate"></a> -->
									</p>
									<p class="earnMoney">
										<b> <fmt:formatNumber
												value="${accountvo.sumCollection==null?0:accountvo.sumCollection}"
												pattern="#,##0.00#" />
										</b> 元
									</p>
									<p class="wantInvest">
										<%--<a class="aa">共 <font>${accountvo.countCollection==null?0:accountvo.countCollection}</font>
											笔
										</a> --%>
 										<a href="${pageContext.request.contextPath}/borrowTender/investRecord#recentSum"
											class="aa">共 <font>${accountvo.countCollection==null?0:accountvo.countCollection}</font> 笔
										</a>
									</p>
								</div>
								<div class="earn">
									<p class="earnAll">
										最近待收金额&nbsp;
										<!-- <a href="javascript:;" title="不包含体验金的待收金额"
											class="profitRate"></a> -->
									</p>
									<p class="earnMoney">
										<b><fmt:formatNumber
												value="${accountvo.recentCollection==null?0:accountvo.recentCollection}"
												pattern="#,##0.00#" /></b> 元
									</p>
									<p class="wantInvest">
										<c:if test="${not empty accountvo.recentCollection &&
										accountvo.recentCollection!=0&&accountvo.recentCollection!=0.0}">
											<%-- <a class="aa">最近待收日期 <date:date parttern="yyyy-MM-dd"
													value="${accountvo.recentCollectionTime==null?'':accountvo.recentCollectionTime}" />
											</a> --%>
											<a href="${pageContext.request.contextPath}/borrowTender/investRecord#recentAccount"
												class="aa">最近待收日期 <font><date:date parttern="yyyy-MM-dd"
													value="${accountvo.recentCollectionTime==null?'':accountvo.recentCollectionTime}" /></font>
											</a>
										</c:if>
									</p>
								</div>
								<div class="earn noBoder_right">
									<p class="earnAll">
										余额生息昨日收益&nbsp;&nbsp;<!-- <a href="javascript:;" title="新手专享体验金账户余额"
											class="profitRate"></a> -->
									</p>
									<p class="earnMoney">
										<b><fmt:formatNumber
												value="${accountSinabonus.sinaBonusPrevious==null?0:accountSinabonus.sinaBonusPrevious}"
												pattern="#,##0.00#" /></b> 元<br>
									</p>
									<p class="wantInvest">
										<c:if test="${not empty accountSinabonus.updatetime && accountSinabonus.updatetime != ''  }">
											<a href="/accountLog/accountLogList?openType=sinaBonus_addmoney"
													class="aa">余额生息日期： <font><fmt:formatDate value='${accountSinabonus.updatetime }' type='both' pattern='yyyy-MM-dd'/></font>
											</a>
										</c:if>
									</p>
									
									
									<%-- 	<c:if test="${isNew == false}">
											<a href="${pageContext.request.contextPath }/user/feelCard"
												class="tyj">体验金激活</a>
										</c:if>
										<c:if test="${isNew == true}">
											<p class="wantInvest">
												<a class="aa">新手体验专享</a>
											</p>
										</c:if> --%>
									
								</div>
							</div>
						</div>
						<div class="borrowAccount">
							<h1>借款账户</h1>
							<div class="earnings">
								<div class="earn">
									<p class="earnAll">借款总额</p>
									<p class="earnMoney">
										<b><fmt:formatNumber
												value="${accountvo.sumBorrowAccount==null?0:accountvo.sumBorrowAccount}"
												pattern="#,##0.00#" /></b> 元
									</p>
									<p class="wantInvest">
										<a
											href="${pageContext.request.contextPath}/borrow/releaseBorrow"
											class="wantInvestBtn">我要借款</a>
									</p>
								</div>
								<div class="earn">
									<p class="earnAll">待还总额</p>
									<p class="earnMoney">
										<b><fmt:formatNumber
												value="${accountvo.sumRepay==null?0:accountvo.sumRepay}"
												pattern="#,##0.00#" /></b> 元
									</p>
									<p class="wantInvest">
										<a
											href="${pageContext.request.contextPath}/borrowRepayment/myRepay"
											class="aa">共 <font>${accountvo.countRepay==null?0:accountvo.countRepay}</font>
											笔
										</a>
									</p>
								</div>
								<div class="earn noBoder_right">
									<p class="earnAll">最近待还金额</p>
									<p class="earnMoney">
										<b><fmt:formatNumber
												value="${accountvo.recentRepay==null?0:accountvo.recentRepay}"
												pattern="#,##0.00#" /></b> 元
									</p>
									<p class="wantInvest">
										<c:if
											test="${not empty accountvo.recentRepay&&
										accountvo.recentRepay!=0&&accountvo.recentRepay!=0.00}">
											<span>最近还款日期&nbsp;&nbsp; <date:date
													parttern="yyyy-MM-dd"
													value="${accountvo.recentRepayTime==null?'':accountvo.recentRepayTime}" />
											</span>
										</c:if>
									</p>
								</div>
							</div>
						</div>

					</div>
				</div>

			</div>

		</div>
	</div>



	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
	
		<script>
		$(function() {
			
			    //引导层
			    var html='<div id="overlay" ></div><div class="modalBox"><div class="yindaoceng" ><div id="dialogContext" class="ydc-cnt">context</div><div class="close"></div></div></div>';
                var tempWidth = $(document.body).width();
                var tempHeight = $(document.body).height();
                $("body").append(html);
                $("#overlay").css({
                  "width":tempWidth,
                  "height":tempHeight,
                  "position":"absolute",
                  "left":"0",
                  "top":"0",
                  "background":"#000",
                  "opacity":"0.5",
                  "filter":"alpha(opacity=50)",
                  "z-index":"999"
                });
                $(".modalBox").css({
                  "width":"1000px",
                  "height":tempHeight,
                  "position":"absolute",
                  "left":"50%",
                  "top":"0",
                  "margin-left":"-500px",
                  "z-index":"999"
                });

                $(".close").click(function(){
                	$(".modalBox").fadeOut();
                	$("#overlay").fadeOut(function(){
                	  $("#overlay,.modalBox").remove();
                	})
                	
                });
                
                $("#overlay").hide();
                $(".modalBox").hide();
		
            //提示框
			$('.backProfit,.profitRate,.showTip,.redbag,.upLevel').poshytip({
				className : 'tip-yellowsimple',
				showTimeout : 1,
				alignTo : 'target',
				alignX : 'center',
				offsetY : 8,
				allowTipHover : true
			});
            
			var inviteFriendsFirstDialog ="${inviteFriendsFirstDialog}";
			
			if(inviteFriendsFirstDialog && inviteFriendsFirstDialog == 0){
				
				var context = '您的好友&nbsp;${inviteUserName}&nbsp;已经注册完成，您已成为微积金的特约理财师。您的好友投资后，您将获得理财师佣金。<a href="${pageContext.request.contextPath }/utilpage/toPage/lcsPost">特约理财师有哪些权益？</a><br/>';
				$("#dialogContext").html(context);
				$("#overlay").show();
                $(".modalBox").show();
			}
			
			var FinancialPlannerLevel="${FinancialPlannerLevel }";
			if(FinancialPlannerLevel == "理财师"){
				$(".lcs-level").show();
			}else{
				$(".lcs-level").hide();
			}
			
			var friendsFirstInvestmentDialog = "${friendsFirstInvestmentDialog}"; 
			
			//friendsFirstInvestmentDialog="0";
			
			if(friendsFirstInvestmentDialog && friendsFirstInvestmentDialog == 0){
				var context = '您的好友&nbsp;${inviteUserName}&nbsp;已经完成投资,';
				if('${rebate}' == '未返利' ){
					context += '您已获得返利佣金';
				}else{
					context += '你获得首次&nbsp;${rebate}&nbsp;元的佣金。'
				}
				context += '<a href="${pageContext.request.contextPath }/utilpage/toPage/lcsPost">如何获得更多佣金？</a><br/>';
				$("#dialogContext").html(context);
				$("#overlay").show();
                $(".modalBox").show();
			}
            
		})
	</script>
	
</body>
</html>