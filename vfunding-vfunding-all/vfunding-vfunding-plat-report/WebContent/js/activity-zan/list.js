var activitydatagrid;
var downloadBasePaht;
$(function() {
	downloadBasePaht=$('#fileBasePath').val();
	activitydatagrid = $('#activitydatagrid').datagrid({
		url : '/activity/zan/list',
		fit : true,
		border : false,
		pagination : true,
		idField : 'id',
		singleSelect:true,
		checkOnSelect : false,
		selectOnCheck : false,
		pageSize : 10,
		pageList : [ 10, 15, 20, 25, 30 ],		
		columns : [ [ {
			field : 'id',
			title : 'ID',
			width : 30,
			hidden : true
		}, {
			field : 'userName',
			title : '用户名',
			width : 150
		}, {
			field : 'userPhone',
			title : '手机',
			width : 150
		},{
			field : 'activityBonuses',
			title : '已得红包',
			width : 100
		}, {
			field : 'addBonuses',
			title : '本次红包',
			width : 100
		}, {
			field : 'addTimeStr',
			title : '本次兑换时间',
			width : 150
		}, {
			field : 'status',
			title : '状态',
			width : 80,
			formatter : function(value, row, index) {
				if (value == 'N')
					return '待确认';
				else if(value == 'Y') {
					return '已确认';
				}
			}
		}, {
			field : 'fileUrls',
			title : '附件信息',
			width : 150,
			formatter : function(value, row, index) {
				var str='';
				if(value){
					var fileName=value.substring(value.lastIndexOf('.')+1).toLowerCase();
					if(fileName=='jpg' || fileName=='png'){
						str='<a href="'+downloadBasePaht+'/ori/'+value+'" target="_blank">查看图片</a>'
					}else{
					}
				}else{
					str='暂无附件';
				}
				
				return str;
			}
		},
		{
			field : 'action',
			title : '操作',
			width : 150,
			formatter : function(value, row, index) {
				var str='';
				if(row.status=='N'){
					str = '<a href="javascript:doAffirm('+row.id+');">确认结算</a>';
				}else{
					str = '该数据已清算';
				}	
				return str;	
			}
		}] ],
		toolbar : '#activityToolbar',
		onLoadSuccess : function() {
			parent.$.messager.progress('close');
		}
	});
});

/**
 * 查询
 * 
 * @param value
 * @param name
 */
function search(value,name){
	activitydatagrid.datagrid({
		queryParams: {
			userName: value
		}
	});
}

function add(){
	parent.$.modalDialog({
		title : '添加统计',
		width : 350,
		height : 180,
		href : '/activity/zan/toAddPage'
	});
	parent.$.modalDialog.openner_dataGrid=activitydatagrid;
}

function doAffirm(id){
	$.messager.confirm('确认对话框', '您确定要清算吗？', function(r){
		if (r){
			   $.getJSON('/activity/zan/doAffirm/'+id,function(data){
			      if(data.success){
			      	   parent.$.messager.show({
							title : '系统提示',
							msg : '清算成功',
							timeout : 5000,
							showType : 'slide'
						});
			      	 activitydatagrid.datagrid('reload');
			      }		   	
			   });
			}
		});
}
