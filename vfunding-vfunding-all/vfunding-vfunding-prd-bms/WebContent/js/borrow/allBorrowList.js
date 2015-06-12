var finalList;
	$(function() {
		finalList = $('#list')
				.datagrid(
						{
							url : '/system/borrow/allBorrowSystemList',
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
							columns : [ [
									{
										field : 'borrowId',
										title : '借款编号',
										align : 'center'
									},
									{
										field : 'userName',
										title : '用户名',
										align : 'center'
									},
									{
										field : 'borrowName',
										title : '借款标题',
										align : 'left',
										formatter : function(value, row, index) {
											if (row.biaoType == 'fast') {
												return '<span style="color: red;">【抵押标】</span>'
														+ value;
											} else if (row.biaoType == 'xin') {
												return '<span style="color: red;">【信用标】</span>'
														+ value;
											} else if (row.biaoType == 'jin') {
												return '<span style="color: red;">【净值标】</span>'
														+ value;
											} else if (row.biaoType == 'lz') {
												return '<span style="color: red;">【流转标】</span>'
														+ value;
											} else if(row.biaoType == 'huodong'){
												return '<span style="color: red;">【活动标】</span>'
												+ value;
											}
										}
									},
									{
										field : 'account',
										title : '借款金额',
										align : 'center',
										formatter : function(value, row, index) {
											return value + "元";
										}
									},
									{
										field : 'aprStr',
										title : '净收益率',
										align : 'center',
										formatter : function(value, row, index) {
											return (row.status==0||row.status==2)?'-':value;
										}
									},
									{
										field : 'apr',
										title : '年利率',
										align : 'center',
										formatter : function(value, row, index) {
											return value + "%";
										}
									},
									{
										field : 'tenderTimes',
										title : '投标次数',
										align : 'center'
									},
									{
										field : 'timeLimit',
										title : '借款期限',
										align : 'center',
										formatter : function(value, row, index) {
											return value + "个月";
										}
									},
									{
										field : 'addtime',
										title : '发标时间',
										align : 'center'
									},
									{
										field : 'verifyTime',
										title : '初审时间',
										align : 'center',
										formatter : function(value, row, index) {
											return value=='1970-01-01 08:00:00'?'-':value;
										}
									},
									{
										field : 'status',
										title : '操作',
										align : 'center',
										formatter : function(value, row, index) {
											return value == 3 ? '复审成功'
													: (value == 5 ? '撤标'
															: (value == 1 ? '招标中'
																	: (value == 7?'流标'
																			:(value == 2?'初审不通过'
																					:(value == 5?'撤标':(value == 0?'等待初审':'error'))))));
										}
									} ] ],
							toolbar : '#searchTool',
							onLoadSuccess : function() {
								parent.$.messager.progress('close');
							}
						});
		
	})
	function queryDetail() {
		finalList.datagrid('load', {
			'status' : $('#status').val(),
			'startTime' : $('#startTime').val(),
			'endTime' : $('#endTime').val()
		});
	}