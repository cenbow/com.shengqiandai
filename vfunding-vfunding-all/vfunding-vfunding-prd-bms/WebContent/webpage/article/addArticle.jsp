<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	var addArticleform;
	$(function() {
		$('#delete').click(function(){
			$('#imgBanner').hide();
			$('#imgBanner').removeAttr('src');
		});
		$('#name').focus();
		
		addArticleform = $('#addArticleform').form({
			url : '/system/article/addArticle',
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
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog('close');
					parent.$.messager.show({
						title : '系统提示',
						msg : '操作失败',
						timeout : 5000,
						showType : 'slide'
					});
				}
			}
		});
		parent.$.messager.progress('close');
		
		$('#siteId').change(function(){
			if(this.value == 85){
				$('#pic_1').show();
				$('#pic_2').show();
				$('#biaoqian').hide();
			} else if(this.value == 95){
				$('#pic_1').hide();
				$('#pic_2').hide();
				$('#biaoqian').hide();
			} else if(this.value == 108 || this.value == 109){
				$('#pic_1').hide();
				$('#pic_2').hide();
				$('#biaoqian').show();
			}
		});
		if($('#siteId').val() == 95){
			$('#pic_1').hide();
			$('#pic_2').hide();
		}
		if($('#siteId').val() == 108 || $('#siteId').val() == 109){
			$('#biaoqian').show();
		}
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<form id="addArticleform" method="post" style="text-align:center;" enctype="multipart/form-data">
			<input type="hidden" name="type" id="type" value="${type}"/>
			<c:if test="${type == true}">
				<!-- 更新的时候才传id -->
				<input type="hidden" name="id" id="id" value="${article.id}"/>
			</c:if>
			<table class="table table-hover table-condensed" style="text-align: center; padding: 10px; margin-left: 35px; margin-top: 15px;">
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">标题：</th>
					<td><input type="text" id="name" name="name" style="width:300px;"
						data-options="required:true,validType:['String','length[0,32]']"
						class="easyui-validatebox" value="${article.name}"></td>
					<th style="vertical-align: middle; text-align: right;">所属栏目：</th>
					<td>
						<select id="siteId" name="siteId">
							<option value="85" ${article.siteId==85?'selected':''}>媒体报道</option>
							<option value="95" ${article.siteId==95?'selected':''}>网站公告</option>
							<option value="108" ${article.siteId==108?'selected':''}>小微新闻</option>
							<option value="109" ${article.siteId==109?'selected':''}>小微攻略</option>
						</select>
					</td>
				</tr>
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">文章来源：</th>
					<td><input type="text" id="source" name="source" style="width:300px;"
						class="easyui-validatebox" value="${article.source}"></td>

					<th style="vertical-align: middle; text-align: right;">作者：</th>
					<td><input type="text" id="author" name="author" value="${article.author}"
						data-options="required:true,validType:['String','length[0,32]']"/></td>
				</tr>
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">状态：</th>
					<td>
						<select id="status" name="status" style="width:150px;">
							<option value="1" ${article.status==1?'selected':''}>显示</option>
							<option value="0" ${article.status==0?'selected':''}>隐藏</option>
						</select>
					</td>
					
					<th style="vertical-align: middle; text-align: right;" id="pic_1">图片:</th>
					<c:if test="${type == false}"> <!-- 插入 -->
						<td id="pic_2">
							<input type="file" name="pic"/>
						</td>
					</c:if>
					<c:if test="${type == true}"> <!-- 更新 -->
						<td id="pic_2">
							<c:if test="${not empty article.litpicPath}">
								<img id="imgBanner" src="${article.litpicPath}" style="width: 200px; height: 50px;" />
							</c:if>
							<input type="button" value="更新" id="delete"/>
							<input type="file" name="pic"/>
						</td>
					</c:if>
					
				</tr>
			</table>
			<table class="table table-hover table-condensed" style="text-align: center; margin-left: 35px; ">
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">内容简介:</th>
					<td>
						<textarea style="width:450px;height:40px;float:left;" name="summary">${article.summary}</textarea>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<div id="biaoqian" style="display:none;width:350px;float:left;margin-left:30px;margin-top: 10px;">
						<span style="font-size: 12px;font-weight: bold;">标签:&nbsp;&nbsp;</span>
						<input type="text" name="mark1" class="easyui-validatebox" value="${mark1}" style="width:80px;">
						<input type="text" name="mark2" class="easyui-validatebox" value="${mark2}" style="width:80px;">
						<input type="text" name="mark3" class="easyui-validatebox" value="${mark3}" style="width:80px;">
						</div>
					</td>
				</tr>
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">SEO标题:</th>
					<td>
						<input type="text" name="seoTitle" value="${article.seoTitle}" style="width:360px;margin-top:8px;float:left;margin-top:18px;"/>
						<span style="font-size:12px;font-weight:bold;float:left;margin-top:25px;margin-left:30px;">SEO关键字:&nbsp;&nbsp;</span>
						<textarea style="width:350px;height:40px;float:left;margin-top:10px;" name="seoKeywords">${article.seoKeywords}</textarea>
					</td>
				</tr>
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">SEO描述:</th>
					<td>
						<textarea style="width:450px;height:40px;float:left;margin-top:5px;" name="seoDescription">${article.seoDescription}</textarea>
					</td>
				</tr>
				
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">内容:</th>
					<td colspan="2">
						<textarea class="easyui-kindeditor" id="easyui_ditor" style="width:730px;height:200px;" name="content" id="content">${article.content}</textarea>
					    <link rel="stylesheet" href="${pageContext.request.contextPath }/js/article/kindeditor-4.1.7/themes/default/default.css" />
						<script charset="utf-8" src="${pageContext.request.contextPath }/js/article/kindeditor-4.1.7/kindeditor.js"></script>
						<script charset="utf-8" src="${pageContext.request.contextPath }/js/article/kindeditor-4.1.7/lang/zh_CN.js"></script>
						<script charset="utf-8" src="${pageContext.request.contextPath }/js/article/kindEditor.js"></script>
						<script>
							parent.$.messager.progress('close');
						</script>
					</td>
				</tr>
				<tr style="height: 15px;">
				</tr>
			</table>
		</form>
	</div>
</div>
