/**
 * 投资人列表
 * @author louchen 2014-12-10
 */
var finalList;
var w_width=750;
var w_height=400;
	$(function() {
		finalList = $('#list').datagrid({
							url : '/system/week/weekTenderList?r='+Math.random(),
							queryParams : {
								'weekId' : $('#weekId').val(),
								'repaymentTime': $('#repaymentTime').val()
							},
							idField : "weekId",
							pagination : true,
							pageSize : 15,
							pageList : [ 10, 15, 20 ],
							checkOnSelect : false,
							selectOnCheck : false,
							singleSelect : true,
							fit : true,
							fitColumns : true,
							border : false,
							columns : [ [
									{
										field : 'username',
										title : '投资人',
										width : 60,
										align : 'left',
										formatter : function(value, row, index) {
											if(row.realname==undefined || row.realname==null){
												return value;
											}else{
												return row.realname;
											}
										}
									},	
									{	
										field : 'addTime',
										title : '认购时间',
										width : 100,
										align : 'left'
									},
									{	
										field : 'money',
										title : '认购金额(元)',
										width : 80,
										align : 'left'
									},
									{	
										field : 'realbuyShare',
										title : '认购份额(份)',
										width : 80,
										align : 'left'
									},
									{	
										field : 'repaymentTime',
										title : '到期时间',
										width : 80,
										align : 'left'
									},
									{	
										field : 'repaymentAccount',
										title : '待还金额',
										width : 80,
										align : 'left'
									}
									] ],
							toolbar : '#searchTool',
							onLoadSuccess : function() {
								parent.$.messager.progress('close');
								parent.$.modalDialog.openner_dataGrid = finalList;
							}
						});
				
	})

	function queryDetail() {
		finalList.datagrid('load', {
			'weekId' : $('#weekId').val(),
			'repaymentTime': $('#repaymentTime').val()
		});
		parent.$.messager.progress('close');
	}
	