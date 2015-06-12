<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
</head>
<body>
<body class="easyui-layout" data-options="fit : true,border : false">
	<div style="text-align: center;margin-top:7%;">
	<form id="subForm">
		<input type="hidden" name="id" value="${template.id}">
		<span>模板名称：<input style="width: 80%" type="text" id="name" name="name" value="${template.name }" /></span>
		<span>模板内容：<textarea rows="3" cols="60" style="width: 80%" id="contentArea">${template.content }</textarea><input type="hidden" id="content" name="content" value="" /><br>
		<font color="blue">模板内容中需要设置的参数位置用  %s 进行标记，如果模板内容中有‘%’需要显示请写为‘%%’<br />
							例如:  '10%'则写为'10%%'</font>
		</span>
		<span>模板参数：<input style="width: 80%" type="text" id="params" name="params" value="${template.params }" /><br>
		<font color="blur">模板内容中所需的参数名,以 "," 逗号分隔(英文逗号)<br>
		例如:  金额,标的名称,备注</font>
		</span>
		<span>模板状态：<select id="status" name="status">
			<option value="0">启用</option>
			<option value="1">停用</option>
		</select></span>
		<input type="button" id="subBtn" value="提交">
	</form>
	</div>
	<script type="text/javascript">
		function validation(){
			if($("#name").val() == ""){
				$.messager.alert('提示', '请添填写模板名称!');
				return false;
			}
			if($("#content").val() == ""){
				$.messager.alert('提示', '请添填写模板内容!');
				return false;
			}
		}
		$(function(){
			$("#subBtn").on("click", function() {
				$("#content").val($("#contentArea").val());
				//提交数据
				$.ajax({
					url : '/giftboxTemplate/templateAddOrEdit',
					data : $('#subForm').serialize(),
					type : 'post',
					cache : false,
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert('很遗憾，出异常了' + errorThrown);
					},
					beforeSend : function() {
						parent.$.messager.progress({
							title : '提示',
							text : '数据处理中，请稍后....'
						});
					},
					success : function(result) {
						parent.$.messager.progress('close');
						result = $.parseJSON(result);
						if (result.success) {
							parent.$.modalDialog.openner_dataGrid.datagrid('reload');
							parent.$.modalDialog.handler.dialog("close");
							parent.$.messager.show({
								title : '系统提示',
								msg : '操作成功',
								timeout : 5000,
								showType : 'slide'
							});
						} else {
							parent.$.modalDialog.openner_dataGrid.datagrid('reload');
							parent.$.modalDialog.handler.dialog("close");
							parent.$.messager.show({
								title : '系统提示',
								msg : '操作失败:'+result.msg,
								timeout : 5000,
								showType : 'slide'
							});
						}
					}
				});
			});
		})
	</script>
	<style>
		body {
			text-align: center;
		}
		span {
			display:block;
			width:100%;
			margin-top:2%;
		}
	</style>
</body>
</html>