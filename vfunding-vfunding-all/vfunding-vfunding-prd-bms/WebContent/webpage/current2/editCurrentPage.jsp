<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	var editCurrentform;
	$(function() {
		parent.$.messager.progress('close');
		editCurrentform = $('#editCurrentform').form(
				{
					url : '/system/current/editCurrent',
					onSubmit : function(param) {
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
						parent.$.modalDialog.openner_dataGrid.datagrid('reload');
						parent.$.modalDialog.handler.dialog('close');
					}
				});
		parent.$.messager.progress('close');
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<form id="editCurrentform" method="post" style="text-align: center;">
			<input type="hidden" name="currentId" value="${current.currentId }"/>
			<table class="table table-hover table-condensed"
				style="text-align: center; padding: 10px; margin-left: 35px; margin-top: 15px;">
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">名称：</th>
					<td><input type="text" id="currentName" name="currentName"
						class="easyui-validatebox" value="${current.currentName}"
						data-options="required:true,validType:['String','length[0,15]']" /></td>
					<th style="vertical-align: middle; text-align: right;">总金额：</th>
					<td><input type="text" id="money" name="money" readonly="readonly"
						class="easyui-validatebox" value="${current.money }"
						data-options="required:true,validType:['String','length[0,15]']" /></td>
				</tr>
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">已借金额：</th>
					<td><input type="text" id="moneyYes" name="moneyYes" readonly="readonly"
						class="easyui-validatebox" value="${current.moneyYes}"
						data-options="required:true,validType:['String','length[0,15]']" /></td>
					<th style="vertical-align: middle; text-align: right;">最小投资额：</th>
					<td><input type="text" id="moneyMin" name="moneyMin"
						class="easyui-validatebox" value="${current.moneyMin}"
						data-options="required:true,validType:['String','length[0,15]']" /></td>
				</tr>
				<tr style="height: 15px;">
				</tr>
				<tr>
					<th style="vertical-align: middle; text-align: right;">最大投资额：</th>
					<td><input type="text" id="moneyMax" name="moneyMax"
						data-options="required:true" class="easyui-validatebox" value="${current.moneyMax}" /></td>
					<th style="vertical-align: middle; text-align: right;">单人最大投资额：</th>
					<td><input type="text" id="moneyMaxSingle" name="moneyMaxSingle"
						data-options="required:true" class="easyui-validatebox" value="${current.moneyMaxSingle}" /></td>
				</tr>
				<tr style="height: 15px;">
				</tr>
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">预备金总额：</th>
					<td><input type="text" id="moneyPreparation" name="moneyPreparation"
						data-options="required:true" class="easyui-validatebox" value="${current.moneyPreparation}" /></td>
					<th style="vertical-align: middle; text-align: right;">已用预备金：</th>
					<td><input type="text" name="moneyPreparationYes" id="moneyPreparationYes" readonly="readonly"
						data-options="required:true" value="${current.moneyPreparationYes}" /></td>
				</tr>
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">状态：</th>
					<td>
						<select name="status" id="status">
							<option value="0" ${current.status==0?'selected':''}>关闭</option>
							<option value="1" ${current.status==1?'selected':''}>开启</option>
						</select>
					</td>
				</tr>
				<tr style="height: 20px;">
				</tr>
			</table>
		</form>
	</div>
</div>
