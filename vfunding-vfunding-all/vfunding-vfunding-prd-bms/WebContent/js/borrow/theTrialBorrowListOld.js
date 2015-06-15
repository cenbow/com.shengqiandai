var dg;
$(function() {
	$('#aprStr').html($('#apr').val() - $('#gfee').val() - ($('#bfee').val()==''?0:$('#bfee').val()));
	
	$('#gfee,#bfee').keyup(function(){
		$('#aprStr').html($('#apr').val() - $('#gfee').val() - ($('#bfee').val()==''?0:$('#bfee').val()));
	});
	
	dg = $('#dg')
			.datagrid(
					{
						url : '/system/borrow/theTrialBorrowList',
						fit : true,
						fitColumns : true,
						border : false,
						pagination : true,
						fit : true,
						fitColumns : true,
						pageSize : 15,
						checkOnSelect : false,
						selectOnCheck : false,
						singleSelect : true,
						pageList : [ 10, 15, 20, 25, 30 ],
						queryParams : {
							'status' : $('#status').val()
						},
						columns : [ [
								{
									field : 'id',
									title : '借款编号',
									align : 'center',
									width : 30
								},
								{
									field : 'username',
									title : '发标用户',
									align : 'left',
									width : 50
								},
								{
									field : 'ownername',
									title : '借款客户',
									align : 'left',
									width : 30
								},
								{
									field : 'name',
									title : '借款标题',
									align : 'left',
									width : 120,
									formatter : function(value, row, index) {
										if (row.biaoType == 'fast') {
											return '<span style="color: red;">【抵押标】</span>'
													+ value;
										} else if (row.biaoType == 'xin') {
											return '<span style="color: red;">【信用标】</span>'
													+ value;
										} else if (row.biaoType == 'jin') {
											return '<span style="color: red;">【净值标】</span>'
													+ value;
										} else if (row.biaoType == 'lz') {
											return '<span style="color: red;">【流转标】</span>'
													+ value;
										} else if(row.biaoType == 'huodong'){
											return '<span style="color: red;">【活动标】</span>'
											+ value;
										} else if(row.biaoType == 'tian'){
											return '<span style="color: red;">【天标】</span>'
											+ value;
										}
									}
								},
								{
									field : 'account',
									title : '借款金额',
									align : 'left',
									width : 50,
									formatter : function(value, row, index) {
										return value + "元";
									}
								},
								{
									field : 'apr',
									title : '年利率',
									align : 'left',
									width : 30,
									formatter : function(value, row, index) {
										return value + "%";
									}
								},
								{
									field : 'timeLimit',
									title : '借款期限',
									align : 'left',
									width : 40,
									formatter : function(value, row, index) {
										if (value > 0) {
											return value + "个月";
										} else {
											return row.timeLimitDay + "天"
										}
									}
								},
								{
									field : 'mortgageTypeid',
									title : '抵押类型',
									width : 40,
									align : 'center',
									formatter : function(value, row, index) {
										if (value == 1) {
											return "汽车抵押";
										} else if (value == 2) {
											return "债权抵押";
										}
									}
								},
								{
									field : 'addTimeStr1',
									title : '发标时间',
									width : 60,
									align : 'center'
								},
								{
									field : 'status',
									title : '操作',
									align : 'center',
									width : 50,
									formatter : function(value, row, index) {
										if (value == 0) {
											if ($.canEdit) {
												return "<a href='javascript:trialing(" + row.id + ")'>进行初审</a>";
											} else {
												return "无权限";
											}
										} else if (value == 1) {
											return "初审通过";
										} else if (value == 2) {
											return "初审未通过";
										}
									}
								} ] ],
						toolbar : '#searchTool',
						onLoadSuccess : function() {
							parent.$.messager.progress('close');
							parent.$.modalDialog.openner_dataGrid = dg;
						}
					});

});
function queryDetail() {
	dg.datagrid('load', {
		'username' : $('#userName').val(),
		'status' : $('#status').val(),
		'biaoType' : $('#biaoType').val()
	});
	parent.$.messager.progress('close');
}

function trialing(id) {
	parent.$.modalDialog({
		title : '初审详细信息',
		width : 1000,
		height : 500,
		href : '/system/borrow/theTrialBorrowDetail?id=' + id
	});
	parent.$.messager.progress('close');
	// parent.$.modalDialog.openner_dataGrid = trialdatagrid;
}

function commitFrom() {
	var status_ = $('input[name=status]:checked').val();
	//2014-12-26 louchen
	if(status_==null||status_==""||status_==undefined){
		alert("请选择审核通过或不通过")
		return false;
	}
	if(status_ == 1){ //初审通过才做判断
		if($('#brand').val() == ''){
			alert('汽车品牌不能为空')
			return false;
		} else if($('#buyMoney').val() == ''){
			alert('购买价不能为空')
			return false;
		} else if($('#assessMoney').val() == ''){
			alert('评估价不能为空')
			return false;
		}
	}
	if($('#bfee').val() == ''){
		alert('服务费率不能为空')
	} else if($('#gfee').val() == ''){
		alert('担保费率不能为空')
	} else if($('#remark').val() == ''){
		alert('备注不能为空')
	} else {
		$.ajax({
			url : '/system/borrow/theTrialBorrow',
			data : $('#finalForm').serialize(),
			cache : false,
			type : 'post',
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
					parent.$.messager.show({
						title : '系统提示',
						msg : '操作成功',
						timeout : 5000,
						showType : 'slide'
					});
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog("close");
				} else {
					parent.$.messager.show({
						title : '系统提示',
						msg : result.msg,
						timeout : 5000,
						showType : 'slide'
					});
				}
			}
		});
	}
}
function saveForm(){
	if($('#brand').val() == '' && $('#buyMoney').val() == '' && $('#assessMoney').val() == '' && $('#remark').val() == ''){
		alert('至少填一项才能保存');
	} else {
		$.ajax({
			url : '/system/borrow/saveBorrowForm',
			data : $('#finalForm').serialize(),
			cache : false,
			type : 'post',
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
					parent.$.messager.show({
						title : '系统提示',
						msg : '操作成功',
						timeout : 5000,
						showType : 'slide'
					});
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog("close");
				} else {
					parent.$.messager.show({
						title : '系统提示',
						msg : result.msg,
						timeout : 5000,
						showType : 'slide'
					});
				}
			}
		});
	}
	
	
	
}