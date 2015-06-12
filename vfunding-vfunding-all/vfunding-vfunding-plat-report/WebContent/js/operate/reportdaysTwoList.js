var reportdatagrid;
$(function() {
	reportdatagrid = $('#reportdatagrid').datagrid({
		url : '/operate/reportdays/listTwo',
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
			title : '累计注册用户',
			width : 90,
			align : 'center'
		}, {
			field : 'dyxzyh',
			title : '当月注册用户数',
			width : 150,
			align : 'center'
		}, {
			field : 'dqzbs',
			title : '累计发表数',
			width : 130,
			align : 'center'
		}, {
			field : 'drfbze',
			title : '当日发表金额',
			width : 130,
			align : 'center'
		}, {
			field : 'ordinaryusersday',
			title : '当日普通用户注册人数',
			width : 150,
			align : 'center'
		}, {
			field : 'internalusersday',
			title : '当日集团用户注册人数',
			width : 170,
			align : 'center'
		}, {
			field : 'ordinaryuserssumaccount',
			title : '累计外部投资金额',
			width : 110,
			align : 'center'
		}, {
			field : 'tenderordinaryuserscount',
			title : '累计外部投资人数',
			width : 130,
			align : 'center'
		}, {
			field : 'internaluserssumaccount',
			title : '累计集团投资金额',
			width : 130,
			align : 'center'
		}, {
			field : 'tenderinternaluserscount',
			title : '累计集团投资人数',
			width : 130,
			align : 'center'
		}, {
			field : 'repaymentsumyesaccount',
			title : '当前已经还款金额',
			width : 130,
			align : 'center'
		}, {
			field : 'tendersumaccount',
			title : '当前累计投资金额',
			width : 130,
			align : 'center'
		}, {
			field : 'repaymentsumyesaccountmonth',
			title : '当月还款金额',
			width : 170,
			align : 'center'
		}, {
			field : 'borrowsumcountmonth',
			title : '当月应还款标数',
			width : 130,
			align : 'center'
		}, {
			field : 'borrowcountmonth',
			title : '当月发表数',
			width : 110,
			align : 'center'
		}, {
			field : 'borrowcountday',
			title : '当日发表数',
			width : 130,
			align : 'center'
		}, {
			field : 'borrowsumaccountmonth',
			title : '当月发表金额',
			width : 130,
			align : 'center'
		}, {
			field : 'borrowsumaccount',
			title : '累计发表金额',
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
		url = "/operate/reportdays/exportExcelTwo?"
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