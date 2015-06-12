$(function(){
	$('#subfrom').click(function(){
		//2014-12-26 louchen
		var status_ = $('input[name=status]:checked').val();
		if(status_==null||status_==""||status_==undefined){
			alert("请选择审核通过或不通过")
			return false;
		}
		if($('#remark').val() == ''){
			alert('备注不能为空');
		} else {
			$.ajax({
				url : '/system/funds/takeCash',
				data : $('#cashForm').serialize(),
				cache : false,
				type : 'post',
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert('很遗憾，出异常了' + errorThrown);
				},
				beforeSend:function(){
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
				},
				success : function(result) {
					parent.$.messager.progress('close');
					result = $.parseJSON(result);
					if(result.success){
						parent.$.messager.show({
							title : '系统提示',
							msg : '操作成功',
							timeout : 5000,
							showType : 'slide'
						});
					} else {
						$.messager.alert('系统提示', result.msg);
						
					}
					parent.$.modalDialog.handler.dialog("close");
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
				}
			});
		} 
			
	});
	
})