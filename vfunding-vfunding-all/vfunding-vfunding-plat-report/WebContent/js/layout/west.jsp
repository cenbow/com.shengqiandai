<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
	layout_west_tree_report = '${ctx }/js/systemjson/reportTree.json';
	layout_west_tree_finance = '${ctx }/js/systemjson/financeTree.json';
	
	layout_west_tree_activity = '${ctx }/js/systemjson/activityTree.json';
	$(function() {
		showTree('#layout_west_tree_report', layout_west_tree_report);
		showTree('#layout_west_tree_finance', layout_west_tree_finance);
		showTree('#layout_west_tree_activity', layout_west_tree_activity);
	});

	function showTree(treeUlId, treeUrl) {
		$(treeUlId).tree({
			url : treeUrl,
			parentField : 'pid',
			lines : true,
			onClick : function(node) {
				if (node.attributes && node.attributes.url) {
					var url;
					if (node.attributes.url.indexOf('/') == 0) {/*如果url第一位字符是"/"，那么代表打开的是本地的资源*/
						url = '${ctx }' + node.attributes.url;
						if (url.indexOf('/druidController') == -1) {/*如果不是druid相关的控制器连接，那么进行遮罩层屏蔽*/
							parent.$.messager.progress({
								title : '提示',
								text : '数据处理中，请稍后....'
							});
						}
					} else {/*打开跨域资源*/
						url = node.attributes.url;
					}
					addTab({
						url : url,
						title : node.text,
						iconCls : node.iconCls
					});
				}
			},
			onBeforeLoad : function(node, param) {

				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});

			},
			onLoadSuccess : function(node, data) {
				parent.$.messager.progress('close');
			}
		});
	}

	function addTab(params) {
		var iframe = '<iframe src="'
				+ params.url
				+ '" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>';
		var t = $('#index_tabs');
		var opts = {
			title : params.title,
			closable : true,
			iconCls : params.iconCls,
			content : iframe,
			border : false,
			fit : true
		};
		if (t.tabs('exists', opts.title)) {
			t.tabs('select', opts.title);
			parent.$.messager.progress('close');
		} else {
			t.tabs('add', opts);
			//parent.$.messager.progress('close');
		}
	}
</script>
<div class="easyui-accordion" data-options="fit:true,border:false" >
	<div title="运营数据" data-options="selected:false,iconCls:'icon-tip'">
		<ul id="layout_west_tree_report">
		</ul>
	</div>

	<div title="财务数据" data-options="selected:false,iconCls:'icon-tip'" >
		<ul id="layout_west_tree_finance">
		
		</ul>
	</div>
	
	<div title="市场活动" data-options="selected:false,iconCls:'icon-tip'" >
		<ul id="layout_west_tree_activity">
		
		</ul>
	</div>

</div>