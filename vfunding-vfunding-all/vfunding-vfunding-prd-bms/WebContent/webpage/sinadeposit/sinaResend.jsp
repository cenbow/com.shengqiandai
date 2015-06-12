<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>手动补单</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
</head>
<body>
<div style="margin-left:1%;margin-top:1%;width:40%">
<form action="" id="resendForm">
补单类型:
<select name="cpType">
	<option value="oneCollect">待收</option>
	<option value="onePay">代付</option>
</select><br />
补单Json:<textarea rows="5" style="width:80%" id="bdJsonAre"></textarea>
<input type="hidden" name="argJson" id="bdJsonVal"/>
</form>
<center>
<button id="subResendForm" style="margin:atuo;">补单</button>
</center>
</div>
<script type="text/javascript">
$(function(){
	parent.$.messager.progress('close');
	$("#subResendForm").on("click",function(){
		$("#bdJsonVal").val($("#bdJsonAre").val());
		$.ajax({
			url:"/sinaCP/cp",
			data:$("#resendForm").serialize(),
			async:true,
			cache:false,
			type:"post",
			success:function(data){
				json = $.parseJSON(data);
				showMsg("系统提示",json.msg);
			}
		});
	});
})
//右下角提示
function showMsg(title,msg){
	parent.$.messager.show({
		title : title,
		msg : msg,
		timeout : 5000,
		showType : 'slide'
	});
}
</script>
</body>
</html>