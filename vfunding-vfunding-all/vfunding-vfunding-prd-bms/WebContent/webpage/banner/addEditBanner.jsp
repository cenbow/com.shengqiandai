<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	var addEditBannerform;
	$(function() {
		$('#delete').click(function(){
			$('#imgBanner').hide();
			$('#imgBanner').removeAttr('src');
		});
		
		addEditBannerform = $('#addEditBannerform').form({
			url : '/system/banner/addBanner',
			onSubmit : function(param) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				
				if(($('#banner').val() == null || $('#banner').val()=='') && $('#imgBanner').attr('src') == null){
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
		<form id="addEditBannerform" method="post" style="text-align: center;" enctype="multipart/form-data">
			<input type="hidden" name="type" id="type" value="${type}"/>
			<c:if test="${type == true}">
				<!-- 更新的时候才传id -->
				<input type="hidden" name="spic" id="spic" value="${scrollpic.pic}"/>
				<input type="hidden" name="id" id="id" value="${scrollpic.id}"/>
			</c:if>
			<table class="table table-hover table-condensed" style="text-align: center; padding: 10px; margin-left: 35px; margin-top: 15px;">
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">标题：</th>
					<td><input type="text" id="name" name="name"
						class="easyui-validatebox" value="${scrollpic.name}">
						</td>
					<th style="vertical-align: middle; text-align: right;">路径：</th>
					<td>
						<input type="text" id="url" name="url" 
						class="easyui-validatebox" value="${scrollpic.url}">
					</td>
				</tr>
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">摘要：</th>
					<td><input type="text" id="summary" name="summary"
						class="easyui-validatebox" value="${scrollpic.summary}"></td>

					<th style="vertical-align: middle; text-align: right;">排序：</th>
					<td>
						<input type="radio" name="location" value="1" ${(sorder==1||sorder==''||sorder==null)?'checked':''}/>置顶
						&nbsp;
						<input type="radio" name="location" value="2" ${sorder==2?'checked':''}/>第二
						&nbsp;
						<input type="radio" name="location" value="3" ${sorder==3?'checked':''}/>第三
						&nbsp;
						<input type="radio" name="location" value="4" ${sorder==4?'checked':''}/>第四
						&nbsp;
						<input type="radio" name="location" value="5" ${sorder==5?'checked':''}/>第五
						&nbsp;
						<input type="radio" name="location" value="6" ${sorder==6?'checked':''}/>第六
						&nbsp;
						<input type="radio" name="location" value="7" ${sorder==7?'checked':''}/>第七
						&nbsp;
						<input type="radio" name="location" value="8" ${sorder==8?'checked':''}/>第八
					</td>
				</tr>
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">状态：</th>
					<td>
						<select id="status" name="status" style="width:150px;">
							<option value="1" ${scrollpic.status==1?'selected':''}>显示</option>
							<option value="0" ${scrollpic.status==0?'selected':''}>隐藏</option>
						</select>
					</td>
					<th style="vertical-align: middle; text-align: right;">类型：</th>
					<td>
						<select id="typeId" name="typeId" style="width:150px;">
							<option value="1" ${scrollpic.typeId==1?'selected':''}>网站</option>
							<option value="2" ${scrollpic.typeId==2?'selected':''}>手机</option>
							<option value="0" ${scrollpic.typeId==0?'selected':''}>宣传</option>
						</select>
					</td>
				</tr>
				<c:if test="${type == false}"><!-- 新增 -->
					<tr style="text-align: center;">
						<th style="vertical-align: middle; text-align: right;">图片：</th>
						<td>
							<input type="file" id="banner" name="banner"/>
						</td>
						<th style="vertical-align: middle; text-align: right;"></th>
						<td></td>
					</tr>
				</c:if>
				<c:if test="${type == true}">
					<tr style="text-align: center;">
						<th style="vertical-align: middle; text-align: right;">图片：</th>
						<td colspan="2">
							<img id="imgBanner" src="${scrollpic.pic}" style="width: 400px; height: 200px;" />
							<input type="button" value="更新" id="delete"/>
						</td>
					</tr>
					<th style="vertical-align: middle; text-align: right;"></th>
					<td>
						<input type="file" id="banner" name="banner"/>
					</td>
				</c:if>
			</table>
		</form>
	</div>
</div>
