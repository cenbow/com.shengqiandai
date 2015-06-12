var reportdatagrid;
$(function() {
	reportdatagrid = $('#reportdatagrid').datagrid({
		url : '/userFrom/userFromListPage',
		fit : true,
		fitColumns : true,
		border : true,
		pagination : true,
		pageSize : 15,
		checkOnSelect : false,
		selectOnCheck : false,
		singleSelect : true,
		pageList : [ 10, 15, 20, 25, 30 ],
		queryParams : {
			'endTime' : $('#endTime').val() + " 23:59:59",
			'startTime' : $('#startTime').val()

		},
		columns : [ [ {
			field : 'fromName',
			title : '用户来源',
			width : 90,
			align : 'center'
		}, {
			field : 'regUser',
			title : '注册人数',
			width : 150,
			align : 'center'
		}, {
			field : 'feelTenderUser',
			title : '体验标投资人数',
			width : 130,
			align : 'center'
		}, {
			field : 'tenderUser',
			title : '投资人数',
			width : 130,
			align : 'center'
		}, {
			field : 'sumTender',
			title : '投资金额',
			width : 150,
			align : 'center'
		}, {
			field : 'sumCollection',
			title : '待收金额',
			width : 170,
			align : 'center'
		} ] ],
		toolbar : '#timeTool',
		onLoadSuccess : function() {
			parent.$.messager.progress('close');
		}
	});
});

function exportExcel() {
	if (confirm("是否导出" + $('#startTime').val() + "到" + $('#endTime').val()
			+ "的数据？")) {
		url = "/operate/reportdays/exportExcel?"
		document.location.href = url + "startTime=" + $('#startTime').val()
				+ "&endTime=" + $('#endTime').val();
	}
}

function queryDetail() {
	reportdatagrid.datagrid('load', {
		'startTime' : $('#startTime').val(),
		'endTime' : $('#endTime').val() + " 23:59:59"
	});
}