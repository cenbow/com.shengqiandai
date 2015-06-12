/**
 * 还款列表
 * 
 */
var finalList;
var w_width=750;
var w_height=400;
	$(function() {
		finalList = $('#list').datagrid({
							url : '/system/week/repaymentRecordList?r='+Math.random(),
							idField : "id",
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
										field : 'weekId',
										title : '计划编号',
										width : 80,
										align : 'center'
									},
									{
										field : 'weekName',
										title : '计划标题',
										width : 100,
										align : 'left'
									},	
									{	
										field : 'amountMoney',
										title : '总金额',
										width : 80,
										align : 'left'
									},
									{	
										field : 'repaymentAccount',
										title : '还款金额',
										width : 80,
										align : 'left'
									},
									{	
										field : 'repaymentYestime',
										title : '还款时间',
										width : 80,
										align : 'left'
									},
									{	
										field : 'holderUserName',
										title : '资金入账账户',
										width : 80,
										align : 'left'
									},
									{	
										field : 'repaymentUserName',
										title : '付款账户',
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
			'name' : $('#name').val(),
			'time': $('#time').val()
		});
		parent.$.messager.progress('close');
	}
	

