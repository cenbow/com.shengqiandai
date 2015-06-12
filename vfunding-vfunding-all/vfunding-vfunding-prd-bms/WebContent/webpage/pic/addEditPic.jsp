<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	var addEditBannerform;
	$(function() {
		$('#delete').click(function(){
			$('#img').hide();
			$('#img').removeAttr('src');
		});
		
		addEditPicform = $('#addEditPicform').form({
			url : '/system/pic/addPic',
			onSubmit : function(param) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				
				if(($('#pic').val() == null || $('#pic').val()=='') && $('#img').attr('src') == null){
					alert('请上传图片');
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
		parent.$.messager.progress('close');
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<form id="addEditPicform" method="post" style="text-align: center;" enctype="multipart/form-data">
			<input type="hidden" name="type" id="type" value="${type}"/>
			<table class="table table-hover table-condensed" style="text-align: center; padding: 10px; margin-left: 35px; margin-top: 15px;">
				<c:if test="${type == true}">
					<tr style="text-align: center;">
						<th style="vertical-align: middle; text-align: right;">picKey：</th>
						<td><input type="text" id="pickey" name="pickey"
							class="easyui-validatebox" readonly = "readonly" value="${pic.pickey}">
							</td>
					</tr>
				</c:if>
				<c:if test="${type == false}">
					<tr style="text-align: center;">
						<th style="vertical-align: middle; text-align: right;">picKey：</th>
						<td><input type="text" id="pickey" name="pickey"
							class="easyui-validatebox" value="${pic.pickey}">
							</td>
					</tr>
				</c:if>
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">描述：</th>
					<td><input type="text" id="description" name="description"
						class="easyui-validatebox" value="${pic.description}">
						</td>
				</tr>
				<c:if test="${type == false}"><!-- 新增 -->
					<tr style="text-align: center;">
						<th style="vertical-align: middle; text-align: right;">图片：</th>
						<td>
							<input type="file" id="pic" name="pic"/>
						</td>
						<th style="vertical-align: middle; text-align: right;"></th>
						<td></td>
					</tr>
				</c:if>
				<c:if test="${type == true}">
					<tr style="text-align: center;">
						<th style="vertical-align: middle; text-align: right;">图片：</th>
						<td colspan="2">
							<img id="img" src="${pic.url}" style="width: 400px; height: 200px;" />
							<input type="button" value="更新" id="delete"/>
						</td>
					</tr>
					<th style="vertical-align: middle; text-align: right;"></th>
					<td>
						<input type="file" id="pic" name="pic"/>
					</td>
				</c:if>
			</table>
		</form>
	</div>
</div>
