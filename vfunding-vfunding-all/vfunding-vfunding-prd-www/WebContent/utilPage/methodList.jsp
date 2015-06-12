<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="date" uri="http://cn.vfunding.vfunding.prd.www/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
<head>
<meta charset="${empty charset ?'utf-8':charset}">
<meta name="description" content="微积金(www.vfunding.cn) 整合了民营汽车借贷机构、二手车商、第三方汽车服务机构等传统领域企业，形成了独特的针对汽车提供投融资对接服务的创新型O2O互联网汽车金融服务模式。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、汽车贷款等。" />
<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|微贷|红包|基金|微基金|小额贷款|微财富|宜车贷|二手车抵押|车贷|托管" />
<title>微积金，中国领先的互联网汽车金融服务平台_理财_理财产品_投融资_理财需求_投资_投资标的_财富_财富成长_手机理财_微信理财_微理财</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/grid.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/box.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/list.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/seo_list.css"/>
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<!-- 分页需要的CSS和JS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/paging/style.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/paging/jquery.paginate.js"></script>
<script type="text/javascript">
$(function() {
	//alert('${keyword}')
	var reportSize = parseInt('${article.total}')/4;
	pageAjax("rpaging",reportSize);
})
function pageAjax(id,count){
	var path = window.location.pathname;
	$("#"+id).paginate({
		count : count,
		start : '${page}',
		images : false,
		mouse : 'press',
		onChange : function(page) {
			window.location.href = path +"?page="+page+"&keyword="+'${keyword}';
		}
	});
}
</script>
</head>
<body>
	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>
<!--中间-->
<div class="content">
<div class="ui-grid-row mt20 mb10">
<div class="ui-grid-17">
  <div class="ui-box">
    <div class="ui-box-head h60">
    <h3 class="ui-box-head-title text-size-blue">攻略</h3>
    </div>
    
    <c:forEach items="${article.rows}" var="vv">
    <div class="ui-box-wrap">
   <div class="ui-box-container">
   <div class=" ui-box-content">
    <h3 class="ui-box-content-title">${vv.name }</h3>
   <p class="ui-box-content-time">发布时间：<date:date parttern="yyyy-MM-dd" value="${vv.addtime}"/>       &nbsp;浏览数：${vv.hits}</p> 
 <p class="ui-box-content-text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${vv.summary }……
 <a href="${pageContext.request.contextPath}/utilpage/vfundingDetail/id-${vv.id}" target="_blank">【阅读全文】</a>   
 </p>
   </div>
   </div>
   <div class="ui-box-footer clear">
    <div class="label fl"> 标签</div>
    <div class="label-title fl">
		<ul>
			<li>${fn:split(vv.littitle,',')[0]}</li>
			<li>${fn:split(vv.littitle,',')[1]}</li>
			<li>${fn:split(vv.littitle,',')[2]}</li>
		</ul>
    </div>
	<div class="share fr">
		<!-- 分享 -->
		<div class="jiathis_style">
			<a class="jiathis_button_qzone"></a>
			<a class="jiathis_button_tsina"></a>
			<a class="jiathis_button_tqq"></a>
			<a class="jiathis_button_weixin"></a>
			<a class="jiathis_button_renren"></a>
			<a class="jiathis_button_xiaoyou"></a>
			<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
		</div>
		<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
		<!-- JiaThis Button END -->
	</div>
	</div>
	</div>
	</c:forEach>
   
   <div id="rpaging" style="padding-left: 62px;"></div>
  </div>
</div>

<div class="ui-grid-8">
<img class="ad-img" src="${pageContext.request.contextPath}/images/news/ad.png" width="310"/>
<div class="ui-box white-color-bg">
  <div class="ui-box-head hot-content" >本月热门内容</div>
  <div class="ui-box-container">
    <div class="ui-box-content pd0">
      <ul class="ui-list">
      	<c:forEach items="${hotArticle}" var="v" varStatus="vs">
			<li class="ui-list-item item"><i class="rect fl ${(vs.index+1)>3?'gray-bg':'blue-bg'}">${vs.index+1}</i>
				<a href="${pageContext.request.contextPath}/utilpage/vfundingDetail/id-${v.id}" target="_blank">
				${fn:substring(v.name, 0, 15)}</a>
			</li>
		</c:forEach>
      </ul>
    </div>
  </div>
</div>

<div class="ui-box white-color-bg mt20">
	<div class="ui-box-head hot-content">热门标签</div>
	<div class="ui-box-container">
		<div class="ui-box-content hot-label">
		<c:forEach items="${fn:split(mark, ',')}" var="v">
			<a href="${pageContext.request.contextPath}/utilpage/vfundingList?keyword=${v}" target="_blank">${v}</a>
		</c:forEach> 
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
