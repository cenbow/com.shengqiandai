<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>充值列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/funds/rechargeList.js"></script>
<style>
.datagrid-row-over,
.datagrid-header td.datagrid-header-over {
  background: #eaf2ff;
  color: #000000;
  cursor: default;
}
.datagrid-row-selected {
  background: #eaf2ff;
  color: #000000;
}
</style>
</head>
<body>
<div id="searchTool">
	用户名:<input type="text" id="userName" name="userName" style="width:110px;"/>
	流水号:<input type="text" id="tradeNo" name="tradeNo" style="width:120px;"/>
	类型:<select id="type" name="type" style="width:90px;">
			<option value="">全部</option>
			<option value="1">在线充值</option>
			<option value="2">线下充值</option>
		</select>
	充值银行:<select id="bank" name="bank" style="width:90px;">
			<option value="" selected="selected">全部</option>
			<option value="32">国付宝</option>
			<option value="55">网银在线</option>
			<option value="54">汇潮支付</option>
			<option value="57">新浪支付</option>
			<option value="1">支付宝</option>
			<option value="36">光大银行</option>
		</select>
	状态:<select id="status" name="status" style="width:90px;">
			<option value="" selected="selected">全部</option>
			<option value="1">充值成功</option>
			<option value="2">充值失败</option>
			<option value="0">充值待审</option>
			<option value="4">等待新浪回调</option>
		</select>
	时间：<input type="text" name="startTime" id="startTime" onClick="WdatePicker()" size="10" style="width:80px;cursor:pointer;"/>
	-<input type="text" name="endTime" id="endTime" onClick="WdatePicker()" size="10" style="width:80px;cursor:pointer;"/>
		
	<a href="javascript:queryDetail();"
		class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="searchButton">查询</a>
	<a class="easyui-linkbutton" id="export"
		style="cursor: pointer" data-options="iconCls:'icon-save'">导出</a>
</div>
	<table id="list"></table>
	
	<script>
	$('#export').click(function() {
		window.location.href="/system/export/rechargeExcel?status="+
				$('#status').val()+'&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+
				'&username='+$('#userName').val()+'&tradeNo='+$('#tradeNo').val()+'&type='+$('#type').val()+'&bank='+$('#bank').val();
	});
	</script>
</body>
</html>