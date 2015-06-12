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

<script src="${pageContext.request.contextPath }/js/jquery1.8.3.js"></script>
<!-- 分页需要的CSS和JS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/paging/style.css"
	media="screen" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/paging/jquery.paginate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/message/systemMessage.js"></script>
</head>

<body>
	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath }/css/zhanneiMessages.css" />
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
							<h2>消息中心</h2>
							<div class="excel">
								我的消息中心
								<!--<c:choose>
									<c:when test="${isLook == 1 }">
								已读
								</c:when>
									<c:otherwise>
								未读
								</c:otherwise>
								</c:choose>-->
							</div>
						</div>
						<div class="msgNav">
							<ul id="msgNavUl">
								<li class="unactive active">系统消息</li>
								<li class="unactive">站内信</li>
							</ul>
						</div>
						<div class="tab-control"></div>

						<div class="msg-bar clear">
							<div class="tab-control fl">
								<a class="qx"><input type="checkbox" id="selectAll" /><label
									for="selectAll">全选</label></a> <a class="delete"
									href="javascript:deteleMessages();">删除</a>

							</div>
							<span class="fr"> 
							<!-- <a id="noRead"
								href="#">未读<label
									id="newNoReadNum"></label>封
							</a>，<a id="read"
								href="#">已读<label
									id="newReadNum"></label>封 -->
							未读<label
									id="newNoReadNum"></label>封
							，已读<label
									id="newReadNum"></label>封
							，共<label id="sumNum"></label>封
							</span>
						</div>

						<div class="msg-nav">

							<span class="topic">主题</span> <span class="topic-time">发送时间</span>
							<span class="topic-delete">操作</span>

						</div>

						<div class="messages">
							<div id="recordTableBody"></div>
						</div>

						<input type="hidden" id="pagCount" value="1">
						<!-- 保存从第几页开始-->
						<input type="hidden" id="pagStart" value="1">
						<!-- 0是所有,1代表待还,2代表已还-->
						<input type="hidden" id="isLook" value="${isLook}">
						<!-- 保存borrow-status的值-->
						<input type="hidden" id="pagStatus" value="10">
						<div id="paging"></div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
</body>
</html>


