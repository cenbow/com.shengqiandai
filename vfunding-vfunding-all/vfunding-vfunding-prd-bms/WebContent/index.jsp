<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>生钱袋综合业务管理平台</title>
<jsp:include page="inc.jsp"></jsp:include>
<script type="text/javascript">
	var index_tabs;
	var index_tabsMenu;
	var index_layout;
	$(function() {
		index_layout = $('#index_layout').layout({
			fit : true
		});
		/*index_layout.layout('collapse', 'east');*/
		index_tabs = $('#index_tabs')
				.tabs(
						{
							fit : true,
							border : false,
							onContextMenu : function(e, title) {
								e.preventDefault();
								index_tabsMenu.menu('show', {
									left : e.pageX,
									top : e.pageY
								}).data('tabTitle', title);
							}
						});

		index_tabsMenu = $('#index_tabsMenu').menu(
				{
					onClick : function(item) {
						var curTabTitle = $(this).data('tabTitle');
						var type = $(item.target).attr('title');

						if (type === 'refresh') {
							index_tabs.tabs('getTab', curTabTitle).panel(
									'refresh');
							return;
						}

						if (type === 'close') {
							var t = index_tabs.tabs('getTab', curTabTitle);
							if (t.panel('options').closable) {
								index_tabs.tabs('close', curTabTitle);
							}
							return;
						}

						var allTabs = index_tabs.tabs('tabs');
						var closeTabsTitle = [];

						$.each(allTabs, function() {
							var opt = $(this).panel('options');
							if (opt.closable && opt.title != curTabTitle
									&& type === 'closeOther') {
								closeTabsTitle.push(opt.title);
							} else if (opt.closable && type === 'closeAll') {
								closeTabsTitle.push(opt.title);
							}
						});

						for (var i = 0; i < closeTabsTitle.length; i++) {
							index_tabs.tabs('close', closeTabsTitle[i]);
						}
					}
				});
	});
</script>
</head>
<body>
	<div id="index_layout">
		<div data-options="region:'north',href:'${ctx }/layout/north.jsp',border:false"
			style="height: 70px; overflow: hidden;" class=""></div>
		<div
			data-options="region:'west',href:'${ctx }/layout/newwest.jsp',split:true,iconCls:'icon-tip'"
			title="菜单导航" style="width: 200px; overflow: hidden;"></div>
		<div data-options="region:'center'" title="欢迎使用生钱袋综合业务管理平台"
			style="overflow: hidden;">
			<div id="index_tabs" style="overflow: hidden;">
				<div title="首页" data-options="border:false"
					style="overflow: hidden;">
					<iframe src="" frameborder="0"
						style="border: 0; width: 100%; height: 98%;"></iframe>
				</div>
			</div> 
		</div>
		 <!--   <div data-options="region:'east',href:'${ctx }/js/layout/east.jsp'"
			title="代办任务" style="width: 280px; overflow: hidden;"></div>-->	
	</div>

	<div id="index_tabsMenu" style="width: 120px; display: none;">
		<div title="refresh" data-options="iconCls:'transmit'">刷新</div>
		<div class="menu-sep"></div>
		<div title="close" data-options="iconCls:'delete'">关闭</div>
		<div title="closeOther" data-options="iconCls:'delete'">关闭其他</div>
		<div title="closeAll" data-options="iconCls:'delete'">关闭所有</div>
	</div>

</body>
</html>