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
	href="${pageContext.request.contextPath}/css/applyvip.css" />

<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>


<script>
	$(function() {

		$("#vipForm").validate({

			submitHandler : function(form) {
				//form.submit();
				var hongbao;
				if ($("#hongbao").attr("checked")) {
					hongbao = 'yes'
				} else {
					hongbao = 'no'
				}
				$.ajax({
					type : "POST",
					url : "/user/becomeVIP",
					dataType : "json",
					data : {
						'useHongbao' :hongbao
					},
					async : false,
					success : function(data) {
						if (data.success) {
							art.dialog({
								content : '恭喜您已经晋升为VIP用户',
								ok : function() {
									location.href = '/user/safeCenter';
								},								
								lock : true,
								icon : 'succeed'
							});

						} else {
							art.dialog({
								content : data.msg,
								ok : true,
								lock : true,
								icon : 'error'
							});
						}
					},
					beforeSend : function(XHR) {
						$("#vipForm input").attr('disabled', true);
					},
					complete : function(XHR, TS) {
						$("#vipForm input").attr('disabled', false);
					}
				});

			}

		});

	})
</script>


<!--[if IE 6]>
         <script src="js/DD_belatedPNG_0.0.8a.js"></script> 
         <script>
         
          $(function(){
          
          DD_belatedPNG.fix('*');
          
          })
         
         </script> 
         
      <![endif]-->

</head>

<body>
	<!---头部--->
	<div id="header">

		<!-------------头部-------------------->
		<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>

		<!--中间-->

		<div class="content">
			<div class="myAccount">
				<!-- 左边通用页面 -->
				<jsp:include
					page="${pageContext.request.contextPath }/user/accountLeft.jsp"></jsp:include>
				<!-- 右边主体部分 开始 -->
				<div class="rechargeTakeCash fr">
					<div class="rT box-shadow">
						<div class="rTWrap">

							<div class="txTop">
								<b class="midLine"></b>
								<h2 class="zhcz">申请VIP</h2>
								<a href="javascript:history.go(-1);" class="goBack">返回</a>
							</div>


							<form method="post" id="vipForm">

								<table width="670" cellpadding="0">
									<tr>
										<td width="100" height="60" class="txtcolor">您的状态：</td>
										<td>${loginedSession.typeName }<c:choose>
												<c:when
													test="${loginedSession.typeId ==2 || loginedSession.typeId ==32}">

												<%-- 	<a style="color: #0065b4;"
														href="${pageContext.request.contextPath }/friend/financiaDetail">【申请理财师】</a> --%>

												</c:when>
												<c:otherwise>

												<%-- 	<a style="color: #0065b4;"
														href="${pageContext.request.contextPath }/friend/financiaDetai">【理财师升级】</a> --%>

												</c:otherwise>
											</c:choose>

										</td>
									</tr>
									<tr>
										<td width="100" height="60" class="txtcolor">用户名：</td>
										<td>${loginedSession.userName }</td>
									</tr>
									<tr>
										<td width="100" height="60" class="txtcolor">姓名：</td>
										<td><c:choose>
												<c:when test="${not empty loginedSession.realName }">
												${loginedSession.realName }
											</c:when>
												<c:otherwise>
												您还未实名认证<a style="color: #0065b4;"
														href="${pageContext.request.contextPath }/user/realName"
														target="_blank">【去认证】</a>
												</c:otherwise>
											</c:choose></td>
									</tr>
									<tr>
										<td width="100" height="60" class="txtcolor">邮箱：</td>
										<td><c:choose>
												<c:when test="${not empty loginedSession.email }">
												${loginedSession.email }
											</c:when>
												<c:otherwise>
												您还未填写邮箱信息<a style="color: #0065b4;"
														href="${pageContext.request.contextPath }/user/checkEmail"
														target="_blank">【去填写】</a>
												</c:otherwise>
											</c:choose></td>
									</tr>
									<tr>
										<td colspan="2" height="10"></td>
									</tr>
									<tr>
										<td width="100" height="66" class="txtcolor  txt-top">温馨提示：</td>
										<td class="txt-top">申请VIP会员将扣除VIP管理费用：150元。<br />您的当前可用余额为${account.useMoney }元，提现抵扣券余额为${hongbao}元，<c:if
												test="${showInfo==true }">不够扣除VIP费用，请先<a
													style="color: #0065b4;"
													href="${pageContext.request.contextPath }/account/recharge"
													target="_blank">【账户充值】</a>。</c:if> 本网站非VIP会员期间投标不享有本网站的垫付保障权益！
										</td>
									</tr>
									<tr>
										<td width="100" height="60" class="txtcolor">使用抵扣券：</td>
										<td><input type="checkbox" id="hongbao" checked style="vertical-align: middle;"/>  <span style="vertical-align: middle;">可用提现抵扣券：${hongbao}</span></td>
									</tr>

									<tr>
										<td width="100" height="60" class="txtcolor"></td>
										<td><c:choose>
												<c:when test="${showInfo==true }">
													<input type="submit" class="btn116" value="提交"
														disabled="disabled" style="background-color: #EEEEEE" />
												</c:when>
												<c:otherwise>
													<input type="submit" class="btn116" value="提交" />
												</c:otherwise>
											</c:choose></td>
									</tr>
									<tr>
										<td width="100" height="20" class="txtcolor"></td>
										<td><c:if test="${showInfo==true }">您的当前可用余额不足VIP申请费用150元，请充值后，再重新申请，谢谢。</c:if></td>
									</tr>
								</table>

							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!------尾部------>
		<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
</body>
</html>
