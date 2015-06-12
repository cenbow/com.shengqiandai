var test;
$(function() {
	test = $('#collateral').datagrid({
		url : '/system/collateral/collateralList',
		fit : true,
		border : false,
		pagination : true,
		pageSize : 10,
		checkOnSelect : false,
		selectOnCheck : false,
		singleSelect : true,
		pageList : [ 10, 15, 20, 25, 30 ],
		frozenColumns : [ [{
			field : 'ownerName',
			title : '姓名',
			align : 'center'
		}, {
			field : 'name',
			title : '标题',
			align : 'center'
		}, {
			field : 'ownerCardId',
			title : '身份证号',
			align : 'center'
		}, {
			field : 'ownerMobile',
			title : '手机',
			align : 'center'
		}, {
			field : 'ownerAddress',
			title : '籍贯',
			align : 'center'
	
		}]],
	columns : [ [ {
			field : 'carStrutNum',
			title : '识别代码',
			align : 'center'
		}, {
			field : 'carLicense',
			title : '车牌号',
			align : 'center'
		}, {
			field : 'carNumber',
			title : '发动机号',
			align : 'center'
		}, {
			field : 'registerDate',
			title : '注册日期',
			align : 'center'
		}, {
			field : 'certificationDate',
			title : '发证日期',
			align : 'center'
		}, {
			field : 'checkYalidDate',
			title : '检验有效日期',
			align : 'center'
		} ] ],
		toolbar : '#empToolbar',
		onLoadSuccess : function() {

			parent.$.messager.progress('close');
		}
//		
	});
});