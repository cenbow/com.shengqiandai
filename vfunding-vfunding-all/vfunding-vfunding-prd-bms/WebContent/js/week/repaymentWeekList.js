/**
 * 还款列表
 * @author louchen 2014-12-9
 */
var finalList;
var w_width=750;
var w_height=400;
	$(function() {
		
		finalList = $('#list').datagrid({
							url : '/system/week/repaymentWeekList?r='+Math.random(),
							queryParams : {
								'status' : $('#status').val()
								,'weekId' : $('#weekId').val()
								,'startTime' : $('#startTime').val()
								,'endTime' : $('#endTime').val()
							},
							idField : "repaymentId",
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
										field : 'timeLimit',
										title : '期限(天)',
										width : 80,
										align : 'left'
									},
									{	
										field : 'apr',
										title : '年化利率(%)',
										width : 80,
										align : 'left'
									},
	
									{	
										field : 'repaymentTime',
										title : '还款到期时间',
										width : 80,
										align : 'left'
									},
									{	
										field : 'capital',
										title : '本金(元)',
										width : 80,
										align : 'left'
									},
									{	
										field : 'interest',
										title : '利息(元)',
										width : 80,
										align : 'left'
									},
									{	
										field : 'repaymentAccount',
										title : '待还款金额(元)',
										width : 80,
										align : 'left'
									},
									{
										field : 'status',
										title : '还款操作',
										width : 100,
										align : 'center',
										formatter : function(value, row, index) {
											return value == 0 ? "<a id='goRepayment_"+row.repaymentId+"_"+row.repaymentTime+"' style='cursor:pointer'>还款</a>   "
														: (value == 1 ?'<font color="green">已还款</font>':'错误状态');
										}
									},
									{
										field : 'operation1',
										title : '投资人',
										width : 100,
										align : 'center',
										formatter : function(value, row, index) {
											url = "<a id='goTenderList_"+row.weekId+"_"+row.repaymentTime+"' style='cursor:pointer'>查看投资人</a>";
											return url
										}
									}
									] ],
							toolbar : '#searchTool',
							onLoadSuccess : function() {
								parent.$.messager.progress('close');
								parent.$.modalDialog.openner_dataGrid = finalList;
								//还款
								$('a[id^="goRepayment_"]').click(function() {
									var repaymentId = this.id.split('_')[1];
									var repaymentTime = this.id.split('_')[2];
									var data = {};
									data["repaymentId"] = repaymentId;
									data["repaymentTime"] = repaymentTime;
									var url = '/system/week/repayment';
									repaymentDetail(repaymentId,repaymentTime);
									/*
									$.ajax({
										url : url,
										cache : false,
										async : false,
										type : 'post',
										error : function(XMLHttpRequest, textStatus, errorThrown) {
											alert('代码出错了' + errorThrown);
										},
										data : data,
										success : function(data) {
											json = $.parseJSON(data);
											if (json.success) {
												parent.$.messager.show({
													title : '系统提示',
													msg : '还款成功',
													timeout : 5000,
													showType : 'slide'
												});
												queryDetail();
											} else {
												parent.$.messager.show({
													title : '系统提示',
													msg : '还款失败：'+json.msg,
													timeout : 5000,
													showType : 'slide'
												});
												queryDetail();
											}
										},
										contentType : "application/x-www-form-urlencoded;charset=UTF-8"
									});
									*/
									
									
									
								});	
								//查看投资人
								$('a[id^="goTenderList_"]').click(function() {
									var weekId = this.id.split('_')[1];
									var repaymentTime = this.id.split('_')[2];
									var url = '/system/week/weekTenderListPage?weekId='+weekId+'&repaymentTime='+repaymentTime;
									parent.$.modalDialog({
										title : '查看投资人',
										width : w_width,
										height : w_height,
										href : url,
										buttons : [ {
											text : '关闭',
											handler : function() {
												parent.$.modalDialog.handler.dialog('close');
											}
										}]
									});
									parent.$.messager.progress('close');
									
								});	
							}
						});
		
		//按钮绑定
		
	})

	function queryDetail() {
		finalList.datagrid('load', {
			'weekId' : $('#weekId').val(),
			'status': $('#status').val(),
			'startTime': $('#startTime').val(),
			'endTime': $('#endTime').val()
		});
		parent.$.messager.progress('close');
	}
	
	function clostDialog(){
		parent.$.modalDialog.handler.dialog('close');
	}
	
	function repaymentDetail(repaymentId,repaymentTime) {
		href = '/system/week/repaymentDetail?repaymentTime='+repaymentTime+'&repaymentId='+repaymentId;
		parent.$.modalDialog({
			title : '还款详情',
			width : w_width,
			height : w_height,
			href : href
		});
		parent.$.messager.progress('close');
	}
	
