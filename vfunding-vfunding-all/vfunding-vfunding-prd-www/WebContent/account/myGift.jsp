<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/myGift.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/myAccount.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	if(window.location.hash = '#feel'){
		$(".giftManage li").eq(1).css({
			background : "#0065b4",
			color : "#fff"
		});
		$(".gift").find(".giftControl").eq(1).show();

	}
	$('#myCode').click(function(){
			$.ajax({
				url : '/accountFeel/myCodeList',
				type : "post",
				success : function(result) {
					result = $.parseJSON(result);
						var head = '<tr>'+
						'<td width="72" height="48" class="btno">编号</td>'+
						'<td width="72">类型</td><td width="92">抵用金额</td>'+
						'<td width="104">获得时间</td><td width="104">过期时间</td>'+
						'<td width="142">备注</td>'+
						'</tr>';
					var content = '';
					if(result.obj !=null){
						for(var i=0;i<result.obj.length;i++){
							content += '<tr>'+
							'<td width="72" height="48">VF'+result.obj[i].no+'</td>'+
							'<td width="72">活动券</td>'+
							'<td width="92">'+result.obj[i].money+' 元</td>'+
							'<td width="104">'+new Date(parseInt(result.obj[i].addtime)*1000).toLocaleString().substr(0,10)+'</td>'+
							'<td width="104">'+new Date((parseInt(result.obj[i].addtime)+2592000)*1000).toLocaleString().substr(0,10)+'</td>'+
							'<td width="142">'+(result.obj[i].status==1?'活动返利':'已使用')+'</td>'+
							'</tr>'
						}
					}
					$("#myCashCoupon").html(head+content);
				}
			});
	});
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
		if($('#pwd1').val()==null||$('#pwd2').val()==null||$('#pwd3').val()==null||$('#pwd4').val()==null){
			$('#pwd1').focus();
			alert('请输入完整体验码.');
		} else if($('#pwd1').val()==''||$('#pwd2').val()==''||$('#pwd3').val()==''||$('#pwd4').val()==''){
			$('#pwd1').focus();
			alert('请输入完整体验码.');
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
  <div class="rT">
    <div class="rTWrap">
      <div class="txTop">
        <b class="midLine"></b>
        <h2>我的礼品</h2>
      </div>
 <div class="giftManage">
   <ul>
     <li>我的奖品</li>
     <li>现金券激活</li>
     <li id="myCode">我的抵用券</li>
     <li>我的地址</li>
   </ul>
 </div>     
   <div class="gift">
     <div class="giftControl myPrize">
       <table width="780"  cellpadding="0" cellspacing="0" id="myPrize">
  <tr>
    <td  width="390" height="48" class="prize">奖品名称</td>
    <td width="390" class="prize">获得时间</td>
  </tr>
  <tr>
      <td  width="390" height="48">&nbsp;</td>
    <td width="390" >&nbsp;</td>
  </tr>
  <tr>
       <td  width="390" height="48">&nbsp;</td>
    <td width="390" >&nbsp;</td>
  </tr>
</table>
	</div>
	<div class="giftControl cashCoupon">
		<div class="Coupon">
		<form action="/rechargeFeel" name="Coupon" id="Coupon" method="post">
			<table width="600" cellpadding="0" cellspacing="0" id="noborder">
				<tr>
					<td width="126">请输入现金券密码:</td>
					<td  width="380" height="30">
						<input type="text" id="pwd1" name="" class="inputPwd" maxlength="16"/> -
						<input type="text" id="pwd2" name="" class="inputPwd" maxlength="4"/> -
						<input type="text" id="pwd3" name="" class="inputPwd" maxlength="4"/> -
						<input type="text" id="pwd4" name="" class="inputPwd" maxlength="4"/>
						<input type="hidden" id="feelCode" name="feelCode" maxlength="16"/>
					</td>
					<td height="30">
						<a id="jihuo" href="javascript:void(0)">激活</a>
					</td>
				</tr>
				
				<tr>
					<td width="126"></td>
					<td width="380" height="30" id="shuoming"></td>
				</tr>
				
				
				<tr id="result_" style="display:none;">
					<td colspan="2" align="center"><span id="msg_" style="color:red;"></span></td>
				</tr>
				
			</table>
		</form>
		</div>
	</div>
     <div class="giftControl myCashCoupon">
	<table width="780" cellpadding="0" cellspacing="0" id="myCashCoupon">
		
	</table>
 </div>
     <div class="giftControl myAddress">
       <table width="326" cellpadding="0" cellspacing="0" id="myAddress">
  <tr>
    <td width="94" height="50">收件人姓名:</td>
    <td width="232"><input type="text" id="recipients"/></td>
  </tr>
  <tr>
   <td width="94" height="50">收件人电话:</td>
    <td width="232"><input type="text" id="recipientsPhone"/></td>
  </tr>
  <tr>
    <td width="94" height="50">收件人地址:</td>
    <td width="232"><input type="text" id="recipientsAddress"/></td>
  </tr>
  <tr>
   <td width="94" height="50">收件人邮箱:</td>
    <td width="232"><input type="text" id="recipientsEmail"/></td>
  </tr>
  <tr>
   <td width="94" height="50">&nbsp;</td>
    <td width="232">
      <a href="#" id="upBtn">提交</a>
    </td>
  </tr>
</table>
     </div>     
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