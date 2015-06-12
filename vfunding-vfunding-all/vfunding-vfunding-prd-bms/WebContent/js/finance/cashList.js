var reportdatagrid;
$(function() {
	reportdatagrid = $('#reportdatagrid').datagrid({
		url : '/finance/reportData/cashList',
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
			field : 'cashFees',
			title : '提现手续费',
			width : 90,
			align : 'center'
		}, {
			field : 'hongbao',
			title : '提现使用的红包',
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
				url : '/finance/reportData/sumCashFees',
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
						if (result.sumCashFees == null) {
							$("#sumCashFees").html("0.00元");
						} else {
							$("#sumCashFees").html(result.sumCashFees + "元");
						}
						if (result.sumHongbao == null) {
							$("#sumHongbao").html("0.00元");
						} else {
							$("#sumHongbao").html(result.sumHongbao + "元");
						}
					}else{
						$("#sumCashFees").html("0元");
						$("#sumHongbao").html("0元");
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
		url = "/finance/reportData/exportCashExcel?"
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