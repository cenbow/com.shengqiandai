var finalList;
	$(function() {
		parent.$.messager.progress('close');
		finalList = $('#list')
				.datagrid(
						{
							url : '/system/experience/waitRepayFeelBorrowListPage',
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
										field : 'repaymentAccount',
										title : '应还金额',
										align : 'center',
										formatter : function(value, row, index) {
											return value+"元";
										}
									},
									{
										field : 'interest',
										title : '应还利息',
										align : 'center',
										formatter : function(value, row, index) {
											return value+"元";
										}
									},
									{
										field : 'repaymentTimeStr',
										title : '应还时间',
										align : 'center'
									},
									{
										field : 'status',
										title : '操作',
										align : 'center',
										formatter : function(value, row, index) {
											if ($.canEdit) {
												return value == 40 ? '已还'
														: (value == 30 ? ("<a id='goFinal_"+row.id+"'>还款</a>")
																: 'error');
											} else {
												return "无权限";
											}
										}
									} ] ],
							toolbar : '#searchTool',
							onLoadSuccess : function() {
								parent.$.messager.progress('close');
								$('a[id^="goFinal_"]').click(function() {
									if(confirm('确定还款?')){
										var id = this.id.split('goFinal_')[1];
										$.ajax({
											url : "/system/experience/paymentFeelBorrow",
											type : "post",
											data : {
												"repaymentId" : id
											},
											success : function(result) {
												result = $.parseJSON(result);
												parent.$.messager.show({
													title : '系统提示',
													msg : result.msg,
													timeout : 5000,
													showType : 'slide'
												});
												queryDetail();
												finalList.openner_dataGrid.datagrid('reload');
												parent.$.modalDialog.handler.dialog("close");
											}
										});
										parent.$.messager.progress('close');
									} else {
										return false;
									}
									
								});
								parent.$.modalDialog.openner_dataGrid=finalList;

							}
						});

	})
	function queryDetail() {
		finalList.datagrid('load', {
			'username' : $('#userName').val(),
			'status' : $('#status').val()
		});
		parent.$.messager.progress('close');
	}