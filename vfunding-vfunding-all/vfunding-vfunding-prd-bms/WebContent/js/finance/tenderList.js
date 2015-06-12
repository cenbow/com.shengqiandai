var reportdatagrid;
$(function() {
	
	var queryParm= {
			'repayYestimeStart' : $('#startTime').val(),
			'repayYestimeEnd' : $('#endTime').val()
		}
	
	reportdatagrid = $('#reportdatagrid')
			.datagrid(
					{
						url : '/finance/reportData/tenderList',
						fit : true,
						fitColumns : true,
						border : false,
						pagination : true,
						pageSize : 10,
						checkOnSelect : false,
						selectOnCheck : false,
						singleSelect : true,
						pageList : [ 10, 15, 20, 25, 30 ],
						queryParams : queryParm,
						columns : [ [ {
							field : 'userName',
							title : '用户名',
							align : 'center'
						}, {
							field : 'borrowName',
							title : '标的名称',
							align : 'center'
							
						},{
							field : 'typeId',
							title : '用户类型',
							align : 'center',
							formatter : function(value, row, index) {
								if (value == 27 || value == 31) {
									return "集团用户";
								} else {
									return "普通用户";
								}
							}

						}, {
							field : 'rateFee',
							title : '平台服务费率',
							align : 'center',
							formatter : function(value, row, index) {
								
									return value+"%";
							
							}
						}, {
							field : 'serviceFees',
							title : '平台服务费',
							align : 'center'
						}, {
							field : 'guaranteeFees',
							title : '担保服务费',
							align : 'center'
						},{
							field : 'timeLimit',
							title : '期限',
							align : 'center',
							formatter : function(value, row, index) {
								return value+"个月";
							}
						}, {
							field : 'addTime',
							title : '投标时间',
							align : 'center'
						},{
							field : 'repayTime',
							title : '预计回款时间',
							align : 'center'
						},{
							field : 'repayYestime',
							title : '实际回款时间',
							align : 'center'
						} ] ],
						toolbar : '#timeTool'
					});
	
	
	
	loadSumTenderFees(queryParm);
});

function loadSumTenderFees(queryParm){
	$.ajax({
		url : '/finance/reportData/sumTenderFees',
		data : queryParm,
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		cache : false,
		type : 'post',
		error : function(XMLHttpRequest,
				textStatus, errorThrown) {
			alert('很遗憾，出异常了' + errorThrown);
		},
		dataType:'json',
		success : function(result) {
			if(!result){
				result = {};
				result["sumServiceFees"] = 0;
				result["sumGuaranteeFees"] = 0;
				result["sumWaitServiceFees"] = 0;
				result["sumWaitGuaranteeFees"] = 0;
			}
			
			
			$("#sumWaitServiceFees").html(result["sumWaitServiceFees"] +"元");
			$("#sumWaitGuaranteeFees").html(result["sumWaitGuaranteeFees"] +"元");
			$("#sumServiceFees").html(result["sumServiceFees"] +"元");
			$("#sumGuaranteeFees").html(result["sumGuaranteeFees"] +"元");
			var searchType = $("#searchType").val();
			if(searchType == 1){
				$("#spanSearchType2").hide();
				$("#spanSearchType1").show();
			}else if(searchType == 2){
				$("#spanSearchType1").hide();
				$("#spanSearchType2").show();
			}
			
			
		}
	});
parent.$.messager.progress('close');
}

function exportExcel() {
	var searchType = $("#searchType").val();
	
	url = "/finance/reportData/exportTenderExcel?"
	if(searchType == 1){
		if (confirm("是否导出" + $('#startTime').val() + "到" + $('#endTime').val()
				+ "的数据？")) {
			
			document.location.href = url + "startTime=" + $('#startTime').val()
					+ "&endTime=" + $('#endTime').val();
		}
	}
	else if(searchType == 2){
		if (confirm("是否导出" + $('#startTime').val() + "到" + $('#endTime').val()
				+ "的数据？")) {
			document.location.href = url + "repayYestimeStart=" + $('#startTime').val()
					+ "&repayYestimeEnd=" + $('#endTime').val();
		}
	} 
}

function queryDetail() {
	
	var searchType = $("#searchType").val();
	var queryParm;
	if(searchType == 1){
		queryParm= {
				'startTime' : $('#startTime').val(),
				'endTime' : $('#endTime').val()
			}
	}else if(searchType == 2){
		queryParm= {
				'repayYestimeStart' : $('#startTime').val(),
				'repayYestimeEnd' : $('#endTime').val()
			}
	}
	loadSumTenderFees(queryParm);
	reportdatagrid.datagrid('load', queryParm);
}
