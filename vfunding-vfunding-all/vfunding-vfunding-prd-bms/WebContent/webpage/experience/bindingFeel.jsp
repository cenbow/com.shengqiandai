<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
/* $(function(){
	parent.$.messager.progress('close');
	$('#subfrom').click(function(){
		if($('#startNo').val() == ''){
			alert('卡号不能为空');
		} else if($('#endNo').val() == ''){
			alert('卡号不能为空');
		} else if($('#username').val() == ''){
			alert('用户名不能为空');
		} else {
			$.ajax({
				url : "/system/experience/batchInsertFeel",
				type : "post",
				data : $('#feelForm').serialize(),
				success : function(result) {
					result = $.parseJSON(result);
					$.messager.alert('提示', result.msg);
					if(result.success){
						$('#startNo,#endNo,#username').val('');
					}
				}
			});
		}
	});
}) */

var feelForm;
		parent.$.messager.progress('close');
	$(function() {
		feelForm = $('#feelForm').form({
			url : '/system/experience/batchInsertFeel',
			onSubmit : function(param) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				if($('#startNo').val()==''||$('#endNo').val()==''||$('#username').val()==''){
					alert('请填写完整');
					parent.$.messager.progress('close');
					return false;
				}
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog('close');
					parent.$.messager.show({
						title : '系统提示',
						msg : '操作成功',
						timeout : 5000,
						showType : 'slide'
					});
				} else {
					parent.$.messager.show({
						title : '系统提示',
						msg : result.msg,
						timeout : 5000,
						showType : 'slide'
					});
				}
			}
		});
		parent.$.messager.progress('close');
	});
</script>
<form id="feelForm" name="feelForm">
	<div style="text-align:center;margin-top:20px;">
		体验卡ID：<input type="text" name="startNo" id="startNo" style="width:100px;"/>
		到<input type="text" name="endNo" id="endNo" style="width:100px;"/>
		<br/>
		<br/>
		<select name="type" id="type" style="width:120px;">
			<option value="1">理财师用户</option>
			<option value="2">虚拟用户</option>
		</select>
		<input type="text" name="username" id="username"/>
	</div>
</form>