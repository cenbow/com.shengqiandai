<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="${pageContext.request.contextPath }/inc.jsp"></jsp:include>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link rel="stylesheet" type="text/css" href="style/login.css" />
<script type="text/javascript">
	var loginform;
	$(function() {
		loginform = $('#form').form({
							url : '${pageContext.request.contextPath }/login',
							onSubmit : function() {
								var isValid = $(this).form('validate');
								return isValid;
							},
							success : function(result) {
								result = $.parseJSON(result);
								if (result.success) {
									window.location.href = "${pageContext.request.contextPath }/";
								} else {
									$.messager.alert('警告',result.msg);
								}
							}
						});

		$('#loginName').blur(function() {
			$('#errorMsg').html('&nbsp;');
		});

		$('#password').blur(function() {
			$('#errorMsg').html('&nbsp;');
		});

	});

	function doLogin() {
		loginform.submit();
	}
</script>
</head>
<body
	style="background: url('/style/images/login/loginBackground.jpg'); background-size: cover;">
	<div id="dlg" class="easyui-dialog login-border" title="登录"
		style="width: 380px; height: 290px; padding: 10px">
		<form action="" method="post" id="form">
			<TABLE border=0 cellSpacing=0 cellPadding=0 width=280>
				<tr>
					<TD width="30%" style="text-align: right;"></TD>
					<td>&nbsp;</td>
				</tr>
				<TR>
					<TD width="30%" style="text-align: right;"><label>帐号：</label></TD>
					<TD width="63%" colspan="2">
						<!-- <INPUT type="text" style="WIDTH: 200px" id=loginName name="loginName" value="admin" > -->
						<input type="text" name="loginName" value="admin" id="loginName"
						class="easyui-validatebox login-user" style="height: 20px;"
						missingMessage="请输入用户名" data-options="required:true">
					</TD>
				</TR>
				<tr>
					<TD width="30%" style="text-align: right;"></TD>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<TD width="30%" style="text-align: right;"></TD>
					<td>&nbsp;</td>
				</tr>

				<TR>
					<TD width="30%" style="text-align: right;"><label>密码：</label></TD>
					<TD colspan="2">
						<!-- <INPUT type="password" name="password" value="123456" style="WIDTH: 200px" id=password size=25> -->
						<input type="password" name="password" id="password"
						class="easyui-validatebox login-password" style="height: 20px;"
						missingMessage="请输入密码" data-options="required:true">
					</TD>
				</TR>

				<tr>
					<TD width="30%" style="text-align: right;"></TD>
					<td>&nbsp;</td>
				</tr>
				
			
				<tr>

					<TD colspan="3" style="bactext-align: center;">
						<center>
							<input value="" type="button" onclick="doLogin();"
								class="login-button" style="width: 80px; height: 35px;">
						</center>
					</TD>
				</tr>

				

			</TABLE>
		</form>
	</div>


</body>
</html>