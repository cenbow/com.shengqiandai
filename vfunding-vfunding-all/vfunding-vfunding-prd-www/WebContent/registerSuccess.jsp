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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ui-form.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/zhuce_success.css"/>
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/phone_verify.js"></script>

</head>
<body>

<!-------------头部-------------------->
	<jsp:include page="top.jsp"></jsp:include>
<!--中间-->


<div class="register2 box-shadow">

  <div class="reg_content">
  
      <div class="reg_logo">
        <h1 class="reg_logo_pic"></h1>
        <a href="#" class="go_login">注册完成，您已登陆微积金</a>
      </div>
     <div class="reg_conditions">
     
       <ol class="reg_step">
         <li class="step1">
           <span class="step_msg">
             <i class="icons dot24">1</i>
           <span class="whiteColor">填写账单信息</span>
           </span>
         </li>
         <li class="step2">
         
         <span class="step_msg">
           <i class="icons dot24">2</i>
               <span class="whiteColor">手机验证</span>
           </span>
         
         </li>
         <li class="step3">
         
         
         
         <span class="step_msg">
           <i class="icons dot24">3</i>
               <span class="whiteColor">注册成功</span>
           </span>
         
         
         </li>
       </ol>
       
   
     <div class="appLoad clear">
        <div class="wx fl">
          <div class="wx-pic app-icons"></div>
          <div class="wx-cnt">
            <p class="wx-attention">扫一扫关注微积金官方微信</p>
            <p class="wx-getCash">抢<span>红包</span>、抢<span>好礼</span> 不容错过</p>
            <p class="wx-share"> 随时随地，纵观理财资讯！</p>
          </div>
          <div class="wx-scan app-icons"></div>       
        </div>
        <div class="app-load fr">
          <div class="load-pic app-icons"></div>
          <div class="load-cnt">
            <p class="wx-attention">APP首笔投资超过2000元</p>
            <p class="wx-getCash">即<span>送5元红包</span></p>
            <p class="wx-share">财富增长，一手掌握！</p>
          </div>
          <div class="load-scan app-icons"></div>
        </div>
     </div>
   
   
   
   
       
       
    <div class="validator_success">
     
       <div class="success_tip right_error_bg">
          <p class="tip1">${loginedSession.userName }，恭喜您注册成功！</p>
          <p class="tip2">您已获得<span>5</span>元提现抵扣券，可用于抵扣提现手续费！</p>
       </div>
        
        
        <div class="goCharge">
          <a href="${pageContext.request.contextPath}/account/recharge" class="charge_btn">立即充值</a>
          <a  href="${pageContext.request.contextPath}/user/account" class="goCenter">进入我的账户</a>
        </div>
    
    </div>   
     </div>
  </div>
</div>
<!------尾部------>
	<jsp:include page="footer.jsp"></jsp:include></body>
</html>
