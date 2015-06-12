var finalList;
$(function() {
	finalList = $('#list')
			.datagrid(
					{
						url : '/system/funds/applyCashList',
						fit : true,
						isField : "id",
						pagination : true,// 显示分页
						pageSize : 15,// 分页大小
						pageList : [ 10, 15, 20 ],// 每页的个数
						singleSelect : true,
						queryParams : {
							'status' : $('#status').val()
						},
						frozenColumns : [ [
								{
									field : 'cashId',
									title : '编号',
									width : 30,
									align : 'center'
								},
								{
									field : 'userName',
									title : '用户名称',
									width : 100,
									align : 'center'
								},
								{
									field : 'realName',
									title : '真实姓名',
									width : 100,
									align : 'center'
								},
								{
									field : 'bankNum',
									title : '提现账号',
									width : 150,
									align : 'center'
								},
								{
									field : 'bankName',
									title : '提现银行',
									width : 150,
									align : 'center'
								}
							]],
						columns : [ [
								{
									field : 'branch',
									title : '支行',
									align : 'center'
								},
								{
									field : 'money',
									title : '提现总额',
									align : 'center',
									formatter : function(value, row, index) {
										return value + "元";
									}
								},
								{
									field : 'account',
									title : '到账金额',
									align : 'center',
									formatter : function(value, row, index) {
										return value + "元";
									}
								},
								{
									field : 'fee',
									title : '手续费',
									align : 'center',
									formatter : function(value, row, index) {
										return value + "元";
									}
								},
								{
									field : 'useHongbao',
									title : '红包抵扣',
									align : 'center',
									formatter : function(value, row, index) {
										return value + "元";
									}
								},
								{
									field : 'addtime',
									title : '提现时间',
									align : 'center'
								},
								{
									field : 'typeId',
									title : '用户类型',
									align : 'center',
									formatter : function(value, row, index) {
										return value == 40 ? '<span style="color:red;">借款用户</span>'
												: '投资账户';
									}
								},
								{
									field : 'bankcashNo',
									title : '是否打款',
									align : 'center',
									formatter : function(value, row, index) {
										if (value != null && value.length > 0) {
											if ($.canUpdate) {
												return '<a  href="javaScript:addBankNumber('
												+ row.cashId + ')">已打款</a>'
											} else {
												return '<a>已打款</a>';
											}
										} else {
											if ($.canAdd) {
												return '<a style="color: red;" href="javaScript:addBankNumber('
												+ row.cashId + ')">未打款</a>'
											} else {
												return "<a>未打款</a>";
											}
										}
									}
								},
								{
									field : 'bankcashTime',
									title : '打款时间',
									align : 'center',
									formatter : function(value, row, index) {
										if (value != null && value.length > 0) {
											return value.substr(0, 19) ;
										} else {
											return "------";
										}
									}

								},
								{
									field : 'status',
									title : '操作',
									align : 'center',
									formatter : function(value, row, index) {
										var result = "";
										if(value==0){
											if ($.canEdit) {
												result = '<a id="goApply_' + row.cashId + '">审核</a>';
											}else{
												result = '需审核';
											}											
										}else if(value==1){
											result = '处理成功';
										}else if(value==2){
											result = '处理失败';
										}else if(value==3){
											result = '用户取消申请';
										}else if(value==4){
											result = '新浪待打款';
										}else if(value==5){
											result = '处理中';
										}else{
											result = 'error';
										}
										return result;
									}
								} ] ],
						toolbar : '#searchTool',
						onLoadSuccess : function() {
							parent.$.messager.progress('close');
							$('a[id^="goApply_"]')
									.click(
											function() {
												var id = this.id.split('_')[1];
												var userName = this.id
														.split('_')[2];
												var cashdialog_ = parent.$
														.modalDialog({
															title : '详情',
															width : 1000,
															height : 500,
															href : '/system/funds/cashDetail?id='
																	+ id
														});
												parent.$.messager
														.progress('close');
												parent.$.modalDialog.openner_dataGrid = finalList;
											});
						}
					});
})
function queryDetail() {
	finalList.datagrid('load', {
		'username' : $('#userName').val(),
		'keyWord' : $('#bankNum').val(),
		'type' : $('#type').val(),
		'startTime' : $('#startTime').val(),
		'endTime' : $('#endTime').val(),
		'status' : $('#status').val()
	});
}
function addBankNumber(cashId) {
	parent.$.modalDialog({
		title : '详情',
		width : 1000,
		height : 500,
		href : '/system/funds/cashNumberDetail?id=' + cashId
	});
	parent.$.messager.progress('close');
	parent.$.modalDialog.openner_dataGrid = finalList;
}

function updateBankcashNo() {
	if ($('#bankcashNo').val() == '') {
		alert('银行单号不能为空');
	} else {
		$.ajax({
			url : '/system/funds/updateBankNumber',
			data : $('#cashForm').serialize(),
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
					$.messager.alert('提示', '操作成功');
					parent.$.modalDialog.handler.dialog("close");
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
				} else {
					$.messager.alert('提示', '操作失败');
					parent.$.modalDialog.handler.dialog("close");
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
				}
				parent.$.messager.progress('close');
			}
		});
	}
}
function addBankcashNo() {
	if ($('#bankcashNo').val() == '') {
		alert('银行单号不能为空');
	} else {
		$.ajax({
			url : '/system/funds/addBankNumber',
			data : $('#cashForm').serialize(),
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
					$.messager.alert('提示', '操作成功');
					parent.$.modalDialog.handler.dialog("close");
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
				} else {
					$.messager.alert('提示', '操作失败');
					parent.$.modalDialog.handler.dialog("close");
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
				}
				parent.$.messager.progress('close');
			}
		});
	}

}
