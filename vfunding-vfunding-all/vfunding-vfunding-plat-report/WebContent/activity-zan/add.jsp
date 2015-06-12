<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<script type="text/javascript">
	var uploadForm;
	var uploadForm2;
	$(function() {
		parent.$.messager.progress('close');
		uploadForm = $('#uploadForm').form({
			url : '/activity/zan/save',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				
				var f = $('#files').val();
				var isValid = $(this).form('validate');
				if (f == '') {
					parent.$.messager.progress('close');
					parent.$.messager.alert('提示', '请选择上传文件');
					isValid = false;
				}
			
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.messager.show({
						title : '系统提示',
						msg : '保存成功',
						timeout : 5000,
						showType : 'slide'
					});				
					parent.$.modalDialog.handler.dialog('close');					
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');

				} else {
					parent.$.messager.alert('提示', result.msg);
				}
			}
		});

		uploadForm2 = $('#uploadForm2').form({
			url : '/system/annex/addOrEditAnnex?bodyId=${bodyId }',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				if (!isEdit) {
					var f = $('#files').val();
					var isValid = $(this).form('validate');
					if (f == '') {
						parent.$.messager.progress('close');
						parent.$.messager.alert('提示', '请选择上传文件');
						isValid = false;
					}
				}

				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.messager.show({
						title : '系统提示',
						msg : '保存成功',
						timeout : 5000,
						showType : 'slide'
					});
					if (isEdit) {//编辑
						parent.$.modalDialog.handler.dialog('close');
					} else {
						clearForm();
					}
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
				} else {
					parent.$.messager.alert('提示', result.msg);
				}
			}
		});		
	});

	function uploadFile() {
		uploadForm.submit();
	}

	function uploadFile2() {
		uploadForm2.submit();
	}

	function clearForm() {
		$('#annexName').val('');
		$('#annexInfo').val('');
		$('#annexRemark').val('');
		//$('#annexType').combobox();
	}
	
	function checkUserName(userName){
		if(userName!=null && userName!='' && userName.length>0)
		 $.getJSON('/activity/zan/checkUserName/'+userName,function(data){
		      if(!data.success){
		    	  $.messager.alert('警告','该用户名不存在，请核对确认用户名['+userName+']');  
		    	  $('#userName').val('');	
		      }	else{
		    	 if(data.msg!=null && data.msg!=''){
		    		 alert(data.msg);
		    		 $('#userName').val('');
		    	 }
		      }	   	
		   });
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false"
		style="overflow: hidden; width: 400px;">
			<form id="uploadForm" method="post" enctype="multipart/form-data">
				<table class="table table-hover table-condensed"
					style="width: 380px;">
					<tr >
						<th>用户名称</th>
						<td><input type="text" id="userName" name="userName" class="easyui-validatebox" data-options="required:true"
								style=" width: 150px;" onblur="checkUserName(this.value);" ></input></td>
					</tr>
					
					<tr style="margin-top: 10px;">
						<th>增加数值</th>
						<td><input type="text" id="addBonuses"  name="addBonuses" 
								style="width: 150px;" class="easyui-numberbox" data-options="min:0,max:50,required:true"></input></td>
					</tr>
					
					<tr style="margin-top: 10px;">
						<th>附件资料</th>
						<td><input id="files" name="files" type="file" value="" /></td>
					</tr>

					<tr style="margin-top: 10px;">
						<td colspan="2" style="text-align: center;"><a
							href="javascript:uploadFile();" style="margin-top: 20px;" class="easyui-linkbutton"
							data-options="iconCls:'icon-save'">保存</a></td>
					</tr>
					
				</table>
			</form>
	</div>
</div>