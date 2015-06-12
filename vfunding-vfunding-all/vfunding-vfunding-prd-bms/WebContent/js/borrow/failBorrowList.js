var finalList;
	$(function() {
		finalList = $('#list_')
				.datagrid(
						{
							url : '/system/borrow/failBorrowList',
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
											return value + "%";
										}
									},
									{
										field : 'apr',
										title : '借款利率',
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
										align : 'center'
									},
									{
										field : 'status',
										title : '操作',
										align : 'center',
										formatter : function(value, row, index) {
											if ($.canEdit) {
												return value == 1 ? "<a id='goFinal_"+row.borrowId+"'>撤标</a>"
														: (value == 5 ? '撤标成功':'error');
											} else {
												return "-";
											}
										}
									} ] ],
							toolbar : '#searchTool',
							onLoadSuccess : function() {
								parent.$.messager.progress('close');
								$('a[id^="goFinal_"]').click(function() {
										if(confirm('确定撤标，撤标将不能恢复.')){
											var id = this.id.split('goFinal_')[1];
											$.ajax({
												url : "/system/borrow/failBorrow",
												type : "post",
												data : {
													"id" : id
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
												}
											});
											parent.$.messager.progress('close');
										} else {
											return false;
										}
								});
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