<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	var addBorrowform;
	$(function() {
		parent.$.messager.progress('close');
		$('#dlg').dialog('close');
		addBorrowform = $('#addBorrowform').form({
			url : '/system/currentBorrow/addBorrow',
			onSubmit : function(param) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				if(parseFloat($('#limitMoney').val())<parseFloat($('#money').val())){
					alert('续标金额过大，最多续标'+$('#limitMoney').val());
					parent.$.messager.progress('close');
					return false;
				}
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
					parent.$.messager.show({
						title : '系统提示',
						msg : '操作成功',
						timeout : 5000,
						showType : 'slide'
					});
				} else {
					parent.$.messager.show({
						title : '系统提示',
						msg : result.msg,
						timeout : 5000,
						showType : 'slide'
					});
				}
				parent.$.modalDialog.openner_dataGrid.datagrid('reload');
				parent.$.modalDialog.handler.dialog('close');
			}
		});
		parent.$.messager.progress('close');
		
		
		$('#status').change(function(){
			if(this.value == 1){
				$('#first,#second,#three').show();
				$('#timeMsg').html('入池时间');
				
				$('#apr,#bFee,#gFee,#endTime').val('');
			} else if(this.value == 2){
				$('#first,#second,#three').hide();
				$('#timeMsg').html('减池时间');
				
				$('#apr,#bFee,#gFee,#endTime').val('1');
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<form id="addBorrowform" method="post" style="text-align: center;">
			<input type="hidden" name="limitMoney" id="limitMoney" value="${money}"/>
			<table class="table table-hover table-condensed"
				style="text-align: center; padding: 10px; margin-left: 35px; margin-top: 15px;">
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">类型：</th>
					<td>
						<select name="status" id="status">
							<option value="1" selected="selected">续标</option>
							<option value="2">不续</option>
						</select>
					</td>
					<th style="vertical-align: middle; text-align: right;">续标金额：</th>
					<td><input type="text" id="money" name="money"
						class="easyui-validatebox" value=""
						data-options="required:true,validType:['String','length[0,15]']"/></td>
				</tr>
				<tr id="first">
					<th style="vertical-align: middle; text-align: right;">活期宝编号：</th>
					<td><input type="text" id="currentId" name="currentId"
						data-options="required:true" class="easyui-validatebox" value="${currentId}"/></td>
					<th style="vertical-align: middle; text-align: right;">年利率：</th>
					<td><input type="text" id="apr" name="apr"
						class="easyui-validatebox"
						data-options="required:true,validType:['String','length[0,32]']" /></td>
				</tr>
				<tr style="height: 15px;">
				</tr>
				<tr style="text-align: center;" id="second">
					<th style="vertical-align: middle; text-align: right;">担保费：</th>
					<td><input type="text" id="gFee" name="gFee"
						data-options="required:true,validType:['Integer','length[0,11]']"
						class="easyui-validatebox" /></td>

					<th style="vertical-align: middle; text-align: right;">服务费：</th>
					<td><input type="text" id="bFee" name="bFee"
						 data-options="required:true,validType:['Integer','length[0,11]']" class="easyui-validatebox"/></td>
				</tr>
				<tr style="height: 15px;">
				</tr>
				<tr style="text-align: center;" id="three">
					<th style="vertical-align: middle; text-align: right;">类型：</th>
					<td>
						<select name="style" id="style">
							<option value="1">抵押标</option>
							<option value="2">信用标</option>
						</select>
					</td>
					<th style="vertical-align: middle; text-align: right;">合同截止时间：</th>
					<td><input type="text" name="endTime" id="endTime" data-options="required:true"
					 readonly="readonly" onClick="WdatePicker()" size="10" style="width:205px;cursor:pointer;background-color:none;"/></td>
				</tr>
				<tr style="text-align: center;" id="four">
					<th style="vertical-align: middle; text-align: right;" id="timeMsg">入池时间：</th>
					<td><input type="text" id="intoTime" name=intoTime readonly="readonly"
						data-options="required:true,validType:['String','length[0,32]']"
						class="easyui-validatebox" value="${time}"></td>
				</tr>
			</table>
		</form>
	</div>
</div>
	