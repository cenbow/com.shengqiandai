var userList;
$(function() {
	userList = $("#userAddList")
			.datagrid(
					{
						url : '/system/userMan/findUserList',
						fit : true,
						border : false,
						pagination : true,
						pageSize : 15,
						idField : 'userId',
						checkOnSelect : false,
						selectOnCheck : false,
						singleSelect : true,
						pageList : [ 10, 15, 20, 25, 30 ],
						frozenColumns : [ [{
							field : 'userName',
							title : '用户名',
							align : 'center',
							width : '100'
						}, {
							field : 'realName',
							title : '真实姓名',
							align : 'center',
							width : '100'
						} ] ],
						columns : [ [
								{
									field : 'sex',
									title : '性别',
									align : 'center',
									width : '40',
									formatter : function(value, row, index) {
										if (value == 2) {
											return value = "女";
										} else {
											return value = "男";
										}
									}
								},
								{
									field : 'email',
									title : '邮箱',
									align : 'center',
									width : '170'
								},
								{
									field : 'qq',
									title : 'QQ',
									align : 'center',
									width : '120'
								},
								{
									field : 'phone',
									title : '手机号',
									align : 'center',
									width : '150'
								},
								{
									field : 'address',
									title : '所在地',
									align : 'center',
									width : '100'
								},
								{
									field : 'cardId',
									title : '身份证',
									align : 'center',
									width : '170'
								},
								{
									field : 'addtime',
									title : '添加时间',
									align : 'center',
									width : '170'
								},
								{
									field : 'status',
									title : '状态',
									align : 'center',
									width : '70',
									formatter : function(value, row, index) {
										if (value == 1) {
											return value = "开通";
										} else {
											return value = "暂未开通";
										}
									}

								},
								{
									field : 'name',
									title : '用户类型',
									align : 'center',
									width : '100'
								},
								{
									field : 'VIPstatus',
									title : '是否VIP',
									align : 'center',
									width : '70',
									formatter : function(value, row, index) {
										if (value == 1) {
											return value = "已开通";
										} else {
											return value = "未开通";
										}
									}
								},
								{
									field : 'videoStatus',
									title : '视频认证',
									align : 'center',
									width : '70',
									formatter : function(value, row, index) {
										if (value == 1) {
											return value = "已认证";
										} else {
											return value = "暂未认证";
										}
									}
								},
								{
									field : 'phoneStatus',
									title : '手机认证',
									align : 'center',
									width : '70',
									formatter : function(value, row, index) {
										if (value == 1) {
											return value = "已认证";
										} else {
											return value = "暂未认证";
										}
									}
								},
								{
									field : 'avatarStatus',
									title : '资料认证',
									align : 'center',
									width : '70',
									formatter : function(value, row, index) {
										if (value == 1) {
											return value = "已认证";
										} else {
											return value = "暂未认证";
										}
									}
								},
								{
									field : 'checkYalidDate',
									title : '操作',
									align : 'center',
									width : '40',
									formatter : function(value, row, index) {
										var str = '';
										if ($.canEdit) {
											str += '[<a id="'
													+ row.userId
													+ '" href="javascript:editUser('
													+ row.userId
													+ ');" style="color: black;text-decoration:none">修改</a>]';
										}
										return str;
									}
								} ] ],
						toolbar : '#userquery',
						onLoadSuccess : function() {
							parent.$.messager.progress('close');
							parent.$.modalDialog.openner_dataGrid = userList;
						}
					});
});
/**
 * 点击编辑方法
 */
function editUser(userId) {
	parent.$.modalDialog({
		title : '编辑用户信息',
		width : 450,
		height : 560,
		href : '/system/userMan/editorUser?id=' + userId
	});
	parent.$.messager.progress('close');
}
/**
 * 用户输入查询
 */
function findUser() {
	userList.datagrid('load', {
		'userName' : $('#userName').val(),
		'email' : $('#email').val(),
		'realName' : $('#realName').val(),
		'typeId' : $('#typeId').val()
	});
	parent.$.messager.progress('close');
}
