<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {

		$('#iconCls')
				.combobox(
						{
							valueField : 'value',
							textField : 'text',
							data : $.iconData,
							formatter : function(v) {
								return $
										.formatString(
												'<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}',
												v.value, v.value);
							},
							onLoadSuccess : function() {
								$(this).combobox('setValue',
										'${resource.icon }');
							}
						});
		$('#pid').combotree({
			url : '/resource/allTree',
			parentField : 'pid',
			lines : true,
			panelHeight : 'auto',
			value : '${resource.pid}',
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
				var t = $('#pid').combotree('tree');
				t.tree('collapseAll');
			}
		});

		$('#form').form(
				{
					url : '/resource/addOrEdit',
					onSubmit : function() {
						//alert($("#form").serialize());
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
							//
							//parent.layout_west_tree.tree('reload');
							var dataObj = result.obj;
							if (result.action == 'edit') {
								parent.$.modalDialog.openner_treeGrid.treegrid(
										'update', {
											id : dataObj.id,
											row : dataObj
										});

							} else {
								parent.$.modalDialog.openner_treeGrid
										.treegrid('reload');

							}
							//var node = $('#tt').treegrid('getSelected');
							/*parent.$.modalDialog.openner_treeGrid.treegrid('append',{
								parent: dataObj.id,  // the node has a 'id' value that defined through 'idField' property
								data: dataObj
							});*/

							parent.$.modalDialog.handler.dialog('close');
							parent.$.messager.show({
								title : '系统提示',
								msg : '保存成功',
								timeout : 5000,
								showType : 'slide'
							});
						}
					}
				});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">

		<form id="form" method="post">
			<div style="margin-top: 10px;">
				<table class="table table-hover table-condensed">
					<tr>
						<td>资源名称 </td>
						<td><input name="id" type="hidden" class="span2"
							value="${resource.id}" readonly="readonly">
						<input name="name" type="text" placeholder="请输入资源名称"
							class="easyui-validatebox " data-options="required:true"
							value="${resource.name}" style="width: 194px; height: 25px;"></td><td>资源路径
						</td>
						<td style="margin-left: 10px;"><input name="url" type="text"
							placeholder="请输入资源路径" class="easyui-validatebox "
							value="${resource.url}" style="width: 195px; height: 25px;"></td>
					</tr>
				
					<tr>
						<td>上级资源
						</td>
						<td style="margin-left: 10px;"><select id="pid" name="pid"
							style="width: 209px; height: 29px;"></select><img
							src="${pageContext.request.contextPath}/style/images/extjs_icons/cut_red.png"
							onclick="$('#pid').combotree('clear');" /></td>
						<td>资源类型
						</td>
						<td style="margin-left: 10px;"><select name="typeId"
							class="easyui-combobox"
							data-options="width:209,height:29,editable:false,panelHeight:'auto'">
								<c:forEach items="${typeList}" var="resourceType">
									<option value="${resourceType.id}"
										<c:if test="${resourceType.id == resource.typeId}">selected="selected"</c:if>>${resourceType.name}</option>
								</c:forEach>
						</select></td>
					</tr>
				
					<tr>
						<td>排序
						</td>
						<td style="margin-left: 10px;"><input name="seq"
							value="${resource.seq }" class="easyui-numberspinner"
							style="width: 209px; height: 29px;" required="required"
							data-options="editable:false,min:1"></td>
						<td>菜单组
						</td>
						<td style="margin-left: 10px;"><select name="groupId"
							class="easyui-combobox"
							data-options="width:209,height:29,editable:false,panelHeight:'auto'">
								<c:forEach items="${groupList}" var="group">
									<option value="${group.id}"
										<c:if test="${group.id == resource.groupId}">selected="selected"</c:if>>${group.name}</option>
								</c:forEach>
						</select></td>
					</tr>
					
					<tr>
						<td>菜单图标
						</td>
						<td colspan="3"><input id="iconCls" name="icon"
							style="width: 537px; height: 29px;" data-options="editable:false" /></td>
					</tr>
					
					<tr>
						<td>备注
						</td>
						<td colspan="3"><textarea name="remark"
								style="width: 525px; height: 50px;" >${resource.remark }</textarea></td>
					</tr>

				</table>
			</div>
		</form>
	</div>
</div>