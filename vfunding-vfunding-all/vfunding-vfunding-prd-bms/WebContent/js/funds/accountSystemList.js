var finalList;
	$(function() {
		finalList = $('#list').datagrid({
			url : '/system/funds/accountSystemList',
			isField : "id",
			fit : true,
			pagination : true,//显示分页
			pageSize : 15,//分页大小
			pageList : [ 10, 15, 20 ],//每页的个数
			border : false,
			singleSelect : true,
			queryParams : {'username' : $('#userName').val()},
			fit : true,//自动补全
			fitColumns : true,
			rownumbers: true,
			columns : [ [ 
			{
				field : 'userName',
				title : '用户名',
				align : 'center'
			}, {
				field : 'realName',
				title : '真实姓名',
				align : 'center'
			}, {
				field : 'total',
				title : '总额',
				align : 'center',
				formatter : function(value, row, index) {
					return value+'元';
				}
			}, {
				field : 'useMoney',
				title : '可用余额',
				align : 'center',
				formatter : function(value, row, index) {
					return value+'元';
				}
			}, {
				field : 'noUseMoney',
				title : '冻结金额',
				align : 'center',
				formatter : function(value, row, index) {
					return value+'元';
				}
			}, {
				field : 'collection',
				title : '待收金额',
				align : 'center',
				formatter : function(value, row, index) {
					return value+'元';
				}
			}, {
				field : 'addtime',
				title : '注册时间',
				align : 'center'
			}
			]],
			toolbar : '#accountToolbar',
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
			}
		});

	})
	
function queryDetail() {
	finalList.datagrid('load', {
		'username' : $('#userName').val()
	});
}
	
/**
 * 查询
 * 
 * @param value
 * @param name
 */
function searchAccount(value, name) {
	finalList.datagrid({
		queryParams : {
			'username' : value
		}
	});
}
