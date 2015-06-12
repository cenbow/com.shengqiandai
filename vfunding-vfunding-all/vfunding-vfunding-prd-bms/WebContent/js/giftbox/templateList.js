/**
 * 模板列表
 * 
 */
var finalList;
var w_width = 750;
var w_height = 400;
$(function() {
	finalList = $('#list')
			.datagrid(
					{
						url : '/giftboxTemplate/templateList?r='
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
									title : '模板编号',
									width : 80,
									align : 'center'
								},
								{
									field : 'name',
									title : '模板名称',
									width : 100,
									align : 'left'
								},
								{
									field : 'content',
									title : '模板内容',
									width : 80,
									align : 'left'
								},
								{
									field : 'status',
									title : '模板状态',
									width : 80,
									align : 'left',
									formatter : function(value, row, index) {
										if(value == 0){
											return '启用'
										}else{
											return '停用'
										}
									}
								},
								{
									field : 'addtime',
									title : '创建时间',
									width : 80,
									align : 'left'
								},
								{
									field : 'createUserName',
									title : '创建人',
									width : 80,
									align : 'left'
								},
								{
									field : 'operation1',
									title : '模板操作',
									width : 100,
									align : 'center',
									formatter : function(value, row, index) {
										var url = "";
											url += "<a id='goPreview_"
													+ row.id
													+ "' style='cursor:pointer'>修改</a>   ";
										return value = url;
									}
								} ] ],
						toolbar : '#searchTool',
						onLoadSuccess : function() {
							parent.$.messager.progress('close');
							parent.$.modalDialog.openner_dataGrid = finalList;
							$('a[id^="goPreview_"]').click(function() {
								var id = this.id.split('_')[1];
								parent.$.modalDialog({
									title : '模板修改',
									width : 600,
									height : 350,
									href : '/giftboxTemplate/templateAddOrEditPage?id='+id
								});
								parent.$.messager.progress('close');
							});	
						}
					});
})

function queryDetail() {
	finalList.datagrid('load', {
		'templateName' : $('#templateName').val(),
		'addTime' : $('#addTime').val(),
		'status' : $('#status').val()
	});
	parent.$.messager.progress('close');
}

function addTemplate(){
	parent.$.modalDialog({
		title : '模板新增',
		width : 600,
		height : 350,
		href : '/giftboxTemplate/templateAddOrEditPage'
	});
	parent.$.messager.progress('close');
}
