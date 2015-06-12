var reportdatagrid;
$(function() {
	reportdatagrid = $('#reportdatagrid')
			.datagrid(
					{
						url : '/finance/reportData/tenderList',
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
							field : 'typeId',
							title : '用户类型',
							width : 130,
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
							width : 130,
							align : 'center',
							formatter : function(value, row, index) {
								
									return value+"%";
							
							}
						}, {
							field : 'serviceFees',
							title : '平台服务费',
							width : 130,
							align : 'center'
						}, {
							field : 'guaranteeFees',
							title : '担保服务费',
							width : 150,
							align : 'center'
						}, {
							field : 'addTime',
							title : '投标时间',
							width : 150,
							align : 'center'
						} ] ],
						toolbar : '#timeTool',
						onLoadSuccess : function() {
							$
									.ajax({
										url : '/finance/reportData/sumTenderFees',
										data : {
											startTime : $('#startTime').val(),
											endTime : $('#endTime').val()
										},
										cache : false,
										type : 'post',
										error : function(XMLHttpRequest,
												textStatus, errorThrown) {
											alert('很遗憾，出异常了' + errorThrown);
										},
										success : function(result) {
											result = $.parseJSON(result);
											if (result != null) {
												if (result.sumServiceFees != null) {
													$("#sumServiceFees")
															.html(
																	result.sumServiceFees
																			+ "元");
												} else {
													$("#sumServiceFees").html(
															"0元");
												}

												if (result.sumServiceFees != null) {
													$("#sumGuaranteeFees")
															.html(
																	result.sumGuaranteeFees
																			+ "元");
												} else {
													$("#sumGuaranteeFees")
															.html("0元");
												}
											} else {
												$("#sumServiceFees").html("0元");
												$("#sumGuaranteeFees").html(
														"0元");
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
		url = "/finance/reportData/exportTenderExcel?"
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