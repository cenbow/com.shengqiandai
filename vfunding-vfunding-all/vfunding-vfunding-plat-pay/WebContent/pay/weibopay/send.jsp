<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<body>
	<form id="recvForm"
		action="https://gate.pay.sina.com.cn/acquire-order-channel/gateway/receiveOrderLoading.htm"
		method="post">
		<input type="hidden" name="signType" value="${payInfo.signType }" /> 
		<input type="hidden" name="version" value="${payInfo.version }" /> 
		<input type="hidden" name="cancelUrl" value="${payInfo.cancelUrl }" /> 
		<input type="hidden" name="inputCharset" value="${payInfo.inputCharset }" /> 
		<input type="hidden" name="bgUrl" value="${payInfo.bgUrl }" />
		<input type="hidden" name="orderAmount" value="${payInfo.orderAmount }" /> 
		<input type="hidden" name="orderId" value="${payInfo.orderId }" /> 
		<input type="hidden" name="merchantAcctId" value="${payInfo.merchantAcctId }" /> 
		<input type="hidden" name="pid" value="${payInfo.pid }" /> 
		<input type="hidden" name="bankId" value="${payInfo.bankId }" /> 
		<input type="hidden" name="orderTime" value="${payInfo.orderTime }" /> 
		<input type="hidden" name="signMsg" value="${payInfo.signMsg }" />
		<input type="hidden" name="language" value="${payInfo.language }" />
		<input type="hidden" name="payType" value="${payInfo.payType }" />
	
	</form>
	<script language="javascript">
		recvForm.submit();
	</script>
</body>
</html>


