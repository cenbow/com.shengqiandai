<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript">
    var roleaddoreditform;
	$(function() {
		
		roleaddoreditform=$('#roleaddoreditform').form({
			url : '/role/addOrEdit',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
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
						title:'系统提示',
						msg:'操作成功',
						timeout:5000,
						showType:'slide'
					});
				}
			}
		});
		parent.$.messager.progress('close');
	});
	
	function saveRole(){
		roleaddoreditform.submit();
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="roleaddoreditform" method="post" style="text-align: center;">
			<table class="table table-hover table-condensed"
				style="text-align: center;margin-top: 10px;">
				<tr style="text-align: center;">
					<th style=" text-align: center;">角色名称</th>
					<td style="vertical-align: middle;"><input name="roleName" type="text" placeholder="请输入角色名称"
						class="easyui-validatebox" data-options="required:true"
						value="${role.roleName}"
						style="vertical-align: middle;  margin-left: 10px;" /> <input
						type="hidden" name="roleId" readonly="readonly" value="${role.roleId }">
						<input
						type="hidden" name="roleState" readonly="readonly" value="${role.roleState }">
						</td>
				</tr>
				
			</table>
		</form>
	</div>
</div>