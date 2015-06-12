parent.$.messager.progress('close');
$(function() {
	var button = $('#searchExcel'), interval;
	new AjaxUpload(
			button,
			{
				action : '/giftboxTiral/previewExcel',
				name : 'excelFile',
				data : {
					'name' : 'excelName'
				},
				onSubmit : function(file, ext) {
					if (!(ext && /^(xls|XLS|xlsx|XLSX)$/.test(ext))) {
						alert('Excel式不正确,请选择 xls或xlsx 格式的文件!', '系统提示');
						return false;
					}
					button.text('上传');
					this.disable();
					interval = window.setInterval(function() {
						var text = button.text();
						if (text.length < 10) {
							button.text(text + '...');
						} else {
							button.text('上传');
						}
					}, 200);
				},
				onComplete : function(file, data) {
					var html = "";
					var result = $.parseJSON(data);
					$("#excelName").val(result.excelName);
					arrayObj = result.excelData;
					var len = arrayObj.length
					for (var i = 0; i < len; i++) {
						html += "<tr>"
						for (var k = 0; k < arrayObj[i].length; k++) {
							html += "<td style='min-width:30px;border:1px black solid;'>"
									+ arrayObj[i][k] + "<td>";
						}
						html += "</tr>";
					}
					$("#showPerview").html(html);
					parent.$.messager.progress('close');
					button.text('浏览');
					window.clearInterval(interval);
					this.enable();
				}
			})

	$("#subBtn").on(
			"click",
			function() {
				// 提交数据前进行验证
				if (validation()) {
					// 提交数据
					$.ajax({
						url : '/giftboxTiral/importExcel',
						data : $('#subForm').serialize(),
						type : 'post',
						cache : false,
						error : function(XMLHttpRequest, textStatus,
								errorThrown) {
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
								$("#showPerview").html("");
								$("#title").val('');
								$("#excelName").val('');
							} else {
								parent.$.modalDialog.openner_dataGrid
										.datagrid('reload');
								parent.$.modalDialog.handler.dialog("close");
								parent.$.messager.show({
									title : '系统提示',
									msg : '操作失败:' + result.msg,
									timeout : 5000,
									showType : 'slide'
								});
							}
						}
					});
				}
			});
})

function showTemplateContent() {
	$("#disSpan").removeClass("disSpan").addClass("disSpan2");
	$("#templateContent").show();
	var tempHide = $("#templateSelect").val();
	$("font[name='gtContent']").hide();
	$("#" + tempHide).show();
}

function validation() {
	if ($("#title").val().trim() == "" || $("#title").val().trim() == null) {
		$.messager.alert('提示', '请填写红包名称!');
		return false;
	}
	if ($("#templateSelect").val() == "") {
		$.messager.alert('提示', '请选择模板!');
		return false;
	}
	if ($("#excelName").val() == "") {
		$.messager.alert('提示', '请选择Excel!');
		return false;
	}
	return true;
}

