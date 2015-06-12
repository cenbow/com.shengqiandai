<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>额度审核详情</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
</head>
<body>
<div style="width:950px;">
	<div style="text-align:center;float:left;width:auto;" class="usermsg">
		<h3 style="background-color:#0081c2;height:30px;color:#ffffff;line-height:30px;width:950px;">一、额度审核</h3>
		<ul style="width:920px;margin:0 auto;">
			<li style="text-align:center;width:410px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
			用户名：${applyDetail.verifyRemark}
			</li>
			<li style="text-align:center;width:490px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
			借款类型:<c:if test="${applyDetail.type == 'borrow_vouch'}">
				信用额度
			</c:if>
			<c:if test="${applyDetail.type != 'credit'}">
				其他额度
			</c:if>
			</li>
			<li style="text-align:center;width:410px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
			原来金额:${applyDetail.accountOld}元
			</li>
			<li style="text-align:center;width:490px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
			申请额度:${applyDetail.account}元
			</li>
			<li style="text-align:center;width:900px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
			内容：${applyDetail.content}
			</li>
		</ul>
	</div>

	<div style="text-align:center;float:left;width:950px;" class="usermsg">
		<h3 style="background-color:#0081c2;height:30px;color:#ffffff;line-height:30px;">二、审核</h3>
		<form id="finalForm" name="finalForm">
			<div>
				<input type="hidden" id="id" name="amountId" value="${applyDetail.id }" /> <br />
				
				审批额度:<input type="text" id="account" name="account" value="${applyDetail.account}" />元<br />
				状态:
				<input type="radio" checked="checked" value="1" name="status">审核成功
				<input type="radio" value="0" name="status"> 审核失败 <br /><br /> 审核备注:
				<textarea rows="3" cols="90" id="remark" name="remark" style="margin: 0px 0px 10px; width: 700px; height: 64px;"></textarea>
				<br/><input type="button" value="审核此额度" name="sub" id="subfrom">
			</div>
		</form>
	</div>
	
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/borrow/userAmountDetail.js"></script>
</body>
</html>