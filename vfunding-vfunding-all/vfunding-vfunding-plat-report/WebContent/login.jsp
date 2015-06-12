<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>

</head>
<body>
	<form action="/login" method="post" id="form">
		username:<input type="text" name="loginName" value="admin">
		password:<input type="password" name="password" value="123456">
		<input type="radio" name="postId" value="1" checked="checked" />业务员 <input
			type="radio" name="postId" value="3" />部门领导 <input type="radio"
			name="postId" value="4" />人事 <input type="radio" name="postId"
			value="59" />财务 <input type="radio" name="postId" value="60" />财务主管

		<input type="radio" name="postId" value="64" />评估员 <input
			type="radio" name="postId" value="63" />风控 <input type="radio"
			name="postId" value="65" />谈判 <input type="radio" name="postId"
			value="66" />业务经理 <input type="radio" name="postId" value="67" />接单员
		<input type="radio" name="postId" value="68" />签约员 <input
			type="radio" name="postId" value="69" />提车员 <input type="radio"
			name="postId" value="70" />车库管理员 <input type="submit" value="登陆">
	</form>
</body>
</html>