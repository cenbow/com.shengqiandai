var lockList;
	$(function() {
		lockList = $('#list')
				.datagrid(
						{
							url : '/system/user/lockUserList',
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
										field : 'userId',
										title : '用户编号',
										align : 'center'
									},
									{
										field : 'username',
										title : '用户名',
										width : 50,
										align : 'center'
									},
									{
										field : 'phone',
										title : '电话',
										width : 50,
										align : 'center',
										formatter : function(value, row, index) {
											return value==null?'-':value;
										}
									},
									{
										field : 'email',
										title : '邮箱',
										width : 50,
										align : 'center',
										formatter : function(value, row, index) {
											return value==null?'-':value;
										}
									},
									{
										field : 'lockTime',
										title : '锁定时间',
										width : 50,
										align : 'center',
										formatter : function(value, row, index) {
											return value==null?'-':value;
										}
									},
									{
										field : 'status',
										title : '操作',
										width : 30,
										align : 'center',
										formatter : function(value, row, index) {
											var str = '';
											if ($.canUnlock) {
												str += "<a id='goLock_"+row.userId+"'>确认解锁</a>";
											}
											return str;
										}
									} ] ],
							toolbar : '#searchTool',
							onLoadSuccess : function() {
								parent.$.messager.progress('close');
								$('a[id^="goLock_"]').click(function() {
									if(confirm('确定解锁?')){
										var id = this.id.split('_')[1];
										$.ajax({
											url : "/system/user/lockUser",
											type : "post",
											data : {
												"userId" : id
											},
											success : function(result) {
												result = $.parseJSON(result);
												if(result.success){
													parent.$.messager.show({
														title : '系统提示',
														msg : '操作成功',
														timeout : 5000,
														showType : 'slide'
													});
												} else {
													parent.$.messager.show({
														title : '系统提示',
														msg : '操作失败',
														timeout : 5000,
														showType : 'slide'
													});
												}
												queryDetail();
												finalList.openner_dataGrid.datagrid('reload');
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
		lockList.datagrid('load', {
			'username' : $('#userName').val(),
			'phone' : $('#phone').val()
		});
		parent.$.messager.progress('close');
	}