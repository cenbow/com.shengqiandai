<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>应用参数列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>

<script type="text/javascript">
		$(function(){
			parent.$.messager.progress('close');
		});
	
	</script>
</head>
<body>
	<div style="margin: 20px 0;"></div>

	<table id="dg" class="easyui-datagrid" title="应用参数列表"
		style="width: 1000px; height: auto", idField:"key"
		data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				url: '${pageContext.request.contextPath}/appParm/list',
				method: 'get',
				onClickRow: onClickRow
			">
		<thead>
			<tr>
				<th data-options="field:'key',width:94 , editor:'text' ,required:true">key</th>
				<th data-options="field:'value',width:300 ,editor:'text' ,required:true">value</th>
				<th data-options="field:'description',width:300 ,editor:'text'">描述</th>
				<!-- 
				<th data-options="field:'attr1',width:250,editor:'textbox'">Attribute</th>
				<th data-options="field:'status',width:60,align:'center',editor:{type:'checkbox',options:{on:'P',off:''}}">Status</th>
				 -->
			</tr>
		</thead>
	</table>

	<div id="tb" style="height: auto">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" onclick="append()">新增</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">移除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存所有修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-undo',plain:true" onclick="reject()">撤销所有修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-search',plain:true"
			onclick="getChanges()">查看修改未保存的条数</a>
	</div>

	<script type="text/javascript">
		var editIndex = undefined;
		function endEditing() {
			if (editIndex == undefined) {
				return true
			}
			if ($('#dg').datagrid('validateRow', editIndex)) {
				var ed = $('#dg').datagrid('getEditor', {
					index : editIndex,
					field : 'key'
				});
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickRow(index) {
			if (editIndex != index) {
				if (endEditing()) {
					$('#dg').datagrid('selectRow', index).datagrid('beginEdit',
							index);
					editIndex = index;
				} else {
					$('#dg').datagrid('selectRow', editIndex);
				}
			}
		}
		function append() {
			if (endEditing()) {
				$('#dg').datagrid('appendRow', {});
				editIndex = $('#dg').datagrid('getRows').length - 1;
				$('#dg').datagrid('selectRow', editIndex).datagrid('beginEdit',
						editIndex);
			}
		}
		function removeit() {
			if (editIndex == undefined) {
				return
			}
			$('#dg').datagrid('cancelEdit', editIndex).datagrid('deleteRow',
					editIndex);
			editIndex = undefined;
		}
		function accept() {
			if (endEditing()) {
				var $dg = $('#dg')
				var rows = $dg.datagrid('getChanges');
				if (rows.length) {
					var inserted = $dg.datagrid('getChanges', "inserted");
					var deleted = $dg.datagrid('getChanges', "deleted");
					var updated = $dg.datagrid('getChanges', "updated");

					
					var effectRow={};
					var insertedList = [];
					var updatedList = [];
					var deletedList = [];
					
					if (inserted.length) {
						for(var i=0;i<inserted.length;i++){
							insertedList.push(inserted[i]);
						}
						effectRow["inserted"] = inserted;
					}
					if (deleted.length) {
						
						for(var i=0;i<deleted.length;i++){
							deletedList.push(deleted[i]);
						}
						
						effectRow["deleted"] = deleted;
					}
					if (updated.length) {
						
						
						for(var i=0;i<updated.length;i++){
							updatedList.push(updated[i]);
						}
						effectRow["updated"] = updated;
					}
					$.ajax({
						type:"post",
						url:"${pageContext.request.contextPath}/appParm/saveDataGrid",	
						data:JSON.stringify(effectRow),
						dataType:"json",
						contentType:'application/json;charset=utf-8',
						success:function(d){
							$.messager.alert("提示", d+"条数据被修改!");
							$dg.datagrid('acceptChanges');
							$dg.datagrid('reload');
						}
					})
					
				}
			}
		}
		function reject() {
			$('#dg').datagrid('rejectChanges');
			editIndex = undefined;
		}
		function getChanges() {
			var rows = $('#dg').datagrid('getChanges');
			alert(rows.length + ' 条记录改变未接受！');
		}
	</script>
</body>
</html>