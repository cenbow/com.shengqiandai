<%@page contentType="text/html; charset=utf-8" language="java"%>
<html>
<body>
	<TABLE width=500 border=0 align="center" cellPadding=0 cellSpacing=0>
		<TBODY>
			<TR>
				<TD vAlign=top align=middle>
					<div align="left">
						<B><FONT style="FONT-SIZE: 14px">MD5校验码:${chinaBankReceive.v_md5str}</FONT></B>
					</div>
				</TD>
			</TR>
			<TR>
				<TD vAlign=top align=middle>
					<div align="left">
						<B><FONT style="FONT-SIZE: 14px">订单号:${chinaBankReceive.v_oid}</FONT></B>
					</div>
				</TD>
			</TR>
			<TR>
				<TD vAlign=top align=middle>
					<div align="left">
						<B><FONT style="FONT-SIZE: 14px">支付卡种:${chinaBankReceive.v_pmode}</FONT></B>
					</div>
				</TD>
			</TR>
			<TR>
				<TD vAlign=top align=middle>
					<div align="left">
						<B><FONT style="FONT-SIZE: 14px">支付结果:${chinaBankReceive.v_pstring}</FONT></B>
					</div>
				</TD>
			</TR>
			<TR>
				<TD vAlign=top align=middle>
					<div align="left">
						<B><FONT style="FONT-SIZE: 14px">是否成功:${chinaBankReceive.v_pstatus}</FONT></B>
					</div>
				</TD>
			</TR>
			
			<TR>
				<TD vAlign=top align=middle>
					<div align="left">
						<B><FONT style="FONT-SIZE: 14px">支付金额:${chinaBankReceive.v_amount}</FONT></B>
					</div>
				</TD>
			</TR>
			<TR>
				<TD vAlign=top align=middle>
					<div align="left">
						<B><FONT style="FONT-SIZE: 14px">支付币种:${chinaBankReceive.v_moneytype}</FONT></B>
					</div>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
</body>
</html>