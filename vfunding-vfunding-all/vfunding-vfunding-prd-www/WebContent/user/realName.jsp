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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/ui-form.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/realName.css" />

<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/safeCenter.js"></script>

 <script type="text/javascript">
	     $(function(){
	    	 
	    	   $('#tongyi').click( function () {
	    	       if(!$(this).attr('checked')){
	    	    	   $('.tjBtn').attr('disabled', 'disabled').css({background:"#ccc",color:"#fff"});
	    	       }  else{
	    	    	   $('.tjBtn').css("background","#0065b4");
	    	    	   $('.tjBtn').removeAttr('disabled');
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
			<jsp:include
				page="${pageContext.request.contextPath }/user/accountLeft.jsp"></jsp:include>

			<div class="rechargeTakeCash fr">
				<div class="rT">
					<div class="rTWrap">
						<div class="txTop">
							<b class="midLine"></b>
							<h2 class="zhcz">实名认证</h2>
							<a href="javascript:history.go(-1);" class="goBack">返回</a>
						</div>

	                <div class="fund-trustee">
	                <span> 微积金已全面接入新浪支付全程资金托管，为了您的资金安全，银行卡必须与实名认证同名。</span>
	                </div>

						<div class="realname">

							<form id="realname" class="ui-form" >
								<fieldset>
									<div class="ui-form-item">
										<label class=" ui-label"> <span
											class=" ui-form-required"></span> 真实姓名：
										</label> <input type="text" class="ui-input truename" name="realname"
											id="truename" value="${user.realname }" />
									</div>

									<div class="ui-form-item sfz">
										<label class=" ui-label"> <span
											class=" ui-form-required"></span> 身份证：
										</label> <input type="text" class="ui-input idcode" name="cardId"
											id="idcode" value="${user.cardId }" style="text-transform: uppercase"/>

									</div>
									<div class="ui-form-item rz"></div>  
									<div class="ui-form-item h40">
										<span class="ui-checkbox"> <input type="hidden"
											checked="checked" /> <input type="checkbox" id="tongyi"
											checked="checked" /> <label for="tongyi">&nbsp;我已阅读并且同意<a
												href="${pageContext.request.contextPath}/utilpage/toPage/sinaPayService"
												target="_blank" class="agreement"> 《新浪支付服务使用协议》 </a>
										</label></span>
		
									</div>
									<div class="ui-form-item">

										<input type="submit" class="tjBtn " hidefocus="true" value="提交并开通新浪支付存钱罐">
									</div>



								</fieldset>

							</form>

						</div>

						<div class="realSucess">
							<p>您的资料已提交成功</p>
							<p>请耐心等待审核</p>
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


