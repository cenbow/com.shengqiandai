<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>	
<jsp:include page="${pageContext.request.contextPath }/inc.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="style/login.css" /> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body
	style="background: url('/style/images/login/loginBackground.jpg'); background-size: cover;">
	<div id="dlg" class="easyui-dialog login-border" title=" "
		style="width: 330px; height: 245px; padding: 10px">
		<form action="${pageContext.request.contextPath }/login" method="post"
			id="form">
			<TABLE border=0 cellSpacing=0 cellPadding=0 width=280>
				<TR>
					<TD width="30%" style="text-align: right;"><label>帐号：</label></TD>
					<TD width="63%" colspan="2">
						<!-- <INPUT type="text" style="WIDTH: 200px" id=loginName name="loginName" value="admin" > -->
						<input type="text" name="loginName" value="admin" id="loginName" class="easyui-validatebox login-user" missingMessage="请输入用户名" data-options="required:true" "icon":"icon-users" onBlur="getPostByEmpName()" onmouseout="getPostByEmpName()">
					</TD>
				</TR>

				<TR>
					<TD width="30%" style="text-align: right;"><label>密码：</label></TD>
					<TD colspan="2">
						<!-- <INPUT type="password" name="password" value="123456" style="WIDTH: 200px" id=password size=25> -->
						<input type="password" name="password" value="123456" class="easyui-validatebox login-password" missingMessage="请输入密码" data-options="required:true">
					</TD>
				</TR>

				<tr>
					
					<TD width="30%" style="text-align: right;"><label>职位：</label></TD>
					<TD colspan="2">  
						<select class="easyui-combobox" panelHeight='auto' name="postId" id="post" style="height: 30px" 
						 data-options="editable:false,valueField:'postId',textField:'postName'">
					 	</select>
					 </TD> 
				</tr>
					 
					 
					 
					 <!-- <input
						type="radio" checked="checked" name="postId" value="1" />业务员 <input
						type="radio" checked="checked" name="postId" value="3" />部门领导 <input
						type="radio" checked="checked" name="postId" value="4" />人事
						&nbsp;&nbsp; &nbsp; &nbsp; -->
					    <tr><td> &nbsp;</td></tr>
						<tr>
						
						<TD colspan="3" style="bactext-align: center;">
							<center>
								<input value=" " type="submit" class="login-button"
								style="width: 80px; height: 35px;">
						</center>
					</TD></tr>

				


			</TABLE>
		</form>
	</div>
	<script type="text/javascript">	
		$(document).ready(function(){
			getPostByEmpName();
			$(".panel-title").addClass("login-border");
			$(".window-header").addClass("login-border");
		});
	
		function getPostByEmpName(){
			try{
				var value=$("#loginName").val();
				if(value != null && value != '' && value !=undefined){
					var url = formatURL("post/empName?userName="+value);
					$('#post').combobox('reload', url);	
				}
				
			}catch(e){
				_errorPrompt(0,"此用户没有职位信息");
			}
			
		}
	</script>

</body>
</html>