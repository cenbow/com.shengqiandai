<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<jsp:include page="${pageContext.request.contextPath }/inc.jsp"></jsp:include>
<body>
	<div style="width: 950px;">
		<div style="text-align: center; float: left; width: auto;"
			class="usermsg">
			<h3
				style="background-color: #0081c2; height: 30px; color: #ffffff; line-height: 30px;">一、提现审核/查看</h3>
			<ul style="width: 930px; margin: 0 auto;">
				<li
					style="text-align: right; width: 220px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					用户名：</li>
				<li
					style="text-align: left; width: 220px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					${cashList.userName}</li>
				<li
					style="text-align: right; width: 220px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					提现银行：</li>
				<li
					style="text-align: left; width: 220px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					${cashList.bankName }</li>
				<li
					style="text-align: right; width: 220px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					提现支行：</li>
				<li
					style="text-align: left; width: 220px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					${cashList.branch }</li>
				<li
					style="text-align: right; width: 220px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					提现账号：</li>
				<li
					style="text-align: left; width: 220px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					${cashList.bankNum }</li>
				<li
					style="text-align: right; width: 220px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					提现总额：</li>
				<li
					style="text-align: left; width: 220px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					<fmt:formatNumber value="${cashList.money}" pattern="#,##0.00" />
					元
				</li>
				<li
					style="text-align: right; width: 220px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					到账金额：</li>
				<li
					style="text-align: left; width: 220px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					<fmt:formatNumber value="${cashList.account}" pattern="#,##0.00" />
					元
				</li>
				<li
					style="text-align: right; width: 220px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					手续费：</li>
				<li
					style="text-align: left; width: 220px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					${cashList.fee }元</li>
				<li
					style="text-align: right; width: 220px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					状态：</li>
				<li
					style="text-align: left; width: 220px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					<span style="color: red;"> <c:if
							test="${cashList.status==0}">待审</c:if> <c:if
							test="${cashList.status==1}">处理成功</c:if> <c:if
							test="${cashList.status==2}">处理失败</c:if> <c:if
							test="${cashList.status==3}">用户取消申请</c:if></span> <br />
				</li>
				<li
					style="text-align: right; width: 220px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					添加时间:</li>
				<li
					style="text-align: left; width: 220px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					${cashList.addtime }</li>
				<li
					style="text-align: center; width: 450px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
				</li>
			</ul>
		</div>
		<div style="text-align: center; float: left; width: 950px;"
			class="usermsg">
			<h3
				style="background-color: #0081c2; height: 30px; color: #ffffff; line-height: 30px;">二、审核此提现信息</h3>
			<form id="cashForm" name="cashForm">
				<br /> 到账金额：<input type="text" id="" value="${cashList.account}"
					readonly="readonly" /> 手续费：<input type="text" id=""
					value="${cashList.fee}" readonly="readonly" /> 红包抵扣：<input
					type="text" id="" value="${cashList.useHongbao}"
					readonly="readonly" /> <br /> <br /> <input type="hidden"
					value="${ cashList.cashId}" name="cashId"> <input
					type="hidden" value="${cashNumber.id }" name="id"> 银行汇款单号:
				<input type="text" id="bankcashNo" name="bankcashNo"
					value="${cashNumber.bankcashNo }"> 汇款时间:
				<c:choose>
					<c:when test="${empty cashNumber.addTime }">
				--------
				</c:when>
					<c:otherwise>
						<fmt:formatDate value="${cashNumber.addTime  }" type="both" />
					</c:otherwise>
				</c:choose>
			<%-- 	&nbsp;&nbsp;&nbsp;&nbsp; 更新时间:
				<c:choose>
					<c:when test="${empty cashNumber.updateTime }">
				--------
				</c:when>
					<c:otherwise>
						<fmt:formatDate value="${cashNumber.updateTime  }" type="both" />
					</c:otherwise>
				</c:choose> --%>
				<br /> <br />
				<c:choose>
					<c:when test="${ empty cashNumber.bankcashNo}">
						<a href="javaScript:addBankcashNo()"><input type="button"
							value="添加汇款单号信息" name="sub" id="subfrom"></a>
					</c:when>
					<c:otherwise>
						<a href="javaScript:updateBankcashNo()"><input type="button"
							value="更新汇款单号信息" name="sub" id="subfrom"></a>

					</c:otherwise>
				</c:choose>

				<br />
			</form>
		</div>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/funds/waitAppltCashList.js"></script>
</body>
</html>