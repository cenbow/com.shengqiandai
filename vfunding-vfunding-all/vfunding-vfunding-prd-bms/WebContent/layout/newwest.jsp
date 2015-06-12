<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:if test="${fn:contains(employee.groupIds, '1')}">
	<script type="text/javascript">
		$.showSystem = true;
	</script>
</c:if>

<c:if test="${fn:contains(employee.groupIds, '2')}">
	<script type="text/javascript">
		$.showCaiWu = true;
	</script>
</c:if>

<c:if test="${fn:contains(employee.groupIds, '3')}">
	<script type="text/javascript">
		$.showFengKong = true;
	</script>
</c:if>

<c:if test="${fn:contains(employee.groupIds, '4')}">
	<script type="text/javascript">
		$.showBiz = true;
	</script>
</c:if>

<c:if test="${fn:contains(employee.groupIds, '5')}">
	<script type="text/javascript">
		$.showKeFu = true;
	</script>
</c:if>
<c:if test="${fn:contains(employee.groupIds, '6')}">
	<script type="text/javascript">
		$.showYunYing = true;
	</script>
</c:if>
<c:if test="${fn:contains(employee.groupIds, '7')}">
	<script type="text/javascript">
		$.showSinaDeposit = true;
	</script>
</c:if>

<script type="text/javascript">
	var layout_west_tree_url = '';
	var layout_west_tree_caiwu_url = '';
	var layout_west_tree_biz_url = '';
	var layout_west_tree_fengkong_url = '';
	var layout_west_tree_kefu_url = '';
	var layout_west_tree_yunying_url='';
	var layout_west_tree_sinadeposit_url='';
	var sessionInfo_userId = '${employee.empId}';
	if (sessionInfo_userId) {
		layout_west_tree_url = '${ctx }/resource/tree?groupId=1';
		layout_west_tree_caiwu_url = '${ctx }/resource/tree?groupId=2';
		layout_west_tree_fengkong_url = '${ctx }/resource/tree?groupId=3';
		layout_west_tree_biz_url = '${ctx }/resource/tree?groupId=4';
		layout_west_tree_kefu_url = '${ctx }/resource/tree?groupId=5';
		layout_west_tree_yunying_url='${ctx }/resource/tree?groupId=6';
		layout_west_tree_sinadeposit_url='${ctx }/resource/tree?groupId=7';
	}
	$(function() {
		if ($.showSystem){
			showTree('#layout_west_tree', layout_west_tree_url);
		}
		if ($.showCaiWu){
			showTree('#layout_west_tree_caiwu', layout_west_tree_caiwu_url);
		}
		if ($.showBiz){
			showTree('#layout_west_tree_biz', layout_west_tree_biz_url);
		}
		if ($.showFengKong){
			showTree('#layout_west_tree_fengkong',layout_west_tree_fengkong_url);
		}
		if ($.showKeFu){
			showTree('#layout_west_tree_kefu', layout_west_tree_kefu_url);
		}
		if ($.showYunYing){
			showTree('#layout_west_tree_yunying', layout_west_tree_yunying_url);
		}
		if ($.showSinaDeposit){
			showTree('#layout_west_tree_sinadeposit', layout_west_tree_sinadeposit_url);
		}
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
				if (layout_west_tree_url) {//只有刷新页面才会执行这个方法
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
				}
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
<div class="easyui-accordion" data-options="fit:true,border:false">
	<c:if test="${fn:contains(employee.groupIds, '1')}">
		<div title="系统菜单" style="padding: 5px;"border:false,isonCls:'anchor'">
			<div class="well well-small" style="height: 90%;">
				<ul id="layout_west_tree"></ul>
			</div>
		</div>
	</c:if>

	<c:if test="${fn:contains(employee.groupIds, '2')}">
		<div title="财务管理" data-options="border:false">
			<ul id="layout_west_tree_caiwu">
			</ul>
		</div>
	</c:if>
	<c:if test="${fn:contains(employee.groupIds, '3')}">
		<div title="风控管理" data-options="border:false">
			<ul id="layout_west_tree_fengkong"></ul>
		</div>
	</c:if>
	<c:if test="${fn:contains(employee.groupIds, '4')}">
		<div title="业务管理" data-options="border:false">
			<ul id="layout_west_tree_biz">
			</ul>
		</div>
	</c:if>
	<c:if test="${fn:contains(employee.groupIds, '5')}">
		<div title="客服管理" data-options="selected:false">
			<ul id="layout_west_tree_kefu">
			</ul>
		</div>
	</c:if>
	<c:if test="${fn:contains(employee.groupIds, '6')}">
		<div title="平台数据" data-options="selected:false">
			<ul id="layout_west_tree_yunying">
			</ul>
		</div>
	</c:if>
	<c:if test="${fn:contains(employee.groupIds, '7')}">
		<div title="新浪资金托管" data-options="selected:false">
			<ul id="layout_west_tree_sinadeposit">
			</ul>
		</div>
	</c:if>
</div>