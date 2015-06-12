$(function() {
			$('#subfrom').click(function() {
				if($('#remark').val() == ''){
					alert('备注不能为空')
				} else {
					$.ajax({
						url : '/system/amount/applyAmount',
						data : $('#finalForm').serialize(),
						cache : false,
						type : 'post',
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							alert('很遗憾，出异常了' + errorThrown);
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
									msg : '操作成功',
									timeout : 5000,
									showType : 'slide'
								});
								parent.$.modalDialog.handler.dialog("close");
								parent.$.modalDialog.openner_dataGrid.datagrid('reload');
							} else {
								parent.$.messager.show({
									title : '系统提示',
									msg : result.msg,
									timeout : 5000,
									showType : 'slide'
								});
								parent.$.modalDialog.handler.dialog("close");
							}
						}
					});
				}
				
			});

		})