//产品列表
$(function() {
	//alert('11');
	$('#dg').datagrid(
					{
						url : '/system/borrow/theTrialBorrowList',
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
									title : '产品id',
									align : 'center',
									width : 20
								},
								{
									field : 'productType',
									title : '产品类型',
									align : 'center',
									width : 20
								},
								{
									field : 'name',
									title : '产品名称',
									align : 'center',
									width : 20
								},
								{
									field : 'apr',
									title : '收益率',
									align : 'center',
									width : 20
								},
								{
									field : 'timeLimit',
									title : '期限',
									align : 'center',
									width : 20
								},
								{
									field : 'addtime',
									title : '跟新时间',
									align : 'center',
									width : 20
								},
								{
									field : 'bzTags',
									title : '标签',
									align : 'center',
									width : 20
								},
								{
									field : 'account',
									title : '募集金额',
									align : 'center',
									width : 20
								},
								{
									field : 'ksje',
									title : '可售金额',
									align : 'center',
									width : 20,
									formatter:function(value,row,index){
										var res=(row.account-row.accountYes);
										//alert(row.accountYes);
										return res;
									}
								},
								{
									field : 'statusName',
									title : '上线状态',
									align : 'center',
									width : 20,
									formatter: function(value,row,index){

										var f=row.status;
										
										var res="";
										if(f==1){
											res="上线首页"
										}else if(f==2){
											res="已上线";
										}else if(f==3){
											res="已下线";
										}
										return res;
									}
								} ,
								{
									field : 'jyStatus',
									title : '交易状态',
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