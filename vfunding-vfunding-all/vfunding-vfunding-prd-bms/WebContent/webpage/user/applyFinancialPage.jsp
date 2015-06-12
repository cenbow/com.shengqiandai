<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	var applyform;
	$(function() {
		parent.$.messager.progress('close');
		applyform = $('#applyform').form({
			url : '/system/user/applyFinancial',
			data : $('#applyform').serialize(),
			onSubmit : function(param) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				if($('#remark').val() == '' || $('#remark').val() == null){
					alert('请填写备注');
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
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="applyform" method="post" style="text-align: center;">
			<table class="table table-hover table-condensed" style="text-align: center; padding: 10px; margin-top: 15px;">
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">审核备注：</th>
					<td><textarea rows="4" cols="4" style="margin: 0px 0px 10px; width: 321px; height: 93px;" name="remark" id="remark"></textarea></td>
				</tr>
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">&nbsp;</th>
					<td style="height:50px;vertical-align: middle;">
						<input type="hidden" name="id" id="id" value="${id}"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						通过：<input type="radio" name="status" checked="checked" value="1"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						不通过：<input type="radio" name="status" value="2"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
