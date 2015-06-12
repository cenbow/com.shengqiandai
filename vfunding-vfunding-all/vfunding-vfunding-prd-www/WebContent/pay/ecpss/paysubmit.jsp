
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
</head>

<body onLoad="javascript:document.E_FORM.submit()">

	<form action="https://pay.ecpss.com/sslpayment" method="post"
		name="E_FORM">
		<table align="center">

			<tr>
				<td></td>
				<td><input type="hidden" name="MerNo" value="${MerNo}"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="hidden" name="BillNo" value="${BillNo}"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="hidden" name="Amount" value="${Amount}"></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="hidden" name="ReturnURL" value="${ReturnURL}"></td>
			</tr>
			
			<tr>
				<td></td>
				<td><input type="hidden" name="defaultBankNumber" value="${defaultBankNumber}"></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="hidden" name="AdviceURL" value="${AdviceURL}"></td>
			</tr>


			<tr>
				<td></td>
				<td><input type="hidden" name="SignInfo" value="${SignInfo}"></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="hidden" name="orderTime" value="${orderTime}"></td>
			</tr>
		</table>
	</form>



</body>
</html>
