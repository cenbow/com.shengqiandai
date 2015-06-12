<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div style="height:30px;"></div>
<form id="exportForm" name="exportForm" action="/system/export/exportBorrow">
<div style="width:150px;margin-left:70px;">
<select id="status" name="status" style="width:150px;">
	<option value="-1" selected="selected">即将到期的借款</option>
	<option value="0">未还借款</option>
	<option value="1">已还借款</option>
	<option value="2">逾期借款</option>
</select>
</div>
</form>