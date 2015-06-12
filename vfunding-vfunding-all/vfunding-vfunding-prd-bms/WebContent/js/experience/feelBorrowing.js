var finalList;
	$(function() {
		parent.$.messager.progress('close');
		finalList = $('#list')
				.datagrid(
						{
							url : '/system/experience/feelBorrowing',
							isField : "id",
							pagination : true,//显示分页
							pageSize : 15,//分页大小
							pageList : [ 10, 15, 20 ],//每页的个数
							checkOnSelect : false,
							selectOnCheck : false,
							singleSelect : true,
							fit : true,//自动补全
							fitColumns : true,
							border : false,
							queryParams : {'status' : $('#status').val()},
							columns : [ [
						            {
						            	field : 'id',
						            	title : '序号',
						            	align : 'center'
						            },
									{
										field : 'borrowName',
										title : '借款标题',
										align : 'center'
									},
									{
										field : 'capital',
										title : '借款金额',
										align : 'center',
										formatter : function(value, row, index) {
											return value+"元";
										}
									},
									{
										field : 'lateDays',
										title : '期限',
										align : 'center',
										formatter : function(value, row, index) {
											return value+"天";
										}
									},
									{
										field : 'repaymentYesaccount',
										title : '应还利息',
										align : 'center',
										formatter : function(value, row, index) {
											return value+"元";
										}
									},
									{
										field : 'repaymentTimeStr',
										title : '发布时间',
										align : 'center'
									},
									{
										field : 'status',
										title : '操作',
										align : 'center',
										formatter : function(value, row, index) {
											return value == 40 ? '已还' : (value == 30 ? '未还' : ((value == 10 ? '招标中' : 'error')));
										}
									} ] ],
							toolbar : '#searchTool',
							onLoadSuccess : function() {
								parent.$.messager.progress('close');
								parent.$.modalDialog.openner_dataGrid = finalList;
							}
						});

	})
	function queryDetail() {
		finalList.datagrid('load', {
			'username' : $('#userName').val()
		});
		parent.$.messager.progress('close');
	}
	/**
	 * 查询
	 * 
	 * @param value
	 * @param name
	 */
	function searchFeel(value, name) {
		finalList.datagrid('load', {
			'username' : value
		});
	}
	function addFeel() {
		parent.$.modalDialog({
			title : '添加体验标',
			width : 900,
			height : 400,
			href : '/system/experience/addFeelBorrowPage',
			buttons : [ {
				text : '添加',
				iconCls:'icon-save',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = finalList;
					var f = parent.$.modalDialog.handler.find('#addFeelform');
					f.submit();
				}
			} ]
		});
	}