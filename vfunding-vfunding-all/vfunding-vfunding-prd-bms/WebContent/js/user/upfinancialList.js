var result;
	$(function() {
		result = $('#list')
			.datagrid({
				url : '/system/user/upfinancialList',
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
				queryParams: { status:$('#status').val()},
				columns : [ [
						{
							field : 'userId',
							title : '用户编号',
							align : 'center'
						},
						{
							field : 'newType',
							title : '申请级别',
							width : 50,
							align : 'center'
						},
						{
							field : 'oldType',
							title : '原来级别',
							width : 50,
							align : 'center',
							formatter : function(value, row, index) {
								return value==null?'-':value;
							}
						},
						{
							field : 'username',
							title : '登录名',
							width : 50,
							align : 'center',
							formatter : function(value, row, index) {
								return value==null?'-':value;
							}
						},
						{
							field : 'realname',
							title : '真实名',
							width : 50,
							align : 'center',
							formatter : function(value, row, index) {
								return value==null?'-':value;
							}
						},
						{
							field : 'phone',
							title : '电话',
							width : 50,
							align : 'center',
							formatter : function(value, row, index) {
								return (value==null||value=='')?'-':value;
							}
						},
						{
							field : 'verifyTime',
							title : '审核时间',
							width : 50,
							align : 'center',
							formatter : function(value, row, index) {
								return value==null?'-':value;
							}
						},
						{
							field : 'addTime',
							title : '添加时间',
							width : 50,
							align : 'center',
							formatter : function(value, row, index) {
								return value==null?'-':value;
							}
						},
						{
							field : 'operator',
							title : '操作',
							width : 30,
							align : 'center',
							formatter : function(value, row, index) {
								var str = '';
								if(row.status==0){
									if ($.canApply) {
										str += "<a id='goApply_"+row.id+"'>审核</a>";
									} else {
										str += "无权限";
									}
								} else if(row.status == 1){
									str += "通过";
								} else if(row.status==2){
									str += "未通过";
								} else {
									str += "error";
								}
								return str;
							}
						} ] ],
				toolbar : '#searchTool',
				onLoadSuccess : function() {
					parent.$.messager.progress('close');
					$('a[id^="goApply_"]').click(function() {
						var id = this.id.split('_')[1];
						parent.$.modalDialog({
							title : '审核',
							width : 500,
							height : 300,
							href : '/system/user/applyFinancialPage?id='+ id,
							buttons : [ {
								text : '审核',
								iconCls:'icon-save',
								handler : function() {
									parent.$.modalDialog.openner_dataGrid = result;
									var f = parent.$.modalDialog.handler.find('#applyform');
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
				}
			});
	})
	function queryDetail() {
		result.datagrid('load', {
			'username' : $('#username').val(),
			'keyWord' : $('#realname').val(),
			'phone' : $('#phone').val(),
			'status' : $('#status').val(),
			'startTime' : $('#startTime').val(),
			'endTime' : $('#endTime').val()
		});
		parent.$.messager.progress('close');
	}