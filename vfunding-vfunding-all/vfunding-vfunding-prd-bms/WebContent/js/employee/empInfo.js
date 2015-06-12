var empdatagrid;
$(function() {
	empdatagrid = $('#empdatagrid')
			.datagrid(
					{
						url : '/emp/list',
						fit : true,
						fitColumns : true,
						border : false,
						pagination : true,
						pageSize : 10,
						checkOnSelect : false,
						selectOnCheck : false,
						singleSelect : true,
						pageList : [ 10, 15, 20, 25, 30 ],
						columns : [ [
								{
									field : 'empName',
									title : '中文姓名',
									width : 120,
									align : 'center'
								},
								{
									field : 'empEnName',
									title : '英文姓名',
									width : 120,
									align : 'center'
								},
								{
									field : 'empLoginName',
									title : '登录名',
									width : 120,
									align : 'center'
								},
								{
									field : 'empTel',
									title : '手机号码',
									width : 100,
									align : 'center'
								},
								{
									field : 'roleNames',
									title : '角色',
									width : 200,
									align : 'center'
								},

								{
									field : 'empSex',
									title : '性别',
									width : 60,
									align : 'center',
									formatter : function(value, row, index) {
										if (value == 1)
											return '男';
										else
											return '女';
									}
								},
								{
									field : 'empState',
									title : '状态',
									width : 100,
									align : 'center',
									formatter : function(value, row, index) {
										if (value == 0)
											return '在职';
										else
											return '离职';
									}
								},{
									field : 'updateDatad_at',
									title : '操作',
									width : 50,
									align : 'center',
									formatter : function(value, row, index) {
										var str = '';
										if ($.addOrEdit) {
											str += '[<a href="javascript: editEmp(\''
													+ row.empId
													+ '\');"  style="color: black;text-decoration:none">编辑</a>]';
										}
										return str;

									}
								} ] ],
						toolbar : '#empToolbar',
						onLoadSuccess : function() {
							
							parent.$.messager.progress('close');
						},
						onRowContextMenu : function(e, rowIndex, rowData) {
							e.preventDefault();
							$(this).datagrid('clearSelections').datagrid(
									'clearChecked');
							$(this).datagrid('selectRow', rowIndex);
							$('#menu').menu('show', {
								left : e.pageX,
								top : e.pageY
							});
						},
						onDblClickRow : function(rowIndex, rowData) {
							editEmp();
						}
					});
});



/**
 * 查询
 * 
 * @param value
 * @param name
 */
function searchEmp(value, name) {
	empdatagrid.datagrid({
		queryParams : {
			empName : value
		}
	});
}

function addEmp() {
	parent.$.modalDialog({
		title : '添加员工',
		width : 650,
		height : 380,
		href : '/emp/toAddOrEditPage',
		buttons : [ {
			text : '添加',
			iconCls:'icon-save',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = empdatagrid;
				var f = parent.$.modalDialog.handler.find('#addoreditform');
				f.submit();
			}
		} ]
	});
}

function editEmp(empId) {
	if (empId == undefined) {
		empId = empdatagrid.datagrid('getSelected').empId;
		if (empId == undefined) {
			parent.$.messager.alert("警告", "请选择数据");
			return;
		}
	}
	parent.$.modalDialog({
		title : '修改员工',
		width : 650,
		height : 380,
		href : '/emp/toAddOrEditPage?empId=' + empId,
		buttons : [ {
			text : '修改',
			iconCls:'icon-edit',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = empdatagrid;
				var f = parent.$.modalDialog.handler.find('#addoreditform');
				f.submit();
			}
		} ]
	});
	
}


