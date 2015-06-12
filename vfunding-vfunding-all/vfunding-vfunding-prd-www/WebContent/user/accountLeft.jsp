<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="myAccount_left fl box-shadow">
	<div class="userSimpleAccount">
		<span>Hi，${loginedSession.userName }</span>
		<div class="identifications">
			<div class="userIdentification">
				<c:choose>
					<c:when test="${loginedSession.realStatus =='1' }">
						<a title="您已实名认证"  href="${pageContext.request.contextPath }/user/realName"
							class="renzheng cardIdentification2  lv1"></a>
					</c:when>
					<c:otherwise>
						<a title="您还未实名认证，请点击认证！"  href="${pageContext.request.contextPath }/user/realName"
							class="renzheng cardIdentification lv2"></a>
					</c:otherwise>
				</c:choose>
				
				<c:choose>
					<c:when test="${loginedSession.phoneStatus =='1' }">
							<a href="${pageContext.request.contextPath }/user/checkPhoneState" title="您已通过手机认证"  class="renzheng mobileIdentification2 lv3"></a>
					</c:when>
					<c:otherwise>
						<a title="您还未手机认证，请点击认证！"  href="${pageContext.request.contextPath }/user/checkPhoneState"
							class="renzheng mobileIdentification lv3"></a>
					</c:otherwise>
				</c:choose>
				
				<c:choose>
					<c:when
						test="${loginedSession.password != loginedSession.payPassword }">
						<a title="您已设置支付密码" 
							href="${pageContext.request.contextPath }/user/updatePayPassword"
							 class="renzheng passwordIdentification2 lv4"></a>
					</c:when>
					<c:otherwise>
						<a title="您的支付密码和登录密码一致， 请点击修改！" 
							href="${pageContext.request.contextPath }/user/updatePayPassword"
							 class="renzheng passwordIdentification lv5"></a>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="progressBar">
				<div
					style="width: ${percentStr }; 
			        <c:if test="${safeStr=='低' }">
					    background:#ff5555 ;
					</c:if>
					<c:if test="${safeStr=='中' }">
					    background:#ffc730 ;
					</c:if>
					<c:if test="${safeStr=='高' }">
					    background:#8bd668 ;
					</c:if>"
					id="progressBar"></div>

			</div>
			<div class="safeLevel">
				安全等级：<font>${safeStr }</font>&nbsp; <a
					href="${pageContext.request.contextPath }/user/safeCenter" class="upLevel" title="完善个人信息获取提现抵扣券"> <c:choose>
						<c:when test="${safeStr=='高' }">[修改]</c:when>
						<c:otherwise>[提升]</c:otherwise>
					</c:choose>
				</a>
			</div>
		</div>
	</div>
	<div class="canUseMoney">
		<span>可用余额</span>
		<p class="moneyNum" id="useMoneyNum">
			<c:choose>
				<c:when test="${empty account.useMoney  }">
			0.00元
			</c:when>
				<c:otherwise>
			${account.useMoney }元
			</c:otherwise>
			</c:choose>
		</p>
		<a href="${pageContext.request.contextPath }/account/recharge"
			class="recharge">立即充值</a>
	</div>
	<div class="items">
		<dl class="item">
			<dt>我的账户</dt>
			<dd>
				<a href="${pageContext.request.contextPath }/user/account"> <i
					class="lefticon accountAll"></i> 账户总览
				</a>
			</dd>
			<dd>
				<a
					href="${pageContext.request.contextPath}/accountLog/accountLogList">
					<i class="lefticon moneyRecord "></i> 资金记录
				</a>
			</dd>
			<dd>
				<a href="${pageContext.request.contextPath }/account/rechargeCash">
					<i class="lefticon rechargeTx "></i> 充值提现
				</a>
			</dd>
			<dd>
				<a href="${pageContext.request.contextPath }/accountBank/bank">
					<i class="lefticon bankCard "></i> 银行卡
				</a>
			</dd>
			<!--  <dd>
				<a href="javascript:;" title="新手体验活动已经结束"  onclick="disp_alert()"> <i
					class="lefticon myGift "></i>新手体验  
				</a>
			</dd>-->
						<dd>
				<a href="${pageContext.request.contextPath }/giftbox/boxPage"> <i
					class="lefticon giftbox "></i>礼品盒
				</a>
			</dd>
		</dl>
		<dl class="item">
			<dt class="safeCenter">
				<a href=""  id="licaishi"   class="ss">理财师管理</a>
				<!-- <a href="${pageContext.request.contextPath }/friend/myFriend">好友管理</a> -->
			</dt>
		</dl>

		<script type="text/javascript">
		$(function(){
			$("#licaishi").attr("href","http://"+self.location.host+"/fp/toFpPage")
		$('.item dt a[href="' + window.location.href + '"]').addClass("current")
		})
		
		</script>
		<dl class="item">
			<dt>我的投资</dt>
			<dd>
		     <a href="${pageContext.request.contextPath}/borrowTender/investRecord">
					<i class="lefticon investProject"></i> 投资记录
			 </a> 
<%-- 					<a
					href="${pageContext.request.contextPath}/borrowTender/investRecord">
					<i class="lefticon investProject"></i> 车贷宝
				   </a> --%>
				
			</dd>
			<!-- <dd>
				<a href="${pageContext.request.contextPath}/weekTender/holdingAssets"><i class="lefticon sureMoney"></i>
					周盈宝 <span class="new"><img src="/images/account/new.png"></span> </a>
			</dd>-->
		<!--  	<dd>
				<a href="javascript:pleaseLook();"><i
					class="lefticon debtSubrogation"></i> 债权转让 </a>
			</dd>-->
			<!-- <dd>
				<a href="javascript:pleaseLook();"><i class="lefticon sureMoney"></i>
					定期宝 </a>
			</dd> -->

			<dd>
				<a href="${pageContext.request.contextPath}/borrowAuto/borrowAuto">
					<i class="lefticon autoInvest"></i> 设置自动投标
				</a>
			</dd>

		</dl>
		<dl class="item">
			<dt>我的借款</dt>
			<dd>
				<a href="${pageContext.request.contextPath }/borrow/myRecord"><i
					class="lefticon borrowRecord"></i> 借款记录 </a>
			</dd>
			<dd>
				<a  href="${pageContext.request.contextPath }/borrow/releaseBorrow"  ><i
					class="lefticon releaseBorrow"></i> 发布借款 </a>
			</dd>
			<dd>
				<a
					href="${pageContext.request.contextPath }/borrowRepayment/myRepay"><i
					class="lefticon limitApply"></i> 我要还款 </a>
			</dd>
		</dl>



		<dl class="item">
			<dt class="safeCenter">
				<a href="${pageContext.request.contextPath }/user/safeCenter"
					class="ss">安全中心</a>
			</dt>
		</dl>



		
	</div>

</div>

<script type="text/javascript">
	$(function() {
		if(window.location.pathname == '/userAmount/toApplyOnlinePage'){
			$('.item dd a[href="/borrow/releaseBorrow"]').parent().addClass("current");
		}
		$('.item dd a[href="' + window.location.pathname + '"]').parent()
				.addClass("current");
		$('.item dt a[href="' + window.location.pathname + '"]').addClass(
				"current")
	})
</script>















<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/tip.css" />
<script src="${pageContext.request.contextPath}/js/tip.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$(".detailItem").eq(0).show();

			$(".licolor").click(
					function() {

						var index = $(".licolor").index($(this));

						$(".detailItem").eq(index).show().siblings(
								".detailItem").hide();

					});

			
			//tips
			$(".lv1,.lv2,.lv3,.lv4,.lv5").poshytip({
				className : 'tip-yellowsimple',
				showTimeout : 1,
				alignTo : 'target',
				alignX : 'center',
				offsetY : 8,
				allowTipHover : true
			});
			
		});
	</script>
<script type="text/javascript">
function disp_alert()
{
alert("新手活动已经结束!")
}
</script>