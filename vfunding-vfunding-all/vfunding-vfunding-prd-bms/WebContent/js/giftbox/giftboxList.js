/**
 * 红包管理
 * 
 */
var finalList;
var w_width = 750;
var w_height = 400;
$(function() {
	finalList = $('#list').datagrid({
		url : '/giftboxBms/giftboxList?r=' + Math.random(),
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
		columns : [ [ {
			field : 'id',
			title : '红包编号',
			width : 80,
			align : 'center'
		}, {
			field : 'title',
			title : '红包标题',
			width : 100,
			align : 'left'
		}, {
			field : 'content',
			title : '红包内容',
			width : 80,
			align : 'left'
		}, {
			field : 'money',
			title : '红包金额',
			width : 80,
			align : 'left',
		}, {
			field : 'addtime',
			title : '红包发放日期',
			width : 80,
			align : 'left'
		}, {
			field : 'takeTime',
			title : '红包生效日期',
			width : 80,
			align : 'left'
		}, {
			field : 'loseTime',
			title : '红包失效日期',
			width : 80,
			align : 'left'
		}, {
			field : 'receiveUserName',
			title : '接收用户',
			width : 80,
			align : 'left'
		}, {
			field : 'status',
			title : '红包使用状态',
			width : 80,
			align : 'left',
			formatter : function(value, row, index) {
				if (value == 0) {
					return '未使用'
				} else if (value == 1) {
					return '已使用'
				} else {
					return '已过期'
				}
			}
		} ] ],
		toolbar : '#searchTool',
		onLoadSuccess : function() {
			parent.$.messager.progress('close');
			parent.$.modalDialog.openner_dataGrid = finalList;
		}
	});
})
function queryDetail() {
	finalList.datagrid('load', {
		'title' : $('#title').val(),
		'addTime' : $('#addTime').val(),
		'status' : $('#status').val()
	});
	parent.$.messager.progress('close');
}
