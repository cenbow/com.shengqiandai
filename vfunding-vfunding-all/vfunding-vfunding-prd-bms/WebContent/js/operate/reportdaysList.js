var reportdatagrid;
$(function() {
	reportdatagrid = $('#reportdatagrid').datagrid({
		url : '/operate/reportdays/list',
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
			'startTime' : $('#startTime').val(),
			'endTime' : $('#endTime').val()
		},
		columns : [ [ {
			field : 'zyh',
			title : '总用户数',
			width : 90,
			align : 'center'
		}, {
			field : 'dyxzyh',
			title : '当月新增用户数',
			width : 150,
			align : 'center'
		}, {
			field : 'drxzyh',
			title : '当日新增用户',
			width : 130,
			align : 'center'
		}, {
			field : 'dryhdls',
			title : '当日用户登录数',
			width : 150,
			align : 'center'
		}, {
			field : 'drdlzs',
			title : '当日用户总登录数',
			width : 170,
			align : 'center'
		}, {
			field : 'dqczzrs',
			title : '充值总人数',
			width : 110,
			align : 'center'
		}, {
			field : 'drczrs',
			title : '当日充值人数',
			width : 130,
			align : 'center'
		}, {
			field : 'drtbrs',
			title : '当日投标人数',
			width : 130,
			align : 'center'
		}, {
			field : 'drczze',
			title : '当日充值总额',
			width : 130,
			align : 'center'
		}, {
			field : 'dqczze',
			title : '当前充值总额',
			width : 130,
			align : 'center'
		}, {
			field : 'drtbze',
			title : '当日投标总额',
			width : 130,
			align : 'center'
		}, {
			field : 'dryhzye',
			title : '当日用户余额总数',
			width : 170,
			align : 'center'
		}, {
			field : 'drfbze',
			title : '当日发标总额',
			width : 130,
			align : 'center'
		}, {
			field : 'dqzbs',
			title : '当前总标数',
			width : 110,
			align : 'center'
		}, {
			field : 'dqptbze',
			title : '当前投标总额',
			width : 130,
			align : 'center'
		}, {
			field : 'dqcgzbs',
			title : '当前成功总标数',
			width : 130,
			align : 'center'
		}, {
			field : 'fromaddtimeStr',
			title : '时间',
			width : 150,
			align : 'center'
		} ] ],
		toolbar: '#timeTool',
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
		'endTime' : $('#endTime').val()
	});
}