var result;
$(function() {
	result = $('#seolists').datagrid({
		url : '/system/seo/seoList',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		singleSelect : true,
		checkOnSelect : false,
		selectOnCheck : false,
		idField : 'id',
		pageSize : 15,
		pageList : [ 15, 30, 50, 100, 500 ],
		columns : [ [
				{
					field : 'id',
					title : '序号',
					align : 'center',
					width : 30
				},
				{
					field : 'jspname',
					title : '名称',
					align : 'left',
					width : 100,
					formatter : function(value, row, index) {
						if(value!=null && value.length>15){
							return value.substring(0,14)+'...';
						} else {
							return value;
						}
					}
				},
				{
					field : 'title',
					title : '标题',
					align : 'center',
					width : 100
				},
				{
					field : 'url',
					title : '路径',
					align : 'left',
					width : 150
				},
				{
					field : 'description',
					title : '描述',
					align : 'left',
					width : 150,
					formatter : function(value, row, index) {
						if(value!=null && value.length>30){
							return value.substring(0,29)+'...';
						} else {
							return value;
						}
					}
				},
				{
					field : 'keywords',
					title : '关键字',
					align : 'left',
					width : 150,
					formatter : function(value, row, index) {
						if(value!=null && value.length>30){
							return value.substring(0,29)+'...';
						} else {
							return value;
						}
					}
				},
				{
					field : 'charset',
					title : '编码',
					align : 'center',
					width : 50
				},
				{
					field : 'updatetime',
					title : '时间',
					align : 'center',
					width : 110
				},
				{
					field : 'other',
					title : '操作',
					align : 'center',
					width : 50,
					formatter : function(value, row, index) {
						var v = "";
						if($.addOrEdit){
							v = "<a id='goEdit_"+row.id+"'>编辑</a> ";
						} else{
							v += "没编辑权限 ";
						}
						if($.canDelete){
							v+=" |"+"  <a id='goDelete_"+row.id+"'>删除</a>";
						} else {
							v += " 没删除权限";
						}
						return v;
					}
				}
				] ],
				toolbar : '#searchTool',
				onLoadSuccess : function() {
					parent.$.messager.progress('close');
					$('a[id^="goEdit_"]').click(function() {
						var id = this.id.split('goEdit_')[1];
						parent.$.modalDialog({
							title : '编辑',
							width : 800,
							height : 520,
							href : '/system/seo/addSeoRecordPage?id=' + id,
							buttons : [{
								text : '更新',
								iconCls:'icon-save',
								handler : function() {
									parent.$.modalDialog.openner_dataGrid = result;
									var f = parent.$.modalDialog.handler.find('#addSeoform');
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
					
					
					$('a[id^="goDelete_"]').click(function() {
						if(confirm('确定删除?')){
							var id = this.id.split('_')[1];
							$.ajax({
								url : "/system/seo/delete?id="+id,
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
});

function queryDetail() {
	result.datagrid('load', {
		'type' : $('#type').val(),
		'url' : $('#url').val(),
		'title' : $('#title').val()
	});
}
function addSeoRecord(){
	parent.$.modalDialog({
		title : '添加SEO关键字',
		width : 800,
		height : 520,
		href : '/system/seo/addSeoRecordPage',
		buttons : [ {
			text : '添加',
			iconCls:'icon-save',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = result;
				var f = parent.$.modalDialog.handler.find('#addSeoform');
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
