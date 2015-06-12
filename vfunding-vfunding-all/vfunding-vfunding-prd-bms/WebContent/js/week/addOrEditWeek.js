/**
 * 添加或编辑短期理财计划
 * @author louchen 2014-12-3
 */
$(function() {
	/*
	$('#delete').click(function() {
		$('#imgBanner').hide();
		$('#imgBanner').removeAttr('src');
	});
	*/

	addoreditform = $('#addoreditform').form({
		url : '/system/week/addOrEditWeek',
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
				parent.$.modalDialog.openner_dataGrid.datagrid('reload');
				parent.$.modalDialog.handler.dialog('close');
				parent.$.messager.show({
					title : '系统提示',
					msg : '操作成功',
					timeout : 5000,
					showType : 'slide'
				});
			} else {
				//console.log(result);
				parent.$.modalDialog.openner_dataGrid.datagrid('reload');
				parent.$.modalDialog.handler.dialog('close');
				parent.$.messager.show({
					title : '系统提示',
					msg : '操作失败:'+result.msg,
					timeout : 5000,
					showType : 'slide'
				});
			}
		}
	});
	
	//页面加载时取消处理中的遮罩层
	parent.$.messager.progress('close');
});