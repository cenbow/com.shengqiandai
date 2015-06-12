var finalList;
	$(function() {
		finalList = $('#list').datagrid({
							url : '/system/banner/bannerList',
							isField : "id",
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
										field : 'id',
										title : '编号',
										align : 'center'
									},
									{
										field : 'name',
										title : '标题',
										align : 'center',
										formatter : function(value, row, index) {
											return (value == null||value == '')?'-':value;
										}
									},
									{
										field : 'summary',
										title : '摘要',
										align : 'center',
										formatter : function(value, row, index) {
											return (value == null||value == '')?'-':value;
										}
									},
									{
										field : 'url',
										title : '地址',
										align : 'center',
										width : 100,
										formatter : function(value, row, index) {
											return (value == null||value == '')?'-':value;
										}
									},
									{
										field : 'typeId',
										title : '类型',
										align : 'center',
										formatter : function(value, row, index) {
											return value==1?'网站':(value==2?'手机':(value==0?'宣传':'其他'));
										}
									},
									{
										field : 'status',
										title : '状态',
										align : 'center',
										formatter : function(value, row, index) {
											return value == 1?'显示':'隐藏';
										}
									},
									{
										field : 'order',
										title : '排序',
										align : 'center'
									},
									{
										field : 'addtimeStr',
										title : '时间',
										align : 'center'
									},
									{
										field : 'other',
										title : '操作',
										align : 'center',
										formatter : function(value, row, index) {
											
											var str = '';
											if ($.addOrEdit) {
												str += "<a id='goEdit_"+row.id+"'>编辑</a>";
											}
											if($.canDelete){
												str+="|"+"<a id='goDelete_"+row.id+"'>删除</a>";
											}
											return str;
										}
									} ] ],
							toolbar : '#searchTool',
							onLoadSuccess : function() {
								parent.$.messager.progress('close');
								$('a[id^="goEdit_"]').click(function() {
									var id = this.id.split('_')[1];
									parent.$.modalDialog({
										title : '编辑',
										width : 1000,
										height : 500,
										href : '/system/banner/addEditBannerPage?id='+ id,
										buttons : [ {
											text : '更新',
											iconCls:'icon-save',
											handler : function() {
												parent.$.modalDialog.openner_dataGrid = finalList;
												var f = parent.$.modalDialog.handler.find('#addEditBannerform');
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
											url : "/system/banner/delete?id="+id,
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
	function addBanner() {
		parent.$.modalDialog({
			title : '添加banner',
			width : 1000,
			height : 300,
			href : '/system/banner/addEditBannerPage',
			buttons : [ {
				text : '添加',
				iconCls:'icon-save',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = finalList;
					var f = parent.$.modalDialog.handler.find('#addEditBannerform');
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