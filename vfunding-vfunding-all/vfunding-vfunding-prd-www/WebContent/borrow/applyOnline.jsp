<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="微积金(www.vfunding.cn) 整合了民营汽车借贷机构、二手车商、第三方汽车服务机构等传统领域企业，形成了独特的针对汽车提供投融资对接服务的创新型O2O互联网汽车金融服务模式。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、汽车贷款等。" />
<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|微贷|红包|基金|微基金|小额贷款|微财富|宜车贷|二手车抵押|车贷|托管" />
<title>我要借款_微积金_中国领先的O2O互联网汽车金融平台</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/chosen.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/applyOnline.css"/>


<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/chosen.jquery.js"></script>
<!-- 弹出层JS -->
<script src="${pageContext.request.contextPath}/js/artDialog/artDialog.js?skin=default"></script>
<script>
$(function(){
	$(".borrowType").find("li").eq(0).css("border-color","#0065b4");
	$(".borrowType li").click(function(){
		$(this).css("border-color","#0065b4").find("i").addClass("selectedSymbol").end().siblings().css("border-color","#cccccc").find("i").removeClass("selectedSymbol");
	});
	$("#borrowArea,#diyakinds,#yearRate,#remark").chosen({
		no_results_text: "没有找到",
		allow_single_de: true
	});
	
	$(".creditBiao").click(function(){
		$('#diya').hide();
		$('#type').val('credit');
	});
	$(".diyaBiao").click(function(){
		$('#diya').show();
		$('#type').val('borrow_vouch');
	});
	
	
	$('#sub').click(function(){
		if($('#account').val() == ''){
			$('#account').focus();
			$('#account_msg').html('申请额度不能为空');
			return false;
		} else if($('#remark').val() == ''){
			$('#remark').focus();
			$('#remark_msg').html('借款期限不能为空');
			return false;
		} else if($('#diyakinds').val() == ''){
			$('#diyakinds').focus();
			$('#diyakinds_msg').html('抵押品种不能为空');
			return false;
		} else if($('#content').val() == ''){
			$('#content').focus();
			$('#content_msg').html('借款说明不能为空');
			return false;
		} else {
			$.ajax({
				url : "/userAmount/applyOnline",
				type : "post",
				data : $('#applyOnline').serialize(),
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert('很遗憾，出异常了' + errorThrown);
				},
				success : function(result) {
					result = $.parseJSON(result);
					art.dialog({
						drag: false,
						icon: 'face-smile',
						title:'微积金提示',
						content : result.msg,
						lock: true,
						cancelVal: '确定',
						cancel:function(){
							window.location.href="/user/account";
						}
					});
				}
			});
		}
	});
	$('#account').keyup(function(){
		this.value=this.value.split('.')[0];
		$.ajax({
			url : "/borrow/getMoneyUppercase",
			type : "post",
			data : {
				"account" : this.value
			},
			success : function(result) {
				result = $.parseJSON(result);
				$('#bigAccount').html(result.msg);
			}
		});
	});
})
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
        <h2>借款申请</h2>
      </div>
   <div class="applyOnline">
   <form id="applyOnline">
     <table width="730" cellpadding="0" cellspacing="0">
  <tr>
    <td width="120" height="48" class="txtRight">
    借款人姓名：
    </td>
    <td width="610">
    <div class="realname fl">${loginedSession.userName}</div>
      <!-- <div class="gocheck tipicon fr"><a href="#" target="_blank">去认证</a></div> -->
    </td>
  </tr>
  <tr>
     <td width="120" height="48" class="txtRight">借款人手机：</td>
    <td width="610">${loginedSession.phone}</td>
  </tr>
  <!-- tr>
   <td width="120" height="48" class="txtRight">借款人地区：</td>
    <td width="610">
    <select name="borrowArea" id="borrowArea" data-placeholder="请选择">
		<option value="-1"></option>
		<option value="1">上海</option>
		<option value="1">北京</option>
		<option value="1">广州</option>
		<option value="1">南京</option>
		<option value="1">苏州</option>
    </select>
    </td>
  </tr> -->
  <tr>
   <td width="120" height="70" class="txtRight">借款类型：</td>
    <td width="610">
   <div class="borrowType">
      <ul>
       <li class="diyaBiao">
        <i class="selectedSymbol"></i>
      	抵押标
       </li>
     <!-- li class="creditBiao">
        <i></i>
      	信用标
       </li> -->
    </ul>
    <input type="hidden" id="type" name="type" value="borrow_vouch"/>
    </div>  
    </td>
  </tr>
  <tr id="diya">
 <td width="120" height="70" class="txtRight">抵押品种：</td>
    <td width="610">
       <select name="mortgagetypeId" id="diyakinds" data-placeholder="请选择">
       <option value="1">汽车</option>
       <option value="3">房产</option>
    </select>
    <span id="diyakinds_msg"></span>
    </td>
  </tr>
  <tr>
   <td width="120" height="70" class="txtRight">申请额度：</td>
    <td width="610">
      <input type="text" class="applyLimit" name="account" id="account" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="8"/>
      <span id="account_msg" style="color:#D35353;"></span>
      <span id="bigAccount"></span>
    </td>
  </tr>
  <tr>
  <td width="120" height="70" class="txtRight">借款期限：</td>
    <td width="610">
     <select name="limit" id="remark" data-placeholder="请选择">
       <option value="1">1个月</option>
       <option value="2">2个月</option>
       <option value="3">3个月</option>
       <option value="4">4个月</option>
       <option value="5">5个月</option>
       <option value="6">6个月</option>
       <option value="7">7个月</option>
       <option value="8">8个月</option>
       <option value="9">9个月</option>
       <option value="10">10个月</option>
       <option value="11">11个月</option>
       <option value="12">12个月</option>
    </select> 
    <span id="remark_msg" style="color:red;"></span>
    </td>
  </tr>
  <!-- tr>
	<td width="120" height="70" class="txtRight">年利率：</td>
    <td width="610">
	<select name="yearRate" id="yearRate" data-placeholder="请选择">
		<option value="-1"></option>
		<option value="1">10</option>
		<option value="1">12</option>
	</select>
     %
    </td>
  </tr> -->
  <tr>
  <td width="120" height="70" class="txtRight">借款说明：</td>
    <td width="610">
      <textarea id="content" name="content" cols="50" rows="7"></textarea>
      <span id="content_msg" style="color:red;"></span>
    </td>
  </tr>
  <tr>
  <td width="120" height="90" class="txtRight"></td>
    <td width="610">
     <input type="button" id="sub" class="btn116" value="提交申请"/>
    </td>
  </tr>
</table>
 </form>  
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