var finalList;
	$(function() {
		finalList = $('#list').datagrid({
							url : '/system/pic/picList',
							isField : "pickey",
							pagination : true,//显示分页
							pageSize : 15,//分页大小
							pageList : [ 10, 15, 20 ],//每页的个数
							checkOnSelect : false,
							selectOnCheck : false,
							singleSelect : true,
							fit : true,//自动补全
							fitColumns : true,
							border : false,
							columns : [ [
									{
										field : 'pickey',
										title : 'key',
										align : 'center'
									},
									{
										field : 'url',
										title : '地址',
										align : 'center',
									},
									{
										field : 'description',
										title : '描述',
										align : 'center',
									},
									{
										field : 'other',
										title : '操作',
										align : 'center',
										formatter : function(value, row, index) {
											var str = '';
											if ($.addOrEdit) {
												str += "<a id='goEdit~"+row["pickey"]+"'>编辑</a>";
											}
											if($.canDelete){
												str+="|"+"<a id='goDelete~"+row["pickey"]+"'>删除</a>";
											}
											return str;
										}
									} ] ],
							toolbar : '#searchTool',
							onLoadSuccess : function() {
								parent.$.messager.progress('close');
								$('a[id^="goEdit~"]').click(function() {
									var picKey = this.id.split('~')[1];

									parent.$.modalDialog({
										title : '编辑',
										width : 1000,
										height : 500,
										href : '/system/pic/addEditPicPage?picKey='+ picKey,
										buttons : [ {
											text : '更新',
											iconCls:'icon-save',
											handler : function() {
												parent.$.modalDialog.openner_dataGrid = finalList;
												var f = parent.$.modalDialog.handler.find('#addEditPicform');
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
								$('a[id^="goDelete~"]').click(function() {
									if(confirm('确定删除?')){
										var picKey = this.id.split('~')[1];
										$.ajax({
											url : "/system/pic/delete?picKey="+picKey,
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
			'status' : $('#status').val(),
			'typeId' : $('#typeId').val()
		});
		parent.$.messager.progress('close');
	}
	function addPic() {
		parent.$.modalDialog({
			title : '添加图片',
			width : 1000,
			height : 300,
			href : '/system/pic/addEditPicPage',
			buttons : [ {
				text : '添加',
				iconCls:'icon-save',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = finalList;
					var f = parent.$.modalDialog.handler.find('#addEditPicform');
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