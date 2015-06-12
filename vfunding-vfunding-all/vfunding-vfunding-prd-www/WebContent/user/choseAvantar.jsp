<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
<meta name="description" content="微积金(www.vfunding.cn) 整合了民营汽车借贷机构、二手车商、第三方汽车服务机构等传统领域企业，形成了独特的针对汽车提供投融资对接服务的创新型O2O互联网汽车金融服务模式。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、汽车贷款等。" />
<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|微贷|红包|基金|微基金|小额贷款|微财富|宜车贷|二手车抵押|车贷|托管" />
<title>微积金，中国领先的互联网汽车金融服务平台_理财_理财产品_投融资_理财需求_投资_投资标的_财富_财富成长_手机理财_微信理财_微理财</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/choseAvantar.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
</head>

<body>
		<!-----头部-------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>

	<!--中间-->

	<div class="content">

		<div class="myAccount">

				<!-- 左边通用页面 -->
			<jsp:include
				page="${pageContext.request.contextPath }/user/accountLeft.jsp"></jsp:include>
			<!-- 右边主体部分 开始 -->

			<div class="rechargeTakeCash fr">
				<div class="rT">
					<div class="rTWrap">
						<div class="txTop">
							<b class="midLine"></b>
							<h2 class="zhcz">修改头像</h2>
							<a href="${pageContext.request.contextPath }/user/account" class="goBack">返回</a>
						</div>




						<div class="modifyAvantar">
							<a class="prev"></a> <a class="next"></a>


							<div class="img-change">

								<form action="#" method="post" id="avantarForm">

									<div class="1" id="mainboard">
										<a><img src="${pageContext.request.contextPath}/data/avatar/default/1_middle.jpg"
											name="1" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/2_middle.jpg" name="2" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/3_middle.jpg" name="3" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/4_middle.jpg" name="4" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/5_middle.jpg" name="5" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/6_middle.jpg" name="6" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/7_middle.jpg" name="7" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/8_middle.jpg" name="8" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/9_middle.jpg" name="9" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/10_middle.jpg" name="10" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/11_middle.jpg" name="11" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/12_middle.jpg" name="12" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/13_middle.jpg" name="13" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/14_middle.jpg" name="14" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/15_middle.jpg" name="15" /></a>
									</div>
									<div class="16" id="mainboard" style="display: none;">
										<a><img src="${pageContext.request.contextPath}/data/avatar/default/16_middle.jpg"
											name="16" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/17_middle.jpg" name="17" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/18_middle.jpg" name="18" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/19_middle.jpg" name="19" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/20_middle.jpg" name="20" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/21_middle.jpg" name="21" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/22_middle.jpg" name="22" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/23_middle.jpg" name="23" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/24_middle.jpg" name="24" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/25_middle.jpg" name="25" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/26_middle.jpg" name="26" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/27_middle.jpg" name="27" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/28_middle.jpg" name="28" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/29_middle.jpg" name="29" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/30_middle.jpg" name="30" /></a>
									</div>

									<div class="31" id="mainboard" style="display: none;">
										<a><img src="${pageContext.request.contextPath}/data/avatar/default/31_middle.jpg"
											name="31" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/32_middle.jpg" name="32" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/33_middle.jpg" name="33" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/34_middle.jpg" name="34" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/35_middle.jpg" name="35" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/36_middle.jpg" name="36" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/37_middle.jpg" name="37" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/38_middle.jpg" name="38" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/39_middle.jpg" name="39" /></a> <a><img
											src="${pageContext.request.contextPath}/data/avatar/default/40_middle.jpg" name="40" /></a>

									</div>


									<input type="hidden" name="user-icon" id="user-icon" />
								</form>

							</div>

							<script type="text/javascript">
								$(function() {
									$(".prev").bind("click",
													function() {
														var classname = $(
																"#mainboard")
																.attr('class');

														if (classname != "1") {

															nclassname = parseInt(classname) - 15;
															$("." + nclassname)
																	.show();
															$("." + nclassname)
																	.attr("id",
																			"mainboard");
														}
													});

									$(".next")
											.bind(
													"click",
													function() {
														var classname = $(
																"#mainboard")
																.attr('class');
														if (classname != "31") {
															$("." + classname)
																	.hide();
															$("." + classname)
																	.removeAttr(
																			"id");
															nclassname = parseInt(classname) + 15;
															$("." + nclassname)
																	.show();
														}
													});
									$("#mainboard a img").bind("click",
											function() {
												$(this).css("border","2px solid #00a0e9").parent().siblings().children("img").css("border",
																"1px solid #cccccc");											
												var image_name = this.name;
												$("#user-icon").val(image_name);
											})
								})
								
								   function saveHead(){
									var userIcon=$('#user-icon').val();
									if(userIcon!=null && userIcon!='' && userIcon.length>0){
										$.post('/user/headPic/'+userIcon,function(data){
											if(data.success){
												location.href = '/user/account';
											}else{
											  art.dialog({
													content : data.msg,
													ok : true,
													lock : true,
													icon : 'error'
												});
											}
										},'json');
									}
									
								}
							</script>

						</div>


						<div class="savebtn">

							<div class="btns">
								<a class="chose-pic" href="javascript:saveHead();">保存头像</a> 
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



