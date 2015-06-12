/**
 * 还款详情
 * 
 * @author louchen 2014-12-15
 */
$(function() {
	$("#subBtn").on("click", function() {
		if(!checkValid()){
			return false;
		}
		$.ajax({
			url : '/system/week/repayment',
			data : {
				"repaymentId":$("#repaymentId").val(),
				"repaymentTime":$("#repaymentTime").val(),
				"repaymentUser":$("#repaymentUser").val(),
			},
			type : 'post',
			cache : false,
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert('异常原因:' + errorThrown);
				parent.$.messager.progress('close');
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
					parent.$.messager.show({
						title : '系统提示',
						msg : '操作成功'+result.msg,
						timeout : 5000,
						showType : 'slide'
					});
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog("close");
				} else {
					parent.$.messager.show({
						title : '系统提示',
						msg : '操作失败:'+result.msg,
						timeout : 5000,
						showType : 'slide'
					});
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog("close");
				}
			}
		});
		
		
		
	});

	$("#clsBtn").on("click", function() {
		parent.$.modalDialog.openner_dataGrid.datagrid('reload');
		parent.$.modalDialog.handler.dialog("close");
	});
	
	function checkValid(){
		return true;
	}	
});