/**
 * 红包批次审核
 * 
 */
var finalList;
var w_width = 750;
var w_height = 400;
$(function() {
	finalList = $('#list')
			.datagrid(
					{
						url : '/giftboxTiral/tiralGiftboxList?r='
								+ Math.random(),
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
									title : '批次编号',
									width : 80,
									align : 'center'
								},
								{
									field : 'title',
									title : '红包标题',
									width : 100,
									align : 'left'
								},
								{
									field : 'moneyCount',
									title : '红包总金额',
									width : 80,
									align : 'left'
								},
								{
									field : 'userCount',
									title : '红包总数量',
									width : 80,
									align : 'left'
								},
								{
									field : 'templateName',
									title : '模板名称',
									width : 80,
									align : 'left'
								},
								{
									field : 'templateContent',
									title : '模板内容',
									width : 80,
									align : 'left'
								},

								{
									field : 'addtime',
									title : '提交日期',
									width : 80,
									align : 'left'
								},
								{
									field : 'createUserName',
									title : '提交人',
									width : 80,
									align : 'left'
								},
								{
									field : 'operation1',
									title : '计划操作',
									width : 100,
									align : 'center',
									formatter : function(value, row, index) {
										var url = "";
										if (row.status == "0") {
											url += "<a id='goPreview_"
													+ row.id
													+ "' style='cursor:pointer'>进行审核</a>   ";
										}else if(row.status == "1"){
											url += "审核通过";
										}else if(row.status == "2"){
											url += "审核失败";
										}
										return value = url;
									}
								} ] ],
						toolbar : '#searchTool',
						onLoadSuccess : function() {
							parent.$.messager.progress('close');
							parent.$.modalDialog.openner_dataGrid = finalList;
							// 预览提交
							$('a[id^="goPreview_"]').click(function() {
								var id = this.id.split('_')[1];
								parent.$.modalDialog({
									title : '红包审核',
									width : 1000,
									height : 600,
									href : '/giftboxTiral/tiralGiftboxInfoList?batchId='+id
								});
								parent.$.messager.progress('close');
							});	
						}
					});
})

function queryDetail() {
	finalList.datagrid('load', {
		'title' : $('#title').val(),
		'addTime' : $('#addTime').val(),
		'status' : $('#status').val()
	});
	parent.$.messager.progress('close');
}
