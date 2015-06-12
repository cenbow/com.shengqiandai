<!-- 暂时没用 -->
$(function(){
	parent.$.messager.progress('close');
	$('#subfrom').click(function(){
		if($('#money').val() == ''){
			alert('金额不能为空')
		} else if($('#username').val() == ''){
			alert('用户名不能为空');
		} else if($('#remark').val() == ''){
			alert('备注不能为空');
		} else {
			$.ajax({
				url : '/system/funds/addRecharge',
				data : $('#rechargeForm').serialize(),
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
					$('#list').datagrid('load');
					result = $.parseJSON(result);
					if(result.success){
						$.messager.alert('提示','充值成功');
					} else {
						$.messager.alert('提示',result.msg);
					}
					parent.$.messager.progress('close');
				}
			});
			
		}
		
	});
	
	$('#fresh').click(function(){
		$("#rechargeCode")
		.attr({"src" : "/system/funds/getGenImage/68/32?id="
					+ Math.random() * 1000,
					"alt" : "看不清，换一张"
			});
	});
})