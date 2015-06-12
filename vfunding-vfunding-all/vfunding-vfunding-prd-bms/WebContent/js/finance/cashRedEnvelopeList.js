/**
 * 用户现金红包
 */
var reportdatagrid;
$(function() {
	reportdatagrid = $('#reportdatagrid').datagrid({
		url : '/finance/reportData/cashRedEnvelopeList',
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
			field : 'unusedRedEnvelope',
			title : '未使用红包',
			width : 90,
			align : 'center'
		}, {
			field : 'usedRedEnvelope',
			title : '已使用红包',
			width : 90,
			align : 'center'
		}, {
			field : 'expiredRedEnvelope',
			title : '已过期红包',
			width : 90,
			align : 'center'
		} ] ],
		toolbar : '#tool',
		onLoadSuccess : function() {
			$.ajax({
				url : '/finance/reportData/sumCashRedEnvelope',
				data : {

				},
				cache : false,
				type : 'post',
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert('很遗憾，出异常了' + errorThrown);
				},
				dataType:"json",
				success : function(result) {
					if(result){
						for(var str in result){
							$("#"+str).html(result[str]);
						}
					}
				},
				contentType : "application/x-www-form-urlencoded;charset=UTF-8"
			});
			
			$.ajax({
				url: "/finance/reportData/getSumUsedRedEnvelope",
				cache : false,
				type : 'post',
				dataType:"json",
				data:{
					'startTime' : $('#startTime').val(),
					'endTime' : $('#endTime').val()
				},
				contentType : "application/x-www-form-urlencoded;charset=UTF-8",
				success:function(result){
					if(result){
						$("#sumUsedRedEnvelope").html(result["sumUsedRedEnvelope"]);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert('很遗憾，出异常了' + errorThrown);
				}
			});
			
			parent.$.messager.progress('close');
		}
	});
});

function exportExcel() {
	if (confirm("是否导出数据？")) {
		url = "/finance/reportData/exportCashRedEnvelopeExcel?startTime="+$('#startTime').val()+"&endTime="+$('#endTime').val();
		document.location.href = url ;
	}
}
function queryDetail() {
	var queryParams = {
			"startTime":$('#startTime').val(),
			"endTime":$('#endTime').val()
	};
	
	reportdatagrid.datagrid('options').queryParams = queryParams;
	reportdatagrid.datagrid('reload');
}

function queryDetail2() {
	reportdatagrid.datagrid('load', {
		'userName' : $('#userName').val()
	});
}