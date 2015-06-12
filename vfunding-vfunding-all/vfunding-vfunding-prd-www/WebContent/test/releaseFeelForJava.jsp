<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>会员登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>微积金-微积金，专业互联网金融服务平台_理财_理财产品_投融资_理财需求_投资_投资标的_财富_财富成长_手机理财_微信理财_微理财</title>
<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|工薪贷|生意贷|基金|微基金|小额贷款|投融资|宜车贷|二手车抵押|押车|担保">
<meta name="description" content="微积金(www.vfunding.cn) 互联网金融服务平台旨在为有资金需求和理财需求的个人或企业搭建一个真实诚信、安全透明、快捷高效的互联网服务平台和投资理财渠道。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、担保贷款等。">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0">
	<h1>Java版发布体验标</h1>
	<hr>
	<form action="/borrow/releaseFeelBorrow" method="post">
	借贷总金额：<input type="text" name="account" ><br/>
	年利率：<input type="text" name="apr" ><br/>
	借款用途：<input type="text" name="use" ><br/>
	借款期限：<input type="text" name="timeLimitDay" >天<br/>
	min：<input type="text" name="lowestAccount" ><br/>
	标题：<input type="text" name="name" ><br/>
	content：<input type="text" name="content" ><br/>
	<input type="hidden" name="userId" value="202"><br/>
	<input type="hidden" name="validTime" value="1"><br/>
	<input type="submit" value="提交">
	</form>
</body>
</html>