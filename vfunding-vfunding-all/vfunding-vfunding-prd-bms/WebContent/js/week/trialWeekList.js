/**
 * 审核计划
 * @author louchen 2014-12-9
 */
var finalList;
var w_width=750;
var w_height=400;
	$(function() {
		
		finalList = $('#list').datagrid({
							url : '/system/week/trailWeekList?r='+Math.random(),
							queryParams : {
								'status' : $('#status').val()
							},
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
										field : 'id',
										title : '计划编号',
										width : 80,
										align : 'center'
									},
									{
										field : 'name',
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
										field : 'createTime',
										title : '创建时间',
										width : 80,
										align : 'left'
									},
									{	
										field : 'saleTime',
										title : '发售开始时间',
										width : 80,
										align : 'left'
									},
									{	
										field : 'expireTime',
										title : '发售截止时间',
										width : 80,
										align : 'left'
									},
									{	
										field : 'planMoney',
										title : '预期计划金额',
										width : 80,
										align : 'left'
									},
									{	
										field : 'realityMoney',
										title : '实际发布金额',
										width : 80,
										align : 'left'
									},
									{	
										field : 'publishTime',
										title : '发布时间',
										width : 80,
										align : 'left'
									},
									{
										field : 'status',
										title : '计划状态',
										width : 100,
										align : 'center',
										formatter : function(value, row, index) {
											return value == 0 ? '未发布'
													: (value == 1 ? '<font color="blue">待审核</font>'
															: (value == 2 ? '<font color="red">审核失败</font>'
																	: (value == 3 ?'<font color="green">审核成功</font>':'错误状态')));
										}
									},
									{
										field : 'operation1',
										title : '计划操作',
										width : 100,
										align : 'center',
										formatter : function(value, row, index) {
											var url = "";
											if(row.status=="1"){
												if ($.canTrial) {
													url += "<a id='goTrial_"+row.id+"' style='cursor:pointer'>审核</a>   ";
												}
											}
											return value = url;
										}
									},									
									{	
										field : 'verifyRemark',
										title : '审核备注',
										width : 80,
										align : 'left'
									}		
									] ],
							toolbar : '#searchTool',
							onLoadSuccess : function() {
								parent.$.messager.progress('close');
								parent.$.modalDialog.openner_dataGrid = finalList;
								//审核
								$('a[id^="goTrial_"]').click(function() {
									var id = this.id.split('_')[1];
									parent.$.modalDialog({
										title : '审核计划',
										width : 1000,
										height : 500,
										href : '/system/week/weekDetail?id='+id+"&type=trial"
									});
									parent.$.messager.progress('close');
								});	

							}
						});
		
		//按钮绑定
		
	})

	function queryDetail() {
		finalList.datagrid('load', {
			'id' : $('#id').val(),
			'status': $('#status').val(),
			'saleTime': $('#saleTime').val(),
			'expireTime': $('#expireTime').val()
		});
		parent.$.messager.progress('close');
	}
	
	function clostDialog(){
		parent.$.modalDialog.handler.dialog('close');
	}
