<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/chosen.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/releaseBorrow.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/chosen.jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/borrow.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/card.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<!-- 上传需要的CSS和JS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/upload/upload.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/upload/ajaxupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/upload/borrowUpload.js"></script>
<!-- 弹出层JS -->
<script src="${pageContext.request.contextPath}/js/artDialog/artDialog.js?skin=default"></script>
<style>
.input_ {
    height: 25px;
    width: 140px;
}
</style>
<script>
$(function(){
	$("#byday").click(function(){
		$("tr.hideTr").toggle();
	});
	$(".persnoalInfo").eq(0).slideDown(1000);
	$(".infobar li").click(function(){
		$(this).removeClass("grayBg").addClass("infoBg").siblings().removeClass("infoBg").addClass("grayBg");
		var index = $(".infobar").find("li").index($(this));
		$(".persnoalInfo").eq(index).slideDown(1000).siblings().hide();
	});
	$("#dy-kind,#pay-type,#borrow-use,#tenderMoney,#yes-no-diya, #yes-nom,#validTime,#b-limit2,#b-limit,#yes-no,#jiguan").chosen({
		no_results_text: "没有找到",
		allow_single_de: true
	});
	
	
	//发标结果
	if('${msg}' != null && '${msg}' != ''){
		art.dialog({
			drag: false,
			icon: 'face-smile',
			title:'微积金提示',
			left: '48%',
			content : '${msg}',
			lock: true,
			cancelVal: '关闭',
			cancel:function(){
				window.location.href="/borrow/myRecord";
			},
			button: [{
				name:'继续发标',
				callback:function(){
					window.location.href="/borrow/releaseBorrow";
				},
				focus: true
			}]
		});
	}
	
	$(".biaokinds li").each(function() {
		$(this).click(function() {
			var text = $(this).text();
			$(this).removeClass("biaoBg").addClass("cur").siblings().removeClass("cur").addClass("biaoBg");
			if(text=='净值标'||text=='信用标'){
				$(".biaokinds").find("li").eq(0).removeClass("biaoBg").addClass("cur").siblings().removeClass("cur").addClass("biaoBg");
				art.dialog({
					icon: 'warning',
					title:'微积金提示',
					left: '48%',
					content : '尚未开放，敬请期待...',
					lock: true,
					button: [{
						name:'确定',
						focus: true
					}]
				});
			}
		})
	});
	
	var diyapinzhong = "${(borrowvo.mortgageTypeid==null||borrowvo.mortgageTypeid==1)?'汽车抵押':'债权抵押'}";
	if(diyapinzhong == "汽车抵押"){
		$('#isContract').hide();
	}else{
		$('#isContract').show();
	}
	
	$('#dy-kind').change(function(){
		if(this.value == '债权抵押'){
			$('#isContract').show();
		} else {
			$('#isContract').hide();
		}
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
	<div class="rT">
	<div class="rTWrap">
		<div class="txTop">
		  <b class="midLine"></b>
		  <h2>发布借款</h2>
		</div>
   	<div class="canUseLimit"><span>可借总额度:</span>&nbsp;
   		<font id="amount_">
   			<fmt:formatNumber value="${amount.borrowVouchUse}" pattern="#,##0.00"/>
   		</font>元
   		<input type="hidden" id="credit_" value="${amount.borrowVouchUse}"/>
   		&nbsp;&nbsp;<span id="amount_msg" style="color:#ff5555;"></span>
   	</div>
		<div class="biaokinds">
			<ul>
			    <li class="cur">抵押标</li>
			    <li class="biaoBg">信用标</li>
			    <li class="biaoBg">净值标</li>
			</ul>
		</div>
<div class="diya">
	<form action="/borrow/toReleaseBorrow" method="post" id="release" enctype="multipart/form-data">
	
	<input id="carId" value="${borrowvo.mortgageId }" style="display:none">
	
	<input type="hidden" name="handleType" value="${borrowvo.handleType==2?borrowvo.handleType:''}" />
    <div class="dy-amount">
	<table width="730" cellpadding="0" cellspacing="0">
	<tr>
	    <td width="120" height="48" class="txtRight">借款总金额：</td>
	    <td width="150">
			<input type="text" class="jk-amount" name="account" id="account" onkeyup="this.value=this.value.split('.')[0].replace(/\D/g,'');" value="${borrowvo.account}"/>
			<input type="hidden" id="biaoType" name="biaoType" value="fast"/>
			<input type="hidden" name="borrowId" value="${borrowvo.handleType==2?borrowvo.id:''}"/>
			<input type="hidden" name="mcId" value="${borrowvo.handleType==2?mortgageCar.id:''}"/>
			<span id="account_msg"></span>
	    </td>
	    <td width="220" class="txtRight">年利率：</td>
	    <td width="240">
			<input type="text" class="jk-rate" name="apr" id="apr" value="${borrowvo.apr }"/> %
			<p id="apr_msg"></p>
	    </td>
	</tr>
	<tr>
		<td width="120" height="48" class="txtRight">金额大写：</td>
		<td width="150">
		<input type="text" class="amountCase" value="${borrowvo.bigAccount }" id="amountCase" readonly="readonly"/>
		</td>
		<td width="220" class="txtRight">抵押品种：</td>
		<td width="240">
		<select name="typeName" id="dy-kind" data-placeholder="${(borrowvo.mortgageTypeid==null||borrowvo.mortgageTypeid==1)?'汽车抵押':'债权抵押'}">
			<option value="${(borrowvo.mortgageTypeid==null||borrowvo.mortgageTypeid==1)?'汽车抵押':'债权抵押'}">
			</option>
			<option value="汽车抵押">汽车抵押</option>
			<option value="债权抵押">债权抵押</option>
		</select>
		<p id="dy-kind_msg"></p>
		</td>
	</tr>
	<tr>
		<td width="120" height="48" class="txtRight">借款期限：</td>
		<td width="150">
			<div class="jkwrap">
				<div class="jkqx">
					<select name="timeLimit" id="b-limit" data-placeholder="${empty borrowvo.timeLimit?'1':borrowvo.timeLimit}个月">
						<option value="${empty borrowvo.timeLimit?'1':borrowvo.timeLimit}">
						</option>
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
					<span id="b-limit_msg"></span>
				</div>
				<div class="jkqx" id="jkqx2">
					<select name="timeLimitDay" id="b-limit2" data-placeholder="请选择">
						<option value="0"></option>
						<option value="1">1天</option>
						<option value="2">2天</option>
						<option value="3">3天</option>
					</select>
					<p id="b-limit2_msg"></p>
				</div>
				<!-- div class="by"> 
					<input type="checkbox" id="byday">
					<label for="byday">按天</label>
				</div> -->
			</div>
		</td>
		<td width="220" class="txtRight">有效时间：</td>
		<td width="240">
			<select name="validTime" id="validTime" data-placeholder="${empty borrowvo.validTime?'7':borrowvo.validTime}天">
				<option value="${empty borrowvo.validTime?'7':borrowvo.validTime}">
				</option>
				<option value="1">1天</option>
				<option value="2">2天</option>
				<option value="3">3天</option>
				<option value="4">4天</option>
				<option value="5">5天</option>
				<option value="6">6天</option>
				<option value="7">7天</option>
				<option value="8">永不</option>
			</select>
			<p id="validTime_msg"></p>
		</td>
	</tr>
	<tr class="hideTr">
		<td width="120" height="48" class="txtRight">是否天标：</td>
		<td width="150">是
		</td>
		<td width="460" colspan="2" class="txtRight">借款成功后，系统将按照每月30天来计算借款利息&nbsp;&nbsp;<a href="#" target="_blank">天标系统说明</a></td>
	</tr>
	<tr>
	    <td width="120" height="48" class="txtRight">还款方式：</td>
		<td width="150">
			<div class="selectDiv">
				<select name="style" id="pay-type"
				 data-placeholder="${empty borrowvo.style?'等额本息':(borrowvo.style==0?'等额本息':(borrowvo.style==3?'先息后本':(borrowvo.style==1?'等本等息':'到期还款')))}">
					<option value="${empty borrowvo.style?'0':borrowvo.style}">
					</option>
					<option value="0">等额本息</option>
					<option value="3">先息后本</option>
					<option value="2">到期还款</option>
					<option value="1">等本等息</option>
				</select>
				<p id="pay-type_msg"></p>
			</div>
		</td>
		<td width="220" class="txtRight">是否担保：</td>
		<td width="240">
			<div class="selectDiv">
				<select name="danbao" id="yes-no" data-placeholder="${empty borrowvo.danbao?'是':(borrowvo.danbao==1?'是':'否')}">
					<option value="${empty borrowvo.danbao?'1':borrowvo.danbao}">
					</option>
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
				<p id="yes-no_msg"></p>
			</div>
		</td>
	</tr>
<tr>
    <td width="120" height="48" class="txtRight">是否抵押标：</td>
    <td width="150">
     <select name="is_fast" id="yes-no-diya" data-placeholder="请选择" disabled="disabled">
     	<option value="-1"></option>
		<option value="1" selected="selected">是</option>
		<option value="2">否</option>
     </select>
     <p id="yes-no-diya_msg"></p>
    </td>
    <td width="220" class="txtRight">
     最低投标金额：
    </td>
    <td width="240">
	<select name="lowestAccount" id="tenderMoney" data-placeholder="${empty borrowvo.lowestAccount?'50':borrowvo.lowestAccount}元">
		<option value="${empty borrowvo.lowestAccount?'50':borrowvo.lowestAccount}">
		</option>
		<option value="50">50元</option>
		<option value="100">100元</option>
    </select>
    <p id="tenderMoney_msg"></p>
    </td>
  </tr>
  <tr>
    <td width="120" height="48" class="txtRight">设定向标密码：</td>
    <td width="150">
    <input type="password" class="pwd-set" name="pwd" id="pwd" value="${borrowvo.pwd }" readonly="readonly"/>
    </td>
    <td width="120" height="48" class="txtRight">借款用途：</td>
		<td width="150">
			<select name="use" id="borrow-use" data-placeholder="${empty borrowvo.use?'短期周转':(borrowvo.use==1?'短期周转':
				(borrowvo.use==2?'生意周转':(borrowvo.use==3?'生活周转':
				(borrowvo.use==4?'购物消费':(borrowvo.use==5?'不提现借款':(borrowvo.use==6?'其它借款':'创业借款'))))))}">
				<option value="${empty borrowvo.use?'1':borrowvo.use}">
				</option>
				<option value="1">短期周转</option>
				<option value="2">生意周转</option>
				<option value="3">生活周转</option>
				<option value="4">购物消费</option>
				<option value="5">不提现借款</option>
				<option value="6">其它借款</option>
				<option value="7">创业借款</option>
			</select>
			<p id="borrow-use_msg"></p>
		</td>
    <%-- <td width="220" class="txtRight">标的图片上传：</td>
    <td width="240">
		<input type="text" id="litpic" readonly="readonly" class="input_" value="${borrowvo.litpic}"/>
		<div id="bpic" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit">浏览</div>
       	<span id="litpic_msg" style="color:red;"></span>
    </td> --%>
  </tr>
  
  <tr id="isContract">
    <td width="120" height="48" class="txtRight">合同开始时间：</td>
    <td width="150">
    	<input type="text" class="pwd-set" name="contract_Start" id="contractStart" value="<fmt:formatDate value='${borrowvo.contractStart}' type='both' pattern='yyyy-MM-dd'/>"
    	 readonly="readonly" onClick="WdatePicker(({realDateFmt:'yyyy-MM-dd'}))"/>
		<p id="contractStart_msg"></p>
    </td>
    <td width="120" height="48" class="txtRight">合同结束时间：</td>
		<td width="150">
	    	<input type="text" class="pwd-set" name="contract_End" id="contractEnd" value="<fmt:formatDate value='${borrowvo.contractEnd }' type='both' pattern='yyyy-MM-dd'/>"
	    	 readonly="readonly" onClick="WdatePicker(({realDateFmt:'yyyy-MM-dd'}))"/>
			<p id="contractEnd_msg"></p>
		</td>
  </tr>
  
 
</table>
    </div>
    <div class="dy-info">
      <div class="dyInfo">抵押物信息</div>
        <table width="730" cellpadding="0" cellspacing="0" id="dyTable">
  <tr>
    <td width="130" height="48" class="txtRight">车主姓名：</td>
    <td width="150">
    <input type="text" class="jk-amount" id="carHost" name="ownerName" value="${mortgageCar.ownerName}"/>
    <span id="carHost_msg"></span>
    </td>
    <td width="220" class="txtRight">车主身份证号码：</td>
    <td width="240">
    <input type="text" class="jk-amount" id="idcarHost" name="ownerCardid" value="${mortgageCar.ownerCardid }"/>
    <p id="idcarHost_msg"></p>
    </td>
  </tr>
  <tr>
    <td width="120" height="48" class="txtRight">车主手机号码：</td>
    <td width="150">
    
    <input type="text" class="jk-amount" id="phoneHost" name="ownerMobile" maxlength="11" value="${mortgageCar.ownerMobile}"/>
    <span id="phoneHost_msg"></span>
    </td>
    <td width="220" class="txtRight">车主籍贯：</td>
    <td width="240">
	<%-- <input type="text" class="jk-amount" id="jiguan" name="ownerAddress" value="${mortgageCar.ownerAddress}"/> --%>
		<select name="ownerAddress" id="jiguan" data-placeholder="${mortgageCar.ownerAddress==null?'请选择':mortgageCar.ownerAddress}">
				<option value="${mortgageCar.ownerAddress}">${mortgageCar.ownerAddress}</option>
				<c:forEach items="${areas}" var="areas">
					<option value="${areas.name}">${areas.name}</option>
				</c:forEach>
		</select>
		<p id="jiguan_msg"></p>
    </td>
  </tr>
  <tr>
    <td width="120" height="48" class="txtRight">车架号：</td>
    <td width="150">
    <input type="text" class="jk-amount" id="chejia" name="carStrutNum" value="${mortgageCar.carStrutNum }"/>
    <p id="chejia_msg"></p>
    </td>
    <td width="220" class="txtRight">车牌号：</td>
    <td width="240">
    <input type="text" class="jk-amount" id="carNo" name="carLicense" value="${mortgageCar.carLicense}"/>
    <p id="carNo_msg"></p>
    </td>
  </tr>
  <tr>
    <td width="120" height="48" class="txtRight">发动机号：</td>
    <td width="150">
    <input type="text" class="jk-amount" id="enginNo" name="carNumber" value="${mortgageCar.carNumber }"/>
    <p id="enginNo_msg"></p>
    </td>
    <td width="220" class="txtRight">注册日期：</td>
    <td width="240">
     <input type="text" class="jk-amount" id="zhuceDate" name="register_Date" readonly="readonly" onClick="WdatePicker(({realDateFmt:'yyyy-MM-dd'}))"
     readonly="readonly" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${mortgageCar.certificationDate}" type="both"/>"/>
     <p id="zhuceDate_msg"></p>
    </td>
  </tr>
  <tr>
   <td width="120" height="48" class="txtRight">发证日期：</td>
    <td width="150">
       <input type="text" class="jk-amount" id="fazhengDate" name="certification_Date"
        onClick="WdatePicker()" readonly="readonly" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${mortgageCar.certificationDate}" type="both"/>"/>
       <p id="fazhengDate_msg"></p>
    </td>
    <td width="220" class="txtRight">检验有效期：</td>
    <td width="240">
     <input type="text" class="jk-amount" id="jianyanDate" name="checkValid_Date"
      onClick="WdatePicker(({dateFmt:'yyyy-MM'}))" readonly="readonly"
      value="<fmt:formatDate pattern="yyyy-MM" value="${mortgageCar.checkValidDate}" type="both"/>"/>
      <p id="jianyanDate_msg"></p>
    </td>
  </tr>
  <tr>
     <td colspan="4" height="48" class="b-title">
      <div class="bt">标题：</div><div class="inputbox">
      	<input type="text" class="borrowTitle" id="borrowTitle" name="name" value="${borrowvo.name }"/>
	      <p id="borrowTitle_msg"></p>
      </div>
     </td>
  </tr>
  <tr>
   <td colspan="4" height="48" class="b-title">
   <div class="bt">借款描述：</div><div class="inputbox">
   		<textarea rows="5" cols="10" class="borrowTitle" id="borroweEx" name="content">${borrowvo.content }</textarea>
	      <p id="borroweEx_msg"></p>
   	</div>
   </td>
  </tr>
  <tr>
    <td width="120" height="48" class="txtRight"><font>*</font>身份证正面：</td>
    <td colspan="2" width="360">
      <div class="scan fl">
        <input type="text" id="card_pic1" readonly="readonly" class="input_" value="${cardpic1[0].pic}"/>
        <input type="text" style="display: none" value="${cardpic1[0].id }">
		<div id="btnUp1" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit">浏览</div>
       	<span id="card_pic1_msg" style="color:#ff5555;"></span>
      </div>
      <div class="carnum fr"><font>*</font>车辆照片1:</div>
    </td>
    <td width="240">
    <div class="scan fl">
      	<input type="text" id="car_pic1" readonly="readonly" class="input_" value="${carpic[0].pic}"/>
      	<input type="text" style="display: none" value="${carpic[0].id }">
		<div id="btnUp2" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit">浏览</div>
       	<span id="car_pic1_msg" style="color:#ff5555;"></span>
      </div>
    </td>
  </tr>
  <tr>
        <td width="120" height="48" class="txtRight"><font>*</font>身份证反面：</td>
    <td colspan="2" width="360">
      <div class="scan fl">
       <input type="text" id="card_pic2" readonly="readonly" class="input_" value="${cardpic2[0].pic}"/>
       <input type="text" style="display: none" value="${cardpic2[0].id }">
		<div id="btnUp3" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit" title="card_pic2">浏览</div>
       	<span id="card_pic2_msg" style="color:#ff5555;"></span>
      </div>
      <div class="carnum fr"><font>*</font>车辆照片2:</div>
    </td>
    <td width="240">
    <div class="scan fl">
       <input type="text" id="car_pic2" readonly="readonly" class="input_" value="${carpic[1].pic}"/>
       <input type="text" style="display: none" value="${carpic[1].id }">
		<div id="btnUp4" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit" title="car_pic2">浏览</div>
       	<span id="car_pic2_msg" style="color:#ff5555;"></span>
      </div>
    </td>
  </tr>
  <tr>
        <td width="120" height="48" class="txtRight"><font>*</font>车辆行驶证1：</td>
    <td colspan="2" width="360">
      <div class="scan fl">
        <input type="text" id="carcard_pic1" readonly="readonly" class="input_" value="${carcardpic[0].pic}"/>
        <input type="text" style="display: none" value="${carcardpic[0].id }">
		<div id="btnUp5" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit" title="carcard_pic1">浏览</div>
       	<span id="carcard_pic1_msg" style="color:#ff5555;"></span>
      </div>
      <div class="carnum fr"><font>*</font>车辆照片3:</div>
    </td>
    <td width="240">
    <div class="scan fl">
        <input type="text" id="car_pic3" readonly="readonly" class="input_" value="${carpic[2].pic}"/>
        <input type="text" style="display: none" value="${carpic[2].id }">
		<div id="btnUp6" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit" title="car_pic3">浏览</div>
       	<span id="car_pic3_msg" style="color:#ff5555;"></span>
      </div>
    </td>
  </tr>
  <tr>
        <td width="120" height="48" class="txtRight"><font></font>车辆行驶证2：</td>
    <td colspan="2" width="360">
      <div class="scan fl">
       <input type="text" id="carcard_pic2" readonly="readonly" class="input_" value="${carcardpic[1].pic}"/>
       <input type="text" style="display: none" value="${carcardpic[1].id }">
		<div id="btnUp7" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit" title="carcard_pic2">浏览</div>
       	<span id="carcard_pic2_msg" style="color:#ff5555;"></span>
      </div>
      <div class="carnum fr"><font>*</font>车辆照片4:</div>
    </td>
    <td width="240">
    <div class="scan fl">
       <input type="text" id="car_pic4" readonly="readonly" class="input_" value="${carpic[3].pic}"/>
       <input type="text" style="display: none" value="${carpic[3].id }">
		<div id="btnUp8" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit" title="car_pic4">浏览</div>
       	<span id="car_pic4_msg" style="color:#ff5555;"></span>
      </div>
    </td>
  </tr>
   <tr>
        <td width="120" height="48" class="txtRight"><font></font>车辆行驶证3：</td>
    <td colspan="2" width="360">
      <div class="scan fl">
       <input type="text" id="carcard_pic3" readonly="readonly" class="input_" value="${carcardpic[2].pic}"/>
       <input type="text" style="display: none" value="${carcardpic[2].id }">
		<div id="btnUp9" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit" title="carcard_pic3">浏览</div>
       	<span id="carcard_pic3_msg" style="color:#ff5555;"></span>
      </div>
      <div class="carnum fr"><font></font>其他:</div>
    </td>
    <td width="240">
    <div class="scan fl">
        <input type="text" id="other1" readonly="readonly" class="input_" value="${other[0].pic}"/>
        <input type="text" style="display: none" value="${other[0].id }">
		<div id="btnUp10" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit" title="other1">浏览</div>
       	<span id="other1_msg" style="color:#ff5555;"></span>
      </div>
    </td>
  </tr>
   <tr>
        <td width="120" height="48" class="txtRight"><font></font>其他：</td>
    <td colspan="2" width="360">
      <div class="scan fl">
       <input type="text" id="other2" readonly="readonly" class="input_" value="${other[1].pic}"/>
       <input type="text" style="display: none" value="${other[1].id }">
		<div id="btnUp11" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit" title="other2">浏览</div>
       	<span id="other2_msg" style="color:#ff5555;"></span>
      </div>
      <div class="carnum fr"><font></font>其他:</div>
    </td>
    <td width="240">
    <div class="scan fl">
       <input type="text" id="other3" readonly="readonly" class="input_" value="${other[2].pic}"/>
       <input type="text" style="display: none" value="${other[2].id }">
		<div id="btnUp12" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit" title="other3">浏览</div>
       	<span id="other3_msg" style="color:#ff5555;"></span>
      </div>
    </td>
  </tr>
</table>
    </div>
    <div class="account-set">
    	<div class="dyInfo">账户信息公开设置</div>
	    <div class="settings">
			<ul>
				<li>
					<input type="checkbox" id="myaccount" name="openAccount" value="1" ${borrowvo.openAccount==1?'checked':''}>
					<label for="myaccount">公开我的账户资金情况</label>
				</li>
				<li>
					<input type="checkbox" id="borrowmoney" name="openBorrow" value="1" ${(borrowvo.openBorrow==1||borrowvo.openBorrow==null)?'checked':''}>
					<label for="borrowmoney">公开我的借款资金情况</label>
				</li>
				<li>
					<input type="checkbox" id="tendermoney" name="openTender" value="1" ${(borrowvo.openTender==1||borrowvo.openTender==null)?'checked':''}>
					<label for="tendermoney">公开我的投标资金情况</label>
				</li>
				<li>
					<input type="checkbox" id="creditmoney" name="openCredit" value="1" ${(borrowvo.openCredit==1||borrowvo.openCredit==null)?'checked':''}>
					<label for="creditmoney">公开我的信用额度情况</label>
				</li>
			</ul>
	    </div>
    </div>
    <div class="btnbox">
    	<input type="submit" class="btn116" value="确认提交"/>
    </div>
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
