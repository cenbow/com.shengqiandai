<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<script type="text/javascript">
	var addoreditform;
	$(function() {
		addoreditform = $('#addoreditform').form({
			url : '/emp/addOrEdit',
			onSubmit : function(param) {
				param.addOrEditRoleIds = $("#roleCombo").combobox('getValues');
				var empsex = $("#empSexCombo").combobox('getValue');
				var empisnew = $("#empNewCombo").combobox('getValue');
				var empState = $("#empStateCombo").combobox('getValue');
				if (empsex == "") {
					empsex = 0;
				}
				if (empisnew == "") {
					empisnew = 0;
				}
				if (empState == "") {
					empState = 0;
				}
				$("#empSex").val(empsex);
				$("#empAnewemp").val(empisnew);
				$("#empState").val(empState);
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});

				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog('close');
					parent.$.messager.show({
						title : '系统提示',
						msg : '操作成功',
						timeout : 5000,
						showType : 'slide'
					});
				}
			}
		});
		$('#roleCombo')
				.combobox(
						{
							valueField : 'roleId',
							textField : 'roleName',
							url : '${pageContext.request.contextPath }/role//comboxgridNoPage',
							onLoadSuccess : function() {
								var data = $('#roleCombo').combobox('getData');
								if (data.length > 0) {
									if ('${roleIds}'.length > 0) {
										$('#roleCombo').combobox('setValues',
												'${roleIds}'.split(','));
									} else {
										$('#roleCombo').combobox('setValue',
												data[0].id);
									}
								}
							}
						});

		$("#empNewCombo")
				.combobox(
						{
							valueField : 'id',
							textField : 'text',
							url : '${pageContext.request.contextPath }/js/jsonData/yesOrNoCombobox.json'
						});
		parent.$.messager.progress('close');

	});

	function saveEmp() {
		var empsex = $("#empSexCombo").combobox('getValue');
		var empisnew = $("#empNewCombo").combobox('getValue');
		var empState = $("#empState").combobox('getValue');
		if (empsex == "") {
			empsex = 0;
		}
		if (empisnew == "") {
			empisnew = 0;
		}
		if (empState == "") {
			empState = 0;
		}
		$("#empSex").val(empsex);
		$("#empAnewemp").val(empisnew);
		$("#empState").val(empState);
		addoreditform.submit();
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="addoreditform" method="post" style="text-align: center;">
			<input type="hidden" value="${emp.empId}" name="empId"> <input
				type="hidden" value="${emp.roleIds}" name="roleIds">
			<table class="table table-hover table-condensed"
				style="text-align: center; ">
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">登录帐号:</th>
					<td style="vertical-align: middle;"><input type="text" id="empLoginName" name="empLoginName"
						data-options="required:true,validType:['String','length[0,32]','checkLoginName']"
						class="easyui-validatebox" placeholder="请输入帐号"
						value="${emp.empLoginName}"></td>
					<th style="vertical-align: middle; text-align: right;">英文姓名:</th>
					<td style="vertical-align: middle;"><input type="text" id="empEnName" name="empEnName"
						class="easyui-validatebox" placeholder="请输入英文姓名"
						data-options="validType:['String','length[0,32]']"
						value="${emp.empEnName}" /></td>
				</tr>
			
				<tr>
					<th style="vertical-align: middle; text-align: right;">中文姓名:</th>
					<td style="vertical-align: middle;"><input type="text" id="empName" name="empName"
						data-options="required:true,validType:['String','length[0,16]']"
						class="easyui-validatebox" placeholder="请输入中文姓名"
						value="${emp.empName}" /></td>
					<th style="vertical-align: middle; text-align: right;">员工角色:</th>
					<td style="vertical-align: middle;"><input type="text" id="roleCombo" editable='false'
						multiple='true' data-options="required:true" /></td>

				</tr>
			

				<tr style="text-align: center;">

					<th style="vertical-align: middle; text-align: right;">手机号码:</th>
					<td style="vertical-align: middle;"><input type="text" id="empTel" name="empTel"
						data-options="validType:['Integer','length[0,11]']"
						class="easyui-validatebox" placeholder="请输入员工手机号码"
						value="${emp.empTel}" /></td>

					<th style="vertical-align: middle; text-align: right;">电子邮箱:</th>
					<td style="vertical-align: middle;"><input type="text" id="empEmail" name="empEmail"
						data-options="validType:'email'" class="easyui-validatebox"
						placeholder="请输入电子邮箱" value="${emp.empEmail}" /></td>
				</tr>
			

				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">家庭住址:</th>
					<td style="vertical-align: middle;"><input type="text" id="empFamilytel" name="empFamilytel"
						class="easyui-validatebox" placeholder="请输入家庭住址"
						value="${emp.empFamilytel}" /></td>

					<th style="vertical-align: middle; text-align: right;">家庭电话:</th>
					<td style="vertical-align: middle;"><input type="text" id="empFamilyaddr" name="empFamilyaddr"
						class="easyui-validatebox" placeholder="请输入家庭电话"
						value="${emp.empFamilyaddr}" /></td>


				</tr>
			
				<tr>
					<th style="vertical-align: middle; text-align: right;">员工性别:</th>
					<td style="vertical-align: middle;"><input type="text" id="empSexCombo" panelHeight='auto'
						editable='false' class="easyui-combobox" value="${emp.empSex}" 
						data-options=" valueField: 'id', textField: 'text',  url: '${pageContext.request.contextPath }/js/jsonData/sexCombobox.json'" /></td>
					<th style="vertical-align: middle; text-align: right;">入职时间:</th>
					<td style="vertical-align: middle;"><input id="empTimeStr" type="text" onClick="WdatePicker()"
						name="empTime" class="Wdate" value="${emp.empTimeStr}"
						editable='false' /></td>
				</tr>

			
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">曾今在职:</th>
					<td style="vertical-align: middle;"><input type="text" id="empNewCombo"
						class="easyui-combobox" panelHeight='auto' editable='false'
						value="${emp.empAnewemp}" /></td>


					<th style="vertical-align: middle; text-align: right;">员工状态:</th>
					<td style="vertical-align: middle;"><input type="text" id="empStateCombo" panelHeight='auto'
						editable='false' class="easyui-combobox" value="${emp.empState}"
						data-options=" valueField: 'id', textField: 'text',  url: '${pageContext.request.contextPath }/js/jsonData/stateCombobox.json'" />

					</td>

				</tr>
				<tr>
					<td colspan="4"><input type="hidden" id="empSex" name="empSex"
						value="0"> <input type="hidden" id="empState"
						name="empState" value="0">
						<input type="hidden" id="empAnewemp"
						name="empAnewemp" value="0"> 
						</td>
				</tr>

			</table>
		</form>
	</div>
</div>
