<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="date" uri="http://cn.vfunding.vfunding.prd.www/tags"%>
<!doctype html>
<html>
<head>
<meta charset="${empty charset ?'utf-8':charset}">
<meta name="description" content="微积金(www.vfunding.cn) 整合了民营汽车借贷机构、二手车商、第三方汽车服务机构等传统领域企业，形成了独特的针对汽车提供投融资对接服务的创新型O2O互联网汽车金融服务模式。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、汽车贷款等。" />
<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|微贷|红包|基金|微基金|小额贷款|微财富|宜车贷|二手车抵押|车贷|托管" />
<title>媒体报道_微积金_中国领先的O2O互联网汽车金融平台</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/aboutUs.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<!-- 分页需要的CSS和JS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/paging/style.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/paging/jquery.paginate.js"></script>
<script type="text/javascript">
$(function() {
	var mediasSize = parseInt('${mediasResults.total}')/8;
	pageAjax("paging",mediasSize,85);
})
function pageAjax(id,count,type){
	$("#"+id).paginate({
		count : count,
		start : 1,
		images : false,
		mouse : 'press',
		onChange : function(page) {
			pageAjaxList(page,type)
		}
	});
}
function pageAjaxList(page,type){
	$.ajax({
		url : "/news/articleAjaxList",
		type : "post",
		cache : false,
		async : false,
		data : {"page":page,"type":type},
		success : function(result) {
			result = $.parseJSON(result);
			var rows = result.rows;
			html = '';
			for(var i=0; i<rows.length;i++){
				var time = timetodate(rows[i].addtime,"yyyy-MM-dd");
				var name = rows[i].name;
				var url = '/utilpage/selectArticleDetail/'+rows[i].id;
				html = html + '<li>'+
				'<a href="'+url+'" target="_blank">'+
				'<span class="g-title">'+name+'</span>'+
				'<span class="g-time">'+time+'</span>'+
				'</a>'+
				'</li>';
			}
			$('#mediascontent').html('<ul>'+html+'</ul>')
		}
	});
}
</script>
</head>
<body>
	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>
	<!--中间-->
	<div class="content clear">
		<jsp:include page="${pageContext.request.contextPath }/utilPage/aboutUsMenu.jsp"></jsp:include>
		<div class="about-us-right fr box-shadow">
			
		<!-- 媒体报道 -->
			<div class="au-box" id="medias_">
				<div class="au-title">媒体报道</div>
				<div id="mediascontent">
					<ul>
						<c:forEach items="${mediasResults.rows}" var="v">
							<li>
								<a href="${pageContext.request.contextPath }/utilpage/selectArticleDetail/${v.id}" target="_blank">
									<span class="g-title">${v.name}</span>
								</a>
								<span class="g-time">
									<date:date parttern="yyyy-MM-dd" value="${v.addtime}"/>
								</span>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div id="paging" style="padding-left: 62px;"></div>
			</div>
		</div>
	</div>
	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
</body>
</html>
