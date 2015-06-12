<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	var editForm;
	$(function() {
		editForm = $('#editForm').form({
			url : '/system/userEmp/editRemark',
			onSubmit : function(param) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.messager.show({
						title : '系统提示',
						msg : '操作成功',
						timeout : 5000,
						showType : 'slide'
					});
				} else {
					parent.$.messager.show({
						title : '系统提示',
						msg : '操作失败',
						timeout : 5000,
						showType : 'slide'
					});
				}
				parent.$.modalDialog.openner_dataGrid.datagrid('reload');
				parent.$.modalDialog.handler.dialog('close');
			}
		});
		parent.$.messager.progress('close');
	});
</script>
<div style="height:20px;"></div>
<form id="editForm" method="post">
<div>
备注:<textarea rows="5" cols="10" id="remark" name="remark" style="width: 318px; height: 124px;">${userEmp.remark}</textarea><br/>
跟踪:<textarea rows="5" cols="10" id="track" name="track" style="width: 318px; height: 124px;">${userEmp.track}</textarea>
<input type="hidden" name="id" value="${userEmp.id}"/>
<br/>
</div>
</form>