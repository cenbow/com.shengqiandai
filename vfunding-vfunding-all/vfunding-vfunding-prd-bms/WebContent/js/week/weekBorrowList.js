/**
 * 标的列表
 * @author louchen 2014-12-4
 */
var finalList;
var w_width=1000;
var w_height=550;
var closable = false;
	$(function() {
		finalList = $('#list').datagrid({
							url : '/system/week/weekBorrowList?weekId='+$('#weekId').val()+'&r='+Math.random(),
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
										title : '标的编号',
										width : 80,
										align : 'center'
									},
									{
										field : 'name',
										title : '标的名称',
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
										field : 'contractStart',
										title : '合同开始时间',
										width : 80,
										align : 'left'
									},
									{	
										field : 'contractEnd',
										title : '合同结束时间',
										width : 80,
										align : 'left'
									},
									{	
										field : 'account',
										title : '标的金额',
										width : 80,
										align : 'left'
									},
									{
										field : 'status',
										title : '标的状态',
										width : 100,
										align : 'center',
										formatter : function(value, row, index) {
											return value == 0 ? '未发布'
													: (value == 1 ? '<font color="blue">提交审核</font>'
															: (value == 2 ? '<font color="red">审核失败</font>'
																	: (value == 3 ?'<font color="green">审核成功</font>':'错误状态')));
										}
									},
									{
										field : 'operation1',
										title : '操作',
										width : 100,
										align : 'center',
										formatter : function(value, row, index) {
											var url = "";
											if(row.status=="0"|row.status=="2"){
												if ($.canEdit) {
													url += "<a id='goEdit_"+row.id+"' style='cursor:pointer'>编辑</a>   ";
												}
//												if ($.canDelete) {
//													url += "   <a id='goDelete_"+row.id+"' style='cursor:pointer'>删除</a>";
//												}
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
								//编辑
								$('a[id^="goEdit_"]').click(function() {
									var id = this.id.split('_')[1];
									parent.$.modalDialog({
										title : '更新标的',
										closable:closable,
										width : w_width,
										height : w_height,
										href : '/system/week/addOrEditWeekBorrowPage?id='+ id + '&weekId='+$('#weekId').val()
									});
									parent.$.messager.progress('close');
								});
								//删除
								$('a[id^="goDelete_"]').click(function() {
									if(confirm('确定删除?')){
										var id = this.id.split('_')[1];
										$.ajax({
											url : "/system/week/deleteWeek?id="+id,
											type : "post",
											success : function(result) {
												result = $.parseJSON(result);
												parent.$.messager.show({
													title : '系统提示',
													msg : '操作成功',
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
							}
						});		
	})

	function queryDetail() {
		finalList.datagrid('load', {
			'weekId' : $('#weekId').val(),
			'id' : $('#id').val(),
			'status': $('#status').val()
		});
		parent.$.messager.progress('close');
	}
	
	function addWeekBorrow() {
		href = '/system/week/addOrEditWeekBorrowPage?r='+Math.random()+'&weekId='+$('#weekId').val();
		parent.$.modalDialog({
			title : '添加标的',
			closable:closable,
			width : w_width,
			height : w_height,
			href : href
		});
		parent.$.messager.progress('close');
	}
