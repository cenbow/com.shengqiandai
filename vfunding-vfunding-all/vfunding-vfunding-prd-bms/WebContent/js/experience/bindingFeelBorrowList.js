var finalList;
	$(function() {
		parent.$.messager.progress('close');
		finalList = $('#list')
				.datagrid(
						{
							url : '/system/experience/bindingFeelList',
							isField : "id",
							pagination : true,//显示分页
							pageSize : 20,//分页大小
							pageList : [ 15, 20, 50, 100 ],//每页的个数
							checkOnSelect : false,
							selectOnCheck : false,
							singleSelect : true,
							fit : true,//自动补全
							fitColumns : true,
							border : false,
							queryParams : {'status' : $('#status').val()},
							columns : [ [
						            {
						            	field : 'no',
						            	title : '体验卡ID',
						            	width : 100,
						            	align : 'center'
						            },
									{
										field : 'username',
										title : '理财师用户名',
										width : 150,
										align : 'center'
									},
									{
										field : 'bindingStatus',
										title : '绑定状态',
										width : 100,
										align : 'center',
										formatter : function(value, row, index) {
											return (row.username==null&&row.groupname==null)?'未绑定' : "已绑定";
											//return (value == 0 ||value == ''||value == null) ? '未绑定' : "已绑定";
										}
									},
									{
										field : 'useStatus',
										title : '激活状态',
										width : 100,
										align : 'center',
										formatter : function(value, row, index) {
											return value == 1 ? '未激活'
													: (value == 2 ? '已激活' : 'error');
										}
									},
									{
										field : 'addtimeStr',
										title : '激活时间',
										width : 150,
										align : 'center'
									},{
										field : 'firstRechargeStr',
										title : '投资人首次充值时间',
										width : 150,
										align : 'center'
									},{
										field : 'tenders',
										title : '投资人',
										width : 150,
										align : 'center',
										formatter : function(value, row, index) {
											return value==null?'-':value;
										}
									} ] ],
							toolbar : '#searchTool',
							onLoadSuccess : function() {
								parent.$.messager.progress('close');
							}
						});

	})
	function queryDetail() {
		finalList.datagrid('load', {
			'startNo' : $('#startNo').val(),
			'endNo' : $('#endNo').val(),
			'type' : $('#status').val(),
			'username' : $('#username').val(),
			'startTime' : $('#startTime').val(),
			'endTime' : $('#endTime').val(),
			'bindingStatus' : $('#bindingStatus').val(),
			'useStatus' : $('#useStatus').val()
		});
		parent.$.messager.progress('close');
	}
	function bindingFeel() {
		parent.$.modalDialog({
			title : '卡号绑定',
			width : 550,
			height : 250,
			href : '/system/experience/toFeelBindingPage',
			buttons : [ {
				text : '绑定',
				iconCls:'icon-save',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = finalList;
					var f = parent.$.modalDialog.handler.find('#feelForm');
					f.submit();
				}
			} ]
		});
	}