$(function(){
	$(":radio[name='status'][value='1']").click(function(){
		$('#aliNum').show();
	});
	$(":radio[name='status'][value='0']").click(function(){
		$('#aliNum').hide();
	});
	
	$('#subfrom').click(function(){
		var checked_ = $("input[name='status']:checked").val();
		//2014-12-26 louchen
		if(checked_==null||checked_==""||checked_==undefined){
			alert("请选择审核通过或不通过")
			return false;
		}
		if($('#remark').val() == ''){
			alert('备注不能为空')
			$('#remark').focus();
		} else if($('#bankType').val()=='支付宝' && checked_==1 && $('#msg').val() == ''){
			alert('请输入支付宝充值流水号');
			$('#msg').focus();
		} else {
			var flag = false;
			if(checked_ == 1){
				if(confirm("确定审核该笔充值？")){
					flag = true;
				} else {
					flag = false;
				}
			} else {
				flag = true;
			}
			if(flag){
				$.ajax({
					url : '/system/funds/checkRechargeOffline',
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
						parent.$.messager.progress('close');
						result = $.parseJSON(result);
						if(result.success){
							parent.$.messager.show({
								title : '系统提示',
								msg : '操作成功',
								timeout : 5000,
								showType : 'slide'
							});
							parent.$.modalDialog.handler.dialog("close");
							parent.$.modalDialog.openner_dataGrid.datagrid('reload');
						} else {
							$('#dialog').dialog('close');
							parent.$.messager.show({
								title : '系统提示',
								msg : result.msg,
								timeout : 5000,
								showType : 'slide'
							});
						}
					}
				});
			}
		}
	});
	
})