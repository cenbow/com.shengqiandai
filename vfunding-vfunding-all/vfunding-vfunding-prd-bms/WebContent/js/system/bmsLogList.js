var result;
$(function() {
	result = $('#loglists').datagrid({
		url : '/system/log/bmsLogList',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		singleSelect : true,
		checkOnSelect : false,
		selectOnCheck : false,
		idField : 'id',
		pageSize : 15,
		pageList : [ 15, 30, 50, 100, 500 ],
		columns : [ [
				{
					field : 'id',
					checkbox:true
				},
				{
					field : 'operationDesc',
					title : '操作内容',
					align : 'center',
					width : 150
				},
				{
					field : 'success',
					title : '操作状态',
					align : 'center',
					width : 50,
					formatter : function(value, row, index) {
						return value=='Y'?'成功':'失败';
					}
				},
				{
					field : 'requestAddress',
					title : '操作路劲',
					align : 'center',
					width : 150
				},
				{
					field : 'operationTime',
					title : '操作时间',
					align : 'center',
					width : 100
				},
				{
					field : 'username',
					title : '操作人',
					align : 'center',
					width : 50
				}
				] ],
				toolbar : '#searchTool',
				onLoadSuccess : function() {
					parent.$.messager.progress('close');
				}
	});
});

function queryDetail() {
	result.datagrid('load', {
		'username' : $('#username').val(),
		'startTime' : $('#startTime').val(),
		'endTime' : $('#endTime').val(),
		'success' : $('#success').val()
	});
}

function deleteByIds(){
	var checkedItems = $('#loglists').datagrid('getChecked');
	if(checkedItems.length<=0){
		alert('请选择数据');
	} else {
		var ids = [];
		$.each(checkedItems, function(index, item){
			ids.push(item.id);
		});
		$.ajax({
			url : "/system/log/deleteByIds",
			data : {'ids':ids},
			type : "post",
			success : function(result) {
				result = $.parseJSON(result);
				parent.$.messager.show({
					title : '系统提示',
					msg : (result.success==true?'操作成功':result.msg),
					timeout : 5000,
					showType : 'slide'
				});
				queryDetail();
				parent.$.modalDialog.handler.dialog("close");
			}
		});
	}
	parent.$.messager.progress('close');
	
	
}
