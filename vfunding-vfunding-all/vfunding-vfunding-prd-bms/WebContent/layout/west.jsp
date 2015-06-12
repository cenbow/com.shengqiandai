<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
	
	layout_west_tree_riskcontrol = '${ctx }/systemjson/riskControlTree.json';
	layout_west_tree_cashcontrol = '${ctx }/systemjson/cashControlTree.json';
	layout_west_tree_rechargecontrol = '${ctx }/systemjson/rechargeControlTree.json';
	$(function() {
		showTree('#layout_west_tree_riskcontrol', layout_west_tree_riskcontrol);
		showTree('#layout_west_tree_cashcontrol', layout_west_tree_cashcontrol);
		showTree('#layout_west_tree_rechargecontrol', layout_west_tree_rechargecontrol);
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
	
	<div title="风控审核" data-options="selected:false,iconCls:'icon-tip'" >
		<ul id="layout_west_tree_riskcontrol">
		
		</ul>
	</div>
	<div title="提现管理" data-options="selected:false,iconCls:'icon-tip'" >
		<ul id="layout_west_tree_cashcontrol">
		</ul>
	</div>
	<div title="充值管理" data-options="selected:false,iconCls:'icon-tip'" >
		<ul id="layout_west_tree_rechargecontrol">
		</ul>
	</div>
	
</div>