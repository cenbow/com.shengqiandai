var reportdatagrid;
$(function() {
	reportdatagrid = $('#reportdatagrid').datagrid({
		url : '/finance/reportData/userHongbaoList',
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
			field : 'hongbao',
			title : '提现抵扣券',
			width : 90,
			align : 'center'
		} ] ],
		toolbar : '#timeTool',
		onLoadSuccess : function() {
			$.ajax({
				url : '/finance/reportData/sumUserHongbaoFees',
				data : {

				},
				cache : false,
				type : 'post',
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert('很遗憾，出异常了' + errorThrown);
				},
				success : function(result) {
					result = $.parseJSON(result);
					if(result!=null){
						$("#sumUserHongbao").html(result.sumHongbao + "元");
					}else{
						$("#sumUserHongbao").html("0元");
					}
				
				},
				contentType : "application/x-www-form-urlencoded;charset=UTF-8"
			});
			parent.$.messager.progress('close');
		}
	});
});

function exportExcel() {
	if (confirm("是否导出数据？")) {
		url = "/finance/reportData/exportUserHongbaoExcel"
		document.location.href = url ;
	}
}

function queryDetail() {
	reportdatagrid.datagrid('load', {
		'userName' : $('#userName').val()
	});
}