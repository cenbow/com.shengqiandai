<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	var addCurrentform;
	$(function() {
		parent.$.messager.progress('close');
		addCurrentform = $('#addCurrentform').form(
				{
					url : '/system/current/addCurrent',
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
						parent.$.modalDialog.openner_dataGrid
								.datagrid('reload');
						parent.$.modalDialog.handler.dialog('close');
					}
				});
		parent.$.messager.progress('close');
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="addCurrentform" method="post" style="text-align: center;">
			<input type="hidden" name="limitMoney" id="limitMoney" value="${money}" />
			<table class="table table-hover table-condensed"
				style="text-align: center; padding: 10px; margin-left: 35px; margin-top: 15px;">
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">类型：</th>
					<td><select name="type" id="type">
							<option value="1">增池</option>
					</select></td>
				</tr>
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">金额：</th>
					<td><input type="text" id="money" name="money"
						class="easyui-validatebox" value=""
						data-options="required:true,validType:['String','length[0,15]']" /></td>
				</tr>
				<tr style="height: 15px;">
				</tr>
				<tr>
					<th style="vertical-align: middle; text-align: right;">活期宝编号：</th>
					<td><input type="text" id="currentId" name="currentId"
						data-options="required:true" class="easyui-validatebox" value="1" /></td>
				</tr>
				<tr style="height: 15px;">
				</tr>
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">时间：</th>
					<td><input type="text" name="updateTime" id="updateTime"
						data-options="required:true" readonly="readonly"
						onClick="WdatePicker()" size="10"
						style="width: 205px; cursor: pointer;background-color:none;" /></td>
				</tr>
				<tr style="height: 20px;">
				</tr>
			</table>
		</form>
	</div>
</div>
