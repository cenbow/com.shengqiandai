<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
    var mytodotask;
	$(function() {
		
		$('#layout_east_onlinePanel').panel({
			tools : [ {
				iconCls : 'database_refresh',
				handler : function() {
					
				}
			} ]
		});
		
		
		mytodotask=$('#mytodotask').datagrid({
			url : '/system/workflow/task/todo/list',
			fit : true,
			fitColumns : true,
			border : false,		
			idField : 'id',		
			columns : [ [ {
				field : 'id',
				title : '编号',
				width : 30,
				hidden : true
			}, {
				field : 'pdname',
				title : '流程名称',
				width : 80
			}, {
				field : 'name',
				title : '当前任务',
				width : 80
			}, {
				field : 'createTime',
				title : '创建时间',
				width : 80
			}] ],
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
				$(this).datagrid('tooltip');
			}
		});
		

		$('#layout_east_calendar').calendar({
			fit : true,
			current : new Date(),
			border : false,
			onSelect : function(date) {
				$(this).calendar('moveTo', new Date());
			}
		});

		

	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	
	<div data-options="region:'north',border:false" style="overflow: hidden;height: 300px;">
		<div id="layout_east_onlinePanel" data-options="fit:true,border:false" title="SYPRO收到的代办任务列表">
			<div id="mytodotask" style="margin: 3px;">
			</div>	
		</div>
	</div>
	<div data-options="region:'center',border:false" style=" height: 180px;overflow: hidden;">
		<div id="layout_east_calendar"></div>
	</div>
</div>