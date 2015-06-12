<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>8jie天标列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>

<script>
var dayList;
$(function() {
	parent.$.messager.progress('close');
	dayList = $('#list')
			.datagrid(
					{
						url : '/system/dayborrow/dayBorrowList',
						isField : "id",
						pagination : true,//显示分页
						pageSize : 15,//分页大小
						pageList : [ 10, 15, 20 ],//每页的个数
						checkOnSelect : false,
						selectOnCheck : false,
						singleSelect : true,
						fit : true,//自动补全
						fitColumns : true,
						border : false,
						queryParams : {'status' : $('#status').val()},
						columns : [ [
					            {
					            	field : 'borrowId',
					            	title : '序号',
					            	align : 'center'
					            },
								{
									field : 'borrowName',
									title : '借款标题',
									align : 'center'
								},
								{
									field : 'account',
									title : '借款金额',
									align : 'center',
									formatter : function(value, row, index) {
										return value+"元";
									}
								},
								{
									field : 'accountYes',
									title : '已投金额',
									align : 'center',
									formatter : function(value, row, index) {
										return value+"元";
									}
								},
								{
									field : 'timeLimit',
									title : '期限',
									align : 'center',
									formatter : function(value, row, index) {
										return value+"天";
									}
								},
								{
									field : 'repaymentAccount',
									title : '应还金额',
									align : 'center',
									formatter : function(value, row, index) {
										return value+"元";
									}
								},
								{
									field : 'apr',
									title : '年利率',
									align : 'center',
									formatter : function(value, row, index) {
										return value + "%";
									}
								},
								{
									field : 'tenderTimes',
									title : '投标次数',
									align : 'center'
								},
								{
									field : 'addtime',
									title : '发标时间',
									align : 'center'
								},
								{
									field : 'verifyTime',
									title : '初审时间',
									align : 'center',
									formatter : function(value, row, index) {
										return value=='1970-01-01 08:00:00'?'-':value;
									}
								},
								{
									field : 'repaymentTimeStr',
									title : '应还时间',
									align : 'center',
									formatter : function(value, row, index) {
										return value=='1970-01-01 08:00:00'?'-':value;
									}
								},
								{
									field : 'status',
									title : '操作',
									align : 'center',
									formatter : function(value, row, index) {
										return value == 3 ? "<a id='goFinal_"+row.id+"'>还款</a>"
												: (value == 5 ? '撤标'
														: (value == 1 ? '招标中'
																: (value == 7?'流标'
																		:(value == 2?'初审不通过'
																				:(value == 5?'撤标':(value == 0?'等待初审':'error'))))));
									}
								} ] ],
						toolbar : '#searchTool',
						onLoadSuccess : function() {
							parent.$.messager.progress('close');
							$('a[id^="goFinal_"]').click(function() {
								if(confirm('确定还款?')){
									var id = this.id.split('goFinal_')[1];
									$.ajax({
										url : "/system/dayborrow/repayDayBorrow",
										type : "post",
										data : {
											"repaymentId" : id
										},
										success : function(result) {
											result = $.parseJSON(result);
											parent.$.messager.show({
												title : '系统提示',
												msg : result.msg,
												timeout : 5000,
												showType : 'slide'
											});
											queryDetail();
											dayList.openner_dataGrid.datagrid('reload');
											parent.$.modalDialog.handler.dialog("close");
										}
									});
									parent.$.messager.progress('close');
								} else {
									return false;
								}
								
							});
							parent.$.modalDialog.openner_dataGrid=dayList;

						}
					});

})
function queryDetail() {
	dayList.datagrid('load', {
		'username' : $('#userName').val(),
		'status' : $('#status').val()
	});
	parent.$.messager.progress('close');
}
function addDayBorrow(){
	parent.$.modalDialog({
		title : '添加天标',
		width : 900,
		height : 400,
		href : '/system/dayborrow/addDayBorrowPage',
		buttons : [ {
			text : '添加',
			iconCls:'icon-save',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = dayList;
				var f = parent.$.modalDialog.handler.find('#addDayform');
				f.submit();
			}
		} ]
	});
	
}
</script>
</head>
<body>
	<div id="searchTool">
		<div style="float: left;">
			<a href="javascript:addDayBorrow();" class="easyui-linkbutton" iconCls="icon-add" plain="true">发布天标</a>
		</div>
		借款标题:<input type="text" id="userName" name="username"/>
		状态:
		<select id="status" name="status">
			<option value="" selected="selected">全部</option>
			<option value="1">正在招标的借款</option>
			<option value="2">初审未通过的借款</option>
			<option value="3">满标复审的标</option>
			<option value="4">流标</option>
			<option value="5">撤标</option>
		</select>
		<a href="javascript:queryDetail();" class="easyui-linkbutton" iconCls="icon-search" id="searchButton">查询</a>
	</div>
	<table id="list"></table>
</body>
</html>