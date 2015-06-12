$(function(){
	parent.$.messager.progress('close');
	$('#subfrom').click(function(){
		if($('#name').val() == ''){
			alert('金额不能为空')
		} else if($('#username').val() == ''){
			alert('发标人不能为空')
		} else if($('#account').val() == ''){
			alert('用户名不能为空');
		} else if($('#timeLimit').val() == ''){
			alert('备注不能为空');
		} else if($('#apr').val() == ''){
			alert('备注不能为空');
		} else if($('#lowestAccount').val() == ''){
			alert('备注不能为空');
		} else if($('#content').val() == ''){
			alert('备注不能为空');
		} else {
			$.ajax({
				url : '/system/experience/addFeelBorrow',
				data : $('#feelForm').serialize(),
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
					$('#list').datagrid('load');
					result = $.parseJSON(result);
					if(result.success){
						$.messager.alert('提示','发布成功');
					} else {
						$.messager.alert('提示',result.msg);
					}
					parent.$.messager.progress('close');
				}
			});
			
		}
		
	});
})