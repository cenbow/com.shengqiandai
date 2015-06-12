var resourceTreeGrid;
$(function() {
		resourceTreeGrid = $('#resourceTreeGrid').treegrid({
			url : '/resource/treeGrid',
			idField : 'id',
			treeField : 'name',
			parentField : 'pid',
			fit : true,
			fitColumns : false,
			border : false,
			frozenColumns : [ [ {
				title : '编号',
				field : 'id',
				width : 150,
				hidden : true
			} ] ],
			columns : [ [ {
				field : 'name',
				title : '资源名称',
				width : 250
			}, {
				field : 'url',
				title : '资源路径',
				width : 250
			}, {
				field : 'typeId',
				title : '资源类型ID',
				width : 100,
				hidden : true
			}, {
				field : 'typeName',
				title : '资源类型',
				width : 100
			}, {
				field : 'groupId',
				title : '菜单组ID',
				width : 150,
				hidden : true
			}, {
				field : 'groupName',
				title : '菜单组',
				width : 100
			}, {
				field : 'pid',
				title : '上级资源ID',
				width : 80,
				hidden : true
			}, {
				field : 'pname',
				title : '上级资源',
				width : 150
			}, {
				field : 'seq',
				title : '排序',
				width : 40
			}, {
				field : 'remark',
				title : '备注',
				width : 150
			}, {
				field : 'action',
				title : '操作',
				width : 150,
				formatter : function(value, row, index) {
					var str = '';
					if ($.canAddOrEdit) {
						str += '[<a href="javascript: editFun(\''+row.id+'\');"  style="color: black;text-decoration:none;">修改</a>]';
					}
					str+='&nbsp;&nbsp;';
					if ($.canDelete) {
						str += '[<a href="javascript: deleteFun(\''+row.id+'\');"  style="color: black;text-decoration:none;">删除</a>]';
					}
					return str;
				}
			}] ],
			toolbar : '#toolbar',
			onContextMenu : function(e, row) {
				e.preventDefault();
				$(this).treegrid('unselectAll');
				$(this).treegrid('select', row.id);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
				$(this).treegrid('collapseAll');
			}
		});
	});

	function editFun(id) {
		if (id != undefined) {
			resourceTreeGrid.treegrid('select', id);
		}
		var node = resourceTreeGrid.treegrid('getSelected');
		if (node) {
			parent.$.modalDialog({
				title : '修改资源',
				width : 650,
				height : 350,
				href : '/resource/toAddOrEditPage?resourceId=' + node.id,
				buttons : [ {
					text : '修改',
					handler : function() {
						parent.$.modalDialog.openner_treeGrid = resourceTreeGrid;
						var f = parent.$.modalDialog.handler.find('#form');
						f.submit();
					}
				} ]
			});
		}
	}

	function addFun() {
		 var p =parent.$.modalDialog({
			title : '添加资源',
			width : 650,
			height : 350,
			href : '/resource/toAddOrEditPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_treeGrid = resourceTreeGrid;
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}

	function redo() {
		var node = resourceTreeGrid.treegrid('getSelected');
		if (node) {
			resourceTreeGrid.treegrid('expandAll', node.id);
		} else {
			resourceTreeGrid.treegrid('expandAll');
		}
	}

	function undo() {
		var node = resourceTreeGrid.treegrid('getSelected');
		if (node) {
			resourceTreeGrid.treegrid('collapseAll', node.id);
		} else {
			resourceTreeGrid.treegrid('collapseAll');
		}
	}
	
	
	function deleteFun(id){
		if (id != undefined) {
			resourceTreeGrid.treegrid('select', id);
		}
		
		$.messager.confirm('确认对话框', '您确定要删除所选择的数据吗？', function(r){
			if (r){
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
		        });
			   $.getJSON('/resource/delete?id='+id,function(data){
			      if(data.success){
			      	$.messager.show({
						title:'系统消息',
						msg:'删除成功，共删除了'+data.obj+'数据',
						timeout:5000,
						showType:'slide'
					});
				   parent.$.messager.progress('close');
                   resourceTreeGrid.treegrid('reload'); 
			      }else{
			      	$.messager.show({
						title:'系统消息',
						msg:'删除失败',
						timeout:5000,
						showType:'slide'
					});					
					parent.$.messager.progress('close');
			      }
			   });
			}
		});


	}