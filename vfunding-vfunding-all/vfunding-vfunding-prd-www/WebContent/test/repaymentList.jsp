<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>list</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="description" content="微积金(www.vfunding.cn) 整合了民营汽车借贷机构、二手车商、第三方汽车服务机构等传统领域企业，形成了独特的针对汽车提供投融资对接服务的创新型O2O互联网汽车金融服务模式。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、汽车贷款等。" />
<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|微贷|红包|基金|微基金|小额贷款|微财富|宜车贷|二手车抵押|车贷|托管" />
<title>微积金，中国领先的互联网汽车金融服务平台_理财_理财产品_投融资_理财需求_投资_投资标的_财富_财富成长_手机理财_微信理财_微理财</title>
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script type="text/javascript">
			function up() {
				$("input[name='page']").val('${pageSearch.page-1}');
				$("#form").submit();
			}
			function next() {
				$("input[name='page']").val('${pageSearch.page+1}');
				$("#form").submit();
			}
			
		</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
	<form action="/borrow/repaymentList" method="post" id="form">
		starttime<input type="text" name="startTime" >
		endtime<input type="text" name="endTime" >
		keyword<input type="text" name="keyWord" >
		
		
		<input type="text" name="page" value="${pageSearch.page}">
		<input type="submit" value="提交">
	</form>
	
	<table>
		<c:forEach items="${result.rows }" var="v">
			<tr>
				<td>${v.id}</td>
				<td>${v.borrowName}</td>
				<td>${v.order}/3</td>
				<td>${v.repaymentTime}</td>
				<td>${v.repaymentAccount}</td>
				<td>${v.interest}</td>
				<td>${v.status}</td>
				<td><a href="/borrow/repayFeelBorrow?id=${v.id}">还款</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<hr/>
	总共${pageSearch.totalResult}-${pageSearch.rows} --共有${pageSearch.totalResult/pageSearch.rows }页
	
	<a href="javascript:first();">首页</a>
	
	<a href="javascript:up();">上一页</a>
	<a href="javascript:next();">下一页</a>
	
	<a href="javascript:end();">末页</a>
</body>
</html>