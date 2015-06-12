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
	href="${pageContext.request.contextPath}/css/persnoalInfo.css" />

<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/chosen.jquery.js"></script>

<!-- 分页需要的CSS和JS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/paging/style.css" media="screen" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/paging/jquery.paginate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/paging/userInfo.js"></script>
<!-- 上传需要的CSS和JS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/upload/upload.css" media="screen" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/upload/ajaxupload.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/upload/upload.js"></script>

<script>
	$(function() {
		$(
				"select.xueli,.company-industry,.company-scale,.income,.uploadType,#zhiwei")
				.chosen({
					no_results_text : "没有找到",
					allow_single_de : true

				});

		$(".infobar li").click(
				function() {
					$(this).removeClass("grayBg").addClass("infoBg").siblings()
							.removeClass("infoBg").addClass("grayBg");

					var index = $(".infobar").find("li").index($(this));

					$(".persnoalInfo").eq(index).slideDown(1000).siblings()
							.hide();

					if (index == 1) {
						var searched = $('#searched').val();
						if (searched != 'yes') {
							rechargeAjax(1);
							pagingAjax();
						}

					}

				});

		$(".btn116").click(function() {

			var file = $("#txtFileName").val();
			var name = $("#name").val();
			var typeId = $('#typeId').val();
			var content = $('#content').val();
			if (file == null || file == "") {
				$(".fileTip").html("请上传资料").css("color", "red");
				return;
			} else if (name == null || name == "") {
				$(".fileTip").html("请填写资料名称").css("color", "red");
				return;
			} else if (content == null || content == "") {
				$(".fileTip").html("请填写内容信息").css("color", "red");
				return;
			} else {
				$(".fileTip").html("");
				$.ajax({
					url : "/user/addAttestation",
					type : "post",
					data : {
						"name" : name,
						"typeId" : typeId,
						"content" : content
					},
					dataType : "json",
					success : function(data) {
						rechargeAjax(1);
						pagingAjax();
						$("#txtFileName").val('');
					}
				});

			}

		});
		$('#searched').val('');
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
							<h2>个人信息</h2>
						</div>

						<div class="infobar">
							<ul>
								<li class="grayBg infoBg">个人资料</li>
								<li class="grayBg">相关资料上传</li>
							</ul>
						</div>


						<div class="ziliao clear">

							<div class="persnoalInfo" style="display: block;">

								<div class="per-left fl">

									<dl class="avantarPic">
										<dt>
											<c:choose>
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
										</dt>
										<dd>
											<a
												href="${pageContext.request.contextPath }/user/headPicPage">修改头像</a>
										</dd>

									</dl>
								</div>

								<div class="per-right fr">

									<form action="/user/saveInfo" name="per-Info" id="per-Info"
										method="post">

										<table width="558" cellpadding="0" cellspacing="0">
											<tr>
												<td width="80" height="48" class="txtRight">用户名：</td>
												<td width="478">${loginedSession.userName }</td>
											</tr>
											<tr>
												<td width="80" height="48" class="txtRight"><font></font>真实姓名：</td>
												<td width="478">

													<div class="realname fl">${loginedSession.realName }</div>
													<c:choose>
														<c:when test="${loginedSession.realStatus =='1' }">
															<div class="phoneChecked tipicon fr">
																<a href="javascript:;">已认证</a>
															</div>
														</c:when>
														<c:otherwise>
															<div class="gocheck tipicon fr">
																<a
																	href="${pageContext.request.contextPath }/user/realName"
																	target="_blank">去认证</a>
															</div>
														</c:otherwise>
													</c:choose>


												</td>
											</tr>
											<tr>
												<td width="80" height="48" class="txtRight"><font></font>身份证号：
												</td>
												<td width="478">
													<div class="realname fl">${loginedSession.cardId }</div> <c:choose>
														<c:when test="${loginedSession.realStatus =='1' }">
															<div class="phoneChecked tipicon fr">
																<a href="javascript:;">已认证</a>
															</div>
														</c:when>
														<c:otherwise>
															<div class="gocheck tipicon fr">
																<a
																	href="${pageContext.request.contextPath }/user/realName"
																	target="_blank">去认证</a>
															</div>
														</c:otherwise>
													</c:choose>

												</td>
											</tr>
											<tr>
												<td width="80" height="48" class="txtRight"><font></font>手机号码：

												</td>
												<td width="478">
													<div class="realname fl">${mobile }</div>
													<div class="phoneChecked tipicon fr">
														<a href="javascript:;">已认证</a>
													</div>

												</td>
											</tr>
											<tr>
												<td width="80" height="48" class="txtRight"><font></font>邮箱地址：

												</td>
												<td width="478">

													<div class="realname fl">${loginedSession.email }</div> <c:choose>
														<c:when test="${emailState =='1' }">
															<div class="phoneChecked tipicon fr">
																<a href="javascript:;">已验证</a>
															</div>
														</c:when>
														<c:otherwise>
															<div class="gocheck tipicon fr">
																<a
																	href="${pageContext.request.contextPath }/user/checkEmail"
																	target="_blank">去验证</a>
															</div>
														</c:otherwise>
													</c:choose>
												</td>
											</tr>
											<tr>
												<td width="80" height="48" class="txtRight"><font></font>性别：
												</td>
												<td width="478"><label for="male"></label><input
													type="radio" name="sex" id="male" value="1"
													<c:if test="${loginedSession.sex == '1' }"> checked="checked"  </c:if> />男
													<label for="female"></label><input type="radio" name="sex"
													value="0" id="female"
													<c:if test="${loginedSession.sex == '0' }"> checked="checked"  </c:if> />女</td>
											<tr>
												<td width="80" height="48" class="txtRight"><font></font>出生日期：

												</td>
												<td width="478"><input type="text" class="year"
													value="${year }" readonly="readonly"> 年 <input
													type="text" class="month" value="${month }"
													readonly="readonly"> 月 <input type="text"
													class="day" value="${day }" readonly="readonly"> 日</td>
											</tr>
											<tr>
												<td width="80" height="48" class="txtRight"><font></font>最高学历：

												</td>
												<td width="478"><select name="education" class="xueli"
													data-placeholder="请选择">
														<c:forEach items="${educations}" var="education">
															<option value="${education.id }"
																<c:if test="${education.id eq info.education }">selected="selected"</c:if>>${education.name }</option>
														</c:forEach>

												</select></td>
											</tr>
											<tr>
												<td width="80" height="48" class="txtRight"><font></font>毕业院校：
												</td>
												<td width="478"><input type="text" class="school"
													name="educationSchool" value="${info.educationSchool }" /></td>
											</tr>
											<tr>
												<td width="80" height="48" class="txtRight"><font></font>婚姻状况：
												</td>
												<td width="478"><c:forEach items="${marriages}"
														var="marriage">
														<input type="radio" name="marry" class="unmarried"
															value="${marriage.id }"
															<c:if test="${marriage.id eq info.marry }">checked="checked"</c:if> />${marriage.name }
														</c:forEach></td>
											</tr>
											<tr>
												<td width="80" height="48" class="txtRight"><font></font>居住地址：
												</td>
												<td width="478"><input type="text" class="live-address"
													name="address" value="${loginedSession.address }" /></td>
											</tr>
											<tr>
												<td width="80" height="48" class="txtRight"><font></font>公司行业：
												</td>
												<td width="478"><select class="company-industry"
													name="companyIndustry" style="width: 200px;"
													data-placeholder="请选择">

														<c:forEach items="${industries}" var="industry">
															<option value="${industry.id }"
																<c:if test="${industry.id eq info.companyIndustry }">selected="selected"</c:if>>${industry.name }</option>
														</c:forEach>


												</select></td>
											</tr>
											<tr>
												<td width="80" height="48" class="txtRight"><font></font>公司性质：
												</td>
												<td width="478"><select name="companyType"
													class="company-scale" data-placeholder="请选择">

														<c:forEach items="${comTypes}" var="type">
															<option value="${type.id }"
																<c:if test="${type.id eq info.companyType }">selected="selected"</c:if>>${type.name }</option>
														</c:forEach>


												</select></td>
											</tr>
											<tr>
												<td width="80" height="48" class="txtRight"><font></font>职位：
												</td>
												<td width="478"><select id="zhiwei"
													name="companyOffice" data-placeholder="请选择">

														<c:forEach items="${companyOffices}" var="companyOffice">
															<option value="${companyOffice.id }"
																<c:if test="${companyOffice.id eq info.companyOffice }">selected="selected"</c:if>>${companyOffice.name }</option>
														</c:forEach>

												</select></td>
											</tr>
											<tr>
												<td width="80" height="48" class="txtRight"><font></font>月收入：
												</td>
												<td width="478"><select name="income" class="income"
													data-placeholder="请选择">

														<c:forEach items="${incomes}" var="income">
															<option value="${income.id }"
																<c:if test="${income.id eq info.income }">selected="selected"</c:if>>${income.name }</option>
														</c:forEach>


												</select></td>
											</tr>
											<tr>
												<td width="80" height="48" class="txtRight">&nbsp;</td>
												<td width="478"><input type="submit" class="btn116"
													value="保存" /></td>
											</tr>
										</table>



									</form>
								</div>


							</div>

							<div class="persnoalInfo">
								<form id="attestationUpload" name="uploadForm" method="post"
									enctype="multipart/form-data">
									<table width="780" cellpadding="0" cellspacing="0"
										class="upload">
										<tr>
											<td width="130" height="48" class="txtRight">资料上传：</td>
											<td width="650"><input type="text" id="txtFileName"
												readonly="readonly" />
												<div id="btnUp" style="width: 60px; display: inline-block;"
													class="btnsubmit">浏览</div> <!-- <div id="imglist"></div> -->

											</td>
										</tr>

										<tr>
											<td width="130" height="20" class="txtRight"></td>
											<td width="650"><span class="uploadExplain fl">上传最大的图片为10M，格式为jpg、gif、bmp、png</span>

											</td>
										</tr>
										<tr>
											<td width="130" height="48" class="txtRight">上传类型：</td>
											<td width="650"><select id="typeId" class="uploadType"
												data-placeholder="请选择">

													<c:forEach items="${types}" var="type">
														<option value="${type.typeId }">${type.name }</option>
													</c:forEach>
											</select></td>
										</tr>
										<tr>
											<td width="130" height="48" class="txtRight">资料名称：</td>
											<td width="650"><input type="text" class="beizhu"
												id="name" /></td>
										</tr>

										<tr>
											<td width="130" height="48" class="txtRight">内容信息：</td>
											<td width="650"><input type="text" class="beizhu"
												id="content" /></td>
										</tr>



										<tr>
											<td width="130" height="20" class="txtRight"></td>
											<td width="650"><span class="fileTip"></span></td>
										</tr>

										<tr>
											<td width="130" height="48" class="txtRight">&nbsp;</td>
											<td width="650"><a class="btn116">提交</a></td>
										</tr>
									</table>

								</form>


								<table width="780" cellpadding="0" cellspacing="0"
									class="upItemsForm">
									<tr>
										<td width="153" height="50" class="tdBg">资料名称</td>
										<td width="107" class="tdBg">资料类型</td>
										<td width="100" class="tdBg">上传时间</td>
										<td width="100" class="tdBg">审核时间</td>
										<td width="108" class="tdBg">审核说明</td>
										<td width="72" class="tdBg">积分</td>
										<td width="62" class="tdBg">状态</td>
										<td width="78" class="tdBg">操作</td>
									</tr>
									<tr>
										<td width="153" height="50">&nbsp;</td>
										<td width="107">&nbsp;</td>
										<td width="100">&nbsp;</td>
										<td width="100">&nbsp;</td>
										<td width="108">&nbsp;</td>
										<td width="72">&nbsp;</td>
										<td width="62">&nbsp;</td>
										<td width="78">&nbsp;</td>
									</tr>

								</table>
								<input type="hidden" id="searched" value=""> <input
									type="hidden" id="pagCount" value="1">
								<div id="paging" style="display: none;"></div>
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


