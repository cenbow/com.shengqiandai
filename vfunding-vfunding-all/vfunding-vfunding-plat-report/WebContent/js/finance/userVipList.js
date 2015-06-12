var reportdatagrid;
$(function() {
	reportdatagrid = $('#reportdatagrid').datagrid({
		url : '/finance/reportData/userVipList',
		fit : true,
		fitColumns : true,
		border : false,
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
			field : 'userName',
			title : '用户名',
			width : 90,
			align : 'center'
		}, {
			field : 'vipMoney',
			title : 'VIP所消耗金额',
			width : 90,
			align : 'center'
		}, {
			field : 'vipRemark',
			title : '使用红包情况',
			width : 90,
			align : 'center'
		}, {
			field : 'addTime',
			title : '时间',
			width : 150,
			align : 'center'
		} ] ],
		toolbar : '#timeTool',
		onLoadSuccess : function() {
			$.ajax({
				url : '/finance/reportData/sumVipFees',
				data : {
					startTime : $('#startTime').val(),
					endTime : $('#endTime').val()
				},
				cache : false,
				type : 'post',
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert('很遗憾，出异常了' + errorThrown);
				},
				success : function(result) {
					result = $.parseJSON(result);
					if (result != null) {
						$("#sumVipMoney").html(result.sumVipMoney + "元");
					}else{
						$("#sumVipMoney").html( "0元");
					}

				},
				contentType : "application/x-www-form-urlencoded;charset=UTF-8"
			});
			parent.$.messager.progress('close');
		}
	});
});

function exportExcel() {
	if (confirm("是否导出" + $('#startTime').val() + "到" + $('#endTime').val()
			+ "的数据？")) {
		url = "/finance/reportData/exportUserVipExcel?"
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