<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="date" uri="http://cn.vfunding.vfunding.prd.www/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="${article.summary}" />
<meta name="keywords" content="${fn:split(article.littitle,',')[0]}|${fn:split(article.littitle,',')[1]}|${fn:split(article.littitle,',')[2]}" />
<title>${article.name }_微积金</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/grid.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/box.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/list.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/seo_list.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<!-- 分页需要的CSS和JS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/paging/style.css"
	media="screen" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/paging/jquery.paginate.js"></script>
<script type="text/javascript">
	function post(URL, PARAMS) {
		var temp = document.createElement("form");
		temp.action = URL;
		temp.method = "post";
		temp.style.display = "none";
		for ( var x in PARAMS) {
			var opt = document.createElement("textarea");
			opt.name = x;
			opt.value = PARAMS[x];
			temp.appendChild(opt);
		}
		document.body.appendChild(temp);
		temp.submit();
		return temp;
	}
</script>
</head>
<body>
	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>
	<!--中间-->
	<div class="content">
		<div class="bread">
			<a href="${pageContext.request.contextPath}">首页 &gt;&gt;</a> <a
				href="${pageContext.request.contextPath}/utilpage/vNewsList/108/1">小微资讯
				&gt;&gt;</a> <a
				href="${pageContext.request.contextPath}/utilpage/vNewsList/${article.siteId}/1">${article.siteId==109?'攻略':'新闻'}</a>
		</div>
		<div class="ui-grid-row mb10">
			<div class="ui-grid-17">
				<div class="ui-box">
					<div class="ui-box-wrap">
						<div class="ui-box-container">
							<div class=" ui-box-content">
								<h3 class="ui-box-content-title text-center">${article.name }</h3>
								<p class="ui-box-content-time text-center">
									发布时间：
									<date:date parttern="yyyy-MM-dd" value="${article.addtime}" />
									&nbsp; 来源：${article.source}&nbsp;&nbsp;浏览数：${article.hits}
								</p>
								<div class="sumit">摘要：${article.summary}</div>

								<p class="ui-box-content-text">${article.content}</p>

							</div>
						</div>
						<div class="ui-box-footer clear">
							<div class="label fl">标签</div>
							<div class="label-title fl">
								<ul>
									<li><a
										style="${(fn:split(article.littitle,',')[0])==keyword?'color:red':''}"
										href="javascript:post('/utilpage/search/1',{keyword:'${fn:split(article.littitle,',')[0]}'})">${fn:split(article.littitle,',')[0]}</a></li>
									<li><a
										style="${(fn:split(article.littitle,',')[1])==keyword?'color:red':''}"
										href="javascript:post('/utilpage/search/1',{keyword:'${fn:split(article.littitle,',')[1]}'})">${fn:split(article.littitle,',')[1]}</a></li>
									<li><a
										style="${(fn:split(article.littitle,',')[2])==keyword?'color:red':''}"
										href="javascript:post('/utilpage/search/1',{keyword:'${fn:split(article.littitle,',')[2]}'})">${fn:split(article.littitle,',')[2]}</a></li>
								</ul>
							</div>
							<div class="share fr">
								<!-- JiaThis Button BEGIN -->
								<div class="jiathis_style">
									<a class="jiathis_button_qzone"></a> <a
										class="jiathis_button_tsina"></a> <a
										class="jiathis_button_tqq"></a> <a
										class="jiathis_button_weixin"></a> <a
										class="jiathis_button_renren"></a> <a
										class="jiathis_button_xiaoyou"></a> <a
										href="http://www.jiathis.com/share"
										class="jiathis jiathis_txt jtico jtico_jiathis"
										target="_blank"></a>
								</div>
								<script type="text/javascript"
									src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
								<!-- JiaThis Button END -->
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="ui-grid-8">
				<a href="${pageContext.request.contextPath}/fresh"><img
					class="ad-img"
					src="${pageContext.request.contextPath}/images/news/ad.jpg"
					width="310" /></a>
				<div class="ui-box white-color-bg">
					<div class="ui-box-head hot-content">本月热门内容</div>
					<div class="ui-box-container">
						<div class="ui-box-content pd0">
							<ul class="ui-list">
								<c:forEach items="${hotArticle}" var="v" varStatus="vs">
									<li class="ui-list-item item"><i
										class="rect fl ${(vs.index+1)>3?'gray-bg':'blue-bg'}">${vs.index+1}</i>
										<a
										href="${pageContext.request.contextPath}/utilpage/vNewsDetail/${v.id}"
										target="_blank"> ${fn:substring(v.name, 0, 15)}</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
				<div class="ui-box white-color-bg mt20">
					<div class="ui-box-head hot-content">热门标签</div>
					<div class="ui-box-container">
						<div class="ui-box-content hot-label">
							<%-- 					<c:forEach items="${marks}" var="v">
						<a href="${pageContext.request.contextPath}/utilpage/search?keyword=${v}" target="_blank">${v}</a>
					</c:forEach> --%>
							<a href="javascript:post('/utilpage/search/1',{keyword:'融资'})" target="_blank">融资</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'微积金'})" target="_blank">微积金</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'理财产品'})" target="_blank">理财产品</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'P2P行业'})" target="_blank">P2P行业</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'融资计划书'})" target="_blank">融资计划书</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'融资融券'})" target="_blank">融资融券</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'融资方式'})" target="_blank">融资方式</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'投资'})" target="_blank">投资</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'投资理财'})" target="_blank">投资理财</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'投资者'})" target="_blank">投资者</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'P2P线上理财产品'})" target="_blank">P2P线上理财产品</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'互联网金融'})" target="_blank">互联网金融</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'投资理财产品'})" target="_blank">投资理财产品</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'P2P金融理财产品'})" target="_blank">P2P金融理财产品</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'金融平台'})" target="_blank">金融平台</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'风险投资'})" target="_blank">风险投资</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'P2P信贷平台'})" target="_blank">P2P信贷平台</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'投资理财知识'})" target="_blank">投资理财知识</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'融资资金'})" target="_blank">融资资金</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'微积金的车贷抵押'})" target="_blank">微积金的车贷抵押</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'贷款'})" target="_blank">贷款</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'线上融资'})" target="_blank">线上融资</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'融资成本'})" target="_blank">融资成本</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'企业融资'})" target="_blank">企业融资</a>	
			<a href="javascript:post('/utilpage/search/1',{keyword:'融资融券交易'})" target="_blank">融资融券交易</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'什么是融资融券'})" target="_blank">什么是融资融券</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'融资担保公司'})" target="_blank">融资担保公司</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'股票'})" target="_blank">股票</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'利润'})" target="_blank">利润</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'委托价'})" target="_blank">委托价</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'财富'})" target="_blank">财富</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'保证金'})" target="_blank">保证金</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'白银'})" target="_blank">白银</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'个股'})" target="_blank">个股</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'存款'})" target="_blank">存款</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'债券'})" target="_blank">债券</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'股票投资'})" target="_blank">股票投资</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'金融'})" target="_blank">金融</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'货币'})" target="_blank">货币</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'P2P'})" target="_blank">P2P</a>	
			<a href="javascript:post('/utilpage/search/1',{keyword:'比特币'})" target="_blank">比特币</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'虚拟商品'})" target="_blank">虚拟商品</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'Q币'})" target="_blank">Q币</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'信用卡'})" target="_blank">信用卡</a>	
			<a href="javascript:post('/utilpage/search/1',{keyword:'集合理财'})" target="_blank">集合理财</a>
			<a href="javascript:post('/utilpage/search/1',{keyword:'P2P平台'})" target="_blank">P2P平台</a>	
			<a href="javascript:post('/utilpage/search/1',{keyword:'基金'})" target="_blank">基金</a>
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





