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
	href="${pageContext.request.contextPath}/css/investAuto.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/chosen.jquery.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/user/investAuto.js"
	type="text/javascript"></script>
<!-- 弹出层JS -->
<script
	src="${pageContext.request.contextPath}/js/artDialog/artDialog.js?skin=default"></script>
<style type="text/css">
.backcolor {
	background: none repeat scroll 0 0 #0065B4;
}
</style>
</head>
<body>
	<!--头部--->
	<jsp:include page="${pageContext.request.contextPath}/top.jsp"></jsp:include>
	<!--中间-->
	<div class="content">
		<div class="myAccount">
			<!-- 左边通用页面 -->
			<jsp:include
				page="${pageContext.request.contextPath}/user/accountLeft.jsp"></jsp:include>
			<div class="rechargeTakeCash fr">
				<div class="rT box-shadow">
					<div class="rTWrap">
						<div class="txTop">
							<b class="midLine"></b>
							<h2>自动投标</h2>
						</div>
						<div class="investAuto">
							<span class="status">自动投标状态：
							<c:choose>
								<c:when test="${(auto.status==false||auto.status==null)}">
									<em class="off">关闭</em>
								</c:when>
								<c:otherwise>
									<em class="on">开启</em>
								</c:otherwise>
							</c:choose>
							</span>
							<form id="autoForm">
								<div class="autoForm">
									<table width="650" cellpadding="0" cellspacing="0">
<!-- 									  <tr>
											<td width="102" height="60"><span class="colname"><b>
														自动投标状态：</b></span></td>
											<td width="548">&nbsp; <select name="status"
												id="open-status" data-placeholder="开启">
													<option value="1">开启</option>
													<option value="0">关闭</option>
											</select>
											</td>
										</tr> -->
						             <tr>
											<td width="102" height="60"><span class="colname">
														自动投标状态:&nbsp;</span></td>
											<td width="548">
											<div class="_switchDiv">
											<c:choose>
												<c:when test="${empty auto}">
											&nbsp;&nbsp;&nbsp;<label for="investOpen"><input type="radio"  name="status" value="1"/>开启</label>
											&nbsp;&nbsp;&nbsp;<label for="investClose"><input type="radio" name="status" value="0" checked="true"/>关闭</label>
												</c:when>
												<c:when test="${auto.status==false}">
											&nbsp;&nbsp;&nbsp;<label for="investOpen"><input type="radio"  name="status" value="1"/>开启</label>
											&nbsp;&nbsp;&nbsp;<label for="investClose"><input type="radio" name="status" value="0" checked="true"/>关闭</label>
												</c:when>
												<c:when test="${auto.status==true}">
											&nbsp;&nbsp;&nbsp;<label for="investOpen"><input type="radio"  name="status" value="1" checked="true"/>开启</label>
											&nbsp;&nbsp;&nbsp;<label for="investClose"><input type="radio" name="status" value="0"/>关闭</label>
												</c:when>
												<c:otherwise>
											&nbsp;&nbsp;&nbsp;<label for="investOpen"><input type="radio"  name="status" value="1"/>开启</label>
											&nbsp;&nbsp;&nbsp;<label for="investClose"><input type="radio" name="status" value="0"/>关闭</label>
												</c:otherwise>
											</c:choose>											
											 </div>
											</td>
									 </tr> 						
										<tr>
											<td width="102" height="60" class="txtRight"><span
												class="colname">投标金额上限：</span></td>
											<td width="548">
												<div class="investNum">

													&nbsp;<input type="text" id="investNum"
														name="tenderAccount"
														onkeyup="this.value=this.value.replace(/\D/g,'')"
														value="${auto.tenderAccount}" maxlength="6" /> 元（单笔50元起投）

													<span style="color: #ff5555;" id="tenderAccount"></span>
												</div>

											</td>
										</tr>
										<tr>
											<td width="102" height="60" class="txtRight"><span
												class="colname">借款期限：</span></td>
											<td width="548" height="60">
												<div class="biaodi">
													&nbsp; <select name="timelimitMonthFirst" id="select3"
														data-placeholder="${(auto.timelimitMonthFirst==null||auto.timelimitMonthFirst==0)?1:auto.timelimitMonthFirst}">
														<option
															value="${(auto.timelimitMonthFirst==null||auto.timelimitMonthFirst==0)?1:auto.timelimitMonthFirst}">
														</option>
														<option value="1">1</option>
														<option value="2">2</option>
														<option value="3">3</option>
														<option value="4">4</option>
														<option value="5">5</option>
														<option value="6">6</option>
														<option value="7">7</option>
														<option value="8">8</option>
														<option value="9">9</option>
														<option value="10">10</option>
														<option value="11">11</option>
														<option value="12">12</option>
														<option value="13">13</option>
														<option value="14">14</option>
														<option value="15">15</option>
														<option value="16">16</option>
														<option value="17">17</option>
														<option value="18">18</option>
														<option value="19">19</option>
														<option value="20">20</option>
														<option value="21">21</option>
														<option value="22">22</option>
														<option value="23">23</option>
														<option value="24">24</option>
													</select> - <select name="timelimitMonthLast" id="select4"
														data-placeholder="${(auto.timelimitMonthLast==null||auto.timelimitMonthLast==0)?24:auto.timelimitMonthLast}">
														<option
															value="${(auto.timelimitMonthLast==null||auto.timelimitMonthLast==0)?24:auto.timelimitMonthLast}">
														</option>
														<option value="1">1</option>
														<option value="2">2</option>
														<option value="3">3</option>
														<option value="4">4</option>
														<option value="5">5</option>
														<option value="6">6</option>
														<option value="7">7</option>
														<option value="8">8</option>
														<option value="9">9</option>
														<option value="10">10</option>
														<option value="11">11</option>
														<option value="12">12</option>
														<option value="13">13</option>
														<option value="14">14</option>
														<option value="15">15</option>
														<option value="16">16</option>
														<option value="17">17</option>
														<option value="18">18</option>
														<option value="19">19</option>
														<option value="20">20</option>
														<option value="21">21</option>
														<option value="22">22</option>
														<option value="23">23</option>
													</select> 月 <span style="color: #ff5555;" id="time_limit"></span>
												</div>

											</td>
										</tr>
										<tr>
											<td width="102" height="60"><span class="colname">年利率：</span>
											</td>
											<td width="548">
												<div class="nianRate">
													&nbsp; <select name="aprFirst" id="select5"
														data-placeholder="${(auto.aprFirst==null||auto.aprFirst==0)?'1':auto.aprFirst}">
														<option
															value="${(auto.aprFirst==null||auto.aprFirst==0)?1:auto.aprFirst}">
														</option>
														<option value="1">1</option>
														<option value="2">2</option>
														<option value="3">3</option>
														<option value="4">4</option>
														<option value="5">5</option>
														<option value="6">6</option>
														<option value="7">7</option>
														<option value="8">8</option>
														<option value="9">9</option>
														<option value="10">10</option>
														<option value="11">11</option>
														<option value="12">12</option>
														<option value="13">13</option>
														<option value="14">14</option>
														<option value="15">15</option>
														<option value="16">16</option>
														<option value="17">17</option>
														<option value="18">18</option>
														<option value="19">19</option>
														<option value="20">20</option>
														<option value="21">21</option>
														<option value="22">22</option>
														<option value="23">23</option>
														<option value="24">24</option>
													</select> - <select name="aprLast" id="select6"
														data-placeholder="${(auto.aprLast==null||auto.aprLast==0)?24:auto.aprLast}">
														<option
															value="${(auto.aprLast==null||auto.aprLast==0)?24:auto.aprLast}"></option>
														<option value="1">1</option>
														<option value="2">2</option>
														<option value="3">3</option>
														<option value="4">4</option>
														<option value="5">5</option>
														<option value="6">6</option>
														<option value="7">7</option>
														<option value="8">8</option>
														<option value="9">9</option>
														<option value="10">10</option>
														<option value="11">11</option>
														<option value="12">12</option>
														<option value="13">13</option>
														<option value="14">14</option>
														<option value="15">15</option>
														<option value="16">16</option>
														<option value="17">17</option>
														<option value="18">18</option>
														<option value="19">19</option>
														<option value="20">20</option>
														<option value="21">21</option>
														<option value="22">22</option>
														<option value="23">23</option>
														<option value="24">24</option>
													</select> % <span style="color: #ff5555;" id="apr"></span>

												</div>
											</td>
										</tr>
										<tr>
											<td width="102" height="60"><span class="colname">还款方式：</span>
											</td>
											<td width="548">&nbsp; <select name="borrowStyle"
												id="select7"
												data-placeholder="${(auto.borrowStyleStatus==null||auto.borrowStyleStatus==0)?'不限':
													(auto.borrowStyle==0?'等额本息':(auto.borrowStyle==2?'到期还款':(auto.borrowStyle==1?'等本等息':'先息后本')))}">

													<option
														value="${(auto.borrowStyleStatus==null||auto.borrowStyleStatus==0)?-1:
													(auto.borrowStyle==0?0:(auto.borrowStyle==2?2:(auto.borrowStyle==3?3:1)))}"></option>
													<option value="-1">不限</option>
													<option value="0">等额本息</option>
													<option value="2">到期还款</option>
													<option value="3">先息后本</option>
													<option value="1">等本等息</option>
											</select> <span style="color: #ff5555;" id="style"></span>

											</td>
										</tr>



										<tr>
											<td width="102" height="60"><span class="colname"></span>
											</td>
											<td width="548">
												<div class="On_Off">
													<a class="onBtn" id="sub">保 存</a>
												</div>
											</td>
										</tr>
									</table>
								</div>
							</form>
						</div>


						<div class="warmTip">
							<h2>温馨提示</h2>
							<div class="tips">
								<p>1、自动投标设置为开启状态，用户余额高于50元将开始投标。</p>
								<p>2、当用户余额不足50元自动失效，若余额再次高于50元规则同第1条。</p>
								<p>3、新借款标的进入招标中后，立即启动自动投标；投标序列按照开启自动投标的时间先后进行排序。</p>
								<p>4、每个用户每个标仅自动投标一次；轮到用户投标时没有符合用户条件的标，也视为投标一次。</p>
								<p>5、用户自动投标设置，因设置错误造成的投标失误，将无法撤回。</p>
								<p>6、解释权归微积金所有。</p>

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
