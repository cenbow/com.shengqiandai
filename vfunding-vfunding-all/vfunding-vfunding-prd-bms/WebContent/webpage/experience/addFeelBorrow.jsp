<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	var addFeelform;
	$(function() {
		parent.$.messager.progress('close');
		addFeelform = $('#addFeelform').form({
			url : '/system/experience/addFeelBorrow',
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
	function saveEmp() {
		addFeelform.submit();
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="addFeelform" method="post" style="text-align: center;">
			<table class="table table-hover table-condensed"
				style="text-align: center; padding: 10px; margin-left: 35px; margin-top: 15px;">
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">体验标标题：</th>
					<td><input type="text" id="name" name="name"
						data-options="required:true,validType:['String','length[0,32]']"
						class="easyui-validatebox"></td>

					<th style="vertical-align: middle; text-align: right;">借款总额：</th>
					<td><input type="text" id="account" name="account"
						class="easyui-validatebox"
						data-options="required:true,validType:['String','length[0,32]']"/></td>
				</tr>
				<tr style="height: 15px;">
				</tr>
				<tr>
					<th style="vertical-align: middle; text-align: right;">借款期限：</th>
					<td><input type="text" id="timeLimitDay" name="timeLimitDay" placeholder="请输入借款期限/天"
						data-options="required:true"
						class="easyui-validatebox" /></td>
					<th style="vertical-align: middle; text-align: right;">年利率：</th>
					<td><input type="text" id="apr" name="apr"
						class="easyui-validatebox"
						data-options="required:true,validType:['String','length[0,32]']" /></td>
				</tr>
				<tr style="height: 15px;">
				</tr>
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">最小投标金额：</th>
					<td><input type="text" id="lowestAccount" name="lowestAccount"
						data-options="required:true,validType:['Integer','length[0,11]']"
						class="easyui-validatebox" /></td>

					<th style="vertical-align: middle; text-align: right;">标的内容：</th>
					<td><input type="text" id="content" name="content"  class="easyui-validatebox"/></td>
				</tr>
				<tr style="height: 15px;">
				</tr>
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">发标人：</th>
					<td><input type="text" name="username" id="username" value="财富助手"/></td>
				</tr>
				<tr style="height: 15px;">
				</tr>
			</table>
		</form>
	</div>
</div>
