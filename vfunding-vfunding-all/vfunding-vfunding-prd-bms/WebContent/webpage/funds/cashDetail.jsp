<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<jsp:include page="${pageContext.request.contextPath }/inc.jsp"></jsp:include>
<body>
<div style="width:950px;">
	<div style="text-align:center;float:left;width:auto;" class="usermsg">
		<h3 style="background-color:#0081c2;height:30px;color:#ffffff;line-height:30px;">一、提现审核/查看</h3>
		<ul style="width:930px;margin:0 auto;">
			<li style="text-align:right;width:220px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				用户名：
			</li>
			<li style="text-align:left;width:220px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				${cashList.userName}
			</li>
			<li style="text-align:right;width:220px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				提现银行：
			</li>
			<li style="text-align:left;width:220px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				${cashList.bankName }
			</li>
			<li style="text-align:right;width:220px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				提现支行：
			</li>
			<li style="text-align:left;width:220px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				${cashList.branch }
			</li>
			<li style="text-align:right;width:220px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				提现账号：
			</li>
			<li style="text-align:left;width:220px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				${cashList.bankNum }
			</li>
			<li style="text-align:right;width:220px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				提现总额：
			</li>
			<li style="text-align:left;width:220px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				<fmt:formatNumber value="${cashList.money}" pattern="#,##0.00" />
				元
			</li>
			<li style="text-align:right;width:220px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				到账金额：
			</li>
			<li style="text-align:left;width:220px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				<fmt:formatNumber value="${cashList.account}" pattern="#,##0.00" />
				元
			</li>
			<li style="text-align:right;width:220px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				手续费：
			</li>
			<li style="text-align:left;width:220px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				${cashList.fee }元
			</li>
			<li style="text-align:right;width:220px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				状态：
			</li>
			<li style="text-align:left;width:220px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				<span style="color:red;">
					<c:if test="${cashList.status==0}">待审</c:if>
					<c:if test="${cashList.status==1}">处理成功</c:if>
					<c:if test="${cashList.status==2}">处理失败</c:if>
					<c:if test="${cashList.status==3}">用户取消申请</c:if></span>
					<br/>
			</li>
			<li style="text-align:right;width:220px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				添加时间:
			</li>
			<li style="text-align:left;width:220px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				${cashList.addtime }
			</li>
			<li style="text-align:center;width:450px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
			</li>
		</ul>
	</div>
	<div style="text-align:center;float:left;width:950px;" class="usermsg">
		<h3 style="background-color:#0081c2;height:30px;color:#ffffff;line-height:30px;">二、审核此提现信息</h3>
		<c:if test="${not empty repayUser && repayUser == 'Y' }">
		<font color="red" size="3em"><B>该用户提现后账户余额:${repayUserUseMoney},今日待还款:${repayMoneyToday},明日待还款:${repayMoneyTomorrow};${repayUserUseMoney-repayMoneyToday-repayMoneyTomorrow <=0?'(建议不打款)':''}</B></font>
		</c:if>
		<form id="cashForm" name="cashForm">
				<input type="hidden" id="cashId" name="cashId" value="${cashList.cashId }"/>
				状态:
				<input type="radio" value="1" name="status">通过
				<input type="radio" value="2" name="status">不通过
				<br/>
				<br/>
				到账金额：<input type="text" id="" value="${cashList.account}" readonly="readonly"/>
				手续费：<input type="text" id="" value="${cashList.fee}" readonly="readonly"/>
				红包抵扣：<input type="text" id="" value="${cashList.useHongbao}" readonly="readonly"/>
				<br/>
				<br/>
				审核备注:
				<textarea rows="3" cols="90" id="remark" name="remark" style="margin: 0px 0px 10px; width: 700px; height: 64px;"></textarea>
				<br/>
				<br/>
				<input type="button" value="审核此提现信息" name="sub" id="subfrom">
				<br/>
		</form>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/funds/cashDetail.js"></script>
</body>
</html>