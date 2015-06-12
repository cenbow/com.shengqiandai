var userEmpList;
$(function() {
	userEmpList = $("#userEmpList")
			.datagrid(
					{
						url : '/system/userEmp/finduserEmpList',
						fit : true,
						border : false,
						pagination : true,
						pageSize : 15,
						checkOnSelect : false,
						selectOnCheck : false,
						singleSelect : true,
						pageList : [ 10, 15, 20, 25, 30 ],
						frozenColumns : [ [
							{
								field : 'userName',
								title : '用户名',
								width : 80,
								align : 'center' 
							},
							{
								field : 'realName',
								title : '真实姓名',
								width : 80,
								align : 'center',
								formatter : function(value, row, index) {
									return value == null ? "-" : value;
								}
							},
							{
								field : 'phone',
								title : '用户手机',
								width : 100,
								align : 'center' 
							},
							{
								field : 'email',
								title : '邮箱',
								width : 150,
								align : 'center' 
							} ]],
						columns : [ [
								{
									field : 'addTime',
									title : '注册时间',
									width : 80,
									align : 'center' ,
									formatter : function(value, row, index) {
										return value = timetodate(row.addTime,"yyyy-MM-dd");
									}
								},
								{
									field : 'source',
									title : '客户来源',
									width : 180,
									align : 'center' ,
									formatter : function(value, row, index) {
										if(value == '自然客户'){
											return value = "无["+value+"]";
										} else {
											return value = row.sourceName +"["+value+"]";
										}
									}
								},
								{
									field : 'tenderSum',
									title : '投资总额',
									width : 70,
									align : 'center' 
								},
								{
									field : 'userMoney',
									title : '可用余额',
									width : 70,
									align : 'center' 
								},
								{
									field : 'total',
									title : '总额',
									width : 70,
									align : 'center' 
								},
								{
									field : 'checkYalidDate',
									title : '操作',
									align : 'center',
									formatter : function(value, row, index) {
										var v = '';
										if ($.canEdit) {
											v += "[<a href='javascript:edit("+ row.userId +")' style='color: black;text-decoration:none'>操作</a>]&nbsp;&nbsp;";
										}
										if ($.canCash) {
											v += '[<a id="'
												+ row.userId
												+ '" href="javascript:withdrawals('
												+ row.userId
												+ ');" style="color: black;text-decoration:none">提现记录</a>]&nbsp;&nbsp;';
										}
										if ($.canRecharge) {
											v += '[<a id="'
												+ row.userId
												+ '" href="javascript:recharge('
												+ row.userId
												+ ');" style="color: black;text-decoration:none">充值记录</a>]&nbsp;&nbsp;';
										}
										if ($.canTender) {
											v += '[<a id="'
												+ row.userId
												+ '" href="javascript:investment('
												+ row.userId
												+ ');" style="color: black;text-decoration:none">投资记录</a>]';
										}
										return value = v;
									}
								},
								{
									field : 'empName',
									title : '所属客服',
									align : 'center' 
								},
								{
									field : 'remark',
									title : '备注',
									width : 200,
									align : 'center'
								},
								{
									field : 'track',
									title : '跟踪',
									width : 200,
									align : 'center' 
								}
								] ],
						toolbar : '#userEmpquery',
						onLoadSuccess : function() {
							parent.$.messager.progress('close');
// parent.$.modalDialog.openner_dataGrid=userEmpList;
						}
					});
});
/**
 * 模糊查询
 * @returns
 */
function findUserEmp() {
		userEmpList.datagrid('load', {
		'username' : $('#userName').val(),
		'startTime' : $('#startTime').val(),
		'endTime' : $('#endTime').val(),
		'sourceName' : $('#sourceName').val(),
		'emp_id' : $('#emp_id').val()
	});
	parent.$.messager.progress('close');
}
/**
 * 点击查询充值记录
 * @param userId
 */
function recharge(userId){
	parent.$.modalDialog({
		title : '用户充值记录',
		width : 560,
		height : 400,
		href : '/system/userEmp/rechargeHt?userId=' + userId
	});
	parent.$.messager.progress('close');
}
/**
 * 点击查询用户提现记录
 * @param userId
 */
function withdrawals(userId){
	parent.$.modalDialog({
		title : '用户提现记录',
		width : 600,
		height : 350,
		href : '/system/userEmp/withdrawalsHtml?userId=' + userId
	});
	parent.$.messager.progress('close');
}
/**
 * 用户投资记录查询
 * @param userId
 */
function investment(userId){
	parent.$.modalDialog({
		title : '用户投资记录',
		width : 950,
		height : 350,
		href : '/system/userEmp/investment?userId=' + userId
	});
	parent.$.messager.progress('close');
}
/**
 * 备注操作
 * @param userId
 */
function edit(userId){
	parent.$.modalDialog({
		title : '操作',
		width : 420,
		height : 400,
		href : '/system/userEmp/editPage?userId='+userId,
		buttons : [ {
			text : '确定',
			iconCls:'icon-save',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = userEmpList;
				var f = parent.$.modalDialog.handler.find('#editForm');
				f.submit();
			}
		},{
			text : '关闭',
			handler : function() {
				parent.$.modalDialog.handler.dialog('close');
			}
		}]
	});
	parent.$.messager.progress('close');
}

//js时间戳转时间
Date.prototype.pattern = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours() == 0 ? 12 : this.getHours(), // 小时
		"H+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds()
	// 秒
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	if (/(E+)/.test(fmt)) {
		fmt = fmt
				.replace(
						RegExp.$1,
						((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f"
								: "\u5468")
								: ""));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
}
function timetodate(tim, dat) {
	return new Date(parseInt(tim) * 1000).pattern(dat); // "yyyy/MM/dd,hh,mm,ss"
}