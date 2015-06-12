<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>单笔代付金额(新浪资金托管)</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sinadeposit/singleHostingPayTrade.js"></script>
<c:if test="${canSearch==true }">
	<script type="text/javascript">
		$.canSearch = true;
	</script>
</c:if>
</head>
<body class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<div style="margin:10px;">
			中间账户付款给这个用户
			<br/><br/>		
			用户ID或企业钱包账号(必填):<input type="text" id="username" name="username" style="width: 120px; height: 12px; margin: 0;" value=""/> 
			<br/><br/>
			金额(必填,小数最多两位):<input type="text" id="money" name="money" style="width: 120px; height: 12px; margin: 0;" value=""/> 
			<br/><br/>
			摘要(必填):<input type="text" id="summary" name="summary" style="width: 120px; height: 12px; margin: 0;" value=""/> 
			<br/><br/>
			中间账户交易码:<select id="tradeCode" name="tradeCode" style="width: 120px; height: 28px;">
				<option value="2001">2001</option>
				<option value="2002">2002</option>
			</select> 
			<br/><br/><br/><br/>
			<c:if test="${canSearch==true }">
				<a href="javascript:queryDetail();" class="easyui-linkbutton" iconCls="icon-search" id="searchButton">提交</a>
			</c:if>			
		</div>
		<div id="wait" style="margin:10px;">
			<img src="/style/images/wait.gif" />
		</div>
		<div id="result" style="margin:10px;">
		</div>		
	</div>
	<div id="searchTool" style="display:none;">
	</div>
</body>
</html>