<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/exprCard.css"/>
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/recharge.js"></script>
<script src="${pageContext.request.contextPath}/js/realName.js"></script>
<!-- 弹出层JS -->
<script src="${pageContext.request.contextPath}/js/artDialog/artDialog.js?skin=default"></script>
<script type="text/javascript">
function dialog_(face,content){
	art.dialog({
		icon: face,
		title:'微积金提示',
		content : content,
		lock: true,
		cancelVal: '关闭'
	});
}
$(document).ready(function(){
	$('#pwd1').keyup(function(){
		$('#pwd2').val($('#pwd1').val().substring(4,8));
		$('#pwd3').val($('#pwd1').val().substring(8,12));
		$('#pwd4').val($('#pwd1').val().substring(12,16));
		if($('#pwd1').val().length>=4){
			$('#pwd2').focus();
		}
		$('#pwd1').val($('#pwd1').val().substring(0,4));
	});
	$('#pwd2').keyup(function(){
		if($('#pwd2').val().length>=4){
			$('#pwd3').focus();
		}
	});
	$('#pwd3').keyup(function(){
		if($('#pwd3').val().length>=4){
			$('#pwd4').focus();
		}
	});
	$('#jihuo').click(function(){
		if ($("#realnameFlag").val() == "0") {
			webUtil.jDialog.Modal('dialogRealName', 'dialogRealNameCont');
			return;
		}
		if($('#pwd1').val()==null||$('#pwd2').val()==null||$('#pwd3').val()==null||$('#pwd4').val()==null){
			$('#pwd1').focus();
			dialog_('face-sad','请输入完整体验码.');
		} else if($('#pwd1').val()==''||$('#pwd2').val()==''||$('#pwd3').val()==''||$('#pwd4').val()==''){
			$('#pwd1').focus();
			dialog_('face-sad','请输入完整体验码.');
		} else {
			$('#feelCode').val($('#pwd1').val()+$('#pwd2').val()+$('#pwd3').val()+$('#pwd4').val());
			$.ajax({
				url : '/rechargeFeelWeb',
				type : "post",
				data : {
					"feelCode" : $('#feelCode').val()
				},
				beforeSend: function(){ 
					$('#jihuo').html('激活中...');
				}, 
				success : function(result) {
					result = $.parseJSON(result);
					$('#result_').show();
					if(result.success){
						$('#pwd1').val('');
						$('#pwd2').val('');
						$('#pwd3').val('');
						$('#pwd4').val('');
						$('#pwd1 pwd2 pwd3 pwd3').val();
						$('#jihuo').html('激活');
						$('#msg_').html(result.msg+"体验金余额为:"+result.obj.useMoney);
						window.location.href="/user/feelCard";
					} else {
						$('#msg_').html(result.msg);
						$('#jihuo').html('激活');
					}
				}
			});
		}
	});
});
</script>
</head>
<body>
<!-------------头部-------------------->
<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>
<!--中间-->
<div class="content">
	<div class="myAccount">
	<!-- 左边通用页面 -->
	<jsp:include page="${pageContext.request.contextPath }/user/accountLeft.jsp"></jsp:include>    
<div class="rechargeTakeCash fr">
  <div class="rT box-shadow">
    <div class="rTWrap">
      <div class="txTop">
        <b class="midLine"></b>
        <h2>我的体验金</h2>
      </div>
   <div class="gift">
     <div class="giftControl cashCoupon" style="display:block;">
     <div class="Coupon">
	<form action="#" name="Coupon" id="Coupon">
		<table width="600" cellpadding="0" cellspacing="0" id="noborder">
		  <tr>
		    <td width="126">请输入现金券密码:</td>
		    <td width="380" height="30">
		        <input type="text" id="pwd1" name="" class="inputPwd" maxlength="16"/> -
				<input type="text" id="pwd2" name="" class="inputPwd" maxlength="4"/> -
				<input type="text" id="pwd3" name="" class="inputPwd" maxlength="4"/> -
				<input type="text" id="pwd4" name="" class="inputPwd" maxlength="4"/>
				<input type="hidden" id="feelCode" name="feelCode" maxlength="16"/>
		    </td>
		    <td height="30">
		    	<input id="realnameFlag" value="${loginedSession.realStatus }" type="hidden">
		    	<a id="jihuo" href="javascript:void(0)">激活</a>
		    </td>
		  </tr>
		  
		  <tr>
		  
		    <td  height="30" id="shuoming" colspan="3" style="padding-top:10px;font-size:13px;color:#888;">体验金说明：
微积金体验金是微积金网提供给您体验本网站功能及收益的虚拟资金，通过投资体验标可以获得真实的（可提现或再投资）理财收益。
您在利用平台理财赚取高额收益的同时，也可向微积金客服索要体验金送给身边的亲朋好友，享受推荐好友的高额返利回报！</td>
		  </tr>
		  
		  <tr id="result_" style="display:none;">
			<td colspan="2" align="center"><span id="msg_" style="color:red;"></span></td>
		  </tr>
		  
		</table>
	</form>
 </div>
  </div>
   </div>
   <div class="exprMoney clear">
     <div class="sub-exprMoney">
      <div class="sib-div">
       <p class="tyj-canuse">可用体验金</p>
       <p class="tyj-num">${accountFeel.useMoney==null?0:accountFeel.useMoney}元</p>
      </div>
        <div class="sib-div">
       <p class="tyj-canuse">已投金额</p>
       <p class="tyj-num">
       <c:choose>
	       <c:when test="${not empty feels}">
		       ${3000-(accountFeel.useMoney==null?0:accountFeel.useMoney)}元
	       </c:when>
       		<c:otherwise>
       		0元
       		</c:otherwise>
       </c:choose>
       </p>
      </div>
     <div class="sib-div" id="noBoder_right">
       <p class="tyj-canuse">待收体验金收益</p>
       <p class="tyj-num"><fmt:formatNumber value="${accountFeel.other==null?0:accountFeel.other}" pattern="#,##0.00" />元</p>
     </div>
     </div>
   </div>
   <p class="tyj-tip">注：体验金在投标到期后系统会自动收回，收益归投资人。
   </p>
   <div class="tyj-tables">
    <h2>投资记录</h2>
    <table width="780" cellpadding="0" cellspacing="0">
  <tr>
    <td width="170" height="50" class="tr-grayColor">标题
    </td>
    <td width="95" class="tr-grayColor">借款人</td>
    <td width="80" class="tr-grayColor">借款人积分</td>
    <td width="90" class="tr-grayColor">净收益率</td>
    <td width="70" class="tr-grayColor">期限</td>
    <td width="107" class="tr-grayColor">投资金额</td>
    <td width="84" class="tr-grayColor">投标时间</td>
    <td width="84" class="tr-grayColor">应收利息</td>
  </tr>
  <c:forEach items="${tenders}" var="v">
	  <tr>
	    <td width="170" height="50">${v.borrowName}</td>
	    <td width="95">${v.borrowUser }</td>
	    <td width="80">${v.credit }</td>
	    <td width="90">${v.apr }%</td>
	    <td width="70">${v.timeLimit }天</td>
	    <td width="107">${v.money }</td>
	    <td width="84"><date:date parttern="yyyy-MM-dd" value="${v.addtime}"/></td>
	    <td width="84"><fmt:formatNumber value="${v.repaymentAccount-v.money}" pattern="#0.00"/></td>
	  </tr>
  </c:forEach>
  <c:if test="${empty tenders}">
		<tr>
		  <td height="50" colspan="8">暂无记录</td>
		</tr>
	</c:if>
</table>
   </div>
  <div class="tyj-tables">
    <h2>待收已收记录</h2>
    <table width="780" cellpadding="0" cellspacing="0">
  <tr>
    <td width="170" height="50" class="tr-grayColor">标题</td>
    <td width="95" class="tr-grayColor">应收日期</td>
    <td width="80" class="tr-grayColor">借款人</td>
    <td width="70" class="tr-grayColor">期限</td>
    <td width="90" class="tr-grayColor">待收本金</td>
    <td width="84" class="tr-grayColor">待收利息</td>
    <td width="107" class="tr-grayColor">待收总额</td>
    <td width="84" class="tr-grayColor">状态</td>
  </tr>
  <c:forEach items="${collections }" var="v">
	  <tr>
	    <td width="170" height="50">${v.borrowName}</td>
	    <td width="95">
	      <c:if test="${v.repayTime == null||v.repayTime == ''}">
			--
		  </c:if>
		  <c:if test="${v.repayTime != null&&v.repayTime != ''}">
		    <date:date parttern="yyyy-MM-dd" value="${v.repayTime}"/>
		  </c:if>
		</td>
	    <td width="90">${v.borrowUser}</td>
	    <td width="80">${v.timeLimit }天</td>
	    <td width="70">0</td>
	    <td width="84">${v.interest}</td>
	    <td width="107">${v.interest}</td>
	    <td width="84"><c:if test="${v.status==40}">已还</c:if><c:if test="${v.status==30}">未还</c:if><c:if test="${v.status==50}">待审</c:if></td>
	  </tr>
  </c:forEach>
  <c:if test="${empty collections}">
		<tr>
		  <td height="50" colspan="8">暂无记录</td>
		</tr>
	</c:if>
</table>
   </div>
  <div class="tyj-tables">
    <h2>充值记录</h2>
<table width="780" cellpadding="0" cellspacing="0">
	<tr>
	  <td width="195" height="50" class="tr-grayColor">激活时间</td>
	  <td width="195" class="tr-grayColor">金额</td>
	  <td width="195" class="tr-grayColor">有效期</td>
	  <td width="195" class="tr-grayColor">备注</td>
	</tr>
	<c:if test="${not empty feels}">
		<c:forEach items="${feels}" var="v">
			<tr>
			  <td width="195" height="50">
				<date:date parttern="yyyy-MM-dd" value="${v.addtime}"/>
			  </td>
			  <td width="195">${v.money}</td>
			  <td width="195"><date:date parttern="yyyy-MM-dd" value="${v.addtime+2592000}"/></td>
			  <td width="195">体验码：${v.code}</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty feels}">
		<tr>
		  <td height="50" colspan="4">暂无记录</td>
		</tr>
	</c:if>
</table>
   </div>
    </div>
</div>
</div>
</div>
</div>
	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
</body>
</html>
