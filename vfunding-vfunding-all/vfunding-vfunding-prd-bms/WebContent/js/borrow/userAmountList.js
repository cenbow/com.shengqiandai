var finalList;
	$(function() {
		finalList = $('#list')
				.datagrid(
						{
							url : '/system/amount/amountApplyList',
							isField : "id",
							pagination : true,//显示分页
							pageSize : 15,//分页大小
							pageList : [ 10, 15, 20 ],//每页的个数
							fit : true,//自动补全
							fitColumns : true,
							queryParams : {'status' : $('#status').val()},
							columns : [ [
									{
										field : 'id',
										title : 'ID',
										align : 'center'
									},
									{
										field : 'verifyRemark',
										title : '用户名称',
										align : 'center'
									},
									{
										field : 'phone',
										title : '手机号',
										align : 'center'
									},
									{
										field : 'type',
										title : '申请类型',
										align : 'center',
										formatter : function(value, row, index) {
											return "借款额度";
										}
									},
									{
										field : 'accountOld',
										title : '原来额度',
										align : 'center',
										formatter : function(value, row, index) {
											return value + "元";
										}
									},
									{
										field : 'account',
										title : '申请额度',
										align : 'center',
										formatter : function(value, row, index) {
											return value + "元";
										}
									},
									{
										field : 'accountNew',
										title : '新额度',
										align : 'center',
										formatter : function(value, row, index) {
											return value + "元";
										}
									},
									{
										field : 'addtime',
										title : '申请时间',
										align : 'center'
									},
									{
										field : 'content',
										title : '内容',
										align : 'center'
									},
									{
										field : 'limit',
										title : '借款期限',
										align : 'center',
										formatter : function(value, row, index) {
											return value + "个月";
										}
									},
									{
										field : 'mortgagetypeId',
										title : '抵押品种',
										align : 'center',
										formatter : function(value, row, index) {
											return value==1?'汽车':(value==3?'房产抵押':'error');
										}
									},
									{
										field : 'status',
										title : '操作',
										align : 'center',
										formatter : function(value, row, index) {
											if ($.canEdit) {
												return value == 0 ? '审核失败'
														: (value == 1 ? '审核成功'
																: (value == 2 ? ("<a id='goFinal_"+row.id+"'>待审</a>")
																		: 'error'));
											} else {
												return "-";
											}
											
											
										}
									} ] ],
							toolbar : '#searchTool',
							onLoadSuccess : function() {
								parent.$.messager.progress('close');
								$('a[id^="goFinal_"]').click(function() {
													var id = this.id
															.split('goFinal_')[1];
													var dialog_ = parent.$
															.modalDialog({
																title : '详情',
																width : 1000,
																height : 500,
																href : '/system/amount/amountDetail?id='
																		+ id
															});
													parent.$.messager
															.progress('close');
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
	}