<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	var resourceTree;
	$(function() {
		resourceTree = $('#resourceTree')
				.tree(
						{
							url : '${pageContext.request.contextPath}/resource/allTree',
							parentField : 'pid',
							//lines : true,
							checkbox : true,
							onLoadSuccess : function(node, data) {
								var ids = $.stringToList('${role.resourceIds}');
								if (ids.length > 0) {
									for (var i = 0; i < ids.length; i++) {
										if (resourceTree.tree('find', ids[i])) {
											resourceTree.tree('check',
													resourceTree.tree('find',
															ids[i]).target);
										}
									}
								}
								$('#roleGrantLayout').layout('panel', 'center')
										.panel(
												'setTitle',
												$.formatString(
														'[{0}]角色可以访问的资源',
														'${role.roleName}'));
								parent.$.messager.progress('close');
							},
							cascadeCheck : false
						});

		$('#form').form({
			url : '/role/grant',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				var checknodes = resourceTree.tree('getChecked');
				var ids = [];
				if (checknodes && checknodes.length > 0) {
					for (var i = 0; i < checknodes.length; i++) {
						ids.push(checknodes[i].id);
					}
				}
				$('#resourceIds').val(ids);
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.handler.dialog('close');
					parent.$.modalDialog.openner_treeGrid.datagrid('reload');
					parent.$.messager.show({
						title : '系统提示',
						msg : '授权成功',
						timeout : 5000,
						showType : 'slide'
					});
				}
			}
		});
	});
</script>
<div id="roleGrantLayout" class="easyui-layout"
	data-options="fit:true,border:false">
	<div data-options="region:'center'" title="系统资源"
		style="padding: 10px;"><!-- overflow: hidden; -->

		<form id="form" method="post">
			<input name="roleId" type="hidden" class="span2"
				value="${role.roleId}" readonly="readonly">
			<ul id="resourceTree"></ul>
			<input id="resourceIds" name="resourceIds" type="hidden" />
		</form>

	</div>

</div>