var roledatagrid;
$(function() {
	roledatagrid = $('#roledatagrid').datagrid({
		url : '/role/list',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'roleId',
		checkOnSelect : false,
		selectOnCheck : false,
		pageSize : 10,
		pageList : [ 10, 15, 20, 25, 30 ],
		columns : [ [
				{
					field : 'roleId',
					title : 'ID',
					width : 30,
					hidden : true
				},
				{
					field : 'roleName',
					title : '名称',
					width : 50
				},
				{
					field : 'roleState',
					title : '状态',
					width : 100,
					formatter : function(value, row, index) {
						if(value==0)
							return '正常';
						else
							return '禁用';
					}
				},
				{
					field : 'action',
					title : '操作',
					width : 150,
					formatter : function(value, row, index) {
						 var str='';
						 if ($.canChange) {
							  if(row.roleState==0){
								  str+=' [<a href="javascript:changeStatus(\''+row.roleId+'\',1);"   style="color: black;text-decoration:none;">禁用</a>]';
							  }else{
								  str+=' [<a href="javascript:changeStatus(\''+row.roleId+'\',0);"   style="color: black;text-decoration:none;">启用</a>]';
							  }
						 }
						 
						  str+='&nbsp;&nbsp;';
						  
						  if($.canAddOrEdit){
							  str += '[<a href="javascript: editRole(\''+row.roleId+'\');"  style="color: black;text-decoration:none;">修改</a>]';
						  }
						  str+='&nbsp;&nbsp;';
	 
						  if($.canGrant){
							  str += '[<a href="javascript: grantFun(\''+row.roleId+'\');"  style="color: black;text-decoration:none;">查看资源</a>]';
						  }
						return str;	
					}
				} ] ],
				toolbar : '#roleToolbar',
				onLoadSuccess : function() {
					parent.$.messager.progress('close');
					//$(this).datagrid('tooltip');
				},
				onRowContextMenu : function(e, rowIndex, rowData) {
					e.preventDefault();
					$(this).datagrid('clearSelections').datagrid('clearChecked');
					$(this).datagrid('selectRow', rowIndex);
					$('#menu').menu('show', {
						left : e.pageX,
						top : e.pageY
					});
				}
	});
});

/**
 * 查询
 * 
 * @param value
 * @param name
 */
function searchRole(value,name){
	roledatagrid.datagrid({
		queryParams: {
			roleName: value
		}
	});
}
/**
 * 角色分配资源
 */
function grantFun(id){
	if (id == undefined) {
	    id=roledatagrid.datagrid('getSelected').roleId;
	    if(id == undefined){
	    	parent.$.messager.alert("警告","请选择数据");
			return;
	    }	
	}
	parent.$.modalDialog({
		title : '角色授权',
		width : 500,
		height : 450,
		href : '/role/toGrantPage?roleId=' +id,
		buttons : [ {
			text : '授权',
			handler : function() {
				parent.$.modalDialog.openner_treeGrid = roledatagrid;
				var f = parent.$.modalDialog.handler.find('#form');
				f.submit();
			}
		} ]
	});
}


function changeStatus(id,status){
	$.getJSON('/role/changeStatus',{roleId:id,status:status},function(result){
		if(result.success){
			roledatagrid.datagrid('reload');
			parent.$.messager.show({
				title:'系统提示',
				msg:'操作成功',
				timeout:5000,
				showType:'slide'
			});
		}else{
			parent.$.messager.alert("警告",result.msg);
		}
	});
}

function addRole(){	
	parent.$.modalDialog({
		title : '添加角色',
		width : 350,
		height : 150,
		href : '/role/toAddOrEditPage',
		buttons : [ {
			text : '添加',
			iconCls:'icon-save',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = roledatagrid;
				var f = parent.$.modalDialog.handler.find('#roleaddoreditform');
				f.submit();
			}
		} ]
	});
	
	
	
}

function editRole(roleId){
	if (roleId == undefined) {
		roleId=roledatagrid.datagrid('getSelected').roleId;
	    if(roleId == undefined){
	    	parent.$.messager.alert("警告","请选择数据");
			return;
	    }
	}
	
	parent.$.modalDialog({
		title : '修改角色',
		width : 350,
		height : 150,
		href : '/role/toAddOrEditPage?roleId='+roleId,
		buttons : [ {
			text : '添加',
			iconCls:'icon-edit',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = roledatagrid;
				var f = parent.$.modalDialog.handler.find('#roleaddoreditform');
				f.submit();
			}
		} ]
	});
}

