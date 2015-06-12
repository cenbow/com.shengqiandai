<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
	function changeThemeFun(themeName) {
		if ($.cookie('easyuiThemeName')) {
			$('#layout_north_pfMenu').menu(
					'setIcon',
					{
						target : $('#layout_north_pfMenu div[title='
								+ $.cookie('easyuiThemeName') + ']')[0],
						iconCls : 'emptyIcon'
					});
		}
		$('#layout_north_pfMenu').menu('setIcon', {
			target : $('#layout_north_pfMenu div[title=' + themeName + ']')[0],
			iconCls : 'tick'
		});

		var $easyuiTheme = $('#easyuiTheme');
		var url = $easyuiTheme.attr('href');
		var href = url.substring(0, url.indexOf('themes')) + 'themes/'
				+ themeName + '/easyui.css';
		$easyuiTheme.attr('href', href);

		var $iframe = $('iframe');
		if ($iframe.length > 0) {
			for (var i = 0; i < $iframe.length; i++) {
				var ifr = $iframe[i];
				try {
					$(ifr).contents().find('#easyuiTheme').attr('href', href);
				} catch (e) {
					try {
						ifr.contentWindow.document
								.getElementById('easyuiTheme').href = href;
					} catch (e) {
					}
				}
			}
		}

		$.cookie('easyuiThemeName', themeName, {
			expires : 7
		});

	};

	function logoutFun(b) {		
		if (b) {
			$.getJSON('${pageContext.request.contextPath}/loginOut', function(result) {
				   if(result.success){		   
					   parent.location.replace('${pageContext.request.contextPath}/index');					  
				   }				   		  		
				});   
			} else {
				/*$('#sessionInfoDiv').html('');
				$('#loginDialog').dialog('open');*/
			}
	}

	function editCurrentUserPwd() {
		parent.$.modalDialog({
					title : '修改密码',
					width : 350,
					height : 300,
					href : '${pageContext.request.contextPath}/system/emp/editCurrentUserPwdPage',
					buttons : [ {
						text : '修改',
						handler : function() {
							var f = parent.$.modalDialog.handler
									.find('#editCurrentUserPwdForm');
							f.submit();
						}
					} ]
				});
	}
</script>
 <div id="sessionInfoDiv" style="position: absolute; right: 0px; top: 0px;" class="alert alert-info">
	<c:if test="${employee.empId != null}">【<strong>${employee.empName}</strong>】<strong id="taskInfo"></strong></c:if>
</div>
<div style="position: absolute;right: 0px; bottom: 0px;" data-options="border:false">
	<a href="javascript:void(0);" class="easyui-menubutton"
		data-options="menu:'#layout_north_pfMenu',iconCls:'cog'">更换皮肤</a>   
		<a href="javascript:void(0);" class="easyui-menubutton"
		data-options="menu:'#layout_north_zxMenu',iconCls:'cog'">注销</a>
</div>
<div id="layout_north_pfMenu" style="width: 120px; display: none;">
	<div onclick="changeThemeFun('default');" title="default">default</div>
	<div onclick="changeThemeFun('gray');" title="gray">gray</div>
	<div onclick="changeThemeFun('metro');" title="metro">metro</div>

	<div class="menu-sep"></div>

</div>

<div id="layout_north_zxMenu" style="width: 100px; display: none;">
	<div onclick="editCurrentUserPwd();">修改密码</div>
	<div class="menu-sep"></div>
	<div onclick="logoutFun(true);">退出系统</div>
</div>