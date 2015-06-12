var finalList;
	$(function() {
		finalList = $('#list')
				.datagrid(
						{
							url : '/system/borrow/finalBorrowList',
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
										width : 20,
										align : 'center'
									},
									{
										field : 'borrowName',
										title : '借款标题',
										width : 70,
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
										width : 30,
										align : 'center',
										formatter : function(value, row, index) {
											return value + "元";
										}
									},
									{
										field : 'aprStr',
										title : '净收益率',
										width : 30,
										align : 'center',
										formatter : function(value, row, index) {
											return value + "%";
										}
									},
									{
										field : 'apr',
										title : '年利率',
										width : 20,
										align : 'center',
										formatter : function(value, row, index) {
											return value + "%";
										}
									},
									{
										field : 'tenderTimes',
										title : '投标次数',
										width : 20,
										align : 'center'
									},
									{
										field : 'timeLimit',
										title : '借款期限',
										width : 20,
										align : 'center',
										formatter : function(value, row, index) {
											return value + "个月";
										}
									},
									{
										field : 'mortgageTypeid',
										title : '抵押类型',
										width : 45,
										align : 'center',
										formatter : function(value, row, index) {
											if (value == 1) {
												return "汽车抵押";
											} else if (value == 2) {
												return "债权抵押";
											}
										}
									},
									{
										field : 'addtime',
										title : '发标时间',
										width : 45,
										align : 'center'
									},
									{
										field : 'verifyTime',
										title : '初审时间',
										width : 45,
										align : 'center'
									},
									{
										field : 'status',
										title : '操作',
										align : 'center',
										formatter : function(value, row, index) {
											var result = "";
											if ($.canEdit) {
												if(value==3){
													result += "【复审成功】";
												}else if(value==4){
													result += "【复审失败】";
												}else if(value==1){
													result += "<a id='goFinal_"+row.borrowId+"'>【满标复审中】</a>";
												}else{
													result += "error";
												}
											} else {
												var result = "无复审权限";
											}
											result += "<a href='javascript:agreementLink("+row.borrowId+");'>【查看借款协议】</a>";
											return result;
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
																href : '/system/borrow/borrowDetail?id='
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
			'status' : $('#status').val(),
			'biaoType' : $('#biaoType').val()
		});
		parent.$.messager.progress('close');
	}
	
	function agreementLink(id){
		var url = "/borrow/agreement?borrowId="+id;
		var newWindow = window.open();
		newWindow.location.href = url;
	}