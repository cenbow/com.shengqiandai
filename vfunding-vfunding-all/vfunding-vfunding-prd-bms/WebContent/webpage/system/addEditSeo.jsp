<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	var addSeoform;
	$(function() {
		addSeoform = $('#addSeoform').form({
			url : '/system/seo/addSeoRecord',
			onSubmit : function(param) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				//$('#url').css("border","2px solid red")
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
		<form id="addSeoform" method="post" style="text-align: center;">
			<input type="hidden" name="status" id="status" value="${status}"/>
			<c:if test="${status == true}">
				<!-- 更新的时候才传id -->
				<input type="hidden" name="id" id="id" value="${record.id}"/>
			</c:if>
			<table class="table table-hover table-condensed" style="text-align: center; padding: 10px; margin-top: 15px;">
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: center; width:80px;">名&nbsp;&nbsp;称：</th>
					<td><input type="text" id="jspName" name="jspname" data-options="required:true,validType:['String','length[0,250]']"
					style="width:634px;" placeholder="请输入该页面名称"
						class="easyui-validatebox" value="${record.jspname}"></td>

				</tr>
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: center; width:80px;">标&nbsp;&nbsp;题：</th>
					<td><input type="text" id="title" name="title" data-options="required:true,validType:['String','length[0,250]']"
						style="width:634px;" placeholder="请输入该页面标题"
						class="easyui-validatebox" value="${record.title}">
						</td>
				</tr>
				<c:if test="${status == true}">
					<!-- 更新不允许修改url -->
					<tr style="text-align: center;">
						<th style="vertical-align: middle; text-align: center; width:80px;">路&nbsp;&nbsp;径：</th>
						<td>
							<input type="text" id="url" name="url" readonly="readonly"
							style="width:634px;" placeholder="请输入该页面在浏览器中唯一地址"
							class="easyui-validatebox" value="${record.url}">
						</td>
					</tr>
				</c:if>
				<c:if test="${status == false}">
					<!-- 新增 -->
					<tr style="text-align: center;">
						<th style="vertical-align: middle; text-align: center; width:80px;">路&nbsp;&nbsp;径：</th>
						<td>
							<input type="text" id="url" name="url" data-options="required:true,validType:['String','length[0,250]','checkUniqueUrl']"
							style="width:634px;" placeholder="请输入该页面在浏览器中唯一地址"
							class="easyui-validatebox" value="${record.url}">
						</td>
					</tr>
				</c:if>
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: center; width:80px;">关键字：</th>
					<td>
					<textarea style="margin: 0px 0px 10px; width: 634px; height: 75px;" id="keywords" name="keywords">${record.keywords}</textarea>
					</td>
				</tr>
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: center; width:80px;">描&nbsp;&nbsp;述：</th>
					<td>
						<textarea style="margin: 0px 0px 10px; width: 634px; height: 95px;" id="description" name="description">${record.description}</textarea>
					</td>
				</tr>
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: center; width:80px;">编&nbsp;&nbsp;码：</th>
					<td>
						<select id="charset" name="charset" style="width:150px;">
							<option value="utf-8" ${record.charset=='utf-8'?'selected':''}>utf-8(默认)</option>
							<option value="gbk" ${record.charset=='gbk'?'selected':''}>gbk</option>
							<option value="ISO-8859-1" ${record.charset=='ISO-8859-1'?'selected':''}>ISO-8859-1</option>
						</select>
						<span style="margin-left:50px;font-weight: bold;">模块：</span>
						<select id="type" name="type" style="width:150px;">
							<option value="1" ${record.type==1?'selected':''}>首页</option>
							<option value="2" ${record.type==2?'selected':''}>我要投资</option>
							<option value="3" ${record.type==3?'selected':''}>我要借款</option>
							<option value="4" ${record.type==4?'selected':''}>我的账户</option>
							<option value="5" ${record.type==5?'selected':''}>关于我们</option>
							<option value="6" ${record.type==6?'selected':''}>帮助中心</option>
							<option value="6" ${record.type==7?'selected':''}>小微资讯</option>
							<option value="0" ${record.type==0?'selected':''}>其他</option>
						</select>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
