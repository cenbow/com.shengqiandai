<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 引入jQuery -->
<%-- <script type="text/javascript" src="${ctx }/js/jquery-1.8.3.js"></script> --%>
<script type="text/javascript">
	var rechargeForm;
	$(function() {
		rechargeForm = $('#rechargeForm').form({
			url : '/system/funds/addRecharge',
			onSubmit : function(param) {
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
				} else {
					parent.$.messager.show({
						title : '系统提示',
						msg : result.msg,
						timeout : 5000,
						showType : 'slide'
					});
				}
			}
		});
		parent.$.messager.progress('close');
		
		$('#fresh').click(function(){
			$("#rechargeCode").attr({"src" : "/system/funds/getGenImage/68/32?id="
					+ Math.random() * 1000,"alt" : "看不清，换一张"
			});
		});
	});
</script>
<form id="rechargeForm" name="rechargeForm">
<table class="table table-hover table-condensed" style="text-align: center; padding: 10px;margin-top: 15px;">
	<tr style="text-align: center;">
		<th style="vertical-align: middle; text-align: right;">用户名：</th>
		<td>
			<input type="text" id="username" name="username"
			data-options="required:true,validType:['String','length[0,32]']"
			class="easyui-validatebox">
		</td>
	</tr>
	<tr style="text-align: center;">
		<th style="vertical-align: middle; text-align: right;">类型：</th>
		<td>
			线下充值
		</td>
	</tr>
	<tr style="text-align: center;">
		<th style="vertical-align: middle; text-align: right;">金额：</th>
		<td>
			<input type="text" id="money" name="money"
			data-options="required:true,validType:['String','length[0,32]']"
			class="easyui-validatebox" value="${article.name}">
		</td>
	</tr>
	<tr style="text-align: center;">
		<th style="vertical-align: middle; text-align: right;">备注：</th>
		<td>
			<input type="text" id="remark" name="remark"
			data-options="required:true,validType:['String','length[0,50]']"
			class="easyui-validatebox" value="${article.name}">
		</td>
	</tr>
	<tr style="text-align: center;">
		<th style="vertical-align: middle; text-align: right;">验证码：</th>
		<td>
			<input type="text" id="code" name="code"
			data-options="required:true,validType:['String','length[0,4]']"
			class="easyui-validatebox"/>
		</td>
	</tr>
	<tr style="text-align: center;">
		<th style="vertical-align: middle; text-align: right;">&nbsp;</th>
		<td>
			<a id="fresh">
		       <img id="rechargeCode" alt="看不清?换一张" src="${pageContext.request.contextPath }/system/funds/getGenImage/68/32">
		   </a>
		</td>
	</tr>
</table>
</form>