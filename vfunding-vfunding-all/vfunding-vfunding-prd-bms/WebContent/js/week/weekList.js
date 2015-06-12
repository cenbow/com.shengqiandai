/**
 * 短期理财计划
 * 
 * @author louchen 2014-12-3
 */
var finalList;
var w_width=750;
var w_height=550;
	$(function() {
		finalList = $('#list').datagrid({
							url : '/system/week/weekList?r='+Math.random(),
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
																	: (value == 3 ? '<font color="green">已发布</font>'
																			: (value == 4 ?'<font color="green">申购结束</font>':'错误状态'))));
										}
									},
									{
										field : 'operation1',
										title : '计划操作',
										width : 100,
										align : 'center',
										formatter : function(value, row, index) {
											var url = "";
											if(row.status=="0"|row.status=="2"){
												if ($.canEdit) {
													url += "<a id='goEdit_"+row.id+"' style='cursor:pointer'>编辑</a>   ";
													url += "<a id='goPreview_"+row.id+"' style='cursor:pointer'>预览提交</a>   ";
												}
// if ($.canDelete) {
// url += " <a id='goDelete_"+row.id+"' style='cursor:pointer'>删除</a>";
// }
											}
											return value = url;
										}
									},
									{
										field : 'operation2',
										title : '标的操作',
										width : 100,
										align : 'center',
										formatter : function(value, row, index) {
											var url = "";
											if(row.status=="0"|row.status=="2"){
												if ($.canEdit) {
													url += "<a id='goToBorrowPage_"+row.id+"' style='cursor:pointer'>编辑标的</a>   ";
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
								
								// 编辑
								$('a[id^="goEdit_"]').click(function() {
									var id = this.id.split('_')[1];
									parent.$.modalDialog({
										title : '更新计划',
										width : w_width,
										height : w_height,
										href : '/system/week/addOrEditWeekPage?id='+ id,
										buttons : [ {
											text : '更新',
											iconCls:'icon-save',
											handler : function() {
												var f = parent.$.modalDialog.handler.find('#addoreditform');
												f.submit();
											}
										},{
											text : '关闭',
											handler : function() {
												parent.$.modalDialog.handler.dialog('close');
											}
										}]
									});
									parent.$.messager.progress('close');
								});
								// 预览提交
								$('a[id^="goPreview_"]').click(function() {
									var id = this.id.split('_')[1];
									parent.$.modalDialog({
										title : '预览提交',
										width : 1000,
										height : 500,
										href : '/system/week/weekDetail?id='+id+"&type=perview"  
									});
									parent.$.messager.progress('close');
								});	
								// 编辑标的
								$('a[id^="goToBorrowPage_"]').click(function() {
									var id = this.id.split('_')[1];
									location.href = "/system/week/weekBorrowListPage?weekid="+id;
								});	
								// 删除
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
		
		$("#subBtn").on(
				"click",
				function() {
					// 校验是否有标的
					if(checkBorrows()){
						$.ajax({
							url : '/system/week/weekSubTrial',
							data : $('#subTrial').serialize(),
							type : 'post',
							cache : false,
							error : function(XMLHttpRequest, textStatus,
									errorThrown) {
								alert('很遗憾，出异常了' + errorThrown);
							},
							beforeSend : function() {
								parent.$.messager.progress({
									title : '提示',
									text : '数据处理中，请稍后....'
								});
							},
							success : function(result) {
								parent.$.messager.progress('close');
								result = $.parseJSON(result);
								if (result.success) {
									parent.$.modalDialog.openner_dataGrid.datagrid('reload');
									parent.$.modalDialog.handler.dialog("close");
									parent.$.messager.show({
										title : '系统提示',
										msg : '操作成功',
										timeout : 5000,
										showType : 'slide'
									});
								} else {
									parent.$.modalDialog.openner_dataGrid.datagrid('reload');
									parent.$.modalDialog.handler.dialog("close");
									parent.$.messager.show({
										title : '系统提示',
										msg : '操作失败',
										timeout : 5000,
										showType : 'slide'
									});
								}
							}
						});
					}
				});
					
		
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
	
	function addWeek() {
		href = '/system/week/addOrEditWeekPage?r='+Math.random();
		parent.$.modalDialog({
			title : '添加理财计划',
			width : w_width,
			height : w_height,
			href : href,
			buttons : [ {
				text : '保存',
				iconCls:'icon-save',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = finalList;
					var f = parent.$.modalDialog.handler.find('#addoreditform');
					f.submit();
				}
			},{
				text : '关闭',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
				}
			} ]
		});
	}
	
	function clostDialog(){
		parent.$.modalDialog.handler.dialog('close');
	}
