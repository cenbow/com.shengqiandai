function toAddCurrentPage(){
	parent.$.modalDialog({
		title : '添加活期标的',
		width : 600,
		height : 350,
		href : '/current/toAddCurrentPage',
		buttons : [ {
			text : '添加',
			iconCls:'icon-save',
			handler : function() {
				var f = parent.$.modalDialog.handler.find('#addForm');
				//提交数据
				$.ajax({
					url : '/current/addCurrent',
					data : $(f).serialize(),
					type : 'post',
					cache : false,
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert('很遗憾，出异常了' + errorThrown);
					},
					beforeSend : function() {
						parent.$.messager.progress({
							title : '提示',
							text : '数据处理中，请稍后....'
						});
					},
					success : function(result) {
						parent.$.messager.progress('close');
						result = $.parseJSON(result);
						if (result.success) {
							parent.$.modalDialog.openner_dataGrid.datagrid('reload');
							parent.$.modalDialog.handler.dialog("close");
							parent.$.messager.show({
								title : '系统提示',
								msg : '操作成功',
								timeout : 5000,
								showType : 'slide'
							});
						} else {
							parent.$.modalDialog.openner_dataGrid.datagrid('reload');
							parent.$.modalDialog.handler.dialog("close");
							parent.$.messager.show({
								title : '系统提示',
								msg : '操作失败:'+result.msg,
								timeout : 5000,
								showType : 'slide'
							});
						}
					}
				});
			}
		} ]
	});
	parent.$.messager.progress('close');
}


function toTrialCurrentPage(currentId){
	parent.$.modalDialog({
		title : '添加活期标的',
		width : 600,
		height : 350,
		href : '/current/toTrialCurrentPage?currentId='+currentId,
		buttons : [ {
			text : '审核',
			iconCls:'icon-save',
			handler : function() {
				var radio = parent.$.modalDialog.handler.find('input:checked');
				var saleTime = parent.$.modalDialog.handler.find('#saleTime');
				var lowestAccount = parent.$.modalDialog.handler.find('#lowestAccount');
				var mostAccount = parent.$.modalDialog.handler.find('#mostAccount');
				//提交数据
				$.ajax({
					url : '/current/trialCurrent',
					data :{
						currentId:$(radio).attr("currentId"),
						status:$(radio).val(),
						saleTime:$(saleTime).val(),
						lowestAccount:$(lowestAccount).val(),
						mostAccount:$(mostAccount).val()
					},
					type : 'post',
					cache : false,
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert('很遗憾，出异常了' + errorThrown);
					},
					beforeSend : function() {
						parent.$.messager.progress({
							title : '提示',
							text : '数据处理中，请稍后....'
						});
					},
					success : function(result) {
						parent.$.messager.progress('close');
						result = $.parseJSON(result);
						if (result.success) {
							parent.$.modalDialog.openner_dataGrid.datagrid('reload');
							parent.$.modalDialog.handler.dialog("close");
							parent.$.messager.show({
								title : '系统提示',
								msg : '操作成功',
								timeout : 5000,
								showType : 'slide'
							});
						} else {
							parent.$.modalDialog.openner_dataGrid.datagrid('reload');
							parent.$.modalDialog.handler.dialog("close");
							parent.$.messager.show({
								title : '系统提示',
								msg : '操作失败:'+result.msg,
								timeout : 5000,
								showType : 'slide'
							});
						}
					}
				});
			}
		} ]
	});
	parent.$.messager.progress('close');
}



var finalList;
$(function() {
	finalList = $('#list').datagrid({
		url : '/current/currentList?r=' + Math.random(),
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
			field : 'currentId',
			title : '标的编号',
			width : 80,
			align : 'center'
		}, {
			field : 'currentName',
			title : '标的名称',
			width : 100,
			align : 'left'
		}, {
			field : 'sumMoney',
			title : '标的金额',
			width : 80,
			align : 'left'
		}, {
			field : 'buyMoney',
			title : '已购金额',
			width : 80,
			align : 'left',
		}, {
			field : 'apr',
			title : '利率',
			width : 80,
			align : 'left'
		}, {
			field : 'tenderCount',
			title : '投资数量',
			width : 80,
			align : 'left'
		}, {
			field : 'appointmentCount',
			title : '预约数量',
			width : 80,
			align : 'left'
		}, {
			field : 'currentRule.lowestAccount',
			title : '最小投资金额',
			width : 80,
			align : 'left'
		},{
			field : 'currentRule.mostAccount',
			title : '最大投资金额',
			width : 80,
			align : 'left'
		},{
			field : 'saleTime',
			title : '开售时间',
			width : 100,
			align : 'left'
		}, {
			field : 'soldoutTime',
			title : '售罄时间',
			width : 100,
			align : 'left'
		}, {
			field : 'verifyTime',
			title : '审核时间',
			width : 100,
			align : 'left'
		},  {
			field : 'status',
			title : '状态',
			width : 80,
			align : 'left',
			formatter : function(value, row, index) {
				if (value == 0) {
					return '<a href="javascript:toTrialCurrentPage('+row.currentId+')">初审</a>'
				} else if (value == 1) {
					return '初审通过'
				} else  if (value == 2){
					return '初审失败'
				}else  if (value == 3){
					return '<a href="#" >满标复审</a>'
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
		'status' : $('#status').val()
	});
	parent.$.messager.progress('close');
}