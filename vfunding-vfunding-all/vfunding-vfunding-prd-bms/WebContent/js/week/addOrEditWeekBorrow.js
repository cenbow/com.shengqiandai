/**
 * 添加或编辑标的
 * 
 * @author louchen 2014-12-5
 */
$(function() {
	$("#subBtn").on("click", function() {
		if(!checkValid()){
			return false;
		}
		$.ajax({
			url : '/system/week/addOrEditWeekBorrow',
			data : $('#addoreditform').serialize(),
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
						msg : '操作成功',
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
		$.get("/system/week/removePicSession", function(json){
			  json = $.parseJSON(json);
			  if(json.success){
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog("close");
			  }
			});
	});
	
	function checkValid(){
		if($("#name").val()==""){
			alert("标的名称不能为空");
			$("#name").focus();
			return false;
		}
		if($("#ownerName").val()==""){
			alert("物主姓名不能为空");
			$("#ownerName").focus();
			return false;
		}
		return true;
	}
	
	
	
});