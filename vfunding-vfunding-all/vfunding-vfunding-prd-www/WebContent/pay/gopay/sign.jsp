<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>

</head>
<body onLoad="javascript:document.E_FORM.submit()">
 <!-- https://gateway.gopay.com.cn/Trans/WebClientAction.do -->
	<form action="https://www.gopay.com.cn/PGServer/Trans/WebClientAction.do"
		method="POST" name="E_FORM" id="E_FORM">
		<table align="center" style="display: none;">
			<tr>
				<td width="40%" align=right>字符集(charset):</td>
				<td align=center><input type="hidden" id="charset" name="charset"
					value="2" size="50" readonly="readonly" /> <br />
					1:GBK,2:UTF-8</td>
			</tr>

			<tr>
				<td width="40%" align=right>语言种类(language)：</td>
				<td align=center><input type="hidden" id="language"
					name="language" value="1" size="50" /> <br />1:ZH,2:EN</td>
			</tr>
			<tr>
				<td width="40%" align=right>加密方式(signType)：</td>
				<td align=center><input type="hidden" id="signType"
					name="signType" value="1" size="50" /> <br />1:MD5,2:SHA</td>
			</tr>

			<tr>
				<td width="40%" align=right>币种(currencyType):</td>
				<td align=center><input type="hidden" id="currencyType"
					name="currencyType" value="156" size="50" /></td>
			</tr>
			  <tr>
				<td width="40%" align=right>商户返回页面地址 (frontMerUrl):</td>
				<td align=center><input type="hidden" id="frontMerUrl"
					name="frontMerUrl" value="${goPay.frontMerUrl}" size="50" /></td>
			</tr>
			 <tr>
				<td width="40%" align=right>商户返回后台通知 (backgroundMerUrl):</td>
				<td align=center><input type="hidden" id="backgroundMerUrl"
					name="backgroundMerUrl" value="${goPay.backgroundMerUrl}" size="50" /></td>
			</tr>
			<tr>
				<td width="40%" align=right>选择的银行(bankCode):</td>
				<td align=center><input type="hidden" id="bankCode"
					name="bankCode" value="${goPay.bankCode}" size="50" /></td>
			</tr>
			
			<tr>
				<td width="40%" align=right>用户类型(userType):</td>
				<td align=center><input type="hidden" id="userType"
					name="userType" value="${goPay.userType}" size="50" /></td>
			</tr>

			<tr>
				<td width="40%" align=right>交易代码(tranCode):</td>
				<td align=center><input type="hidden" id="tranCode"
					name="tranCode" value="8888" size="50" /></td>
			</tr>
			<tr>
				<td width="40%" align=right>商户ID(merchantID):</td>
				<td align=center><input type="hidden" id="merchantID"
					name="merchantID" value="${goPay.merchantID}" size="50" /></td>
			</tr>
			<tr>
				<td width="40%" align=right>订单号(merOrderNum):</td>
				<td align=center><input type="hidden" id="merOrderNum"
					name="merOrderNum" value="${goPay.merOrderNum}" size="50" /></td>
			</tr>
			<tr>
				<td width="40%" align=right>交易金额(tranAmt):</td>
				<td align=center><input type="hidden" id="tranAmt" name="tranAmt"
					value="${goPay.tranAmt}" size="50" /></td>
			</tr>

			<tr>
				<td width="40%" align=right>交易时间(tranDateTime):</td>
				<td align=center><input type="hidden" id="tranDateTime"
					name="tranDateTime" value="${goPay.tranDateTime}" size="50" /></td>
			</tr>

			<tr>
				<td width="40%" align=right>国付宝转入账户（virCardNoIn）:</td>
				<td align=center><input type="hidden" id="virCardNoIn"
					name="virCardNoIn" value="${goPay.virCardNoIn}" size="50" /></td>
			</tr>
			<tr>
				<td width="40%" align=right>用户浏览器IP（tranIP）:</td>
				<td align=center><input type="hidden" id="tranIP" name="tranIP"
					value="${goPay.tranIP}" size="50" /></td>
			</tr>




			<tr>
				<td width="40%" align=right>MD5加密报文(signValue):</td>
				<td align=center><input type="hidden" id="signValue"
					name="signValue" value="${goPay.signValue}" size="50"
					readonly="readonly" /></td>
			</tr>

			<tr>
				<td width="40%" align=right>版本号(version):</td>
				<td align=center><input type="hidden" id="version" name="version"
					value="2.1" size="50" readonly="readonly" /></td>
			</tr>



		</table>
	</form>
</body>
</html>