//列表
$(function() {
	//alert('11');
	$('#dg').datagrid(
					{
						url : '/system/sqdPayLog/listPayLog',
						fit : true,
						fitColumns : true,
						height:500,
						pagination : true,
						
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
									title : 'payLogId',
									align : 'center',
									width : 20
								},
								{
									field : 'productId',
									title : '产品id',
									align : 'center',
									width : 20
								},
								{
									field : 'userId',
									title : '用户id',
									align : 'center',
									width : 20
								},
								{
									field : 'tradeNo',
									title : '订单No',
									align : 'center',
									width : 20
								},
								{
									field : 'payMoney',
									title : '付款金额',
									align : 'center',
									width : 20
								},
								{
									field : 'resultPay',
									title : '支付状态',
									align : 'center',
									width : 20
								},
								{
									field : 'remark',
									title : '备注',
									align : 'center',
									width : 20
								},
								{
									field : 'addDate',
									title : '支付日期',
									align : 'center',
									width : 20
								},
								{
									field : 'addIp',
									title : '支付IP',
									align : 'center',
									width : 20
								},
								{
									field : 'opt',
									title : '操作',
									align : 'center',
									width : 20,
									formatter:function(value,row,index){
										var res="操作";
										return res;
									}
								}   
								
								
								] ],
						toolbar : '#searchToolBar',
						onLoadSuccess : function() {
							parent.$.messager.progress('close');
							parent.$.modalDialog.openner_dataGrid = dg;
						}
					});

});

//打开添加dialog
function openAddDialog(){
	$("#add-dlg").dialog('setTitle','添加');
	$("#add-dlg").dialog('open');
}
//添加产品
function add(){
	alert('add');
}


function addBorrow(){
	parent.$.modalDialog({
		title : '添加借款标',
		width : 1200,
		height : 600,
		href : '/system/borrow/addBorrowPage',
		buttons : [ {
			text : '添加',
			iconCls:'icon-save',
			handler : function() {
				var f = parent.$.modalDialog.handler.find('#addBorrowform');
				f.submit();
			}
		} ]
	});
}


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