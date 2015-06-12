<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="date" uri="http://cn.vfunding.vfunding.prd.www/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="description"
	content="微积金(www.vfunding.cn) 互联网金融服务平台旨在为有资金需求和理财需求的个人或企业搭建一个真实诚信、安全透明、快捷高效的互联网服务平台和投资理财渠道。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、担保贷款等。" />

<meta name="keywords"
	content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|工薪贷|生意贷|基金|微基金|小额贷款|投融资|宜车贷|二手车抵押|押车|担保" />

<title>${borrow.name }_微积金</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/tip.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/img.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/borrowDetail.css" />

<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script
	src="${pageContext.request.contextPath}/js/borrow/borrowDetail.js"></script>
<!-- 弹出层JS -->
<script
	src="${pageContext.request.contextPath}/js/artDialog/artDialog.js?skin=default"></script>
<script
	src="${pageContext.request.contextPath}/js/artDialog/plugins/iframeTools.js"></script>
</head>
<body>
	<!---头部--->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>
	<!--中间-->
	<div class="bread">
		<a href="${pageContext.request.contextPath}">首页></a> <a
			href="${pageContext.request.contextPath}/borrow/borrowList">借款项目></a>
		<a href="${pageContext.request.contextPath}/borrow/borrowList">汽车抵押贷款</a>
	</div>
	<div class="content">
		<div class="borWrap clear">
			<div class="leftArea fl box-shadow">
				<div class="borrowTitle">
					<h3>
						${borrow.name }
						<c:if test="${borrow.biaoType != 'feel'}">
							<c:choose>
								<c:when test="${borrowAll.mortgageTypeid==1 }">
									<a
										href="${pageContext.request.contextPath}/borrow/agreementVfunding/${borrow.id}"
										target="_blank">&nbsp;&nbsp;&nbsp;&nbsp;借款协议书</a>
								</c:when>
								<c:when test="${borrowAll.mortgageTypeid==2 }">
									<a
										href="${pageContext.request.contextPath}/borrow/agreementVfunding/${borrow.id}"
										target="_blank">&nbsp;&nbsp;&nbsp;&nbsp;（用户间）债权转让协议</a>
								</c:when>
							</c:choose>

						</c:if>

						<div class="selected"></div>

					</h3>
				</div>


				<div class="borrowDetail clear">
					<input type="hidden" value="${borrow.id }" id="borrowId">
					<div class="leftTable fl">
						<table width="300" cellpadding="0" cellspacing="0" id="leftTable">
							<tr>
								<td width="100" height="37" class="txtRight">借款金额：</td>
								<td width="200"><div class="borMoney">
										<fmt:formatNumber value="${borrow.account }"
											pattern="#,##0.00#" />
										<span style="font-size: 12px; color: #ff6600;"> 元</span>
									</div></td>
							</tr>
							<tr>
								<td width="100" height="37" class="txtRight">预期收益：</td>
								<td width="200"><span>${borrow.expectApr}%</span></td>
							</tr>
							<tr>
								<td width="100" height="37" class="txtRight">投标进度：</td>
								<td width="200">
									<div class="progressBox">
										<div style="width: ${borrow.accountYes/borrow.account*100}%;"
											class="jdt"></div>
									</div>
								</td>
							</tr>

							<tr>
								<td width="100" height="37" class="txtRight">还差借款：</td>
								<td width="200"><span><fmt:formatNumber
											value="${borrow.account-borrow.accountYes}"
											pattern="#,##0.00#" /> </span><input type="hidden"
									value="${borrow.account-borrow.accountYes}"
									id="overplusAccount"><span style="font-size: 12px;">
										元</span></td>
							</tr>


							<tr>
								<td width="100" height="37" class="txtRight">还款方式：</td>
								<td width="200"><c:if test="${borrow.style ==0 }">等额本息</c:if>
									<c:if test="${borrow.style ==1 }">等本等息</c:if> <c:if
										test="${borrow.style ==2 }">到期还款</c:if> <c:if
										test="${borrow.style ==3 }">先息后本</c:if></td>
							</tr>
							<%-- 	<tr>
								<td width="100" height="37" class="txtRight">投标奖励：</td>
								<td width="200"><c:if test="${borrow.award ==0 }">无</c:if>
									<c:if test="${borrow.award !=0 }">${borrow.partAccount}</c:if></td>
							</tr> --%>
							<tr>
								<td width="100" height="37" class="txtRight">借款期限：</td>
								<td width="200"><c:if test="${borrow.isday==0 }">${borrow.timeLimit}个月</c:if>
									<c:if test="${borrow.isday==1 }">${borrow.timeLimitDay }天</c:if></td>
							</tr>
							<tr>
								<%--<td width="100" height="37" class="txtRight">担保：</td>
								 <td width="200"><c:if
										test="${borrow.vouchUser !=null&& borrow.vouchUser !=''}">
								${borrow.vouchUser}担保</c:if> <c:if
										test="${borrow.vouchUser==null || borrow.vouchUser ==''}">
								无担保</c:if></td>
								<td>本息担保</td> --%>
							</tr>

							<tr>
								<td width="100" height="37" class="txtRight">投标人次：</td>
								<td width="200">${tenderCount}次</td>
							</tr>
							<tr>
								<c:choose>
									<c:when
										test="${borrow.account-borrow.accountYes >0 && (borrowAll.status==1 || borrowAll.status==10)}">
										<td width="100" height="37" class="txtRight">投标截止时间：</td>
										<td width="200">${borrow.tenderEndtime }</td>
									</c:when>
									<c:when
										test="${borrow.account-borrow.accountYes <=0&& (borrowAll.status==1 || borrowAll.status==10)}">
										<td width="100" height="37" class="txtRight">计息时间：</td>
										<td width="200">满标后次日开始计息</td>
									</c:when>
									<c:when
										test="${(borrowAll.status==3 && !borrowAll.borrowIsRepay) || (borrowAll.status==30 && !borrowAll.borrowIsRepay)}">
										<td width="100" height="37" class="txtRight">计息时间：</td>
										<td width="200"><date:date parttern="yyyy-MM-dd"
												value="${borrowAll.successTime+(60*60*24)}" /></td>
									</c:when>
									<c:when
										test="${(borrowAll.status==3 && borrowAll.borrowIsRepay) ||(borrowAll.status==30 && borrowAll.borrowIsRepay) }">
										<td width="100" height="37" class="txtRight">计息时间：</td>
										<td width="200">此标已还款</td>
									</c:when>
								</c:choose>


								<%-- ${borrow.tenderEndtime} --%>
							</tr>

							<tr>
								<td width="100" height="37" class="txtRight">合作监管方：</td>
								<td width="200"><a
									href="${pageContext.request.contextPath}/utilpage/oumeiya"
									target="_blank" class="oumeiya">欧美亚名车行</a></td>
							</tr>
						</table>
					</div>
					<c:if test="${not  empty loginedSession}">
						<div class="rightTable fr">
							<form action="#" method="post" id="tenderForm">
								<table width="340" cellpadding="0" cellspacing="0"
									id="rightTable">
									<c:choose>
										<c:when
											test="${borrow.account-borrow.accountYes >0 && (borrowAll.status==1 || borrowAll.status==10)}">
											<c:if test="${borrow.biaoType=='huodong' }">
												<c:if test="${borrowRule!=null && borrowRule.mostAccount!='0.00' && borrowRule.mostTenderCount!='0' }">
													<tr>
														<td class="hd-tips" height="20">限投金额${borrowRule.mostAccount }元，单标限投${borrowRule.mostTenderCount }次</td>
													</tr>
												</c:if>
											</c:if>

											<tr>
												<td height="30">投标金额（${borrow.lowestAccount}元起投<input
													type="hidden" id="lowestAccount"
													value="${borrow.lowestAccount}">）：<span
													class="tenderMoneyWrong"></span></td>
											</tr>
											<tr>
												<td height="52" style="position: relative;" id="tender-td"><input
													type="text" class="tenderMoney" id="tenderMoney"
													tabindex="1" /> 元</td>
											</tr>
											<tr>
												<td height="30"><c:choose>
														<c:when test="${borrowAll.status==10 }">
												您的体验金可用余额：${accountFeel.useMoney }
												<input type="hidden" id="useMoney"
																value="${accountFeel.useMoney }">
														</c:when>
														<c:otherwise>
															<input type="hidden" id="useMoney"
																value="${account.useMoney}">可用余额：${account.useMoney }元
													<a class="tenderAll" href="javascript:tenderAll()">全部投出</a>&nbsp;<a
																href="${pageContext.request.contextPath }/account/recharge"
																class="goRecharge" target="_blank"> 充值</a>

														</c:otherwise>
													</c:choose></td>
											</tr>
											<tr>
												<td height="20"><input type="checkbox" id="addRate-use" />
													<label for="addRate-use">使用加息卡</label></td>
											</tr>
											<tr>
												<td height="30">支付密码（
												<c:choose>
												<c:when
													test="${loginedSession.payPassword ==  loginedSession.password}">
													<a class="findPassword" href="/user/updatePayPassword" style="color: #00a0e9; ">设置支付密码</a>
												</c:when>
												<c:otherwise>
													<a class="findPasswordTwo" href="/user/findPayPasswordPage" style="color: #00a0e9; ">找回支付密码</a>
												</c:otherwise>
											</c:choose>
												）：<span class="zfPwdWrong"></span></td>
											</tr>
											<tr>
												<td height="30"><input type="password" class="zfPwd"
													tabindex="2" value="" /></td>
											</tr>
											<tr>
												<td height="40"></td>
											</tr>
											<tr>
												<td height="80"><a class="btn216"
													onClick="javascript:verifyInvest()">立即投资</a>
													<p>
														<!-- 投标100元，到期收益¥0.88元 -->
													</p></td>
											</tr>
										</c:when>
										<c:when
											test="${borrowAll.status != 1 && borrowAll.status != 3 && borrowAll.status != 10 && borrowAll.status != 30}">


											<c:if test="${borrow.biaoType=='huodong' }">
												<c:if test="${borrowRule!=null && borrowRule.mostAccount!='0.00' && borrowRule.mostTenderCount!='0' }">
													<tr>
														<td class="hd-tips" height="20">限投金额${borrowRule.mostAccount }元，单标限投${borrowRule.mostTenderCount }次</td>
													</tr>
												</c:if>
											</c:if>

											<tr>
												<td height="30">投标金额：<span class="tenderMoneyWrong"></span></td>
											</tr>
											<tr>
												<td height="52"><input type="text" class="tenderMoney"
													disabled="disabled" id="tenderMoney" tabindex="1" /> 元</td>
											</tr>
											<tr>
												<td height="30">此标不可投，<a
													href="${pageContext.request.contextPath }/borrow/borrowList"
													style="color: #00a0e9;">可选择其它借款标</a></td>
											</tr>
											<tr>
												<td height="10"></td>
											</tr>
											<tr>
												<td height="30">支付密码：</td>
											</tr>
											<tr>
												<td height="30"><input type="password" class="zfPwd"
													tabindex="2" value="" disabled="disabled" /> <span
													class="zfPwdWrong"></span></td>
											</tr>
											<tr>
												<td height="40"></td>
											</tr>
											<tr>
												<td height="80"><a class="btn216-gray"
													onClick="javascript:full()">此标不可投</a>
													<p>
														<!-- 投标100元，到期收益¥0.88元 -->
													</p></td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:if test="${borrow.biaoType=='huodong' }">
												<c:if test="${borrowRule!=null && borrowRule.mostAccount!='0.00' && borrowRule.mostTenderCount!='0' }">
													<tr>
														<td class="hd-tips" height="20">限投金额${borrowRule.mostAccount }元，单标限投${borrowRule.mostTenderCount }次</td>
													</tr>
												</c:if>
											</c:if>
											<tr>
												<td height="40">投标金额：<span class="tenderMoneyWrong"></span></td>
											</tr>
											<tr>
												<td height="52"><input type="text" class="tenderMoney"
													disabled="disabled" id="tenderMoney" tabindex="1" /> 元</td>
											</tr>
											<tr>
												<td height="30">标的已满，<a
													href="${pageContext.request.contextPath }/borrow/borrowList"
													style="color: #00a0e9;">可选择其它借款标</a></td>
											</tr>
											<tr>
												<td height="10"></td>
											</tr>
											<tr>
												<td height="30">支付密码：</td>
											</tr>
											<tr>
												<td height="30"><input type="password" class="zfPwd"
													tabindex="2" value="" disabled="disabled" /> <span
													class="zfPwdWrong"></span></td>
											</tr>
											<tr>
												<td height="40"></td>
											</tr>
											<tr>

												<c:choose>
													<c:when
														test="${borrowAll.status==1 ||borrowAll.status==10}">
														<td height="80"><a class="btn216-gray"
															onClick="javascript:full()">满标待审</a>
															<p>
																<!-- 投标100元，到期收益¥0.88元 -->
															</p></td>
													</c:when>
													<c:when
														test="${(borrowAll.status==3 && !borrowAll.borrowIsRepay) || (borrowAll.status==30 && !borrowAll.borrowIsRepay)}">
														<td height="80"><a class="btn216-gray"
															onClick="javascript:full()">撮合成功</a>
															<p>
																<!-- 投标100元，到期收益¥0.88元 -->
															</p></td>
													</c:when>
													<c:when
														test="${(borrowAll.status==3 && borrowAll.borrowIsRepay) ||(borrowAll.status==30 && borrowAll.borrowIsRepay) }">
														<td height="80"><a class="btn216-gray"
															onClick="javascript:full()">撮合成功</a>
															<p>
																<!-- 投标100元，到期收益¥0.88元 -->
															</p></td>
													</c:when>
												</c:choose>
											</tr>
										</c:otherwise>
									</c:choose>
								</table>
							</form>
						</div>
					</c:if>

					<c:if test="${ empty loginedSession}">
						<%-- <div class="rightTable fr">
							<a href="/goLogin?returnUrl=/borrow/borrowDetail?id=${borrow.id}"
								class="btn216">请点击登录</a>
						</div> --%>
						<div class="rightTable fr">
							<form action="#" method="post" id="tenderForm">
								<table width="340" cellpadding="0" cellspacing="0"
									id="rightTable">
									<c:if test="${borrow.biaoType=='huodong' }">
												<c:if test="${borrowRule!=null && borrowRule.mostAccount!='0.00' && borrowRule.mostTenderCount!='0' }">
													<tr>
														<td class="hd-tips" height="20">限投金额${borrowRule.mostAccount }元，单标限投${borrowRule.mostTenderCount }次</td>
													</tr>
												</c:if>
									</c:if>
									<tr>
										<td height="30">投标金额：<span class="tenderMoneyWrong"></span></td>
									</tr>
									<tr>
										<td height="52"><input type="text" class="tenderMoney"
											id="tenderMoney" tabindex="1" disabled="disabled" /> 元</td>
									</tr>
									<tr>
										<td height="30">您的可用余额 &nbsp;&nbsp;<a
											href="/goLogin?returnUrl=/borrow/borrowDetail/${borrow.id}"
											style="color: #00a0e9;">登录&nbsp;&nbsp;</a>后可见
										</td>
									</tr>
									<tr>
										<td height="10"></td>
									</tr>
									<tr>
										<td height="30">请输入支付密码：</td>
									</tr>
									<tr>
										<td height="30"><input type="password" class="zfPwd"
											tabindex="2" disabled="disabled" value="" /> <span
											class="zfPwdWrong"></span></td>
									</tr>
									<tr>
										<td height="40"></td>
									</tr>
									<tr>
										<td height="80"><a
											href="/goLogin?returnUrl=/borrow/borrowDetail/${borrow.id}"
											class="btn216">去登录</a> <!-- 	<p>投标100元，到期收益¥0.88元111</p>--></td>
									</tr>
								</table>
							</form>
						</div>
					</c:if>
				</div>
			</div>


			<div class="rightArea fr">

				<div class="userAccount box-shadow">
					<div class="userIntro clear">
						<div class="touxiang fl">
							<img src="/images/account/avatarPic.png" />
						</div>
						<div class="simpleInfo fr">
							<ul>
								<li>用户名：${user.username }&nbsp; <img
									src="/images/account/vip.png" /></li>
								<li>积分等级：<img src="/images/account/diamond.png" /><img
									src="/images/account/diamond.png" /><img
									src="/images/account/diamond.png" /></li>
								<li>籍贯：${user.province } ${user.city}</li>
							</ul>
						</div>



					</div>

					<div class="rzBar">
						<a title="实名认证"
							class="rz-icon cardIdentification${user.realStatus} rc1"></a> <a
							title="手机认证"
							class="rz-icon mobileIdentification${user.phoneStatus}  rc2"></a>
						<a title="邮箱认证"
							class="rz-icon emailIdentification${user.emailStatus}   rc3"></a>
						<a title="视频认证"
							class="rz-icon videoIdentification${user.videoStatus}  rc4"></a>
						<a title="实地认证"
							class="rz-icon realplaceIdentification${user.sceneStatus}  rc5"></a>
						<a title="资料认证"
							class="rz-icon ziliaoIdentification${user.sceneStatus} rc6"></a>
					</div>

				</div>
				<div class="smallBanner box-shadow">
					<c:choose>
						<%-- 卓文汽车 --%>
						<c:when test="${user.username == '卓文汽车A'}">
							<img src="/images/detail/zhuowen.gif" width="300" height="270" />
						</c:when>
						<%-- vf1010 --%>
						<c:when test="${user.username == 'vf1010'}">
							<img src="/images/detail/vf1010.gif" width="300" height="270" />
						</c:when>
						<%-- 押车宝 --%>
						<c:when test="${user.username == 'ycb1'}">
							<img src="/images/detail/ycb1.gif" width="300" height="270" />
						</c:when>
						<%-- w --%>
						<c:when test="${user.username == 'wxianrong'}">
							<img src="/images/detail/w.gif" width="300" height="270" />
						</c:when>
						<%-- 财富助手 --%>
						<c:when test="${user.username == '财富助手'}">
							<img src="/images/detail/rich.gif" width="300" height="270" />
						</c:when>
						<%-- 默认 --%>
						<c:otherwise>
							<img src="/images/detail/vf1010.gif" width="300" height="270" />
						</c:otherwise>
					</c:choose>
				</div>

			</div>
		</div>


		<c:choose>
			<c:when test="${borrowAll.status<10 }">
				<div class="desc  box-shadow">
					<div class="bornNav">
						<ul>
							<a class="licolor cur">车辆详情</a>
							<a class="licolor">资料审核</a>
							<a class="licolor">投标记录</a>
							<a class="licolor">借款人详细信息</a>
							<a class="licolor">还款信用</a>

							<a class="licolor">待还款记录</a>
							<!-- <a href="#tenderRewrad" class="licolor">投标奖励</a> -->
						</ul>
					</div>


					<div class="detailItem" id="detailItem1">
						<div class="carTable">
							<table width="970" cellpadding="0" cellspacing="0" id="carTable">
								<tr>
									<td width="242" height="48" class="tdBg top-border">车辆品牌</td>
									<td width="243" class="tdBg  top-border">购买价格</td>
									<td width="243" class="tdBg top-border">抵押估价</td>
									<td width="242" class="tdBg  top-border">审核时间</td>
								</tr>
								<tr>
									<td width="242" height="48"><c:choose>
											<c:when test="${ not empty mortgageCar.brand }">
										${mortgageCar.brand }
											</c:when>
											<c:otherwise>--</c:otherwise>
										</c:choose></td>
									<td width="243"><c:choose>
											<c:when test="${ not empty mortgageCar.buyMoney }">
												<fmt:formatNumber value="${mortgageCar.buyMoney}"
													pattern="#,##0.00#" />
											</c:when>
											<c:otherwise>--</c:otherwise>
										</c:choose></td>
									<td width="243"><c:choose>
											<c:when test="${ not empty mortgageCar.assessMoney }">
												<fmt:formatNumber value="${mortgageCar.assessMoney}"
													pattern="#,##0.00#" />
											</c:when>
											<c:otherwise>--</c:otherwise>
										</c:choose></td>
									<td width="242"><c:choose>
											<c:when test="${ not empty borrowAll.verifyTimeStr }">${borrowAll.verifyTimeStr}</c:when>
											<c:otherwise>--</c:otherwise>
										</c:choose></td>
								</tr>
							</table>
						</div>


						<div id="imageShow">
							<div id="imgshow_mask"></div>
							<c:choose>
								<c:when test="${not empty oldPic && oldPic==true }">
						${borrowAll.content }
						</c:when>
								<c:otherwise>
									<ul class="imagebg" id="imagebg">
										<c:forEach items="${listPic}" var="v">
											<li data-sPic="${v.pic}"><a class="bannerbg_main"
												style="background: url(${v.pic}) 50% 0 no-repeat; width:970px;height:642px;background-size:auto 100%;"></a></li>
										</c:forEach>
									</ul>

									<div class="scrollbg">
										<div class="scroll">
											<a id="left_img_btn" class="s_pre" href="javascript:void(0)"></a>
											<div class="current" id="current"></div>
											<div class="outScroll_pic">
												<ul class="scroll_pic cls" id="small_pic"></ul>
											</div>
											<a id="right_img_btn" class="s_next"
												href="javascript:void(0)"></a>
										</div>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
						<script src="${pageContext.request.contextPath}/js/imgSlider.js"></script>
						<script type="text/javascript">
							img.init();
							img.play(0);
							//阻止事件冒泡
							function estop(e) {
								var e = arguments.callee.caller.arguments[0]
										|| event;
								if (e && e.stopPropagation) {
									//因此它支持W3C的stopPropagation()方法
									e.stopPropagation();
								} else {
									//否则，我们需要使用IE的方式来取消事件冒泡
									window.event.cancelBubble = true;
									return false;
								}
							}
						</script>
					</div>


					<div class="detailItem" id="dataAudit">
						<div class="dataAudit">
							<table width="970" cellpadding="0" cellspacing="0">
								<tr>
									<td width="406" height="48" class="tdBg"><div
											class="borrow-style top-border">资料类型</div></td>
									<td width="276" class="tdBg top-border">审核说明</td>
									<td width="288" class="tdBg top-border">审核时间</td>
								</tr>


								<tr>
									<td width="406" height="48"><div class="borrow-style">借款人身份证明</div></td>
									<td width="276"><div class="pass tipicon"></div></td>
									<td width="288">${borrowAll.verifyTimeStr}</td>
								</tr>
								<tr>
									<td width="406" height="48"><div class="borrow-style">借款人资产证明</div></td>
									<td width="276"><div class="pass tipicon"></div></td>
									<td width="288">${borrowAll.verifyTimeStr}</td>
								</tr>
								<tr>
									<td width="406" height="48"><div class="borrow-style">机动车登记证</div></td>
									<td width="276"><div class="pass tipicon"></div></td>
									<td width="288">${borrowAll.verifyTimeStr}</td>
								</tr>
								<tr>
									<td width="406" height="48"><div class="borrow-style">机动车行驶证</div></td>
									<td width="276"><div class="pass tipicon"></div></td>
									<td width="288">${borrowAll.verifyTimeStr}</td>
								</tr>
								<tr>
									<td width="406" height="48"><div class="borrow-style">车主身份证</div></td>
									<td width="276"><div class="pass tipicon"></div></td>
									<td width="288">${borrowAll.verifyTimeStr}</td>
								</tr>
								<tr>
									<td width="406" height="48"><div class="borrow-style">车辆保险单</div></td>
									<td width="276"><div class="pass tipicon"></div></td>
									<td width="288">${borrowAll.verifyTimeStr}</td>
								</tr>
								<tr>
									<td width="406" height="48"><div class="borrow-style">车辆购置完税证明</div></td>
									<td width="276"><div class="pass tipicon"></div></td>
									<td width="288">${borrowAll.verifyTimeStr}</td>
								</tr>
								<tr>
									<td width="406" height="48"><div class="borrow-style">车辆抵押合同及借条</div></td>
									<td width="276"><div class="pass tipicon"></div></td>
									<td width="288">${borrowAll.verifyTimeStr}</td>
								</tr>
							</table>
						</div>

					</div>


					<div class="detailItem" id="tenderRecord">
						<div class="tenderRecord">
							<table width="970" cellpadding="0" cellspacing="0">
								<tr>
									<td width="138" height="48" class="tdBg top-border">投标人</td>
									<td width="183" class="tdBg top-border">投标金额</td>
									<td width="217" class="tdBg top-border">有效金额</td>
									<td width="143" class="tdBg top-border">投资奖励</td>
									<td width="156" class="tdBg top-border">投标时间</td>
									<td width="133" class="tdBg top-border">状态</td>
								</tr>

								<c:if test="${empty tenderList}">
									<tr>
										<td colspan="6" height="48">暂无投资记录</td>
									</tr>
								</c:if>
								<c:forEach var="tenderList" items="${tenderList }">
									<tr>
										<td width="138" height="48">${tenderList.username }</td>
										<td width="183"><fmt:formatNumber
												value="${tenderList.money }" pattern="#,##0.00#" /></td>
										<td width="217"><fmt:formatNumber
												value="${tenderList.account }" pattern="#,##0.00#" /></td>
										<td width="143">0</td>
										<td width="156">${tenderList.tendertime }</td>
										<td width="133"><c:choose>
												<c:when
													test="${tenderList.status ==5 || tenderList.status ==50}">
									投资待审
									</c:when>
												<c:when
													test="${(tenderList.status ==1 || tenderList.status ==10) && tenderList.money-tenderList.account==0 }">
									投资成功
									</c:when>
												<c:when
													test="${ (tenderList.status ==1 || tenderList.status ==10)&& tenderList.money-tenderList.account>0 }">
									部分投资
									</c:when>
												<c:when test="${tenderList.status ==20}">
									投资失败
									</c:when>
											</c:choose>
									</tr>
								</c:forEach>

							</table>

						</div>

					</div>


					<div class="detailItem" id="borrower">
						<div class="borrower">
							<table width="970" cellpadding="0" cellspacing="0">
								<tr>
									<td width="138" height="48" class="tdBg top-border">性别</td>
									<td width="183" class="tdBg top-border">出生年月</td>
									<td width="217" class="tdBg top-border">婚姻状况</td>
									<td width="136" class="tdBg top-border">学历</td>
									<td width="172" class="tdBg top-border">住房条件</td>
									<td width="124" class="tdBg top-border">有无购车</td>
								</tr>
								<tr>
									<td width="138" height="48"><c:choose>
											<c:when test="${user.sex==1 }">男</c:when>
											<c:otherwise>女</c:otherwise>
										</c:choose></td>
									<td width="183"><c:choose>
											<c:when test="${ not empty user.birthday }">${user.birthday}</c:when>
											<c:otherwise>--</c:otherwise>
										</c:choose></td>
									<td width="217"><c:choose>
											<c:when test="${userInfo.marry==1 }">已婚</c:when>
											<c:otherwise>未婚</c:otherwise>
										</c:choose></td>
									<td width="136"><c:choose>
											<c:when test="${ not empty userInfo.education  }">${userInfo.education }</c:when>
											<c:otherwise>--</c:otherwise>
										</c:choose></td>
									<td width="172"><c:choose>
											<c:when test="${ not empty userInfo.housing  }">${userInfo.housing }</c:when>
											<c:otherwise>--</c:otherwise>
										</c:choose></td>
									<td width="124"><c:choose>
											<c:when test="${ not empty userInfo.car  }">${userInfo.car }</c:when>
											<c:otherwise>--</c:otherwise>
										</c:choose></td>
								</tr>
								<tr>
									<td width="138" height="48" class="tdBg">工作城市</td>
									<td width="183" class="tdBg">公司性质</td>
									<td width="217" class="tdBg">公司名称</td>
									<td width="136" class="tdBg">职位</td>
									<td width="172" class="tdBg">月收入</td>
									<td width="124" class="tdBg"></td>
								</tr>
								<tr>
									<td width="138" height="48"><c:choose>
											<c:when test="${ not empty userInfo.companyAddress  }">${userInfo.companyAddress }</c:when>
											<c:otherwise>--</c:otherwise>
										</c:choose>
									<td width="183"><c:choose>
											<c:when test="${ not empty userInfo.companyType  }">${userInfo.companyType }</c:when>
											<c:otherwise>--</c:otherwise>
										</c:choose></td>
									<td width="217"><c:choose>
											<c:when test="${ not empty userInfo.companyName  }">${userInfo.companyName }</c:when>
											<c:otherwise>--</c:otherwise>
										</c:choose></td>
									<td width="136"><c:choose>
											<c:when test="${ not empty userInfo.companyOffice  }">${userInfo.companyOffice }</c:when>
											<c:otherwise>--</c:otherwise>
										</c:choose></td>
									<td width="172"><c:choose>
											<c:when test="${ not empty userInfo.income  }">${userInfo.income }</c:when>
											<c:otherwise>--</c:otherwise>
										</c:choose></td>
									<td width="124">&nbsp;</td>
								</tr>
							</table>
						</div>

					</div>

					<div class="detailItem" id="repayCredit">
						<div class="repayCredit">

							<table width="970" cellpadding="0" cellspacing="0">
								<tr>
									<td width="100" height="48" class="tdBg top-border">成功借款</td>
									<td width="100" class="tdBg top-border">流标</td>
									<td width="100" class="tdBg top-border">待还款</td>
									<td width="100" class="tdBg top-border">成功还款</td>
									<td width="100" class="tdBg top-border">提前还款</td>
									<td width="135" class="tdBg top-border"><30天 逾期还款</td>
									<td width="135" class="tdBg top-border">>30天 逾期还款</td>
									<td width="100" class="tdBg top-border">逾期未还款</td>
								</tr>

								<tr>
									<td width="100" height="48">${repaymentSituation.successBorrow }</td>
									<td width="100">${repaymentSituation.loseRayment}</td>
									<td width="100">${repaymentSituation.waitRayment}</td>
									<td width="100">${repaymentSituation.successRayment}</td>
									<td width="100">${repaymentSituation.advanceRayment}</td>
									<td width="135">${repaymentSituation.overdueRaymentLittle}</td>
									<td width="135">${repaymentSituation.overdueRaymentmore}</td>
									<td width="100">${repaymentSituation.overdueRaymentNo}</td>
								</tr>
							</table>

						</div>

					</div>

					<div class="detailItem" id="repayRecord">
						<h2 id="moreRecord">
							<span class="sp1 fl"></span> <span class="sp2 fr">待还款记录(只显示最近待还款的10条记录)
							</span>
						</h2>
						<div class="repayRecord">
							<table width="970" cellpadding="0" cellspacing="0">
								<tr>
									<td width="276" height="48" class="tdBg top-border"><div
											id="txt-left1">借款标题</div></td>
									<td width="124" class="tdBg top-border">期数</td>
									<td width="150" class="tdBg top-border">还款本息</td>
									<td width="158" class="tdBg top-border">预期到期日期</td>
								</tr>
								<c:forEach var="waitRepaymentList" items="${waitRepaymentList }">
									<tr>
										<td width="276" height="48"><div id="txt-left2">${waitRepaymentList.borrowName }</div></td>
										<td width="124">${waitRepaymentList.order+1}/${waitRepaymentList.timeLimit}</td>
										<td width="150"><fmt:formatNumber
												value="${waitRepaymentList.repaymentAccount }"
												pattern="#,##0.00#" /></td>
										<td width="158">${waitRepaymentList.repaymentTimeStr }</td>
									</tr>
								</c:forEach>
								<c:if test="${ empty waitRepaymentList }">
									<tr>
										<td width="538" height="48" colspan="4">无还款记录</td>
									</tr>
								</c:if>
							</table>
						</div>
					</div>
				</div>
			</c:when>

			<c:otherwise>
				<div class="desc  box-shadow">
					<div class="bornNav">
						<ul>
							<a class="licolor cur">借款详情</a>
							<a class="licolor">投标记录</a>
							<!-- <a href="#tenderRewrad" class="licolor">投标奖励</a> -->
						</ul>
					</div>

					<div class="detailItem" id="detailItem1">
						<div class="carTable" style="padding-left: 30px;">
							<p class="pp">尊敬的特邀用户：</p>
							<p class="pp">您好。 欢迎来到微积金。</p>
							<p>体验标是微积金网提供给您体验本网站功能及收益的标种。您在此体验到的功能及收益获得情况与真实标的一致。</p>
							<p>如果您希望获得更多的收益，请进行账户充值及真实标的的投资。</p>
							<p>微积金全体员工祝您体验愉快并随时关注账户资金增值情况，更可以下载手机APP，随时关注。</p>
							<p>如有任何疑问，请与我们联系。</p>
							<p>微积金官方QQ群：315344877</p>
							<p>客服 小薇QQ：2775156774</p>
							<p>客服热线：4009919999</p>

							<h3 style="text-align: center;">
								<b>微积金管理中心</b>
							</h3>
							<p>
								<span style="color: #ff5555; font-weight: bold;">什么是微积金？</span>
							</p>
							<p style="line-height: 24px; text-indent: 34px;">
								微积金（www.vfunding.cn）：微积金金融旗下品牌，中国最专业的汽车抵押网络借贷平台。微积金通过大数据、互联网等有效工具为有需要的个人或企业提供一个安全、诚信、低风险、高回报的投融资渠道，致力于成为中国最专业的互联网汽车金融服务商。</p>
							<p>
								<span style="color: #ff5555; font-weight: bold;">为什么选择微积金？</span>
							</p>
							<p>
								<span style="color: #ff5555;"><b>高</b></span>收益：年化10%--20%收益率
							</p>
							<p>
								<span style="color: #ff5555;"><b>低</b></span>门槛：50元起投，快速灵活收回本息
							<p></p>
							<span style="color: #ff5555;"><b>零</b></span>风险:
							短期、透明、小额抵押型债权，并由第三方担保公司提供担保支持，本息保障
							</p>
							<p>
								<span style="color: #ff5555;"><b>最</b></span>安心：多层风控，方便快捷，透明操作，专业管理。
							</p>

						</div>
					</div>

					<div class="detailItem" id="tenderRecord">
						<div class="tenderRecord">
							<table width="970" cellpadding="0" cellspacing="0">
								<tr>
									<td width="138" height="48" class="tdBg top-border">投标人</td>
									<td width="183" class="tdBg top-border">投标金额</td>
									<td width="217" class="tdBg top-border">有效金额</td>
									<td width="143" class="tdBg top-border">投资奖励</td>
									<td width="156" class="tdBg top-border">投标时间</td>
									<td width="133" class="tdBg top-border">状态</td>
								</tr>

								<c:if test="${empty tenderList}">
									<tr>
										<td colspan="6" height="48">暂无投资记录</td>
									</tr>
								</c:if>
								<c:forEach var="tenderList" items="${tenderList }">
									<tr>
										<td width="138" height="48">${tenderList.username }</td>
										<td width="183">${tenderList.money }</td>
										<td width="217">${tenderList.account }</td>
										<td width="143">0</td>
										<td width="156">${tenderList.tendertime }</td>
										<td width="133"><c:choose>
												<c:when
													test="${tenderList.status ==5 || tenderList.status ==50}">
									投资待审
									</c:when>
												<c:when
													test="${(tenderList.status ==1 || tenderList.status ==10) && tenderList.money-tenderList.account==0 }">
									投资成功
									</c:when>
												<c:when
													test="${ (tenderList.status ==1 || tenderList.status ==10)&& tenderList.money-tenderList.account>0 }">
									部分投资
									</c:when>
												<c:when test="${tenderList.status ==20}">
									投资失败
									</c:when>
											</c:choose>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
		<!-- <div class="detailItem" id="tenderRewrad">
				<h2>投标奖励</h2>
				<div class="tenderRewrad"></div>
			</div>
			<div class="detailItem" id="comment">
				<h2 id="commentH2">
					<span class="sp1 fl">欢迎你来评论</span> <span class="sp2 fr">总评价数（0个）
						<a href="#" target="_blank">查看所有评论</a>
					</span>
				</h2>

				<div class="comment">
					<textarea id="com-textarea"></textarea>
				</div>
				<a href="#" class="btn116 fr">发表评论</a>
			</div> -->
	
	
	
	<div class="advertisement" style="width: 1000px; height:130px; background-color: white;  margin-bottom: 20px; ">

	    <li style=" margin:15px 15px;"><a href="http://www.vfunding.cn/utilpage/toPage/trust"  target="_blank"><img style="margin-top: 15px" src="/images/cach.jpg"/></a></li>
	    
	 
	
	</div>
	
	
	
	
	</div>

	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>

	<script src="${pageContext.request.contextPath}/js/tip.min.js"></script>
	<script type="text/javascript">
		$(function() {
			var useHikesRate = '${useHikesRate }';
			var overplusRate = '${hikesCard.useRate-useHikesRate }';
			var html = '';
			html += ' <tr><td height="20"> <div id="jxk">可加息' + useHikesRate
					+ '%，使用后剩余' + overplusRate
					+ '%的加息 <a class="know-more">了解更多</a></div> </td><tr>';
			//加息卡
			$("#addRate-use").on("click", function() {
				if (useHikesRate <= 0) {
					art.dialog({
						content : "无可用加息",
						title : '微积金提示',
						icon : 'warning',
						ok : function() {
						},
						opacity : .3,
						fixed : true,
						lock : true
					});
					$(this).removeAttr("checked");
					return;
				}

				if ($(this).attr("checked")) {

					$(this).parent().parent().after(html);
				} else {
					$(this).parent().parent().next().remove();
				}

			});

			$(".detailItem").eq(0).show();

			$(".licolor").click(
					function() {

						var index = $(".licolor").index($(this));

						$(".detailItem").eq(index).show().siblings(
								".detailItem").hide();

					});

			//tips
			$(".rc1,.rc2,.rc3,.rc4,.rc5,.rc6").poshytip({
				className : 'tip-yellowsimple',
				showTimeout : 1,
				alignTo : 'target',
				alignX : 'center',
				offsetY : 8,
				allowTipHover : true
			});

			$("#tender-td").on('click', "#lowest-tip", function() {
				$(this).hide().parent().children("input").trigger('focus');
			}).on('blur', 'input', function() {
				if (!this.value) {
					$(this).parent().children("#lowest-tip").show();
				}
			});

		});
	</script>

</body>
</html>
