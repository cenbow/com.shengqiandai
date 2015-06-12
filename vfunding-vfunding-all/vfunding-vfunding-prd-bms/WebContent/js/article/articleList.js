var finalList;
	$(function() {
		finalList = $('#list').datagrid({
							url : '/system/article/articleList',
							isField : "id",
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
										title : '文章编号',
										width : 80,
										align : 'center'
									},
									{
										field : 'name',
										title : '标题',
										width : 400,
										align : 'left'
									},
									{
										field : 'siteId',
										title : '栏目类型',
										width : 100,
										align : 'center',
										formatter : function(value, row, index) {
											return value == 108 ? '小微新闻'
													: (value == 95 ? '网站公告'
															: (value == 85 ? "媒体报道"
																	: (value == 109 ?'小微攻略':'其他')));
										}
									},
									{
										field : 'source',
										title : '来源',
										width : 250,
										align : 'center',
										formatter : function(value, row, index) {
											return value == ''?'--':value;
										}
									},
									{
										field : 'status',
										title : '状态',
										width : 100,
										align : 'center',
										formatter : function(value, row, index) {
											return value == 1?'显示':'隐藏';
										}
									},
									{
										field : 'order',
										title : '排序',
										width : 100,
										align : 'center'
									},
									{
										field : 'flag',
										title : '属性',
										width : 100,
										align : 'center',
										formatter : function(value, row, index) {
											return value == 't' ? '推荐'
													: (value == 'h' ? '头条'
															: (value == 't,h' ? "推荐|头条" : '--'));
										}
									},
									{
										field : 'other',
										title : '操作',
										width : 100,
										align : 'center',
										formatter : function(value, row, index) {
											var url = "";
											if ($.canEdit) {
												url += "<a id='goEdit_"+row.id+"'>编辑</a>   ";
											}
											if ($.canDelete) {
												url += "   <a id='goDelete_"+row.id+"'>删除</a>";
											}
											return value = url;
										}
									} ] ],
							toolbar : '#searchTool',
							onLoadSuccess : function() {
								parent.$.messager.progress('close');
								$('a[id^="goEdit_"]').click(function() {
									var id = this.id.split('_')[1];
									parent.$.modalDialog({
										title : '更新文章',
										width : 1000,
										height : 600,
										href : '/system/article/addArticlePage?id='+ id,
										buttons : [ {
											text : '更新',
											iconCls:'icon-save',
											handler : function() {
												parent.$.modalDialog.openner_dataGrid = finalList;
												var f = parent.$.modalDialog.handler.find('#addArticleform');
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
											url : "/system/article/delete?id="+id,
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
			'siteId' : $('#siteId').val()
		});
		parent.$.messager.progress('close');
	}
	
	function addArticle() {
		parent.$.modalDialog({
			title : '添加文章',
			width : 1100,
			height : 600,
			href : '/system/article/addArticlePage',
			buttons : [ {
				text : '添加',
				iconCls:'icon-save',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = finalList;
					var f = parent.$.modalDialog.handler.find('#addArticleform');
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
	
	/**
	 * 查询
	 * 
	 * @param value
	 * @param name
	 */
	function searchAccount(value, name) {
		empdatagrid.datagrid({
			queryParams : {
				empName : value
			}
		});
	}
