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
	href="${pageContext.request.contextPath}/css/chosen.css" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/pwdSafe.css" />

<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/chosen.jquery.js"></script>
<script>
	$(function() {
		$("select.que-select").chosen({
			no_results_text : "没有找到",
			allow_single_de : true
		});
		$("#pwdSafeBtn").click(function() {
			var answer = $("#answer").val();
			if (answer == "" || answer == null) {

				$(".answerTip").html("请输入密保答案");
				return;
			} else {
				$("#pwdAnswer").submit();
			}
		});

		$("#pwdSafeBtn2")
				.click(
						function() {
							var oldAnswer = $("#oldAnswer").val();
							var oldQuestion = $("#oldQuestion").val();
							var answer = $("#answer").val();
							if (oldAnswer == "" || oldAnswer == null) {
								$(".oldAnswerTip").html("请输入原始答案");
								return;
							} else if (answer == "" || answer == null) {
								$(".answerTip").html("请输入新的答案");
								return;
							} else {
								//$("#pwdAnswer").submit();
								$.ajax({
											url : '/user/verificationQuestion',
											data : {
												answer : oldAnswer,
												question : oldQuestion
											},
											cache : false,
											async : false,
											type : 'post',
											error : function(XMLHttpRequest,
													textStatus, errorThrown) {
												alert('很遗憾，出异常了' + errorThrown);
											},
											success : function(result) {
												result = $.parseJSON(result);
												if (result == true) {
													$("#pwdAnswer2").submit();
												} else {
													art
															.dialog({
																title : '微积金提示',
																icon : 'warning',
																left : '48%',
																content : '您输入的原始问题或答案有误',
																lock : true,
																ok : true
															});
												}
											},
											contentType : "application/x-www-form-urlencoded;charset=UTF-8"
										});
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
				<div class="rT box-shadow">
					<div class="rTWrap">
						<div class="txTop">
							<b class="midLine"></b>
							<h2 class="zhcz">密保问题</h2>
							<a href="javascript:history.go(-1);" class="goBack">返回</a>
						</div>

						<div class="pwdQue">
							<c:choose>
								<c:when test="${ empty loginedSession.question }">
									<form
										action="${pageContext.request.contextPath }/user/saveQuestion"
										name="pwdSafe" method="post" id="pwdAnswer">

										<table width="560" cellpadding="0" cellspacing="0"
											id="pwdSafe">
											<tr>
												<td width="90" height="48" class="textAlign">请选择问题：</td>
												<td width="470" class="relative"><select
													name="question" class="que-select" data-placeholder="请选择">
														<c:forEach items="${linkages}" var="linkage">
															<option value="${linkage.id }">${linkage.name }</option>
														</c:forEach>
												</select></td>
											</tr>
											<tr>
												<td width="90" height="48" class="textAlign">请输入答案：</td>
												<td width="470"><input type="text" id="answer"
													name="answer" /> <span class="answerTip"></span></td>
											</tr>
											<tr>
												<td width="90" height="48" class="textAlign"></td>
												<td width="470"><a href="#" id="pwdSafeBtn">提交</a></td>
											</tr>

										</table>

									</form>
								</c:when>
								<c:otherwise>
									<form
										action="${pageContext.request.contextPath }/user/saveQuestion"
										name="pwdSafe" method="post" id="pwdAnswer2">

										<table width="560" cellpadding="0" cellspacing="0"
											id="pwdSafe">
											<tr>
												<td width="160" height="48" class="textAlign">请选择原始问题：</td>
												<td width="470" class="relative"><select
													name="oldQuestion" class="que-select" id="oldQuestion"
													data-placeholder="请选择">
														<c:forEach items="${linkages}" var="linkage">
															<option value="${linkage.id }">${linkage.name }</option>
														</c:forEach>
												</select></td>
											</tr>
											<tr>
												<td width="160" height="48" class="textAlign">请输入原始答案：</td>
												<td width="470"><input type="text" id="oldAnswer"
													name="oldAnswer" /> <span class="oldAnswerTip"></span></td>
											</tr>
											<tr>
												<td width="160" height="48" class="textAlign">请选择新的问题：</td>
												<td width="470" class="relative"><select
													name="question" class="que-select" data-placeholder="请选择">
														<c:forEach items="${linkages}" var="linkage">
															<option value="${linkage.id }">${linkage.name }</option>
														</c:forEach>
												</select></td>
											</tr>
											<tr>
												<td width="160" height="48" class="textAlign">请输入新的答案：</td>
												<td width="470"><input type="text" id="answer"
													name="answer" /> <span class="answerTip"></span></td>
											</tr>
											<tr>
												<td width="160" height="48" class="textAlign"></td>
												<td width="470"><a href="#" id="pwdSafeBtn2">提交</a></td>
											</tr>
										</table>

									</form>
								</c:otherwise>
							</c:choose>


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


